package com.example.examplemod.Module.COMBAT;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTotem
        extends Module {
    private final int OFFHAND_SLOT = 45;
    static Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        ItemStack itemStack = AutoTotem.mc.player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);
        NonNullList nonNullList = Minecraft.getMinecraft().player.inventory.mainInventory;
        for (int i = 0; i < nonNullList.size(); ++i) {
            if (nonNullList.get(i) == ItemStack.EMPTY || itemStack != null && itemStack.getItem() == Items.TOTEM_OF_UNDYING || ((ItemStack)nonNullList.get(i)).getItem() != Items.TOTEM_OF_UNDYING) continue;
            new ItemStack(Items.TOTEM_OF_UNDYING);
            this.b(i);
            break;
        }
    }

    public AutoTotem() {
        super("AutoTotem",0, Module.Category.COMBAT);
    }

    public void b(int n) {
        n = 0;
        if (AutoTotem.mc.player.openContainer instanceof ContainerPlayer && AutoTotem.mc.player.ticksExisted % 5 == 0) {
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            int n2 = n + 36;
            AutoTotem.mc.playerController.windowClick(0, n2, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        }
    }
}
