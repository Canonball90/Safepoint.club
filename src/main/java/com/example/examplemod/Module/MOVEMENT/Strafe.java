package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Strafe
        extends Module {
    public static void strafe() {
        strafe(getSpeed());
    }

    static Minecraft mc = Minecraft.getMinecraft();

    public static void strafe(float f) {
        if (!isMoving()) {
            return;
        }
        double d = getDirection();
        mc.player.motionX = -Math.sin(d) * (double)f;
        mc.player.motionZ = Math.cos(d) * (double)f;
    }

    public Strafe() {
        super("Strafe", 0,Category.MOVEMENT);
    }

    public static float getSpeed() {
        return (float)Math.sqrt(mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ);
    }

    public static double getDirection() {
        float f = mc.player.rotationYaw;
        if (mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        strafe();
    }

    public static boolean isMoving() {
        if(mc.player.onGround && mc.player.moveForward != 0.0f || mc.player.moveStrafing != 0.0f) {
            mc.player.jump();
        }
        return mc.player != null && (mc.player.movementInput.moveForward != 0.0f || mc.player.movementInput.moveStrafe != 0.0f);
    }
}
