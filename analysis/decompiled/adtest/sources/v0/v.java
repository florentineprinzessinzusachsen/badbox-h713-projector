package v0;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class v extends s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final HashMap f2497e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Constructor f2498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f2499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f2500d;

    static {
        HashMap map = new HashMap();
        map.put(Byte.TYPE, (byte) 0);
        map.put(Short.TYPE, (short) 0);
        map.put(Integer.TYPE, 0);
        map.put(Long.TYPE, 0L);
        map.put(Float.TYPE, Float.valueOf(0.0f));
        map.put(Double.TYPE, Double.valueOf(0.0d));
        map.put(Character.TYPE, (char) 0);
        map.put(Boolean.TYPE, Boolean.FALSE);
        f2497e = map;
    }

    public v(Class cls, u uVar) {
        super(uVar);
        this.f2500d = new HashMap();
        d0.l0 l0Var = x0.c.f2671a;
        Constructor constructorV = l0Var.v(cls);
        this.f2498b = constructorV;
        x0.c.f(constructorV);
        String[] strArrX = l0Var.x(cls);
        for (int i4 = 0; i4 < strArrX.length; i4++) {
            this.f2500d.put(strArrX[i4], Integer.valueOf(i4));
        }
        Class<?>[] parameterTypes = this.f2498b.getParameterTypes();
        this.f2499c = new Object[parameterTypes.length];
        for (int i5 = 0; i5 < parameterTypes.length; i5++) {
            this.f2499c[i5] = f2497e.get(parameterTypes[i5]);
        }
    }

    @Override // v0.s
    public final Object d() {
        return (Object[]) this.f2499c.clone();
    }

    @Override // v0.s
    public final Object e(Object obj) {
        Object[] objArr = (Object[]) obj;
        Constructor constructor = this.f2498b;
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException e4) {
            d0.l0 l0Var = x0.c.f2671a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.13.2). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e4);
        } catch (IllegalArgumentException e5) {
            e = e5;
            throw new RuntimeException("Failed to invoke constructor '" + x0.c.b(constructor) + "' with args " + Arrays.toString(objArr), e);
        } catch (InstantiationException e6) {
            e = e6;
            throw new RuntimeException("Failed to invoke constructor '" + x0.c.b(constructor) + "' with args " + Arrays.toString(objArr), e);
        } catch (InvocationTargetException e7) {
            throw new RuntimeException("Failed to invoke constructor '" + x0.c.b(constructor) + "' with args " + Arrays.toString(objArr), e7.getCause());
        }
    }

    @Override // v0.s
    public final void f(Object obj, a1.b bVar, r rVar) {
        Object[] objArr = (Object[]) obj;
        String str = rVar.f2480c;
        Integer num = (Integer) this.f2500d.get(str);
        if (num == null) {
            throw new IllegalStateException("Could not find the index in the constructor '" + x0.c.b(this.f2498b) + "' for field with name '" + str + "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
        }
        int iIntValue = num.intValue();
        Object objB = rVar.f2483f.b(bVar);
        if (objB != null || !rVar.f2484g) {
            objArr[iIntValue] = objB;
            return;
        }
        throw new a0.c("null is not allowed as value for record component '" + str + "' of primitive type; at path " + bVar.K(false));
    }
}
