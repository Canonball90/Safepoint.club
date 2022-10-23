package com.example.examplemod.eventsystem.impl;

import com.example.examplemod.eventsystem.Event;

public final class KeyPressEvent extends Event {
	private int key;

	public KeyPressEvent(int key) {
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
