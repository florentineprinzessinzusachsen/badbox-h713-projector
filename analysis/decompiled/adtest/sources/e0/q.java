package e0;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q extends a.a {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f670p = d0.a0.g("WorkContinuationImpl");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final y f671h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f672i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d0.n f673j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List f674k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f675l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f676m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f677n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d0.l f678o;

    public q(y yVar, String str, d0.n nVar, List list, int i4) {
        this.f671h = yVar;
        this.f672i = str;
        this.f673j = nVar;
        this.f674k = list;
        this.f675l = new ArrayList(list.size());
        for (int i5 = 0; i5 < list.size(); i5++) {
            if (nVar == d0.n.f483d && ((d0.m0) list.get(i5)).f481b.f1351u != Long.MAX_VALUE) {
                throw new IllegalArgumentException("Next Schedule Time Override must be used with ExistingPeriodicWorkPolicyUPDATE (preferably) or KEEP");
            }
            String string = ((d0.m0) list.get(i5)).f480a.toString();
            j2.i.d(string, "toString(...)");
            this.f675l.add(string);
            this.f676m.add(string);
        }
    }

    public static HashSet K(q qVar) {
        HashSet hashSet = new HashSet();
        qVar.getClass();
        return hashSet;
    }

    public final d0.l J() {
        if (this.f677n) {
            d0.a0.e().h(f670p, "Already enqueued work ids (" + TextUtils.join(", ", this.f675l) + ")");
        } else {
            y yVar = this.f671h;
            this.f678o = l3.h.R(yVar.f693b.f416m, "EnqueueRunnable_" + this.f673j.name(), (m0.j) yVar.f695d.f184e, new a3.o(1, this));
        }
        return this.f678o;
    }
}
