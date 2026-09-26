package a3;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h0 f193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f195c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.i f196d;

    public p(h0 h0Var, g gVar, List list, i2.a aVar) {
        this.f193a = h0Var;
        this.f194b = gVar;
        this.f195c = list;
        this.f196d = new u1.i(new n(0, aVar));
    }

    public final List a() {
        return (List) this.f196d.getValue();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return pVar.f193a == this.f193a && j2.i.a(pVar.f194b, this.f194b) && j2.i.a(pVar.a(), a()) && j2.i.a(pVar.f195c, this.f195c);
    }

    public final int hashCode() {
        return this.f195c.hashCode() + ((a().hashCode() + ((this.f194b.hashCode() + ((this.f193a.hashCode() + 527) * 31)) * 31)) * 31);
    }

    public final String toString() {
        String type;
        String type2;
        List<Certificate> listA = a();
        ArrayList arrayList = new ArrayList(v1.l.u0(listA));
        for (Certificate certificate : listA) {
            if (certificate instanceof X509Certificate) {
                type2 = ((X509Certificate) certificate).getSubjectDN().toString();
            } else {
                type2 = certificate.getType();
                j2.i.d(type2, "getType(...)");
            }
            arrayList.add(type2);
        }
        String string = arrayList.toString();
        StringBuilder sb = new StringBuilder("Handshake{tlsVersion=");
        sb.append(this.f193a);
        sb.append(" cipherSuite=");
        sb.append(this.f194b);
        sb.append(" peerCertificates=");
        sb.append(string);
        sb.append(" localCertificates=");
        List<Certificate> list = this.f195c;
        ArrayList arrayList2 = new ArrayList(v1.l.u0(list));
        for (Certificate certificate2 : list) {
            if (certificate2 instanceof X509Certificate) {
                type = ((X509Certificate) certificate2).getSubjectDN().toString();
            } else {
                type = certificate2.getType();
                j2.i.d(type, "getType(...)");
            }
            arrayList2.add(type);
        }
        sb.append(arrayList2);
        sb.append('}');
        return sb.toString();
    }
}
