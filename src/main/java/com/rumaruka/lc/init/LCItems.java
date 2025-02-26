package com.rumaruka.lc.init;

import com.google.common.collect.Lists;
import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.common.items.materials.ElectroMaterial;
import com.rumaruka.lc.common.items.tools.LightningWand;
import com.rumaruka.lc.common.items.tools.electro.LCItemAxe;
import com.rumaruka.lc.common.items.tools.electro.LCItemHoe;
import com.rumaruka.lc.common.items.tools.electro.LCItemPickaxe;
import com.rumaruka.lc.common.items.tools.electro.LCItemShovel;
import com.rumaruka.lc.misc.LCUtils;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

import static com.rumaruka.lc.LightningCraft.MODID;

public class LCItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);


    public static void setup(IEventBus bus) {
        ITEMS.register(bus);


    }

    //Materials
    public static final DeferredItem<ElectroMaterial> ELECTRO_IRON_INGOT = ITEMS.register("electro_iron_ingot", () -> new ElectroMaterial(new Item.Properties()));
    public static final DeferredItem<ElectroMaterial> ELECTRO_IRON_NUGGET = ITEMS.register("electro_iron_nugget", () -> new ElectroMaterial(new Item.Properties()));
    public static final DeferredItem<ElectroMaterial> ELECTRO_IRON_DUST = ITEMS.register("electro_iron_dust", () -> new ElectroMaterial(new Item.Properties()));


    public static final DeferredItem<LightningWand> LIGHTNING_WAND = ITEMS.register("lightning_wand", () -> new LightningWand(new Item.Properties().durability(100)));
    public static final DeferredItem<LCItemPickaxe> ELECTRO_PICKAXE_ITEM = ITEMS.register("electro_pickaxe", () -> new LCItemPickaxe(Tiers.NETHERITE, 3, 3));
    public static final DeferredItem<LCItemAxe> ELECTRO_AXE_ITEM = ITEMS.register("electro_axe", () -> new LCItemAxe(Tiers.NETHERITE, 3, 3));
    public static final DeferredItem<LCItemHoe> ELECTRO_HOE_ITEM = ITEMS.register("electro_hoe", () -> new LCItemHoe(Tiers.NETHERITE, 3, 3));
    public static final DeferredItem<LCItemShovel> ELECTRO_SHOVEL_ITEM = ITEMS.register("electro_shovel", () -> new LCItemShovel(Tiers.NETHERITE, 3, 3));

    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("plate_0", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRO_PLATE = ITEMS.register("plate_7", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> LIGHTNING = ITEMS.register("lightning", () -> new Item(new Item.Properties()));
    // public static final DeferredItem<DustItem> IRON_DUST = ITEMS.register("dust_iron", DustItem::new);
    public static final DeferredItem<Item> INFUSER_ITEM = ITEMS.register("infuser", () -> new BlockItem(LCBlocks.INFUSER.get(), new Item.Properties()));


    public static ArrayList<Item> getAllItems() {

        return
                Lists.newArrayList(
                        ELECTRO_IRON_NUGGET.get(),
                        ELECTRO_IRON_DUST.get(),
                        LIGHTNING_WAND.get(),
                        INFUSER_ITEM.get(),
                        ELECTRO_IRON_INGOT.get(),
                        IRON_PLATE.get(),
                        ELECTRO_PLATE.get()

                );

    }

    public static ArrayList<ItemStack> getTools() {
        ItemStack pickaxe = ELECTRO_PICKAXE_ITEM.get().getDefaultInstance();
        ItemStack axe = ELECTRO_AXE_ITEM.get().getDefaultInstance();
        ItemStack hoe = ELECTRO_HOE_ITEM.get().getDefaultInstance();
        ItemStack shovel = ELECTRO_SHOVEL_ITEM.get().getDefaultInstance();

        LEStorage data = pickaxe.get(LCData.LE_ENERGY_ITEM.get());
        LEStorage data1 = axe.get(LCData.LE_ENERGY_ITEM.get());
        LEStorage data2 = hoe.get(LCData.LE_ENERGY_ITEM.get());
        LEStorage data3 = shovel.get(LCData.LE_ENERGY_ITEM.get());
        if (data != null) {
            data.setLE(0);
        }
        if (data1 != null) {
            data1.setLE(0);
        }
        if (data2 != null) {
            data2.setLE(0);
        }
        if (data3 != null) {
            data3.setLE(0);
        }

        return Lists.newArrayList(pickaxe, axe, hoe, shovel);
    }

    public static ArrayList<ItemStack> getMaxTools() {
        ItemStack pickaxe = ELECTRO_PICKAXE_ITEM.get().getDefaultInstance();
        ItemStack axe = ELECTRO_AXE_ITEM.get().getDefaultInstance();
        ItemStack hoe = ELECTRO_HOE_ITEM.get().getDefaultInstance();
        ItemStack shovel = ELECTRO_SHOVEL_ITEM.get().getDefaultInstance();

        LEStorage data = pickaxe.get(LCData.LE_ENERGY_ITEM.get());
        LEStorage data1 = axe.get(LCData.LE_ENERGY_ITEM.get());
        LEStorage data2 = hoe.get(LCData.LE_ENERGY_ITEM.get());
        LEStorage data3 = shovel.get(LCData.LE_ENERGY_ITEM.get());
        if (data != null) {
            data.setLE(LCUtils.getMaxEnergyTools());
        }
        if (data1 != null) {
            data1.setLE(LCUtils.getMaxEnergyTools());
        }
        if (data2 != null) {
            data2.setLE(LCUtils.getMaxEnergyTools());
        }
        if (data3 != null) {
            data3.setLE(LCUtils.getMaxEnergyTools());
        }

        return Lists.newArrayList(pickaxe, axe, hoe, shovel);
    }

}


