package com.dragonblockinfinity;

import com.dragonblockinfinity.util.Constants;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(Constants.MOD_ID)
public class DragonBlockInfinity {
    
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public DragonBlockInfinity() {
        LOGGER.info("Dragon Block Infinity inicializando...");
        
        // Registra no FORGE EVENT BUS (para eventos de gameplay)
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("Dragon Block Infinity carregado!");
    }
    
    // Este vai para MinecraftForge.EVENT_BUS (já registrado acima)
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Servidor iniciando...");
    }
}
