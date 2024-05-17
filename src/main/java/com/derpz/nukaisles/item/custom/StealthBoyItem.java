package com.derpz.nukaisles.item.custom;

import com.derpz.nukaisles.effect.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class StealthBoyItem extends Item {
    public StealthBoyItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.addStatusEffect(ModEffects.STEALTHY);
        }
        return super.use(world, user, hand);
    }
}
