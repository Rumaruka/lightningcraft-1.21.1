package com.rumaruka.lc.init;


import com.rumaruka.lc.common.recipes.crusher.CrusherRecipe;
import com.rumaruka.lc.common.recipes.crusher.CrusherRecipeSerializer;
import com.rumaruka.lc.common.recipes.infuser.InfuserRecipe;
import com.rumaruka.lc.common.recipes.infuser.InfuserRecipeSerializer;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import com.rumaruka.lc.common.recipes.transform.TransformRecipeSerializer;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCRecipes {

   private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);

   public static void setup(IEventBus bus) {
       RECIPE_SERIALIZER.register(bus);

   }

  public static final DeferredHolder<RecipeSerializer<?>, TransformRecipeSerializer> TRANSFORM = RECIPE_SERIALIZER.register("transform", TransformRecipeSerializer::new);
  public static final DeferredHolder<RecipeSerializer<?>, CrusherRecipeSerializer<CrusherRecipe>> CRUSHER = RECIPE_SERIALIZER.register("crusher", () -> new CrusherRecipeSerializer<>(CrusherRecipe::new,100));
  public static final DeferredHolder<RecipeSerializer<?>, InfuserRecipeSerializer<InfuserRecipe>> INFUSHER = RECIPE_SERIALIZER.register("infuser", () -> new InfuserRecipeSerializer<>(InfuserRecipe::new,100));

}
