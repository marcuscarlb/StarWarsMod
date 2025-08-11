package com.marcusstarwarsmod.mixin;

import com.marcusstarwarsmod.item.HiltItem;
import com.marcusstarwarsmod.item.HiltSaberItem;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class ClientPlayerEntityMixin extends Player {

	public ClientPlayerEntityMixin(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
		super(level, blockPos, f, gameProfile);
    }


	@Inject(at = @At("HEAD"), method = "swing", cancellable = true)
	private void swing(InteractionHand hand, CallbackInfo ci) {
		ItemStack stack = this.getItemInHand(hand);

		if (stack.getItem() instanceof HiltSaberItem && stack.getOrCreateTag().getBoolean("Sheathed")) {
			// Only cancel if player is not targeting an entity or block
			ci.cancel();
		}
		if (stack.getItem() instanceof HiltItem) {
			// Only cancel if player is not targeting an entity or block
			ci.cancel();
		}
	}
}