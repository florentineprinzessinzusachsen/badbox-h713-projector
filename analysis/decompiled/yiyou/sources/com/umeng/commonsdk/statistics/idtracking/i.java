package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;

/* JADX INFO: compiled from: OldUMIDTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4123a = "oldumid";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4124b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4125c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4126d;

    public i(Context context) {
        super(f4123a);
        this.f4125c = null;
        this.f4126d = null;
        this.f4124b = context;
    }

    private void b(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private void j() {
        try {
            b("/data/local/tmp/.um");
            HelperUtils.writeFile(new File("/data/local/tmp/.um/sysid.dat"), this.f4126d);
        } catch (Throwable unused) {
        }
    }

    private void k() {
        try {
            b("/sdcard/Android/obj/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/obj/.um/sysid.dat"), this.f4126d);
        } catch (Throwable unused) {
        }
    }

    private void l() {
        try {
            b("/sdcard/Android/data/.um");
            HelperUtils.writeFile(new File("/sdcard/Android/data/.um/sysid.dat"), this.f4126d);
        } catch (Throwable unused) {
        }
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        return this.f4125c;
    }

    public boolean g() {
        return h();
    }

    public boolean h() {
        this.f4126d = UMEnvelopeBuild.imprintProperty(this.f4124b, com.umeng.commonsdk.proguard.e.f3967f, null);
        if (TextUtils.isEmpty(this.f4126d)) {
            return false;
        }
        this.f4126d = DataHelper.encryptBySHA1(this.f4126d);
        String file = HelperUtils.readFile(new File("/sdcard/Android/data/.um/sysid.dat"));
        String file2 = HelperUtils.readFile(new File("/sdcard/Android/obj/.um/sysid.dat"));
        String file3 = HelperUtils.readFile(new File("/data/local/tmp/.um/sysid.dat"));
        if (TextUtils.isEmpty(file)) {
            l();
        } else if (!this.f4126d.equals(file)) {
            this.f4125c = file;
            return true;
        }
        if (TextUtils.isEmpty(file2)) {
            k();
        } else if (!this.f4126d.equals(file2)) {
            this.f4125c = file2;
            return true;
        }
        if (TextUtils.isEmpty(file3)) {
            j();
            return false;
        }
        if (this.f4126d.equals(file3)) {
            return false;
        }
        this.f4125c = file3;
        return true;
    }

    public void i() {
        try {
            l();
            k();
            j();
        } catch (Exception unused) {
        }
    }
}
