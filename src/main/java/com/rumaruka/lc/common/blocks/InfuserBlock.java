package com.rumaruka.lc.common.blocks;

import com.mojang.serialization.MapCodec;
import com.rumaruka.lc.api.lightning_energy_api.LEStorage;
import com.rumaruka.lc.common.tiles.machines.InfuserBE;
import com.rumaruka.lc.init.LCTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.DirectionalBlock.FACING;

public class InfuserBlock extends BaseEntityBlock {
    public static final MapCodec<InfuserBlock> CODEC = simpleCodec(InfuserBlock::new);

    public InfuserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));

    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createInfuserTicker(pLevel, pBlockEntityType, LCTiles.INFUSER_BE.get());
    }
    public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
        return this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
    }
    @javax.annotation.Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createInfuserTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<? extends InfuserBE> p_151990_) {
        return p_151988_.isClientSide ? null : createTickerHelper(p_151989_, p_151990_, InfuserBE::serverTick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof InfuserBE infuserBE) {
                LEStorage le = infuserBE.getLeStorage();
                player.sendSystemMessage(Component.literal(String.valueOf(le.getCurrentLE())));
            }
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new InfuserBE(pPos, pState);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }
}
