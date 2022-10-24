package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespawn", Keyboard.KEY_NONE, Category.PLAYER);}

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent e) {
        if (mc.player.isDead){
            mc.player.respawnPlayer();
        }
    }
}
