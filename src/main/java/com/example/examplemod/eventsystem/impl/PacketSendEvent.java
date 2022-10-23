package com.example.examplemod.eventsystem.impl;

import net.minecraft.network.Packet;
import com.example.examplemod.eventsystem.Event;

public final class PacketSendEvent extends Event {
	private Packet packet;

	public PacketSendEvent(Packet packet) {
		this.packet = packet;
	}

	public Packet getPacket() {
		return packet;
	}

	public void setPacket(Packet packet) {
		this.packet = packet;
	}
}