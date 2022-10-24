package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

public class PigPOV extends Module {
    public PigPOV() {
        super("PigPOV", Keyboard.KEY_NONE, Category.PLAYER);

        ExampleMod.instance.settingsManager.rSetting(new Setting("eyeHeight", this, 0.6, 0, 50, false));
    }

    @Override
    public void onEnable() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.player.eyeHeight = (float) ExampleMod.instance.settingsManager.getSettingByName(this.name, "eyeHeight").getValDouble();
        mc.getRenderManager().entityRenderMap.put(EntityPig.class, new RenderPig(mc.getRenderManager()));
    }

    @Override
    public void onDisable() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.player.eyeHeight = mc.player.getDefaultEyeHeight();
        mc.getRenderManager().entityRenderMap.put(EntityPig.class, new RenderPig(mc.getRenderManager()));
    }
}
