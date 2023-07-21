package io.github.idontplayz343.ninjacraft.item.client;

import io.github.idontplayz343.ninjacraft.NinjaCraft;
import io.github.idontplayz343.ninjacraft.item.custom.GiArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GiModel extends GeoModel<GiArmorItem> {
	@Override
	public Identifier getModelResource(GiArmorItem animatable) {
		return new Identifier(NinjaCraft.MOD_ID, "geo/armor/gi.geo.json");
	}

	@Override
	public Identifier getTextureResource(GiArmorItem animatable) {
		return new Identifier(NinjaCraft.MOD_ID, "textures/armor/gi.png");
	}

	@Override
	public Identifier getAnimationResource(GiArmorItem animatable) {
		return new Identifier(NinjaCraft.MOD_ID, "animations/gi.animation.json");
	}
}
