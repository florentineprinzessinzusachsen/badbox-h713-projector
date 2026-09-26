package androidx.core.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.Editable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.PasswordTransformationMethod;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TextViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static ActionMode.Callback a(TextView textView, ActionMode.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        return (i < 26 || i > 27 || (callback instanceof a)) ? callback : new a(callback, textView);
    }

    public static void b(TextView textView, int i) {
        androidx.core.e.e.a(i);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = (Build.VERSION.SDK_INT < 16 || textView.getIncludeFontPadding()) ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static void c(TextView textView, int i) {
        androidx.core.e.e.a(i);
        int fontMetricsInt = textView.getPaint().getFontMetricsInt(null);
        if (i != fontMetricsInt) {
            textView.setLineSpacing(i - fontMetricsInt, 1.0f);
        }
    }

    public static void d(TextView textView, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }

    public static void a(TextView textView, int i) {
        int i2;
        androidx.core.e.e.a(i);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        if (Build.VERSION.SDK_INT >= 16 && !textView.getIncludeFontPadding()) {
            i2 = fontMetricsInt.ascent;
        } else {
            i2 = fontMetricsInt.top;
        }
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i - (-i2), textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    private static TextDirectionHeuristic c(TextView textView) {
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            return TextDirectionHeuristics.LTR;
        }
        if (Build.VERSION.SDK_INT >= 28 && (textView.getInputType() & 15) == 3) {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if (directionality != 1 && directionality != 2) {
                return TextDirectionHeuristics.LTR;
            }
            return TextDirectionHeuristics.RTL;
        }
        boolean z = textView.getLayoutDirection() == 1;
        switch (textView.getTextDirection()) {
            case 2:
                return TextDirectionHeuristics.ANYRTL_LTR;
            case 3:
                return TextDirectionHeuristics.LTR;
            case 4:
                return TextDirectionHeuristics.RTL;
            case 5:
                return TextDirectionHeuristics.LOCALE;
            case 6:
                return TextDirectionHeuristics.FIRSTSTRONG_LTR;
            case 7:
                return TextDirectionHeuristics.FIRSTSTRONG_RTL;
            default:
                if (z) {
                    return TextDirectionHeuristics.FIRSTSTRONG_RTL;
                }
                return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
    }

    public static androidx.core.d.a.C0020a d(TextView textView) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new androidx.core.d.a.C0020a(textView.getTextMetricsParams());
        }
        androidx.core.d.a.C0020a.C0021a c0021a = new androidx.core.d.a.C0020a.C0021a(new TextPaint(textView.getPaint()));
        if (Build.VERSION.SDK_INT >= 23) {
            c0021a.a(textView.getBreakStrategy());
            c0021a.b(textView.getHyphenationFrequency());
        }
        if (Build.VERSION.SDK_INT >= 18) {
            c0021a.a(c(textView));
        }
        return c0021a.a();
    }

    public static int b(TextView textView) {
        return textView.getPaddingBottom() + textView.getPaint().getFontMetricsInt().bottom;
    }

    public static int a(TextView textView) {
        return textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
    }

    public static void a(TextView textView, androidx.core.d.a.C0020a c0020a) {
        if (Build.VERSION.SDK_INT >= 18) {
            textView.setTextDirection(a(c0020a.c()));
        }
        if (Build.VERSION.SDK_INT < 23) {
            float textScaleX = c0020a.d().getTextScaleX();
            textView.getPaint().set(c0020a.d());
            if (textScaleX == textView.getTextScaleX()) {
                textView.setTextScaleX((textScaleX / 2.0f) + 1.0f);
            }
            textView.setTextScaleX(textScaleX);
            return;
        }
        textView.getPaint().set(c0020a.d());
        textView.setBreakStrategy(c0020a.a());
        textView.setHyphenationFrequency(c0020a.b());
    }

    /* JADX INFO: compiled from: TextViewCompat.java */
    private static class a implements ActionMode.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ActionMode.Callback f1167a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextView f1168b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Class f1169c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Method f1170d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1171e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f1172f = false;

        a(ActionMode.Callback callback, TextView textView) {
            this.f1167a = callback;
            this.f1168b = textView;
        }

        private void a(Menu menu) {
            Context context = this.f1168b.getContext();
            PackageManager packageManager = context.getPackageManager();
            if (!this.f1172f) {
                this.f1172f = true;
                try {
                    this.f1169c = Class.forName("com.android.internal.view.menu.MenuBuilder");
                    this.f1170d = this.f1169c.getDeclaredMethod("removeItemAt", Integer.TYPE);
                    this.f1171e = true;
                } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    this.f1169c = null;
                    this.f1170d = null;
                    this.f1171e = false;
                }
            }
            try {
                Method declaredMethod = (this.f1171e && this.f1169c.isInstance(menu)) ? this.f1170d : menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
                for (int size = menu.size() - 1; size >= 0; size--) {
                    MenuItem item = menu.getItem(size);
                    if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                        declaredMethod.invoke(menu, Integer.valueOf(size));
                    }
                }
                List<ResolveInfo> listA = a(context, packageManager);
                for (int i = 0; i < listA.size(); i++) {
                    ResolveInfo resolveInfo = listA.get(i);
                    menu.add(0, 0, i + 100, resolveInfo.loadLabel(packageManager)).setIntent(a(resolveInfo, this.f1168b)).setShowAsAction(1);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
            }
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.f1167a.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.f1167a.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.f1167a.onDestroyActionMode(actionMode);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            a(menu);
            return this.f1167a.onPrepareActionMode(actionMode, menu);
        }

        private List<ResolveInfo> a(Context context, PackageManager packageManager) {
            ArrayList arrayList = new ArrayList();
            if (!(context instanceof Activity)) {
                return arrayList;
            }
            for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(a(), 0)) {
                if (a(resolveInfo, context)) {
                    arrayList.add(resolveInfo);
                }
            }
            return arrayList;
        }

        private boolean a(ResolveInfo resolveInfo, Context context) {
            if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                return true;
            }
            if (!resolveInfo.activityInfo.exported) {
                return false;
            }
            String str = resolveInfo.activityInfo.permission;
            return str == null || context.checkSelfPermission(str) == 0;
        }

        private Intent a(ResolveInfo resolveInfo, TextView textView) {
            return a().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !a(textView)).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }

        private boolean a(TextView textView) {
            return (textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled();
        }

        private Intent a() {
            return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
        }
    }

    public static void a(TextView textView, androidx.core.d.a aVar) {
        if (d(textView).a(aVar.a())) {
            textView.setText(aVar);
            return;
        }
        throw new IllegalArgumentException("Given text can not be applied to TextView.");
    }

    private static int a(TextDirectionHeuristic textDirectionHeuristic) {
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL || textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 1;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            return 2;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            return 3;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            return 4;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            return 5;
        }
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            return 6;
        }
        return textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL ? 7 : 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(TextView textView, ColorStateList colorStateList) {
        androidx.core.e.e.a(textView);
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setCompoundDrawableTintList(colorStateList);
        } else if (textView instanceof k) {
            ((k) textView).setSupportCompoundDrawablesTintList(colorStateList);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(TextView textView, PorterDuff.Mode mode) {
        androidx.core.e.e.a(textView);
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setCompoundDrawableTintMode(mode);
        } else if (textView instanceof k) {
            ((k) textView).setSupportCompoundDrawablesTintMode(mode);
        }
    }
}
