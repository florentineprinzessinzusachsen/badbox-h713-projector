package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.UMImprintChangeCallback;
import com.umeng.commonsdk.statistics.internal.UMImprintPreProcessCallback;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class ImprintHandler implements FileLockCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4091a = "ImprintHandler";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4093c = ".imprint";
    private static Context k = null;
    private static FileLockUtil l = null;
    private static final int m = 0;
    private static final int n = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.umeng.commonsdk.statistics.internal.d f4096e;
    private a h = new a();
    private com.umeng.commonsdk.statistics.proto.d i = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Object f4092b = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final byte[] f4094d = "pbl0".getBytes();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Map<String, ArrayList<UMImprintChangeCallback>> f4095f = new HashMap();
    private static Object g = new Object();
    private static ImprintHandler j = null;
    private static Map<String, UMImprintPreProcessCallback> o = new HashMap();
    private static Object p = new Object();

    private ImprintHandler(Context context) {
        k = context.getApplicationContext();
    }

    private static void a(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        synchronized (g) {
            try {
                int i = 0;
                if (f4095f.containsKey(str)) {
                    ArrayList<UMImprintChangeCallback> arrayList = f4095f.get(str);
                    int size = arrayList.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size);
                    while (i < size) {
                        if (uMImprintChangeCallback == arrayList.get(i)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList.size());
                } else {
                    ArrayList<UMImprintChangeCallback> arrayList2 = new ArrayList<>();
                    int size2 = arrayList2.size();
                    ULog.i("--->>> addCallback: before add: callbacks size is: " + size2);
                    while (i < size2) {
                        if (uMImprintChangeCallback == arrayList2.get(i)) {
                            ULog.i("--->>> addCallback: callback has exist, just exit");
                            return;
                        }
                        i++;
                    }
                    arrayList2.add(uMImprintChangeCallback);
                    ULog.i("--->>> addCallback: after add: callbacks size is: " + arrayList2.size());
                    f4095f.put(str, arrayList2);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    private static void b(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (TextUtils.isEmpty(str) || uMImprintChangeCallback == null) {
            return;
        }
        synchronized (g) {
            try {
                if (f4095f.containsKey(str)) {
                    ArrayList<UMImprintChangeCallback> arrayList = f4095f.get(str);
                    if (uMImprintChangeCallback != null && arrayList.size() > 0) {
                        int size = arrayList.size();
                        ULog.i("--->>> removeCallback: before remove: callbacks size is: " + size);
                        for (int i = 0; i < size; i++) {
                            if (uMImprintChangeCallback == arrayList.get(i)) {
                                ULog.i("--->>> removeCallback: remove index " + i);
                                arrayList.remove(i);
                                break;
                            }
                        }
                        ULog.i("--->>> removeCallback: after remove: callbacks size is: " + arrayList.size());
                        if (arrayList.size() == 0) {
                            ULog.i("--->>> removeCallback: remove key from map: key = " + str);
                            f4095f.remove(str);
                        }
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    private boolean c(com.umeng.commonsdk.statistics.proto.d dVar) {
        if (!dVar.i().equals(a(dVar))) {
            return false;
        }
        for (com.umeng.commonsdk.statistics.proto.e eVar : dVar.c().values()) {
            byte[] bArrReverseHexString = DataHelper.reverseHexString(eVar.h());
            byte[] bArrA = a(eVar);
            for (int i = 0; i < 4; i++) {
                if (bArrReverseHexString[i] != bArrA[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private com.umeng.commonsdk.statistics.proto.d d(com.umeng.commonsdk.statistics.proto.d dVar) {
        ArrayList<UMImprintChangeCallback> arrayList;
        boolean z;
        ArrayList<UMImprintChangeCallback> arrayList2;
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        Map<String, com.umeng.commonsdk.statistics.proto.e> mapC = dVar.c();
        ArrayList<String> arrayList3 = new ArrayList(mapC.size() / 2);
        Iterator<Map.Entry<String, com.umeng.commonsdk.statistics.proto.e>> it = mapC.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> next = it.next();
            if (next.getValue().d()) {
                String key = next.getKey();
                String str = next.getValue().f4221a;
                synchronized (p) {
                    z = !TextUtils.isEmpty(key) && o.containsKey(key) && (uMImprintPreProcessCallback = o.get(key)) != null && uMImprintPreProcessCallback.onPreProcessImprintKey(key, str);
                }
                if (z) {
                    arrayList3.add(key);
                }
                synchronized (g) {
                    if (!TextUtils.isEmpty(key) && f4095f.containsKey(key) && (arrayList2 = f4095f.get(key)) != null) {
                        for (int i = 0; i < arrayList2.size(); i++) {
                            arrayList2.get(i).onImprintValueChanged(key, str);
                        }
                    }
                }
            } else {
                arrayList3.add(next.getKey());
            }
        }
        for (String str2 : arrayList3) {
            synchronized (g) {
                if (!TextUtils.isEmpty(str2) && f4095f.containsKey(str2) && (arrayList = f4095f.get(str2)) != null) {
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        arrayList.get(i2).onImprintValueChanged(str2, null);
                    }
                }
            }
            mapC.remove(str2);
        }
        return dVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v0 */
    private void e() {
        FileInputStream fileInputStreamOpenFileInput;
        File filesDir = k.getFilesDir();
        ?? r2 = f4093c;
        File file = new File(filesDir, f4093c);
        synchronized (f4092b) {
            if (file.exists()) {
                ?? r0 = 0;
                byte[] streamToByteArray = null;
                try {
                    try {
                        fileInputStreamOpenFileInput = k.openFileInput(f4093c);
                        try {
                            streamToByteArray = HelperUtils.readStreamToByteArray(fileInputStreamOpenFileInput);
                            r2 = fileInputStreamOpenFileInput;
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            r2 = fileInputStreamOpenFileInput;
                        }
                    } catch (Throwable th) {
                        ?? r4 = r2;
                        th = th;
                        r0 = r4;
                        HelperUtils.safeClose((InputStream) r0);
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    fileInputStreamOpenFileInput = null;
                } catch (Throwable th2) {
                    th = th2;
                    HelperUtils.safeClose((InputStream) r0);
                    throw th;
                }
                HelperUtils.safeClose((InputStream) r2);
                if (streamToByteArray != null) {
                    try {
                        com.umeng.commonsdk.statistics.proto.d dVar = new com.umeng.commonsdk.statistics.proto.d();
                        new com.umeng.commonsdk.proguard.m().a(dVar, streamToByteArray);
                        this.i = dVar;
                        this.h.a(dVar);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }
    }

    public static synchronized ImprintHandler getImprintService(Context context) {
        if (j == null) {
            j = new ImprintHandler(context);
            l = new FileLockUtil();
            if (l != null) {
                l.doFileOperateion(new File(k.getFilesDir(), f4093c), j, 0);
            }
        }
        return j;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(File file, int i) {
        if (i == 0) {
            j.e();
        } else if (i == 1) {
            j.a(file);
        }
        return true;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str) {
        return false;
    }

    @Override // com.umeng.commonsdk.utils.FileLockCallback
    public boolean onFileLock(String str, Object obj) {
        return false;
    }

    public void registImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (TextUtils.isEmpty(str) || uMImprintChangeCallback == null) {
            return;
        }
        a(str, uMImprintChangeCallback);
    }

    public void registPreProcessCallback(String str, UMImprintPreProcessCallback uMImprintPreProcessCallback) {
        if (TextUtils.isEmpty(str) || uMImprintPreProcessCallback == null) {
            return;
        }
        synchronized (p) {
            try {
                if (o.containsKey(str)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> key : " + str + " PreProcesser has registed!");
                } else {
                    o.put(str, uMImprintPreProcessCallback);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> registPreProcessCallback: key : " + str + " regist success.");
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    public void unregistImprintCallback(String str, UMImprintChangeCallback uMImprintChangeCallback) {
        if (TextUtils.isEmpty(str) || uMImprintChangeCallback == null) {
            return;
        }
        b(str, uMImprintChangeCallback);
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Map<String, String> f4097a = new HashMap();

        a() {
        }

        private synchronized void b(com.umeng.commonsdk.statistics.proto.d dVar) {
            com.umeng.commonsdk.statistics.proto.e eVar;
            if (dVar != null) {
                try {
                    if (dVar.e()) {
                        Map<String, com.umeng.commonsdk.statistics.proto.e> mapC = dVar.c();
                        for (String str : mapC.keySet()) {
                            if (!TextUtils.isEmpty(str) && (eVar = mapC.get(str)) != null) {
                                String strB = eVar.b();
                                if (TextUtils.isEmpty(strB)) {
                                    continue;
                                } else {
                                    this.f4097a.put(str, strB);
                                    if (AnalyticsConstants.UM_DEBUG) {
                                        Log.i(ImprintHandler.f4091a, "imKey is " + str + ", imValue is " + strB);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }

        public synchronized void a(String str) {
            if (this.f4097a != null && this.f4097a.size() > 0 && !TextUtils.isEmpty(str) && this.f4097a.containsKey(str)) {
                this.f4097a.remove(str);
            }
        }

        a(com.umeng.commonsdk.statistics.proto.d dVar) {
            a(dVar);
        }

        public void a(com.umeng.commonsdk.statistics.proto.d dVar) {
            if (dVar == null) {
                return;
            }
            b(dVar);
        }

        public synchronized String a(String str, String str2) {
            if (!TextUtils.isEmpty(str) && this.f4097a.size() > 0) {
                String str3 = this.f4097a.get(str);
                return !TextUtils.isEmpty(str3) ? str3 : str2;
            }
            return str2;
        }
    }

    public a c() {
        return this.h;
    }

    public void b(com.umeng.commonsdk.statistics.proto.d dVar) {
        com.umeng.commonsdk.statistics.proto.d dVarA;
        boolean z;
        if (dVar == null) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.d("Imprint is null");
                return;
            }
            return;
        }
        if (!c(dVar)) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e("Imprint is not valid");
                return;
            }
            return;
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.d("Imprint is ok");
        }
        HashMap map = new HashMap();
        synchronized (this) {
            com.umeng.commonsdk.statistics.proto.d dVar2 = this.i;
            String strI = null;
            String strI2 = dVar2 == null ? null : dVar2.i();
            if (dVar2 == null) {
                dVarA = d(dVar);
            } else {
                dVarA = a(dVar2, dVar, map);
            }
            this.i = dVarA;
            if (dVarA != null) {
                strI = dVarA.i();
            }
            z = !a(strI2, strI);
        }
        com.umeng.commonsdk.statistics.proto.d dVar3 = this.i;
        if (dVar3 != null) {
            boolean z2 = AnalyticsConstants.UM_DEBUG;
            if (z) {
                this.h.a(dVar3);
                com.umeng.commonsdk.statistics.internal.d dVar4 = this.f4096e;
                if (dVar4 != null) {
                    dVar4.onImprintChanged(this.h);
                }
            }
        }
        if (map.size() > 0) {
            synchronized (g) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (!TextUtils.isEmpty(key) && f4095f.containsKey(key)) {
                        ULog.i("--->>> target imprint key is: " + key + "; value is: " + value);
                        ArrayList<UMImprintChangeCallback> arrayList = f4095f.get(key);
                        if (arrayList != null) {
                            for (int i = 0; i < arrayList.size(); i++) {
                                arrayList.get(i).onImprintValueChanged(key, value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (p) {
            try {
                if (o.containsKey(str)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: unregist [" + str + "] success.");
                    f4095f.remove(str);
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> unregistPreProcessCallback: can't find [" + str + "], pls regist first.");
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(k, th);
            }
        }
    }

    public void a(com.umeng.commonsdk.statistics.internal.d dVar) {
        this.f4096e = dVar;
    }

    public void d() {
        if (this.i == null || l == null) {
            return;
        }
        File file = new File(k.getFilesDir(), f4093c);
        if (!file.exists()) {
            try {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    UMCrashManager.reportCrash(k, e2);
                }
            } catch (IOException unused) {
                file.createNewFile();
            }
        }
        l.doFileOperateion(file, j, 1);
    }

    public String a(com.umeng.commonsdk.statistics.proto.d dVar) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : new TreeMap(dVar.c()).entrySet()) {
            sb.append((String) entry.getKey());
            if (((com.umeng.commonsdk.statistics.proto.e) entry.getValue()).d()) {
                sb.append(((com.umeng.commonsdk.statistics.proto.e) entry.getValue()).b());
            }
            sb.append(((com.umeng.commonsdk.statistics.proto.e) entry.getValue()).e());
            sb.append(((com.umeng.commonsdk.statistics.proto.e) entry.getValue()).h());
        }
        sb.append(dVar.f4210b);
        return HelperUtils.MD5(sb.toString()).toLowerCase(Locale.US);
    }

    public byte[] a(com.umeng.commonsdk.statistics.proto.e eVar) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(null);
        byteBufferAllocate.putLong(eVar.e());
        byte[] bArrArray = byteBufferAllocate.array();
        byte[] bArr = f4094d;
        byte[] bArr2 = new byte[4];
        for (int i = 0; i < 4; i++) {
            bArr2[i] = (byte) (bArrArray[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public byte[] a() {
        try {
            synchronized (this) {
                if (this.i == null) {
                    return null;
                }
                return new com.umeng.commonsdk.proguard.s().a(this.i);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(k, th);
            return null;
        }
    }

    public synchronized com.umeng.commonsdk.statistics.proto.d b() {
        return this.i;
    }

    private boolean a(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private com.umeng.commonsdk.statistics.proto.d a(com.umeng.commonsdk.statistics.proto.d dVar, com.umeng.commonsdk.statistics.proto.d dVar2, Map<String, String> map) {
        UMImprintPreProcessCallback uMImprintPreProcessCallback;
        ArrayList<UMImprintChangeCallback> arrayList;
        if (dVar2 == null) {
            return dVar;
        }
        Map<String, com.umeng.commonsdk.statistics.proto.e> mapC = dVar.c();
        for (Map.Entry<String, com.umeng.commonsdk.statistics.proto.e> entry : dVar2.c().entrySet()) {
            int i = 0;
            if (entry.getValue().d()) {
                String key = entry.getKey();
                String str = entry.getValue().f4221a;
                synchronized (p) {
                    if (!TextUtils.isEmpty(key) && o.containsKey(key) && (uMImprintPreProcessCallback = o.get(key)) != null && uMImprintPreProcessCallback.onPreProcessImprintKey(key, str)) {
                        i = 1;
                    }
                }
                if (i == 0) {
                    mapC.put(entry.getKey(), entry.getValue());
                    synchronized (g) {
                        if (!TextUtils.isEmpty(key) && f4095f.containsKey(key) && f4095f.get(key) != null) {
                            map.put(key, str);
                        }
                    }
                } else {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> merge: [" + key + "] should be ignored.");
                }
            } else {
                String key2 = entry.getKey();
                synchronized (g) {
                    if (!TextUtils.isEmpty(key2) && f4095f.containsKey(key2) && (arrayList = f4095f.get(key2)) != null) {
                        while (i < arrayList.size()) {
                            arrayList.get(i).onImprintValueChanged(key2, null);
                            i++;
                        }
                    }
                }
                mapC.remove(key2);
                this.h.a(key2);
            }
        }
        dVar.a(dVar2.f());
        dVar.a(a(dVar));
        return dVar;
    }

    private void a(File file) {
        if (this.i == null) {
            return;
        }
        try {
            synchronized (f4092b) {
                byte[] bArrA = new com.umeng.commonsdk.proguard.s().a(this.i);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(bArrA);
                    fileOutputStream.flush();
                    HelperUtils.safeClose(fileOutputStream);
                } catch (Throwable th) {
                    HelperUtils.safeClose(fileOutputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
