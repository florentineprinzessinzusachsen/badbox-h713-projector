package com.ad.proxy.g;

import com.ad.proxy.Robin;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public abstract class D {
    public static String a(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        String string = sb.toString();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (str.contains("?")) {
            for (String str2 : str.substring(str.indexOf("?") + 1).split("&")) {
                String[] strArrSplit = str2.split("=", 2);
                if (strArrSplit.length == 2) {
                    linkedHashMap.put(strArrSplit[0], strArrSplit[1]);
                }
            }
        }
        linkedHashMap.put("timestamp", String.valueOf(jCurrentTimeMillis));
        linkedHashMap.put("nonce", string);
        linkedHashMap.put("signature", C.a(linkedHashMap, Robin.appKey));
        StringBuilder sb2 = new StringBuilder(str.split("\\?")[0]);
        sb2.append("?");
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            sb2.append(M.a((String) entry.getKey()));
            sb2.append("=");
            sb2.append(M.a((String) entry.getValue()));
            sb2.append("&");
        }
        sb2.deleteCharAt(sb2.length() - 1);
        return sb2.toString();
    }
}
