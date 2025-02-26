package com.rumaruka.lc.common.compat.jei.transform;

import com.rumaruka.lc.common.compat.jei.ModConts;
import com.rumaruka.lc.common.compat.jei.api.AbstractCategory;
import com.rumaruka.lc.common.compat.jei.api.AbstractCategoryAPI;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import com.rumaruka.lc.init.LCItems;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;

public class TransformCategory extends AbstractCategoryAPI<TransformRecipe> {
    public TransformCategory(IGuiHelper guiHelper) {
        super(guiHelper, LCItems.LIGHTNING.get(), "gui.jei.category.lightning");
    }


    @Override
    public RecipeType<RecipeHolder<TransformRecipe>> getRecipeType() {
        return ModConts.TRANSFORM;
    }

}
