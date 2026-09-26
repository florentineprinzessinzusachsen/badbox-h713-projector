package w2;

import d0.l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f2650a = 0;

    static {
        Object objL;
        Object objL2;
        Exception exc = new Exception();
        String simpleName = a.a.class.getSimpleName();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        new StackTraceElement("_COROUTINE.".concat(simpleName), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            objL = a2.a.class.getCanonicalName();
        } catch (Throwable th) {
            objL = l0.l(th);
        }
        if (u1.h.a(objL) != null) {
            objL = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        try {
            objL2 = s.class.getCanonicalName();
        } catch (Throwable th2) {
            objL2 = l0.l(th2);
        }
        if (u1.h.a(objL2) != null) {
            objL2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
    }
}
