package u0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends Number {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f2256d;

    public k(String str) {
        this.f2256d = str;
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return Double.parseDouble(this.f2256d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof k) {
            return this.f2256d.equals(((k) obj).f2256d);
        }
        return false;
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return Float.parseFloat(this.f2256d);
    }

    public final int hashCode() {
        return this.f2256d.hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        String str = this.f2256d;
        try {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(str);
            }
        } catch (NumberFormatException unused2) {
            return i.j(str).intValue();
        }
    }

    @Override // java.lang.Number
    public final long longValue() {
        String str = this.f2256d;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return i.j(str).longValue();
        }
    }

    public final String toString() {
        return this.f2256d;
    }
}
