package s0;

import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f2085d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ h[] f2086e;

    static {
        a aVar = new a();
        f2085d = aVar;
        f2086e = new h[]{aVar, new h() { // from class: s0.b
            @Override // s0.h
            public final String b(Field field) {
                return h.c(field.getName());
            }
        }, new h() { // from class: s0.c
            @Override // s0.h
            public final String b(Field field) {
                return h.c(h.a(field.getName(), ' '));
            }
        }, new h() { // from class: s0.d
            @Override // s0.h
            public final String b(Field field) {
                return h.a(field.getName(), '_').toUpperCase(Locale.ENGLISH);
            }
        }, new h() { // from class: s0.e
            @Override // s0.h
            public final String b(Field field) {
                return h.a(field.getName(), '_').toLowerCase(Locale.ENGLISH);
            }
        }, new h() { // from class: s0.f
            @Override // s0.h
            public final String b(Field field) {
                return h.a(field.getName(), '-').toLowerCase(Locale.ENGLISH);
            }
        }, new h() { // from class: s0.g
            @Override // s0.h
            public final String b(Field field) {
                return h.a(field.getName(), '.').toLowerCase(Locale.ENGLISH);
            }
        }};
    }

    public static String a(String str, char c4) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i4 = 0; i4 < length; i4++) {
            char cCharAt = str.charAt(i4);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(c4);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String c(String str) {
        int length = str.length();
        for (int i4 = 0; i4 < length; i4++) {
            char cCharAt = str.charAt(i4);
            if (Character.isLetter(cCharAt)) {
                if (Character.isUpperCase(cCharAt)) {
                    break;
                }
                char upperCase = Character.toUpperCase(cCharAt);
                if (i4 == 0) {
                    return upperCase + str.substring(1);
                }
                return str.substring(0, i4) + upperCase + str.substring(i4 + 1);
            }
        }
        return str;
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f2086e.clone();
    }

    public abstract String b(Field field);
}
