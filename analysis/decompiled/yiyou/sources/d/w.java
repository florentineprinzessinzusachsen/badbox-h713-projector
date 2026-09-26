package d;

import com.umeng.commonsdk.proguard.ap;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: MultipartBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class w extends b0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final v f4682e = v.a("multipart/mixed");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final v f4683f;
    private static final byte[] g;
    private static final byte[] h;
    private static final byte[] i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e.f f4684a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final v f4685b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<b> f4686c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f4687d = -1;

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.f f4688a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private v f4689b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<b> f4690c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a a(v vVar) {
            if (vVar == null) {
                throw new NullPointerException("type == null");
            }
            if (vVar.c().equals("multipart")) {
                this.f4689b = vVar;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + vVar);
        }

        public a(String str) {
            this.f4689b = w.f4682e;
            this.f4690c = new ArrayList();
            this.f4688a = e.f.d(str);
        }

        public a a(s sVar, b0 b0Var) {
            a(b.a(sVar, b0Var));
            return this;
        }

        public a a(b bVar) {
            if (bVar != null) {
                this.f4690c.add(bVar);
                return this;
            }
            throw new NullPointerException("part == null");
        }

        public w a() {
            if (!this.f4690c.isEmpty()) {
                return new w(this.f4688a, this.f4689b, this.f4690c);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final s f4691a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final b0 f4692b;

        private b(s sVar, b0 b0Var) {
            this.f4691a = sVar;
            this.f4692b = b0Var;
        }

        public static b a(s sVar, b0 b0Var) {
            if (b0Var == null) {
                throw new NullPointerException("body == null");
            }
            if (sVar != null && sVar.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (sVar == null || sVar.a("Content-Length") == null) {
                return new b(sVar, b0Var);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
    }

    static {
        v.a("multipart/alternative");
        v.a("multipart/digest");
        v.a("multipart/parallel");
        f4683f = v.a("multipart/form-data");
        g = new byte[]{58, 32};
        h = new byte[]{ap.k, 10};
        i = new byte[]{45, 45};
    }

    w(e.f fVar, v vVar, List<b> list) {
        this.f4684a = fVar;
        this.f4685b = v.a(vVar + "; boundary=" + fVar.i());
        this.f4686c = d.h0.c.a(list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long a(e.d dVar, boolean z) {
        e.c cVar;
        if (z) {
            dVar = new e.c();
            cVar = dVar;
        } else {
            cVar = 0;
        }
        int size = this.f4686c.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f4686c.get(i2);
            s sVar = bVar.f4691a;
            b0 b0Var = bVar.f4692b;
            dVar.write(i);
            dVar.a(this.f4684a);
            dVar.write(h);
            if (sVar != null) {
                int iB = sVar.b();
                for (int i3 = 0; i3 < iB; i3++) {
                    dVar.b(sVar.a(i3)).write(g).b(sVar.b(i3)).write(h);
                }
            }
            v vVarContentType = b0Var.contentType();
            if (vVarContentType != null) {
                dVar.b("Content-Type: ").b(vVarContentType.toString()).write(h);
            }
            long jContentLength = b0Var.contentLength();
            if (jContentLength != -1) {
                dVar.b("Content-Length: ").h(jContentLength).write(h);
            } else if (z) {
                cVar.a();
                return -1L;
            }
            dVar.write(h);
            if (z) {
                j += jContentLength;
            } else {
                b0Var.writeTo(dVar);
            }
            dVar.write(h);
        }
        dVar.write(i);
        dVar.a(this.f4684a);
        dVar.write(i);
        dVar.write(h);
        if (!z) {
            return j;
        }
        long jQ = j + cVar.q();
        cVar.a();
        return jQ;
    }

    @Override // d.b0
    public long contentLength() {
        long j = this.f4687d;
        if (j != -1) {
            return j;
        }
        long jA = a(null, true);
        this.f4687d = jA;
        return jA;
    }

    @Override // d.b0
    public v contentType() {
        return this.f4685b;
    }

    @Override // d.b0
    public void writeTo(e.d dVar) {
        a(dVar, false);
    }
}
