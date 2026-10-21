package com.megatrex4.mixin.client

import com.megatrex4.RandomBlockPlacementClient
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.network.ClientPlayerInteractionManager
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

/** Triggers random hotbar slot selection after a block placement succeeds on the client. */
@Mixin(ClientPlayerInteractionManager::class)
class PlayerInteractBlockMixin {
    @Inject(method = ["interactBlock"], at = [At("RETURN")])
    private fun onBlockPlaceSuccess(
        player: ClientPlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult,
        cir: CallbackInfoReturnable<ActionResult>,
    ) {
        MinecraftClient.getInstance().player?.let { RandomBlockPlacementClient.handleBlockPlacement(it) }
    }
}
