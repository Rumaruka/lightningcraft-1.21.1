package com.rumaruka.lc.init;

import com.rumaruka.lc.LightningCraft;

import com.rumaruka.lc.api.lightning_energy_api.ILEStorage;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ItemCapability;
import org.jetbrains.annotations.Nullable;


//TODO: Fix Capabilities
public class LCCapability {

    public static final class LEEnergy {
        public static final BlockCapability<ILEStorage, @Nullable Direction> BLOCK = BlockCapability.createSided(create("le"), ILEStorage.class);
        public static final EntityCapability<ILEStorage, @Nullable Direction> ENTITY = EntityCapability.createSided(create("le"), ILEStorage.class);
        public static final ItemCapability<ILEStorage, Void> ITEM = ItemCapability.createVoid(create("le"), ILEStorage.class);

        private LEEnergy() {}
    }


    private static ResourceLocation create(String path) {
        return ResourceLocation.fromNamespaceAndPath(LightningCraft.MODID, path);
    }

}
