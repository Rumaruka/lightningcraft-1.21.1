package com.rumaruka.lc.common.config;

import com.rumaruka.lc.LightningCraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.Nonnull;

public class LCConfig {
    public static final LCConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final ModConfigSpec.IntValue PER_USE_ENERGY;
    public final ModConfigSpec.IntValue LOST_ENERGY;


    public LCConfig(ModConfigSpec.Builder builder ) {
        this.PER_USE_ENERGY = builder.defineInRange("Use Energy for Tools", 100, 10, 100_000);
        this.LOST_ENERGY = builder.defineInRange("Lost Energy", 1000, 100, 100_000);
    }
    static {
        Pair<LCConfig, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(LCConfig::new);

        //Store the resulting values
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();




}
}
