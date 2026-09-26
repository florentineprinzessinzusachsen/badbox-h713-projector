package u0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f2255a;

    static {
        int i4;
        String property = System.getProperty("java.version");
        try {
            String[] strArrSplit = property.split("[._]", 3);
            i4 = Integer.parseInt(strArrSplit[0]);
            if (i4 == 1 && strArrSplit.length > 1) {
                i4 = Integer.parseInt(strArrSplit[1]);
            }
        } catch (NumberFormatException unused) {
            i4 = -1;
        }
        if (i4 == -1) {
            try {
                StringBuilder sb = new StringBuilder();
                for (int i5 = 0; i5 < property.length(); i5++) {
                    char cCharAt = property.charAt(i5);
                    if (!Character.isDigit(cCharAt)) {
                        break;
                    }
                    sb.append(cCharAt);
                }
                i4 = Integer.parseInt(sb.toString());
            } catch (NumberFormatException unused2) {
                i4 = -1;
            }
        }
        if (i4 == -1) {
            i4 = 6;
        }
        f2255a = i4;
    }
}
