package com.waffleman0310.ancientmagicks.api.world.gen.feature.tree;

import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.api.util.helpers.TreeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.Random;

public interface ISpacialColTwistedGenerator extends ISpacialColNormalGenerator {

	int getMinCurves();

	int getMaxCurves();

	int getMinCurveAmplitude();

	int getMaxCurveAmplitude();

	@Override
	default boolean generateTree(World worldIn, Random rand, BlockPos position) {
		AncientMagicksUtil.log(Level.WARN, String.format("Starting %s growth...", getTreeName()));

		int crownWidth = MathHelper.getInt(rand, getMinCrownDiameter(), getMaxCrownDiameter());
		int crownDepth = MathHelper.getInt(rand, getMinCrownDiameter(), getMaxCrownDiameter());
		int rootfieldWidth = MathHelper.getInt(rand, getMinRootfieldDiameter(), getMaxRootfieldDiameter());
		int rootfieldDepth = MathHelper.getInt(rand, getMinRootfieldDiameter(), getMaxRootfieldDiameter());
		int rootfieldHeight = MathHelper.getInt(rand, getMinRootDepth(position), getMaxRootDepth(position));
		int trunkWidth = MathHelper.getInt(rand, getMinTrunkWidth(), getMaxTrunkWidth());
		int trunkHeight = MathHelper.getInt(rand, getMinTrunkHeight(), getMaxTrunkHeight());
		int crownHeight = MathHelper.getInt(rand,
				getMinCrownHeight(trunkHeight, position.getY() + trunkHeight),
				getMaxCrownHeight(trunkHeight, position.getY() + trunkHeight)
		);
		int trunkCurves = MathHelper.getInt(rand, getMinCurves(), getMaxCurves());
		int trunkCurveAmplitude = MathHelper.getInt(rand, getMinCurveAmplitude(), getMaxCurveAmplitude());
		int subTrunkWidth = MathHelper.ceil(trunkWidth / 6.5);
		int subTrunkIndent = (int) (trunkWidth / 2.5f);
		int innerTrunkIndent = (int) (subTrunkWidth / 1.5f);

		TreeHelper.generateTwistedTrunk(
				worldIn,
				position,
				this,
				trunkHeight,
				trunkWidth,
				trunkCurves,
				trunkCurveAmplitude,
				subTrunkIndent,
				innerTrunkIndent,
				1.5f,
				2.0f,
				rand
		);

		// Generate the crown of the tree.
		TreeHelper.spacialColonizationGenerationWithLeaves(
				worldIn,
				position,
				this,
				getCrownShape(),
				crownWidth,
				crownHeight,
				crownDepth,
				0,
				trunkHeight - getTrunkCrownOverlap(),
				0,
				0,
				trunkHeight - getTrunkCrownOverlap(),
				0,
				getCrownNodes(),
				getCrownAttractionRadius(),
				getCrownRemoveRadius(),
				getCrownBranchLength(),
				subTrunkWidth,
				getCrownSizeDecrement(),
				getLeafRoughness(),
				getMinLeafDiameter(),
				getMaxLeafDiameter(),
				rand
		);

		// Generate the roots of the tree.
		TreeHelper.spacialColonizationGenerationWithoutLeaves(
				worldIn,
				position,
				this,
				getRootShape(),
				rootfieldWidth,
				rootfieldHeight + getTrunkRootOverlap(),
				rootfieldDepth,
				0,
				-rootfieldHeight,
				0,
				0,
				getTrunkRootOverlap(),
				0,
				getRootNodes(),
				getRootAttractionRadius(),
				getRootRemovalRadius(),
				getRootBranchLength(),
				subTrunkWidth,
				getRootSizeDecrement(),
				rand
		);

		return true;
	}
}
