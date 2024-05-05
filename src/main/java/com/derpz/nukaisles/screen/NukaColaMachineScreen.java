package com.derpz.nukaisles.screen;

import com.derpz.nukaisles.NukaIsles;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class NukaColaMachineScreen extends HandledScreen<NukaColaMachineScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(NukaIsles.MOD_ID, "textures/gui/nuka_cola_machine.png");

    public NukaColaMachineScreen(NukaColaMachineScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
       // titleX = 1000;
       // playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionProgram);
        RenderSystem.setShaderColor(1f, 1f,1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x,y, 0, 0, backgroundWidth, backgroundHeight);

        renderFuelBar(context, x, y);
    }

    private void renderFuelBar(DrawContext context, int x, int y) {
        if(handler.isCrafting()) {
            context.drawTexture(TEXTURE,  x + 62, y + 23, 0, 168, handler.getScaledProgress(), 6);
//            blit(pPoseStack, x + 62, y + 23, 0, 168, menu.getStorage(), 6);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
