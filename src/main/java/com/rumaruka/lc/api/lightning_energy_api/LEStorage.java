package com.rumaruka.lc.api.lightning_energy_api;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.common.util.INBTSerializable;

import javax.xml.transform.Source;
import java.util.Set;

public class LEStorage implements ILEStorage, INBTSerializable<Tag> {
    public static final Codec<LEStorage> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("capacity").forGetter(cap -> cap.capacity),
            Codec.INT.fieldOf("maxReceive").forGetter(cap -> cap.maxReceive),
            Codec.INT.fieldOf("maxExtract").forGetter(cap -> cap.maxExtract),
            Codec.INT.fieldOf("energy").forGetter(cap -> cap.energy)
    ).apply(instance, LEStorage::new));

    public static final StreamCodec<ByteBuf, LEStorage> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, cap -> cap.capacity,
            ByteBufCodecs.VAR_INT, cap -> cap.maxReceive,
            ByteBufCodecs.VAR_INT, cap -> cap.maxExtract,
            ByteBufCodecs.VAR_INT, cap -> cap.energy,
            LEStorage::new);

    public static final LEStorage EMPTY = new LEStorage(0, 0, 0,  0);
    public static final int INFINITE = -1;

    protected int energy;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;



    public LEStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public LEStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public LEStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public LEStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
    }

    @Override
    public int addLE(int toReceive) {
        if (toReceive <= 0) {
            return 0;
        }

        int energyReceived = Mth.clamp(this.capacity - this.energy, 0, Math.min(this.maxReceive, toReceive));

        this.energy += energyReceived;
        return energyReceived;
    }

    @Override
    public boolean hasLE() {
        return getCurrentLE() != 0;
    }

    @Override
    public int useLE(int toExtract) {
        if (toExtract <= 0) {
            return 0;
        }

        int energyExtracted = Math.min(this.energy, Math.min(this.maxExtract, toExtract));

        this.energy -= energyExtracted;
        return energyExtracted;
    }

    public void setLE(int amount) {
        this.energy=amount;
    }



    @Override
    public int getCurrentLE() {
        return this.energy;
    }

    @Override
    public int getMaxLE() {
        return this.capacity;
    }


    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {
        return IntTag.valueOf(this.getCurrentLE());
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {
        if (!(nbt instanceof IntTag intNbt))
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        this.energy = intNbt.getAsInt();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LEStorage le){
            return le.getCurrentLE() == this.getCurrentLE();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
