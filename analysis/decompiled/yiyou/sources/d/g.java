package d;

import com.baidu.mobstat.Config;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: compiled from: CertificatePinner.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g f4321c = new a().a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<b> f4322a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d.h0.l.c f4323b;

    /* JADX INFO: compiled from: CertificatePinner.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<b> f4324a = new ArrayList();

        public g a() {
            return new g(new LinkedHashSet(this.f4324a), null);
        }
    }

    /* JADX INFO: compiled from: CertificatePinner.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f4325a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final String f4326b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final String f4327c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final e.f f4328d;

        boolean a(String str) {
            if (!this.f4325a.startsWith("*.")) {
                return str.equals(this.f4326b);
            }
            int iIndexOf = str.indexOf(46);
            if ((str.length() - iIndexOf) - 1 == this.f4326b.length()) {
                String str2 = this.f4326b;
                if (str.regionMatches(false, iIndexOf + 1, str2, 0, str2.length())) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                if (this.f4325a.equals(bVar.f4325a) && this.f4327c.equals(bVar.f4327c) && this.f4328d.equals(bVar.f4328d)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return ((((527 + this.f4325a.hashCode()) * 31) + this.f4327c.hashCode()) * 31) + this.f4328d.hashCode();
        }

        public String toString() {
            return this.f4327c + this.f4328d.a();
        }
    }

    g(Set<b> set, d.h0.l.c cVar) {
        this.f4322a = set;
        this.f4323b = cVar;
    }

    static e.f b(X509Certificate x509Certificate) {
        return e.f.a(x509Certificate.getPublicKey().getEncoded()).e();
    }

    public void a(String str, List<Certificate> list) {
        List<b> listA = a(str);
        if (listA.isEmpty()) {
            return;
        }
        d.h0.l.c cVar = this.f4323b;
        if (cVar != null) {
            list = cVar.a(list, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i);
            int size2 = listA.size();
            e.f fVarB = null;
            e.f fVarA = null;
            for (int i2 = 0; i2 < size2; i2++) {
                b bVar = listA.get(i2);
                if (bVar.f4327c.equals("sha256/")) {
                    if (fVarB == null) {
                        fVarB = b(x509Certificate);
                    }
                    if (bVar.f4328d.equals(fVarB)) {
                        return;
                    }
                } else {
                    if (!bVar.f4327c.equals("sha1/")) {
                        throw new AssertionError("unsupported hashAlgorithm: " + bVar.f4327c);
                    }
                    if (fVarA == null) {
                        fVarA = a(x509Certificate);
                    }
                    if (bVar.f4328d.equals(fVarA)) {
                        return;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Certificate pinning failure!");
        sb.append("\n  Peer certificate chain:");
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            X509Certificate x509Certificate2 = (X509Certificate) list.get(i3);
            sb.append("\n    ");
            sb.append(a((Certificate) x509Certificate2));
            sb.append(": ");
            sb.append(x509Certificate2.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        int size4 = listA.size();
        for (int i4 = 0; i4 < size4; i4++) {
            b bVar2 = listA.get(i4);
            sb.append("\n    ");
            sb.append(bVar2);
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (d.h0.c.a(this.f4323b, gVar.f4323b) && this.f4322a.equals(gVar.f4322a)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        d.h0.l.c cVar = this.f4323b;
        return ((cVar != null ? cVar.hashCode() : 0) * 31) + this.f4322a.hashCode();
    }

    List<b> a(String str) {
        List<b> listEmptyList = Collections.emptyList();
        for (b bVar : this.f4322a) {
            if (bVar.a(str)) {
                if (listEmptyList.isEmpty()) {
                    listEmptyList = new ArrayList<>();
                }
                listEmptyList.add(bVar);
            }
        }
        return listEmptyList;
    }

    g a(d.h0.l.c cVar) {
        return d.h0.c.a(this.f4323b, cVar) ? this : new g(this.f4322a, cVar);
    }

    public static String a(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return "sha256/" + b((X509Certificate) certificate).a();
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    static e.f a(X509Certificate x509Certificate) {
        return e.f.a(x509Certificate.getPublicKey().getEncoded()).d();
    }
}
