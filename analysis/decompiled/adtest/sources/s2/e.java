package s2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import d0.l0;
import j2.i;
import java.lang.reflect.InvocationTargetException;
import u1.g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {
    private static volatile Choreographer choreographer;

    static {
        Object objL;
        try {
            objL = new d(a(Looper.getMainLooper()), false);
        } catch (Throwable th) {
            objL = l0.l(th);
        }
        if (objL instanceof g) {
            objL = null;
        }
    }

    public static final Handler a(Looper looper) throws IllegalAccessException, InvocationTargetException {
        if (Build.VERSION.SDK_INT < 28) {
            try {
                return (Handler) Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(looper, null, Boolean.TRUE);
            } catch (NoSuchMethodException unused) {
                return new Handler(looper);
            }
        }
        Object objInvoke = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke(null, looper);
        i.c(objInvoke, "null cannot be cast to non-null type android.os.Handler");
        return (Handler) objInvoke;
    }
}
