package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements Runnable {
    public final /* synthetic */ com.ad.proxy.c.A a;
    public final /* synthetic */ g0 b;

    public e0(g0 g0Var, com.ad.proxy.c.A a) {
        this.b = g0Var;
        this.a = a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.a.f;
        g0 g0Var = this.b;
        if (i < g0Var.l) {
            return;
        }
        g0Var.m.put(Integer.valueOf(i), this.a);
        try {
            g0 g0Var2 = this.b;
            if (g0Var2.h == null || !g0Var2.e) {
                return;
            }
            while (true) {
                g0 g0Var3 = this.b;
                if (((com.ad.proxy.c.A) g0Var3.m.remove(Integer.valueOf(g0Var3.l))) == null) {
                    return;
                }
                this.b.h.write(this.a.g);
                this.b.h.flush();
                this.b.l++;
            }
        } catch (Exception e) {
            com.ad.proxy.g.F.a("TCPProxy", e);
            this.b.a();
        }
    }
}
