package com.rumaruka.lc.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class LCConfig {
    public static final LCConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final ModConfigSpec.IntValue PER_USE_ENERGY;
    public final ModConfigSpec.IntValue LOST_ENERGY;


    public LCConfig(ModConfigSpec.Builder builder) {
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
