package coda.goatskitchen.crafting;

import coda.goatskitchen.init.GKRecipes;
import coda.goatskitchen.tileentities.BlenderTileEntity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlendingRecipe implements IRecipe<BlenderTileEntity> {
    private final ResourceLocation id;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean isSimple;
    private final Ingredient bottle;

    public BlendingRecipe(ResourceLocation idIn, ItemStack recipeOutput, NonNullList<Ingredient> recipeItems, Ingredient bottle) {
        this.id = idIn;
        this.recipeOutput = recipeOutput;
        this.recipeItems = recipeItems;
        this.isSimple = recipeItems.stream().allMatch(Ingredient::isSimple);
        this.bottle = bottle;
    }

    @Override
    public boolean matches(BlenderTileEntity inv, World worldIn) {
        if (!bottle.test(inv.getStackInSlot(9))) {
            return false;
        }
        RecipeItemHelper recipeitemhelper = new RecipeItemHelper();
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < inv.getSizeInventory() - 2; ++j) {
            ItemStack itemstack = inv.getStackInSlot(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    recipeitemhelper.func_221264_a(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.recipeItems.size() && (isSimple ? recipeitemhelper.canCraft(this, null) : RecipeMatcher.findMatches(inputs, this.recipeItems) != null);
    }

    @Override
    public ItemStack getCraftingResult(BlenderTileEntity inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
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
        return GKRecipes.BLENDING_TYPE;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GKRecipes.BLENDING_SERIALIZER.get();
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BlendingRecipe> {
        @Override
        public BlendingRecipe read(ResourceLocation recipeId, JsonObject json) {
            final NonNullList<Ingredient> items = NonNullList.create();
            for (JsonElement element : json.getAsJsonArray("ingredients")) {
                items.add(Ingredient.deserialize(element));
            }
            return new BlendingRecipe(recipeId, ShapedRecipe.deserializeItem(json.getAsJsonObject("result")), items, Ingredient.deserialize(json.get("bottle")));
        }

        @Nullable
        @Override
        public BlendingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            final NonNullList<Ingredient> items = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, Ingredient.read(buffer));
            }
            return new BlendingRecipe(recipeId, buffer.readItemStack(), items, Ingredient.read(buffer));
        }

        @Override
        public void write(PacketBuffer buffer, BlendingRecipe recipe) {
            buffer.writeVarInt(recipe.recipeItems.size());
            for (Ingredient recipeItem : recipe.recipeItems) {
                recipeItem.write(buffer);
            }
            buffer.writeItemStack(recipe.recipeOutput);
            recipe.bottle.write(buffer);
        }
    }
}
