package fr.samlegamer.moremushrooms.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockBaseMushroom extends BushBlock
{
	   protected static final float AABB_OFFSET = 3.0F;
	   protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);

	   public BlockBaseMushroom()
	   {
	      super(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((p_50892_) -> {
	          return 1;
	      }).hasPostProcess(BlockBaseMushroom::always));
	   }
	   
	   private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		      return true;
		   }
	   
	   public VoxelShape getShape(BlockState p_54889_, BlockGetter p_54890_, BlockPos p_54891_, CollisionContext p_54892_) {
	      return SHAPE;
	   }

	   public void randomTick(BlockState p_54884_, ServerLevel p_54885_, BlockPos p_54886_, Random p_54887_) {
	      if (p_54887_.nextInt(25) == 0) {
	         int i = 5;
	         @SuppressWarnings("unused")
			int j = 4;

	         for(BlockPos blockpos : BlockPos.betweenClosed(p_54886_.offset(-4, -1, -4), p_54886_.offset(4, 1, 4))) {
	            if (p_54885_.getBlockState(blockpos).is(this)) {
	               --i;
	               if (i <= 0) {
	                  return;
	               }
	            }
	         }

	         BlockPos blockpos1 = p_54886_.offset(p_54887_.nextInt(3) - 1, p_54887_.nextInt(2) - p_54887_.nextInt(2), p_54887_.nextInt(3) - 1);

	         for(int k = 0; k < 4; ++k) {
	            if (p_54885_.isEmptyBlock(blockpos1) && p_54884_.canSurvive(p_54885_, blockpos1)) {
	               p_54886_ = blockpos1;
	            }

	            blockpos1 = p_54886_.offset(p_54887_.nextInt(3) - 1, p_54887_.nextInt(2) - p_54887_.nextInt(2), p_54887_.nextInt(3) - 1);
	         }

	         if (p_54885_.isEmptyBlock(blockpos1) && p_54884_.canSurvive(p_54885_, blockpos1)) {
	            p_54885_.setBlock(blockpos1, p_54884_, 2);
	         }
	      }

	   }

	   protected boolean mayPlaceOn(BlockState p_54894_, BlockGetter p_54895_, BlockPos p_54896_) {
	      return p_54894_.isSolidRender(p_54895_, p_54896_);
	   }

	   public boolean canSurvive(BlockState p_54880_, LevelReader p_54881_, BlockPos p_54882_) {
	      BlockPos blockpos = p_54882_.below();
	      BlockState blockstate = p_54881_.getBlockState(blockpos);
	      if (blockstate.is(BlockTags.MUSHROOM_GROW_BLOCK)) {
	         return true;
	      } else {
	         return p_54881_.getRawBrightness(p_54882_, 200) < 13 && blockstate.canSustainPlant(p_54881_, blockpos, net.minecraft.core.Direction.UP, this);
	      }
	   }
}