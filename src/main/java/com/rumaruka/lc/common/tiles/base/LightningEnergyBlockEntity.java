package com.rumaruka.lc.common.tiles.base;


import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


import java.util.function.Supplier;

public abstract class LightningEnergyBlockEntity extends BaseContainerBlockEntity  {



    public LightningEnergyBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {

        super(pType, pPos, pBlockState);


    }


}
