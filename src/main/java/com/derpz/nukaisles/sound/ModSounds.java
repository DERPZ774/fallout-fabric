package com.derpz.nukaisles.sound;

import com.derpz.nukaisles.NukaIsles;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent LAMP_CLICK = registerSoundEvent("lamp_use");
    public static final SoundEvent TEN_MM_SHOOT = registerSoundEvent("ten_mm_shoot");

//    public static final BlockSoundGroup EXAMPLE_BLOCK_SOUNDS = new BlockSoundGroup(1f, 1f,
//            ModSounds.LAMP_CLICK, ModSounds.LAMP_CLICK, ModSounds.LAMP_CLICK, ModSounds.LAMP_CLICK, ModSounds.LAMP_CLICK);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(NukaIsles.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        NukaIsles.LOGGER.info("Registering Sounds for " + NukaIsles.MOD_ID);
    }
}
