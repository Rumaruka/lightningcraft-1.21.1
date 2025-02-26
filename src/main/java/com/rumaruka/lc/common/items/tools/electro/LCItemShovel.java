package com.rumaruka.lc.common.items.tools.electro;


import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.init.LCAttachment;
import com.rumaruka.lc.init.LCData;
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

public class LCItemShovel extends ShovelItem  implements IElectro{


    public LCItemShovel(Tier p_41336_, float damage, float speed) {
        super(p_41336_, new Properties().component(LCData.LE_ENERGY_ITEM.get(), new LEStorage(LCUtils.getMaxEnergyTools())).attributes(ShovelItem.createAttributes(p_41336_, damage, speed)));
    }


    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        var le = pStack.get(LCData.LE_ENERGY_ITEM.get());
        if (le != null) {
            LCUtils.tooltipLE(pTooltipComponents, le);
        }
    }


    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> onBroken) {
        var storage = stack.get(LCData.LE_ENERGY_ITEM.get());
        int finalUsed = Math.max(0, amount);

        if (storage != null) {
            storage.useLE(finalUsed);
        }

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
        var storage = stack.get(LCData.LE_ENERGY_ITEM.get());
        if (storage != null) {
            return Math.min(13 * storage.getLE() / storage.getMaxLE(), 13);
        }
        return 0;
    }


    @Override
    public void inventoryTick(ItemStack stack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {


        if (!pLevel.isClientSide()) {
            if (pEntity instanceof Player player) {
                boolean b = pLevel.getGameTime() % 200 == 0;
                boolean isCreative = player.getAbilities().instabuild;
                var storage = stack.get(LCData.LE_ENERGY_ITEM.get());


                if (b && !isCreative) {
                    if (storage != null) {
                        storage.useLE(10);
                    }
                }
            }

        }


    }


    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        var storage = pStack.get(LCData.LE_ENERGY_ITEM.get());
        if (storage != null && storage.getLE() == 0) {
            return 0;
        }
        return super.getDestroySpeed(pStack, pState);
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide()) {
            var storage = pStack.get(LCData.LE_ENERGY_ITEM.get());

            if (storage != null && storage.getLE() > 1) {
                storage.useLE(100);
                return true;
            }
        }


        return false;
    }


}
