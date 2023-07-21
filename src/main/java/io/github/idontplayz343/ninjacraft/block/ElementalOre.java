package io.github.idontplayz343.ninjacraft.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.int_provider.UniformIntProvider;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class ElementalOre extends ExperienceDroppingBlock {
    public ElementalOre(){
        super(QuiltBlockSettings
                .copyOf(Blocks.DEEPSLATE_DIAMOND_ORE)
                .requiresTool()
                .strength(3.0f, 6.0f)
                .sounds(BlockSoundGroup.STONE), UniformIntProvider.create(3, 20));
    }
}
