package a3;

import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f158a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String[] f159b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String[] f160c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f161d;

    public final j a() {
        return new j(this.f158a, this.f161d, this.f159b, this.f160c);
    }

    public final void b(g... gVarArr) {
        j2.i.e(gVarArr, "cipherSuites");
        if (!this.f158a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(gVarArr.length);
        for (g gVar : gVarArr) {
            arrayList.add(gVar.f144a);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        c((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final void c(String... strArr) {
        j2.i.e(strArr, "cipherSuites");
        if (!this.f158a) {
            throw new IllegalArgumentException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        j2.i.d(objArrCopyOf, "copyOf(...)");
        this.f159b = (String[]) objArrCopyOf;
    }

    public final void d(h0... h0VarArr) {
        if (!this.f158a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        ArrayList arrayList = new ArrayList(h0VarArr.length);
        for (h0 h0Var : h0VarArr) {
            arrayList.add(h0Var.f157d);
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        e((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public final void e(String... strArr) {
        j2.i.e(strArr, "tlsVersions");
        if (!this.f158a) {
            throw new IllegalArgumentException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        j2.i.d(objArrCopyOf, "copyOf(...)");
        this.f160c = (String[]) objArrCopyOf;
    }
}
