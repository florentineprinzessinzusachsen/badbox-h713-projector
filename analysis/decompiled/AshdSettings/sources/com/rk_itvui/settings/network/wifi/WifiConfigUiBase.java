package com.rk_itvui.settings.network.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public interface WifiConfigUiBase {
    Button getCancelButton();

    Context getContext();

    Button getForgetButton();

    LayoutInflater getLayoutInflater();

    Button getSubmitButton();

    boolean isEdit();

    void setCancelButton(CharSequence charSequence);

    void setForgetButton(CharSequence charSequence);

    void setSubmitButton(CharSequence charSequence);

    void setTitle(int i);

    void setTitle(CharSequence charSequence);
}
