package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Objects;

public class Criticals extends Module {
    public Criticals() {
        super("Criticals", 0, Category.COMBAT);
        ArrayList<String> options = new ArrayList<>();

        options.add("Jump");
        options.add("Packet");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
    }

    @SubscribeEvent
    public void onUpdate(RenderWorldLastEvent e) {
        String mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();
        if(Objects.equals(mode, "Jump") && mc.player.onGround){
            mc.player.motionY = 0.15;
        }
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event) {
        String mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();
        if (Criticals.mc.player.isInWater() || Criticals.mc.player.isInLava()) {
            return;
        }
        if (Criticals.mc.player.onGround) {
            if (Objects.equals(mode, "Packet")) {
                Criticals.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1625, Criticals.mc.player.posZ, false));
                Criticals.mc.player.connection.sendPacket((Packet) new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                Criticals.mc.player.onCriticalHit(event.getTarget());
            }
        }
    }

}

