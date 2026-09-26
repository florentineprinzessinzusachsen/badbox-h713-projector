package com.ad.proxy.b;

/* JADX INFO: loaded from: classes.dex */
public final class B implements Runnable {
    public final /* synthetic */ C a;

    public B(C c) {
        this.a = c;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C c = this.a;
        c.getClass();
        com.ad.proxy.g.F.a("ActiveReporter", "------开始执行活跃上报任务-----");
        try {
            com.ad.proxy.f.C.a(new A(c));
        } catch (Exception e) {
            com.ad.proxy.g.F.b("ActiveReporter", "活跃上报失败：" + e.getMessage());
        }
    }
}
