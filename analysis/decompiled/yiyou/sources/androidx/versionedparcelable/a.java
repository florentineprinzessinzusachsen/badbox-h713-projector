package androidx.versionedparcelable;

import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: VersionedParcel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final a.b.a<String, Method> f1474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final a.b.a<String, Method> f1475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final a.b.a<String, Class> f1476c;

    public a(a.b.a<String, Method> aVar, a.b.a<String, Method> aVar2, a.b.a<String, Class> aVar3) {
        this.f1474a = aVar;
        this.f1475b = aVar2;
        this.f1476c = aVar3;
    }

    protected abstract void a();

    protected abstract void a(Parcelable parcelable);

    protected abstract void a(CharSequence charSequence);

    protected abstract void a(String str);

    protected abstract void a(boolean z);

    public void a(boolean z, boolean z2) {
    }

    protected abstract void a(byte[] bArr);

    protected abstract boolean a(int i);

    public boolean a(boolean z, int i) {
        return !a(i) ? z : d();
    }

    protected abstract a b();

    protected abstract void b(int i);

    public void b(boolean z, int i) {
        b(i);
        a(z);
    }

    protected abstract void c(int i);

    public boolean c() {
        return false;
    }

    protected abstract boolean d();

    protected abstract byte[] e();

    protected abstract CharSequence f();

    protected abstract int g();

    protected abstract <T extends Parcelable> T h();

    protected abstract String i();

    protected <T extends c> T j() {
        String strI = i();
        if (strI == null) {
            return null;
        }
        return (T) a(strI, b());
    }

    public int a(int i, int i2) {
        return !a(i2) ? i : g();
    }

    public void b(byte[] bArr, int i) {
        b(i);
        a(bArr);
    }

    public String a(String str, int i) {
        return !a(i) ? str : i();
    }

    public void b(CharSequence charSequence, int i) {
        b(i);
        a(charSequence);
    }

    public byte[] a(byte[] bArr, int i) {
        return !a(i) ? bArr : e();
    }

    public void b(int i, int i2) {
        b(i2);
        c(i);
    }

    public <T extends Parcelable> T a(T t, int i) {
        return !a(i) ? t : (T) h();
    }

    public void b(String str, int i) {
        b(i);
        a(str);
    }

    public CharSequence a(CharSequence charSequence, int i) {
        return !a(i) ? charSequence : f();
    }

    public void b(Parcelable parcelable, int i) {
        b(i);
        a(parcelable);
    }

    protected void a(c cVar) {
        if (cVar == null) {
            a((String) null);
            return;
        }
        b(cVar);
        a aVarB = b();
        a(cVar, aVarB);
        aVarB.a();
    }

    public void b(c cVar, int i) {
        b(i);
        a(cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(c cVar) {
        try {
            a(a((Class<? extends c>) cVar.getClass()).getName());
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(cVar.getClass().getSimpleName() + " does not have a Parcelizer", e2);
        }
    }

    private Method b(String str) throws NoSuchMethodException {
        Method method = this.f1474a.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, a.class.getClassLoader()).getDeclaredMethod("read", a.class);
        this.f1474a.put(str, declaredMethod);
        return declaredMethod;
    }

    public <T extends c> T a(T t, int i) {
        return !a(i) ? t : (T) j();
    }

    protected <T extends c> T a(String str, a aVar) {
        try {
            return (T) b(str).invoke(null, aVar);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Method b(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        Method method = this.f1475b.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class clsA = a((Class<? extends c>) cls);
        System.currentTimeMillis();
        Method declaredMethod = clsA.getDeclaredMethod("write", cls, a.class);
        this.f1475b.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    protected <T extends c> void a(T t, a aVar) {
        try {
            b(t.getClass()).invoke(null, t, aVar);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e4);
        } catch (InvocationTargetException e5) {
            if (e5.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e5.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e5);
        }
    }

    private Class a(Class<? extends c> cls) throws ClassNotFoundException {
        Class cls2 = this.f1476c.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.f1476c.put(cls.getName(), cls3);
        return cls3;
    }
}
