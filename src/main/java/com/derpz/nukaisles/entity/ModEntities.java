package com.derpz.nukaisles.entity;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.entity.custom.BulletProjectileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ModEntities {
    public static final EntityType<BulletProjectileEntity> BULLET_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(NukaIsles.MOD_ID, "bullet_projectile"), EntityType.Builder.<BulletProjectileEntity>create(
                    BulletProjectileEntity::new, SpawnGroup.MISC).setDimensions(0.0625f, 0.0625f)
                    .maxTrackingRange(4).trackingTickInterval(10).build());

    private static <E extends Entity, T extends EntityType<E>> T register(@NotNull String name, @NotNull T entityType) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(NukaIsles.MOD_ID, name), entityType);
    }

    public static void registerEntities() {
        NukaIsles.LOGGER.info("Registering Entities for " + NukaIsles.MOD_ID);
    }
}
