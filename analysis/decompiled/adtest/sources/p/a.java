package p;

import android.content.Context;
import android.content.Intent;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1578b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final x.c f1579c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d0.i f1580d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f1581e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1582f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r f1583g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Executor f1584h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Executor f1585i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Intent f1586j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f1587k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f1588l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Set f1589m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f1590n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final File f1591o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Callable f1592p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final List f1593q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final List f1594r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f1595s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final w.b f1596t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final y1.h f1597u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1598v;

    public a(Context context, String str, x.c cVar, d0.i iVar, List list, boolean z3, r rVar, Executor executor, Executor executor2, Intent intent, boolean z4, boolean z5, Set set, String str2, File file, Callable callable, List list2, List list3, boolean z6, w.b bVar, y1.h hVar) {
        j2.i.e(context, "context");
        j2.i.e(iVar, "migrationContainer");
        j2.i.e(executor, "queryExecutor");
        j2.i.e(executor2, "transactionExecutor");
        j2.i.e(list2, "typeConverters");
        j2.i.e(list3, "autoMigrationSpecs");
        this.f1577a = context;
        this.f1578b = str;
        this.f1579c = cVar;
        this.f1580d = iVar;
        this.f1581e = list;
        this.f1582f = z3;
        this.f1583g = rVar;
        this.f1584h = executor;
        this.f1585i = executor2;
        this.f1586j = intent;
        this.f1587k = z4;
        this.f1588l = z5;
        this.f1589m = set;
        this.f1590n = str2;
        this.f1591o = file;
        this.f1592p = callable;
        this.f1593q = list2;
        this.f1594r = list3;
        this.f1595s = z6;
        this.f1596t = bVar;
        this.f1597u = hVar;
        this.f1598v = true;
    }
}
