package com.example.examplemod.Module.RENDER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Objects;

public class FullBright extends Module {

    private float oldBright;

    public FullBright() {
        super("FullBright", Keyboard.KEY_NONE, Category.RENDER);
        ArrayList<String> options = new ArrayList<>();

        options.add("Gamma");
        options.add("Potion");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
    }

    @Override
    public void onEnable() {

        if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString().equalsIgnoreCase("Gamma")) {
            oldBright = mc.gameSettings.gammaSetting;
            mc.gameSettings.gammaSetting = 10f;
        }else if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString().equalsIgnoreCase("Potion")) {
            mc.player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(16)), 999999, 1));
        }
    }

    @Override
    public void onDisable() {
        if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString().equalsIgnoreCase("Gamma")) {
            mc.gameSettings.gammaSetting = oldBright;
        }else if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString().equalsIgnoreCase("Potion")) {
            mc.player.removePotionEffect(Objects.requireNonNull(Potion.getPotionById(16)));
        }
    }
}
