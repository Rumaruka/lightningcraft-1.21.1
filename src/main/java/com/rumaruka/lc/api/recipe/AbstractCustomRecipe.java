package com.rumaruka.lc.api.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractCustomRecipe implements Recipe<RecipeInput> {
    protected final RecipeType<?> type;

    public final String group;
    public final NonNullList<Ingredient> ingredients;

    public final ItemStack result;


    public AbstractCustomRecipe(RecipeType<?> p_250197_, String p_249518_, NonNullList<Ingredient> p_251354_, ItemStack p_252185_) {
        this.type = p_250197_;

        this.group = p_249518_;

        this.ingredients = p_251354_;
        this.result = p_252185_;

    }

    public boolean matches(RecipeInput inv, Level p_43749_) {

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

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }


    public ItemStack assemble(RecipeInput p_43746_,  HolderLookup.Provider  p_267063_) {


        return this.result.copy();
    }

    public boolean canCraftInDimensions(int p_43743_, int p_43744_) {
        return true;
    }


    public ItemStack getResultItem(HolderLookup.Provider p_266851_) {
        return this.result;
    }

    public ItemStack getResult() {
        return result;
    }

    public String getGroup() {
        return this.group;
    }


    public RecipeType<?> getType() {
        return this.type;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}