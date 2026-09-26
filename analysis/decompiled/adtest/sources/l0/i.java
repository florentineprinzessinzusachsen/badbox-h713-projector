package l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1316d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f1317e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f1318f;

    public /* synthetic */ i(int i4, String str) {
        this.f1316d = 2;
        this.f1318f = i4;
        this.f1317e = str;
    }

    @Override // i2.l
    public final Object h(Object obj) throws Exception {
        h hVar;
        switch (this.f1316d) {
            case 0:
                String str = this.f1317e;
                int i4 = this.f1318f;
                w.a aVar = (w.a) obj;
                j2.i.e(aVar, "_connection");
                w.c cVarP = aVar.P("SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?");
                try {
                    cVarP.o(1, str);
                    cVarP.a(2, i4);
                    int iD = l3.h.D(cVarP, "work_spec_id");
                    int iD2 = l3.h.D(cVarP, "generation");
                    int iD3 = l3.h.D(cVarP, "system_id");
                    if (cVarP.F()) {
                        hVar = new h(cVarP.n(iD), (int) cVarP.getLong(iD2), (int) cVarP.getLong(iD3));
                        break;
                    } else {
                        hVar = null;
                    }
                    return hVar;
                } finally {
                    cVarP.close();
                }
            case 1:
                String str2 = this.f1317e;
                int i5 = this.f1318f;
                w.a aVar2 = (w.a) obj;
                j2.i.e(aVar2, "_connection");
                w.c cVarP2 = aVar2.P("UPDATE workspec SET next_schedule_time_override=9223372036854775807 WHERE (id=? AND next_schedule_time_override_generation=?)");
                try {
                    cVarP2.o(1, str2);
                    cVarP2.a(2, i5);
                    cVarP2.F();
                } finally {
                    cVarP2.close();
                }
                break;
            default:
                int i6 = this.f1318f;
                String str3 = this.f1317e;
                w.a aVar3 = (w.a) obj;
                j2.i.e(aVar3, "_connection");
                w.c cVarP3 = aVar3.P("UPDATE workspec SET stop_reason=? WHERE id=?");
                try {
                    cVarP3.a(1, i6);
                    cVarP3.o(2, str3);
                    cVarP3.F();
                } finally {
                    cVarP3.close();
                }
                break;
        }
        return u1.k.f2301a;
    }

    public /* synthetic */ i(String str, int i4, int i5) {
        this.f1316d = i5;
        this.f1317e = str;
        this.f1318f = i4;
    }
}
