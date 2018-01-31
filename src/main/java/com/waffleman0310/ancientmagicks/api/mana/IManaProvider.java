package com.waffleman0310.ancientmagicks.api.mana;

import net.minecraft.util.EnumFacing;

public interface IManaProvider {

	long extractMana(EnumFacing side, long maxExtract);

}
