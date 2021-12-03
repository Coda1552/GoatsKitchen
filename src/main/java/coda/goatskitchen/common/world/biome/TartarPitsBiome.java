package coda.goatskitchen.common.world.biome;

import coda.goatskitchen.registry.GKConfiguredFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class TartarPitsBiome extends Biome.BiomeBuilder {
    static final MobSpawnSettings.Builder SPAWN_SETTINGS = new MobSpawnSettings.Builder();
    static final BiomeGenerationSettings.Builder GENERATION_SETTINGS = new BiomeGenerationSettings.Builder();

    public TartarPitsBiome() {
        this.specialEffects(new BiomeSpecialEffects.Builder()
                .waterColor(4566523)
                .waterFogColor(2587774)
                .fogColor(12638463)
                .skyColor(7842047)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .build());
        this.downfall(0.4F);
        this.generationSettings(GENERATION_SETTINGS.build());
        this.mobSpawnSettings(SPAWN_SETTINGS.build());
        this.precipitation(Biome.Precipitation.RAIN);
        this.temperature(0.9F);
        this.biomeCategory(Biome.BiomeCategory.UNDERGROUND);
        this.temperatureAdjustment(Biome.TemperatureModifier.NONE);
        this.build();
    }

    static {
        GENERATION_SETTINGS.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, GKConfiguredFeatures.PLACED_FILLETSTONE_CLUSTER);

        BiomeDefaultFeatures.addDefaultOres(GENERATION_SETTINGS);
    }
}