package androidx.core.content.c;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import androidx.core.R$styleable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: GradientColorInflaterCompat.java */
/* JADX INFO: loaded from: classes.dex */
final class d {
    static Shader a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayA = g.a(resources, theme, attributeSet, R$styleable.GradientColor);
        float fA = g.a(typedArrayA, xmlPullParser, "startX", R$styleable.GradientColor_android_startX, 0.0f);
        float fA2 = g.a(typedArrayA, xmlPullParser, "startY", R$styleable.GradientColor_android_startY, 0.0f);
        float fA3 = g.a(typedArrayA, xmlPullParser, "endX", R$styleable.GradientColor_android_endX, 0.0f);
        float fA4 = g.a(typedArrayA, xmlPullParser, "endY", R$styleable.GradientColor_android_endY, 0.0f);
        float fA5 = g.a(typedArrayA, xmlPullParser, "centerX", R$styleable.GradientColor_android_centerX, 0.0f);
        float fA6 = g.a(typedArrayA, xmlPullParser, "centerY", R$styleable.GradientColor_android_centerY, 0.0f);
        int iB = g.b(typedArrayA, xmlPullParser, "type", R$styleable.GradientColor_android_type, 0);
        int iA = g.a(typedArrayA, xmlPullParser, "startColor", R$styleable.GradientColor_android_startColor, 0);
        boolean zA = g.a(xmlPullParser, "centerColor");
        int iA2 = g.a(typedArrayA, xmlPullParser, "centerColor", R$styleable.GradientColor_android_centerColor, 0);
        int iA3 = g.a(typedArrayA, xmlPullParser, "endColor", R$styleable.GradientColor_android_endColor, 0);
        int iB2 = g.b(typedArrayA, xmlPullParser, "tileMode", R$styleable.GradientColor_android_tileMode, 0);
        float fA7 = g.a(typedArrayA, xmlPullParser, "gradientRadius", R$styleable.GradientColor_android_gradientRadius, 0.0f);
        typedArrayA.recycle();
        a aVarA = a(b(resources, xmlPullParser, attributeSet, theme), iA, iA3, zA, iA2);
        if (iB != 1) {
            return iB != 2 ? new LinearGradient(fA, fA2, fA3, fA4, aVarA.f1031a, aVarA.f1032b, a(iB2)) : new SweepGradient(fA5, fA6, aVarA.f1031a, aVarA.f1032b);
        }
        if (fA7 > 0.0f) {
            return new RadialGradient(fA5, fA6, fA7, aVarA.f1031a, aVarA.f1032b, a(iB2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    private static a b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        int depth2 = xmlPullParser.getDepth() + 1;
        ArrayList arrayList = new ArrayList(20);
        ArrayList arrayList2 = new ArrayList(20);
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || ((depth = xmlPullParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlPullParser.getName().equals("item")) {
                TypedArray typedArrayA = g.a(resources, theme, attributeSet, R$styleable.GradientColorItem);
                boolean zHasValue = typedArrayA.hasValue(R$styleable.GradientColorItem_android_color);
                boolean zHasValue2 = typedArrayA.hasValue(R$styleable.GradientColorItem_android_offset);
                if (!zHasValue || !zHasValue2) {
                    throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
                }
                int color = typedArrayA.getColor(R$styleable.GradientColorItem_android_color, 0);
                float f2 = typedArrayA.getFloat(R$styleable.GradientColorItem_android_offset, 0.0f);
                typedArrayA.recycle();
                arrayList2.add(Integer.valueOf(color));
                arrayList.add(Float.valueOf(f2));
            }
        }
        if (arrayList2.size() > 0) {
            return new a(arrayList2, arrayList);
        }
        return null;
    }

    /* JADX INFO: compiled from: GradientColorInflaterCompat.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final int[] f1031a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final float[] f1032b;

        a(List<Integer> list, List<Float> list2) {
            int size = list.size();
            this.f1031a = new int[size];
            this.f1032b = new float[size];
            for (int i = 0; i < size; i++) {
                this.f1031a[i] = list.get(i).intValue();
                this.f1032b[i] = list2.get(i).floatValue();
            }
        }

        a(int i, int i2) {
            this.f1031a = new int[]{i, i2};
            this.f1032b = new float[]{0.0f, 1.0f};
        }

        a(int i, int i2, int i3) {
            this.f1031a = new int[]{i, i2, i3};
            this.f1032b = new float[]{0.0f, 0.5f, 1.0f};
        }
    }

    private static a a(a aVar, int i, int i2, boolean z, int i3) {
        if (aVar != null) {
            return aVar;
        }
        if (z) {
            return new a(i, i3, i2);
        }
        return new a(i, i2);
    }

    private static Shader.TileMode a(int i) {
        if (i == 1) {
            return Shader.TileMode.REPEAT;
        }
        if (i != 2) {
            return Shader.TileMode.CLAMP;
        }
        return Shader.TileMode.MIRROR;
    }
}
