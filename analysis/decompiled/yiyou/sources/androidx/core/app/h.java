package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.baidu.mobstat.Config;
import java.util.ArrayList;

/* JADX INFO: compiled from: NotificationCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Bundle f925a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final l[] f926b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final l[] f927c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f928d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f929e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final int f930f;
        public int g;
        public CharSequence h;
        public PendingIntent i;

        public PendingIntent a() {
            return this.i;
        }

        public boolean b() {
            return this.f928d;
        }

        public l[] c() {
            return this.f927c;
        }

        public Bundle d() {
            return this.f925a;
        }

        public int e() {
            return this.g;
        }

        public l[] f() {
            return this.f926b;
        }

        public int g() {
            return this.f930f;
        }

        public boolean h() {
            return this.f929e;
        }

        public CharSequence i() {
            return this.h;
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static class b {
        String A;
        Bundle B;
        int C;
        int D;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        int J;
        String K;
        long L;
        int M;
        Notification N;

        @Deprecated
        public ArrayList<String> O;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f931a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ArrayList<a> f932b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ArrayList<a> f933c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        CharSequence f934d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        CharSequence f935e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        PendingIntent f936f;
        PendingIntent g;
        RemoteViews h;
        Bitmap i;
        CharSequence j;
        int k;
        int l;
        boolean m;
        boolean n;
        c o;
        CharSequence p;
        CharSequence[] q;
        int r;
        int s;
        boolean t;
        String u;
        boolean v;
        String w;
        boolean x;
        boolean y;
        boolean z;

        public b(Context context, String str) {
            this.f932b = new ArrayList<>();
            this.f933c = new ArrayList<>();
            this.m = true;
            this.x = false;
            this.C = 0;
            this.D = 0;
            this.J = 0;
            this.M = 0;
            this.N = new Notification();
            this.f931a = context;
            this.I = str;
            this.N.when = System.currentTimeMillis();
            this.N.audioStreamType = -1;
            this.l = 0;
            this.O = new ArrayList<>();
        }

        protected static CharSequence c(CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, Config.MAX_CACHE_JSON_CAPACIT_EXCEPTION) : charSequence;
        }

        public b a(int i) {
            this.N.icon = i;
            return this;
        }

        public b b(CharSequence charSequence) {
            this.f934d = c(charSequence);
            return this;
        }

        public b a(CharSequence charSequence) {
            this.f935e = c(charSequence);
            return this;
        }

        public Bundle b() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        public b a(PendingIntent pendingIntent) {
            this.f936f = pendingIntent;
            return this;
        }

        public b a(boolean z) {
            a(16, z);
            return this;
        }

        private void a(int i, boolean z) {
            if (z) {
                Notification notification = this.N;
                notification.flags = i | notification.flags;
            } else {
                Notification notification2 = this.N;
                notification2.flags = (i ^ (-1)) & notification2.flags;
            }
        }

        public b a(String str) {
            this.u = str;
            return this;
        }

        public Notification a() {
            return new i(this).a();
        }

        @Deprecated
        public b(Context context) {
            this(context, null);
        }
    }

    /* JADX INFO: compiled from: NotificationCompat.java */
    public static abstract class c {
        public abstract void a(Bundle bundle);

        public abstract void a(g gVar);

        public abstract RemoteViews b(g gVar);

        public abstract RemoteViews c(g gVar);

        public abstract RemoteViews d(g gVar);
    }

    public static Bundle a(Notification notification) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 19) {
            return notification.extras;
        }
        if (i >= 16) {
            return j.a(notification);
        }
        return null;
    }
}
