package com.rumaruka.lc.common.compat.jei.api;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.rumaruka.lc.api.recipe.AbstractCustomRecipe;
import com.rumaruka.lc.common.compat.jei.ModConts;
import com.rumaruka.lc.misc.LCUtils;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.common.Constants;
import mezz.jei.library.util.RecipeUtil;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.phys.Vec2;

import static mezz.jei.api.recipe.RecipeIngredientRole.INPUT;
import static mezz.jei.api.recipe.RecipeIngredientRole.OUTPUT;

public abstract class AbstractSingleIngredientCategory<T extends AbstractCustomRecipe> extends VariantCategory<RecipeHolder<T>> {
    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;
    private final LoadingCache<Integer, IDrawableAnimated> cachedArrows;

    public AbstractSingleIngredientCategory(IGuiHelper guiHelper, Item icon, String translationKey) {
        super(guiHelper);
        this.background = guiHelper.createDrawable(ModConts.RECIPE_GUI_VANILLA, 0, 114, 82, 54);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(icon));
        this.localizedName = Component.translatable(translationKey);
        this.cachedArrows = CacheBuilder.newBuilder().maximumSize(25L).build(new CacheLoader<Integer, IDrawableAnimated>() {
            public IDrawableAnimated load(Integer cookTime) {
                return guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 82, 128, 24, 17).buildAnimated(100, IDrawableAnimated.StartDirection.LEFT, false);
            }
        });
    }


    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(RecipeHolder<T> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        IDrawableAnimated arrow = getArrow(recipe);
        arrow.draw(guiGraphics, 24, 18);


    }

    protected IDrawableAnimated getArrow(RecipeHolder<T> recipeHolder) {

        return this.cachedArrows.getUnchecked(100);
    }


    @Override
    public Component getTitle() {
        return localizedName;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<T> recipe, IFocusGroup focuses) {
        double angleBetweenEach = 360.0 / recipe.value().getIngredients().size();
        Vec2 point = new Vec2(10, 5), center = new Vec2(10, 20);
        for (var ingr : recipe.value().getIngredients()) {
            builder.addSlot(INPUT, (int) point.x, (int) point.y)
                    .addIngredients(ingr);
            point = LCUtils.rotatePointAbout(point, center, angleBetweenEach);

        }

        builder.addSlot(OUTPUT, 61, 19)
                .addItemStack(RecipeUtil.getResultItem(recipe.value()));
    }
}
