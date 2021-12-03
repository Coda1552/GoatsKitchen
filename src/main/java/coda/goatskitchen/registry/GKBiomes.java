package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class GKBiomes {
    public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, GoatsKitchen.MOD_ID);

    static {
        tartarPits("tartar_pits", OverworldBiomes::theVoid);
    }

    public static RegistryObject<Biome> tartarPits(String name, Supplier<Biome> biome) {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(GoatsKitchen.MOD_ID, name)), 1));
        return REGISTER.register(name, biome);
    }
}