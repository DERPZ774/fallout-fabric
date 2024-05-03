package com.derpz.nukaisles.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;

public class GunUtil {
    private float currentRecoilAmount = 0.0f;
    private float recoilDuration = 0.0f;
    private int remainingTicks = 0;

    public void update() {
        if (remainingTicks > 0) {
            float progress = 1.0f - (float) remainingTicks / (float) recoilDuration;
            currentRecoilAmount -= currentRecoilAmount * progress;
            assert MinecraftClient.getInstance().player != null;
            float newPitch = MinecraftClient.getInstance().player.getPitch() + currentRecoilAmount;
            newPitch = MathHelper.clamp(newPitch, -90.0F, 90.0F);
            MinecraftClient.getInstance().player.setPitch(newPitch);
            remainingTicks--;
        } else {
            currentRecoilAmount = 0.0f;
        }
    }

    public void applyRecoil(float recoilAmount, float duration) {
        currentRecoilAmount -= recoilAmount;
        recoilDuration = duration;
        remainingTicks = (int) duration;
    }
}
