package coda.goatskitchen.common.crafting;

import coda.goatskitchen.registry.GKRecipes;
import coda.goatskitchen.common.tileentities.BlenderTileEntity;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlendingRecipe implements Recipe<BlenderTileEntity> {
    private final ResourceLocation id;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> recipeItems;
    private final Ingredient bottle;

    public BlendingRecipe(ResourceLocation idIn, ItemStack recipeOutput, NonNullList<Ingredient> recipeItems, Ingredient bottle) {
        this.id = idIn;
        this.recipeOutput = recipeOutput;
        this.recipeItems = recipeItems;
        this.bottle = bottle;
    }

    @Override
    public boolean matches(BlenderTileEntity inv, Level worldIn) {
        if (!bottle.test(inv.getItem(9))) {
            return false;
        }
        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < inv.getContainerSize() - 2; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.recipeItems.size() && RecipeMatcher.findMatches(inputs, this.recipeItems) != null;
    }

    @Override
    public ItemStack assemble(BlenderTileEntity inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.recipeOutput;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeType<?> getType() {
        return GKRecipes.BLENDING_TYPE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GKRecipes.BLENDING_SERIALIZER.get();
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<BlendingRecipe> {
        @Override
        public BlendingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            final NonNullList<Ingredient> items = NonNullList.create();
            for (JsonElement element : json.getAsJsonArray("ingredients")) {
                items.add(Ingredient.fromJson(element));
            }
            return new BlendingRecipe(recipeId, ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result")), items, Ingredient.fromJson(json.get("bottle")));
        }

        @Nullable
        @Override
        public BlendingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            final NonNullList<Ingredient> items = NonNullList.withSize(buffer.readVarInt(), Ingredient.EMPTY);
            for (int i = 0; i < items.size(); i++) {
                items.set(i, Ingredient.fromNetwork(buffer));
            }
            return new BlendingRecipe(recipeId, buffer.readItem(), items, Ingredient.fromNetwork(buffer));
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, BlendingRecipe recipe) {
            buffer.writeVarInt(recipe.recipeItems.size());
            for (Ingredient recipeItem : recipe.recipeItems) {
                recipeItem.toNetwork(buffer);
            }
            buffer.writeItem(recipe.recipeOutput);
            recipe.bottle.toNetwork(buffer);
        }
    }
}
