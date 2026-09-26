package e0;

import android.content.Context;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0.b f596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a3.l f597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k0.a f598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WorkDatabase f599d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l0.p f600e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f601f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Context f602g;

    public c0(Context context, d0.b bVar, a3.l lVar, k0.a aVar, WorkDatabase workDatabase, l0.p pVar, ArrayList arrayList) {
        j2.i.e(context, "context");
        j2.i.e(aVar, "foregroundProcessor");
        this.f596a = bVar;
        this.f597b = lVar;
        this.f598c = aVar;
        this.f599d = workDatabase;
        this.f600e = pVar;
        this.f601f = arrayList;
        Context applicationContext = context.getApplicationContext();
        j2.i.d(applicationContext, "getApplicationContext(...)");
        this.f602g = applicationContext;
        new d0.l();
    }
}
