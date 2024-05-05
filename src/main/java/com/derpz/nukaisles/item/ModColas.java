package com.derpz.nukaisles.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModColas {
    private final FoodComponent foodComponent;

    public ModColas(int hunger, float saturationModifier, int statusEffectDuration) {
        StatusEffectInstance effect = new StatusEffectInstance(StatusEffects.LUCK, statusEffectDuration);
        FoodComponent.Builder builder = new FoodComponent.Builder().hunger(hunger).saturationModifier(saturationModifier).statusEffect(effect, 1.0F);
        this.foodComponent = builder.build();
    }

    public FoodComponent getFoodComponent() {
        return foodComponent;
    }

}
