package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.aa;
import com.umeng.commonsdk.proguard.ac;
import com.umeng.commonsdk.proguard.ad;
import com.umeng.commonsdk.proguard.ae;
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
import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.p;
import com.umeng.commonsdk.proguard.q;
import com.umeng.commonsdk.proguard.v;
import com.umeng.commonsdk.proguard.w;
import com.umeng.commonsdk.proguard.x;
import com.umeng.commonsdk.proguard.y;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: IdTracking.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements j<c, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Map<e, v> f4194d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final long f4195e = -5764118265293965743L;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final an f4196f = new an("IdTracking");
    private static final ad g = new ad("snapshots", ap.k, 1);
    private static final ad h = new ad("journals", ap.m, 2);
    private static final ad i = new ad("checksum", (byte) 11, 3);
    private static final Map<Class<? extends aq>, ar> j = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, com.umeng.commonsdk.statistics.proto.b> f4197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<com.umeng.commonsdk.statistics.proto.a> f4198b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4199c;
    private e[] k;

    /* JADX INFO: compiled from: IdTracking.java */
    private static class a extends as<c> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void b(ai aiVar, c cVar) throws aj {
            aiVar.j();
            while (true) {
                ad adVarL = aiVar.l();
                byte b2 = adVarL.f3907b;
                if (b2 == 0) {
                    aiVar.k();
                    cVar.n();
                    return;
                }
                short s = adVarL.f3908c;
                int i = 0;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            al.a(aiVar, b2);
                        } else if (b2 == 11) {
                            cVar.f4199c = aiVar.z();
                            cVar.c(true);
                        } else {
                            al.a(aiVar, b2);
                        }
                    } else if (b2 == 15) {
                        ae aeVarP = aiVar.p();
                        cVar.f4198b = new ArrayList(aeVarP.f3910b);
                        while (i < aeVarP.f3910b) {
                            com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                            aVar.read(aiVar);
                            cVar.f4198b.add(aVar);
                            i++;
                        }
                        aiVar.q();
                        cVar.b(true);
                    } else {
                        al.a(aiVar, b2);
                    }
                } else if (b2 == 13) {
                    af afVarN = aiVar.n();
                    cVar.f4197a = new HashMap(afVarN.f3913c * 2);
                    while (i < afVarN.f3913c) {
                        String strZ = aiVar.z();
                        com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                        bVar.read(aiVar);
                        cVar.f4197a.put(strZ, bVar);
                        i++;
                    }
                    aiVar.o();
                    cVar.a(true);
                } else {
                    al.a(aiVar, b2);
                }
                aiVar.m();
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(ai aiVar, c cVar) throws aj {
            cVar.n();
            aiVar.a(c.f4196f);
            if (cVar.f4197a != null) {
                aiVar.a(c.g);
                aiVar.a(new af((byte) 11, (byte) 12, cVar.f4197a.size()));
                for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.f4197a.entrySet()) {
                    aiVar.a(entry.getKey());
                    entry.getValue().write(aiVar);
                }
                aiVar.e();
                aiVar.c();
            }
            if (cVar.f4198b != null && cVar.j()) {
                aiVar.a(c.h);
                aiVar.a(new ae((byte) 12, cVar.f4198b.size()));
                Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.f4198b.iterator();
                while (it.hasNext()) {
                    it.next().write(aiVar);
                }
                aiVar.f();
                aiVar.c();
            }
            if (cVar.f4199c != null && cVar.m()) {
                aiVar.a(c.i);
                aiVar.a(cVar.f4199c);
                aiVar.c();
            }
            aiVar.d();
            aiVar.b();
        }
    }

    /* JADX INFO: compiled from: IdTracking.java */
    private static class b implements ar {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IdTracking.java */
    private static class C0093c extends at<c> {
        private C0093c() {
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void a(ai aiVar, c cVar) {
            ao aoVar = (ao) aiVar;
            aoVar.a(cVar.f4197a.size());
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.f4197a.entrySet()) {
                aoVar.a(entry.getKey());
                entry.getValue().write(aoVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            aoVar.a(bitSet, 2);
            if (cVar.j()) {
                aoVar.a(cVar.f4198b.size());
                Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.f4198b.iterator();
                while (it.hasNext()) {
                    it.next().write(aoVar);
                }
            }
            if (cVar.m()) {
                aoVar.a(cVar.f4199c);
            }
        }

        @Override // com.umeng.commonsdk.proguard.aq
        public void b(ai aiVar, c cVar) {
            ao aoVar = (ao) aiVar;
            af afVar = new af((byte) 11, (byte) 12, aoVar.w());
            cVar.f4197a = new HashMap(afVar.f3913c * 2);
            for (int i = 0; i < afVar.f3913c; i++) {
                String strZ = aoVar.z();
                com.umeng.commonsdk.statistics.proto.b bVar = new com.umeng.commonsdk.statistics.proto.b();
                bVar.read(aoVar);
                cVar.f4197a.put(strZ, bVar);
            }
            cVar.a(true);
            BitSet bitSetB = aoVar.b(2);
            if (bitSetB.get(0)) {
                ae aeVar = new ae((byte) 12, aoVar.w());
                cVar.f4198b = new ArrayList(aeVar.f3910b);
                for (int i2 = 0; i2 < aeVar.f3910b; i2++) {
                    com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
                    aVar.read(aoVar);
                    cVar.f4198b.add(aVar);
                }
                cVar.b(true);
            }
            if (bitSetB.get(1)) {
                cVar.f4199c = aoVar.z();
                cVar.c(true);
            }
        }
    }

    /* JADX INFO: compiled from: IdTracking.java */
    private static class d implements ar {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.ar
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0093c b() {
            return new C0093c();
        }
    }

    static {
        j.put(as.class, new b());
        j.put(at.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new v("snapshots", (byte) 1, new y(ap.k, new w((byte) 11), new aa((byte) 12, com.umeng.commonsdk.statistics.proto.b.class))));
        enumMap.put(e.JOURNALS, new v("journals", (byte) 2, new x(ap.m, new aa((byte) 12, com.umeng.commonsdk.statistics.proto.a.class))));
        enumMap.put(e.CHECKSUM, new v("checksum", (byte) 2, new w((byte) 11)));
        f4194d = Collections.unmodifiableMap(enumMap);
        v.a(c.class, f4194d);
    }

    public c() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    @Override // com.umeng.commonsdk.proguard.j
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public c deepCopy() {
        return new c(this);
    }

    public int b() {
        Map<String, com.umeng.commonsdk.statistics.proto.b> map = this.f4197a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public Map<String, com.umeng.commonsdk.statistics.proto.b> c() {
        return this.f4197a;
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void clear() {
        this.f4197a = null;
        this.f4198b = null;
        this.f4199c = null;
    }

    public void d() {
        this.f4197a = null;
    }

    public boolean e() {
        return this.f4197a != null;
    }

    public int f() {
        List<com.umeng.commonsdk.statistics.proto.a> list = this.f4198b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<com.umeng.commonsdk.statistics.proto.a> g() {
        List<com.umeng.commonsdk.statistics.proto.a> list = this.f4198b;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public List<com.umeng.commonsdk.statistics.proto.a> h() {
        return this.f4198b;
    }

    public void i() {
        this.f4198b = null;
    }

    public boolean j() {
        return this.f4198b != null;
    }

    public String k() {
        return this.f4199c;
    }

    public void l() {
        this.f4199c = null;
    }

    public boolean m() {
        return this.f4199c != null;
    }

    public void n() throws aj {
        if (this.f4197a != null) {
            return;
        }
        throw new aj("Required field 'snapshots' was not present! Struct: " + toString());
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void read(ai aiVar) {
        j.get(aiVar.D()).b().b(aiVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        Map<String, com.umeng.commonsdk.statistics.proto.b> map = this.f4197a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        if (j()) {
            sb.append(", ");
            sb.append("journals:");
            List<com.umeng.commonsdk.statistics.proto.a> list = this.f4198b;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("checksum:");
            String str = this.f4199c;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.umeng.commonsdk.proguard.j
    public void write(ai aiVar) {
        j.get(aiVar.D()).b().a(aiVar, this);
    }

    /* JADX INFO: compiled from: IdTracking.java */
    public enum e implements q {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");


        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final Map<String, e> f4203d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final short f4204e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f4205f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4203d.put(eVar.b(), eVar);
            }
        }

        e(short s, String str) {
            this.f4204e = s;
            this.f4205f = str;
        }

        public static e a(int i) {
            if (i == 1) {
                return SNAPSHOTS;
            }
            if (i == 2) {
                return JOURNALS;
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
            return this.f4205f;
        }

        public static e a(String str) {
            return f4203d.get(str);
        }

        @Override // com.umeng.commonsdk.proguard.q
        public short a() {
            return this.f4204e;
        }
    }

    public void a(String str, com.umeng.commonsdk.statistics.proto.b bVar) {
        if (this.f4197a == null) {
            this.f4197a = new HashMap();
        }
        this.f4197a.put(str, bVar);
    }

    public void b(boolean z) {
        if (z) {
            return;
        }
        this.f4198b = null;
    }

    public void c(boolean z) {
        if (z) {
            return;
        }
        this.f4199c = null;
    }

    public c(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this();
        this.f4197a = map;
    }

    public c(c cVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.b> entry : cVar.f4197a.entrySet()) {
                map.put(entry.getKey(), new com.umeng.commonsdk.statistics.proto.b(entry.getValue()));
            }
            this.f4197a = map;
        }
        if (cVar.j()) {
            ArrayList arrayList = new ArrayList();
            Iterator<com.umeng.commonsdk.statistics.proto.a> it = cVar.f4198b.iterator();
            while (it.hasNext()) {
                arrayList.add(new com.umeng.commonsdk.statistics.proto.a(it.next()));
            }
            this.f4198b = arrayList;
        }
        if (cVar.m()) {
            this.f4199c = cVar.f4199c;
        }
    }

    public c a(Map<String, com.umeng.commonsdk.statistics.proto.b> map) {
        this.f4197a = map;
        return this;
    }

    public void a(boolean z) {
        if (z) {
            return;
        }
        this.f4197a = null;
    }

    public void a(com.umeng.commonsdk.statistics.proto.a aVar) {
        if (this.f4198b == null) {
            this.f4198b = new ArrayList();
        }
        this.f4198b.add(aVar);
    }

    public c a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.f4198b = list;
        return this;
    }

    public c a(String str) {
        this.f4199c = str;
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

    private void a(ObjectInputStream objectInputStream) throws IOException {
        try {
            read(new ac(new au(objectInputStream)));
        } catch (p e2) {
            throw new IOException(e2.getMessage());
        }
    }
}
