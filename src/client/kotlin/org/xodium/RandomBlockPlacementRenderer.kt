package org.xodium

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.resources.Identifier

/** Renders a small HUD indicator while random block placement is active. */
internal object RandomBlockPlacementRenderer {
    private val TEXTURE = Identifier.fromNamespaceAndPath("randomblockplacement", "textures/gui/rblock.png")

    private const val TEXTURE_WIDTH = 16
    private const val TEXTURE_HEIGHT = 16
    private const val Y_OFFSET = -15.0

    /** Renders the mod indicator texture centered on screen with a small upward offset. */
    fun render(
        extractor: GuiGraphicsExtractor,
        screenWidth: Int,
        screenHeight: Int,
    ) {
        val client = Minecraft.getInstance()
        if (client.gui.hud.isHidden) return

        val x = (screenWidth / 2) - (TEXTURE_WIDTH / 2)
        val y = (screenHeight / 2) - (TEXTURE_HEIGHT / 2)

        val poseStack = extractor.pose()

        poseStack.pushPose()
        poseStack.translate(0.0, Y_OFFSET, 0.0)

        extractor.blit(TEXTURE, x, y, 0, 0, 0f, 0f, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT)

        poseStack.popPose()
    }
}
