package com.szns.sdk;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class x {
    public static void a(String str, String str2, ah ahVar) {
        if (n.a().b(new y(str, str2, ahVar))) {
            return;
        }
        ahVar.b("scheduler pool exhausted");
    }

    public static void a(String str, String str2, e eVar) {
        if (n.a().b(new aa(str, str2, eVar))) {
            return;
        }
        eVar.b("scheduler pool exhausted");
    }

    public static void a(String str, Map map, ab abVar) {
        if (n.a().b(new z(map, str, abVar))) {
            return;
        }
        abVar.b("scheduler pool exhausted");
    }
}
