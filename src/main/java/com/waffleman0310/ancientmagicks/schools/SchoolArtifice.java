package com.waffleman0310.ancientmagicks.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;

public class SchoolArtifice implements ISchool {

	public static final SchoolArtifice INSTANCE = new SchoolArtifice();

	@Override
	public String getName() {
		return "artifice";
	}

	@Override
	public String getResourceName() {
		return "essentia";
	}

	@Override
	public ISchool[] getConnectedSchools() {
		return new ISchool[0];
	}
}