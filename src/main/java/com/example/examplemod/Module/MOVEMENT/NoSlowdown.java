package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoSlowdown extends Module {
    public NoSlowdown() {
        super("NoSlowdown", 0, Category.MOVEMENT);
    }

    static Minecraft mc = Minecraft.getMinecraft();

    public static boolean isMoving() {
        return NoSlowdown.mc.player != null && (NoSlowdown.mc.player.movementInput.moveForward != 0.0f || NoSlowdown.mc.player.movementInput.moveStrafe != 0.0f);
    }

    public static void setMoveSpeed(double d) {
        double d2 = NoSlowdown.mc.player.movementInput.moveForward;
        double d3 = NoSlowdown.mc.player.movementInput.moveStrafe;
        float f = NoSlowdown.mc.player.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            NoSlowdown.mc.player.motionX = 0.0;
            NoSlowdown.mc.player.motionZ = 0.0;
        } else {
            if (d2 != 0.0) {
                if (d3 > 0.0) {
                    f += (float)(d2 > 0.0 ? -45 : 45);
                } else if (d3 < 0.0) {
                    f += (float)(d2 > 0.0 ? 45 : -45);
                }
                d3 = 0.0;
                if (d2 > 0.0) {
                    d2 = 1.0;
                } else if (d2 < 0.0) {
                    d2 = -1.0;
                }
            }
            NoSlowdown.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            NoSlowdown.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
    }

    public static float getSpeed() {
        return (float)Math.sqrt(NoSlowdown.mc.player.motionX * NoSlowdown.mc.player.motionX + NoSlowdown.mc.player.motionZ * NoSlowdown.mc.player.motionZ);
    }

    public static double getBaseMoveSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = Minecraft.getMinecraft().player.getActivePotionEffect(Potion.getPotionById((int)1)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static void setSpeed(EntityLivingBase entityLivingBase, double d) {
        double[] arrd = NoSlowdown.forward(d);
        entityLivingBase.motionX = arrd[0];
        entityLivingBase.motionZ = arrd[1];
    }

    public static double getDirection() {
        float f = NoSlowdown.mc.player.rotationYaw;
        if (NoSlowdown.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (NoSlowdown.mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (NoSlowdown.mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (NoSlowdown.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (NoSlowdown.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }


    public static double[] forward(double d) {
        float f = Minecraft.getMinecraft().player.movementInput.moveForward;
        float f2 = Minecraft.getMinecraft().player.movementInput.moveStrafe;
        float f3 = Minecraft.getMinecraft().player.prevRotationYaw + (Minecraft.getMinecraft().player.rotationYaw - Minecraft.getMinecraft().player.prevRotationYaw) * Minecraft.getMinecraft().getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (NoSlowdown.isMoving() && NoSlowdown.mc.player.isHandActive()) {
            if (NoSlowdown.mc.player.onGround) {
                NoSlowdown.mc.player.jump();
            }
            float f = NoSlowdown.mc.player.rotationYaw;
            Vec3d vec3d = Vec3d.fromPitchYaw((float)0.0f, (float)f);
            Vec3d vec3d2 = Vec3d.fromPitchYaw((float)0.0f, (float)(f + 90.0f));
            double d = 0.0;
            double d2 = 0.0;
            boolean bl = false;
            if (NoSlowdown.mc.player.movementInput.forwardKeyDown) {
                d += vec3d.x / 20.0 * 5.6;
                d2 += vec3d.z / 20.0 * 5.6;
                bl = true;
            }
            if (NoSlowdown.mc.player.movementInput.backKeyDown) {
                d -= vec3d.x / 20.0 * 5.6;
                d2 -= vec3d.z / 20.0 * 5.6;
                bl = true;
            }
            if (NoSlowdown.mc.player.movementInput.rightKeyDown) {
                d += vec3d2.x / 20.0 * 5.6;
                d2 += vec3d2.z / 20.0 * 5.6;
            }
            if (NoSlowdown.mc.player.movementInput.leftKeyDown) {
                d -= vec3d2.x / 20.0 * 5.6;
                d2 -= vec3d2.z / 20.0 * 5.6;
            }
            NoSlowdown.mc.player.motionX = d;
            NoSlowdown.mc.player.motionZ = d2;
        }
    }

}
