package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.aq;
import com.umeng.commonsdk.proguard.ar;
import com.umeng.commonsdk.proguard.as;
import com.umeng.commonsdk.proguard.at;
import com.umeng.commonsdk.proguard.au;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: IdJournal.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements j<a, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Map<e, v> f4170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final long f4171f = 9132678615281394583L;
    private static final an g = new an("IdJournal");
    private static final ad h = new ad("domain", (byte) 11, 1);
    private static final ad i = new ad("old_id", (byte) 11, 2);
    private static final ad j = new ad("new_id", (byte) 11, 3);
    private static final ad k = new ad("ts", (byte) 10, 4);
    private static final Map<Class<? extends aq>, ar> l = new HashMap();
    private static final int m = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4175d;
    private byte n;
    private e[] o;

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IdJournal.java */
    private static class C0091a extends as<a> {
        private C0091a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, a aVar) throws aj {
            aiVar.j();
            while (true) {
                ad adVarL = aiVar.l();
                byte b2 = adVarL.f3907b;
                if (b2 == 0) {
                    break;
                }
                short s = adVarL.f3908c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            if (s != 4) {
                                al.a(aiVar, b2);
                            } else if (b2 == 10) {
                                aVar.f4175d = aiVar.x();
                                aVar.d(true);
                            } else {
                                al.a(aiVar, b2);
                            }
                        } else if (b2 == 11) {
                            aVar.f4174c = aiVar.z();
                            aVar.c(true);
                        } else {
                            al.a(aiVar, b2);
                        }
                    } else if (b2 == 11) {
                        aVar.f4173b = aiVar.z();
                        aVar.b(true);
                    } else {
                        al.a(aiVar, b2);
                    }
                } else if (b2 == 11) {
                    aVar.f4172a = aiVar.z();
                    aVar.a(true);
                } else {
                    al.a(aiVar, b2);
                }
                aiVar.m();
            }
            aiVar.k();
            if (aVar.m()) {
                aVar.n();
                return;
            }
            throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, a aVar) throws aj {
            aVar.n();
            aiVar.a(a.g);
            if (aVar.f4172a != null) {
                aiVar.a(a.h);
                aiVar.a(aVar.f4172a);
                aiVar.c();
            }
            if (aVar.f4173b != null && aVar.g()) {
                aiVar.a(a.i);
                aiVar.a(aVar.f4173b);
                aiVar.c();
            }
            if (aVar.f4174c != null) {
                aiVar.a(a.j);
                aiVar.a(aVar.f4174c);
                aiVar.c();
            }
            aiVar.a(a.k);
            aiVar.a(aVar.f4175d);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: compiled from: IdJournal.java */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0091a b() {
            return new C0091a();
        }
    }

    /* JADX INFO: compiled from: IdJournal.java */
    private static class c extends at<a> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, a aVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(aVar.f4172a);
            aoVar.a(aVar.f4174c);
            aoVar.a(aVar.f4175d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (aVar.g()) {
                aoVar.a(aVar.f4173b);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, a aVar) {
            ao aoVar = (ao) aiVar;
            aVar.f4172a = aoVar.z();
            aVar.a(true);
            aVar.f4174c = aoVar.z();
            aVar.c(true);
            aVar.f4175d = aoVar.x();
            aVar.d(true);
            if (aoVar.b(1).get(0)) {
                aVar.f4173b = aoVar.z();
                aVar.b(true);
            }
        }
    }

    /* JADX INFO: compiled from: IdJournal.java */
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
        l.put(as.class, new b());
        l.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new v("domain", (byte) 1, new w((byte) 11)));
        enumMap.put(e.OLD_ID, new v("old_id", (byte) 2, new w((byte) 11)));
        enumMap.put(e.NEW_ID, new v("new_id", (byte) 1, new w((byte) 11)));
        enumMap.put(e.TS, new v("ts", (byte) 1, new w((byte) 10)));
        f4170e = Collections.unmodifiableMap(enumMap);
        v.a(a.class, f4170e);
    }

    public a() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a deepCopy() {
        return new a(this);
    }

    public String b() {
        return this.f4172a;
    }

    public void c() {
        this.f4172a = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f4172a = null;
        this.f4173b = null;
        this.f4174c = null;
        d(false);
        this.f4175d = 0L;
    }

    public boolean d() {
        return this.f4172a != null;
    }

    public String e() {
        return this.f4173b;
    }

    public void f() {
        this.f4173b = null;
    }

    public boolean g() {
        return this.f4173b != null;
    }

    public String h() {
        return this.f4174c;
    }

    public void i() {
        this.f4174c = null;
    }

    public boolean j() {
        return this.f4174c != null;
    }

    public long k() {
        return this.f4175d;
    }

    public void l() {
        this.n = g.b(this.n, 0);
    }

    public boolean m() {
        return g.a(this.n, 0);
    }

    public void n() throws aj {
        if (this.f4172a == null) {
            throw new aj("Required field 'domain' was not present! Struct: " + toString());
        }
        if (this.f4174c != null) {
            return;
        }
        throw new aj("Required field 'new_id' was not present! Struct: " + toString());
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        l.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        String str = this.f4172a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (g()) {
            sb.append(", ");
            sb.append("old_id:");
            String str2 = this.f4173b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        String str3 = this.f4174c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f4175d);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        l.get(aiVar.D()).b().a(aiVar, this);
    }

    /* JADX INFO: compiled from: IdJournal.java */
    public enum e implements q {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final Map<String, e> f4180e = new HashMap();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final short f4181f;
        private final String g;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4180e.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f4181f = s;
            this.g = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return DOMAIN;
            }
            if (i == 2) {
                return OLD_ID;
            }
            if (i == 3) {
                return NEW_ID;
            }
            if (i != 4) {
                return null;
            }
            return TS;
        }

        public static e b(int i) {
            e eVarA = a(i);
            if (eVarA != null) {
                return eVarA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        @Override // com.umeng.commonsdk.proguard.q
        public String b() {
            return this.g;
        }

        public static e a(String str) {
            return f4180e.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.f4181f;
        }
    }

    public a a(String str) {
        this.f4172a = str;
        return this;
    }

    public a b(String str) {
        this.f4173b = str;
        return this;
    }

    public a c(String str) {
        this.f4174c = str;
        return this;
    }

    public void d(boolean z) {
        this.n = g.a(this.n, 0, z);
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f4172a = null;
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.f4173b = null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.f4174c = null;
    }

    public a(String str, String str2, long j2) {
        this();
        this.f4172a = str;
        this.f4174c = str2;
        this.f4175d = j2;
        d(true);
    }

    public a a(long j2) {
        this.f4175d = j2;
        d(true);
        return this;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public a(a aVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = aVar.n;
        if (aVar.d()) {
            this.f4172a = aVar.f4172a;
        }
        if (aVar.g()) {
            this.f4173b = aVar.f4173b;
        }
        if (aVar.j()) {
            this.f4174c = aVar.f4174c;
        }
        this.f4175d = aVar.f4175d;
    }

    private void a(ObjectInputStream objectInputStream) throws IOException {
        try {
            this.n = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
