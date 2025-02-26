package com.rumaruka.lc.api.lightning_energy_api;

import net.minecraft.world.entity.player.Player;

public interface ILEItem {

    Player getPlayer();

    int getMaxLE();

    int getLE();

    void addLE(int amount);

    void setLE(int amount);

    void useLE(int amount);

    boolean hasLE();
}
