package com.rumaruka.lc.api.recipe;

import com.rumaruka.lc.common.recipes.transform.TransformRecipeInput;
import lombok.Getter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public abstract class APITransferRecipe implements Recipe<TransformRecipeInput> {
    protected final RecipeType<?> type;

    public final String group;
    public final NonNullList<Ingredient> ingredients;

    public final ItemStack result;


    public APITransferRecipe(RecipeType<?> p_250197_, String p_249518_, NonNullList<Ingredient> p_251354_, ItemStack p_252185_) {
        this.type = p_250197_;

        this.group = p_249518_;

        this.ingredients = p_251354_;
        this.result = p_252185_;

    }

    public boolean matches(TransformRecipeInput inv, Level p_43749_) {

        List<Ingredient> stacks = new ArrayList<>(getIngredients());
        for (int i = 1; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                boolean flag = false;
                Iterator<Ingredient> itr = stacks.iterator();
                while (itr.hasNext()) {
                    Ingredient ingredient = itr.next();
                    if (ingredient.test(stack)) {
                        flag = true;
                        itr.remove();
                        break;
                    }
                }
                if (!flag) {
                    return false;
                }
            }
        }
        return stacks.isEmpty();

    }


    public ItemStack assemble(TransformRecipeInput p_43746_, HolderLookup.Provider p_267063_) {


        return this.result.copy();
    }

    public boolean canCraftInDimensions(int p_43743_, int p_43744_) {
        return true;
    }


    public ItemStack getResultItem(HolderLookup.Provider p_266851_) {
        return this.result;
    }


    @Override
    public boolean isSpecial() {
        return true;
    }
}