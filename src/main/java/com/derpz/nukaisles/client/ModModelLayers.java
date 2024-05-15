package com.derpz.nukaisles.client;

import com.derpz.nukaisles.NukaIsles;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer UNDER_ARMOR =
            new EntityModelLayer(new Identifier(NukaIsles.MOD_ID, "under_armor"), "main");
}
