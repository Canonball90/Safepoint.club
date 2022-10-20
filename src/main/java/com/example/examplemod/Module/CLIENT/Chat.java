package com.example.examplemod.Module.CLIENT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yea.bushroot.clickgui.Setting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Chat extends Module {
    public Chat() {
        super("Chat", 0, Module.Category.CLIENT);
        ExampleMod.instance.settingsManager.rSetting(new Setting("ChatSuffix", this, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("TimeStamps", this, false));
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (ExampleMod.instance.settingsManager.getSettingByName(this.name, "TimeStamps").getValBoolean()) {
            SimpleDateFormat timeformat = new SimpleDateFormat("H:m");
            String time = timeformat.format(new Date());
            event.setMessage(new TextComponentString(ChatFormatting.GRAY + "<" + ChatFormatting.DARK_PURPLE + time + ChatFormatting.GRAY + "> " + ChatFormatting.RESET).appendSibling(event.getMessage()));
        }
    }

    @SubscribeEvent
    public void onChat(final ClientChatEvent event)
    {
        if(toggled && ExampleMod.instance.settingsManager.getSettingByName(this.name, "ChatSuffix").getValBoolean()) {
            for (final String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@"))
            {
                if (event.getMessage().startsWith(s)) return;
            }
            event.setMessage(event.getMessage() + " " + "\u23D0" + toUnicode(" " + "Safepoint.club"));
        }
    }

    public String toUnicode(String s) {
        return s.toLowerCase()
                .replace("a", "\u1d00")
                .replace("b", "\u0299")
                .replace("c", "\u1d04")
                .replace("d", "\u1d05")
                .replace("e", "\u1d07")
                .replace("f", "\ua730")
                .replace("g", "\u0262")
                .replace("h", "\u029c")
                .replace("i", "\u026a")
                .replace("j", "\u1d0a")
                .replace("k", "\u1d0b")
                .replace("l", "\u029f")
                .replace("m", "\u1d0d")
                .replace("n", "\u0274")
                .replace("o", "\u1d0f")
                .replace("p", "\u1d18")
                .replace("q", "\u01eb")
                .replace("r", "\u0280")
                .replace("s", "\ua731")
                .replace("t", "\u1d1b")
                .replace("u", "\u1d1c")
                .replace("v", "\u1d20")
                .replace("w", "\u1d21")
                .replace("x", "\u02e3")
                .replace("y", "\u028f")
                .replace("z", "\u1d22");
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}

