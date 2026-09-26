package com.hs.p.basic;

import com.hs.p.common.utils.LOG;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HTTPDNS {
    private static final long DNS_RESOLVE_TIMEOUT = 5000;
    private static final String TAG = "HTTPDNS";
    private static final ConcurrentHashMap<String, DNSRecord> sDNSCache = new ConcurrentHashMap<>();
    private static final long CACHE_EXPIRE_TIME = TimeUnit.MINUTES.toMillis(5);
    private static final String[] DNS_SERVERS = {"https://dns.google/resolve?name=%s&type=A", "https://cloudflare-dns.com/dns-query?name=%s&type=A", "https://doh.pub/dns-query?name=%s&type=A", "http://119.29.29.29/d?dn=%s", "http://223.5.5.5/resolve?name=%s&type=A"};
    private static final ExecutorService sDnsExecutor = Executors.newCachedThreadPool();

    private static class DNSRecord {
        final List<String> ips;
        final long timestamp = System.currentTimeMillis();

        DNSRecord(List<String> list) {
            this.ips = Collections.unmodifiableList(new ArrayList(list));
        }

        boolean isExpired() {
            return System.currentTimeMillis() - this.timestamp > HTTPDNS.CACHE_EXPIRE_TIME;
        }
    }

    public static class URLConvertResult {
        public final String originalHost;
        public final String url;

        URLConvertResult(String str, String str2) {
            this.url = str;
            this.originalHost = str2;
        }
    }

    public static void clearCache() {
        sDNSCache.clear();
        LOG.d(TAG, "DNS cache cleared");
    }

    public static URLConvertResult convertUrlToIP(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String strExtractHostname = extractHostname(str);
        if (strExtractHostname.isEmpty() || isValidIP(strExtractHostname)) {
            return new URLConvertResult(str, strExtractHostname);
        }
        List<String> listResolve = resolve(strExtractHostname);
        if (listResolve.isEmpty()) {
            LOG.w(TAG, "Failed to resolve hostname: " + strExtractHostname);
            return null;
        }
        String strReplace = str.replace(strExtractHostname, (String) listResolve.get(0));
        LOG.d(TAG, "Converted URL: " + str + " -> " + strReplace + " (host: " + strExtractHostname + ")");
        return new URLConvertResult(strReplace, strExtractHostname);
    }

    public static List<URLConvertResult> convertUrlsToIP(List<String> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            URLConvertResult uRLConvertResultConvertUrlToIP = convertUrlToIP(str);
            if (uRLConvertResultConvertUrlToIP != null) {
                arrayList.add(uRLConvertResultConvertUrlToIP);
            } else {
                arrayList.add(new URLConvertResult(str, extractHostname(str)));
                LOG.w(TAG, "Failed to convert URL to IP, using original: " + str);
            }
        }
        return arrayList;
    }

    public static String extractHostname(String str) {
        return extractHostnameInternal(str);
    }

    private static String extractHostnameInternal(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String strReplaceFirst = str.replaceFirst("^https?://", "");
        int iIndexOf = strReplaceFirst.indexOf(47);
        if (iIndexOf > 0) {
            strReplaceFirst = strReplaceFirst.substring(0, iIndexOf);
        }
        int iIndexOf2 = strReplaceFirst.indexOf(58);
        if (iIndexOf2 > 0) {
            strReplaceFirst = strReplaceFirst.substring(0, iIndexOf2);
        }
        return strReplaceFirst.trim();
    }

    public static String extractIP(String str) {
        String strExtractHostnameInternal = extractHostnameInternal(str);
        if (isValidIP(strExtractHostnameInternal)) {
            return strExtractHostnameInternal;
        }
        return null;
    }

    public static String getFirstIP(String str) {
        List<String> listResolve = resolve(str);
        if (listResolve.isEmpty()) {
            return null;
        }
        return listResolve.get(0);
    }

    public static boolean isHostResolvable(String str) {
        return !resolve(str).isEmpty();
    }

    private static boolean isValidIP(String str) {
        if (str != null && !str.isEmpty()) {
            String[] strArrSplit = str.split("\\.");
            if (strArrSplit.length != 4) {
                return false;
            }
            try {
                for (String str2 : strArrSplit) {
                    int i = Integer.parseInt(str2);
                    if (i < 0 || i > 255) {
                        return false;
                    }
                }
                return true;
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    private static List<String> parseDNSResponse(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (str == null || str.trim().isEmpty()) {
            return arrayList;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("{") || strTrim.startsWith("[")) {
            return parseJsonDNSResponse(strTrim);
        }
        if (isValidIP(strTrim)) {
            arrayList.add(strTrim);
            return arrayList;
        }
        String[] strArr = {";", ","};
        for (int i = 0; i < 2; i++) {
            String str3 = strArr[i];
            if (strTrim.contains(str3)) {
                for (String str4 : strTrim.split(str3)) {
                    String strTrim2 = str4.trim();
                    if (isValidIP(strTrim2)) {
                        arrayList.add(strTrim2);
                    }
                }
                if (!arrayList.isEmpty()) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    private static List<String> parseJsonDNSResponse(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("Answer")) {
                JSONArray jSONArray = jSONObject.getJSONArray("Answer");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    int iOptInt = jSONObject2.optInt("type", 0);
                    if ((iOptInt == 1 || iOptInt == 0) && jSONObject2.has("data")) {
                        String string = jSONObject2.getString("data");
                        if (isValidIP(string)) {
                            arrayList.add(string);
                        }
                    }
                }
            }
            if (arrayList.isEmpty() && jSONObject.has("data")) {
                Object obj = jSONObject.get("data");
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (isValidIP(str2)) {
                        arrayList.add(str2);
                    }
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray2 = (JSONArray) obj;
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        String string2 = jSONArray2.getString(i2);
                        if (isValidIP(string2)) {
                            arrayList.add(string2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.d(TAG, "Failed to parse JSON response: " + e.getMessage());
        }
        return arrayList;
    }

    public static List<String> resolve(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }
        String strExtractHostname = extractHostname(str);
        DNSRecord dNSRecord = sDNSCache.get(strExtractHostname);
        if (dNSRecord != null && !dNSRecord.isExpired()) {
            LOG.d(TAG, "DNS cache hit: " + strExtractHostname + " -> " + dNSRecord.ips);
            return dNSRecord.ips;
        }
        List<String> listResolveParallel = resolveParallel(strExtractHostname);
        if (!listResolveParallel.isEmpty()) {
            return listResolveParallel;
        }
        LOG.w(TAG, "All HTTPDNS servers failed, falling back to system DNS for: " + strExtractHostname);
        return resolveSystemDNS(strExtractHostname);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> resolveFromServer(String str, String str2) throws Exception {
        HttpURLConnection httpURLConnection;
        String str3 = String.format(str2, str);
        BufferedReader bufferedReader = null;
        try {
            if (str3.startsWith("https://")) {
                SSLUtils.enableSSLIgnore();
            }
            httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
            try {
                if (httpURLConnection instanceof HttpsURLConnection) {
                    SSLUtils.configureSSLIgnore((HttpsURLConnection) httpURLConnection);
                }
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(com.cloudmedia.tv.server.a.m);
                httpURLConnection.setReadTimeout(com.cloudmedia.tv.server.a.m);
                httpURLConnection.setRequestProperty("Accept", "application/dns-json");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    throw new Exception("HTTP " + responseCode);
                }
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader2.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                    List<String> dNSResponse = parseDNSResponse(sb.toString(), str);
                    try {
                        bufferedReader2.close();
                    } catch (Exception unused) {
                    }
                    httpURLConnection.disconnect();
                    return dNSResponse;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception unused2) {
                        }
                    }
                    if (httpURLConnection == null) {
                        throw th;
                    }
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0066 A[LOOP:2: B:18:0x0060->B:20:0x0066, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:24:0x0079  */
    /* JADX WARN: Code duplicated, block: B:40:? A[RETURN, SYNTHETIC] */
    private static List<String> resolveParallel(final String str) {
        Iterator it;
        List<String> list;
        final AtomicReference atomicReference = new AtomicReference(null);
        final AtomicReference atomicReference2 = new AtomicReference(null);
        final Object obj = new Object();
        ArrayList arrayList = new ArrayList();
        for (final String str2 : DNS_SERVERS) {
            arrayList.add(sDnsExecutor.submit(new Runnable() { // from class: com.hs.p.basic.HTTPDNS.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        List listResolveFromServer = HTTPDNS.resolveFromServer(str, str2);
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (listResolveFromServer.isEmpty()) {
                            return;
                        }
                        synchronized (obj) {
                            try {
                                if (atomicReference.get() == null) {
                                    atomicReference.set(listResolveFromServer);
                                    atomicReference2.set(str2);
                                    HTTPDNS.sDNSCache.put(str, new DNSRecord(listResolveFromServer));
                                    LOG.d(HTTPDNS.TAG, "DNS resolved (winner, " + jCurrentTimeMillis2 + "ms): " + str + " -> " + listResolveFromServer + " (via " + str2 + ")");
                                    obj.notifyAll();
                                } else {
                                    LOG.d(HTTPDNS.TAG, "DNS resolved (slower, " + jCurrentTimeMillis2 + "ms): " + str + " -> " + listResolveFromServer + " (via " + str2 + ", winner was " + ((String) atomicReference2.get()) + ")");
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    } catch (Exception e) {
                        LOG.w(HTTPDNS.TAG, "DNS resolve failed from " + str2 + ": " + e.getMessage());
                    }
                }
            }));
        }
        synchronized (obj) {
            long jCurrentTimeMillis = System.currentTimeMillis() + DNS_RESOLVE_TIMEOUT;
            while (atomicReference.get() == null) {
                long jCurrentTimeMillis2 = jCurrentTimeMillis - System.currentTimeMillis();
                if (jCurrentTimeMillis2 <= 0) {
                    break;
                }
                try {
                    obj.wait(jCurrentTimeMillis2);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((Future) it.next()).cancel(false);
                    }
                    list = (List) atomicReference.get();
                    if (list != null) {
                        return list;
                    }
                    return Collections.emptyList();
                }
            }
        }
        it = arrayList.iterator();
        while (it.hasNext()) {
            ((Future) it.next()).cancel(false);
        }
        list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        return Collections.emptyList();
    }

    private static List<String> resolveSystemDNS(String str) {
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            ArrayList arrayList = new ArrayList();
            for (InetAddress inetAddress : allByName) {
                arrayList.add(inetAddress.getHostAddress());
            }
            if (!arrayList.isEmpty()) {
                sDNSCache.put(str, new DNSRecord(arrayList));
            }
            return arrayList;
        } catch (Exception e) {
            LOG.e(TAG, "System DNS resolve failed for: " + str, e);
            return Collections.emptyList();
        }
    }

    public static void clearCache(String str) {
        sDNSCache.remove(str);
        LOG.d(TAG, "DNS cache cleared for: " + str);
    }
}
