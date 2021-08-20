package coda.goatskitchen.client.renderer;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.model.LonghornModel;
import coda.goatskitchen.common.entities.LonghornEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LonghornRenderer extends MobRenderer<LonghornEntity, LonghornModel> {
    public static final ModelLayerLocation MODEL_LAYER = new ModelLayerLocation(new ResourceLocation(GoatsKitchen.MOD_ID, "longhorn"), "main");
    public static final ResourceLocation TEXTURE = new ResourceLocation(GoatsKitchen.MOD_ID, "textures/entity/longhorn.png");

    public LonghornRenderer(EntityRendererProvider.Context context) {
        super(context, new LonghornModel(context.bakeLayer(MODEL_LAYER)), 0.85F);
    }

    public ResourceLocation getTextureLocation(LonghornEntity entity) {
        return TEXTURE;
    }
}
