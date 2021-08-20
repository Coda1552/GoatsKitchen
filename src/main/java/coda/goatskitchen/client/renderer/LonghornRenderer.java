package coda.goatskitchen.client.renderer;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.model.LonghornModel;
import coda.goatskitchen.common.entities.LonghornEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LonghornRenderer extends MobRenderer<LonghornEntity, LonghornModel<LonghornEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(GoatsKitchen.MOD_ID, "textures/entity/longhorn.png");

    public LonghornRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new LonghornModel<>(), 0.85F);
    }

    public ResourceLocation getTextureLocation(LonghornEntity entity) {
        return TEXTURE;
    }
}
