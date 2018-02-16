package com.waffleman0310.ancientmagicks.api.world.gen.feature.tree;

import com.waffleman0310.ancientmagicks.api.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.api.util.helpers.TreeHelper;
import com.waffleman0310.ancientmagicks.api.util.helpers.TreeHelper.EnumTreeShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.Random;

public interface ISpacialColNormalGenerator extends ITreeGenerator {

	int getMinTrunkHeight();

	int getMaxTrunkHeight();

	int getMinTrunkWidth();

	int getMaxTrunkWidth();

	int getMinCrownHeight(int trunkHeight, int trunkTopY);

	int getMaxCrownHeight(int trunkHeight, int trunkTopY);

	int getMinCrownDiameter();

	int getMaxCrownDiameter();

	int getTrunkCrownOverlap();


	int getMinRootfieldDiameter();

	int getMaxRootfieldDiameter();

	int getMinRootDepth(BlockPos pos);

	int getMaxRootDepth(BlockPos pos);

	int getMinLeafDiameter();

	int getMaxLeafDiameter();

	int getTrunkRootOverlap();


	int getCrownNodes();

	int getCrownAttractionRadius();

	int getCrownRemoveRadius();

	int getCrownBranchLength();

	float getCrownSizeDecrement();


	int getRootNodes();

	int getRootAttractionRadius();

	int getRootRemovalRadius();

	int getRootBranchLength();

	float getLeafRoughness();

	float getRootSizeDecrement();


	String getTreeName();


	EnumTreeShape getCrownShape();

	EnumTreeShape getRootShape();


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
		int subTrunkWidth = MathHelper.ceil(trunkWidth / 6.5);
		int subTrunkIndent = (int) (trunkWidth / 2.5f);
		int innerTrunkIndent = (int) (subTrunkWidth / 1.5f);

		TreeHelper.generateNormalTrunk(
				worldIn,
				position,
				this,
				trunkHeight,
				trunkWidth,
				subTrunkIndent,
				innerTrunkIndent,
				1.5f,
				2.0f
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
