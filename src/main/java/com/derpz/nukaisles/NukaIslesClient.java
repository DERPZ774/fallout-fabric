package com.derpz.nukaisles;

import com.derpz.nukaisles.block.ModBlocks;
import com.derpz.nukaisles.entity.ModEntities;
import com.derpz.nukaisles.event.KeyInputHandler;
import com.derpz.nukaisles.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EmptyEntityRenderer;

public class NukaIslesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LAMP, RenderLayer.getCutout());
        KeyInputHandler.register();
        ModMessages.registerS2CPackets();
        EntityRendererRegistry.register(ModEntities.BULLET_PROJECTILE, EmptyEntityRenderer::new);
    }
}
