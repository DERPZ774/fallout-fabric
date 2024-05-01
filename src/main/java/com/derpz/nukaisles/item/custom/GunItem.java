package com.derpz.nukaisles.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GunItem extends Item {
    private final SoundEvent sound;
    private final float damage;
    private final float recoil;
//    private int ammunitionCount;
//    private int maxAmmunition;
//    private static double range;
//    private final int fireRate;

    public GunItem(Settings settings, SoundEvent sound, float damage, float recoil) {
        super(settings);
        this.sound = sound;
        this.damage = damage;
        this.recoil = recoil;
    }

    public float getDamage() {
        return damage;
    }

    public float getRecoil() {
        return recoil;
    }

    public SoundEvent getSound() {
        return sound;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Bomba"));
        super.appendTooltip(stack, world, tooltip, context);
    }


}
