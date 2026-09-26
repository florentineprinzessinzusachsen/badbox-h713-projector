package ddth2;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class n {
    public n(Context context) {
    }

    public final String a(e eVar, String str) {
        String strM41d = eVar.m41d();
        if (strM41d == null || strM41d.isEmpty()) {
            return str + ", u:";
        }
        return str + ", u:" + strM41d;
    }

    public void a() {
    }

    public void a(e eVar) {
        m63a(eVar, "active:false");
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final void m63a(e eVar, String str) {
        if (eVar == null || !eVar.m29a()) {
            return;
        }
        Log.i("ddth", a(eVar, str));
    }

    public void b(e eVar) {
        m63a(eVar, "active:true");
    }

    public void c(e eVar) {
        m63a(eVar, "loadInfo:false");
    }

    public void d(e eVar) {
        m63a(eVar, "loadInfo:true");
    }

    public void e(e eVar) {
        m63a(eVar, "runStart:true");
    }

    public void f(e eVar) {
        m63a(eVar, "runStop:true");
    }
}
