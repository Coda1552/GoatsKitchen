package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.world.biome.TartarPitsBiome;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GKBiomes {
    public static final DeferredRegister<Biome> REGISTER = DeferredRegister.create(ForgeRegistries.BIOMES, GoatsKitchen.MOD_ID);

    public static final RegistryObject<Biome> TARTAR_PITS = REGISTER.register("tartar_pits", () -> new TartarPitsBiome().build());
}