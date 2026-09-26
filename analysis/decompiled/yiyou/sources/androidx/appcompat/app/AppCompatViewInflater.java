package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.a0;
import androidx.core.f.t;
import com.umeng.commonsdk.proguard.ap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatViewInflater {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Class<?>[] f284b = {Context.class, AttributeSet.class};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f285c = {R.attr.onClick};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f286d = {"android.widget.", "android.view.", "android.webkit."};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Map<String, Constructor<? extends View>> f287e = new a.b.a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f288a = new Object[2];

    private static class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final View f289a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f290b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Method f291c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Context f292d;

        public a(View view, String str) {
            this.f289a = view;
            this.f290b = str;
        }

        private void a(Context context, String str) {
            String str2;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f290b, View.class)) != null) {
                        this.f291c = method;
                        this.f292d = context;
                        return;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.f289a.getId();
            if (id == -1) {
                str2 = "";
            } else {
                str2 = " with id '" + this.f289a.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.f290b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f289a.getClass() + str2);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f291c == null) {
                a(this.f289a.getContext(), this.f290b);
            }
            try {
                this.f291c.invoke(this.f292d, view);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
            } catch (InvocationTargetException e3) {
                throw new IllegalStateException("Could not execute method for android:onClick", e3);
            }
        }
    }

    protected View a(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    final View a(View view, String str, Context context, AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        View viewM;
        Context context2 = (!z || view == null) ? context : view.getContext();
        if (z2 || z3) {
            context2 = a(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = a0.b(context2);
        }
        byte b2 = -1;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    b2 = 11;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    b2 = 8;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    b2 = 10;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    b2 = 0;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    b2 = 5;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    b2 = 12;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    b2 = 4;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    b2 = 7;
                }
                break;
            case 799298502:
                if (str.equals("ToggleButton")) {
                    b2 = ap.k;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    b2 = 1;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    b2 = 9;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    b2 = 6;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    b2 = 3;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    b2 = 2;
                }
                break;
        }
        switch (b2) {
            case 0:
                viewM = m(context2, attributeSet);
                a(viewM, str);
                break;
            case 1:
                viewM = g(context2, attributeSet);
                a(viewM, str);
                break;
            case 2:
                viewM = b(context2, attributeSet);
                a(viewM, str);
                break;
            case 3:
                viewM = e(context2, attributeSet);
                a(viewM, str);
                break;
            case 4:
                viewM = l(context2, attributeSet);
                a(viewM, str);
                break;
            case 5:
                viewM = f(context2, attributeSet);
                a(viewM, str);
                break;
            case 6:
                viewM = c(context2, attributeSet);
                a(viewM, str);
                break;
            case 7:
                viewM = i(context2, attributeSet);
                a(viewM, str);
                break;
            case 8:
                viewM = d(context2, attributeSet);
                a(viewM, str);
                break;
            case 9:
                viewM = a(context2, attributeSet);
                a(viewM, str);
                break;
            case 10:
                viewM = h(context2, attributeSet);
                a(viewM, str);
                break;
            case 11:
                viewM = j(context2, attributeSet);
                a(viewM, str);
                break;
            case 12:
                viewM = k(context2, attributeSet);
                a(viewM, str);
                break;
            case 13:
                viewM = n(context2, attributeSet);
                a(viewM, str);
                break;
            default:
                viewM = a(context2, str, attributeSet);
                break;
        }
        if (viewM == null && context != context2) {
            viewM = b(context2, str, attributeSet);
        }
        if (viewM != null) {
            a(viewM, attributeSet);
        }
        return viewM;
    }

    protected AppCompatButton b(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    protected AppCompatCheckBox c(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    protected AppCompatCheckedTextView d(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckedTextView(context, attributeSet);
    }

    protected AppCompatEditText e(Context context, AttributeSet attributeSet) {
        return new AppCompatEditText(context, attributeSet);
    }

    protected AppCompatImageButton f(Context context, AttributeSet attributeSet) {
        return new AppCompatImageButton(context, attributeSet);
    }

    protected AppCompatImageView g(Context context, AttributeSet attributeSet) {
        return new AppCompatImageView(context, attributeSet);
    }

    protected AppCompatMultiAutoCompleteTextView h(Context context, AttributeSet attributeSet) {
        return new AppCompatMultiAutoCompleteTextView(context, attributeSet);
    }

    protected AppCompatRadioButton i(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    protected AppCompatRatingBar j(Context context, AttributeSet attributeSet) {
        return new AppCompatRatingBar(context, attributeSet);
    }

    protected AppCompatSeekBar k(Context context, AttributeSet attributeSet) {
        return new AppCompatSeekBar(context, attributeSet);
    }

    protected AppCompatSpinner l(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    protected AppCompatTextView m(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    protected AppCompatToggleButton n(Context context, AttributeSet attributeSet) {
        return new AppCompatToggleButton(context, attributeSet);
    }

    private View b(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.f288a[0] = context;
            this.f288a[1] = attributeSet;
            if (-1 != str.indexOf(46)) {
                return a(context, str, (String) null);
            }
            for (int i = 0; i < f286d.length; i++) {
                View viewA = a(context, str, f286d[i]);
                if (viewA != null) {
                    return viewA;
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        } finally {
            Object[] objArr = this.f288a;
            objArr[0] = null;
            objArr[1] = null;
        }
    }

    protected AppCompatAutoCompleteTextView a(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    private void a(View view, String str) {
        if (view != null) {
            return;
        }
        throw new IllegalStateException(AppCompatViewInflater.class.getName() + " asked to inflate view for <" + str + ">, but returned null");
    }

    private void a(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (Build.VERSION.SDK_INT < 15 || t.o(view)) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f285c);
                String string = typedArrayObtainStyledAttributes.getString(0);
                if (string != null) {
                    view.setOnClickListener(new a(view, string));
                }
                typedArrayObtainStyledAttributes.recycle();
            }
        }
    }

    private View a(Context context, String str, String str2) {
        String str3;
        Constructor<? extends View> constructor = f287e.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception unused) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(f284b);
            f287e.put(str, constructor);
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.f288a);
    }

    private static Context a(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.View, 0, 0);
        int resourceId = z ? typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_android_theme, 0) : 0;
        if (z2 && resourceId == 0 && (resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_theme, 0)) != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            return ((context instanceof androidx.appcompat.d.d) && ((androidx.appcompat.d.d) context).a() == resourceId) ? context : new androidx.appcompat.d.d(context, resourceId);
        }
        return context;
    }
}
