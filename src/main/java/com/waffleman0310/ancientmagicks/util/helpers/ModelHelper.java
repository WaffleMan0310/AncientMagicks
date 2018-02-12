package com.waffleman0310.ancientmagicks.util.helpers;

public class ModelHelper {

	public static class ScaleModifier {
		private float x;
		private float y;
		private float z;

		public ScaleModifier(float scale) {
			this(scale, scale, scale);
		}

		public ScaleModifier(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public ScaleModifier add(float x, float y, float z) {
			return new ScaleModifier(this.x + x, this.y + y, this.z + z);
		}

		public ScaleModifier setX(float x) {
			this.x = x;
			return this;
		}

		public ScaleModifier setY(float y) {
			this.y = y;
			return this;
		}

		public ScaleModifier setZ(float z) {
			this.z = z;
			return this;
		}

		public float getX() {
			return this.x;
		}

		public float getY() {
			return this.y;
		}

		public float getZ() {
			return this.z;
		}
	}

	public static class RotationModifier {

		private float axisX;
		private float axisY;
		private float axisZ;

		private float x;
		private float y;
		private float z;

		private float angle;

		public RotationModifier(float axisX, float axisY, float axisZ) {
			this(axisX, axisY, axisZ, 0.0f, 0.0f, 0.0f);
		}

		public RotationModifier(float axisX, float axisY, float axisZ, float x, float y, float z) {
			this.axisX = axisX;
			this.axisY = axisY;
			this.axisZ = axisZ;

			this.x = x;
			this.y = y;
			this.z = z;

			this.angle = 0.0f;
		}

		public RotationModifier incrementAngle(float angle) {
			float newAngle = this.angle + angle;
			if (newAngle >= 360.0f) {
				newAngle = 0.0f;
			}

			this.angle = newAngle;
			return this;
		}

		public RotationModifier add(float angle, float axisX, float axisY, float axisZ, float x, float y, float z) {
			return new RotationModifier(this.axisX + axisX, this.axisY + axisY, this.axisZ + axisZ, this.x + x, this.y + y, this.z + z).setAngle(this.angle + angle);
		}

		public RotationModifier setAngle(float angle) {
			this.angle = angle;
			return this;
		}

		public RotationModifier setAxisX(float axisX) {
			this.axisX = axisX;
			return this;
		}

		public RotationModifier setAxisY(float axisY) {
			this.axisY = axisY;
			return this;
		}

		public RotationModifier setAxisZ(float axisZ) {
			this.axisZ = axisZ;
			return this;
		}

		public RotationModifier setX(float x) {
			this.x = x;
			return this;
		}

		public RotationModifier setY(float y) {
			this.y = y;
			return this;
		}

		public RotationModifier setZ(float z) {
			this.z = z;
			return this;
		}

		public float getAxisX() {
			return axisX;
		}

		public float getAxisY() {
			return axisY;
		}

		public float getAxisZ() {
			return axisZ;
		}

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		}

		public float getZ() {
			return z;
		}

		public float getAngle() {
			return this.angle;
		}
	}

	public static class PositionModifier {
		private float x;
		private float y;
		private float z;

		public PositionModifier(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public PositionModifier add(float x, float y, float z) {
			return new PositionModifier(this.x + x, this.y + y, this.z + z);
		}

		public PositionModifier setX(float x) {
			this.x = x;
			return this;
		}

		public PositionModifier setY(float y) {
			this.y = y;
			return this;
		}

		public PositionModifier setZ(float z) {
			this.z = z;
			return this;
		}

		public float getX() {
			return this.x;
		}

		public float getY() {
			return this.y;
		}

		public float getZ() {
			return this.z;
		}
	}
}
