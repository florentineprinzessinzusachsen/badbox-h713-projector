package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: SuggestionsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
class x extends a.d.a.c implements View.OnClickListener {
    private final SearchView l;
    private final SearchableInfo m;
    private final Context n;
    private final WeakHashMap<String, Drawable.ConstantState> o;
    private final int p;
    private boolean q;
    private int r;
    private ColorStateList s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;

    /* JADX INFO: compiled from: SuggestionsAdapter.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f840a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f841b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f842c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f843d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f844e;

        public a(View view) {
            this.f840a = (TextView) view.findViewById(R.id.text1);
            this.f841b = (TextView) view.findViewById(R.id.text2);
            this.f842c = (ImageView) view.findViewById(R.id.icon1);
            this.f843d = (ImageView) view.findViewById(R.id.icon2);
            this.f844e = (ImageView) view.findViewById(R$id.edit_query);
        }
    }

    public x(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.q = false;
        this.r = 1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.l = searchView;
        this.m = searchableInfo;
        this.p = searchView.getSuggestionCommitIconResId();
        this.n = context;
        this.o = weakHashMap;
    }

    private Drawable d(Cursor cursor) {
        Drawable drawableB = b(this.m.getSearchActivity());
        return drawableB != null ? drawableB : this.f176d.getPackageManager().getDefaultActivityIcon();
    }

    private Drawable e(Cursor cursor) {
        int i = this.w;
        if (i == -1) {
            return null;
        }
        Drawable drawableB = b(cursor.getString(i));
        return drawableB != null ? drawableB : d(cursor);
    }

    private Drawable f(Cursor cursor) {
        int i = this.x;
        if (i == -1) {
            return null;
        }
        return b(cursor.getString(i));
    }

    private void g(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    public void a(int i) {
        this.r = i;
    }

    @Override // a.d.a.a, a.d.a.b.a
    public void b(Cursor cursor) {
        if (this.q) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.b(cursor);
            if (cursor != null) {
                this.t = cursor.getColumnIndex("suggest_text_1");
                this.u = cursor.getColumnIndex("suggest_text_2");
                this.v = cursor.getColumnIndex("suggest_text_2_url");
                this.w = cursor.getColumnIndex("suggest_icon_1");
                this.x = cursor.getColumnIndex("suggest_icon_2");
                this.y = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // a.d.a.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewA = a(this.f176d, this.f175c, viewGroup);
            if (viewA != null) {
                ((a) viewA.getTag()).f840a.setText(e2.toString());
            }
            return viewA;
        }
    }

    @Override // a.d.a.a, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewB = b(this.f176d, this.f175c, viewGroup);
            if (viewB != null) {
                ((a) viewB.getTag()).f840a.setText(e2.toString());
            }
            return viewB;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        g(a());
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        g(a());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.l.a((CharSequence) tag);
        }
    }

    @Override // a.d.a.b.a
    public Cursor a(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        if (this.l.getVisibility() == 0 && this.l.getWindowVisibility() == 0) {
            try {
                Cursor cursorA = a(this.m, string, 50);
                if (cursorA != null) {
                    cursorA.getCount();
                    return cursorA;
                }
            } catch (RuntimeException e2) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e2);
            }
        }
        return null;
    }

    @Override // a.d.a.a
    public void a(View view, Context context, Cursor cursor) {
        CharSequence charSequenceA;
        a aVar = (a) view.getTag();
        int i = this.y;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.f840a != null) {
            a(aVar.f840a, a(cursor, this.t));
        }
        if (aVar.f841b != null) {
            String strA = a(cursor, this.v);
            if (strA != null) {
                charSequenceA = b((CharSequence) strA);
            } else {
                charSequenceA = a(cursor, this.u);
            }
            if (TextUtils.isEmpty(charSequenceA)) {
                TextView textView = aVar.f840a;
                if (textView != null) {
                    textView.setSingleLine(false);
                    aVar.f840a.setMaxLines(2);
                }
            } else {
                TextView textView2 = aVar.f840a;
                if (textView2 != null) {
                    textView2.setSingleLine(true);
                    aVar.f840a.setMaxLines(1);
                }
            }
            a(aVar.f841b, charSequenceA);
        }
        ImageView imageView = aVar.f842c;
        if (imageView != null) {
            a(imageView, e(cursor), 4);
        }
        ImageView imageView2 = aVar.f843d;
        if (imageView2 != null) {
            a(imageView2, f(cursor), 8);
        }
        int i3 = this.r;
        if (i3 != 2 && (i3 != 1 || (i2 & 1) == 0)) {
            aVar.f844e.setVisibility(8);
            return;
        }
        aVar.f844e.setVisibility(0);
        aVar.f844e.setTag(aVar.f840a.getText());
        aVar.f844e.setOnClickListener(this);
    }

    @Override // a.d.a.c, a.d.a.a
    public View b(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewB = super.b(context, cursor, viewGroup);
        viewB.setTag(new a(viewB));
        ((ImageView) viewB.findViewById(R$id.edit_query)).setImageResource(this.p);
        return viewB;
    }

    private CharSequence b(CharSequence charSequence) {
        if (this.s == null) {
            TypedValue typedValue = new TypedValue();
            this.f176d.getTheme().resolveAttribute(R$attr.textColorSearchUrl, typedValue, true);
            this.s = this.f176d.getResources().getColorStateList(typedValue.resourceId);
        }
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.s, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private Drawable b(String str) {
        if (str == null || str.isEmpty() || "0".equals(str)) {
            return null;
        }
        try {
            int i = Integer.parseInt(str);
            String str2 = "android.resource://" + this.n.getPackageName() + "/" + i;
            Drawable drawableA = a(str2);
            if (drawableA != null) {
                return drawableA;
            }
            Drawable drawableC = androidx.core.content.a.c(this.n, i);
            a(str2, drawableC);
            return drawableC;
        } catch (Resources.NotFoundException unused) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        } catch (NumberFormatException unused2) {
            Drawable drawableA2 = a(str);
            if (drawableA2 != null) {
                return drawableA2;
            }
            Drawable drawableB = b(Uri.parse(str));
            a(str, drawableB);
            return drawableB;
        }
    }

    private void a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private Drawable b(Uri uri) {
        try {
            if ("android.resource".equals(uri.getScheme())) {
                try {
                    return a(uri);
                } catch (Resources.NotFoundException unused) {
                    throw new FileNotFoundException("Resource does not exist: " + uri);
                }
            }
            InputStream inputStreamOpenInputStream = this.n.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream != null) {
                try {
                    Drawable drawableCreateFromStream = Drawable.createFromStream(inputStreamOpenInputStream, null);
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException e2) {
                        Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e2);
                    }
                    return drawableCreateFromStream;
                } catch (Throwable th) {
                    try {
                        inputStreamOpenInputStream.close();
                    } catch (IOException e3) {
                        Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e3);
                    }
                    throw th;
                }
            }
            throw new FileNotFoundException("Failed to open " + uri);
        } catch (FileNotFoundException e4) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e4.getMessage());
            return null;
        }
        Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e4.getMessage());
        return null;
    }

    private void a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    @Override // a.d.a.a, a.d.a.b.a
    public CharSequence a(Cursor cursor) {
        String strA;
        String strA2;
        if (cursor == null) {
            return null;
        }
        String strA3 = a(cursor, "suggest_intent_query");
        if (strA3 != null) {
            return strA3;
        }
        if (this.m.shouldRewriteQueryFromData() && (strA2 = a(cursor, "suggest_intent_data")) != null) {
            return strA2;
        }
        if (!this.m.shouldRewriteQueryFromText() || (strA = a(cursor, "suggest_text_1")) == null) {
            return null;
        }
        return strA;
    }

    private Drawable a(String str) {
        Drawable.ConstantState constantState = this.o.get(str);
        if (constantState == null) {
            return null;
        }
        return constantState.newDrawable();
    }

    private Drawable b(ComponentName componentName) {
        String strFlattenToShortString = componentName.flattenToShortString();
        if (this.o.containsKey(strFlattenToShortString)) {
            Drawable.ConstantState constantState = this.o.get(strFlattenToShortString);
            if (constantState == null) {
                return null;
            }
            return constantState.newDrawable(this.n.getResources());
        }
        Drawable drawableA = a(componentName);
        this.o.put(strFlattenToShortString, drawableA != null ? drawableA.getConstantState() : null);
        return drawableA;
    }

    private void a(String str, Drawable drawable) {
        if (drawable != null) {
            this.o.put(str, drawable.getConstantState());
        }
    }

    private Drawable a(ComponentName componentName) {
        PackageManager packageManager = this.f176d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.w("SuggestionsAdapter", e2.toString());
            return null;
        }
    }

    public static String a(Cursor cursor, String str) {
        return a(cursor, cursor.getColumnIndex(str));
    }

    private static String a(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return null;
        }
    }

    Drawable a(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority)) {
            try {
                Resources resourcesForApplication = this.f176d.getPackageManager().getResourcesForApplication(authority);
                List<String> pathSegments = uri.getPathSegments();
                if (pathSegments != null) {
                    int size = pathSegments.size();
                    if (size == 1) {
                        try {
                            identifier = Integer.parseInt(pathSegments.get(0));
                        } catch (NumberFormatException unused) {
                            throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                        }
                    } else if (size == 2) {
                        identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
                    } else {
                        throw new FileNotFoundException("More than two path segments: " + uri);
                    }
                    if (identifier != 0) {
                        return resourcesForApplication.getDrawable(identifier);
                    }
                    throw new FileNotFoundException("No resource found for: " + uri);
                }
                throw new FileNotFoundException("No path: " + uri);
            } catch (PackageManager.NameNotFoundException unused2) {
                throw new FileNotFoundException("No package found for authority: " + uri);
            }
        }
        throw new FileNotFoundException("No authority: " + uri);
    }

    Cursor a(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.f176d.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }
}
