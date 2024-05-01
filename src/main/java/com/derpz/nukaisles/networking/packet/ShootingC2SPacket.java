package com.derpz.nukaisles.networking.packet;

import com.derpz.nukaisles.entity.custom.BulletProjectileEntity;
import com.derpz.nukaisles.item.custom.GunItem;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ShootingC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender sender) {
        // Get the item currently held by the player
        ItemStack heldItem = player.getMainHandStack();
        if (heldItem.getItem() instanceof GunItem) {
            // Get the damage value and projectile speed from the GunItem instance
            float damage = ((GunItem) heldItem.getItem()).getDamage();
            Vec3d lookVec = player.getRotationVector();
            double speed = 20; // Adjust the speed as needed
            Vec3d velocity = new Vec3d(lookVec.x * speed, lookVec.y * speed, lookVec.z * speed);


            // Create and spawn the bullet entity using the damage value and calculated velocity
            BulletProjectileEntity bulletProjectileEntity = new BulletProjectileEntity(player, player.getWorld(), damage, velocity);
            player.getServerWorld().spawnEntity(bulletProjectileEntity);
        }
    }
}