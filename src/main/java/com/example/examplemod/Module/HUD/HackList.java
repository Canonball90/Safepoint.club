package com.example.examplemod.Module.HUD;

import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yea.bushroot.clickgui.Setting;

import java.awt.*;
import java.util.ArrayList;

public class HackList extends Module {
    public HackList() {
        super("ArrayList", 0, Category.HUD);
        ArrayList<String> options = new ArrayList<>();

        ExampleMod.instance.settingsManager.rSetting(new Setting("RightBorder", this,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("LeftBorder", this, false));

    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        switch (e.getType()) {
            case TEXT:
                int y = 10;
                final int[] counter = {1};
                boolean leftborder = ExampleMod.instance.settingsManager.getSettingByName(this.name, "LeftBorder").getValBoolean();
                boolean rightborder = ExampleMod.instance.settingsManager.getSettingByName(this.name, "RightBorder").getValBoolean();

                Minecraft mc = Minecraft.getMinecraft();
                FontRenderer fr = mc.fontRenderer;
                ScaledResolution sr = new ScaledResolution(mc);

                int posY = 10;

                ArrayList<Module> enabledMods = new ArrayList<>();

                for (Module module : Client.modules) {
                    if (module.toggled) {
                        enabledMods.add(module);
                    }
                }

                enabledMods.sort((module1, module2) -> mc.fontRenderer.getStringWidth(module2.getName()) - mc.fontRenderer.getStringWidth(module1.getName()));

                for (PotionEffect activePotionEffect : mc.player.getActivePotionEffects()) {
                    if (activePotionEffect.getPotion().isBeneficial()) {
                        y = 36;
                    } if (activePotionEffect.getPotion().isBadEffect()) {
                        y = 36 * 2;
                    }
                }

                for (Module module : enabledMods) {
                    if(leftborder) {
                        Gui.drawRect(sr.getScaledWidth(), y, sr.getScaledWidth() - 2,
                                y + 10, rainbow(counter[0] * 300));                     //2
                    }
                    if(rightborder) {
                        Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 10, y, sr.getScaledWidth() - fr.getStringWidth(module.name) - 6, y + 10, rainbow(counter[0] * 300));
                    }
                    fr.drawString(module.name, sr.getScaledWidth() - 4 - fr.getStringWidth(module.name),
                            y, rainbow(counter[0] * 300));

                    y += 10;
                    counter[0]++;
                }
        }
    }
//0.735f

    public static int rainbow(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 10.0);
        return Color.getHSBColor((float)((d %= -360.0) / -360.0), 0.735f, 1.0f).getRGB();
    }

}
