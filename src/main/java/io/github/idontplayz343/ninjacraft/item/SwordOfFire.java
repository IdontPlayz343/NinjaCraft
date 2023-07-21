package io.github.idontplayz343.ninjacraft.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.List;


public class SwordOfFire extends ToolItem {

	private final float attackDamage;
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	public SwordOfFire(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, QuiltItemSettings settings) {
		super(toolMaterial, settings.rarity(Rarity.EPIC));
		this.attackDamage = (float) attackDamage;
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
		builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		EntityAttributeModifier FIRE_RESISTANCE_MODIFIER = new EntityAttributeModifier(
				"Sword of Fire Modifier",
				0.0,
				EntityAttributeModifier.Operation.ADDITION
		);

		ItemStack itemStack = user.getStackInHand(hand);
		user.getItemCooldownManager().set(this, 10); // Cooldown of 10 ticks

		if (!world.isClient) {
			SmallFireballEntity fireball = new SmallFireballEntity(EntityType.SMALL_FIREBALL, world);
			fireball.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);

			// Calculate fireball direction based on player's cursor position
			Vec3d lookVec = user.getRotationVec(1.0F);
			fireball.setVelocity(lookVec.x, lookVec.y, lookVec.z);

			fireball.setPosition(user.getX(), user.getBodyY(0.5) + 0.5, user.getZ());

			world.spawnEntity(fireball);
			world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.3F, 1.1F / (world.random.nextFloat() * 0.4F + 1.2F));
		}

		return TypedActionResult.success(itemStack);
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
		return !miner.isCreative();
	}

	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		if (state.isOf(Blocks.COBWEB)) {
			return 50.0F;
		} else {
			return state.isIn(BlockTags.SWORD_EFFICIENT) ? 5F : 1.0F;
		}
	}

	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return true;
	}

	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		return true;
	}

	public boolean isSuitableFor(BlockState state) {
		return state.isOf(Blocks.COBWEB);
	}

	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}

	public static class FireTarget {
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.translatable("item.ninjacraft.sword_of_fire.tooltip"));
		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		StatusEffectInstance fireResistance = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60, 1, true, false);
		StatusEffectInstance strength = new StatusEffectInstance(StatusEffects.STRENGTH, 5, 2, true, true);
		if (selected && entity instanceof LivingEntity) {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addStatusEffect(fireResistance, livingEntity);
			if (entity.isOnFire()) {
				livingEntity.addStatusEffect(strength, livingEntity);
			}
		}
		super.inventoryTick(stack, world, entity, slot, selected);
	}
}
