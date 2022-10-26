package com.example.examplemod.Utils;

import com.example.examplemod.Client;
import com.example.examplemod.ExampleMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL32;

import static com.example.examplemod.Utils.RenderUtils.drawRect;

public class RenderUtil {
    public static void trace(Minecraft mc, Entity e, float partialTicks, int mode) {
        if (mc.getRenderManager().renderViewEntity != null) {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glLineWidth(2F);

            GL11.glPushMatrix();
            GL11.glDepthMask(false);

            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINES);

            RenderManager r = mc.getRenderManager();

            Vec3d v = new Vec3d(0.0D, 0.0D, 1.0D).rotatePitch(-((float) Math.toRadians((double) mc.player.rotationPitch))).rotateYaw(-((float) Math.toRadians((double) mc.player.rotationYaw)));

            GL11.glVertex3d(v.x, mc.player.getEyeHeight() + v.y, v.z);

            double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * partialTicks;
            double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * partialTicks;
            double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * partialTicks;

            GL11.glVertex3d(x - r.viewerPosX, y - r.viewerPosY + 0.25, z - r.viewerPosZ);

            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }
    }

    public static void FillLine(Entity entity, AxisAlignedBB box) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        RenderGlobal.renderFilledBox(box, (float) ExampleMod.instance.settingsManager.getSettingByName("BoxESP", "Red").getValDouble(), (float) ExampleMod.instance.settingsManager.getSettingByName("BoxESP", "Green").getValDouble(), (float) ExampleMod.instance.settingsManager.getSettingByName("BoxESP", "Blue").getValDouble(), (float) ExampleMod.instance.settingsManager.getSettingByName("BoxESP", "Alpha").getValDouble());
        RenderGlobal.drawSelectionBoundingBox(box, 0, 1, 0, 0.8F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void renderEntity(EntityLivingBase entity, int scale, int posX, int posY) {
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        GL11.glPushAttrib(GL11.GL_SCISSOR_BIT);
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GlStateManager.clear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glPopAttrib();
        GlStateManager.enableDepth();
        GlStateManager.disableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.color(1,1,1,1);
        GuiInventory.drawEntityOnScreen(posX, posY,scale,1,1,entity);
        GlStateManager.popMatrix();
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
    }

    public static void blockESP(BlockPos blockPos) {
        GL11.glPushMatrix();

        double x =
                blockPos.getX()
                        - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        double y =
                blockPos.getY()
                        - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        double z =
                blockPos.getZ()
                        - Minecraft.getMinecraft().getRenderManager().viewerPosZ;

        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        GL11.glDepthMask(false);

        RenderGlobal.renderFilledBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 0, 1, 0, 0.5F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL11.glDepthMask(true);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public static void traceBlock(Minecraft mc, BlockPos blockPos, float partialTicks, int mode) {
        if (mc.getRenderManager().renderViewEntity != null) {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glLineWidth(2F);

            GL11.glPushMatrix();
            GL11.glDepthMask(false);
            GL11.glColor4d(0, mode == 1 ? 1 : 0, 0, 1);

            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINES);

            RenderManager r = mc.getRenderManager();

            Vec3d v = new Vec3d(0.0D, 0.0D, 1.0D).rotatePitch(-((float) Math.toRadians((double) mc.player.rotationPitch))).rotateYaw(-((float) Math.toRadians((double) mc.player.rotationYaw)));

            GL11.glVertex3d(v.x, mc.player.getEyeHeight() + v.y, v.z);

            double x =
                    blockPos.getX()
                            - Minecraft.getMinecraft().getRenderManager().viewerPosX;
            double y =
                    blockPos.getY()
                            - Minecraft.getMinecraft().getRenderManager().viewerPosY;
            double z =
                    blockPos.getZ()
                            - Minecraft.getMinecraft().getRenderManager().viewerPosZ;

            GL11.glVertex3d(x - r.viewerPosX, y - r.viewerPosY + 0.25, z - r.viewerPosZ);

            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glPopMatrix();
        }
    }

    public static void drawGradientSideways(final double left, final double top, final double right, final double bottom, final int col1, final int col2)
    {
        final float f = (col1 >> 24 & 0xFF) / 255.0f;
        final float f2 = (col1 >> 16 & 0xFF) / 255.0f;
        final float f3 = (col1 >> 8 & 0xFF) / 255.0f;
        final float f4 = (col1 & 0xFF) / 255.0f;
        final float f5 = (col2 >> 24 & 0xFF) / 255.0f;
        final float f6 = (col2 >> 16 & 0xFF) / 255.0f;
        final float f7 = (col2 >> 8 & 0xFF) / 255.0f;
        final float f8 = (col2 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static void drawBorderedRect(double x, double y, double x1, double y1, double width, int internalColor, int borderColor)
    {
        enableGL2D();
        fakeGuiRect(x + width, y + width, x1 - width, y1 - width, internalColor);
        fakeGuiRect(x + width, y, x1 - width, y + width, borderColor);
        fakeGuiRect(x, y, x + width, y1, borderColor);
        fakeGuiRect(x1 - width, y, x1, y1, borderColor);
        fakeGuiRect(x + width, y1 - width, x1 - width, y1, borderColor);
        disableGL2D();
    }

    private static void enableGL2D()
    {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(true);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
    }

    private static void disableGL2D()
    {
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }

    public static void fakeGuiRect(double left, double top, double right, double bottom, int color)
    {
        if (left < right)
        {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 0xFF) / 255.0F;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(left, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, bottom, 0.0D).endVertex();
        bufferbuilder.pos(right, top, 0.0D).endVertex();
        bufferbuilder.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void prepare() {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.disableCull();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ZERO, GL11.GL_ONE);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL32.GL_DEPTH_CLAMP);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
    }

    public static void release() {
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL32.GL_DEPTH_CLAMP);
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public static void drawSolidBox(AxisAlignedBB axisAlignedBB) {
        GL11.glBegin((int) 7);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.minY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.minY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.minY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.minY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.minY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.minY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.minY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.minY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.minY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.minY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.minY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.minY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.minZ);
        GL11.glVertex3d((double) axisAlignedBB.minX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.maxZ);
        GL11.glVertex3d((double) axisAlignedBB.maxX, (double) axisAlignedBB.maxY, (double) axisAlignedBB.minZ);
        GL11.glEnd();
    }

    public static void drawBorderedRect(float x, float y, float x1, float y1, final int insideC, final int borderC) {
        enableGL2D();
        x *= 2.0f;
        x1 *= 2.0f;
        y *= 2.0f;
        y1 *= 2.0f;
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawVLine(x, y, y1 - 1.0f, borderC);
        drawVLine(x1 - 1.0f, y, y1, borderC);
        drawHLine(x, x1 - 1.0f, y, borderC);
        drawHLine(x, x1 - 2.0f, y1 - 1.0f, borderC);
        drawRect((int) (x + 1.0f), (int) (y + 1.0f), (int) (x1 - 1.0f), (int) (y1 - 1.0f), insideC);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        disableGL2D();
    }

    public static void drawVLine(final float paramFloat1, float paramFloat2, float paramFloat3, final int paramInt) {
        if (paramFloat3 < paramFloat2) {
            final float f = paramFloat2;
            paramFloat2 = paramFloat3;
            paramFloat3 = f;
        }
        drawRect((int) paramFloat1, (int) (paramFloat2 + 1.0f), (int) (paramFloat1 + 1.0f), (int) paramFloat3, paramInt);
    }

    public static void drawHLine(float paramFloat1, float paramFloat2, final float paramFloat3, final int paramInt) {
        if (paramFloat2 < paramFloat1) {
            final float f = paramFloat1;
            paramFloat1 = paramFloat2;
            paramFloat2 = f;
        }
        drawRect((int) paramFloat1, (int) paramFloat3, (int) (paramFloat2 + 1.0f), (int) (paramFloat3 + 1.0f), paramInt);
    }

}
