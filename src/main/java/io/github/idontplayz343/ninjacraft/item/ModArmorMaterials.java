package io.github.idontplayz343.ninjacraft.item;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

	GI("gi", 18, Util.make(new EnumMap(ArmorItem.ArmorSlot.class), (map) -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 1);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 2);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 3);
		map.put(ArmorItem.ArmorSlot.HELMET, 1);
	}), 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 3.0F, 0.1F, () -> {
		return Ingredient.ofItems(new ItemConvertible[]{Items.WHITE_WOOL});
	});

	public static final StringIdentifiable.EnumCodec<ArmorMaterials> CODEC = StringIdentifiable.createCodec(ArmorMaterials::values);
	private static final EnumMap<ArmorItem.ArmorSlot, Integer> BASE_DURABILITY_VALUES = Util.make(new EnumMap(ArmorItem.ArmorSlot.class), (map) -> {
		map.put(ArmorItem.ArmorSlot.BOOTS, 13);
		map.put(ArmorItem.ArmorSlot.LEGGINGS, 15);
		map.put(ArmorItem.ArmorSlot.CHESTPLATE, 16);
		map.put(ArmorItem.ArmorSlot.HELMET, 11);
	});
	private final String name;
	private final int durabilityMultiplier;
	private final EnumMap<ArmorItem.ArmorSlot, Integer> slotProtections;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredientSupplier;

	private ModArmorMaterials(String name, int durabilityMultiplier, EnumMap slotProtections, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.slotProtections = slotProtections;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
	}

	public int getDurability(ArmorItem.ArmorSlot slot) {
		return (Integer)BASE_DURABILITY_VALUES.get(slot) * this.durabilityMultiplier;
	}

	public int getProtection(ArmorItem.ArmorSlot slot) {
		return (Integer)this.slotProtections.get(slot);
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	public Ingredient getRepairIngredient() {
		return (Ingredient)this.repairIngredientSupplier.get();
	}

	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

	public String asString() {
		return this.name;
	}
}
