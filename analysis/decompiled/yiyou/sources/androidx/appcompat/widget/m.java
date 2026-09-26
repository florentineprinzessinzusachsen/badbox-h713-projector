package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import com.blankj.utilcode.constant.MemoryConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
/* JADX INFO: loaded from: classes.dex */
class m {
    private static final RectF k = new RectF();
    private static ConcurrentHashMap<String, Method> l = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Field> m = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f796a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f797b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f798c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f799d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f800e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f801f = new int[0];
    private boolean g = false;
    private TextPaint h;
    private final TextView i;
    private final Context j;

    m(TextView textView) {
        this.i = textView;
        this.j = this.i.getContext();
    }

    private void h() {
        this.f796a = 0;
        this.f799d = -1.0f;
        this.f800e = -1.0f;
        this.f798c = -1.0f;
        this.f801f = new int[0];
        this.f797b = false;
    }

    private boolean i() {
        if (k() && this.f796a == 1) {
            if (!this.g || this.f801f.length == 0) {
                int iFloor = ((int) Math.floor((this.f800e - this.f799d) / this.f798c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i = 0; i < iFloor; i++) {
                    iArr[i] = Math.round(this.f799d + (i * this.f798c));
                }
                this.f801f = a(iArr);
            }
            this.f797b = true;
        } else {
            this.f797b = false;
        }
        return this.f797b;
    }

    private boolean j() {
        int length = this.f801f.length;
        this.g = length > 0;
        if (this.g) {
            this.f796a = 1;
            int[] iArr = this.f801f;
            this.f799d = iArr[0];
            this.f800e = iArr[length - 1];
            this.f798c = -1.0f;
        }
        return this.g;
    }

    private boolean k() {
        return !(this.i instanceof AppCompatEditText);
    }

    void a(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray typedArrayObtainStyledAttributes = this.j.obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView, i, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_autoSizeTextType)) {
            this.f796a = typedArrayObtainStyledAttributes.getInt(R$styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_autoSizeStepGranularity) ? typedArrayObtainStyledAttributes.getDimension(R$styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_autoSizeMinTextSize) ? typedArrayObtainStyledAttributes.getDimension(R$styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_autoSizeMaxTextSize) ? typedArrayObtainStyledAttributes.getDimension(R$styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTextView_autoSizePresetSizes) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            a(typedArrayObtainTypedArray);
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!k()) {
            this.f796a = 0;
            return;
        }
        if (this.f796a == 1) {
            if (!this.g) {
                DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                a(dimension2, dimension3, dimension);
            }
            i();
        }
    }

    void b(int i) {
        if (k()) {
            if (i == 0) {
                h();
                return;
            }
            if (i != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i);
            }
            DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (i()) {
                a();
            }
        }
    }

    int c() {
        return Math.round(this.f799d);
    }

    int d() {
        return Math.round(this.f798c);
    }

    int[] e() {
        return this.f801f;
    }

    int f() {
        return this.f796a;
    }

    boolean g() {
        return k() && this.f796a != 0;
    }

    int b() {
        return Math.round(this.f800e);
    }

    private StaticLayout b(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        TextDirectionHeuristic textDirectionHeuristic;
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.h, i);
        StaticLayout.Builder hyphenationFrequency = builderObtain.setAlignment(alignment).setLineSpacing(this.i.getLineSpacingExtra(), this.i.getLineSpacingMultiplier()).setIncludePad(this.i.getIncludeFontPadding()).setBreakStrategy(this.i.getBreakStrategy()).setHyphenationFrequency(this.i.getHyphenationFrequency());
        if (i2 == -1) {
            i2 = Integer.MAX_VALUE;
        }
        hyphenationFrequency.setMaxLines(i2);
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                textDirectionHeuristic = this.i.getTextDirectionHeuristic();
            } else {
                textDirectionHeuristic = (TextDirectionHeuristic) b(this.i, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
            }
            builderObtain.setTextDirection(textDirectionHeuristic);
        } catch (ClassCastException unused) {
            Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
        }
        return builderObtain.build();
    }

    void a(int i, int i2, int i3, int i4) {
        if (k()) {
            DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
            a(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (i()) {
                a();
            }
        }
    }

    private StaticLayout b(CharSequence charSequence, Layout.Alignment alignment, int i) {
        return new StaticLayout(charSequence, this.h, i, alignment, this.i.getLineSpacingMultiplier(), this.i.getLineSpacingExtra(), this.i.getIncludeFontPadding());
    }

    private static <T> T b(Object obj, String str, T t) {
        try {
            return (T) b(str).invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return t;
        }
    }

    void a(int[] iArr, int i) {
        if (k()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.j.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArrCopyOf[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                this.f801f = a(iArrCopyOf);
                if (!j()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.g = false;
            }
            if (i()) {
                a();
            }
        }
    }

    private static Method b(String str) {
        try {
            Method declaredMethod = l.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                l.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    private void a(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                iArr[i] = typedArray.getDimensionPixelSize(i, -1);
            }
            this.f801f = a(iArr);
            j();
        }
    }

    private int[] a(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    private void a(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        }
        if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        if (f4 > 0.0f) {
            this.f796a = 1;
            this.f799d = f2;
            this.f800e = f3;
            this.f798c = f4;
            this.g = false;
            return;
        }
        throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
    }

    void a() {
        boolean zBooleanValue;
        if (g()) {
            if (this.f797b) {
                if (this.i.getMeasuredHeight() <= 0 || this.i.getMeasuredWidth() <= 0) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    zBooleanValue = this.i.isHorizontallyScrollable();
                } else {
                    zBooleanValue = ((Boolean) b(this.i, "getHorizontallyScrolling", false)).booleanValue();
                }
                int measuredWidth = zBooleanValue ? MemoryConstants.MB : (this.i.getMeasuredWidth() - this.i.getTotalPaddingLeft()) - this.i.getTotalPaddingRight();
                int height = (this.i.getHeight() - this.i.getCompoundPaddingBottom()) - this.i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                synchronized (k) {
                    k.setEmpty();
                    k.right = measuredWidth;
                    k.bottom = height;
                    float fA = a(k);
                    if (fA != this.i.getTextSize()) {
                        a(0, fA);
                    }
                }
            }
            this.f797b = true;
        }
    }

    void a(int i, float f2) {
        Resources resources;
        Context context = this.j;
        if (context == null) {
            resources = Resources.getSystem();
        } else {
            resources = context.getResources();
        }
        a(TypedValue.applyDimension(i, f2, resources.getDisplayMetrics()));
    }

    private void a(float f2) {
        if (f2 != this.i.getPaint().getTextSize()) {
            this.i.getPaint().setTextSize(f2);
            boolean zIsInLayout = Build.VERSION.SDK_INT >= 18 ? this.i.isInLayout() : false;
            if (this.i.getLayout() != null) {
                this.f797b = false;
                try {
                    Method methodB = b("nullLayouts");
                    if (methodB != null) {
                        methodB.invoke(this.i, new Object[0]);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (!zIsInLayout) {
                    this.i.requestLayout();
                } else {
                    this.i.forceLayout();
                }
                this.i.invalidate();
            }
        }
    }

    private int a(RectF rectF) {
        int length = this.f801f.length;
        if (length != 0) {
            int i = length - 1;
            int i2 = 1;
            int i3 = 0;
            while (i2 <= i) {
                int i4 = (i2 + i) / 2;
                if (a(this.f801f[i4], rectF)) {
                    int i5 = i4 + 1;
                    i3 = i2;
                    i2 = i5;
                } else {
                    i3 = i4 - 1;
                    i = i3;
                }
            }
            return this.f801f[i3];
        }
        throw new IllegalStateException("No available text sizes to choose from.");
    }

    void a(int i) {
        TextPaint textPaint = this.h;
        if (textPaint == null) {
            this.h = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.h.set(this.i.getPaint());
        this.h.setTextSize(i);
    }

    StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 23) {
            return b(charSequence, alignment, i, i2);
        }
        if (i3 >= 16) {
            return b(charSequence, alignment, i);
        }
        return a(charSequence, alignment, i);
    }

    private boolean a(int i, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.i.getText();
        TransformationMethod transformationMethod = this.i.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.i)) != null) {
            text = transformation;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.i.getMaxLines() : -1;
        a(i);
        StaticLayout staticLayoutA = a(text, (Layout.Alignment) b(this.i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        return (maxLines == -1 || (staticLayoutA.getLineCount() <= maxLines && staticLayoutA.getLineEnd(staticLayoutA.getLineCount() - 1) == text.length())) && ((float) staticLayoutA.getHeight()) <= rectF.bottom;
    }

    private StaticLayout a(CharSequence charSequence, Layout.Alignment alignment, int i) {
        return new StaticLayout(charSequence, this.h, i, alignment, ((Float) a(this.i, "mSpacingMult", Float.valueOf(1.0f))).floatValue(), ((Float) a(this.i, "mSpacingAdd", Float.valueOf(0.0f))).floatValue(), ((Boolean) a(this.i, "mIncludePad", true)).booleanValue());
    }

    private static <T> T a(Object obj, String str, T t) {
        try {
            Field fieldA = a(str);
            return fieldA == null ? t : (T) fieldA.get(obj);
        } catch (IllegalAccessException e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + str + " member", e2);
            return t;
        }
    }

    private static Field a(String str) {
        try {
            Field declaredField = m.get(str);
            if (declaredField == null && (declaredField = TextView.class.getDeclaredField(str)) != null) {
                declaredField.setAccessible(true);
                m.put(str, declaredField);
            }
            return declaredField;
        } catch (NoSuchFieldException e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + str + " member", e2);
            return null;
        }
    }
}
