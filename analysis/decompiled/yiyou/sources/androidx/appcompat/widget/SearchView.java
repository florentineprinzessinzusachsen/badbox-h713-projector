package androidx.appcompat.widget;

import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends LinearLayoutCompat implements androidx.appcompat.d.c {
    static final k r0 = new k();
    private Rect A;
    private int[] B;
    private int[] C;
    private final ImageView D;
    private final Drawable F;
    private final int G;
    private final int H;
    private final Intent I;
    private final Intent J;
    private final CharSequence K;
    private m L;
    private l M;
    View.OnFocusChangeListener N;
    private n O;
    private View.OnClickListener P;
    private boolean Q;
    private boolean R;
    a.d.a.a S;
    private boolean T;
    private CharSequence U;
    private boolean V;
    private boolean W;
    private int a0;
    private boolean b0;
    private CharSequence c0;
    private CharSequence d0;
    private boolean e0;
    private int f0;
    SearchableInfo g0;
    private Bundle h0;
    private final Runnable i0;
    private Runnable j0;
    private final WeakHashMap<String, Drawable.ConstantState> k0;
    private final View.OnClickListener l0;
    View.OnKeyListener m0;
    private final TextView.OnEditorActionListener n0;
    private final AdapterView.OnItemClickListener o0;
    final SearchAutoComplete p;
    private final AdapterView.OnItemSelectedListener p0;
    private final View q;
    private TextWatcher q0;
    private final View r;
    private final View s;
    final ImageView t;
    final ImageView u;
    final ImageView v;
    final ImageView w;
    private final View x;
    private p y;
    private Rect z;

    public static class SearchAutoComplete extends AppCompatAutoCompleteTextView {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f647d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private SearchView f648e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f649f;
        final Runnable g;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                SearchAutoComplete.this.b();
            }
        }

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        private int getSearchViewTextMinWidthDp() {
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            if (i >= 960 && i2 >= 720 && configuration.orientation == 2) {
                return 256;
            }
            if (i < 600) {
                return (i < 640 || i2 < 480) ? 160 : 192;
            }
            return 192;
        }

        boolean a() {
            return TextUtils.getTrimmedLength(getText()) == 0;
        }

        void b() {
            if (this.f649f) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                this.f649f = false;
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public boolean enoughToFilter() {
            return this.f647d <= 0 || super.enoughToFilter();
        }

        @Override // androidx.appcompat.widget.AppCompatAutoCompleteTextView, android.widget.TextView, android.view.View
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.f649f) {
                removeCallbacks(this.g);
                post(this.g);
            }
            return inputConnectionOnCreateInputConnection;
        }

        @Override // android.view.View
        protected void onFinishInflate() {
            super.onFinishInflate();
            setMinWidth((int) TypedValue.applyDimension(1, getSearchViewTextMinWidthDp(), getResources().getDisplayMetrics()));
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.f648e.i();
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                }
                if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f648e.clearFocus();
                        setImeVisibility(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f648e.hasFocus() && getVisibility() == 0) {
                this.f649f = true;
                if (SearchView.a(getContext())) {
                    SearchView.r0.a(this, true);
                }
            }
        }

        @Override // android.widget.AutoCompleteTextView
        public void performCompletion() {
        }

        @Override // android.widget.AutoCompleteTextView
        protected void replaceText(CharSequence charSequence) {
        }

        void setImeVisibility(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.f649f = false;
                removeCallbacks(this.g);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                if (!inputMethodManager.isActive(this)) {
                    this.f649f = true;
                    return;
                }
                this.f649f = false;
                removeCallbacks(this.g);
                inputMethodManager.showSoftInput(this, 0);
            }
        }

        void setSearchView(SearchView searchView) {
            this.f648e = searchView;
        }

        @Override // android.widget.AutoCompleteTextView
        public void setThreshold(int i) {
            super.setThreshold(i);
            this.f647d = i;
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.g = new a();
            this.f647d = getThreshold();
        }
    }

    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            SearchView.this.b(charSequence);
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SearchView.this.k();
        }
    }

    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.d.a.a aVar = SearchView.this.S;
            if (aVar instanceof x) {
                aVar.b(null);
            }
        }
    }

    class d implements View.OnFocusChangeListener {
        d() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            SearchView searchView = SearchView.this;
            View.OnFocusChangeListener onFocusChangeListener = searchView.N;
            if (onFocusChangeListener != null) {
                onFocusChangeListener.onFocusChange(searchView, z);
            }
        }
    }

    class e implements View.OnLayoutChangeListener {
        e() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            SearchView.this.c();
        }
    }

    class f implements View.OnClickListener {
        f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SearchView searchView = SearchView.this;
            if (view == searchView.t) {
                searchView.g();
                return;
            }
            if (view == searchView.v) {
                searchView.f();
                return;
            }
            if (view == searchView.u) {
                searchView.h();
            } else if (view == searchView.w) {
                searchView.j();
            } else if (view == searchView.p) {
                searchView.d();
            }
        }
    }

    class g implements View.OnKeyListener {
        g() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            SearchView searchView = SearchView.this;
            if (searchView.g0 == null) {
                return false;
            }
            if (searchView.p.isPopupShowing() && SearchView.this.p.getListSelection() != -1) {
                return SearchView.this.a(view, i, keyEvent);
            }
            if (SearchView.this.p.a() || !keyEvent.hasNoModifiers() || keyEvent.getAction() != 1 || i != 66) {
                return false;
            }
            view.cancelLongPress();
            SearchView searchView2 = SearchView.this;
            searchView2.a(0, (String) null, searchView2.p.getText().toString());
            return true;
        }
    }

    class h implements TextView.OnEditorActionListener {
        h() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            SearchView.this.h();
            return true;
        }
    }

    class i implements AdapterView.OnItemClickListener {
        i() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.a(i, 0, (String) null);
        }
    }

    class j implements AdapterView.OnItemSelectedListener {
        j() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SearchView.this.d(i);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public interface l {
        boolean a();
    }

    public interface m {
        boolean a(String str);

        boolean b(String str);
    }

    public interface n {
        boolean a(int i);

        boolean b(int i);
    }

    static class o extends androidx.customview.a.a {
        public static final Parcelable.Creator<o> CREATOR = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f664c;

        static class a implements Parcelable.ClassLoaderCreator<o> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            public o[] newArray(int i) {
                return new o[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public o createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new o(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public o createFromParcel(Parcel parcel) {
                return new o(parcel, null);
            }
        }

        o(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f664c + "}";
        }

        @Override // androidx.customview.a.a, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.f664c));
        }

        public o(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f664c = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    private static class p extends TouchDelegate {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f665a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Rect f666b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Rect f667c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Rect f668d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f669e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f670f;

        public p(Rect rect, Rect rect2, View view) {
            super(rect, view);
            this.f669e = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
            this.f666b = new Rect();
            this.f668d = new Rect();
            this.f667c = new Rect();
            a(rect, rect2);
            this.f665a = view;
        }

        public void a(Rect rect, Rect rect2) {
            this.f666b.set(rect);
            this.f668d.set(rect);
            Rect rect3 = this.f668d;
            int i = this.f669e;
            rect3.inset(-i, -i);
            this.f667c.set(rect2);
        }

        /* JADX WARN: Code duplicated, block: B:18:0x003a  */
        @Override // android.view.TouchDelegate
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean z;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int action = motionEvent.getAction();
            boolean z2 = true;
            if (action != 0) {
                if (action == 1 || action == 2) {
                    z = this.f670f;
                    if (z && !this.f668d.contains(x, y)) {
                        z2 = false;
                    }
                } else if (action != 3) {
                    z = false;
                } else {
                    z = this.f670f;
                    this.f670f = false;
                }
            } else if (this.f666b.contains(x, y)) {
                this.f670f = true;
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
            if (!z2 || this.f667c.contains(x, y)) {
                Rect rect = this.f667c;
                motionEvent.setLocation(x - rect.left, y - rect.top);
            } else {
                motionEvent.setLocation(this.f665a.getWidth() / 2, this.f665a.getHeight() / 2);
            }
            return this.f665a.dispatchTouchEvent(motionEvent);
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    private void b(boolean z) {
        this.R = z;
        int i2 = z ? 0 : 8;
        boolean z2 = !TextUtils.isEmpty(this.p.getText());
        this.t.setVisibility(i2);
        a(z2);
        this.q.setVisibility(z ? 8 : 0);
        this.D.setVisibility((this.D.getDrawable() == null || this.Q) ? 8 : 0);
        p();
        c(!z2);
        s();
    }

    private CharSequence c(CharSequence charSequence) {
        if (!this.Q || this.F == null) {
            return charSequence;
        }
        double textSize = this.p.getTextSize();
        Double.isNaN(textSize);
        int i2 = (int) (textSize * 1.25d);
        this.F.setBounds(0, 0, i2, i2);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.setSpan(new ImageSpan(this.F), 1, 2, 33);
        spannableStringBuilder.append(charSequence);
        return spannableStringBuilder;
    }

    private int getPreferredHeight() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_height);
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(R$dimen.abc_search_view_preferred_width);
    }

    private void l() {
        this.p.dismissDropDown();
    }

    private boolean m() {
        SearchableInfo searchableInfo = this.g0;
        if (searchableInfo == null || !searchableInfo.getVoiceSearchEnabled()) {
            return false;
        }
        Intent intent = null;
        if (this.g0.getVoiceSearchLaunchWebSearch()) {
            intent = this.I;
        } else if (this.g0.getVoiceSearchLaunchRecognizer()) {
            intent = this.J;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? false : true;
    }

    private boolean n() {
        return (this.T || this.b0) && !e();
    }

    private void o() {
        post(this.i0);
    }

    private void p() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.p.getText());
        if (!z2 && (!this.Q || this.e0)) {
            z = false;
        }
        this.v.setVisibility(z ? 0 : 8);
        Drawable drawable = this.v.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    private void q() {
        CharSequence queryHint = getQueryHint();
        SearchAutoComplete searchAutoComplete = this.p;
        if (queryHint == null) {
            queryHint = "";
        }
        searchAutoComplete.setHint(c(queryHint));
    }

    private void r() {
        this.p.setThreshold(this.g0.getSuggestThreshold());
        this.p.setImeOptions(this.g0.getImeOptions());
        int inputType = this.g0.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.g0.getSuggestAuthority() != null) {
                inputType = inputType | 65536 | 524288;
            }
        }
        this.p.setInputType(inputType);
        a.d.a.a aVar = this.S;
        if (aVar != null) {
            aVar.b(null);
        }
        if (this.g0.getSuggestAuthority() != null) {
            this.S = new x(getContext(), this, this.g0, this.k0);
            this.p.setAdapter(this.S);
            ((x) this.S).a(this.V ? 2 : 1);
        }
    }

    private void s() {
        this.s.setVisibility((n() && (this.u.getVisibility() == 0 || this.w.getVisibility() == 0)) ? 0 : 8);
    }

    private void setQuery(CharSequence charSequence) {
        this.p.setText(charSequence);
        this.p.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    public void a(CharSequence charSequence, boolean z) {
        this.p.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.p;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.d0 = charSequence;
        }
        if (!z || TextUtils.isEmpty(charSequence)) {
            return;
        }
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void clearFocus() {
        this.W = true;
        super.clearFocus();
        this.p.clearFocus();
        this.p.setImeVisibility(false);
        this.W = false;
    }

    boolean d(int i2) {
        n nVar = this.O;
        if (nVar != null && nVar.a(i2)) {
            return false;
        }
        e(i2);
        return true;
    }

    public boolean e() {
        return this.R;
    }

    void f() {
        if (!TextUtils.isEmpty(this.p.getText())) {
            this.p.setText("");
            this.p.requestFocus();
            this.p.setImeVisibility(true);
        } else if (this.Q) {
            l lVar = this.M;
            if (lVar == null || !lVar.a()) {
                clearFocus();
                b(true);
            }
        }
    }

    void g() {
        b(false);
        this.p.requestFocus();
        this.p.setImeVisibility(true);
        View.OnClickListener onClickListener = this.P;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public int getImeOptions() {
        return this.p.getImeOptions();
    }

    public int getInputType() {
        return this.p.getInputType();
    }

    public int getMaxWidth() {
        return this.a0;
    }

    public CharSequence getQuery() {
        return this.p.getText();
    }

    public CharSequence getQueryHint() {
        CharSequence charSequence = this.U;
        if (charSequence != null) {
            return charSequence;
        }
        SearchableInfo searchableInfo = this.g0;
        return (searchableInfo == null || searchableInfo.getHintId() == 0) ? this.K : getContext().getText(this.g0.getHintId());
    }

    int getSuggestionCommitIconResId() {
        return this.H;
    }

    int getSuggestionRowLayout() {
        return this.G;
    }

    public a.d.a.a getSuggestionsAdapter() {
        return this.S;
    }

    void h() {
        Editable text = this.p.getText();
        if (text == null || TextUtils.getTrimmedLength(text) <= 0) {
            return;
        }
        m mVar = this.L;
        if (mVar == null || !mVar.b(text.toString())) {
            if (this.g0 != null) {
                a(0, (String) null, text.toString());
            }
            this.p.setImeVisibility(false);
            l();
        }
    }

    void i() {
        b(e());
        o();
        if (this.p.hasFocus()) {
            d();
        }
    }

    void j() {
        SearchableInfo searchableInfo = this.g0;
        if (searchableInfo == null) {
            return;
        }
        try {
            if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                getContext().startActivity(b(this.I, searchableInfo));
            } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                getContext().startActivity(a(this.J, searchableInfo));
            }
        } catch (ActivityNotFoundException unused) {
            Log.w("SearchView", "Could not find voice search activity");
        }
    }

    void k() {
        int[] iArr = this.p.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.r.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.s.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.i0);
        post(this.j0);
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            a(this.p, this.z);
            Rect rect = this.A;
            Rect rect2 = this.z;
            rect.set(rect2.left, 0, rect2.right, i5 - i3);
            p pVar = this.y;
            if (pVar != null) {
                pVar.a(this.A, this.z);
            } else {
                this.y = new p(this.A, this.z, this.p);
                setTouchDelegate(this.y);
            }
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        if (e()) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            int i5 = this.a0;
            size = i5 > 0 ? Math.min(i5, size) : Math.min(getPreferredWidth(), size);
        } else if (mode == 0) {
            size = this.a0;
            if (size <= 0) {
                size = getPreferredWidth();
            }
        } else if (mode == 1073741824 && (i4 = this.a0) > 0) {
            size = Math.min(i4, size);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getPreferredHeight(), size2);
        } else if (mode2 == 0) {
            size2 = getPreferredHeight();
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec(size2, MemoryConstants.GB));
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof o)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        o oVar = (o) parcelable;
        super.onRestoreInstanceState(oVar.a());
        b(oVar.f664c);
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        o oVar = new o(super.onSaveInstanceState());
        oVar.f664c = e();
        return oVar;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        o();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int i2, Rect rect) {
        if (this.W || !isFocusable()) {
            return false;
        }
        if (e()) {
            return super.requestFocus(i2, rect);
        }
        boolean zRequestFocus = this.p.requestFocus(i2, rect);
        if (zRequestFocus) {
            b(false);
        }
        return zRequestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.h0 = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            f();
        } else {
            g();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.Q == z) {
            return;
        }
        this.Q = z;
        b(z);
        q();
    }

    public void setImeOptions(int i2) {
        this.p.setImeOptions(i2);
    }

    public void setInputType(int i2) {
        this.p.setInputType(i2);
    }

    public void setMaxWidth(int i2) {
        this.a0 = i2;
        requestLayout();
    }

    public void setOnCloseListener(l lVar) {
        this.M = lVar;
    }

    public void setOnQueryTextFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.N = onFocusChangeListener;
    }

    public void setOnQueryTextListener(m mVar) {
        this.L = mVar;
    }

    public void setOnSearchClickListener(View.OnClickListener onClickListener) {
        this.P = onClickListener;
    }

    public void setOnSuggestionListener(n nVar) {
        this.O = nVar;
    }

    public void setQueryHint(CharSequence charSequence) {
        this.U = charSequence;
        q();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.V = z;
        a.d.a.a aVar = this.S;
        if (aVar instanceof x) {
            ((x) aVar).a(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.g0 = searchableInfo;
        if (this.g0 != null) {
            r();
            q();
        }
        this.b0 = m();
        if (this.b0) {
            this.p.setPrivateImeOptions("nm");
        }
        b(e());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.T = z;
        b(e());
    }

    public void setSuggestionsAdapter(a.d.a.a aVar) {
        this.S = aVar;
        this.p.setAdapter(this.S);
    }

    private static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Method f661a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Method f662b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f663c;

        k() {
            try {
                this.f661a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f661a.setAccessible(true);
            } catch (NoSuchMethodException unused) {
            }
            try {
                this.f662b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f662b.setAccessible(true);
            } catch (NoSuchMethodException unused2) {
            }
            try {
                this.f663c = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
                this.f663c.setAccessible(true);
            } catch (NoSuchMethodException unused3) {
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f662b;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void b(AutoCompleteTextView autoCompleteTextView) {
            Method method = this.f661a;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception unused) {
                }
            }
        }

        void a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            Method method = this.f663c;
            if (method != null) {
                try {
                    method.invoke(autoCompleteTextView, Boolean.valueOf(z));
                } catch (Exception unused) {
                }
            }
        }
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.searchViewStyle);
    }

    private void e(int i2) {
        Editable text = this.p.getText();
        Cursor cursorA = this.S.a();
        if (cursorA == null) {
            return;
        }
        if (!cursorA.moveToPosition(i2)) {
            setQuery(text);
            return;
        }
        CharSequence charSequenceA = this.S.a(cursorA);
        if (charSequenceA != null) {
            setQuery(charSequenceA);
        } else {
            setQuery(text);
        }
    }

    public SearchView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.z = new Rect();
        this.A = new Rect();
        this.B = new int[2];
        this.C = new int[2];
        this.i0 = new b();
        this.j0 = new c();
        this.k0 = new WeakHashMap<>();
        this.l0 = new f();
        this.m0 = new g();
        this.n0 = new h();
        this.o0 = new i();
        this.p0 = new j();
        this.q0 = new a();
        d0 d0VarA = d0.a(context, attributeSet, R$styleable.SearchView, i2, 0);
        LayoutInflater.from(context).inflate(d0VarA.g(R$styleable.SearchView_layout, R$layout.abc_search_view), (ViewGroup) this, true);
        this.p = (SearchAutoComplete) findViewById(R$id.search_src_text);
        this.p.setSearchView(this);
        this.q = findViewById(R$id.search_edit_frame);
        this.r = findViewById(R$id.search_plate);
        this.s = findViewById(R$id.submit_area);
        this.t = (ImageView) findViewById(R$id.search_button);
        this.u = (ImageView) findViewById(R$id.search_go_btn);
        this.v = (ImageView) findViewById(R$id.search_close_btn);
        this.w = (ImageView) findViewById(R$id.search_voice_btn);
        this.D = (ImageView) findViewById(R$id.search_mag_icon);
        androidx.core.f.t.a(this.r, d0VarA.b(R$styleable.SearchView_queryBackground));
        androidx.core.f.t.a(this.s, d0VarA.b(R$styleable.SearchView_submitBackground));
        this.t.setImageDrawable(d0VarA.b(R$styleable.SearchView_searchIcon));
        this.u.setImageDrawable(d0VarA.b(R$styleable.SearchView_goIcon));
        this.v.setImageDrawable(d0VarA.b(R$styleable.SearchView_closeIcon));
        this.w.setImageDrawable(d0VarA.b(R$styleable.SearchView_voiceIcon));
        this.D.setImageDrawable(d0VarA.b(R$styleable.SearchView_searchIcon));
        this.F = d0VarA.b(R$styleable.SearchView_searchHintIcon);
        f0.a(this.t, getResources().getString(R$string.abc_searchview_description_search));
        this.G = d0VarA.g(R$styleable.SearchView_suggestionRowLayout, R$layout.abc_search_dropdown_item_icons_2line);
        this.H = d0VarA.g(R$styleable.SearchView_commitIcon, 0);
        this.t.setOnClickListener(this.l0);
        this.v.setOnClickListener(this.l0);
        this.u.setOnClickListener(this.l0);
        this.w.setOnClickListener(this.l0);
        this.p.setOnClickListener(this.l0);
        this.p.addTextChangedListener(this.q0);
        this.p.setOnEditorActionListener(this.n0);
        this.p.setOnItemClickListener(this.o0);
        this.p.setOnItemSelectedListener(this.p0);
        this.p.setOnKeyListener(this.m0);
        this.p.setOnFocusChangeListener(new d());
        setIconifiedByDefault(d0VarA.a(R$styleable.SearchView_iconifiedByDefault, true));
        int iC = d0VarA.c(R$styleable.SearchView_android_maxWidth, -1);
        if (iC != -1) {
            setMaxWidth(iC);
        }
        this.K = d0VarA.e(R$styleable.SearchView_defaultQueryHint);
        this.U = d0VarA.e(R$styleable.SearchView_queryHint);
        int iD = d0VarA.d(R$styleable.SearchView_android_imeOptions, -1);
        if (iD != -1) {
            setImeOptions(iD);
        }
        int iD2 = d0VarA.d(R$styleable.SearchView_android_inputType, -1);
        if (iD2 != -1) {
            setInputType(iD2);
        }
        setFocusable(d0VarA.a(R$styleable.SearchView_android_focusable, true));
        d0VarA.a();
        this.I = new Intent("android.speech.action.WEB_SEARCH");
        this.I.addFlags(268435456);
        this.I.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.J = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.J.addFlags(268435456);
        this.x = findViewById(this.p.getDropDownAnchor());
        View view = this.x;
        if (view != null) {
            view.addOnLayoutChangeListener(new e());
        }
        b(this.Q);
        q();
    }

    void d() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.p.refreshAutoCompleteResults();
        } else {
            r0.b(this.p);
            r0.a(this.p);
        }
    }

    private void a(View view, Rect rect) {
        view.getLocationInWindow(this.B);
        getLocationInWindow(this.C);
        int[] iArr = this.B;
        int i2 = iArr[1];
        int[] iArr2 = this.C;
        int i3 = i2 - iArr2[1];
        int i4 = iArr[0] - iArr2[0];
        rect.set(i4, i3, view.getWidth() + i4, view.getHeight() + i3);
    }

    private void c(boolean z) {
        int i2;
        if (this.b0 && !e() && z) {
            i2 = 0;
            this.u.setVisibility(8);
        } else {
            i2 = 8;
        }
        this.w.setVisibility(i2);
    }

    void c() {
        int i2;
        if (this.x.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.r.getPaddingLeft();
            Rect rect = new Rect();
            boolean zA = j0.a(this);
            int dimensionPixelSize = this.Q ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) : 0;
            this.p.getDropDownBackground().getPadding(rect);
            if (zA) {
                i2 = -rect.left;
            } else {
                i2 = paddingLeft - (rect.left + dimensionPixelSize);
            }
            this.p.setDropDownHorizontalOffset(i2);
            this.p.setDropDownWidth((((this.x.getWidth() + rect.left) + rect.right) + dimensionPixelSize) - paddingLeft);
        }
    }

    private void a(boolean z) {
        this.u.setVisibility((this.T && n() && hasFocus() && (z || !this.b0)) ? 0 : 8);
    }

    void b(CharSequence charSequence) {
        Editable text = this.p.getText();
        this.d0 = text;
        boolean z = !TextUtils.isEmpty(text);
        a(z);
        c(!z);
        p();
        s();
        if (this.L != null && !TextUtils.equals(charSequence, this.c0)) {
            this.L.a(charSequence.toString());
        }
        this.c0 = charSequence.toString();
    }

    void a(CharSequence charSequence) {
        setQuery(charSequence);
    }

    boolean a(View view, int i2, KeyEvent keyEvent) {
        if (this.g0 != null && this.S != null && keyEvent.getAction() == 0 && keyEvent.hasNoModifiers()) {
            if (i2 == 66 || i2 == 84 || i2 == 61) {
                return a(this.p.getListSelection(), 0, (String) null);
            }
            if (i2 != 21 && i2 != 22) {
                if (i2 != 19 || this.p.getListSelection() == 0) {
                    return false;
                }
            } else {
                this.p.setSelection(i2 == 21 ? 0 : this.p.length());
                this.p.setListSelection(0);
                this.p.clearListSelection();
                r0.a(this.p, true);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.d.c
    public void b() {
        a("", false);
        clearFocus();
        b(true);
        this.p.setImeOptions(this.f0);
        this.e0 = false;
    }

    private boolean b(int i2, int i3, String str) {
        Cursor cursorA = this.S.a();
        if (cursorA == null || !cursorA.moveToPosition(i2)) {
            return false;
        }
        a(a(cursorA, i3, str));
        return true;
    }

    @Override // androidx.appcompat.d.c
    public void a() {
        if (this.e0) {
            return;
        }
        this.e0 = true;
        this.f0 = this.p.getImeOptions();
        this.p.setImeOptions(this.f0 | 33554432);
        this.p.setText("");
        setIconified(false);
    }

    private Intent b(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    boolean a(int i2, int i3, String str) {
        n nVar = this.O;
        if (nVar != null && nVar.b(i2)) {
            return false;
        }
        b(i2, 0, null);
        this.p.setImeVisibility(false);
        l();
        return true;
    }

    private void a(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            getContext().startActivity(intent);
        } catch (RuntimeException e2) {
            Log.e("SearchView", "Failed launch activity: " + intent, e2);
        }
    }

    void a(int i2, String str, String str2) {
        getContext().startActivity(a("android.intent.action.SEARCH", (Uri) null, (String) null, str2, i2, str));
    }

    private Intent a(String str, Uri uri, String str2, String str3, int i2, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.d0);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        Bundle bundle = this.h0;
        if (bundle != null) {
            intent.putExtra("app_data", bundle);
        }
        if (i2 != 0) {
            intent.putExtra("action_key", i2);
            intent.putExtra("action_msg", str4);
        }
        intent.setComponent(this.g0.getSearchActivity());
        return intent;
    }

    private Intent a(Intent intent, SearchableInfo searchableInfo) {
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        PendingIntent activity = PendingIntent.getActivity(getContext(), 0, intent2, MemoryConstants.GB);
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.h0;
        if (bundle2 != null) {
            bundle.putParcelable("app_data", bundle2);
        }
        Intent intent3 = new Intent(intent);
        Resources resources = getResources();
        String string = searchableInfo.getVoiceLanguageModeId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageModeId()) : "free_form";
        String string2 = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
        String string3 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
        int voiceMaxResults = searchableInfo.getVoiceMaxResults() != 0 ? searchableInfo.getVoiceMaxResults() : 1;
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", string);
        intent3.putExtra("android.speech.extra.PROMPT", string2);
        intent3.putExtra("android.speech.extra.LANGUAGE", string3);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        intent3.putExtra("calling_package", searchActivity != null ? searchActivity.flattenToShortString() : null);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    private Intent a(Cursor cursor, int i2, String str) {
        int position;
        String strA;
        try {
            String strA2 = x.a(cursor, "suggest_intent_action");
            if (strA2 == null) {
                strA2 = this.g0.getSuggestIntentAction();
            }
            if (strA2 == null) {
                strA2 = "android.intent.action.SEARCH";
            }
            String str2 = strA2;
            String strA3 = x.a(cursor, "suggest_intent_data");
            if (strA3 == null) {
                strA3 = this.g0.getSuggestIntentData();
            }
            if (strA3 != null && (strA = x.a(cursor, "suggest_intent_data_id")) != null) {
                strA3 = strA3 + "/" + Uri.encode(strA);
            }
            return a(str2, strA3 == null ? null : Uri.parse(strA3), x.a(cursor, "suggest_intent_extra_data"), x.a(cursor, "suggest_intent_query"), i2, str);
        } catch (RuntimeException e2) {
            try {
                position = cursor.getPosition();
            } catch (RuntimeException unused) {
                position = -1;
            }
            Log.w("SearchView", "Search suggestions cursor at row " + position + " returned exception.", e2);
            return null;
        }
    }

    static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
