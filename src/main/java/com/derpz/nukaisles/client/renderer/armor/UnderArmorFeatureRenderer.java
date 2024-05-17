package com.derpz.nukaisles.client.renderer.armor;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.client.ModModelLayers;
import com.derpz.nukaisles.client.models.UnderArmorModel;
import com.derpz.nukaisles.item.custom.UnderArmor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Vector3f;

@Environment(value= EnvType.CLIENT)
public class UnderArmorFeatureRenderer implements ArmorRenderer {
    private static UnderArmorModel armorModel;
    Vector3f scaleFactors = new Vector3f(0.05f, 0.05f, 0.05f);

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, ItemStack stack, LivingEntity entity, EquipmentSlot slot, int light, BipedEntityModel<LivingEntity> contextModel) {
        if (armorModel == null) {
            armorModel = new UnderArmorModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModModelLayers.UNDER_ARMOR), false);
        }

        contextModel.copyBipedStateTo(armorModel);
        Identifier texture = new Identifier(NukaIsles.MOD_ID, ((UnderArmor)stack.getItem()).getTexture());

        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, armorModel, texture);
    }
}