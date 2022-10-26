package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.entity.EntityOtherPlayerMP;

public class FreeCam extends Module
{
    private double[] oldPosition;
    private EntityOtherPlayerMP freecamPlayer;

    public FreeCam() {
        super("FreeCam", 0, Category.PLAYER);
        this.freecamPlayer = null;
    }

    @SubscribeEvent
    public void onPlayerTickEvent(final TickEvent.PlayerTickEvent playerTickEvent) {
        FreeCam.mc.player.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
        FreeCam.mc.player.setPositionAndRotation(this.oldPosition[0], this.oldPosition[1], this.oldPosition[2], FreeCam.mc.player.rotationYaw, FreeCam.mc.player.rotationPitch);
        FreeCam.mc.world.removeEntityFromWorld(-420);
        this.freecamPlayer = null;
        super.onDisable();
    }

    @Override
    public void onEnable() {
        this.clonePositions();
        this.freecamPlayer.noClip = true;
        this.freecamPlayer.rotationYawHead = FreeCam.mc.player.rotationYawHead;
        FreeCam.mc.world.addEntityToWorld(-420, (Entity)this.freecamPlayer);
        super.onEnable();
    }

    private void clonePositions() {
        this.oldPosition = new double[] { FreeCam.mc.player.posX, FreeCam.mc.player.posY, FreeCam.mc.player.posZ };
    }
}