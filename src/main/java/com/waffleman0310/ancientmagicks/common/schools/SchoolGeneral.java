package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolGeneral extends School {

	public SchoolGeneral(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "general";
	}

	@Override
	public String getResourceName() {
		return "null";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}
