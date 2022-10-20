package com.example.examplemod.Module.RENDER;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import yea.bushroot.clickgui.Setting;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class SwingAnimation extends Module {

    public SwingAnimation(){
        super("SwingAnimation",0, Module.Category.RENDER);
        ArrayList<String> options = new ArrayList<>();

        options.add("1");
        options.add("2");
        options.add("3");

        ExampleMod.instance.settingsManager.rSetting(new Setting("Mode", this, options, "Mode"));
    }

    @SubscribeEvent public void onRenderArms(final RenderSpecificHandEvent event){

        String mode = ExampleMod.instance.settingsManager.getSettingByName(this.name, "Mode").getValString();

        if(event.getSwingProgress() > 0){

            final float angle = (1f - event.getSwingProgress()) * 360f;

            switch (mode){
                case "1":
                    glRotatef(angle, 1, 0, 0);
                    break;

                case "2":
                    glRotatef(angle, 0, 1, 0);
                    break;

                case "3":
                    glRotatef(angle, 0, 0, 1);
                    break;
            }
        }
    }
}