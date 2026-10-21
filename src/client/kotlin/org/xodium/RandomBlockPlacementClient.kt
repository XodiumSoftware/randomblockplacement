package org.xodium

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.player.LocalPlayer
import net.minecraft.network.chat.Component
import net.minecraft.world.item.BlockItem
import java.util.Random

/** Client-side entry point that handles input, random slot selection, and HUD rendering. */
internal object RandomBlockPlacementClient : ClientModInitializer {
    private val random = Random()
    private var randomPlacementMode = false
    private var wasRightClicking = false

    override fun onInitializeClient() {
        KeyBindings.register()

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            if (client.player == null || client.gui.screen() != null) return@register

            val isRightClicking = client.options.keyUse.isDown
            val isPlacingBlock = isRightClicking && !wasRightClicking

            if (randomPlacementMode && isPlacingBlock) {
                client.player?.let { handleBlockPlacement(it) }
                wasRightClicking = true
            } else if (!isRightClicking) {
                wasRightClicking = false
            }
        }

        HudElementRegistry.addLast(RandomBlockPlacement.id("hud")) { extractor, _ ->
            if (randomPlacementMode) {
                val client = Minecraft.getInstance()
                RandomBlockPlacementRenderer.render(
                    extractor,
                    client.window.guiScaledWidth,
                    client.window.guiScaledHeight,
                )
            }
        }
    }

    /** Toggles random block placement mode when the bound key is pressed. */
    fun onRandomPlaceKeyPressed() {
        randomPlacementMode = !randomPlacementMode

        val translationKey =
            if (randomPlacementMode) {
                "randomblockplacement.enabled"
            } else {
                "randomblockplacement.disabled"
            }
        Minecraft
            .getInstance()
            .gui
            .hud
            .setOverlayMessage(Component.translatable(translationKey), false)
    }

    /** Selects a random hotbar slot containing a block when the player places a block. */
    fun handleBlockPlacement(player: LocalPlayer) {
        if (!randomPlacementMode) return
        if (player.mainHandItem.item !is BlockItem) return
        randomizeHotbarSlot(player)
    }

    private fun randomizeHotbarSlot(player: LocalPlayer) {
        val currentSlot = player.inventory.selectedSlot
        val blockSlots = (0..8).filter { player.inventory.getItem(it).item is BlockItem }

        if (blockSlots.size <= 1) return

        val randomSlot = blockSlots.filter { it != currentSlot }.randomOrNull() ?: return
        player.inventory.selectedSlot = randomSlot
    }
}
