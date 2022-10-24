package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;
import java.util.Objects;

public class Particles extends Module {
    public Particles() {
        super("Particles", Keyboard.KEY_NONE, Module.Category.COMBAT);

        ArrayList<String> options = new ArrayList<>();

        options.add("NOTE");
        options.add("TOTEM");
        options.add("END_CRYSTAL");
        options.add("HEART");
        options.add("DROPLET");
        options.add("CLOUD");
        options.add("FLAME");
        options.add("VILLAGER_ANGRY");
        options.add("CRIT_MAGIC");
        options.add("EXPLOSION_LARGE");
        options.add("CRIT_MAGIC");
        options.add("SPELL_INSTANT");
        options.add("PORTAL");
        options.add("ENCHANTMENT_TABLE");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Particles", this, 16, 1, 30, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("posY", this, 1, 0, 3, true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("xSpeed", this, 0.2, 0, 5, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("ySpeed", this, 0.25, 0, 5, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("zSpeed", this, 0.2, 0, 5, false));
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent e) {
        String Mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();
        for (EntityPlayer entityPlayer : mc.world.playerEntities) {
            if(entityPlayer.getDistance(mc.player) <= 6 && entityPlayer != mc.player){
                for (int j = 0; j < ExampleMod.instance.settingsManager.getSettingByName(this.name, "Particles").getValDouble(); j++) {
                    if (Objects.equals(Mode, "NOTE")) {
                        mc.world.spawnParticle(EnumParticleTypes.NOTE, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "TOTEM")) {
                        mc.world.spawnParticle(EnumParticleTypes.TOTEM, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "END_CRYSTAL")) {
                        mc.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1, Item.getIdFromItem(Items.END_CRYSTAL));
                    }if (Objects.equals(Mode, "Test")) {
                        mc.world.spawnParticle(EnumParticleTypes.HEART, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "HEART")) {
                        mc.world.spawnParticle(EnumParticleTypes.HEART, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "DROPLET")) {
                        mc.world.spawnParticle(EnumParticleTypes.WATER_DROP, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "CLOUD")) {
                        mc.world.spawnParticle(EnumParticleTypes.CLOUD, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "FLAME")) {
                        mc.world.spawnParticle(EnumParticleTypes.FLAME, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "VILLAGER_ANGRY")) {
                        mc.world.spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "CRIT_MAGIC")) {
                        mc.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "EXPLOSION_LARGE")) {
                        mc.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "CRIT_MAGIC")) {
                        mc.world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "SPELL_INSTANT")) {
                        mc.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "PORTAL")) {
                        mc.world.spawnParticle(EnumParticleTypes.PORTAL, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }if (Objects.equals(Mode, "ENCHANTMENT_TABLE")) {
                        mc.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, entityPlayer.posX, entityPlayer.posY + ExampleMod.instance.settingsManager.getSettingByName(this.name, "posY").getValDouble(), entityPlayer.posZ, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "xSpeed").getValDouble() - 0.1, Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "ySpeed").getValDouble(), Math.random() * ExampleMod.instance.settingsManager.getSettingByName(this.name, "zSpeed").getValDouble() - 0.1);
                    }
                }
            }
        }
    }}