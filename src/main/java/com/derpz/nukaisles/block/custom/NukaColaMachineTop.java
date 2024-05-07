package com.derpz.nukaisles.block.custom;

import com.derpz.nukaisles.block.entity.NukaColaMachineBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NukaColaMachineTop extends NukaColaMachineBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, -16, 0, 16, 16, 16);

    public NukaColaMachineTop(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NukaColaMachineBlockEntity) {
            ItemScatterer.spawn(world, pos, (NukaColaMachineBlockEntity)blockEntity);
            world.updateComparators(pos, this);
        }

        return super.onBreak(world, pos, state, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockPos bottomPos = pos.down();

        if (world.getBlockState(bottomPos).getBlock() instanceof NukaColaMachineBlock) {
            return world.getBlockState(bottomPos).getBlock().onUse(world.getBlockState(bottomPos), world, bottomPos, player, hand, hit);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
