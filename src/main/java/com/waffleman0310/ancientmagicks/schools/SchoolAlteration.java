package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.research.ResearchMap;

public class SchoolAlteration implements ISchool {

	@Override
	public String getName() {
		return "alteration";
	}

	@Override
	public String getResourceName() {
		return "glyphs";
	}

	@Override
	public ISchool[] getConnectedSchools() {
		return new ISchool[0];
	}

	@Override
	public ResearchMap getResearchMap() {
		return null;
	}
}
