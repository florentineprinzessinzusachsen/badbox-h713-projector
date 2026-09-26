package a.a.c.a;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.android.sysapp.R;

/* JADX INFO: loaded from: classes.dex */
public class e extends Dialog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f26a;
    public String b;
    public String c;
    public int d;
    public b e;

    public interface b {
    }

    public class c implements View.OnClickListener {
        public /* synthetic */ c(a aVar) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == R.id.confirm) {
                f fVar = (f) e.this.e;
                Log.i(fVar.b.b, " buildUpdateLogDialog doConfirm");
                fVar.f28a.dismiss();
            }
        }
    }

    public e(Context context, String str, String str2, int i) {
        super(context, R.style.MyUpdateDialog);
        this.f26a = context;
        this.c = str2;
        this.b = str;
        this.d = i;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f26a);
        int i = this.d;
        a aVar = null;
        if (i == 0) {
            i = R.layout.show_dialog;
        }
        View viewInflate = layoutInflaterFrom.inflate(i, (ViewGroup) null);
        setContentView(viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(R.id.confirm);
        textView.setText(this.b);
        ((EditText) viewInflate.findViewById(R.id.msg)).setText(this.c);
        textView.setOnClickListener(new c(aVar));
    }
}
