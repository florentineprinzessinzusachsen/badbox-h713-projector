package d.h0.f;

import d.k;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpecSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<k> f4383a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f4384b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4385c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4386d;

    public b(List<k> list) {
        this.f4383a = list;
    }

    private boolean b(SSLSocket sSLSocket) {
        for (int i = this.f4384b; i < this.f4383a.size(); i++) {
            if (this.f4383a.get(i).a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public k a(SSLSocket sSLSocket) throws UnknownServiceException {
        k kVar;
        int i = this.f4384b;
        int size = this.f4383a.size();
        while (true) {
            if (i >= size) {
                kVar = null;
                break;
            }
            kVar = this.f4383a.get(i);
            if (kVar.a(sSLSocket)) {
                this.f4384b = i + 1;
                break;
            }
            i++;
        }
        if (kVar != null) {
            this.f4385c = b(sSLSocket);
            d.h0.a.f4335a.a(kVar, sSLSocket, this.f4386d);
            return kVar;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f4386d + ", modes=" + this.f4383a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    public boolean a(IOException iOException) {
        this.f4386d = true;
        if (!this.f4385c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException);
    }
}
