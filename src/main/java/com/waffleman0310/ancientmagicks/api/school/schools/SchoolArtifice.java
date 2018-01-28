package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

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

    @Override
    public ResearchMap getResearchMap() {
        return null;
    }
}
