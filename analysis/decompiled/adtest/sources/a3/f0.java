package a3;

import java.io.Closeable;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class f0 implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e0 f124d;

    static {
        q3.h hVar = q3.h.f1823g;
        j2.i.e(hVar, "<this>");
        q3.e eVar = new q3.e();
        eVar.V(hVar);
        f124d = new e0(hVar.f1824d.length, eVar);
    }

    public abstract long b();

    public abstract v c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b3.d.b(k());
    }

    public abstract q3.g k();

    /* JADX WARN: Code duplicated, block: B:7:0x0013 A[Catch: all -> 0x0026, TryCatch #1 {all -> 0x0026, blocks: (B:3:0x0005, B:5:0x000b, B:8:0x0015, B:7:0x0013), top: B:24:0x0005 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v7 */
    public final String l() {
        Charset charsetA;
        q3.g gVarK = k();
        String th = null;
        try {
            v vVarC = c();
            if (vVarC != null) {
                p2.h hVar = v.f216c;
                charsetA = vVarC.a(null);
                if (charsetA == null) {
                    charsetA = p2.a.f1738a;
                }
            } else {
                charsetA = p2.a.f1738a;
            }
            String strO = gVarK.O(b3.g.f(gVarK, charsetA));
            try {
                gVarK.close();
            } catch (Throwable th2) {
                th = th2;
            }
            th = th;
            th = strO;
        } catch (Throwable th3) {
            th = th3;
            if (gVarK != null) {
                try {
                    gVarK.close();
                } catch (Throwable th4) {
                    l3.h.a(th, th4);
                }
            }
        }
        if (th == 0) {
            return th;
        }
        throw th;
    }
}
