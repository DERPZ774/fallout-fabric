package com.derpz.nukaisles.effect;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.effect.effects.StealthyEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect STEALTHY = registerStatusEffect("stealthy", new StealthyEffect(StatusEffectCategory.BENEFICIAL, 0xff));

    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(NukaIsles.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        NukaIsles.LOGGER.info("Registering Mod Effects for " + NukaIsles.MOD_ID);
    }

}
