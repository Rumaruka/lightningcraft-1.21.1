package com.rumaruka.lc.data.recipe_builder;

import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.api.recipe.AbstractCustomRecipe;
import com.rumaruka.lc.api.recipe.AbstractSingleIngredientRecipe;
import com.rumaruka.lc.common.recipes.infuser.InfuserRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class InfuserRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final ItemStack stackResult; // Neo: add stack result support
    private final Ingredient ingredient;
    private final float experience;
    private final int cookingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @javax.annotation.Nullable
    private String group;
    private final InfuserRecipe.Factory<?> factory;

    private InfuserRecipeBuilder(
            RecipeCategory pCategory,
            ItemLike pResult,
            Ingredient pIngredient,
            float pExperience,
            int pCookingTime,
            InfuserRecipe.Factory<?> pFactory
    ) {
        this(pCategory,  new ItemStack(pResult), pIngredient, pExperience, pCookingTime, pFactory);
    }

    private InfuserRecipeBuilder(
            RecipeCategory p_251345_,
            ItemStack result,
            Ingredient p_250362_,
            float p_251204_,
            int p_250189_,
            InfuserRecipe.Factory<?> p_311960_
    ) {
        this.category = p_251345_;

        this.result = result.getItem();
        this.stackResult = result;
        this.ingredient = p_250362_;
        this.experience = p_251204_;
        this.cookingTime = p_250189_;
        this.factory = p_311960_;
    }

    public static <T extends InfuserRecipe> InfuserRecipeBuilder generic(
            Ingredient pIngredient,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            RecipeSerializer<T> pCookingSerializer,
            InfuserRecipe.Factory<T> pFactory
    ) {
        return new InfuserRecipeBuilder(pCategory, pResult, pIngredient, pExperience, pCookingTime, pFactory);
    }


    @Override
    public RecipeBuilder unlockedBy(String pName, Criterion<?> pCriterion) {
        this.criteria.put(pName, pCriterion);
        return this;
    }

    public static InfuserRecipeBuilder infuser(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime) {
        return new InfuserRecipeBuilder(
                pCategory, pResult, pIngredient, pExperience, pCookingTime, InfuserRecipe::new
        );
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        this.ensureValid(pId);
        Advancement.Builder advancement$builder = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        InfuserRecipe abstractcookingrecipe = new InfuserRecipe(this.group,this.ingredient,this.stackResult,this.experience,this.cookingTime);
        pRecipeOutput.accept(pId, abstractcookingrecipe, advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

}
