package com.example.examplemod.Module.COMBAT;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RotationUtils;
import com.example.examplemod.Utils.TimerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import yea.bushroot.clickgui.Setting;

public class TargetStrafe
        extends Module {
    public TimerUtil timerUtil;
    public boolean sideDirection = true;
    public static int direction = -1;
    public double increment = 0.05;

    static Minecraft mc = Minecraft.getMinecraft();

    public double getMovementSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().player.isPotionActive(Objects.requireNonNull(Potion.getPotionById((int)1)))) {
            int n = Objects.requireNonNull(Minecraft.getMinecraft().player.getActivePotionEffect(Objects.requireNonNull(Potion.getPotionById((int)1)))).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public Entity getTargetEz() {
        if (TargetStrafe.mc.player == null || TargetStrafe.mc.player.isDead) {
            return null;
        }
        List list = TargetStrafe.mc.world.loadedEntityList.stream().filter(entity -> entity != TargetStrafe.mc.player).filter(entity -> TargetStrafe.mc.player.getDistance(entity) <= 7.0f).filter(entity -> !entity.isDead).filter(this::lambda$getTargetEz$3).sorted(Comparator.comparing(entity -> Float.valueOf(TargetStrafe.mc.player.getDistance(entity)))).collect(Collectors.toList());
        if (list.size() > 0) {
            return (Entity)list.get(0);
        }
        return null;
    }

    public final boolean doStrafeAtSpeed(double d) {
        boolean bl = true;
        Entity entity = this.getTargetEz();

        if (entity != null) {
            if (TargetStrafe.mc.player.onGround) {
                TargetStrafe.mc.player.jump();
            }
            float[] arrf = RotationUtils.getNeededRotations((EntityLivingBase)entity);
            if ((double)Minecraft.getMinecraft().player.getDistance(entity) <= 3) {
                TargetStrafe.mc.player.renderYawOffset = arrf[0];
                TargetStrafe.mc.player.rotationYawHead = arrf[0];
                TargetStrafe.setSpeed(d - (0.1 - 7 / 100.0), arrf[0], direction, 0.0);
            } else {
                TargetStrafe.setSpeed(d - (0.1 - 7 / 100.0), arrf[0], direction, 1.0);
                TargetStrafe.mc.player.renderYawOffset = arrf[0];
                TargetStrafe.mc.player.rotationYawHead = arrf[0];
            }
        }
        return bl;
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        try {
            if (mc.player == null) return;
            if (mc.player.collidedHorizontally && timerUtil.hasReached(80.0)) {
                timerUtil.reset();
                invertStrafe();
            }
            mc.player.movementInput.moveForward = 0.0f;
            double d = this.getMovementSpeed();
            doStrafeAtSpeed(d);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public TargetStrafe() {
        super("TargetStrafe", 0, Module.Category.COMBAT);
        ExampleMod.instance.settingsManager.rSetting(new Setting("ThirdPerson", this, false));

    }

    @Override
    public void onEnable() {
        if(ExampleMod.instance.settingsManager.getSettingByName(this.name, "ThirdPerson").getValBoolean()) {
            mc.gameSettings.thirdPersonView = 1;
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.gameSettings.thirdPersonView = 0;
        super.onDisable();
    }

    public static void setSpeed(double d, float f, double d2, double d3) {
        double d4 = d3;
        double d5 = d2;
        float f2 = f;
        if (d4 == 0.0 && d5 == 0.0) {
            TargetStrafe.mc.player.motionZ = 0.0;
            TargetStrafe.mc.player.motionX = 0.0;
        } else {
            if (d4 != 0.0) {
                if (d5 > 0.0) {
                    f2 += (float)(d4 > 0.0 ? -45 : 45);
                } else if (d5 < 0.0) {
                    f2 += (float)(d4 > 0.0 ? 45 : -45);
                }
                d5 = 0.0;
                if (d4 > 0.0) {
                    d4 = 1.0;
                } else if (d4 < 0.0) {
                    d4 = -1.0;
                }
            }
            double d6 = Math.cos(Math.toRadians(f2 + 90.0f));
            double d7 = Math.sin(Math.toRadians(f2 + 90.0f));
            TargetStrafe.mc.player.motionX = d4 * d * d6 + d5 * d * d7;
            TargetStrafe.mc.player.motionZ = d4 * d * d7 - d5 * d * d6;
        }
    }

    private void drawRadius(Entity entity, double d, double d2, double d3, double d4) {
        GlStateManager.enableDepth();
        double d5 = 0.0;
        Double.compare(d5, 0.01);
    }

    private boolean lambda$getTargetEz$3(Entity entity) {
        return this.attackCheck(entity);
    }

    @Override
    public void onRenderWorldLast(float f) {
        if (TargetStrafe.mc.player == null || TargetStrafe.mc.player.isDead) {
            return;
        }
        List list = TargetStrafe.mc.world.loadedEntityList.stream().filter(entity -> entity != TargetStrafe.mc.player).filter(entity -> TargetStrafe.mc.player.getDistance(entity) <= 15.0f).filter(entity -> !entity.isDead).filter(this::attackCheckin).filter(entity -> !(entity instanceof EntityArmorStand)).sorted(Comparator.comparing(entity -> Float.valueOf(TargetStrafe.mc.player.getDistance(entity)))).collect(Collectors.toList());
        if (list.size() > 0) {
            double d = 3;
            Entity entity2 = (Entity)list.get(0);
            double d2 = entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)f - TargetStrafe.mc.getRenderManager().viewerPosX;
            double d3 = entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)f - TargetStrafe.mc.getRenderManager().viewerPosY;
            double d4 = entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)f - TargetStrafe.mc.getRenderManager().viewerPosZ;
            if (this.increment < 2.03 && this.sideDirection) {
                if (this.increment >= 2.0) {
                    this.sideDirection = false;
                    this.increment = 2.0;
                    d3 -= this.increment;
                }
                this.increment += 0.02;
                this.drawRadius((Entity)list.get(0), d2, d3 += this.increment, d4, d);
            }
            if (this.increment > 0.01 && !this.sideDirection) {
                if (this.increment <= 0.02) {
                    this.sideDirection = true;
                    this.increment = 0.01;
                }
                this.increment -= 0.02;
                this.drawRadius((Entity)list.get(0), d2, d3 += this.increment, d4, d);
            }
        }
    }

    public boolean attackCheck(Entity entity) {
        if (entity instanceof EntityPlayer && !entity.isInvisible()) {
            return true;
        }
        return entity instanceof EntityMob && !entity.isInvisible();
    }

    private void invertStrafe() {
        direction = -direction;
    }

    public boolean attackCheckin(Entity entity) {
        return entity instanceof EntityPlayer && ((EntityPlayer)entity).getHealth() > 0.0f && Math.abs(TargetStrafe.mc.player.rotationYaw - RotationUtils.getNeededRotations((EntityLivingBase)entity)[0]) % 180.0f < 190.0f && !entity.isInvisible() && !entity.getUniqueID().equals(TargetStrafe.mc.player.getUniqueID());
    }
}
