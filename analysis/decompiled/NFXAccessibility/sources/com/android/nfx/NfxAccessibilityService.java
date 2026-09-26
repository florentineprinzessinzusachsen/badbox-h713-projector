package com.android.nfx;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.pm.PackageInfo;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NfxAccessibilityService extends AccessibilityService {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private String l;
    private AccessibilityNodeInfo n;
    private Rect o;
    private List m = new ArrayList();
    private String p = "41011110431a0d310a0c2c1c1a003a070b1209";
    private String q = "41011110430a1d3b13172c0b1015";
    private boolean r = false;
    private int s = 0;
    private int t = 0;
    private int u = 6;
    private String v = "com.netflix.mediaclient:id/2131429476";
    private String w = "com.netflix.mediaclient:id/2131429480";
    private String x = "com.netflix.mediaclient:id/2131429694";
    private String y = "com.netflix.mediaclient:id/2131429117";
    private String z = "com.netflix.mediaclient:id/2131427585";
    private String A = "com.netflix.mediaclient:id/2131429095";
    private String B = "com.netflix.mediaclient:id/2131429374";
    private String C = "com.netflix.mediaclient:id/2131429377";
    private String D = "com.netflix.mediaclient:id/2131429049";
    private String E = "com.netflix.mediaclient:id/2131429602";
    private String F = "com.netflix.mediaclient:id/2131427595";
    private String G = "com.netflix.mediaclient:id/2131427593";
    private String H = "com.netflix.mediaclient:id/2131429038";
    private float I = 0.0f;
    private float J = 0.0f;
    private int K = 0;
    private int L = 0;

    /* JADX WARN: Code duplicated, block: B:57:0x0118  */
    private boolean a(int i, int i2) {
        int i3;
        boolean z = false;
        Rect rect = new Rect(0, 0, 0, 0);
        if (i <= this.b - 1 && i >= 0) {
            ((AccessibilityNodeInfo) this.m.get(i)).getBoundsInScreen(rect);
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < this.m.size(); i4++) {
                Rect rect2 = new Rect(0, 0, 0, 0);
                ((AccessibilityNodeInfo) this.m.get(i4)).getBoundsInScreen(rect2);
                rect2.width();
                rect2.height();
                d dVar = new d(this, i4, rect2);
                int i5 = rect2.left - rect.left;
                dVar.c = i5;
                int i6 = rect2.top - rect.top;
                dVar.d = i6;
                dVar.e = (int) Math.sqrt((i6 * i6) + (i5 * i5));
                arrayList.add(dVar);
            }
            arrayList.remove(i);
            if (i < this.m.size() && i >= 0) {
                ((AccessibilityNodeInfo) this.m.get(i)).getBoundsInScreen(rect);
                if (i2 == 22) {
                    if ((r10 = this.u) == 6) {
                    }
                } else if (i2 == 21) {
                    if (this.u == 6) {
                    }
                } else if (i2 == 20) {
                    Collections.sort(arrayList, new a(this));
                    e(arrayList);
                    if (arrayList.size() > 0 && ((d) arrayList.get(0)).d <= 0) {
                        z = true;
                    }
                } else if (i2 == 19 && ((i3 = this.u) != 6 ? !(i3 != 9 ? rect.top >= this.o.top + this.k : rect.top >= this.o.top + 150) : rect.top < this.o.top + 100)) {
                    z = true;
                }
            }
            Log.d("IGX", "checkEdge = " + z);
        }
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:195:0x0277  */
    private void b(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        int i2;
        int i3;
        boolean z5;
        int i4;
        Rect rect = new Rect();
        accessibilityNodeInfo.getBoundsInScreen(rect);
        boolean zIsClickable = accessibilityNodeInfo.isClickable();
        boolean zIsVisibleToUser = accessibilityNodeInfo.isVisibleToUser();
        accessibilityNodeInfo.getContentDescription();
        int iHeight = rect.height() * rect.width();
        String viewIdResourceName = accessibilityNodeInfo.getViewIdResourceName();
        if (viewIdResourceName == null || this.u != 6) {
            if (viewIdResourceName != null && this.u == 8) {
                String str = SystemProperties.get("sys.lmk_top_activity", "");
                if (viewIdResourceName.equals(this.x)) {
                    i = 4;
                    this.t = 4;
                } else {
                    i = 4;
                }
                if (str.indexOf("ui.profiles.ProfileSelectionActivity") <= 0 && str.indexOf("ui.home.HomeActivity") <= 0) {
                    if (str.indexOf("ui.player.PlayerActivity") > 0) {
                        this.t = i;
                    } else if (str.indexOf("ui.upnextfeed.impl.UpNextFeedActivity") <= 0 && str.indexOf("ui.profiles.MyNetflixActivity") <= 0 && str.indexOf("ui.launch.UIWebViewActivity") > 0 && viewIdResourceName.equals("android:id/button2")) {
                        int iCenterX = rect.centerX();
                        int iCenterY = rect.centerY();
                        h(iCenterX, iCenterY, iCenterX, iCenterY);
                    }
                }
                if (viewIdResourceName.equals("android:id/message") && accessibilityNodeInfo.getText().toString().indexOf("version of application found. Do you want to update") > 0) {
                    this.t = 5;
                    int i5 = this.d;
                    if (i5 == 1080) {
                        i2 = 1227;
                        i3 = 588;
                    } else if (i5 == 720) {
                        i2 = 851;
                        i3 = 397;
                    }
                    h(i2, i3, i2, i3);
                    Log.d("TXT", "touch to pass");
                }
            } else if (viewIdResourceName != null && this.u == 9) {
                String str2 = SystemProperties.get("sys.lmk_top_activity", "");
                if (str2.indexOf("ui.profiles.ProfileSelectionActivity") <= 0 && str2.indexOf("ui.home.HomeActivity") <= 0 && str2.indexOf("ui.player.PlayerActivity") <= 0 && str2.indexOf("ui.upnextfeed.impl.UpNextFeedActivity") <= 0) {
                    str2.indexOf("ui.profiles.MyNetflixActivity");
                }
            }
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        } else {
            z2 = viewIdResourceName.indexOf("sliding_menu_content") > 0;
            z = viewIdResourceName.indexOf("cw_view_info") > 0;
            z3 = viewIdResourceName.indexOf("player_seekbar") > 0;
            z4 = viewIdResourceName.indexOf("player_controls_secondary") > 0;
            if (viewIdResourceName.indexOf("movie_boxart") > 0) {
                this.s = 1;
            } else {
                if (viewIdResourceName.indexOf("episode_row_play_button") > 0) {
                    i4 = 2;
                } else if (viewIdResourceName.indexOf("video_details_download_button") > 0) {
                    i4 = 3;
                } else if (viewIdResourceName.indexOf("sb_image_button") > 0) {
                    this.s = 4;
                } else if (viewIdResourceName.indexOf("search_src_text") > 0) {
                    i4 = 5;
                } else if (viewIdResourceName.indexOf("sliding_menu_profiles_group") > 0) {
                    this.s = 6;
                }
                this.s = i4;
            }
        }
        boolean z6 = rect.width() == this.c || rect.height() == this.d;
        int i6 = this.d == 720 ? 190000 : 500000;
        int i7 = this.d == 720 ? 30000 : 50000;
        int i8 = this.u;
        if (i8 == 6) {
            z5 = (!zIsClickable || !zIsVisibleToUser || z || z2 || z3 || z4 || z6) ? false : true;
            if (this.s != 3 && iHeight > i6) {
                z5 = false;
            }
            if (this.s == 2 && iHeight > i7) {
                z5 = false;
            }
        } else if (i8 == 8) {
            z5 = zIsClickable && zIsVisibleToUser && iHeight > (this.d == 1080 ? 8000 : 4000) && iHeight < (this.d != 1080 ? 400000 : 1000000);
            if (viewIdResourceName != null) {
                if (viewIdResourceName.equals(this.w) || viewIdResourceName.equals(this.v)) {
                    z5 = true;
                }
                if (viewIdResourceName.equals(this.x)) {
                    d(accessibilityNodeInfo);
                    z5 = true;
                }
                if (viewIdResourceName.equals(this.y)) {
                    z5 = false;
                }
                if (viewIdResourceName.equals(this.z) || viewIdResourceName.equals(this.A)) {
                    z5 = false;
                }
            }
        } else {
            z5 = zIsClickable && zIsVisibleToUser && iHeight > (this.d == 1080 ? 8000 : 4000) && iHeight < (this.d != 1080 ? 400000 : 1000000);
            if (viewIdResourceName != null) {
                if (viewIdResourceName.equals(this.C) || viewIdResourceName.equals(this.B)) {
                    z5 = true;
                }
                if (viewIdResourceName.equals(this.D)) {
                    z5 = true;
                }
                if (viewIdResourceName.equals(this.E)) {
                    d(accessibilityNodeInfo);
                    z5 = true;
                }
                if (viewIdResourceName.equals(this.G)) {
                    z5 = false;
                }
                if (viewIdResourceName.equals(this.F) || viewIdResourceName.equals(this.H)) {
                    z5 = false;
                }
            }
        }
        boolean z7 = SystemProperties.getInt("sys.nfx_debug_icon", 0) > 0 ? true : z5;
        if (z7) {
            this.m.add(accessibilityNodeInfo);
            Rect rect2 = new Rect();
            accessibilityNodeInfo.getBoundsInScreen(rect2);
            Log.d("INF", "granted = " + z7 + " Content: " + ((Object) accessibilityNodeInfo.getContentDescription()) + "  size = " + (rect2.height() * rect2.width()) + " rect = " + rect2.toString() + "VIR = " + accessibilityNodeInfo.getViewIdResourceName());
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i9);
            if (child != null) {
                b(child);
            }
        }
    }

    private void d(AccessibilityNodeInfo accessibilityNodeInfo) {
        AccessibilityNodeInfo.RangeInfo rangeInfo = accessibilityNodeInfo.getRangeInfo();
        if (rangeInfo != null) {
            float current = rangeInfo.getCurrent();
            float max = rangeInfo.getMax();
            float min = rangeInfo.getMin();
            this.I = current;
            this.J = max;
            Rect rect = new Rect();
            accessibilityNodeInfo.getBoundsInScreen(rect);
            int iWidth = rect.width();
            this.K = iWidth;
            this.L = (int) ((iWidth * this.I) / this.J);
            Log.d("DGX", "seek8 curr = " + current + " max = " + max + " min = " + min + " xint = " + this.L + " width = " + this.K);
        }
        Log.d("DGX", "range info is empty");
    }

    private void e(List list) {
        Log.d("IGX", "--------------------------------");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            Log.d("IGX", "rect = " + dVar.b.toString() + "idx= " + dVar.a + " xproj= " + dVar.c + " yproj= " + dVar.d + " score=0 dis = " + dVar.e);
        }
        Log.d("IGX", "--------------------------------");
    }

    public static String f(String str) {
        byte[] bytes = "netflix_resouce".getBytes();
        int length = str.length();
        int i = length / 2;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) (Character.digit(str.charAt(i2 + 1), 16) + (Character.digit(str.charAt(i2), 16) << 4));
        }
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) (bArr[i3] ^ bytes[i3 % bytes.length]);
        }
        return new String(bArr2);
    }

    private void g(int i) {
        Rect rect = new Rect(0, 0, 0, 0);
        Log.d("TPX", "index = " + i + " Total size = " + this.m.size());
        if (i >= this.m.size() || i < 0) {
            return;
        }
        AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) this.m.get(i);
        this.n = accessibilityNodeInfo;
        accessibilityNodeInfo.getBoundsInScreen(rect);
        this.e = rect.centerX();
        this.f = rect.centerY();
        this.l = rect.left + " " + rect.top + " " + rect.right + " " + rect.bottom;
        Log.d("IND", "Content Description: " + ((Object) accessibilityNodeInfo.getContentDescription()) + "  size = " + (rect.height() * rect.width()) + " rect = " + rect.toString() + "VIR = " + accessibilityNodeInfo.getViewIdResourceName());
        if (this.r) {
            SystemProperties.set("sys.nfx_bound", this.l);
        }
    }

    public int c(int i, int i2) {
        StringBuilder sb;
        int i3 = -1;
        if (i >= 0 && i < this.m.size()) {
            Rect rect = new Rect(0, 0, 0, 0);
            ((AccessibilityNodeInfo) this.m.get(i)).getBoundsInScreen(rect);
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < this.m.size(); i4++) {
                Rect rect2 = new Rect(0, 0, 0, 0);
                ((AccessibilityNodeInfo) this.m.get(i4)).getBoundsInScreen(rect2);
                rect2.width();
                rect2.height();
                d dVar = new d(this, i4, rect2);
                int i5 = rect2.left - rect.left;
                dVar.c = i5;
                int i6 = rect2.top - rect.top;
                dVar.d = i6;
                dVar.e = (int) Math.sqrt((i6 * i6) + (i5 * i5));
                arrayList.add(dVar);
            }
            arrayList.remove(i);
            if (i2 == 20) {
                e(arrayList);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((d) it.next()).d <= 0) {
                        it.remove();
                    }
                }
                Collections.sort(arrayList, new b(this));
                e(arrayList);
                i3 = arrayList.size() > 0 ? ((d) arrayList.get(0)).a : -1;
                sb = new StringBuilder();
            } else if (i2 == 19) {
                e(arrayList);
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    if (((d) it2.next()).d >= 0) {
                        it2.remove();
                    }
                }
                Collections.sort(arrayList, new c(this));
                e(arrayList);
                i3 = arrayList.size() > 0 ? ((d) arrayList.get(0)).a : -1;
                sb = new StringBuilder();
            }
            sb.append("ret_index = ");
            sb.append(i3);
            Log.d("IGX", sb.toString());
        }
        return i3;
    }

    public void h(int i, int i2, int i3, int i4) {
        Log.d("THT", "X1 = " + i + " Y1 = " + i2 + " X2 = " + i3 + " Y2 = " + i4);
        Path path = new Path();
        path.moveTo((float) i, (float) i2);
        path.lineTo((float) i3, (float) i4);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 0L, 50L));
        dispatchGesture(builder.build(), null, null);
    }

    public void i(int i, int i2, int i3, int i4, int i5) {
        Log.d("DGX", "X1 = " + i + " Y1 = " + i2 + " X2 = " + i3 + " Y2 = " + i4);
        Path path = new Path();
        path.moveTo((float) i, (float) i2);
        path.lineTo((float) i3, (float) i4);
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(path, 80L, (long) i5));
        dispatchGesture(builder.build(), null, null);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0082  */
    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        this.m = new ArrayList();
        AccessibilityNodeInfo rootInActiveWindow = getRootInActiveWindow();
        if (rootInActiveWindow != null) {
            b(rootInActiveWindow);
        }
        this.b = this.m.size();
        Rect rect = new Rect(0, 0, 0, 0);
        AccessibilityNodeInfo accessibilityNodeInfo = this.n;
        if (accessibilityNodeInfo != null) {
            int i = 0;
            while (true) {
                if (i >= this.m.size()) {
                    i = -1;
                    break;
                } else if (((AccessibilityNodeInfo) this.m.get(i)).equals(accessibilityNodeInfo)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                this.a = i;
            }
        }
        Log.d("WWW", "viewNum = " + this.b);
        int i2 = this.b;
        if (i2 == 1) {
            ((AccessibilityNodeInfo) this.m.get(0)).getBoundsInScreen(rect);
            if (rect.width() == this.c && rect.height() == this.d) {
                SystemProperties.set("sys.nfx_bound_shown", "0");
            }
        } else if (i2 == 0) {
            SystemProperties.set("sys.nfx_bound_shown", "0");
        } else {
            SystemProperties.set("sys.nfx_bound_shown", "1");
        }
        if (this.m.size() == 2) {
            this.a = 0;
        }
        if (this.a >= this.m.size()) {
            this.a = 0;
        }
        if (this.a < this.m.size()) {
            ((AccessibilityNodeInfo) this.m.get(this.a)).getBoundsInScreen(rect);
            this.e = rect.centerX();
            this.f = rect.centerY();
            this.l = rect.left + " " + rect.top + " " + rect.right + " " + rect.bottom;
        }
        SystemProperties.set("sys.nfx_bound", this.l);
    }

    @Override // android.app.Service
    public void onCreate() {
        int i;
        int i2;
        super.onCreate();
        Log.d("NFV", "NFXAccessibility Version 1.1.4");
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.netflix.mediaclient", 0);
            i = packageInfo != null ? packageInfo.versionCode : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("NFV", "NFXAPP version = " + i);
        if (i < 50000) {
            i2 = 6;
        } else {
            i2 = (i <= 50000 || i >= 63057) ? 9 : 8;
        }
        this.u = i2;
        if (new File(f(this.p)).exists()) {
            this.r = true;
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.c = displayMetrics.widthPixels;
        this.d = displayMetrics.heightPixels;
        this.o = new Rect(0, 0, this.c, this.d);
        int i3 = this.c / 2;
        this.e = i3;
        int i4 = this.d;
        int i5 = i4 / 2;
        this.f = i5;
        this.g = i3;
        this.h = i5;
        if (i4 == 1080) {
            this.i = 120;
            this.j = 50;
            this.k = 65;
        } else {
            this.i = 100;
            this.j = 35;
            this.k = 13;
        }
        Log.d("EDG", "edgeDelta = " + this.k);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    /* JADX WARN: Code duplicated, block: B:100:0x020a  */
    /* JADX WARN: Code duplicated, block: B:103:0x021c  */
    /* JADX WARN: Code duplicated, block: B:117:0x0246 A[PHI: r8
      0x0246: PHI (r8v14 int) = (r8v13 int), (r8v16 int) binds: [B:116:0x0244, B:113:0x023d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:78:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:81:0x01d6  */
    @Override // android.accessibilityservice.AccessibilityService
    public boolean onKeyEvent(KeyEvent keyEvent) {
        int i;
        int i2;
        int i3;
        if (SystemProperties.get("sys.lmk_top_app", "").equals("com.netflix.mediaclient") && SystemProperties.getInt("sys.ime_shown", 0) != 1 && SystemProperties.getInt("sys.nfx_key_pass", 0) != 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("current nVersion  = ");
            sb.append(this.u);
            sb.append(" pageType = ");
            sb.append(this.u == 6 ? this.s : this.t);
            Log.d("IGX", sb.toString());
            int keyCode = keyEvent.getKeyCode();
            int action = keyEvent.getAction();
            if (this.b == 0 && keyCode != 23) {
                Log.d("IGX", "no view return");
                return super.onKeyEvent(keyEvent);
            }
            int i4 = this.a;
            AccessibilityNodeInfo accessibilityNodeInfo = (i4 > this.b + (-1) || i4 < 0) ? null : (AccessibilityNodeInfo) this.m.get(i4);
            if (accessibilityNodeInfo != null) {
                if (this.x.equals(accessibilityNodeInfo.getViewIdResourceName())) {
                    accessibilityNodeInfo.getBoundsInScreen(new Rect());
                    if (keyCode == 22 && action == 0) {
                        Log.d("DGX", "R  seek to = " + this.L);
                        int i5 = this.L;
                        if (i5 < this.K) {
                            int i6 = this.f;
                            i(i5 + 65, i6 + 30, i5 + 65, i6 + 30, 200);
                        }
                        return false;
                    }
                    if (keyCode == 21 && action == 0) {
                        Log.d("DGX", "L seek to = " + this.L);
                        int i7 = this.L;
                        if (i7 > 0) {
                            int i8 = this.f;
                            i(i7 + 5, i8 + 30, i7 + 5, i8 + 30, 200);
                        }
                        return false;
                    }
                }
                if (this.D.equals(accessibilityNodeInfo.getViewIdResourceName())) {
                    accessibilityNodeInfo.getBoundsInScreen(new Rect());
                    if (keyCode == 22 && action == 0) {
                        Log.d("DGX", "R  seek to = " + this.L);
                        int i9 = this.L;
                        if (i9 < this.K) {
                            int i10 = this.f;
                            i(i9 + 65, i10 + 30, i9 + 65, i10 + 30, 200);
                        }
                        return false;
                    }
                    if (keyCode == 21 && action == 0) {
                        Log.d("DGX", "L seek to = " + this.L);
                        int i11 = this.L;
                        if (i11 > 0) {
                            int i12 = this.f;
                            i(i11 + 5, i12 + 30, i11 + 5, i12 + 30, 200);
                        }
                        return false;
                    }
                }
            }
            if (keyCode == 22 && action == 0) {
                if (this.u == 6) {
                    if (this.s != 1) {
                        int i13 = this.a + 1;
                        this.a = i13;
                        if (i13 > this.b) {
                            this.a = 0;
                        }
                    } else if (a(this.a, 22)) {
                        int i14 = this.g;
                        int i15 = this.f;
                        h(i14, i15, i14 - this.i, i15);
                    } else {
                        int i16 = this.a + 1;
                        this.a = i16;
                        if (i16 > this.b) {
                            this.a = 0;
                        }
                    }
                    g(this.a);
                } else if (a(this.a, 22)) {
                    int i17 = this.g;
                    int i18 = this.f;
                    h(i17, i18, i17 - this.i, i18);
                } else {
                    int i19 = this.a + 1;
                    this.a = i19;
                    if (i19 > this.b) {
                        this.a = 0;
                    }
                    g(this.a);
                }
            }
            if (keyCode == 21 && action == 0) {
                if (this.u == 6) {
                    if (this.s != 1) {
                        int i20 = this.a - 1;
                        this.a = i20;
                        if (i20 < 0) {
                            this.a = this.b;
                        }
                    } else if (a(this.a, 21)) {
                        int i21 = this.g;
                        int i22 = this.f;
                        h(i21, i22, this.i + i21, i22);
                    } else {
                        int i23 = this.a - 1;
                        this.a = i23;
                        if (i23 < 0) {
                            this.a = this.b;
                        }
                    }
                    g(this.a);
                } else if (a(this.a, 21)) {
                    int i24 = this.g;
                    int i25 = this.f;
                    h(i24, i25, this.i + i24, i25);
                } else {
                    int i26 = this.a - 1;
                    this.a = i26;
                    if (i26 < 0) {
                        this.a = this.b;
                    }
                    g(this.a);
                }
            }
            if (keyCode == 20 && action == 0) {
                if (!a(this.a, 20)) {
                    Log.d("IGX", "current focindex = " + this.a);
                    int iC = c(this.a, 20);
                    Log.d("IGX", "down to focindex = " + this.a);
                    if (iC != -1) {
                        this.a = iC;
                    }
                    if (this.a > this.b) {
                        this.a = 0;
                        int i27 = this.f;
                        int i28 = this.i;
                        if (i27 > i28) {
                            int i29 = this.e;
                            h(i29, i27, i29, i27 - i28);
                        }
                    }
                    g(this.a);
                } else if (this.s == 6) {
                    int i30 = this.f;
                    i3 = this.j;
                    if (i30 > i3) {
                        int i31 = this.e;
                        int i32 = this.h;
                        h(i31, i32, i31, i32 - i3);
                    }
                } else {
                    int i33 = this.f;
                    i3 = this.i;
                    if (i33 > i3) {
                        int i34 = this.e;
                        int i35 = this.h;
                        h(i34, i35, i34, i35 - i3);
                    }
                }
            }
            if (keyCode == 19 && action == 0) {
                if (a(this.a, 19)) {
                    int i36 = this.f;
                    if (this.s == 6) {
                        if (i36 < 300) {
                            i36 += this.h;
                        }
                        i = this.e;
                        i2 = this.j;
                    } else {
                        if (i36 < 300) {
                            i36 += this.h;
                        }
                        i = this.e;
                        i2 = this.i;
                    }
                    h(i, i36, i, i2 + i36);
                } else {
                    Log.d("IGX", "current focindex = " + this.a);
                    int iC2 = c(this.a, 19);
                    Log.d("IGX", "up to focindex = " + this.a);
                    if (iC2 != -1) {
                        this.a = iC2;
                    }
                    if (this.a < 0) {
                        this.a = this.b;
                        int i37 = this.e;
                        int i38 = this.f;
                        h(i37, i38, i37, this.i + i38);
                    }
                    g(this.a);
                }
            }
            if (keyCode == 23 && action == 0) {
                if (!new File(f(this.q)).exists()) {
                    int i39 = 0 / 0;
                }
                int i40 = this.e;
                int i41 = this.f;
                h(i40, i41, i40, i41);
            }
            if (!new File(f(this.p)).exists()) {
                int i42 = 0 / 0;
            }
            return super.onKeyEvent(keyEvent);
        }
        return super.onKeyEvent(keyEvent);
    }
}
