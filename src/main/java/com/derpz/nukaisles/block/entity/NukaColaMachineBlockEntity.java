package com.derpz.nukaisles.block.entity;

import com.derpz.nukaisles.item.ModItems;
import com.derpz.nukaisles.screen.NukaColaMachineScreenHandler;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.SharedConstants;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class NukaColaMachineBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(7, ItemStack.EMPTY);

    private static final int FUEL_SLOT = 0;
    private static final int FIRST_OUTPUT_SLOT = 1;
    private static final int LAST_OUTPUT_SLOT = 6;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;
    private int fuel = 0;
    private int maxFuel = 3500;
    private int minChill = 20;
    private int extractionTime = 0;

    public NukaColaMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NUKA_COLA_MACHINE_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NukaColaMachineBlockEntity.this.progress;
                    case 1 -> NukaColaMachineBlockEntity.this.maxProgress;
                    case 2 -> NukaColaMachineBlockEntity.this.fuel;
                    case 3 -> NukaColaMachineBlockEntity.this.maxFuel;
                    case 4 -> NukaColaMachineBlockEntity.this.minChill;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NukaColaMachineBlockEntity.this.progress = value;
                    case 1 -> NukaColaMachineBlockEntity.this.maxProgress = value;
                    case 2 -> NukaColaMachineBlockEntity.this.fuel = value;
                    case 3 -> NukaColaMachineBlockEntity.this.maxFuel = value;
                    case 4 -> NukaColaMachineBlockEntity.this.minChill = value;
                };
            }

            //Has to be the size of ints we sync
            @Override
            public int size() {
                return 5;
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
        nbt.putInt("nuka_cola_machine.fuel", fuel);
    }

    //load data
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("nuka_cola_machine.progress");
        fuel = nbt.getInt("nuka_cola_machine.fuel");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.entity.nuka-isles.nuka_cola_machine");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NukaColaMachineScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }


    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        add(map, Items.SNOWBALL, 500);
        add(map, Items.POWDER_SNOW_BUCKET, 750);
        add(map, Items.SNOW_BLOCK, 1250);
        add(map, Items.ICE, 1750);
        add(map, Items.PACKED_ICE, 2250);
        add(map, Items.BLUE_ICE, 2750);
        return map;
    }

    private static void add(Map<Item, Integer> fuelTimes, ItemConvertible item, int fuelTime) {
        Item item2 = item.asItem();
        if (SharedConstants.isDevelopment) {
            throw Util.throwOrPause(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item2.getName(null).getString() + " a furnace fuel. That will not work!"));
        } else {
            fuelTimes.put(item2, fuelTime);
        }
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) {
            return;
        }

        if (hasCoolant()) {
            markDirty(world, pos, state);
            return;
        }

        if (isOutPutSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                    System.out.println(this.fuel);
                }
            } else {
                this.resetProgress();
            }

        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(ModItems.ICE_COLD_NUKA_COLA);

        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (getStack(i).getItem() == ModItems.NUKA_COLA.asItem()) {
                setStack(i, result.copy());
                return;
            }/* else if (getStack(i).getItem() == ModItems.NUKA_COLA && getStack(i).getCount() < getStack(i).getMaxCount()) {
                getStack(i).increment(1);
                return;
            }*/
            this.fuel -= minChill;
        }
    }

    private boolean hasCraftingFinished() {
        return  progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.NUKA_COLA);
        boolean hasFuel = fuel > minChill;

        return hasFuel && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean hasCoolant() {
        Map<Item, Integer> fuelMap = getFuel();
        ItemStack stack = this.getStack(FUEL_SLOT);
        Item item = stack.getItem();

        extractionTime++;

        int extractionMaxTime = 20;
        if (extractionTime >= extractionMaxTime) {

            if (fuelMap.containsKey(item) && this.fuel != maxFuel) {
                int fuel = fuelMap.get(item);

                if (item == Items.POWDER_SNOW_BUCKET) {
                    this.removeStack(FUEL_SLOT, 1);
                    setStack(0, new ItemStack(Items.BUCKET));
                } else {
                    this.removeStack(FUEL_SLOT, 1);
                }

                //fix powder bucket bug
//            this.removeStack(FUEL_SLOT);
                this.fuel += fuel;
                if (this.fuel > maxFuel) {
                    this.fuel = maxFuel;
                }
            }
            extractionTime = 0;
        }
        return false;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (this.getStack(i).getItem() == item) {
                return true;
            }
        }
        return false;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (getStack(i).getCount() < getStack(i).getMaxCount()) {
                return true;
            }
        }
        return false;
    }

    private boolean isOutPutSlotEmptyOrReceivable() {
        for (int i = FIRST_OUTPUT_SLOT; i <= LAST_OUTPUT_SLOT; i++) {
            if (getStack(i).isEmpty() || getStack(i).getCount() < getStack(i).getMaxCount()) {
                return true;
            }
        }
        return false;
    }
}
