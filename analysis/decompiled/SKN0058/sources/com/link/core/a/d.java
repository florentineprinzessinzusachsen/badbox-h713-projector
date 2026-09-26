package com.link.core.a;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static int d;
    public int a = 0;
    public c b = null;
    public c c = null;

    public final c a() {
        c cVar = this.b;
        if (cVar == null) {
            return null;
        }
        c cVar2 = cVar.b;
        this.b = cVar2;
        cVar.b = null;
        if (cVar2 == null) {
            this.c = null;
        }
        this.a--;
        d--;
        return cVar;
    }

    public final void a(c cVar) {
        if (cVar.b != null) {
            return;
        }
        this.a++;
        d++;
        if (this.b == null) {
            this.c = cVar;
            this.b = cVar;
        } else {
            this.c.b = cVar;
            this.c = cVar;
        }
    }
}
