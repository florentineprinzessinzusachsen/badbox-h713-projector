package c0;

import android.os.Parcel;
import android.os.Parcelable;
import e.e;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f358a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f359b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f360c;

    public b(e eVar, e eVar2, e eVar3) {
        this.f358a = eVar;
        this.f359b = eVar2;
        this.f360c = eVar3;
    }

    public abstract c a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        e eVar = this.f360c;
        Class cls2 = (Class) eVar.d(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        eVar.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        e eVar = this.f358a;
        Method method = (Method) eVar.d(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, b.class.getClassLoader()).getDeclaredMethod("read", b.class);
        eVar.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        e eVar = this.f359b;
        Method method = (Method) eVar.d(name);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, b.class);
        eVar.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i4);

    public final Parcelable f(Parcelable parcelable, int i4) {
        if (!e(i4)) {
            return parcelable;
        }
        return ((c) this).f362e.readParcelable(c.class.getClassLoader());
    }

    public final d g() {
        String string = ((c) this).f362e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (d) c(string).invoke(null, a());
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e5);
        } catch (NoSuchMethodException e6) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e6);
        } catch (InvocationTargetException e7) {
            if (e7.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e7.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e7);
        }
    }

    public abstract void h(int i4);

    public final void i(d dVar) {
        if (dVar == null) {
            ((c) this).f362e.writeString(null);
            return;
        }
        try {
            ((c) this).f362e.writeString(b(dVar.getClass()).getName());
            c cVarA = a();
            try {
                d(dVar.getClass()).invoke(null, dVar, cVarA);
                Parcel parcel = cVarA.f362e;
                int i4 = cVarA.f366i;
                if (i4 >= 0) {
                    int i5 = cVarA.f361d.get(i4);
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i5);
                    parcel.writeInt(iDataPosition - i5);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e4) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e4);
            } catch (IllegalAccessException e5) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e5);
            } catch (NoSuchMethodException e6) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e6);
            } catch (InvocationTargetException e7) {
                if (!(e7.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e7);
                }
                throw ((RuntimeException) e7.getCause());
            }
        } catch (ClassNotFoundException e8) {
            throw new RuntimeException(dVar.getClass().getSimpleName().concat(" does not have a Parcelizer"), e8);
        }
    }
}
