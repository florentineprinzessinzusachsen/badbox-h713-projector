package com.ad.proxy.b;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements H {
    public final /* synthetic */ a0 a;
    public final /* synthetic */ com.ad.proxy.c.A b;
    public final /* synthetic */ ArrayList c;
    public final /* synthetic */ int d;
    public final /* synthetic */ c0 e;

    public b0(c0 c0Var, a0 a0Var, com.ad.proxy.c.A a, ArrayList arrayList, int i) {
        this.e = c0Var;
        this.a = a0Var;
        this.b = a;
        this.c = arrayList;
        this.d = i;
    }

    @Override // com.ad.proxy.b.H
    public final void a(boolean z) {
        if (z) {
            return;
        }
        this.e.a(this.a, this.b, this.c, this.d + 1);
    }
}
