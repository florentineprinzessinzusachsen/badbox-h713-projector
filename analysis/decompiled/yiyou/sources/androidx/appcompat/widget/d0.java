package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/* JADX INFO: compiled from: TintTypedArray.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f731a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final TypedArray f732b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TypedValue f733c;

    private d0(Context context, TypedArray typedArray) {
        this.f731a = context;
        this.f732b = typedArray;
    }

    public static d0 a(Context context, AttributeSet attributeSet, int[] iArr) {
        return new d0(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public Drawable b(int i) {
        int resourceId;
        return (!this.f732b.hasValue(i) || (resourceId = this.f732b.getResourceId(i, 0)) == 0) ? this.f732b.getDrawable(i) : androidx.appcompat.a.a.a.c(this.f731a, resourceId);
    }

    public Drawable c(int i) {
        int resourceId;
        if (!this.f732b.hasValue(i) || (resourceId = this.f732b.getResourceId(i, 0)) == 0) {
            return null;
        }
        return e.b().a(this.f731a, resourceId, true);
    }

    public String d(int i) {
        return this.f732b.getString(i);
    }

    public CharSequence e(int i) {
        return this.f732b.getText(i);
    }

    public int f(int i, int i2) {
        return this.f732b.getLayoutDimension(i, i2);
    }

    public int g(int i, int i2) {
        return this.f732b.getResourceId(i, i2);
    }

    public static d0 a(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new d0(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public int d(int i, int i2) {
        return this.f732b.getInt(i, i2);
    }

    public int e(int i, int i2) {
        return this.f732b.getInteger(i, i2);
    }

    public CharSequence[] f(int i) {
        return this.f732b.getTextArray(i);
    }

    public boolean g(int i) {
        return this.f732b.hasValue(i);
    }

    public static d0 a(Context context, int i, int[] iArr) {
        return new d0(context, context.obtainStyledAttributes(i, iArr));
    }

    public int c(int i, int i2) {
        return this.f732b.getDimensionPixelSize(i, i2);
    }

    public Typeface a(int i, int i2, androidx.core.content.c.f.a aVar) {
        int resourceId = this.f732b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f733c == null) {
            this.f733c = new TypedValue();
        }
        return androidx.core.content.c.f.a(this.f731a, resourceId, this.f733c, i2, aVar);
    }

    public int b(int i, int i2) {
        return this.f732b.getDimensionPixelOffset(i, i2);
    }

    public boolean a(int i, boolean z) {
        return this.f732b.getBoolean(i, z);
    }

    public float a(int i, float f2) {
        return this.f732b.getFloat(i, f2);
    }

    public int a(int i, int i2) {
        return this.f732b.getColor(i, i2);
    }

    public ColorStateList a(int i) {
        int resourceId;
        ColorStateList colorStateListB;
        return (!this.f732b.hasValue(i) || (resourceId = this.f732b.getResourceId(i, 0)) == 0 || (colorStateListB = androidx.appcompat.a.a.a.b(this.f731a, resourceId)) == null) ? this.f732b.getColorStateList(i) : colorStateListB;
    }

    public void a() {
        this.f732b.recycle();
    }
}
