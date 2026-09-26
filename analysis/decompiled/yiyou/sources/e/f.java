package e;

import com.android.umanalytics.utils.ShellUtils;
import com.umeng.commonsdk.proguard.ap;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: compiled from: ByteString.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements Serializable, Comparable<f> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final char[] f4731d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final f f4732e = a(new byte[0]);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f4733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    transient int f4734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    transient String f4735c;

    f(byte[] bArr) {
        this.f4733a = bArr;
    }

    public static f a(byte... bArr) {
        if (bArr != null) {
            return new f((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static f d(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        f fVar = new f(str.getBytes(u.f4772a));
        fVar.f4735c = str;
        return fVar;
    }

    public String b() {
        byte[] bArr = this.f4733a;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f4731d;
            cArr[i] = cArr2[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & ap.m];
        }
        return new String(cArr);
    }

    public f c() {
        return c("MD5");
    }

    public f e() {
        return c("SHA-256");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            int iF = fVar.f();
            byte[] bArr = this.f4733a;
            if (iF == bArr.length && fVar.a(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int f() {
        return this.f4733a.length;
    }

    public f g() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f4733a;
            if (i >= bArr.length) {
                return this;
            }
            byte b2 = bArr[i];
            if (b2 >= 65 && b2 <= 90) {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b2 + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b3 = bArr2[i2];
                    if (b3 >= 65 && b3 <= 90) {
                        bArr2[i2] = (byte) (b3 + 32);
                    }
                }
                return new f(bArr2);
            }
            i++;
        }
    }

    public byte[] h() {
        return (byte[]) this.f4733a.clone();
    }

    public int hashCode() {
        int i = this.f4734b;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.f4733a);
        this.f4734b = iHashCode;
        return iHashCode;
    }

    public String i() {
        String str = this.f4735c;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f4733a, u.f4772a);
        this.f4735c = str2;
        return str2;
    }

    public String toString() {
        if (this.f4733a.length == 0) {
            return "[size=0]";
        }
        String strI = i();
        int iA = a(strI, 64);
        if (iA == -1) {
            if (this.f4733a.length <= 64) {
                return "[hex=" + b() + "]";
            }
            return "[size=" + this.f4733a.length + " hex=" + a(0, 64).b() + "…]";
        }
        String strReplace = strI.substring(0, iA).replace("\\", "\\\\").replace(ShellUtils.COMMAND_LINE_END, "\\n").replace("\r", "\\r");
        if (iA >= strI.length()) {
            return "[text=" + strReplace + "]";
        }
        return "[size=" + this.f4733a.length + " text=" + strReplace + "…]";
    }

    private f c(String str) {
        try {
            return a(MessageDigest.getInstance(str).digest(this.f4733a));
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        }
    }

    public String a() {
        return b.a(this.f4733a);
    }

    public static f a(String str) {
        if (str != null) {
            byte[] bArrA = b.a(str);
            if (bArrA != null) {
                return new f(bArrA);
            }
            return null;
        }
        throw new IllegalArgumentException("base64 == null");
    }

    public f d() {
        return c("SHA-1");
    }

    public static f b(String str) {
        if (str != null) {
            if (str.length() % 2 == 0) {
                byte[] bArr = new byte[str.length() / 2];
                for (int i = 0; i < bArr.length; i++) {
                    int i2 = i * 2;
                    bArr[i] = (byte) ((a(str.charAt(i2)) << 4) + a(str.charAt(i2 + 1)));
                }
                return a(bArr);
            }
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        throw new IllegalArgumentException("hex == null");
    }

    private static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c2);
            }
        }
        return (c2 - c3) + 10;
    }

    public f a(int i, int i2) {
        if (i >= 0) {
            byte[] bArr = this.f4733a;
            if (i2 > bArr.length) {
                throw new IllegalArgumentException("endIndex > length(" + this.f4733a.length + ")");
            }
            int i3 = i2 - i;
            if (i3 >= 0) {
                if (i == 0 && i2 == bArr.length) {
                    return this;
                }
                byte[] bArr2 = new byte[i3];
                System.arraycopy(this.f4733a, i, bArr2, 0, i3);
                return new f(bArr2);
            }
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        throw new IllegalArgumentException("beginIndex < 0");
    }

    public final boolean b(f fVar) {
        return a(0, fVar, 0, fVar.f());
    }

    public byte a(int i) {
        return this.f4733a[i];
    }

    void a(c cVar) {
        byte[] bArr = this.f4733a;
        cVar.write(bArr, 0, bArr.length);
    }

    public boolean a(int i, f fVar, int i2, int i3) {
        return fVar.a(i2, this.f4733a, i, i3);
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.f4733a;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && u.a(bArr2, i, bArr, i2, i3)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(f fVar) {
        int iF = f();
        int iF2 = fVar.f();
        int iMin = Math.min(iF, iF2);
        for (int i = 0; i < iMin; i++) {
            int iA = a(i) & 255;
            int iA2 = fVar.a(i) & 255;
            if (iA != iA2) {
                return iA < iA2 ? -1 : 1;
            }
        }
        if (iF == iF2) {
            return 0;
        }
        return iF < iF2 ? -1 : 1;
    }

    static int a(String str, int i) {
        int length = str.length();
        int iCharCount = 0;
        int i2 = 0;
        while (iCharCount < length) {
            if (i2 == i) {
                return iCharCount;
            }
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                return -1;
            }
            i2++;
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.length();
    }
}
