package com.example.examplemod.Module.MOVEMENT;

import java.lang.reflect.Field;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Speed
        extends Module {

    public static double getDirection() {
        float f = Speed.mc.player.rotationYaw;
        if (Speed.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (Speed.mc.player.moveForward < 0.0f) {
            f2 = -0.5f;
        } else if (Speed.mc.player.moveForward > 0.0f) {
            f2 = 0.5f;
        }
        if (Speed.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (Speed.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    public Speed() {
        super("Speed", 0, Module.Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Speed.mc.player.onGround && Speed.mc.player.moveForward != 0.0f) {
            Speed.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
            Speed.mc.player.jump();
        }
        float f = Speed.mc.player.rotationYaw;
        Vec3d vec3d = Vec3d.fromPitchYaw((float)0.0f, (float)f);
        Vec3d vec3d2 = Vec3d.fromPitchYaw((float)0.0f, (float)(f + 90.0f));
        double d = 0.0;
        double d2 = 0.0;
        boolean bl = false;
        if (Speed.mc.player.movementInput.forwardKeyDown) {
            d += vec3d.x / 20.0 * 20;
            d2 += vec3d.z / 20.0 * 20;
            bl = true;
        }
        if (Speed.mc.player.movementInput.backKeyDown) {
            d -= vec3d.x / 20.0 * 20;
            d2 -= vec3d.z / 20.0 * 20;
            bl = true;
        }
        if (Speed.mc.player.movementInput.rightKeyDown) {
            d += vec3d2.x / 20.0 * 20;
            d2 += vec3d2.z / 20.0 * 20;
        }
        if (Speed.mc.player.movementInput.leftKeyDown) {
            d -= vec3d2.x / 20.0 * 20;
            d2 -= vec3d2.z / 20.0 * 20;
        }
        Speed.mc.player.motionX = d;
        Speed.mc.player.motionZ = d2;
    }

    public static float getSpeed() {
        return (float)Math.sqrt(Speed.mc.player.motionX * Speed.mc.player.motionX + Speed.mc.player.motionZ * Speed.mc.player.motionZ);
    }

    public static void setMoveSpeed(double d) {
        double d2 = Speed.mc.player.movementInput.moveForward;
        double d3 = Speed.mc.player.movementInput.moveStrafe;
        float f = Speed.mc.player.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            Speed.mc.player.motionX = 0.0;
            Speed.mc.player.motionZ = 0.0;
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
            Speed.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            Speed.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
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

    public static void setSpeed(EntityLivingBase entityLivingBase, double d) {
        double[] arrd = Speed.forward(d);
        entityLivingBase.motionX = arrd[0];
        entityLivingBase.motionZ = arrd[1];
    }

    public static boolean isMoving() {
        return Speed.mc.player != null && (Speed.mc.player.movementInput.moveForward != 0.0f || Speed.mc.player.movementInput.moveStrafe != 0.0f);
    }

    public static void setTimerSpeed(float f) {
        Class<Minecraft> class_ = Minecraft.class;
        try {
            Field field = class_.getDeclaredField("timer");
        } catch (NoSuchFieldException e) {

        }
        Minecraft.getMinecraft();
        Object object = null;
        Class<?> class_2 = object.getClass();
        Field field2;
        try {
            field2 = class_2.getDeclaredField("timerSpeed");
        } catch (NoSuchFieldException e) {

        }
    }

    public static double getBaseMoveSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.isPotionActive(Potion.getPotionById((int)1))) {
            int n = Minecraft.getMinecraft().player.getActivePotionEffect(Potion.getPotionById((int)1)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }
}