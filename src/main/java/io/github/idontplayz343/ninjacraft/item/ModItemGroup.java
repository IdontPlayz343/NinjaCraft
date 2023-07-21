package io.github.idontplayz343.ninjacraft.item;

import io.github.idontplayz343.ninjacraft.NinjaCraft;
import io.github.idontplayz343.ninjacraft.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static ItemGroup NinjaGear = Registry.register(Registries.ITEM_GROUP, new Identifier(NinjaCraft.MOD_ID, "ninjacraft"),
            FabricItemGroup.builder().name(Text.translatable("itemgroup.ninjagear"))
                    .icon(() -> new ItemStack(ModItems.IRON_KATANA)).entries((displayContext, entries) -> {
                        entries.addItem(ModItems.IRON_KATANA);
                        entries.addItem(ModItems.GOLDEN_KATANA);
                        entries.addItem(ModItems.DIAMOND_KATANA);
                        entries.addItem(ModItems.NETHERITE_KATANA);
						entries.addItem(ModItems.GI_MASK);
						entries.addItem(ModItems.GI_TUNIC);
						entries.addItem(ModItems.GI_LEGGINGS);
						entries.addItem(ModItems.GI_SHOES);
                    }).build());
	public static ItemGroup ElementalPowers = Registry.register(Registries.ITEM_GROUP, new Identifier(NinjaCraft.MOD_ID+1, "ninjacraft"),
			FabricItemGroup.builder().name(Text.translatable("itemgroup.elemental"))
					.icon(() -> new ItemStack(ModItems.GEM_OF_FIRE)).entries((displayContext, entries) -> {
						entries.addItem(ModItems.SWORD_OF_FIRE);
						entries.addItem(ModItems.GEM_OF_FIRE);
						entries.addItem(ModBlocks.ELEMENTAL_ORE);
					}).build());
    public static void registerItemGroups() {

    }
}
