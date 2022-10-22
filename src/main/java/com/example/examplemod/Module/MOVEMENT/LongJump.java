package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LongJump
        extends Module {
    private double lastDist = 0.0;
    public static int stage;
    private double moveSpeed;

    public LongJump() {
        super("LongJump",0, Module.Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Speed.isMoving()) {
            if (LongJump.mc.player.moveForward != 0.0f || LongJump.mc.player.moveStrafing != 0.0f) {
                if (stage == 0) {
                    this.moveSpeed = 1.0 + Speed.getBaseMoveSpeed() - 0.05;
                } else if (stage == 1) {
                    LongJump.mc.player.motionY = 0.42;
                    this.moveSpeed *= 2.13;
                } else if (stage == 2) {
                    double d = 0.66 * (this.lastDist - Speed.getBaseMoveSpeed());
                    this.moveSpeed = this.lastDist - d;
                } else {
                    this.moveSpeed = this.lastDist - this.lastDist / 159.0;
                }
                this.moveSpeed = Math.max(Speed.getBaseMoveSpeed(), this.moveSpeed);
                LongJump.setMoveSpeed(this.moveSpeed);
                ++stage;
            } else if (stage > 1) {
                stage = 0;
            }
        }
    }

    public static void setMoveSpeed(double d) {
        double d2 = LongJump.mc.player.movementInput.moveForward;
        double d3 = LongJump.mc.player.movementInput.moveStrafe;
        float f = LongJump.mc.player.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            LongJump.mc.player.motionX = 0.0;
            LongJump.mc.player.motionZ = 0.0;
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
            LongJump.mc.player.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            LongJump.mc.player.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
    }
}
