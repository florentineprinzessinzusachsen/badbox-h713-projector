package com.blankj.utilcode.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import androidx.core.content.a;
import com.blankj.utilcode.constant.PermissionConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class PermissionUtils {
    private static final List<String> PERMISSIONS = getPermissions();
    private static PermissionUtils sInstance;
    private static SimpleCallback sSimpleCallback4DrawOverlays;
    private static SimpleCallback sSimpleCallback4WriteSettings;
    private FullCallback mFullCallback;
    private OnRationaleListener mOnRationaleListener;
    private Set<String> mPermissions = new LinkedHashSet();
    private List<String> mPermissionsDenied;
    private List<String> mPermissionsDeniedForever;
    private List<String> mPermissionsGranted;
    private List<String> mPermissionsRequest;
    private SimpleCallback mSimpleCallback;
    private ThemeCallback mThemeCallback;

    public interface FullCallback {
        void onDenied(List<String> list, List<String> list2);

        void onGranted(List<String> list);
    }

    public interface OnRationaleListener {

        public interface ShouldRequest {
            void again(boolean z);
        }

        void rationale(ShouldRequest shouldRequest);
    }

    public static class PermissionActivity extends Activity {
        private static final String TYPE = "TYPE";
        public static final int TYPE_DRAW_OVERLAYS = 3;
        public static final int TYPE_RUNTIME = 1;
        public static final int TYPE_WRITE_SETTINGS = 2;

        public static void start(Context context, int i) {
            Intent intent = new Intent(context, (Class<?>) PermissionActivity.class);
            intent.addFlags(268435456);
            intent.putExtra(TYPE, i);
            context.startActivity(intent);
        }

        @Override // android.app.Activity, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            finish();
            return true;
        }

        @Override // android.app.Activity
        protected void onActivityResult(int i, int i2, Intent intent) {
            if (i == 2) {
                if (PermissionUtils.sSimpleCallback4WriteSettings == null) {
                    return;
                }
                if (PermissionUtils.isGrantedWriteSettings()) {
                    PermissionUtils.sSimpleCallback4WriteSettings.onGranted();
                } else {
                    PermissionUtils.sSimpleCallback4WriteSettings.onDenied();
                }
                SimpleCallback unused = PermissionUtils.sSimpleCallback4WriteSettings = null;
            } else if (i == 3) {
                if (PermissionUtils.sSimpleCallback4DrawOverlays == null) {
                    return;
                } else {
                    Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.PermissionUtils.PermissionActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PermissionUtils.isGrantedDrawOverlays()) {
                                PermissionUtils.sSimpleCallback4DrawOverlays.onGranted();
                            } else {
                                PermissionUtils.sSimpleCallback4DrawOverlays.onDenied();
                            }
                            SimpleCallback unused2 = PermissionUtils.sSimpleCallback4DrawOverlays = null;
                        }
                    }, 100L);
                }
            }
            finish();
        }

        @Override // android.app.Activity
        protected void onCreate(Bundle bundle) {
            getWindow().addFlags(262160);
            int intExtra = getIntent().getIntExtra(TYPE, 1);
            if (intExtra != 1) {
                if (intExtra == 2) {
                    super.onCreate(bundle);
                    PermissionUtils.startWriteSettingsActivity(this, 2);
                    return;
                } else {
                    if (intExtra == 3) {
                        super.onCreate(bundle);
                        PermissionUtils.startOverlayPermissionActivity(this, 3);
                        return;
                    }
                    return;
                }
            }
            if (PermissionUtils.sInstance == null) {
                super.onCreate(bundle);
                Log.e("PermissionUtils", "request permissions failed");
                finish();
                return;
            }
            if (PermissionUtils.sInstance.mThemeCallback != null) {
                PermissionUtils.sInstance.mThemeCallback.onActivityCreate(this);
            }
            super.onCreate(bundle);
            if (PermissionUtils.sInstance.rationale(this) || PermissionUtils.sInstance.mPermissionsRequest == null) {
                return;
            }
            int size = PermissionUtils.sInstance.mPermissionsRequest.size();
            if (size <= 0) {
                finish();
            } else {
                requestPermissions((String[]) PermissionUtils.sInstance.mPermissionsRequest.toArray(new String[size]), 1);
            }
        }

        @Override // android.app.Activity
        public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
            if (strArr == null) {
                throw new NullPointerException("Argument 'permissions' of type String[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            if (iArr == null) {
                throw new NullPointerException("Argument 'grantResults' of type int[] (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            if (PermissionUtils.sInstance != null && PermissionUtils.sInstance.mPermissionsRequest != null) {
                PermissionUtils.sInstance.onRequestPermissionsResult(this);
            }
            finish();
        }
    }

    public interface SimpleCallback {
        void onDenied();

        void onGranted();
    }

    public interface ThemeCallback {
        void onActivityCreate(Activity activity);
    }

    private PermissionUtils(String... strArr) {
        for (String str : strArr) {
            for (String str2 : PermissionConstants.getPermissions(str)) {
                if (PERMISSIONS.contains(str2)) {
                    this.mPermissions.add(str2);
                }
            }
        }
        sInstance = this;
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    private void getPermissionsStatus(Activity activity) {
        for (String str : this.mPermissionsRequest) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsDenied.add(str);
                if (!activity.shouldShowRequestPermissionRationale(str)) {
                    this.mPermissionsDeniedForever.add(str);
                }
            }
        }
    }

    public static boolean isGranted(String... strArr) {
        for (String str : strArr) {
            if (!isGranted(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGrantedDrawOverlays() {
        return Settings.canDrawOverlays(Utils.getApp());
    }

    public static boolean isGrantedWriteSettings() {
        return Settings.System.canWrite(Utils.getApp());
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (isIntentAvailable(intent)) {
            Utils.getApp().startActivity(intent.addFlags(268435456));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestPermissionsResult(Activity activity) {
        getPermissionsStatus(activity);
        requestCallback();
    }

    public static PermissionUtils permission(String... strArr) {
        return new PermissionUtils(strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestCallback() {
        if (this.mSimpleCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mSimpleCallback.onGranted();
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mSimpleCallback.onDenied();
            }
            this.mSimpleCallback = null;
        }
        if (this.mFullCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mFullCallback.onGranted(this.mPermissionsGranted);
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mFullCallback.onDenied(this.mPermissionsDeniedForever, this.mPermissionsDenied);
            }
            this.mFullCallback = null;
        }
        this.mOnRationaleListener = null;
        this.mThemeCallback = null;
    }

    public static void requestDrawOverlays(SimpleCallback simpleCallback) {
        if (!isGrantedDrawOverlays()) {
            sSimpleCallback4DrawOverlays = simpleCallback;
            PermissionActivity.start(Utils.getApp(), 3);
        } else if (simpleCallback != null) {
            simpleCallback.onGranted();
        }
    }

    public static void requestWriteSettings(SimpleCallback simpleCallback) {
        if (!isGrantedWriteSettings()) {
            sSimpleCallback4WriteSettings = simpleCallback;
            PermissionActivity.start(Utils.getApp(), 2);
        } else if (simpleCallback != null) {
            simpleCallback.onGranted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    public static void startOverlayPermissionActivity(Activity activity, int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (isIntentAvailable(intent)) {
            activity.startActivityForResult(intent, i);
        } else {
            launchAppDetailsSettings();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPermissionActivity() {
        this.mPermissionsDenied = new ArrayList();
        this.mPermissionsDeniedForever = new ArrayList();
        PermissionActivity.start(Utils.getApp(), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(23)
    public static void startWriteSettingsActivity(Activity activity, int i) {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (isIntentAvailable(intent)) {
            activity.startActivityForResult(intent, i);
        } else {
            launchAppDetailsSettings();
        }
    }

    public PermissionUtils callback(SimpleCallback simpleCallback) {
        this.mSimpleCallback = simpleCallback;
        return this;
    }

    public PermissionUtils rationale(OnRationaleListener onRationaleListener) {
        this.mOnRationaleListener = onRationaleListener;
        return this;
    }

    public void request() {
        this.mPermissionsGranted = new ArrayList();
        this.mPermissionsRequest = new ArrayList();
        if (Build.VERSION.SDK_INT < 23) {
            this.mPermissionsGranted.addAll(this.mPermissions);
            requestCallback();
            return;
        }
        for (String str : this.mPermissions) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsRequest.add(str);
            }
        }
        if (this.mPermissionsRequest.isEmpty()) {
            requestCallback();
        } else {
            startPermissionActivity();
        }
    }

    public PermissionUtils theme(ThemeCallback themeCallback) {
        this.mThemeCallback = themeCallback;
        return this;
    }

    public static List<String> getPermissions(String str) {
        try {
            String[] strArr = Utils.getApp().getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
            return strArr == null ? Collections.emptyList() : Arrays.asList(strArr);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean rationale(final Activity activity) {
        boolean z = false;
        if (this.mOnRationaleListener != null) {
            Iterator<String> it = this.mPermissionsRequest.iterator();
            while (it.hasNext()) {
                if (activity.shouldShowRequestPermissionRationale(it.next())) {
                    getPermissionsStatus(activity);
                    this.mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest() { // from class: com.blankj.utilcode.util.PermissionUtils.1
                        @Override // com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest
                        public void again(boolean z2) {
                            activity.finish();
                            if (z2) {
                                PermissionUtils.this.startPermissionActivity();
                            } else {
                                PermissionUtils.this.requestCallback();
                            }
                        }
                    });
                    z = true;
                    break;
                }
            }
            this.mOnRationaleListener = null;
        }
        return z;
    }

    public PermissionUtils callback(FullCallback fullCallback) {
        this.mFullCallback = fullCallback;
        return this;
    }

    private static boolean isGranted(String str) {
        return Build.VERSION.SDK_INT < 23 || a.a(Utils.getApp(), str) == 0;
    }
}
