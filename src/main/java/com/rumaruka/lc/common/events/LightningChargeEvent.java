package com.rumaruka.lc.common.events;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.common.tiles.machines.InfuserBE;
import com.rumaruka.lc.init.LCAttachment;
import com.rumaruka.lc.init.LCBlocks;
import com.rumaruka.lc.init.LCData;
import com.rumaruka.lc.misc.RandomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;

import java.util.Random;

@EventBusSubscriber
public class LightningChargeEvent {

    private static final Random rnd = new Random();

    @SubscribeEvent
    public static void onChargeLE(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        LightningBolt lightning = event.getLightning();

        if (!level.isClientSide()) {
            if (entity instanceof Player player) {

                for (ItemStack stack : player.getInventory().items) {
                    if (stack.has(LCData.LE_ENERGY_ITEM.get())) {
                        LEStorage data = stack.get(LCData.LE_ENERGY_ITEM.get());
                        if (data != null) {
                            data.addLE(rnd.nextInt(500));
                        }
                    }

                }
                BlockState blockStateOn = lightning.getBlockStateOn();
                if (blockStateOn.is(LCBlocks.INFUSER.get())) {
                    int blockX = lightning.getBlockX();
                    int blockY = lightning.getBlockY();
                    int blockZ = lightning.getBlockZ();
                    BlockEntity blockEntity = level.getBlockEntity(new BlockPos(blockX, blockY, blockZ));
                    if (blockEntity instanceof InfuserBE infuser) {
                        LEStorage data = infuser.getData(LCAttachment.LE_ENERGY_INFUSER.get());

                        data.addLE(RandomUtil.randomNumberBetween(1, 5));


                    }
                }
            }
        }
    }
}

