package com.rumaruka.lc.common.events;


import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.init.LCData;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.DispenserMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import org.openjdk.nashorn.internal.objects.annotations.Getter;
import org.openjdk.nashorn.internal.objects.annotations.Setter;

import java.util.Random;

@EventBusSubscriber
public class LightningTickEvent {

    private static final Random rnd = new Random();


    @SubscribeEvent
    public static void onChestLELost(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (entity instanceof Player player) {
                boolean second_10 = LCUtils.isSecondGone(level, 1);


                containersLosts(player, second_10);
                if (ModList.get().isLoaded("ae2")) {
                    // integrationAE(player, second_10);
                }

            }

        }
    }

//    private static void integrationAE(Player player, boolean time) {
//
//        if (player.containerMenu instanceof SkyChestMenu menu) {
//            for (int i = 0; i < menu.getItems().size(); i++) {
//                ItemStack stack = menu.getItems().get(i);
//                if (stack.hasData(LCAttachment.LE_ENERGY.get())) {
//                    if (time) {
//                        LEStorage data = stack.getData(LCAttachment.LE_ENERGY.get());
//                        data.useLE(rnd.nextInt(500));
//                    }
//
//                }
//            }
//        }
//
//    }

    private static void containersLosts(Player player, boolean time) {
        if (player.containerMenu instanceof ChestMenu menu) {
            for (int i = 0; i < menu.getContainer().getContainerSize(); i++) {
                ItemStack stack = menu.getContainer().getItem(i);
                if (stack.has(LCData.LE_ENERGY_ITEM.get())) {

                    if (time) {
                        LEStorage data = stack.get(LCData.LE_ENERGY_ITEM.get());
                        if (data != null) {
                            data.useLE(1);
                        }
                    }

                }
            }
        }
        if (player.containerMenu instanceof DispenserMenu menu) {
            for (int i = 0; i < menu.getItems().size(); i++) {
                ItemStack stack = menu.getItems().get(i);
                if (stack.has(LCData.LE_ENERGY_ITEM.get())) {

                    if (time) {
                        LEStorage data = stack.get(LCData.LE_ENERGY_ITEM.get());
                        if (data != null) {
                            data.useLE(rnd.nextInt(500));
                        }
                    }

                }
            }
        }
        if (player.containerMenu instanceof HopperMenu menu) {
            for (int i = 0; i < menu.getItems().size(); i++) {
                ItemStack stack = menu.getItems().get(i);
                if (stack.has(LCData.LE_ENERGY_ITEM.get())) {

                    if (time) {
                        LEStorage data = stack.get(LCData.LE_ENERGY_ITEM.get());
                        if (data != null) {
                            data.useLE(rnd.nextInt(500));
                        }
                    }

                }
            }
        }
    }


}













