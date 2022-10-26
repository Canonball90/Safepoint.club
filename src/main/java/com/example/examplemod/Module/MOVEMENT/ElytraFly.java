package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.Module.Module;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ElytraFly extends Module {
    public ElytraFly() {
        super("ElytraFly", 0x00, Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        float yaw = (float)Math.toRadians(ElytraFly.mc.player.rotationYaw);
        double sped = 10 / 10.0;
        mc.player.setVelocity(0,0,0);
        if(mc.player.movementInput.forwardKeyDown) {
            mc.player.motionX = (double) MathHelper.sin((float) yaw) * -sped;
            mc.player.motionZ = (double) MathHelper.cos((float) yaw) * sped;
        }
        if(mc.player.movementInput.jump){
            mc.player.motionY = 1;
        }else if(mc.player.movementInput.sneak){
            mc.player.motionY = -1;
        }else{
            mc.player.motionY = 0;
        }
    }

}
