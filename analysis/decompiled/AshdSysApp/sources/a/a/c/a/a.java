package a.a.c.a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.sysapp.R;

/* JADX INFO: loaded from: classes.dex */
public class a extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f14a;
    public String b;
    public String c;
    public String d;
    public String e;
    public int f;
    public boolean g;
    public b h;

    public interface b {
        void a();

        void b();
    }

    public class c implements View.OnClickListener {
        public /* synthetic */ c(C0001a c0001a) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.cancel) {
                a.this.h.b();
            } else {
                if (id != R.id.confirm) {
                    return;
                }
                a.this.h.a();
            }
        }
    }

    public a(Context context, String str, String str2, int i, String str3, String str4) {
        super(context, R.style.MyUpdateDialog);
        this.b = "";
        this.d = "";
        this.f = 0;
        this.g = false;
        this.f14a = context;
        this.b = str3;
        this.d = str4;
        this.c = str;
        this.e = str2;
        this.f = i;
    }

    public a(Context context, String str, String str2, int i, String str3, String str4, boolean z) {
        super(context, R.style.MyUpdateDialog);
        this.b = "";
        this.d = "";
        this.f = 0;
        this.g = false;
        this.f14a = context;
        this.b = str3;
        this.d = str4;
        this.c = str;
        this.e = str2;
        this.f = i;
        this.g = z;
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f14a);
        int i = this.f;
        C0001a c0001a = null;
        if (i == 0) {
            i = R.layout.show_dialog;
        }
        View viewInflate = layoutInflaterFrom.inflate(i, (ViewGroup) null);
        setContentView(viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(R.id.confirm);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.cancel);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.recovery_title_name);
        TextView textView4 = (TextView) viewInflate.findViewById(R.id.message);
        textView.setText(this.c);
        textView2.setText(this.e);
        if (!this.b.equals("")) {
            textView3.setText(this.b);
        }
        if (!this.d.equals("")) {
            textView4.setText(this.d);
        }
        textView.setOnClickListener(new c(c0001a));
        textView2.setOnClickListener(new c(c0001a));
        if (this.g) {
            textView.setFocusable(true);
            textView.requestFocus();
        } else {
            textView2.setFocusable(true);
            textView2.requestFocus();
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return false;
        }
        if (i == 4) {
            dismiss();
        }
        return super.onKeyDown(i, keyEvent);
    }
}
