package coda.goatskitchen.crafting;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.init.GKRecipes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class BlendingRecipe implements IRecipe {
    private final ResourceLocation id;
    private final String group;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    final boolean isSimple;

    public BlendingRecipe(ResourceLocation idIn, String groupIn, ItemStack recipeOutputIn, NonNullList<Ingredient> recipeItemsIn) {
        this.id = idIn;
        this.group = groupIn;
        this.recipeOutput = recipeOutputIn;
        this.recipeItems = recipeItemsIn;
        this.isSimple = recipeItemsIn.stream().allMatch(Ingredient::isSimple);
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < inv.getSizeInventory(); ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    recipeitemhelper.func_221264_a(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.recipeItems.size() && (isSimple ? recipeitemhelper.canCraft(this, (IntList) null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs, this.recipeItems) != null);
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= this.recipeItems.size();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeType<?> getType() {
        return IRecipeType.CRAFTING;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GKRecipes.BLENDING_SERIALIZER.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessRecipe> {

        @Override
        public ShapelessRecipe read(ResourceLocation recipeId, JsonObject json) {
            return null;
        }

        @Nullable
        @Override
        public ShapelessRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            return null;
        }

        @Override
        public void write(PacketBuffer buffer, ShapelessRecipe recipe) {

        }
    }
}
