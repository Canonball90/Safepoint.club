package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GodMode extends Module {

    public GodMode() {
        super("GodMode", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (GodMode.mc.player.getHealth() <= 0.0f) {
            GodMode.mc.player.setHealth(GodMode.mc.player.getMaxHealth());
            if (GodMode.mc.currentScreen instanceof GuiGameOver) {
                GodMode.mc.currentScreen = null;
            }
        }
    }
}
