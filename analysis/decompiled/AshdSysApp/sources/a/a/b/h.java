package a.a.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SharedPreferences f8a;

    public h(Context context) {
        try {
            this.f8a = context.getSharedPreferences("SHARE", 0);
        } catch (Exception unused) {
        }
    }

    public int a() {
        return this.f8a.getInt("app_mode", 0);
    }

    public void a(int i) {
        Log.e("setAppMode", String.valueOf(i));
        SharedPreferences.Editor editorEdit = this.f8a.edit();
        editorEdit.putInt("app_mode", i);
        editorEdit.commit();
    }

    public void b(boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences.Editor editorEdit = this.f8a.edit();
        editorEdit.putBoolean("force_mode", boolValueOf.booleanValue());
        editorEdit.commit();
    }

    public boolean b() {
        return this.f8a.getBoolean("auto_check_mode", true);
    }

    public long c() {
        return this.f8a.getLong("download_size", 0L);
    }

    public String d() {
        return this.f8a.getString("download_target", null);
    }

    public void a(boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        SharedPreferences.Editor editorEdit = this.f8a.edit();
        editorEdit.putBoolean("auto_check_mode", boolValueOf.booleanValue());
        editorEdit.commit();
    }

    public void a(long j, long j2) {
        SharedPreferences.Editor editorEdit = this.f8a.edit();
        editorEdit.putLong("download_size", j);
        editorEdit.commit();
        SharedPreferences.Editor editorEdit2 = this.f8a.edit();
        editorEdit2.putLong("download_position", j2);
        editorEdit2.commit();
    }
}
