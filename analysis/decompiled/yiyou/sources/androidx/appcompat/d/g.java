package androidx.appcompat.d;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.d0;
import androidx.appcompat.widget.p;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: SupportMenuInflater.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final Class<?>[] f377e = {Context.class};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final Class<?>[] f378f = f377e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object[] f379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Object[] f380b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Context f381c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Object f382d;

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private static class a implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final Class<?>[] f383c = {MenuItem.class};

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Method f385b;

        public a(Object obj, String str) {
            this.f384a = obj;
            Class<?> cls = obj.getClass();
            try {
                this.f385b = cls.getMethod(str, f383c);
            } catch (Exception e2) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e2);
                throw inflateException;
            }
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f385b.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f385b.invoke(this.f384a, menuItem)).booleanValue();
                }
                this.f385b.invoke(this.f384a, menuItem);
                return true;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public g(Context context) {
        super(context);
        this.f381c = context;
        this.f379a = new Object[]{context};
        this.f380b = this.f379a;
    }

    private void a(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        b bVar = new b(menu);
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("menu")) {
                    eventType = xmlPullParser.next();
                    break;
                }
                throw new RuntimeException("Expecting menu, got " + name);
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        int next = eventType;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            if (next == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (next != 2) {
                if (next == 3) {
                    String name2 = xmlPullParser.getName();
                    if (z2 && name2.equals(str)) {
                        str = null;
                        z2 = false;
                    } else if (name2.equals("group")) {
                        bVar.d();
                    } else if (name2.equals("item")) {
                        if (!bVar.c()) {
                            androidx.core.f.b bVar2 = bVar.A;
                            if (bVar2 == null || !bVar2.a()) {
                                bVar.a();
                            } else {
                                bVar.b();
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z = true;
                    }
                }
            } else if (!z2) {
                String name3 = xmlPullParser.getName();
                if (name3.equals("group")) {
                    bVar.a(attributeSet);
                } else if (name3.equals("item")) {
                    bVar.b(attributeSet);
                } else if (name3.equals("menu")) {
                    a(xmlPullParser, attributeSet, bVar.b());
                } else {
                    str = name3;
                    z2 = true;
                }
            }
            next = xmlPullParser.next();
        }
    }

    @Override // android.view.MenuInflater
    public void inflate(int i, Menu menu) {
        if (!(menu instanceof androidx.core.b.a.a)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f381c.getResources().getLayout(i);
                    a(layout, Xml.asAttributeSet(layout), menu);
                    if (layout != null) {
                        layout.close();
                    }
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (IOException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }

    /* JADX INFO: compiled from: SupportMenuInflater.java */
    private class b {
        androidx.core.f.b A;
        private CharSequence B;
        private CharSequence C;
        private ColorStateList D = null;
        private PorterDuff.Mode E = null;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Menu f386a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f388c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f389d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f390e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f391f;
        private boolean g;
        private boolean h;
        private int i;
        private int j;
        private CharSequence k;
        private CharSequence l;
        private int m;
        private char n;
        private int o;
        private char p;
        private int q;
        private int r;
        private boolean s;
        private boolean t;
        private boolean u;
        private int v;
        private int w;
        private String x;
        private String y;
        private String z;

        public b(Menu menu) {
            this.f386a = menu;
            d();
        }

        public void a(AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = g.this.f381c.obtainStyledAttributes(attributeSet, R$styleable.MenuGroup);
            this.f387b = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.f388c = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.f389d = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.f390e = typedArrayObtainStyledAttributes.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.f391f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public void b(AttributeSet attributeSet) {
            d0 d0VarA = d0.a(g.this.f381c, attributeSet, R$styleable.MenuItem);
            this.i = d0VarA.g(R$styleable.MenuItem_android_id, 0);
            this.j = (d0VarA.d(R$styleable.MenuItem_android_menuCategory, this.f388c) & (-65536)) | (d0VarA.d(R$styleable.MenuItem_android_orderInCategory, this.f389d) & 65535);
            this.k = d0VarA.e(R$styleable.MenuItem_android_title);
            this.l = d0VarA.e(R$styleable.MenuItem_android_titleCondensed);
            this.m = d0VarA.g(R$styleable.MenuItem_android_icon, 0);
            this.n = a(d0VarA.d(R$styleable.MenuItem_android_alphabeticShortcut));
            this.o = d0VarA.d(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.p = a(d0VarA.d(R$styleable.MenuItem_android_numericShortcut));
            this.q = d0VarA.d(R$styleable.MenuItem_numericModifiers, 4096);
            if (d0VarA.g(R$styleable.MenuItem_android_checkable)) {
                this.r = d0VarA.a(R$styleable.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.r = this.f390e;
            }
            this.s = d0VarA.a(R$styleable.MenuItem_android_checked, false);
            this.t = d0VarA.a(R$styleable.MenuItem_android_visible, this.f391f);
            this.u = d0VarA.a(R$styleable.MenuItem_android_enabled, this.g);
            this.v = d0VarA.d(R$styleable.MenuItem_showAsAction, -1);
            this.z = d0VarA.d(R$styleable.MenuItem_android_onClick);
            this.w = d0VarA.g(R$styleable.MenuItem_actionLayout, 0);
            this.x = d0VarA.d(R$styleable.MenuItem_actionViewClass);
            this.y = d0VarA.d(R$styleable.MenuItem_actionProviderClass);
            boolean z = this.y != null;
            if (z && this.w == 0 && this.x == null) {
                this.A = (androidx.core.f.b) a(this.y, g.f378f, g.this.f380b);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.A = null;
            }
            this.B = d0VarA.e(R$styleable.MenuItem_contentDescription);
            this.C = d0VarA.e(R$styleable.MenuItem_tooltipText);
            if (d0VarA.g(R$styleable.MenuItem_iconTintMode)) {
                this.E = p.a(d0VarA.d(R$styleable.MenuItem_iconTintMode, -1), this.E);
            } else {
                this.E = null;
            }
            if (d0VarA.g(R$styleable.MenuItem_iconTint)) {
                this.D = d0VarA.a(R$styleable.MenuItem_iconTint);
            } else {
                this.D = null;
            }
            d0VarA.a();
            this.h = false;
        }

        public boolean c() {
            return this.h;
        }

        public void d() {
            this.f387b = 0;
            this.f388c = 0;
            this.f389d = 0;
            this.f390e = 0;
            this.f391f = true;
            this.g = true;
        }

        private char a(String str) {
            if (str == null) {
                return (char) 0;
            }
            return str.charAt(0);
        }

        private void a(MenuItem menuItem) {
            boolean z = false;
            menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
            int i = this.v;
            if (i >= 0) {
                menuItem.setShowAsAction(i);
            }
            if (this.z != null) {
                if (!g.this.f381c.isRestricted()) {
                    menuItem.setOnMenuItemClickListener(new a(g.this.a(), this.z));
                } else {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
            }
            boolean z2 = menuItem instanceof j;
            if (z2) {
            }
            if (this.r >= 2) {
                if (z2) {
                    ((j) menuItem).c(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).a(true);
                }
            }
            String str = this.x;
            if (str != null) {
                menuItem.setActionView((View) a(str, g.f377e, g.this.f379a));
                z = true;
            }
            int i2 = this.w;
            if (i2 > 0) {
                if (!z) {
                    menuItem.setActionView(i2);
                } else {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                }
            }
            androidx.core.f.b bVar = this.A;
            if (bVar != null) {
                androidx.core.f.g.a(menuItem, bVar);
            }
            androidx.core.f.g.a(menuItem, this.B);
            androidx.core.f.g.b(menuItem, this.C);
            androidx.core.f.g.a(menuItem, this.n, this.o);
            androidx.core.f.g.b(menuItem, this.p, this.q);
            PorterDuff.Mode mode = this.E;
            if (mode != null) {
                androidx.core.f.g.a(menuItem, mode);
            }
            ColorStateList colorStateList = this.D;
            if (colorStateList != null) {
                androidx.core.f.g.a(menuItem, colorStateList);
            }
        }

        public SubMenu b() {
            this.h = true;
            SubMenu subMenuAddSubMenu = this.f386a.addSubMenu(this.f387b, this.i, this.j, this.k);
            a(subMenuAddSubMenu.getItem());
            return subMenuAddSubMenu;
        }

        public void a() {
            this.h = true;
            a(this.f386a.add(this.f387b, this.i, this.j, this.k));
        }

        private <T> T a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                Constructor<?> constructor = Class.forName(str, false, g.this.f381c.getClassLoader()).getConstructor(clsArr);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(objArr);
            } catch (Exception e2) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
                return null;
            }
        }
    }

    Object a() {
        if (this.f382d == null) {
            this.f382d = a(this.f381c);
        }
        return this.f382d;
    }

    private Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }
}
