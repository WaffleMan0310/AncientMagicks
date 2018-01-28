package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolAlteration implements ISchool {

    public static final SchoolAlteration INSTANCE = new SchoolAlteration();

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
