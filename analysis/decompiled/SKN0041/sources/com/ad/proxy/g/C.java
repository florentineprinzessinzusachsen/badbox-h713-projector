package com.ad.proxy.g;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {
    public static String a(LinkedHashMap linkedHashMap, String str) throws NoSuchAlgorithmException, InvalidKeyException {
        if (linkedHashMap.isEmpty()) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("appKey 不能为空");
        }
        TreeMap treeMap = new TreeMap(linkedHashMap);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append((String) entry.getKey());
            sb.append("=");
            sb.append((String) entry.getValue());
        }
        Mac mac = Mac.getInstance("HmacSHA256");
        Charset charset = StandardCharsets.UTF_8;
        mac.init(new SecretKeySpec(str.getBytes(charset), "HmacSHA256"));
        byte[] bArrDoFinal = mac.doFinal(sb.toString().getBytes(charset));
        StringBuilder sb2 = new StringBuilder();
        for (byte b : bArrDoFinal) {
            sb2.append(String.format("%02x", Byte.valueOf(b)));
        }
        return sb2.toString();
    }
}
