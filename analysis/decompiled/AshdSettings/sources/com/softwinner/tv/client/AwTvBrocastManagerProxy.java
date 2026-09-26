package com.softwinner.tv.client;

import android.os.IHwBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import vendor.aw.hardware.btvserver.V1_0.IBtvServer;
import vendor.aw.hardware.btvserver.V1_0.IBtvServerCallback;

/* JADX INFO: loaded from: classes.dex */
public class AwTvBrocastManagerProxy {
    public static final int BTV_SERVICE_CONNECTED = 1;
    public static final int BTV_SERVICE_CONNECTING = 2;
    public static final int BTV_SERVICE_UNCONNECTED = 0;
    private static final int SERVICE_COOKIE = 1000;
    private static final String TAG = "AwTvBrocastManagerProxy";
    private static AwTvBrocastManagerProxy sAwTvBrocastManagerProxy;
    private AwTvBrocastProxyDeathRecipient mDeathRecipient;
    private IBtvServer mTvBrocastService = null;
    private IBtvServerCallback mTvBrocastEventCallback = null;
    private ArrayList<AwTvBrocastServiceListener> mServiceListeners = new ArrayList<>();

    public interface AwTvBrocastServiceListener {
        void onServiceStatusChange(int i);
    }

    public static synchronized AwTvBrocastManagerProxy getInstance() {
        if (sAwTvBrocastManagerProxy == null) {
            sAwTvBrocastManagerProxy = new AwTvBrocastManagerProxy();
        }
        return sAwTvBrocastManagerProxy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyTvBrocastServiceStatus(int i) {
        Iterator<AwTvBrocastServiceListener> it = this.mServiceListeners.iterator();
        while (it.hasNext()) {
            it.next().onServiceStatusChange(i);
        }
    }

    public void setTvBrocastServiceListener(AwTvBrocastServiceListener awTvBrocastServiceListener) {
        this.mServiceListeners.add(awTvBrocastServiceListener);
    }

    public IBtvServer getAwTvBrocastService() {
        try {
            if (this.mTvBrocastService == null) {
                this.mTvBrocastService = IBtvServer.getService(true);
                if (this.mTvBrocastService != null) {
                    this.mTvBrocastService.linkToDeath(this.mDeathRecipient, 1000L);
                }
            }
            return this.mTvBrocastService;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setBrocastEventCallback(IBtvServerCallback iBtvServerCallback) {
        if (iBtvServerCallback == null || this.mTvBrocastService == null) {
            return;
        }
        this.mTvBrocastEventCallback = iBtvServerCallback;
        try {
            this.mTvBrocastService.setCallback(this.mTvBrocastEventCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private AwTvBrocastManagerProxy() {
        this.mDeathRecipient = null;
        this.mDeathRecipient = new AwTvBrocastProxyDeathRecipient();
        getAwTvBrocastService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnectServices() {
        synchronized (AwTvBrocastManagerProxy.class) {
            Log.d(TAG, "disconnectServices");
            this.mTvBrocastService = null;
            System.gc();
            try {
                Thread.sleep(100L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.softwinner.tv.client.AwTvBrocastManagerProxy$1] */
    public void reconnectServices() {
        new Thread("" + TAG + "## reconnectServices") { // from class: com.softwinner.tv.client.AwTvBrocastManagerProxy.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int i = 0;
                try {
                    AwTvBrocastManagerProxy.getInstance().disconnectServices();
                    Log.d(AwTvBrocastManagerProxy.TAG, "begin to reconnectServices");
                    AwTvBrocastManagerProxy.this.notifyTvBrocastServiceStatus(2);
                    while (AwTvBrocastManagerProxy.getInstance().getAwTvBrocastService() == null && i < 10000) {
                        i++;
                        sleep(1000L);
                        Log.e(AwTvBrocastManagerProxy.TAG, "reconnectServices, count=" + i);
                    }
                    if (AwTvBrocastManagerProxy.this.mTvBrocastService != null) {
                        AwTvBrocastManagerProxy.this.mTvBrocastService.setCallback(AwTvBrocastManagerProxy.this.mTvBrocastEventCallback);
                        AwTvBrocastManagerProxy.this.notifyTvBrocastServiceStatus(1);
                    }
                } catch (Exception unused) {
                    Log.v(AwTvBrocastManagerProxy.TAG, "reconnectServices failed");
                }
            }
        }.start();
    }

    final class AwTvBrocastProxyDeathRecipient implements IHwBinder.DeathRecipient {
        AwTvBrocastProxyDeathRecipient() {
        }

        public void serviceDied(long j) {
            Log.d(AwTvBrocastManagerProxy.TAG, "Proxy service has die. Try to reconnect btv service, cookie = " + j);
            AwTvBrocastManagerProxy.this.notifyTvBrocastServiceStatus(0);
            if (j == 1000) {
                AwTvBrocastManagerProxy.this.reconnectServices();
            }
        }
    }
}
