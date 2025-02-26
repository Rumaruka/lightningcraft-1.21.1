package com.rumaruka.lc.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCEntity {

    private static final DeferredRegister<EntityType<?>> ENTITYS = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);

    public static void setup(IEventBus bus) {
        ENTITYS.register(bus);

    }

//  public static final DeferredHolder<EntityType<?>, EntityType<LightningBoltLC>> LIGHTNING_BOLT_LC = ENTITYS.register("lightning_bolt", ()-> EntityType.Builder.<LightningBoltLC>of(LightningBoltLC::new, MobCategory.MISC).noSave()
//          .sized(0.0F, 0.0F)
//          .clientTrackingRange(16)
//          .updateInterval(Integer.MAX_VALUE).build(null));


}
