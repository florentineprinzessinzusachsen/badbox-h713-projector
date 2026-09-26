package a.a.c.a;

import a.a.b.m;
import a.a.b.n;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.shared.FileSelector;
import com.android.sysapp.R;
import com.android.sysapp.UpdateService;

/* JADX INFO: loaded from: classes.dex */
public class g implements View.OnClickListener, View.OnKeyListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f29a;
    public ViewGroup c;
    public ViewGroup d;
    public ViewGroup e;
    public ViewGroup f;
    public UpdateService.k g;
    public a.a.b.h h;
    public CheckBox i;
    public Button j;
    public ProgressBar k;
    public ProgressBar l;
    public ImageView m;
    public TextView n;
    public TextView o;
    public Dialog p;
    public a.a.b.h s;
    public boolean t;
    public String b = "CMUpdate2UIController";
    public a.a.c.a.d[] q = new a.a.c.a.d[2];
    public Handler r = new Handler();
    public int[] u = {0, 0, 0, 0};
    public final ServiceConnection v = new b();

    public class a implements DialogInterface.OnKeyListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            Log.i(g.this.b, "buildCheckLogDialog onKey keyCode=" + i);
            if (i != 4 && i != 111) {
                return true;
            }
            dialogInterface.dismiss();
            return true;
        }
    }

    public class b implements ServiceConnection {

        public class a implements a.a.c.a.c {

            /* JADX INFO: renamed from: a.a.c.a.g$b$a$a, reason: collision with other inner class name */
            public class RunnableC0003a implements Runnable {
                public RunnableC0003a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = g.this.n;
                    if (textView != null) {
                        textView.setText(R.string.install_ota_output_start);
                    }
                }
            }

            public a() {
            }

            public void a(int i) {
                Log.i(g.this.b, "get data=" + i);
                if (i != 201) {
                    if (i == 6000) {
                        g.this.r.post(new RunnableC0003a());
                    }
                } else {
                    Dialog dialog = g.this.p;
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    g.this.e();
                }
            }
        }

        public b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            g gVar = g.this;
            gVar.g = (UpdateService.k) iBinder;
            if (gVar.g == null) {
                return;
            }
            ((Activity) gVar.f29a).getIntent();
            g.this.g.a(new a());
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(g.this.b, "onServiceDisconnected Fail");
        }
    }

    public class c extends a.a.c.a.d {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.n.setText(R.string.check_now);
            }
        }

        public /* synthetic */ c(f fVar) {
        }

        @Override // a.a.c.a.d
        public void a() {
            Handler handler;
            Runnable hVar;
            UpdateService.k kVar = g.this.g;
            if (kVar == null) {
                return;
            }
            int iC = kVar.c(101);
            if (iC == 0) {
                this.f25a = true;
                return;
            }
            if (iC == 1) {
                g.this.r.post(new a());
                return;
            }
            if (iC != 3) {
                return;
            }
            int iA = g.this.g.a(101);
            if (iA == 0) {
                g.this.r.post(new j(this));
                g.this.g.d(101);
                this.f25a = true;
                return;
            }
            if (iA == 1) {
                handler = g.this.r;
                hVar = new h(this);
            } else if (iA == 2) {
                handler = g.this.r;
                hVar = new k(this);
            } else {
                if (iA != 3) {
                    return;
                }
                handler = g.this.r;
                hVar = new i(this);
            }
            handler.post(hVar);
        }
    }

    public class d extends a.a.c.a.d {
        public int b = -1;

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.j.setText(R.string.download_download);
                g.this.j.setTag(Integer.valueOf(R.string.download_download));
                g.this.j.setFocusable(true);
                g.this.j.requestFocus();
            }
        }

        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.j.setText(R.string.download_download);
                g.this.j.setTag(Integer.valueOf(R.string.download_download));
                g.this.j.requestFocus();
                g.this.k.setSecondaryProgress(100);
                g.this.o.setText(R.string.download_failed);
                UpdateService.q = 0;
                g.this.t = false;
            }
        }

        public class c implements Runnable {
            public c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g gVar = g.this;
                gVar.j.setText(gVar.f29a.getString(R.string.download_downloading));
                g.this.j.setTag(Integer.valueOf(R.string.download_pause));
            }
        }

        /* JADX INFO: renamed from: a.a.c.a.g$d$d, reason: collision with other inner class name */
        public class RunnableC0004d implements Runnable {
            public RunnableC0004d() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.j.setText(R.string.download_resume);
                g.this.j.setTag(Integer.valueOf(R.string.download_resume));
            }
        }

        public class e implements Runnable {
            public e() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.j.setText(R.string.download_update);
                g.this.j.setTag(Integer.valueOf(R.string.download_update));
                g.this.j.setFocusable(true);
                g.this.j.requestFocus();
            }
        }

        public class f implements Runnable {
            public f() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.g.d(102);
                g.this.o.setText(R.string.download_error);
            }
        }

        /* JADX INFO: renamed from: a.a.c.a.g$d$g, reason: collision with other inner class name */
        public class RunnableC0005g implements Runnable {
            public RunnableC0005g() {
            }

            @Override // java.lang.Runnable
            public void run() {
                String string;
                StringBuilder sb;
                String str;
                if (g.this.s.c() > 0) {
                    g.this.o.setVisibility(0);
                    g gVar = g.this;
                    TextView textView = gVar.o;
                    long jC = gVar.s.c();
                    int i = d.this.b;
                    long j = jC / 1024;
                    if (j < 1024) {
                        sb = new StringBuilder();
                        sb.append((((long) i) * j) / 100);
                        sb.append("K/");
                        sb.append(j);
                        str = "K";
                    } else {
                        if (j >= 1024) {
                            long j2 = j / 1024;
                            long j3 = j % 1024;
                            sb = new StringBuilder();
                            long j4 = i;
                            sb.append((j2 * j4) / 100);
                            sb.append(".");
                            sb.append((j4 * j3) / 100);
                            sb.append("MB/");
                            sb.append(j2);
                            sb.append(".");
                            sb.append(j3);
                            str = "MB";
                        } else {
                            string = null;
                        }
                        textView.setText(string);
                    }
                    sb.append(str);
                    string = sb.toString();
                    textView.setText(string);
                }
                d dVar = d.this;
                g.this.k.setProgress(dVar.b);
                d dVar2 = d.this;
                if (dVar2.b == 100) {
                    g gVar2 = g.this;
                    gVar2.o.setText(gVar2.f29a.getString(R.string.Download_succeed));
                    g.this.j.setText(R.string.download_update);
                    g.this.j.setTag(Integer.valueOf(R.string.download_update));
                    g.this.j.setFocusable(true);
                    g.this.j.requestFocus();
                }
                Log.i(g.this.b, "lastProgress=" + d.this.b);
            }
        }

        public /* synthetic */ d(a.a.c.a.f fVar) {
        }

        @Override // a.a.c.a.d
        public void a() {
            Handler handler;
            Runnable bVar;
            Handler handler2;
            Runnable cVar;
            UpdateService.k kVar = g.this.g;
            if (kVar == null) {
                return;
            }
            int iC = kVar.c(102);
            Log.d("CMUpdate2", "query status " + iC);
            if (iC != 0) {
                if (iC == 1) {
                    Log.d("CMUpdate2", "2 ");
                    handler2 = g.this.r;
                    cVar = new c();
                } else if (iC == 2) {
                    Log.d("CMUpdate2", "3 ");
                    handler2 = g.this.r;
                    cVar = new RunnableC0004d();
                } else if (iC == 3) {
                    int iA = g.this.g.a(102);
                    Log.d("CMUpdate2", "4，errorCode1= " + iA);
                    if (iA == 0) {
                        handler2 = g.this.r;
                        cVar = new e();
                    } else {
                        handler2 = g.this.r;
                        cVar = new f();
                    }
                }
                handler2.post(cVar);
            } else {
                int iA2 = g.this.g.a(102);
                Log.d("CMUpdate2", "1 errorCode=" + iA2);
                Log.i("qzr", "ErrorCode: " + iA2);
                if (iA2 == 0) {
                    handler = g.this.r;
                    bVar = new a();
                } else {
                    handler = g.this.r;
                    bVar = new b();
                }
                handler.post(bVar);
                this.f25a = true;
            }
            Log.d("CMUpdate2", "5 ");
            int iB = g.this.g.b(102);
            if (this.b != iB) {
                this.b = iB;
                g.this.r.post(new RunnableC0005g());
            }
        }
    }

    public g(Context context, View view) {
        this.f29a = context;
        this.c = (ViewGroup) view.findViewById(R.id.menu_field);
        f fVar = null;
        this.d = (ViewGroup) LayoutInflater.from(this.f29a).inflate(R.layout.menu_main, (ViewGroup) null);
        this.e = (ViewGroup) LayoutInflater.from(this.f29a).inflate(R.layout.menu_update_online, (ViewGroup) null);
        this.f = (ViewGroup) this.d.findViewById(R.id.download_mainui);
        this.d.findViewById(R.id.update_online).setOnClickListener(this);
        this.d.findViewById(R.id.update_local).setOnClickListener(this);
        this.d.findViewById(R.id.update_log).setOnClickListener(this);
        this.d.findViewById(R.id.updateCheckBox).setOnClickListener(this);
        this.e.findViewById(R.id.update_check).setOnClickListener(this);
        this.e.findViewById(R.id.update_or_download).setOnClickListener(this);
        this.e.requestFocus();
        this.d.findViewById(R.id.update_online).setOnKeyListener(this);
        this.d.findViewById(R.id.update_local).setOnKeyListener(this);
        this.d.findViewById(R.id.update_log).setOnKeyListener(this);
        this.e.findViewById(R.id.update_check).setOnKeyListener(this);
        this.e.findViewById(R.id.update_or_download).setOnKeyListener(this);
        ViewGroup viewGroup = this.d;
        this.c.removeAllViews();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400L);
        this.c.setLayoutAnimation(new LayoutAnimationController(alphaAnimation));
        this.c.addView(viewGroup);
        this.d.requestFocus();
        if (n.b) {
            ((TextView) this.d.findViewById(R.id.updatename)).setText(this.f29a.getString(R.string.update_name_test_mode));
        }
        TextView textView = (TextView) this.d.findViewById(R.id.model);
        TextView textView2 = (TextView) this.d.findViewById(R.id.version);
        textView.setText(this.f29a.getString(R.string.system_model) + m.f12a);
        textView2.setText(this.f29a.getString(R.string.system_version) + m.d);
        this.h = new a.a.b.h(this.f29a);
        this.i = (CheckBox) this.d.findViewById(R.id.updateCheckBox);
        this.i.setChecked(this.h.b());
        this.t = false;
        this.f29a.bindService(new Intent(this.f29a, (Class<?>) UpdateService.class), this.v, 1);
        this.q[0] = new c(fVar);
        this.q[1] = new d(fVar);
        this.s = new a.a.b.h(this.f29a);
        if (UpdateService.q == 1) {
            e();
        }
    }

    public final boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f29a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        Log.v("CMUpdate2UIController", "It's can't connect the Internet!");
        return false;
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
        Log.i(this.b, "buildCheckLogDialog");
        this.p = new Dialog(this.f29a, R.style.MyUpdateDialog);
        View viewInflate = LayoutInflater.from(this.f29a).inflate(R.layout.update_checking, (ViewGroup) null);
        this.p.setContentView(viewInflate);
        this.p.setOnKeyListener(new a());
        this.p.show();
        this.m = (ImageView) viewInflate.findViewById(R.id.checking_ok);
        this.l = (ProgressBar) viewInflate.findViewById(R.id.checking_progress);
        this.n = (TextView) viewInflate.findViewById(R.id.checking_status);
        this.n.setText(R.string.check_connecting);
        this.l.setVisibility(0);
        this.m.setVisibility(8);
        this.q[0].b();
        Intent intent = new Intent(this.f29a, (Class<?>) UpdateService.class);
        intent.putExtra("start_command", 101);
        this.f29a.startService(intent);
    }

    public final void e() {
        if (this.t) {
            return;
        }
        View viewInflate = LayoutInflater.from(this.f29a).inflate(R.layout.update_download, (ViewGroup) null);
        this.f.removeAllViews();
        this.f.addView(viewInflate);
        this.j = (Button) viewInflate.findViewById(R.id.download_pause_and_update);
        this.j.setOnClickListener(this);
        this.k = (ProgressBar) viewInflate.findViewById(R.id.download_progress);
        this.o = (TextView) viewInflate.findViewById(R.id.info);
        this.o.setText("");
        this.q[1].b();
        this.t = true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Context context;
        String string;
        if (n.f13a) {
            Log.i(this.b, "onClick,onClick()!!!");
        }
        if (view.getId() == R.id.download_pause_and_update) {
            Object tag = view.getTag();
            int i = 1;
            if (Integer.valueOf(R.string.download_download).equals(tag)) {
                Log.i(this.b, "go to download zip!");
                if (!a()) {
                    Context context2 = this.f29a;
                    Toast.makeText(context2, context2.getString(R.string.net_error), 1).show();
                    return;
                }
                Log.i(this.b, "update_online");
                int i2 = UpdateService.q;
                if (i2 == 0) {
                    d();
                    return;
                } else {
                    if (i2 != 1) {
                        return;
                    }
                    context = this.f29a;
                    string = context.getString(R.string.download_downloading);
                }
            } else {
                if (!Integer.valueOf(R.string.download_pause).equals(tag)) {
                    if (Integer.valueOf(R.string.download_update).equals(tag)) {
                        a.a.c.a.b bVar = new a.a.c.a.b(this.f29a, R.style.MyUpdateDialog_Dim_on);
                        bVar.f = n.h;
                        bVar.b.a(true);
                        bVar.show();
                        return;
                    }
                    return;
                }
                context = this.f29a;
                string = context.getString(R.string.download_downloading_wait);
                i = 0;
            }
            Toast.makeText(context, string, i).show();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Context context;
        int i2;
        int i3;
        Toast toastMakeText;
        if (keyEvent.getAction() == 0) {
            if (n.f13a) {
                Log.i(this.b, "onKey,keyCode=" + i);
            }
            int[] iArr = this.u;
            iArr[3] = iArr[2];
            iArr[2] = iArr[1];
            iArr[1] = iArr[0];
            iArr[0] = i;
            if (i == 66 || i == 23) {
                if (n.f13a) {
                    Log.i(this.b, "onKey,KEYCODE_ENTER!");
                }
                int id = view.getId();
                if (id == R.id.update_local) {
                    if (n.f13a) {
                        Log.i(this.b, "enterEvent update_local");
                    }
                    m.c();
                    Intent intent = new Intent(this.f29a, (Class<?>) FileSelector.class);
                    intent.putExtra("root", (m.f12a.contains("m201") || m.b.equals("S805") || m.b.equals("s805") || m.b.contains("mainz")) ? "/storage" : "/mnt");
                    ((Activity) this.f29a).startActivityForResult(intent, 0);
                } else if (id == R.id.update_online) {
                    if (a()) {
                        Log.i(this.b, "update_online");
                        int i4 = UpdateService.q;
                        if (i4 == 0) {
                            d();
                        } else if (i4 == 1) {
                            context = this.f29a;
                            i2 = R.string.download_downloading;
                            i3 = 1;
                            toastMakeText = Toast.makeText(context, context.getString(i2), i3);
                            toastMakeText.show();
                        }
                    } else {
                        Context context2 = this.f29a;
                        toastMakeText = Toast.makeText(context2, context2.getString(R.string.net_error), 1);
                        toastMakeText.show();
                    }
                } else if (id == R.id.update_check) {
                    Log.i(this.b, "update_check");
                } else if (id == R.id.update_log) {
                    String string = this.h.f8a.getString("update_log", null);
                    if (string == null || string.equals("null")) {
                        context = this.f29a;
                        i2 = R.string.have_no_update_log;
                        i3 = 0;
                        toastMakeText = Toast.makeText(context, context.getString(i2), i3);
                        toastMakeText.show();
                    } else {
                        Log.i(this.b, "buildUpdateLogDialog");
                        String strReplaceAll = string.replaceAll("\\\\n", "\n");
                        Context context3 = this.f29a;
                        e eVar = new e(context3, context3.getString(R.string.sure), strReplaceAll, R.layout.show_update_info);
                        eVar.show();
                        eVar.e = new f(this, eVar);
                    }
                } else if (id == R.id.updateCheckBox) {
                    this.h.a(this.i.isChecked());
                    if (this.i.isChecked()) {
                        context = this.f29a;
                        i2 = R.string.open_auto_update_check;
                    } else {
                        context = this.f29a;
                        i2 = R.string.close_auto_update_check;
                    }
                    i3 = 0;
                    toastMakeText = Toast.makeText(context, context.getString(i2), i3);
                    toastMakeText.show();
                }
            } else if (i == 4) {
                if (n.f13a) {
                    Log.i("Update", "onKey,keyCode == KeyEvent.KEYCODE_BACK,finish!");
                }
                ((Activity) this.f29a).finish();
                return true;
            }
            int[] iArr2 = this.u;
            if (iArr2[3] == 8 && iArr2[2] == 9 && iArr2[1] == 10 && iArr2[0] == 11) {
                n.b = true;
                Log.i(this.b, "test mode");
                n.c = n.a(1);
                ((TextView) this.d.findViewById(R.id.updatename)).setText(this.f29a.getString(R.string.update_name_test_mode));
            }
        }
        return false;
    }
}
