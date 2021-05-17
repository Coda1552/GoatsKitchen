package coda.goatskitchen.client.renderer;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.client.model.ChefModel;
import coda.goatskitchen.entities.ChefEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChefRenderer extends MobRenderer<ChefEntity, ChefModel<ChefEntity>> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(GoatsKitchen.MOD_ID, "textures/entity/chef.png");

    public ChefRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ChefModel(), 0.2F);
    }

    public ResourceLocation getEntityTexture(ChefEntity entity) {
        return TEXTURE;
    }
}
