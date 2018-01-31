package com.waffleman0310.ancientmagicks.common.blocks.base;

import com.waffleman0310.ancientmagicks.creativetabs.CreativeTab;
import com.waffleman0310.ancientmagicks.util.AncientMagicksUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public abstract class AncientMagicksBlock extends Block {

	private String name;

	public AncientMagicksBlock(String name, Material material) {
		super(material, material.getMaterialMapColor());
		this.name = name;
		setUnlocalizedName(AncientMagicksUtil.formatUnlocalizedName(AncientMagicksUtil.EnumResourcePrefix.BLOCK, getName()));
		setCreativeTab(CreativeTab.ancientMagicksTab);
	}

	public AncientMagicksBlock(Material material) {
		super(material);
		setCreativeTab(CreativeTab.ancientMagicksTab);
	}

	@Override
	public String getLocalizedName() {
		return AncientMagicksUtil.localize(AncientMagicksUtil.EnumResourceSuffix.NAME, getUnlocalizedName());
	}

	public AncientMagicksBlock setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}
}
