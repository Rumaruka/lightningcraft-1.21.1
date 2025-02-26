package com.rumaruka.lc.api.lightning_energy_api;

public interface ILEStorage {

    int getMaxLE();

    int getLE();

    int addLE(int amount);


    int useLE(int amount);

    boolean hasLE();
}
