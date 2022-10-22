package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.event.entity.living.LivingEvent;
import yea.bushroot.clickgui.Setting;

public class DamageFly extends Module
{
    public DamageFly() {
        super("DamageFly", 0, Category.MOVEMENT);
        ExampleMod.instance.settingsManager.rSetting(new Setting("Horizontal", this, 300.0, 0.0, 600.0, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Vertical", this, 300.0, 0.0, 600.0, true));
    }

    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent e) {
        final float horizontal = (float)ExampleMod.instance.settingsManager.getSettingByName(this.name, "Horizontal").getValDouble();
        final float vertical = (float)ExampleMod.instance.settingsManager.getSettingByName(this.name, "Vertical").getValDouble();
        if (DamageFly.mc.player.hurtTime == DamageFly.mc.player.maxHurtTime && DamageFly.mc.player.maxHurtTime > 0) {
            DamageFly.mc.player.jump();
            final EntityPlayerSP player = DamageFly.mc.player;
            player.motionX *= horizontal / 100.0f;
            final EntityPlayerSP player2 = DamageFly.mc.player;
            player2.motionY *= vertical / 100.0f;
            final EntityPlayerSP player3 = DamageFly.mc.player;
            player3.motionZ *= horizontal / 100.0f;
        }
    }
}
