package com.rumaruka.lc.common.items.tools.electro;

import net.minecraft.world.item.ItemStack;

public interface IElectro {

    void setLE(ItemStack stack, int amount);

    void addLE(ItemStack stack, int amount);

    void useLE(ItemStack stack, int amount);


}
