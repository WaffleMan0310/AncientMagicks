package com.waffleman0310.ancientmagicks.api.world.gen.feature.tree;

import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ITreeGenerator;
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

    int getMinRootRadius();

    int getMaxRootRadius();

    int getRootDepth(BlockPos pos);

    int getMinCrownRadius();

    int getMaxCrownRadius();

    int getMinRootfieldRadius();

    int getMaxRootfieldRadius();

    int getMinLeafRadius();

    int getMaxLeafRadius();

    int getTrunkCrownOverlap();

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
        int rootWidth = MathHelper.getInt(rand, getMinRootfieldRadius(), getMaxRootfieldRadius());
        int rootDepth = MathHelper.getInt(rand, getMinRootfieldRadius(), getMaxRootfieldRadius());
        int trunkWidth = MathHelper.getInt(rand, getMinTrunkWidth(), getMaxTrunkWidth());
        int trunkHeight = MathHelper.getInt(rand, getMinTrunkHeight(), getMaxTrunkHeight());
        int rootRadius = MathHelper.getInt(rand, getMinRootRadius(), getMaxRootRadius());
        int rootHeight = getRootDepth(position) + getTrunkRootOverlap();
        int crownHeight = (256 - (getMaxLeafRadius() * 2)) - trunkHeight;
        int subTrunkWidth = MathHelper.ceil(trunkWidth / 6.5);
        int innerTrunkWidth = MathHelper.ceil(trunkWidth / 4.5f);
        int indent = (int) (trunkWidth / 2.5f);
        int innerTrunkIndent = (int) (subTrunkWidth / 1.5f);
        int branchRadius = subTrunkWidth;

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

        this.generateCrown(
                worldIn,
                position,
                getCrownShape(),
                (trunkHeight - getTrunkCrownOverlap()) + (crownHeight / 2),
                trunkHeight - getTrunkCrownOverlap(),
                crownWidth,
                crownHeight,
                crownDepth,
                getCrownNodes(),
                getCrownAttractionRadius(),
                getCrownRemoveRadius(),
                getCrownBranchLength(),
                branchRadius,
                getCrownSizeDecrement(),
                getLeafRoughness(),
                getMinLeafRadius(),
                getMaxLeafRadius(),
                rand
        );

        this.generateRoots( // Generate Roots
                worldIn,
                position,
                getRootShape(),
                -getRootDepth(position),
                getTrunkRootOverlap(),
                rootWidth,
                rootHeight,
                rootDepth,
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
            System.out.printf("Trunk Height: %d, Trunk Width: %d, Sub Trunk Width: %d, Inner Trunk Width: %d\n", trunkHeight, trunkWidth, subTrunkWidth, innerTrunkWidth);

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

    default void generateCrown(
            World worldIn,
            BlockPos position,
            TreeHelper.TreeShapeEnum shape,
            int nodefieldHeight,
            int skeletonHeight,
            int width,
            int height,
            int depth,
            int nodes,
            int attractionRadius,
            int removeRadius,
            int branchLength,
            int branchRadius,
            float sizeDecrement,
            float leafRoughness,
            int minLeafRadius,
            int maxLeafRadius,
            Random rand
    ) {
        List<TreeHelper.Node> nodefield;
        List<TreeHelper.Segment> skeleton;

        AncientMagicksUtil.log(Level.INFO, "Generating crown skeleton...");

        nodefield = TreeHelper.generateNodefield(
                nodes,
                position.add(0, nodefieldHeight, 0),
                shape,
                width,
                height,
                depth,
                rand
        );

        skeleton = TreeHelper.generateSkeleton(
                nodefield,
                position.add(0, skeletonHeight, 0),
                removeRadius,
                attractionRadius,
                branchLength,
                sizeDecrement

        );

        AncientMagicksUtil.log(Level.INFO, "Done");

        BlockPos.MutableBlockPos parentPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos segPos = new BlockPos.MutableBlockPos();

        float crownPercentIncrement = 100.0f / skeleton.size();
        float crownPercentCurrent = 0.0f;
        int last = 0;

        AncientMagicksUtil.log(Level.INFO, "Generating crown...");

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

    default void generateRoots(
            World worldIn,
            BlockPos pos,
            TreeHelper.TreeShapeEnum shape,
            int nodefieldHeight,
            int skeletonHeight,
            int width,
            int height,
            int depth,
            int nodes,
            int attractionRadius,
            int removeRadius,
            int branchLength,
            int branchRadius,
            float sizeDecrement,
            Random rand
    ) {
        List<TreeHelper.Node> nodefield;
        List<TreeHelper.Segment> skeleton;

        AncientMagicksUtil.log(Level.INFO, "Generating root skeleton...");

        nodefield = TreeHelper.generateNodefield(
                nodes,
                pos.add(0, nodefieldHeight, 0),
                shape,
                width,
                height,
                depth,
                rand
        );

        skeleton = TreeHelper.generateSkeleton(
                nodefield,
                pos.add(0, skeletonHeight, 0),
                removeRadius,
                attractionRadius,
                branchLength,
                sizeDecrement
        );

        AncientMagicksUtil.log(Level.INFO, "Done!");

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

}
