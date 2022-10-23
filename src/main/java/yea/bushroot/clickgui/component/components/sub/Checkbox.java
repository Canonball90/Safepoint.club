package yea.bushroot.clickgui.component.components.sub;

import java.awt.Color;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.UI.ui;
import com.example.examplemod.Utils.animation.Animation;
import com.example.examplemod.Utils.animation.ColourAnimation;
import com.example.examplemod.Utils.animation.Easing;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import yea.bushroot.clickgui.Setting;
import yea.bushroot.clickgui.component.Component;
import yea.bushroot.clickgui.component.components.Button;

public class Checkbox extends Component {

	private boolean hovered;
	private Setting op;
	private Button parent;
	private int offset;
	private int x;
	private int y;
	
	public Checkbox(Setting option, Button button, int offset) {
		this.op = option;
		this.parent = button;
		this.x = button.parent.getX() + button.parent.getWidth();
		this.y = button.parent.getY() + button.offset;
		this.offset = offset;
	}

	ColourAnimation state1 = new ColourAnimation(Color.GREEN, Color.RED, 200f, false, Easing.LINEAR);
	ColourAnimation state = new ColourAnimation(Color.RED, Color.GREEN, 200f, false, Easing.LINEAR);

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.parent.getX(), parent.parent.getY() + offset, parent.parent.getX() + (parent.parent.getWidth() * 1), parent.parent.getY() + offset + 12, this.hovered ? 0xFF222222 : 0xFF111111);

//		GL11.glPushMatrix();
//		GL11.glScalef(0.5f, 0.5f, 0.5f);
//		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.op.getName(), (parent.parent.getX() + 10 + 4) * 2 + 5, (parent.parent.getY() + offset + 2) * 2 + 4, -1);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.op.getName(), parent.parent.getX() + 18, parent.parent.getY() + offset + 2, 0xfff2f2f2);
//		GL11.glPopMatrix();

		if (this.op.getValBoolean()) {
			Color colour = state.getColour();
			state.setState(true);
			state1.setState(false);
			Gui.drawRect(parent.parent.getX() + 4 + 4, parent.parent.getY() + offset + 4, parent.parent.getX() + 8 + 4, parent.parent.getY() + offset + 8, ExampleMod.instance.settingsManager.getSettingByName("ClickGUI", "Rainbow").getValBoolean() ? ui.rainbow(300) : new Color(colour.getRed(), colour.getGreen(), colour.getBlue()).getRGB());
		} else {
			Color colour = state1.getColour();
			state.setState(false);
			state1.setState(true);
			Gui.drawRect(parent.parent.getX() + 4 + 4, parent.parent.getY() + offset + 4, parent.parent.getX() + 8 + 4, parent.parent.getY() + offset + 8, new Color(colour.getRed(), colour.getGreen(), colour.getBlue()).getRGB());
//			Gui.drawRect(parent.parent.getX() + 5 + 4, parent.parent.getY() + offset + 5, parent.parent.getX() + 7 + 4, parent.parent.getY() + offset + 7, new Color(0xffff2222).hashCode());
		}
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
	}
	
	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.hovered = isMouseOnButton(mouseX, mouseY);
		this.y = parent.parent.getY() + offset;
		this.x = parent.parent.getX();
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
			this.op.setValBoolean(!op.getValBoolean());;
		}
	}
	
	public boolean isMouseOnButton(int x, int y) {
		if(x > this.x && x < this.x + parent.parent.getWidth() && y > this.y && y < this.y + 12) {
			return true;
		}
		return false;
	}
}
