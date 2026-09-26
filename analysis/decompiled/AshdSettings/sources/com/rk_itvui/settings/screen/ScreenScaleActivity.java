package com.rk_itvui.settings.screen;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.DisplayOutputManager;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.alibaba.fastjson.asm.Opcodes;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class ScreenScaleActivity extends Activity {
    private static final int MAX_SCALE = 100;
    private static final int MIN_SCALE = 80;
    public static final int SYSTEM_UI_FLAG_SHOW_FULLSCREEN = 4;
    private ImageView mDirctionButton;
    private ImageView mDownButton;
    private ImageView mLeftButton;
    private ImageView mRightButton;
    private ImageView mUpButton;
    private final int RightButtonPressed = 0;
    private final int LeftButttonPressed = 1;
    private final int UpButtonPressed = 2;
    private final int DownButtonPressed = 3;
    private final int RightButtonResume = 4;
    private final int LeftButtonResume = 5;
    private final int UpButtonResume = 6;
    private final int DownButtonResume = 7;
    private DisplayOutputManager mDisplayOutputManager = null;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private int mDensityDpi = 0;
    private int mDefaultDpi = Opcodes.IF_ICMPNE;
    private float mDpiRatio = 0.0f;
    private int mkeylast = -1;
    private View.OnClickListener mOnClick = new View.OnClickListener() { // from class: com.rk_itvui.settings.screen.ScreenScaleActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_down /* 2131361891 */:
                    ScreenScaleActivity.this.LOGD("touch_down");
                    if (ScreenScaleActivity.this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager = ScreenScaleActivity.this.mDisplayOutputManager;
                        ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                        int i = displayOutputManager.getOverScan(0).bottom + 1;
                        if (i > 100) {
                            i = 100;
                        }
                        if (i >= 0) {
                            DisplayOutputManager displayOutputManager2 = ScreenScaleActivity.this.mDisplayOutputManager;
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            displayOutputManager2.setOverScan(0, 1, i);
                        }
                    }
                    break;
                case R.id.button_left /* 2131361892 */:
                    ScreenScaleActivity.this.LOGD("button_left");
                    if (ScreenScaleActivity.this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager3 = ScreenScaleActivity.this.mDisplayOutputManager;
                        ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                        int i2 = displayOutputManager3.getOverScan(0).left - 1;
                        if (i2 < 80) {
                            i2 = 80;
                        }
                        if (i2 >= 0) {
                            DisplayOutputManager displayOutputManager4 = ScreenScaleActivity.this.mDisplayOutputManager;
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            displayOutputManager4.setOverScan(0, 0, i2);
                        }
                    }
                    break;
                case R.id.button_right /* 2131361894 */:
                    ScreenScaleActivity.this.LOGD("button_right");
                    if (ScreenScaleActivity.this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager5 = ScreenScaleActivity.this.mDisplayOutputManager;
                        ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                        int i3 = displayOutputManager5.getOverScan(0).right + 1;
                        if (i3 > 100) {
                            i3 = 100;
                        }
                        if (i3 >= 0) {
                            DisplayOutputManager displayOutputManager6 = ScreenScaleActivity.this.mDisplayOutputManager;
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            displayOutputManager6.setOverScan(0, 0, i3);
                        }
                    }
                    break;
                case R.id.button_up /* 2131361896 */:
                    ScreenScaleActivity.this.LOGD("touch_up");
                    if (ScreenScaleActivity.this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager7 = ScreenScaleActivity.this.mDisplayOutputManager;
                        ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                        int i4 = displayOutputManager7.getOverScan(0).top - 1;
                        if (i4 < 80) {
                            i4 = 80;
                        }
                        if (i4 >= 0) {
                            DisplayOutputManager displayOutputManager8 = ScreenScaleActivity.this.mDisplayOutputManager;
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            ScreenScaleActivity.this.mDisplayOutputManager.getClass();
                            displayOutputManager8.setOverScan(0, 1, i4);
                        }
                    }
                    break;
            }
        }
    };
    private Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.screen.ScreenScaleActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 4:
                    ScreenScaleActivity.this.mRightButton.setImageResource(R.drawable.button_vertical_right);
                    break;
                case 5:
                    ScreenScaleActivity.this.mLeftButton.setImageResource(R.drawable.button_vertical_left);
                    break;
                case 6:
                    ScreenScaleActivity.this.mUpButton.setImageResource(R.drawable.button_up);
                    break;
                case 7:
                    ScreenScaleActivity.this.mDownButton.setImageResource(R.drawable.button_down);
                    break;
            }
        }
    };

    private void getScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
        this.mDensityDpi = displayMetrics.densityDpi;
        this.mDpiRatio = this.mDefaultDpi / displayMetrics.densityDpi;
        Log.d("ScreenSettingActivity", "displayMetrics.densityDpi is: " + this.mDensityDpi);
        Log.d("ScreenSettingActivity", "displayMetrics.widthPixels is: " + this.mScreenWidth);
        Log.d("ScreenSettingActivity", "displayMetrics.heightPixels is: " + this.mScreenHeight);
    }

    private Bitmap bitMapScale(int i) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), i);
        float f = (this.mScreenWidth / 1280.0f) * this.mDpiRatio;
        return Bitmap.createScaledBitmap(bitmapDecodeResource, (int) (bitmapDecodeResource.getWidth() * f), (int) (bitmapDecodeResource.getHeight() * f), true);
    }

    private void createView() {
        int i = this.mScreenWidth / 2;
        int i2 = this.mScreenHeight / 5;
        ImageView imageView = (ImageView) findViewById(R.id.screen_touch_up);
        Bitmap bitmapBitMapScale = bitMapScale(R.drawable.screen_vertical_reduce);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(bitmapBitMapScale.getWidth(), bitmapBitMapScale.getHeight());
        layoutParams.addRule(10);
        layoutParams.setMargins(i - (bitmapBitMapScale.getWidth() / 2), i2, 0, 0);
        int height = i2 + bitmapBitMapScale.getHeight() + 10;
        imageView.setLayoutParams(layoutParams);
        this.mUpButton = (ImageView) findViewById(R.id.button_up);
        Bitmap bitmapBitMapScale2 = bitMapScale(R.drawable.button_vertical_up);
        int height2 = height + bitmapBitMapScale2.getHeight() + 10;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(bitmapBitMapScale2.getWidth(), bitmapBitMapScale2.getHeight());
        layoutParams2.addRule(3, R.id.screen_touch_up);
        layoutParams2.setMargins(i - (bitmapBitMapScale2.getWidth() / 2), 10, 0, 0);
        this.mUpButton.setLayoutParams(layoutParams2);
        ImageView imageView2 = (ImageView) findViewById(R.id.button_ok);
        Bitmap bitmapBitMapScale3 = bitMapScale(R.drawable.ok);
        int height3 = height2 + (bitmapBitMapScale3.getHeight() / 2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(bitmapBitMapScale3.getWidth(), bitmapBitMapScale3.getHeight());
        layoutParams3.addRule(3, R.id.button_up);
        layoutParams3.setMargins(i - (bitmapBitMapScale3.getWidth() / 2), 10, 0, 0);
        imageView2.setLayoutParams(layoutParams3);
        this.mDownButton = (ImageView) findViewById(R.id.button_down);
        Bitmap bitmapBitMapScale4 = bitMapScale(R.drawable.button_vertical_down);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(bitmapBitMapScale4.getWidth(), bitmapBitMapScale4.getHeight());
        layoutParams4.addRule(3, R.id.button_ok);
        layoutParams4.setMargins(i - (bitmapBitMapScale4.getWidth() / 2), 10, 0, 0);
        this.mDownButton.setLayoutParams(layoutParams4);
        ImageView imageView3 = (ImageView) findViewById(R.id.screen_touch_down);
        Bitmap bitmapBitMapScale5 = bitMapScale(R.drawable.screen_vertical_add);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(bitmapBitMapScale5.getWidth(), bitmapBitMapScale5.getHeight());
        layoutParams5.addRule(3, R.id.button_down);
        layoutParams5.setMargins(i - (bitmapBitMapScale5.getWidth() / 2), 10, 0, 0);
        imageView3.setLayoutParams(layoutParams5);
        this.mLeftButton = (ImageView) findViewById(R.id.button_left);
        Bitmap bitmapBitMapScale6 = bitMapScale(R.drawable.button_left);
        int width = (((-bitmapBitMapScale3.getWidth()) / 2) - 10) - (bitmapBitMapScale6.getWidth() / 2);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(bitmapBitMapScale6.getWidth(), bitmapBitMapScale6.getHeight());
        layoutParams6.addRule(5, R.id.button_ok);
        layoutParams6.setMargins(width, height3 - (bitmapBitMapScale6.getHeight() / 2), 0, 0);
        this.mLeftButton.setLayoutParams(layoutParams6);
        ImageView imageView4 = (ImageView) findViewById(R.id.screen_button_left);
        Bitmap bitmapBitMapScale7 = bitMapScale(R.drawable.screen_horizontal_reduce);
        int i3 = width + (((-bitmapBitMapScale7.getWidth()) / 2) - 10);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(bitmapBitMapScale7.getWidth(), bitmapBitMapScale7.getHeight());
        layoutParams7.addRule(5, R.id.button_left);
        layoutParams7.setMargins(i3, height3 - (bitmapBitMapScale7.getHeight() / 2), 0, 0);
        imageView4.setLayoutParams(layoutParams7);
        this.mRightButton = (ImageView) findViewById(R.id.button_right);
        Bitmap bitmapBitMapScale8 = bitMapScale(R.drawable.button_right);
        int width2 = (bitmapBitMapScale3.getWidth() / 2) + 10 + (bitmapBitMapScale8.getWidth() / 2);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(bitmapBitMapScale8.getWidth(), bitmapBitMapScale8.getHeight());
        layoutParams8.addRule(7, R.id.button_ok);
        layoutParams8.setMargins(0, height3 - (bitmapBitMapScale8.getHeight() / 2), -width2, 0);
        this.mRightButton.setLayoutParams(layoutParams8);
        ImageView imageView5 = (ImageView) findViewById(R.id.screen_button_right);
        Bitmap bitmapBitMapScale9 = bitMapScale(R.drawable.screen_horizontal_add);
        int width3 = width2 + (bitmapBitMapScale9.getWidth() / 2) + 10;
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(bitmapBitMapScale9.getWidth(), bitmapBitMapScale9.getHeight());
        layoutParams9.addRule(7, R.id.button_right);
        layoutParams9.setMargins(0, height3 - (bitmapBitMapScale9.getHeight() / 2), -width3, 0);
        imageView5.setLayoutParams(layoutParams9);
        this.mRightButton.setOnClickListener(this.mOnClick);
        this.mLeftButton.setOnClickListener(this.mOnClick);
        this.mUpButton.setOnClickListener(this.mOnClick);
        this.mDownButton.setOnClickListener(this.mOnClick);
    }

    @TargetApi(19)
    private void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(AudioFormat.OPUS, AudioFormat.OPUS);
            getWindow().setFlags(67108864, 67108864);
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideSystemUI();
        setContentView(R.layout.screen_setting);
        getWindow().setFormat(1);
        getScreenSize();
        createView();
        try {
            this.mDisplayOutputManager = new DisplayOutputManager();
        } catch (RemoteException unused) {
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        LOGD("keyCode = " + keyCode);
        if (this.mkeylast != keyCode) {
            this.mkeylast = keyCode;
            switch (keyCode) {
                case 19:
                    this.mHandler.removeMessages(6);
                    this.mUpButton.setImageResource(R.drawable.button_vertical_up_pressed);
                    if (this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager = this.mDisplayOutputManager;
                        this.mDisplayOutputManager.getClass();
                        int i = displayOutputManager.getOverScan(0).top - 1;
                        if (i < 80) {
                            i = 80;
                        }
                        if (i >= 0) {
                            DisplayOutputManager displayOutputManager2 = this.mDisplayOutputManager;
                            this.mDisplayOutputManager.getClass();
                            this.mDisplayOutputManager.getClass();
                            displayOutputManager2.setOverScan(0, 1, i);
                        }
                    }
                    this.mHandler.sendEmptyMessageDelayed(6, 100L);
                    break;
                case 20:
                    this.mHandler.removeMessages(7);
                    this.mDownButton.setImageResource(R.drawable.button_vertical_down_pressed);
                    if (this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager3 = this.mDisplayOutputManager;
                        this.mDisplayOutputManager.getClass();
                        int i2 = displayOutputManager3.getOverScan(0).bottom + 1;
                        if (i2 > 100) {
                            i2 = 100;
                        }
                        if (i2 >= 0) {
                            DisplayOutputManager displayOutputManager4 = this.mDisplayOutputManager;
                            this.mDisplayOutputManager.getClass();
                            this.mDisplayOutputManager.getClass();
                            displayOutputManager4.setOverScan(0, 1, i2);
                        }
                    }
                    this.mHandler.sendEmptyMessageDelayed(7, 100L);
                    break;
                case 21:
                    this.mHandler.removeMessages(5);
                    this.mLeftButton.setImageResource(R.drawable.button_left_pressed);
                    if (this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager5 = this.mDisplayOutputManager;
                        this.mDisplayOutputManager.getClass();
                        int i3 = displayOutputManager5.getOverScan(0).left - 1;
                        if (i3 < 80) {
                            i3 = 80;
                        }
                        if (i3 >= 0) {
                            DisplayOutputManager displayOutputManager6 = this.mDisplayOutputManager;
                            this.mDisplayOutputManager.getClass();
                            this.mDisplayOutputManager.getClass();
                            displayOutputManager6.setOverScan(0, 0, i3);
                        }
                    }
                    this.mHandler.sendEmptyMessageDelayed(5, 100L);
                    break;
                case 22:
                    this.mRightButton.setImageResource(R.drawable.button_right_pressed);
                    this.mHandler.removeMessages(4);
                    if (this.mDisplayOutputManager != null) {
                        DisplayOutputManager displayOutputManager7 = this.mDisplayOutputManager;
                        this.mDisplayOutputManager.getClass();
                        int i4 = displayOutputManager7.getOverScan(0).right + 1;
                        if (i4 > 100) {
                            i4 = 100;
                        }
                        if (i4 >= 0) {
                            DisplayOutputManager displayOutputManager8 = this.mDisplayOutputManager;
                            this.mDisplayOutputManager.getClass();
                            this.mDisplayOutputManager.getClass();
                            displayOutputManager8.setOverScan(0, 0, i4);
                        }
                    }
                    this.mHandler.sendEmptyMessageDelayed(4, 100L);
                    break;
            }
        } else {
            this.mkeylast = -1;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LOGD(String str) {
        Log.d("ScreenSettingActivity", str);
    }
}
