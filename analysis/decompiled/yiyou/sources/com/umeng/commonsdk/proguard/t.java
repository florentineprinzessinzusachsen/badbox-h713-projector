package com.umeng.commonsdk.proguard;

import com.baidu.mobstat.Config;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.t;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: TUnion.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t<T extends t<?, ?>, F extends q> implements j<T, F> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Map<Class<? extends aq>, ar> f3998c = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Object f3999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected F f4000b;

    /* JADX INFO: compiled from: TUnion.java */
    private static class a extends as<t> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, t tVar) {
            tVar.f4000b = null;
            tVar.f3999a = null;
            aiVar.j();
            ad adVarL = aiVar.l();
            tVar.f3999a = tVar.a(aiVar, adVarL);
            if (tVar.f3999a != null) {
                tVar.f4000b = (F) tVar.a(adVarL.f3908c);
            }
            aiVar.m();
            aiVar.l();
            aiVar.k();
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, t tVar) throws aj {
            if (tVar.a() == null || tVar.b() == null) {
                throw new aj("Cannot write a TUnion with no set value!");
            }
            aiVar.a(tVar.d());
            aiVar.a(tVar.c(tVar.f4000b));
            tVar.a(aiVar);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: compiled from: TUnion.java */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: TUnion.java */
    private static class c extends at<t> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, t tVar) {
            tVar.f4000b = null;
            tVar.f3999a = null;
            short sV = aiVar.v();
            tVar.f3999a = tVar.a(aiVar, sV);
            if (tVar.f3999a != null) {
                tVar.f4000b = (F) tVar.a(sV);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, t tVar) throws aj {
            if (tVar.a() == null || tVar.b() == null) {
                throw new aj("Cannot write a TUnion with no set value!");
            }
            aiVar.a(tVar.f4000b.a());
            tVar.b(aiVar);
        }
    }

    /* JADX INFO: compiled from: TUnion.java */
    private static class d implements ar {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    static {
        f3998c.put(as.class, new b());
        f3998c.put(at.class, new d());
    }

    protected t() {
        this.f4000b = null;
        this.f3999a = null;
    }

    private static Object a(Object obj) {
        if (obj instanceof j) {
            return ((j) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return k.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        return obj instanceof Map ? a((Map<Object, Object>) obj) : obj;
    }

    protected abstract F a(short s);

    protected abstract Object a(ai aiVar, ad adVar);

    protected abstract Object a(ai aiVar, short s);

    protected abstract void a(ai aiVar);

    public Object b() {
        return this.f3999a;
    }

    protected abstract void b(ai aiVar);

    protected abstract void b(F f2, Object obj);

    protected abstract ad c(F f2);

    public boolean c() {
        return this.f4000b != null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public final void clear() {
        this.f4000b = null;
        this.f3999a = null;
    }

    protected abstract an d();

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        f3998c.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(t.class.getSimpleName());
        sb.append(" ");
        if (a() != null) {
            Object objB = b();
            sb.append(c(a()).f3906a);
            sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
            if (objB instanceof ByteBuffer) {
                k.a((ByteBuffer) objB, sb);
            } else {
                sb.append(objB.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        f3998c.get(aiVar.D()).b().a(aiVar, this);
    }

    public boolean b(F f2) {
        return this.f4000b == f2;
    }

    public boolean b(int i) {
        return b(a((short) i));
    }

    protected t(F f2, Object obj) {
        a(f2, obj);
    }

    protected t(t<T, F> tVar) {
        if (tVar.getClass().equals(t.class)) {
            this.f4000b = tVar.f4000b;
            this.f3999a = a(tVar.f3999a);
            return;
        }
        throw new ClassCastException();
    }

    private static Map a(Map<Object, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            map2.put(a(entry.getKey()), a(entry.getValue()));
        }
        return map2;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.add(a(it.next()));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public F a() {
        return this.f4000b;
    }

    public Object a(F f2) {
        if (f2 == this.f4000b) {
            return b();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f2 + " because union's set field is " + this.f4000b);
    }

    public Object a(int i) {
        return a(a((short) i));
    }

    public void a(F f2, Object obj) {
        b(f2, obj);
        this.f4000b = f2;
        this.f3999a = obj;
    }

    public void a(int i, Object obj) {
        a(a((short) i), obj);
    }
}
