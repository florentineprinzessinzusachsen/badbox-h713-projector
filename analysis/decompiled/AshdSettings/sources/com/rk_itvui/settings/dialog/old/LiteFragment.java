package com.rk_itvui.settings.dialog.old;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public interface LiteFragment {
    Activity getActivity();

    Bundle getArguments();

    Resources getResources();

    View getView();

    boolean isAdded();

    void startActivity(Intent intent);
}
