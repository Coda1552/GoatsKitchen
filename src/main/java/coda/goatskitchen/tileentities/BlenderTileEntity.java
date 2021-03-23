package coda.goatskitchen.tileentities;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.containers.BlenderContainer;
import coda.goatskitchen.crafting.BlendingRecipe;
import coda.goatskitchen.init.GKRecipes;
import coda.goatskitchen.init.GKTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Optional;

public class BlenderTileEntity extends LockableLootTileEntity implements ITickableTileEntity {
    public static int slots = 11;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
    public int blendingTicks;
    private BlendingRecipe currentRecipe;

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
        for (int i = 0; i < getSizeInventory(); i++) {
            if (!getStackInSlot(i).isEmpty()) return false;
        }
        return true;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public void tick() {
        if (currentRecipe != null) {
            if (++blendingTicks >= 100) {
                //Clear the inventory and remove the bottle
                for (int i = 0; i < 9; i++) {
                    removeItem(i);
                }
                setInventorySlotContents(9, ItemStack.EMPTY);
                //Set the output
                setInventorySlotContents(10, currentRecipe.getCraftingResult(this));
                currentRecipe = null;
                blendingTicks = 0;
            }
        }
    }

    private void removeItem(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack.hasContainerItem())
            this.items.set(slot, stack.getContainerItem());
        else if (!stack.isEmpty()) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                this.items.set(slot, stack.getContainerItem());
            }
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        super.setInventorySlotContents(index, stack);
        onSlotChanged();
    }

    public void onSlotChanged() {
        assert world != null;
        //If output is empty, check for recipe
        if (getStackInSlot(10).isEmpty()) {
            world.getRecipeManager().getRecipe(GKRecipes.BLENDING_TYPE, this, world).ifPresent(recipe -> currentRecipe = recipe);
        }
    }
}
