package com.derpz.nukaisles.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//TODO - make it so that the keybind only registers when the player is holding a gun. Otherwise it prevents other uses of the key.

public class GunItem extends Item {
    private final SoundEvent shootSound, drySound;
    private final float damage;
    private final float recoil;
    private final int maxAmmo;
//    private static double range;
//    private final int fireRate;

    public GunItem(Settings settings, SoundEvent shootSound, SoundEvent drySound, float damage, float recoil, int maxAmmo) {
        super(settings.maxCount(1));
        this.shootSound = shootSound;
        this.drySound = drySound;
        this.damage = damage;
        this.recoil = recoil;
        this.maxAmmo = maxAmmo;
    }

    public float getDamage() {
        return damage;
    }

    public float getRecoil() {
        return recoil;
    }

    public SoundEvent getShootSound() {
        return shootSound;
    }

    public SoundEvent getDrySound() {
        return drySound;
    }

    public int getMaxAmmo(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("maxAmmo");
    }

    public int getCurrentAmmo(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("currentAmmo");
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//        if (Screen.hasShiftDown()) {
//            //blah blah add shift details for gun
//        } else {
//            //add current ammo and max ammo display
            tooltip.add(Text.translatable("Ammo: " + getCurrentAmmo(stack) + "/" + getMaxAmmo(stack)).formatted(Formatting.WHITE));
//
//        }
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity && stack.getOrCreateNbt().isEmpty()) {
            setDefaultNBT(stack);
        }
    }

    private void setDefaultNBT(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        //Change current ammo to 0 when reloading is implemented
        nbt.putInt("currentAmmo", this.maxAmmo);
        nbt.putInt("maxAmmo", this.maxAmmo);
    }

    public void fireGun(World world, PlayerEntity player, ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int currentAmmo = nbt.getInt("currentAmmo");
        if (!player.getAbilities().creativeMode) {
            if (currentAmmo >= 0) {
                nbt.putInt("currentAmmo", --currentAmmo);
            } else {
                return;
            }
        }
    }
    public boolean hasAmmo(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int currentAmmo = nbt.getInt("currentAmmo");
        return currentAmmo > 0;
    }
}