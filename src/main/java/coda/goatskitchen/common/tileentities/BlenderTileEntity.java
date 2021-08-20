package coda.goatskitchen.common.tileentities;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.containers.BlenderContainer;
import coda.goatskitchen.common.crafting.BlendingRecipe;
import coda.goatskitchen.common.init.GKRecipes;
import coda.goatskitchen.common.init.GKTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;

public class BlenderTileEntity extends RandomizableContainerBlockEntity implements BlockEntityTicker<?> {
    public static int slots = 11;
    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
    public int blendingTicks;
    private BlendingRecipe currentRecipe;

    public BlenderTileEntity(BlockEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + GoatsKitchen.MOD_ID + ".blender");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new BlenderContainer(id, player, this);
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ContainerHelper.saveAllItems(compound, this.items);
        }

        return compound;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ContainerHelper.loadAllItems(nbt, this.items);
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

    @Override
    public void tick(Level p_155253_, BlockPos p_155254_, BlockState p_155255_, BlockEntity p_155256_) {
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
}
