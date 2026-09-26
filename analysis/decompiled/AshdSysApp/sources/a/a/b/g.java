package a.a.b;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.RecoverySystem;
import android.os.UpdateEngine;
import android.os.UpdateEngineCallback;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public static String e = n.f;
    public static File f = new File("/cache/recovery");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public UpdateEngine f3a;
    public Context b;
    public String c;
    public a d;

    public interface a extends RecoverySystem.ProgressListener {
        void a(int i);

        void a(int i, Object obj);

        void a(String str, int i);

        void b(String str, int i);

        @Override // android.os.RecoverySystem.ProgressListener
        void onProgress(int i);
    }

    public class b extends UpdateEngineCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a f4a;
        public Handler b = new Handler();

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ float f5a;

            public a(float f) {
                this.f5a = f;
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = b.this.f4a;
                if (aVar != null) {
                    aVar.b("进度更新...", (int) (((this.f5a / 2.0f) * 100.0f) + 50.0f));
                }
            }
        }

        /* JADX INFO: renamed from: a.a.b.g$b$b, reason: collision with other inner class name */
        public class RunnableC0000b implements Runnable {
            public RunnableC0000b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f4a.b("进度完成，准备重启...", 101);
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f4a.a("更新失败", -1);
            }
        }

        public b(g gVar, a aVar) {
            this.f4a = aVar;
        }

        public void onPayloadApplicationComplete(int i) {
            Handler handler;
            Runnable cVar;
            if (i == 0) {
                handler = this.b;
                cVar = new RunnableC0000b();
            } else {
                handler = this.b;
                cVar = new c();
            }
            handler.post(cVar);
        }

        public void onStatusUpdate(int i, float f) {
            if (i == 3) {
                g.c();
                Log.d("CMUpdate2OtaUpgradeUtils", "update progress: " + f);
                this.b.post(new a(f));
            }
        }
    }

    static {
        new File(f, "command");
        new File(f, "log");
    }

    public g(Context context) {
        this.f3a = null;
        this.b = context;
        this.f3a = new UpdateEngine();
    }

    public static /* synthetic */ String c() {
        return "CMUpdate2OtaUpgradeUtils";
    }

    public File a(String str, String str2) {
        String str3;
        UnsupportedEncodingException e2;
        String[] strArrSplit = str2.split("/");
        File file = new File(str);
        if (strArrSplit.length <= 1) {
            return file;
        }
        int i = 0;
        while (i < strArrSplit.length - 1) {
            String str4 = strArrSplit[i];
            try {
                str4 = new String(str4.getBytes("8859_1"), "GB2312");
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
            }
            i++;
            file = new File(file, str4);
        }
        Log.d("CMUpdate2OtaUpgradeUtils", "ret = " + file);
        if (!file.exists()) {
            file.mkdirs();
        }
        String str5 = strArrSplit[strArrSplit.length - 1];
        try {
            str3 = new String(str5.getBytes("8859_1"), "GB2312");
            try {
                Log.d("CMUpdate2OtaUpgradeUtils", "substr = " + str3);
            } catch (UnsupportedEncodingException e4) {
                e2 = e4;
                e2.printStackTrace();
            }
        } catch (UnsupportedEncodingException e5) {
            str3 = str5;
            e2 = e5;
        }
        File file2 = new File(file, str3);
        Log.d("CMUpdate2OtaUpgradeUtils", "ret = " + file2);
        return file2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    public void a() throws Throwable {
        Exception e2;
        IOException e3;
        ?? r1;
        this.f3a.bind(new b(this, this.d));
        ?? inputStreamReader = "/data/ota_package/tmp/META-INF/com/android/metadata";
        int[] iArr = new int[2];
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    inputStreamReader = new InputStreamReader(new FileInputStream(new File("/data/ota_package/tmp/META-INF/com/android/metadata")));
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                        while (true) {
                            try {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (line.contains("payload.bin")) {
                                    String[] strArrSplit = line.split(",");
                                    for (int i = 0; i < strArrSplit.length; i++) {
                                        if (strArrSplit[i].contains("payload.bin")) {
                                            String[] strArrSplit2 = strArrSplit[i].split(":");
                                            int iIntValue = Integer.valueOf(strArrSplit2[1]).intValue();
                                            int iIntValue2 = Integer.valueOf(strArrSplit2[2]).intValue();
                                            System.out.print(iIntValue);
                                            System.out.print(iIntValue2);
                                            iArr[0] = iIntValue;
                                            iArr[1] = iIntValue2;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            } catch (IOException e4) {
                                e3 = e4;
                                bufferedReader = bufferedReader2;
                                e3.printStackTrace();
                                bufferedReader.close();
                                r1 = inputStreamReader;
                                r1.close();
                            } catch (Exception e5) {
                                e2 = e5;
                                bufferedReader = bufferedReader2;
                                e2.printStackTrace();
                                bufferedReader.close();
                                r1 = inputStreamReader;
                                r1.close();
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                try {
                                    bufferedReader.close();
                                    inputStreamReader.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                                throw th;
                            }
                        }
                        bufferedReader2.close();
                        inputStreamReader.close();
                    } catch (IOException e7) {
                        e3 = e7;
                    } catch (Exception e8) {
                        e2 = e8;
                    }
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            } catch (IOException e10) {
                e3 = e10;
                inputStreamReader = 0;
            } catch (Exception e11) {
                e2 = e11;
                inputStreamReader = 0;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = 0;
            }
            try {
                Log.w("CMUpdate2OtaUpgradeUtils", "applyUpdate");
                this.f3a.applyPayload("file:///data/ota_package/tmp/update.zip", iArr[0], iArr[1], a("/data/ota_package/tmp/payload_properties.txt"));
            } catch (Exception e12) {
                String localizedMessage = e12.getLocalizedMessage();
                Log.w("CMUpdate2OtaUpgradeUtils", "getLocalizedMessage: " + localizedMessage);
                if (localizedMessage.contains("reboot") && localizedMessage.contains("applied")) {
                    this.d.b("更新完成", 101);
                }
                e12.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final void a(File file) {
        file.setWritable(true, false);
        file.setReadable(true, false);
        file.setExecutable(true, false);
    }

    public void a(boolean z) {
    }

    public String[] a(String str) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(new File(str)));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ArrayList arrayList = new ArrayList();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    inputStreamReader.close();
                    return (String[]) arrayList.toArray(new String[arrayList.size()]);
                }
                Log.d("CMUpdate2OtaUpgradeUtils", "getPayloadProperties line: " + line);
                arrayList.add(line);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public void b() {
        Log.w("CMUpdate2OtaUpgradeUtils", "start reboot!");
        Intent intent = new Intent("android.intent.action.REBOOT");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        intent.setFlags(268435456);
        this.b.startActivity(intent);
    }

    public boolean a(String str, a aVar) throws Throwable {
        boolean z;
        Method method;
        boolean zBooleanValue;
        Throwable th;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream2;
        Throwable th2;
        boolean z2;
        this.d = aVar;
        File file = new File(str);
        String absolutePath = file.getAbsolutePath();
        File file2 = new File("/data/ota_package/tmp/");
        if (!file2.exists()) {
            file2.mkdir();
            a(file2);
        }
        if (file2.exists() && file2.canWrite()) {
            File file3 = absolutePath.lastIndexOf(".zip") != -1 ? new File("/data/ota_package/tmp/", "update.zip") : new File("/data/ota_package/tmp/", "payload.bin");
            File file4 = new File(absolutePath);
            try {
                if (!file3.exists()) {
                    file3.createNewFile();
                    a(file3);
                }
                fileInputStream = new FileInputStream(file4);
                try {
                    fileOutputStream2 = new FileOutputStream(file3);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (i != -1) {
                                fileOutputStream2.write(bArr, 0, i);
                            } else {
                                try {
                                    break;
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                } catch (NullPointerException e3) {
                                    Log.d("CMUpdate2OtaUpgradeUtils", "outputStream or inputStream is null");
                                    e3.printStackTrace();
                                }
                            }
                        }
                        fileOutputStream2.flush();
                        fileInputStream.close();
                        fileOutputStream2.close();
                        z2 = true;
                    } catch (IOException e4) {
                        e = e4;
                        try {
                            e.printStackTrace();
                            try {
                                fileOutputStream2.flush();
                                fileInputStream.close();
                                fileOutputStream2.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            } catch (NullPointerException e6) {
                                Log.d("CMUpdate2OtaUpgradeUtils", "outputStream or inputStream is null");
                                e6.printStackTrace();
                            }
                            z2 = false;
                        } catch (Throwable th3) {
                            th2 = th3;
                            fileOutputStream = fileOutputStream2;
                            th = th2;
                            try {
                                fileOutputStream.flush();
                                fileInputStream.close();
                                fileOutputStream.close();
                                throw th;
                            } catch (IOException e7) {
                                e7.printStackTrace();
                                throw th;
                            } catch (NullPointerException e8) {
                                Log.d("CMUpdate2OtaUpgradeUtils", "outputStream or inputStream is null");
                                e8.printStackTrace();
                                throw th;
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        fileOutputStream = fileOutputStream2;
                        fileOutputStream.flush();
                        fileInputStream.close();
                        fileOutputStream.close();
                        throw th;
                    }
                } catch (IOException e9) {
                    e = e9;
                    fileOutputStream2 = null;
                } catch (Throwable th5) {
                    th2 = th5;
                    fileOutputStream = null;
                    th = th2;
                    fileOutputStream.flush();
                    fileInputStream.close();
                    fileOutputStream.close();
                    throw th;
                }
            } catch (IOException e10) {
                e = e10;
                fileOutputStream2 = null;
                fileInputStream = null;
            } catch (Throwable th6) {
                th = th6;
                fileOutputStream = null;
                fileInputStream = null;
            }
            if (z2) {
                Log.w("CMUpdate2OtaUpgradeUtils", "cope file failed");
                aVar.a(0);
            }
            aVar.a(100);
            this.c = file3.getAbsolutePath();
        }
        String str2 = this.c;
        Log.w("CMUpdate2OtaUpgradeUtils", "zipFile: " + str2 + " unzipPath: /data/ota_package/tmp/");
        if (str2.lastIndexOf(".bin") != -1) {
            z = false;
        } else {
            try {
                ZipFile zipFile = new ZipFile(new File(str2));
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                byte[] bArr2 = new byte[1024];
                while (enumerationEntries != null && enumerationEntries.hasMoreElements()) {
                    Log.d("CMUpdate2OtaUpgradeUtils", "unzip start");
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    if (zipEntryNextElement.isDirectory()) {
                        Log.d("CMUpdate2OtaUpgradeUtils", "file name: " + zipEntryNextElement.getName());
                        File file5 = new File(new String(("/data/ota_package/tmp/" + zipEntryNextElement.getName()).getBytes("8859_1"), "GB2312"));
                        file5.mkdir();
                        a(file5);
                    } else {
                        Log.d("CMUpdate2OtaUpgradeUtils", "file name: " + zipEntryNextElement.getName());
                        a("/data/ota_package/tmp/", zipEntryNextElement.getName());
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/data/ota_package/tmp/" + zipEntryNextElement.getName()));
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                        Log.d("CMUpdate2OtaUpgradeUtils", "copy file");
                        while (true) {
                            int i2 = bufferedInputStream.read(bArr2, 0, 1024);
                            if (i2 == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr2, 0, i2);
                        }
                        Log.d("CMUpdate2OtaUpgradeUtils", "unzip end, start chmod");
                        a(new File("/data/ota_package/tmp/", zipEntryNextElement.getName()));
                        bufferedInputStream.close();
                        bufferedOutputStream.close();
                    }
                }
                zipFile.close();
                z = true;
            } catch (IOException e11) {
                e11.printStackTrace();
                z = false;
            }
        }
        Log.v("CMUpdate2OtaUpgradeUtils", "upzip file zipStats=" + z);
        if (z) {
            aVar.onProgress(70);
            try {
                method = Class.forName("android.os.UpdateEngine").getMethod("verifyPayloadMetadata", String.class);
            } catch (Exception unused) {
                method = null;
            }
            try {
                if (new File("/data/ota_package/tmp/", "payload.bin").exists()) {
                    Boolean bool = (Boolean) method.invoke(this.f3a, "/data/ota_package/tmp/payload.bin");
                    Log.e("CMUpdate2OtaUpgradeUtils", "verifyPackage=" + bool);
                    zBooleanValue = bool.booleanValue();
                } else {
                    zBooleanValue = false;
                }
            } catch (Exception e12) {
                e12.printStackTrace();
            }
            Log.v("CMUpdate2OtaUpgradeUtils", "verifyStatus=" + zBooleanValue);
            if (zBooleanValue) {
                aVar.b("开始升级", -1);
            } else {
                aVar.a(0, file.getPath());
            }
        }
        return false;
    }
}
