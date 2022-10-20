package com.example.examplemod.Module.HUD;

import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import font.FontUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.example.examplemod.Client;
import com.example.examplemod.Module.Module;
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
import yea.bushroot.clickgui.Setting;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static net.minecraft.client.gui.Gui.drawRect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

import static net.minecraft.client.gui.Gui.drawRect;

public class Watermark extends Module {
    public Watermark() {
        super("WaterMark", 0, Category.HUD);
        ArrayList<String> options = new ArrayList<>();

        ExampleMod.instance.settingsManager.rSetting(new Setting("X", this, 8.0, 0.0, 255.0,false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Y", this, 8.0, 0.0, 255.0, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("CSGO", this, true));
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        switch (e.getType()) {
            case TEXT:
                int y = 10;
                final int[] counter = {1};


                Minecraft mc = Minecraft.getMinecraft();
                FontRenderer fr = mc.fontRenderer;
                ScaledResolution sr = new ScaledResolution(mc);

                if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "CSGO").getValBoolean()) {
                    try {
                        String text = Client.cName + " | " + mc.getSession().getUsername() + " | " + Objects.requireNonNull(mc.getCurrentServerData()).serverIP +
                                " | FPS: " + Minecraft.getDebugFPS() + " | Ping: " + mc.getCurrentServerData().pingToServer;

                        drawRect(5, 5, FontUtils.normal.getStringWidth(text) > 190 ? (int) (FontUtils.normal.getStringWidth(text) + 14) : 200, 14, new Color(0x151515).hashCode());
                        drawRect(5, 5, FontUtils.normal.getStringWidth(text) > 190 ? (int) (FontUtils.normal.getStringWidth(text) + 14) : 200, 4, rainbow(300));

                        FontUtils.normal.drawString(text, 10, 10, -1);
                    } catch (Exception ex) {
                        drawRect(5, 5, 200, 14, new Color(0x151515).hashCode());
                        drawRect(5, 5, 200, 4, rainbow(300));

                        FontUtils.normal.drawString(Client.cName + " | " + mc.getSession().getUsername() +
                                " | FPS: " + Minecraft.getDebugFPS(), 10, 10, -1);
                    }
                }else{
                    fr.drawStringWithShadow(Client.cName + " | " + mc.getSession().getUsername(), (float) ExampleMod.instance.settingsManager.getSettingByName(this.name, "X").getValDouble(), (float) ExampleMod.instance.settingsManager.getSettingByName(this.name, "Y").getValDouble(), -1);
                }
                /*
                fr.drawString(ChatFormatting.RED + "Safe" + ChatFormatting.WHITE + "point", 5, 5, -1);
                fr.drawString("FPS: " + ChatFormatting.RED + Minecraft.getDebugFPS(), 5, 15, -1);




 */
        }
    }

    public static int rainbow(int delay){
        double rainbowState =  Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        rainbowState %= 360;
        return Color.getHSBColor((float) (rainbowState / 360.0f), 0.5f, 1f).getRGB();
    }
}