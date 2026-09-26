package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ag f3440a = new ag();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<String, String> f3441b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<Character, Integer> f3442c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HashMap<String, String> f3443d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HashMap<Character, Integer> f3444e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private HashMap<String, String> f3445f = new HashMap<>();
    private HashMap<Character, Integer> g = new HashMap<>();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static int f3447a = 0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static int f3448b = 1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static int f3449c = 2;
    }

    public static ag a() {
        return f3440a;
    }

    public void b(int i) {
        if (i == a.f3447a) {
            this.f3442c.clear();
            this.f3441b.clear();
        } else if (i == a.f3449c) {
            this.g.clear();
            this.f3445f.clear();
        } else {
            this.f3444e.clear();
            this.f3443d.clear();
        }
    }

    public String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (i == a.f3447a) {
            String str2 = this.f3441b.get(str);
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            a(str, this.f3442c, this.f3441b);
            return this.f3441b.get(str);
        }
        if (i == a.f3449c) {
            String str3 = this.f3445f.get(str);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
            a(str, this.g, this.f3445f);
            return this.f3445f.get(str);
        }
        String str4 = this.f3443d.get(str);
        if (!TextUtils.isEmpty(str4)) {
            return str4;
        }
        a(str, this.f3444e, this.f3443d);
        return this.f3443d.get(str);
    }

    public void b() {
        b(a.f3447a);
        b(a.f3449c);
        b(a.f3448b);
    }

    private void a(String str, HashMap<Character, Integer> map, HashMap<String, String> map2) {
        char lowerCase = Character.toLowerCase(str.charAt(0));
        Integer num = map.get(Character.valueOf(lowerCase));
        int iIntValue = num != null ? num.intValue() + 1 : 0;
        String str2 = Character.toString(lowerCase) + iIntValue;
        map.put(Character.valueOf(lowerCase), Integer.valueOf(iIntValue));
        map2.put(str, str2);
    }

    public JSONObject a(int i) {
        HashMap<String, String> map;
        if (i == a.f3447a) {
            map = this.f3441b;
        } else if (i == a.f3449c) {
            map = this.f3445f;
        } else {
            map = this.f3443d;
        }
        JSONObject jSONObject = new JSONObject();
        if (map == null) {
            return jSONObject;
        }
        ArrayList<Map.Entry> arrayList = new ArrayList(map.entrySet());
        try {
            Collections.sort(arrayList, new Comparator<Map.Entry<String, String>>() { // from class: com.baidu.mobstat.ag.1
                @Override // java.util.Comparator
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<String, String> entry, Map.Entry<String, String> entry2) {
                    return entry.getValue().compareTo(entry2.getValue());
                }
            });
        } catch (Exception unused) {
        }
        for (Map.Entry entry : arrayList) {
            try {
                jSONObject.put((String) entry.getValue(), (String) entry.getKey());
            } catch (Exception unused2) {
            }
        }
        return jSONObject;
    }
}
