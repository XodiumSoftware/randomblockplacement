package org.xodium

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

/** Main entry point for the Random Block Placement mod. */
internal object RandomBlockPlacement : ModInitializer {
    /** Mod identifier used for logging and Fabric metadata. */
    const val MOD_ID: String = "randomblockplacement"

    private val LOGGER = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        LOGGER.info("Random Block Placement initialized")
    }
}
