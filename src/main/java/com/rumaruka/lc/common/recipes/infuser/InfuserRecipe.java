package com.rumaruka.lc.common.recipes.infuser;


import com.rumaruka.lc.api.recipe.AbstractSingleIngredientRecipe;


import com.rumaruka.lc.init.LCRecipes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class InfuserRecipe extends AbstractSingleIngredientRecipe {

    public static final RecipeType<InfuserRecipe> RECIPE_TYPE = new RecipeType<InfuserRecipe>() {
        @Override
        public String toString() {
            return "lc:infuser";
        }
    };

    public InfuserRecipe( String p_249518_, Ingredient p_251354_, ItemStack p_252185_, float p_252165_, int p_250256_) {
        super(RECIPE_TYPE, p_249518_, p_251354_, p_252185_, p_252165_, p_250256_);
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return LCRecipes.INFUSHER.get();
    }
}
