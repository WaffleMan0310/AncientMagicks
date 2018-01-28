package com.waffleman0310.ancientmagicks.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public interface IPlant extends IGrowable {

    float getGrowthChance(World worldIn, BlockPos pos);

    int getBonemealAgeIncrease(World worldIn, BlockPos pos);

    NonNullList<DropEntry> getDrops();

    Item getCrop();

    Item getSeeds();

    int getMinSeedsDropped();

    int getMaxSeedsDropped();

    default int getLightToGrow(World worldIn, BlockPos pos){
        return 9;
    }

    class DropEntry {

        private final Item item;
        private final int meta;
        private final int min;
        private final int max;
        private final float chance;

        public DropEntry(Item item, int meta, int min, int max, float chance) {
            this.item = item;
            this.meta = meta;
            this.max = max;
            this.min = min;
            this.chance = chance;
        }

        public DropEntry(Item item, int min, int max, float chance) {
            this.item = item;
            this.meta = 0;
            this.max = max;
            this.min = min;
            this.chance = chance;
        }

        public ItemStack drop(Random random) {
            return new ItemStack(item, meta, MathHelper.getInt(random, min, max));
        }

        public float getChance() {
            return chance;
        }

        public Item getItem() {
            return item;
        }

        public int getMeta() {
            return meta;
        }

        public static DropEntry create(Item item, int meta, int min, int max, float chance) {
            return new DropEntry(item, meta, min, max, chance);
        }

        public static DropEntry create(Block block, int meta, int min, int max, float chance) {
            return new DropEntry(Item.getItemFromBlock(block), meta, min, max, chance);
        }
    }
}
