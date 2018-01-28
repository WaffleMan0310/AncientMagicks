package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolAstromancy implements ISchool {

    public static final SchoolAstromancy INSTANCE = new SchoolAstromancy();

    @Override
    public String getName() {
        return "astromancy";
    }

    @Override
    public String getResourceName() {
        return "stardust";
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
