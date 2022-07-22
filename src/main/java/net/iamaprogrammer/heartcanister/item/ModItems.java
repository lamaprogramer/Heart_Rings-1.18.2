package net.iamaprogrammer.heartcanister.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.iamaprogrammer.heartcanister.HeartCanisters;
import net.iamaprogrammer.heartcanister.item.custom.LifeRingItem;
import net.iamaprogrammer.heartcanister.item.custom.LifeShardItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    // Heart Rings
    public static final Item RED_RING = registerItem("red_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1), 4.0f));

    public static final Item BLUE_RING = registerItem("blue_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1), 8.0f));

    public static final Item GREEN_RING = registerItem("green_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1), 12.0f));

    public static final Item YELLOW_RING = registerItem("yellow_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1), 16.0f));

    public static final Item PURPLE_RING = registerItem("purple_heart_ring",
            new LifeRingItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1), 20.0f));


    // Life Shard
    public static final Item LIFE_SHARD = registerItem("red_heart_shard",
            new LifeShardItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16), 1));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(HeartCanisters.MOD_ID, name), item);
    }
    public static void registerModItems() {
        HeartCanisters.LOGGER.info("Registering Mod Items for " + HeartCanisters.MOD_ID);
    }
}
