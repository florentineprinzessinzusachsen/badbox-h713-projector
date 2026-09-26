package n1;

import j2.i;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import l3.h;
import org.json.JSONArray;
import org.json.JSONObject;
import p2.p;
import v1.l;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f1489a = TimeUnit.MINUTES.toMillis(5);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ExecutorService f1490b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f1491c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap f1492d;

    static {
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        i.d(executorServiceNewCachedThreadPool, "newCachedThreadPool(...)");
        f1490b = executorServiceNewCachedThreadPool;
        f1491c = new String[]{"https://dns.google/resolve?name=%s&type=A", "https://cloudflare-dns.com/dns-query?name=%s&type=A", "https://doh.pub/dns-query?name=%s&type=A", "http://119.29.29.29/d?dn=%s", "http://223.5.5.5/resolve?name=%s&type=A"};
        f1492d = new ConcurrentHashMap();
    }

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        List listO0 = p2.i.O0(str, new String[]{"."}, 6);
        if (listO0.size() != 4) {
            return false;
        }
        try {
            if (listO0.isEmpty()) {
                return true;
            }
            Iterator it = listO0.iterator();
            while (it.hasNext()) {
                int i4 = Integer.parseInt((String) it.next());
                if (i4 < 0 || i4 >= 256) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public static ArrayList b(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null && !p2.i.H0(str)) {
            String string = p2.i.S0(str).toString();
            if (a(string)) {
                arrayList.add(string);
                return arrayList;
            }
            String[] strArr = {";", ","};
            for (int i4 = 0; i4 < 2; i4++) {
                String str2 = strArr[i4];
                if (p2.i.B0(string, str2)) {
                    List listO0 = p2.i.O0(string, new String[]{str2}, 6);
                    ArrayList arrayList2 = new ArrayList(l.u0(listO0));
                    Iterator it = listO0.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(p2.i.S0((String) it.next()).toString());
                    }
                    int size = arrayList2.size();
                    int i5 = 0;
                    while (i5 < size) {
                        Object obj = arrayList2.get(i5);
                        i5++;
                        String str3 = (String) obj;
                        if (a(str3)) {
                            arrayList.add(str3);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("Answer")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("Answer");
                    int length = jSONArray.length();
                    for (int i6 = 0; i6 < length; i6++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i6);
                        int iOptInt = jSONObject2.optInt("type", 0);
                        if ((iOptInt == 0 || iOptInt == 1) && jSONObject2.has("data")) {
                            String string2 = jSONObject2.getString("data");
                            if (a(string2)) {
                                arrayList.add(string2);
                            }
                        }
                    }
                }
                if (arrayList.isEmpty() && jSONObject.has("data")) {
                    Object obj2 = jSONObject.get("data");
                    if (obj2 instanceof String) {
                        if (a((String) obj2)) {
                            arrayList.add(obj2);
                            return arrayList;
                        }
                    } else if (obj2 instanceof JSONArray) {
                        int length2 = ((JSONArray) obj2).length();
                        for (int i7 = 0; i7 < length2; i7++) {
                            String string3 = ((JSONArray) obj2).getString(i7);
                            if (a(string3)) {
                                arrayList.add(string3);
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public static ArrayList c(String str, String str2) {
        String str3 = String.format(str2, Arrays.copyOf(new Object[]{str}, 1));
        HttpURLConnection httpURLConnection = null;
        try {
            if (p.z0(str3, "https://", false)) {
                h.w();
            }
            URLConnection uRLConnectionOpenConnection = new URL(str3).openConnection();
            i.c(uRLConnectionOpenConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) uRLConnectionOpenConnection;
            try {
                if (httpURLConnection2 instanceof HttpsURLConnection) {
                    h.q((HttpsURLConnection) httpURLConnection2);
                }
                httpURLConnection2.setRequestMethod("GET");
                httpURLConnection2.setConnectTimeout(3000);
                httpURLConnection2.setReadTimeout(3000);
                httpURLConnection2.setRequestProperty("Accept", "application/dns-json");
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode != 200) {
                    throw new Exception("HTTP " + responseCode);
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                try {
                    StringBuilder sb = new StringBuilder();
                    for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                        sb.append(line);
                    }
                    ArrayList arrayListB = b(sb.toString());
                    bufferedReader.close();
                    httpURLConnection2.disconnect();
                    return arrayListB;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        h.j(bufferedReader, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                httpURLConnection = httpURLConnection2;
                th = th3;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
