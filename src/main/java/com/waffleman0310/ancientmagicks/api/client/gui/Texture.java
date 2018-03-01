package com.waffleman0310.ancientmagicks.api.client.gui;

import net.minecraft.util.ResourceLocation;

public class Texture {

	private final ResourceLocation texture;
	private int u;
	private int v;
	private int width;
	private int height;

	public Texture(ResourceLocation texture, int u, int v, int width, int height) {
		this.texture = texture;

		setU(u);
		setV(v);
		setWidth(width);
		setHeight(height);
	}

	public void setU(int u) {
		if (this.width > 256) {
			this.u = (u / this.width) * 256;
		} else {
			this.u = u;
		}
	}

	public void setV(int v) {
		if (this.height > 256) {
			this.v = (v / this.height) * 256;
		} else {
			this.v = v;
		}
	}

	public void setWidth(int width) {
		this.width = width > 256 ? 256 : width;
	}

	public void setHeight(int height) {
		this.height = height > 256 ? 256 : height;
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	public int getU() {
		return this.u;
	}

	public int getV() {
		return this.v;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
