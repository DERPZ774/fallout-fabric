package com.derpz.nukaisles.item.custom;

import com.derpz.nukaisles.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class NukaColaItem extends Item {
    public NukaColaItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity) user : null;
        if (playerEntity != null) {
            if (!world.isClient) {
                if (!playerEntity.getAbilities().creativeMode) {
                    stack.decrement(1);
                    playerEntity.giveItemStack(new ItemStack(ModItems.BOTTLE_CAP.asItem()));
                }
            }
            world.emitGameEvent(playerEntity, GameEvent.DRINK, playerEntity.getEyePos());
        }
        return super.finishUsing(stack, world, user);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

//        if (pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock().equals(ModBlocks.NUKA_COLA_MACHINE.get()) && !pContext.getItemInHand().hasTag()) {
//            pContext.getLevel().playSound(pContext.getPlayer(), pContext.getClickedPos(), ModSounds.COLA_DECAP.get(), SoundSource.BLOCKS, 4.0f, 1.0f);
//            if (!pContext.getLevel().isClientSide) {
//                pContext.getItemInHand().getOrCreateTag().putInt("cap", 1);
//                Objects.requireNonNull(pContext.getPlayer()).getInventory().add(new ItemStack(ModItems.BOTTLE_CAP.get()));
//            }
//        }
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
    @Override
    public SoundEvent getDrinkSound() {
        return super.getDrinkSound();
    }
}
