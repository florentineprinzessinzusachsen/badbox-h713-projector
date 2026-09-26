package com.umeng.commonsdk.statistics.proto;

import com.baidu.mobstat.Config;
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
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: IdSnapshot.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements j<b, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<e, v> f4182d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final long f4183e = -6496538196005191531L;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final an f4184f = new an("IdSnapshot");
    private static final ad g = new ad("identity", (byte) 11, 1);
    private static final ad h = new ad("ts", (byte) 10, 2);
    private static final ad i = new ad(Config.INPUT_DEF_VERSION, (byte) 8, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4186b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4187c;
    private byte m;

    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class a extends as<b> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, b bVar) throws aj {
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
                        } else if (b2 == 8) {
                            bVar.f4187c = aiVar.w();
                            bVar.c(true);
                        } else {
                            al.a(aiVar, b2);
                        }
                    } else if (b2 == 10) {
                        bVar.f4186b = aiVar.x();
                        bVar.b(true);
                    } else {
                        al.a(aiVar, b2);
                    }
                } else if (b2 == 11) {
                    bVar.f4185a = aiVar.z();
                    bVar.a(true);
                } else {
                    al.a(aiVar, b2);
                }
                aiVar.m();
            }
            aiVar.k();
            if (!bVar.g()) {
                throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
            }
            if (bVar.j()) {
                bVar.k();
                return;
            }
            throw new aj("Required field 'version' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, b bVar) throws aj {
            bVar.k();
            aiVar.a(b.f4184f);
            if (bVar.f4185a != null) {
                aiVar.a(b.g);
                aiVar.a(bVar.f4185a);
                aiVar.c();
            }
            aiVar.a(b.h);
            aiVar.a(bVar.f4186b);
            aiVar.c();
            aiVar.a(b.i);
            aiVar.a(bVar.f4187c);
            aiVar.c();
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class C0092b implements ar {
        private C0092b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class c extends at<b> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, b bVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(bVar.f4185a);
            aoVar.a(bVar.f4186b);
            aoVar.a(bVar.f4187c);
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, b bVar) {
            ao aoVar = (ao) aiVar;
            bVar.f4185a = aoVar.z();
            bVar.a(true);
            bVar.f4186b = aoVar.x();
            bVar.b(true);
            bVar.f4187c = aoVar.w();
            bVar.c(true);
        }
    }

    /* JADX INFO: compiled from: IdSnapshot.java */
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
        j.put(as.class, new C0092b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.IDENTITY, new v("identity", (byte) 1, new w((byte) 11)));
        enumMap.put(e.TS, new v("ts", (byte) 1, new w((byte) 10)));
        enumMap.put(e.VERSION, new v(Config.INPUT_DEF_VERSION, (byte) 1, new w((byte) 8)));
        f4182d = Collections.unmodifiableMap(enumMap);
        v.a(b.class, f4182d);
    }

    public b() {
        this.m = (byte) 0;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public b deepCopy() {
        return new b(this);
    }

    public String b() {
        return this.f4185a;
    }

    public void c() {
        this.f4185a = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f4185a = null;
        b(false);
        this.f4186b = 0L;
        c(false);
        this.f4187c = 0;
    }

    public boolean d() {
        return this.f4185a != null;
    }

    public long e() {
        return this.f4186b;
    }

    public void f() {
        this.m = g.b(this.m, 0);
    }

    public boolean g() {
        return g.a(this.m, 0);
    }

    public int h() {
        return this.f4187c;
    }

    public void i() {
        this.m = g.b(this.m, 1);
    }

    public boolean j() {
        return g.a(this.m, 1);
    }

    public void k() throws aj {
        if (this.f4185a != null) {
            return;
        }
        throw new aj("Required field 'identity' was not present! Struct: " + toString());
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        String str = this.f4185a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f4186b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f4187c);
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        j.get(aiVar.D()).b().a(aiVar, this);
    }

    /* JADX INFO: compiled from: IdSnapshot.java */
    public enum e implements q {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, Config.INPUT_DEF_VERSION);


        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final Map<String, e> f4191d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final short f4192e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f4193f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4191d.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f4192e = s;
            this.f4193f = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return IDENTITY;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return VERSION;
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
            return this.f4193f;
        }

        public static e a(String str) {
            return f4191d.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.f4192e;
        }
    }

    public b a(String str) {
        this.f4185a = str;
        return this;
    }

    public void b(boolean z) {
        this.m = g.a(this.m, 0, z);
    }

    public void c(boolean z) {
        this.m = g.a(this.m, 1, z);
    }

    public b(String str, long j2, int i2) {
        this();
        this.f4185a = str;
        this.f4186b = j2;
        b(true);
        this.f4187c = i2;
        c(true);
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f4185a = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    public b a(long j2) {
        this.f4186b = j2;
        b(true);
        return this;
    }

    public b a(int i2) {
        this.f4187c = i2;
        c(true);
        return this;
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public b(b bVar) {
        this.m = (byte) 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.f4185a = bVar.f4185a;
        }
        this.f4186b = bVar.f4186b;
        this.f4187c = bVar.f4187c;
    }

    private void a(ObjectInputStream objectInputStream) throws IOException {
        try {
            this.m = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
