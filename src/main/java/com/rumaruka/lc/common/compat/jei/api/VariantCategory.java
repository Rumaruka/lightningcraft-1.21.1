package com.rumaruka.lc.common.compat.jei.api;



import com.rumaruka.lc.common.compat.jei.ModConts;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;

public abstract class VariantCategory<T> implements IRecipeCategory<T> {

    //protected final IDrawableStatic staticFlame;
    //protected final IDrawableAnimated animatedFlame;

    public VariantCategory(IGuiHelper guiHelper) {
       // staticFlame = guiHelper.createDrawable(ModConts.RECIPE_GUI_VANILLA, 82, 114, 14, 14);
       // animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);
    }
}
