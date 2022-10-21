package com.example.examplemod.Module.HUD;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.particle.ParticleSystem;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Particleeesss extends Module {

    private final ParticleSystem particleSystem;

    public Particleeesss() {
        super("Particles", 0, Category.HUD);
        this.particleSystem = new ParticleSystem(150);
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post e) {
        if(mc.currentScreen != null){
            this.particleSystem.tick(5);
            this.particleSystem.render();
        }
    }
}

