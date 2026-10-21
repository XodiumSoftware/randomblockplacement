package com.megatrex4

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.item.BlockItem
import java.util.Random

/** Client-side entry point that handles input, random slot selection, and HUD rendering. */
internal object RandomBlockPlacementClient : ClientModInitializer {
    private val random = Random()
    private var randomPlacementMode = false
    private var wasRightClicking = false

    override fun onInitializeClient() {
        KeyBindings.register()

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (client.player == null || client.currentScreen != null) return@register

            val isRightClicking = client.options.useKey.isPressed
            val isPlacingBlock = isRightClicking && !wasRightClicking

            if (randomPlacementMode && isPlacingBlock) {
                client.player?.let { handleBlockPlacement(it) }
                wasRightClicking = true
            } else if (!isRightClicking) {
                wasRightClicking = false
            }
        }

        HudRenderCallback.EVENT.register { drawContext, _ ->
            if (randomPlacementMode) {
                val client = MinecraftClient.getInstance()
                RandomBlockPlacementRenderer.render(drawContext, client.window.scaledWidth, client.window.scaledHeight)
            }
        }
    }

    /** Toggles random block placement mode when the bound key is pressed. */
    fun onRandomPlaceKeyPressed() {
        randomPlacementMode = !randomPlacementMode
    }

    /** Selects a random hotbar slot containing a block when the player places a block. */
    fun handleBlockPlacement(player: ClientPlayerEntity) {
        if (!randomPlacementMode) return
        if (player.mainHandStack.item !is BlockItem) return
        randomizeHotbarSlot(player)
    }

    private fun randomizeHotbarSlot(player: ClientPlayerEntity) {
        val currentSlot = player.inventory.selectedSlot
        val blockSlots = (0..8).filter { player.inventory.getStack(it).item is BlockItem }

        if (blockSlots.size <= 1) return

        val randomSlot = blockSlots.filter { it != currentSlot }.randomOrNull() ?: return
        player.inventory.selectedSlot = randomSlot
    }
}
