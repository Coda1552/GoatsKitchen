package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.crafting.BlendingRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GKRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<BlendingRecipe.Serializer> BLENDING_SERIALIZER = REGISTER.register("blending", BlendingRecipe.Serializer::new);
    public static final RecipeType<BlendingRecipe> BLENDING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(GoatsKitchen.MOD_ID, "blending"), new RecipeType<BlendingRecipe>() {
        @Override
        public String toString() {
            return "blending";
        }
    });
}
