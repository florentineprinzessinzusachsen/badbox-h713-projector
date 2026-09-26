package ddth2;

import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class t {
    public static String a(e eVar, String str, long j) {
        String strM38c = eVar.m38c();
        Uri.Builder builder = new Uri.Builder();
        builder.appendQueryParameter("c", eVar.m34b());
        builder.appendQueryParameter("u", str);
        builder.appendQueryParameter("t", String.valueOf(j));
        builder.appendQueryParameter("tpm", String.valueOf(eVar.m()));
        builder.appendQueryParameter("pcm", String.valueOf(eVar.e()));
        return strM38c + builder.build().toString();
    }
}
