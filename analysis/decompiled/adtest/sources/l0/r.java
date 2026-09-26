package l0;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1357d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ long f1358e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f1359f;

    public /* synthetic */ r(long j4, String str, int i4) {
        this.f1357d = i4;
        this.f1358e = j4;
        this.f1359f = str;
    }

    @Override // i2.l
    public final Object h(Object obj) throws Exception {
        switch (this.f1357d) {
            case 0:
                long j4 = this.f1358e;
                String str = this.f1359f;
                w.a aVar = (w.a) obj;
                j2.i.e(aVar, "_connection");
                w.c cVarP = aVar.P("UPDATE workspec SET schedule_requested_at=? WHERE id=?");
                try {
                    cVarP.a(1, j4);
                    cVarP.o(2, str);
                    cVarP.F();
                    return Integer.valueOf(l0.y(aVar));
                } finally {
                    cVarP.close();
                }
            default:
                long j5 = this.f1358e;
                String str2 = this.f1359f;
                w.a aVar2 = (w.a) obj;
                j2.i.e(aVar2, "_connection");
                w.c cVarP2 = aVar2.P("UPDATE workspec SET last_enqueue_time=? WHERE id=?");
                try {
                    cVarP2.a(1, j5);
                    cVarP2.o(2, str2);
                    cVarP2.F();
                    return u1.k.f2301a;
                } finally {
                    cVarP2.close();
                }
        }
    }
}
