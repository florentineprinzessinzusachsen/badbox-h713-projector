package l3;

import a3.x;
import android.util.Log;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import v1.q;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CopyOnWriteArraySet f1373a = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f1374b;

    static {
        Map mapSingletonMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package r4 = x.class.getPackage();
        String name = r4 != null ? r4.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        linkedHashMap.put(x.class.getName(), "okhttp.OkHttpClient");
        linkedHashMap.put(h3.h.class.getName(), "okhttp.Http2");
        linkedHashMap.put(d3.e.class.getName(), "okhttp.TaskRunner");
        linkedHashMap.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        int size = linkedHashMap.size();
        if (size == 0) {
            mapSingletonMap = q.f2518d;
        } else if (size != 1) {
            mapSingletonMap = new LinkedHashMap(linkedHashMap);
        } else {
            Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
            mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
            j2.i.d(mapSingletonMap, "with(...)");
        }
        f1374b = mapSingletonMap;
    }

    public static void a(String str, int i4, String str2, Throwable th) {
        int iMin;
        String strR0 = (String) f1374b.get(str);
        if (strR0 == null) {
            strR0 = p2.i.R0(23, str);
        }
        if (Log.isLoggable(strR0, i4)) {
            if (th != null) {
                str2 = str2 + '\n' + Log.getStackTraceString(th);
            }
            int length = str2.length();
            int i5 = 0;
            while (i5 < length) {
                int iE0 = p2.i.E0(str2, '\n', i5, 4);
                if (iE0 == -1) {
                    iE0 = length;
                }
                while (true) {
                    iMin = Math.min(iE0, i5 + 4000);
                    String strSubstring = str2.substring(i5, iMin);
                    j2.i.d(strSubstring, "substring(...)");
                    Log.println(i4, strR0, strSubstring);
                    if (iMin >= iE0) {
                        break;
                    } else {
                        i5 = iMin;
                    }
                }
                i5 = iMin + 1;
            }
        }
    }

    public static void b(String str, String str2) {
        Level level;
        Logger logger = Logger.getLogger(str);
        if (f1373a.add(logger)) {
            logger.setUseParentHandlers(false);
            if (Log.isLoggable(str2, 3)) {
                level = Level.FINE;
            } else {
                level = Log.isLoggable(str2, 4) ? Level.INFO : Level.WARNING;
            }
            logger.setLevel(level);
            logger.addHandler(e.f1375a);
        }
    }
}
