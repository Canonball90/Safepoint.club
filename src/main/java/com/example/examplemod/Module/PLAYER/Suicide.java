package com.example.examplemod.Module.PLAYER;

import com.example.examplemod.Module.Module;

public class Suicide extends Module {
    public Suicide() {
        super("Suicide", 0, Category.PLAYER);
    }

    @Override
    public void onEnable() {
        mc.player.sendChatMessage("/kill");
        super.onEnable();
        toggle();
    }
}
