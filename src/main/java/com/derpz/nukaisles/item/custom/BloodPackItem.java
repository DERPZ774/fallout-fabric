package com.derpz.nukaisles.item.custom;

import com.derpz.nukaisles.functions.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BloodPackItem extends Item {
    private static final int HEAL_AMOUNT = 24;

    public BloodPackItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            startHealing((ServerPlayerEntity) user);
            user.getStackInHand(hand).decrement(1);
        }
        return super.use(world, user, hand);
    }

    private void startHealing(ServerPlayerEntity player) {
        MinecraftServer server = player.getServer();

        server.execute(() -> {
            if (player.isAlive()) {
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
                Main.wait(1000);
                player.heal(HEAL_AMOUNT / 10);
            }
        });
    }
}
