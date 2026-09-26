package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.s;
import androidx.lifecycle.t;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: FragmentActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends ComponentActivity implements androidx.core.app.a.InterfaceC0014a, androidx.core.app.a.c {
    boolean i;
    boolean j;
    boolean l;
    boolean m;
    int n;
    a.b.h<String> o;
    final e g = e.a(new a());
    final androidx.lifecycle.i h = new androidx.lifecycle.i(this);
    boolean k = true;

    /* JADX INFO: compiled from: FragmentActivity.java */
    class a extends g<c> implements t, androidx.activity.c {
        public a() {
            super(c.this);
        }

        @Override // androidx.lifecycle.h
        public androidx.lifecycle.e a() {
            return c.this.h;
        }

        @Override // androidx.activity.c
        public OnBackPressedDispatcher b() {
            return c.this.b();
        }

        @Override // androidx.fragment.app.g, androidx.fragment.app.d
        public boolean c() {
            Window window = c.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }

        @Override // androidx.lifecycle.t
        public s d() {
            return c.this.d();
        }

        @Override // androidx.fragment.app.g
        public LayoutInflater i() {
            return c.this.getLayoutInflater().cloneInContext(c.this);
        }

        @Override // androidx.fragment.app.g
        public int j() {
            Window window = c.this.getWindow();
            if (window == null) {
                return 0;
            }
            return window.getAttributes().windowAnimations;
        }

        @Override // androidx.fragment.app.g
        public boolean k() {
            return c.this.getWindow() != null;
        }

        @Override // androidx.fragment.app.g
        public void l() {
            c.this.i();
        }

        @Override // androidx.fragment.app.g
        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            c.this.dump(str, fileDescriptor, printWriter, strArr);
        }

        @Override // androidx.fragment.app.g
        public boolean b(Fragment fragment) {
            return !c.this.isFinishing();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.fragment.app.g
        public c h() {
            return c.this;
        }

        @Override // androidx.fragment.app.g
        public void a(Fragment fragment) {
            c.this.a(fragment);
        }

        @Override // androidx.fragment.app.g, androidx.fragment.app.d
        public View a(int i) {
            return c.this.findViewById(i);
        }
    }

    private void j() {
        while (a(g(), androidx.lifecycle.e.b.CREATED)) {
        }
    }

    final View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.g.a(view, str, context, attributeSet);
    }

    public void a(Fragment fragment) {
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.i);
        printWriter.print(" mResumed=");
        printWriter.print(this.j);
        printWriter.print(" mStopped=");
        printWriter.print(this.k);
        if (getApplication() != null) {
            androidx.loader.a.a.a(this).a(str2, fileDescriptor, printWriter, strArr);
        }
        this.g.j().a(str, fileDescriptor, printWriter, strArr);
    }

    public h g() {
        return this.g.j();
    }

    protected void h() {
        this.h.a(androidx.lifecycle.e.a.ON_RESUME);
        this.g.f();
    }

    @Deprecated
    public void i() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        this.g.k();
        int i3 = i >> 16;
        if (i3 == 0) {
            androidx.core.app.a.b bVarA = androidx.core.app.a.a();
            if (bVarA == null || !bVarA.a(this, i, i2, intent)) {
                super.onActivityResult(i, i2, intent);
                return;
            }
            return;
        }
        int i4 = i3 - 1;
        String strA = this.o.a(i4);
        this.o.c(i4);
        if (strA == null) {
            Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            return;
        }
        Fragment fragmentA = this.g.a(strA);
        if (fragmentA != null) {
            fragmentA.a(i & 65535, i2, intent);
            return;
        }
        Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strA);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.g.k();
        this.g.a(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.e, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.g.a((Fragment) null);
        if (bundle != null) {
            this.g.a(bundle.getParcelable("android:support:fragments"));
            if (bundle.containsKey("android:support:next_request_index")) {
                this.n = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (intArray == null || stringArray == null || intArray.length != stringArray.length) {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else {
                    this.o = new a.b.h<>(intArray.length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.o.c(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.o == null) {
            this.o = new a.b.h<>();
            this.n = 0;
        }
        super.onCreate(bundle);
        this.h.a(androidx.lifecycle.e.a.ON_CREATE);
        this.g.b();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return i == 0 ? super.onCreatePanelMenu(i, menu) | this.g.a(menu, getMenuInflater()) : super.onCreatePanelMenu(i, menu);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewA = a(view, str, context, attributeSet);
        return viewA == null ? super.onCreateView(view, str, context, attributeSet) : viewA;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.g.c();
        this.h.a(androidx.lifecycle.e.a.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.g.d();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.g.b(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.g.a(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.g.a(z);
    }

    @Override // android.app.Activity
    protected void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.g.k();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.g.a(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.j = false;
        this.g.e();
        this.h.a(androidx.lifecycle.e.a.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.g.b(z);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        h();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return i == 0 ? a(view, menu) | this.g.b(menu) : super.onPreparePanel(i, view, menu);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.g.k();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String strA = this.o.a(i3);
            this.o.c(i3);
            if (strA == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragmentA = this.g.a(strA);
            if (fragmentA != null) {
                fragmentA.a(i & 65535, strArr, iArr);
                return;
            }
            Log.w("FragmentActivity", "Activity result no fragment exists for who: " + strA);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.j = true;
        this.g.k();
        this.g.i();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.e, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        j();
        this.h.a(androidx.lifecycle.e.a.ON_STOP);
        Parcelable parcelableL = this.g.l();
        if (parcelableL != null) {
            bundle.putParcelable("android:support:fragments", parcelableL);
        }
        if (this.o.b() > 0) {
            bundle.putInt("android:support:next_request_index", this.n);
            int[] iArr = new int[this.o.b()];
            String[] strArr = new String[this.o.b()];
            for (int i = 0; i < this.o.b(); i++) {
                iArr[i] = this.o.b(i);
                strArr[i] = this.o.d(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.k = false;
        if (!this.i) {
            this.i = true;
            this.g.a();
        }
        this.g.k();
        this.g.i();
        this.h.a(androidx.lifecycle.e.a.ON_START);
        this.g.g();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.g.k();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.k = true;
        j();
        this.g.h();
        this.h.a(androidx.lifecycle.e.a.ON_STOP);
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        if (!this.m && i != -1) {
            a(i);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (!this.l && i != -1) {
            a(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Deprecated
    protected boolean a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    static void a(int i) {
        if ((i & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewA = a(null, str, context, attributeSet);
        return viewA == null ? super.onCreateView(str, context, attributeSet) : viewA;
    }

    private static boolean a(h hVar, androidx.lifecycle.e.b bVar) {
        boolean zA = false;
        for (Fragment fragment : hVar.e()) {
            if (fragment != null) {
                if (fragment.a().a().a(androidx.lifecycle.e.b.STARTED)) {
                    fragment.S.b(bVar);
                    zA = true;
                }
                if (fragment.s() != null) {
                    zA |= a(fragment.l(), bVar);
                }
            }
        }
        return zA;
    }

    @Override // android.app.Activity
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, Bundle bundle) {
        if (!this.m && i != -1) {
            a(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.l && i != -1) {
            a(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }
}
