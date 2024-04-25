package com.derpz.nukaisles.entity.custom;

import com.derpz.nukaisles.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BulletProjectileEntity extends PersistentProjectileEntity {
    private float damage;
    private Vec3d vel;
    private int maxLife;
    private int lifeTicks;

    public BulletProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public BulletProjectileEntity(LivingEntity owner, World world, float bulletDamage) {
        super(ModEntities.BULLET_PROJECTILE, owner, world);
        this.maxLife = 10;
        this.lifeTicks = 0;
        this.damage = bulletDamage;
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        this.setVelocity(vel);

        if(this.lifeTicks++ >= this.maxLife) {
            this.discard();
        }
    }

    @Override
    public boolean hasNoGravity() {
        return !this.isTouchingWater();
    }

    @Override
    protected ItemStack asItemStack() {
        return null;
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
        this.discard();
    }

    public void setVel(Vec3d vel) {
        this.vel = vel;
    }
}
