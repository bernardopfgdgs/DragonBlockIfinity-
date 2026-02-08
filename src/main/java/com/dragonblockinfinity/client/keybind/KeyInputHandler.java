package com.dragonblockinfinity.client.keybind;

import com.dragonblockinfinity.client.gui.screen.MenuIncial;
import com.dragonblockinfinity.util.Constants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class KeyInputHandler {
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getInstance();
            
            // Abre menu de customização
            while (KeyBindings.OPEN_CUSTOMIZATION_MENU.consumeClick()) {
                if (mc.screen == null) {
                    mc.setScreen(new MenuInicial());
                }
            }
            
            }
        }
    }

