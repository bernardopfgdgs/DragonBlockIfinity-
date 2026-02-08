package com.dragonblockinfinity.client.keybind;

import com.dragonblockinfinity.util.Constants;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    
    public static final String CATEGORY = "key.categories." + Constants.MOD_ID;
    
    public static final KeyMapping OPEN_CUSTOMIZATION_MENU = new KeyMapping(
        "key." + Constants.MOD_ID + ".open_customization",
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_K,  // Tecla K
        CATEGORY
    );
    
    public static final KeyMapping OPEN_STATUS_MENU = new KeyMapping(
        "key." + Constants.MOD_ID + ".open_status",
        KeyConflictContext.IN_GAME,
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_C,  // Tecla C
        CATEGORY
    );
}
