package io.github.idontplayz343.ninjacraft.item.custom;

import io.github.idontplayz343.ninjacraft.item.client.GiRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GiArmorItem extends DyeableArmorItem implements GeoItem {

	private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
	final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

	public GiArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	private boolean isWearingFullSet(LivingEntity entity) {
		for (ItemStack stack : entity.getArmorItems()) {
			if (!(stack.getItem() instanceof GiArmorItem)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient && entity instanceof LivingEntity && isWearingFullSet((LivingEntity) entity)) {
			LivingEntity livingEntity = (LivingEntity) entity;
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 0, true, false));
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 20, 0, true, false));
			livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20, 0, true, false));
		}
	}

	@Override
	public void createRenderer(Consumer<Object> consumer) {
		consumer.accept(new RenderProvider() {
			private GiRenderer renderer;

			@Override
			public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
				if (this.renderer == null)
					this.renderer = new GiRenderer();

				this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return this.renderProvider;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController(this,"controller",0, this::predicate));
	}

	private PlayState predicate(AnimationState animationState){
		animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
		return PlayState.CONTINUE;
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
