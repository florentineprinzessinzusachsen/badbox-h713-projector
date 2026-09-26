package d;

import com.baidu.mobstat.Config;
import java.io.IOException;

/* JADX INFO: compiled from: Protocol.java */
/* JADX INFO: loaded from: classes.dex */
public enum y {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2(Config.EVENT_NATIVE_VIEW_HIERARCHY),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");


    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4710a;

    y(String str) {
        this.f4710a = str;
    }

    public static y a(String str) throws IOException {
        if (str.equals(HTTP_1_0.f4710a)) {
            return HTTP_1_0;
        }
        if (str.equals(HTTP_1_1.f4710a)) {
            return HTTP_1_1;
        }
        if (str.equals(H2_PRIOR_KNOWLEDGE.f4710a)) {
            return H2_PRIOR_KNOWLEDGE;
        }
        if (str.equals(HTTP_2.f4710a)) {
            return HTTP_2;
        }
        if (str.equals(SPDY_3.f4710a)) {
            return SPDY_3;
        }
        if (str.equals(QUIC.f4710a)) {
            return QUIC;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f4710a;
    }
}
