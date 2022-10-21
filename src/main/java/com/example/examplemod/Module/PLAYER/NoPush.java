package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoPush
        extends Module {
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        NoPush.mc.player.entityCollisionReduction = 0.0f;
    }

    public NoPush() {
        super("NoPush",  0, Module.Category.PLAYER);
    }
}
