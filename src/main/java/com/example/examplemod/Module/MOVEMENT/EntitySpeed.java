package com.example.examplemod.Module.MOVEMENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

public class EntitySpeed extends Module {
    public EntitySpeed(){
        super("EntitySpeed", Keyboard.KEY_NONE, Category.MOVEMENT);

        ExampleMod.instance.settingsManager.rSetting(new Setting("Speed", this, 0.5, 0, 10, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("AntiStuck", this, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Fly", this, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("GlideSpeed", this, 0.1, 0, 1, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("UpSpeed", this, 1, 0, 5, false));
    }

    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent e) {
        if (mc.player == null || mc.player.getRidingEntity() == null) {
            return;
        }

        if (mc.player.getRidingEntity() instanceof EntityBoat || mc.player.getRidingEntity() instanceof EntityPig || mc.player.getRidingEntity() instanceof AbstractHorse && mc.player.getRidingEntity().getControllingPassenger().equals(mc.player)) {
            moveEntity(mc.player.getRidingEntity(), ExampleMod.instance.settingsManager.getSettingByName(this.name, "Speed").getValDouble(), ExampleMod.instance.settingsManager.getSettingByName(this.name, "AntiStuck").getValBoolean());

            if (mc.player.getRidingEntity() instanceof AbstractHorse) {
                mc.player.getRidingEntity().rotationYaw = mc.player.rotationYaw;
            }if (ExampleMod.instance.settingsManager.getSettingByName(this.name, "Fly").getValBoolean()) {
                fly(mc.player.getRidingEntity());
            }
        }
    }

    public static void moveEntity(Entity entity, double speed, boolean antiStuck) {
        double yawRad = Math.toRadians(mc.player.rotationYaw - getRotationFromVec(new Vec3d(-mc.player.moveStrafing, 0.0, mc.player.moveForward))[0]);

        if (isInputting()) {
            entity.motionX = -Math.sin(yawRad) * speed;
            entity.motionZ = Math.cos(yawRad) * speed;
        } else {
            entity.motionX = 0;
            entity.motionZ = 0;
        }

        if (antiStuck && entity.posY > entity.lastTickPosY) {
            entity.motionX = -Math.sin(yawRad) * 0.1;
            entity.motionZ = Math.cos(yawRad) * 0.1;
        }
    }

    public static boolean isInputting() {
        return mc.player.movementInput.moveForward != 0 || mc.player.movementInput.moveStrafe != 0;
    }

    public void fly(Entity entity) {
        if (!entity.isInWater()) {
            entity.motionY = -ExampleMod.instance.settingsManager.getSettingByName(this.name, "GlideSpeed").getValDouble();
        }

        if (mc.gameSettings.keyBindJump.isKeyDown()) {
            entity.motionY += ExampleMod.instance.settingsManager.getSettingByName(this.name, "UpSpeed").getValDouble();
        }
    }

    public static double[] getRotationFromVec(Vec3d vec) {
        double xz = Math.sqrt(vec.x * vec.x + vec.z * vec.z);
        double yaw = normalizeAngle(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0);
        double pitch = normalizeAngle(Math.toDegrees(-Math.atan2(vec.y, xz)));
        return new double[]{yaw, pitch};
    }

    public static double normalizeAngle(double angle) {
        angle %= 360.0;

        if (angle >= 180.0) {
            angle -= 360.0;
        }

        if (angle < -180.0) {
            angle += 360.0;
        }

        return angle;
    }
}
