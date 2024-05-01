package com.derpz.nukaisles.event;

import com.derpz.nukaisles.item.custom.GunItem;
import com.derpz.nukaisles.networking.ModMessages;
import com.derpz.nukaisles.sound.ModSounds;
import com.derpz.nukaisles.util.GunUtil;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_NUKAISLES = "key.category.nuka-isles";
    public static final String KEY_SHOOT = "key.nuka-isles.shoot";

    public static KeyBinding shootingKey;
    private static final GunUtil gunUtil = new GunUtil(); // Create an instance of GunUtil

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (shootingKey.wasPressed()) {
                assert client.player != null;
                ItemStack heldItem = client.player.getMainHandStack();
                if (heldItem.getItem() instanceof GunItem) {
                    SoundEvent sound = ((GunItem) heldItem.getItem()).getSound();
                    ClientPlayNetworking.send(ModMessages.SHOOTING_ID, PacketByteBufs.create());
                    client.player.playSound(sound , SoundCategory.PLAYERS, 0.4f, 1f);
                    float recoil = ((GunItem) heldItem.getItem()).getRecoil();
                    gunUtil.applyRecoil(recoil, 4.0f); // Adjust recoil amount and duration as needed
                }
            }
            gunUtil.update();
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