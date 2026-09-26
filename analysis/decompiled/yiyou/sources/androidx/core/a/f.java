package androidx.core.a;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: compiled from: TypefaceCompatApi26Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends d {
    protected final Class g;
    protected final Constructor h;
    protected final Method i;
    protected final Method j;
    protected final Method k;
    protected final Method l;
    protected final Method m;

    public f() throws NoSuchMethodException {
        Method methodD;
        Constructor constructorE;
        Method methodB;
        Method methodC;
        Method methodF;
        Method methodA;
        Class cls = null;
        try {
            Class clsA = a();
            constructorE = e(clsA);
            methodB = b(clsA);
            methodC = c(clsA);
            methodF = f(clsA);
            methodA = a(clsA);
            methodD = d(clsA);
            cls = clsA;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e2.getClass().getName(), e2);
            methodD = null;
            constructorE = null;
            methodB = null;
            methodC = null;
            methodF = null;
            methodA = null;
        }
        this.g = cls;
        this.h = constructorE;
        this.i = methodB;
        this.j = methodC;
        this.k = methodF;
        this.l = methodA;
        this.m = methodD;
    }

    private boolean a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.i.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    private boolean b() {
        if (this.i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.i != null;
    }

    private Object c() {
        try {
            return this.h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    protected Method d(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    protected Constructor e(Class cls) {
        return cls.getConstructor(new Class[0]);
    }

    protected Method f(Class cls) {
        return cls.getMethod("freeze", new Class[0]);
    }

    private boolean c(Object obj) {
        try {
            return ((Boolean) this.k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    protected Method c(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    private void b(Object obj) {
        try {
            this.l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    private boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) this.j.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    protected Method b(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    protected Typeface a(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.g, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.m.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.a.d, androidx.core.a.h
    public Typeface a(Context context, androidx.core.content.c.c.b bVar, Resources resources, int i) {
        if (!b()) {
            return super.a(context, bVar, resources, i);
        }
        Object objC = c();
        if (objC == null) {
            return null;
        }
        for (androidx.core.content.c.c.C0018c c0018c : bVar.a()) {
            if (!a(context, objC, c0018c.a(), c0018c.c(), c0018c.e(), c0018c.f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(c0018c.d()))) {
                b(objC);
                return null;
            }
        }
        if (c(objC)) {
            return a(objC);
        }
        return null;
    }

    @Override // androidx.core.a.d, androidx.core.a.h
    public Typeface a(Context context, CancellationSignal cancellationSignal, androidx.core.c.b.f[] fVarArr, int i) {
        Typeface typefaceA;
        if (fVarArr.length < 1) {
            return null;
        }
        if (!b()) {
            androidx.core.c.b.f fVarA = a(fVarArr, i);
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(fVarA.c(), "r", cancellationSignal);
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(fVarA.d()).setItalic(fVarA.e()).build();
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return typefaceBuild;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (IOException unused) {
                return null;
            }
        }
        Map<Uri, ByteBuffer> mapA = androidx.core.c.b.a(context, fVarArr, cancellationSignal);
        Object objC = c();
        if (objC == null) {
            return null;
        }
        boolean z = false;
        for (androidx.core.c.b.f fVar : fVarArr) {
            ByteBuffer byteBuffer = mapA.get(fVar.c());
            if (byteBuffer != null) {
                if (!a(objC, byteBuffer, fVar.b(), fVar.d(), fVar.e() ? 1 : 0)) {
                    b(objC);
                    return null;
                }
                z = true;
            }
        }
        if (!z) {
            b(objC);
            return null;
        }
        if (c(objC) && (typefaceA = a(objC)) != null) {
            return Typeface.create(typefaceA, i);
        }
        return null;
    }

    @Override // androidx.core.a.h
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        if (!b()) {
            return super.a(context, resources, i, str, i2);
        }
        Object objC = c();
        if (objC == null) {
            return null;
        }
        if (!a(context, objC, str, 0, -1, -1, null)) {
            b(objC);
            return null;
        }
        if (c(objC)) {
            return a(objC);
        }
        return null;
    }

    protected Class a() {
        return Class.forName("android.graphics.FontFamily");
    }

    protected Method a(Class cls) {
        return cls.getMethod("abortCreation", new Class[0]);
    }
}
