package b.b.a.y.n;

import b.b.a.r;
import b.b.a.s;
import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> extends v<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final s<T> f1670a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.b.a.k<T> f1671b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final b.b.a.f f1672c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b.b.a.z.a<T> f1673d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final w f1674e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final l<T>.b f1675f = new b();
    private v<T> g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    private final class b implements r, b.b.a.j {
        private b(l lVar) {
        }
    }

    public l(s<T> sVar, b.b.a.k<T> kVar, b.b.a.f fVar, b.b.a.z.a<T> aVar, w wVar) {
        this.f1670a = sVar;
        this.f1671b = kVar;
        this.f1672c = fVar;
        this.f1673d = aVar;
        this.f1674e = wVar;
    }

    private v<T> b() {
        v<T> vVar = this.g;
        if (vVar != null) {
            return vVar;
        }
        v<T> vVarA = this.f1672c.a(this.f1674e, this.f1673d);
        this.g = vVarA;
        return vVarA;
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a */
    public T a2(JsonReader jsonReader) {
        if (this.f1671b == null) {
            return b().a2(jsonReader);
        }
        b.b.a.l lVarA = b.b.a.y.l.a(jsonReader);
        if (lVarA.e()) {
            return null;
        }
        return this.f1671b.a(lVarA, this.f1673d.b(), this.f1675f);
    }

    @Override // b.b.a.v
    public void a(JsonWriter jsonWriter, T t) throws IOException {
        s<T> sVar = this.f1670a;
        if (sVar == null) {
            b().a(jsonWriter, t);
        } else if (t == null) {
            jsonWriter.nullValue();
        } else {
            b.b.a.y.l.a(sVar.a(t, this.f1673d.b(), this.f1675f), jsonWriter);
        }
    }
}
