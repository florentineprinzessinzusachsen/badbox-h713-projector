package com.baidu.mobstat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import java.util.List;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'a' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f3523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t f3524b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final t f3525c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final t f3526d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final /* synthetic */ t[] f3527f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3528e;

    static {
        int i = 1;
        f3523a = new t("SERVICE", 0, i) { // from class: com.baidu.mobstat.t.1
            @Override // com.baidu.mobstat.t
            public void a(Context context) {
                if (t.d(context) && u.a(context).b(context)) {
                    try {
                        Intent intent = new Intent(context, Class.forName("com.baidu.bottom.service.BottomService"));
                        intent.putExtra("SDK_PRODUCT_LY", "MS");
                        context.startService(intent);
                    } catch (Throwable th) {
                        al.c().b(th);
                    }
                }
            }
        };
        int i2 = 2;
        f3524b = new t("NO_SERVICE", i, i2) { // from class: com.baidu.mobstat.t.2
            @Override // com.baidu.mobstat.t
            public void a(Context context) {
                if (t.d(context)) {
                    Context applicationContext = context.getApplicationContext();
                    a aVarA = u.a(context);
                    ac acVar = new ac();
                    acVar.f3430a = false;
                    acVar.f3431b = "M";
                    acVar.f3432c = false;
                    aVarA.a(applicationContext, acVar.a());
                }
            }
        };
        int i3 = 3;
        f3525c = new t("RECEIVER", i2, i3) { // from class: com.baidu.mobstat.t.3
            @Override // com.baidu.mobstat.t
            public void a(Context context) {
                if (t.d(context)) {
                    Context applicationContext = context.getApplicationContext();
                    a aVarA = u.a(context);
                    ac acVar = new ac();
                    acVar.f3430a = false;
                    acVar.f3431b = "R";
                    acVar.f3432c = false;
                    aVarA.a(applicationContext, acVar.a());
                }
            }
        };
        f3526d = new t("ERISED", i3, 4) { // from class: com.baidu.mobstat.t.4
            @Override // com.baidu.mobstat.t
            public void a(Context context) {
                if (t.d(context)) {
                    Context applicationContext = context.getApplicationContext();
                    a aVarA = u.a(context);
                    ac acVar = new ac();
                    acVar.f3430a = false;
                    acVar.f3431b = "E";
                    acVar.f3432c = false;
                    aVarA.a(applicationContext, acVar.a());
                }
            }
        };
        f3527f = new t[]{f3523a, f3524b, f3525c, f3526d};
    }

    public static t a(int i) {
        for (t tVar : values()) {
            if (tVar.f3528e == i) {
                return tVar;
            }
        }
        return f3524b;
    }

    public static boolean b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            try {
                List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                for (int i = 0; runningServices != null && i < runningServices.size(); i++) {
                    if ("com.baidu.bottom.service.BottomService".equals(runningServices.get(i).service.getClassName())) {
                        return true;
                    }
                }
            } catch (Exception e2) {
                al.c().a(e2);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(Context context) {
        return at.e(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static t valueOf(String str) {
        return (t) Enum.valueOf(t.class, str);
    }

    public static t[] values() {
        return (t[]) f3527f.clone();
    }

    public abstract void a(Context context);

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.f3528e);
    }

    private t(String str, int i, int i2) {
        super(str, i);
        this.f3528e = i2;
    }
}
