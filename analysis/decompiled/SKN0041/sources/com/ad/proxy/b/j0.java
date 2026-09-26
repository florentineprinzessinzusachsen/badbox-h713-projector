package com.ad.proxy.b;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j0 implements H {
    public final /* synthetic */ com.ad.proxy.c.A a;
    public final /* synthetic */ k0 b;

    public j0(k0 k0Var, com.ad.proxy.c.A a) {
        this.b = k0Var;
        this.a = a;
    }

    @Override // com.ad.proxy.b.H
    public final void a(boolean z) {
        if (z) {
            return;
        }
        com.ad.proxy.g.F.b("UDPProxy", "sendPacket failed");
        c0 c0VarA = c0.a();
        a0 a0Var = this.b.a;
        com.ad.proxy.c.A a = this.a;
        c0 c0VarA2 = c0.a();
        c0VarA2.getClass();
        c0VarA.a(a0Var, a, new ArrayList(c0VarA2.a.values()), 0);
    }
}
