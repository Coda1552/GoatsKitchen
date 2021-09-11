package coda.goatskitchen.client.screen;

import coda.goatskitchen.GoatsKitchen;
import coda.goatskitchen.common.containers.CuttingBoardMenu;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class CuttingBoardScreen extends AbstractContainerScreen<CuttingBoardMenu> {
    private static final FruitCuttingRenderer CUT_RENDERER = new FruitCuttingRenderer();
    private static final ResourceLocation TEXTURE = new ResourceLocation(GoatsKitchen.MOD_ID, "textures/gui/cutting_board.png");

    private final Vector3f[] mousePoints = new Vector3f[16];
    private int currentIndex;

    private double cutStartX = -1;
    private double cutStartY = -1;

    public CuttingBoardScreen(CuttingBoardMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        CUT_RENDERER.begin(new ResourceLocation(GoatsKitchen.MOD_ID, "textures/gui/pineapple_cutting_item.png"), 48, 73);
    }

    private int next() {
        return currentIndex++ % 16;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        Matrix4f pose = poseStack.last().pose();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        CUT_RENDERER.bind();
        float u = 48 / 256f;
        float v = 73 / 256f;
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(pose, i + 88, j + 78, 0).uv(u, 0).endVertex();
        bufferbuilder.vertex(pose, i + 88, j + 5, 0).uv(u, v).endVertex();
        bufferbuilder.vertex(pose, i + 48, j + 5, 0).uv(0, v).endVertex();
        bufferbuilder.vertex(pose, i + 48, j + 78, 0).uv(0, 0).endVertex();
        tesselator.end();

        float cutRed = 0f;
        float cutGreen = 0f;
        float cutBlue = 0f;

        RenderSystem.disableCull();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        int startingPoint = currentIndex;
        for (int k = 0; k < 15; k++) {
            int index = (startingPoint + k) % 16;
            int nextIndex = (index + 1) % 16;
            if (mousePoints[index] != null) {
                mousePoints[index].setZ(mousePoints[index].z() + 1);
/*                if (mousePoints[index].z() > 20) {
                    mousePoints[index] = null;
                    continue;
                }*/
                if (mousePoints[nextIndex] != null) {
                    float t = k / 16f;
                    float angle = (float) (Mth.atan2(mousePoints[nextIndex].y() - mousePoints[index].y(), mousePoints[nextIndex].x() - mousePoints[index].x()) + Math.PI * 0.5);
                    float centerDistance = 1 - Mth.abs(t - 0.5f) * 2;
                    float fadeAmount = centerDistance * centerDistance * centerDistance;
                    float orthoX = Mth.cos(angle) * 0.5f * fadeAmount;
                    float orthoY = Mth.sin(angle) * 0.5f * fadeAmount;
                    bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
                    bufferbuilder.vertex(pose, mousePoints[index].x() - orthoX, mousePoints[index].y() - orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    bufferbuilder.vertex(pose, mousePoints[index].x() + orthoX, mousePoints[index].y() + orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    bufferbuilder.vertex(pose, mousePoints[nextIndex].x() + orthoX, mousePoints[nextIndex].y() + orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    bufferbuilder.vertex(pose, mousePoints[nextIndex].x() - orthoX, mousePoints[nextIndex].y() - orthoY, 0).color(cutRed, cutGreen, cutBlue, 1f).endVertex();
                    tesselator.end();
                }
            } else if (mousePoints[nextIndex] != null) {
                mousePoints[nextIndex].setZ(mousePoints[nextIndex].z() + 1);
/*                if (mousePoints[nextIndex].z() > 20) {
                    mousePoints[nextIndex] = null;
                }*/
            }
        }
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            cutStartX = mouseX;
            cutStartY = mouseY;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && cutStartX >= 0 && cutStartY >= 0) {
            int i = this.leftPos + 48;
            int j = (this.height - this.imageHeight) / 2 + 5;
            float xPos = (float) (cutStartX + mouseX) * 0.5f;
            float yPos = (float) (cutStartY + mouseY) * 0.5f;
            if (xPos > i && yPos > j && xPos < i + 40 && yPos < j + 73) {
                CUT_RENDERER.addCut((xPos - i) / 40, (yPos - j) / 73, (float) -Mth.atan2(mouseY - cutStartY, mouseX - cutStartX));
                cutStartX = -1;
                cutStartY = -1;
            }
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int p_97754_, double p_97755_, double p_97756_) {
        int next = next();
        Vector3f mousePoint = mousePoints[next];
        if (mousePoint == null) {
            mousePoint = new Vector3f((float) mouseX, (float) mouseY, 0);
            mousePoints[next] = mousePoint;
        } else {
            mousePoint.setX((float) mouseX);
            mousePoint.setY((float) mouseY);
        }

        return super.mouseDragged(mouseX, mouseY, p_97754_, p_97755_, p_97756_);
    }
}
