package com.marcusstarwarsmod.item;

import com.marcusstarwarsmod.MarcusStarWarsMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public class ModItems {
    public static void registerModItems() {
        MarcusStarWarsMod.LOGGER.info("Registering Mod Items");
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MarcusStarWarsMod.MOD_ID, name), item);
    }
    public static final Item GENERIC_KYBER_CRYSTAL = registerItem("kyber_crystal", new Item(new Item.Properties()));

    public static final Item GENERIC_SABER = registerItem("obi_wan_kenobi", new HiltSaberItem(Tiers.IRON, 16, 0.6f, new Item.Properties()));

    public static final Item GENERIC_HILT = registerItem("generic_hilt", new HiltItem(new Item.Properties()));
}
