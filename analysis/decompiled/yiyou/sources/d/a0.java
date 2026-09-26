package d;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final t f4236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f4237b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final s f4238c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final b0 f4239d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Map<Class<?>, Object> f4240e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile d f4241f;

    a0(a aVar) {
        this.f4236a = aVar.f4242a;
        this.f4237b = aVar.f4243b;
        this.f4238c = aVar.f4244c.a();
        this.f4239d = aVar.f4245d;
        this.f4240e = d.h0.c.a(aVar.f4246e);
    }

    public String a(String str) {
        return this.f4238c.a(str);
    }

    public List<String> b(String str) {
        return this.f4238c.b(str);
    }

    public s c() {
        return this.f4238c;
    }

    public boolean d() {
        return this.f4236a.h();
    }

    public String e() {
        return this.f4237b;
    }

    public a f() {
        return new a(this);
    }

    public t g() {
        return this.f4236a;
    }

    public String toString() {
        return "Request{method=" + this.f4237b + ", url=" + this.f4236a + ", tags=" + this.f4240e + '}';
    }

    /* JADX INFO: compiled from: Request.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        t f4242a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f4243b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        s.a f4244c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        b0 f4245d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Map<Class<?>, Object> f4246e;

        public a() {
            this.f4246e = Collections.emptyMap();
            this.f4243b = "GET";
            this.f4244c = new s.a();
        }

        public a a(t tVar) {
            if (tVar == null) {
                throw new NullPointerException("url == null");
            }
            this.f4242a = tVar;
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            a(t.d(str));
            return this;
        }

        public a a(String str, String str2) {
            this.f4244c.a(str, str2);
            return this;
        }

        public a a(String str) {
            this.f4244c.c(str);
            return this;
        }

        a(a0 a0Var) {
            Map<Class<?>, Object> linkedHashMap;
            this.f4246e = Collections.emptyMap();
            this.f4242a = a0Var.f4236a;
            this.f4243b = a0Var.f4237b;
            this.f4245d = a0Var.f4239d;
            if (a0Var.f4240e.isEmpty()) {
                linkedHashMap = Collections.emptyMap();
            } else {
                linkedHashMap = new LinkedHashMap<>(a0Var.f4240e);
            }
            this.f4246e = linkedHashMap;
            this.f4244c = a0Var.f4238c.a();
        }

        public a a(s sVar) {
            this.f4244c = sVar.a();
            return this;
        }

        public a a(d dVar) {
            String string = dVar.toString();
            if (string.isEmpty()) {
                a("Cache-Control");
                return this;
            }
            b("Cache-Control", string);
            return this;
        }

        public a b(String str, String str2) {
            this.f4244c.c(str, str2);
            return this;
        }

        public a a(b0 b0Var) {
            a("POST", b0Var);
            return this;
        }

        public a a(String str, b0 b0Var) {
            if (str != null) {
                if (str.length() != 0) {
                    if (b0Var != null && !d.h0.g.f.b(str)) {
                        throw new IllegalArgumentException("method " + str + " must not have a request body.");
                    }
                    if (b0Var == null && d.h0.g.f.e(str)) {
                        throw new IllegalArgumentException("method " + str + " must have a request body.");
                    }
                    this.f4243b = str;
                    this.f4245d = b0Var;
                    return this;
                }
                throw new IllegalArgumentException("method.length() == 0");
            }
            throw new NullPointerException("method == null");
        }

        public a0 a() {
            if (this.f4242a != null) {
                return new a0(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    public b0 a() {
        return this.f4239d;
    }

    public d b() {
        d dVar = this.f4241f;
        if (dVar != null) {
            return dVar;
        }
        d dVarA = d.a(this.f4238c);
        this.f4241f = dVarA;
        return dVarA;
    }
}
