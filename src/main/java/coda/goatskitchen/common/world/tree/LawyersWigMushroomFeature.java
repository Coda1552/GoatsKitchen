package coda.goatskitchen.common.world.tree;

import coda.goatskitchen.common.init.GKBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class LawyersWigMushroomFeature extends Feature<NoneFeatureConfiguration> {
    private static final BlockState TRUNK = Blocks.MUSHROOM_STEM.defaultBlockState();
    private static final BlockState MUSHROOM = GKBlocks.LAWYERS_WIG_MUSHROOM_BLOCK.get().defaultBlockState();

    //NOTE all random values below have 1 added to them when randomizing, the values determine the maximum possible output, not number of outputs

    //trunk placement
    public static int minimumTrunkHeight = 4;
    public static int trunkHeightExtra = 1;

    public LawyersWigMushroomFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel reader = context.level();
        Random random = context.random();
        BlockPos blockPos = context.origin();

        int trunkHeight = 5;//minimumTrunkHeight + random.nextInt(trunkHeightExtra + 1);
        for (int i = 0; i < trunkHeight; i++) {
            BlockPos trunkPos = blockPos.offset(0, i, 0);
            if (!canPlace(reader, trunkPos)) {
                break;
            }
            reader.setBlock(trunkPos, TRUNK, 3);
        }

        int height = 4 + random.nextInt(1);
        int width = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= height; j++) {
                double remove = 2 + Math.cos(j * -(Math.PI / height + 8)) * 2;
                for (int k = -width; k <= width; k++) {
                    for (int m = -width; m <= width; m++) {
                        for (int l = -4; l < 0; l++) {
                            if (k * k + m * m <= remove * remove) {
                                if (i == 1) {
                                    reader.setBlock(blockPos.offset(k, l + trunkHeight + 2, m), MUSHROOM, 2);
                                }
                            }
                        }
                    }
                }
            }
        }

        int sliceSize = 1;
        for (int x = -sliceSize; x <= sliceSize; x++) {
            for (int z = -sliceSize; z <= sliceSize; z++) {
                if (Math.abs(x) == sliceSize && Math.abs(z) == sliceSize) {
                    continue;
                }
                var down = blockPos.offset(x, trunkHeight - height + 1, z);
                var up = blockPos.offset(x, trunkHeight + 2, z);
                if (canPlace(reader, down)) {
                    reader.setBlock(down, MUSHROOM, 2);
                }
                if (canPlace(reader, up)) {
                    reader.setBlock(up, MUSHROOM, 2);
                }
            }
        }

        return true;
    }

    public static boolean canPlace(WorldGenLevel reader, BlockPos pos) {
        if (pos.getY() > reader.getMaxBuildHeight() || pos.getY() < 0) {
            return false;
        }
        return reader.getBlockState(pos).getBlock().equals(GKBlocks.LAWYERS_WIG_MUSHROOM.get()) || reader.isEmptyBlock(pos) || reader.getBlockState(pos).getMaterial().isReplaceable();
    }
}
