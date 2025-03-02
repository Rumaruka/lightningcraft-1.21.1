package com.rumaruka.lc.common.tiles.base;


import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.init.LCAttachmentTypes;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.attachment.AttachmentType;


public abstract class LightningEnergyBlockEntity extends BaseContainerBlockEntity {

    protected AttachmentType<LEStorage> attachmentType ; // <LEStorage>
    public LEStorage le ; // <LEStorage>

    public LightningEnergyBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState,AttachmentType<LEStorage> attachmentType, LEStorage le) {

        super(pType, pPos, pBlockState);
        this.attachmentType=attachmentType;
        this.le=le;
        setData(attachmentType,le);


    }
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("le_energy", le.getLE());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.le.setLE(tag.getInt("le_energy"));

    }
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag, lookupProvider);
    }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

}
