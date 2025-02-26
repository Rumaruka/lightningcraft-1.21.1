package com.rumaruka.lc.common.compat.jei;

import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.common.recipes.transform.TransformRecipe;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ModConts {
    public static final String TEXTURE_GUI_PATH = "textures/jei/";
    public static final String TEXTURE_GUI_VANILLA = ModConts.TEXTURE_GUI_PATH + "gui.png";

    public static final ResourceLocation RECIPE_GUI_VANILLA = ResourceLocation.fromNamespaceAndPath(LightningCraft.MODID, TEXTURE_GUI_VANILLA);
    public static final int MAX_TOOLTIP_WIDTH = 150;

    public static final ResourceLocation UNIVERSAL_RECIPE_TRANSFER_UID =ResourceLocation.fromNamespaceAndPath(ModIds.JEI_ID, "universal_recipe_transfer_handler");
    public static final ResourceLocation LOCATION_JEI_GUI_TEXTURE_ATLAS =ResourceLocation.fromNamespaceAndPath(ModIds.JEI_ID, "textures/atlas/gui.png");
    public static final RecipeType<RecipeHolder<TransformRecipe>> TRANSFORM = create(LightningCraft.MODID,"transform",TransformRecipe.class);
    public static <R extends Recipe<?>> RecipeType<RecipeHolder<R>> create(String modid, String name, Class<? extends R> recipeClass) {
        ResourceLocation uid =ResourceLocation.fromNamespaceAndPath(modid, name);
        @SuppressWarnings({"unchecked", "RedundantCast"})
        Class<? extends RecipeHolder<R>> holderClass = (Class<? extends RecipeHolder<R>>) (Object) RecipeHolder.class;
        return new RecipeType<>(uid, holderClass);
    }
}
