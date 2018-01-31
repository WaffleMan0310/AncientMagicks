package com.waffleman0310.ancientmagicks.research;

import com.waffleman0310.ancientmagicks.api.research.IResearchable;
import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.api.research.ResearchableMetaFactory;
import com.waffleman0310.ancientmagicks.init.Blocks;
import com.waffleman0310.ancientmagicks.init.Items;
import com.waffleman0310.ancientmagicks.variant.EnumReagentType;
import net.minecraft.item.ItemStack;

public enum EnumResearch {

	//TREES(Blocks.LOG, null),
	ARCANISTS_SMELTERY(
			Blocks.ARCANIST_SMELTERY, // Item / Block
			ResearchableMetaFactory.create(Items.REAGENTS, EnumReagentType.ALCHEMICAL_SALT.getMetadata())); // Prerequisites

	private ResearchableMeta researchable;
	private ResearchableMeta[] prerequisites;

	EnumResearch(IResearchable researchable, IResearchable... prerequisites) {
		this.researchable = ResearchableMetaFactory.create(researchable, 0);
		this.prerequisites = new ResearchableMeta[prerequisites.length];
		for (int i = 0; i < prerequisites.length; i++) {
			this.prerequisites[i] = ResearchableMetaFactory.create(prerequisites[i], 0);
		}
	}

	EnumResearch(IResearchable researchable, ResearchableMeta... prerequisites) {
		this(
				ResearchableMetaFactory.create(researchable, 0),
				prerequisites
		);
	}

	EnumResearch(ResearchableMeta researchable, ResearchableMeta... prerequisites) {
		this.researchable = researchable;
		this.prerequisites = prerequisites;
	}

	public ResearchableMeta getResearchable() {
		return this.researchable;
	}

	public ResearchableMeta[] getPrerequisites() {
		return this.prerequisites;
	}
}
