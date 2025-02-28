package com.rumaruka.lc.common.recipes.transform;

import com.rumaruka.lc.api.recipe.APITransferRecipe;
import com.rumaruka.lc.init.LCRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Collections;
import java.util.Map;

public class TransformRecipe extends APITransferRecipe {

    public static final RecipeType<TransformRecipe> RECIPE_TYPE = new RecipeType<TransformRecipe>() {
        @Override
        public String toString() {
            return "lc:transform";
        }
    };


    // Initialized by reload listener
    public static Map<ResourceLocation, TransformRecipe> recipeList = Collections.emptyMap();

    public TransformRecipe(String name, NonNullList<Ingredient> p_251354_, ItemStack p_252185_) {
        super(RECIPE_TYPE, name, p_251354_, p_252185_);
    }

    public TransformRecipe(NonNullList<Ingredient> p_251354_, ItemStack p_252185_) {
        super(RECIPE_TYPE, "group", p_251354_, p_252185_);
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return LCRecipes.TRANSFORM.get();
    }


}
