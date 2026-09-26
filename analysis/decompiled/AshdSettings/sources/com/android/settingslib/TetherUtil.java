package com.android.settingslib;

import android.content.Context;
import android.os.SystemProperties;
import android.telephony.CarrierConfigManager;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class TetherUtil {
    private static boolean isEntitlementCheckRequired(Context context) {
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
        if (carrierConfigManager.getConfig() == null) {
            Log.e("TetherUtil ", "is box ???, PersistableBundle is null!!!");
            return true;
        }
        return carrierConfigManager.getConfig().getBoolean("require_entitlement_checks_bool");
    }

    public static boolean isProvisioningNeeded(Context context) {
        String[] stringArray = context.getResources().getStringArray(android.R.array.config_autoBrightnessLevelsIdle);
        return !SystemProperties.getBoolean("net.tethering.noprovisioning", false) && stringArray != null && isEntitlementCheckRequired(context) && stringArray.length == 2;
    }
}
