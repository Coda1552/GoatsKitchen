package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import net.minecraft.core.Registry;
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


    // TODO - just make a biome in code
    public static final RegistryObject<Biome> TARTAR_PITS = REGISTER.register("tartar_pits", () -> new TartarPitsBiome());
}