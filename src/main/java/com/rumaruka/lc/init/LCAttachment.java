package com.rumaruka.lc.init;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.misc.LCUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCAttachment {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    // Serialization via INBTSerializable
    public static final Supplier<AttachmentType<ItemStackHandler>> HANDLER = ATTACHMENT_TYPES.register(
            "handler", () -> AttachmentType.serializable(() -> new ItemStackHandler(1)).build()
    );
    // Serialization via codec
    public static final Supplier<AttachmentType<LEStorage>> LE_ENERGY = ATTACHMENT_TYPES.register(
            "le_energy", () -> AttachmentType.serializable(() -> new LEStorage(LCUtils.getMaxEnergyTools())).build()
    );
//    public static final Supplier<AttachmentType<LEStorage>> LE_ENERGY_MACHINES = ATTACHMENT_TYPES.register(
//            "le_energy_machines", () -> AttachmentType.serializable(() -> new LEStorage(LCUtils.getMaxMachineLE())).build()
//    );
    public static final Supplier<AttachmentType<LEStorage>> LE_ENERGY_INFUSER = ATTACHMENT_TYPES.register(
            "le_energy_infuser", () -> AttachmentType.serializable(() -> new LEStorage(LCUtils.getMaxInfuserLE())).build()
    );
    public static void setup(IEventBus bus) {
        ATTACHMENT_TYPES.register(bus);
    }
}
