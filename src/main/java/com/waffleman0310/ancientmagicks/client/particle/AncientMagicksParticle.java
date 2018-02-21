package com.waffleman0310.ancientmagicks.client.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class AncientMagicksParticle extends Particle {


	protected AncientMagicksParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
	}

	@Override
	public int getFXLayer() {
		return 3;
	}
}
