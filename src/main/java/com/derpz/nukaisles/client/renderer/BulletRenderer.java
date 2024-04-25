package com.derpz.nukaisles.client.renderer;

import com.derpz.nukaisles.entity.custom.BulletProjectileEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class BulletRenderer extends EntityRenderer<BulletProjectileEntity> {
    public BulletRenderer(EntityRendererFactory.Context ctx)
    {
        super(ctx);
    }

    @Override
    public Identifier getTexture(BulletProjectileEntity entity)
    {
        return null;
    }

    @Override
    public void render(BulletProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
    }
}
