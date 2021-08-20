package coda.goatskitchen.common.blocks;

import java.util.Random;

import coda.goatskitchen.common.init.GKBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChiliPepperTopBlock extends GrowingPlantHeadBlock {
   protected static final VoxelShape SHAPE = Block.box(4.0D, 9.0D, 4.0D, 12.0D, 16.0D, 12.0D);

   public ChiliPepperTopBlock(BlockBehaviour.Properties p_i241194_1_) {
      super(p_i241194_1_, Direction.DOWN, SHAPE, false, 0.1D);
   }

   protected int getBlocksToGrowWhenBonemealed(Random p_230332_1_) {
      return NetherVines.getBlocksToGrowWhenBonemealed(p_230332_1_);
   }

   protected Block getBodyBlock() {
      return GKBlocks.CHILI_PEPPER_PLANT.get();
   }

   protected boolean canGrowInto(BlockState p_230334_1_) {
      return NetherVines.isValidGrowthState(p_230334_1_);
   }
}