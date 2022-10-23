package com.example.examplemod.eventsystem.impl;

import net.minecraft.util.EnumHandSide;
import com.example.examplemod.eventsystem.Event;

public final class TransformSideFirstPersonEvent extends Event {
    private final EnumHandSide enumHandSide;

    public TransformSideFirstPersonEvent(EnumHandSide enumHandSide) {
        this.enumHandSide = enumHandSide;
    }

    public EnumHandSide getEnumHandSide() {
        return this.enumHandSide;
    }
}
