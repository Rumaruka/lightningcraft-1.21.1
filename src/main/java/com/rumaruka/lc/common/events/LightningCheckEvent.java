package com.rumaruka.lc.common.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import org.openjdk.nashorn.internal.objects.annotations.Getter;
import org.openjdk.nashorn.internal.objects.annotations.Setter;

@EventBusSubscriber
public class LightningCheckEvent {



    public static boolean isStuck;

    @SubscribeEvent
    public static void onStruckLightning(EntityStruckByLightningEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player ) {
            isStuck=true;
        }

    }
}

