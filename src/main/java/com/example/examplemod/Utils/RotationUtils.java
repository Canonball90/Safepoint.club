package com.example.examplemod.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class RotationUtils {
    public static float[] getNeededRotations(EntityLivingBase entityLivingBase) {
        double d = entityLivingBase.posX - Minecraft.getMinecraft().player.posX;
        double d2 = entityLivingBase.posZ - Minecraft.getMinecraft().player.posZ;
        double d3 = entityLivingBase.posY + (double)entityLivingBase.getEyeHeight() - (Minecraft.getMinecraft().player.getEntityBoundingBox().minY + (Minecraft.getMinecraft().player.getEntityBoundingBox().maxY - Minecraft.getMinecraft().player.getEntityBoundingBox().minY));
        double d4 = MathHelper.sqrt((double)(d * d + d2 * d2));
        float f = (float)(MathHelper.atan2((double)d2, (double)d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(MathHelper.atan2((double)d3, (double)d4) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

}
