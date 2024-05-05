package com.derpz.nukaisles.block.entity;

import com.derpz.nukaisles.item.ModItems;
import com.derpz.nukaisles.screen.NukaColaMachineScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NukaColaMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private static final int FUEL_SLOT = 0;
    private static final int FIRST_OUTPUT_SLOT = 1;
    private static final int LAST_OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public NukaColaMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NUKA_COLA_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NukaColaMachineBlockEntity.this.progress;
                    case 1 -> NukaColaMachineBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                 switch (index) {
                    case 0 -> NukaColaMachineBlockEntity.this.progress = value;
                    case 1 -> NukaColaMachineBlockEntity.this.maxProgress = value;
                };
            }

            //Has to be the size of ints we sync
            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    //save data
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("nuka_cola_machine.progress", progress);
    }

    //load data
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("nuka_cola_machine.progress");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        //make translatable after
        return Text.literal("Nuka Cola Machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NukaColaMachineScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }
        if (isOutPutSlotEmptyOrReceivable()) {
            if (this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
            
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }


    //done
    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        this.removeStack(FUEL_SLOT, 1);
        ItemStack result = new ItemStack(ModItems.NUKA_COLA);

        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (getStack(i).isEmpty()) {
                setStack(i, result.copy());
                return;
            } else if (getStack(i).getItem() == ModItems.NUKA_COLA && getStack(i).getCount() < getStack(i).getMaxCount()) {
                getStack(i).increment(1);
                return;
            }
        }
    }

    //done
    private boolean hasCraftingFinished() {
        return  progress >= maxProgress;
    }

    //done
    private void increaseCraftProgress() {
        progress++;
    }

    //done
    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.NUKA_COLA);
        boolean hasInput = getStack(FUEL_SLOT).getItem() == Items.ICE.asItem();

        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (this.getStack(i).getItem() == item || this.getStack(i).isEmpty()) {
                return true;
            }
            //return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
        }
        return false;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (getStack(i).isEmpty() || getStack(i).getCount() < getStack(i).getMaxCount()) {
                return true;
            }
        }
        return false;
    }
       // return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();

    private boolean isOutPutSlotEmptyOrReceivable() {
        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (getStack(i).isEmpty() || getStack(i).getCount() < getStack(i).getMaxCount()) {
                return true;
            }
        }
        return false;
    }
}
