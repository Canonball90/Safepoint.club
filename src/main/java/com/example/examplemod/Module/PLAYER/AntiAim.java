package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AntiAim extends Module {
    public AntiAim() {
        super("AntiAim", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent e){
        e.player.setRotationYawHead(e.player.getRotationYawHead() + 6);
    }
}
