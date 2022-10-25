package canon.ball.rewriteGUI;

import java.io.IOException;
import java.util.ArrayList;

import canon.ball.rewriteGUI.component.Component;
import canon.ball.rewriteGUI.component.Frame;
import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ClickGui extends GuiScreen {

	public static ArrayList<Frame> frames;
	public static int color = -1;
	
	public ClickGui() {
		frames = new ArrayList<>();
		int frameX = 5;
		for(Module.Category category : Module.Category.values()) {
			Frame frame = new Frame(category);
			frame.setX(frameX);
			frames.add(frame);
			frameX += frame.getWidth() + 5;
		}
	}
	
	@Override
	public void initGui() {
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		for(Frame frame : frames) {
			frame.renderFrame(this.fontRenderer);
			for(Component comp : frame.getComponents()) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
		     ScaledResolution s = new ScaledResolution(mc);
	}

	@Override
	protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);

		for (Frame frame : frames) {
			if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
				frame.setDrag(true);
				frame.dragX = mouseX - frame.getX();
				frame.dragY = mouseY - frame.getY();
			}
			if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
				frame.setOpen(!frame.isOpen());
			}
			if (frame.isOpen()) {
				if (!frame.getComponents().isEmpty()) {
					for (Component component : frame.getComponents()) {
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
