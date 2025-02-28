package com.rumaruka.lc.init;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCDataComponent {
    private static final DeferredRegister.DataComponents DATA_COMPONENT = DeferredRegister.createDataComponents( MODID);

    public static final Supplier<DataComponentType<LEStorage>> LE_ENERGY_ITEM = DATA_COMPONENT.register(
            "le_energy", () -> DataComponentType.<LEStorage>builder().persistent(LEStorage.CODEC).networkSynchronized(LEStorage.STREAM_CODEC).build()
    );


    public static void setup(IEventBus bus) {
        DATA_COMPONENT.register(bus);
    }
}
