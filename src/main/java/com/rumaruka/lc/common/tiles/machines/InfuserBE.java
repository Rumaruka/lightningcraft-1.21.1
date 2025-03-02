package com.rumaruka.lc.common.tiles.machines;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.common.tiles.base.LightningEnergyBlockEntity;
import com.rumaruka.lc.init.LCAttachmentTypes;
import com.rumaruka.lc.init.LCTiles;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class InfuserBE extends LightningEnergyBlockEntity {

    private boolean isWorked = false;
    private static int timeWork = 0;
    private static int tick = 0;
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    public InfuserBE(BlockPos pPos, BlockState pBlockState) {
        super(LCTiles.INFUSER_BE.get(), pPos, pBlockState, LCAttachmentTypes.LE_ENERGY_INFUSER.get(), new LEStorage(LCUtils.getMaxInfuserLE()));
    }


    @Override
    protected Component getDefaultName() {
        return Component.literal("infuser");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }



    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, InfuserBE infuser) {

//        tick++;
//
//        LEStorage le = infuser.getLe();
//        if (tick % 20 == 0) {
//            timeWork++;
//            if (timeWork >= 200) {
//                infuser.isWorked = true;
//                if (infuser.isWorked()) {
//                    //Make Recipes
//                    for (var holder : pLevel.getRecipeManager().getAllRecipesFor(InfuserRecipe.RECIPE_TYPE)) {
//                        var recipe = holder.value();
//                        NonNullList<Ingredient> ingredients = recipe.getIngredients();
//
//                        if (ingredients.isEmpty()) continue;
//
//                        if (le.hasLE()) {
//
//
//
//
//                            for (int i = 0; i < 4; i++) {
//                                ItemStack input = infuser.items.get(i);
//                                if (input.isEmpty()) {
//                                    le.useLE(100);
//                                    infuser.items.set(i, recipe.getResultItem(pLevel.registryAccess()).copy());
//                                    break;
//                                }
//                            }
//
//
//                            infuser.isWorked = false;
//                        }
//                    }
//
//                }
//
//            }
//        }
    }


    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return null;
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return items.get(pSlot);
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return items.remove(pSlot);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        return items.remove(pSlot);
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
