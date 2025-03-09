package com.rumaruka.lc.common.items.tools.electro;


import com.rumaruka.lc.api.lightning_energy_api.LEStorage;

import com.rumaruka.lc.common.config.LCConfig;
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
import java.util.Random;
import java.util.function.Consumer;

public class LCItemPickaxe extends PickaxeItem implements IElectro {




    public LCItemPickaxe(Tier p_41336_, float damage, float speed) {
        super(p_41336_, new Item.Properties()
                .component(LCDataComponent.LE_ENERGY.get(),0)
                .component(LCDataComponent.LE_ENERGY_MAX.get(), LCUtils.getMaxEnergyTools())
                .attributes(AxeItem.createAttributes(p_41336_, damage, speed)));


    }




    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (pStack.has(LCDataComponent.LE_ENERGY.get())&&pStack.has(LCDataComponent.LE_ENERGY_MAX.get())){
            Integer LE = pStack.get(LCDataComponent.LE_ENERGY.get());
            Integer j = pStack.get(LCDataComponent.LE_ENERGY_MAX.get());
            if (LE != null && j != null) {
                LCUtils.tooltipLE(pTooltipComponents, LE, j);

            }
        }


    }


    public void setLE(ItemStack stack, int amount) {
        if (stack.has(LCDataComponent.LE_ENERGY.get())) {
            stack.set(LCDataComponent.LE_ENERGY.get(),amount);
        }
    }
    public void addLE(ItemStack stack, int amount) {
        Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
        if (i != null) {
            setLE(stack, i+amount);
        }

    }
    public void useLE(ItemStack stack, int amount) {
        Integer i = stack.get(LCDataComponent.LE_ENERGY.get());

        if (i != null) {
            if (i!=0){
                setLE(stack, i-amount);
            }

        }

    }
    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {

        int finalUsed = Math.max(0, amount);


        useLE(stack,finalUsed);


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
        Integer le=  stack.get(LCDataComponent.LE_ENERGY.get());
        Integer le_max=stack.get(LCDataComponent.LE_ENERGY_MAX.get());
        if (le!=null&&le_max!=null)
        {
            return Math.min(13 * le / le_max, 13);

        }
        return 0;

    }


    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {


        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                boolean b = LCUtils.isSecondGone(pLevel,5);
                boolean isCreative = player.getAbilities().instabuild;


                if (b && !isCreative) {

                    useLE(stack,1000);
                    Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
                    if (i!=null&&i<0){
                        setLE(stack,0);
                    }
                }

            }

        }


    }


    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        Integer i = pStack.get(LCDataComponent.LE_ENERGY.get());
        if (i!=null&&i == 0) {
            return 0;
        }
        return super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide()) {
            Integer i = pStack.get(LCDataComponent.LE_ENERGY.get());

            if (i!=null&&i >1) {
                useLE(pStack,100);
                return true;
            }
        }


        return false;
    }



}
