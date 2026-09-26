package b.a.a.a.b;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f1518d = new String(b.a.a.a.a.b.a(new byte[]{77, 122, 65, 121, 77, 84, 73, 120, 77, 68, 73, 61})) + new String(b.a.a.a.a.b.a(new byte[]{90, 71, 108, 106, 100, 87, 82, 112, 89, 87, 73, 61}));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static d f1519e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f1520f = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1522b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PublicKey f1523c;

    class a implements Comparator<C0035c> {
        a(c cVar) {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(C0035c c0035c, C0035c c0035c2) {
            int i = c0035c2.f1527b - c0035c.f1527b;
            if (i == 0) {
                if (c0035c.f1529d && c0035c2.f1529d) {
                    return 0;
                }
                if (c0035c.f1529d) {
                    return -1;
                }
                if (c0035c2.f1529d) {
                    return 1;
                }
            }
            return i;
        }
    }

    class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f1524a;

        b(d dVar) {
            this.f1524a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            d dVar = new d(null);
            d dVar2 = this.f1524a;
            dVar.f1531b = dVar2.f1531b;
            dVar.f1530a = dVar2.f1530a;
            File file = new File(c.this.f1521a.getFilesDir(), "libcuid.so");
            String strG = c.g(dVar.d());
            if (file.exists()) {
                d dVarA = d.a(c.f(c.b(file)));
                if (dVarA != null) {
                    if (c.this.a(dVarA)) {
                        c.this.m(c.g(dVarA.d()));
                    }
                } else if (dVarA == null) {
                    c.this.m(strG);
                }
            } else {
                c.this.m(strG);
            }
            boolean zD = c.this.d();
            if (zD) {
                String strK = c.this.k("com.baidu.deviceid.v2");
                if (TextUtils.isEmpty(strK)) {
                    c.this.c("com.baidu.deviceid.v2", strG);
                } else {
                    d dVarA2 = d.a(c.f(strK));
                    if (dVarA2 != null) {
                        if (c.this.a(dVarA2)) {
                            c.this.c("com.baidu.deviceid.v2", c.g(dVarA2.d()));
                        }
                    } else if (dVarA2 == null) {
                        c.this.c("com.baidu.deviceid.v2", strG);
                    }
                }
            }
            boolean zE = c.this.e("android.permission.WRITE_EXTERNAL_STORAGE");
            if (zE) {
                if (new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2").exists()) {
                    d dVarC = c.this.c();
                    if (dVarC != null) {
                        if (c.this.a(dVarC)) {
                            c.l(c.g(dVarC.d()));
                        }
                    } else if (dVarC == null) {
                        c.l(strG);
                    }
                } else {
                    c.l(strG);
                }
            }
            if (zD) {
                String strK2 = c.this.k("bd_setting_i");
                if (d.a(TextUtils.isEmpty(strK2) ? 0 : strK2.length())) {
                    c.this.c("bd_setting_i", "O");
                } else if (d.b(strK2)) {
                    c.this.c("bd_setting_i", "0");
                }
            }
            if (zE && new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid").exists()) {
                d dVarJ = c.this.j(c.this.i(""));
                if (dVarJ == null || !c.this.a(dVarJ)) {
                    return;
                }
                c.b(dVarJ.f1531b, dVarJ.f1530a);
            }
        }
    }

    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1530a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1531b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1532c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1533d;

        private d() {
            this.f1532c = 2;
            this.f1533d = 0;
        }

        public static boolean a(int i) {
            return i >= 14;
        }

        public boolean b() {
            return a(this.f1533d);
        }

        public boolean c() {
            return b(this.f1531b);
        }

        public String d() {
            try {
                return new JSONObject().put(c.h("ZGV2aWNlaWQ="), this.f1530a).put(c.h("aW1laQ=="), this.f1531b).put(c.h("dmVy"), this.f1532c).toString();
            } catch (JSONException e2) {
                c.b(e2);
                return null;
            }
        }

        public static d a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> itKeys = jSONObject.keys();
                String str2 = "0";
                String strOptString = "0";
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!c.h("ZGV2aWNlaWQ=").equals(next) && !c.h("dmVy").equals(next)) {
                        strOptString = jSONObject.optString(next, "0");
                    }
                }
                String string = jSONObject.getString(c.h("ZGV2aWNlaWQ="));
                int i = jSONObject.getInt(c.h("dmVy"));
                int length = TextUtils.isEmpty(strOptString) ? 0 : strOptString.length();
                if (!TextUtils.isEmpty(string)) {
                    d dVar = new d();
                    dVar.f1530a = string;
                    dVar.f1532c = i;
                    dVar.f1533d = length;
                    if (dVar.f1533d < 14) {
                        if (!TextUtils.isEmpty(strOptString)) {
                            str2 = strOptString;
                        }
                        dVar.f1531b = str2;
                    }
                    return dVar;
                }
            } catch (JSONException e2) {
                c.b(e2);
            }
            return null;
        }

        public static boolean b(String str) {
            return TextUtils.isEmpty(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static d b(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            d dVar = new d();
            dVar.f1530a = str;
            dVar.f1533d = TextUtils.isEmpty(str2) ? 0 : str2.length();
            if (dVar.f1533d < 14) {
                if (TextUtils.isEmpty(str2)) {
                    str2 = "0";
                }
                dVar.f1531b = str2;
            }
            return dVar;
        }

        /* synthetic */ d(a aVar) {
            this();
        }

        public String a() {
            String str = this.f1531b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.f1530a + "|" + str;
        }
    }

    static class e {
        static boolean a(String str, int i) {
            try {
                Os.chmod(str, i);
                return true;
            } catch (ErrnoException e2) {
                c.b(e2);
                return false;
            } catch (Exception e3) {
                c.b(e3);
                return false;
            }
        }
    }

    private c(Context context) throws Throwable {
        this.f1521a = context.getApplicationContext();
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new String(b.a.a.a.a.a.a(f1518d, f1518d, b.a.a.a.a.b.a(str.getBytes())));
        } catch (Exception e2) {
            b(e2);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return b.a.a.a.a.b.a(b.a.a.a.a.a.b(f1518d, f1518d, str.getBytes()), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            b(e2);
            return "";
        } catch (Exception e3) {
            b(e3);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h(String str) {
        return new String(b.a.a.a.a.b.a(str.getBytes()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String i(String str) {
        return "0";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d j(String str) {
        String str2;
        String str3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
                sb.append("\r\n");
            }
            bufferedReader.close();
            String[] strArrSplit = new String(b.a.a.a.a.a.a(f1518d, f1518d, b.a.a.a.a.b.a(sb.toString().getBytes()))).split("=");
            if (strArrSplit == null || strArrSplit.length != 2) {
                str2 = "";
            } else {
                str2 = strArrSplit[0];
                try {
                    str3 = strArrSplit[1];
                } catch (FileNotFoundException | IOException | Exception unused) {
                }
            }
        } catch (FileNotFoundException | IOException | Exception unused2) {
        }
        return d.b(str3, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String k(String str) {
        try {
            return Settings.System.getString(this.f1521a.getContentResolver(), str);
        } catch (Exception e2) {
            b(e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l(String str) {
        File file;
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid2");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public boolean m(String str) {
        int i = (!f1520f || Build.VERSION.SDK_INT >= 24) ? 0 : 1;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            try {
                fileOutputStreamOpenFileOutput = this.f1521a.openFileOutput("libcuid.so", i);
                fileOutputStreamOpenFileOutput.write(str.getBytes());
                fileOutputStreamOpenFileOutput.flush();
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e2) {
                        b(e2);
                    }
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    if (i == 0 && f1520f) {
                        return e.a(new File(this.f1521a.getFilesDir(), "libcuid.so").getAbsolutePath(), 436);
                    }
                    if (!f1520f) {
                        return e.a(new File(this.f1521a.getFilesDir(), "libcuid.so").getAbsolutePath(), 432);
                    }
                }
                return true;
            } catch (Exception e3) {
                b(e3);
                if (fileOutputStreamOpenFileOutput != null) {
                    try {
                        fileOutputStreamOpenFileOutput.close();
                    } catch (Exception e4) {
                        b(e4);
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStreamOpenFileOutput != null) {
                try {
                    fileOutputStreamOpenFileOutput.close();
                } catch (Exception e5) {
                    b(e5);
                }
            }
            throw th;
        }
    }

    private void e() throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(b.a.a.a.b.b.a());
                try {
                    this.f1523c = CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream).getPublicKey();
                    byteArrayInputStream.close();
                } catch (Exception unused) {
                    if (byteArrayInputStream == null) {
                    } else {
                        byteArrayInputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (Exception e2) {
                            b(e2);
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                b(e3);
            }
        } catch (Exception unused2) {
            byteArrayInputStream = null;
        } catch (Throwable th3) {
            byteArrayInputStream = null;
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        return e("android.permission.WRITE_SETTINGS");
    }

    /* JADX INFO: renamed from: b.a.a.a.b.c$c, reason: collision with other inner class name */
    static class C0035c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ApplicationInfo f1526a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1527b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1528c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1529d;

        private C0035c() {
            this.f1527b = 0;
            this.f1528c = false;
            this.f1529d = false;
        }

        /* synthetic */ C0035c(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(File file) throws Throwable {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            try {
                try {
                    char[] cArr = new char[8192];
                    CharArrayWriter charArrayWriter = new CharArrayWriter();
                    while (true) {
                        int i = fileReader.read(cArr);
                        if (i <= 0) {
                            break;
                        }
                        charArrayWriter.write(cArr, 0, i);
                        th = th;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Exception e2) {
                                b(e2);
                            }
                        }
                        throw th;
                    }
                    String string = charArrayWriter.toString();
                    try {
                        fileReader.close();
                    } catch (Exception e3) {
                        b(e3);
                    }
                    return string;
                } catch (Exception e4) {
                    e = e4;
                    b(e);
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (Exception e5) {
                            b(e5);
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e6) {
            e = e6;
            fileReader = null;
        } catch (Throwable th2) {
            th = th2;
            fileReader = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str, String str2) {
        try {
            return Settings.System.putString(this.f1521a.getContentResolver(), str, str2);
        } catch (Exception e2) {
            b(e2);
            return false;
        }
    }

    private static d c(Context context) {
        if (f1519e == null) {
            synchronized (d.class) {
                if (f1519e == null) {
                    SystemClock.uptimeMillis();
                    f1519e = new c(context).a();
                    SystemClock.uptimeMillis();
                }
            }
        }
        return f1519e;
    }

    private static String a(byte[] bArr) {
        if (bArr != null) {
            String str = "";
            for (byte b2 : bArr) {
                String hexString = Integer.toHexString(b2 & 255);
                str = hexString.length() == 1 ? str + "0" + hexString : str + hexString;
            }
            return str.toLowerCase();
        }
        throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str) {
        return this.f1521a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private synchronized void c(d dVar) {
        new Thread(b(dVar)).start();
    }

    private String[] a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = a(b.a.a.a.a.d.a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    public static String b(Context context) {
        return c(context).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d c() throws Throwable {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (!file.exists()) {
            return null;
        }
        String strB = b(file);
        if (TextUtils.isEmpty(strB)) {
            return null;
        }
        try {
            return d.a(new String(b.a.a.a.a.a.a(f1518d, f1518d, b.a.a.a.a.b.a(strB.getBytes()))));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Runnable b(d dVar) {
        return new b(dVar);
    }

    private static byte[] a(byte[] bArr, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(2, publicKey);
        return cipher.doFinal(bArr);
    }

    private d b() {
        return d.b(k("com.baidu.deviceid"), k("bd_setting_i"));
    }

    private List<C0035c> a(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.f1521a.getPackageManager();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (activityInfo != null && activityInfo.applicationInfo != null) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] bArrA = b.a.a.a.a.b.a(string.getBytes("utf-8"));
                                JSONObject jSONObject = new JSONObject(new String(bArrA));
                                C0035c c0035c = new C0035c(null);
                                c0035c.f1527b = jSONObject.getInt("priority");
                                c0035c.f1526a = resolveInfo.activityInfo.applicationInfo;
                                if (this.f1521a.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    c0035c.f1529d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = jSONObject.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (int i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (a(strArr, a(packageInfo.signatures))) {
                                            byte[] bArrA2 = a(b.a.a.a.a.b.a(string2.getBytes()), this.f1523c);
                                            if (bArrA2 != null && Arrays.equals(bArrA2, b.a.a.a.a.d.a(bArrA))) {
                                                c0035c.f1528c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(c0035c);
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        Collections.sort(arrayList, new a(this));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, String str2) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file2 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig");
        File file3 = new File(file2, ".cuid");
        try {
            if (file2.exists() && !file2.isDirectory()) {
                Random random = new Random();
                File parentFile = file2.getParentFile();
                String name = file2.getName();
                do {
                    file = new File(parentFile, name + random.nextInt() + ".tmp");
                } while (file.exists());
                file2.renameTo(file);
                file.delete();
            }
            file2.mkdirs();
            FileWriter fileWriter = new FileWriter(file3, false);
            fileWriter.write(b.a.a.a.a.b.a(b.a.a.a.a.a.b(f1518d, f1518d, (str + "=" + str2).getBytes()), "utf-8"));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException | Exception unused) {
        }
    }

    private boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        HashSet hashSet2 = new HashSet();
        for (String str2 : strArr2) {
            hashSet2.add(str2);
        }
        return hashSet.equals(hashSet2);
    }

    private static String a(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(string) ? "" : string;
    }

    private d a() throws Throwable {
        boolean z;
        String strI;
        List<C0035c> listA = a(new Intent("com.baidu.intent.action.GALAXY").setPackage(this.f1521a.getPackageName()), true);
        boolean z2 = false;
        if (listA == null || listA.size() == 0) {
            for (int i = 0; i < 3; i++) {
                Log.w("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            z = listA.get(0).f1528c;
            if (!z) {
                for (int i2 = 0; i2 < 3; i2++) {
                    Log.w("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
        }
        File file = new File(this.f1521a.getFilesDir(), "libcuid.so");
        a aVar = null;
        d dVarA = file.exists() ? d.a(f(b(file))) : null;
        if (dVarA == null) {
            this.f1522b |= 16;
            List<C0035c> listA2 = a(new Intent("com.baidu.intent.action.GALAXY"), z);
            if (listA2 != null) {
                String name = "files";
                File filesDir = this.f1521a.getFilesDir();
                if (!"files".equals(filesDir.getName())) {
                    Log.e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                    name = filesDir.getName();
                }
                for (C0035c c0035c : listA2) {
                    if (!c0035c.f1529d) {
                        File file2 = new File(new File(c0035c.f1526a.dataDir, name), "libcuid.so");
                        if (file2.exists() && (dVarA = d.a(f(b(file2)))) != null) {
                            break;
                        }
                    }
                }
            }
        }
        if (dVarA == null) {
            dVarA = d.a(f(k("com.baidu.deviceid.v2")));
        }
        boolean zE = e("android.permission.READ_EXTERNAL_STORAGE");
        if (dVarA == null && zE) {
            this.f1522b |= 2;
            dVarA = c();
        }
        if (dVarA == null) {
            this.f1522b |= 8;
            dVarA = b();
        }
        if (dVarA == null && zE) {
            this.f1522b |= 1;
            strI = i("");
            dVarA = j(strI);
            z2 = true;
        } else {
            strI = null;
        }
        if (dVarA == null) {
            this.f1522b |= 4;
            if (!z2) {
                strI = i("");
            }
            dVarA = new d(aVar);
            String strA = a(this.f1521a);
            dVarA.f1530a = b.a.a.a.a.c.a((Build.VERSION.SDK_INT < 23 ? strI + strA + UUID.randomUUID().toString() : "com.baidu" + strA).getBytes(), true);
            dVarA.f1531b = strI;
        }
        a(dVarA);
        c(dVarA);
        return dVarA;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(d dVar) {
        if (dVar.b()) {
            dVar.f1531b = "O";
            return true;
        }
        if (!dVar.c()) {
            return false;
        }
        dVar.f1531b = "0";
        return true;
    }
}
