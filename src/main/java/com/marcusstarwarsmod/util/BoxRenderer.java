package com.marcusstarwarsmod.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Matrix3f;
import org.joml.Matrix4f;


public class BoxRenderer {

    private static final int LAYER_COUNT = 20;

    public static void render(PoseStack matrices, MultiBufferSource vertexConsumers, float tickDelta,
                              ItemDisplayContext renderContext,
                              int light, float length, float width, float depth,
                              float red, float green, float blue, float alpha) {
        matrices.pushPose();

        for (int i = 0; i < 1; i++) {
            float size = 0.05f + (0.01f);
            renderCore(matrices, vertexConsumers, renderContext, length,
                    width * size, depth * size,
                    1, 1, 1, alpha * (0.95f - 0.1f * (float) i),
                    15728880, true);
        }

        for (int i = 3; i < LAYER_COUNT; i++) {
            float progress = (i - 3) / (float) (LAYER_COUNT - 4);
            float layerWidth = width * (0.5f + 0.9f * progress);
            float layerDepth = depth * (0.5f + 0.9f * progress);
            float layerAlpha = alpha * (0.7f - 0.3f * progress);

            // Color transition (white -> blade color)
            float layerRed = lerp(1f, red, progress * 1.9f);
            float layerGreen = lerp(1f, green, progress * 1.9f);
            float layerBlue = lerp(1f, blue, progress * 1.9f);

            renderBladeLayer(matrices, vertexConsumers, renderContext, length,
                    layerWidth, layerDepth,
                    layerRed, layerGreen, layerBlue, layerAlpha,
                    light, false);
        }

        matrices.popPose();

    }

    private static boolean isFirstPersonView(ItemDisplayContext context) {
        return context == ItemDisplayContext.FIRST_PERSON_LEFT_HAND ||
                context == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND;
    }

    private static void renderCore(PoseStack matrices, MultiBufferSource vertexConsumers,
                                   ItemDisplayContext renderContext,
                                   float length, float width, float depth,
                                   float red, float green, float blue, float alpha,
                                   int packedLight, boolean isCore) {

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(CustomRenderType.CORE_RENDER);
        Matrix4f modelMatrix = matrices.last().pose();
        Matrix3f normalMatrix = matrices.last().normal();

        float halfWidth = width / 2;
        float halfDepth = depth / 2;

        // Top face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                -halfWidth, 0, halfDepth, halfWidth, 0, halfDepth,
                halfWidth, length, halfDepth, -halfWidth, length, halfDepth,
                0, 1, 1, 1, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
                red, green, blue, alpha, packedLight);

        // Back face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                halfWidth, 0, -halfDepth, -halfWidth, 0, -halfDepth,
                -halfWidth, length, -halfDepth, halfWidth, length, -halfDepth,
                0, 1, 1, 1, 1, 0, 0, 0,
                0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
                red, green, blue, alpha, packedLight);

        // Left face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                -halfWidth, 0, -halfDepth, -halfWidth, 0, halfDepth,
                -halfWidth, length, halfDepth, -halfWidth, length, -halfDepth,
                0, 1, 1, 1, 1, 0, 0, 0,
                -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
                red, green, blue, alpha, packedLight);

        // Right face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                halfWidth, 0, halfDepth, halfWidth, 0, -halfDepth,
                halfWidth, length, -halfDepth, halfWidth, length, halfDepth,
                0, 1, 1, 1, 1, 0, 0, 0,
                1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
                red, green, blue, alpha, packedLight);

//             Top face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                -halfWidth, length, -halfDepth, halfWidth, length, -halfDepth,
                halfWidth, length, halfDepth, -halfWidth, length, halfDepth,
                0, 0, 1, 0, 1, 1, 0, 1,
                0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
                red, green, blue, alpha, packedLight);
    }

    private static void renderBladeLayer(PoseStack matrices, MultiBufferSource vertexConsumers,
                                         ItemDisplayContext renderContext,
                                         float length, float width, float depth,
                                         float red, float green, float blue, float alpha,
                                         int packedLight, boolean isCore) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(CustomRenderType.CUSTOM_TRANSPARENT);
        Matrix4f modelMatrix = matrices.last().pose();
        Matrix3f normalMatrix = matrices.last().normal();

        float halfWidth = width / 2;
        float halfDepth = depth / 2;
        if(isFirstPersonView(renderContext)) {
//        // Right face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                halfWidth, 0, halfDepth, halfWidth, 0, -halfDepth,
                halfWidth, length, -halfDepth, halfWidth, length, halfDepth,
                0, 1, 1, 1, 1, 0, 0, 0,
                1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
                red, green, blue, alpha, packedLight);
//        // Back face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                halfWidth, 0, -halfDepth, -halfWidth, 0, -halfDepth,
                -halfWidth, length, -halfDepth, halfWidth, length, -halfDepth,
                0, 1, 1, 1, 1, 0, 0, 0,
                0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
                red, green, blue, alpha, packedLight);
            // Front face
        } else {
            addQuad(vertexConsumer, modelMatrix, normalMatrix,
                    -halfWidth, 0, halfDepth, halfWidth, 0, halfDepth,
                    halfWidth, length, halfDepth, -halfWidth, length, halfDepth,
                    0, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
                    red, green, blue, alpha, packedLight);

            // Back face
            addQuad(vertexConsumer, modelMatrix, normalMatrix,
                    halfWidth, 0, -halfDepth, -halfWidth, 0, -halfDepth,
                    -halfWidth, length, -halfDepth, halfWidth, length, -halfDepth,
                    0, 1, 1, 1, 1, 0, 0, 0,
                    0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
                    red, green, blue, alpha, packedLight);
            // Left face
            addQuad(vertexConsumer, modelMatrix, normalMatrix,
                    -halfWidth, 0, -halfDepth, -halfWidth, 0, halfDepth,
                    -halfWidth, length, halfDepth, -halfWidth, length, -halfDepth,
                    0, 1, 1, 1, 1, 0, 0, 0,
                    -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,
                    red, green, blue, alpha, packedLight);

            // Right face
            addQuad(vertexConsumer, modelMatrix, normalMatrix,
                    halfWidth, 0, halfDepth, halfWidth, 0, -halfDepth,
                    halfWidth, length, -halfDepth, halfWidth, length, halfDepth,
                    0, 1, 1, 1, 1, 0, 0, 0,
                    1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
                    red, green, blue, alpha, packedLight);

//             Top face
            addQuad(vertexConsumer, modelMatrix, normalMatrix,
                    -halfWidth, length, -halfDepth, halfWidth, length, -halfDepth,
                    halfWidth, length, halfDepth, -halfWidth, length, halfDepth,
                    0, 0, 1, 0, 1, 1, 0, 1,
                    0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
                    red, green, blue, alpha, packedLight);
        }

        // Top face
        addQuad(vertexConsumer, modelMatrix, normalMatrix,
                -halfWidth, length, -halfDepth, halfWidth, length, -halfDepth,
                halfWidth, length, halfDepth, -halfWidth, length, halfDepth,
                0, 0, 1, 0, 1, 1, 0, 1,
                0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
                red, green, blue, alpha, packedLight);
    }

    private static float lerp(float start, float end, float progress) {
        return start + (end - start) * Math.min(1, progress);
    }

    private static void addQuad(VertexConsumer vertexConsumer, Matrix4f modelMatrix, Matrix3f normalMatrix,
                                float x1, float y1, float z1, float x2, float y2, float z2,
                                float x3, float y3, float z3, float x4, float y4, float z4,
                                float u1, float v1, float u2, float v2, float u3, float v3, float u4, float v4,
                                float nx1, float ny1, float nz1, float nx2, float ny2, float nz2,
                                float nx3, float ny3, float nz3, float nx4, float ny4, float nz4,
                                float red, float green, float blue, float alpha, int packedLight) {
        vertex(vertexConsumer, modelMatrix, normalMatrix, x1, y1, z1, u1, v1, nx1, ny1, nz1, red, green, blue, alpha, packedLight);
        vertex(vertexConsumer, modelMatrix, normalMatrix, x2, y2, z2, u2, v2, nx2, ny2, nz2, red, green, blue, alpha, packedLight);
        vertex(vertexConsumer, modelMatrix, normalMatrix, x3, y3, z3, u3, v3, nx3, ny3, nz3, red, green, blue, alpha, packedLight);
        vertex(vertexConsumer, modelMatrix, normalMatrix, x4, y4, z4, u4, v4, nx4, ny4, nz4, red, green, blue, alpha, packedLight);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f modelMatrix, Matrix3f normalMatrix,
                               float x, float y, float z, float u, float v,
                               float nx, float ny, float nz,
                               float red, float green, float blue, float alpha, int packedLight) {
        vertexConsumer.vertex(modelMatrix, x, y, z)
                .color(red, green, blue, alpha)
                .uv(u, v)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(packedLight)
                .normal(normalMatrix, nx, ny, nz)
                .endVertex();
    }
}