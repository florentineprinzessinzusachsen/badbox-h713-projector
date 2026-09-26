package com.umeng.commonsdk.proguard;

import com.baidu.mobstat.Config;
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

/* JADX INFO: compiled from: UMEnvelope.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements j<f, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, v> k;
    private static final long l = 420342210744516016L;
    private static final an m = new an("UMEnvelope");
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
    public String f3968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3969b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3970c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3971d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3972e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3973f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class a extends as<f> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, f fVar) throws aj {
            aiVar.j();
            while (true) {
                ad adVarL = aiVar.l();
                byte b2 = adVarL.f3907b;
                if (b2 == 0) {
                    aiVar.k();
                    if (!fVar.m()) {
                        throw new aj("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!fVar.p()) {
                        throw new aj("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (fVar.s()) {
                        fVar.G();
                        return;
                    }
                    throw new aj("Required field 'length' was not found in serialized data! Struct: " + toString());
                }
                switch (adVarL.f3908c) {
                    case 1:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.f3968a = aiVar.z();
                            fVar.a(true);
                        }
                        break;
                    case 2:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.f3969b = aiVar.z();
                            fVar.b(true);
                        }
                        break;
                    case 3:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.f3970c = aiVar.z();
                            fVar.c(true);
                        }
                        break;
                    case 4:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.f3971d = aiVar.w();
                            fVar.d(true);
                        }
                        break;
                    case 5:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.f3972e = aiVar.w();
                            fVar.e(true);
                        }
                        break;
                    case 6:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.f3973f = aiVar.w();
                            fVar.f(true);
                        }
                        break;
                    case 7:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.g = aiVar.A();
                            fVar.g(true);
                        }
                        break;
                    case 8:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.h = aiVar.z();
                            fVar.h(true);
                        }
                        break;
                    case 9:
                        if (b2 != 11) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.i = aiVar.z();
                            fVar.i(true);
                        }
                        break;
                    case 10:
                        if (b2 != 8) {
                            al.a(aiVar, b2);
                        } else {
                            fVar.j = aiVar.w();
                            fVar.j(true);
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
        public void a(ai aiVar, f fVar) throws aj {
            fVar.G();
            aiVar.a(f.m);
            if (fVar.f3968a != null) {
                aiVar.a(f.n);
                aiVar.a(fVar.f3968a);
                aiVar.c();
            }
            if (fVar.f3969b != null) {
                aiVar.a(f.o);
                aiVar.a(fVar.f3969b);
                aiVar.c();
            }
            if (fVar.f3970c != null) {
                aiVar.a(f.p);
                aiVar.a(fVar.f3970c);
                aiVar.c();
            }
            aiVar.a(f.q);
            aiVar.a(fVar.f3971d);
            aiVar.c();
            aiVar.a(f.r);
            aiVar.a(fVar.f3972e);
            aiVar.c();
            aiVar.a(f.s);
            aiVar.a(fVar.f3973f);
            aiVar.c();
            if (fVar.g != null) {
                aiVar.a(f.t);
                aiVar.a(fVar.g);
                aiVar.c();
            }
            if (fVar.h != null) {
                aiVar.a(f.u);
                aiVar.a(fVar.h);
                aiVar.c();
            }
            if (fVar.i != null) {
                aiVar.a(f.v);
                aiVar.a(fVar.i);
                aiVar.c();
            }
            if (fVar.F()) {
                aiVar.a(f.w);
                aiVar.a(fVar.j);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class c extends at<f> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, f fVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(fVar.f3968a);
            aoVar.a(fVar.f3969b);
            aoVar.a(fVar.f3970c);
            aoVar.a(fVar.f3971d);
            aoVar.a(fVar.f3972e);
            aoVar.a(fVar.f3973f);
            aoVar.a(fVar.g);
            aoVar.a(fVar.h);
            aoVar.a(fVar.i);
            BitSet bitSet = new BitSet();
            if (fVar.F()) {
                bitSet.set(0);
            }
            aoVar.a(bitSet, 1);
            if (fVar.F()) {
                aoVar.a(fVar.j);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, f fVar) {
            ao aoVar = (ao) aiVar;
            fVar.f3968a = aoVar.z();
            fVar.a(true);
            fVar.f3969b = aoVar.z();
            fVar.b(true);
            fVar.f3970c = aoVar.z();
            fVar.c(true);
            fVar.f3971d = aoVar.w();
            fVar.d(true);
            fVar.f3972e = aoVar.w();
            fVar.e(true);
            fVar.f3973f = aoVar.w();
            fVar.f(true);
            fVar.g = aoVar.A();
            fVar.g(true);
            fVar.h = aoVar.z();
            fVar.h(true);
            fVar.i = aoVar.z();
            fVar.i(true);
            if (aoVar.b(1).get(0)) {
                fVar.j = aoVar.w();
                fVar.j(true);
            }
        }
    }

    /* JADX INFO: compiled from: UMEnvelope.java */
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
        x.put(as.class, new b());
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
        v.a(f.class, k);
    }

    public f() {
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
        if (this.f3968a == null) {
            throw new aj("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.f3969b == null) {
            throw new aj("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.f3970c == null) {
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
    public f deepCopy() {
        return new f(this);
    }

    public String b() {
        return this.f3968a;
    }

    public void c() {
        this.f3968a = null;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f3968a = null;
        this.f3969b = null;
        this.f3970c = null;
        d(false);
        this.f3971d = 0;
        e(false);
        this.f3972e = 0;
        f(false);
        this.f3973f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public boolean d() {
        return this.f3968a != null;
    }

    public String e() {
        return this.f3969b;
    }

    public void f() {
        this.f3969b = null;
    }

    public boolean g() {
        return this.f3969b != null;
    }

    public String h() {
        return this.f3970c;
    }

    public void i() {
        this.f3970c = null;
    }

    public boolean j() {
        return this.f3970c != null;
    }

    public int k() {
        return this.f3971d;
    }

    public void l() {
        this.C = g.b(this.C, 0);
    }

    public boolean m() {
        return g.a(this.C, 0);
    }

    public int n() {
        return this.f3972e;
    }

    public void o() {
        this.C = g.b(this.C, 1);
    }

    public boolean p() {
        return g.a(this.C, 1);
    }

    public int q() {
        return this.f3973f;
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
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        String str = this.f3968a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("address:");
        String str2 = this.f3969b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("signature:");
        String str3 = this.f3970c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.f3971d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f3972e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f3973f);
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

    /* JADX INFO: compiled from: UMEnvelope.java */
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

    public f a(String str) {
        this.f3968a = str;
        return this;
    }

    public f b(String str) {
        this.f3969b = str;
        return this;
    }

    public f c(String str) {
        this.f3970c = str;
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
        this.f3968a = null;
    }

    public void b(boolean z2) {
        if (z2) {
            return;
        }
        this.f3969b = null;
    }

    public void c(boolean z2) {
        if (z2) {
            return;
        }
        this.f3970c = null;
    }

    public f d(String str) {
        this.h = str;
        return this;
    }

    public f e(String str) {
        this.i = str;
        return this;
    }

    public f(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f3968a = str;
        this.f3969b = str2;
        this.f3970c = str3;
        this.f3971d = i;
        d(true);
        this.f3972e = i2;
        e(true);
        this.f3973f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public f a(int i) {
        this.f3971d = i;
        d(true);
        return this;
    }

    public f b(int i) {
        this.f3972e = i;
        e(true);
        return this;
    }

    public f c(int i) {
        this.f3973f = i;
        f(true);
        return this;
    }

    public f d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.a(i);
    }

    public f a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public f a(ByteBuffer byteBuffer) {
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

    public f(f fVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = fVar.C;
        if (fVar.d()) {
            this.f3968a = fVar.f3968a;
        }
        if (fVar.g()) {
            this.f3969b = fVar.f3969b;
        }
        if (fVar.j()) {
            this.f3970c = fVar.f3970c;
        }
        this.f3971d = fVar.f3971d;
        this.f3972e = fVar.f3972e;
        this.f3973f = fVar.f3973f;
        if (fVar.w()) {
            this.g = k.d(fVar.g);
        }
        if (fVar.z()) {
            this.h = fVar.h;
        }
        if (fVar.C()) {
            this.i = fVar.i;
        }
        this.j = fVar.j;
    }
}
