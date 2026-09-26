package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.f.u;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: MenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements androidx.core.b.a.a {
    private static final int[] A = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f469c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f470d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f471e;
    private ContextMenu.ContextMenuInfo m;
    CharSequence n;
    Drawable o;
    View p;
    private j x;
    private boolean z;
    private int l = 0;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private ArrayList<j> v = new ArrayList<>();
    private CopyOnWriteArrayList<WeakReference<m>> w = new CopyOnWriteArrayList<>();
    private boolean y = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<j> f472f = new ArrayList<>();
    private ArrayList<j> g = new ArrayList<>();
    private boolean h = true;
    private ArrayList<j> i = new ArrayList<>();
    private ArrayList<j> j = new ArrayList<>();
    private boolean k = true;

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface a {
        void a(g gVar);

        boolean a(g gVar, MenuItem menuItem);
    }

    /* JADX INFO: compiled from: MenuBuilder.java */
    public interface b {
        boolean a(j jVar);
    }

    public g(Context context) {
        this.f467a = context;
        this.f468b = context.getResources();
        e(true);
    }

    private void d(boolean z) {
        if (this.w.isEmpty()) {
            return;
        }
        s();
        for (WeakReference<m> weakReference : this.w) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.w.remove(weakReference);
            } else {
                mVar.a(z);
            }
        }
        r();
    }

    private void e(boolean z) {
        this.f470d = z && this.f468b.getConfiguration().keyboard != 1 && u.a(ViewConfiguration.get(this.f467a), this.f467a);
    }

    private static int f(int i) {
        int i2 = ((-65536) & i) >> 16;
        if (i2 >= 0) {
            int[] iArr = A;
            if (i2 < iArr.length) {
                return (i & 65535) | (iArr[i2] << 16);
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public void a(m mVar) {
        a(mVar, this.f467a);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.f467a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i6 = 0; i6 < size; i6++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i6);
            int i7 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i7 < 0 ? intent : intentArr[i7]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(m mVar) {
        for (WeakReference<m> weakReference : this.w) {
            m mVar2 = weakReference.get();
            if (mVar2 == null || mVar2 == mVar) {
                this.w.remove(weakReference);
            }
        }
    }

    public g c(int i) {
        this.l = i;
        return this;
    }

    @Override // android.view.Menu
    public void clear() {
        j jVar = this.x;
        if (jVar != null) {
            a(jVar);
        }
        this.f472f.clear();
        b(true);
    }

    public void clearHeader() {
        this.o = null;
        this.n = null;
        this.p = null;
        b(false);
    }

    @Override // android.view.Menu
    public void close() {
        a(true);
    }

    protected String d() {
        return "android:menu:actionviewstates";
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = this.f472f.get(i2);
            if (jVar.getItemId() == i) {
                return jVar;
            }
            if (jVar.hasSubMenu() && (menuItemFindItem = jVar.getSubMenu().findItem(i)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public Drawable g() {
        return this.o;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.f472f.get(i);
    }

    public CharSequence h() {
        return this.n;
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.z) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.f472f.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public View i() {
        return this.p;
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return a(i, keyEvent) != null;
    }

    public ArrayList<j> j() {
        b();
        return this.j;
    }

    boolean k() {
        return this.t;
    }

    Resources l() {
        return this.f468b;
    }

    public g m() {
        return this;
    }

    public ArrayList<j> n() {
        if (!this.h) {
            return this.g;
        }
        this.g.clear();
        int size = this.f472f.size();
        for (int i = 0; i < size; i++) {
            j jVar = this.f472f.get(i);
            if (jVar.isVisible()) {
                this.g.add(jVar);
            }
        }
        this.h = false;
        this.k = true;
        return this.g;
    }

    public boolean o() {
        return this.y;
    }

    boolean p() {
        return this.f469c;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return a(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        j jVarA = a(i, keyEvent);
        boolean zA = jVarA != null ? a(jVarA, i2) : false;
        if ((i2 & 2) != 0) {
            a(true);
        }
        return zA;
    }

    public boolean q() {
        return this.f470d;
    }

    public void r() {
        this.q = false;
        if (this.r) {
            this.r = false;
            b(this.s);
        }
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int iA = a(i);
        if (iA >= 0) {
            int size = this.f472f.size() - iA;
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= size || this.f472f.get(iA).getGroupId() != i) {
                    break;
                }
                a(iA, false);
                i2 = i3;
            }
            b(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        a(b(i), true);
    }

    public void s() {
        if (this.q) {
            return;
        }
        this.q = true;
        this.r = false;
        this.s = false;
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f472f.size();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = this.f472f.get(i2);
            if (jVar.getGroupId() == i) {
                jVar.c(z2);
                jVar.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.y = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        int size = this.f472f.size();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = this.f472f.get(i2);
            if (jVar.getGroupId() == i) {
                jVar.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.f472f.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = this.f472f.get(i2);
            if (jVar.getGroupId() == i && jVar.e(z)) {
                z2 = true;
            }
        }
        if (z2) {
            b(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f469c = z;
        b(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.f472f.size();
    }

    public void a(m mVar, Context context) {
        this.w.add(new WeakReference<>(mVar));
        mVar.a(context, this);
        this.k = true;
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.f468b.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f468b.getString(i));
    }

    void c(j jVar) {
        this.k = true;
        b(true);
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        j jVar = (j) a(i, i2, i3, charSequence);
        r rVar = new r(this.f467a, this, jVar);
        jVar.a(rVar);
        return rVar;
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.f468b.getString(i4));
    }

    public void b(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).b(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(d(), sparseArray);
        }
    }

    public ArrayList<j> c() {
        b();
        return this.i;
    }

    public j f() {
        return this.x;
    }

    private boolean a(r rVar, m mVar) {
        if (this.w.isEmpty()) {
            return false;
        }
        boolean zA = mVar != null ? mVar.a(rVar) : false;
        for (WeakReference<m> weakReference : this.w) {
            m mVar2 = weakReference.get();
            if (mVar2 == null) {
                this.w.remove(weakReference);
            } else if (!zA) {
                zA = mVar2.a(rVar);
            }
        }
        return zA;
    }

    public Context e() {
        return this.f467a;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f468b.getString(i4));
    }

    public void c(boolean z) {
        this.z = z;
    }

    protected g e(int i) {
        a(i, null, 0, null, null);
        return this;
    }

    void d(j jVar) {
        this.h = true;
        b(true);
    }

    protected g d(int i) {
        a(0, null, i, null, null);
        return this;
    }

    public void a(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(d());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((r) item.getSubMenu()).a(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 <= 0 || (menuItemFindItem = findItem(i2)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    public int b(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.f472f.get(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public void b(boolean z) {
        if (!this.q) {
            if (z) {
                this.h = true;
                this.k = true;
            }
            d(z);
            return;
        }
        this.r = true;
        if (z) {
            this.s = true;
        }
    }

    public void a(a aVar) {
        this.f471e = aVar;
    }

    protected MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int iF = f(i3);
        j jVarA = a(i, i2, i3, iF, charSequence, this.l);
        ContextMenu.ContextMenuInfo contextMenuInfo = this.m;
        if (contextMenuInfo != null) {
            jVarA.a(contextMenuInfo);
        }
        ArrayList<j> arrayList = this.f472f;
        arrayList.add(a(arrayList, iF), jVarA);
        b(true);
        return jVarA;
    }

    public void b() {
        ArrayList<j> arrayListN = n();
        if (this.k) {
            boolean zA = false;
            for (WeakReference<m> weakReference : this.w) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.w.remove(weakReference);
                } else {
                    zA |= mVar.a();
                }
            }
            if (zA) {
                this.i.clear();
                this.j.clear();
                int size = arrayListN.size();
                for (int i = 0; i < size; i++) {
                    j jVar = arrayListN.get(i);
                    if (jVar.h()) {
                        this.i.add(jVar);
                    } else {
                        this.j.add(jVar);
                    }
                }
            } else {
                this.i.clear();
                this.j.clear();
                this.j.addAll(n());
            }
            this.k = false;
        }
    }

    private j a(int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        return new j(this, i, i2, i3, i4, charSequence, i5);
    }

    private void a(int i, boolean z) {
        if (i < 0 || i >= this.f472f.size()) {
            return;
        }
        this.f472f.remove(i);
        if (z) {
            b(true);
        }
    }

    void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f472f.size();
        s();
        for (int i = 0; i < size; i++) {
            j jVar = this.f472f.get(i);
            if (jVar.getGroupId() == groupId && jVar.i() && jVar.isCheckable()) {
                jVar.b(jVar == menuItem);
            }
        }
        r();
    }

    public boolean b(j jVar) {
        boolean zB = false;
        if (this.w.isEmpty()) {
            return false;
        }
        s();
        for (WeakReference<m> weakReference : this.w) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.w.remove(weakReference);
            } else {
                zB = mVar.b(this, jVar);
                if (zB) {
                    break;
                }
            }
        }
        r();
        if (zB) {
            this.x = jVar;
        }
        return zB;
    }

    public int a(int i) {
        return a(i, 0);
    }

    public int a(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < size) {
            if (this.f472f.get(i2).getGroupId() == i) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    boolean a(g gVar, MenuItem menuItem) {
        a aVar = this.f471e;
        return aVar != null && aVar.a(gVar, menuItem);
    }

    public void a() {
        a aVar = this.f471e;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    private static int a(ArrayList<j> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size).c() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    void a(List<j> list, int i, KeyEvent keyEvent) {
        boolean zP = p();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f472f.size();
            for (int i2 = 0; i2 < size; i2++) {
                j jVar = this.f472f.get(i2);
                if (jVar.hasSubMenu()) {
                    ((g) jVar.getSubMenu()).a(list, i, keyEvent);
                }
                char alphabeticShortcut = zP ? jVar.getAlphabeticShortcut() : jVar.getNumericShortcut();
                if (((modifiers & 69647) == ((zP ? jVar.getAlphabeticModifiers() : jVar.getNumericModifiers()) & 69647)) && alphabeticShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((alphabeticShortcut == cArr[0] || alphabeticShortcut == cArr[2] || (zP && alphabeticShortcut == '\b' && i == 67)) && jVar.isEnabled()) {
                        list.add(jVar);
                    }
                }
            }
        }
    }

    j a(int i, KeyEvent keyEvent) {
        char numericShortcut;
        ArrayList<j> arrayList = this.v;
        arrayList.clear();
        a(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean zP = p();
        for (int i2 = 0; i2 < size; i2++) {
            j jVar = arrayList.get(i2);
            if (zP) {
                numericShortcut = jVar.getAlphabeticShortcut();
            } else {
                numericShortcut = jVar.getNumericShortcut();
            }
            if ((numericShortcut == keyData.meta[0] && (metaState & 2) == 0) || ((numericShortcut == keyData.meta[2] && (metaState & 2) != 0) || (zP && numericShortcut == '\b' && i == 67))) {
                return jVar;
            }
        }
        return null;
    }

    public boolean a(MenuItem menuItem, int i) {
        return a(menuItem, (m) null, i);
    }

    public boolean a(MenuItem menuItem, m mVar, int i) {
        j jVar = (j) menuItem;
        if (jVar == null || !jVar.isEnabled()) {
            return false;
        }
        boolean zG = jVar.g();
        androidx.core.f.b bVarA = jVar.a();
        boolean z = bVarA != null && bVarA.a();
        if (jVar.f()) {
            zG |= jVar.expandActionView();
            if (zG) {
                a(true);
            }
        } else if (jVar.hasSubMenu() || z) {
            if ((i & 4) == 0) {
                a(false);
            }
            if (!jVar.hasSubMenu()) {
                jVar.a(new r(e(), this, jVar));
            }
            r rVar = (r) jVar.getSubMenu();
            if (z) {
                bVarA.a(rVar);
            }
            zG |= a(rVar, mVar);
            if (!zG) {
                a(true);
            }
        } else if ((i & 1) == 0) {
            a(true);
        }
        return zG;
    }

    public final void a(boolean z) {
        if (this.u) {
            return;
        }
        this.u = true;
        for (WeakReference<m> weakReference : this.w) {
            m mVar = weakReference.get();
            if (mVar == null) {
                this.w.remove(weakReference);
            } else {
                mVar.a(this, z);
            }
        }
        this.u = false;
    }

    private void a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resourcesL = l();
        if (view != null) {
            this.p = view;
            this.n = null;
            this.o = null;
        } else {
            if (i > 0) {
                this.n = resourcesL.getText(i);
            } else if (charSequence != null) {
                this.n = charSequence;
            }
            if (i2 > 0) {
                this.o = androidx.core.content.a.c(e(), i2);
            } else if (drawable != null) {
                this.o = drawable;
            }
            this.p = null;
        }
        b(false);
    }

    protected g a(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    protected g a(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    protected g a(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    public boolean a(j jVar) {
        boolean zA = false;
        if (!this.w.isEmpty() && this.x == jVar) {
            s();
            for (WeakReference<m> weakReference : this.w) {
                m mVar = weakReference.get();
                if (mVar == null) {
                    this.w.remove(weakReference);
                } else {
                    zA = mVar.a(this, jVar);
                    if (zA) {
                        break;
                    }
                }
            }
            r();
            if (zA) {
                this.x = null;
            }
        }
        return zA;
    }
}
