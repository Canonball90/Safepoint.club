package canon.ball.rewriteGUI.component.components;

import java.awt.Color;
import java.util.ArrayList;

import canon.ball.rewriteGUI.component.components.sub.Checkbox;
import canon.ball.rewriteGUI.component.components.sub.Keybind;
import canon.ball.rewriteGUI.component.components.sub.ModeButton;
import canon.ball.rewriteGUI.component.components.sub.Slider;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import font.FontUtils;
import net.minecraft.client.gui.Gui;

import canon.ball.rewriteGUI.component.Component;
import canon.ball.rewriteGUI.component.Frame;
import yea.bushroot.clickgui.Setting;


public class Button extends Component {

	public Module mod;
	public Frame parent;
	public int offset;
	private boolean isHovered;
	private ArrayList<Component> subcomponents;
	public boolean open;
	private int height;
	
	public Button(Module mod, Frame parent, int offset) {
		this.mod = mod;
		this.parent = parent;
		this.offset = offset;
		this.subcomponents = new ArrayList<>();
		this.open = false;
		height = 12;
		int opY = offset + 12;
		if(ExampleMod.instance.settingsManager.getSettingsByMod(mod) != null) {
			for(Setting s : ExampleMod.instance.settingsManager.getSettingsByMod(mod)){
				if(s.isCombo()){
					this.subcomponents.add(new ModeButton(s, this, mod, opY));
					opY += 12;
				}
				if(s.isSlider()){
					this.subcomponents.add(new Slider(s, this, opY));
					opY += 12;
				}
				if(s.isCheck()){
					this.subcomponents.add(new Checkbox(s, this, opY));
					opY += 12;
				}
			}
		}
		this.subcomponents.add(new Keybind(this, opY));
	}
	
	@Override
	public void setOff(int newOff) {
		offset = newOff;
		int opY = offset + 12;
		for(Component comp : this.subcomponents) {
			comp.setOff(opY);
			opY += 12;
		}
	}

	@Override
	public void renderComponent() {
		Gui.drawRect(parent.getX(), this.parent.getY() + this.offset, parent.getX() + parent.getWidth(), this.parent.getY() + 12 + this.offset, this.isHovered ? (this.mod.toggled ? new Color(255, 0, 255, 191).darker().getRGB() : new Color(15, 15, 15, 191).getRGB()) : (this.mod.toggled ? new Color(255, 0, 255, 191).getRGB() : new Color(30, 30, 30, 191).getRGB()));
		FontUtils.normal.drawString(this.mod.getName(), (parent.getX() + 2) + 2, (parent.getY() + offset + 2) + 2, new Color(255, 255, 255).getRGB());
		if(this.subcomponents.size() > 2)
			FontUtils.normal.drawString(this.open ? "-" : "+", (parent.getX() + parent.getWidth() - 10), (parent.getY() + offset) + 4, new Color(255, 255, 255, 255).getRGB());
		if(this.open) {
			if(!this.subcomponents.isEmpty()) {
				for(Component comp : this.subcomponents) {
					comp.renderComponent();
				}
				Gui.drawRect(parent.getX() + 2, parent.getY() + this.offset + 12, parent.getX() + 3, parent.getY() + this.offset + ((this.subcomponents.size() + 1) * 12), new Color(255, 0, 255, 191).getRGB());
			}
		}
	}


	@Override
	public int getHeight() {
		if(this.open) {
			return (12 * (this.subcomponents.size() + 1));
		}
		return 12;
	}

	@Override
	public void updateComponent(int mouseX, int mouseY) {
		this.isHovered = isMouseOnButton(mouseX, mouseY);
		if(!this.subcomponents.isEmpty()) {
			for(Component comp : this.subcomponents) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(isMouseOnButton(mouseX, mouseY) && button == 0) {
			this.mod.toggle();
		}
		if(isMouseOnButton(mouseX, mouseY) && button == 1) {
			this.open = !this.open;
			this.parent.refresh();
		}
		for(Component comp : this.subcomponents) {
			comp.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
		for(Component comp : this.subcomponents) {
			comp.mouseReleased(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	public void keyTyped(char typedChar, int key) {
		for(Component comp : this.subcomponents) {
			comp.keyTyped(typedChar, key);
		}
	}

	public boolean isMouseOnButton(int x, int y) {
		return x > parent.getX() && x < parent.getX() + parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
	}

}
