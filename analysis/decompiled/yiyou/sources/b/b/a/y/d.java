package b.b.a.y;

import b.b.a.v;
import b.b.a.w;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements w, Cloneable {
    public static final d g = new d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1587d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f1584a = -1.0d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1585b = 136;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1586c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<b.b.a.b> f1588e = Collections.emptyList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<b.b.a.b> f1589f = Collections.emptyList();

    private boolean b(Class<?> cls, boolean z) {
        Iterator<b.b.a.b> it = (z ? this.f1588e : this.f1589f).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean c(Class<?> cls) {
        return cls.isMemberClass() && !d(cls);
    }

    private boolean d(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    @Override // b.b.a.w
    public <T> v<T> a(b.b.a.f fVar, b.b.a.z.a<T> aVar) {
        Class<? super T> clsA = aVar.a();
        boolean zA = a(clsA);
        boolean z = zA || b(clsA, true);
        boolean z2 = zA || b(clsA, false);
        if (z || z2) {
            return new a(z2, z, fVar, aVar);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public d m3clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    class a<T> extends v<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private v<T> f1590a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f1591b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1592c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ b.b.a.f f1593d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ b.b.a.z.a f1594e;

        a(boolean z, boolean z2, b.b.a.f fVar, b.b.a.z.a aVar) {
            this.f1591b = z;
            this.f1592c = z2;
            this.f1593d = fVar;
            this.f1594e = aVar;
        }

        private v<T> b() {
            v<T> vVar = this.f1590a;
            if (vVar != null) {
                return vVar;
            }
            v<T> vVarA = this.f1593d.a(d.this, this.f1594e);
            this.f1590a = vVarA;
            return vVarA;
        }

        @Override // b.b.a.v
        /* JADX INFO: renamed from: a */
        public T a2(JsonReader jsonReader) throws IOException {
            if (!this.f1591b) {
                return b().a2(jsonReader);
            }
            jsonReader.skipValue();
            return null;
        }

        @Override // b.b.a.v
        public void a(JsonWriter jsonWriter, T t) throws IOException {
            if (this.f1592c) {
                jsonWriter.nullValue();
            } else {
                b().a(jsonWriter, t);
            }
        }
    }

    private boolean b(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public boolean a(Field field, boolean z) {
        b.b.a.x.a aVar;
        if ((this.f1585b & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f1584a != -1.0d && !a((b.b.a.x.d) field.getAnnotation(b.b.a.x.d.class), (b.b.a.x.e) field.getAnnotation(b.b.a.x.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f1587d && ((aVar = (b.b.a.x.a) field.getAnnotation(b.b.a.x.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f1586c && c(field.getType())) || b(field.getType())) {
            return true;
        }
        List<b.b.a.b> list = z ? this.f1588e : this.f1589f;
        if (list.isEmpty()) {
            return false;
        }
        b.b.a.c cVar = new b.b.a.c(field);
        Iterator<b.b.a.b> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().a(cVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        if (this.f1584a == -1.0d || a((b.b.a.x.d) cls.getAnnotation(b.b.a.x.d.class), (b.b.a.x.e) cls.getAnnotation(b.b.a.x.e.class))) {
            return (!this.f1586c && c(cls)) || b(cls);
        }
        return true;
    }

    public boolean a(Class<?> cls, boolean z) {
        return a(cls) || b(cls, z);
    }

    private boolean a(b.b.a.x.d dVar, b.b.a.x.e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(b.b.a.x.d dVar) {
        return dVar == null || dVar.value() <= this.f1584a;
    }

    private boolean a(b.b.a.x.e eVar) {
        return eVar == null || eVar.value() > this.f1584a;
    }
}
