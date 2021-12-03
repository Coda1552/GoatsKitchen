package coda.goatskitchen.common.world.feature;

import coda.goatskitchen.registry.GKBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Random;

public class FilletstoneClusterFeature extends Feature<FilletstoneClusterConfiguration> {

   public FilletstoneClusterFeature() {
      super(FilletstoneClusterConfiguration.CODEC);
   }

   public boolean place(FeaturePlaceContext<FilletstoneClusterConfiguration> context) {
      BlockPos pos = context.origin();
      Random random = context.random();
      Level world = context.level().getLevel();
      BlockPos.MutableBlockPos mutablePos = pos.mutable();

      if (world.getBlockState(pos).is(BlockTags.BASE_STONE_OVERWORLD) && context.chunkGenerator().getNoiseBiome(pos.getX(), pos.getY(), pos.getZ()).equals(GKBiomes.TARTAR_PITS.get())) {
         for (int i = -20; i < 20; i++) {
            world.setBlock(pos.offset(i, 0, i), Blocks.RED_SANDSTONE.defaultBlockState(), 2);
         }
      }

      return true;
   }
}