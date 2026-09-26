package m0;

import android.content.ComponentName;
import android.content.Context;
import d0.a0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1408a = a0.g("PackageManagerHelper");

    public static void a(Context context, Class cls, boolean z3) {
        String str = f1408a;
        try {
            int componentEnabledSetting = context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, cls.getName()));
            boolean z4 = false;
            if (componentEnabledSetting != 0 && componentEnabledSetting == 1) {
                z4 = true;
            }
            if (z3 == z4) {
                a0.e().a(str, "Skipping component enablement for ".concat(cls.getName()));
                return;
            }
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z3 ? 1 : 2, 1);
            a0 a0VarE = a0.e();
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append(" ");
            sb.append(z3 ? "enabled" : "disabled");
            a0VarE.a(str, sb.toString());
        } catch (Exception e4) {
            a0 a0VarE2 = a0.e();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cls.getName());
            sb2.append("could not be ");
            sb2.append(z3 ? "enabled" : "disabled");
            a0VarE2.b(str, sb2.toString(), e4);
        }
    }
}
