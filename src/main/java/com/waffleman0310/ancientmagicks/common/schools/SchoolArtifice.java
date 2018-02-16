package com.waffleman0310.ancientmagicks.common.schools;

import com.waffleman0310.ancientmagicks.api.school.School;

public class SchoolArtifice extends School {

	public SchoolArtifice(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "artifice";
	}

	@Override
	public String getResourceName() {
		return "essentia";
	}

	@Override
	public School[] getConnectedSchools() {
		return new School[0];
	}
}
