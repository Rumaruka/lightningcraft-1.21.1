package com.rumaruka.lc.common.items.tools.electro;

import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IElectro {

void setLE(ItemStack stack, int amount);
void addLE(ItemStack stack, int amount);
void useLE(ItemStack stack, int amount);

}
