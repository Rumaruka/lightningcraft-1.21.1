package com.rumaruka.lc.data;

import com.rumaruka.lc.LightningCraft;
import com.rumaruka.lc.data.recipes.LCRecipesProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static net.neoforged.fml.common.EventBusSubscriber.Bus.MOD;

@EventBusSubscriber(bus = MOD, modid = LightningCraft.MODID)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        dataGenerator.addProvider(event.includeServer(), new LCRecipesProvider(dataGenerator, provider));

    }
}
