package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;
import yea.bushroot.clickgui.Setting;

import javax.swing.text.JTextComponent;
import java.util.concurrent.ThreadLocalRandom;

public class AutoClicker extends Module {

    private long lastClick;
    private long hold;

    private double speed;
    private double holdLength;
    private double min;
    private double max;

    public AutoClicker() {
        super("AutoClicker", 0, Category.COMBAT);

        ExampleMod.instance.settingsManager.rSetting(new Setting("MinCPS", this, 10, 0, 20, false));
        ExampleMod.instance.settingsManager.rSetting(new Setting("MaxCPS", this, 10, 0, 20, false));
    }

    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent e){
        if(Mouse.isButtonDown(0)){
            if(System.currentTimeMillis() - lastClick > speed * 1000){
                if(hold < lastClick){
                    hold = lastClick;
                }
                int key = mc.gameSettings.keyBindAttack.getKeyCode();
                KeyBinding.setKeyBindState(key,true);
                KeyBinding.onTick(key);
            }else if(System.currentTimeMillis() - hold > holdLength * 1000){
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
                this.updateV();
            }
        }
    }

    @Override
    public void onEnable(){
        super.onEnable();
        this.updateV();
    }

    private void updateV(){
        min = ExampleMod.instance.settingsManager.getSettingByName(this.name, "MinCPS").getValDouble();
        max = ExampleMod.instance.settingsManager.getSettingByName(this.name, "MaxCPS").getValDouble();

        if(min >= max){
            max = min + 1;
        }

        speed = 1.0 / ThreadLocalRandom.current().nextDouble(min - 0.2, max);
        holdLength = speed / ThreadLocalRandom.current().nextDouble(min, max);

    }
}
