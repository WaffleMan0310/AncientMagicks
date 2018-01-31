package com.waffleman0310.ancientmagicks.api.school;

import com.waffleman0310.ancientmagicks.research.ResearchMap;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;

public interface ISchool {

    String getName();

    String getResourceName();

    ISchool[] getConnectedSchools();

    ResearchMap getResearchMap();

    default String getUnlocalizedResourceName() {
        return AncientMagicksUtil.formatUnlocalizedName(
                AncientMagicksUtil.EnumResourcePrefix.RESOURCE,
                getResourceName());
    }

    default String getUnlocalizedName() {
        return AncientMagicksUtil.formatUnlocalizedName(
                AncientMagicksUtil.EnumResourcePrefix.SCHOOL,
                getName());
    }

    default String getUnlocalizedDescription() {
        return AncientMagicksUtil.formatUnlocalizedName(
                AncientMagicksUtil.EnumResourcePrefix.SCHOOL,
                getName());
    }
}

