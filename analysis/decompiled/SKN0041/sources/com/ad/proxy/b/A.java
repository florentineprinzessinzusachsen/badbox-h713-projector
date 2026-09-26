package com.ad.proxy.b;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class A implements com.ad.proxy.f.F {
    public final /* synthetic */ C a;

    public A(C c) {
        this.a = c;
    }

    @Override // com.ad.proxy.f.F
    public final void a(Object obj) {
        com.ad.proxy.e.B b = (com.ad.proxy.e.B) obj;
        com.ad.proxy.g.F.a("ActiveReporter", "活跃上报成功");
        a0.n = b.e;
        int i = 0;
        if (b.d != C.c) {
            com.ad.proxy.g.F.a("ActiveReporter", "下次活跃上报时间间隔：" + C.c);
            C.c = (long) b.d;
            this.a.b.cancel(false);
            C c = this.a;
            long j = C.c;
            c.b = c.a.scheduleWithFixedDelay(new B(c), j, j, TimeUnit.SECONDS);
        }
        this.a.getClass();
        com.ad.proxy.d.A.a = b.c * 1000;
        c0 c0VarA = c0.a();
        c0VarA.getClass();
        ArrayList arrayList = new ArrayList(c0VarA.a.values());
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj2 = arrayList.get(i2);
            i2++;
            a0 a0Var = (a0) obj2;
            if (a0Var.l != b.c * 1000) {
                ScheduledFuture scheduledFuture = a0Var.k;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(true);
                }
                int i3 = com.ad.proxy.d.A.a;
                a0Var.l = i3;
                long j2 = i3;
                a0Var.k = a0Var.f.scheduleWithFixedDelay(new N(a0Var), j2, j2, TimeUnit.MILLISECONDS);
            }
        }
        HashMap map = new HashMap();
        ArrayList arrayList2 = b.a;
        int size2 = arrayList2.size();
        int i4 = 0;
        while (i4 < size2) {
            Object obj3 = arrayList2.get(i4);
            i4++;
            com.ad.proxy.e.A a = (com.ad.proxy.e.A) obj3;
            map.put(a.a(), a);
        }
        int size3 = arrayList.size();
        int i5 = 0;
        while (i5 < size3) {
            Object obj4 = arrayList.get(i5);
            i5++;
            a0 a0Var2 = (a0) obj4;
            if (!map.containsKey(a0Var2.m.a())) {
                c0.a().a.remove(a0Var2.m.a());
                com.ad.proxy.g.F.a("SocketClient", "Closing socket");
                a0Var2.h = false;
                Socket socket = a0Var2.a;
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        com.ad.proxy.g.F.a("E", e);
                    }
                }
                try {
                    a0Var2.e.shutdownNow();
                } catch (Exception e2) {
                    com.ad.proxy.g.F.a("SocketClient", e2);
                }
            }
        }
        ArrayList arrayList3 = b.a;
        int size4 = arrayList3.size();
        while (i < size4) {
            Object obj5 = arrayList3.get(i);
            i++;
            com.ad.proxy.e.A a2 = (com.ad.proxy.e.A) obj5;
            if (!c0.a().a.containsKey(a2.a())) {
                a0 a0Var3 = new a0(a2, b.b);
                a0Var3.h = true;
                new Thread(new S(a0Var3)).start();
                c0.a().a.put(a0Var3.m.a(), a0Var3);
            }
        }
    }

    @Override // com.ad.proxy.f.F
    public final void a(int i, String str) {
        com.ad.proxy.g.F.b("ActiveReporter", "活跃上报失败：" + str);
    }
}
