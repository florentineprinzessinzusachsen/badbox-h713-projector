package h3;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d[] f1106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f1107b;

    static {
        d dVar = new d(d.f1084i, "");
        q3.h hVar = d.f1081f;
        d dVar2 = new d(hVar, "GET");
        d dVar3 = new d(hVar, "POST");
        q3.h hVar2 = d.f1082g;
        d dVar4 = new d(hVar2, "/");
        d dVar5 = new d(hVar2, "/index.html");
        q3.h hVar3 = d.f1083h;
        d dVar6 = new d(hVar3, "http");
        d dVar7 = new d(hVar3, "https");
        q3.h hVar4 = d.f1080e;
        d[] dVarArr = {dVar, dVar2, dVar3, dVar4, dVar5, dVar6, dVar7, new d(hVar4, "200"), new d(hVar4, "204"), new d(hVar4, "206"), new d(hVar4, "304"), new d(hVar4, "400"), new d(hVar4, "404"), new d(hVar4, "500"), new d("accept-charset", ""), new d("accept-encoding", "gzip, deflate"), new d("accept-language", ""), new d("accept-ranges", ""), new d("accept", ""), new d("access-control-allow-origin", ""), new d("age", ""), new d("allow", ""), new d("authorization", ""), new d("cache-control", ""), new d("content-disposition", ""), new d("content-encoding", ""), new d("content-language", ""), new d("content-length", ""), new d("content-location", ""), new d("content-range", ""), new d("content-type", ""), new d("cookie", ""), new d("date", ""), new d("etag", ""), new d("expect", ""), new d("expires", ""), new d("from", ""), new d("host", ""), new d("if-match", ""), new d("if-modified-since", ""), new d("if-none-match", ""), new d("if-range", ""), new d("if-unmodified-since", ""), new d("last-modified", ""), new d("link", ""), new d("location", ""), new d("max-forwards", ""), new d("proxy-authenticate", ""), new d("proxy-authorization", ""), new d("range", ""), new d("referer", ""), new d("refresh", ""), new d("retry-after", ""), new d("server", ""), new d("set-cookie", ""), new d("strict-transport-security", ""), new d("transfer-encoding", ""), new d("user-agent", ""), new d("vary", ""), new d("via", ""), new d("www-authenticate", "")};
        f1106a = dVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61, 1.0f);
        for (int i4 = 0; i4 < 61; i4++) {
            if (!linkedHashMap.containsKey(dVarArr[i4].f1085a)) {
                linkedHashMap.put(dVarArr[i4].f1085a, Integer.valueOf(i4));
            }
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        j2.i.d(mapUnmodifiableMap, "unmodifiableMap(...)");
        f1107b = mapUnmodifiableMap;
    }

    public static void a(q3.h hVar) throws IOException {
        j2.i.e(hVar, "name");
        int iA = hVar.a();
        for (int i4 = 0; i4 < iA; i4++) {
            byte bD = hVar.d(i4);
            if (65 <= bD && bD < 91) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: ".concat(hVar.j()));
            }
        }
    }
}
