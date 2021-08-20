package coda.goatskitchen.common.containers.slot;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BlenderBottleSlot extends Slot {

    public BlenderBottleSlot(Container inventory, int index, int xPos, int yPos) {
        super(inventory, index, xPos, yPos);
    }

    public boolean mayPlace(ItemStack stack) {
        return stack.getItem() == Items.GLASS_BOTTLE;
    }

    public int getMaxStackSize(ItemStack stack) {
        return super.getMaxStackSize(stack);
    }
}
