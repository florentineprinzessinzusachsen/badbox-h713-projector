package a.a.b;

import android.content.DialogInterface;
import com.android.sysapp.OtaActivity;

/* JADX INFO: loaded from: classes.dex */
public class e implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ OtaActivity f1a;

    public e(OtaActivity otaActivity) {
        this.f1a = otaActivity;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.f1a.finish();
    }
}
