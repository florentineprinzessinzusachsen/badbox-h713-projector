package com.umeng.commonsdk.statistics.proto;

import com.baidu.mobstat.Config;
import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.af;
import com.umeng.commonsdk.proguard.ai;
import com.umeng.commonsdk.proguard.aj;
import com.umeng.commonsdk.proguard.al;
import com.umeng.commonsdk.proguard.an;
import com.umeng.commonsdk.proguard.ao;
import com.umeng.commonsdk.proguard.ap;
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
import com.umeng.commonsdk.proguard.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Imprint.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements j<d, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<e, v> f4206d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final long f4207e = 2846460275012375038L;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final an f4208f = new an("Imprint");
    private static final ad g = new ad("property", ap.k, 1);
    private static final ad h = new ad(Config.INPUT_DEF_VERSION, (byte) 8, 2);
    private static final ad i = new ad("checksum", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, com.umeng.commonsdk.statistics.proto.e> f4209a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4210b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4211c;
    private byte l;

    /* JADX INFO: compiled from: Imprint.java */
    private static class a extends as<d> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, d dVar) throws aj {
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
                            al.a(aiVar, b2);
                        } else if (b2 == 11) {
                            dVar.f4211c = aiVar.z();
                            dVar.c(true);
                        } else {
                            al.a(aiVar, b2);
                        }
                    } else if (b2 == 8) {
                        dVar.f4210b = aiVar.w();
                        dVar.b(true);
                    } else {
                        al.a(aiVar, b2);
                    }
                } else if (b2 == 13) {
                    af afVarN = aiVar.n();
                    dVar.f4209a = new HashMap(afVarN.f3913c * 2);
                    for (int i = 0; i < afVarN.f3913c; i++) {
                        String strZ = aiVar.z();
                        com.umeng.commonsdk.statistics.proto.e eVar = new com.umeng.commonsdk.statistics.proto.e();
                        eVar.read(aiVar);
                        dVar.f4209a.put(strZ, eVar);
                    }
                    aiVar.o();
                    dVar.a(true);
                } else {
                    al.a(aiVar, b2);
                }
                aiVar.m();
            }
            aiVar.k();
            if (dVar.h()) {
                dVar.l();
                return;
            }
            throw new aj("Required field 'version' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, d dVar) throws aj {
            dVar.l();
            aiVar.a(d.f4208f);
            if (dVar.f4209a != null) {
                aiVar.a(d.g);
                aiVar.a(new af((byte) 11, (byte) 12, dVar.f4209a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.f4209a.entrySet()) {
                    aiVar.a(entry.getKey());
                    entry.getValue().write(aiVar);
                }
                aiVar.e();
                aiVar.c();
            }
            aiVar.a(d.h);
            aiVar.a(dVar.f4210b);
            aiVar.c();
            if (dVar.f4211c != null) {
                aiVar.a(d.i);
                aiVar.a(dVar.f4211c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: compiled from: Imprint.java */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: Imprint.java */
    private static class c extends at<d> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, d dVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(dVar.f4209a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.f4209a.entrySet()) {
                aoVar.a(entry.getKey());
                entry.getValue().write(aoVar);
            }
            aoVar.a(dVar.f4210b);
            aoVar.a(dVar.f4211c);
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, d dVar) {
            ao aoVar = (ao) aiVar;
            af afVar = new af((byte) 11, (byte) 12, aoVar.w());
            dVar.f4209a = new HashMap(afVar.f3913c * 2);
            for (int i = 0; i < afVar.f3913c; i++) {
                String strZ = aoVar.z();
                com.umeng.commonsdk.statistics.proto.e eVar = new com.umeng.commonsdk.statistics.proto.e();
                eVar.read(aoVar);
                dVar.f4209a.put(strZ, eVar);
            }
            dVar.a(true);
            dVar.f4210b = aoVar.w();
            dVar.b(true);
            dVar.f4211c = aoVar.z();
            dVar.c(true);
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Imprint.java */
    private static class C0094d implements ar {
        private C0094d() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c b() {
            return new c();
        }
    }

    static {
        j.put(as.class, new b());
        j.put(at.class, new C0094d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new v("property", (byte) 1, new y(ap.k, new w((byte) 11), new aa((byte) 12, com.umeng.commonsdk.statistics.proto.e.class))));
        enumMap.put(e.VERSION, new v(Config.INPUT_DEF_VERSION, (byte) 1, new w((byte) 8)));
        enumMap.put(e.CHECKSUM, new v("checksum", (byte) 1, new w((byte) 11)));
        f4206d = Collections.unmodifiableMap(enumMap);
        v.a(d.class, f4206d);
    }

    public d() {
        this.l = (byte) 0;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public d deepCopy() {
        return new d(this);
    }

    public int b() {
        Map<String, com.umeng.commonsdk.statistics.proto.e> map = this.f4209a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.e> c() {
        return this.f4209a;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f4209a = null;
        b(false);
        this.f4210b = 0;
        this.f4211c = null;
    }

    public void d() {
        this.f4209a = null;
    }

    public boolean e() {
        return this.f4209a != null;
    }

    public int f() {
        return this.f4210b;
    }

    public void g() {
        this.l = g.b(this.l, 0);
    }

    public boolean h() {
        return g.a(this.l, 0);
    }

    public String i() {
        return this.f4211c;
    }

    public void j() {
        this.f4211c = null;
    }

    public boolean k() {
        return this.f4211c != null;
    }

    public void l() throws aj {
        if (this.f4209a == null) {
            throw new aj("Required field 'property' was not present! Struct: " + toString());
        }
        if (this.f4211c != null) {
            return;
        }
        throw new aj("Required field 'checksum' was not present! Struct: " + toString());
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        Map<String, com.umeng.commonsdk.statistics.proto.e> map = this.f4209a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f4210b);
        sb.append(", ");
        sb.append("checksum:");
        String str = this.f4211c;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        j.get(aiVar.D()).b().a(aiVar, this);
    }

    /* JADX INFO: compiled from: Imprint.java */
    public enum e implements q {
        PROPERTY(1, "property"),
        VERSION(2, Config.INPUT_DEF_VERSION),
        CHECKSUM(3, "checksum");


        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final Map<String, e> f4215d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final short f4216e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f4217f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4215d.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f4216e = s;
            this.f4217f = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return PROPERTY;
            }
            if (i == 2) {
                return VERSION;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
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
            return this.f4217f;
        }

        public static e a(String str) {
            return f4215d.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.f4216e;
        }
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.e eVar) {
        if (this.f4209a == null) {
            this.f4209a = new HashMap();
        }
        this.f4209a.put(str, eVar);
    }

    public void b(boolean z) {
        this.l = g.a(this.l, 0, z);
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.f4211c = null;
    }

    public d(Map<String, com.umeng.commonsdk.statistics.proto.e> map, int i2, String str) {
        this();
        this.f4209a = map;
        this.f4210b = i2;
        b(true);
        this.f4211c = str;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public d a(Map<String, com.umeng.commonsdk.statistics.proto.e> map) {
        this.f4209a = map;
        return this;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f4209a = null;
    }

    public d a(int i2) {
        this.f4210b = i2;
        b(true);
        return this;
    }

    public d(d dVar) {
        this.l = (byte) 0;
        this.l = dVar.l;
        if (dVar.e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar.f4209a.entrySet()) {
                map.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.e(entry.getValue()));
            }
            this.f4209a = map;
        }
        this.f4210b = dVar.f4210b;
        if (dVar.k()) {
            this.f4211c = dVar.f4211c;
        }
    }

    public d a(String str) {
        this.f4211c = str;
        return this;
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException {
        try {
            this.l = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
