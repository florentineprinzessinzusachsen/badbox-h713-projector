package v0;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends s0.b0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final i f2459d = new i();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f2460a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f2461b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f2462c = new HashMap();

    public j(Class cls) {
        try {
            Field[] declaredFields = cls.getDeclaredFields();
            int i4 = 0;
            for (Field field : declaredFields) {
                if (field.isEnumConstant()) {
                    declaredFields[i4] = field;
                    i4++;
                }
            }
            Field[] fieldArr = (Field[]) Arrays.copyOf(declaredFields, i4);
            AccessibleObject.setAccessible(fieldArr, true);
            for (Field field2 : fieldArr) {
                Enum r4 = (Enum) field2.get(null);
                String strName = r4.name();
                String string = r4.toString();
                t0.b bVar = (t0.b) field2.getAnnotation(t0.b.class);
                if (bVar != null) {
                    strName = bVar.value();
                    for (String str : bVar.alternate()) {
                        this.f2460a.put(str, r4);
                    }
                }
                this.f2460a.put(strName, r4);
                this.f2461b.put(string, r4);
                this.f2462c.put(r4, strName);
            }
        } catch (IllegalAccessException e4) {
            throw new AssertionError(e4);
        }
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        Enum r4 = (Enum) this.f2460a.get(strD0);
        return r4 == null ? (Enum) this.f2461b.get(strD0) : r4;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        Enum r4 = (Enum) obj;
        dVar.a0(r4 == null ? null : (String) this.f2462c.get(r4));
    }
}
