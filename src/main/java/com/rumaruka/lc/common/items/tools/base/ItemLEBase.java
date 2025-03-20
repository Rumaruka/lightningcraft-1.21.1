package com.rumaruka.lc.common.items.tools.base;

import com.rumaruka.lc.common.items.tools.electro.IElectro;
import com.rumaruka.lc.init.LCDataComponent;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.List;

public abstract class ItemLEBase extends Item implements IElectro {


    public ItemLEBase(Properties properties) {
        super(properties);
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
        Integer le = stack.get(LCDataComponent.LE_ENERGY.get());
        Integer le_max = stack.get(LCDataComponent.LE_ENERGY_MAX.get());
        if (le != null && le_max != null) {
            return Math.min(13 * le / le_max, 13);

        }
        return 0;

    }


    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {


        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                boolean b = LCUtils.isSecondGone(pLevel, 5);
                boolean isCreative = player.getAbilities().instabuild;


                if (b && !isCreative) {

                    useLE(stack, 1000);
                    Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
                    if (i != null && i < 0) {
                        setLE(stack, 0);
                    }
                }

            }

        }


    }

    public  int getLE(ItemStack stack) {
        if (stack.has(LCDataComponent.LE_ENERGY.get())) {

            Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
            if (i == null) return 0;
            return i;
        }
        return 0;
    }


public void setLE(ItemStack stack, int amount) {
    if (stack.has(LCDataComponent.LE_ENERGY.get())) {
        stack.set(LCDataComponent.LE_ENERGY.get(), amount);
    }
}

public void addLE(ItemStack stack, int amount) {
    Integer i = stack.get(LCDataComponent.LE_ENERGY.get());
    if (i != null) {
        setLE(stack, i + amount);
    }

}

public void useLE(ItemStack stack, int amount) {
    Integer i = stack.get(LCDataComponent.LE_ENERGY.get());

    if (i != null) {
        if (i != 0) {
            setLE(stack, i - amount);
            LCUtils.leEnergyDontMore(stack);
        }
    }

}

@Override
public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
    if (pStack.has(LCDataComponent.LE_ENERGY.get()) && pStack.has(LCDataComponent.LE_ENERGY_MAX.get())) {
        Integer LE = pStack.get(LCDataComponent.LE_ENERGY.get());
        Integer j = pStack.get(LCDataComponent.LE_ENERGY_MAX.get());
        if (LE != null && j != null) {
            LCUtils.tooltipLE(pTooltipComponents, LE, j);

        }
    }


}
}
