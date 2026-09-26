package androidx.appcompat.d;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import androidx.appcompat.R$style;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Resources.Theme f363b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private LayoutInflater f364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Configuration f365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Resources f366e;

    public d() {
        super(null);
    }

    private Resources b() {
        if (this.f366e == null) {
            Configuration configuration = this.f365d;
            if (configuration == null) {
                this.f366e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f366e = createConfigurationContext(configuration).getResources();
            }
        }
        return this.f366e;
    }

    private void c() {
        boolean z = this.f363b == null;
        if (z) {
            this.f363b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f363b.setTo(theme);
            }
        }
        a(this.f363b, this.f362a, z);
    }

    public int a() {
        return this.f362a;
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return b();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f364c == null) {
            this.f364c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f364c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f363b;
        if (theme != null) {
            return theme;
        }
        if (this.f362a == 0) {
            this.f362a = R$style.Theme_AppCompat_Light;
        }
        c();
        return this.f363b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f362a != i) {
            this.f362a = i;
            c();
        }
    }

    public d(Context context, int i) {
        super(context);
        this.f362a = i;
    }

    protected void a(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f363b = theme;
    }
}
