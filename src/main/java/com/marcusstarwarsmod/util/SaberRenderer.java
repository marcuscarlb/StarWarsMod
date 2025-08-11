package com.marcusstarwarsmod.util;

import com.marcusstarwarsmod.item.CustomGeoModel;
import com.marcusstarwarsmod.item.HiltSaberItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SaberRenderer extends GeoItemRenderer<HiltSaberItem> {
    public SaberRenderer() {
        super(new CustomGeoModel());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);

        CompoundTag nbt = stack.getOrCreateTag();
        if (nbt.getBoolean("Sheathed")) return;

        int color = HiltSaberItem.getBladeColour(stack);
        float alpha = 1f;
        float width = 0.3f;
        float height = 8.5f;
        float red = 0.1f;
        float green = 0.9f;
        float blue = 0.1f;
        poseStack.pushPose();
        poseStack.translate(0.5, 4, 0.5);
        BoxRenderer.render(
                poseStack, bufferSource, Minecraft.getInstance().getFrameTime(), transformType,
                packedLight, height, width, width,
                red, green, blue, alpha
        );

        poseStack.popPose();
    }

}
