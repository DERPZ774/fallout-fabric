package com.derpz.nukaisles.entity.custom;

import com.derpz.nukaisles.entity.ModEntities;
import com.derpz.nukaisles.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BulletProjectileEntity extends PersistentProjectileEntity {
    private float damage;

    public BulletProjectileEntity(EntityType<BulletProjectileEntity> type, World world) {
        super(type, world, ModItems.SCRAP_METAL.getDefaultStack());
    }

    public BulletProjectileEntity(LivingEntity owner, World world, float bulletDamage, Vec3d vel) {
        super(ModEntities.BULLET_PROJECTILE, world, ModItems.SCRAP_METAL.getDefaultStack());
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.10000000149011612, owner.getZ());
        this.damage = bulletDamage;
        this.setVelocity(vel);
        this.setNoGravity(true);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if(entityHitResult.getEntity() instanceof LivingEntity entity)
        {
            entity.damage(getDamageSources().thrown(this, this.getOwner() != null ? this.getOwner():this), this.damage);
            entity.timeUntilRegen = 0;
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        if (!this.getWorld().isClient()) {
            this.getWorld().sendEntityStatus( this, (byte) 3);
        }
        this.discard();
    }
}
