package androidx.core.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import com.android.umanalytics.http.ApiException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Long, androidx.core.content.c.c.b> f898a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    class a implements c<androidx.core.c.b.f> {
        a(h hVar) {
        }

        @Override // androidx.core.a.h.c
        public int a(androidx.core.c.b.f fVar) {
            return fVar.d();
        }

        @Override // androidx.core.a.h.c
        public boolean b(androidx.core.c.b.f fVar) {
            return fVar.e();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    class b implements c<androidx.core.content.c.c.C0018c> {
        b(h hVar) {
        }

        @Override // androidx.core.a.h.c
        public int a(androidx.core.content.c.c.C0018c c0018c) {
            return c0018c.e();
        }

        @Override // androidx.core.a.h.c
        public boolean b(androidx.core.content.c.c.C0018c c0018c) {
            return c0018c.f();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    private interface c<T> {
        int a(T t);

        boolean b(T t);
    }

    h() {
    }

    private static <T> T a(T[] tArr, int i, c<T> cVar) {
        int i2 = (i & 1) == 0 ? ApiException.FAILURE : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int iAbs = (Math.abs(cVar.a(t2) - i2) * 2) + (cVar.b(t2) == z ? 0 : 1);
            if (t == null || i3 > iAbs) {
                t = t2;
                i3 = iAbs;
            }
        }
        return t;
    }

    private static long b(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        } catch (NoSuchFieldException e3) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e3);
            return 0L;
        }
    }

    protected androidx.core.c.b.f a(androidx.core.c.b.f[] fVarArr, int i) {
        return (androidx.core.c.b.f) a(fVarArr, i, new a(this));
    }

    protected Typeface a(Context context, InputStream inputStream) {
        File fileA = i.a(context);
        if (fileA == null) {
            return null;
        }
        try {
            if (i.a(fileA, inputStream)) {
                return Typeface.createFromFile(fileA.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileA.delete();
        }
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, androidx.core.c.b.f[] fVarArr, int i) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (fVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(a(fVarArr, i).c());
            try {
                Typeface typefaceA = a(context, inputStreamOpenInputStream);
                i.a(inputStreamOpenInputStream);
                return typefaceA;
            } catch (IOException unused) {
                i.a(inputStreamOpenInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStream;
                i.a(inputStream);
                throw th;
            }
        } catch (IOException unused2) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private androidx.core.content.c.c.C0018c a(androidx.core.content.c.c.b bVar, int i) {
        return (androidx.core.content.c.c.C0018c) a(bVar.a(), i, new b(this));
    }

    public Typeface a(Context context, androidx.core.content.c.c.b bVar, Resources resources, int i) {
        androidx.core.content.c.c.C0018c c0018cA = a(bVar, i);
        if (c0018cA == null) {
            return null;
        }
        Typeface typefaceA = androidx.core.a.c.a(context, resources, c0018cA.b(), c0018cA.a(), i);
        a(typefaceA, bVar);
        return typefaceA;
    }

    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        File fileA = i.a(context);
        if (fileA == null) {
            return null;
        }
        try {
            if (i.a(fileA, resources, i)) {
                return Typeface.createFromFile(fileA.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileA.delete();
        }
    }

    androidx.core.content.c.c.b a(Typeface typeface) {
        long jB = b(typeface);
        if (jB == 0) {
            return null;
        }
        return this.f898a.get(Long.valueOf(jB));
    }

    private void a(Typeface typeface, androidx.core.content.c.c.b bVar) {
        long jB = b(typeface);
        if (jB != 0) {
            this.f898a.put(Long.valueOf(jB), bVar);
        }
    }
}
