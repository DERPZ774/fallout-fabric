package com.derpz.nukaisles.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;

public class GunUtil {
    private static final float DEFAULT_RECOIL_AMOUNT = 2.0f;
    private static final float DEFAULT_RECOIL_DURATION = 10.0f;

    private float currentRecoilAmount = 0.0f;
    private float recoilDuration = 0.0f;
    private int remainingTicks = 0;

    public void update() {
        if (remainingTicks > 0) {
            // Calculate interpolation amount based on remaining ticks
            float progress = 1.0f - (float) remainingTicks / (float) recoilDuration;
            // Gradually decrease recoil amount over time
            currentRecoilAmount -= currentRecoilAmount * progress;
            // Calculate the new pitch based on the recoil amount and the player's current pitch
            assert MinecraftClient.getInstance().player != null;
            float newPitch = MinecraftClient.getInstance().player.getPitch() + currentRecoilAmount;
            // Clamp the new pitch to prevent flipping
            newPitch = MathHelper.clamp(newPitch, -90.0F, 90.0F);
            // Apply the new pitch to the player's camera
            MinecraftClient.getInstance().player.setPitch(newPitch);
            // Decrement remaining ticks
            remainingTicks--;
        } else {
            // Reset recoil if no remaining ticks
            currentRecoilAmount = 0.0f;
        }
    }

    public void applyRecoil(float recoilAmount, float duration) {
        // Add recoil amount
        currentRecoilAmount -= recoilAmount;
        // Set recoil duration
        recoilDuration = duration;
        // Set remaining ticks
        remainingTicks = (int) duration;
    }
}
