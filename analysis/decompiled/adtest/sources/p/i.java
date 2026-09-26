package p;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.MultiInstanceInvalidationService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i extends Binder implements e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ MultiInstanceInvalidationService f1662c;

    public i(MultiInstanceInvalidationService multiInstanceInvalidationService) {
        this.f1662c = multiInstanceInvalidationService;
        attachInterface(this, e.f1619b);
    }

    @Override // p.e
    public final void a(int i4, String[] strArr) {
        j2.i.e(strArr, "tables");
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.f1662c;
        synchronized (multiInstanceInvalidationService.f304c) {
            String str = (String) multiInstanceInvalidationService.f303b.get(Integer.valueOf(i4));
            if (str == null) {
                Log.w("ROOM", "Remote invalidation client ID not registered");
                return;
            }
            int iBeginBroadcast = multiInstanceInvalidationService.f304c.beginBroadcast();
            for (int i5 = 0; i5 < iBeginBroadcast; i5++) {
                try {
                    Object broadcastCookie = multiInstanceInvalidationService.f304c.getBroadcastCookie(i5);
                    j2.i.c(broadcastCookie, "null cannot be cast to non-null type kotlin.Int");
                    Integer num = (Integer) broadcastCookie;
                    int iIntValue = num.intValue();
                    String str2 = (String) multiInstanceInvalidationService.f303b.get(num);
                    if (i4 != iIntValue && str.equals(str2)) {
                        try {
                            ((d) multiInstanceInvalidationService.f304c.getBroadcastItem(i5)).b(strArr);
                        } catch (RemoteException e4) {
                            Log.w("ROOM", "Error invoking a remote callback", e4);
                        }
                    }
                } catch (Throwable th) {
                    multiInstanceInvalidationService.f304c.finishBroadcast();
                    throw th;
                }
            }
            multiInstanceInvalidationService.f304c.finishBroadcast();
        }
    }

    public final int c(d dVar, String str) {
        j2.i.e(dVar, "callback");
        int i4 = 0;
        if (str == null) {
            return 0;
        }
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.f1662c;
        synchronized (multiInstanceInvalidationService.f304c) {
            try {
                int i5 = multiInstanceInvalidationService.f302a + 1;
                multiInstanceInvalidationService.f302a = i5;
                if (multiInstanceInvalidationService.f304c.register(dVar, Integer.valueOf(i5))) {
                    multiInstanceInvalidationService.f303b.put(Integer.valueOf(i5), str);
                    i4 = i5;
                } else {
                    multiInstanceInvalidationService.f302a--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i4;
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i4, Parcel parcel, Parcel parcel2, int i5) {
        String str = e.f1619b;
        if (i4 >= 1 && i4 <= 16777215) {
            parcel.enforceInterface(str);
        }
        if (i4 == 1598968902) {
            parcel2.writeString(str);
            return true;
        }
        d dVar = null;
        d dVar2 = null;
        if (i4 == 1) {
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface(d.f1614a);
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) {
                    c cVar = new c();
                    cVar.f1608c = strongBinder;
                    dVar = cVar;
                } else {
                    dVar = (d) iInterfaceQueryLocalInterface;
                }
            }
            int iC = c(dVar, parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(iC);
            return true;
        }
        if (i4 != 2) {
            if (i4 != 3) {
                return super.onTransact(i4, parcel, parcel2, i5);
            }
            a(parcel.readInt(), parcel.createStringArray());
            return true;
        }
        IBinder strongBinder2 = parcel.readStrongBinder();
        if (strongBinder2 != null) {
            IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface(d.f1614a);
            if (iInterfaceQueryLocalInterface2 == null || !(iInterfaceQueryLocalInterface2 instanceof d)) {
                c cVar2 = new c();
                cVar2.f1608c = strongBinder2;
                dVar2 = cVar2;
            } else {
                dVar2 = (d) iInterfaceQueryLocalInterface2;
            }
        }
        int i6 = parcel.readInt();
        j2.i.e(dVar2, "callback");
        MultiInstanceInvalidationService multiInstanceInvalidationService = this.f1662c;
        synchronized (multiInstanceInvalidationService.f304c) {
            multiInstanceInvalidationService.f304c.unregister(dVar2);
        }
        parcel2.writeNoException();
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
