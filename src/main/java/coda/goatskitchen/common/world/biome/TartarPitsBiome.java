package coda.goatskitchen.common.world.biome;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.*;

public class TartarPitsBiome extends GKBiome {
    static final Biome.ClimateSettings CLIMATE = new Biome.ClimateSettings(Biome.Precipitation.RAIN, 0.8F, Biome.TemperatureModifier.NONE, 0.4F);
    static final MobSpawnSettings.Builder SPAWN_SETTINGS = new MobSpawnSettings.Builder();
    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = (new BiomeGenerationSettings.Builder());

    public TartarPitsBiome() {
        super(CLIMATE, Biome.BiomeCategory.UNDERGROUND, 0.5F, 0.5F, (new AmbientMoodSettings().Builder()).waterColor(4566523).waterFogColor(2587774).fogColor(12638463).skyColor(7842047).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build(), GENERATION_SETTINGS.build(), SPAWN_SETTINGS.build());
    }

    static {
        BiomeDefaultFeatures.addDefaultOres(GENERATION_SETTINGS);
    }
}