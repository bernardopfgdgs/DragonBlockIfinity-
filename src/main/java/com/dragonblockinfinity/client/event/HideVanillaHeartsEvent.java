package com.dragonblockinfinity.client.event;

import com.dragonblockinfinity.util.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Esconde a barra de vida vanilla após o jogador completar a criação de personagem
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class HideVanillaHeartsEvent {
    
    @SubscribeEvent
    public static void onRenderHealthBar(RenderGuiOverlayEvent.Pre event) {
        // TODO: Verificar se jogador completou criação
        // if (!PlayerDataManager.hasCompletedCharacterCreation(event.getPlayer())) return;
        
        // Cancela renderização dos corações
        if (event.getOverlay().id().equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
            event.setCanceled(true);
        }
    }
}
