package com.marcusstarwarsmod.screen;

//import com.marcusstarwarsmod.MarcusStarWarsMod;
//import com.mojang.blaze3d.systems.RenderSystem;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.screen.ingame.HandledScreen;
//import net.minecraft.client.render.GameRenderer;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.text.Text;
//import net.minecraft.util.Identifier;

//public class HiltScreen extends HandledScreen<HiltScreenHandler> {

//    private static final Identifier KNAPPING = new Identifier(MarcusStarWarsMod.MOD_ID, "textures/gui/container/knapping.png");
//
//    public HiltScreen(HiltScreenHandler handler, PlayerInventory inventory, Text title) {
//        super(handler, inventory, title);
//        this.backgroundWidth = 256;
//        this.backgroundHeight = 256;
//        this.playerInventoryTitleY = this.backgroundHeight - 120;
//        this.playerInventoryTitleX = this.backgroundWidth - 220;
//    }
//
//    @Override
//    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
//        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//        RenderSystem.setShaderColor(1f, 1f,  1f, 1f);
//        RenderSystem.setShaderTexture(0, KNAPPING);
//        int x = (this.width - this.backgroundWidth) / 2;
//        int y = (this.height - this.backgroundHeight) / 2;
//
//        context.drawTexture(KNAPPING, x, y, 0, 0, backgroundWidth, backgroundHeight);
//    }
//
//    @Override
//    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//        return super.mouseClicked(mouseX, mouseY, button);
//    }
//
//    @Override
//    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
//        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
//    }
//
//    @Override
//    public boolean mouseReleased(double mouseX, double mouseY, int button) {
//        return super.mouseReleased(mouseX, mouseY, button);
//    }
//    @Override
//    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
//        renderBackground(context);
//        super.render(context, mouseX, mouseY, delta);
//        drawMouseoverTooltip(context, mouseX, mouseY);
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
//    }
//}
