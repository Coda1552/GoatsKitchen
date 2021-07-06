package coda.goatskitchen.init;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.crafting.BlendingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GKRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> REGISTER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GoatsKitchen.MOD_ID);

    public static final RegistryObject<BlendingRecipe.Serializer> BLENDING_SERIALIZER = REGISTER.register("blending", BlendingRecipe.Serializer::new);
    public static final IRecipeType<BlendingRecipe> BLENDING_TYPE = Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(GoatsKitchen.MOD_ID, "blending"), new IRecipeType<BlendingRecipe>() {
        @Override
        public String toString() {
            return "blending";
        }
    });
}
