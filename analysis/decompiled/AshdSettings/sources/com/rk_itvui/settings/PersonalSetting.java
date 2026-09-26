package com.rk_itvui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class PersonalSetting extends FullScreenActivity {
    Button dlna;
    private String mDlnaName = "eHomeMediaCenter";

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.mDlnaName = getApplicationContext().createPackageContext("com.rockchip.mediacenter", 2).getSharedPreferences("external", 3).getString("devicename", "eHomeMediaCenter");
        } catch (Exception unused) {
        }
        setContentView(com.ashd.settings.R.layout.personal_setting);
        this.dlna = getButton();
    }

    private void createTitle() {
        ImageView imageView = (ImageView) findViewById(com.ashd.settings.R.id.title_img);
        Bitmap bitmapBitMapScale = bitMapScale(com.ashd.settings.R.drawable.personal, ScreenInformation.mDpiRatio);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageBitmap(bitmapBitMapScale);
    }

    private Bitmap bitMapScale(int i, float f) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), i);
        float f2 = (ScreenInformation.mScreenWidth / 1280.0f) * f;
        return Bitmap.createScaledBitmap(bitmapDecodeResource, (int) (bitmapDecodeResource.getWidth() * f2), (int) (bitmapDecodeResource.getHeight() * f2), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Button getButton() {
        this.dlna = (Button) findViewById(com.ashd.settings.R.id.dlna_name);
        this.dlna.setText(this.mDlnaName);
        this.dlna.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, new BitmapDrawable(bitMapScale(com.ashd.settings.R.drawable.dlna_icon, 1.0f)), (Drawable) null, (Drawable) null);
        return this.dlna;
    }

    public void showSetMaxDialog() {
        final EditText editText = new EditText(this);
        new AlertDialog.Builder(this).setTitle(com.ashd.settings.R.string.btn_setting).setView(editText).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.PersonalSetting.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText.getText().toString().trim().equals("")) {
                    return;
                }
                PersonalSetting.this.mDlnaName = editText.getText().toString();
                PersonalSetting.this.getButton().setText(PersonalSetting.this.mDlnaName);
                Intent intent = new Intent();
                intent.setAction("com.rockchip.mediacenter.action.SystemDeviceService");
                intent.setPackage("com.rockchip.mediacenter");
                intent.putExtra("command", 6);
                intent.putExtra("friendlyname", PersonalSetting.this.mDlnaName);
                PersonalSetting.this.startService(intent);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.PersonalSetting.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).show();
    }

    public void onClick(View view) {
        if (view.getId() != com.ashd.settings.R.id.dlna_name) {
            return;
        }
        showSetMaxDialog();
    }
}
