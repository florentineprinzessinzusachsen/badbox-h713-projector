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

/* JADX INFO: compiled from: ImprintValue.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements j<e, EnumC0095e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<EnumC0095e, v> f4218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final long f4219e = 7501688097813630241L;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final an f4220f = new an("ImprintValue");
    private static final ad g = new ad("value", (byte) 11, 1);
    private static final ad h = new ad("ts", (byte) 10, 2);
    private static final ad i = new ad("guid", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();
    private static final int k = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4222b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4223c;
    private byte l;
    private EnumC0095e[] m;

    /* JADX INFO: compiled from: ImprintValue.java */
    private static class a extends as<e> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, e eVar) throws aj {
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
                            eVar.f4223c = aiVar.z();
                            eVar.c(true);
                        } else {
                            al.a(aiVar, b2);
                        }
                    } else if (b2 == 10) {
                        eVar.f4222b = aiVar.x();
                        eVar.b(true);
                    } else {
                        al.a(aiVar, b2);
                    }
                } else if (b2 == 11) {
                    eVar.f4221a = aiVar.z();
                    eVar.a(true);
                } else {
                    al.a(aiVar, b2);
                }
                aiVar.m();
            }
            aiVar.k();
            if (eVar.g()) {
                eVar.k();
                return;
            }
            throw new aj("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, e eVar) throws aj {
            eVar.k();
            aiVar.a(e.f4220f);
            if (eVar.f4221a != null && eVar.d()) {
                aiVar.a(e.g);
                aiVar.a(eVar.f4221a);
                aiVar.c();
            }
            aiVar.a(e.h);
            aiVar.a(eVar.f4222b);
            aiVar.c();
            if (eVar.f4223c != null) {
                aiVar.a(e.i);
                aiVar.a(eVar.f4223c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: compiled from: ImprintValue.java */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: ImprintValue.java */
    private static class c extends at<e> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, e eVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(eVar.f4222b);
            aoVar.a(eVar.f4223c);
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (eVar.d()) {
                aoVar.a(eVar.f4221a);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, e eVar) {
            ao aoVar = (ao) aiVar;
            eVar.f4222b = aoVar.x();
            eVar.b(true);
            eVar.f4223c = aoVar.z();
            eVar.c(true);
            if (aoVar.b(1).get(0)) {
                eVar.f4221a = aoVar.z();
                eVar.a(true);
            }
        }
    }

    /* JADX INFO: compiled from: ImprintValue.java */
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
        j.put(as.class, new b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(EnumC0095e.class);
        enumMap.put(EnumC0095e.VALUE, new v("value", (byte) 2, new w((byte) 11)));
        enumMap.put(EnumC0095e.TS, new v("ts", (byte) 1, new w((byte) 10)));
        enumMap.put(EnumC0095e.GUID, new v("guid", (byte) 1, new w((byte) 11)));
        f4218d = Collections.unmodifiableMap(enumMap);
        v.a(e.class, f4218d);
    }

    public e() {
        this.l = (byte) 0;
        this.m = new EnumC0095e[]{EnumC0095e.VALUE};
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e deepCopy() {
        return new e(this);
    }

    public String b() {
        return this.f4221a;
    }

    public void c() {
        this.f4221a = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f4221a = null;
        b(false);
        this.f4222b = 0L;
        this.f4223c = null;
    }

    public boolean d() {
        return this.f4221a != null;
    }

    public long e() {
        return this.f4222b;
    }

    public void f() {
        this.l = g.b(this.l, 0);
    }

    public boolean g() {
        return g.a(this.l, 0);
    }

    public String h() {
        return this.f4223c;
    }

    public void i() {
        this.f4223c = null;
    }

    public boolean j() {
        return this.f4223c != null;
    }

    public void k() throws aj {
        if (this.f4223c != null) {
            return;
        }
        throw new aj("Required field 'guid' was not present! Struct: " + toString());
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (d()) {
            sb.append("value:");
            String str = this.f4221a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.f4222b);
        sb.append(", ");
        sb.append("guid:");
        String str2 = this.f4223c;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        j.get(aiVar.D()).b().a(aiVar, this);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$e, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ImprintValue.java */
    public enum EnumC0095e implements q {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");


        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final Map<String, EnumC0095e> f4227d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final short f4228e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f4229f;

        static {
            for (EnumC0095e enumC0095e : EnumSet.allOf(EnumC0095e.class)) {
                f4227d.put(enumC0095e.b(), enumC0095e);
            }
        }

        EnumC0095e(short s, String str) {
            this.f4228e = s;
            this.f4229f = str;
        }

        public static EnumC0095e a(int i) {
            if (i == 1) {
                return VALUE;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return GUID;
        }

        public static EnumC0095e b(int i) {
            EnumC0095e enumC0095eA = a(i);
            if (enumC0095eA != null) {
                return enumC0095eA;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        @Override // com.umeng.commonsdk.proguard.q
        public String b() {
            return this.f4229f;
        }

        public static EnumC0095e a(String str) {
            return f4227d.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.f4228e;
        }
    }

    public e a(String str) {
        this.f4221a = str;
        return this;
    }

    public void b(boolean z) {
        this.l = g.a(this.l, 0, z);
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.f4223c = null;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f4221a = null;
    }

    public e b(String str) {
        this.f4223c = str;
        return this;
    }

    public e(long j2, String str) {
        this();
        this.f4222b = j2;
        b(true);
        this.f4223c = str;
    }

    public e a(long j2) {
        this.f4222b = j2;
        b(true);
        return this;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public EnumC0095e fieldForId(int i2) {
        return EnumC0095e.a(i2);
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new ac(new au(objectOutputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public e(e eVar) {
        this.l = (byte) 0;
        this.m = new EnumC0095e[]{EnumC0095e.VALUE};
        this.l = eVar.l;
        if (eVar.d()) {
            this.f4221a = eVar.f4221a;
        }
        this.f4222b = eVar.f4222b;
        if (eVar.j()) {
            this.f4223c = eVar.f4223c;
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
