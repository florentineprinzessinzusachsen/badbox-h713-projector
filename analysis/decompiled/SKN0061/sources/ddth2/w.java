package ddth2;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static String a(String str, e eVar) {
        try {
            return new String(y.b(v.a(v.a(str, eVar.c(), eVar.a())), a0.a(eVar.m30a(), eVar.b())), StandardCharsets.UTF_8);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(String str, e eVar) {
        try {
            return v.a(v.a(y.b(str.getBytes(), a0.a(eVar.m30a(), eVar.b()))), eVar.a(), eVar.c());
        } catch (Exception unused) {
            return "";
        }
    }
}
