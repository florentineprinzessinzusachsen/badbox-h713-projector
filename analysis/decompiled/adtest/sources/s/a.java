package s;

import j2.i;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements w.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x.a f2066d;

    public a(x.a aVar) {
        i.e(aVar, "db");
        this.f2066d = aVar;
    }

    @Override // w.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final g P(String str) {
        i.e(str, "sql");
        x.a aVar = this.f2066d;
        i.e(aVar, "db");
        String string = p2.i.S0(str).toString();
        if (string.length() >= 3) {
            String strSubstring = string.substring(0, 3);
            i.d(strSubstring, "substring(...)");
            String upperCase = strSubstring.toUpperCase(Locale.ROOT);
            i.d(upperCase, "toUpperCase(...)");
            int iHashCode = upperCase.hashCode();
            if (iHashCode == 79487 ? upperCase.equals("PRA") : !(iHashCode == 81978 ? !upperCase.equals("SEL") : !(iHashCode == 85954 && upperCase.equals("WIT")))) {
                e eVar = new e(aVar, str);
                eVar.f2074g = new int[0];
                eVar.f2075h = new long[0];
                eVar.f2076i = new double[0];
                eVar.f2077j = new String[0];
                eVar.f2078k = new byte[0][];
                return eVar;
            }
        }
        return new f(aVar, str);
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f2066d.close();
    }
}
