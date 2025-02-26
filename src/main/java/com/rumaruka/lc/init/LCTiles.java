package com.rumaruka.lc.init;

import com.rumaruka.lc.common.tiles.machines.InfuserBE;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCTiles {

    private static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);

    public static void setup(IEventBus bus) {
        TILES.register(bus);

    }

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<InfuserBE>> INFUSER_BE = TILES.register("infuser", () -> BlockEntityType.Builder.of(InfuserBE::new, LCBlocks.INFUSER.get()).build(null));


}
