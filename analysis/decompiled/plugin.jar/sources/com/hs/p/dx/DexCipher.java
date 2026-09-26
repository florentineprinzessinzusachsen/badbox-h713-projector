package com.hs.p.dx;

import com.hs.p.common.utils.LOG;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DexCipher {
    private static final String TAG = "DexCipher";

    private static class CipherBean {
        private final byte[] mDexCipher;
        private final byte[] mInvocationCipher;
        private final String mSalt;
        private final int mVersion;

        public CipherBean(int i, String str, byte[] bArr, byte[] bArr2) {
            this.mVersion = i;
            this.mSalt = str;
            this.mInvocationCipher = bArr;
            this.mDexCipher = bArr2;
        }
    }

    public static Invocation decrypt(String str, String str2, File file, File file2) throws Exception {
        return decrypt(str, str2, FileUtils.read(file), file2);
    }

    public static Invocation decryptInMemory(String str, String str2, byte[] bArr) throws Exception {
        Invocation invocation = new Invocation();
        CipherBean cipherBean = readCipherBean(str, bArr);
        parseInvocation(new String(AES.decrypt(cipherBean.mInvocationCipher, 0, cipherBean.mInvocationCipher.length, str2, cipherBean.mSalt), StandardCharsets.UTF_8), invocation);
        byte[] bArrDecrypt = AES.decrypt(cipherBean.mDexCipher, 0, cipherBean.mDexCipher.length, str2, cipherBean.mSalt);
        File file = new File(System.getProperty("java.io.tmpdir"), "dex" + System.currentTimeMillis());
        if (!file.exists()) {
            file.mkdirs();
        }
        new DexClassLoader("", file.getAbsolutePath(), null, DexCipher.class.getClassLoader());
        try {
            try {
                File fileCreateTempFile = File.createTempFile("temp", DIR.SUFFIX_DXF);
                try {
                    FileUtils.write(fileCreateTempFile, bArrDecrypt);
                    invocation.setClassLoader(new DexClassLoader(fileCreateTempFile.getAbsolutePath(), file.getAbsolutePath(), null, DexCipher.class.getClassLoader()));
                    invocation.setDexData(bArrDecrypt);
                    fileCreateTempFile.delete();
                    FileUtils.deleteDir(file);
                    return invocation;
                } catch (Throwable th) {
                    fileCreateTempFile.delete();
                    throw th;
                }
            } catch (Throwable th2) {
                FileUtils.deleteDir(file);
                throw th2;
            }
        } catch (Exception e) {
            throw new Exception("Failed to load DEX data in memory: " + e.getMessage(), e);
        }
    }

    private static void parseInvocation(String str, Invocation invocation) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("cn")) {
            invocation.mClassName = jSONObject.getString("cn");
        }
        if (jSONObject.has("m_init")) {
            invocation.mInitMethod = jSONObject.getString("m_init");
        }
        if (jSONObject.has("m_uninit")) {
            invocation.mUninitMethod = jSONObject.getString("m_uninit");
        }
    }

    public static int parseVersion(String str, File file) {
        try {
            return readCipherBean(str, FileUtils.read(file)).mVersion;
        } catch (Exception e) {
            LOG.w(TAG, "parse " + file + " version failed: " + e);
            return -1;
        }
    }

    static CipherBean readCipherBean(String str, byte[] bArr) throws Exception {
        int i = readInt(bArr, 0);
        if (i <= 0) {
            throw new Exception("illegal version(" + i + ")");
        }
        String string = readString(bArr, 4100, 32);
        byte[] intBytes = readIntBytes(bArr, 4132);
        int length = 4132 + intBytes.length + 4;
        byte[] intBytes2 = readIntBytes(bArr, length);
        int length2 = length + intBytes2.length + 4;
        verifySignature(str, bArr, length2, readIntBytes(bArr, length2));
        return new CipherBean(i, string, intBytes, intBytes2);
    }

    private static int readInt(byte[] bArr, int i) throws Exception {
        if (bArr.length < i + 4) {
            throw new Exception("illegal data size");
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, i, bArr2, 0, 4);
        return NumUtils.bytes2int(bArr2);
    }

    private static byte[] readIntBytes(byte[] bArr, int i) throws Exception {
        int i2 = i + 4;
        if (bArr.length < i2) {
            throw new Exception("illegal data size");
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, i, bArr2, 0, 4);
        int iBytes2int = NumUtils.bytes2int(bArr2);
        if (bArr.length < i2 + iBytes2int) {
            throw new Exception("illegal data size");
        }
        if (iBytes2int > 10485760) {
            throw new Exception("data too large");
        }
        byte[] bArr3 = new byte[iBytes2int];
        System.arraycopy(bArr, i2, bArr3, 0, iBytes2int);
        return bArr3;
    }

    private static String readString(byte[] bArr, int i, int i2) throws Exception {
        if (bArr.length < i + i2) {
            throw new Exception("illegal data size");
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new String(bArr2, StandardCharsets.UTF_8);
    }

    private static void verifySignature(String str, byte[] bArr, int i, byte[] bArr2) throws Exception {
        if (!DSAUtils.verify(bArr, 0, i, bArr2, str)) {
            throw new Exception("signature verify failed");
        }
    }

    public static Invocation decrypt(String str, String str2, byte[] bArr, File file) throws Exception {
        Invocation invocation = new Invocation();
        CipherBean cipherBean = readCipherBean(str, bArr);
        parseInvocation(new String(AES.decrypt(cipherBean.mInvocationCipher, 0, cipherBean.mInvocationCipher.length, str2, cipherBean.mSalt), StandardCharsets.UTF_8), invocation);
        FileUtils.write(file, AES.decrypt(cipherBean.mDexCipher, 0, cipherBean.mDexCipher.length, str2, cipherBean.mSalt));
        invocation.mLocalDexFile = file;
        return invocation;
    }
}
