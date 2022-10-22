package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;

public class FastEat extends Module
{
    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public FastEat() {
        super("FastEat", 0, Category.PLAYER);
    }

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent playerTickEvent) {
        if (FastEat.mc.player.getHealth() > 0.0f && FastEat.mc.player.onGround && FastEat.mc.player.inventory.getCurrentItem() != null && FastEat.mc.player.inventory.getCurrentItem().getItem() instanceof ItemFood && FastEat.mc.player.getFoodStats().needFood() && FastEat.mc.gameSettings.keyBindUseItem.isPressed()) {
            for (int i = 0; i < 100; ++i) {
                FastEat.mc.player.connection.sendPacket((Packet)new CPacketPlayer(false));
            }
        }
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
}
