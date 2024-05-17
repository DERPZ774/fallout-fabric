package com.derpz.nukaisles.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BloodPackItem extends Item {
    private static final float HEAL_AMOUNT = 24;
    private static final int TICKS_BETWEEN_HEALS = 20;


    public BloodPackItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user.isAlive() && user.getHealth() != user.getMaxHealth()) {
            user.getStackInHand(hand).decrement(1);
            user.heal(HEAL_AMOUNT / 10);

            //ToDo replace with effect
        }
        return super.use(world, user, hand);
    }

}
