package com.rumaruka.lc.common.recipes.crusher;

import com.rumaruka.lc.api.recipe.AbstractSingleIngredientRecipe;

import com.rumaruka.lc.init.LCRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class CrusherRecipe extends AbstractSingleIngredientRecipe {

    public static final RecipeType<CrusherRecipe> RECIPE_TYPE = new RecipeType<CrusherRecipe>() {
        @Override
        public String toString() {
            return "lc:crusher";
        }
    };


    public CrusherRecipe( String p_249518_, Ingredient p_251354_, ItemStack p_252185_, float p_252165_, int p_250256_) {
        super(RECIPE_TYPE, p_249518_, p_251354_, p_252185_, p_252165_, p_250256_);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return LCRecipes.CRUSHER.get();
    }
}
