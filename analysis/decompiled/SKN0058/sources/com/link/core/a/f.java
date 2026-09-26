package com.link.core.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    /* JADX WARN: Code duplicated, block: B:100:0x0198 A[Catch: all -> 0x01a4, TRY_LEAVE, TryCatch #2 {all -> 0x01a4, blocks: (B:92:0x017c, B:95:0x0187, B:98:0x018e, B:100:0x0198), top: B:192:0x017c }] */
    /* JADX WARN: Code duplicated, block: B:102:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:105:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:108:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:111:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:114:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:115:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:118:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:119:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:11:0x0024  */
    /* JADX WARN: Code duplicated, block: B:122:0x0227  */
    /* JADX WARN: Code duplicated, block: B:123:0x022a  */
    /* JADX WARN: Code duplicated, block: B:126:0x0236  */
    /* JADX WARN: Code duplicated, block: B:127:0x023b  */
    /* JADX WARN: Code duplicated, block: B:130:0x0248  */
    /* JADX WARN: Code duplicated, block: B:133:0x0252  */
    /* JADX WARN: Code duplicated, block: B:134:0x0255  */
    /* JADX WARN: Code duplicated, block: B:137:0x0266  */
    /* JADX WARN: Code duplicated, block: B:140:0x0272  */
    /* JADX WARN: Code duplicated, block: B:143:0x02af A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:144:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:146:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:149:0x02c1  */
    /* JADX WARN: Code duplicated, block: B:152:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:162:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:165:0x0312  */
    /* JADX WARN: Code duplicated, block: B:166:0x0315  */
    /* JADX WARN: Code duplicated, block: B:169:0x0326  */
    /* JADX WARN: Code duplicated, block: B:170:0x0329  */
    /* JADX WARN: Code duplicated, block: B:173:0x0366 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:174:0x0367  */
    /* JADX WARN: Code duplicated, block: B:176:0x0374  */
    /* JADX WARN: Code duplicated, block: B:179:0x037b  */
    /* JADX WARN: Code duplicated, block: B:183:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:190:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:192:0x017c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:207:0x0171 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:211:0x016f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:214:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:219:0x012a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:225:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:57:0x00f4 A[Catch: all -> 0x0171, TryCatch #0 {all -> 0x0171, blocks: (B:54:0x00e2, B:55:0x00ee, B:57:0x00f4, B:60:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x0125, B:68:0x012a, B:69:0x0130, B:71:0x013a, B:74:0x0143, B:77:0x014d, B:79:0x0153, B:80:0x0158, B:81:0x0163, B:83:0x0169), top: B:190:0x00e2 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0117 A[Catch: all -> 0x0171, TryCatch #0 {all -> 0x0171, blocks: (B:54:0x00e2, B:55:0x00ee, B:57:0x00f4, B:60:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x0125, B:68:0x012a, B:69:0x0130, B:71:0x013a, B:74:0x0143, B:77:0x014d, B:79:0x0153, B:80:0x0158, B:81:0x0163, B:83:0x0169), top: B:190:0x00e2 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0125 A[Catch: all -> 0x0171, TryCatch #0 {all -> 0x0171, blocks: (B:54:0x00e2, B:55:0x00ee, B:57:0x00f4, B:60:0x0107, B:63:0x010e, B:65:0x0117, B:67:0x0125, B:68:0x012a, B:69:0x0130, B:71:0x013a, B:74:0x0143, B:77:0x014d, B:79:0x0153, B:80:0x0158, B:81:0x0163, B:83:0x0169), top: B:190:0x00e2 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x0168  */
    /* JADX WARN: Code duplicated, block: B:90:0x0179 A[DONT_INVERT] */
    public static String a(String str, Object obj) throws Throwable {
        String string;
        String string2;
        String strC;
        String str2;
        String str3;
        String str4;
        String str5;
        int i;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        String string3;
        String str16;
        String string4;
        String str17;
        String str18;
        String str19;
        String str20;
        String string5;
        String string6;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String macAddress;
        Iterator it;
        NetworkInterface networkInterface;
        byte[] hardwareAddress;
        StringBuilder sb;
        int i2;
        String upperCase;
        String string7;
        String hexString;
        BufferedReader bufferedReader = null;
        Context applicationContext = obj instanceof Context ? ((Context) obj).getApplicationContext() : null;
        if (applicationContext == null) {
            string = "";
        } else {
            try {
                string = Settings.Secure.getString(applicationContext.getContentResolver(), "android_id");
                if (string == null) {
                    string = "";
                }
            } catch (Throwable unused) {
            }
        }
        if (applicationContext != null) {
            try {
                SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("device_id.xml", 0);
                string2 = sharedPreferences.getString("device_id", null);
                if (a(string2)) {
                    string2 = a(string, str);
                    sharedPreferences.edit().putString("device_id", string2).apply();
                }
            } catch (Throwable unused2) {
                string2 = a(string, str);
            }
        } else {
            string2 = a(string, str);
        }
        String strA = j.a(string2);
        String strA2 = j.a(string2 + "assdk_huang");
        String[] strArr = {"", ""};
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
                try {
                    String line = bufferedReader2.readLine();
                    String line2 = bufferedReader2.readLine();
                    if (line != null) {
                        String[] strArrSplit = line.split("\\s+");
                        StringBuilder sb2 = new StringBuilder();
                        for (int i3 = 2; i3 < strArrSplit.length; i3++) {
                            sb2.append(strArrSplit[i3]);
                        }
                        strArr[0] = sb2.toString();
                    }
                    if (line2 != null) {
                        String[] strArrSplit2 = line2.split("\\s+");
                        if (strArrSplit2.length > 2) {
                            strArr[1] = strArrSplit2[2];
                        }
                    }
                    bufferedReader2.close();
                } catch (IOException unused3) {
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    strC = c("/sys/class/net/eth0/address");
                    if (a(strC)) {
                        strC = c("/sys/class/net/wlan0/address");
                        if (a(strC)) {
                            try {
                                it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        strC = "";
                                        break;
                                    }
                                    networkInterface = (NetworkInterface) it.next();
                                    if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                                        sb = new StringBuilder();
                                        for (byte b : hardwareAddress) {
                                            hexString = Integer.toHexString(b & 255);
                                            if (hexString.length() == 1) {
                                                sb.append('0');
                                            }
                                            sb.append(hexString);
                                        }
                                        upperCase = sb.toString().toUpperCase();
                                        if (upperCase != null) {
                                            string7 = "";
                                        } else {
                                            string7 = "";
                                        }
                                        if (b(string7)) {
                                            strC = upperCase;
                                            break;
                                        }
                                    }
                                }
                            } catch (Throwable unused4) {
                            }
                            if (a(strC)) {
                                if (applicationContext == null) {
                                    strC = "";
                                } else {
                                    try {
                                        wifiManager = (WifiManager) applicationContext.getSystemService("wifi");
                                        if (wifiManager != null) {
                                            macAddress = connectionInfo.getMacAddress();
                                            if (b(macAddress)) {
                                                strC = macAddress.toUpperCase().replace(":", "");
                                            }
                                        }
                                    } catch (Throwable unused5) {
                                    }
                                    strC = "";
                                }
                            }
                        }
                    }
                    if (a(strC)) {
                        strC = string;
                    }
                    JSONObject jSONObject = new JSONObject();
                    str2 = strArr[0];
                    if (str2 == null) {
                        str2 = "";
                    }
                    a(jSONObject, "cpu", str2);
                    a(jSONObject, "mem", "");
                    str3 = strArr[1];
                    if (str3 == null) {
                        str3 = "";
                    }
                    a(jSONObject, "cpuid", str3);
                    a(jSONObject, "os", "android");
                    str4 = Build.VERSION.RELEASE;
                    if (str4 == null) {
                        str5 = "";
                    } else {
                        str5 = str4;
                    }
                    a(jSONObject, "systemVersion", str5);
                    i = Build.VERSION.SDK_INT;
                    a(jSONObject, "sdkVersion", Integer.valueOf(i));
                    str6 = Build.MODEL;
                    if (str6 == null) {
                        str7 = "";
                    } else {
                        str7 = str6;
                    }
                    a(jSONObject, "deviceModel", str7);
                    Object obj2 = a.a;
                    a(jSONObject, "pluginVersion", 24032810107L);
                    a(jSONObject, "bandwidth", 0);
                    a(jSONObject, "mac", strC);
                    a(jSONObject, "spare1", "m02x_assdkhuang");
                    StringBuilder sb3 = new StringBuilder();
                    str8 = Build.SERIAL;
                    str9 = strC;
                    if (str8 == null) {
                        str10 = "";
                    } else {
                        str10 = str8;
                    }
                    sb3.append(str10);
                    sb3.append("#*#");
                    if (str6 == null) {
                        str11 = "";
                    } else {
                        str11 = str6;
                    }
                    sb3.append(str11);
                    sb3.append("#*#");
                    str12 = Build.FINGERPRINT;
                    if (str12 == null) {
                        str12 = "";
                    }
                    sb3.append(str12);
                    sb3.append("#*#");
                    if (str4 == null) {
                        str13 = "";
                    } else {
                        str13 = str4;
                    }
                    sb3.append(str13);
                    sb3.append("#*#");
                    sb3.append(i);
                    sb3.append("#*#");
                    str14 = Build.ID;
                    if (str14 == null) {
                        str14 = "";
                    }
                    sb3.append(str14);
                    sb3.append("#*#");
                    str15 = Build.DISPLAY;
                    if (str15 == null) {
                        str15 = "";
                    }
                    sb3.append(str15);
                    a(jSONObject, "spare2", sb3.toString());
                    a(jSONObject, "spare3", strA);
                    a(jSONObject, "cpumd5", strA2);
                    a(jSONObject, "androidid", string);
                    a(jSONObject, "channelId", "m02x_assdkhuang");
                    a(jSONObject, "name", strA2);
                    string3 = jSONObject.toString();
                    if (d(string3) <= 2048) {
                        return string3;
                    }
                    StringBuilder sb4 = new StringBuilder();
                    if (str6 == null) {
                        str6 = "";
                    }
                    sb4.append(str6);
                    sb4.append("#*#");
                    if (str4 == null) {
                        str4 = "";
                    }
                    sb4.append(str4);
                    sb4.append("#*#");
                    sb4.append(i);
                    sb4.append("#*#");
                    str16 = Build.ID;
                    if (str16 == null) {
                        str16 = "";
                    }
                    sb4.append(str16);
                    string4 = sb4.toString();
                    if (string4 != null) {
                        string4 = "";
                    } else {
                        string4 = "";
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    a(jSONObject2, "name", strA2);
                    a(jSONObject2, "cpumd5", strA2);
                    a(jSONObject2, "os", "android");
                    str17 = Build.VERSION.RELEASE;
                    if (str17 == null) {
                        str18 = "";
                    } else {
                        str18 = str17;
                    }
                    a(jSONObject2, "systemVersion", str18);
                    a(jSONObject2, "sdkVersion", Integer.valueOf(Build.VERSION.SDK_INT));
                    str19 = Build.MODEL;
                    if (str19 == null) {
                        str20 = "";
                    } else {
                        str20 = str19;
                    }
                    a(jSONObject2, "deviceModel", str20);
                    Object obj3 = a.a;
                    a(jSONObject2, "pluginVersion", 24032810107L);
                    a(jSONObject2, "mac", str9);
                    a(jSONObject2, "androidid", string);
                    a(jSONObject2, "spare2", string4);
                    a(jSONObject2, "spare3", strA);
                    a(jSONObject2, "channelId", "m02x_assdkhuang");
                    string5 = jSONObject2.toString();
                    if (d(string5) <= 2048) {
                        return string5;
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    a(jSONObject3, "name", strA2);
                    a(jSONObject3, "cpumd5", strA2);
                    if (str17 == null) {
                        str17 = "";
                    }
                    a(jSONObject3, "systemVersion", str17);
                    if (str19 == null) {
                        str19 = "";
                    }
                    a(jSONObject3, "deviceModel", str19);
                    a(jSONObject3, "pluginVersion", 24032810107L);
                    a(jSONObject3, "androidid", string);
                    a(jSONObject3, "spare3", strA);
                    a(jSONObject3, "channelId", "m02x_assdkhuang");
                    string6 = jSONObject3.toString();
                    if (d(string6) <= 2048) {
                        return string6;
                    }
                    JSONObject jSONObject4 = new JSONObject();
                    a(jSONObject4, "name", "");
                    a(jSONObject4, "channelId", "m02x_assdkhuang");
                    return jSONObject4.toString();
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused6) {
                        }
                    }
                    throw th;
                }
            } catch (IOException unused7) {
            }
        } catch (IOException unused8) {
        } catch (Throwable th2) {
            th = th2;
        }
        strC = c("/sys/class/net/eth0/address");
        if (a(strC)) {
            strC = c("/sys/class/net/wlan0/address");
            if (a(strC)) {
                it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                while (true) {
                    if (it.hasNext()) {
                        strC = "";
                        break;
                    }
                    networkInterface = (NetworkInterface) it.next();
                    if ("wlan0".equalsIgnoreCase(networkInterface.getName()) && (hardwareAddress = networkInterface.getHardwareAddress()) != null) {
                        sb = new StringBuilder();
                        while (i2 < r11) {
                            hexString = Integer.toHexString(b & 255);
                            if (hexString.length() == 1) {
                                sb.append('0');
                            }
                            sb.append(hexString);
                        }
                        upperCase = sb.toString().toUpperCase();
                        if (upperCase != null || upperCase.length() < 12) {
                            string7 = "";
                        } else {
                            StringBuilder sb5 = new StringBuilder();
                            int i4 = 0;
                            while (i4 < 12) {
                                if (sb5.length() > 0) {
                                    sb5.append(':');
                                }
                                int i5 = i4 + 2;
                                sb5.append(upperCase.substring(i4, i5));
                                i4 = i5;
                            }
                            string7 = sb5.toString();
                        }
                        if (b(string7)) {
                            strC = upperCase;
                            break;
                        }
                    }
                }
                if (a(strC)) {
                    if (applicationContext == null) {
                        strC = "";
                    } else {
                        wifiManager = (WifiManager) applicationContext.getSystemService("wifi");
                        if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                            macAddress = connectionInfo.getMacAddress();
                            if (b(macAddress)) {
                                strC = macAddress.toUpperCase().replace(":", "");
                            }
                        }
                        strC = "";
                    }
                }
            }
        }
        if (a(strC)) {
            strC = string;
        }
        JSONObject jSONObject5 = new JSONObject();
        str2 = strArr[0];
        if (str2 == null) {
            str2 = "";
        }
        a(jSONObject5, "cpu", str2);
        a(jSONObject5, "mem", "");
        str3 = strArr[1];
        if (str3 == null) {
            str3 = "";
        }
        a(jSONObject5, "cpuid", str3);
        a(jSONObject5, "os", "android");
        str4 = Build.VERSION.RELEASE;
        if (str4 == null) {
            str5 = "";
        } else {
            str5 = str4;
        }
        a(jSONObject5, "systemVersion", str5);
        i = Build.VERSION.SDK_INT;
        a(jSONObject5, "sdkVersion", Integer.valueOf(i));
        str6 = Build.MODEL;
        if (str6 == null) {
            str7 = "";
        } else {
            str7 = str6;
        }
        a(jSONObject5, "deviceModel", str7);
        Object obj4 = a.a;
        a(jSONObject5, "pluginVersion", 24032810107L);
        a(jSONObject5, "bandwidth", 0);
        a(jSONObject5, "mac", strC);
        a(jSONObject5, "spare1", "m02x_assdkhuang");
        StringBuilder sb6 = new StringBuilder();
        str8 = Build.SERIAL;
        str9 = strC;
        if (str8 == null) {
            str10 = "";
        } else {
            str10 = str8;
        }
        sb6.append(str10);
        sb6.append("#*#");
        if (str6 == null) {
            str11 = "";
        } else {
            str11 = str6;
        }
        sb6.append(str11);
        sb6.append("#*#");
        str12 = Build.FINGERPRINT;
        if (str12 == null) {
            str12 = "";
        }
        sb6.append(str12);
        sb6.append("#*#");
        if (str4 == null) {
            str13 = "";
        } else {
            str13 = str4;
        }
        sb6.append(str13);
        sb6.append("#*#");
        sb6.append(i);
        sb6.append("#*#");
        str14 = Build.ID;
        if (str14 == null) {
            str14 = "";
        }
        sb6.append(str14);
        sb6.append("#*#");
        str15 = Build.DISPLAY;
        if (str15 == null) {
            str15 = "";
        }
        sb6.append(str15);
        a(jSONObject5, "spare2", sb6.toString());
        a(jSONObject5, "spare3", strA);
        a(jSONObject5, "cpumd5", strA2);
        a(jSONObject5, "androidid", string);
        a(jSONObject5, "channelId", "m02x_assdkhuang");
        a(jSONObject5, "name", strA2);
        string3 = jSONObject5.toString();
        if (d(string3) <= 2048) {
            return string3;
        }
        StringBuilder sb7 = new StringBuilder();
        if (str6 == null) {
            str6 = "";
        }
        sb7.append(str6);
        sb7.append("#*#");
        if (str4 == null) {
            str4 = "";
        }
        sb7.append(str4);
        sb7.append("#*#");
        sb7.append(i);
        sb7.append("#*#");
        str16 = Build.ID;
        if (str16 == null) {
            str16 = "";
        }
        sb7.append(str16);
        string4 = sb7.toString();
        if (string4 != null || string4.length() == 0) {
            string4 = "";
        } else {
            while (d(string4) > 42 && string4.length() > 0) {
                string4 = string4.substring(0, string4.length() - 1);
            }
        }
        JSONObject jSONObject6 = new JSONObject();
        a(jSONObject6, "name", strA2);
        a(jSONObject6, "cpumd5", strA2);
        a(jSONObject6, "os", "android");
        str17 = Build.VERSION.RELEASE;
        if (str17 == null) {
            str18 = "";
        } else {
            str18 = str17;
        }
        a(jSONObject6, "systemVersion", str18);
        a(jSONObject6, "sdkVersion", Integer.valueOf(Build.VERSION.SDK_INT));
        str19 = Build.MODEL;
        if (str19 == null) {
            str20 = "";
        } else {
            str20 = str19;
        }
        a(jSONObject6, "deviceModel", str20);
        Object obj5 = a.a;
        a(jSONObject6, "pluginVersion", 24032810107L);
        a(jSONObject6, "mac", str9);
        a(jSONObject6, "androidid", string);
        a(jSONObject6, "spare2", string4);
        a(jSONObject6, "spare3", strA);
        a(jSONObject6, "channelId", "m02x_assdkhuang");
        string5 = jSONObject6.toString();
        if (d(string5) <= 2048) {
            return string5;
        }
        JSONObject jSONObject7 = new JSONObject();
        a(jSONObject7, "name", strA2);
        a(jSONObject7, "cpumd5", strA2);
        if (str17 == null) {
            str17 = "";
        }
        a(jSONObject7, "systemVersion", str17);
        if (str19 == null) {
            str19 = "";
        }
        a(jSONObject7, "deviceModel", str19);
        a(jSONObject7, "pluginVersion", 24032810107L);
        a(jSONObject7, "androidid", string);
        a(jSONObject7, "spare3", strA);
        a(jSONObject7, "channelId", "m02x_assdkhuang");
        string6 = jSONObject7.toString();
        if (d(string6) <= 2048) {
            return string6;
        }
        JSONObject jSONObject8 = new JSONObject();
        a(jSONObject8, "name", "");
        a(jSONObject8, "channelId", "m02x_assdkhuang");
        return jSONObject8.toString();
    }

    public static String a(String str, String str2) {
        if (a(str) || "9774d56d682e549c".equals(str)) {
            str = str2;
        }
        if (a(str)) {
            str = Build.BOARD + Build.BRAND + Build.DEVICE + Build.MODEL + Build.PRODUCT;
        }
        return UUID.nameUUIDFromBytes(str.getBytes(StandardCharsets.UTF_8)).toString();
    }

    public static void a(JSONObject jSONObject, String str, Object obj) {
        if (obj == null) {
            obj = "";
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException unused) {
        }
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean b(String str) {
        if (a(str)) {
            return false;
        }
        String upperCase = str.toUpperCase();
        if ("02:00:00:00:00:00".equals(upperCase) || "00:00:00:00:00:00".equals(upperCase) || "FF:FF:FF:FF:FF:FF".equals(upperCase)) {
            return false;
        }
        return upperCase.matches("([0-9A-F]{2}[:-]){5}([0-9A-F]{2})");
    }

    public static String c(String str) throws Throwable {
        Throwable th;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(str);
            if (!file.exists()) {
                return "";
            }
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
            try {
                String line = bufferedReader2.readLine();
                if (line == null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused) {
                    }
                    return "";
                }
                String upperCase = line.trim().toUpperCase();
                if (upperCase.length() >= 17) {
                    upperCase = upperCase.substring(0, 17);
                }
                String strReplace = b(upperCase) ? upperCase.replace(":", "") : "";
                try {
                    bufferedReader2.close();
                } catch (IOException unused2) {
                }
                return strReplace;
            } catch (IOException unused3) {
                bufferedReader = bufferedReader2;
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                if (bufferedReader == null) {
                    throw th;
                }
                try {
                    bufferedReader.close();
                    throw th;
                } catch (IOException unused4) {
                    throw th;
                }
            }
        } catch (IOException unused5) {
        } catch (Throwable th3) {
            th = th3;
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused6) {
            }
        }
        return "";
    }

    public static int d(String str) {
        if (str == null) {
            return 0;
        }
        return str.getBytes(StandardCharsets.UTF_8).length;
    }
}
