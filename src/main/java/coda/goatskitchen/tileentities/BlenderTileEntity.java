package coda.goatskitchen.tileentities;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.containers.BlenderContainer;
import coda.goatskitchen.init.GKTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class BlenderTileEntity extends LockableLootTileEntity implements ITickableTileEntity {
    public static int slots = 11;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public BlenderTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + GoatsKitchen.MOD_ID + ".blender");
    }



    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new BlenderContainer(id, player, this);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items);
        }

        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    public BlenderTileEntity() {
        this(GKTileEntities.BLENDER_TILE_ENTITY.get());
    }

    @Override
    public int getSizeInventory() {
        return slots;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public ItemStack getItem0() {
        return this.items.get(0);
    }

    public ItemStack getItem1() {
        return this.items.get(1);
    }

    public ItemStack getItem2() {
        return this.items.get(2);
    }

    public ItemStack getItem3() {
        return this.items.get(3);
    }

    public ItemStack getItem4() {
        return this.items.get(4);
    }

    public ItemStack getItem5() {
        return this.items.get(5);
    }

    public ItemStack getItem6() {
        return this.items.get(6);
    }

    public ItemStack getItem7() {
        return this.items.get(7);
    }

    public ItemStack getItem8() {
        return this.items.get(8);
    }

    @Override
    public void tick() {

    }
}
