package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.TimerUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class AntiAim extends Module {
    public AntiAim() {
        super("AntiAim", 0, Category.PLAYER);
    }

    TimerUtil timer = new TimerUtil();

    @Override
    public void onEnable(){
        timer.reset();
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent e){
        Random random = new Random();
        mc.player.renderYawOffset = random.nextInt(360);
        mc.player.rotationYawHead = random.nextInt(360);
    }
}
