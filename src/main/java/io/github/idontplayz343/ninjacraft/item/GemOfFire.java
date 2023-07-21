package io.github.idontplayz343.ninjacraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Rarity;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class GemOfFire extends Item {
    public GemOfFire(){
        super(new QuiltItemSettings().maxCount(64).rarity(Rarity.UNCOMMON).fireproof());
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.addItem(this));
    }
}
