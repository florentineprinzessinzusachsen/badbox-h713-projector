package a3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f281b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f283d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f284e = b3.a.f339a;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f280a = "GET";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f282c = new q();

    public void a(String str, String str2) {
        q qVar = (q) this.f282c;
        qVar.getClass();
        l3.h.I(str);
        l3.h.J(str2, str);
        l3.h.n(qVar, str, str2);
    }

    public void b(String str, String str2) {
        j2.i.e(str2, "value");
        q qVar = (q) this.f282c;
        qVar.getClass();
        l3.h.I(str);
        l3.h.J(str2, str);
        qVar.b(str);
        l3.h.n(qVar, str, str2);
    }

    public void c(String str, b0 b0Var) {
        j2.i.e(str, "method");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("method.isEmpty() == true");
        }
        if (b0Var == null) {
            if (str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("QUERY") || str.equals("REPORT")) {
                throw new IllegalArgumentException(("method " + str + " must have a request body.").toString());
            }
        } else if (!a.a.y(str)) {
            throw new IllegalArgumentException(("method " + str + " must not have a request body.").toString());
        }
        this.f280a = str;
        this.f283d = b0Var;
    }

    public void d(String str) {
        j2.i.e(str, "url");
        if (p2.p.z0(str, "ws:", true)) {
            String strSubstring = str.substring(3);
            j2.i.d(strSubstring, "substring(...)");
            str = "http:".concat(strSubstring);
        } else if (p2.p.z0(str, "wss:", true)) {
            String strSubstring2 = str.substring(4);
            j2.i.d(strSubstring2, "substring(...)");
            str = "https:".concat(strSubstring2);
        }
        j2.i.e(str, "<this>");
        s sVar = new s();
        sVar.c(null, str);
        this.f281b = sVar.a();
    }
}
