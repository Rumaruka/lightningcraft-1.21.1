//package com.rumaruka.lc.common.config;
//
//import com.rumaruka.lc.LightningCraft;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.Mod;
//import net.neoforged.fml.event.config.ModConfigEvent;
//import net.neoforged.neoforge.common.ModConfigSpec;
//import org.checkerframework.checker.units.qual.C;
//
//@Mod.EventBusSubscriber(modid = LightningCraft.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
//public class LCConfig {
//    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
//    //CapabilitiesEnergy
//    public static final ModConfigSpec.IntValue CAPACITRY_ELECTRO_TOOLS = BUILDER.comment("Capacitry all electro tools").defineInRange("Capacitry: ",10_000,10, Integer.MAX_VALUE);
//    public static final ModConfigSpec SPEC = BUILDER.build();
//
//    public static int cap_tools;
//
//    @SubscribeEvent
//  public  static void onLoad(final ModConfigEvent event){
//        cap_tools = CAPACITRY_ELECTRO_TOOLS.get();
//    }
//}
