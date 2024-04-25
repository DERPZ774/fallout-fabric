package com.derpz.nukaisles.item;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup FALLOUT_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(NukaIsles.MOD_ID, "nuka-isles"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.nuka-isles"))
                    .icon(() -> new ItemStack(ModItems.SCRAP_METAL)).entries((displayContext, entries) -> {
                        for (Item item : ModItems.getAllModItems()) {
                            entries.add(item);
                        }
                        // Add all mod block items
                        for (Item item : ModBlocks.getAllModBlockItems()) {
                            entries.add(item);
                        }
                    }).build());

    public static void registerItemGroups() {
        NukaIsles.LOGGER.info("Registering Mod Item Groups for " + NukaIsles.MOD_ID);    }
}
