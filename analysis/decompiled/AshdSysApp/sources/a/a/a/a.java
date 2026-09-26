package a.a.a;

import android.content.DialogInterface;
import com.android.service.TemperatureMonitoringService;

/* JADX INFO: loaded from: classes.dex */
public class a implements DialogInterface.OnClickListener {
    public a(TemperatureMonitoringService temperatureMonitoringService) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
