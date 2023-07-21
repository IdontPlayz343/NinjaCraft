package io.github.idontplayz343.ninjacraft.item;
import io.github.idontplayz343.ninjacraft.NinjaCraft;
import io.github.idontplayz343.ninjacraft.item.custom.GiArmorItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ModItems {
    public static final Item IRON_KATANA = registerItem("iron_katana", new KatanaItem(ToolMaterials.IRON, 1, 6F, new QuiltItemSettings()));
    public static final Item GOLDEN_KATANA = registerItem("golden_katana", new KatanaItem(ToolMaterials.GOLD, 4, 6F, new QuiltItemSettings()));
    public static final Item DIAMOND_KATANA = registerItem("diamond_katana", new KatanaItem(ToolMaterials.DIAMOND, 2, 6F, new QuiltItemSettings()));
    public static final Item NETHERITE_KATANA = registerItem("netherite_katana", new KatanaItem(ToolMaterials.NETHERITE, 2, 6F, new QuiltItemSettings().fireproof()));
    public static final Item SWORD_OF_FIRE = registerItem("sword_of_fire", new SwordOfFire(ToolMaterials.GOLD, 6, 6F, new QuiltItemSettings().fireproof().rarity(Rarity.EPIC)));
    public static final Item GEM_OF_FIRE = registerItem("gem_of_fire", new GemOfFire());
	public static final Item GI_MASK = registerItem("gi_mask",
			new GiArmorItem(ModArmorMaterials.GI, ArmorItem.ArmorSlot.HELMET, new QuiltItemSettings()));
	public static final Item GI_TUNIC = registerItem("gi_tunic",
			new GiArmorItem(ModArmorMaterials.GI, ArmorItem.ArmorSlot.CHESTPLATE, new QuiltItemSettings()));
	public static final Item GI_LEGGINGS = registerItem("gi_leggings",
			new GiArmorItem(ModArmorMaterials.GI, ArmorItem.ArmorSlot.LEGGINGS, new QuiltItemSettings()));
	public static final Item GI_SHOES = registerItem("gi_shoes",
			new GiArmorItem(ModArmorMaterials.GI, ArmorItem.ArmorSlot.BOOTS, new QuiltItemSettings()));
    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(NinjaCraft.MOD_ID, name), item);
    }
    public static void registerModItems(){
        NinjaCraft.LOGGER.info("Registering mod items for " + NinjaCraft.MOD_ID);
    }
}
