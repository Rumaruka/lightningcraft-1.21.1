//package com.rumaruka.lc.common.events;
//
//import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
//import com.rumaruka.lc.init.LCAttachment;
//import com.rumaruka.lc.init.LCData;
//import com.rumaruka.lc.init.LCItems;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
//
//
//public class DataComponentsEvent {
//
//   @SubscribeEvent
//    public static void  modifyComponents(ModifyDefaultComponentsEvent event) {
//        event.modify(LCItems.ELECTRO_AXE_ITEM.get(),builder -> builder.set(LCData.LE_ENERGY_ITEM.get(), new LEStorage(1_000_000)));
//        event.modify(LCItems.ELECTRO_SHOVEL_ITEM.get(),builder -> builder.set(LCData.LE_ENERGY_ITEM.get(), new LEStorage(1_000_000)));
//        event.modify(LCItems.ELECTRO_PICKAXE_ITEM.get(),builder -> builder.set(LCData.LE_ENERGY_ITEM.get(), new LEStorage(1_000_000)));
//        event.modify(LCItems.ELECTRO_HOE_ITEM.get(),builder -> builder.set(LCData.LE_ENERGY_ITEM.get(), new LEStorage(1_000_000)));
//
//
//    }
//}
