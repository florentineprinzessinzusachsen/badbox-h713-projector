package com.android.umanalytics.http.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MapUtils {

    static class MapKeyComparator implements Comparator<String> {
        MapKeyComparator() {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        TreeMap treeMap = new TreeMap(new MapKeyComparator());
        treeMap.putAll(map);
        return treeMap;
    }
}
