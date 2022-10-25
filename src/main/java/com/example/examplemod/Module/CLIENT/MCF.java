package com.example.examplemod.Module.CLIENT;

import com.example.examplemod.Module.Module;
import com.example.examplemod.Utils.ChatUtils;
import com.example.examplemod.Utils.FriendsUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Mouse;

public class MCF extends Module {
    public MCF() {
        super("MCF", 0, Module.Category.CLIENT);
    }

    @SubscribeEvent
    public void onInput(InputEvent.MouseInputEvent event) {
        if(Mouse.isButtonDown(2)) {
            if (mc.objectMouseOver.typeOfHit.equals(RayTraceResult.Type.ENTITY) && mc.objectMouseOver.entityHit instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)mc.objectMouseOver.entityHit;
                final String name = player.getName();
                if (FriendsUtil.isFriend(player)) {
                    FriendsUtil.removeFriend(player);
                    ChatUtils.sendMessage("Removed " + name);
                }
                else {
                    FriendsUtil.addFriend(player);
                    ChatUtils.sendMessage("Added " + name);
                }
            }
            }
        }
    }

