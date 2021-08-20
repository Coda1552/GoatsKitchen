package coda.goatskitchen.common.blocks;

import coda.goatskitchen.common.init.GKBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChiliPepperBlock extends GrowingPlantBodyBlock {
   public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

   public ChiliPepperBlock(BlockBehaviour.Properties p_i241195_1_) {
      super(p_i241195_1_, Direction.DOWN, SHAPE, false);
   }

   protected GrowingPlantHeadBlock getHeadBlock() {
      return (GrowingPlantHeadBlock) GKBlocks.CHILI_PEPPER_PLANT.get();
   }
}