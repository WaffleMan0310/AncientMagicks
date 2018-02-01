package com.waffleman0310.ancientmagicks.world.feature.gen.ore;

import com.waffleman0310.ancientmagicks.api.world.gen.feature.ore.IOreGenerator;
import com.waffleman0310.ancientmagicks.common.blocks.BlockOre;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.variant.EnumOreType;
import net.minecraft.block.state.IBlockState;

public class OreGenGalena implements IOreGenerator {

	@Override
	public IBlockState getOre() {
		return Blocks.ORE.getDefaultState().withProperty(BlockOre.VARIANT, EnumOreType.GALENA);
	}

	@Override
	public int getGenerationDimension() {
		return 0;
	}

	@Override
	public int getMaxHeight() {
		return 128;
	}

	@Override
	public int getMinHeight() {
		return 10;
	}

	@Override
	public int getMaxVeinSize() {
		return 8;
	}

	@Override
	public int getMinVeinSize() {
		return 4;
	}
}
