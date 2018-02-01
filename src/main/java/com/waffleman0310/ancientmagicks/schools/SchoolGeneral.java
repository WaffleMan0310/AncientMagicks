package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public class SchoolGeneral implements ISchool {

	@Override
	public String getName() {
		return "general";
	}

	@Override
	public String getResourceName() {
		return "null";
	}

	@Override
	public ISchool[] getConnectedSchools() {
		return new ISchool[0];
	}
}
