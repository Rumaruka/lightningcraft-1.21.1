package com.rumaruka.lc.api.recipe;


import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public abstract class AbstractSingleIngredientRecipe implements Recipe<SingleRecipeInput> {
    protected final RecipeType<?> type;

    public final String group;
    public final Ingredient ingredient;
    public final ItemStack result;
    public final float experience;
    public final int cookingTime;

    public AbstractSingleIngredientRecipe(RecipeType<?> p_250197_, String p_249518_, Ingredient p_251354_, ItemStack p_252185_, float p_252165_, int p_250256_) {
        this.type = p_250197_;

        this.group = p_249518_;
        this.ingredient = p_251354_;
        this.result = p_252185_;
        this.experience = p_252165_;
        this.cookingTime = p_250256_;
    }

    public boolean matches(SingleRecipeInput p_43748_, Level p_43749_) {

        return this.ingredient.test(p_43748_.getItem(0));
    }

    public ItemStack assemble(SingleRecipeInput p_43746_,  HolderLookup.Provider  p_267063_) {


        return this.result.copy();
    }

    public boolean canCraftInDimensions(int p_43743_, int p_43744_) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getResultItem(HolderLookup.Provider p_266851_) {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public RecipeType<?> getType() {
        return this.type;
    }


    public interface Factory<T extends AbstractSingleIngredientRecipe> {
        T create(String var1, Ingredient var3, ItemStack var4, float var5, int var6);
    }
}