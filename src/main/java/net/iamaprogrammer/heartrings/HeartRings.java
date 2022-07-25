package net.iamaprogrammer.heartrings;

import net.fabricmc.api.ModInitializer;
import net.iamaprogrammer.heartrings.item.ModItems;
import net.iamaprogrammer.heartrings.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartRings implements ModInitializer {
	public static final String MOD_ID = "heartrings";
	public static final Logger LOGGER = LoggerFactory.getLogger("heartrings");

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();

	}
}
