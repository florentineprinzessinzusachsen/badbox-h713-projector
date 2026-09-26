package j2;

import i2.r;
import i2.s;
import i2.t;
import i2.u;
import i2.v;
import i2.w;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements n2.b, d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f1269b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f1270a;

    static {
        Map mapSingletonMap;
        int i4 = 0;
        List listR = v1.i.R(new Class[]{i2.a.class, i2.l.class, i2.p.class, i2.q.class, r.class, s.class, t.class, u.class, v.class, w.class, i2.b.class, i2.c.class, i2.d.class, i2.e.class, i2.f.class, i2.g.class, i2.h.class, i2.i.class, i2.j.class, i2.k.class, i2.m.class, i2.n.class, i2.o.class});
        ArrayList arrayList = new ArrayList(v1.l.u0(listR));
        int i5 = 0;
        for (Object obj : listR) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                throw new ArithmeticException("Index overflow has happened.");
            }
            arrayList.add(new u1.f((Class) obj, Integer.valueOf(i5)));
            i5 = i6;
        }
        int size = arrayList.size();
        if (size == 0) {
            mapSingletonMap = v1.q.f2518d;
        } else if (size != 1) {
            mapSingletonMap = new LinkedHashMap(v1.t.J(arrayList.size()));
            int size2 = arrayList.size();
            while (i4 < size2) {
                Object obj2 = arrayList.get(i4);
                i4++;
                u1.f fVar = (u1.f) obj2;
                mapSingletonMap.put(fVar.f2294d, fVar.f2295e);
            }
        } else {
            u1.f fVar2 = (u1.f) arrayList.get(0);
            i.e(fVar2, "pair");
            mapSingletonMap = Collections.singletonMap(fVar2.f2294d, fVar2.f2295e);
            i.d(mapSingletonMap, "singletonMap(...)");
        }
        f1269b = mapSingletonMap;
    }

    public e(Class cls) {
        i.e(cls, "jClass");
        this.f1270a = cls;
    }

    @Override // j2.d
    public final Class a() {
        return this.f1270a;
    }

    public final String b() {
        String strB;
        Class cls = this.f1270a;
        i.e(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return null;
        }
        if (!cls.isArray()) {
            String strB2 = q.b(cls.getName());
            return strB2 == null ? cls.getCanonicalName() : strB2;
        }
        Class<?> componentType = cls.getComponentType();
        if (componentType.isPrimitive() && (strB = q.b(componentType.getName())) != null) {
            strConcat = strB.concat("Array");
        }
        return strConcat == null ? "kotlin.Array" : strConcat;
    }

    public final String c() {
        String strD;
        Class cls = this.f1270a;
        i.e(cls, "jClass");
        String strConcat = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (!cls.isLocalClass()) {
            if (!cls.isArray()) {
                String strD2 = q.d(cls.getName());
                return strD2 == null ? cls.getSimpleName() : strD2;
            }
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (strD = q.d(componentType.getName())) != null) {
                strConcat = strD.concat("Array");
            }
            return strConcat == null ? "Array" : strConcat;
        }
        String simpleName = cls.getSimpleName();
        Method enclosingMethod = cls.getEnclosingMethod();
        if (enclosingMethod != null) {
            return p2.i.Q0(simpleName, enclosingMethod.getName() + '$');
        }
        Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
        if (enclosingConstructor != null) {
            return p2.i.Q0(simpleName, enclosingConstructor.getName() + '$');
        }
        int iE0 = p2.i.E0(simpleName, '$', 0, 6);
        if (iE0 == -1) {
            return simpleName;
        }
        String strSubstring = simpleName.substring(iE0 + 1, simpleName.length());
        i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public final boolean d(Object obj) {
        Class clsN = this.f1270a;
        i.e(clsN, "jClass");
        Map map = f1269b;
        i.c(map, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        Integer num = (Integer) map.get(clsN);
        if (num != null) {
            return q.c(num.intValue(), obj);
        }
        if (clsN.isPrimitive()) {
            clsN = a.a.n(o.a(clsN));
        }
        return clsN.isInstance(obj);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof e) && a.a.n(this).equals(a.a.n((n2.b) obj));
    }

    public final int hashCode() {
        return a.a.n(this).hashCode();
    }

    public final String toString() {
        return this.f1270a.toString() + " (Kotlin reflection is not available)";
    }
}
