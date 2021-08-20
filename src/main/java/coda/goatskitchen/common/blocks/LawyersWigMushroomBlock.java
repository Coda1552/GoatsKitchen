package coda.goatskitchen.common.blocks;

import coda.goatskitchen.common.init.GKFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class LawyersWigMushroomBlock extends SaplingBlock {
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

    public LawyersWigMushroomBlock(Properties p_i48363_1_) {
        super(null, p_i48363_1_);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
        if (p_225542_4_.nextInt(25) == 0) {
            int i = 5;
            int j = 4;

            for(BlockPos blockpos : BlockPos.betweenClosed(p_225542_3_.offset(-4, -1, -4), p_225542_3_.offset(4, 1, 4))) {
                if (p_225542_2_.getBlockState(blockpos).is(this)) {
                    --i;
                    if (i <= 0) {
                        return;
                    }
                }
            }

            BlockPos blockpos1 = p_225542_3_.offset(p_225542_4_.nextInt(3) - 1, p_225542_4_.nextInt(2) - p_225542_4_.nextInt(2), p_225542_4_.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (p_225542_2_.isEmptyBlock(blockpos1) && p_225542_1_.canSurvive(p_225542_2_, blockpos1)) {
                    p_225542_3_ = blockpos1;
                }

                blockpos1 = p_225542_3_.offset(p_225542_4_.nextInt(3) - 1, p_225542_4_.nextInt(2) - p_225542_4_.nextInt(2), p_225542_4_.nextInt(3) - 1);
            }

            if (p_225542_2_.isEmptyBlock(blockpos1) && p_225542_1_.canSurvive(p_225542_2_, blockpos1)) {
                p_225542_2_.setBlock(blockpos1, p_225542_1_, 2);
            }
        }

    }

    @Override
    protected boolean mayPlaceOn(BlockState p_200014_1_, BlockGetter p_200014_2_, BlockPos p_200014_3_) {
        return p_200014_1_.isSolidRender(p_200014_2_, p_200014_3_);
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.below();
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
            return true;
        } else {
            return p_196260_2_.getRawBrightness(p_196260_3_, 0) < 13 && blockstate.canSustainPlant(p_196260_2_, blockpos, Direction.UP, this);
        }
    }

    @Override
    public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, Random rand) {
        if (state.getValue(STAGE) == 0) {
            world.setBlock(pos, state.cycle(STAGE), 4);
        }
        else {
            if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, rand, pos)) {
                return;
            }
            GKFeatures.LAWYERS_WIG_MUSHROOM.get().place(new FeaturePlaceContext<>(world, world.getChunkSource().generator, rand, pos, null));
        }
    }
}
