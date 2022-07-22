package net.iamaprogrammer.heartcanister;

import net.fabricmc.api.ModInitializer;
import net.iamaprogrammer.heartcanister.item.ModItems;
import net.iamaprogrammer.heartcanister.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartCanisters implements ModInitializer {
	public static final String MOD_ID = "heartcanister";
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();

	}
}
