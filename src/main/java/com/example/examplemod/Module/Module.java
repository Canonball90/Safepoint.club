package com.example.examplemod.Module;

import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.CLIENT.Notification;
import com.example.examplemod.Utils.ChatUtils;
import com.example.examplemod.Utils.notification.NotificationUtil;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class Module {
    public String name;
    public boolean toggled;
    public int keyCode;

    public Category category;
    public static Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, int key, Category c) {
        this.name = name;
        this.keyCode = key;
        this.category = c;
    }

    public void onRenderWorldLast(float f) {
    }

    public boolean isEnabled() {
        return toggled;
    }

    public int getKey() {
        return keyCode;
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public void setKey(int key) {
        this.keyCode = key;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return this.name;
    }

    public enum Category {
        COMBAT,
        MOVEMENT,
        PLAYER,
        RENDER,
        HUD,
        CLIENT,
        EXPLOIT;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            onEnable();
            if(Client.getModule(Notification.class).isEnabled()){
                NotificationUtil.send_notification(new com.example.examplemod.Utils.notification.Notification(name +" is enabled", 0, 255, 0));
            }else {
                ChatUtils.sendMessage(ChatFormatting.GREEN + "Enabled " + ChatFormatting.RESET + this.name);
            }
        } else {
            onDisable();
            if(Client.getModule(Notification.class).isEnabled()){
                NotificationUtil.send_notification(new com.example.examplemod.Utils.notification.Notification(name +" is disabled", 255, 0, 0));
            }else {
                ChatUtils.sendMessage(ChatFormatting.RED + "Disabled " + ChatFormatting.RESET + this.name);
            }
       }
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }
}
