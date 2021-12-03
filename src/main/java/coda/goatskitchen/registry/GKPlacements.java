package coda.goatskitchen.registry;

import coda.goatskitchen.GoatsKitchen;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class GKPlacements {
    public static PlacedFeature PLACED_FILLETSTONE_CLUSTER = Registry.register(BuiltinRegistries.PLACED_FEATURE, GoatsKitchen.MOD_ID + ":" + "placed_filletstone_cluster", GKConfiguredFeatures.CONFIGURED_FILLETSTONE_CLUSTER.placed(CountPlacement.of(UniformInt.of(48, 96)), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome()));

}
