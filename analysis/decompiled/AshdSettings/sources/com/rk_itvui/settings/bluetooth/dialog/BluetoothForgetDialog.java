package com.rk_itvui.settings.bluetooth.dialog;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;
import com.ashd.settings.R;
import com.rk_itvui.settings.bluetooth.BluetoothUtils;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothForgetDialog extends AlertActivity implements DialogInterface.OnClickListener {
    private BluetoothDevice mDevice;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDevice = (BluetoothDevice) getIntent().getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (this.mDevice == null) {
            finish();
        }
        setWindowSize();
        createDeviceForgetDialog();
    }

    private DisplayMetrics getMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    private int getScreenWidth(Context context) {
        return getMetrics(context).widthPixels;
    }

    private int getScreenHeight(Context context) {
        return getMetrics(context).heightPixels;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setWindowSize() {
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = getScreenWidth(this) / 4;
        attributes.height = getScreenHeight(this) / 5;
        attributes.gravity = 17;
        window.setAttributes(attributes);
    }

    private void createDeviceForgetDialog() {
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mTitle = "已配对设备：" + (this.mDevice.getName() == null ? this.mDevice.getAddress() : this.mDevice.getName());
        alertParams.mView = createDeviceForgetView();
        alertParams.mPositiveButtonText = "忘记";
        alertParams.mNegativeButtonText = "确定";
        alertParams.mPositiveButtonListener = this;
        alertParams.mNegativeButtonListener = this;
        setupAlert();
        Log.e("fixButtonStyle", "createDeviceForgetDialog");
    }

    private View createDeviceForgetView() {
        return getLayoutInflater().inflate(R.layout.bluetooth_forget_dialog, (ViewGroup) null);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case -2:
                finish();
                break;
            case -1:
                if (BluetoothUtils.isDeviceCanDisconnect(this.mDevice.getName())) {
                    SystemProperties.set("persist.sys.yyyklj", "false");
                    Log.d("BluetoothForgetDialog", "persist.sys.yyyklj=false");
                }
                this.mDevice.removeBond();
                break;
        }
    }
}
