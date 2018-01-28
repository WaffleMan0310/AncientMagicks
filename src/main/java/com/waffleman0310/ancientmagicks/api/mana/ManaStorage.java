package com.waffleman0310.ancientmagicks.api.mana;

import net.minecraft.nbt.NBTTagCompound;

public class ManaStorage implements IManaStorage{

    protected EnumManaType type;
    protected float purity;
    protected long mana;
    protected long capacity;
    protected long maxRecieve;
    protected long maxExtract;

    public ManaStorage(long capacity, EnumManaType type) {
        this(capacity, capacity, capacity, type);
    }

    public ManaStorage(long capacity, long maxTransfer, EnumManaType type) {
        this(capacity, maxTransfer, maxTransfer, type);
    }

    public ManaStorage(long capacity, long maxExtract, long maxRecieve, EnumManaType type) {
        this.capacity = capacity;
        this.maxExtract = maxExtract;
        this.maxRecieve = maxRecieve;
        this.type = type;
    }

    @Override
    public long recieveMana(long maxRecieve, float purity) {
        if (!canRecieve()) {
            return 0;
        }

        long manaReceived = Math.min(this.capacity - this.mana, Math.min(this.maxRecieve, maxRecieve));
        mana += manaReceived;

        float purityModification = purity * (manaReceived / (float) this.mana);
        if ((this.purity + purityModification) > 1.0f) {
            this.purity = 1.0f;
        } else {
            this.purity += purityModification;
        }

        return manaReceived;
    }

    @Override
    public long extractMana(long maxExtract) {
        if (!canExtract()) {
           return 0;
        }

        long manaExtracted = Math.min(mana, Math.min(this.maxExtract, maxExtract));
        mana -= manaExtracted;
        return manaExtracted;
    }

    @Override
    public long getManaCapacity() {
        return capacity;
    }

    @Override
    public long getManaStored() {
        return mana;
    }

    @Override
    public boolean canExtract() {
        return maxExtract > 0;
    }

    @Override
    public boolean canRecieve() {
        return maxRecieve > 0;
    }

    @Override
    public float getManaPurity() {
        return this.purity;
    }

    @Override
    public float getPurityModifier() {
        return (1 - this.purity) * 10;
    }

    @Override
    public EnumManaType getManaType() {
        return type;
    }

    public ManaStorage setMaxTransfer(long maxTransfer) {
        this.maxRecieve = maxRecieve;
        this.maxExtract = maxExtract;
        return this;
    }

    public ManaStorage setMaxRecieve(long maxRecieve) {
        this.maxRecieve = maxRecieve;
        return this;
    }

    public ManaStorage setMaxExtract(long maxExtract) {
        this.maxExtract = maxExtract;
        return this;
    }

    public ManaStorage setCapacity(long capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        }
        return this;
    }

    public ManaStorage setManaType(EnumManaType type) {
        this.type = type;
        return this;
    }

    // Strictly for server -> client sync
    public void setManaStored(long mana) {
        if (mana > capacity) {
            this.mana = capacity;
        } else if (mana < 0) {
            this.mana = 0;
        } else {
            this.mana = mana;
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong(String.format("%s_mana", getManaType().name().toLowerCase()), getManaStored());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.mana = compound.getLong(String.format("%s_mana", getManaType().name().toLowerCase()));
    }
}
