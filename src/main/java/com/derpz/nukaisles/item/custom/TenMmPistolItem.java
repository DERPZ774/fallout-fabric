package com.derpz.nukaisles.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TenMmPistolItem extends Item {
    public TenMmPistolItem(Settings settings) {
        super(settings);
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand
//        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 1F); // plays a globalSoundEvent
//		/*
//		user.getItemCooldownManager().set(this, 5);
//		Optionally, you can add a cooldown to your item's right-click use, similar to Ender Pearls.
//		*/
//        if (!world.isClient) {
//            BulletProjectileEntity bulletProjectileEntity = new BulletProjectileEntity(user, world, 1);
//            bulletProjectileEntity.getStack();
//            bulletProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0F);
//                        /*
//                        snowballEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
//                        In 1.17,we will use setProperties instead of setVelocity.
//                        */
//            world.spawnEntity(bulletProjectileEntity); // spawns entity
//        }
//
//        user.incrementStat(Stats.USED.getOrCreateStat(this));
//        if (!user.getAbilities().creativeMode) {
//            itemStack.decrement(1); // decrements itemStack if user is not in creative mode
//        }
//
//        return TypedActionResult.success(itemStack, world.isClient());
//    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Bomba"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
