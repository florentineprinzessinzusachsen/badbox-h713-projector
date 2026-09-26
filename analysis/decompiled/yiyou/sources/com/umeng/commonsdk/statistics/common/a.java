package com.umeng.commonsdk.statistics.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: compiled from: AdvertisingId.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.common.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdvertisingId.java */
    private static final class C0089a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f4075a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f4076b;

        C0089a(String str, boolean z) {
            this.f4075a = str;
            this.f4076b = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String b() {
            return this.f4075a;
        }

        public boolean a() {
            return this.f4076b;
        }
    }

    /* JADX INFO: compiled from: AdvertisingId.java */
    private static final class b implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f4077a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final LinkedBlockingQueue<IBinder> f4078b;

        private b() {
            this.f4077a = false;
            this.f4078b = new LinkedBlockingQueue<>(1);
        }

        public IBinder a() {
            if (this.f4077a) {
                throw new IllegalStateException();
            }
            this.f4077a = true;
            return this.f4078b.take();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f4078b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public static String a(Context context) {
        try {
            C0089a c0089aB = b(context);
            if (c0089aB == null) {
                return null;
            }
            return c0089aB.b();
        } catch (Exception unused) {
            return null;
        }
    }

    private static C0089a b(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return null;
        }
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            b bVar = new b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            if (!context.bindService(intent, bVar, 1)) {
                throw new IOException("Google Play connection failed");
            }
            try {
                try {
                    c cVar = new c(bVar.a());
                    C0089a c0089a = new C0089a(cVar.a(), cVar.a(true));
                    context.unbindService(bVar);
                    return c0089a;
                } catch (Exception e2) {
                    throw e2;
                }
            } catch (Throwable th) {
                context.unbindService(bVar);
                throw th;
            }
        } catch (Exception e3) {
            throw e3;
        }
    }

    /* JADX INFO: compiled from: AdvertisingId.java */
    private static final class c implements IInterface {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private IBinder f4079a;

        public c(IBinder iBinder) {
            this.f4079a = iBinder;
        }

        public String a() {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f4079a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f4079a;
        }

        public boolean a(boolean z) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                parcelObtain.writeInt(z ? 1 : 0);
                this.f4079a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }
}
