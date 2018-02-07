package com.waffleman0310.ancientmagicks.util.helpers;

import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ITreeGenerator;
import com.waffleman0310.ancientmagicks.api.world.gen.feature.tree.ITreeGenerator.EnumGenerationType;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeHelper {

	/*---------------------------------------- Generation Helper Methods ----------------------------------------*/

	public enum EnumTrunkType {
		NORMAL, TWISTED
	}

	public static void spacialColonizationGenerationWithLeaves(
			World worldIn, // The world.
			BlockPos position, // Position of the sapling spawning the tree.
			ITreeGenerator tree, // Object implementing the tree generator.
			EnumTreeShape shape, // Shape of tree to generate.
			int width, int height, int depth, // Dimensions of the tree to generate
			int nodefieldOffsetX, int nodefieldOffsetY, int nodefieldOffsetZ, // Offset applied to the nodefield; the space the tree will try to fill.
			int skeletonOffsetX, int skeletonOffsetY, int skeletonOffsetZ, // Offset applied to the skeleton; where the tree will start generating from.
			int nodes, int attractionRadius, int removeRadius, int branchLength, // Space Colonization Algorithm tuning variables.
			int branchRadius, float sizeDecrement, // Initial radius of the branches, followed by the size decrement based on how far the branch is from the hub.
			float leafRoughness, // How rough the leaves will generate.
			int minLeafRadius, // Minimum size of a leaf node.
			int maxLeafRadius, // Maximum size of a leaf node.
			Random rand // RNGesus
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
				TreeHelper.generateLine(worldIn, tree, EnumGenerationType.WOOD, parentPos, segPos, radius);
			}

			if (segment.getChildren().size() <= 1) {
				TreeHelper.generateRoughEllipsoid(
						worldIn,
						tree,
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

	public static void spacialColonizationGenerationWithoutLeaves(
			World worldIn, // The world.
			BlockPos position, // Position of the sapling spawning the tree.
			ITreeGenerator tree, // Object implementing the tree generator
			EnumTreeShape shape, // Shape of tree to generate
			int width, int height, int depth, // Dimensions of the tree to generate.
			int nodefieldOffsetX, int nodefieldOffsetY, int nodefieldOffsetZ, // Offset applied to the nodefield; the space the tree will try to fill.
			int skeletonOffsetX, int skeletonOffsetY, int skeletonOffsetZ, // Offset applied to the skeleton; where the tree will start generating from.
			int nodes, int attractionRadius, int removeRadius, int branchLength, // Space Colonization Algorithm tuning variables.
			int branchRadius, float sizeDecrement, // Initial radius of the branches, followed by the size decrement based on how far the branch is from the hub.
			Random rand // RNGesus
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
				TreeHelper.generateLine(worldIn, tree, EnumGenerationType.WOOD, parentPos, segPos, Math.round(branchRadius * segment.getSizeModifier()));
			}

			crownPercentCurrent += crownPercentIncrement;
			if (MathHelper.ceil(crownPercentCurrent) != last) {
				AncientMagicksUtil.logf(Level.INFO, "%d%s", MathHelper.ceil(crownPercentCurrent), "%");
			}

			last = MathHelper.ceil(crownPercentCurrent);
		}
		AncientMagicksUtil.log(Level.INFO, "Done with generation, expect some lag!");
	}

	public static void generateNormalTrunk(
			World worldIn, // The world.
			BlockPos pos, // Position of the sapling spawning the tree.
			ITreeGenerator tree, // Object implementing the tree generator.
			int trunkHeight, int trunkWidth, // Dimensions of the trunk.
			float subTrunkIndent, float innerTrunkIndent, // How indented the sub and inner trunks will be.
			float edgeSpreadMod, // Phase adjustment for the 4 trunks positioned on the x, -x, y, -y.
			float cornerSpreadMod // Phase adjustment for the 4 trunks positioned on on the corners, -x+y, -x-y, +x+y, +x-y.
	) {
		float interval = (float) (Math.PI / 1.3f) / trunkHeight;
		float innerTrunkInterval = (float) (Math.PI) / trunkHeight;

		for (int h = 0; h <= trunkHeight; h++) {
			TreeHelper.generateOctaTrunkLayer(
					worldIn,
					pos.add(0, h, 0),
					tree,
					trunkWidth,
					subTrunkIndent,
					innerTrunkIndent,
					interval,
					innerTrunkInterval,
					h,
					edgeSpreadMod,
					cornerSpreadMod
			);
		}
	}


	// Randomize the curvature of each of the curves in the trunk? and direction?
	public static void generateTwistedTrunk(
			World worldIn, // The world
			BlockPos pos, // Position of the sapling spawning the tree.
			ITreeGenerator tree, // Object implementing the tree generator.
			int trunkHeight, int trunkWidth, // Dimensions of the trunk.
			int numOfCurves, // Amount of different curves will be in the trunk.
			int curveAmplitude, // How intense the curve will be in blocks.
			float subTrunkIndent, float innerTrunkIndent, // How indented the sub and inner trunks will be.
			float edgeSpreadMod, // Phase adjustment for the 4 trunks positioned on the x, -x, y, -y.
			float cornerSpreadMod, // Phase adjustment for the 4 trunks positioned on on the corners, -x+y, -x-y, +x+y, +x-y.
			Random rand // RNGesus
	) {
		/*
		EnumDirection value representation:
		0 : +x
		1 : +y
		2 : -x
		3 : -y
		4 : +xy
		5 : -xy
		6 : -x +y
		7 : +x -y
		*/
		int direction = MathHelper.getInt(rand, 0,7);
		//int maxCurveY = MathHelper.getInt(rand, -(trunkHeight / 2), (trunkHeight / 2));
		int maxCurveY = 0;

		float curveInterval = (float) (numOfCurves * Math.PI) / trunkHeight;
		float subTrunkInterval = (float) (Math.PI / 1.3f) / trunkHeight;
		float innerTrunkInterval = (float) (Math.PI) / trunkHeight;

		for (int i = 0; i < trunkHeight; i++) {
			double xOffset = 0;
			double zOffset = 0;

			if (direction == 0 || direction == 4 || direction == 7) {
				xOffset = (curveAmplitude * MathHelper.sin(curveInterval * i) + maxCurveY);
			}

			if (direction == 2 || direction == 5 || direction == 6) {
				xOffset = -(curveAmplitude * MathHelper.sin(curveInterval * i) + maxCurveY);
			}

			if (direction == 1 || direction == 4 || direction == 6) {
				zOffset = (curveAmplitude * MathHelper.sin(curveInterval * i) + maxCurveY);
			}

			if (direction == 3 || direction == 5 || direction == 7) {
				zOffset = -(curveAmplitude * MathHelper.sin(curveInterval * i) + maxCurveY);
			}

			TreeHelper.generateOctaTrunkLayer(
					worldIn,
					pos.add(xOffset, i, zOffset),
					tree,
					trunkWidth,
					subTrunkIndent,
					innerTrunkIndent,
					subTrunkInterval,
					innerTrunkInterval,
					i,
					edgeSpreadMod,
					cornerSpreadMod
			);
		}
	}

	public static void generateOctaTrunkLayer(
			World worldIn, // The world.
			BlockPos pos,  // Position to generate.
			ITreeGenerator tree, // Object implementing the tree interface.
			int trunkWidth, // Trunk width.
			float sinAmplitude, // Amplitude of sin function, or how much the trunks curve in towards the middle.
			float innerTrunkSinAmplitude, // Amplitude of sin function, or how much the inner trunk indents.
			float sinInterval, // The section of the sine wave to generate, for ex: (Math.PI / 2) / trunkHeight will complete 1 peak in the trunk generation.
			float innerTrunkSinInterval, // Same as sin interval but applied to the inner trunk.
			float currSinInterval, // The multiplier on the above intervals, in most cases the counting variable of the for loop building the trunk.
			float edgePhase, // Phase adjustment for the 4 trunks positioned on the x, -x, y, -y.
			float cornerPhase // Phase adjustment for the 4 trunks positioned on on the corners, -x+y, -x-y, +x+y, +x-y.
	) {

		int subTrunkWidth = MathHelper.ceil(trunkWidth / 6.5);
		int innerTrunkWidth = MathHelper.ceil(trunkWidth / 4.5f);

		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						(trunkWidth / edgePhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval)),
						0,
						0
				),
				subTrunkWidth
		);
		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						-((trunkWidth / edgePhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval))),
						0,
						0
				),
				subTrunkWidth
		);
		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						0,
						0,
						(trunkWidth / edgePhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval))
				),
				subTrunkWidth
		);
		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						0,
						0,
						-((trunkWidth / edgePhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval)))
				),
				subTrunkWidth
		);

		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						(trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval)),
						0,
						(trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval))
				),
				subTrunkWidth
		);
		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						-((trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval))),
						0,
						(trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval))
				),
				subTrunkWidth
		);
		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						(trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval)),
						0,
						-((trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval)))
				),
				subTrunkWidth
		);
		TreeHelper.generateEllipsoid(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos.add(
						-((trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval))),
						0,
						-((trunkWidth / cornerPhase) - (int) (sinAmplitude * MathHelper.sin(sinInterval * currSinInterval)))
				),
				subTrunkWidth
		);
		TreeHelper.generateCircle(
				worldIn,
				tree,
				EnumGenerationType.WOOD,
				pos,
				(innerTrunkWidth) - (int) (innerTrunkSinAmplitude * MathHelper.sin(innerTrunkSinInterval * currSinInterval)),
				EnumFacing.Axis.Y
		);
	}

	/*---------------------------------------- Shape Generation Helper Methods ----------------------------------------*/

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

	public enum EnumTreeShape {
		SPHERICAL, CUBICAL, TRIANGULAR, CONICAL, SEMISPHERE
	}

	// Generates a list of random points in a specified shape, in a specified area
	public static List<Node> generateNodefield(int nodes, BlockPos pos, EnumTreeShape shape, int width, int height, int depth, Random random) {
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
				System.out.println(semisphere.size());

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