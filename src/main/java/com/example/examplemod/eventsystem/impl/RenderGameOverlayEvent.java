package com.example.examplemod.eventsystem.impl;

import net.minecraft.client.gui.ScaledResolution;
import com.example.examplemod.eventsystem.Event;

public final class RenderGameOverlayEvent extends Event {
    private final ScaledResolution resolution;
    private final float partialTicks;

    public RenderGameOverlayEvent(ScaledResolution resolution, float partialTicks) {
        this.resolution = resolution;
        this.partialTicks = partialTicks;
    }

    public ScaledResolution getResolution() {
        return resolution;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
