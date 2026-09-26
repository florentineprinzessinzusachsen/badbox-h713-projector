package com.rk_itvui.settings.network;

import android.content.ContentResolver;
import android.content.Context;
import android.net.EthernetManager;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.NetworkUtils;
import android.net.StaticIpConfiguration;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class EthernetIP {
    public static final int ETHER_DHCP = 0;
    public static final int ETHER_PPPOE = 2;
    public static final int ETHER_STATIC = 1;
    public static final String defaultDNS1 = "8.8.8.8";
    public static final String defaultDNS2 = "202.38.64.1";
    public static final String defaultGateWay = "192.168.1.1";
    public static final String defaultIPAdress = "192.168.1.100";
    public static final String defaultIPNetMask = "255.255.255.0";
    public ContentResolver contentResolver;
    private Context context;
    public String mEthGateway;
    public String mEthIpAddress;
    public EthernetManager mEthManager;
    public String mEthNetmask;
    public String mEthdns1;
    public String mEthdns2;
    public String mPppoeAccount;
    public String mPppoePassword;
    public String mIface = "eth0";
    public String TAG = "EthernetIP";

    private void log(String str) {
        Log.d(this.TAG, str);
    }

    public boolean isUsingStaticIp() {
        return this.mEthManager.getConfiguration().getIpAssignment() == IpConfiguration.IpAssignment.STATIC;
    }

    public boolean isUsingPppoe() {
        return this.mEthManager.getConfiguration().getIpAssignment() == IpConfiguration.IpAssignment.PPPOE;
    }

    public boolean isConnected() {
        return ((EthernetManager) this.context.getSystemService("ethernet")).getEthernetConnectState() == 2;
    }

    public boolean switchEthernetMode(int i) {
        switch (i) {
            case 0:
                log("switch to dhcp");
                this.mEthManager.setConfiguration(new IpConfiguration(IpConfiguration.IpAssignment.DHCP, IpConfiguration.ProxySettings.NONE, null, null));
                break;
            case 1:
                log("switch to static IP");
                this.mEthManager.setConfiguration(setStaticIpConfiguration());
                break;
            case 2:
                log("switch to pppoe");
                this.mEthManager.setConfiguration(setPppoeIpConfiguration());
                break;
        }
        return true;
    }

    public boolean enableEthernetStaticIP(IpConfiguration ipConfiguration) {
        log("switch to staticIP");
        this.mEthManager.setConfiguration(ipConfiguration);
        return true;
    }

    public void transContext(Context context) {
        this.context = context;
        this.contentResolver = context.getContentResolver();
        this.mEthManager = (EthernetManager) context.getSystemService("ethernet");
        if (this.mEthManager == null) {
            Log.e("ehernetIP", "get ethernet manager failed");
        }
    }

    public String getIPAddress(char c) {
        return this.mEthManager.getIpAddress();
    }

    public String getGateWay(char c) {
        return this.mEthManager.getGateway();
    }

    public String getNetMask(char c) {
        return this.mEthManager.getNetmask();
    }

    public String getDNS1(char c) {
        String[] strArrSplit = this.mEthManager.getDns().split(",");
        if (strArrSplit.length == 0) {
            return null;
        }
        return strArrSplit[0];
    }

    public String getDNS2(char c) {
        String[] strArrSplit = this.mEthManager.getDns().split(",");
        if (strArrSplit.length <= 1) {
            return null;
        }
        return strArrSplit[1];
    }

    public String getEthMac() throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("sys/class/net/eth0/address"));
                try {
                    String line = bufferedReader2.readLine();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e) {
                            Log.e("EthernetIP", "close sys/class/net/eth0/address failed : " + e);
                        }
                    }
                    return line;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    Log.e("EthernetIP", "open sys/class/net/eth0/address failed : " + e);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            Log.e("EthernetIP", "close sys/class/net/eth0/address failed : " + e3);
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            Log.e("EthernetIP", "close sys/class/net/eth0/address failed : " + e4);
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public boolean setIPAddress(String str) {
        if (!isValidIpAddress(str)) {
            return false;
        }
        this.mEthIpAddress = str;
        return true;
    }

    public boolean setGateWay(String str) {
        if (!isValidIpAddress(str)) {
            return false;
        }
        this.mEthGateway = str;
        return true;
    }

    public boolean setNetMask(String str) {
        if (!isValidIpAddress(str)) {
            return false;
        }
        this.mEthNetmask = str;
        return true;
    }

    public boolean setDNS1(String str) {
        if (!isValidIpAddress(str)) {
            return false;
        }
        this.mEthdns1 = str;
        return true;
    }

    public boolean setDNS2(String str) {
        if (str.isEmpty()) {
            this.mEthdns2 = str;
            return true;
        }
        if (!isValidIpAddress(str)) {
            return false;
        }
        this.mEthdns2 = str;
        return true;
    }

    public boolean setPppoeAccount(String str) {
        this.mPppoeAccount = str;
        return true;
    }

    public boolean setPppoePassword(String str) {
        this.mPppoePassword = str;
        return true;
    }

    private boolean isValidIpAddress(String str) {
        int iIndexOf = str.indexOf(46);
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            if (-1 == iIndexOf) {
                iIndexOf = str.length();
            }
            try {
                int i3 = Integer.parseInt(str.substring(i, iIndexOf));
                if (i3 > 255 || i3 < 0) {
                    Log.w("EthernetIP", "isValidIpAddress() : invalid 'block', block = " + i3);
                    return false;
                }
                i2++;
                i = iIndexOf + 1;
                iIndexOf = str.indexOf(46, i);
            } catch (NumberFormatException e) {
                Log.w("EthernetIP", "isValidIpAddress() : e = " + e);
                return false;
            }
        }
        return i2 == 4;
    }

    private IpConfiguration setPppoeIpConfiguration() {
        IpConfiguration ipConfiguration = new IpConfiguration();
        if (this.mPppoeAccount.isEmpty() || this.mPppoePassword.isEmpty()) {
            log("pppoe account or password empty");
            return null;
        }
        log("mPppoeAccount = " + this.mPppoeAccount + ", mPppoePassword = " + this.mPppoePassword);
        ipConfiguration.pppoeAccount = this.mPppoeAccount;
        ipConfiguration.pppoePassword = this.mPppoePassword;
        ipConfiguration.ipAssignment = IpConfiguration.IpAssignment.PPPOE;
        log("ipConfig = " + ipConfiguration);
        return ipConfiguration;
    }

    private IpConfiguration setStaticIpConfiguration() {
        StaticIpConfiguration staticIpConfiguration = new StaticIpConfiguration();
        Inet4Address iPv4Address = getIPv4Address(this.mEthIpAddress);
        int iMaskStr2InetMask = maskStr2InetMask(this.mEthNetmask);
        Inet4Address iPv4Address2 = getIPv4Address(this.mEthGateway);
        Inet4Address iPv4Address3 = getIPv4Address(this.mEthdns1);
        if (iPv4Address.getAddress().toString().isEmpty() || iMaskStr2InetMask == 0 || iPv4Address2.toString().isEmpty() || iPv4Address3.toString().isEmpty()) {
            log("ip,mask or dnsAddr is wrong");
            return null;
        }
        String str = this.mEthdns2;
        staticIpConfiguration.ipAddress = new LinkAddress(iPv4Address, iMaskStr2InetMask);
        staticIpConfiguration.gateway = iPv4Address2;
        staticIpConfiguration.dnsServers.add(iPv4Address3);
        if (str != null && !str.isEmpty()) {
            staticIpConfiguration.dnsServers.add(getIPv4Address(str));
        }
        return new IpConfiguration(IpConfiguration.IpAssignment.STATIC, IpConfiguration.ProxySettings.NONE, staticIpConfiguration, null);
    }

    private int maskStr2InetMask(String str) {
        int iIndexOf;
        if (!Pattern.compile("(^((\\d|[01]?\\d\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|[01]?\\d\\d|2[0-4]\\d|25[0-5])$)|^(\\d|[1-2]\\d|3[0-2])$").matcher(str).matches()) {
            Log.e(this.TAG, "subMask is error");
            return 0;
        }
        String[] strArrSplit = str.split("\\.");
        int i = 0;
        for (String str2 : strArrSplit) {
            String string = new StringBuffer(Integer.toBinaryString(Integer.parseInt(str2))).reverse().toString();
            int i2 = 0;
            int i3 = 0;
            while (i2 < string.length() && (iIndexOf = string.indexOf("1", i2)) != -1) {
                i3++;
                i2 = iIndexOf + 1;
            }
            i += i3;
        }
        return i;
    }

    private Inet4Address getIPv4Address(String str) {
        try {
            return (Inet4Address) NetworkUtils.numericToInetAddress(str);
        } catch (ClassCastException | IllegalArgumentException unused) {
            return null;
        }
    }
}
