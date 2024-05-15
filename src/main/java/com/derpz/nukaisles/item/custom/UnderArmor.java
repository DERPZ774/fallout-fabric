package com.derpz.nukaisles.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;

public class UnderArmor extends Item implements Equipment {
    private final String texture;

    public UnderArmor(Settings settings, String texture) {
        super(settings);
        this.texture = texture;
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.CHEST;
    }

    public String getTexture() {
        return "textures/models/under_armor/"+texture+".png";
    }
}
