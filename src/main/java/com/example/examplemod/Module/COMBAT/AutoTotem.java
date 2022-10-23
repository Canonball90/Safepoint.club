package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTotem
        extends Module {
    private final int OFFHAND_SLOT = 45;
    static Minecraft mc = Minecraft.getMinecraft();
    private boolean switching;
    private int last_slot;
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {

        if (mc.player.getHeldItemOffhand().getItem() != Items.TOTEM_OF_UNDYING) {
            int slot=finditemSlot();
            swap_items(slot, 0);
        }
    }


    private int finditemSlot() {
        if (Items.TOTEM_OF_UNDYING == AutoTotem.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        int i = 36;
        while (i >= 0) {
            final Item item = AutoTotem.mc.player.inventory.getStackInSlot(i).getItem();
            if (item == Items.TOTEM_OF_UNDYING) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
            else {
                --i;
            }
        }
        return -1;
    }

    public void swap_items(final int slot, final int step) {
        if (slot == -1) {
            return;
        }
        if (step == 0) {
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        }
        if (step == 1) {
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            this.switching = true;
            this.last_slot = slot;
        }
        if (step == 2) {
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            this.switching = false;
        }
        AutoTotem.mc.playerController.updateController();
    }
    public AutoTotem() {
        super("AutoTotem",0, Module.Category.COMBAT);
    }


}
