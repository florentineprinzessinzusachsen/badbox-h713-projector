package s0;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u extends q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Serializable f2136d;

    public u(Boolean bool) {
        Objects.requireNonNull(bool);
        this.f2136d = bool;
    }

    public static boolean e(u uVar) {
        Serializable serializable = uVar.f2136d;
        if (!(serializable instanceof Number)) {
            return false;
        }
        Number number = (Number) serializable;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    @Override // s0.q
    public final String b() {
        Serializable serializable = this.f2136d;
        if (serializable instanceof String) {
            return (String) serializable;
        }
        if (serializable instanceof Number) {
            return d().toString();
        }
        if (serializable instanceof Boolean) {
            return ((Boolean) serializable).toString();
        }
        throw new AssertionError("Unexpected value type: " + serializable.getClass());
    }

    public final BigInteger c() {
        Serializable serializable = this.f2136d;
        if (serializable instanceof BigInteger) {
            return (BigInteger) serializable;
        }
        if (e(this)) {
            return BigInteger.valueOf(d().longValue());
        }
        String strB = b();
        u0.i.c(strB);
        return new BigInteger(strB);
    }

    public final Number d() {
        Serializable serializable = this.f2136d;
        if (serializable instanceof Number) {
            return (Number) serializable;
        }
        if (serializable instanceof String) {
            return new u0.k((String) serializable);
        }
        throw new UnsupportedOperationException("Primitive is neither a number nor a string");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || u.class != obj.getClass()) {
            return false;
        }
        u uVar = (u) obj;
        Serializable serializable = uVar.f2136d;
        Serializable serializable2 = this.f2136d;
        if (serializable2 == null) {
            return serializable == null;
        }
        if (e(this) && e(uVar)) {
            if ((serializable2 instanceof BigInteger) || (serializable instanceof BigInteger)) {
                return c().equals(uVar.c());
            }
            return d().longValue() == uVar.d().longValue();
        }
        if (!(serializable2 instanceof Number) || !(serializable instanceof Number)) {
            return serializable2.equals(serializable);
        }
        if ((serializable2 instanceof BigDecimal) && (serializable instanceof BigDecimal)) {
            return (serializable2 instanceof BigDecimal ? (BigDecimal) serializable2 : u0.i.j(b())).compareTo(serializable instanceof BigDecimal ? (BigDecimal) serializable : u0.i.j(uVar.b())) == 0;
        }
        double dDoubleValue = serializable2 instanceof Number ? d().doubleValue() : Double.parseDouble(b());
        double dDoubleValue2 = serializable instanceof Number ? uVar.d().doubleValue() : Double.parseDouble(uVar.b());
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        Serializable serializable = this.f2136d;
        if (serializable == null) {
            return 31;
        }
        if (e(this)) {
            jDoubleToLongBits = d().longValue();
        } else {
            if (!(serializable instanceof Number)) {
                return serializable.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(d().doubleValue());
        }
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    public u(Number number) {
        Objects.requireNonNull(number);
        this.f2136d = number;
    }

    public u(String str) {
        Objects.requireNonNull(str);
        this.f2136d = str;
    }
}
