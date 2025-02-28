package com.rumaruka.lc.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> tab = CREATIVE_MODE_TABS.register("lightningcraft",() -> CreativeModeTab.builder()
            .icon(()-> new ItemStack(LCItems.LIGHTNING.get()))
            .title(Component.translatable("itemGroup.lc"))
            .displayItems((p_270258_, p_259752_) ->  {
              LCItems.getAllItems().forEach(p_259752_::accept);
              LCItems.getTools().forEach(p_259752_::accept);
               LCItems.getMaxTools().forEach(p_259752_::accept);


            })

            .build()
    );

    public static void setup(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);

    }

}
