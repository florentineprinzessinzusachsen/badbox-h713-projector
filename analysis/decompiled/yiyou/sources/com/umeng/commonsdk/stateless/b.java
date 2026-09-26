package com.umeng.commonsdk.stateless;

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
import com.umeng.commonsdk.proguard.k;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: UMSLEnvelope.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements j<b, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, v> k;
    private static final long l = 420342210744516016L;
    private static final an m = new an("UMSLEnvelope");
    private static final ad n = new ad(Config.INPUT_DEF_VERSION, (byte) 11, 1);
    private static final ad o = new ad("address", (byte) 11, 2);
    private static final ad p = new ad("signature", (byte) 11, 3);
    private static final ad q = new ad("serial_num", (byte) 8, 4);
    private static final ad r = new ad("ts_secs", (byte) 8, 5);
    private static final ad s = new ad("length", (byte) 8, 6);
    private static final ad t = new ad("entity", (byte) 11, 7);
    private static final ad u = new ad("guid", (byte) 11, 8);
    private static final ad v = new ad("checksum", (byte) 11, 9);
    private static final ad w = new ad("codex", (byte) 8, 10);
    private static final Map<Class<? extends aq>, ar> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4030d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4031e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4032f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    /* JADX INFO: compiled from: UMSLEnvelope.java */
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
                    aiVar.k();
                    if (!bVar.m()) {
                        throw new aj("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!bVar.p()) {
                        throw new aj("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (bVar.s()) {
                        bVar.G();
                        return;
                    }
                    throw new aj("Required field 'length' was not found in serialized data! Struct: " + toString());
                }
                switch (adVarL.f3908c) {
                    case 1:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.f4027a = aiVar.z();
                            bVar.a(true);
                        }
                        break;
                    case 2:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.f4028b = aiVar.z();
                            bVar.b(true);
                        }
                        break;
                    case 3:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.f4029c = aiVar.z();
                            bVar.c(true);
                        }
                        break;
                    case 4:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.f4030d = aiVar.w();
                            bVar.d(true);
                        }
                        break;
                    case 5:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.f4031e = aiVar.w();
                            bVar.e(true);
                        }
                        break;
                    case 6:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.f4032f = aiVar.w();
                            bVar.f(true);
                        }
                        break;
                    case 7:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.g = aiVar.A();
                            bVar.g(true);
                        }
                        break;
                    case 8:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.h = aiVar.z();
                            bVar.h(true);
                        }
                        break;
                    case 9:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.i = aiVar.z();
                            bVar.i(true);
                        }
                        break;
                    case 10:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            bVar.j = aiVar.w();
                            bVar.j(true);
                        }
                        break;
                    default:
                        al.a(aiVar, b2);
                        break;
                }
                aiVar.m();
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, b bVar) throws aj {
            bVar.G();
            aiVar.a(b.m);
            if (bVar.f4027a != null) {
                aiVar.a(b.n);
                aiVar.a(bVar.f4027a);
                aiVar.c();
            }
            if (bVar.f4028b != null) {
                aiVar.a(b.o);
                aiVar.a(bVar.f4028b);
                aiVar.c();
            }
            if (bVar.f4029c != null) {
                aiVar.a(b.p);
                aiVar.a(bVar.f4029c);
                aiVar.c();
            }
            aiVar.a(b.q);
            aiVar.a(bVar.f4030d);
            aiVar.c();
            aiVar.a(b.r);
            aiVar.a(bVar.f4031e);
            aiVar.c();
            aiVar.a(b.s);
            aiVar.a(bVar.f4032f);
            aiVar.c();
            if (bVar.g != null) {
                aiVar.a(b.t);
                aiVar.a(bVar.g);
                aiVar.c();
            }
            if (bVar.h != null) {
                aiVar.a(b.u);
                aiVar.a(bVar.h);
                aiVar.c();
            }
            if (bVar.i != null) {
                aiVar.a(b.v);
                aiVar.a(bVar.i);
                aiVar.c();
            }
            if (bVar.F()) {
                aiVar.a(b.w);
                aiVar.a(bVar.j);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: UMSLEnvelope.java */
    private static class C0088b implements ar {
        private C0088b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: UMSLEnvelope.java */
    private static class c extends at<b> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, b bVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(bVar.f4027a);
            aoVar.a(bVar.f4028b);
            aoVar.a(bVar.f4029c);
            aoVar.a(bVar.f4030d);
            aoVar.a(bVar.f4031e);
            aoVar.a(bVar.f4032f);
            aoVar.a(bVar.g);
            aoVar.a(bVar.h);
            aoVar.a(bVar.i);
            BitSet bitSet = new BitSet();
            if (bVar.F()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (bVar.F()) {
                aoVar.a(bVar.j);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, b bVar) {
            ao aoVar = (ao) aiVar;
            bVar.f4027a = aoVar.z();
            bVar.a(true);
            bVar.f4028b = aoVar.z();
            bVar.b(true);
            bVar.f4029c = aoVar.z();
            bVar.c(true);
            bVar.f4030d = aoVar.w();
            bVar.d(true);
            bVar.f4031e = aoVar.w();
            bVar.e(true);
            bVar.f4032f = aoVar.w();
            bVar.f(true);
            bVar.g = aoVar.A();
            bVar.g(true);
            bVar.h = aoVar.z();
            bVar.h(true);
            bVar.i = aoVar.z();
            bVar.i(true);
            if (aoVar.b(1).get(0)) {
                bVar.j = aoVar.w();
                bVar.j(true);
            }
        }
    }

    /* JADX INFO: compiled from: UMSLEnvelope.java */
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
        x.put(as.class, new C0088b());
        x.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new v(Config.INPUT_DEF_VERSION, (byte) 1, new w((byte) 11)));
        enumMap.put(e.ADDRESS, new v("address", (byte) 1, new w((byte) 11)));
        enumMap.put(e.SIGNATURE, new v("signature", (byte) 1, new w((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new v("serial_num", (byte) 1, new w((byte) 8)));
        enumMap.put(e.TS_SECS, new v("ts_secs", (byte) 1, new w((byte) 8)));
        enumMap.put(e.LENGTH, new v("length", (byte) 1, new w((byte) 8)));
        enumMap.put(e.ENTITY, new v("entity", (byte) 1, new w((byte) 11, true)));
        enumMap.put(e.GUID, new v("guid", (byte) 1, new w((byte) 11)));
        enumMap.put(e.CHECKSUM, new v("checksum", (byte) 1, new w((byte) 11)));
        enumMap.put(e.CODEX, new v("codex", (byte) 2, new w((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        v.a(b.class, k);
    }

    public b() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public String A() {
        return this.i;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public int D() {
        return this.j;
    }

    public void E() {
        this.C = g.b(this.C, 3);
    }

    public boolean F() {
        return g.a(this.C, 3);
    }

    public void G() throws aj {
        if (this.f4027a == null) {
            throw new aj("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.f4028b == null) {
            throw new aj("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.f4029c == null) {
            throw new aj("Required field 'signature' was not present! Struct: " + toString());
        }
        if (this.g == null) {
            throw new aj("Required field 'entity' was not present! Struct: " + toString());
        }
        if (this.h == null) {
            throw new aj("Required field 'guid' was not present! Struct: " + toString());
        }
        if (this.i != null) {
            return;
        }
        throw new aj("Required field 'checksum' was not present! Struct: " + toString());
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public b deepCopy() {
        return new b(this);
    }

    public String b() {
        return this.f4027a;
    }

    public void c() {
        this.f4027a = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f4027a = null;
        this.f4028b = null;
        this.f4029c = null;
        d(false);
        this.f4030d = 0;
        e(false);
        this.f4031e = 0;
        f(false);
        this.f4032f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public boolean d() {
        return this.f4027a != null;
    }

    public String e() {
        return this.f4028b;
    }

    public void f() {
        this.f4028b = null;
    }

    public boolean g() {
        return this.f4028b != null;
    }

    public String h() {
        return this.f4029c;
    }

    public void i() {
        this.f4029c = null;
    }

    public boolean j() {
        return this.f4029c != null;
    }

    public int k() {
        return this.f4030d;
    }

    public void l() {
        this.C = g.b(this.C, 0);
    }

    public boolean m() {
        return g.a(this.C, 0);
    }

    public int n() {
        return this.f4031e;
    }

    public void o() {
        this.C = g.b(this.C, 1);
    }

    public boolean p() {
        return g.a(this.C, 1);
    }

    public int q() {
        return this.f4032f;
    }

    public void r() {
        this.C = g.b(this.C, 2);
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        x.get(aiVar.D()).b().b(aiVar, this);
    }

    public boolean s() {
        return g.a(this.C, 2);
    }

    public byte[] t() {
        a(k.c(this.g));
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            return null;
        }
        return byteBuffer.array();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMSLEnvelope(");
        sb.append("version:");
        String str = this.f4027a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("address:");
        String str2 = this.f4028b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("signature:");
        String str3 = this.f4029c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.f4030d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f4031e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f4032f);
        sb.append(", ");
        sb.append("entity:");
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            sb.append("null");
        } else {
            k.a(byteBuffer, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        String str4 = this.h;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        sb.append(", ");
        sb.append("checksum:");
        String str5 = this.i;
        if (str5 == null) {
            sb.append("null");
        } else {
            sb.append(str5);
        }
        if (F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(")");
        return sb.toString();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        x.get(aiVar.D()).b().a(aiVar, this);
    }

    public String x() {
        return this.h;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    /* JADX INFO: compiled from: UMSLEnvelope.java */
    public enum e implements q {
        VERSION(1, Config.INPUT_DEF_VERSION),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");

        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                k.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
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
            return this.m;
        }

        public static e a(String str) {
            return k.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.l;
        }
    }

    public b a(String str) {
        this.f4027a = str;
        return this;
    }

    public b b(String str) {
        this.f4028b = str;
        return this;
    }

    public b c(String str) {
        this.f4029c = str;
        return this;
    }

    public void d(boolean z2) {
        this.C = g.a(this.C, 0, z2);
    }

    public void e(boolean z2) {
        this.C = g.a(this.C, 1, z2);
    }

    public void f(boolean z2) {
        this.C = g.a(this.C, 2, z2);
    }

    public void g(boolean z2) {
        if (z2) {
            return;
        }
        this.g = null;
    }

    public void h(boolean z2) {
        if (z2) {
            return;
        }
        this.h = null;
    }

    public void i(boolean z2) {
        if (z2) {
            return;
        }
        this.i = null;
    }

    public void j(boolean z2) {
        this.C = g.a(this.C, 3, z2);
    }

    public void a(boolean z2) {
        if (z2) {
            return;
        }
        this.f4027a = null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.f4028b = null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.f4029c = null;
    }

    public b d(String str) {
        this.h = str;
        return this;
    }

    public b e(String str) {
        this.i = str;
        return this;
    }

    public b(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f4027a = str;
        this.f4028b = str2;
        this.f4029c = str3;
        this.f4030d = i;
        d(true);
        this.f4031e = i2;
        e(true);
        this.f4032f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public b a(int i) {
        this.f4030d = i;
        d(true);
        return this;
    }

    public b b(int i) {
        this.f4031e = i;
        e(true);
        return this;
    }

    public b c(int i) {
        this.f4032f = i;
        f(true);
        return this;
    }

    public b d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.a(i);
    }

    public b a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public b a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
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
            this.C = (byte) 0;
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public b(b bVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = bVar.C;
        if (bVar.d()) {
            this.f4027a = bVar.f4027a;
        }
        if (bVar.g()) {
            this.f4028b = bVar.f4028b;
        }
        if (bVar.j()) {
            this.f4029c = bVar.f4029c;
        }
        this.f4030d = bVar.f4030d;
        this.f4031e = bVar.f4031e;
        this.f4032f = bVar.f4032f;
        if (bVar.w()) {
            this.g = k.d(bVar.g);
        }
        if (bVar.z()) {
            this.h = bVar.h;
        }
        if (bVar.C()) {
            this.i = bVar.i;
        }
        this.j = bVar.j;
    }
}
