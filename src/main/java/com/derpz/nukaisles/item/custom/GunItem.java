package com.derpz.nukaisles.item.custom;

import com.derpz.nukaisles.item.ModItems;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GunItem extends Item {
    private final SoundEvent sound;
    private final float damage;
    private final float recoil;
    private final int maxAmmo;
//    private static double range;
//    private final int fireRate;

    public GunItem(Settings settings, SoundEvent sound, float damage, float recoil, int maxAmmo) {
        super(settings);
        this.sound = sound;
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

    public SoundEvent getSound() {
        return sound;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public static ItemStack getDefaultGunStack(GunItem gunItem, int defaultAmmo, int maxAmmo) {
        ItemStack stack = new ItemStack(gunItem);
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt("currentAmmo", defaultAmmo);
        nbt.putInt("maxAmmo", maxAmmo);
        return stack;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
//        if (Screen.hasShiftDown()) {
//            //blah blah add shift details for gun
//        } else {
//            //add current ammo and max ammo display
        tooltip.add(Text.translatable("Ammo: " + (stack.getOrCreateNbt().getInt("currentAmmo")) + "/" + this.maxAmmo).formatted(Formatting.WHITE));
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
        nbt.putInt("currentAmmo", this.maxAmmo);
        nbt.putInt("maxAmmo", this.maxAmmo);
    }

    public void fireGun(World world, PlayerEntity player, ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int currentAmmo = nbt.getInt("currentAmmo");
        if (currentAmmo > 0) {
            // Perform shooting action here
            // For example, play sound, spawn bullet entity, etc.
            // Decrement current ammo count
            nbt.putInt("currentAmmo", --currentAmmo);
        } else {
            return;
        }
    }
    public boolean hasAmmo(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        int currentAmmo = nbt.getInt("currentAmmo");
        return currentAmmo > 0;
    }

}