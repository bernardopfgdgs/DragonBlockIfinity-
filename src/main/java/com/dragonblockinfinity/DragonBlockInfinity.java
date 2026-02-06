package com.dragonblockinfinity;

import com.dragonblockinfinity.util.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Constants.MOD_ID)
public class DragonBlockInfinity {
    public static final DragonBlockInfinity INSTANCE = new DragonBlockInfinity();

    public DragonBlockInfinity() {
        // Registro de eventos
        Mod.EventBusSubscriber.Bus.MOD.bus().get().register(this);
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {
        // Configuração comum
    }

    @SubscribeEvent
    public void onClientSetup(FMLClientSetupEvent event) {
        // Configuração do cliente
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Configuração do servidor
    }
}
