package b.b.a;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: FieldNamingPolicy.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements b.b.a.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f1536a = new a("IDENTITY", 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f1537b = new d("UPPER_CAMEL_CASE", 1) { // from class: b.b.a.d.b
        {
            a aVar = null;
        }

        @Override // b.b.a.e
        public String a(Field field) {
            return d.a(field.getName());
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f1538c = new d("UPPER_CAMEL_CASE_WITH_SPACES", 2) { // from class: b.b.a.d.c
        {
            a aVar = null;
        }

        @Override // b.b.a.e
        public String a(Field field) {
            return d.a(d.a(field.getName(), " "));
        }
    };

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d f1539d = new d("LOWER_CASE_WITH_UNDERSCORES", 3) { // from class: b.b.a.d.d
        {
            a aVar = null;
        }

        @Override // b.b.a.e
        public String a(Field field) {
            return d.a(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f1540e = new d("LOWER_CASE_WITH_DASHES", 4) { // from class: b.b.a.d.e
        {
            a aVar = null;
        }

        @Override // b.b.a.e
        public String a(Field field) {
            return d.a(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    };

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d f1541f = new d("LOWER_CASE_WITH_DOTS", 5) { // from class: b.b.a.d.f
        {
            a aVar = null;
        }

        @Override // b.b.a.e
        public String a(Field field) {
            return d.a(field.getName(), ".").toLowerCase(Locale.ENGLISH);
        }
    };
    private static final /* synthetic */ d[] g = {f1536a, f1537b, f1538c, f1539d, f1540e, f1541f};

    /* JADX INFO: compiled from: FieldNamingPolicy.java */
    static enum a extends d {
        a(String str, int i) {
            super(str, i, null);
        }

        @Override // b.b.a.e
        public String a(Field field) {
            return field.getName();
        }
    }

    private d(String str, int i) {
        super(str, i);
    }

    static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static d valueOf(String str) {
        return (d) Enum.valueOf(d.class, str);
    }

    public static d[] values() {
        return (d[]) g.clone();
    }

    /* synthetic */ d(String str, int i, a aVar) {
        this(str, i);
    }

    static String a(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char cCharAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i++;
            cCharAt = str.charAt(i);
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        sb.append(a(Character.toUpperCase(cCharAt), str, i + 1));
        return sb.toString();
    }

    private static String a(char c2, String str, int i) {
        if (i < str.length()) {
            return c2 + str.substring(i);
        }
        return String.valueOf(c2);
    }
}
