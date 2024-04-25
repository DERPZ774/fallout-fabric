package com.derpz.nukaisles.datagen;

import com.derpz.nukaisles.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SCRAP_METAL_BLOCK);
        addDrop(ModBlocks.LAMP);

       // addDrop(ModBlocks.SCRAP_METAL_BLOCK, oreDrops(ModBlocks.SCRAP_METAL_BLOCK, ModItems.SCRAP_METAL)); Shows how to add ore drops
    }

}
