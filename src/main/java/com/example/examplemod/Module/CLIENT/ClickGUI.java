package com.example.examplemod.Module.CLIENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.CLIENT);
        ArrayList<String> options = new ArrayList<>();

        options.add("New");
        options.add("Old");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Design", this, options, "Theme"));
        ExampleMod.instance.settingsManager.rSetting(new Setting("uwu", this, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Rainbow", this, false));
    }

    @Override
    public void onEnable() {

            mc.displayGuiScreen(ExampleMod.instance.clickGui);
        super.onEnable();
    }
}
