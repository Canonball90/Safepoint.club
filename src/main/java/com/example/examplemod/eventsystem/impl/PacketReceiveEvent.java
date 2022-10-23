package com.example.examplemod.eventsystem.impl;

import net.minecraft.network.Packet;
import com.example.examplemod.eventsystem.Event;

public final class PacketReceiveEvent extends Event {
    private Packet packet;

    public PacketReceiveEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }
}
