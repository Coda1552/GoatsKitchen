package coda.goatskitchen.common.tileentities;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.containers.BlenderContainer;
import coda.goatskitchen.common.crafting.BlendingRecipe;
import coda.goatskitchen.common.init.GKRecipes;
import coda.goatskitchen.common.init.GKTileEntities;
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
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items);
        }

        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    public BlenderTileEntity() {
        this(GKTileEntities.BLENDER_TILE_ENTITY.get());
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            if (!getItem(i).isEmpty()) return false;
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
                getItem(9).shrink(1);
                //Set the output
                setItem(10, currentRecipe.assemble(this));
                currentRecipe = null;
                blendingTicks = 0;
            }
        }
    }

    private void removeItem(int slot) {
        ItemStack stack = getItem(slot);
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
    public void setItem(int index, ItemStack stack) {
        super.setItem(index, stack);
        onSlotChanged();
    }

    public void onSlotChanged() {
        assert level != null;
        //If output is empty, check for recipe
        if (getItem(10).isEmpty()) {
            currentRecipe = level.getRecipeManager().getRecipeFor(GKRecipes.BLENDING_TYPE, this, level).orElse(null);
        }
    }
}
