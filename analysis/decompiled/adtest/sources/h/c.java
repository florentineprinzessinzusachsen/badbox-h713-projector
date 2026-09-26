package h;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Service f983a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f987e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f988f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PendingIntent f989g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Bundle f991i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f992j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f993k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Notification f994l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f995m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f984b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f985c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f986d = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f990h = true;

    public c(Service service, String str) {
        Notification notification = new Notification();
        this.f994l = notification;
        this.f983a = service;
        this.f992j = str;
        notification.when = System.currentTimeMillis();
        notification.audioStreamType = -1;
        this.f995m = new ArrayList();
        this.f993k = true;
    }

    public static CharSequence b(String str) {
        return (str != null && str.length() > 5120) ? str.subSequence(0, 5120) : str;
    }

    public final Notification a() {
        ArrayList arrayList;
        new ArrayList();
        Bundle bundle = new Bundle();
        int i4 = Build.VERSION.SDK_INT;
        Service service = this.f983a;
        String str = this.f992j;
        Notification.Builder builderA = i4 >= 26 ? k.a(service, str) : new Notification.Builder(service);
        Notification notification = this.f994l;
        builderA.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(this.f987e).setContentText(this.f988f).setContentInfo(null).setContentIntent(this.f989g).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(null, (notification.flags & 128) != 0).setNumber(0).setProgress(0, 0, false);
        i.b(builderA, null);
        d.b(d.d(d.c(builderA, null), false), 0);
        Iterator it = this.f984b.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
        Bundle bundle2 = this.f991i;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        e.a(builderA, this.f990h);
        g.i(builderA, false);
        g.g(builderA, null);
        g.j(builderA, null);
        g.h(builderA, false);
        h.b(builderA, null);
        h.c(builderA, 0);
        h.f(builderA, 0);
        h.d(builderA, null);
        h.e(builderA, notification.sound, notification.audioAttributes);
        ArrayList arrayList2 = this.f995m;
        ArrayList arrayList3 = this.f985c;
        if (i4 < 28) {
            if (arrayList3 == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(arrayList3.size());
                Iterator it2 = arrayList3.iterator();
                if (it2.hasNext()) {
                    it2.next().getClass();
                    throw new ClassCastException();
                }
            }
            if (arrayList != null) {
                if (arrayList2 == null) {
                    arrayList2 = arrayList;
                } else {
                    int size = arrayList2.size() + arrayList.size();
                    e.f fVar = new e.f();
                    fVar.f570d = f.a.f834a;
                    fVar.f571e = f.a.f835b;
                    if (size > 0) {
                        fVar.f570d = new int[size];
                        fVar.f571e = new Object[size];
                    }
                    fVar.addAll(arrayList);
                    fVar.addAll(arrayList2);
                    arrayList2 = new ArrayList(fVar);
                }
            }
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            int size2 = arrayList2.size();
            int i5 = 0;
            while (i5 < size2) {
                Object obj = arrayList2.get(i5);
                i5++;
                h.a(builderA, (String) obj);
            }
        }
        ArrayList arrayList4 = this.f986d;
        if (arrayList4.size() > 0) {
            if (this.f991i == null) {
                this.f991i = new Bundle();
            }
            Bundle bundle3 = this.f991i.getBundle("android.car.EXTENSIONS");
            if (bundle3 == null) {
                bundle3 = new Bundle();
            }
            Bundle bundle4 = new Bundle(bundle3);
            Bundle bundle5 = new Bundle();
            if (arrayList4.size() > 0) {
                Integer.toString(0);
                if (arrayList4.get(0) != null) {
                    throw new ClassCastException();
                }
                new Bundle();
                throw null;
            }
            bundle3.putBundle("invisible_actions", bundle5);
            bundle4.putBundle("invisible_actions", bundle5);
            if (this.f991i == null) {
                this.f991i = new Bundle();
            }
            this.f991i.putBundle("android.car.EXTENSIONS", bundle3);
            bundle.putBundle("android.car.EXTENSIONS", bundle4);
        }
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 24) {
            f.a(builderA, this.f991i);
            j.e(builderA, null);
        }
        if (i6 >= 26) {
            k.b(builderA, 0);
            k.e(builderA, null);
            k.f(builderA, null);
            k.g(builderA, 0L);
            k.d(builderA, 0);
            if (!TextUtils.isEmpty(str)) {
                builderA.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i6 >= 28) {
            Iterator it3 = arrayList3.iterator();
            if (it3.hasNext()) {
                it3.next().getClass();
                throw new ClassCastException();
            }
        }
        if (i6 >= 29) {
            l.a(builderA, this.f993k);
            l.b(builderA, null);
        }
        if (i6 >= 26) {
            return d.a(builderA);
        }
        if (i6 >= 24) {
            return d.a(builderA);
        }
        f.a(builderA, bundle);
        return d.a(builderA);
    }
}
