package org.xodium

import net.fabricmc.api.ModInitializer
import net.minecraft.resources.Identifier
import org.slf4j.LoggerFactory

/** Main entry point for the Random Block Placement mod. */
internal object RandomBlockPlacement : ModInitializer {
    /** Mod identifier used for logging and Fabric metadata. */
    const val MOD_ID: String = "randomblockplacement"

    private val LOGGER = LoggerFactory.getLogger(MOD_ID)

    /** Creates a namespaced [Identifier] under this mod's ID. */
    fun id(path: String): Identifier = Identifier.fromNamespaceAndPath(MOD_ID, path)

    override fun onInitialize() {
        LOGGER.info("Random Block Placement initialized")
    }
}
