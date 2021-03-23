package coda.goatskitchen.client.ter;

import coda.goatskitchen.tileentities.BlenderTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class BlenderTileEntityRenderer extends TileEntityRenderer<BlenderTileEntity> {
    private Minecraft mc = Minecraft.getInstance();

    public BlenderTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(BlenderTileEntity te, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (te.getItem0().equals(ItemStack.EMPTY) && te.getItem1().equals(ItemStack.EMPTY) && te.getItem2().equals(ItemStack.EMPTY) && te.getItem3().equals(ItemStack.EMPTY) && te.getItem4().equals(ItemStack.EMPTY) && te.getItem5().equals(ItemStack.EMPTY) && te.getItem6().equals(ItemStack.EMPTY) && te.getItem7().equals(ItemStack.EMPTY) && te.getItem8().equals(ItemStack.EMPTY)) {
            return;
        }

        ClientPlayerEntity player = mc.player;
        int lightLevel = getLightLevel(te.getWorld(), te.getPos().up());

        renderItem(te.getItem0(), new double[] {0.5D, 0.25D, 0.4D}, Vector3f.YP.rotationDegrees(0F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem1(), new double[] {0.4D, 0.25D, 0.5D}, Vector3f.YP.rotationDegrees(90F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem2(), new double[] {0.5D, 0.25D, 0.6D}, Vector3f.YP.rotationDegrees(0F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem3(), new double[] {0.6D, 0.25D, 0.5D}, Vector3f.YP.rotationDegrees(90F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem4(), new double[] {0.65D, 0.45D, 0.58D}, Vector3f.YP.rotationDegrees(72F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem5(), new double[] {0.6D, 0.45D, 0.4D}, Vector3f.YP.rotationDegrees(144F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem6(), new double[] {0.4D, 0.45D, 0.4D}, Vector3f.YP.rotationDegrees(216F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem7(), new double[] {0.35D, 0.45D, 0.58D}, Vector3f.YP.rotationDegrees(288F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
        renderItem(te.getItem8(), new double[] {0.5D, 0.45D, 0.65D}, Vector3f.YP.rotationDegrees(0F), matrixStackIn, bufferIn, partialTicks, combinedOverlayIn, lightLevel, 0.65f);
    }

    private void renderItem(ItemStack stack, double[] translation, Quaternion rotation, MatrixStack matrixStack, IRenderTypeBuffer buffer, float partialTicks, int combinedOverlay, int level, float scale) {
        matrixStack.push();
        matrixStack.translate(translation[0], translation[1], translation[2]);
        matrixStack.rotate(rotation);
        matrixStack.scale(scale, scale, scale);

        IBakedModel model = mc.getItemRenderer().getItemModelWithOverrides(stack, null, null);
        mc.getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStack, buffer, level, combinedOverlay, model);
        matrixStack.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int blockLight = world.getLightFor(LightType.BLOCK, pos);
        int skyLight = world.getLightFor(LightType.SKY, pos);
        return LightTexture.packLight(blockLight, skyLight);
    }
}
