package com.rumaruka.lc.init;

import com.google.common.collect.Lists;
import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.common.items.materials.ElectroMaterial;
import com.rumaruka.lc.common.items.tools.LightningWand;
import com.rumaruka.lc.common.items.tools.electro.*;
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

    public static final DeferredItem<LCItemMagnet> MAGNET_1 = ITEMS.register("magnet_1",()->new LCItemMagnet(8));
    public static final DeferredItem<LCItemMagnet> MAGNET_2 = ITEMS.register("magnet_2",()->new LCItemMagnet(16));
    public static final DeferredItem<LCItemMagnet> MAGNET_3 = ITEMS.register("magnet_3",()->new LCItemMagnet(24));
    public static final DeferredItem<LCItemMagnet> MAGNET_4 = ITEMS.register("magnet_4",()->new LCItemMagnet(32));

    public static final DeferredItem<Item> IRON_PLATE = ITEMS.register("plate_0", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRO_PLATE = ITEMS.register("plate_7", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_PLATE = ITEMS.register("plate_1", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_PLATE = ITEMS.register("plate_2", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_PLATE = ITEMS.register("plate_3", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINIUM_PLATE = ITEMS.register("plate_4", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_PLATE = ITEMS.register("plate_5", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_PLATE = ITEMS.register("plate_6", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SKYFATHER_PLATE = ITEMS.register("plate_8", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MYSTIC_PLATE = ITEMS.register("plate_9", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> IRON_ROD = ITEMS.register("rod_0", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELECTRO_ROD = ITEMS.register("rod_7", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STEEL_ROD = ITEMS.register("rod_1", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LEAD_ROD = ITEMS.register("rod_2", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIN_ROD = ITEMS.register("rod_3", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALUMINIUM_ROD = ITEMS.register("rod_4", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GOLD_ROD = ITEMS.register("rod_5", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COPPER_ROD = ITEMS.register("rod_6", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SKYFATHER_ROD = ITEMS.register("rod_8", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MYSTIC_ROD = ITEMS.register("rod_9", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> LIGHTNING = ITEMS.register("lightning", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> INFUSER_ITEM = ITEMS.register("infuser", () -> new BlockItem(LCBlocks.INFUSER.get(), new Item.Properties()));


    public static ArrayList<Item> getAllItems() {

        return
                Lists.newArrayList(
                        ELECTRO_IRON_NUGGET.get(),
                        ELECTRO_IRON_DUST.get(),
                        LIGHTNING_WAND.get(),
                        INFUSER_ITEM.get(),
                        ELECTRO_IRON_INGOT.get(),
                        ELECTRO_AXE_ITEM.get(),
                        ELECTRO_PICKAXE_ITEM.get(),
                        ELECTRO_HOE_ITEM.get(),
                        ELECTRO_SHOVEL_ITEM.get(),
                        IRON_PLATE.get(),
                        TIN_PLATE.get(),
                        STEEL_PLATE.get(),
                        ALUMINIUM_PLATE.get(),
                        LEAD_PLATE.get(),
                        GOLD_PLATE.get(),
                        COPPER_PLATE.get(),
                        SKYFATHER_PLATE.get(),
                        MYSTIC_PLATE.get(),
                        ELECTRO_PLATE.get(),
                        IRON_ROD.get(),
                        TIN_ROD.get(),
                        STEEL_ROD.get(),
                        ALUMINIUM_ROD.get(),
                        LEAD_ROD.get(),
                        GOLD_ROD.get(),
                        COPPER_ROD.get(),
                        SKYFATHER_ROD.get(),
                        MYSTIC_ROD.get(),
                        ELECTRO_ROD.get(),
                        ELECTRO_AXE_ITEM.get(),
                        MAGNET_1.get(),
                        MAGNET_2.get(),
                        MAGNET_3.get(),
                        MAGNET_4.get(),
                        LIGHTNING.get()


                );

    }

    public static ArrayList<ItemStack> getToolsItems() {
        ItemStack pickaxe = ELECTRO_PICKAXE_ITEM.get().getDefaultInstance();
        ItemStack axe = ELECTRO_AXE_ITEM.get().getDefaultInstance();
        ItemStack hoe = ELECTRO_HOE_ITEM.get().getDefaultInstance();
        ItemStack shovel = ELECTRO_SHOVEL_ITEM.get().getDefaultInstance();
        ItemStack magnet = MAGNET_1.get().getDefaultInstance();
        ItemStack magnet1 = MAGNET_2.get().getDefaultInstance();
        ItemStack magnet2= MAGNET_3.get().getDefaultInstance();
        ItemStack magnet3 = MAGNET_4.get().getDefaultInstance();

        if (pickaxe.getItem() instanceof IElectro electro) {
            electro.setLE(pickaxe,LCUtils.getMaxEnergyTools());
        }
        if (axe.getItem() instanceof IElectro electro) {
            electro.setLE(axe,LCUtils.getMaxEnergyTools());

        }
        if (hoe.getItem() instanceof IElectro electro) {
            electro.setLE(hoe,LCUtils.getMaxEnergyTools());

        }
        if (shovel.getItem() instanceof IElectro electro) {
            electro.setLE(shovel,LCUtils.getMaxEnergyTools());

        }
        if (magnet.getItem() instanceof LCItemMagnet  electro) {
            electro.setLE(magnet,electro.getMaxEnergy());

        }
        if (magnet1.getItem() instanceof LCItemMagnet  electro) {
            electro.setLE(magnet1,electro.getMaxEnergy());

        }
        if (magnet2.getItem() instanceof LCItemMagnet  electro) {
            electro.setLE(magnet2,electro.getMaxEnergy());

        }
        if (magnet3.getItem() instanceof LCItemMagnet  electro) {
            electro.setLE(magnet3,electro.getMaxEnergy());

        }
        return Lists.newArrayList(pickaxe, axe, hoe, shovel,
                                  magnet,magnet1,magnet2,magnet3
        );
    }


}


