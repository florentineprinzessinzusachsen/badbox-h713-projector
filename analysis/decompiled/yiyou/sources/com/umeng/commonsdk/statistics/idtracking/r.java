package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.os.Environment;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: UTDIdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4143a = "utdid";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4144b = "android.permission.WRITE_EXTERNAL_STORAGE";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Pattern f4145c = Pattern.compile("UTDID\">([^<]+)");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f4146d;

    public r(Context context) {
        super(f4143a);
        this.f4146d = context;
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = f4145c.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String g() {
        File fileH = h();
        if (fileH != null && fileH.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileH);
                try {
                    return b(HelperUtils.readStreamToString(fileInputStream));
                } finally {
                    HelperUtils.safeClose(fileInputStream);
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private File h() {
        if (DeviceConfig.checkPermission(this.f4146d, f4144b) && Environment.getExternalStorageState().equals("mounted")) {
            try {
                return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.a
    public String f() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, this.f4146d);
        } catch (Exception unused) {
            return g();
        }
    }
}
