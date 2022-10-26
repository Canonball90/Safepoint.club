package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import yea.bushroot.clickgui.Setting;

import static com.example.examplemod.Module.MOVEMENT.Strafe.isMoving;

public class ElytraFly extends Module {
    public ElytraFly() {
        super("ElytraFly", 0x00, Category.MOVEMENT);
        ExampleMod.instance.settingsManager.rSetting(new Setting("Speed", this, 1, 0.5, 3, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("UpSpeed", this, 0.6, 0.1, 1, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("DownSpeed", this, 0.6, 0.1, 1, false));
    }

    public static void strafe(float f) {
        if (!isMoving()) {
            return;
        }
        double d = getDirection();
        mc.player.motionX = -Math.sin(d) * (double)f;
        mc.player.motionZ = Math.cos(d) * (double)f;
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
        if (mc.player.isElytraFlying()) {
            float yaw = (float) Math.toRadians(ElytraFly.mc.player.rotationYaw);
            double sped = 10 / 10.0;
            double speed = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Speed").getValDouble();
            double downspeed = ExampleMod.instance.settingsManager.getSettingByName(this.name, "DownSpeed").getValDouble();
            double upspeed = ExampleMod.instance.settingsManager.getSettingByName(this.name, "UpSpeed").getValDouble();
            mc.player.setVelocity(0, 0, 0);
            if (mc.player.movementInput.forwardKeyDown || mc.player.movementInput.leftKeyDown || mc.player.movementInput.rightKeyDown || mc.player.movementInput.backKeyDown) {
                //   mc.player.motionX = (double) MathHelper.sin((float) yaw) * -sped;
                //   mc.player.motionZ = (double) MathHelper.cos((float) yaw) * sped;
                strafe((float) speed);
            }
            if (mc.player.movementInput.jump) {
                mc.player.motionY = upspeed;
            } else if (mc.player.movementInput.sneak) {
                mc.player.motionY = -downspeed;
            } else {
                mc.player.motionY = 0;
            }

        }
    }
}
