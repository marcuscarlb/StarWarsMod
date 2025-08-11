package com.marcusstarwarsmod.item;

import com.marcusstarwarsmod.MarcusStarWarsMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CustomGeoModel extends GeoModel<HiltSaberItem> {
    private final ResourceLocation model = new ResourceLocation(MarcusStarWarsMod.MOD_ID, "geo/obi_wan_kenobi.geo.json");
    private final ResourceLocation texture = new ResourceLocation(MarcusStarWarsMod.MOD_ID, "textures/item/obi_wan_kenobi.png");
    private final ResourceLocation animation = new ResourceLocation(MarcusStarWarsMod.MOD_ID, "animations/obi_wan_kenobi.json");

    @Override
    public ResourceLocation getModelResource(HiltSaberItem hiltSaberItem) {
        return model;
    }

    @Override
    public ResourceLocation getTextureResource(HiltSaberItem hiltSaberItem) {
        return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(HiltSaberItem hiltSaberItem) {
        return animation;
    }
}
