package com.example.examplemod.Module.RENDER;

import com.example.examplemod.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class BulletTracer extends Module {

    private final List<Bullet> bullets;

    static Minecraft mc = Minecraft.getMinecraft();

    public BulletTracer() {
        super("BulletTracer", 0, Category.RENDER);
        this.bullets = new CopyOnWriteArrayList<Bullet>();
    }

    @SubscribeEvent
    public void onTick(TickEvent.WorldTickEvent e) {
        //this.info = String.valueOf(this.bullets.size());
        this.bullets.forEach(bullet -> {
            if (bullet.time <= 0.0) {
                this.bullets.remove(bullet);
            }
            bullet.time -= 0.05;
            return;
        });
        final Bullet bullet2;
        final Object o;

    }

    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(2.0f);
        AtomicInteger i = new AtomicInteger();
        AtomicReference<List<Vec3d>> pos = null;
        this.bullets.stream().filter(bullet -> bullet.vec3d.size() > 2).forEach(bullet -> {
            GL11.glBegin(1);
            for (i.set(1); i.get() < bullet.vec3d.size(); i.incrementAndGet()) {
                GL11.glColor4d((double)(255 / 255.0f), (double)(10 / 255.0f), (double)(10 / 255.0f), (double)(200 / 255.0f));
                pos.set(bullet.vec3d);
                GL11.glVertex3d(pos.get().get(i.get()).x - BulletTracer.mc.getRenderManager().viewerPosX, pos.get().get(i.get()).y - BulletTracer.mc.getRenderManager().viewerPosY, pos.get().get(i.get()).z - BulletTracer.mc.getRenderManager().viewerPosZ);
                GL11.glVertex3d(pos.get().get(i.get() - 1).x - BulletTracer.mc.getRenderManager().viewerPosX, pos.get().get(i.get() - 1).y - BulletTracer.mc.getRenderManager().viewerPosY, pos.get().get(i.get() - 1).z - BulletTracer.mc.getRenderManager().viewerPosZ);
            }
            GL11.glEnd();
            return;
        });
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    private Bullet getByEntity(final EntityArrow entityArrow) {
        return this.bullets.stream().filter(e -> e.entity == entityArrow).findFirst().orElse(null);
    }

    class Bullet
    {
        int alpha;
        EntityArrow entity;
        double time;
        List<Vec3d> vec3d;

        Bullet(final EntityArrow entity, final double time, final Vec3d vec3d) {
            this.entity = entity;
            this.time = time;
            this.alpha = 255;
            this.vec3d = new ArrayList<Vec3d>();
            vec3d.add(vec3d);
        }

        void update() {
            if (this.alpha <= 0) {
                BulletTracer.this.bullets.remove(this);
            }
            else {
                this.alpha -= (int)Math.min(Math.max(283.33334f * Minecraft.getDebugFPS(), 0.0f), 255.0f);
            }
        }
    }


}
