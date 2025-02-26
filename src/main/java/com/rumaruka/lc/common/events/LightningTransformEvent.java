package com.rumaruka.lc.common.events;

import com.google.common.collect.Lists;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import com.rumaruka.lc.common.recipes.transform.TransformRecipeInput;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EventBusSubscriber
public class LightningTransformEvent {

    @SubscribeEvent
    public static void onTransformRecipe(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();


        if (!level.isClientSide() && entity instanceof ItemEntity item) {

            transform(item);

        }


    }

    private static void transform(ItemEntity entity) {
        var level = entity.level();
        if (!level.isClientSide()) {
            var region = new AABB(entity.getX() - 1, entity.getY() - 1, entity.getZ() - 1, entity.getX() + 1, entity.getY() + 1, entity.getZ() + 1);
            List<ItemEntity> itemEntities = level.getEntities(null, region).stream().filter(e -> e instanceof ItemEntity && !e.isRemoved()).map(e -> (ItemEntity) e).toList();
            for (var holder : level.getRecipeManager().getAllRecipesFor(TransformRecipe.RECIPE_TYPE)) {
                var recipe = holder.value();
                if (recipe.getIngredients().isEmpty()) continue;
                List<Ingredient> missingIngredients = Lists.newArrayList(recipe.getIngredients());
                Reference2IntMap<ItemEntity> consumedItems = new Reference2IntOpenHashMap<>(missingIngredients.size());                for (var itemEntity : itemEntities) {
                    final ItemStack other = itemEntity.getItem();
                    if (!other.isEmpty()) {
                        for (var it = missingIngredients.iterator(); it.hasNext(); ) {
                            Ingredient ing = it.next();
                            if (ing.test(other)) {
                                consumedItems.merge(itemEntity,1,Integer::sum);
                                it.remove();
                                break;
                            }
                        }
                    }
                }

                if (missingIngredients.isEmpty()) {
                    var items = new ArrayList<ItemStack>(consumedItems.size());
                    for (var e : consumedItems.reference2IntEntrySet()) {
                        var itemEntity = e.getKey();
                        items.add(itemEntity.getItem().split(e.getIntValue()));

                        if (itemEntity.getItem().getCount() <= 0) {
                            itemEntity.discard();
                        }
                    }

                    if (!level.isClientSide()) {
                        var recipeInput = new TransformRecipeInput(items);
                        var craftResult = recipe.assemble(recipeInput, level.registryAccess());

                        final ItemEntity newEntity = getItemEntity(entity, level, craftResult);
                        level.addFreshEntity(newEntity);

                    }


                }

            }
        }


    }

    private static @NotNull ItemEntity getItemEntity(ItemEntity entity, Level level, ItemStack craftResult) {
        var random = level.getRandom();
        final double x = Math.floor(entity.getX()) + .25d + random.nextDouble() * .5;
        final double y = Math.floor(entity.getY()) + .25d + random.nextDouble() * .5;
        final double z = Math.floor(entity.getZ()) + .25d + random.nextDouble() * .5;
        final double xSpeed = random.nextDouble() * .25 - 0.125;
        final double ySpeed = random.nextDouble() * .25 - 0.125;
        final double zSpeed = random.nextDouble() * .25 - 0.125;

        final ItemEntity newEntity = new ItemEntity(level, x, y, z, craftResult);

        newEntity.setDeltaMovement(xSpeed, ySpeed, zSpeed);
        return newEntity;
    }

}
