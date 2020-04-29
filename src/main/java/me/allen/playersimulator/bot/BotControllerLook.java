package me.allen.playersimulator.bot;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.TrigMath;

public class BotControllerLook
        extends ControllerLook {
    private final EntityBot a;
    private float b;
    private float c;
    private boolean d;
    private double e;
    private double f;
    private double g;

    public BotControllerLook(EntityBot bot) {
        super(new EntitySheep(bot.world));
        this.a = bot;
    }

    public void a(Entity entity, float f, float f1) {
        this.e = entity.locX;
        if ((entity instanceof EntityLiving)) {
            this.f = (entity.locY + entity.getHeadHeight());
        } else {
            this.f = ((entity.getBoundingBox().b + entity.getBoundingBox().e) / 2.0D);
        }
        this.g = entity.locZ;
        this.b = f;
        this.c = f1;
        this.d = true;
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.e = d0;
        this.f = d1;
        this.g = d2;
        this.b = f;
        this.c = f1;
        this.d = true;
    }

    public void a() {
        this.a.pitch = 0.0F;
        if (this.d) {
            this.d = false;
            double d0 = this.e - this.a.locX;
            double d1 = this.f - (this.a.locY + this.a.getHeadHeight());
            double d2 = this.g - this.a.locZ;
            double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);

            float f = (float) (TrigMath.atan2(d2, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
            float f1 = (float) -(TrigMath.atan2(d1, d3) * 180.0D / 3.1415927410125732D);


            this.a.pitch = a(this.a.pitch, f1, this.c);
            this.a.aK = a(this.a.aK, f, this.b);
        } else {
            this.a.aK = a(this.a.aK, this.a.aI, 10.0F);
        }
        float f2 = MathHelper.g(this.a.aK - this.a.aI);
        if (!this.a.getNavigation().g()) {
            if (f2 < -75.0F) {
                this.a.aK = (this.a.aI - 75.0F);
            }
            if (f2 > 75.0F) {
                this.a.aK = (this.a.aI + 75.0F);
            }
        }
    }

    private float a(float f, float f1, float f2) {
        float f3 = MathHelper.g(f1 - f);
        if (f3 > f2) {
            f3 = f2;
        }
        if (f3 < -f2) {
            f3 = -f2;
        }
        return f + f3;
    }
}