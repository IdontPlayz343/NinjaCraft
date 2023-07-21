package io.github.idontplayz343.ninjacraft;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NinjaCraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "ninjacraft";
	public static final Logger LOGGER = LoggerFactory.getLogger("NinjaCraft");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		io.github.idontplayz343.ninjacraft.item.ModItemGroup.registerItemGroups();
		io.github.idontplayz343.ninjacraft.item.ModItems.registerModItems();
		io.github.idontplayz343.ninjacraft.block.ModBlocks.registerModBlocks();

	}
}
