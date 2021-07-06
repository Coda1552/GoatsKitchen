package coda.goatskitchen.common.blocks;

import coda.goatskitchen.init.GKBlocks;
import net.minecraft.block.*;

public class PineappleBlock extends StemGrownBlock {
    public PineappleBlock(AbstractBlock.Properties p_i48365_1_) {
        super(p_i48365_1_);
    }

    public StemBlock getStem() {
        return (StemBlock) GKBlocks.PINEAPPLE_STEM.get();
    }

    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock)Blocks.ATTACHED_MELON_STEM;
    }
}
