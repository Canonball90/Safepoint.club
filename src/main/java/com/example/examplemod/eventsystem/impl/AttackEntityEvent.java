package com.example.examplemod.eventsystem.impl;

import net.minecraft.entity.Entity;
import com.example.examplemod.eventsystem.Event;

public final class AttackEntityEvent extends Event {
    private final Entity entity;

    public AttackEntityEvent(Entity targetEntity) {
        this.entity = targetEntity;
    }

    public Entity getEntity() {
        return entity;
    }
}
