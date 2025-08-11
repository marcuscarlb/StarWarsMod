package com.marcusstarwarsmod.mixin;

import com.marcusstarwarsmod.item.HiltItem;
import com.marcusstarwarsmod.item.HiltSaberItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Shadow
    public LocalPlayer player;
    @Inject(method = "startAttack", at = @At("HEAD"), cancellable = true)
    private void startAttack(CallbackInfoReturnable<Boolean> cir) {

        ItemStack stack = player.getMainHandItem();
        if (stack.getItem() instanceof HiltSaberItem && stack.getOrCreateTag().getBoolean("Sheathed")) {
            cir.cancel(); // Cancel the attack before it's ever processed
        }
        if (stack.getItem() instanceof HiltItem) {
            cir.cancel(); // Cancel the attack before it's ever processed
        }
    }
}
