package com.derpz.nukaisles.block;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.block.custom.LampBlock;
import com.derpz.nukaisles.block.custom.NukaColaMachineBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    private static final List<Item> registeredBlockItems = new ArrayList<>();

    public static final Block SCRAP_METAL_BLOCK = registerBlock("scrap_metal_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block LAMP = registerBlock("lamp",
            new LampBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque().luminance((blockState) -> blockState.get(LampBlock.CLICKED) ? 8 : 0)));

    //Block entities
    public static final Block NUKA_COLA_MACHINE = registerBlock("nuka_cola_machine",
            new NukaColaMachineBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

//    public static final Block NUKA_COLA_MACHINE_TOP = registerBlock("nuka_cola_machine_top",
//            new NukaColaMachineTop(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        registeredBlockItems.add(block.asItem());
        return Registry.register(Registries.BLOCK, new Identifier(NukaIsles.MOD_ID, name), block);
    }

    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(NukaIsles.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        NukaIsles.LOGGER.info("Registering Mod Blocks for " + NukaIsles.MOD_ID);
    }
    // Method to retrieve all registered block items
    public static List<Item> getAllModBlockItems() {
        return registeredBlockItems;
    }
}
