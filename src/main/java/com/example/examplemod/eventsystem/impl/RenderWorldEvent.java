package com.example.examplemod.eventsystem.impl;

import com.example.examplemod.eventsystem.Event;

public final class RenderWorldEvent extends Event {
	private float partialTicks;

	public RenderWorldEvent(float partialTicks) {
		this.partialTicks = partialTicks;
	}

	public float getPartialTicks() {
		return partialTicks;
	}
}