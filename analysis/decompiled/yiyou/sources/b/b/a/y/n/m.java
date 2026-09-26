package b.b.a.y.n;

import b.b.a.v;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: TypeAdapterRuntimeTypeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
final class m<T> extends v<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b.b.a.f f1676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final v<T> f1677b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Type f1678c;

    m(b.b.a.f fVar, v<T> vVar, Type type) {
        this.f1676a = fVar;
        this.f1677b = vVar;
        this.f1678c = type;
    }

    @Override // b.b.a.v
    /* JADX INFO: renamed from: a */
    public T a2(JsonReader jsonReader) {
        return this.f1677b.a2(jsonReader);
    }

    @Override // b.b.a.v
    public void a(JsonWriter jsonWriter, T t) {
        v<T> vVarA = this.f1677b;
        Type typeA = a(this.f1678c, t);
        if (typeA != this.f1678c) {
            vVarA = this.f1676a.a((b.b.a.z.a) b.b.a.z.a.a(typeA));
            if (vVarA instanceof i.b) {
                v<T> vVar = this.f1677b;
                if (!(vVar instanceof i.b)) {
                    vVarA = vVar;
                }
            }
        }
        vVarA.a(jsonWriter, t);
    }

    private Type a(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        }
        return type;
    }
}
