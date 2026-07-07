package com.vivids.mod.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.vivids.mod.VividsMod;
import com.vivids.mod.menu.VividBackpackMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * Pantalla de la Mochila Vivid.
 * ESTADO: FUNCIONAL - reutiliza la textura generic_54/27 de vanilla
 * (contenedor generico) para no depender de una textura de GUI custom
 * que no puedo dibujar a mano; puedes reemplazarla despues por tu
 * propio arte de inventario.
 */
public class VividBackpackScreen extends AbstractContainerScreen<VividBackpackMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("minecraft", "textures/gui/container/generic_54.png");

    public VividBackpackScreen(VividBackpackMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageHeight = 168;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}
