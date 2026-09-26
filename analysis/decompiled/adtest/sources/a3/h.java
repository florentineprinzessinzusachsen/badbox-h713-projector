package a3;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.profileinstaller.ProfileInstallReceiver;
import d0.l0;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements e3.i, o.f, w.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f148d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f149e;

    public /* synthetic */ h(int i4, Object obj) {
        this.f148d = i4;
        this.f149e = obj;
    }

    @Override // w.b
    public w.a a(String str) {
        j2.i.e(str, "fileName");
        return new s.a(((x.d) this.f149e).M());
    }

    @Override // o.f
    public void b() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // e3.i
    public e3.q c() throws Throwable {
        IOException iOException = null;
        while (!((e3.s) this.f149e).f815k.f782s) {
            try {
                e3.v vVarB = ((e3.s) this.f149e).b();
                if (!vVarB.b()) {
                    e3.u uVarD = vVarB.d();
                    if (uVarD.f823b == null && uVarD.f824c == null) {
                        uVarD = vVarB.e();
                    }
                    e3.v vVar = uVarD.f823b;
                    Throwable th = uVarD.f824c;
                    if (th != null) {
                        throw th;
                    }
                    if (vVar != null) {
                        ((e3.s) this.f149e).f820p.addFirst(vVar);
                    }
                }
                return vVarB.f();
            } catch (IOException e4) {
                if (iOException == null) {
                    iOException = e4;
                } else {
                    l3.h.a(iOException, e4);
                }
                if (!((e3.s) this.f149e).a(null)) {
                    throw iOException;
                }
            }
        }
        throw new IOException("Canceled");
    }

    @Override // e3.i
    public e3.s d() {
        return (e3.s) this.f149e;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public void e(p.b0 b0Var, a2.c cVar) {
        p.l lVar;
        if (cVar instanceof p.l) {
            lVar = (p.l) cVar;
            int i4 = lVar.f1682i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                lVar.f1682i = i4 - Integer.MIN_VALUE;
            } else {
                lVar = new p.l(this, cVar);
            }
        } else {
            lVar = new p.l(this, cVar);
        }
        Object obj = lVar.f1680g;
        int i5 = lVar.f1682i;
        if (i5 != 0) {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            l0.M(obj);
            throw new a0.c();
        }
        l0.M(obj);
        u2.r rVar = (u2.r) this.f149e;
        lVar.f1682i = 1;
        rVar.b(b0Var, lVar);
    }

    @Override // o.f
    public void f(int i4, Object obj) {
        String str;
        switch (i4) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i4 == 6 || i4 == 7 || i4 == 8) {
            Log.e("ProfileInstaller", str, (Throwable) obj);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.f149e).setResultCode(i4);
    }

    public synchronized void g(g0 g0Var) {
        j2.i.e(g0Var, "route");
        ((LinkedHashSet) this.f149e).remove(g0Var);
    }

    public void h(Set set) {
        Object obj;
        int[] iArr;
        h hVar = v2.c.f2527b;
        j2.i.e(set, "tableIds");
        if (set.isEmpty()) {
            return;
        }
        u2.r rVar = (u2.r) this.f149e;
        do {
            rVar.getClass();
            obj = u2.r.f2361h.get(rVar);
            if (obj == hVar) {
                obj = null;
            }
            int[] iArr2 = (int[]) obj;
            int length = iArr2.length;
            iArr = new int[length];
            for (int i4 = 0; i4 < length; i4++) {
                iArr[i4] = set.contains(Integer.valueOf(i4)) ? iArr2[i4] + 1 : iArr2[i4];
            }
            if (obj == null) {
                obj = hVar;
            }
        } while (!rVar.g(obj, iArr));
    }

    public String toString() {
        switch (this.f148d) {
            case 10:
                return "<" + ((String) this.f149e) + '>';
            default:
                return super.toString();
        }
    }

    public h(x.d dVar) {
        this.f148d = 8;
        j2.i.e(dVar, "openHelper");
        this.f149e = dVar;
    }

    public h(int i4, byte b4) {
        Handler handler;
        Handler handlerB;
        this.f148d = i4;
        switch (i4) {
            case 3:
                Looper mainLooper = Looper.getMainLooper();
                if (Build.VERSION.SDK_INT >= 28) {
                    handlerB = c.d.b(mainLooper);
                } else {
                    try {
                        handler = (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(mainLooper, null, Boolean.TRUE);
                    } catch (IllegalAccessException e4) {
                        e = e4;
                        Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
                        handler = new Handler(mainLooper);
                    } catch (InstantiationException e5) {
                        e = e5;
                        Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
                        handler = new Handler(mainLooper);
                    } catch (NoSuchMethodException e6) {
                        e = e6;
                        Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", e);
                        handler = new Handler(mainLooper);
                    } catch (InvocationTargetException e7) {
                        Throwable cause = e7.getCause();
                        if (!(cause instanceof RuntimeException)) {
                            if (cause instanceof Error) {
                                throw ((Error) cause);
                            }
                            throw new RuntimeException(cause);
                        }
                        throw ((RuntimeException) cause);
                    }
                    handlerB = handler;
                    break;
                }
                this.f149e = handlerB;
                return;
            case 4:
                this.f149e = new LinkedHashSet();
                return;
            case 11:
                this.f149e = null;
                return;
            default:
                j2.i.e(TimeUnit.MINUTES, "timeUnit");
                d3.e eVar = d3.e.f542l;
                j2.i.e(eVar, "taskRunner");
                this.f149e = new e3.r(eVar);
                return;
        }
    }

    public h(b3.f fVar) {
        this.f148d = 2;
        this.f149e = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), fVar);
    }

    public h(int i4) {
        this.f148d = 7;
        this.f149e = new u2.r(new int[i4]);
    }
}
