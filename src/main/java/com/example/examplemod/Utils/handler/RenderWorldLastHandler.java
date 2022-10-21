package com.example.examplemod.Utils.handler;

import com.example.examplemod.Client;
import com.example.examplemod.Module.Module;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderWorldLastHandler {

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent renderWorldLastEvent) {
        for (Object modulew : Client.getModuleList()) {
            Module module = (Module) modulew;
            if (!module.toggled) continue;
            module.onRenderWorldLast(renderWorldLastEvent.getPartialTicks());
        }
    }
}
