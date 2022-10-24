package com.example.examplemod.Module.RENDER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import yea.bushroot.clickgui.Setting;

public class HitAnimation extends Module {
    public HitAnimation() {
        super("HitAnimation", Keyboard.KEY_NONE, Category.RENDER);

        ExampleMod.instance.settingsManager.rSetting(new Setting("NoAnimation", this, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Angle", this, 0, -360, 360, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("X", this, 0, -2.5, 2.5, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Y", this, 0, -2.5, 2.5, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Z", this, 0, -2.5, 2.5, false));
    }

    @SubscribeEvent
    public void onRenderArms(RenderSpecificHandEvent e) {
        double FastAngle = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Angle").getValDouble();
        double X = ExampleMod.instance.settingsManager.getSettingByName(this.name, "X").getValDouble();
        double Y = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Y").getValDouble();
        double Z = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Z").getValDouble();
        float angle = 0;
        if (e.getSwingProgress() > 0) {
            angle = (float) ((1f - e.getSwingProgress()) * FastAngle);
        }
        GL11.glRotatef(angle, (float) X, (float) Y, (float) Z);
    }
}
