package d;

import java.io.IOException;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;

/* JADX INFO: compiled from: Handshake.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f0 f4658a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f4659b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<Certificate> f4660c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<Certificate> f4661d;

    private r(f0 f0Var, h hVar, List<Certificate> list, List<Certificate> list2) {
        this.f4658a = f0Var;
        this.f4659b = hVar;
        this.f4660c = list;
        this.f4661d = list2;
    }

    public static r a(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if ("SSL_NULL_WITH_NULL_NULL".equals(cipherSuite)) {
            throw new IOException("cipherSuite == SSL_NULL_WITH_NULL_NULL");
        }
        h hVarA = h.a(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        f0 f0VarA = f0.a(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
            peerCertificates = null;
        }
        List listA = peerCertificates != null ? d.h0.c.a(peerCertificates) : Collections.emptyList();
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new r(f0VarA, hVarA, listA, localCertificates != null ? d.h0.c.a(localCertificates) : Collections.emptyList());
    }

    public List<Certificate> b() {
        return this.f4661d;
    }

    public List<Certificate> c() {
        return this.f4660c;
    }

    public f0 d() {
        return this.f4658a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f4658a.equals(rVar.f4658a) && this.f4659b.equals(rVar.f4659b) && this.f4660c.equals(rVar.f4660c) && this.f4661d.equals(rVar.f4661d);
    }

    public int hashCode() {
        return ((((((527 + this.f4658a.hashCode()) * 31) + this.f4659b.hashCode()) * 31) + this.f4660c.hashCode()) * 31) + this.f4661d.hashCode();
    }

    public static r a(f0 f0Var, h hVar, List<Certificate> list, List<Certificate> list2) {
        if (f0Var == null) {
            throw new NullPointerException("tlsVersion == null");
        }
        if (hVar != null) {
            return new r(f0Var, hVar, d.h0.c.a(list), d.h0.c.a(list2));
        }
        throw new NullPointerException("cipherSuite == null");
    }

    public h a() {
        return this.f4659b;
    }
}
