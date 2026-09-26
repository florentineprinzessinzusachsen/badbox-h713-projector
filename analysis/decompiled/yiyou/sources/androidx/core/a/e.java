package androidx.core.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: compiled from: TypefaceCompatApi24Impl.java */
/* JADX INFO: loaded from: classes.dex */
class e extends h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Class f894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Constructor f895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Method f896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Method f897e;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            method2 = null;
        }
        f895c = constructor;
        f894b = cls;
        f896d = method2;
        f897e = method;
    }

    e() {
    }

    public static boolean a() {
        if (f896d == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return f896d != null;
    }

    private static Object b() {
        try {
            return f895c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) f896d.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private static Typeface a(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) f894b, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f897e.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.a.h
    public Typeface a(Context context, CancellationSignal cancellationSignal, androidx.core.c.b.f[] fVarArr, int i) {
        Object objB = b();
        if (objB == null) {
            return null;
        }
        a.b.g gVar = new a.b.g();
        for (androidx.core.c.b.f fVar : fVarArr) {
            Uri uriC = fVar.c();
            ByteBuffer byteBufferA = (ByteBuffer) gVar.get(uriC);
            if (byteBufferA == null) {
                byteBufferA = i.a(context, cancellationSignal, uriC);
                gVar.put(uriC, byteBufferA);
            }
            if (byteBufferA == null || !a(objB, byteBufferA, fVar.b(), fVar.d(), fVar.e())) {
                return null;
            }
        }
        Typeface typefaceA = a(objB);
        if (typefaceA == null) {
            return null;
        }
        return Typeface.create(typefaceA, i);
    }

    @Override // androidx.core.a.h
    public Typeface a(Context context, androidx.core.content.c.c.b bVar, Resources resources, int i) {
        Object objB = b();
        if (objB == null) {
            return null;
        }
        for (androidx.core.content.c.c.C0018c c0018c : bVar.a()) {
            ByteBuffer byteBufferA = i.a(context, resources, c0018c.b());
            if (byteBufferA == null || !a(objB, byteBufferA, c0018c.c(), c0018c.e(), c0018c.f())) {
                return null;
            }
        }
        return a(objB);
    }
}
