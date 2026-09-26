package h1;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import java.lang.reflect.Method;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1033d;

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, u1.c] */
    @Override // i2.a
    public final Object a() {
        Class<?> returnType;
        switch (this.f1033d) {
            case 0:
                String string = UUID.randomUUID().toString();
                j2.i.d(string, "toString(...)");
                return string;
            case 1:
                return u1.k.f2301a;
            case 2:
                return Boolean.TRUE;
            case 3:
                try {
                    Method declaredMethod = SQLiteDatabase.class.getDeclaredMethod("getThreadSession", null);
                    declaredMethod.setAccessible(true);
                    return declaredMethod;
                } catch (Throwable unused) {
                    return null;
                }
            default:
                try {
                    Method method = (Method) y.c.f2679g.getValue();
                    if (method == null || (returnType = method.getReturnType()) == null) {
                        return null;
                    }
                    Class<?> cls = Integer.TYPE;
                    return returnType.getDeclaredMethod("beginTransaction", cls, SQLiteTransactionListener.class, cls, CancellationSignal.class);
                } catch (Throwable unused2) {
                    return null;
                }
        }
    }
}
