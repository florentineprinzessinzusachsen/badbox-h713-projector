package g3;

import a3.r;
import j2.i;
import java.util.ArrayList;
import q3.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q3.g f959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f960b;

    public a(o oVar) {
        i.e(oVar, "source");
        this.f959a = oVar;
        this.f960b = 262144L;
    }

    public final r a() {
        ArrayList arrayList = new ArrayList(20);
        while (true) {
            String strT = this.f959a.t(this.f960b);
            this.f960b -= (long) strT.length();
            if (strT.length() == 0) {
                return new r((String[]) arrayList.toArray(new String[0]));
            }
            int iE0 = p2.i.E0(strT, ':', 1, 4);
            if (iE0 != -1) {
                String strSubstring = strT.substring(0, iE0);
                i.d(strSubstring, "substring(...)");
                String strSubstring2 = strT.substring(iE0 + 1);
                i.d(strSubstring2, "substring(...)");
                arrayList.add(strSubstring);
                arrayList.add(p2.i.S0(strSubstring2).toString());
            } else if (strT.charAt(0) == ':') {
                String strSubstring3 = strT.substring(1);
                i.d(strSubstring3, "substring(...)");
                arrayList.add("");
                arrayList.add(p2.i.S0(strSubstring3).toString());
            } else {
                arrayList.add("");
                arrayList.add(p2.i.S0(strT).toString());
            }
        }
    }
}
