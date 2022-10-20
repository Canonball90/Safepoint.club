package com.example.examplemod.Menu;

import com.example.examplemod.Menu.Tools.changeUser;
import com.example.examplemod.Utils.RenderUtils;
import com.example.examplemod.Utils.particle.ParticleSystem;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import yea.bushroot.clickgui.ClickGuiManager;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TutorialMenu extends GuiScreen {
    private static final ResourceLocation texture = new ResourceLocation("texture.jpg");
    private ParticleSystem particleSystem;

    public TutorialMenu() {
        super();
    }

    @Override
    public void initGui() {
        this.particleSystem = new ParticleSystem(150);
        int i = this.height / 4 + 48;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, i + 72 + 12, 98,
                20, "Options"));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 2, i + 72 + 12, 98,
                20, "Quit"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, i + 72 - 12, 98,
                20, "Change User"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, i + 72 - 12, 98,
                20, "ClickGui"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, i + 72 - 34, 200,
                20, "Multiplayer"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, i + 72 - 58, 200,
                20, "Singleplayer"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        } if (button.id == 1) {
            mc.shutdown();
        } if (button.id == 2) {
            mc.displayGuiScreen(new changeUser());
        } if (button.id == 3) {
            mc.displayGuiScreen(new ClickGuiManager());
        } if (button.id == 4) {
            mc.displayGuiScreen(new GuiMultiplayer(this));
        } if (button.id == 5) {
            mc.displayGuiScreen(new GuiWorldSelection(this));
        }
        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1, 1, 1, 1);
        drawDefaultBackground();
        mc.renderEngine.bindTexture(texture);
        RenderUtils.drawGradient(0, 0, this.width, this.height, new Color(127, 0, 145, 255).getRGB(), new Color(122, 0, 0, 255).getRGB());
        this.particleSystem.tick(20);
        this.particleSystem.render();
        drawLogo.drawString(5, "Safepoint.club", this.width / 10 - this.fontRenderer.getStringWidth("Safepoint.club") / 2,
                this.height / 20, new Color(0x00FF9E).getRGB());

        for (GuiButton guiButton : this.buttonList) {
            guiButton.drawButton(this.mc, mouseX, mouseY, partialTicks);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
