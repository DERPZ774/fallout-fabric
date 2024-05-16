package com.derpz.nukaisles.mixin.client;

import com.derpz.nukaisles.item.custom.UnderArmor;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedModelMixin<T extends LivingEntity> {
    @Shadow @Final public ModelPart hat;

    @Inject(at = @At("HEAD"), method = "setAngles*")
    private void modifyModelScale(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo info) {
        if (livingEntity.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof UnderArmor) {
            this.hat.visible = false;
        }
    }
}
