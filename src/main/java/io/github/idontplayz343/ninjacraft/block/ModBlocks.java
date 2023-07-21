package io.github.idontplayz343.ninjacraft.block;
import io.github.idontplayz343.ninjacraft.NinjaCraft;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModBlocks {

    public static final Block ELEMENTAL_ORE = registerBlock("elemental_ore", new ElementalOre());

    private static Block registerBlock(String name, Block block) {
       registerBlockItem(name, block);
       return Registry.register(Registries.BLOCK, new Identifier(NinjaCraft.MOD_ID, name), block);
    }
    private static final Item registerBlockItem(String name, Block block){
        Item item = Registry.register(Registries.ITEM, new Identifier(NinjaCraft.MOD_ID, name),
                new BlockItem(block, new QuiltItemSettings()));
        return item;
    }
    public static void registerModBlocks(){
        NinjaCraft.LOGGER.info("Registering mod blocks for " + NinjaCraft.MOD_ID);
    }
}
