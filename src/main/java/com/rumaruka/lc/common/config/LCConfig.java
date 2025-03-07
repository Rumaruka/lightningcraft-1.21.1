package com.rumaruka.lc.common.config;

import com.rumaruka.lc.LightningCraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.C;

public class LCConfig {
    public static final LCConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final ModConfigSpec.IntValue capabiltyTools;


    public LCConfig(ModConfigSpec.Builder builder ) {
        this.capabiltyTools = builder.defineInRange("Capabilty Tools", 10_000, 1000, Integer.MAX_VALUE);
    }
    static {
        Pair<LCConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(LCConfig::new);

        //Store the resulting values
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
}
