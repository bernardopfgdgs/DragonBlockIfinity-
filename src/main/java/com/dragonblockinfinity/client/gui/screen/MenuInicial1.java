package com.dragonblockinfinity.client.gui.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;

public class MenuInicial1 extends Screen {

    private static final ResourceLocation MENU_TEXTURE = new ResourceLocation("dragonblockinfinity", "textures/gui/menus/menu_base.png");
    private static final int MENU_WIDTH = 256;
    private static final int MENU_HEIGHT = 200;

    private int currentRaceIndex = 0;
    private int currentHairIndex = 0;
    private int currentEyeIndex = 0;

    private static final String[] RACES = {"Saiyan", "Namekian", "Human", "Majin"};
    private static final String[] HAIRS = {"Hair1", "Hair2", "Hair3"};
    private static final String[] EYES = {"Eye1", "Eye2", "Eye3"};

    public MenuInicial1() {
        super(Component.literal("Menu Inicial"));
    }

    @Override
    protected void init() {
        int centerX = (this.width - MENU_WIDTH) / 2;
        int centerY = (this.height - MENU_HEIGHT) / 2;

        // Race buttons
        this.addRenderableWidget(Button.builder(Component.literal("<"), b -> {
            currentRaceIndex = (currentRaceIndex - 1 + RACES.length) % RACES.length;
        }).pos(centerX + 200, centerY + 20).size(20, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal(">"), b -> {
            currentRaceIndex = (currentRaceIndex + 1) % RACES.length;
        }).pos(centerX + 230, centerY + 20).size(20, 20).build());

        // Hair buttons
        this.addRenderableWidget(Button.builder(Component.literal("<"), b -> {
            currentHairIndex = (currentHairIndex - 1 + HAIRS.length) % HAIRS.length;
        }).pos(centerX + 200, centerY + 50).size(20, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal(">"), b -> {
            currentHairIndex = (currentHairIndex + 1) % HAIRS.length;
        }).pos(centerX + 230, centerY + 50).size(20, 20).build());

        // Eye buttons
        this.addRenderableWidget(Button.builder(Component.literal("<"), b -> {
            currentEyeIndex = (currentEyeIndex - 1 + EYES.length) % EYES.length;
        }).pos(centerX + 200, centerY + 80).size(20, 20).build());
        this.addRenderableWidget(Button.builder(Component.literal(">"), b -> {
            currentEyeIndex = (currentEyeIndex + 1) % EYES.length;
        }).pos(centerX + 230, centerY + 80).size(20, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int centerX = (this.width - MENU_WIDTH) / 2;
        int centerY = (this.height - MENU_HEIGHT) / 2;
        RenderSystem.setShaderTexture(0, MENU_TEXTURE);
        guiGraphics.blit(MENU_TEXTURE, centerX, centerY, 0, 0, MENU_WIDTH, MENU_HEIGHT);

        guiGraphics.drawCenteredString(this.font, "Raça: " + RACES[currentRaceIndex], centerX + 215, centerY + 20, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "Cabelo: " + HAIRS[currentHairIndex], centerX + 215, centerY + 50, 0xFFFFFF);
        guiGraphics.drawCenteredString(this.font, "Olhos: " + EYES[currentEyeIndex], centerX + 215, centerY + 80, 0xFFFFFF);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}