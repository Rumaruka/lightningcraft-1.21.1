package com.rumaruka.lc.data.recipe_builder;

import com.rumaruka.lc.common.recipes.infuser.InfuserRecipe;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class TransformRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final ItemStack stackResult; // Neo: add stack result support
    private final NonNullList<Ingredient> ingredient= NonNullList.create();
    private final int count;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @javax.annotation.Nullable
    private String group;

    private TransformRecipeBuilder(
            RecipeCategory pCategory,
            ItemLike pResult,
            int count
        

    ) {
        this(pCategory,  new ItemStack(pResult,count));
    }

    private TransformRecipeBuilder(
            RecipeCategory p_251345_,
            ItemStack result
           
           
    ) {
        this.category = p_251345_;
        this.result = result.getItem();
        this.count=result.getCount();
        this.stackResult = result;
    

    }
    public static TransformRecipeBuilder transform(RecipeCategory pCategory, ItemLike pResult, int count) {
        return new TransformRecipeBuilder(
                pCategory, pResult, count
        );
    }
    public static <T extends TransformRecipe> TransformRecipeBuilder generic(

            RecipeCategory pCategory,
            ItemLike pResult,
            int count

    ) {
        return new TransformRecipeBuilder(pCategory, pResult,count);
    }

    @Override
    public RecipeBuilder unlockedBy(String pName, Criterion<?> pCriterion) {
        this.criteria.put(pName, pCriterion);
        return this;
    }

    /**
     * Adds an ingredient that can be any item in the given tag.
     */
    public TransformRecipeBuilder requires(TagKey<Item> pTag) {
        return this.requires(Ingredient.of(pTag));
    }

    /**
     * Adds an ingredient of the given item.
     */
    public TransformRecipeBuilder requires(ItemLike pItem) {
        return this.requires(pItem, 1);
    }

    /**
     * Adds the given ingredient multiple times.
     */
    public TransformRecipeBuilder requires(ItemLike pItem, int pQuantity) {
        for(int i = 0; i < pQuantity; ++i) {
            this.requires(Ingredient.of(pItem));
        }

        return this;
    }

    /**
     * Adds an ingredient.
     */
    public TransformRecipeBuilder requires(Ingredient pIngredient) {
        return this.requires(pIngredient, 1);
    }
    
    public TransformRecipeBuilder requires(Ingredient pIngredient, int pQuantity) {
        for(int i = 0; i < pQuantity; ++i) {
            this.ingredient.add(pIngredient);
        }

        return this;
    }
    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public void save(RecipeOutput pRecipeOutput, ResourceLocation pId) {
        this.ensureValid(pId);
        Advancement.Builder advancement$builder = pRecipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pId))
                .rewards(AdvancementRewards.Builder.recipe(pId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        TransformRecipe abstractcookingrecipe = new TransformRecipe(this.group,this.ingredient,this.stackResult);
        pRecipeOutput.accept(pId, abstractcookingrecipe, advancement$builder.build(pId.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation pId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }
}
