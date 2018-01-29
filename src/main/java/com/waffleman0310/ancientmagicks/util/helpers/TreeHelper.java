package com.waffleman0310.ancientmagicks.util.helpers;

import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ITreeGenerator;
import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ITreeGenerator.EnumGenerationType;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class TreeHelper {

    /*---------------------------------------- Generation Helper Methods ----------------------------------------*/

    public static void generateLine(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos from, BlockPos to, int thickness) {
        MutableBlockPos mutablePos = new MutableBlockPos();
        for (Vec3d point : ShapeHelper.generateLine(from, to)) {
            mutablePos.setPos(
                    point.xCoord,
                    point.yCoord,
                    point.zCoord
            );
            generateEllipsoid(worldIn, tree, type, mutablePos, thickness);
        }
    }

    public static void generateCircle(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos pos, int widthRadius, int heightRadius, EnumFacing.Axis axis) {
        MutableBlockPos mutablePos = new MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
        for (Vec3d point : ShapeHelper.generateCircle(pos, widthRadius, heightRadius, axis)) {
            mutablePos.setPos(
                    point.xCoord,
                    point.yCoord,
                    point.zCoord
            );
            placeTypeAt(worldIn, tree, type, mutablePos);
        }
    }

    public static void generateCircle(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos pos, int radius, EnumFacing.Axis axis) {
        generateCircle(worldIn, tree, type, pos, radius, radius, axis);
    }

    public static void generateEllipsoid(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos pos, int widthRadius, int heightRadius, int depthRadius) {
        MutableBlockPos mutablePos = new MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
        for (Vec3d point : ShapeHelper.generateEllipsoid(pos, widthRadius, heightRadius, depthRadius)) {
            mutablePos.setPos(
                    point.xCoord,
                    point.yCoord,
                    point.zCoord
            );
            placeTypeAt(worldIn, tree, type, mutablePos);
        }
    }

    public static void generateRoughEllipsoid(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos pos, int radius, float roughness) {
        Random random = worldIn.rand != null ? worldIn.rand : new Random();
        MutableBlockPos mutablePos = new MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
        Vec3d posVector = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        for (Vec3d point : ShapeHelper.generateEllipsoid(pos, radius, radius, radius)) {
            if (point.distanceTo(posVector) > (radius - 2)) {
                if (random.nextFloat() < 0.6) {
                    mutablePos.setPos(
                        point.xCoord,
                        point.yCoord,
                        point.zCoord
                    );
                    placeTypeAt(worldIn, tree, type, mutablePos);
                }
            } else {
                mutablePos.setPos(
                        point.xCoord,
                        point.yCoord,
                        point.zCoord
                );
                placeTypeAt(worldIn, tree, type, mutablePos);
            }
        }
    }

    public static void generateEllipsoid(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos pos, int radius) {
        generateEllipsoid(worldIn, tree, type, pos, radius, radius, radius);
    }

    private static void placeTypeAt(World worldIn, ITreeGenerator tree, EnumGenerationType type, BlockPos pos) {
        switch (type) {
            case WOOD:
                tree.placeWoodAt(worldIn, pos);
                break;
            case LEAVES:
                tree.placeLeavesAt(worldIn, pos);
                break;
            case ROOTS:
                tree.placeRootsAt(worldIn, pos);
                break;
        }
    }

    /*---------------------------------------- Math Helper Methods ----------------------------------------*/

    public enum TreeShapeEnum {
        SPHERICAL, CUBICAL, TRIANGULAR, CONICAL, SEMISPHERE
    }

    // Generates a list of random points in a specified shape, in a specified area
    public static List<Node> generateNodefield(int nodes, BlockPos pos, TreeShapeEnum shape, int width, int height, int depth, Random random) {
        List<Node> nodefield = new ArrayList<>(nodes);
        switch (shape) {
            case SPHERICAL:
                int widthRadius = width / 2, heightRadius = height / 2, depthRadius = depth / 2;

                List<Vec3d> sphere = ShapeHelper.generateEllipsoid(pos.add(0, heightRadius, 0), widthRadius, heightRadius, depthRadius);

                for (int n = 0; n < nodes; n++) {
                    int idx = MathHelper.getInt(random, 0, sphere.size() - 1);
                    nodefield.add(new Node(sphere.get(idx)));
                }
                break;
            case CUBICAL:
                for (int n = 0; n < nodes; n++) {
                    nodefield.add(n, new Node(new Vec3d(
                            pos.getX() + MathHelper.getInt(random, -(width / 2), width / 2),
                            pos.getY() + MathHelper.getInt(random, 0, height),
                            pos.getZ() + MathHelper.getInt(random, -(depth / 2), depth / 2)
                    )));
                }
                break;
            case TRIANGULAR:
                // Implement
                break;
            case CONICAL:
                // Implement
                break;
            case SEMISPHERE:
                int widthRad = width / 2, depthRad = depth / 2;

                List<Vec3d> semisphere = ShapeHelper.generateSemisphere(pos, widthRad, height, depthRad);

                for (int n = 0; n < nodes; n++) {
                    int idx = MathHelper.getInt(random, 0, semisphere.size() - 1);
                    nodefield.add(new Node(semisphere.get(idx)));
                }
                break;
        }
        return nodefield;
    }

    // Space Colonization Algortihm
    public static List<Segment> generateSkeleton(List<Node> nodefield, BlockPos pos, int removeRadius, int attractionRadius, int branchLength, float sizeDecrement) {
        List<Segment> skeleton = new ArrayList<>();

        Segment root = new Segment(null, new Vec3d(pos.getX(), pos.getY(), pos.getZ()));
        skeleton.add(root);

        boolean isGrowing = true;
        while (isGrowing) {

            nodefield.removeIf(node -> skeleton.stream()
                    .anyMatch(segment -> node.getPosition().distanceTo(segment.getPosition()) <= removeRadius));

            nodefield.forEach(node -> {
                node.setClosestSegment(null);
                skeleton.stream()
                        .filter(segment -> node.getPosition().distanceTo(segment.getPosition()) <= attractionRadius)
                        .filter(segment -> node.getClosestSegment() == null ||
                                node.getPosition().distanceTo(node.getClosestSegment().getPosition()) > node.getPosition().distanceTo(segment.getPosition()))
                        .forEach(node::setClosestSegment);
                if (node.getClosestSegment() != null) {
                    node.getClosestSegment().addNode(node);
                }
            });

            List<Segment> newSegments = new ArrayList<>();
            skeleton.forEach(segment -> {
                if (segment.getNearbyNodes().size() > 0) {
                    Vec3d averageDirection = segment.averagePosition().normalize();
                    Segment newSegment = new Segment(segment, averageDirection.scale(branchLength).add(segment.getPosition()));
                    if (!skeleton.contains(newSegment)) {
                        float newModifier = segment.getSizeModifier() - sizeDecrement;
                        //segment.setSizeModifier(newModifier);
                        newSegment.setSizeModifier(newModifier);

                        if (newModifier > 0.0f) {
                            newSegments.add(newSegment);
                            segment.addChild(newSegment);
                            segment.reset();
                        }
                    }
                }
            });

            //newSegments.forEach(skeleton::add);
            skeleton.addAll(newSegments);

            //System.out.printf("Skeleton Size: %d - New Segments: %d - Nodefield Size: %d\n", skeleton.size(), newSegments.size(), nodefield.size());

            if (newSegments.size() <= 0 || nodefield.size() <= 0) {
                isGrowing = false;
            }
        }

        return skeleton;
    }

    public static class Node {

        private Vec3d position;
        private Segment closestSegment;

        public Node(Vec3d position) {
            this.position = position;
        }

        public Vec3d getPosition() {
            return this.position;
        }

        public Segment getClosestSegment() {
            return closestSegment;
        }

        public void setClosestSegment(Segment segment) {
            this.closestSegment = segment;

        }
    }

    public static class Segment {

        private Segment parent;
        private List<Segment> children = new ArrayList<>();
        private Vec3d position;
        private List<Node> nearbyNodes = new ArrayList<>();
        private float sizeModifier = 1.0f;

        public Segment(@Nullable Segment parent, Vec3d position) {
            this.parent = parent;
            this.position = position;
        }

        public void setSizeModifier(float sizeModifier) {
            this.sizeModifier = sizeModifier;
        }

        public float getSizeModifier() {
            return sizeModifier;
        }

        public void addNode(Node node) {
            nearbyNodes.add(node);
        }

        public void addChild(Segment segment) {
            children.add(segment);
        }

        public void reset() {
            nearbyNodes = new ArrayList<>();
        }

        public List<Node> getNearbyNodes() {
            return nearbyNodes;
        }

        public Vec3d averagePosition() {
            Vec3d sum = Vec3d.ZERO;
            int numOfNodes = getNearbyNodes().size();

            for (Node n : getNearbyNodes()) {
                sum = sum.add(n.getPosition().subtract(n.getClosestSegment().getPosition()).normalize());
            }

            return new Vec3d(sum.xCoord / numOfNodes, sum.yCoord / numOfNodes, sum.zCoord / numOfNodes);
        }

        public Vec3d getPosition() {
            return position;
        }

        public Segment getParent() {
            return parent;
        }

        public List<Segment> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Segment) {
                if (((Segment) o).getPosition().equals(this.getPosition())) {
                    return true;
                }
            }
            return false;
        }
    }
}