package com.rk_itvui.settings.network.wifi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.ashd.settings.R;
import com.rk_itvui.settings.Utils;

/* JADX INFO: loaded from: classes.dex */
class WifiDialog extends AlertDialog implements WifiConfigUiBase {
    static final int BUTTON_FORGET = -3;
    static final int BUTTON_SUBMIT = -1;
    private final AccessPoint mAccessPoint;
    private final boolean mEdit;
    private boolean mHideSubmitButton;
    private final DialogInterface.OnClickListener mListener;
    private View mView;

    public WifiDialog(Context context, DialogInterface.OnClickListener onClickListener, AccessPoint accessPoint, boolean z, boolean z2) {
        this(context, onClickListener, accessPoint, z);
        this.mHideSubmitButton = z2;
    }

    public WifiDialog(Context context, DialogInterface.OnClickListener onClickListener, AccessPoint accessPoint, boolean z) {
        super(context);
        this.mEdit = z;
        this.mListener = onClickListener;
        this.mAccessPoint = accessPoint;
        this.mHideSubmitButton = false;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        this.mView = getLayoutInflater().inflate(R.layout.wifi_dialog, (ViewGroup) null);
        setView(this.mView);
        setInverseBackgroundForced(true);
        super.onCreate(bundle);
        Utils.fixButtonStyle(this);
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public boolean isEdit() {
        return this.mEdit;
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public Button getSubmitButton() {
        return getButton(-1);
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public Button getForgetButton() {
        return getButton(-3);
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public Button getCancelButton() {
        return getButton(-2);
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public void setSubmitButton(CharSequence charSequence) {
        setButton(-1, charSequence, this.mListener);
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public void setForgetButton(CharSequence charSequence) {
        setButton(-3, charSequence, this.mListener);
    }

    @Override // com.rk_itvui.settings.network.wifi.WifiConfigUiBase
    public void setCancelButton(CharSequence charSequence) {
        setButton(-2, charSequence, this.mListener);
    }
}
