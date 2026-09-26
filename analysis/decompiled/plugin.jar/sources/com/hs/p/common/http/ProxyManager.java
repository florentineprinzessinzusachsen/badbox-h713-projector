package com.hs.p.common.http;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.hs.p.common.utils.IoUtils;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class ProxyManager {
    private static final Uri URI_PREFER_APN = Uri.parse("content://telephony/carriers/preferapn");

    public static class ProxyNode {
        private final String apn;
        private final int port;
        private final String proxy;

        private ProxyNode(String str, String str2, int i) {
            this.apn = str;
            this.proxy = str2;
            this.port = i;
        }

        public int getPort() {
            return this.port;
        }

        public String getProxy() {
            return this.proxy;
        }

        public String toString() {
            return this.apn + " " + this.proxy + ":" + this.port;
        }
    }

    private static String getDefaultApnNode(String str) {
        if (TextUtils.empty(str)) {
            return null;
        }
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.contains("cmwap") || lowerCase.contains("uniwap") || lowerCase.contains("3gwap")) {
            return "10.0.0.172";
        }
        if (lowerCase.contains("ctwap")) {
            return "10.0.0.200";
        }
        return null;
    }

    public static ProxyNode getNode(Context context) {
        try {
            String networkInfo = SystemUtils.getNetworkInfo(context);
            if (TextUtils.empty(networkInfo) || !networkInfo.toLowerCase(Locale.getDefault()).contains("wap")) {
                return null;
            }
            return readProxyNode(context);
        } catch (Exception unused) {
            return null;
        }
    }

    private static int parsePort(String str) {
        try {
            if (TextUtils.empty(str)) {
                return 80;
            }
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 80;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static ProxyNode readProxyNode(Context context) throws Throwable {
        Cursor cursor = null;
        Object[] objArr = 0;
        try {
            Cursor cursorQuery = context.getContentResolver().query(URI_PREFER_APN, new String[]{"apn", "proxy", "port"}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        do {
                            String string = cursorQuery.getString(cursorQuery.getColumnIndex("apn"));
                            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("proxy"));
                            String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("port"));
                            if (!TextUtils.empty(string)) {
                                if (TextUtils.empty(string2)) {
                                    string2 = getDefaultApnNode(string);
                                }
                                if (!TextUtils.empty(string2)) {
                                    ProxyNode proxyNode = new ProxyNode(string, string2, parsePort(string3));
                                    IoUtils.close(cursorQuery);
                                    return proxyNode;
                                }
                            }
                        } while (cursorQuery.moveToNext());
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    IoUtils.close(cursor);
                    throw th;
                }
            }
            IoUtils.close(cursorQuery);
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
