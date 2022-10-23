package com.example.examplemod.Module.CLIENT;

import com.example.examplemod.Module.Module;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.mojang.realmsclient.gui.ChatFormatting;

public class VisualRange extends Module {

    List<Entity> knownPlayers = new ArrayList<>();;
    List<Entity> players;

    public VisualRange() {
        super("VisualRange", 0, Category.CLIENT);
    }

    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event)
    {
        players = mc.world.loadedEntityList.stream().filter(e-> e instanceof EntityPlayer).collect(Collectors.toList());
        try
        {
            for (Entity e : players)
            {
                if (e instanceof EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName()))
                {
                    if (!knownPlayers.contains(e))
                    {
                        knownPlayers.add(e);
                        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(ChatFormatting.DARK_GRAY + "[SafePoint] " + ChatFormatting.WHITE + "<VisualRange> " + ChatFormatting.GOLD +  e.getName() + " entered visual range."));
                    }
                }
            }
        }
        catch(Exception e) { }
        try
        {
            for (Entity e : knownPlayers)
            {
                if (e instanceof EntityPlayer && !e.getName().equalsIgnoreCase(mc.player.getName()))
                {
                    if (!players.contains(e)) {
                        knownPlayers.remove(e);;
                    }
                }
            }
        }
        catch(Exception e) { }
    }
}