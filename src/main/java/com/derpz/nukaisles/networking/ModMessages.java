package com.derpz.nukaisles.networking;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.networking.packet.ShootingC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier SHOOTING_ID = new Identifier(NukaIsles.MOD_ID, "shooting");
    public static final Identifier SHOOTING_SYNC_ID = new Identifier(NukaIsles.MOD_ID, "shooting_sync");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SHOOTING_ID, ShootingC2SPacket::receive);
    }

    public static void registerS2CPackets() {
      //  ClientPlayNetworking.registerGlobalReceiver(SHOOTING_SYNC_ID, ShootingS2CPacket::receive);
    }
}
