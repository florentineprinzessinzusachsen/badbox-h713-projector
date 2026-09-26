package b.b.a.y;

import java.math.BigDecimal;

/* JADX INFO: compiled from: LazilyParsedNumber.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends Number {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1597a;

    public g(String str) {
        this.f1597a = str;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.f1597a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        String str = this.f1597a;
        String str2 = ((g) obj).f1597a;
        return str == str2 || str.equals(str2);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.f1597a);
    }

    public int hashCode() {
        return this.f1597a.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        try {
            try {
                return Integer.parseInt(this.f1597a);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(this.f1597a);
            }
        } catch (NumberFormatException unused2) {
            return new BigDecimal(this.f1597a).intValue();
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        try {
            return Long.parseLong(this.f1597a);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.f1597a).longValue();
        }
    }

    public String toString() {
        return this.f1597a;
    }
}
