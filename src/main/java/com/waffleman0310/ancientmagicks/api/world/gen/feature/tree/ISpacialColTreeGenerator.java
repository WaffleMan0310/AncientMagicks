package com.waffleman0310.ancientmagicks.api.world.gen.feature.tree;

import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import com.waffleman0310.ancientmagicks.util.helpers.TreeHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Random;

public interface ISpacialColTreeGenerator extends ITreeGenerator {
    enum EnumTrunkType {
        MULTITRUNK, HELIXIAL
    }

    int getMinTrunkHeight();

    int getMaxTrunkHeight();

    int getMinTrunkWidth();

    int getMaxTrunkWidth();

    int getMinCrownHeight(int trunkHeight, int trunkTopY);

    int getMaxCrownHeight(int trunkHeight, int trunkTopY);

    int getMinCrownRadius();

    int getMaxCrownRadius();

    int getTrunkCrownOverlap();


    int getMinRootfieldRadius();

    int getMaxRootfieldRadius();

    int getMinRootRadius();

    int getMaxRootRadius();

    int getMinRootDepth(BlockPos pos);

    int getMaxRootDepth(BlockPos pos);

    int getMinLeafRadius();

    int getMaxLeafRadius();

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

    EnumTrunkType getTrunkType();

    TreeHelper.TreeShapeEnum getCrownShape();

    TreeHelper.TreeShapeEnum getRootShape();

    default boolean generateTree(World worldIn, Random rand, BlockPos position) {
        AncientMagicksUtil.log(Level.WARN, String.format("Starting %s growth...", getTreeName()));

        int crownWidth = MathHelper.getInt(rand, getMinCrownRadius(), getMaxCrownRadius());
        int crownDepth = MathHelper.getInt(rand, getMinCrownRadius(), getMaxCrownRadius());
        int rootfieldWidth = MathHelper.getInt(rand, getMinRootfieldRadius(), getMaxRootfieldRadius());
        int rootfieldDepth = MathHelper.getInt(rand, getMinRootfieldRadius(), getMaxRootfieldRadius());
        int rootfieldHeight = MathHelper.getInt(rand,  getMinRootDepth(position), getMaxRootDepth(position));
        int trunkWidth = MathHelper.getInt(rand, getMinTrunkWidth(), getMaxTrunkWidth());
        int trunkHeight = MathHelper.getInt(rand, getMinTrunkHeight(), getMaxTrunkHeight());
        int rootRadius = MathHelper.getInt(rand, getMinRootRadius(), getMaxRootRadius());
        int crownHeight = MathHelper.getInt(rand,
                getMinCrownHeight(trunkHeight, position.getY() + trunkHeight),
                getMaxCrownHeight(trunkHeight, position.getY() + trunkHeight)
        );
        int subTrunkWidth = MathHelper.ceil(trunkWidth / 6.5);
        int innerTrunkWidth = MathHelper.ceil(trunkWidth / 4.5f);
        int indent = (int) (trunkWidth / 2.5f);
        int innerTrunkIndent = (int) (subTrunkWidth / 1.5f);

        System.out.printf("Rootfield Height: %d\n", rootfieldHeight);

        switch (getTrunkType()) {
            case MULTITRUNK:
                this.generateMultiTrunk(
                        worldIn,
                        position,
                        trunkHeight,
                        trunkWidth,
                        subTrunkWidth,
                        innerTrunkWidth,
                        indent,
                        innerTrunkIndent,
                        1.5f,
                        2.0f
                );
                break;
            case HELIXIAL:
                this.generateHelixialTrunk(
                        worldIn,
                        position,
                        trunkHeight,
                        trunkWidth,
                        subTrunkWidth
                );
                break;
        }

        switch (getCrownShape()) {
            case SPHERICAL:

                break;
        }

        // Generate the crown of the tree.
        this.spacialColonizationGenerationWithLeaves(
                worldIn,
                position,
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
                getMinLeafRadius(),
                getMaxLeafRadius(),
                rand
        );

        // Generate the roots of the tree.
        this.spacialColonizationGenerationWithoutLeaves(
                worldIn,
                position,
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
                rootRadius,
                getRootSizeDecrement(),
                rand
        );

        return true;
    }

    default void generateHelixialTrunk(World worldIn, BlockPos pos, int trunkHeight, int trunkWidth, int subTrunkWidth) {

    }

    default void generateMultiTrunk(
            World worldIn,
            BlockPos pos,
            int trunkHeight,
            int trunkWidth,
            int subTrunkWidth,
            int innerTrunkWidth,
            float indent,
            float innerTrunkIndent,
            float edgeSpreadMod,
            float cornerSpreadMod
    ) {
        float interval = (float) (Math.PI / 1.3f) / trunkHeight;
        float innerTrunkInterval = (float) (Math.PI) / trunkHeight;

        for (int h = 0; h <= trunkHeight; h++) {

            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            (trunkWidth / edgeSpreadMod) - (int)(indent * MathHelper.sin(interval * h)),
                            h,
                            0
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            -((trunkWidth / edgeSpreadMod) - (int)(indent * MathHelper.sin(interval * h))),
                            h,
                            0
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            0,
                            h,
                            (trunkWidth / edgeSpreadMod) - (int)(indent * MathHelper.sin(interval * h))
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            0,
                            h,
                            -((trunkWidth / edgeSpreadMod) - (int)(indent * MathHelper.sin(interval * h)))
                    ),
                    subTrunkWidth
            );

            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            (trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h)),
                            h,
                            (trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h))
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            -((trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h))),
                            h,
                            (trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h))
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            (trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h)),
                            h,
                            -((trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h)))
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateEllipsoid(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(
                            -((trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h))),
                            h,
                            -((trunkWidth / cornerSpreadMod) - (int)(indent * MathHelper.sin(interval * h)))
                    ),
                    subTrunkWidth
            );
            TreeHelper.generateCircle(
                    worldIn,
                    this,
                    EnumGenerationType.WOOD,
                    pos.add(0, h, 0),
                    (innerTrunkWidth) - (int)(innerTrunkIndent * MathHelper.sin(innerTrunkInterval * h)),
                    EnumFacing.Axis.Y
            );
        }
    }

    default void spacialColonizationGenerationWithoutLeaves(
            World worldIn, // The world.
            BlockPos position, // Position of the sapling spawning the tree.
            TreeHelper.TreeShapeEnum shape, // Shape of tree to generate
            int width, int height, int depth, // Dimensions of the tree to generate.
            int nodefieldOffsetX, int nodefieldOffsetY, int nodefieldOffsetZ, // Offset applied to the nodefield; the space the tree will try to fill.
            int skeletonOffsetX, int skeletonOffsetY, int skeletonOffsetZ, // Offset applied to the skeleton; where the tree will start generating from.
            int nodes, int attractionRadius, int removeRadius, int branchLength, // Space Colonization Algorithm tuning variables.
            int branchRadius, float sizeDecrement, // Initial radius of the branches, followed by the size decrement based on how far the branch is from the hub.
            Random rand
    ) {
        List<TreeHelper.Node> nodefield;
        List<TreeHelper.Segment> skeleton;

        AncientMagicksUtil.log(Level.INFO, "Beginning spacial colonization...");

        nodefield = TreeHelper.generateNodefield(
                nodes,
                position.add(nodefieldOffsetX, nodefieldOffsetY, nodefieldOffsetZ),
                shape,
                width,
                height,
                depth,
                rand
        );

        skeleton = TreeHelper.generateSkeleton(
                nodefield,
                position.add(skeletonOffsetX, skeletonOffsetY, skeletonOffsetZ),
                removeRadius,
                attractionRadius,
                branchLength,
                sizeDecrement
        );

        AncientMagicksUtil.log(Level.INFO, "Finished, Beginning generation...");

        BlockPos.MutableBlockPos parentPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos segPos = new BlockPos.MutableBlockPos();

        float crownPercentIncrement = 100.0f / skeleton.size();
        float crownPercentCurrent = 0.0f;
        int last = 0;

        for (TreeHelper.Segment segment : skeleton) {
            segPos.setPos(
                    segment.getPosition().xCoord,
                    segment.getPosition().yCoord,
                    segment.getPosition().zCoord
            );
            if (segment.getParent() != null) {
                parentPos.setPos(
                        segment.getParent().getPosition().xCoord,
                        segment.getParent().getPosition().yCoord,
                        segment.getParent().getPosition().zCoord
                );
                TreeHelper.generateLine(worldIn, this, EnumGenerationType.WOOD, parentPos, segPos, Math.round(branchRadius * segment.getSizeModifier()));
            }

            crownPercentCurrent += crownPercentIncrement;
            if (MathHelper.ceil(crownPercentCurrent) != last) {
                AncientMagicksUtil.logf(Level.INFO, "%d%s", MathHelper.ceil(crownPercentCurrent), "%");
            }

            last = MathHelper.ceil(crownPercentCurrent);
        }
        AncientMagicksUtil.log(Level.INFO, "Done with generation, expect some lag!");
    }

    default void spacialColonizationGenerationWithLeaves(
            World worldIn, // The world.
            BlockPos position, // Position of the sapling spawning the tree.
            TreeHelper.TreeShapeEnum shape, // Shape of tree to generate.
            int width, int height, int depth, // Dimensions of the tree to generate
            int nodefieldOffsetX, int nodefieldOffsetY, int nodefieldOffsetZ, // Offset applied to the nodefield; the space the tree will try to fill.
            int skeletonOffsetX, int skeletonOffsetY, int skeletonOffsetZ, // Offset applied to the skeleton; where the tree will start generating from.
            int nodes, int attractionRadius, int removeRadius, int branchLength, // Space Colonization Algorithm tuning variables.
            int branchRadius, float sizeDecrement, // Initial radius of the branches, followed by the size decrement based on how far the branch is from the hub.
            float leafRoughness, // How rough the leaves will generate.
            int minLeafRadius, // Minimum size of a leaf node.
            int maxLeafRadius, // Maximum size of a leaf node.
            Random rand
    ) {
        List<TreeHelper.Node> nodefield;
        List<TreeHelper.Segment> skeleton;

        AncientMagicksUtil.log(Level.INFO, "Beginning spacial colonization...");

        nodefield = TreeHelper.generateNodefield(
                nodes,
                position.add(nodefieldOffsetX, nodefieldOffsetY, nodefieldOffsetZ),
                shape,
                width,
                height,
                depth,
                rand
        );

        skeleton = TreeHelper.generateSkeleton(
                nodefield,
                position.add(skeletonOffsetX, skeletonOffsetY, skeletonOffsetZ),
                removeRadius,
                attractionRadius,
                branchLength,
                sizeDecrement
        );

        AncientMagicksUtil.log(Level.INFO, "Finished, Beginning generation...");

        BlockPos.MutableBlockPos parentPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos segPos = new BlockPos.MutableBlockPos();

        float crownPercentIncrement = 100.0f / skeleton.size();
        float crownPercentCurrent = 0.0f;
        int last = 0;

        for (TreeHelper.Segment segment : skeleton) {
            segPos.setPos(
                    segment.getPosition().xCoord,
                    segment.getPosition().yCoord,
                    segment.getPosition().zCoord
            );
            if (segment.getParent() != null) {
                parentPos.setPos(
                        segment.getParent().getPosition().xCoord,
                        segment.getParent().getPosition().yCoord,
                        segment.getParent().getPosition().zCoord
                );
                int radius = Math.round(branchRadius * segment.getSizeModifier());
                TreeHelper.generateLine(worldIn, this, EnumGenerationType.WOOD, parentPos, segPos, radius);
            }

            if (segment.getChildren().size() < 1) {
                TreeHelper.generateRoughEllipsoid(
                        worldIn,
                        this,
                        EnumGenerationType.LEAVES,
                        segPos,
                        (int) ((1.0f - segment.getSizeModifier()) * MathHelper.getInt(rand, minLeafRadius, maxLeafRadius)),
                        leafRoughness
                );
            }

            crownPercentCurrent += crownPercentIncrement;
            if (MathHelper.ceil(crownPercentCurrent) != last) {
                AncientMagicksUtil.logf(Level.INFO, "%d%s", MathHelper.ceil(crownPercentCurrent), "%");
            }
            last = MathHelper.ceil(crownPercentCurrent);
        }

        AncientMagicksUtil.log(Level.INFO, "Done with generation, expect some lag!");
    }

}
