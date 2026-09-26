package com.android.settingslib.system;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.AppOpsManager;
import android.app.admin.DevicePolicyManager;
import android.app.backup.IBackupManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbManager;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserManager;
import android.provider.Settings;
import android.service.persistentdata.PersistentDataBlockManager;
import android.support.v14.preference.SwitchPreference;
import android.support.v17.preference.LeanbackPreferenceFragment;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceGroup;
import android.support.v7.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.app.LocalePicker;
import com.android.settingslib.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DevelopmentFragment extends LeanbackPreferenceFragment implements Preference.OnPreferenceChangeListener, EnableDevelopmentDialog.Callback, OemUnlockDialog.Callback, AdbDialog.Callback {
    private static final String ANIMATOR_DURATION_SCALE_KEY = "animator_duration_scale";
    private static final String APP_PROCESS_LIMIT_KEY = "app_process_limit";
    private static final String BT_HCI_SNOOP_LOG = "bt_hci_snoop_log";
    private static final String BUGREPORT = "bugreport";
    private static final String BUGREPORT_IN_POWER_KEY = "bugreport_in_power";
    private static final String CLEAR_ADB_KEYS = "clear_adb_keys";
    private static final String DEBUG_APP_KEY = "debug_app";
    private static final String DEBUG_DEBUGGING_CATEGORY_KEY = "debug_debugging_category";
    private static final String DEBUG_HW_OVERDRAW_KEY = "debug_hw_overdraw";
    private static final String DEBUG_LAYOUT_KEY = "debug_layout";
    private static final String DEBUG_VIEW_ATTRIBUTES = "debug_view_attributes";
    private static String DEFAULT_LOG_RING_BUFFER_SIZE_IN_BYTES = "262144";
    private static final String DISABLE_OVERLAYS_KEY = "disable_overlays";
    private static final String ENABLE_ABC = "enable_abc";
    private static final String ENABLE_ADB = "enable_adb";
    private static final String ENABLE_DEVELOPER = "development_settings_enable";
    private static final String ENABLE_INTERNET_ADB = "enable_internet_adb";
    private static final String ENABLE_OEM_UNLOCK = "oem_unlock_enable";
    private static final String ENABLE_TERMINAL = "enable_terminal";
    private static final String ENABLE_USB = "enable_usb";
    private static final String FORCE_ALLOW_ON_EXTERNAL_KEY = "force_allow_on_external";
    private static final String FORCE_HARDWARE_UI_KEY = "force_hw_ui";
    private static final String FORCE_MSAA_KEY = "force_msaa";
    private static final String FORCE_RESIZABLE_KEY = "force_resizable_activities";
    private static final String FORCE_RTL_LAYOUT_KEY = "force_rtl_layout_all_locales";
    private static final String HARDWARE_UI_PROPERTY = "persist.sys.ui.hw";
    private static final String HDCP_CHECKING_KEY = "hdcp_checking";
    private static final String HDCP_CHECKING_PROPERTY = "persist.sys.hdcp_checking";
    private static final String IMMEDIATELY_DESTROY_ACTIVITIES_KEY = "immediately_destroy_activities";
    private static final String INACTIVE_APPS_KEY = "inactive_apps";
    private static final String KEEP_SCREEN_ON = "keep_screen_on";
    private static final String KEY_COLOR_MODE = "color_mode";
    private static final String KEY_CONVERT_FBE = "convert_to_file_encryption";
    private static final String LOCAL_BACKUP_PASSWORD = "local_backup_password";
    private static final String MOBILE_DATA_ALWAYS_ON = "mobile_data_always_on";
    private static final String MOCK_LOCATION_APP_KEY = "mock_location_app";
    private static final int[] MOCK_LOCATION_APP_OPS = {58};
    private static final String MSAA_PROPERTY = "debug.egl.force_msaa";
    private static final String OPENGL_TRACES_KEY = "enable_opengl_traces";
    private static final String OPENGL_TRACES_PROPERTY = "debug.egl.trace";
    private static final String OVERLAY_DISPLAY_DEVICES_KEY = "overlay_display_devices";
    private static final String PACKAGE_MIME_TYPE = "application/vnd.android.package-archive";
    private static final String PERSISTENT_DATA_BLOCK_PROP = "ro.frp.pst";
    private static final String PERSIST_RK_ABC_SWITCH = "persist.rk.abc_switch";
    private static final String POINTER_LOCATION_KEY = "pointer_location";
    private static final int RESULT_DEBUG_APP = 1000;
    private static final int RESULT_MOCK_LOCATION_APP = 1001;
    private static final String RUNNING_APPS = "running_apps";
    private static final String SELECT_LOGD_DEFAULT_SIZE_PROPERTY = "ro.logd.size";
    private static final String SELECT_LOGD_SIZE_KEY = "select_logd_size";
    private static final String SELECT_LOGD_SIZE_PROPERTY = "persist.logd.size";
    private static final String SHOW_ALL_ANRS_KEY = "show_all_anrs";
    private static final String SHOW_HW_LAYERS_UPDATES_KEY = "show_hw_layers_udpates";
    private static final String SHOW_HW_SCREEN_UPDATES_KEY = "show_hw_screen_udpates";
    private static final String SHOW_NON_RECTANGULAR_CLIP_KEY = "show_non_rect_clip";
    private static final String SHOW_SCREEN_UPDATES_KEY = "show_screen_updates";
    private static final String SHOW_TOUCHES_KEY = "show_touches";
    private static final String SIMULATE_COLOR_SPACE = "simulate_color_space";
    private static final String STRICT_MODE_KEY = "strict_mode";
    private static final String TAG = "DevelopmentSettings";
    private static final String TERMINAL_APP_PACKAGE = "com.android.terminal";
    private static final String TRACK_FRAME_TIME_KEY = "track_frame_time";
    private static final String TRANSITION_ANIMATION_SCALE_KEY = "transition_animation_scale";
    private static final String USB_AUDIO_KEY = "usb_audio";
    private static final String USB_CONFIGURATION_KEY = "select_usb_configuration";
    private static final String VERIFY_APPS_OVER_USB_KEY = "verify_apps_over_usb";
    private static final String WAIT_FOR_DEBUGGER_KEY = "wait_for_debugger";
    private static final String WIFI_AGGRESSIVE_HANDOVER_KEY = "wifi_aggressive_handover";
    private static final String WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY = "wifi_allow_scan_with_traffic";
    private static final String WIFI_DISPLAY_CERTIFICATION_KEY = "wifi_display_certification";
    private static final String WIFI_VERBOSE_LOGGING_KEY = "wifi_verbose_logging";
    private static final String WINDOW_ANIMATION_SCALE_KEY = "window_animation_scale";
    private ListPreference mAnimatorDurationScale;
    private ListPreference mAppProcessLimit;
    private IBackupManager mBackupManager;
    private SwitchPreference mBtHciSnoopLog;
    private Preference mBugreport;
    private SwitchPreference mBugreportInPower;
    private Preference mClearAdbKeys;
    private ColorModePreference mColorModePreference;
    private ContentResolver mContentResolver;
    private String mDebugApp;
    private Preference mDebugAppPref;
    private ListPreference mDebugHwOverdraw;
    private SwitchPreference mDebugLayout;
    private SwitchPreference mDebugViewAttributes;
    private SwitchPreference mDisableOverlays;
    private boolean mDontPokeProperties;
    private DevicePolicyManager mDpm;
    private SwitchPreference mEnableAbc;
    private SwitchPreference mEnableAdb;
    private SwitchPreference mEnableDeveloper;
    private SwitchPreference mEnableInternetAdb;
    private SwitchPreference mEnableOemUnlock;
    private SwitchPreference mEnableTerminal;
    private SwitchPreference mEnableUsb;
    private SwitchPreference mForceAllowOnExternal;
    private SwitchPreference mForceHardwareUi;
    private SwitchPreference mForceMsaa;
    private SwitchPreference mForceResizable;
    private SwitchPreference mForceRtlLayout;
    private boolean mHaveDebugSettings;
    private SwitchPreference mImmediatelyDestroyActivities;
    private SwitchPreference mKeepScreenOn;
    private boolean mLastEnabledState;
    private ListPreference mLogdSize;
    private SwitchPreference mMobileDataAlwaysOn;
    private String mMockLocationApp;
    private Preference mMockLocationAppPref;
    private ListPreference mOpenGLTraces;
    private ListPreference mOverlayDisplayDevices;
    private PreferenceScreen mPassword;
    private SwitchPreference mPointerLocation;
    private SwitchPreference mShowAllANRs;
    private SwitchPreference mShowHwLayersUpdates;
    private SwitchPreference mShowHwScreenUpdates;
    private ListPreference mShowNonRectClip;
    private SwitchPreference mShowScreenUpdates;
    private SwitchPreference mShowTouches;
    private ListPreference mSimulateColorSpace;
    private SwitchPreference mStrictMode;
    private ListPreference mTrackFrameTime;
    private ListPreference mTransitionAnimationScale;
    private SwitchPreference mUSBAudio;
    private UserManager mUm;
    private boolean mUnavailable;
    private ListPreference mUsbConfiguration;
    private SwitchPreference mVerifyAppsOverUsb;
    private SwitchPreference mWaitForDebugger;
    private SwitchPreference mWifiAggressiveHandover;
    private SwitchPreference mWifiAllowScansWithTraffic;
    private SwitchPreference mWifiDisplayCertification;
    private WifiManager mWifiManager;
    private SwitchPreference mWifiVerboseLogging;
    private ListPreference mWindowAnimationScale;
    private IWindowManager mWindowManager;
    private final ArrayList<Preference> mAllPrefs = new ArrayList<>();
    private final ArrayList<SwitchPreference> mResetSwitchPrefs = new ArrayList<>();
    private final HashSet<Preference> mDisabledPrefs = new HashSet<>();
    private UsbModeSettings mUsbModeSetting = null;
    private BroadcastReceiver mUsbReceiver = new BroadcastReceiver() { // from class: com.android.settingslib.system.DevelopmentFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DevelopmentFragment.this.updateUsbConfigurationValues();
        }
    };

    public static DevelopmentFragment newInstance() {
        return new DevelopmentFragment();
    }

    @Override // android.support.v14.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        this.mWindowManager = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));
        this.mBackupManager = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
        this.mDpm = (DevicePolicyManager) getActivity().getSystemService("device_policy");
        this.mUm = (UserManager) getActivity().getSystemService("user");
        this.mWifiManager = (WifiManager) getActivity().getSystemService("wifi");
        this.mContentResolver = getActivity().getContentResolver();
        super.onCreate(bundle);
        if (this.mUnavailable) {
            return;
        }
        this.mUsbModeSetting = new UsbModeSettings(getPreferenceManager().getContext());
        this.mEnableUsb.setChecked(this.mUsbModeSetting.getDefaultValue());
        if (this.mEnableUsb.isChecked()) {
            this.mEnableUsb.setSummary(R.string.usb_connect_to_computer);
        } else {
            this.mEnableUsb.setSummary(R.string.usb_disconnect_to_computer);
        }
        if (SystemProperties.get("persist.internet.adb.enable", "0").equals("1")) {
            this.mEnableInternetAdb.setChecked(true);
        } else {
            this.mEnableInternetAdb.setChecked(false);
        }
    }

    @Override // android.support.v14.preference.PreferenceFragment
    public void onCreatePreferences(Bundle bundle, String str) {
        getPreferenceManager().getContext();
        if (!this.mUm.isAdminUser() || this.mUm.hasUserRestriction("no_debugging_features") || Settings.Global.getInt(this.mContentResolver, "device_provisioned", 0) == 0) {
            this.mUnavailable = true;
            addPreferencesFromResource(R.xml.development_prefs_empty);
            return;
        }
        addPreferencesFromResource(R.xml.development_prefs);
        this.mEnableDeveloper = (SwitchPreference) findPreference(ENABLE_DEVELOPER);
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference(DEBUG_DEBUGGING_CATEGORY_KEY);
        this.mEnableAdb = findAndInitSwitchPref(ENABLE_ADB);
        this.mEnableUsb = findAndInitSwitchPref(ENABLE_USB);
        this.mEnableInternetAdb = findAndInitSwitchPref(ENABLE_INTERNET_ADB);
        this.mEnableAbc = findAndInitSwitchPref(ENABLE_ABC);
        this.mClearAdbKeys = findPreference(CLEAR_ADB_KEYS);
        if (!SystemProperties.getBoolean("ro.adb.secure", false) && preferenceGroup != null) {
            preferenceGroup.removePreference(this.mClearAdbKeys);
        }
        this.mAllPrefs.add(this.mClearAdbKeys);
        this.mEnableTerminal = findAndInitSwitchPref(ENABLE_TERMINAL);
        if (!isPackageInstalled(getActivity(), TERMINAL_APP_PACKAGE)) {
            if (preferenceGroup != null) {
                preferenceGroup.removePreference(this.mEnableTerminal);
            }
            this.mEnableTerminal = null;
        }
        this.mBugreport = findPreference(BUGREPORT);
        this.mBugreportInPower = findAndInitSwitchPref(BUGREPORT_IN_POWER_KEY);
        removePreference(BUGREPORT_IN_POWER_KEY);
        this.mKeepScreenOn = findAndInitSwitchPref(KEEP_SCREEN_ON);
        this.mBtHciSnoopLog = findAndInitSwitchPref(BT_HCI_SNOOP_LOG);
        this.mEnableOemUnlock = findAndInitSwitchPref(ENABLE_OEM_UNLOCK);
        if (!showEnableOemUnlockPreference()) {
            removePreference(this.mEnableOemUnlock);
            this.mEnableOemUnlock = null;
        }
        removePreference(RUNNING_APPS);
        this.mDebugViewAttributes = findAndInitSwitchPref(DEBUG_VIEW_ATTRIBUTES);
        this.mForceAllowOnExternal = findAndInitSwitchPref(FORCE_ALLOW_ON_EXTERNAL_KEY);
        this.mPassword = (PreferenceScreen) findPreference(LOCAL_BACKUP_PASSWORD);
        this.mPassword.setVisible(false);
        this.mAllPrefs.add(this.mPassword);
        if (!this.mUm.isAdminUser()) {
            disableForUser(this.mEnableAdb);
            disableForUser(this.mEnableUsb);
            disableForUser(this.mEnableInternetAdb);
            disableForUser(this.mEnableAbc);
            disableForUser(this.mClearAdbKeys);
            disableForUser(this.mEnableTerminal);
            disableForUser(this.mPassword);
        }
        this.mDebugAppPref = findPreference(DEBUG_APP_KEY);
        this.mAllPrefs.add(this.mDebugAppPref);
        this.mWaitForDebugger = findAndInitSwitchPref(WAIT_FOR_DEBUGGER_KEY);
        this.mMockLocationAppPref = findPreference(MOCK_LOCATION_APP_KEY);
        this.mAllPrefs.add(this.mMockLocationAppPref);
        this.mVerifyAppsOverUsb = findAndInitSwitchPref(VERIFY_APPS_OVER_USB_KEY);
        if (!showVerifierSetting()) {
            if (preferenceGroup != null) {
                preferenceGroup.removePreference(this.mVerifyAppsOverUsb);
            } else {
                this.mVerifyAppsOverUsb.setEnabled(false);
            }
        }
        this.mStrictMode = findAndInitSwitchPref(STRICT_MODE_KEY);
        this.mPointerLocation = findAndInitSwitchPref(POINTER_LOCATION_KEY);
        this.mShowTouches = findAndInitSwitchPref(SHOW_TOUCHES_KEY);
        this.mShowScreenUpdates = findAndInitSwitchPref(SHOW_SCREEN_UPDATES_KEY);
        this.mDisableOverlays = findAndInitSwitchPref(DISABLE_OVERLAYS_KEY);
        this.mForceHardwareUi = findAndInitSwitchPref(FORCE_HARDWARE_UI_KEY);
        this.mForceMsaa = findAndInitSwitchPref(FORCE_MSAA_KEY);
        this.mTrackFrameTime = addListPreference(TRACK_FRAME_TIME_KEY);
        this.mShowNonRectClip = addListPreference(SHOW_NON_RECTANGULAR_CLIP_KEY);
        this.mShowHwScreenUpdates = findAndInitSwitchPref(SHOW_HW_SCREEN_UPDATES_KEY);
        this.mShowHwLayersUpdates = findAndInitSwitchPref(SHOW_HW_LAYERS_UPDATES_KEY);
        this.mDebugLayout = findAndInitSwitchPref(DEBUG_LAYOUT_KEY);
        this.mForceRtlLayout = findAndInitSwitchPref(FORCE_RTL_LAYOUT_KEY);
        this.mDebugHwOverdraw = addListPreference(DEBUG_HW_OVERDRAW_KEY);
        this.mWifiDisplayCertification = findAndInitSwitchPref(WIFI_DISPLAY_CERTIFICATION_KEY);
        this.mWifiVerboseLogging = findAndInitSwitchPref(WIFI_VERBOSE_LOGGING_KEY);
        this.mWifiAggressiveHandover = findAndInitSwitchPref(WIFI_AGGRESSIVE_HANDOVER_KEY);
        this.mWifiAllowScansWithTraffic = findAndInitSwitchPref(WIFI_ALLOW_SCAN_WITH_TRAFFIC_KEY);
        this.mMobileDataAlwaysOn = findAndInitSwitchPref(MOBILE_DATA_ALWAYS_ON);
        this.mLogdSize = addListPreference(SELECT_LOGD_SIZE_KEY);
        this.mUsbConfiguration = addListPreference(USB_CONFIGURATION_KEY);
        this.mWindowAnimationScale = addListPreference(WINDOW_ANIMATION_SCALE_KEY);
        this.mTransitionAnimationScale = addListPreference(TRANSITION_ANIMATION_SCALE_KEY);
        this.mAnimatorDurationScale = addListPreference(ANIMATOR_DURATION_SCALE_KEY);
        this.mOverlayDisplayDevices = addListPreference(OVERLAY_DISPLAY_DEVICES_KEY);
        this.mOpenGLTraces = addListPreference(OPENGL_TRACES_KEY);
        this.mSimulateColorSpace = addListPreference(SIMULATE_COLOR_SPACE);
        this.mUSBAudio = findAndInitSwitchPref(USB_AUDIO_KEY);
        this.mForceResizable = findAndInitSwitchPref(FORCE_RESIZABLE_KEY);
        this.mImmediatelyDestroyActivities = (SwitchPreference) findPreference(IMMEDIATELY_DESTROY_ACTIVITIES_KEY);
        this.mAllPrefs.add(this.mImmediatelyDestroyActivities);
        this.mResetSwitchPrefs.add(this.mImmediatelyDestroyActivities);
        this.mAppProcessLimit = addListPreference(APP_PROCESS_LIMIT_KEY);
        this.mShowAllANRs = (SwitchPreference) findPreference(SHOW_ALL_ANRS_KEY);
        this.mAllPrefs.add(this.mShowAllANRs);
        this.mResetSwitchPrefs.add(this.mShowAllANRs);
        Preference preferenceFindPreference = findPreference(HDCP_CHECKING_KEY);
        if (preferenceFindPreference != null) {
            this.mAllPrefs.add(preferenceFindPreference);
            removePreferenceForProduction(preferenceFindPreference);
        }
        removePreference(KEY_CONVERT_FBE);
        this.mColorModePreference = (ColorModePreference) findPreference(KEY_COLOR_MODE);
        this.mColorModePreference.updateCurrentAndSupported();
        if (this.mColorModePreference.getColorModeCount() < 2) {
            removePreference(KEY_COLOR_MODE);
            this.mColorModePreference = null;
        }
    }

    private void removePreference(String str) {
        Preference preferenceFindPreference = findPreference(str);
        if (preferenceFindPreference != null) {
            getPreferenceScreen().removePreference(preferenceFindPreference);
        }
    }

    private ListPreference addListPreference(String str) {
        ListPreference listPreference = (ListPreference) findPreference(str);
        this.mAllPrefs.add(listPreference);
        listPreference.setOnPreferenceChangeListener(this);
        return listPreference;
    }

    private void disableForUser(Preference preference) {
        if (preference != null) {
            preference.setEnabled(false);
            this.mDisabledPrefs.add(preference);
        }
    }

    private SwitchPreference findAndInitSwitchPref(String str) {
        SwitchPreference switchPreference = (SwitchPreference) findPreference(str);
        if (switchPreference == null) {
            throw new IllegalArgumentException("Cannot find preference with key = " + str);
        }
        this.mAllPrefs.add(switchPreference);
        this.mResetSwitchPrefs.add(switchPreference);
        return switchPreference;
    }

    @Override // android.support.v14.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!this.mUnavailable || this.mEnableDeveloper == null) {
            return;
        }
        this.mEnableDeveloper.setEnabled(false);
    }

    private boolean removePreferenceForProduction(Preference preference) {
        if (!"user".equals(Build.TYPE)) {
            return false;
        }
        removePreference(preference);
        return true;
    }

    private void removePreference(Preference preference) {
        getPreferenceScreen().removePreference(preference);
        this.mAllPrefs.remove(preference);
        this.mResetSwitchPrefs.remove(preference);
    }

    private void setPrefsEnabledState(boolean z) {
        for (Preference preference : this.mAllPrefs) {
            preference.setEnabled(z && !this.mDisabledPrefs.contains(preference));
        }
        updateAllOptions();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUnavailable) {
            return;
        }
        if (this.mDpm.getMaximumTimeToLock(null) > 0) {
            this.mDisabledPrefs.add(this.mKeepScreenOn);
        } else {
            this.mDisabledPrefs.remove(this.mKeepScreenOn);
        }
        this.mLastEnabledState = Settings.Global.getInt(this.mContentResolver, "development_settings_enabled", 0) != 0;
        this.mEnableDeveloper.setChecked(this.mLastEnabledState);
        setPrefsEnabledState(this.mLastEnabledState);
        if (this.mHaveDebugSettings && !this.mLastEnabledState) {
            Settings.Global.putInt(this.mContentResolver, "development_settings_enabled", 1);
            this.mLastEnabledState = true;
            this.mEnableDeveloper.setChecked(this.mLastEnabledState);
            setPrefsEnabledState(this.mLastEnabledState);
        }
        if (this.mColorModePreference != null) {
            this.mColorModePreference.startListening();
            this.mColorModePreference.updateCurrentAndSupported();
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mColorModePreference != null) {
            this.mColorModePreference.stopListening();
        }
    }

    @Override // android.support.v17.preference.LeanbackPreferenceFragment, android.support.v14.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        if (getActivity().registerReceiver(this.mUsbReceiver, intentFilter) == null) {
            updateUsbConfigurationValues();
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // android.support.v14.preference.PreferenceFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(this.mUsbReceiver);
    }

    void updateSwitchPreference(SwitchPreference switchPreference, boolean z) {
        switchPreference.setChecked(z);
        this.mHaveDebugSettings |= z;
    }

    private void updateAllOptions() {
        Activity activity = getActivity();
        ContentResolver contentResolver = activity.getContentResolver();
        this.mHaveDebugSettings = false;
        updateSwitchPreference(this.mEnableAdb, Settings.Global.getInt(contentResolver, "adb_enabled", 0) != 0);
        if (this.mEnableTerminal != null) {
            updateSwitchPreference(this.mEnableTerminal, activity.getPackageManager().getApplicationEnabledSetting(TERMINAL_APP_PACKAGE) == 1);
        }
        updateSwitchPreference(this.mBugreportInPower, Settings.Secure.getInt(contentResolver, "bugreport_in_power_menu", 0) != 0);
        updateSwitchPreference(this.mKeepScreenOn, Settings.Global.getInt(contentResolver, "stay_on_while_plugged_in", 0) != 0);
        updateSwitchPreference(this.mBtHciSnoopLog, Settings.Secure.getInt(contentResolver, "bluetooth_hci_log", 0) != 0);
        if (this.mEnableOemUnlock != null) {
            updateSwitchPreference(this.mEnableOemUnlock, isOemUnlockEnabled(getActivity()));
            this.mEnableOemUnlock.setEnabled(isOemUnlockAllowed());
        }
        updateSwitchPreference(this.mDebugViewAttributes, Settings.Global.getInt(contentResolver, DEBUG_VIEW_ATTRIBUTES, 0) != 0);
        updateSwitchPreference(this.mForceAllowOnExternal, Settings.Global.getInt(contentResolver, FORCE_ALLOW_ON_EXTERNAL_KEY, 0) != 0);
        updateSwitchPreference(this.mEnableAbc, SystemProperties.getInt(PERSIST_RK_ABC_SWITCH, 0) != 0);
        updateHdcpValues();
        updatePasswordSummary();
        updateDebuggerOptions();
        updateMockLocation();
        updateStrictModeVisualOptions();
        updatePointerLocationOptions();
        updateShowTouchesOptions();
        updateFlingerOptions();
        updateHardwareUiOptions();
        updateMsaaOptions();
        updateTrackFrameTimeOptions();
        updateShowNonRectClipOptions();
        updateShowHwScreenUpdatesOptions();
        updateShowHwLayersUpdatesOptions();
        updateDebugHwOverdrawOptions();
        updateDebugLayoutOptions();
        updateAnimationScaleOptions();
        updateOverlayDisplayDevicesOptions();
        updateOpenGLTracesOptions();
        updateImmediatelyDestroyActivitiesOptions();
        updateAppProcessLimitOptions();
        updateShowAllANRsOptions();
        updateVerifyAppsOverUsbOptions();
        updateBugreportOptions();
        updateForceRtlOptions();
        updateLogdSizeValues();
        updateWifiDisplayCertificationOptions();
        updateWifiVerboseLoggingOptions();
        updateWifiAggressiveHandoverOptions();
        updateWifiAllowScansWithTrafficOptions();
        updateMobileDataAlwaysOnOptions();
        updateSimulateColorSpace();
        updateUSBAudioOptions();
        updateForceResizableOptions();
    }

    private void resetDangerousOptions() {
        this.mDontPokeProperties = true;
        for (SwitchPreference switchPreference : this.mResetSwitchPrefs) {
            if (switchPreference.isChecked()) {
                switchPreference.setChecked(false);
                onPreferenceTreeClick(switchPreference);
            }
        }
        resetDebuggerOptions();
        writeLogdSizeOption(null);
        writeAnimationScaleOption(0, this.mWindowAnimationScale, null);
        writeAnimationScaleOption(1, this.mTransitionAnimationScale, null);
        writeAnimationScaleOption(2, this.mAnimatorDurationScale, null);
        if (usingDevelopmentColorSpace()) {
            writeSimulateColorSpace(-1);
        }
        writeOverlayDisplayDevicesOptions(null);
        writeAppProcessLimitOptions(null);
        this.mHaveDebugSettings = false;
        updateAllOptions();
        this.mDontPokeProperties = false;
        pokeSystemProperties();
    }

    private void updateHdcpValues() {
        ListPreference listPreference = (ListPreference) findPreference(HDCP_CHECKING_KEY);
        if (listPreference != null) {
            String str = SystemProperties.get(HDCP_CHECKING_PROPERTY);
            String[] stringArray = getResources().getStringArray(R.array.hdcp_checking_values);
            String[] stringArray2 = getResources().getStringArray(R.array.hdcp_checking_summaries);
            int i = 0;
            while (i < stringArray.length) {
                if (str.equals(stringArray[i])) {
                    listPreference.setValue(stringArray[i]);
                    listPreference.setSummary(stringArray2[i]);
                    listPreference.setOnPreferenceChangeListener(this);
                }
                i++;
            }
            i = 1;
            listPreference.setValue(stringArray[i]);
            listPreference.setSummary(stringArray2[i]);
            listPreference.setOnPreferenceChangeListener(this);
        }
    }

    private void updatePasswordSummary() {
        try {
            if (this.mBackupManager.hasBackupPassword()) {
                this.mPassword.setSummary(R.string.local_backup_password_summary_change);
            } else {
                this.mPassword.setSummary(R.string.local_backup_password_summary_none);
            }
        } catch (RemoteException unused) {
        }
    }

    private void writeBtHciSnoopLogOptions() {
        BluetoothAdapter.getDefaultAdapter().configHciSnoopLog(this.mBtHciSnoopLog.isChecked());
        Settings.Secure.putInt(this.mContentResolver, "bluetooth_hci_log", this.mBtHciSnoopLog.isChecked() ? 1 : 0);
    }

    private void writeDebuggerOptions() {
        try {
            ActivityManagerNative.getDefault().setDebugApp(this.mDebugApp, this.mWaitForDebugger.isChecked(), true);
        } catch (RemoteException unused) {
        }
    }

    private void writeMockLocation() {
        AppOpsManager appOpsManager = (AppOpsManager) getActivity().getSystemService("appops");
        List<AppOpsManager.PackageOps> packagesForOps = appOpsManager.getPackagesForOps(MOCK_LOCATION_APP_OPS);
        if (packagesForOps != null) {
            for (AppOpsManager.PackageOps packageOps : packagesForOps) {
                if (((AppOpsManager.OpEntry) packageOps.getOps().get(0)).getMode() != 2) {
                    String packageName = packageOps.getPackageName();
                    try {
                        appOpsManager.setMode(58, getActivity().getPackageManager().getApplicationInfo(packageName, 512).uid, packageName, 2);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
            }
        }
        if (TextUtils.isEmpty(this.mMockLocationApp)) {
            return;
        }
        try {
            appOpsManager.setMode(58, getActivity().getPackageManager().getApplicationInfo(this.mMockLocationApp, 512).uid, this.mMockLocationApp, 0);
        } catch (PackageManager.NameNotFoundException unused2) {
        }
    }

    private static void resetDebuggerOptions() {
        try {
            ActivityManagerNative.getDefault().setDebugApp((String) null, false, true);
        } catch (RemoteException unused) {
        }
    }

    private void updateDebuggerOptions() {
        String string;
        this.mDebugApp = Settings.Global.getString(this.mContentResolver, DEBUG_APP_KEY);
        updateSwitchPreference(this.mWaitForDebugger, Settings.Global.getInt(this.mContentResolver, WAIT_FOR_DEBUGGER_KEY, 0) != 0);
        if (this.mDebugApp != null && this.mDebugApp.length() > 0) {
            try {
                CharSequence applicationLabel = getActivity().getPackageManager().getApplicationLabel(getActivity().getPackageManager().getApplicationInfo(this.mDebugApp, 512));
                string = applicationLabel != null ? applicationLabel.toString() : this.mDebugApp;
            } catch (PackageManager.NameNotFoundException unused) {
                string = this.mDebugApp;
            }
            this.mDebugAppPref.setSummary(getResources().getString(R.string.debug_app_set, string));
            this.mWaitForDebugger.setEnabled(true);
            this.mHaveDebugSettings = true;
            return;
        }
        this.mDebugAppPref.setSummary(getResources().getString(R.string.debug_app_not_set));
        this.mWaitForDebugger.setEnabled(false);
    }

    private void updateMockLocation() {
        List packagesForOps = ((AppOpsManager) getActivity().getSystemService("appops")).getPackagesForOps(MOCK_LOCATION_APP_OPS);
        if (packagesForOps != null) {
            Iterator it = packagesForOps.iterator();
            while (it.hasNext()) {
                if (((AppOpsManager.OpEntry) ((AppOpsManager.PackageOps) it.next()).getOps().get(0)).getMode() == 0) {
                    this.mMockLocationApp = ((AppOpsManager.PackageOps) packagesForOps.get(0)).getPackageName();
                    break;
                }
            }
        }
        if (!TextUtils.isEmpty(this.mMockLocationApp)) {
            String string = this.mMockLocationApp;
            try {
                CharSequence applicationLabel = getActivity().getPackageManager().getApplicationLabel(getActivity().getPackageManager().getApplicationInfo(this.mMockLocationApp, 512));
                if (applicationLabel != null) {
                    string = applicationLabel.toString();
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            this.mMockLocationAppPref.setSummary(getString(R.string.mock_location_app_set, new Object[]{string}));
            this.mHaveDebugSettings = true;
            return;
        }
        this.mMockLocationAppPref.setSummary(getString(R.string.mock_location_app_not_set));
    }

    private void updateVerifyAppsOverUsbOptions() {
        updateSwitchPreference(this.mVerifyAppsOverUsb, Settings.Global.getInt(this.mContentResolver, "verifier_verify_adb_installs", 1) != 0);
        this.mVerifyAppsOverUsb.setEnabled(enableVerifierSetting());
    }

    private void writeVerifyAppsOverUsbOptions() {
        Settings.Global.putInt(this.mContentResolver, "verifier_verify_adb_installs", this.mVerifyAppsOverUsb.isChecked() ? 1 : 0);
    }

    private boolean enableVerifierSetting() {
        if (Settings.Global.getInt(this.mContentResolver, "adb_enabled", 0) == 0 || Settings.Global.getInt(this.mContentResolver, "package_verifier_enable", 1) == 0) {
            return false;
        }
        PackageManager packageManager = getActivity().getPackageManager();
        Intent intent = new Intent("android.intent.action.PACKAGE_NEEDS_VERIFICATION");
        intent.setType(PACKAGE_MIME_TYPE);
        intent.addFlags(1);
        return packageManager.queryBroadcastReceivers(intent, 0).size() != 0;
    }

    private boolean showVerifierSetting() {
        return Settings.Global.getInt(this.mContentResolver, "verifier_setting_visible", 1) > 0;
    }

    private static boolean showEnableOemUnlockPreference() {
        return !SystemProperties.get(PERSISTENT_DATA_BLOCK_PROP).equals("");
    }

    private boolean isOemUnlockAllowed() {
        return !this.mUm.hasUserRestriction("no_oem_unlock");
    }

    private void updateBugreportOptions() {
        if (this.mBugreport != null) {
            this.mBugreport.setEnabled(true);
        }
        this.mBugreportInPower.setEnabled(true);
        setBugreportStorageProviderStatus();
    }

    private void setBugreportStorageProviderStatus() {
        getActivity().getPackageManager().setComponentEnabledSetting(new ComponentName("com.android.shell", "com.android.shell.BugreportStorageProvider"), this.mBugreportInPower.isChecked() ? 1 : 0, 0);
    }

    private static int currentStrictModeActiveIndex() {
        if (TextUtils.isEmpty(SystemProperties.get("persist.sys.strictmode.visual"))) {
            return 0;
        }
        return SystemProperties.getBoolean("persist.sys.strictmode.visual", false) ? 1 : 2;
    }

    private void writeStrictModeVisualOptions() {
        try {
            this.mWindowManager.setStrictModeVisualIndicatorPreference(this.mStrictMode.isChecked() ? "1" : "");
        } catch (RemoteException unused) {
        }
    }

    private void updateStrictModeVisualOptions() {
        updateSwitchPreference(this.mStrictMode, currentStrictModeActiveIndex() == 1);
    }

    private void writePointerLocationOptions() {
        Settings.System.putInt(this.mContentResolver, POINTER_LOCATION_KEY, this.mPointerLocation.isChecked() ? 1 : 0);
    }

    private void updatePointerLocationOptions() {
        updateSwitchPreference(this.mPointerLocation, Settings.System.getInt(this.mContentResolver, POINTER_LOCATION_KEY, 0) != 0);
    }

    private void writeShowTouchesOptions() {
        Settings.System.putInt(this.mContentResolver, SHOW_TOUCHES_KEY, this.mShowTouches.isChecked() ? 1 : 0);
    }

    private void updateShowTouchesOptions() {
        updateSwitchPreference(this.mShowTouches, Settings.System.getInt(this.mContentResolver, SHOW_TOUCHES_KEY, 0) != 0);
    }

    private void updateFlingerOptions() {
        try {
            IBinder service = ServiceManager.getService("SurfaceFlinger");
            if (service != null) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                parcelObtain.writeInterfaceToken("android.ui.ISurfaceComposer");
                service.transact(PointerIconCompat.TYPE_ALIAS, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readInt();
                parcelObtain2.readInt();
                updateSwitchPreference(this.mShowScreenUpdates, parcelObtain2.readInt() != 0);
                parcelObtain2.readInt();
                updateSwitchPreference(this.mDisableOverlays, parcelObtain2.readInt() != 0);
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        } catch (RemoteException unused) {
        }
    }

    private void writeShowUpdatesOption() {
        try {
            IBinder service = ServiceManager.getService("SurfaceFlinger");
            if (service != null) {
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.writeInterfaceToken("android.ui.ISurfaceComposer");
                parcelObtain.writeInt(this.mShowScreenUpdates.isChecked() ? 1 : 0);
                service.transact(1002, parcelObtain, null, 0);
                parcelObtain.recycle();
                updateFlingerOptions();
            }
        } catch (RemoteException unused) {
        }
    }

    private void writeDisableOverlaysOption() {
        try {
            IBinder service = ServiceManager.getService("SurfaceFlinger");
            if (service != null) {
                Parcel parcelObtain = Parcel.obtain();
                parcelObtain.writeInterfaceToken("android.ui.ISurfaceComposer");
                parcelObtain.writeInt(this.mDisableOverlays.isChecked() ? 1 : 0);
                service.transact(PointerIconCompat.TYPE_TEXT, parcelObtain, null, 0);
                parcelObtain.recycle();
                updateFlingerOptions();
            }
        } catch (RemoteException unused) {
        }
    }

    private void updateHardwareUiOptions() {
        updateSwitchPreference(this.mForceHardwareUi, SystemProperties.getBoolean(HARDWARE_UI_PROPERTY, false));
    }

    private void writeHardwareUiOptions() {
        SystemProperties.set(HARDWARE_UI_PROPERTY, this.mForceHardwareUi.isChecked() ? "true" : "false");
        pokeSystemProperties();
    }

    private void updateMsaaOptions() {
        updateSwitchPreference(this.mForceMsaa, SystemProperties.getBoolean(MSAA_PROPERTY, false));
    }

    private void writeMsaaOptions() {
        SystemProperties.set(MSAA_PROPERTY, this.mForceMsaa.isChecked() ? "true" : "false");
        pokeSystemProperties();
    }

    private void updateTrackFrameTimeOptions() {
        String str = SystemProperties.get("debug.hwui.profile");
        if (str == null) {
            str = "";
        }
        CharSequence[] entryValues = this.mTrackFrameTime.getEntryValues();
        for (int i = 0; i < entryValues.length; i++) {
            if (str.contentEquals(entryValues[i])) {
                this.mTrackFrameTime.setValueIndex(i);
                this.mTrackFrameTime.setSummary(this.mTrackFrameTime.getEntries()[i]);
                return;
            }
        }
        this.mTrackFrameTime.setValueIndex(0);
        this.mTrackFrameTime.setSummary(this.mTrackFrameTime.getEntries()[0]);
    }

    private void writeTrackFrameTimeOptions(Object obj) {
        SystemProperties.set("debug.hwui.profile", obj == null ? "" : obj.toString());
        pokeSystemProperties();
        updateTrackFrameTimeOptions();
    }

    private void updateShowNonRectClipOptions() {
        String str = SystemProperties.get("debug.hwui.show_non_rect_clip");
        if (str == null) {
            str = "hide";
        }
        CharSequence[] entryValues = this.mShowNonRectClip.getEntryValues();
        for (int i = 0; i < entryValues.length; i++) {
            if (str.contentEquals(entryValues[i])) {
                this.mShowNonRectClip.setValueIndex(i);
                this.mShowNonRectClip.setSummary(this.mShowNonRectClip.getEntries()[i]);
                return;
            }
        }
        this.mShowNonRectClip.setValueIndex(0);
        this.mShowNonRectClip.setSummary(this.mShowNonRectClip.getEntries()[0]);
    }

    private void writeShowNonRectClipOptions(Object obj) {
        SystemProperties.set("debug.hwui.show_non_rect_clip", obj == null ? "" : obj.toString());
        pokeSystemProperties();
        updateShowNonRectClipOptions();
    }

    private void updateShowHwScreenUpdatesOptions() {
        updateSwitchPreference(this.mShowHwScreenUpdates, SystemProperties.getBoolean("debug.hwui.show_dirty_regions", false));
    }

    private void writeShowHwScreenUpdatesOptions() {
        SystemProperties.set("debug.hwui.show_dirty_regions", this.mShowHwScreenUpdates.isChecked() ? "true" : null);
        pokeSystemProperties();
    }

    private void updateShowHwLayersUpdatesOptions() {
        updateSwitchPreference(this.mShowHwLayersUpdates, SystemProperties.getBoolean("debug.hwui.show_layers_updates", false));
    }

    private void writeShowHwLayersUpdatesOptions() {
        SystemProperties.set("debug.hwui.show_layers_updates", this.mShowHwLayersUpdates.isChecked() ? "true" : null);
        pokeSystemProperties();
    }

    private void updateDebugHwOverdrawOptions() {
        String str = SystemProperties.get("debug.hwui.overdraw");
        if (str == null) {
            str = "";
        }
        CharSequence[] entryValues = this.mDebugHwOverdraw.getEntryValues();
        for (int i = 0; i < entryValues.length; i++) {
            if (str.contentEquals(entryValues[i])) {
                this.mDebugHwOverdraw.setValueIndex(i);
                this.mDebugHwOverdraw.setSummary(this.mDebugHwOverdraw.getEntries()[i]);
                return;
            }
        }
        this.mDebugHwOverdraw.setValueIndex(0);
        this.mDebugHwOverdraw.setSummary(this.mDebugHwOverdraw.getEntries()[0]);
    }

    private void writeDebugHwOverdrawOptions(Object obj) {
        SystemProperties.set("debug.hwui.overdraw", obj == null ? "" : obj.toString());
        pokeSystemProperties();
        updateDebugHwOverdrawOptions();
    }

    private void updateDebugLayoutOptions() {
        updateSwitchPreference(this.mDebugLayout, SystemProperties.getBoolean("debug.layout", false));
    }

    private void writeDebugLayoutOptions() {
        SystemProperties.set("debug.layout", this.mDebugLayout.isChecked() ? "true" : "false");
        pokeSystemProperties();
    }

    private void updateSimulateColorSpace() {
        if (Settings.Secure.getInt(this.mContentResolver, "accessibility_display_daltonizer_enabled", 0) != 0) {
            String string = Integer.toString(Settings.Secure.getInt(this.mContentResolver, "accessibility_display_daltonizer", -1));
            this.mSimulateColorSpace.setValue(string);
            if (this.mSimulateColorSpace.findIndexOfValue(string) < 0) {
                this.mSimulateColorSpace.setSummary(getString(R.string.daltonizer_type_overridden, new Object[]{getString(R.string.accessibility_display_daltonizer_preference_title)}));
                return;
            } else {
                this.mSimulateColorSpace.setSummary("%s");
                return;
            }
        }
        this.mSimulateColorSpace.setValue(Integer.toString(-1));
    }

    private boolean usingDevelopmentColorSpace() {
        if (Settings.Secure.getInt(this.mContentResolver, "accessibility_display_daltonizer_enabled", 0) != 0) {
            if (this.mSimulateColorSpace.findIndexOfValue(Integer.toString(Settings.Secure.getInt(this.mContentResolver, "accessibility_display_daltonizer", -1))) >= 0) {
                return true;
            }
        }
        return false;
    }

    private void writeSimulateColorSpace(Object obj) {
        int i = Integer.parseInt(obj.toString());
        if (i < 0) {
            Settings.Secure.putInt(this.mContentResolver, "accessibility_display_daltonizer_enabled", 0);
        } else {
            Settings.Secure.putInt(this.mContentResolver, "accessibility_display_daltonizer_enabled", 1);
            Settings.Secure.putInt(this.mContentResolver, "accessibility_display_daltonizer", i);
        }
    }

    private void updateUSBAudioOptions() {
        updateSwitchPreference(this.mUSBAudio, Settings.Secure.getInt(this.mContentResolver, "usb_audio_automatic_routing_disabled", 0) != 0);
    }

    private void writeUSBAudioOptions() {
        Settings.Secure.putInt(this.mContentResolver, "usb_audio_automatic_routing_disabled", this.mUSBAudio.isChecked() ? 1 : 0);
    }

    private void updateForceResizableOptions() {
        updateSwitchPreference(this.mForceResizable, Settings.Global.getInt(this.mContentResolver, FORCE_RESIZABLE_KEY, 0) != 0);
    }

    private void writeForceResizableOptions() {
        Settings.Global.putInt(this.mContentResolver, FORCE_RESIZABLE_KEY, this.mForceResizable.isChecked() ? 1 : 0);
    }

    private void updateForceRtlOptions() {
        updateSwitchPreference(this.mForceRtlLayout, Settings.Global.getInt(this.mContentResolver, "debug.force_rtl", 0) != 0);
    }

    private void writeForceRtlOptions() {
        boolean zIsChecked = this.mForceRtlLayout.isChecked();
        Settings.Global.putInt(this.mContentResolver, "debug.force_rtl", zIsChecked ? 1 : 0);
        SystemProperties.set("debug.force_rtl", zIsChecked ? "1" : "0");
        LocalePicker.updateLocale(getActivity().getResources().getConfiguration().getLocales().get(0));
    }

    private void updateWifiDisplayCertificationOptions() {
        updateSwitchPreference(this.mWifiDisplayCertification, Settings.Global.getInt(this.mContentResolver, "wifi_display_certification_on", 0) != 0);
    }

    private void writeWifiDisplayCertificationOptions() {
        Settings.Global.putInt(this.mContentResolver, "wifi_display_certification_on", this.mWifiDisplayCertification.isChecked() ? 1 : 0);
    }

    private void updateWifiVerboseLoggingOptions() {
        updateSwitchPreference(this.mWifiVerboseLogging, this.mWifiManager.getVerboseLoggingLevel() > 0);
    }

    private void writeWifiVerboseLoggingOptions() {
        this.mWifiManager.enableVerboseLogging(this.mWifiVerboseLogging.isChecked() ? 1 : 0);
    }

    private void updateWifiAggressiveHandoverOptions() {
        updateSwitchPreference(this.mWifiAggressiveHandover, this.mWifiManager.getAggressiveHandover() > 0);
    }

    private void writeWifiAggressiveHandoverOptions() {
        this.mWifiManager.enableAggressiveHandover(this.mWifiAggressiveHandover.isChecked() ? 1 : 0);
    }

    private void updateWifiAllowScansWithTrafficOptions() {
        updateSwitchPreference(this.mWifiAllowScansWithTraffic, this.mWifiManager.getAllowScansWithTraffic() > 0);
    }

    private void writeWifiAllowScansWithTrafficOptions() {
        this.mWifiManager.setAllowScansWithTraffic(this.mWifiAllowScansWithTraffic.isChecked() ? 1 : 0);
    }

    private void updateMobileDataAlwaysOnOptions() {
        updateSwitchPreference(this.mMobileDataAlwaysOn, Settings.Global.getInt(this.mContentResolver, MOBILE_DATA_ALWAYS_ON, 0) != 0);
    }

    private void writeMobileDataAlwaysOnOptions() {
        Settings.Global.putInt(this.mContentResolver, MOBILE_DATA_ALWAYS_ON, this.mMobileDataAlwaysOn.isChecked() ? 1 : 0);
    }

    private void updateLogdSizeValues() {
        if (this.mLogdSize != null) {
            String str = SystemProperties.get(SELECT_LOGD_SIZE_PROPERTY);
            if (str == null && (str = SystemProperties.get(SELECT_LOGD_DEFAULT_SIZE_PROPERTY)) == null) {
                str = "256K";
            }
            String[] stringArray = getResources().getStringArray(R.array.select_logd_size_values);
            String[] stringArray2 = getResources().getStringArray(R.array.select_logd_size_titles);
            if (SystemProperties.get("ro.config.low_ram").equals("true")) {
                this.mLogdSize.setEntries(R.array.select_logd_size_lowram_titles);
                stringArray2 = getResources().getStringArray(R.array.select_logd_size_lowram_titles);
            }
            String[] stringArray3 = getResources().getStringArray(R.array.select_logd_size_summaries);
            int i = 0;
            while (i < stringArray2.length) {
                if (str.equals(stringArray[i]) || str.equals(stringArray2[i])) {
                    this.mLogdSize.setValue(stringArray[i]);
                    this.mLogdSize.setSummary(stringArray3[i]);
                    this.mLogdSize.setOnPreferenceChangeListener(this);
                }
                i++;
            }
            i = 1;
            this.mLogdSize.setValue(stringArray[i]);
            this.mLogdSize.setSummary(stringArray3[i]);
            this.mLogdSize.setOnPreferenceChangeListener(this);
        }
    }

    private void writeLogdSizeOption(Object obj) {
        String str = SystemProperties.get(SELECT_LOGD_DEFAULT_SIZE_PROPERTY);
        if (str != null) {
            DEFAULT_LOG_RING_BUFFER_SIZE_IN_BYTES = str;
        }
        String string = obj != null ? obj.toString() : DEFAULT_LOG_RING_BUFFER_SIZE_IN_BYTES;
        SystemProperties.set(SELECT_LOGD_SIZE_PROPERTY, string);
        pokeSystemProperties();
        try {
            Runtime.getRuntime().exec("logcat -b all -G " + string).waitFor();
            Log.i(TAG, "Logcat ring buffer sizes set to: " + string);
        } catch (Exception e) {
            Log.w(TAG, "Cannot set logcat ring buffer sizes", e);
        }
        updateLogdSizeValues();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUsbConfigurationValues() {
        if (this.mUsbConfiguration != null) {
            UsbManager usbManager = (UsbManager) getActivity().getSystemService("usb");
            String[] stringArray = getResources().getStringArray(R.array.usb_configuration_values);
            String[] stringArray2 = getResources().getStringArray(R.array.usb_configuration_titles);
            int i = 0;
            for (int i2 = 0; i2 < stringArray2.length; i2++) {
                if (usbManager.isFunctionEnabled(stringArray[i2])) {
                    i = i2;
                    break;
                }
            }
            this.mUsbConfiguration.setValue(stringArray[i]);
            this.mUsbConfiguration.setSummary(stringArray2[i]);
            this.mUsbConfiguration.setOnPreferenceChangeListener(this);
        }
    }

    private void writeUsbConfigurationOption(Object obj) {
        ((UsbManager) getActivity().getSystemService("usb")).setCurrentFunction(obj.toString(), false);
    }

    private void writeImmediatelyDestroyActivitiesOptions() {
        try {
            ActivityManagerNative.getDefault().setAlwaysFinish(this.mImmediatelyDestroyActivities.isChecked());
        } catch (RemoteException unused) {
        }
    }

    private void updateImmediatelyDestroyActivitiesOptions() {
        updateSwitchPreference(this.mImmediatelyDestroyActivities, Settings.Global.getInt(this.mContentResolver, "always_finish_activities", 0) != 0);
    }

    private void updateAnimationScaleValue(int i, ListPreference listPreference) {
        try {
            float animationScale = this.mWindowManager.getAnimationScale(i);
            if (animationScale != 1.0f) {
                this.mHaveDebugSettings = true;
            }
            CharSequence[] entryValues = listPreference.getEntryValues();
            for (int i2 = 0; i2 < entryValues.length; i2++) {
                if (animationScale <= Float.parseFloat(entryValues[i2].toString())) {
                    listPreference.setValueIndex(i2);
                    listPreference.setSummary(listPreference.getEntries()[i2]);
                    return;
                }
            }
            listPreference.setValueIndex(entryValues.length - 1);
            listPreference.setSummary(listPreference.getEntries()[0]);
        } catch (RemoteException unused) {
        }
    }

    private void updateAnimationScaleOptions() {
        updateAnimationScaleValue(0, this.mWindowAnimationScale);
        updateAnimationScaleValue(1, this.mTransitionAnimationScale);
        updateAnimationScaleValue(2, this.mAnimatorDurationScale);
    }

    private void writeAnimationScaleOption(int i, ListPreference listPreference, Object obj) {
        float f;
        if (obj != null) {
            try {
                f = Float.parseFloat(obj.toString());
            } catch (RemoteException unused) {
                return;
            }
        } else {
            f = 1.0f;
        }
        this.mWindowManager.setAnimationScale(i, f);
        updateAnimationScaleValue(i, listPreference);
    }

    private void updateOverlayDisplayDevicesOptions() {
        String string = Settings.Global.getString(this.mContentResolver, OVERLAY_DISPLAY_DEVICES_KEY);
        if (string == null) {
            string = "";
        }
        CharSequence[] entryValues = this.mOverlayDisplayDevices.getEntryValues();
        for (int i = 0; i < entryValues.length; i++) {
            if (string.contentEquals(entryValues[i])) {
                this.mOverlayDisplayDevices.setValueIndex(i);
                this.mOverlayDisplayDevices.setSummary(this.mOverlayDisplayDevices.getEntries()[i]);
                return;
            }
        }
        this.mOverlayDisplayDevices.setValueIndex(0);
        this.mOverlayDisplayDevices.setSummary(this.mOverlayDisplayDevices.getEntries()[0]);
    }

    private void writeOverlayDisplayDevicesOptions(Object obj) {
        Settings.Global.putString(this.mContentResolver, OVERLAY_DISPLAY_DEVICES_KEY, (String) obj);
        updateOverlayDisplayDevicesOptions();
    }

    private void updateOpenGLTracesOptions() {
        String str = SystemProperties.get(OPENGL_TRACES_PROPERTY);
        if (str == null) {
            str = "";
        }
        CharSequence[] entryValues = this.mOpenGLTraces.getEntryValues();
        for (int i = 0; i < entryValues.length; i++) {
            if (str.contentEquals(entryValues[i])) {
                this.mOpenGLTraces.setValueIndex(i);
                this.mOpenGLTraces.setSummary(this.mOpenGLTraces.getEntries()[i]);
                return;
            }
        }
        this.mOpenGLTraces.setValueIndex(0);
        this.mOpenGLTraces.setSummary(this.mOpenGLTraces.getEntries()[0]);
    }

    private void writeOpenGLTracesOptions(Object obj) {
        SystemProperties.set(OPENGL_TRACES_PROPERTY, obj == null ? "" : obj.toString());
        pokeSystemProperties();
        updateOpenGLTracesOptions();
    }

    private void updateAppProcessLimitOptions() {
        try {
            int processLimit = ActivityManagerNative.getDefault().getProcessLimit();
            CharSequence[] entryValues = this.mAppProcessLimit.getEntryValues();
            for (int i = 0; i < entryValues.length; i++) {
                if (Integer.parseInt(entryValues[i].toString()) >= processLimit) {
                    if (i != 0) {
                        this.mHaveDebugSettings = true;
                    }
                    this.mAppProcessLimit.setValueIndex(i);
                    this.mAppProcessLimit.setSummary(this.mAppProcessLimit.getEntries()[i]);
                    return;
                }
            }
            this.mAppProcessLimit.setValueIndex(0);
            this.mAppProcessLimit.setSummary(this.mAppProcessLimit.getEntries()[0]);
        } catch (RemoteException unused) {
        }
    }

    private void writeAppProcessLimitOptions(Object obj) {
        int i;
        if (obj != null) {
            try {
                i = Integer.parseInt(obj.toString());
            } catch (RemoteException unused) {
                return;
            }
        } else {
            i = -1;
        }
        ActivityManagerNative.getDefault().setProcessLimit(i);
        updateAppProcessLimitOptions();
    }

    private void writeShowAllANRsOptions() {
        Settings.Secure.putInt(this.mContentResolver, "anr_show_background", this.mShowAllANRs.isChecked() ? 1 : 0);
    }

    private void updateShowAllANRsOptions() {
        updateSwitchPreference(this.mShowAllANRs, Settings.Secure.getInt(this.mContentResolver, "anr_show_background", 0) != 0);
    }

    @Override // com.android.settingslib.system.OemUnlockDialog.Callback
    public void onOemUnlockConfirm() {
        this.mEnableOemUnlock.setChecked(true);
        setOemUnlockEnabled(getActivity(), true);
        updateAllOptions();
    }

    @Override // com.android.settingslib.system.EnableDevelopmentDialog.Callback
    public void onEnableDevelopmentConfirm() {
        this.mEnableDeveloper.setChecked(true);
        Settings.Global.putInt(this.mContentResolver, "development_settings_enabled", 1);
        this.mLastEnabledState = true;
        setPrefsEnabledState(true);
    }

    @Override // com.android.settingslib.system.AdbDialog.Callback
    public void onEnableAdbConfirm() {
        Settings.Global.putInt(this.mContentResolver, "adb_enabled", 1);
        this.mVerifyAppsOverUsb.setEnabled(true);
        updateVerifyAppsOverUsbOptions();
        updateBugreportOptions();
    }

    @Override // android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1000) {
            if (i2 == -1) {
                this.mDebugApp = intent.getAction();
                writeDebuggerOptions();
                updateDebuggerOptions();
                return;
            }
            return;
        }
        if (i != 1001) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            this.mMockLocationApp = intent.getAction();
            writeMockLocation();
            updateMockLocation();
        }
    }

    @Override // android.support.v14.preference.PreferenceFragment, android.support.v7.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (ActivityManager.isUserAMonkey()) {
            return false;
        }
        if (preference == this.mEnableDeveloper) {
            if (this.mEnableDeveloper.isChecked()) {
                super.onPreferenceTreeClick(preference);
                this.mEnableDeveloper.setChecked(false);
            } else {
                resetDangerousOptions();
                Settings.Global.putInt(this.mContentResolver, "development_settings_enabled", 0);
                this.mLastEnabledState = false;
                setPrefsEnabledState(false);
            }
        } else if (preference == this.mEnableAdb) {
            if (this.mEnableAdb.isChecked()) {
                super.onPreferenceTreeClick(preference);
                this.mEnableAdb.setChecked(false);
            } else {
                Settings.Global.putInt(this.mContentResolver, "adb_enabled", 0);
                this.mVerifyAppsOverUsb.setEnabled(false);
                this.mVerifyAppsOverUsb.setChecked(false);
                updateBugreportOptions();
            }
        } else if (preference == this.mEnableUsb) {
            if (this.mEnableUsb.isChecked()) {
                this.mUsbModeSetting.onUsbModeClick(UsbModeSettings.SLAVE_MODE);
                this.mEnableUsb.setSummary(R.string.usb_connect_to_computer);
            } else {
                this.mUsbModeSetting.onUsbModeClick(UsbModeSettings.HOST_MODE);
                this.mEnableUsb.setSummary(R.string.usb_disconnect_to_computer);
            }
        } else if (preference == this.mEnableInternetAdb) {
            if (this.mEnableInternetAdb.isChecked()) {
                SystemProperties.set("persist.internet.adb.enable", "1");
            } else {
                SystemProperties.set("persist.internet.adb.enable", "0");
            }
        } else if (preference == this.mEnableAbc) {
            if (SystemProperties.getInt(PERSIST_RK_ABC_SWITCH, 0) == 1) {
                Log.d(TAG, "set modify abc property to persist 0");
                SystemProperties.set(PERSIST_RK_ABC_SWITCH, "0");
            } else {
                Log.d(TAG, "set modify abc property to persist 1");
                SystemProperties.set(PERSIST_RK_ABC_SWITCH, "1");
            }
        } else if (preference == this.mEnableTerminal) {
            getActivity().getPackageManager().setApplicationEnabledSetting(TERMINAL_APP_PACKAGE, this.mEnableTerminal.isChecked() ? 1 : 0, 0);
        } else if (preference == this.mBugreportInPower) {
            Settings.Secure.putInt(this.mContentResolver, "bugreport_in_power_menu", this.mBugreportInPower.isChecked() ? 1 : 0);
            setBugreportStorageProviderStatus();
        } else if (preference == this.mKeepScreenOn) {
            Settings.Global.putInt(this.mContentResolver, "stay_on_while_plugged_in", this.mKeepScreenOn.isChecked() ? 3 : 0);
        } else if (preference == this.mBtHciSnoopLog) {
            writeBtHciSnoopLogOptions();
        } else if (preference == this.mEnableOemUnlock) {
            if (this.mEnableOemUnlock.isChecked()) {
                super.onPreferenceTreeClick(preference);
                this.mEnableOemUnlock.setChecked(false);
            } else {
                setOemUnlockEnabled(getActivity(), false);
            }
        } else if (preference == this.mMockLocationAppPref) {
            Intent intent = new Intent(getActivity(), (Class<?>) AppPicker.class);
            intent.putExtra(AppPicker.EXTRA_REQUESTIING_PERMISSION, "android.permission.ACCESS_MOCK_LOCATION");
            startActivityForResult(intent, 1001);
        } else if (preference == this.mDebugViewAttributes) {
            Settings.Global.putInt(this.mContentResolver, DEBUG_VIEW_ATTRIBUTES, this.mDebugViewAttributes.isChecked() ? 1 : 0);
        } else if (preference == this.mForceAllowOnExternal) {
            Settings.Global.putInt(this.mContentResolver, FORCE_ALLOW_ON_EXTERNAL_KEY, this.mForceAllowOnExternal.isChecked() ? 1 : 0);
        } else if (preference == this.mDebugAppPref) {
            Intent intent2 = new Intent(getActivity(), (Class<?>) AppPicker.class);
            intent2.putExtra(AppPicker.EXTRA_DEBUGGABLE, true);
            startActivityForResult(intent2, 1000);
        } else if (preference == this.mWaitForDebugger) {
            writeDebuggerOptions();
        } else if (preference == this.mVerifyAppsOverUsb) {
            writeVerifyAppsOverUsbOptions();
        } else if (preference == this.mStrictMode) {
            writeStrictModeVisualOptions();
        } else if (preference == this.mPointerLocation) {
            writePointerLocationOptions();
        } else if (preference == this.mShowTouches) {
            writeShowTouchesOptions();
        } else if (preference == this.mShowScreenUpdates) {
            writeShowUpdatesOption();
        } else if (preference == this.mDisableOverlays) {
            writeDisableOverlaysOption();
        } else if (preference == this.mImmediatelyDestroyActivities) {
            writeImmediatelyDestroyActivitiesOptions();
        } else if (preference == this.mShowAllANRs) {
            writeShowAllANRsOptions();
        } else if (preference == this.mForceHardwareUi) {
            writeHardwareUiOptions();
        } else if (preference == this.mForceMsaa) {
            writeMsaaOptions();
        } else if (preference == this.mShowHwScreenUpdates) {
            writeShowHwScreenUpdatesOptions();
        } else if (preference == this.mShowHwLayersUpdates) {
            writeShowHwLayersUpdatesOptions();
        } else if (preference == this.mDebugLayout) {
            writeDebugLayoutOptions();
        } else if (preference == this.mForceRtlLayout) {
            writeForceRtlOptions();
        } else if (preference == this.mWifiDisplayCertification) {
            writeWifiDisplayCertificationOptions();
        } else if (preference == this.mWifiVerboseLogging) {
            writeWifiVerboseLoggingOptions();
        } else if (preference == this.mWifiAggressiveHandover) {
            writeWifiAggressiveHandoverOptions();
        } else if (preference == this.mWifiAllowScansWithTraffic) {
            writeWifiAllowScansWithTrafficOptions();
        } else if (preference == this.mMobileDataAlwaysOn) {
            writeMobileDataAlwaysOnOptions();
        } else if (preference == this.mUSBAudio) {
            writeUSBAudioOptions();
        } else if (preference == this.mForceResizable) {
            writeForceResizableOptions();
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return false;
    }

    @Override // android.support.v7.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (HDCP_CHECKING_KEY.equals(preference.getKey())) {
            SystemProperties.set(HDCP_CHECKING_PROPERTY, obj.toString());
            updateHdcpValues();
            pokeSystemProperties();
            return true;
        }
        if (preference == this.mLogdSize) {
            writeLogdSizeOption(obj);
            return true;
        }
        if (preference == this.mUsbConfiguration) {
            writeUsbConfigurationOption(obj);
            return true;
        }
        if (preference == this.mWindowAnimationScale) {
            writeAnimationScaleOption(0, this.mWindowAnimationScale, obj);
            return true;
        }
        if (preference == this.mTransitionAnimationScale) {
            writeAnimationScaleOption(1, this.mTransitionAnimationScale, obj);
            return true;
        }
        if (preference == this.mAnimatorDurationScale) {
            writeAnimationScaleOption(2, this.mAnimatorDurationScale, obj);
            return true;
        }
        if (preference == this.mOverlayDisplayDevices) {
            writeOverlayDisplayDevicesOptions(obj);
            return true;
        }
        if (preference == this.mOpenGLTraces) {
            writeOpenGLTracesOptions(obj);
            return true;
        }
        if (preference == this.mTrackFrameTime) {
            writeTrackFrameTimeOptions(obj);
            return true;
        }
        if (preference == this.mDebugHwOverdraw) {
            writeDebugHwOverdrawOptions(obj);
            return true;
        }
        if (preference == this.mShowNonRectClip) {
            writeShowNonRectClipOptions(obj);
            return true;
        }
        if (preference == this.mAppProcessLimit) {
            writeAppProcessLimitOptions(obj);
            return true;
        }
        if (preference != this.mSimulateColorSpace) {
            return false;
        }
        writeSimulateColorSpace(obj);
        return true;
    }

    void pokeSystemProperties() {
        if (this.mDontPokeProperties) {
            return;
        }
        new SystemPropPoker().execute(new Void[0]);
    }

    static class SystemPropPoker extends AsyncTask<Void, Void, Void> {
        SystemPropPoker() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            for (String str : ServiceManager.listServices()) {
                IBinder iBinderCheckService = ServiceManager.checkService(str);
                if (iBinderCheckService != null) {
                    Parcel parcelObtain = Parcel.obtain();
                    try {
                        iBinderCheckService.transact(1599295570, parcelObtain, null, 0);
                    } catch (RemoteException unused) {
                    } catch (Exception e) {
                        Log.i(DevelopmentFragment.TAG, "Someone wrote a bad service '" + str + "' that doesn't like to be poked: " + e);
                    }
                    parcelObtain.recycle();
                }
            }
            return null;
        }
    }

    private static boolean isPackageInstalled(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    static boolean isOemUnlockEnabled(Context context) {
        return ((PersistentDataBlockManager) context.getSystemService("persistent_data_block")).getOemUnlockEnabled();
    }

    static void setOemUnlockEnabled(Context context, boolean z) {
        try {
            ((PersistentDataBlockManager) context.getSystemService("persistent_data_block")).setOemUnlockEnabled(z);
        } catch (SecurityException e) {
            Log.e(TAG, "Fail to set oem unlock.", e);
        }
    }
}
