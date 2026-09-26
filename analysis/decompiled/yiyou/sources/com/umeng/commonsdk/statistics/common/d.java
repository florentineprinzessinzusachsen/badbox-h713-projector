package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: compiled from: StoreHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f4081a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f4082b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f4083c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4084e = "mobclick_agent_user_";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4085f = "mobclick_agent_header_";
    private static final String g = "mobclick_agent_cached_";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f4086d;

    /* JADX INFO: compiled from: StoreHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f4087a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private File f4088b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private FilenameFilter f4089c;

        public a(Context context) {
            this(context, ".um");
        }

        public boolean a() {
            File[] fileArrListFiles = this.f4088b.listFiles();
            return fileArrListFiles != null && fileArrListFiles.length > 0;
        }

        public void b() {
            File[] fileArrListFiles = this.f4088b.listFiles(this.f4089c);
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return;
            }
            for (File file : fileArrListFiles) {
                file.delete();
            }
        }

        public int c() {
            File[] fileArrListFiles = this.f4088b.listFiles(this.f4089c);
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return 0;
            }
            return fileArrListFiles.length;
        }

        public a(Context context, String str) {
            this.f4087a = 10;
            this.f4089c = new FilenameFilter() { // from class: com.umeng.commonsdk.statistics.common.d.a.1
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str2) {
                    return str2.startsWith("um");
                }
            };
            this.f4088b = new File(context.getFilesDir(), str);
            if (this.f4088b.exists() && this.f4088b.isDirectory()) {
                return;
            }
            this.f4088b.mkdir();
        }

        public void a(b bVar) {
            File file;
            File[] fileArrListFiles = this.f4088b.listFiles(this.f4089c);
            if (fileArrListFiles != null && fileArrListFiles.length >= 10) {
                Arrays.sort(fileArrListFiles);
                int length = fileArrListFiles.length - 10;
                for (int i = 0; i < length; i++) {
                    fileArrListFiles[i].delete();
                }
            }
            if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
                return;
            }
            bVar.a(this.f4088b);
            int length2 = fileArrListFiles.length;
            for (int i2 = 0; i2 < length2; i2++) {
                try {
                    if (bVar.b(fileArrListFiles[i2])) {
                        file = fileArrListFiles[i2];
                        file.delete();
                    }
                } catch (Throwable unused) {
                    file = fileArrListFiles[i2];
                }
            }
            bVar.c(this.f4088b);
        }

        public void a(byte[] bArr) {
            if (bArr == null || bArr.length == 0) {
                return;
            }
            try {
                HelperUtils.writeFile(new File(this.f4088b, String.format(Locale.US, "um_cache_%d.env", Long.valueOf(System.currentTimeMillis()))), bArr);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: compiled from: StoreHelper.java */
    public interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    public d(Context context) {
        this.f4086d = new a(context);
    }

    public static synchronized d a(Context context) {
        f4082b = context.getApplicationContext();
        f4083c = context.getPackageName();
        if (f4081a == null) {
            f4081a = new d(context);
        }
        return f4081a;
    }

    private SharedPreferences f() {
        return f4082b.getSharedPreferences(f4084e + f4083c, 0);
    }

    public String b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4082b);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("st", null);
        }
        return null;
    }

    public boolean c() {
        return UMFrUtils.envelopeFileNumber(f4082b) > 0;
    }

    public String[] d() {
        try {
            SharedPreferences sharedPreferencesF = f();
            String string = sharedPreferencesF.getString("au_p", null);
            String string2 = sharedPreferencesF.getString("au_u", null);
            if (string != null && string2 != null) {
                return new String[]{string, string2};
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public void e() {
        f().edit().remove("au_p").remove("au_u").commit();
    }

    public void a(int i) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4082b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt("vt", i).commit();
        }
    }

    public int a() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4082b);
        if (sharedPreferences != null) {
            return sharedPreferences.getInt("vt", 0);
        }
        return 0;
    }

    public void a(String str) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f4082b);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString("st", str).commit();
        }
    }

    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor editorEdit = f().edit();
        editorEdit.putString("au_p", str);
        editorEdit.putString("au_u", str2);
        editorEdit.commit();
    }
}
