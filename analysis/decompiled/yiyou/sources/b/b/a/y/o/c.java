package b.b.a.y.o;

import b.b.a.m;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: UnsafeReflectionAccessor.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Class f1703d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f1704b = c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Field f1705c = b();

    c() {
    }

    private static Object c() {
        try {
            f1703d = Class.forName("sun.misc.Unsafe");
            Field declaredField = f1703d.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // b.b.a.y.o.b
    public void a(AccessibleObject accessibleObject) {
        if (b(accessibleObject)) {
            return;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e2) {
            throw new m("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e2);
        }
    }

    boolean b(AccessibleObject accessibleObject) {
        if (this.f1704b != null && this.f1705c != null) {
            try {
                f1703d.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.f1704b, accessibleObject, Long.valueOf(((Long) f1703d.getMethod("objectFieldOffset", Field.class).invoke(this.f1704b, this.f1705c)).longValue()), true);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private static Field b() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
