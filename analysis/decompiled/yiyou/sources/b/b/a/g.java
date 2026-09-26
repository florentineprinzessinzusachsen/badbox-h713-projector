package b.b.a;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: GsonBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    private String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.b.a.y.d f1551a = b.b.a.y.d.g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private u f1552b = u.f1562a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f1553c = d.f1536a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Type, h<?>> f1554d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<w> f1555e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final List<w> f1556f = new ArrayList();
    private boolean g = false;
    private int i = 2;
    private int j = 2;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;

    public f a() {
        List<w> arrayList = new ArrayList<>(this.f1555e.size() + this.f1556f.size() + 3);
        arrayList.addAll(this.f1555e);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(this.f1556f);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        a(this.h, this.i, this.j, arrayList);
        return new f(this.f1551a, this.f1553c, this.f1554d, this.g, this.k, this.o, this.m, this.n, this.p, this.l, this.f1552b, this.h, this.i, this.j, this.f1555e, this.f1556f, arrayList);
    }

    public g b() {
        this.g = true;
        return this;
    }

    public g c() {
        this.n = true;
        return this;
    }

    private void a(String str, int i, int i2, List<w> list) {
        a aVar;
        a aVar2;
        a aVar3;
        if (str != null && !"".equals(str.trim())) {
            a aVar4 = new a(Date.class, str);
            aVar2 = new a(Timestamp.class, str);
            aVar3 = new a(java.sql.Date.class, str);
            aVar = aVar4;
        } else {
            if (i == 2 || i2 == 2) {
                return;
            }
            aVar = new a(Date.class, i, i2);
            a aVar5 = new a(Timestamp.class, i, i2);
            a aVar6 = new a(java.sql.Date.class, i, i2);
            aVar2 = aVar5;
            aVar3 = aVar6;
        }
        list.add(b.b.a.y.n.n.a(Date.class, aVar));
        list.add(b.b.a.y.n.n.a(Timestamp.class, aVar2));
        list.add(b.b.a.y.n.n.a(java.sql.Date.class, aVar3));
    }
}
