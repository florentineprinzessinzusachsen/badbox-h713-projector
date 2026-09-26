package androidx.core.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final h f887a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a.b.e<String, Typeface> f888b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            f887a = new g();
        } else if (i >= 26) {
            f887a = new f();
        } else if (i >= 24 && e.a()) {
            f887a = new e();
        } else if (Build.VERSION.SDK_INT >= 21) {
            f887a = new d();
        } else {
            f887a = new h();
        }
        f888b = new a.b.e<>(16);
    }

    private static String a(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface b(Resources resources, int i, int i2) {
        return f888b.b(a(resources, i, i2));
    }

    public static Typeface a(Context context, androidx.core.content.c.c.a aVar, Resources resources, int i, int i2, androidx.core.content.c.f.a aVar2, Handler handler, boolean z) {
        Typeface typefaceA;
        if (aVar instanceof androidx.core.content.c.c.d) {
            androidx.core.content.c.c.d dVar = (androidx.core.content.c.c.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.a() == 0) {
                z2 = true;
            }
            typefaceA = androidx.core.c.b.a(context, dVar.b(), aVar2, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            typefaceA = f887a.a(context, (androidx.core.content.c.c.b) aVar, resources, i2);
            if (aVar2 != null) {
                if (typefaceA != null) {
                    aVar2.a(typefaceA, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typefaceA != null) {
            f888b.a(a(resources, i, i2), typefaceA);
        }
        return typefaceA;
    }

    private static Typeface b(Context context, Typeface typeface, int i) {
        androidx.core.content.c.c.b bVarA = f887a.a(typeface);
        if (bVarA == null) {
            return null;
        }
        return f887a.a(context, bVarA, context.getResources(), i);
    }

    public static Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceA = f887a.a(context, resources, i, str, i2);
        if (typefaceA != null) {
            f888b.a(a(resources, i, i2), typefaceA);
        }
        return typefaceA;
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, androidx.core.c.b.f[] fVarArr, int i) {
        return f887a.a(context, cancellationSignal, fVarArr, i);
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        Typeface typefaceB;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceB = b(context, typeface, i)) == null) ? Typeface.create(typeface, i) : typefaceB;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }
}
