package androidx.core.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: TypefaceCompatApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
class d extends h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Class f889b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Constructor f890c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f891d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Method f892e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f893f = false;

    d() {
    }

    private static void a() throws NoSuchMethodException {
        Method method;
        Class<?> cls;
        Method method2;
        if (f893f) {
            return;
        }
        f893f = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
            method = null;
            cls = null;
            method2 = null;
        }
        f890c = constructor;
        f889b = cls;
        f891d = method2;
        f892e = method;
    }

    private static Object b() throws NoSuchMethodException {
        a();
        try {
            return f890c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private File a(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String str = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (OsConstants.S_ISREG(Os.stat(str).st_mode)) {
                return new File(str);
            }
        } catch (ErrnoException unused) {
        }
        return null;
    }

    private static Typeface a(Object obj) throws NoSuchMethodException {
        a();
        try {
            Object objNewInstance = Array.newInstance((Class<?>) f889b, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f892e.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static boolean a(Object obj, String str, int i, boolean z) throws NoSuchMethodException {
        a();
        try {
            return ((Boolean) f891d.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // androidx.core.a.h
    public Typeface a(Context context, CancellationSignal cancellationSignal, androidx.core.c.b.f[] fVarArr, int i) {
        if (fVarArr.length < 1) {
            return null;
        }
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
                File fileA = a(parcelFileDescriptorOpenFileDescriptor);
                if (fileA != null && fileA.canRead()) {
                    Typeface typefaceCreateFromFile = Typeface.createFromFile(fileA);
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return typefaceCreateFromFile;
                }
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                try {
                    Typeface typefaceA = super.a(context, fileInputStream);
                    fileInputStream.close();
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return typefaceA;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                try {
                    throw th4;
                } catch (Throwable th5) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        try {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } catch (Throwable th6) {
                            th4.addSuppressed(th6);
                        }
                    }
                    throw th5;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // androidx.core.a.h
    public Typeface a(Context context, androidx.core.content.c.c.b bVar, Resources resources, int i) throws NoSuchMethodException {
        Object objB = b();
        for (androidx.core.content.c.c.C0018c c0018c : bVar.a()) {
            File fileA = i.a(context);
            if (fileA == null) {
                return null;
            }
            try {
                if (!i.a(fileA, resources, c0018c.b())) {
                    return null;
                }
                if (!a(objB, fileA.getPath(), c0018c.e(), c0018c.f())) {
                    return null;
                }
                fileA.delete();
            } catch (RuntimeException unused) {
                return null;
            } finally {
                fileA.delete();
            }
        }
        return a(objB);
    }
}
