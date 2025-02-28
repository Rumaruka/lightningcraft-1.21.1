package com.rumaruka.lc.common.items.tools;

import com.rumaruka.lc.common.events.LightningCheckEvent;
import com.rumaruka.lc.init.LCItems;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class LightningWand extends Item {
    public LightningWand(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            var hitResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.NONE);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                var pos = hitResult.getBlockPos();
                LCUtils.damageStack(1, itemInHand, pPlayer, EquipmentSlot.MAINHAND);
                LCUtils.lightning(pos, pLevel,pPlayer, LightningCheckEvent.isStuck);
            }
            return InteractionResultHolder.success(itemInHand);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}
