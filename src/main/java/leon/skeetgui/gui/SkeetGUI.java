package leon.skeetgui.gui;
import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import font.FontUtils;
import leon.skeetgui.gui.comps.CheckBox;
import leon.skeetgui.gui.comps.Combo;
import leon.skeetgui.gui.comps.KeyBinds;
import leon.skeetgui.gui.comps.Slider;
import leon.skeetgui.utils.Quad;
import leon.skeetgui.utils.SkeetUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import yea.bushroot.clickgui.Setting;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SkeetGUI extends GuiScreen {
    // the key to open the gui
    public static int GUI_KEY = Keyboard.KEY_RSHIFT;

    // key to close the gui
    public static int GUI_KEY_CLOSE = Keyboard.KEY_ESCAPE;

    // the background of the gui - args: BLUR, DARKEN, NONE, BOTH
    public static String BACKGROUND = "BLUR";

    // pause the game when opening the gui?
    public static boolean PAUSE_GAME = false;

    public double posX, posY, width, height, dragX, dragY;
    public boolean dragging;
    public Module.Category selectedCategory;

    private Module selectedModule;
    public int modeIndex;

    public ArrayList<Comp> comps = new ArrayList<>();

    public SkeetGUI() {
        MinecraftForge.EVENT_BUS.register(this);
        posX = 20;
        posY = 20;
        width = posX + 150 * 2;
        height = height + 200;
        selectedCategory = Module.Category.COMBAT;
    }

    private final ResourceLocation neku = new ResourceLocation("owo.png");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        SkeetUtils.renderSkeetBox(new Quad(10, 10, 400, 400));
        FontUtils.normal.drawString("Safepoint.club", 20, 388, -1);

        int offset = 0;
        for (Module.Category category : Module.Category.values()) {
            RenderUtils.drawRect((int) posX, (int) (posY + 1 + offset), (int) (posX + 60), (int) (posY + 45 + offset), category.equals(selectedCategory) ? new Color(55, 255, 0).getRGB() : new Color(28, 28, 28).getRGB());
            Minecraft.getMinecraft().fontRenderer.drawString(category.name(), (int) posX + 2, (int) (posY + 20) + offset, new Color(170, 170, 170).getRGB());
        }
        offset = 0;
        for (Module m : Client.getModulesInCategory(selectedCategory)) {
            RenderUtils.drawRect((int) (posX + 65), (int) (posY + 1 + offset), (int) (posX + 135), (int) (posY + 15 + offset),m.toggled ? new Color(55,255,0).getRGB() : new Color(28,28,28).getRGB());
            Minecraft.getMinecraft().fontRenderer.drawString(m.getName(),(int)posX + 67, (int)(posY + 5) + offset, new Color(170,170,170).getRGB());
            offset += 15;
        }

        for (Comp comp : comps) {
            comp.drawScreen(mouseX, mouseY);
        }
        int divi=6;
        if(ExampleMod.instance.settingsManager.getSettingByName("ClickGUI", "uwu").getValBoolean()) {
            mc.renderEngine.bindTexture(neku);
            drawScaledCustomSizeModalRect(Minecraft.getMinecraft().currentScreen.width - (1189 / divi), Minecraft.getMinecraft().currentScreen.height - (1620 / divi), 0, 0, 1189 / divi, 1620 / divi, 1189 / divi, 1620 / divi, 1189 / divi, 1620 / divi);
        }

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (Comp comp : comps) {
            comp.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, posX, posY - 10, width, posY) && mouseButton == 0) {
            dragging = true;
            dragX = mouseX - posX;
            dragY = mouseY - posY;
        }
        int offset = 0;
        for (Module.Category category : Module.Category.values()) {
            if (isInside(mouseX, mouseY,posX,posY + 1 + offset,posX + 60,posY + 45 + offset) && mouseButton == 0) {
                selectedCategory = category;
            }
            offset += 50;
        }
        offset = 0;
        for (Module m : Client.getModulesInCategory(selectedCategory)) {
            if (isInside(mouseX, mouseY,posX + 65,posY + 1 + offset,posX + 125,posY + 15 + offset)) {
                if (mouseButton == 0) {
                    m.toggle();
                }
                if (mouseButton == 1) {
                    int sOffset = 3;
                    comps.clear();
                    if (ExampleMod.instance.settingsManager.getSettingsByMod(m) != null)
                        for (Setting setting : ExampleMod.instance.settingsManager.getSettingsByMod(m)) {
                            selectedModule = m;
                            if (setting.isCombo()) {
                                comps.add(new Combo(275, sOffset, this, selectedModule, setting));
                                sOffset += 15;
                            }
                            if (setting.isCheck()) {
                                comps.add(new CheckBox(275, sOffset, this, selectedModule, setting));
                                sOffset += 15;
                            }
                            if (setting.isSlider()) {
                                comps.add(new Slider(275, sOffset, this, selectedModule, setting));
                                sOffset += 25;
                            }
                            comps.add(new KeyBinds(275, sOffset, this, selectedModule, setting));
                        }
                }
            }
            offset += 15;
        }
        for (Comp comp : comps) {
            comp.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = false;
        for (Comp comp : comps) {
            comp.mouseReleased(mouseX, mouseY, state);
        }
    }

    public boolean isInside(int mouseX, int mouseY, double x, double y, double x2, double y2) {
        return (mouseX > x && mouseX < x2) && (mouseY > y && mouseY < y2);
    }

    @Override
    public void onGuiClosed() {
        switch (BACKGROUND) {
            case "BOTH":
            case "BLUR": {
                mc.entityRenderer.stopUseShader();
            }
        }
    }

    public void onGuiOpened() {
        switch (BACKGROUND) {
            case "BOTH":
            case "BLUR": {
                mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
            }
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return PAUSE_GAME;
    }
}
