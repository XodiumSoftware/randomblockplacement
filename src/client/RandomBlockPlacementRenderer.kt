package com.megatrex4

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.util.Identifier

/** Renders a small HUD indicator while random block placement is active. */
internal object RandomBlockPlacementRenderer {
    private val TEXTURE = Identifier.of("randomblockplacement", "textures/gui/rblock.png")

    private const val TEXTURE_WIDTH = 16
    private const val TEXTURE_HEIGHT = 16
    private const val Y_OFFSET = -15.0

    /** Renders the mod indicator texture centered on screen with a small upward offset. */
    fun render(drawContext: DrawContext, screenWidth: Int, screenHeight: Int) {
        val client = MinecraftClient.getInstance()
        if (client.options.hudHidden) return

        client.textureManager.bindTexture(TEXTURE)

        val x = (screenWidth / 2) - (TEXTURE_WIDTH / 2)
        val y = (screenHeight / 2) - (TEXTURE_HEIGHT / 2)

        val matrixStack = drawContext.matrices

        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()

        matrixStack.push()
        matrixStack.translate(0.0, Y_OFFSET, 0.0)

        drawContext.drawTexture(
            TEXTURE,
            x,
            y,
            0,
            0,
            TEXTURE_WIDTH,
            TEXTURE_HEIGHT,
            TEXTURE_WIDTH,
            TEXTURE_HEIGHT,
        )

        matrixStack.pop()

        RenderSystem.disableBlend()
    }
}
