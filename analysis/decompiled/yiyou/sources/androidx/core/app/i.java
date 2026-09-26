package androidx.core.app;

import android.app.Notification;
import android.app.RemoteInput;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NotificationCompatBuilder.java */
/* JADX INFO: loaded from: classes.dex */
class i implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Notification.Builder f937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h.b f938b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private RemoteViews f939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private RemoteViews f940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<Bundle> f941e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Bundle f942f = new Bundle();
    private int g;
    private RemoteViews h;

    i(h.b bVar) {
        ArrayList<String> arrayList;
        this.f938b = bVar;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f937a = new Notification.Builder(bVar.f931a, bVar.I);
        } else {
            this.f937a = new Notification.Builder(bVar.f931a);
        }
        Notification notification = bVar.N;
        this.f937a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, bVar.h).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(bVar.f934d).setContentText(bVar.f935e).setContentInfo(bVar.j).setContentIntent(bVar.f936f).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(bVar.g, (notification.flags & 128) != 0).setLargeIcon(bVar.i).setNumber(bVar.k).setProgress(bVar.r, bVar.s, bVar.t);
        if (Build.VERSION.SDK_INT < 21) {
            this.f937a.setSound(notification.sound, notification.audioStreamType);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f937a.setSubText(bVar.p).setUsesChronometer(bVar.n).setPriority(bVar.l);
            Iterator<h.a> it = bVar.f932b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle = bVar.B;
            if (bundle != null) {
                this.f942f.putAll(bundle);
            }
            if (Build.VERSION.SDK_INT < 20) {
                if (bVar.x) {
                    this.f942f.putBoolean("android.support.localOnly", true);
                }
                String str = bVar.u;
                if (str != null) {
                    this.f942f.putString("android.support.groupKey", str);
                    if (bVar.v) {
                        this.f942f.putBoolean("android.support.isGroupSummary", true);
                    } else {
                        this.f942f.putBoolean("android.support.useSideChannel", true);
                    }
                }
                String str2 = bVar.w;
                if (str2 != null) {
                    this.f942f.putString("android.support.sortKey", str2);
                }
            }
            this.f939c = bVar.F;
            this.f940d = bVar.G;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.f937a.setShowWhen(bVar.m);
            if (Build.VERSION.SDK_INT < 21 && (arrayList = bVar.O) != null && !arrayList.isEmpty()) {
                Bundle bundle2 = this.f942f;
                ArrayList<String> arrayList2 = bVar.O;
                bundle2.putStringArray("android.people", (String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.f937a.setLocalOnly(bVar.x).setGroup(bVar.u).setGroupSummary(bVar.v).setSortKey(bVar.w);
            this.g = bVar.M;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.f937a.setCategory(bVar.A).setColor(bVar.C).setVisibility(bVar.D).setPublicVersion(bVar.E).setSound(notification.sound, notification.audioAttributes);
            Iterator<String> it2 = bVar.O.iterator();
            while (it2.hasNext()) {
                this.f937a.addPerson(it2.next());
            }
            this.h = bVar.H;
            if (bVar.f933c.size() > 0) {
                Bundle bundle3 = bVar.b().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle();
                for (int i = 0; i < bVar.f933c.size(); i++) {
                    bundle4.putBundle(Integer.toString(i), j.a(bVar.f933c.get(i)));
                }
                bundle3.putBundle("invisible_actions", bundle4);
                bVar.b().putBundle("android.car.EXTENSIONS", bundle3);
                this.f942f.putBundle("android.car.EXTENSIONS", bundle3);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            this.f937a.setExtras(bVar.B).setRemoteInputHistory(bVar.q);
            RemoteViews remoteViews = bVar.F;
            if (remoteViews != null) {
                this.f937a.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = bVar.G;
            if (remoteViews2 != null) {
                this.f937a.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = bVar.H;
            if (remoteViews3 != null) {
                this.f937a.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.f937a.setBadgeIconType(bVar.J).setShortcutId(bVar.K).setTimeoutAfter(bVar.L).setGroupAlertBehavior(bVar.M);
            if (bVar.z) {
                this.f937a.setColorized(bVar.y);
            }
            if (TextUtils.isEmpty(bVar.I)) {
                return;
            }
            this.f937a.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
        }
    }

    public Notification a() {
        Bundle bundleA;
        RemoteViews remoteViewsD;
        RemoteViews remoteViewsB;
        h.c cVar = this.f938b.o;
        if (cVar != null) {
            cVar.a(this);
        }
        RemoteViews remoteViewsC = cVar != null ? cVar.c(this) : null;
        Notification notificationB = b();
        if (remoteViewsC != null) {
            notificationB.contentView = remoteViewsC;
        } else {
            RemoteViews remoteViews = this.f938b.F;
            if (remoteViews != null) {
                notificationB.contentView = remoteViews;
            }
        }
        if (Build.VERSION.SDK_INT >= 16 && cVar != null && (remoteViewsB = cVar.b(this)) != null) {
            notificationB.bigContentView = remoteViewsB;
        }
        if (Build.VERSION.SDK_INT >= 21 && cVar != null && (remoteViewsD = this.f938b.o.d(this)) != null) {
            notificationB.headsUpContentView = remoteViewsD;
        }
        if (Build.VERSION.SDK_INT >= 16 && cVar != null && (bundleA = h.a(notificationB)) != null) {
            cVar.a(bundleA);
        }
        return notificationB;
    }

    protected Notification b() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            return this.f937a.build();
        }
        if (i >= 24) {
            Notification notificationBuild = this.f937a.build();
            if (this.g != 0) {
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) != 0 && this.g == 2) {
                    a(notificationBuild);
                }
                if (notificationBuild.getGroup() != null && (notificationBuild.flags & 512) == 0 && this.g == 1) {
                    a(notificationBuild);
                }
            }
            return notificationBuild;
        }
        if (i >= 21) {
            this.f937a.setExtras(this.f942f);
            Notification notificationBuild2 = this.f937a.build();
            RemoteViews remoteViews = this.f939c;
            if (remoteViews != null) {
                notificationBuild2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.f940d;
            if (remoteViews2 != null) {
                notificationBuild2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.h;
            if (remoteViews3 != null) {
                notificationBuild2.headsUpContentView = remoteViews3;
            }
            if (this.g != 0) {
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) != 0 && this.g == 2) {
                    a(notificationBuild2);
                }
                if (notificationBuild2.getGroup() != null && (notificationBuild2.flags & 512) == 0 && this.g == 1) {
                    a(notificationBuild2);
                }
            }
            return notificationBuild2;
        }
        if (i >= 20) {
            this.f937a.setExtras(this.f942f);
            Notification notificationBuild3 = this.f937a.build();
            RemoteViews remoteViews4 = this.f939c;
            if (remoteViews4 != null) {
                notificationBuild3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.f940d;
            if (remoteViews5 != null) {
                notificationBuild3.bigContentView = remoteViews5;
            }
            if (this.g != 0) {
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) != 0 && this.g == 2) {
                    a(notificationBuild3);
                }
                if (notificationBuild3.getGroup() != null && (notificationBuild3.flags & 512) == 0 && this.g == 1) {
                    a(notificationBuild3);
                }
            }
            return notificationBuild3;
        }
        if (i >= 19) {
            SparseArray<Bundle> sparseArrayA = j.a(this.f941e);
            if (sparseArrayA != null) {
                this.f942f.putSparseParcelableArray("android.support.actionExtras", sparseArrayA);
            }
            this.f937a.setExtras(this.f942f);
            Notification notificationBuild4 = this.f937a.build();
            RemoteViews remoteViews6 = this.f939c;
            if (remoteViews6 != null) {
                notificationBuild4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.f940d;
            if (remoteViews7 != null) {
                notificationBuild4.bigContentView = remoteViews7;
            }
            return notificationBuild4;
        }
        if (i < 16) {
            return this.f937a.getNotification();
        }
        Notification notificationBuild5 = this.f937a.build();
        Bundle bundleA = h.a(notificationBuild5);
        Bundle bundle = new Bundle(this.f942f);
        for (String str : this.f942f.keySet()) {
            if (bundleA.containsKey(str)) {
                bundle.remove(str);
            }
        }
        bundleA.putAll(bundle);
        SparseArray<Bundle> sparseArrayA2 = j.a(this.f941e);
        if (sparseArrayA2 != null) {
            h.a(notificationBuild5).putSparseParcelableArray("android.support.actionExtras", sparseArrayA2);
        }
        RemoteViews remoteViews8 = this.f939c;
        if (remoteViews8 != null) {
            notificationBuild5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.f940d;
        if (remoteViews9 != null) {
            notificationBuild5.bigContentView = remoteViews9;
        }
        return notificationBuild5;
    }

    private void a(h.a aVar) {
        Bundle bundle;
        int i = Build.VERSION.SDK_INT;
        if (i < 20) {
            if (i >= 16) {
                this.f941e.add(j.a(this.f937a, aVar));
                return;
            }
            return;
        }
        Notification.Action.Builder builder = new Notification.Action.Builder(aVar.e(), aVar.i(), aVar.a());
        if (aVar.f() != null) {
            for (RemoteInput remoteInput : l.a(aVar.f())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        if (aVar.d() != null) {
            bundle = new Bundle(aVar.d());
        } else {
            bundle = new Bundle();
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", aVar.b());
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setAllowGeneratedReplies(aVar.b());
        }
        bundle.putInt("android.support.action.semanticAction", aVar.g());
        if (Build.VERSION.SDK_INT >= 28) {
            builder.setSemanticAction(aVar.g());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", aVar.h());
        builder.addExtras(bundle);
        this.f937a.addAction(builder.build());
    }

    private void a(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        notification.defaults &= -2;
        notification.defaults &= -3;
    }
}
