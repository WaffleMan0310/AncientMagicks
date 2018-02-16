package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolWitchcraft extends School {

	public SchoolWitchcraft(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "witchcraft";
	}

	@Override
	public String getResourceName() {
		return "brewing_base";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}
