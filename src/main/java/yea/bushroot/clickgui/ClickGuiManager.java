package yea.bushroot.clickgui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import com.example.examplemod.Utils.particle.ParticleSystem;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import yea.bushroot.clickgui.component.Component;
import yea.bushroot.clickgui.component.Frame;


public class ClickGuiManager extends GuiScreen {
	public static ArrayList<Frame> frames;
	private final ParticleSystem particleSystem;
	public static int color;
	
	public ClickGuiManager() {
		this.frames = new ArrayList<Frame>();
		this.particleSystem = new ParticleSystem(150);
		int frameX = 10;
		int frameY = 10;
		for(Module.Category category : Module.Category.values()) {
			Frame frame = new Frame(category);
			frame.setY(frameY);
			frame.setX(frameX);
			frames.add(frame);
			frameX += 130;
		}
	}
	
	@Override
	public void initGui() {
		
	}

	private final ResourceLocation neko = new ResourceLocation("uwu.png");
	private final ResourceLocation neku = new ResourceLocation("owo.png");
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		RenderUtils.drawGradient(0, 0, width, height, new Color(211, 0, 255, 128).getRGB(), new Color(255, 0, 58, 128).getRGB());

		this.particleSystem.tick(20);
		this.particleSystem.render();

		int divi=6;
		if(ExampleMod.instance.settingsManager.getSettingByName("ClickGUI", "uwu").getValBoolean()) {
			mc.renderEngine.bindTexture(neku);
			drawScaledCustomSizeModalRect(Minecraft.getMinecraft().currentScreen.width - (1189 / divi), Minecraft.getMinecraft().currentScreen.height - (1620 / divi), 0, 0, 1189 / divi, 1620 / divi, 1189 / divi, 1620 / divi, 1189 / divi, 1620 / divi);
		}else {
			RenderUtils.drawRect(Minecraft.getMinecraft().currentScreen.width - 960, Minecraft.getMinecraft().currentScreen.height - 60, Minecraft.getMinecraft().currentScreen.width - 700, Minecraft.getMinecraft().currentScreen.height + 40, new Color(25,25,25, 100).getRGB());
			drawHead(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(mc.player.getUniqueID()).getLocationSkin(), Minecraft.getMinecraft().currentScreen.width - 950, Minecraft.getMinecraft().currentScreen.height - 50);
			mc.fontRenderer.drawString("Kills: " + getPlayerKills(), Minecraft.getMinecraft().currentScreen.width - 900, Minecraft.getMinecraft().currentScreen.height - 50, new Color(255, 255, 255, 255).getRGB());
			mc.fontRenderer.drawString("Deaths: " + getPlayerDeaths(), Minecraft.getMinecraft().currentScreen.width - 900, Minecraft.getMinecraft().currentScreen.height - 40, new Color(255, 255, 255, 255).getRGB());
			mc.fontRenderer.drawString("Time: " + onlineTime(), Minecraft.getMinecraft().currentScreen.width - 900, Minecraft.getMinecraft().currentScreen.height - 30, new Color(255, 255, 255, 255).getRGB());
		}
		for(Frame frame : frames) {
			frame.renderFrame(this.fontRenderer);
			frame.updatePosition(mouseX, mouseY);
			for(Component comp : frame.getComponents()) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
	}
	
	@Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
		for(Frame frame : frames) {
			if(frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
				frame.setDrag(true);
				frame.dragX = mouseX - frame.getX();
				frame.dragY = mouseY - frame.getY();
			}
			if(frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
				frame.setOpen(!frame.isOpen());
			}
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.mouseClicked(mouseX, mouseY, mouseButton);
					}
				}
			}
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		for(Frame frame : frames) {
			if(frame.isOpen() && keyCode != 1) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.keyTyped(typedChar, keyCode);
					}
				}
			}
		}
		if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        }
	}

	
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
		for(Frame frame : frames) {
			frame.setDrag(false);
		}
		for(Frame frame : frames) {
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.mouseReleased(mouseX, mouseY, state);
					}
				}
			}
		}
	}

	public void drawHead(ResourceLocation skin, int width, int height) {
		GL11.glColor4f(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(skin);
		Gui.drawScaledCustomSizeModalRect(width, height, 8, 8, 8, 8, 37, 37, 64, 64);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	//Get Player kills
	public static int getPlayerKills() {
		return Minecraft.getMinecraft().player.getStatFileWriter().readStat(net.minecraft.stats.StatList.PLAYER_KILLS);
	}

	//Get Player Deaths
	public static int getPlayerDeaths() {
		return Minecraft.getMinecraft().player.getStatFileWriter().readStat(net.minecraft.stats.StatList.DEATHS);
	}

	public static String onlineTime(){
		int timeSeconds = (int) (ExampleMod.instance.time / 20);
		int timeMinutes = timeSeconds / 60;
		int timeHours = timeMinutes / 60;
		timeSeconds -= timeMinutes * 60;
		return ChatFormatting.GRAY.toString() + timeHours + ChatFormatting.RESET + " Hours, " + ChatFormatting.GRAY + timeMinutes + ChatFormatting.RESET + " Minutes, " + ChatFormatting.GRAY + timeSeconds + ChatFormatting.RESET + " Seconds.";
	}
}
