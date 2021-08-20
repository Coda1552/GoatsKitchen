package coda.goatskitchen.client.renderer;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.model.ChefModel;
import coda.goatskitchen.common.entities.ChefEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChefRenderer extends MobRenderer<ChefEntity, ChefModel<ChefEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(GoatsKitchen.MOD_ID, "textures/entity/chef.png");

    public ChefRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ChefModel(), 0.2F);
    }

    public ResourceLocation getTextureLocation(ChefEntity entity) {
        return TEXTURE;
    }
}
