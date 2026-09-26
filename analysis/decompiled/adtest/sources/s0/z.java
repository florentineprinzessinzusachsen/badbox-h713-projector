package s0;

import java.io.IOException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final v f2137d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w f2138e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ z[] f2139f;

    static {
        v vVar = new v();
        f2137d = vVar;
        w wVar = new w();
        f2138e = wVar;
        f2139f = new z[]{vVar, wVar, new z() { // from class: s0.x
            public static Double b(String str, a1.b bVar) throws a1.e {
                try {
                    Double dValueOf = Double.valueOf(str);
                    if (dValueOf.isInfinite() || dValueOf.isNaN()) {
                        if (bVar.f25r != 1) {
                            throw new a1.e("JSON forbids NaN and infinities: " + dValueOf + "; at path " + bVar.K(true));
                        }
                    }
                    return dValueOf;
                } catch (NumberFormatException e4) {
                    throw new a0.c("Cannot parse " + str + "; at path " + bVar.K(true), e4);
                }
            }

            @Override // s0.z
            public final Number a(a1.b bVar) throws IOException {
                String strD0 = bVar.d0();
                if (strD0.indexOf(46) >= 0) {
                    return b(strD0, bVar);
                }
                try {
                    return Long.valueOf(Long.parseLong(strD0));
                } catch (NumberFormatException unused) {
                    return b(strD0, bVar);
                }
            }
        }, new z() { // from class: s0.y
            @Override // s0.z
            public final Number a(a1.b bVar) throws IOException {
                String strD0 = bVar.d0();
                try {
                    return u0.i.j(strD0);
                } catch (NumberFormatException e4) {
                    throw new a0.c("Cannot parse " + strD0 + "; at path " + bVar.K(true), e4);
                }
            }
        }};
    }

    public static z valueOf(String str) {
        return (z) Enum.valueOf(z.class, str);
    }

    public static z[] values() {
        return (z[]) f2139f.clone();
    }

    public abstract Number a(a1.b bVar);
}
