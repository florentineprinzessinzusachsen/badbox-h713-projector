package e0;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.SystemForegroundService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements k0.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f611l = d0.a0.g("Processor");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d0.b f614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a3.l f615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WorkDatabase f616e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f618g = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap f617f = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final HashSet f620i = new HashSet();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f621j = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PowerManager.WakeLock f612a = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Object f622k = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f619h = new HashMap();

    public f(Context context, d0.b bVar, a3.l lVar, WorkDatabase workDatabase) {
        this.f613b = context;
        this.f614c = bVar;
        this.f615d = lVar;
        this.f616e = workDatabase;
    }

    public static boolean e(String str, k0 k0Var, int i4) {
        String str2 = f611l;
        if (k0Var == null) {
            d0.a0.e().a(str2, "WorkerWrapper could not be found for " + str);
            return false;
        }
        k0Var.f655m.s(new z(i4));
        d0.a0.e().a(str2, "WorkerWrapper interrupted for " + str);
        return true;
    }

    public final void a(b bVar) {
        synchronized (this.f622k) {
            this.f621j.add(bVar);
        }
    }

    public final k0 b(String str) {
        k0 k0Var = (k0) this.f617f.remove(str);
        boolean z3 = k0Var != null;
        if (!z3) {
            k0Var = (k0) this.f618g.remove(str);
        }
        this.f619h.remove(str);
        if (z3) {
            synchronized (this.f622k) {
                try {
                    if (this.f617f.isEmpty()) {
                        Context context = this.f613b;
                        String str2 = k0.b.f1280j;
                        Intent intent = new Intent(context, (Class<?>) SystemForegroundService.class);
                        intent.setAction("ACTION_STOP_FOREGROUND");
                        try {
                            this.f613b.startService(intent);
                        } catch (Throwable th) {
                            d0.a0.e().d(f611l, "Unable to stop foreground service", th);
                        }
                        PowerManager.WakeLock wakeLock = this.f612a;
                        if (wakeLock != null) {
                            wakeLock.release();
                            this.f612a = null;
                        }
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        return k0Var;
    }

    public final l0.p c(String str) {
        synchronized (this.f622k) {
            try {
                k0 k0VarD = d(str);
                if (k0VarD == null) {
                    return null;
                }
                return k0VarD.f643a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final k0 d(String str) {
        k0 k0Var = (k0) this.f617f.get(str);
        return k0Var == null ? (k0) this.f618g.get(str) : k0Var;
    }

    public final boolean f(String str) {
        boolean z3;
        synchronized (this.f622k) {
            z3 = d(str) != null;
        }
        return z3;
    }

    public final void g(b bVar) {
        synchronized (this.f622k) {
            this.f621j.remove(bVar);
        }
    }

    public final boolean h(l lVar, int i4) {
        String str = lVar.f656a.f1321a;
        synchronized (this.f622k) {
            try {
                if (this.f617f.get(str) == null) {
                    Set set = (Set) this.f619h.get(str);
                    if (set != null && set.contains(lVar)) {
                        return e(str, b(str), i4);
                    }
                    return false;
                }
                d0.a0.e().a(f611l, "Ignored stopWork. WorkerWrapper " + str + " is in foreground");
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
