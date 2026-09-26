package com.android.settingslib.system;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepFragment;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import com.android.settingslib.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class OemUnlockDialog extends GuidedStepFragment {

    public interface Callback {
        void onOemUnlockConfirm();
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    @NonNull
    public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
        return new GuidanceStylist.Guidance(getString(R.string.confirm_enable_oem_unlock_title), getString(R.string.confirm_enable_oem_unlock_text), null, null);
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    public void onCreateActions(@NonNull List<GuidedAction> list, Bundle bundle) {
        Context context = getContext();
        list.add(new GuidedAction.Builder(context).id(-4L).title(getString(R.string.device_apps_app_management_enable)).build());
        list.add(new GuidedAction.Builder(context).clickAction(-5L).build());
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    public void onGuidedActionClicked(GuidedAction guidedAction) {
        if (guidedAction.getId() == -4) {
            ((Callback) getTargetFragment()).onOemUnlockConfirm();
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
