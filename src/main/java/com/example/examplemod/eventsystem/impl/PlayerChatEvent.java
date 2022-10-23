package com.example.examplemod.eventsystem.impl;

import com.example.examplemod.eventsystem.Event;

public final class PlayerChatEvent extends Event {
    private String text;

    public PlayerChatEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
