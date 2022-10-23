package com.example.examplemod.Module.RENDER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import yea.bushroot.clickgui.Setting;

public class ViewModel extends Module {
    public ViewModel() {
        super("ViewModel", Keyboard.KEY_NONE, Category.RENDER);
        ExampleMod.instance.settingsManager.rSetting(new Setting("X", this, 0, -10, 10,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Y", this, 0, -10, 10,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Z", this, 0, -10, 10,true));
    }

    @SubscribeEvent
    public void onRender(RenderSpecificHandEvent e) {
        int x = (int)ExampleMod.instance.settingsManager.getSettingByName(this.name, "X").getValDouble();
        int y = (int)ExampleMod.instance.settingsManager.getSettingByName(this.name, "Y").getValDouble();
        int z = (int)ExampleMod.instance.settingsManager.getSettingByName(this.name, "Z").getValDouble();
        GL11.glTranslated(x, y, z);
    }
}
