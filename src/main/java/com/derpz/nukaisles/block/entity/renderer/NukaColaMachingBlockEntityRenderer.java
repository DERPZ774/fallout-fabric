package com.derpz.nukaisles.block.entity.renderer;

import com.derpz.nukaisles.block.custom.NukaColaMachineBlock;
import com.derpz.nukaisles.block.entity.NukaColaMachineBlockEntity;
import com.derpz.nukaisles.item.custom.NukaColaItem;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

import java.util.Objects;

public class NukaColaMachingBlockEntityRenderer implements BlockEntityRenderer<NukaColaMachineBlockEntity> {
    public NukaColaMachingBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(NukaColaMachineBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        if (Objects.requireNonNull(entity.getWorld()).getBlockState(entity.getPos()).getBlock() != Blocks.AIR) {

        for (int i = 1; i <= 6; i++) {
            ItemStack stack = entity.getRenderStack(i);
            if (stack.getItem() instanceof NukaColaItem) {
                matrices.push();
                    Direction facing = Objects.requireNonNull(entity.getWorld()).getBlockState(entity.getPos()).get(NukaColaMachineBlock.FACING);

                    switch (i) {
                        case 1 -> {
                            if (facing == Direction.WEST) {
                                matrices.translate(0.18f, 0.96f, 0.71f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.EAST) {
                                matrices.translate(0.82f, 0.96f, 0.29f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.NORTH) {
                                matrices.translate(0.29f, 0.96f, 0.16f);
                            } else if (facing == Direction.SOUTH) {
                                matrices.translate(0.71f, 0.96f, 0.82f);
                            }
                            matrices.scale(0.24f, 0.24f, 0.24f);
                        }
                        case 2 -> {
                            if (facing == Direction.WEST) {
                                matrices.translate(0.08f, 0.96f, 0.81f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.EAST) {
                                matrices.translate(0.90f, 0.96f, 0.18f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.NORTH) {
                                matrices.translate(0.19f, 0.96f, 0.10f);
                            } else if (facing == Direction.SOUTH) {
                                matrices.translate(0.82f, 0.96f, 0.92f);
                            }
                            matrices.scale(0.24f, 0.24f, 0.24f);
                        }

                        case 3 -> {
                            if (facing == Direction.WEST) {
                                matrices.translate(0.18f, 0.96f, 0.91f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.EAST) {
                                matrices.translate(0.82f, 0.96f, 0.08f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.NORTH) {
                                matrices.translate(0.09f, 0.96f, 0.19f);
                            } else if (facing == Direction.SOUTH) {
                                matrices.translate(0.92f, 0.96f, 0.82f);
                            }
                            matrices.scale(0.24f, 0.24f, 0.24f);
                        }

                        case 4 -> {
                            if (facing == Direction.WEST) {
                                matrices.translate(0.18f, 0.74f, 0.71f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.EAST) {
                                matrices.translate(0.82f, 0.74f, 0.29f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.NORTH) {
                                matrices.translate(0.29f, 0.74f, 0.16f);
                            } else if (facing == Direction.SOUTH) {
                                matrices.translate(0.71f, 0.74f, 0.82f);
                            }
                            matrices.scale(0.24f, 0.24f, 0.24f);
                        }

                        case 5 -> {
                            if (facing == Direction.WEST) {
                                matrices.translate(0.08f, 0.74f, 0.81f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.EAST) {
                                matrices.translate(0.90f, 0.74f, 0.18f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.NORTH) {
                                matrices.translate(0.19f, 0.74f, 0.10f);
                            } else if (facing == Direction.SOUTH) {
                                matrices.translate(0.82f, 0.74f, 0.92f);
                            }
                            matrices.scale(0.24f, 0.24f, 0.24f);
                        }

                        case 6 -> {
                            if (facing == Direction.WEST) {
                                matrices.translate(0.18f, 0.74f, 0.91f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.EAST) {
                                matrices.translate(0.82f, 0.74f, 0.08f);
                                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(92));
                            } else if (facing == Direction.NORTH) {
                                matrices.translate(0.09f, 0.74f, 0.19f);
                            } else if (facing == Direction.SOUTH) {
                                matrices.translate(0.92f, 0.74f, 0.82f);
                            }
                            matrices.scale(0.24f, 0.24f, 0.24f);
                        }

                    }
                    itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, getLightLevel(Objects.requireNonNull(entity.getWorld()),
                            entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), entity.getRenderStack(i).getCount());
                    matrices.pop();
                }

            }

        }
    }


    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
