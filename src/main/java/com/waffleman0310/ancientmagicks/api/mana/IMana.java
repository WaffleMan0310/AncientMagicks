package com.waffleman0310.ancientmagicks.api.mana;

import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;

public interface IMana {
	enum EnumManaType {
		UNSTABLE, BLIGHTED, PURE, ENERGETIC, CORRUPT, NORMAL
	}

	float getManaPurity();

	float getPurityModifier();

	EnumManaType getManaType();

	default String getUnlocalizedName() {
		return AncientMagicksUtil.formatUnlocalizedName(AncientMagicksUtil.EnumResourcePrefix.RESOURCE, String.format("%s.%s", "mana", getManaType().name().toLowerCase()));
	}

	default String getPurityUnlocalizedName() {
		return AncientMagicksUtil.formatUnlocalizedName(AncientMagicksUtil.EnumResourcePrefix.RESOURCE, "mana.purity");
	}
}
