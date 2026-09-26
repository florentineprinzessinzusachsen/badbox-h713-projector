package com.umeng.commonsdk.internal.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: CpuUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: compiled from: CpuUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f3837a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3838b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3839c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f3840d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f3841e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f3842f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
    }

    /* JADX WARN: Code duplicated, block: B:126:0x0131 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:133:0x013b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:137:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:147:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.BufferedReader] */
    public static a a() throws Throwable {
        int i;
        a aVar;
        FileReader fileReader;
        ?? bufferedReader;
        Throwable th;
        try {
            try {
                try {
                    aVar = new a();
                    try {
                        fileReader = new FileReader("/proc/cpuinfo");
                        try {
                            bufferedReader = new BufferedReader(fileReader);
                            try {
                                try {
                                    int i2 = 0;
                                    boolean z = true;
                                    i = 0;
                                    for (String line = bufferedReader.readLine(); !TextUtils.isEmpty(line) && (i2 = i2 + 1) < 30; line = bufferedReader.readLine()) {
                                        try {
                                            String[] strArrSplit = line.split(":\\s+", 2);
                                            if (z && strArrSplit != null && strArrSplit.length > 1) {
                                                aVar.f3837a = strArrSplit[1];
                                                z = false;
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("processor")) {
                                                i++;
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("Features")) {
                                                aVar.f3840d = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("implementer")) {
                                                aVar.f3841e = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("architecture")) {
                                                aVar.f3842f = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("variant")) {
                                                aVar.g = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("part")) {
                                                aVar.h = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("revision")) {
                                                aVar.i = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("Hardware")) {
                                                aVar.j = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("Revision")) {
                                                aVar.k = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("Serial")) {
                                                aVar.l = strArrSplit[1];
                                            }
                                            if (strArrSplit != null && strArrSplit.length > 1 && strArrSplit[0].contains("implementer")) {
                                                aVar.f3841e = strArrSplit[1];
                                            }
                                        } catch (Exception unused) {
                                            bufferedReader = bufferedReader;
                                            if (fileReader != null) {
                                                try {
                                                    fileReader.close();
                                                } catch (IOException unused2) {
                                                }
                                            }
                                            if (bufferedReader != 0) {
                                            }
                                            aVar.f3839c = i;
                                            return aVar;
                                        }
                                    }
                                    try {
                                        fileReader.close();
                                    } catch (IOException unused3) {
                                    }
                                } catch (Exception unused4) {
                                    i = 0;
                                    bufferedReader = bufferedReader;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileReader != null) {
                                    try {
                                        fileReader.close();
                                    } catch (IOException unused5) {
                                    }
                                }
                                if (bufferedReader != 0) {
                                    throw th;
                                }
                                try {
                                    bufferedReader.close();
                                    throw th;
                                } catch (IOException unused6) {
                                    throw th;
                                }
                            }
                        } catch (Exception unused7) {
                            i = 0;
                            bufferedReader = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader = 0;
                            th = th;
                            if (fileReader != null) {
                                fileReader.close();
                            }
                            if (bufferedReader != 0) {
                                throw th;
                            }
                            bufferedReader.close();
                            throw th;
                        }
                    } catch (Exception unused8) {
                        i = 0;
                        fileReader = null;
                        bufferedReader = fileReader;
                        if (fileReader != null) {
                            fileReader.close();
                        }
                        if (bufferedReader != 0) {
                            bufferedReader.close();
                        }
                        aVar.f3839c = i;
                        return aVar;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileReader = null;
                    bufferedReader = 0;
                }
            } catch (Exception unused9) {
                i = 0;
                aVar = null;
                fileReader = null;
            }
            bufferedReader.close();
        } catch (IOException unused10) {
        }
        aVar.f3839c = i;
        return aVar;
    }

    public static String b() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        return str.trim();
    }

    public static String c() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        return str.trim();
    }

    public static String d() throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"));
            try {
                String strTrim = bufferedReader2.readLine().trim();
                try {
                    bufferedReader2.close();
                    return strTrim;
                } catch (Throwable unused) {
                    return strTrim;
                }
            } catch (Exception unused2) {
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused3) {
                    }
                }
                return "";
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
