package com.waffleman0310.ancientmagicks.api.research;

public class ResearchableMetaFactory {

	public static ResearchableMeta create(IResearchable researchableItem, int metadata) {
		return new ResearchableMeta(researchableItem, metadata);
	}

}
