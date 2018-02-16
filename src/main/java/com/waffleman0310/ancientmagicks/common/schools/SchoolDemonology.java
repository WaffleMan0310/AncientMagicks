package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolDemonology extends School {

	public SchoolDemonology(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "demonology";
	}

	@Override
	public String getResourceName() {
		return "demonic_blood";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}
