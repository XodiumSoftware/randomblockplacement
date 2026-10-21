package org.xodium

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

/** Registers and handles the random placement toggle key binding. */
internal object KeyBindings {
    /** The registered toggle key binding. */
    lateinit var randomPlaceKey: KeyBinding
        private set

    /** Registers the toggle key binding and its tick handler. */
    fun register() {
        randomPlaceKey = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.randomblockplacement.random_place",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.randomblockplacement",
            ),
        )

        ClientTickEvents.END_CLIENT_TICK.register {
            if (randomPlaceKey.wasPressed()) {
                RandomBlockPlacementClient.onRandomPlaceKeyPressed()
            }
        }
    }
}
