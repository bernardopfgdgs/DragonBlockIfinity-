package com.dragonblockinfinity.client.gui.overlay;

import com.dragonblockinfinity.util.Constants;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

/**
 * HUD completo do Dragon Block Infinity
 * 
 * Renderiza:
 * - Barra de Ki (topo esquerdo)
 * - Barra de Vida + Stamina (meio direito)
 * - Hotbar de Ki Attacks (acima da vida/stamina)
 * 
 * Só ativa após o jogador completar o MenuInicial (criação de personagem)
 */
public class DBCHudOverlay implements IGuiOverlay {
    
    // ========== TEXTURAS ==========
    private static final ResourceLocation HUD_ATLAS = 
        new ResourceLocation(Constants.MOD_ID, "textures/gui/huds/icons_hud.png");
    
    // ========== COORDENADAS DO ATLAS (icons_hud.png) ==========
    
    // Barra de Ki Vazia
    private static final int KI_BAR_EMPTY_X = 1;
    private static final int KI_BAR_EMPTY_Y = 3;
    private static final int KI_BAR_EMPTY_WIDTH = 181;
    private static final int KI_BAR_EMPTY_HEIGHT = 7;
    
    // Vida + Stamina Preenchidas (Vermelho + Amarelo)
    private static final int HEALTH_STAMINA_FULL_X = 1;
    private static final int HEALTH_STAMINA_FULL_Y = 11;
    private static final int HEALTH_STAMINA_FULL_WIDTH = 139;
    private static final int HEALTH_STAMINA_FULL_HEIGHT = 183;
    
    // Vida + Stamina Vazias (Azul escuro)
    private static final int HEALTH_STAMINA_EMPTY_X = 142;
    private static final int HEALTH_STAMINA_EMPTY_Y = 11;
    private static final int HEALTH_STAMINA_EMPTY_WIDTH = 139;
    private static final int HEALTH_STAMINA_EMPTY_HEIGHT = 183;
    
    // Hotbar de Ki Attacks
    private static final int KI_HOTBAR_X = 1;
    private static final int KI_HOTBAR_Y = 179;
    private static final int KI_HOTBAR_WIDTH = 60;
    private static final int KI_HOTBAR_HEIGHT = 720;
    private static final int KI_HOTBAR_SLOTS = 12;
    private static final int KI_HOTBAR_SLOT_HEIGHT = KI_HOTBAR_HEIGHT / KI_HOTBAR_SLOTS; // 60px cada
    
    // ========== TAMANHOS DE RENDERIZAÇÃO ==========
    
    // Barra de Ki (renderiza no tamanho original)
    private static final int KI_BAR_RENDER_WIDTH = 181;
    private static final int KI_BAR_RENDER_HEIGHT = 7;
    
    // Vida + Stamina (renderiza menor - escala 50%)
    private static final int HEALTH_STAMINA_RENDER_WIDTH = 70;   // 139/2
    private static final int HEALTH_STAMINA_RENDER_HEIGHT = 92;  // 183/2
    private static final int SINGLE_BAR_WIDTH = 35; // Metade (vida OU stamina)
    
    // Hotbar de Ki (renderiza menor - escala 50%)
    private static final int KI_HOTBAR_RENDER_WIDTH = 30;   // 60/2
    private static final int KI_HOTBAR_RENDER_HEIGHT = 360; // 720/2
    private static final int KI_HOTBAR_RENDER_SLOT_HEIGHT = 30; // 60/2
    
    // ========== ESTADO DO JOGADOR ==========
    private final Minecraft mc;
    private int selectedAttackSlot = 0;
    
    public DBCHudOverlay() {
        this.mc = Minecraft.getInstance();
    }
    
    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        Player player = mc.player;
        if (player == null || mc.options.hideGui) return;
        
        // TODO: Verificar se jogador completou criação de personagem
        // if (!PlayerDataManager.hasCompletedCharacterCreation(player)) return;
        
        // Renderiza HUD
        renderKiBar(guiGraphics, screenWidth, screenHeight, player);
        renderHealthStaminaBars(guiGraphics, screenWidth, screenHeight, player);
        renderKiAttacksHotbar(guiGraphics, screenWidth, screenHeight);
    }
    
    /**
     * Renderiza a barra de Ki (topo esquerdo)
     */
    private void renderKiBar(GuiGraphics guiGraphics, int screenWidth, int screenHeight, Player player) {
        Font font = mc.font;
        
        // TODO: Obter Ki real do jogador via capability
        int currentKi = 5000;
        int maxKi = 10000;
        
        // Posição: topo esquerdo
        int barX = 10;
        int barY = 10;
        
        // Calcula largura do preenchimento
        float fillPercentage = (float) currentKi / (float) Math.max(maxKi, 1);
        int fillWidth = (int) (KI_BAR_RENDER_WIDTH * fillPercentage);
        fillWidth = Math.min(fillWidth, KI_BAR_RENDER_WIDTH);
        
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        // Renderiza barra vazia (contorno)
        guiGraphics.blit(HUD_ATLAS,
            barX, barY,
            KI_BAR_EMPTY_X, KI_BAR_EMPTY_Y,
            KI_BAR_RENDER_WIDTH, KI_BAR_RENDER_HEIGHT,
            1024, 1024  // Tamanho total do atlas (ajuste se necessário)
        );
        
        // Renderiza preenchimento (cor ciano/azul via shader)
        if (fillWidth > 0) {
            RenderSystem.setShaderColor(0.0f, 1.0f, 1.0f, 1.0f); // Cor ciano
            guiGraphics.fill(barX + 2, barY + 2, barX + 2 + fillWidth - 4, barY + KI_BAR_RENDER_HEIGHT - 2, 0xFF00FFFF);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
        
        // Texto do Ki
        String kiText = currentKi + " / " + maxKi;
        int textX = barX + KI_BAR_RENDER_WIDTH / 2 - font.width(kiText) / 2;
        int textY = barY + KI_BAR_RENDER_HEIGHT + 2;
        
        renderTextWithOutline(guiGraphics, font, kiText, textX, textY, 0x00FFFF);
    }
    
    /**
     * Renderiza barras de Vida + Stamina (meio direito)
     */
    private void renderHealthStaminaBars(GuiGraphics guiGraphics, int screenWidth, int screenHeight, Player player) {
        Font font = mc.font;
        
        // TODO: Obter vida e stamina reais via capability
        int currentHealth = 80;
        int maxHealth = 100;
        int currentStamina = 60;
        int maxStamina = 100;
        
        // Posição: meio direito da tela
        int barX = screenWidth - HEALTH_STAMINA_RENDER_WIDTH - 10;
        int barY = screenHeight / 2 - HEALTH_STAMINA_RENDER_HEIGHT / 2;
        
        // Renderiza barra de VIDA (metade esquerda - vermelha)
        renderHealthBar(guiGraphics, barX, barY, currentHealth, maxHealth);
        
        // Renderiza barra de STAMINA (metade direita - amarela)
        renderStaminaBar(guiGraphics, barX + SINGLE_BAR_WIDTH, barY, currentStamina, maxStamina);
        
        // Texto da Vida
        String healthText = String.valueOf(currentHealth);
        renderTextWithOutline(guiGraphics, font, healthText, 
            barX + SINGLE_BAR_WIDTH / 2 - font.width(healthText) / 2, 
            barY + HEALTH_STAMINA_RENDER_HEIGHT + 2, 
            0xFF5555);
        
        // Texto da Stamina
        String staminaText = String.valueOf(currentStamina);
        renderTextWithOutline(guiGraphics, font, staminaText, 
            barX + SINGLE_BAR_WIDTH + SINGLE_BAR_WIDTH / 2 - font.width(staminaText) / 2, 
            barY + HEALTH_STAMINA_RENDER_HEIGHT + 2, 
            0xFFFF55);
    }
    
    /**
     * Renderiza barra de vida (metade esquerda)
     */
    private void renderHealthBar(GuiGraphics guiGraphics, int x, int y, int current, int max) {
        float fillPercentage = (float) current / (float) Math.max(max, 1);
        int fillHeight = (int) (HEALTH_STAMINA_RENDER_HEIGHT * fillPercentage);
        
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        // Renderiza vazio (azul escuro) - metade esquerda
        guiGraphics.blit(HUD_ATLAS,
            x, y,
            HEALTH_STAMINA_EMPTY_X, HEALTH_STAMINA_EMPTY_Y,
            SINGLE_BAR_WIDTH, HEALTH_STAMINA_RENDER_HEIGHT,
            1024, 1024
        );
        
        // Renderiza preenchimento (vermelho) - de baixo para cima
        if (fillHeight > 0) {
            int fillY = y + (HEALTH_STAMINA_RENDER_HEIGHT - fillHeight);
            guiGraphics.blit(HUD_ATLAS,
                x, fillY,
                HEALTH_STAMINA_FULL_X, HEALTH_STAMINA_FULL_Y + (HEALTH_STAMINA_FULL_HEIGHT - fillHeight * 2),
                SINGLE_BAR_WIDTH, fillHeight,
                1024, 1024
            );
        }
    }
    
    /**
     * Renderiza barra de stamina (metade direita)
     */
    private void renderStaminaBar(GuiGraphics guiGraphics, int x, int y, int current, int max) {
        float fillPercentage = (float) current / (float) Math.max(max, 1);
        int fillHeight = (int) (HEALTH_STAMINA_RENDER_HEIGHT * fillPercentage);
        
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        // Renderiza vazio (azul escuro) - metade direita
        guiGraphics.blit(HUD_ATLAS,
            x, y,
            HEALTH_STAMINA_EMPTY_X + HEALTH_STAMINA_EMPTY_WIDTH / 2, HEALTH_STAMINA_EMPTY_Y,
            SINGLE_BAR_WIDTH, HEALTH_STAMINA_RENDER_HEIGHT,
            1024, 1024
        );
        
        // Renderiza preenchimento (amarelo) - de baixo para cima
        if (fillHeight > 0) {
            int fillY = y + (HEALTH_STAMINA_RENDER_HEIGHT - fillHeight);
            guiGraphics.blit(HUD_ATLAS,
                x, fillY,
                HEALTH_STAMINA_FULL_X + HEALTH_STAMINA_FULL_WIDTH / 2, 
                HEALTH_STAMINA_FULL_Y + (HEALTH_STAMINA_FULL_HEIGHT - fillHeight * 2),
                SINGLE_BAR_WIDTH, fillHeight,
                1024, 1024
            );
        }
    }
    
    /**
     * Renderiza hotbar de ataques de Ki (acima da vida/stamina)
     */
    private void renderKiAttacksHotbar(GuiGraphics guiGraphics, int screenWidth, int screenHeight) {
        // Posição: um pouco acima das barras de vida/stamina, no lado direito
        int hotbarX = screenWidth - KI_HOTBAR_RENDER_WIDTH - 10;
        int hotbarY = screenHeight / 2 - HEALTH_STAMINA_RENDER_HEIGHT / 2 - KI_HOTBAR_RENDER_HEIGHT - 10;
        
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        
        // Renderiza apenas os 6 primeiros slots (ataques próprios)
        for (int i = 0; i < 6; i++) {
            int slotY = hotbarY + (i * KI_HOTBAR_RENDER_SLOT_HEIGHT);
            int textureSlotY = KI_HOTBAR_Y + (i * KI_HOTBAR_SLOT_HEIGHT);
            
            guiGraphics.blit(HUD_ATLAS,
                hotbarX, slotY,
                KI_HOTBAR_X, textureSlotY,
                KI_HOTBAR_RENDER_WIDTH, KI_HOTBAR_RENDER_SLOT_HEIGHT,
                1024, 1024
            );
            
            // Indicador de seleção
            if (i == selectedAttackSlot) {
                renderSelectionIndicator(guiGraphics, hotbarX, slotY);
            }
        }
    }
    
    /**
     * Renderiza indicador de slot selecionado
     */
    private void renderSelectionIndicator(GuiGraphics guiGraphics, int x, int y) {
        long time = System.currentTimeMillis();
        float pulse = (float) Math.sin(time / 300.0) * 0.3f + 0.7f;
        
        int color = (int) (pulse * 255) << 24 | 0xFFFFFF;
        
        // Borda branca pulsante
        guiGraphics.fill(x - 2, y - 2, x + KI_HOTBAR_RENDER_WIDTH + 2, y, color);
        guiGraphics.fill(x - 2, y + KI_HOTBAR_RENDER_SLOT_HEIGHT, x + KI_HOTBAR_RENDER_WIDTH + 2, y + KI_HOTBAR_RENDER_SLOT_HEIGHT + 2, color);
        guiGraphics.fill(x - 2, y, x, y + KI_HOTBAR_RENDER_SLOT_HEIGHT, color);
        guiGraphics.fill(x + KI_HOTBAR_RENDER_WIDTH, y, x + KI_HOTBAR_RENDER_WIDTH + 2, y + KI_HOTBAR_RENDER_SLOT_HEIGHT, color);
    }
    
    /**
     * Renderiza texto com contorno preto
     */
    private void renderTextWithOutline(GuiGraphics guiGraphics, Font font, String text, int x, int y, int color) {
        // Contorno preto
        guiGraphics.drawString(font, text, x + 1, y, 0x000000, false);
        guiGraphics.drawString(font, text, x - 1, y, 0x000000, false);
        guiGraphics.drawString(font, text, x, y + 1, 0x000000, false);
        guiGraphics.drawString(font, text, x, y - 1, 0x000000, false);
        
        // Texto principal
        guiGraphics.drawString(font, text, x, y, color, false);
    }
    
    /**
     * Muda o slot de ataque selecionado
     */
    public void selectAttackSlot(int slot) {
        if (slot >= 0 && slot < 6) {
            this.selectedAttackSlot = slot;
        }
    }
}
