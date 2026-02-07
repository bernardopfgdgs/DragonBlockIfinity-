package com.dragonblockinfinity.client.gui.overlay;

import com.dragonblockinfinity.util.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Registra os overlays customizados do mod
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayRegistry {
    
    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        // Registra o HUD completo do DBC
        event.registerAbove(
            VanillaGuiOverlay.FOOD_LEVEL.id(),
            "dbc_hud",
            new DBCHudOverlay()
        );
    }
}
