package com.derpz.nukaisles;

import com.derpz.nukaisles.block.ModBlocks;
import com.derpz.nukaisles.block.entity.ModBlockEntities;
import com.derpz.nukaisles.entity.ModEntities;
import com.derpz.nukaisles.item.ModItemGroups;
import com.derpz.nukaisles.item.ModItems;
import com.derpz.nukaisles.networking.ModMessages;
import com.derpz.nukaisles.screen.ModScreenHandlers;
import com.derpz.nukaisles.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NukaIsles implements ModInitializer {
	public static final String MOD_ID = "nuka-isles";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("War, war never changes...");
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerSounds();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModMessages.registerC2SPackets();
		ModEntities.registerEntities();
	}
}