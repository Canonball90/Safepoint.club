package com.example.examplemod.Module.CLIENT;

import canon.ball.rewriteGUI.ClickGui;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import leon.skeetgui.gui.SkeetGUI;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.CLIENT);
        ArrayList<String> options = new ArrayList<>();

        options.add("New");
        options.add("CSGO");
        options.add("ReWrite");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Design", this, options, "Theme"));
        ExampleMod.instance.settingsManager.rSetting(new Setting("uwu", this, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Rainbow", this, false));
    }

    @Override
    public void onEnable() {
        if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "Design").getValString().equalsIgnoreCase("New")) {
            mc.displayGuiScreen(ExampleMod.instance.clickGui);
        }else if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "Design").getValString().equalsIgnoreCase("CSGO")) {
            mc.displayGuiScreen(new SkeetGUI());
        }else {
            mc.displayGuiScreen(new ClickGui());
        }

        super.onEnable();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e){
        if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
            this.toggle();
        }
    }

}
