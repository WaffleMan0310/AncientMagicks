package com.waffleman0310.ancientmagicks.api.school;

import com.waffleman0310.ancientmagicks.api.research.ResearchableMeta;
import com.waffleman0310.ancientmagicks.research.ResearchMap;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;

import java.util.HashMap;
import java.util.List;

public interface ISchool {

	String getName();

	String getResourceName();

	ISchool[] getConnectedSchools();

	default HashMap<ResearchableMeta, List<ResearchableMeta>> getResearch() {
		return ResearchMap.getResearchMapForSchool(this);
	}

	default String getUnlocalizedResourceName() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.RESOURCE,
				getResourceName());
	}

	default String getUnlocalizedName() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.SCHOOL,
				getName());
	}

	default String getUnlocalizedDescription() {
		return AncientMagicksUtil.formatUnlocalizedName(
				AncientMagicksUtil.EnumResourcePrefix.SCHOOL,
				getName());
	}
}

