package org.xodium.mixin.client

import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.MultiPlayerGameMode
import net.minecraft.client.player.LocalPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.phys.BlockHitResult
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
import org.xodium.RandomBlockPlacementClient

/** Triggers random hotbar slot selection after a block placement succeeds on the client. */
@Mixin(MultiPlayerGameMode::class)
class PlayerInteractBlockMixin {
    @Inject(method = ["useItemOn"], at = [At("RETURN")])
    private fun onBlockPlaceSuccess(
        player: LocalPlayer,
        hand: InteractionHand,
        hitResult: BlockHitResult,
        cir: CallbackInfoReturnable<InteractionResult>,
    ) {
        Minecraft.getInstance().player?.let { RandomBlockPlacementClient.handleBlockPlacement(it) }
    }
}
