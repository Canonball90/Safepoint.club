package com.example.examplemod.Module.CLIENT;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.RenderUtils;
import com.example.examplemod.Utils.TimerUtil;
import com.example.examplemod.Utils.notification.NotificationUtil;
import font.FontUtils;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;

public class Notification extends Module {
    public Notification() {
        super("Notification", 0, Category.CLIENT);
    }

}
