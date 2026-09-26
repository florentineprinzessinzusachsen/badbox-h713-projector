package com.link.core.b;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static Pattern a;
    public static Pattern b;
    public static Pattern c;

    /* JADX INFO: renamed from: com.link.core.b.a$a, reason: collision with other inner class name */
    public static class C0001a {
        public String a = null;
        public long b = new Date().getTime();
        public ArrayList<d> c = new ArrayList<>();
    }

    public enum b {
        CACHED,
        DONE,
        FAILED
    }

    public interface c {
        void a();
    }

    public interface d {
        void a(b bVar, InetAddress inetAddress);
    }

    static {
        try {
            a = Pattern.compile("(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])", 2);
            b = Pattern.compile("([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}", 2);
            c = Pattern.compile("((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)", 2);
        } catch (PatternSyntaxException unused) {
            System.out.println("Neither");
        }
    }

    public static boolean a(String str) {
        if (a.matcher(str).matches() || b.matcher(str).matches()) {
            return true;
        }
        return c.matcher(str).matches();
    }
}
