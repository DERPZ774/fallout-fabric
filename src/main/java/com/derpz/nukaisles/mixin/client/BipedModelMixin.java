package com.derpz.nukaisles.mixin.client;

import com.derpz.nukaisles.item.custom.UnderArmor;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;

@Mixin(BipedEntityModel.class)
public class BipedModelMixin<T extends LivingEntity> {
    @Shadow @Final public ModelPart hat;

    // Courtesy of the goat, Joebob
    @Inject(at = @At("TAIL"), method = "setAngles*")
    private void modifyModelScale(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo info) {
        Item chestStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem();

        if (chestStack instanceof UnderArmor && ((UnderArmor)chestStack).adjustHat()) {
            this.hat.copyTransform(hat);
            this.hat.xScale = 0.95f;
            this.hat.yScale = 0.95f;
            this.hat.zScale = 0.95f;


        }
    }

}
