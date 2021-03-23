package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.crafting.BlendingRecipe;
import coda.goatskitchen.crafting.BlendingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<BlendingRecipe>> BLENDING_SERIALIZER = REGISTRY.register("blending", BlendingRecipe.Serializer::new);

    public static final RegistryObject<BlendingRecipeSerializer<BlendingRecipe>> BLENDING = REGISTRY.register("blending", () -> new BlendingRecipeSerializer<>(BlendingRecipe::new));
}
