package o;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a1.a f1518a = new a1.a(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f1519b = {112, 114, 111, 0};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f1520c = {112, 114, 109, 0};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f1521d = {48, 49, 53, 0};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f1522e = {48, 49, 48, 0};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f1523f = {48, 48, 57, 0};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f1524g = {48, 48, 53, 0};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f1525h = {48, 48, 49, 0};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final byte[] f1526i = {48, 48, 49, 0};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f1527j = {48, 48, 50, 0};

    public static byte[] a(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                try {
                    deflaterOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            deflater.end();
            throw th3;
        }
    }

    public static byte[] b(d[] dVarArr, byte[] bArr) throws IOException {
        int i4 = 0;
        int length = 0;
        for (d dVar : dVarArr) {
            length += ((((dVar.f1515g * 2) + 7) & (-8)) / 8) + (dVar.f1513e * 2) + d(dVar.f1509a, dVar.f1510b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + dVar.f1514f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, f1523f)) {
            int length2 = dVarArr.length;
            while (i4 < length2) {
                d dVar2 = dVarArr[i4];
                q(byteArrayOutputStream, dVar2, d(dVar2.f1509a, dVar2.f1510b, bArr));
                p(byteArrayOutputStream, dVar2);
                i4++;
            }
        } else {
            for (d dVar3 : dVarArr) {
                q(byteArrayOutputStream, dVar3, d(dVar3.f1509a, dVar3.f1510b, bArr));
            }
            int length3 = dVarArr.length;
            while (i4 < length3) {
                p(byteArrayOutputStream, dVarArr[i4]);
                i4++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static boolean c(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z3 = true;
        for (File file2 : fileArrListFiles) {
            z3 = c(file2) && z3;
        }
        return z3;
    }

    public static String d(String str, String str2, byte[] bArr) {
        byte[] bArr2 = f1525h;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = f1524g;
        Object obj = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            if ("!".equals(obj)) {
                return str2.replace(":", "!");
            }
            if (":".equals(obj)) {
                return str2.replace("!", ":");
            }
        } else {
            if (str2.equals("classes.dex")) {
                return str;
            }
            if (str2.contains("!") || str2.contains(":")) {
                if ("!".equals(obj)) {
                    return str2.replace(":", "!");
                }
                if (":".equals(obj)) {
                    return str2.replace("!", ":");
                }
            } else if (!str2.endsWith(".apk")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append((Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!");
                sb.append(str2);
                return sb.toString();
            }
        }
        return str2;
    }

    public static void e(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
        }
    }

    public static byte[] f(InputStream inputStream, int i4) throws IOException {
        byte[] bArr = new byte[i4];
        int i5 = 0;
        while (i5 < i4) {
            int i6 = inputStream.read(bArr, i5, i4 - i5);
            if (i6 < 0) {
                throw new IllegalStateException(a1.c.c(i4, "Not enough bytes to read: "));
            }
            i5 += i6;
        }
        return bArr;
    }

    public static int[] g(ByteArrayInputStream byteArrayInputStream, int i4) {
        int[] iArr = new int[i4];
        int iM = 0;
        for (int i5 = 0; i5 < i4; i5++) {
            iM += (int) m(byteArrayInputStream, 2);
            iArr[i5] = iM;
        }
        return iArr;
    }

    public static byte[] h(FileInputStream fileInputStream, int i4, int i5) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i5];
            byte[] bArr2 = new byte[2048];
            int i6 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i6 < i4) {
                int i7 = fileInputStream.read(bArr2);
                if (i7 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i4 + " bytes");
                }
                inflater.setInput(bArr2, 0, i7);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i5 - iInflate);
                    i6 += i7;
                } catch (DataFormatException e4) {
                    throw new IllegalStateException(e4.getMessage());
                }
            }
            if (i6 == i4) {
                if (!inflater.finished()) {
                    throw new IllegalStateException("Inflater did not finish");
                }
                inflater.end();
                return bArr;
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i4 + " actual=" + i6);
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    public static d[] i(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, d[] dVarArr) throws IOException {
        byte[] bArr3 = f1526i;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, f1527j)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iM = (int) m(fileInputStream, 2);
            byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
            try {
                d[] dVarArrK = k(byteArrayInputStream, bArr2, iM, dVarArr);
                byteArrayInputStream.close();
                return dVarArrK;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(f1521d, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iM2 = (int) m(fileInputStream, 1);
        byte[] bArrH2 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrH2);
        try {
            d[] dVarArrJ = j(byteArrayInputStream2, iM2, dVarArr);
            byteArrayInputStream2.close();
            return dVarArrJ;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static d[] j(ByteArrayInputStream byteArrayInputStream, int i4, d[] dVarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new d[0];
        }
        if (i4 != dVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i4];
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            int iM = (int) m(byteArrayInputStream, 2);
            iArr[i5] = (int) m(byteArrayInputStream, 2);
            strArr[i5] = new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8);
        }
        for (int i6 = 0; i6 < i4; i6++) {
            d dVar = dVarArr[i6];
            if (!dVar.f1510b.equals(strArr[i6])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i7 = iArr[i6];
            dVar.f1513e = i7;
            dVar.f1516h = g(byteArrayInputStream, i7);
        }
        return dVarArr;
    }

    public static d[] k(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i4, d[] dVarArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new d[0];
        }
        if (i4 != dVarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i5 = 0; i5 < i4; i5++) {
            m(byteArrayInputStream, 2);
            String str = new String(f(byteArrayInputStream, (int) m(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jM = m(byteArrayInputStream, 4);
            int iM = (int) m(byteArrayInputStream, 2);
            d dVar = null;
            if (dVarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                for (int i6 = 0; i6 < dVarArr.length; i6++) {
                    if (dVarArr[i6].f1510b.equals(strSubstring)) {
                        dVar = dVarArr[i6];
                        break;
                    }
                }
            }
            if (dVar == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            dVar.f1512d = jM;
            int[] iArrG = g(byteArrayInputStream, iM);
            if (Arrays.equals(bArr, f1525h)) {
                dVar.f1513e = iM;
                dVar.f1516h = iArrG;
            }
        }
        return dVarArr;
    }

    public static d[] l(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, f1522e)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iM = (int) m(fileInputStream, 1);
        byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
        try {
            d[] dVarArrN = n(byteArrayInputStream, str, iM);
            byteArrayInputStream.close();
            return dVarArrN;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long m(InputStream inputStream, int i4) throws IOException {
        byte[] bArrF = f(inputStream, i4);
        long j4 = 0;
        for (int i5 = 0; i5 < i4; i5++) {
            j4 += ((long) (bArrF[i5] & 255)) << (i5 * 8);
        }
        return j4;
    }

    public static d[] n(ByteArrayInputStream byteArrayInputStream, String str, int i4) throws IOException {
        int i5 = 0;
        if (byteArrayInputStream.available() == 0) {
            return new d[0];
        }
        d[] dVarArr = new d[i4];
        for (int i6 = 0; i6 < i4; i6++) {
            int iM = (int) m(byteArrayInputStream, 2);
            int iM2 = (int) m(byteArrayInputStream, 2);
            dVarArr[i6] = new d(str, new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8), m(byteArrayInputStream, 4), iM2, (int) m(byteArrayInputStream, 4), (int) m(byteArrayInputStream, 4), new int[iM2], new TreeMap());
        }
        int i7 = 0;
        while (i7 < i4) {
            d dVar = dVarArr[i7];
            int iAvailable = byteArrayInputStream.available();
            int i8 = dVar.f1514f;
            int i9 = dVar.f1515g;
            TreeMap treeMap = dVar.f1517i;
            int i10 = iAvailable - i8;
            int iM3 = i5;
            while (byteArrayInputStream.available() > i10) {
                iM3 += (int) m(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iM3), 1);
                int iM4 = (int) m(byteArrayInputStream, 2);
                while (iM4 > 0) {
                    m(byteArrayInputStream, 2);
                    int iM5 = (int) m(byteArrayInputStream, 1);
                    if (iM5 != 6 && iM5 != 7) {
                        while (iM5 > 0) {
                            m(byteArrayInputStream, 1);
                            int i11 = i5;
                            int i12 = i7;
                            for (int iM6 = (int) m(byteArrayInputStream, 1); iM6 > 0; iM6--) {
                                m(byteArrayInputStream, 2);
                            }
                            iM5--;
                            i5 = i11;
                            i7 = i12;
                        }
                    }
                    iM4--;
                    i5 = i5;
                    i7 = i7;
                }
            }
            int i13 = i5;
            int i14 = i7;
            if (byteArrayInputStream.available() != i10) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            dVar.f1516h = g(byteArrayInputStream, dVar.f1513e);
            BitSet bitSetValueOf = BitSet.valueOf(f(byteArrayInputStream, (((i9 * 2) + 7) & (-8)) / 8));
            for (int i15 = i13; i15 < i9; i15++) {
                int i16 = bitSetValueOf.get(i15) ? 2 : i13;
                if (bitSetValueOf.get(i15 + i9)) {
                    i16 |= 4;
                }
                if (i16 != 0) {
                    Integer numValueOf = (Integer) treeMap.get(Integer.valueOf(i15));
                    if (numValueOf == null) {
                        numValueOf = Integer.valueOf(i13);
                    }
                    treeMap.put(Integer.valueOf(i15), Integer.valueOf(i16 | numValueOf.intValue()));
                }
            }
            i7 = i14 + 1;
            i5 = i13;
        }
        return dVarArr;
    }

    public static boolean o(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, d[] dVarArr) throws IOException {
        long j4;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = f1521d;
        int i4 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = f1522e;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrB = b(dVarArr, bArr3);
                u(byteArrayOutputStream, dVarArr.length, 1);
                u(byteArrayOutputStream, bArrB.length, 4);
                byte[] bArrA = a(bArrB);
                u(byteArrayOutputStream, bArrA.length, 4);
                byteArrayOutputStream.write(bArrA);
                return true;
            }
            byte[] bArr4 = f1524g;
            if (Arrays.equals(bArr, bArr4)) {
                u(byteArrayOutputStream, dVarArr.length, 1);
                for (d dVar : dVarArr) {
                    int size = dVar.f1517i.size() * 4;
                    String strD = d(dVar.f1509a, dVar.f1510b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    v(byteArrayOutputStream, strD.getBytes(charset).length);
                    v(byteArrayOutputStream, dVar.f1516h.length);
                    u(byteArrayOutputStream, size, 4);
                    u(byteArrayOutputStream, dVar.f1511c, 4);
                    byteArrayOutputStream.write(strD.getBytes(charset));
                    Iterator it = dVar.f1517i.keySet().iterator();
                    while (it.hasNext()) {
                        v(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        v(byteArrayOutputStream, 0);
                    }
                    for (int i5 : dVar.f1516h) {
                        v(byteArrayOutputStream, i5);
                    }
                }
                return true;
            }
            byte[] bArr5 = f1523f;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrB2 = b(dVarArr, bArr5);
                u(byteArrayOutputStream, dVarArr.length, 1);
                u(byteArrayOutputStream, bArrB2.length, 4);
                byte[] bArrA2 = a(bArrB2);
                u(byteArrayOutputStream, bArrA2.length, 4);
                byteArrayOutputStream.write(bArrA2);
                return true;
            }
            byte[] bArr6 = f1525h;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            v(byteArrayOutputStream, dVarArr.length);
            for (d dVar2 : dVarArr) {
                String str = dVar2.f1509a;
                TreeMap treeMap = dVar2.f1517i;
                String strD2 = d(str, dVar2.f1510b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                v(byteArrayOutputStream, strD2.getBytes(charset2).length);
                v(byteArrayOutputStream, treeMap.size());
                v(byteArrayOutputStream, dVar2.f1516h.length);
                u(byteArrayOutputStream, dVar2.f1511c, 4);
                byteArrayOutputStream.write(strD2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    v(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i6 : dVar2.f1516h) {
                    v(byteArrayOutputStream, i6);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            v(byteArrayOutputStream2, dVarArr.length);
            int i7 = 2;
            int i8 = 2;
            for (d dVar3 : dVarArr) {
                u(byteArrayOutputStream2, dVar3.f1511c, 4);
                u(byteArrayOutputStream2, dVar3.f1512d, 4);
                u(byteArrayOutputStream2, dVar3.f1515g, 4);
                String strD3 = d(dVar3.f1509a, dVar3.f1510b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strD3.getBytes(charset3).length;
                v(byteArrayOutputStream2, length2);
                i8 = i8 + 14 + length2;
                byteArrayOutputStream2.write(strD3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i8 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i8 + ", does not match actual size " + byteArray.length);
            }
            n nVar = new n(1, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(nVar);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i9 = 0;
            int i10 = 0;
            while (i9 < dVarArr.length) {
                try {
                    d dVar4 = dVarArr[i9];
                    v(byteArrayOutputStream3, i9);
                    v(byteArrayOutputStream3, dVar4.f1513e);
                    i10 = i10 + 4 + (dVar4.f1513e * i7);
                    int[] iArr = dVar4.f1516h;
                    int length3 = iArr.length;
                    int i11 = i4;
                    int i12 = i7;
                    int i13 = i11;
                    while (i13 < length3) {
                        int i14 = iArr[i13];
                        v(byteArrayOutputStream3, i14 - i11);
                        i13++;
                        i11 = i14;
                    }
                    i9++;
                    i7 = i12;
                    i4 = 0;
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i10 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i10 + ", does not match actual size " + byteArray2.length);
            }
            n nVar2 = new n(3, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(nVar2);
            ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
            int i15 = 0;
            int i16 = 0;
            while (i15 < dVarArr.length) {
                try {
                    d dVar5 = dVarArr[i15];
                    Iterator it3 = dVar5.f1517i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                    try {
                        r(byteArrayOutputStream5, iIntValue, dVar5);
                        byte[] byteArray3 = byteArrayOutputStream5.toByteArray();
                        byteArrayOutputStream5.close();
                        ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
                        try {
                            s(byteArrayOutputStream6, dVar5);
                            byte[] byteArray4 = byteArrayOutputStream6.toByteArray();
                            byteArrayOutputStream6.close();
                            v(byteArrayOutputStream4, i15);
                            int length4 = byteArray3.length + 2 + byteArray4.length;
                            int i17 = i16 + 6;
                            ArrayList arrayList4 = arrayList3;
                            u(byteArrayOutputStream4, length4, 4);
                            v(byteArrayOutputStream4, iIntValue);
                            byteArrayOutputStream4.write(byteArray3);
                            byteArrayOutputStream4.write(byteArray4);
                            i16 = i17 + length4;
                            i15++;
                            arrayList3 = arrayList4;
                        } catch (Throwable th3) {
                            try {
                                byteArrayOutputStream6.close();
                                throw th3;
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                                throw th3;
                            }
                        }
                    } catch (Throwable th5) {
                        try {
                            byteArrayOutputStream5.close();
                            throw th5;
                        } catch (Throwable th6) {
                            th5.addSuppressed(th6);
                            throw th5;
                        }
                    }
                } catch (Throwable th7) {
                    try {
                        byteArrayOutputStream4.close();
                        throw th7;
                    } catch (Throwable th8) {
                        th7.addSuppressed(th8);
                        throw th7;
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream4.toByteArray();
            if (i16 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i16 + ", does not match actual size " + byteArray5.length);
            }
            n nVar3 = new n(4, byteArray5, true);
            byteArrayOutputStream4.close();
            arrayList2.add(nVar3);
            long j5 = 4;
            long size2 = j5 + j5 + 4 + ((long) (arrayList2.size() * 16));
            u(byteArrayOutputStream, arrayList2.size(), 4);
            int i18 = 0;
            while (i18 < arrayList2.size()) {
                n nVar4 = (n) arrayList2.get(i18);
                int i19 = nVar4.f1538a;
                byte[] bArr7 = nVar4.f1539b;
                if (i19 == 1) {
                    j4 = 0;
                } else if (i19 == 2) {
                    j4 = 1;
                } else if (i19 == 3) {
                    j4 = 2;
                } else if (i19 == 4) {
                    j4 = 3;
                } else {
                    if (i19 != 5) {
                        throw null;
                    }
                    j4 = 4;
                }
                u(byteArrayOutputStream, j4, 4);
                u(byteArrayOutputStream, size2, 4);
                if (nVar4.f1540c) {
                    long length5 = bArr7.length;
                    byte[] bArrA3 = a(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrA3);
                    u(byteArrayOutputStream, bArrA3.length, 4);
                    u(byteArrayOutputStream, length5, 4);
                    length = bArrA3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    u(byteArrayOutputStream, bArr7.length, 4);
                    u(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i18++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i20 = 0; i20 < arrayList6.size(); i20++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i20));
            }
            return true;
        } catch (Throwable th9) {
            try {
                byteArrayOutputStream2.close();
                throw th9;
            } catch (Throwable th10) {
                th9.addSuppressed(th10);
                throw th9;
            }
        }
    }

    public static void p(ByteArrayOutputStream byteArrayOutputStream, d dVar) throws IOException {
        s(byteArrayOutputStream, dVar);
        int i4 = dVar.f1515g;
        int[] iArr = dVar.f1516h;
        int length = iArr.length;
        int i5 = 0;
        int i6 = 0;
        while (i5 < length) {
            int i7 = iArr[i5];
            v(byteArrayOutputStream, i7 - i6);
            i5++;
            i6 = i7;
        }
        byte[] bArr = new byte[(((i4 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : dVar.f1517i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i8 = iIntValue / 8;
                bArr[i8] = (byte) (bArr[i8] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i9 = iIntValue + i4;
                int i10 = i9 / 8;
                bArr[i10] = (byte) ((1 << (i9 % 8)) | bArr[i10]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void q(ByteArrayOutputStream byteArrayOutputStream, d dVar, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        v(byteArrayOutputStream, str.getBytes(charset).length);
        v(byteArrayOutputStream, dVar.f1513e);
        u(byteArrayOutputStream, dVar.f1514f, 4);
        u(byteArrayOutputStream, dVar.f1511c, 4);
        u(byteArrayOutputStream, dVar.f1515g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void r(ByteArrayOutputStream byteArrayOutputStream, int i4, d dVar) throws IOException {
        int i5 = dVar.f1515g;
        byte[] bArr = new byte[(((Integer.bitCount(i4 & (-2)) * i5) + 7) & (-8)) / 8];
        for (Map.Entry entry : dVar.f1517i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i6 = 0;
            for (int i7 = 1; i7 <= 4; i7 <<= 1) {
                if (i7 != 1 && (i7 & i4) != 0) {
                    if ((i7 & iIntValue2) == i7) {
                        int i8 = (i6 * i5) + iIntValue;
                        int i9 = i8 / 8;
                        bArr[i9] = (byte) ((1 << (i8 % 8)) | bArr[i9]);
                    }
                    i6++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void s(ByteArrayOutputStream byteArrayOutputStream, d dVar) throws IOException {
        int i4 = 0;
        for (Map.Entry entry : dVar.f1517i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                v(byteArrayOutputStream, iIntValue - i4);
                v(byteArrayOutputStream, 0);
                i4 = iIntValue;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0191 A[Catch: all -> 0x018e, TRY_ENTER, TryCatch #28 {all -> 0x018e, blocks: (B:96:0x016c, B:98:0x0178, B:109:0x0191, B:110:0x0196), top: B:285:0x016c, outer: #34 }] */
    /* JADX WARN: Code duplicated, block: B:116:0x01a0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:117:0x01a2 A[Catch: IllegalStateException -> 0x0187, IOException -> 0x0189, FileNotFoundException -> 0x018c, TRY_LEAVE, TryCatch #34 {FileNotFoundException -> 0x018c, IOException -> 0x0189, IllegalStateException -> 0x0187, blocks: (B:94:0x0164, B:99:0x0182, B:117:0x01a2, B:115:0x019f, B:114:0x019c, B:96:0x016c, B:98:0x0178, B:109:0x0191, B:110:0x0196, B:111:0x0197), top: B:301:0x0164, inners: #28, #36 }] */
    /* JADX WARN: Code duplicated, block: B:124:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:134:0x01db A[Catch: all -> 0x01ea, TRY_LEAVE, TryCatch #10 {all -> 0x01ea, blocks: (B:132:0x01cf, B:134:0x01db, B:143:0x01ed), top: B:266:0x01cf, outer: #35 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x01ed A[Catch: all -> 0x01ea, TRY_ENTER, TRY_LEAVE, TryCatch #10 {all -> 0x01ea, blocks: (B:132:0x01cf, B:134:0x01db, B:143:0x01ed), top: B:266:0x01cf, outer: #35 }] */
    /* JADX WARN: Code duplicated, block: B:154:0x020a  */
    /* JADX WARN: Code duplicated, block: B:158:0x0214  */
    /* JADX WARN: Code duplicated, block: B:159:0x0218  */
    /* JADX WARN: Code duplicated, block: B:168:0x0238 A[Catch: all -> 0x0277, TryCatch #18 {all -> 0x0277, blocks: (B:166:0x0232, B:168:0x0238, B:169:0x023c, B:171:0x0242), top: B:275:0x0232 }] */
    /* JADX WARN: Code duplicated, block: B:171:0x0242 A[Catch: all -> 0x0277, TRY_LEAVE, TryCatch #18 {all -> 0x0277, blocks: (B:166:0x0232, B:168:0x0238, B:169:0x023c, B:171:0x0242), top: B:275:0x0232 }] */
    /* JADX WARN: Code duplicated, block: B:237:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:241:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:248:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:275:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:277:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:285:0x016c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:296:0x021c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:300:0x01ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:302:0x0247 A[EDGE_INSN: B:302:0x0247->B:173:0x0247 BREAK  A[LOOP:0: B:169:0x023c->B:303:?], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x00db  */
    /* JADX WARN: Code duplicated, block: B:55:0x010f A[Catch: all -> 0x0122, IllegalStateException -> 0x0125, IOException -> 0x0127, TRY_LEAVE, TryCatch #20 {IllegalStateException -> 0x0125, blocks: (B:53:0x0105, B:55:0x010f, B:66:0x0129, B:67:0x012e), top: B:277:0x0105, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x0129 A[Catch: all -> 0x0122, IllegalStateException -> 0x0125, IOException -> 0x0127, TRY_ENTER, TryCatch #20 {IllegalStateException -> 0x0125, blocks: (B:53:0x0105, B:55:0x010f, B:66:0x0129, B:67:0x012e), top: B:277:0x0105, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x0178 A[Catch: all -> 0x018e, TRY_LEAVE, TryCatch #28 {all -> 0x018e, blocks: (B:96:0x016c, B:98:0x0178, B:109:0x0191, B:110:0x0196), top: B:285:0x016c, outer: #34 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v27, types: [int] */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v38 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v44 */
    /* JADX WARN: Type inference failed for: r7v45 */
    /* JADX WARN: Type inference failed for: r7v46 */
    /* JADX WARN: Type inference failed for: r7v47 */
    /* JADX WARN: Type inference failed for: r7v48 */
    /* JADX WARN: Type inference failed for: r7v49 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v50 */
    /* JADX WARN: Type inference failed for: r7v51 */
    /* JADX WARN: Type inference failed for: r7v52 */
    /* JADX WARN: Type inference failed for: r7v53 */
    /* JADX WARN: Type inference failed for: r7v54 */
    /* JADX WARN: Type inference failed for: r7v55 */
    /* JADX WARN: Type inference failed for: r7v56 */
    /* JADX WARN: Type inference failed for: r7v57 */
    /* JADX WARN: Type inference failed for: r7v58 */
    /* JADX WARN: Type inference failed for: r7v59 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v60 */
    /* JADX WARN: Type inference failed for: r7v61 */
    /* JADX WARN: Type inference failed for: r7v62 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v8 */
    public static void t(Context context, Executor executor, f fVar, boolean z3) {
        boolean z4;
        ?? A;
        byte[] bArr;
        d[] dVarArrL;
        d[] dVarArr;
        f fVar2;
        d[] dVarArr2;
        byte[] bArr2;
        ?? r7;
        byte[] bArr3;
        ?? r8;
        boolean z5;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        FileOutputStream fileOutputStream;
        Throwable th2;
        FileChannel channel;
        FileLock fileLockTryLock;
        byte[] bArr4;
        int i4;
        ?? r9;
        boolean z6;
        ?? byteArrayOutputStream;
        ?? r10;
        c cVar;
        ?? r11;
        FileInputStream fileInputStreamA;
        ?? r12;
        ?? r13;
        boolean z7;
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        AssetManager assets = applicationContext.getAssets();
        String name = new File(applicationInfo.sourceDir).getName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            File filesDir = context.getFilesDir();
            if (!z3) {
                File file = new File(filesDir, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
                if (file.exists()) {
                    try {
                        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                        try {
                            long j4 = dataInputStream.readLong();
                            dataInputStream.close();
                            z7 = j4 == packageInfo.lastUpdateTime;
                            if (z7) {
                                fVar.f(2, null);
                            }
                        } catch (Throwable th3) {
                            try {
                                dataInputStream.close();
                                throw th3;
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                                throw th3;
                            }
                        }
                    } catch (IOException unused) {
                        z7 = false;
                    }
                } else {
                    z7 = false;
                }
                if (z7) {
                    Log.d("ProfileInstaller", "Skipping profile installation for " + context.getPackageName());
                    m.c(context, false);
                    return;
                }
            }
            Log.d("ProfileInstaller", "Installing profile for " + context.getPackageName());
            File file2 = new File(new File("/data/misc/profiles/cur/0", packageName), "primary.prof");
            c cVar2 = new c(assets, executor, fVar, name, file2);
            byte[] bArr5 = cVar2.f1503c;
            if (bArr5 != null) {
                if (!file2.exists()) {
                    try {
                        if (file2.createNewFile()) {
                            cVar2.f1506f = true;
                            A = cVar2.a(assets, "dexopt/baseline.prof");
                            bArr = f1519b;
                            if (A != 0) {
                                if (Arrays.equals(bArr, f(A, 4))) {
                                    throw new IllegalStateException("Invalid magic");
                                }
                                dVarArrL = l(A, f(A, 4), cVar2.f1505e);
                                A.close();
                                cVar2.f1507g = dVarArrL;
                            }
                            dVarArr = cVar2.f1507g;
                            if (dVarArr != null) {
                                A = "dexopt/baseline.profm";
                                fileInputStreamA = cVar2.a(assets, "dexopt/baseline.profm");
                                r11 = A;
                                if (fileInputStreamA == null) {
                                    if (fileInputStreamA != null) {
                                        fileInputStreamA.close();
                                        r11 = A;
                                    }
                                    cVar = null;
                                    A = r11;
                                } else {
                                    if (Arrays.equals(f1520c, f(fileInputStreamA, 4))) {
                                        throw new IllegalStateException("Invalid magic");
                                    }
                                    byte[] bArrF = f(fileInputStreamA, 4);
                                    cVar2.f1507g = i(fileInputStreamA, bArrF, bArr5, dVarArr);
                                    fileInputStreamA.close();
                                    cVar = cVar2;
                                    A = bArrF;
                                }
                                if (cVar != null) {
                                    cVar2 = cVar;
                                }
                            }
                            fVar2 = cVar2.f1502b;
                            dVarArr2 = cVar2.f1507g;
                            bArr2 = cVar2.f1503c;
                            r7 = A;
                            r7 = A;
                            if (dVarArr2 != null) {
                                byteArrayOutputStream = cVar2.f1506f;
                                if (byteArrayOutputStream != 0) {
                                    throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                }
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                byteArrayOutputStream.write(bArr);
                                byteArrayOutputStream.write(bArr2);
                                if (o(byteArrayOutputStream, bArr2, dVarArr2)) {
                                    cVar2.f1508h = byteArrayOutputStream.toByteArray();
                                    byteArrayOutputStream.close();
                                    r10 = byteArrayOutputStream;
                                    cVar2.f1507g = null;
                                    r7 = r10;
                                } else {
                                    fVar2.f(5, null);
                                    cVar2.f1507g = null;
                                    byteArrayOutputStream.close();
                                    r7 = byteArrayOutputStream;
                                }
                            }
                            bArr3 = cVar2.f1508h;
                            if (bArr3 != null) {
                                if (cVar2.f1506f) {
                                    throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                }
                                byteArrayInputStream = new ByteArrayInputStream(bArr3);
                                fileOutputStream = new FileOutputStream(cVar2.f1504d);
                                channel = fileOutputStream.getChannel();
                                fileLockTryLock = channel.tryLock();
                                if (fileLockTryLock != null) {
                                    if (fileLockTryLock.isValid()) {
                                        bArr4 = new byte[512];
                                        while (true) {
                                            i4 = byteArrayInputStream.read(bArr4);
                                            if (i4 > 0) {
                                                break;
                                                break;
                                            }
                                            fileOutputStream.write(bArr4, 0, i4);
                                        }
                                        r9 = 1;
                                        cVar2.b(1, null);
                                        fileLockTryLock.close();
                                        channel.close();
                                        fileOutputStream.close();
                                        byteArrayInputStream.close();
                                        cVar2.f1508h = null;
                                        cVar2.f1507g = null;
                                        z5 = true;
                                    }
                                }
                                throw new IOException("Unable to acquire a lock on the underlying file channel.");
                            }
                            z5 = false;
                            r9 = 1;
                            if (z5) {
                                e(packageInfo, filesDir);
                            }
                            z6 = z5;
                            r12 = r9;
                        } else {
                            cVar2.b(4, null);
                        }
                    } catch (IOException unused2) {
                        z4 = true;
                        cVar2.b(4, null);
                    }
                } else if (file2.canWrite()) {
                    cVar2.f1506f = true;
                    try {
                        A = cVar2.a(assets, "dexopt/baseline.prof");
                    } catch (FileNotFoundException e4) {
                        fVar.f(6, e4);
                        A = 0;
                    } catch (IOException e5) {
                        fVar.f(7, e5);
                        A = 0;
                    }
                    bArr = f1519b;
                    try {
                        if (A != 0) {
                            try {
                                try {
                                    if (Arrays.equals(bArr, f(A, 4))) {
                                        throw new IllegalStateException("Invalid magic");
                                    }
                                    dVarArrL = l(A, f(A, 4), cVar2.f1505e);
                                    try {
                                        A.close();
                                    } catch (IOException e6) {
                                        fVar.f(7, e6);
                                    }
                                    cVar2.f1507g = dVarArrL;
                                } catch (IllegalStateException e7) {
                                    fVar.f(8, e7);
                                    try {
                                        A.close();
                                    } catch (IOException e8) {
                                        fVar.f(7, e8);
                                    }
                                    dVarArrL = null;
                                }
                            } catch (IOException e9) {
                                fVar.f(7, e9);
                                A.close();
                                dVarArrL = null;
                            }
                        }
                        dVarArr = cVar2.f1507g;
                        if (dVarArr != null && (A = Build.VERSION.SDK_INT) >= 24 && (A >= 31 || A == 24 || A == 25)) {
                            try {
                                A = "dexopt/baseline.profm";
                                fileInputStreamA = cVar2.a(assets, "dexopt/baseline.profm");
                                r11 = A;
                                if (fileInputStreamA == null) {
                                    try {
                                        if (Arrays.equals(f1520c, f(fileInputStreamA, 4))) {
                                            throw new IllegalStateException("Invalid magic");
                                        }
                                        byte[] bArrF2 = f(fileInputStreamA, 4);
                                        cVar2.f1507g = i(fileInputStreamA, bArrF2, bArr5, dVarArr);
                                        fileInputStreamA.close();
                                        cVar = cVar2;
                                        A = bArrF2;
                                    } catch (Throwable th5) {
                                        try {
                                            fileInputStreamA.close();
                                            throw th5;
                                        } catch (Throwable th6) {
                                            th5.addSuppressed(th6);
                                            throw th5;
                                        }
                                    }
                                } else {
                                    if (fileInputStreamA != null) {
                                        fileInputStreamA.close();
                                        r11 = A;
                                    }
                                    cVar = null;
                                    A = r11;
                                }
                            } catch (FileNotFoundException e10) {
                                fVar.f(9, e10);
                                r11 = A;
                                cVar = null;
                                A = r11;
                            } catch (IOException e11) {
                                fVar.f(7, e11);
                                r11 = A;
                                cVar = null;
                                A = r11;
                            } catch (IllegalStateException e12) {
                                cVar2.f1507g = null;
                                fVar.f(8, e12);
                                r11 = A;
                                cVar = null;
                                A = r11;
                            }
                            if (cVar != null) {
                                cVar2 = cVar;
                            }
                        }
                        fVar2 = cVar2.f1502b;
                        dVarArr2 = cVar2.f1507g;
                        bArr2 = cVar2.f1503c;
                        r7 = A;
                        r7 = A;
                        if (dVarArr2 != null && bArr2 != null) {
                            byteArrayOutputStream = cVar2.f1506f;
                            if (byteArrayOutputStream != 0) {
                                throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                            }
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    byteArrayOutputStream.write(bArr);
                                    byteArrayOutputStream.write(bArr2);
                                    if (o(byteArrayOutputStream, bArr2, dVarArr2)) {
                                        fVar2.f(5, null);
                                        cVar2.f1507g = null;
                                        byteArrayOutputStream.close();
                                        r7 = byteArrayOutputStream;
                                    } else {
                                        cVar2.f1508h = byteArrayOutputStream.toByteArray();
                                        byteArrayOutputStream.close();
                                        r10 = byteArrayOutputStream;
                                        cVar2.f1507g = null;
                                        r7 = r10;
                                    }
                                } catch (Throwable th7) {
                                    try {
                                        byteArrayOutputStream.close();
                                        throw th7;
                                    } catch (Throwable th8) {
                                        th7.addSuppressed(th8);
                                        throw th7;
                                    }
                                }
                            } catch (IOException e13) {
                                fVar2.f(7, e13);
                                r10 = byteArrayOutputStream;
                            } catch (IllegalStateException e14) {
                                fVar2.f(8, e14);
                                r10 = byteArrayOutputStream;
                            }
                        }
                        bArr3 = cVar2.f1508h;
                        if (bArr3 != null) {
                            z5 = false;
                            r9 = 1;
                        } else {
                            try {
                                if (cVar2.f1506f) {
                                    throw new IllegalStateException("This device doesn't support aot. Did you call deviceSupportsAotProfile()?");
                                }
                                try {
                                    try {
                                        byteArrayInputStream = new ByteArrayInputStream(bArr3);
                                        try {
                                            try {
                                                fileOutputStream = new FileOutputStream(cVar2.f1504d);
                                                try {
                                                    try {
                                                        channel = fileOutputStream.getChannel();
                                                        try {
                                                            fileLockTryLock = channel.tryLock();
                                                            try {
                                                                try {
                                                                    if (fileLockTryLock != null) {
                                                                        try {
                                                                            if (fileLockTryLock.isValid()) {
                                                                                bArr4 = new byte[512];
                                                                                while (true) {
                                                                                    i4 = byteArrayInputStream.read(bArr4);
                                                                                    if (i4 > 0) {
                                                                                        break;
                                                                                    } else {
                                                                                        fileOutputStream.write(bArr4, 0, i4);
                                                                                    }
                                                                                }
                                                                                r9 = 1;
                                                                                cVar2.b(1, null);
                                                                                fileLockTryLock.close();
                                                                                channel.close();
                                                                                fileOutputStream.close();
                                                                                byteArrayInputStream.close();
                                                                                cVar2.f1508h = null;
                                                                                cVar2.f1507g = null;
                                                                                z5 = true;
                                                                            }
                                                                        } catch (Throwable th9) {
                                                                            th = th9;
                                                                            Throwable th10 = th;
                                                                            if (fileLockTryLock == null) {
                                                                                throw th10;
                                                                            }
                                                                            try {
                                                                                fileLockTryLock.close();
                                                                                throw th10;
                                                                            } catch (Throwable th11) {
                                                                                th10.addSuppressed(th11);
                                                                                throw th10;
                                                                            }
                                                                        }
                                                                    }
                                                                    throw new IOException("Unable to acquire a lock on the underlying file channel.");
                                                                } catch (Throwable th12) {
                                                                    th = th12;
                                                                    Throwable th13 = th;
                                                                    if (channel == null) {
                                                                        throw th13;
                                                                    }
                                                                    try {
                                                                        channel.close();
                                                                        throw th13;
                                                                    } catch (Throwable th14) {
                                                                        th13.addSuppressed(th14);
                                                                        throw th13;
                                                                    }
                                                                }
                                                            } catch (Throwable th15) {
                                                                th = th15;
                                                            }
                                                        } catch (Throwable th16) {
                                                            th = th16;
                                                        }
                                                    } catch (Throwable th17) {
                                                        th = th17;
                                                        th2 = th;
                                                        try {
                                                            fileOutputStream.close();
                                                            throw th2;
                                                        } catch (Throwable th18) {
                                                            th2.addSuppressed(th18);
                                                            throw th2;
                                                        }
                                                    }
                                                } catch (Throwable th19) {
                                                    th = th19;
                                                    th2 = th;
                                                    fileOutputStream.close();
                                                    throw th2;
                                                }
                                            } catch (Throwable th20) {
                                                th = th20;
                                                th = th;
                                                try {
                                                    byteArrayInputStream.close();
                                                    throw th;
                                                } catch (Throwable th21) {
                                                    th.addSuppressed(th21);
                                                    throw th;
                                                }
                                            }
                                        } catch (Throwable th22) {
                                            th = th22;
                                            th = th;
                                            byteArrayInputStream.close();
                                            throw th;
                                        }
                                    } catch (FileNotFoundException e15) {
                                        e = e15;
                                        cVar2.b(6, e);
                                        r8 = r7;
                                        cVar2.f1508h = null;
                                        cVar2.f1507g = null;
                                        z5 = false;
                                        r9 = r8;
                                    } catch (IOException e16) {
                                        e = e16;
                                        cVar2.b(7, e);
                                        r8 = r7;
                                        cVar2.f1508h = null;
                                        cVar2.f1507g = null;
                                        z5 = false;
                                        r9 = r8;
                                    }
                                } catch (FileNotFoundException e17) {
                                    e = e17;
                                    r7 = 1;
                                    cVar2.b(6, e);
                                    r8 = r7;
                                    cVar2.f1508h = null;
                                    cVar2.f1507g = null;
                                    z5 = false;
                                    r9 = r8;
                                } catch (IOException e18) {
                                    e = e18;
                                    r7 = 1;
                                    cVar2.b(7, e);
                                    r8 = r7;
                                    cVar2.f1508h = null;
                                    cVar2.f1507g = null;
                                    z5 = false;
                                    r9 = r8;
                                }
                            } catch (Throwable th23) {
                                cVar2.f1508h = null;
                                cVar2.f1507g = null;
                                throw th23;
                            }
                        }
                        if (z5) {
                            e(packageInfo, filesDir);
                        }
                        z6 = z5;
                        r12 = r9;
                    } catch (Throwable th24) {
                        try {
                            A.close();
                            throw th24;
                        } catch (IOException e19) {
                            fVar.f(7, e19);
                            throw th24;
                        }
                    }
                } else {
                    cVar2.b(4, null);
                }
                if (z6 || !z3) {
                    r13 = 0;
                } else {
                    r13 = r12;
                }
                m.c(context, r13);
            }
            cVar2.b(3, Integer.valueOf(Build.VERSION.SDK_INT));
            z4 = true;
            z6 = false;
            r12 = z4;
            if (z6) {
                r13 = 0;
            } else {
                r13 = 0;
            }
            m.c(context, r13);
        } catch (PackageManager.NameNotFoundException e20) {
            fVar.f(7, e20);
            m.c(context, false);
        }
    }

    public static void u(ByteArrayOutputStream byteArrayOutputStream, long j4, int i4) throws IOException {
        byte[] bArr = new byte[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            bArr[i5] = (byte) ((j4 >> (i5 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void v(ByteArrayOutputStream byteArrayOutputStream, int i4) throws IOException {
        u(byteArrayOutputStream, i4, 2);
    }
}
