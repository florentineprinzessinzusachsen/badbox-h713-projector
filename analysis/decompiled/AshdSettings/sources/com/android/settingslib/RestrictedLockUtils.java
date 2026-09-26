package com.android.settingslib;

import android.app.AppGlobals;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;
import com.android.internal.widget.LockPatternUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedLockUtils {
    public static Drawable getRestrictedPadlock(Context context) {
        Drawable drawable = context.getDrawable(R.drawable.ic_info);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.restricted_icon_size);
        drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        return drawable;
    }

    public static EnforcedAdmin checkIfRestrictionEnforced(Context context, String str, int i) {
        int userRestrictionSource;
        if (((DevicePolicyManager) context.getSystemService("device_policy")) != null && (userRestrictionSource = UserManager.get(context).getUserRestrictionSource(str, UserHandle.of(i))) != 0) {
            if (userRestrictionSource != 1) {
                boolean z = (userRestrictionSource & 4) != 0;
                boolean z2 = (userRestrictionSource & 2) != 0;
                if (z) {
                    return getProfileOwner(context, i);
                }
                if (!z2) {
                    return null;
                }
                EnforcedAdmin deviceOwner = getDeviceOwner(context);
                return deviceOwner.userId == i ? deviceOwner : EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
            }
        }
        return null;
    }

    public static boolean hasBaseUserRestriction(Context context, String str, int i) {
        return ((UserManager) context.getSystemService("user")).hasBaseUserRestriction(str, UserHandle.of(i));
    }

    public static EnforcedAdmin checkIfKeyguardFeaturesDisabled(Context context, int i, int i2) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        EnforcedAdmin enforcedAdmin = null;
        if (devicePolicyManager == null) {
            return null;
        }
        UserManager userManager = (UserManager) context.getSystemService("user");
        LockPatternUtils lockPatternUtils = new LockPatternUtils(context);
        if (userManager.getUserInfo(i2).isManagedProfile()) {
            List<ComponentName> activeAdminsAsUser = devicePolicyManager.getActiveAdminsAsUser(i2);
            if (activeAdminsAsUser == null) {
                return null;
            }
            for (ComponentName componentName : activeAdminsAsUser) {
                if ((devicePolicyManager.getKeyguardDisabledFeatures(componentName, i2) & i) != 0) {
                    if (enforcedAdmin == null) {
                        enforcedAdmin = new EnforcedAdmin(componentName, i2);
                    } else {
                        return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                    }
                }
            }
        } else {
            for (UserInfo userInfo : userManager.getProfiles(i2)) {
                List<ComponentName> activeAdminsAsUser2 = devicePolicyManager.getActiveAdminsAsUser(userInfo.id);
                if (activeAdminsAsUser2 != null) {
                    boolean zIsSeparateProfileChallengeEnabled = lockPatternUtils.isSeparateProfileChallengeEnabled(userInfo.id);
                    for (ComponentName componentName2 : activeAdminsAsUser2) {
                        if (zIsSeparateProfileChallengeEnabled || (devicePolicyManager.getKeyguardDisabledFeatures(componentName2, userInfo.id) & i) == 0) {
                            if (userInfo.isManagedProfile() && (devicePolicyManager.getParentProfileInstance(userInfo).getKeyguardDisabledFeatures(componentName2, userInfo.id) & i) != 0) {
                                if (enforcedAdmin == null) {
                                    enforcedAdmin = new EnforcedAdmin(componentName2, userInfo.id);
                                } else {
                                    return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                                }
                            }
                        } else if (enforcedAdmin == null) {
                            enforcedAdmin = new EnforcedAdmin(componentName2, userInfo.id);
                        } else {
                            return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                        }
                    }
                }
            }
        }
        return enforcedAdmin;
    }

    public static EnforcedAdmin checkIfUninstallBlocked(Context context, String str, int i) {
        EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = checkIfRestrictionEnforced(context, "no_control_apps", i);
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            return enforcedAdminCheckIfRestrictionEnforced;
        }
        EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced2 = checkIfRestrictionEnforced(context, "no_uninstall_apps", i);
        if (enforcedAdminCheckIfRestrictionEnforced2 != null) {
            return enforcedAdminCheckIfRestrictionEnforced2;
        }
        try {
            if (AppGlobals.getPackageManager().getBlockUninstallForUser(str, i)) {
                return getProfileOrDeviceOwner(context, i);
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public static EnforcedAdmin checkIfApplicationIsSuspended(Context context, String str, int i) {
        try {
            if (AppGlobals.getPackageManager().isPackageSuspendedForUser(str, i)) {
                return getProfileOrDeviceOwner(context, i);
            }
            return null;
        } catch (RemoteException | IllegalArgumentException unused) {
            return null;
        }
    }

    public static EnforcedAdmin checkIfInputMethodDisallowed(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null) {
            return null;
        }
        EnforcedAdmin profileOrDeviceOwner = getProfileOrDeviceOwner(context, i);
        boolean zIsInputMethodPermittedByAdmin = profileOrDeviceOwner != null ? devicePolicyManager.isInputMethodPermittedByAdmin(profileOrDeviceOwner.component, str, i) : true;
        int managedProfileId = getManagedProfileId(context, i);
        EnforcedAdmin profileOrDeviceOwner2 = getProfileOrDeviceOwner(context, managedProfileId);
        boolean zIsInputMethodPermittedByAdmin2 = profileOrDeviceOwner2 != null ? devicePolicyManager.isInputMethodPermittedByAdmin(profileOrDeviceOwner2.component, str, managedProfileId) : true;
        if (!zIsInputMethodPermittedByAdmin && !zIsInputMethodPermittedByAdmin2) {
            return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
        }
        if (!zIsInputMethodPermittedByAdmin) {
            return profileOrDeviceOwner;
        }
        if (zIsInputMethodPermittedByAdmin2) {
            return null;
        }
        return profileOrDeviceOwner2;
    }

    public static EnforcedAdmin checkIfRemoteContactSearchDisallowed(Context context, int i) {
        EnforcedAdmin profileOwner;
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null || (profileOwner = getProfileOwner(context, i)) == null) {
            return null;
        }
        UserHandle userHandleOf = UserHandle.of(i);
        if (devicePolicyManager.getCrossProfileContactsSearchDisabled(userHandleOf) && devicePolicyManager.getCrossProfileCallerIdDisabled(userHandleOf)) {
            return profileOwner;
        }
        return null;
    }

    public static EnforcedAdmin checkIfAccessibilityServiceDisallowed(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null) {
            return null;
        }
        EnforcedAdmin profileOrDeviceOwner = getProfileOrDeviceOwner(context, i);
        boolean zIsAccessibilityServicePermittedByAdmin = profileOrDeviceOwner != null ? devicePolicyManager.isAccessibilityServicePermittedByAdmin(profileOrDeviceOwner.component, str, i) : true;
        int managedProfileId = getManagedProfileId(context, i);
        EnforcedAdmin profileOrDeviceOwner2 = getProfileOrDeviceOwner(context, managedProfileId);
        boolean zIsAccessibilityServicePermittedByAdmin2 = profileOrDeviceOwner2 != null ? devicePolicyManager.isAccessibilityServicePermittedByAdmin(profileOrDeviceOwner2.component, str, managedProfileId) : true;
        if (!zIsAccessibilityServicePermittedByAdmin && !zIsAccessibilityServicePermittedByAdmin2) {
            return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
        }
        if (!zIsAccessibilityServicePermittedByAdmin) {
            return profileOrDeviceOwner;
        }
        if (zIsAccessibilityServicePermittedByAdmin2) {
            return null;
        }
        return profileOrDeviceOwner2;
    }

    private static int getManagedProfileId(Context context, int i) {
        for (UserInfo userInfo : ((UserManager) context.getSystemService("user")).getProfiles(i)) {
            if (userInfo.id != i && userInfo.isManagedProfile()) {
                return userInfo.id;
            }
        }
        return -10000;
    }

    public static EnforcedAdmin checkIfAccountManagementDisabled(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager;
        if (str == null || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        boolean z = false;
        for (String str2 : devicePolicyManager.getAccountTypesWithManagementDisabledAsUser(i)) {
            if (str.equals(str2)) {
                z = true;
                break;
            }
        }
        if (z) {
            return getProfileOrDeviceOwner(context, i);
        }
        return null;
    }

    public static EnforcedAdmin checkIfAutoTimeRequired(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null || !devicePolicyManager.getAutoTimeRequired()) {
            return null;
        }
        return new EnforcedAdmin(devicePolicyManager.getDeviceOwnerComponentOnCallingUser(), UserHandle.myUserId());
    }

    public static EnforcedAdmin checkIfPasswordQualityIsSet(Context context, int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        EnforcedAdmin enforcedAdmin = null;
        if (devicePolicyManager == null) {
            return null;
        }
        LockPatternUtils lockPatternUtils = new LockPatternUtils(context);
        if (lockPatternUtils.isSeparateProfileChallengeEnabled(i)) {
            List<ComponentName> activeAdminsAsUser = devicePolicyManager.getActiveAdminsAsUser(i);
            if (activeAdminsAsUser == null) {
                return null;
            }
            for (ComponentName componentName : activeAdminsAsUser) {
                if (devicePolicyManager.getPasswordQuality(componentName, i) > 0) {
                    if (enforcedAdmin == null) {
                        enforcedAdmin = new EnforcedAdmin(componentName, i);
                    } else {
                        return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                    }
                }
            }
        } else {
            for (UserInfo userInfo : ((UserManager) context.getSystemService("user")).getProfiles(i)) {
                List<ComponentName> activeAdminsAsUser2 = devicePolicyManager.getActiveAdminsAsUser(userInfo.id);
                if (activeAdminsAsUser2 != null) {
                    boolean zIsSeparateProfileChallengeEnabled = lockPatternUtils.isSeparateProfileChallengeEnabled(userInfo.id);
                    for (ComponentName componentName2 : activeAdminsAsUser2) {
                        if (zIsSeparateProfileChallengeEnabled || devicePolicyManager.getPasswordQuality(componentName2, userInfo.id) <= 0) {
                            if (userInfo.isManagedProfile() && devicePolicyManager.getParentProfileInstance(userInfo).getPasswordQuality(componentName2, userInfo.id) > 0) {
                                if (enforcedAdmin == null) {
                                    enforcedAdmin = new EnforcedAdmin(componentName2, userInfo.id);
                                } else {
                                    return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                                }
                            }
                        } else if (enforcedAdmin == null) {
                            enforcedAdmin = new EnforcedAdmin(componentName2, userInfo.id);
                        } else {
                            return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                        }
                    }
                }
            }
        }
        return enforcedAdmin;
    }

    public static EnforcedAdmin checkIfMaximumTimeToLockIsSet(Context context) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        EnforcedAdmin enforcedAdmin = null;
        if (devicePolicyManager == null) {
            return null;
        }
        new LockPatternUtils(context);
        List profiles = UserManager.get(context).getProfiles(UserHandle.myUserId());
        int size = profiles.size();
        for (int i = 0; i < size; i++) {
            UserInfo userInfo = (UserInfo) profiles.get(i);
            List<ComponentName> activeAdminsAsUser = devicePolicyManager.getActiveAdminsAsUser(userInfo.id);
            if (activeAdminsAsUser != null) {
                for (ComponentName componentName : activeAdminsAsUser) {
                    if (devicePolicyManager.getMaximumTimeToLock(componentName, userInfo.id) > 0) {
                        if (enforcedAdmin == null) {
                            enforcedAdmin = new EnforcedAdmin(componentName, userInfo.id);
                        } else {
                            return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                        }
                    } else if (userInfo.isManagedProfile() && devicePolicyManager.getParentProfileInstance(userInfo).getMaximumTimeToLock(componentName, userInfo.id) > 0) {
                        if (enforcedAdmin == null) {
                            enforcedAdmin = new EnforcedAdmin(componentName, userInfo.id);
                        } else {
                            return EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                        }
                    }
                }
            }
        }
        return enforcedAdmin;
    }

    public static EnforcedAdmin getProfileOrDeviceOwner(Context context, int i) {
        DevicePolicyManager devicePolicyManager;
        ComponentName deviceOwnerComponentOnAnyUser;
        if (i == -10000 || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        ComponentName profileOwnerAsUser = devicePolicyManager.getProfileOwnerAsUser(i);
        if (profileOwnerAsUser != null) {
            return new EnforcedAdmin(profileOwnerAsUser, i);
        }
        if (devicePolicyManager.getDeviceOwnerUserId() != i || (deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == null) {
            return null;
        }
        return new EnforcedAdmin(deviceOwnerComponentOnAnyUser, i);
    }

    public static EnforcedAdmin getDeviceOwner(Context context) {
        ComponentName deviceOwnerComponentOnAnyUser;
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null || (deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == null) {
            return null;
        }
        return new EnforcedAdmin(deviceOwnerComponentOnAnyUser, devicePolicyManager.getDeviceOwnerUserId());
    }

    private static EnforcedAdmin getProfileOwner(Context context, int i) {
        DevicePolicyManager devicePolicyManager;
        ComponentName profileOwnerAsUser;
        if (i == -10000 || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null || (profileOwnerAsUser = devicePolicyManager.getProfileOwnerAsUser(i)) == null) {
            return null;
        }
        return new EnforcedAdmin(profileOwnerAsUser, i);
    }

    public static void setMenuItemAsDisabledByAdmin(final Context context, MenuItem menuItem, final EnforcedAdmin enforcedAdmin) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(menuItem.getTitle());
        removeExistingRestrictedSpans(spannableStringBuilder);
        if (enforcedAdmin != null) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getColor(R.color.disabled_text_color)), 0, spannableStringBuilder.length(), 33);
            spannableStringBuilder.append(" ", new RestrictedLockImageSpan(context), 33);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.android.settingslib.RestrictedLockUtils.1
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem2) {
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(context, enforcedAdmin);
                    return true;
                }
            });
        } else {
            menuItem.setOnMenuItemClickListener(null);
        }
        menuItem.setTitle(spannableStringBuilder);
    }

    private static void removeExistingRestrictedSpans(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length();
        for (RestrictedLockImageSpan restrictedLockImageSpan : (RestrictedLockImageSpan[]) spannableStringBuilder.getSpans(length - 1, length, RestrictedLockImageSpan.class)) {
            int spanStart = spannableStringBuilder.getSpanStart(restrictedLockImageSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(restrictedLockImageSpan);
            spannableStringBuilder.removeSpan(restrictedLockImageSpan);
            spannableStringBuilder.delete(spanStart, spanEnd);
        }
        for (ForegroundColorSpan foregroundColorSpan : (ForegroundColorSpan[]) spannableStringBuilder.getSpans(0, length, ForegroundColorSpan.class)) {
            spannableStringBuilder.removeSpan(foregroundColorSpan);
        }
    }

    public static void sendShowAdminSupportDetailsIntent(Context context, EnforcedAdmin enforcedAdmin) {
        Intent showAdminSupportDetailsIntent = getShowAdminSupportDetailsIntent(context, enforcedAdmin);
        int iMyUserId = UserHandle.myUserId();
        if (enforcedAdmin != null && enforcedAdmin.userId != -10000 && isCurrentUserOrProfile(context, enforcedAdmin.userId)) {
            iMyUserId = enforcedAdmin.userId;
        }
        context.startActivityAsUser(showAdminSupportDetailsIntent, new UserHandle(iMyUserId));
    }

    public static Intent getShowAdminSupportDetailsIntent(Context context, EnforcedAdmin enforcedAdmin) {
        Intent intent = new Intent("android.settings.SHOW_ADMIN_SUPPORT_DETAILS");
        if (enforcedAdmin != null) {
            if (enforcedAdmin.component != null) {
                intent.putExtra("android.app.extra.DEVICE_ADMIN", enforcedAdmin.component);
            }
            int iMyUserId = UserHandle.myUserId();
            if (enforcedAdmin.userId != -10000) {
                iMyUserId = enforcedAdmin.userId;
            }
            intent.putExtra("android.intent.extra.USER_ID", iMyUserId);
        }
        return intent;
    }

    public static boolean isCurrentUserOrProfile(Context context, int i) {
        Iterator it = UserManager.get(context).getProfiles(UserHandle.myUserId()).iterator();
        while (it.hasNext()) {
            if (((UserInfo) it.next()).id == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAdminInCurrentUserOrProfile(Context context, ComponentName componentName) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        Iterator it = UserManager.get(context).getProfiles(UserHandle.myUserId()).iterator();
        while (it.hasNext()) {
            if (devicePolicyManager.isAdminActiveAsUser(componentName, ((UserInfo) it.next()).id)) {
                return true;
            }
        }
        return false;
    }

    public static void setTextViewPadlock(Context context, TextView textView, boolean z) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
        removeExistingRestrictedSpans(spannableStringBuilder);
        if (z) {
            spannableStringBuilder.append(" ", new RestrictedLockImageSpan(context), 33);
        }
        textView.setText(spannableStringBuilder);
    }

    public static void setTextViewAsDisabledByAdmin(Context context, TextView textView, boolean z) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
        removeExistingRestrictedSpans(spannableStringBuilder);
        if (z) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getColor(R.color.disabled_text_color)), 0, spannableStringBuilder.length(), 33);
            textView.setCompoundDrawables(null, null, getRestrictedPadlock(context), null);
            textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelSize(R.dimen.restricted_icon_padding));
        } else {
            textView.setCompoundDrawables(null, null, null, null);
        }
        textView.setText(spannableStringBuilder);
    }

    public static class EnforcedAdmin {
        public static final EnforcedAdmin MULTIPLE_ENFORCED_ADMIN = new EnforcedAdmin();
        public ComponentName component;
        public int userId;

        public EnforcedAdmin(ComponentName componentName, int i) {
            this.component = null;
            this.userId = -10000;
            this.component = componentName;
            this.userId = i;
        }

        public EnforcedAdmin(EnforcedAdmin enforcedAdmin) {
            this.component = null;
            this.userId = -10000;
            if (enforcedAdmin == null) {
                throw new IllegalArgumentException();
            }
            this.component = enforcedAdmin.component;
            this.userId = enforcedAdmin.userId;
        }

        public EnforcedAdmin() {
            this.component = null;
            this.userId = -10000;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnforcedAdmin)) {
                return false;
            }
            EnforcedAdmin enforcedAdmin = (EnforcedAdmin) obj;
            if (this.userId != enforcedAdmin.userId) {
                return false;
            }
            return (this.component == null && enforcedAdmin.component == null) || (this.component != null && this.component.equals(enforcedAdmin.component));
        }

        public String toString() {
            return "EnforcedAdmin{component=" + this.component + ",userId=" + this.userId + "}";
        }

        public void copyTo(EnforcedAdmin enforcedAdmin) {
            if (enforcedAdmin == null) {
                throw new IllegalArgumentException();
            }
            enforcedAdmin.component = this.component;
            enforcedAdmin.userId = this.userId;
        }
    }
}
