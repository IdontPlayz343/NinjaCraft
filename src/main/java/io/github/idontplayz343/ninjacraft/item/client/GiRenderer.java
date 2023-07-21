package io.github.idontplayz343.ninjacraft.item.client;

import io.github.idontplayz343.ninjacraft.item.custom.GiArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class GiRenderer extends GeoArmorRenderer<GiArmorItem> {
	public GiRenderer() {
		super(new GiModel());
	}
}
