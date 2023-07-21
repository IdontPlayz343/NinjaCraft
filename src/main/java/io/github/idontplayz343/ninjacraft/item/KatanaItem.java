package io.github.idontplayz343.ninjacraft.item;

import net.minecraft.item.*;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class KatanaItem extends SwordItem implements Vanishable {

    public KatanaItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, QuiltItemSettings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
