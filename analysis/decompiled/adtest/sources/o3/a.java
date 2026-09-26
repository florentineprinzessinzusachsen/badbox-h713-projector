package o3;

import d0.l0;
import j2.i;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f1574a;

    public a(d dVar) {
        i.e(dVar, "trustRootIndex");
        this.f1574a = dVar;
    }

    public static boolean R(X509Certificate x509Certificate, X509Certificate x509Certificate2, int i4) {
        if (!i.a(x509Certificate.getIssuerDN(), x509Certificate2.getSubjectDN()) || x509Certificate2.getBasicConstraints() < i4) {
            return false;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof a) && i.a(((a) obj).f1574a, this.f1574a);
    }

    public final int hashCode() {
        return this.f1574a.hashCode();
    }

    @Override // d0.l0
    public final List i(List list, String str) throws SSLPeerUnverifiedException {
        X509Certificate x509Certificate;
        i.e(list, "chain");
        i.e(str, "hostname");
        ArrayDeque arrayDeque = new ArrayDeque(list);
        ArrayList arrayList = new ArrayList();
        Object objRemoveFirst = arrayDeque.removeFirst();
        i.d(objRemoveFirst, "removeFirst(...)");
        arrayList.add(objRemoveFirst);
        boolean z3 = false;
        for (int i4 = 0; i4 < 9; i4++) {
            Object obj = arrayList.get(arrayList.size() - 1);
            i.c(obj, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            X509Certificate x509Certificate2 = (X509Certificate) obj;
            X509Certificate x509CertificateA = this.f1574a.a(x509Certificate2);
            if (x509CertificateA != null) {
                if (arrayList.size() > 1 || !x509Certificate2.equals(x509CertificateA)) {
                    arrayList.add(x509CertificateA);
                }
                if (R(x509CertificateA, x509CertificateA, arrayList.size() - 2)) {
                    return arrayList;
                }
                z3 = true;
            } else {
                Iterator it = arrayDeque.iterator();
                i.d(it, "iterator(...)");
                do {
                    if (!it.hasNext()) {
                        if (!z3) {
                            throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate2);
                        }
                        return arrayList;
                    }
                    Object next = it.next();
                    i.c(next, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    x509Certificate = (X509Certificate) next;
                } while (!R(x509Certificate2, x509Certificate, arrayList.size() - 1));
                it.remove();
                arrayList.add(x509Certificate);
            }
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
    }
}
