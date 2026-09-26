package b.b.a;

import java.math.BigInteger;

/* JADX INFO: compiled from: JsonPrimitive.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Class<?>[] f1560b = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object f1561a;

    public q(Boolean bool) {
        a(bool);
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : f1560b) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    void a(Object obj) {
        if (obj instanceof Character) {
            this.f1561a = String.valueOf(((Character) obj).charValue());
        } else {
            b.b.a.y.a.a((obj instanceof Number) || b(obj));
            this.f1561a = obj;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || q.class != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        if (this.f1561a == null) {
            return qVar.f1561a == null;
        }
        if (a(this) && a(qVar)) {
            return m().longValue() == qVar.m().longValue();
        }
        if (!(this.f1561a instanceof Number) || !(qVar.f1561a instanceof Number)) {
            return this.f1561a.equals(qVar.f1561a);
        }
        double dDoubleValue = m().doubleValue();
        double dDoubleValue2 = qVar.m().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public boolean h() {
        return o() ? i().booleanValue() : Boolean.parseBoolean(n());
    }

    public int hashCode() {
        long jDoubleToLongBits;
        if (this.f1561a == null) {
            return 31;
        }
        if (a(this)) {
            jDoubleToLongBits = m().longValue();
        } else {
            Object obj = this.f1561a;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(m().doubleValue());
        }
        return (int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits);
    }

    Boolean i() {
        return (Boolean) this.f1561a;
    }

    public double j() {
        return p() ? m().doubleValue() : Double.parseDouble(n());
    }

    public int k() {
        return p() ? m().intValue() : Integer.parseInt(n());
    }

    public long l() {
        return p() ? m().longValue() : Long.parseLong(n());
    }

    public Number m() {
        Object obj = this.f1561a;
        return obj instanceof String ? new b.b.a.y.g((String) obj) : (Number) obj;
    }

    public String n() {
        if (p()) {
            return m().toString();
        }
        return o() ? i().toString() : (String) this.f1561a;
    }

    public boolean o() {
        return this.f1561a instanceof Boolean;
    }

    public boolean p() {
        return this.f1561a instanceof Number;
    }

    public boolean q() {
        return this.f1561a instanceof String;
    }

    public q(Number number) {
        a(number);
    }

    public q(String str) {
        a(str);
    }

    private static boolean a(q qVar) {
        Object obj = qVar.f1561a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }
}
