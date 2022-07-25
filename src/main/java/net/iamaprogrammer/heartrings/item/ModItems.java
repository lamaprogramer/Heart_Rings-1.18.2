package net.iamaprogrammer.heartrings.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.iamaprogrammer.heartrings.HeartRings;
import net.iamaprogrammer.heartrings.item.custom.LifeRingItem;
import net.iamaprogrammer.heartrings.item.custom.LifeShardItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    // Heart Rings
    public static final Item RED_RING = registerItem("red_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).maxDamage(2), 4.0f, 1));

    public static final Item BLUE_RING = registerItem("blue_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).maxDamage(4), 8.0f, 2));

    public static final Item GREEN_RING = registerItem("green_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).maxDamage(6), 12.0f, 3));

    public static final Item YELLOW_RING = registerItem("yellow_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).maxDamage(8), 16.0f, 4));

    public static final Item PURPLE_RING = registerItem("purple_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).maxDamage(10), 20.0f,  5));


    // Life Shard
    public static final Item LIFE_SHARD = registerItem("red_heart_shard",
            new LifeShardItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16), 1));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(HeartRings.MOD_ID, name), item);
    }
    public static void registerModItems() {
        HeartRings.LOGGER.info("Registering Mod Items for " + HeartRings.MOD_ID);
    }
}
