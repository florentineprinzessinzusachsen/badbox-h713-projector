package a2;

import a3.d0;
import android.os.Handler;
import android.os.Looper;
import com.speed.net.ApiResponse;
import d0.l0;
import e3.p;
import i2.l;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import n.j;
import q3.o;
import q3.t;
import q3.u;
import t1.m;
import t1.n;
import t1.r;
import t1.s;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements t, a3.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f44d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f45e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f46f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f47g;

    public /* synthetic */ f(Object obj, Object obj2, Object obj3, int i4) {
        this.f44d = i4;
        this.f45e = obj;
        this.f46f = obj2;
        this.f47g = obj3;
    }

    public void a(n.c cVar) {
        j jVar = (j) this.f47g;
        if (jVar != null) {
            jVar.run();
        }
        j jVar2 = new j((n.f) this.f45e, cVar);
        this.f47g = jVar2;
        ((Handler) this.f46f).postAtFrontOfQueue(jVar2);
    }

    @Override // a3.d
    public void b(p pVar, d0 d0Var) {
        switch (this.f44d) {
            case 4:
                l lVar = (l) this.f47g;
                if (!d0Var.f118s) {
                    l1.f.f1370c.post(new f0.a(d0Var, lVar, 7, false));
                } else {
                    try {
                        String strL = d0Var.f109j.l();
                        if (strL.length() != 0 && !j2.i.a(p2.i.S0(strL).toString(), "null")) {
                            if (((o1.a) this.f45e) == o1.a.f1559e) {
                                strL = l3.h.u(strL);
                            }
                            try {
                                try {
                                    l3.h.d0(d0Var, strL);
                                    Object objB = l1.f.f1369b.b(strL, (Type) this.f46f);
                                    j2.i.d(objB, "fromJson(...)");
                                    l1.f.f1370c.post(new t1.l((ApiResponse) objB, strL, lVar, 0));
                                } catch (Exception unused) {
                                    l1.f.f1370c.post(new f0.a(l1.f.f1369b.b(strL, new m().f2779b), lVar, 6, false));
                                    return;
                                }
                            } catch (Exception e4) {
                                l3.h.c0("直接解析为T失败: " + e4.getMessage() + ", , 响应数据: " + strL);
                                l1.f.f1370c.post(new n(e4, lVar, 0));
                                return;
                            }
                        }
                        l1.f.f1370c.post(new d3.d(2, lVar));
                    } catch (Exception e5) {
                        l3.h.c0("解密或解析失败: " + e5.getMessage() + ", 响应数据: ");
                        l1.f.f1370c.post(new n(e5, lVar, 1));
                        return;
                    }
                }
                break;
            default:
                i2.p pVar2 = (i2.p) this.f47g;
                if (!d0Var.f118s) {
                    l1.f.f1370c.post(new f0.a(d0Var, pVar2, 10, false));
                } else {
                    try {
                        String strL2 = d0Var.f109j.l();
                        if (strL2.length() != 0 && !j2.i.a(p2.i.S0(strL2).toString(), "null")) {
                            if (((o1.a) this.f45e) == o1.a.f1559e) {
                                strL2 = l3.h.u(strL2);
                            }
                            try {
                                try {
                                    l3.h.d0(d0Var, strL2);
                                    Object objB2 = l1.f.f1369b.b(strL2, (Type) this.f46f);
                                    j2.i.d(objB2, "fromJson(...)");
                                    l1.f.f1370c.post(new t1.l((ApiResponse) objB2, strL2, pVar2, 1));
                                } catch (Exception unused2) {
                                    l1.f.f1370c.post(new f0.a(l1.f.f1369b.b(strL2, new r().f2779b), pVar2, 9, false));
                                    return;
                                }
                            } catch (Exception e6) {
                                l3.h.c0("直接解析为T失败: " + e6.getMessage() + ", , 响应数据: " + strL2);
                                l1.f.f1370c.post(new s(e6, pVar2, 0));
                                return;
                            }
                        }
                        l1.f.f1370c.post(new d3.d(3, pVar2));
                    } catch (Exception e7) {
                        l3.h.c0("解密或解析失败: " + e7.getMessage() + ", 响应数据: ");
                        l1.f.f1370c.post(new s(e7, pVar2, 1));
                        return;
                    }
                }
                break;
        }
    }

    @Override // q3.t
    public q3.s c() {
        return (q3.n) this.f47g;
    }

    @Override // q3.t
    public u d() {
        return (o) this.f46f;
    }

    @Override // a3.d
    public void e(p pVar, IOException iOException) {
        switch (this.f44d) {
            case 4:
                l1.f.f1370c.post(new f0.a(iOException, (l) this.f47g, 5, false));
                break;
            default:
                l1.f.f1370c.post(new f0.a(iOException, (i2.p) this.f47g, 8, false));
                break;
        }
    }

    public f(n.g gVar) {
        this.f44d = 2;
        this.f45e = new n.f(gVar);
        this.f46f = new Handler(Looper.getMainLooper());
    }

    public f(a3.l lVar) {
        this.f44d = 1;
        this.f45e = lVar;
        this.f46f = l0.f((r3.d) lVar.f186g);
        r3.c cVar = (r3.c) lVar.f187h;
        j2.i.e(cVar, "<this>");
        this.f47g = new q3.n(cVar);
    }

    public f(Matcher matcher, CharSequence charSequence) {
        this.f44d = 3;
        j2.i.e(charSequence, "input");
        this.f45e = matcher;
        this.f46f = new p2.g(this);
    }
}
