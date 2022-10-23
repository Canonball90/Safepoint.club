package yea.bushroot.clickgui.component.components.sub;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.UI.ui;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import yea.bushroot.clickgui.Setting;
import yea.bushroot.clickgui.component.Component;
import yea.bushroot.clickgui.component.components.Button;

public class Slider extends Component {

	private boolean hovered;

	private Setting set;
	private Button parent;
	private int offset;
	private int x;
	private int y;
	private boolean dragging = false;

	private double renderWidth;
	
	public Slider(Setting value, Button button, int offset) {
		this.set = value;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}
	int eee=14;
	int eeeY=3;
	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + parent.parent.getWidth(), parent.parent.getY() + offset + 12, this.hovered ? 0xFF222222 : 0xFF111111);
		final int drag = (int)(this.set.getValDouble() / this.set.getMax() * (this.parent.parent.getWidth()));
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX(), parent.parent.getY() + offset + 12, 0xFF111111);
		Gui.drawRect(
				parent.parent.getX()+eee,
				parent.parent.getY() + offset+eeeY,
				parent.parent.getX() + 88+eee,
				parent.parent.getY() + offset + 6+eeeY,
				Color.GRAY.darker().darker().darker().getRGB());

		Gui.drawRect(parent.parent.getX()+eee,
				parent.parent.getY() + offset+eeeY,
				parent.parent.getX() + (int) renderWidth+eee,
				parent.parent.getY() + offset + 6+eeeY,
				ExampleMod.instance.settingsManager.getSettingByName("ClickGUI", "Rainbow").getValBoolean() ? ui.rainbow(300) : new Color(0x36D003).hashCode());

//		GL11.glPushMatrix();
//		GL11.glScalef(0.7f,0.7f, 0.7f);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getName() + ": " + this.set.getValDouble() , parent.parent.getX() + 18, parent.parent.getY() + offset + 2, 0xfff2f2f2);
//		GL11.glPopMatrix();
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButtonD(mouseX, mouseY) || isMouseOnButtonI(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
		
		double diff = Math.min(88, Math.max(0, mouseX - this.x));

		double min = set.getMin();
		double max = set.getMax();
		
		renderWidth = (88) * (set.getValDouble() - min) / (max - min);
		
		if (dragging) {
			if (diff == 0) {
				set.setValDouble(set.getMin());
			}
			else {
				double newValue = roundToPlace(((diff / 88) * (max - min) + min), 2);
				set.setValDouble(newValue);
			}
		}
	}
	
	private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButtonD(mouseX+eee, mouseY) && button == 0 && this.parent.open) {
			dragging = true;
		}
		if(isMouseOnButtonI(mouseX+eee, mouseY) && button == 0 && this.parent.open) {
			dragging = true;
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		dragging = false;
	}
	
	public boolean isMouseOnButtonD(int x, int y) {
		return x > this.x && x < this.x + (parent.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 12;
	}
	
	public boolean isMouseOnButtonI(int x, int y) {
		return x > this.x + parent.parent.getWidth() / 2 && x < this.x + parent.parent.getWidth() && y > this.y && y < this.y + 12;
	}
}
