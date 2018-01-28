package com.waffleman0310.ancientmagicks.api.school.schools;

import com.waffleman0310.ancientmagicks.api.school.ISchool;
import com.waffleman0310.ancientmagicks.api.school.research.ResearchMap;

public class SchoolMetamorphosis implements ISchool {

    public static final SchoolMetamorphosis INSTANCE = new SchoolMetamorphosis();

    @Override
    public String getName() {
        return "metamorphosis";
    }

    @Override
    public String getResourceName() {
        return "runic_power";
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
