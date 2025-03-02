package com.rumaruka.lc.common.items.tools.electro;


import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.init.LCDataComponent;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class LCItemAxe extends AxeItem implements IElectro {


    protected LEStorage energy;

    public LCItemAxe(Tier p_41336_, float damage, float speed) {
        super(p_41336_, new Item.Properties()
                .component(LCDataComponent.LE_ENERGY_ITEM.get(),new LEStorage(LCUtils.getMaxEnergyTools()))
                .durability(new LEStorage(LCUtils.getMaxEnergyTools()).getLE())
                .attributes(AxeItem.createAttributes(p_41336_, damage, speed)));

         energy= new LEStorage(LCUtils.getMaxEnergyTools());
    }

    public LEStorage getLE() {
        return energy;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

            LCUtils.tooltipLE(pTooltipComponents, energy);

    }




    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {

        int finalUsed = Math.max(0, amount);


            energy.useLE(finalUsed);


        return 0;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Color.BLUE.getRGB();
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {

        return true;

    }

    @Override
    public int getBarWidth(ItemStack stack) {

            return Math.min(13 * energy.getLE() / energy.getMaxLE(), 13);

    }


    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {


        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                boolean b = pLevel.getGameTime() % 200 == 0;
                boolean isCreative = player.getAbilities().instabuild;


                if (b && !isCreative) {

                        energy.useLE(1000);
                    }

            }

        }


    }


    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {

        if (energy.getLE() == 0) {
            return 0;
        }
        return super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide()) {


            if (energy.getLE() > 1) {
                energy.useLE(100);
                return true;
            }
        }


        return false;
    }



}
