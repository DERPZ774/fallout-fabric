package com.derpz.nukaisles;

import com.derpz.nukaisles.block.ModBlocks;
import com.derpz.nukaisles.block.entity.ModBlockEntities;
import com.derpz.nukaisles.block.entity.renderer.NukaColaMachingBlockEntityRenderer;
import com.derpz.nukaisles.entity.ModEntities;
import com.derpz.nukaisles.event.KeyInputHandler;
import com.derpz.nukaisles.networking.ModMessages;
import com.derpz.nukaisles.screen.ModScreenHandlers;
import com.derpz.nukaisles.screen.NukaColaMachineScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.EmptyEntityRenderer;

public class NukaIslesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAMP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NUKA_COLA_MACHINE, RenderLayer.getCutout());
        KeyInputHandler.register();
        ModMessages.registerS2CPackets();
        EntityRendererRegistry.register(ModEntities.BULLET_PROJECTILE, EmptyEntityRenderer::new);

        HandledScreens.register(ModScreenHandlers.NUKA_COLA_MACHINE_SCREEN_HANDLER, NukaColaMachineScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.NUKA_COLA_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY, NukaColaMachingBlockEntityRenderer::new);
    }
}
