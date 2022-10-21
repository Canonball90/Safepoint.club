package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class Derp extends Module {

    private final Random e = new Random();
    static Minecraft mc = Minecraft.getMinecraft();

    public Derp() {
        super("Derp", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        float f = this.e.nextFloat() * 360.0f;
        float cfr_ignored_0 = this.e.nextFloat() * 180.0f - 90.0f;
        Derp.mc.player.rotationYawHead = f;
        Derp.mc.player.renderYawOffset = f;
    }

}
