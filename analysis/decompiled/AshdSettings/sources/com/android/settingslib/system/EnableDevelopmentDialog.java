package com.android.settingslib.system;

import android.app.Activity;
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
public class EnableDevelopmentDialog extends GuidedStepFragment {

    public interface Callback {
        void onEnableDevelopmentConfirm();
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    @NonNull
    public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
        return new GuidanceStylist.Guidance(getString(R.string.dev_settings_warning_title), getString(R.string.dev_settings_warning_message), null, null);
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    public void onCreateActions(@NonNull List<GuidedAction> list, Bundle bundle) {
        Activity activity = getActivity();
        list.add(new GuidedAction.Builder(activity).clickAction(-8L).build());
        list.add(new GuidedAction.Builder(activity).clickAction(-9L).build());
    }

    @Override // android.support.v17.leanback.app.GuidedStepFragment
    public void onGuidedActionClicked(GuidedAction guidedAction) {
        if (guidedAction.getId() == -8) {
            ((Callback) getTargetFragment()).onEnableDevelopmentConfirm();
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
