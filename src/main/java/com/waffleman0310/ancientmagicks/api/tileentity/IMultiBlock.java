package com.waffleman0310.ancientmagicks.api.tileentity;

import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.util.ITickable;

public interface IMultiBlock extends ITickable {

	void form();

	boolean canForm();

	BlockPattern getPattern();
}
