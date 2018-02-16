package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolAstromancy extends School {

	public SchoolAstromancy(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "astromancy";
	}

	@Override
	public String getResourceName() {
		return "stardust";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}
