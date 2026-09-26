package a.a.c.a;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.UpdateEngine;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.sysapp.R;
import com.android.sysapp.UpdateService;

/* JADX INFO: loaded from: classes.dex */
public class b extends Dialog implements a.a.b.g.a {
    public static String l = "";
    public static int m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ProgressBar f16a;
    public a.a.b.g b;
    public LinearLayout c;
    public LayoutInflater d;
    public View e;
    public String f;
    public final Handler g;
    public final Context h;
    public boolean i;
    public a.a.b.h j;
    public TextView k;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            b.this.j.a(0);
            b bVar = b.this;
            bVar.b.a(bVar.f, bVar);
        }
    }

    /* JADX INFO: renamed from: a.a.c.a.b$b, reason: collision with other inner class name */
    public class RunnableC0002b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f18a;

        public RunnableC0002b(int i) {
            this.f18a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f18a;
            if (i == 0) {
                TextView textView = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
                textView.setTextColor(-16777216);
                textView.setText(R.string.install_ota_output_checking);
                b.this.c.addView(textView);
            } else if (i == 100) {
                TextView textView2 = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
                textView2.setTextColor(-16711936);
                textView2.setText(R.string.install_ota_output_check_ok);
                b.this.c.addView(textView2);
                Log.i("CMUpdate2InstallPackage", "On check success");
            }
            Log.i("CMUpdate2InstallPackage", "On progress =" + this.f18a);
            b.this.f16a.setProgress(this.f18a / 2);
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f19a;

        public c(int i) {
            this.f19a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            TextView textView = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
            int i2 = this.f19a;
            if (i2 == 1) {
                i = R.string.install_ota_output_check_file_error;
            } else {
                i = i2 == 0 ? R.string.install_ota_output_check_upgrade_error : R.string.install_ota_output_check_unkown_error;
            }
            textView.setText(i);
            textView.setTextColor(-65536);
            b.this.c.addView(textView);
            UpdateService.q = 0;
            a.a.b.k.a();
            b.this.i = true;
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f20a;

        public d(int i) {
            this.f20a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.f16a.setProgress((this.f20a / 2) + 50);
            int i = this.f20a;
            if (i == 0) {
                TextView textView = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
                textView.setText(R.string.install_ota_output_copying);
                textView.setTextColor(-16777216);
                textView.setTextSize(20.0f);
                b.this.c.addView(textView);
                return;
            }
            if (i == 100) {
                TextView textView2 = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
                textView2.setTextColor(-16711936);
                textView2.setText(R.string.install_ota_output_copy_ok);
                b.this.c.addView(textView2);
                TextView textView3 = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
                textView3.setText(R.string.install_ota_output_restart);
                textView3.setTextColor(-16711936);
                b.this.c.addView(textView3);
                Log.i("CMUpdate2InstallPackage", "On copy success");
                Log.i("CMUpdate2InstallPackage", "Normal Update Mode！installPackage() ");
            }
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            TextView textView = (TextView) b.this.d.inflate(R.layout.medium_text, (ViewGroup) null);
            int i2 = b.m;
            if (i2 > 0) {
                i = -16711936;
            } else {
                i = i2 == 0 ? -16777216 : -65536;
            }
            textView.setTextColor(i);
            textView.setText(b.l);
            b.this.c.addView(textView);
        }
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            b.this.b.a();
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.j.a(0);
            a.a.b.k.a();
            b.this.b.b();
        }
    }

    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f24a;

        public h(int i) {
            this.f24a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            TextView textView = bVar.k;
            if (textView != null) {
                textView.setText(b.this.getContext().getString(R.string.install_ota_output_restart) + "..." + this.f24a + "%");
                return;
            }
            bVar.k = (TextView) bVar.d.inflate(R.layout.medium_text, (ViewGroup) null);
            b.this.k.setTextColor(-65536);
            b.this.k.setText(b.this.getContext().getString(R.string.install_ota_output_restart) + "..." + this.f24a + "%");
            b bVar2 = b.this;
            bVar2.c.addView(bVar2.k);
        }
    }

    public b(Context context, int i) {
        super(context, i);
        this.g = new Handler();
        this.i = false;
        this.h = context;
        this.b = new a.a.b.g(context);
        this.d = LayoutInflater.from(context);
        this.e = this.d.inflate(R.layout.install_ota, (ViewGroup) null);
        setContentView(this.e);
        this.f16a = (ProgressBar) findViewById(R.id.verify_progress);
        this.c = (LinearLayout) findViewById(R.id.output_field);
        TextView textView = (TextView) this.d.inflate(R.layout.medium_text, (ViewGroup) null);
        textView.setText(R.string.install_ota_output_confirm);
        textView.setTextColor(-16777216);
        this.c.addView(textView);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(600L);
        this.c.setLayoutAnimation(new LayoutAnimationController(alphaAnimation));
        if (this.j == null) {
            this.j = new a.a.b.h(this.h);
        }
        new UpdateEngine();
        new Thread(new a()).start();
    }

    @Override // a.a.b.g.a
    public void a(int i) {
        this.g.post(new d(i));
    }

    @Override // a.a.b.g.a
    public void a(int i, Object obj) {
        this.g.post(new c(i));
    }

    @Override // a.a.b.g.a
    public void a(String str, int i) {
        l = str;
        m = i;
        this.g.post(new e());
    }

    @Override // a.a.b.g.a
    public void b(String str, int i) {
        Handler handler;
        Runnable gVar;
        if (i == -1) {
            handler = this.g;
            gVar = new f();
        } else if (i != 101) {
            this.g.post(new h(i));
            return;
        } else {
            handler = this.g;
            gVar = new g();
        }
        handler.post(gVar);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!this.i || i != 4) {
            return true;
        }
        dismiss();
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (!this.i || i != 4) {
            return true;
        }
        dismiss();
        return true;
    }

    @Override // a.a.b.g.a, android.os.RecoverySystem.ProgressListener
    public void onProgress(int i) {
        this.g.post(new RunnableC0002b(i));
    }
}
