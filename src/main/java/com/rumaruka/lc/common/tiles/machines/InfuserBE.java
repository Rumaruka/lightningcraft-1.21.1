package com.rumaruka.lc.common.tiles.machines;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.common.recipes.infuser.InfuserRecipe;
import com.rumaruka.lc.common.tiles.base.LightningEnergyBlockEntity;
import com.rumaruka.lc.init.LCAttachment;
import com.rumaruka.lc.init.LCTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InfuserBE extends LightningEnergyBlockEntity {

    private boolean isWorked = false;
    private static int timeWork = 0;
    private static int tick = 0;

    public InfuserBE(BlockPos pPos, BlockState pBlockState) {
        super(LCTiles.INFUSER_BE.get(), pPos, pBlockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("infuser");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {

    }


    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, InfuserBE infuser) {
        tick++;
        if (tick % 20 == 0) {
            timeWork++;
            if (timeWork >= 200) {
                infuser.isWorked = true;
                if (infuser.isWorked()) {
                    //Make Recipes
                    infuser.isWorked = false;
                    for (var holder : pLevel.getRecipeManager().getAllRecipesFor(InfuserRecipe.RECIPE_TYPE)) {
                        var recipe = holder.value();
                        NonNullList<Ingredient> ingredients = recipe.getIngredients();

                    }
                }

            }
        }
    }


    private boolean checkLE() {
        LEStorage data = this.getData(LCAttachment.LE_ENERGY_INFUSER.get());
        return data.hasLE();
    }


    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return null;
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        return null;
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {

    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
    }

    @Override
    public void clearContent() {

    }

    public boolean isWorked() {
        return isWorked;
    }

    public void setWorked(boolean worked) {
        isWorked = worked;
    }
}
