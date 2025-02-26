package com.rumaruka.lc.data.recipes;

import com.rumaruka.lc.common.items.tools.electro.LCItemAxe;
import com.rumaruka.lc.data.recipe_builder.InfuserRecipeBuilder;
import com.rumaruka.lc.data.recipe_builder.TransformRecipeBuilder;
import com.rumaruka.lc.init.LCBlocks;
import com.rumaruka.lc.init.LCItems;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LCRecipesProvider extends RecipeProvider {
    protected final DataGenerator generator;
    private final CompletableFuture<HolderLookup.Provider> registries;

    public LCRecipesProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(generator.getPackOutput(), pRegistries);
        this.generator = generator;
        this.registries = pRegistries;
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, LCItems.ELECTRO_SHOVEL_ITEM.get())
                .group("lc")
                .pattern(" e ")
                .pattern(" s ")
                .pattern(" s ")
                .define('e', LCItems.ELECTRO_IRON_INGOT.get())
                .define('s', Items.STICK)
                .unlockedBy("has_electro_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(LCItems.ELECTRO_IRON_INGOT.get()))
                .save(pRecipeOutput, LCUtils.rl("electro_shovel"));


        SimpleCookingRecipeBuilder.smelting(Ingredient.of(LCItems.ELECTRO_IRON_DUST.get()), RecipeCategory.MISC, LCItems.ELECTRO_IRON_INGOT .get(), 2.0F,150)
                .group("lc")
                .unlockedBy("has_electro_dust", InventoryChangeTrigger.TriggerInstance.hasItems(LCItems.ELECTRO_IRON_DUST.get()))
                .save(pRecipeOutput, LCUtils.rl("electro_iron_ingot"));






        //In Customs

        InfuserRecipeBuilder.infuser(Ingredient.of(Items.DIAMOND,Items.GOLD_INGOT), RecipeCategory.MISC, LCItems.ELECTRO_IRON_INGOT.get() ,1.0f,100)

                .group("lc")
                .unlockedBy("has_alt_electro", InventoryChangeTrigger.TriggerInstance.hasItems(LCBlocks.INFUSER.get()))
                .save(pRecipeOutput, LCUtils.rl("infuser_alt_electro"));





        TransformRecipeBuilder.transform(RecipeCategory.MISC, LCItems.ELECTRO_IRON_DUST.get(),2)
                .requires(Ingredient.of(Items.DIAMOND))
                .requires(Ingredient.of(Items.GOLD_INGOT))
                .requires(Ingredient.of(Items.REDSTONE))
                .group("lc")
                .unlockedBy("electro_dust",InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(pRecipeOutput,LCUtils.rl("electro_dust"));


        ;
    }
}
