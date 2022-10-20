package com.example.examplemod.Module.RENDER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.*;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;

public class ChestESP extends Module {
    public ChestESP() {
        super("ChestESP", Keyboard.KEY_NONE, Category.RENDER);
        ArrayList<String> options = new ArrayList<>();

        ExampleMod.instance.settingsManager.rSetting(new Setting("Chest", this,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("EChest", this,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Hopper", this,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Furnace", this,true));
        ExampleMod.instance.settingsManager.rSetting(new Setting("Shulker", this,true));
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent e) {

        boolean chest = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Chest").getValBoolean();
        boolean Echest = ExampleMod.instance.settingsManager.getSettingByName(this.name, "EChest").getValBoolean();
        boolean Hopper = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Hopper").getValBoolean();
        boolean Furnace = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Furnace").getValBoolean();
        boolean Shulker = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Shulker").getValBoolean();

        for (Object c : mc.world.loadedTileEntityList) {
            if(chest) {
                if (c instanceof TileEntityChest) {
                    RenderUtils.blockESP(((TileEntityChest) c).getPos());
                }
            }
            if(Echest) {
                if (c instanceof TileEntityEnderChest) {
                    RenderUtils.blockESP(((TileEntityEnderChest) c).getPos());
                }
            }
            if(Hopper) {
                if (c instanceof TileEntityHopper) {
                    RenderUtils.blockESP(((TileEntityHopper) c).getPos());
                }
            }
            if(Furnace) {
                if (c instanceof TileEntityFurnace) {
                    RenderUtils.blockESP(((TileEntityFurnace) c).getPos());
                }
            }
            if(Shulker){
                if(c instanceof TileEntityShulkerBox){
                    RenderUtils.blockESP(((TileEntityShulkerBox) c).getPos());
                }
            }
        }
    }
}
