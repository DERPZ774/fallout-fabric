package com.derpz.nukaisles.mixin.client;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.client.ModModelLayers;
import com.derpz.nukaisles.client.models.UnderArmorModel;
import com.derpz.nukaisles.item.custom.UnderArmor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class FirstPersonArmMixin {
    @Unique
    private static UnderArmorModel armorModel;


    @Shadow protected abstract void setModelPose(AbstractClientPlayerEntity player);

    @Inject(at = @At("HEAD"), method = "renderRightArm")
    public void renderRightArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, CallbackInfo ci) {
        ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);

        if (chestStack.getItem() instanceof UnderArmor) {
            if (armorModel == null) {
                armorModel = new UnderArmorModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.UNDER_ARMOR), false);
            }

            Identifier texture = new Identifier(NukaIsles.MOD_ID, ((UnderArmor)chestStack.getItem()).getTexture());; // Get the player's skin texture

            renderModelArm(matrices, vertexConsumers, light, player, ((UnderArmorModel<?>) armorModel).rightArm, texture);
        }
    }

    @Inject(at = @At("HEAD"), method = "renderLeftArm")
    public void renderLeftArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, CallbackInfo ci) {
        ItemStack chestStack = player.getEquippedStack(EquipmentSlot.CHEST);

        if (chestStack.getItem() instanceof UnderArmor) {
            if (armorModel == null) {
                armorModel = new UnderArmorModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.UNDER_ARMOR), false);
            }

            Identifier texture = new Identifier(NukaIsles.MOD_ID, ((UnderArmor)chestStack.getItem()).getTexture());; // Get the player's skin texture

            renderModelArm(matrices, vertexConsumers, light, player, ((UnderArmorModel<?>) armorModel).leftArm, texture);
        }
    }

    @Unique
    private void renderModelArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, ModelPart arm, Identifier texture) {
        this.setModelPose(player);

        armorModel.handSwingProgress = 0.0F;
        armorModel.sneaking = false;
        armorModel.leaningPitch = 0.0F;
        armorModel.setAngles(player, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        armorModel.rightArm.scale(new Vector3f(0.00001f, 0.00001f, 0.00001f));

        arm.pitch = 0.0F;
        arm.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityAlpha(texture)), light, OverlayTexture.DEFAULT_UV);
        RenderLayer skinLayer = RenderLayer.getEntityAlpha(texture);
        VertexConsumer skinConsumer = vertexConsumers.getBuffer(skinLayer);
        arm.render(matrices, skinConsumer, light, OverlayTexture.DEFAULT_UV);
    }

}
