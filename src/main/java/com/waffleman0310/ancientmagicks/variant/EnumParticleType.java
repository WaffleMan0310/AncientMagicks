package com.waffleman0310.ancientmagicks.variant;

import net.minecraft.client.particle.Particle;

public class EnumParticleType {

	private Particle particle;

	EnumParticleType(Particle particle) {
		this.particle = particle;
	}

	public Particle getParticle() {
		return particle;
	}
}
