package h3;

import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1122d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q f1123e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f1124f;

    public /* synthetic */ l(q qVar, int i4, Object obj, int i5) {
        this.f1122d = i5;
        this.f1123e = qVar;
        this.f1124f = i4;
    }

    private final Object c() {
        q qVar = this.f1123e;
        int i4 = this.f1124f;
        qVar.f1141n.getClass();
        try {
            qVar.f1153z.J(i4, b.CANCEL);
            synchronized (qVar) {
                qVar.B.remove(Integer.valueOf(i4));
            }
        } catch (IOException unused) {
        }
        return u1.k.f2301a;
    }

    private final Object e() {
        q qVar = this.f1123e;
        int i4 = this.f1124f;
        qVar.f1141n.getClass();
        synchronized (qVar) {
            qVar.B.remove(Integer.valueOf(i4));
        }
        return u1.k.f2301a;
    }

    @Override // i2.a
    public final Object a() {
        switch (this.f1122d) {
            case 0:
                return c();
            case 1:
                return e();
            default:
                q qVar = this.f1123e;
                int i4 = this.f1124f;
                qVar.f1141n.getClass();
                try {
                    qVar.f1153z.J(i4, b.CANCEL);
                    synchronized (qVar) {
                        qVar.B.remove(Integer.valueOf(i4));
                    }
                } catch (IOException unused) {
                }
                return u1.k.f2301a;
        }
    }

    public /* synthetic */ l(q qVar, int i4, List list, boolean z3) {
        this.f1122d = 2;
        this.f1123e = qVar;
        this.f1124f = i4;
    }
}
