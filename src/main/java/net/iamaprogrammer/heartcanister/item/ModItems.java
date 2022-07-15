package net.iamaprogrammer.heartcanister.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.iamaprogrammer.heartcanister.HeartCanisters;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item RED_CANISTER = registerItem("red_heart_canister",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item BLUE_CANISTER = registerItem("blue_heart_canister",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item GREEN_CANISTER = registerItem("green_heart_canister",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(HeartCanisters.MOD_ID, name), item);
    }
    public static void registerModItems() {
        HeartCanisters.LOGGER.info("Registering Mod Items for " + HeartCanisters.MOD_ID);
    }
}
