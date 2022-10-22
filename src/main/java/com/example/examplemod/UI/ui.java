package com.example.examplemod.UI;

import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.CLIENT.Panic;
import com.example.examplemod.Module.Module;
import font.FontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static net.minecraft.client.gui.Gui.drawRect;

public class ui {
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        switch (e.getType()) {
            case TEXT:
                if (!Panic.isPanic) {
                    int y = 10;
                    final int[] counter = {1};

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

                } else {
                    Minecraft.getMinecraft().fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), 5, 5, -1);
                }
            default:
                break;
        }
    }

    public static int rainbow(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 10.0);
        return Color.getHSBColor((float)((d %= -360.0) / -360.0), 0.735f, 1.0f).getRGB();
    }

}
