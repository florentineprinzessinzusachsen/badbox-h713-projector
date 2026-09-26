package androidx.core.content.c;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: TypedArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static boolean a(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static int b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !a(xmlPullParser, str) ? i2 : typedArray.getInt(i, i2);
    }

    public static int c(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !a(xmlPullParser, str) ? i2 : typedArray.getResourceId(i, i2);
    }

    public static float a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, float f2) {
        return !a(xmlPullParser, str) ? f2 : typedArray.getFloat(i, f2);
    }

    public static TypedValue b(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (a(xmlPullParser, str)) {
            return typedArray.peekValue(i);
        }
        return null;
    }

    public static boolean a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, boolean z) {
        return !a(xmlPullParser, str) ? z : typedArray.getBoolean(i, z);
    }

    public static int a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i, int i2) {
        return !a(xmlPullParser, str) ? i2 : typedArray.getColor(i, i2);
    }

    public static b a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i, int i2) {
        if (a(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return b.b(typedValue.data);
            }
            b bVarB = b.b(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
            if (bVarB != null) {
                return bVarB;
            }
        }
        return b.b(i2);
    }

    public static ColorStateList a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i) {
        if (!a(xmlPullParser, str)) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        typedArray.getValue(i, typedValue);
        int i2 = typedValue.type;
        if (i2 != 2) {
            if (i2 >= 28 && i2 <= 31) {
                return a(typedValue);
            }
            return a.a(typedArray.getResources(), typedArray.getResourceId(i, 0), theme);
        }
        throw new UnsupportedOperationException("Failed to resolve attribute at index " + i + ": " + typedValue);
    }

    private static ColorStateList a(TypedValue typedValue) {
        return ColorStateList.valueOf(typedValue.data);
    }

    public static String a(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i) {
        if (a(xmlPullParser, str)) {
            return typedArray.getString(i);
        }
        return null;
    }

    public static TypedArray a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        if (theme == null) {
            return resources.obtainAttributes(attributeSet, iArr);
        }
        return theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }
}
