package d;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FormBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends b0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final v f4652c = v.a("application/x-www-form-urlencoded");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<String> f4653a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<String> f4654b;

    /* JADX INFO: compiled from: FormBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<String> f4655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<String> f4656b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Charset f4657c;

        public a() {
            this(null);
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f4655a.add(t.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.f4657c));
            this.f4656b.add(t.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, this.f4657c));
            return this;
        }

        public a b(String str, String str2) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            if (str2 == null) {
                throw new NullPointerException("value == null");
            }
            this.f4655a.add(t.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.f4657c));
            this.f4656b.add(t.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, this.f4657c));
            return this;
        }

        public a(Charset charset) {
            this.f4655a = new ArrayList();
            this.f4656b = new ArrayList();
            this.f4657c = charset;
        }

        public q a() {
            return new q(this.f4655a, this.f4656b);
        }
    }

    q(List<String> list, List<String> list2) {
        this.f4653a = d.h0.c.a(list);
        this.f4654b = d.h0.c.a(list2);
    }

    public int a() {
        return this.f4653a.size();
    }

    public String b(int i) {
        return this.f4654b.get(i);
    }

    public String c(int i) {
        return t.a(a(i), true);
    }

    @Override // d.b0
    public long contentLength() {
        return a(null, true);
    }

    @Override // d.b0
    public v contentType() {
        return f4652c;
    }

    public String d(int i) {
        return t.a(b(i), true);
    }

    @Override // d.b0
    public void writeTo(e.d dVar) {
        a(dVar, false);
    }

    public String a(int i) {
        return this.f4653a.get(i);
    }

    private long a(e.d dVar, boolean z) {
        e.c cVarC;
        if (z) {
            cVarC = new e.c();
        } else {
            cVarC = dVar.c();
        }
        int size = this.f4653a.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cVarC.writeByte(38);
            }
            cVarC.b(this.f4653a.get(i));
            cVarC.writeByte(61);
            cVarC.b(this.f4654b.get(i));
        }
        if (!z) {
            return 0L;
        }
        long jQ = cVarC.q();
        cVarC.a();
        return jQ;
    }
}
