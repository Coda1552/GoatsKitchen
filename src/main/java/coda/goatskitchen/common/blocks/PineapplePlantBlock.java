package coda.goatskitchen.common.blocks;

import coda.goatskitchen.common.init.GKItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class PineapplePlantBlock extends BushBlock implements BonemealableBlock {
   public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
   private static final VoxelShape SAPLING_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
   private static final VoxelShape MID_GROWTH_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
   private final Random random = new Random();

   public PineapplePlantBlock(BlockBehaviour.Properties p_i49971_1_) {
      super(p_i49971_1_);
      this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
   }

   @Override
   public ItemStack getCloneItemStack(BlockGetter p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
      return new ItemStack(GKItems.PINEAPPLE_CROWN.get());
   }

   @Override
   public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_) {
      if (p_220053_1_.getValue(AGE) == 0) {
         return SAPLING_SHAPE;
      } else {
         return p_220053_1_.getValue(AGE) < 4 ? MID_GROWTH_SHAPE : super.getShape(p_220053_1_, p_220053_2_, p_220053_3_, p_220053_4_);
      }
   }

   @Override
   public boolean isRandomlyTicking(BlockState p_149653_1_) {
      return p_149653_1_.getValue(AGE) < 5;
   }

   @Override
   public void randomTick(BlockState p_225542_1_, ServerLevel p_225542_2_, BlockPos p_225542_3_, Random p_225542_4_) {
      int i = p_225542_1_.getValue(AGE);
      if (i < 5 && p_225542_2_.getRawBrightness(p_225542_3_.above(), 0) >= 9) {
         if (p_225542_2_.getBlockState(p_225542_3_.below()).is(Blocks.COARSE_DIRT) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_225542_2_, p_225542_3_, p_225542_1_,p_225542_4_.nextInt(4) == 0)) {
            p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_225542_2_, p_225542_3_, p_225542_1_);
         }
         else if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_225542_2_, p_225542_3_, p_225542_1_,p_225542_4_.nextInt(5) == 0)) {
            p_225542_2_.setBlock(p_225542_3_, p_225542_1_.setValue(AGE, i + 1), 2);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_225542_2_, p_225542_3_, p_225542_1_);
         }
      }
   }

   @Override
   public InteractionResult use(BlockState p_225533_1_, Level p_225533_2_, BlockPos p_225533_3_, Player p_225533_4_, InteractionHand p_225533_5_, BlockHitResult p_225533_6_) {
      int i = p_225533_1_.getValue(AGE);
      boolean flag = i == 5;
      if (!flag && p_225533_4_.getItemInHand(p_225533_5_).getItem() == Items.BONE_MEAL) {
         return InteractionResult.PASS;
      } else if (flag && (p_225533_4_.getItemInHand(p_225533_5_).getItem() instanceof SwordItem)) {
         popResource(p_225533_2_, p_225533_3_, new ItemStack(GKItems.PINEAPPLE_CROWN.get()));
         popResource(p_225533_2_, p_225533_3_, new ItemStack(GKItems.PINEAPPLE_CUTTINGS.get(), random.nextInt(3) + 2));

         int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, p_225533_4_.getItemInHand(p_225533_5_));
         if (level > 0) {
            for (int j = 0; j <= level; j++) {
               popResource(p_225533_2_, p_225533_3_, new ItemStack(GKItems.PINEAPPLE_CUTTINGS.get(), random.nextInt(2)));
            }
         }

         p_225533_2_.playSound(null, p_225533_3_, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + p_225533_2_.random.nextFloat() * 0.4F);
         p_225533_2_.setBlock(p_225533_3_, p_225533_1_.setValue(AGE, 1), 1);
         return InteractionResult.sidedSuccess(p_225533_2_.isClientSide);
      } else if (flag) {
         popResource(p_225533_2_, p_225533_3_, new ItemStack(GKItems.PINEAPPLE.get()));
         p_225533_2_.playSound(null, p_225533_3_, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + p_225533_2_.random.nextFloat() * 0.4F);
         p_225533_2_.setBlock(p_225533_3_, p_225533_1_.setValue(AGE, 1), 1);
         return InteractionResult.sidedSuccess(p_225533_2_.isClientSide);

      }
      return super.use(p_225533_1_, p_225533_2_, p_225533_3_, p_225533_4_, p_225533_5_, p_225533_6_);
   }


   @Override
   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_206840_1_) {
      p_206840_1_.add(AGE);
   }

   @Override
   public boolean isValidBonemealTarget(BlockGetter p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
      return p_176473_3_.getValue(AGE) < 5;
   }

   @Override
   public boolean isBonemealSuccess(Level p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
      return true;
   }

   @Override
   public void performBonemeal(ServerLevel p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
      int i = Math.min(5, p_225535_4_.getValue(AGE) + 1);
      p_225535_1_.setBlock(p_225535_3_, p_225535_4_.setValue(AGE, Integer.valueOf(i)), 2);
   }
}
