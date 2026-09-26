package c1;

import a3.x;
import android.util.Log;
import com.speed.ad.NativePluginHttpBridge;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import l1.f;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements HostnameVerifier {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f369a;

    public /* synthetic */ a(int i4) {
        this.f369a = i4;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        switch (this.f369a) {
            case 0:
                return NativePluginHttpBridge.client$lambda$0(str, sSLSession);
            case 1:
                x xVar = f.f1368a;
                return true;
            case 2:
                Log.d("SSLUtils", "Per-connection HostnameVerifier: Accepting hostname: " + str);
                return true;
            case 3:
                Log.d("SSLUtils", "HostnameVerifier: Accepting hostname: " + str);
                return true;
            default:
                x xVar2 = q1.f.f1797a;
                return true;
        }
    }
}
