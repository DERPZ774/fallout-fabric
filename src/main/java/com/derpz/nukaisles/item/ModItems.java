package com.derpz.nukaisles.item;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.item.custom.TenMmPistolItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final Item BOTTLE_CAP = registerItem("bottle_cap", new Item(new FabricItemSettings()));
    public static final Item LIGHTBULB = registerItem("lightbulb", new Item(new FabricItemSettings()));
    public static final Item TEN_MM_PISTOL = registerItem("ten_mm_pistol", new TenMmPistolItem(new FabricItemSettings()));
    public static final Item SCRAP_METAL = registerItem("scrap_metal", new Item(new FabricItemSettings()));

//    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
//        entries.add(BOTTLE_CAP);
//        entries.add(SCRAP_METAL);
//    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(NukaIsles.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NukaIsles.LOGGER.info("Registering Mod Items for " + NukaIsles.MOD_ID);
       // ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }

    //Dynamically adds all registered items to a list for ItemGroup to read
    public static List<Item> getAllModItems() {
        List<Item> items = new ArrayList<>();
        // Retrieve all registered items
        Field[] itemFields = ModItems.class.getDeclaredFields();
        for (Field field : itemFields) {
            try {
                Object obj = field.get(null);
                if (obj instanceof Item) {
                    items.add((Item) obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return items;
    }



}
