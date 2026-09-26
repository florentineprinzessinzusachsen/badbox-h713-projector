package j1;

import android.util.Base64;
import d0.h;
import d0.l0;
import h1.c0;
import j2.i;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import u1.k;
import w2.q;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f1256a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final z2.c f1257b = new z2.c();

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object a(h hVar, a2.c cVar) {
        a aVar;
        if (cVar instanceof a) {
            aVar = (a) cVar;
            int i4 = aVar.f1235i;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                aVar.f1235i = i4 - Integer.MIN_VALUE;
            } else {
                aVar = new a(this, cVar);
            }
        } else {
            aVar = new a(this, cVar);
        }
        Object obj = aVar.f1233g;
        int i5 = aVar.f1235i;
        try {
            if (i5 == 0) {
                l0.M(obj);
                c0.f1036a.getClass();
                String str = (String) c0.f1046k.a(c0.f1037b[4]);
                i.e(str, "encrypted");
                try {
                    byte[] bArrDecode = Base64.decode(str, 2);
                    i.b(bArrDecode);
                    byte[] bArrB = f.b(f.a(bArrDecode));
                    Charset charset = StandardCharsets.UTF_8;
                    i.d(charset, "UTF_8");
                    str = new String(bArrB, charset);
                } catch (Exception unused) {
                }
                c cVar2 = new c(str, hVar, null);
                aVar.f1235i = 1;
                q qVar = new q(aVar, aVar.g());
                Object objB = z1.d.b(qVar, qVar, cVar2);
                z1.a aVar2 = z1.a.f2781d;
                if (objB == aVar2) {
                    return aVar2;
                }
            } else {
                if (i5 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                l0.M(obj);
            }
        } catch (Exception unused2) {
        }
        return k.f2301a;
    }
}
