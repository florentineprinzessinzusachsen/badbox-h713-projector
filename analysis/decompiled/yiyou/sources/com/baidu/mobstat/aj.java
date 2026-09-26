package com.baidu.mobstat;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class aj {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final aj f3462b = new aj();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<String, String> f3463a = new HashMap<>();

    private aj() {
        b();
    }

    public static aj a() {
        return f3462b;
    }

    private void b() {
        if (android.os.Build.VERSION.SDK_INT >= 14 && this.f3463a.size() == 0) {
            try {
                a(AutoCompleteTextView.class.getSimpleName(), "A0");
            } catch (Throwable th) {
                a(th);
            }
            try {
                a(ActionBar.class.getSimpleName(), "A1");
            } catch (Throwable th2) {
                a(th2);
            }
            try {
                a(AlertDialog.class.getSimpleName(), "A2");
            } catch (Throwable th3) {
                a(th3);
            }
            try {
                a(Button.class.getSimpleName(), "B0");
            } catch (Throwable th4) {
                a(th4);
            }
            try {
                a(CheckBox.class.getSimpleName(), "C0");
            } catch (Throwable th5) {
                a(th5);
            }
            try {
                a(CheckedTextView.class.getSimpleName(), "C1");
            } catch (Throwable th6) {
                a(th6);
            }
            try {
                a(Class.forName("com.android.internal.policy.DecorView").getSimpleName(), "D0");
            } catch (Throwable th7) {
                a(th7);
            }
            try {
                a(DrawerLayout.class.getSimpleName(), "D1");
            } catch (Throwable th8) {
                a(th8);
            }
            try {
                a(EditText.class.getSimpleName(), "E0");
            } catch (Throwable th9) {
                a(th9);
            }
            try {
                a(ExpandableListView.class.getSimpleName(), "E1");
            } catch (Throwable th10) {
                a(th10);
            }
            try {
                a(FrameLayout.class.getSimpleName(), "F0");
            } catch (Throwable th11) {
                a(th11);
            }
            try {
                a(Fragment.class.getSimpleName(), "F1");
            } catch (Throwable th12) {
                a(th12);
            }
            try {
                a(Gallery.class.getSimpleName(), "G0");
            } catch (Throwable th13) {
                a(th13);
            }
            try {
                a(GridView.class.getSimpleName(), "G1");
            } catch (Throwable th14) {
                a(th14);
            }
            try {
                a(HorizontalScrollView.class.getSimpleName(), "H0");
            } catch (Throwable th15) {
                a(th15);
            }
            try {
                a(ImageButton.class.getSimpleName(), "I0");
            } catch (Throwable th16) {
                a(th16);
            }
            try {
                a(ImageSwitcher.class.getSimpleName(), "I1");
            } catch (Throwable th17) {
                a(th17);
            }
            try {
                a(ImageView.class.getSimpleName(), "I2");
            } catch (Throwable th18) {
                a(th18);
            }
            try {
                a(LinearLayout.class.getSimpleName(), "L0");
            } catch (Throwable th19) {
                a(th19);
            }
            try {
                a(ListView.class.getSimpleName(), "L1");
            } catch (Throwable th20) {
                a(th20);
            }
            try {
                a(ListFragment.class.getSimpleName(), "L2");
            } catch (Throwable th21) {
                a(th21);
            }
            try {
                a(MultiAutoCompleteTextView.class.getSimpleName(), "M0");
            } catch (Throwable th22) {
                a(th22);
            }
            try {
                a(NestedScrollView.class.getSimpleName(), "N0");
            } catch (Throwable th23) {
                a(th23);
            }
            try {
                a(ProgressBar.class.getSimpleName(), "P0");
            } catch (Throwable th24) {
                a(th24);
            }
            try {
                a(RadioButton.class.getSimpleName(), "R0");
            } catch (Throwable th25) {
                a(th25);
            }
            try {
                a(RadioGroup.class.getSimpleName(), "R1");
            } catch (Throwable th26) {
                a(th26);
            }
            try {
                a(RatingBar.class.getSimpleName(), "R2");
            } catch (Throwable th27) {
                a(th27);
            }
            try {
                a(RelativeLayout.class.getSimpleName(), "R3");
            } catch (Throwable th28) {
                a(th28);
            }
            try {
                a(RecyclerView.class.getSimpleName(), "R4");
            } catch (Throwable th29) {
                a(th29);
            }
            try {
                a(ScrollView.class.getSimpleName(), "S0");
            } catch (Throwable th30) {
                a(th30);
            }
            try {
                a(SearchView.class.getSimpleName(), "S1");
            } catch (Throwable th31) {
                a(th31);
            }
            try {
                a(SeekBar.class.getSimpleName(), "S2");
            } catch (Throwable th32) {
                a(th32);
            }
            try {
                a(Spinner.class.getSimpleName(), "S3");
            } catch (Throwable th33) {
                a(th33);
            }
            try {
                a(Switch.class.getSimpleName(), "S4");
            } catch (Throwable th34) {
                a(th34);
            }
            try {
                a(SurfaceView.class.getSimpleName(), "S5");
            } catch (Throwable th35) {
                a(th35);
            }
            try {
                a(SwipeRefreshLayout.class.getSimpleName(), "S6");
            } catch (Throwable th36) {
                a(th36);
            }
            try {
                a(TabHost.class.getSimpleName(), "T0");
            } catch (Throwable th37) {
                a(th37);
            }
            try {
                a(TableLayout.class.getSimpleName(), "T1");
            } catch (Throwable th38) {
                a(th38);
            }
            try {
                a(TableRow.class.getSimpleName(), "T2");
            } catch (Throwable th39) {
                a(th39);
            }
            try {
                a(TabWidget.class.getSimpleName(), "T3");
            } catch (Throwable th40) {
                a(th40);
            }
            try {
                a(TextSwitcher.class.getSimpleName(), "T4");
            } catch (Throwable th41) {
                a(th41);
            }
            try {
                a(TextView.class.getSimpleName(), "T5");
            } catch (Throwable th42) {
                a(th42);
            }
            try {
                a(Toast.class.getSimpleName(), "T6");
            } catch (Throwable th43) {
                a(th43);
            }
            try {
                a(ToggleButton.class.getSimpleName(), "T7");
            } catch (Throwable th44) {
                a(th44);
            }
            try {
                a(TextureView.class.getSimpleName(), "T8");
            } catch (Throwable th45) {
                a(th45);
            }
            try {
                a(Toolbar.class.getSimpleName(), "T9");
            } catch (Throwable th46) {
                a(th46);
            }
            try {
                a(View.class.getSimpleName(), "V0");
            } catch (Throwable th47) {
                a(th47);
            }
            try {
                a(ViewGroup.class.getSimpleName(), "V1");
            } catch (Throwable th48) {
                a(th48);
            }
            try {
                a(ViewStub.class.getSimpleName(), "V2");
            } catch (Throwable th49) {
                a(th49);
            }
            try {
                a(VideoView.class.getSimpleName(), "V3");
            } catch (Throwable th50) {
                a(th50);
            }
            try {
                a(ViewSwitcher.class.getSimpleName(), "V4");
            } catch (Throwable th51) {
                a(th51);
            }
            try {
                a(ViewFlipper.class.getSimpleName(), "V5");
            } catch (Throwable th52) {
                a(th52);
            }
            try {
                a(ViewPager.class.getSimpleName(), "V6");
            } catch (Throwable th53) {
                a(th53);
            }
            try {
                a(WebView.class.getSimpleName(), "W0");
            } catch (Throwable th54) {
                a(th54);
            }
            try {
                a(WebViewFragment.class.getSimpleName(), "W1");
            } catch (Throwable th55) {
                a(th55);
            }
        }
    }

    private void a(Throwable th) {
        if (ao.c().b()) {
            ao.c().b(th.toString());
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || this.f3463a.containsKey(str)) {
            return;
        }
        this.f3463a.put(str, str2.toUpperCase(Locale.ENGLISH));
    }

    public String a(String str) {
        return this.f3463a.get(str);
    }
}
