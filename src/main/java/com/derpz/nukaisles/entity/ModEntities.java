package com.derpz.nukaisles.entity;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.entity.custom.BulletProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BulletProjectileEntity> BULLET_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(NukaIsles.MOD_ID, "bullet_projectile"), EntityType.Builder.<BulletProjectileEntity>create(
                    BulletProjectileEntity::new, SpawnGroup.MISC).setDimensions(0.75f, 0.75f)
                    .maxTrackingRange(4).trackingTickInterval(10).build());
}
