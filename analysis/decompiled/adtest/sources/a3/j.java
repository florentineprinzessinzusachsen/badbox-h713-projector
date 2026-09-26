package a3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f163e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final j f164f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f166b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f167c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f168d;

    static {
        g gVar = g.f141r;
        g gVar2 = g.f142s;
        g gVar3 = g.f143t;
        g gVar4 = g.f135l;
        g gVar5 = g.f137n;
        g gVar6 = g.f136m;
        g gVar7 = g.f138o;
        g gVar8 = g.f140q;
        g gVar9 = g.f139p;
        List listR = v1.i.R(new g[]{gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7, gVar8, gVar9});
        List listR2 = v1.i.R(new g[]{gVar, gVar2, gVar3, gVar4, gVar5, gVar6, gVar7, gVar8, gVar9, g.f133j, g.f134k, g.f131h, g.f132i, g.f129f, g.f130g, g.f128e});
        i iVar = new i();
        g[] gVarArr = (g[]) listR.toArray(new g[0]);
        iVar.b((g[]) Arrays.copyOf(gVarArr, gVarArr.length));
        h0 h0Var = h0.f151f;
        h0 h0Var2 = h0.f152g;
        iVar.d(h0Var, h0Var2);
        iVar.f161d = true;
        iVar.a();
        i iVar2 = new i();
        g[] gVarArr2 = (g[]) listR2.toArray(new g[0]);
        iVar2.b((g[]) Arrays.copyOf(gVarArr2, gVarArr2.length));
        iVar2.d(h0Var, h0Var2);
        iVar2.f161d = true;
        f163e = iVar2.a();
        i iVar3 = new i();
        g[] gVarArr3 = (g[]) listR2.toArray(new g[0]);
        iVar3.b((g[]) Arrays.copyOf(gVarArr3, gVarArr3.length));
        iVar3.d(h0Var, h0Var2, h0.f153h, h0.f154i);
        iVar3.f161d = true;
        iVar3.a();
        f164f = new j(false, false, null, null);
    }

    public j(boolean z3, boolean z4, String[] strArr, String[] strArr2) {
        this.f165a = z3;
        this.f166b = z4;
        this.f167c = strArr;
        this.f168d = strArr2;
    }

    public final void a(SSLSocket sSLSocket, boolean z3) {
        String[] enabledProtocols;
        String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        j2.i.b(enabledCipherSuites);
        String[] strArr = this.f167c;
        if (strArr != null) {
            enabledCipherSuites = b3.d.i(strArr, enabledCipherSuites, g.f126c);
        }
        String[] strArr2 = this.f168d;
        if (strArr2 != null) {
            String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
            j2.i.d(enabledProtocols2, "getEnabledProtocols(...)");
            enabledProtocols = b3.d.i(enabledProtocols2, strArr2, x1.a.f2672b);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        j2.i.b(supportedCipherSuites);
        f fVar = g.f126c;
        byte[] bArr = b3.d.f343a;
        int length = supportedCipherSuites.length;
        int i4 = 0;
        while (true) {
            if (i4 >= length) {
                i4 = -1;
                break;
            } else if (fVar.compare(supportedCipherSuites[i4], "TLS_FALLBACK_SCSV") == 0) {
                break;
            } else {
                i4++;
            }
        }
        if (z3 && i4 != -1) {
            String str = supportedCipherSuites[i4];
            j2.i.d(str, "get(...)");
            j2.i.e(enabledCipherSuites, "<this>");
            Object[] objArrCopyOf = Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length + 1);
            j2.i.d(objArrCopyOf, "copyOf(...)");
            enabledCipherSuites = (String[]) objArrCopyOf;
            enabledCipherSuites[enabledCipherSuites.length - 1] = str;
        }
        i iVar = new i();
        iVar.f158a = this.f165a;
        iVar.f159b = strArr;
        iVar.f160c = strArr2;
        iVar.f161d = this.f166b;
        iVar.c((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
        iVar.e((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length));
        j jVarA = iVar.a();
        if (jVarA.c() != null) {
            sSLSocket.setEnabledProtocols(jVarA.f168d);
        }
        if (jVarA.b() != null) {
            sSLSocket.setEnabledCipherSuites(jVarA.f167c);
        }
    }

    public final ArrayList b() {
        String[] strArr = this.f167c;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(g.f125b.c(str));
        }
        return arrayList;
    }

    public final ArrayList c() {
        String[] strArr = this.f168d;
        if (strArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            h0.f150e.getClass();
            arrayList.add(b.d(str));
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        j jVar = (j) obj;
        boolean z3 = jVar.f165a;
        boolean z4 = this.f165a;
        if (z4 != z3) {
            return false;
        }
        if (z4) {
            return Arrays.equals(this.f167c, jVar.f167c) && Arrays.equals(this.f168d, jVar.f168d) && this.f166b == jVar.f166b;
        }
        return true;
    }

    public final int hashCode() {
        if (!this.f165a) {
            return 17;
        }
        String[] strArr = this.f167c;
        int iHashCode = (527 + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        String[] strArr2 = this.f168d;
        return ((iHashCode + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31) + (!this.f166b ? 1 : 0);
    }

    public final String toString() {
        if (!this.f165a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + Objects.toString(b(), "[all enabled]") + ", tlsVersions=" + Objects.toString(c(), "[all enabled]") + ", supportsTlsExtensions=" + this.f166b + ')';
    }
}
