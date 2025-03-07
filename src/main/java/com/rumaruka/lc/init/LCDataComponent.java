package com.rumaruka.lc.init;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.util.ExtraCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCDataComponent {
    private static final DeferredRegister.DataComponents DATA_COMPONENT = DeferredRegister.createDataComponents( MODID);

    public static final Supplier<DataComponentType<Integer>> LE_ENERGY_MAX = DATA_COMPONENT.registerComponentType(
            "le_capacity", p_335177_ -> p_335177_.persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT)
    );
    public static final Supplier<DataComponentType<Integer>> LE_ENERGY =  DATA_COMPONENT.registerComponentType(
            "le_energy", p_331382_ -> p_331382_.persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT)
    );
    public static void setup(IEventBus bus) {
        DATA_COMPONENT.register(bus);
    }
}
