package okhttp3.internal.publicsuffix;

import d.h0.c;
import d.h0.k.f;
import e.e;
import e.j;
import e.l;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class PublicSuffixDatabase {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final byte[] f4773e = {42};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String[] f4774f = new String[0];
    private static final String[] g = {"*"};
    private static final PublicSuffixDatabase h = new PublicSuffixDatabase();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f4775a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CountDownLatch f4776b = new CountDownLatch(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private byte[] f4777c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private byte[] f4778d;

    public static PublicSuffixDatabase a() {
        return h;
    }

    private void b() {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
        if (resourceAsStream == null) {
            return;
        }
        e eVarA = l.a(new j(l.a(resourceAsStream)));
        try {
            byte[] bArr = new byte[eVarA.readInt()];
            eVarA.readFully(bArr);
            byte[] bArr2 = new byte[eVarA.readInt()];
            eVarA.readFully(bArr2);
            c.a(eVarA);
            synchronized (this) {
                this.f4777c = bArr;
                this.f4778d = bArr2;
            }
            this.f4776b.countDown();
        } catch (Throwable th) {
            c.a(eVarA);
            throw th;
        }
    }

    private void c() {
        boolean z = false;
        while (true) {
            try {
                try {
                    b();
                    break;
                } catch (InterruptedIOException unused) {
                    z = true;
                } catch (IOException e2) {
                    f.d().a(5, "Failed to read public suffix list", e2);
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public String a(String str) {
        int length;
        int length2;
        if (str == null) {
            throw new NullPointerException("domain == null");
        }
        String[] strArrSplit = IDN.toUnicode(str).split("\\.");
        String[] strArrA = a(strArrSplit);
        if (strArrSplit.length == strArrA.length && strArrA[0].charAt(0) != '!') {
            return null;
        }
        if (strArrA[0].charAt(0) == '!') {
            length = strArrSplit.length;
            length2 = strArrA.length;
        } else {
            length = strArrSplit.length;
            length2 = strArrA.length + 1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit2 = str.split("\\.");
        for (int i = length - length2; i < strArrSplit2.length; i++) {
            sb.append(strArrSplit2[i]);
            sb.append('.');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String[] a(String[] strArr) {
        String strA;
        String strA2;
        String strA3;
        String[] strArrSplit;
        String[] strArrSplit2;
        int i = 0;
        if (!this.f4775a.get() && this.f4775a.compareAndSet(false, true)) {
            c();
        } else {
            try {
                this.f4776b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        synchronized (this) {
            if (this.f4777c == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        byte[][] bArr = new byte[strArr.length][];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            bArr[i2] = strArr[i2].getBytes(c.i);
        }
        int i3 = 0;
        while (true) {
            if (i3 >= bArr.length) {
                strA = null;
                break;
            }
            strA = a(this.f4777c, bArr, i3);
            if (strA != null) {
                break;
            }
            i3++;
        }
        if (bArr.length <= 1) {
            strA2 = null;
            break;
        }
        byte[][] bArr2 = (byte[][]) bArr.clone();
        int i4 = 0;
        while (true) {
            if (i4 >= bArr2.length - 1) {
                strA2 = null;
                break;
            }
            bArr2[i4] = f4773e;
            strA2 = a(this.f4777c, bArr2, i4);
            if (strA2 != null) {
                break;
            }
            i4++;
        }
        if (strA2 == null) {
            strA3 = null;
            break;
        }
        while (true) {
            if (i >= bArr.length - 1) {
                strA3 = null;
                break;
            }
            strA3 = a(this.f4778d, bArr, i);
            if (strA3 != null) {
                break;
            }
            i++;
        }
        if (strA3 != null) {
            return ("!" + strA3).split("\\.");
        }
        if (strA == null && strA2 == null) {
            return g;
        }
        if (strA != null) {
            strArrSplit = strA.split("\\.");
        } else {
            strArrSplit = f4774f;
        }
        if (strA2 != null) {
            strArrSplit2 = strA2.split("\\.");
        } else {
            strArrSplit2 = f4774f;
        }
        return strArrSplit.length > strArrSplit2.length ? strArrSplit : strArrSplit2;
    }

    private static String a(byte[] bArr, byte[][] bArr2, int i) {
        int i2;
        int i3;
        int i4;
        int length = bArr.length;
        int i5 = 0;
        while (i5 < length) {
            int i6 = (i5 + length) / 2;
            while (i6 > -1 && bArr[i6] != 10) {
                i6--;
            }
            int i7 = i6 + 1;
            int i8 = 1;
            while (true) {
                i2 = i7 + i8;
                if (bArr[i2] == 10) {
                    break;
                }
                i8++;
            }
            int i9 = i2 - i7;
            int i10 = i;
            boolean z = false;
            int i11 = 0;
            int i12 = 0;
            while (true) {
                if (z) {
                    z = false;
                    i3 = 46;
                } else {
                    i3 = bArr2[i10][i11] & 255;
                }
                i4 = i3 - (bArr[i7 + i12] & 255);
                if (i4 == 0) {
                    i12++;
                    i11++;
                    if (i12 == i9) {
                        break;
                    }
                    if (bArr2[i10].length == i11) {
                        if (i10 == bArr2.length - 1) {
                            break;
                        }
                        i10++;
                        z = true;
                        i11 = -1;
                    }
                } else {
                    break;
                }
            }
            if (i4 >= 0) {
                if (i4 <= 0) {
                    int i13 = i9 - i12;
                    int length2 = bArr2[i10].length - i11;
                    while (true) {
                        i10++;
                        if (i10 >= bArr2.length) {
                            break;
                        }
                        length2 += bArr2[i10].length;
                    }
                    if (length2 >= i13) {
                        if (length2 <= i13) {
                            return new String(bArr, i7, i9, c.i);
                        }
                    }
                }
                i5 = i2 + 1;
            }
            length = i7 - 1;
        }
        return null;
    }
}
