package com.derpz.nukaisles.item;

import com.derpz.nukaisles.NukaIsles;
import com.derpz.nukaisles.item.custom.GunItem;
import com.derpz.nukaisles.item.custom.NukaColaItem;
import com.derpz.nukaisles.item.custom.UnderArmor;
import com.derpz.nukaisles.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModItems {
    //Regular Items
    public static final Item BOTTLE_CAP = registerItem("bottle_cap", new Item(new FabricItemSettings()));
    public static final Item LIGHTBULB = registerItem("lightbulb", new Item(new FabricItemSettings()));
    public static final Item SCRAP_METAL = registerItem("scrap_metal", new Item(new FabricItemSettings()));
    public static final Item EMPTY_NUKA_COLA = registerItem("empty_nuka_cola", new Item(new FabricItemSettings()));

    //Guns
    public static final Item TEN_MM_PISTOL = registerItem("ten_mm_pistol", new GunItem(new FabricItemSettings(),
            ModSounds.TEN_MM_SHOOT, ModSounds.TEN_MM_DRY, 5, 1, 12));

    public static final Item THIRST_ZAPPER = registerItem("thirst_zapper", new GunItem(new FabricItemSettings(),
            ModSounds.TEN_MM_SHOOT, ModSounds.TEN_MM_DRY,10, 5,  12));

    //Nuka Cola
    public static final Item NUKA_COLA = registerItem("nuka_cola", new NukaColaItem(new FabricItemSettings()));
    public static final Item ICE_COLD_NUKA_COLA = registerItem("ice_cold_nuka_cola", new NukaColaItem(new FabricItemSettings()));

    //Under Armor
    public static final Item VAULT_SUIT =  registerItem("vault_suit", new UnderArmor(new FabricItemSettings(), "test"));


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
