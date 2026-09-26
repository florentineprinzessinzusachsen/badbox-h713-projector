package f3;

import a3.a0;
import a3.d0;
import a3.t;
import a3.u;
import e3.p;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f898b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f899c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e3.h f900d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a0 f901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f903g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f904h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f905i;

    public i(p pVar, ArrayList arrayList, int i4, e3.h hVar, a0 a0Var, int i5, int i6, int i7) {
        this.f897a = pVar;
        this.f898b = arrayList;
        this.f899c = i4;
        this.f900d = hVar;
        this.f901e = a0Var;
        this.f902f = i5;
        this.f903g = i6;
        this.f904h = i7;
    }

    public static i a(i iVar, int i4, e3.h hVar, a0 a0Var, int i5) {
        if ((i5 & 1) != 0) {
            i4 = iVar.f899c;
        }
        int i6 = i4;
        if ((i5 & 2) != 0) {
            hVar = iVar.f900d;
        }
        e3.h hVar2 = hVar;
        if ((i5 & 4) != 0) {
            a0Var = iVar.f901e;
        }
        a0 a0Var2 = a0Var;
        int i7 = iVar.f902f;
        int i8 = iVar.f903g;
        int i9 = iVar.f904h;
        j2.i.e(a0Var2, "request");
        return new i(iVar.f897a, iVar.f898b, i6, hVar2, a0Var2, i7, i8, i9);
    }

    public final d0 b(a0 a0Var) {
        j2.i.e(a0Var, "request");
        ArrayList arrayList = this.f898b;
        int size = arrayList.size();
        int i4 = this.f899c;
        if (i4 >= size) {
            throw new IllegalStateException("Check failed.");
        }
        this.f905i++;
        e3.h hVar = this.f900d;
        if (hVar != null) {
            if (!((e3.i) hVar.f751f).d().f((t) a0Var.f63c)) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i4 - 1) + " must retain the same host and port").toString());
            }
            if (this.f905i != 1) {
                throw new IllegalStateException(("network interceptor " + arrayList.get(i4 - 1) + " must call proceed() exactly once").toString());
            }
        }
        int i5 = i4 + 1;
        i iVarA = a(this, i5, null, a0Var, 58);
        u uVar = (u) arrayList.get(i4);
        d0 d0VarA = uVar.a(iVarA);
        if (d0VarA == null) {
            throw new NullPointerException("interceptor " + uVar + " returned null");
        }
        if (hVar == null || i5 >= arrayList.size() || iVarA.f905i == 1) {
            return d0VarA;
        }
        throw new IllegalStateException(("network interceptor " + uVar + " must call proceed() exactly once").toString());
    }
}
