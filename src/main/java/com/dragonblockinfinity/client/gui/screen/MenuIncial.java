package com.dragonblockinfinity.client.gui.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.GuiGraphics; // Essencial para desenhar tudo na 1.20.1
import net.minecraft.client.gui.components.Button; // Para os botões de raça/cor/etc
import net.minecraft.network.chat.Component; // Para textos traduzíveis ou literais
import net.minecraft.resources.ResourceLocation; // Para carregar sua textura de DBZ (.png)
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.GameRenderer;


public class MenuIncial extends Screen {
    private static final ResourceLocation menu = new ResourceLocation("dragonblockinfinity", "textures/gui/menus/menu_base.png");
    private static final ResourceLocation icons = new ResourceLocation("dragonblockinfinity", "textures/gui/buttoes/icons_btn");
    private int menuWidth = 256;
    private int menuHeight = 256;
    private int iconsWidth = 1024;
    private int iconsHeight = 1024;


     @Override
     public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
         super.render(guiGraphics, mouseX, mouseY, partialTick);

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, menu);

            int x = (this.width - menuWidth) / 2;
            int y = (this.height - menuHeight) / 2;

            guiGraphics.blit(menu, x, y, 0, 0, menuWidth, menuHeight);

            guiGraphics.blit(
            icons,   // textura atlas
            x + 200, y + 100,    // posição na tela
            0, 0,    // posição dentro da textura
            128, 128 // tamanho do recorte
        );
            
     }

}