package l1;

import a3.w;
import a3.x;
import android.os.Handler;
import android.os.Looper;
import j2.i;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import s0.n;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f1368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final n f1369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Handler f1370c;

    static {
        w wVar = new w();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        wVar.a(20L);
        wVar.b(30L);
        wVar.d(30L);
        wVar.f231l = new a1.a(8);
        c3.a aVar = new c3.a(1);
        ArrayList arrayList = wVar.f222c;
        arrayList.add(aVar);
        arrayList.add(new p1.a());
        wVar.c(b(), new a());
        wVar.f238s = new c1.a(1);
        f1368a = new x(wVar);
        w wVar2 = new w();
        wVar2.a(30L);
        wVar2.b(40L);
        wVar2.d(40L);
        wVar2.f231l = new a1.a(8);
        wVar2.f222c.add(new c3.a(3));
        wVar2.c(b(), new a());
        wVar2.f238s = new c1.a(1);
        new x(wVar2);
        f1369b = new n();
        f1370c = new Handler(Looper.getMainLooper());
        new LinkedHashMap();
    }

    public static String a(String str, Map map) {
        i.e(str, "url");
        StringBuilder sb = new StringBuilder(str);
        if (!map.isEmpty()) {
            sb.append("?");
            for (Map.Entry entry : map.entrySet()) {
                String str2 = (String) entry.getKey();
                Object value = entry.getValue();
                sb.append(URLEncoder.encode(str2, "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(value.toString(), "UTF-8"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        String string = sb.toString();
        i.d(string, "toString(...)");
        return string;
    }

    public static SSLSocketFactory b() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustManagerArr = {new a()};
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        i.d(socketFactory, "getSocketFactory(...)");
        return socketFactory;
    }
}
