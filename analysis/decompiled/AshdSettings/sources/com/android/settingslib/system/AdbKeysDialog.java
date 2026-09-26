package com.android.settingslib.system;

import android.content.Context;
import android.hardware.usb.IUsbManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepFragment;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import android.util.Log;
import com.android.settingslib.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class AdbKeysDialog extends GuidedStepFragment {
    private static final String TAG = "AdbKeysDialog";

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    @NonNull
    public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
        return new GuidanceStylist.Guidance(getString(R.string.clear_adb_keys), getString(R.string.adb_keys_warning_message), null, null);
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    public void onCreateActions(@NonNull List<GuidedAction> list, Bundle bundle) {
        Context context = getContext();
        list.add(new GuidedAction.Builder(context).clickAction(-4L).build());
        list.add(new GuidedAction.Builder(context).clickAction(-5L).build());
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    public void onGuidedActionClicked(GuidedAction guidedAction) {
        if (guidedAction.getId() == -4) {
            try {
                IUsbManager.Stub.asInterface(ServiceManager.getService("usb")).clearUsbDebuggingKeys();
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to clear adb keys", e);
            }
            getFragmentManager().popBackStack();
            return;
        }
        getFragmentManager().popBackStack();
    }
}
