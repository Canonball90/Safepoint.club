package com.example.examplemod.Module.RENDER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Objects;


import static org.lwjgl.opengl.GL11.glRotatef;

public class SwingAnimation extends Module {
    public SwingAnimation() {
        super("SwingAnimation", Keyboard.KEY_NONE, Category.RENDER);
        ArrayList<String> options = new ArrayList<>();

        options.add("Rotate");
        options.add("Hoiw");
        options.add("Angle");
        options.add("Trop");
        options.add("Hit");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
    }

    @SubscribeEvent
    public void onRenderArms(final RenderSpecificHandEvent event) {
        String Mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();

        if (event.getSwingProgress() > 0) {

            final float angle = (1f - event.getSwingProgress()) * 360f;
            if (Objects.equals(Mode, "Rotate")) {
                glRotatef(angle, 0, 1, 0);
                return;
            }
            if (Objects.equals(Mode, "Hoiw")) {
                glRotatef(angle, 0, 0, 1);
                return;
            }
            if (Objects.equals(Mode, "Angle")) {
                glRotatef(angle, 1, 0, 0);
                return;
            }
            if (Objects.equals(Mode, "Trop")) {
                glRotatef(angle, 4, -2, 3);
                return;
            }
        }
    }
}