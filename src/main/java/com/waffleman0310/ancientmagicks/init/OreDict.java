package com.waffleman0310.ancientmagicks.init;

import com.waffleman0310.ancientmagicks.common.items.ItemMetal;
import com.waffleman0310.ancientmagicks.common.items.ItemMetal.EnumMetalForm;
import com.waffleman0310.ancientmagicks.variant.EnumMetalType;
import com.waffleman0310.ancientmagicks.variant.EnumOreType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDict {

	public static void registerAllOreDictEntries() {
		registerOre("oreBauxite", new ItemStack(Blocks.ORE, 1, EnumOreType.BAUXITE.getMetadata()));
		registerOre("oreGalena", new ItemStack(Blocks.ORE, 1, EnumOreType.GALENA.getMetadata()));
		registerOre("oreChalocite", new ItemStack(Blocks.ORE, 1, EnumOreType.CHALOCITE.getMetadata()));
		registerOre("oreCinnabar", new ItemStack(Blocks.ORE, 1, EnumOreType.CINNABAR.getMetadata()));
		registerOre("oreSperrylite", new ItemStack(Blocks.ORE, 1, EnumOreType.SPERRYLITE.getMetadata()));
		registerOre("oreUranium", new ItemStack(Blocks.ORE, 1, EnumOreType.URANITE.getMetadata()));
		registerOre("oreWolframite", new ItemStack(Blocks.ORE, 1, EnumOreType.WOLFRAMITE.getMetadata()));

		registerOre("ingotLead", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.LEAD)));
		registerOre("ingotSilver", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.SILVER)));
		registerOre("ingotCopper", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.COPPER)));
		registerOre("ingotPlatinum", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.PLATINIUM)));
		registerOre("ingotTitanium", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.TITANIUM)));
		registerOre("ingotBronze", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.BRONZE)));
		registerOre("ingotBrass", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.BRASS)));
		registerOre("ingotUranium", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.URANIUM)));
		registerOre("ingotTungsten", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.TUNGSTEN)));
		registerOre("ingotArcanite", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.ARCANITE)));
		registerOre("ingotArcanium", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.ARCANIUM)));
		registerOre("ingotMageSteel", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.INGOT, EnumMetalType.MAGE_STEEL)));

		registerOre("nuggetLead", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.LEAD)));
		registerOre("nuggetSilver", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.SILVER)));
		registerOre("nuggetCopper", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.COPPER)));
		registerOre("nuggetPlatinum", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.PLATINIUM)));
		registerOre("nuggetTitanium", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.TITANIUM)));
		registerOre("nuggetBronze", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.BRONZE)));
		registerOre("nuggetBrass", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.BRASS)));
		registerOre("nuggetUranium", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.URANIUM)));
		registerOre("nuggetTungsten", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.TUNGSTEN)));
		registerOre("nuggetArcanite", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.ARCANITE)));
		registerOre("nuggetArcanium", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.ARCANIUM)));
		registerOre("nuggetMageSteel", new ItemStack(Items.METAL, 1, ItemMetal.formatMetadata(EnumMetalForm.NUGGET, EnumMetalType.MAGE_STEEL)));

		registerOre("blockLead", new ItemStack(Blocks.METAL, 1, EnumMetalType.LEAD.getMetadata()));
		registerOre("blockSilver", new ItemStack(Blocks.METAL, 1, EnumMetalType.SILVER.getMetadata()));
		registerOre("blockCopper", new ItemStack(Blocks.METAL, 1, EnumMetalType.COPPER.getMetadata()));
		registerOre("blockPlatinum", new ItemStack(Blocks.METAL, 1, EnumMetalType.PLATINIUM.getMetadata()));
		registerOre("blockTitanium", new ItemStack(Blocks.METAL, 1, EnumMetalType.TITANIUM.getMetadata()));
		registerOre("blockBronze", new ItemStack(Blocks.METAL, 1, EnumMetalType.BRONZE.getMetadata()));
		registerOre("blockBrass", new ItemStack(Blocks.METAL, 1, EnumMetalType.BRASS.getMetadata()));
		registerOre("blockUranium", new ItemStack(Blocks.METAL, 1, EnumMetalType.URANIUM.getMetadata()));
		registerOre("blockTungsten", new ItemStack(Blocks.METAL, 1, EnumMetalType.TUNGSTEN.getMetadata()));
		registerOre("blockArcanite", new ItemStack(Blocks.METAL, 1, EnumMetalType.ARCANITE.getMetadata()));
		registerOre("blockArcanium", new ItemStack(Blocks.METAL, 1, EnumMetalType.ARCANIUM.getMetadata()));
		registerOre("blockMageSteel", new ItemStack(Blocks.METAL, 1, EnumMetalType.MAGE_STEEL.getMetadata()));
	}

	public static void registerOre(String name, ItemStack stack) {
		OreDictionary.registerOre(name, stack);
	}

	public static void registerOre(String name, Item item) {
		OreDictionary.registerOre(name, item);
	}

	public static void registerOre(String name, Block block) {
		OreDictionary.registerOre(name, block);
	}
}
