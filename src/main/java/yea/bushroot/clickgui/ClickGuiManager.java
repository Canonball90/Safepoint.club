package yea.bushroot.clickgui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import com.example.examplemod.Utils.particle.ParticleSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
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

	private final ResourceLocation background = new ResourceLocation("borgor.png");
	private final ResourceLocation neko = new ResourceLocation("uwu.png");
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		RenderUtils.drawGradient(0, 0, width, height, new Color(255, 255, 255, 26).getRGB(), new Color(18, 2, 243, 160).getRGB());

		this.particleSystem.tick(20);
		this.particleSystem.render();

		mc.renderEngine.bindTexture(background);
		drawScaledCustomSizeModalRect(5, Minecraft.getMinecraft().currentScreen.height - 50, 0, 0, 50, 50, 50, 50, 50, 50);

		int divi=4;
		mc.renderEngine.bindTexture(neko);
		drawScaledCustomSizeModalRect(Minecraft.getMinecraft().currentScreen.width-(640/divi), Minecraft.getMinecraft().currentScreen.height - (1024/divi), 0, 0, 640/divi, 1024/divi, 640/divi, 1024/divi, 640/divi, 1024/divi);

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
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

}
