package com.derpz.nukaisles.block.custom;

import com.derpz.nukaisles.block.ModBlocks;
import com.derpz.nukaisles.block.entity.ModBlockEntities;
import com.derpz.nukaisles.block.entity.NukaColaMachineBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class NukaColaMachineBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0,0, 16, 32, 16);
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public NukaColaMachineBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new NukaColaMachineBlockEntity(pos, state);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof NukaColaMachineBlockEntity) {
            ItemScatterer.spawn(world, pos, (NukaColaMachineBlockEntity)blockEntity);
            world.updateComparators(pos, this);
        }

        BlockPos topPos = pos.up();
        BlockPos bottomPos = pos.down();

        if (world.getBlockState(topPos).getBlock() instanceof NukaColaMachineTop) {
            world.breakBlock(topPos, true);
        }
        if (world.getBlockState(bottomPos).getBlock() instanceof NukaColaMachineBlock) {
            world.breakBlock(bottomPos, true);
        }

        return super.onBreak(world, pos, state, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        Direction facing = state.get(FACING);

        // Calculate the position for the top block based on the facing direction
        BlockPos topPos = pos.up();

        // Place the top block
        if (world.getBlockState(topPos).isAir()) {
            BlockState topBlockState = ModBlocks.NUKA_COLA_MACHINE_TOP.getDefaultState().with(FACING, facing);
            world.setBlockState(topPos, topBlockState, 3);

            // Get the block entity of the bottom block
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof NukaColaMachineBlockEntity bottomBlockEntity) {

                // Set the top block position
                bottomBlockEntity.setTopBlock(topPos);

                // Create a new block entity instance for the top block and copy data from the bottom block entity
                BlockEntity topBlockEntity = world.getBlockEntity(topPos);
                if (topBlockEntity instanceof NukaColaMachineBlockEntity) {
                    ((NukaColaMachineBlockEntity) topBlockEntity).copyDataFrom(bottomBlockEntity);
                }
            }
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((NukaColaMachineBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.NUKA_COLA_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
    }
}
