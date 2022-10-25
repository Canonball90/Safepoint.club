package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

public class ClientTime extends Module {
    public ClientTime(){
        super("ClientTime", Keyboard.KEY_NONE, Category.RENDER);

        ExampleMod.instance.settingsManager.rSetting(new Setting("Time", this, 1000, 0, 23000, true));
    }

    @SubscribeEvent
    public void onRender(TickEvent.PlayerTickEvent e) {
        mc.world.setWorldTime((long) ExampleMod.instance.settingsManager.getSettingByName(this.name, "Time").getValDouble());
    }
}
