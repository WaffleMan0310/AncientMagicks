package com.waffleman0310.ancientmagicks.util.helpers;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class ShapeHelper {

    // Brensenham's Algorithm
    // This also seems to miss some spots along the line
    // Generates a list of vectors that represent a line between two block positions
    public static List<Vec3d> generateLine(BlockPos from, BlockPos to) {
        int deltaX = to.getX() - from.getX(), deltaY = to.getY() - from.getY(), deltaZ = to.getZ() - from.getZ();
        EnumFacing stepX = deltaX >= 0 ? EnumFacing.EAST : EnumFacing.WEST,
                stepY = deltaY >= 0 ? EnumFacing.UP : EnumFacing.DOWN,
                stepZ = deltaZ >= 0 ? EnumFacing.SOUTH : EnumFacing.NORTH;
        int errorX = 0, errorY = 0, errorZ = 0;

        deltaX = MathHelper.abs(deltaX);
        deltaY = MathHelper.abs(deltaY);
        deltaZ = MathHelper.abs(deltaZ);

        int n = deltaX > deltaY ? (deltaX > deltaZ ? deltaX : deltaZ) : deltaY;
        List<Vec3d> line = new ArrayList<>();
        Vec3d pos = new Vec3d(from.getX(), from.getY(), from.getZ());
        for (int i = 0; i < n; i++) {

            line.add(pos);

            errorX += deltaX;
            errorY += deltaY;
            errorZ += deltaZ;

            if (errorX >= 0) {
                pos = pos.addVector(stepX.getAxisDirection().getOffset(), 0, 0);
                errorX -= n;
            }
            if (errorY >= 0) {
                pos = pos.addVector(0, stepY.getAxisDirection().getOffset(), 0);
                errorY -= n;
            }
            if (errorZ >= 0) {
                pos = pos.addVector(0, 0, stepZ.getAxisDirection().getOffset());
                errorZ -= n;
            }
        }
        return line;
    }

    // Needs to be faster...
    // Generates a list of vectors that represent a circle a specified location and size, on a specified axis
    public static List<Vec3d> generateCircle(BlockPos pos, int widthRadius, int heightRadius, EnumFacing.Axis axis) {
        List<Vec3d> circle = new ArrayList<>();
        for (int x = -widthRadius; x <= widthRadius; x++) {
            float dX = (float) x / widthRadius;
            for (int y = -heightRadius; y <= heightRadius; y++) {
                float dY = (float) y / heightRadius;
                if (dX * dX + dY * dY <= 1) {
                    Vec3d point = Vec3d.ZERO;
                    switch (axis) {
                        case X:
                            point = new Vec3d(
                                    pos.getX() + x,
                                    pos.getY() + y,
                                    pos.getZ()
                            );
                            break;
                        case Y:
                            point = new Vec3d(
                                pos.getX() + x,
                                pos.getY(),
                                pos.getZ() + y
                            );
                            break;
                        case Z:
                            point = new Vec3d(
                                pos.getX(),
                                pos.getY() + x,
                                pos.getZ() + y
                            );
                            break;
                    }
                    circle.add(point);
                }
            }
        }
        return circle;
    }

    // Needs to be faster...
    public static List<Vec3d> generateEllipsoid(BlockPos pos, int widthRadius, int heightRadius, int depthRadius) {
        List<Vec3d> ellipsoid = new ArrayList<>();
        for (int y = -heightRadius; y <= heightRadius; y++) {
            float dY = (float) y / heightRadius;
            for (int x = -widthRadius; x <= widthRadius; x++) {
                float dX = (float) x / widthRadius;
                for (int z = -depthRadius; z <= depthRadius; z++) {
                    float dZ = (float) z / depthRadius;
                    if (dY * dY + dX * dX + dZ * dZ <= 1) {
                        ellipsoid.add(new Vec3d(
                                pos.getX() + x,
                                pos.getY() + y ,
                                pos.getZ() + z)
                        );
                    }
                }
            }
        }
        return ellipsoid;
    }

    public static List<Vec3d> generateSemisphere(BlockPos pos, int widthRadius, int height, int depthRadius) {
        List<Vec3d> cylinder = new ArrayList<>();
        for (int y = 0; y <= height; y++) {
            float dY = (float) y / height;
            for (int x = -widthRadius; x <= widthRadius; x++) {
                float dX = (float) x / widthRadius;
                for (int z = -depthRadius; z <= depthRadius; z++) {
                    float dZ = (float) z / depthRadius;
                    if (dY * dY + dX * dX + dZ * dZ <= 1) {
                        cylinder.add(new Vec3d(
                                pos.getX() + x,
                                pos.getY() + y,
                                pos.getZ() + z)

                        );
                    }
                }
            }
        }
        return cylinder;
    }
}
