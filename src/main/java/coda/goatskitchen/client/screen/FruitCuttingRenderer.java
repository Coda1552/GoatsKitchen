package coda.goatskitchen.client.screen;

import coda.goatskitchen.client.ClientEvents;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

public class FruitCuttingRenderer {
    public final RenderTarget fruitTexture;
    public final RenderTarget swapTexture;

    public RenderTarget activeTexture;

    private int width;
    private int height;

    public FruitCuttingRenderer() {
        fruitTexture = new TextureTarget(256, 256, false, Minecraft.ON_OSX);
        swapTexture = new TextureTarget(256, 256, false, Minecraft.ON_OSX);
    }

    public void begin(ResourceLocation texture, int width, int height) {
        fruitTexture.clear(false);
        fruitTexture.bindWrite(false);

        this.width = width;
        this.height = height;
        activeTexture = fruitTexture;
        RenderSystem.setShaderTexture(0, texture);
        beginDrawing(GameRenderer.getPositionTexShader());
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(width, 256 - height, 0).uv(1, 0).endVertex();
        buffer.vertex(0, 256 - height, 0).uv(0, 0).endVertex();
        buffer.vertex(0, 256, 0).uv(0, 1).endVertex();
        buffer.vertex(width, 256, 0).uv(1, 1).endVertex();
        buffer.end();
        BufferUploader._endInternal(buffer);
        endDrawing(GameRenderer.getPositionTexShader());
    }

    public void addCut(float x, float y, float angle) {
        RenderTarget toRead;
        if (activeTexture == swapTexture) {
            swapTexture.bindRead();
            toRead = swapTexture;
            activeTexture = fruitTexture;
        } else {
            fruitTexture.bindRead();
            toRead = fruitTexture;
            activeTexture = swapTexture;
        }

        activeTexture.clear(false);
        activeTexture.bindWrite(false);

        ClientEvents.getCuttingShader().safeGetUniform("CutData").set(x, y, angle);
        RenderSystem.setShaderTexture(0, toRead.getColorTextureId());
        float u = width / 256f;
        float v = height / 256f;
        beginDrawing(ClientEvents.getCuttingShader());
        Tesselator tesselator = RenderSystem.renderThreadTesselator();
        BufferBuilder buffer = tesselator.getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        buffer.vertex(width, 256 - height, 0).color(1f, 1f, 0f, 0f).uv(u, v).endVertex();
        buffer.vertex(0, 256 - height, 0).color(0f, 1f, 0f, 0f).uv(0, v).endVertex();
        buffer.vertex(0, 256, 0).color(0f, 0f, 0f, 0f).uv(0, 0).endVertex();
        buffer.vertex(width, 256, 0).color(1f, 0f, 0f, 0f).uv(u, 0).endVertex();
        buffer.end();
        BufferUploader._endInternal(buffer);
        endDrawing(ClientEvents.getCuttingShader());
    }

    private void beginDrawing(ShaderInstance shader) {
        RenderSystem.viewport(0, 0, 256, 256);

        shader.setSampler("Sampler0", RenderSystem.getShaderTexture(0));
        if (shader.MODEL_VIEW_MATRIX != null) {
            shader.MODEL_VIEW_MATRIX.set(Matrix4f.createTranslateMatrix(0.0F, 0.0F, -2000.0F));
        }

        if (shader.PROJECTION_MATRIX != null) {
            Matrix4f projectionMatrix = Matrix4f.orthographic(256f, -256f, 1000.0F, 3000.0F);
            shader.PROJECTION_MATRIX.set(projectionMatrix);
        }

        shader.apply();
    }

    private void endDrawing(ShaderInstance shader) {
        shader.clear();
        Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
        Window window = Minecraft.getInstance().getWindow();
        RenderSystem.viewport(0, 0, window.getWidth(), window.getHeight());
    }

    public void bind() {
        RenderSystem.setShaderTexture(0, activeTexture.getColorTextureId());
    }
}
