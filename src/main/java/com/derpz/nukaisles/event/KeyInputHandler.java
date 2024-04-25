package com.derpz.nukaisles.event;

import com.derpz.nukaisles.item.custom.TenMmPistolItem;
import com.derpz.nukaisles.networking.ModMessages;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_NUKAISLES = "key.category.nuka-isles";
    public static final String KEY_SHOOT = "key.nuka-isles.shoot";

    public static KeyBinding shootingKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (shootingKey.wasPressed()) {
                assert client.player != null;
                if (client.player.getEquippedStack(EquipmentSlot.MAINHAND).getItem() instanceof TenMmPistolItem) {
                    client.player.sendMessage(Text.of("Test"));
                    ClientPlayNetworking.send(ModMessages.SHOOTING_ID, PacketByteBufs.create());
//                    ServerPlayNetworking.send(client.player., ModMessages.SHOOTING_SYNC_ID, PacketByteBufs.create());
                }
            }
        });
    }

    public static void register() {
        shootingKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_SHOOT,
                InputUtil.Type.MOUSE,
                GLFW.GLFW_MOUSE_BUTTON_1,
                KEY_CATEGORY_NUKAISLES
        ));
        registerKeyInputs();
    }

}