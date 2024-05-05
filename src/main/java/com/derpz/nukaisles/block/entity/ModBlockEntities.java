package com.derpz.nukaisles.block.entity;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<NukaColaMachineBlockEntity> NUKA_COLA_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(NukaIsles.MOD_ID, "nuka_cola_be"),
                    BlockEntityType.Builder.create(NukaColaMachineBlockEntity::new,
                            ModBlocks.NUKA_COLA_MACHINE).build());

    public static void registerBlockEntities() {
        NukaIsles.LOGGER.info("Registering Block Entities for " + NukaIsles.MOD_ID);
    }
}
