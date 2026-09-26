package com.hs.p.common.utils;

import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class UUIDUtils {
    public static String getAsString() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase(Locale.getDefault());
    }
}
