package com.derpz.nukaisles.entity.custom;

import com.derpz.nukaisles.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BulletProjectileEntity extends ProjectileEntity {
    private float damage;
    private Vec3d vel;
    private int maxLife;
    private int lifeTicks;

    public BulletProjectileEntity(EntityType<BulletProjectileEntity> type, World world) {
        super(ModEntities.BULLET_PROJECTILE, world);
    }

    public BulletProjectileEntity(LivingEntity owner, World world, float bulletDamage) {
        super(ModEntities.BULLET_PROJECTILE, world);
        this.damage = bulletDamage;
       // this.setNoGravity(true);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick() {
        super.tick();
    }

//    @Override
//    public boolean hasNoGravity() {
//        return !this.isTouchingWater();
//    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if(entityHitResult.getEntity() instanceof LivingEntity entity)
        {
            entity.damage(getDamageSources().thrown(this, this.getOwner() != null ? this.getOwner():this), this.damage);
            entity.timeUntilRegen = 0;
            System.out.println("Hit entity");
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        System.out.println("Hit block");
        this.discard();
    }

    public void setBaseVel(Vec3d vel) {
        this.vel = vel;
    }
}
