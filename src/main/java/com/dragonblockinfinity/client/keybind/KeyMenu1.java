package com.dragonblockinfinity.client.keybind;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import com.dragonblockinfinity.client.gui.screen.MenuInicial1;
import net.minecraft.client.Minecraft;

@Mod.EventBusSubscriber(modid = "dragonblockinfinity", value = Dist.CLIENT)
public class KeyMenu1 {
    public static final KeyMapping CUSTOMIZATION_MENU_KEY = new KeyMapping(
        "key.dragonblockinfinity.customization_menu",
        GLFW.GLFW_KEY_M,
        "key.categories.dragonblockinfinity"
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(CUSTOMIZATION_MENU_KEY);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (CUSTOMIZATION_MENU_KEY.consumeClick()) {
            Minecraft.getInstance().setScreen(new MenuInicial1());
        }
    }
}