package org.xodium

import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.client.KeyMapping.Category
import net.minecraft.resources.Identifier

/** Registers and handles the random placement toggle key binding. */
internal object KeyBindings {
    /** The registered toggle key binding. */
    lateinit var randomPlaceKey: KeyMapping
        private set

    /** Registers the toggle key binding and its tick handler. */
    fun register() {
        randomPlaceKey =
            KeyMappingHelper.registerKeyMapping(
                KeyMapping(
                    "key.randomblockplacement.random_place",
                    InputConstants.Type.KEYBOARD,
                    InputConstants.KEY_R,
                    Category.register(Identifier.fromNamespaceAndPath(RandomBlockPlacement.MOD_ID, "key_category")),
                ),
            )

        ClientTickEvents.END_CLIENT_TICK.register {
            if (randomPlaceKey.consumeClick()) {
                RandomBlockPlacementClient.onRandomPlaceKeyPressed()
            }
        }
    }
}
