package androidx.vectordrawable.a.a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: AnimatorInflaterCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: AnimatorInflaterCompat.java */
    private static class a implements TypeEvaluator<androidx.core.a.b.C0013b[]> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private androidx.core.a.b.C0013b[] f1438a;

        a() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public androidx.core.a.b.C0013b[] evaluate(float f2, androidx.core.a.b.C0013b[] c0013bArr, androidx.core.a.b.C0013b[] c0013bArr2) {
            if (!androidx.core.a.b.a(c0013bArr, c0013bArr2)) {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            if (!androidx.core.a.b.a(this.f1438a, c0013bArr)) {
                this.f1438a = androidx.core.a.b.a(c0013bArr);
            }
            for (int i = 0; i < c0013bArr.length; i++) {
                this.f1438a[i].a(c0013bArr[i], c0013bArr2[i], f2);
            }
            return this.f1438a;
        }
    }

    public static Animator a(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? AnimatorInflater.loadAnimator(context, i) : a(context, context.getResources(), context.getTheme(), i);
    }

    private static boolean a(int i) {
        return i >= 28 && i <= 31;
    }

    public static Animator a(Context context, Resources resources, Resources.Theme theme, int i) {
        return a(context, resources, theme, i, 1.0f);
    }

    public static Animator a(Context context, Resources resources, Resources.Theme theme, int i, float f2) {
        XmlResourceParser animation = null;
        try {
            try {
                try {
                    animation = resources.getAnimation(i);
                    Animator animatorA = a(context, resources, theme, animation, f2);
                    if (animation != null) {
                        animation.close();
                    }
                    return animatorA;
                } catch (XmlPullParserException e2) {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                    notFoundException.initCause(e2);
                    throw notFoundException;
                }
            } catch (IOException e3) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e3);
                throw notFoundException2;
            }
        } catch (Throwable th) {
            if (animation != null) {
                animation.close();
            }
            throw th;
        }
    }

    private static PropertyValuesHolder a(TypedArray typedArray, int i, int i2, int i3, String str) {
        int color;
        int color2;
        int color3;
        float dimension;
        PropertyValuesHolder propertyValuesHolderOfFloat;
        float dimension2;
        float dimension3;
        PropertyValuesHolder propertyValuesHolderOfObject;
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        boolean z = typedValuePeekValue != null;
        int i4 = z ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i3);
        boolean z2 = typedValuePeekValue2 != null;
        int i5 = z2 ? typedValuePeekValue2.type : 0;
        if (i == 4) {
            i = ((z && a(i4)) || (z2 && a(i5))) ? 3 : 0;
        }
        boolean z3 = i == 0;
        PropertyValuesHolder propertyValuesHolderOfInt = null;
        if (i == 2) {
            String string = typedArray.getString(i2);
            String string2 = typedArray.getString(i3);
            androidx.core.a.b.C0013b[] c0013bArrA = androidx.core.a.b.a(string);
            androidx.core.a.b.C0013b[] c0013bArrA2 = androidx.core.a.b.a(string2);
            if (c0013bArrA == null && c0013bArrA2 == null) {
                return null;
            }
            if (c0013bArrA == null) {
                if (c0013bArrA2 != null) {
                    return PropertyValuesHolder.ofObject(str, new a(), c0013bArrA2);
                }
                return null;
            }
            a aVar = new a();
            if (c0013bArrA2 != null) {
                if (androidx.core.a.b.a(c0013bArrA, c0013bArrA2)) {
                    propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, aVar, c0013bArrA, c0013bArrA2);
                } else {
                    throw new InflateException(" Can't morph from " + string + " to " + string2);
                }
            } else {
                propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, aVar, c0013bArrA);
            }
            return propertyValuesHolderOfObject;
        }
        f fVarA = i == 3 ? f.a() : null;
        if (z3) {
            if (z) {
                if (i4 == 5) {
                    dimension2 = typedArray.getDimension(i2, 0.0f);
                } else {
                    dimension2 = typedArray.getFloat(i2, 0.0f);
                }
                if (z2) {
                    if (i5 == 5) {
                        dimension3 = typedArray.getDimension(i3, 0.0f);
                    } else {
                        dimension3 = typedArray.getFloat(i3, 0.0f);
                    }
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension2, dimension3);
                } else {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension2);
                }
            } else {
                if (i5 == 5) {
                    dimension = typedArray.getDimension(i3, 0.0f);
                } else {
                    dimension = typedArray.getFloat(i3, 0.0f);
                }
                propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension);
            }
            propertyValuesHolderOfInt = propertyValuesHolderOfFloat;
        } else if (z) {
            if (i4 == 5) {
                color2 = (int) typedArray.getDimension(i2, 0.0f);
            } else if (a(i4)) {
                color2 = typedArray.getColor(i2, 0);
            } else {
                color2 = typedArray.getInt(i2, 0);
            }
            if (z2) {
                if (i5 == 5) {
                    color3 = (int) typedArray.getDimension(i3, 0.0f);
                } else if (a(i5)) {
                    color3 = typedArray.getColor(i3, 0);
                } else {
                    color3 = typedArray.getInt(i3, 0);
                }
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color2, color3);
            } else {
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color2);
            }
        } else if (z2) {
            if (i5 == 5) {
                color = (int) typedArray.getDimension(i3, 0.0f);
            } else if (a(i5)) {
                color = typedArray.getColor(i3, 0);
            } else {
                color = typedArray.getInt(i3, 0);
            }
            propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color);
        }
        if (propertyValuesHolderOfInt == null || fVarA == null) {
            return propertyValuesHolderOfInt;
        }
        propertyValuesHolderOfInt.setEvaluator(fVarA);
        return propertyValuesHolderOfInt;
    }

    private static void a(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f2, XmlPullParser xmlPullParser) {
        long jB = androidx.core.content.c.g.b(typedArray, xmlPullParser, "duration", 1, 300);
        long jB2 = androidx.core.content.c.g.b(typedArray, xmlPullParser, "startOffset", 2, 0);
        int iB = androidx.core.content.c.g.b(typedArray, xmlPullParser, "valueType", 7, 4);
        if (androidx.core.content.c.g.a(xmlPullParser, "valueFrom") && androidx.core.content.c.g.a(xmlPullParser, "valueTo")) {
            if (iB == 4) {
                iB = a(typedArray, 5, 6);
            }
            PropertyValuesHolder propertyValuesHolderA = a(typedArray, iB, 5, 6, "");
            if (propertyValuesHolderA != null) {
                valueAnimator.setValues(propertyValuesHolderA);
            }
        }
        valueAnimator.setDuration(jB);
        valueAnimator.setStartDelay(jB2);
        valueAnimator.setRepeatCount(androidx.core.content.c.g.b(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(androidx.core.content.c.g.b(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            a(valueAnimator, typedArray2, iB, f2, xmlPullParser);
        }
    }

    private static void a(ValueAnimator valueAnimator, TypedArray typedArray, int i, float f2, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        String strA = androidx.core.content.c.g.a(typedArray, xmlPullParser, "pathData", 1);
        if (strA != null) {
            String strA2 = androidx.core.content.c.g.a(typedArray, xmlPullParser, "propertyXName", 2);
            String strA3 = androidx.core.content.c.g.a(typedArray, xmlPullParser, "propertyYName", 3);
            if (i != 2) {
            }
            if (strA2 == null && strA3 == null) {
                throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            a(androidx.core.a.b.b(strA), objectAnimator, f2 * 0.5f, strA2, strA3);
            return;
        }
        objectAnimator.setPropertyName(androidx.core.content.c.g.a(typedArray, xmlPullParser, "propertyName", 0));
    }

    private static void a(Path path, ObjectAnimator objectAnimator, float f2, String str, String str2) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(0.0f));
        float length = 0.0f;
        do {
            length += pathMeasure.getLength();
            arrayList.add(Float.valueOf(length));
        } while (pathMeasure.nextContour());
        PathMeasure pathMeasure2 = new PathMeasure(path, false);
        int iMin = Math.min(100, ((int) (length / f2)) + 1);
        float[] fArr = new float[iMin];
        float[] fArr2 = new float[iMin];
        float[] fArr3 = new float[2];
        float f3 = length / (iMin - 1);
        int i = 0;
        float f4 = 0.0f;
        int i2 = 0;
        while (true) {
            if (i >= iMin) {
                break;
            }
            pathMeasure2.getPosTan(f4 - ((Float) arrayList.get(i2)).floatValue(), fArr3, null);
            fArr[i] = fArr3[0];
            fArr2[i] = fArr3[1];
            f4 += f3;
            int i3 = i2 + 1;
            if (i3 < arrayList.size() && f4 > ((Float) arrayList.get(i3)).floatValue()) {
                pathMeasure2.nextContour();
                i2 = i3;
            }
            i++;
        }
        PropertyValuesHolder propertyValuesHolderOfFloat = str != null ? PropertyValuesHolder.ofFloat(str, fArr) : null;
        PropertyValuesHolder propertyValuesHolderOfFloat2 = str2 != null ? PropertyValuesHolder.ofFloat(str2, fArr2) : null;
        if (propertyValuesHolderOfFloat == null) {
            objectAnimator.setValues(propertyValuesHolderOfFloat2);
        } else if (propertyValuesHolderOfFloat2 == null) {
            objectAnimator.setValues(propertyValuesHolderOfFloat);
        } else {
            objectAnimator.setValues(propertyValuesHolderOfFloat, propertyValuesHolderOfFloat2);
        }
    }

    private static Animator a(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, float f2) {
        return a(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0, f2);
    }

    private static Animator a(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i, float f2) throws XmlPullParserException, IOException {
        int i2;
        int depth = xmlPullParser.getDepth();
        Animator animatorA = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            i2 = 0;
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                break;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("objectAnimator")) {
                    animatorA = a(context, resources, theme, attributeSet, f2, xmlPullParser);
                } else {
                    if (name.equals("animator")) {
                        animatorA = a(context, resources, theme, attributeSet, null, f2, xmlPullParser);
                    } else if (name.equals("set")) {
                        AnimatorSet animatorSet2 = new AnimatorSet();
                        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.h);
                        a(context, resources, theme, xmlPullParser, attributeSet, animatorSet2, androidx.core.content.c.g.b(typedArrayA, xmlPullParser, "ordering", 0, 0), f2);
                        typedArrayA.recycle();
                        animatorA = animatorSet2;
                    } else if (name.equals("propertyValuesHolder")) {
                        PropertyValuesHolder[] propertyValuesHolderArrA = a(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser));
                        if (propertyValuesHolderArrA != null && (animatorA instanceof ValueAnimator)) {
                            ((ValueAnimator) animatorA).setValues(propertyValuesHolderArrA);
                        }
                        i2 = 1;
                    } else {
                        throw new RuntimeException("Unknown animator name: " + xmlPullParser.getName());
                    }
                    if (animatorSet == null && i2 == 0) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(animatorA);
                    }
                }
                if (animatorSet == null) {
                }
            }
        }
        if (animatorSet != null && arrayList != null) {
            Animator[] animatorArr = new Animator[arrayList.size()];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                animatorArr[i2] = (Animator) it.next();
                i2++;
            }
            if (i == 0) {
                animatorSet.playTogether(animatorArr);
            } else {
                animatorSet.playSequentially(animatorArr);
            }
        }
        return animatorA;
    }

    private static PropertyValuesHolder[] a(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        int i;
        PropertyValuesHolder[] propertyValuesHolderArr = null;
        ArrayList arrayList = null;
        while (true) {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 3 || eventType == 1) {
                break;
            }
            if (eventType != 2) {
                xmlPullParser.next();
            } else {
                if (xmlPullParser.getName().equals("propertyValuesHolder")) {
                    TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.i);
                    String strA = androidx.core.content.c.g.a(typedArrayA, xmlPullParser, "propertyName", 3);
                    int iB = androidx.core.content.c.g.b(typedArrayA, xmlPullParser, "valueType", 2, 4);
                    PropertyValuesHolder propertyValuesHolderA = a(context, resources, theme, xmlPullParser, strA, iB);
                    if (propertyValuesHolderA == null) {
                        propertyValuesHolderA = a(typedArrayA, iB, 0, 1, strA);
                    }
                    if (propertyValuesHolderA != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(propertyValuesHolderA);
                    }
                    typedArrayA.recycle();
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null) {
            int size = arrayList.size();
            propertyValuesHolderArr = new PropertyValuesHolder[size];
            for (i = 0; i < size; i++) {
                propertyValuesHolderArr[i] = (PropertyValuesHolder) arrayList.get(i);
            }
        }
        return propertyValuesHolderArr;
    }

    private static int a(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.j);
        int i = 0;
        TypedValue typedValueB = androidx.core.content.c.g.b(typedArrayA, xmlPullParser, "value", 0);
        if ((typedValueB != null) && a(typedValueB.type)) {
            i = 3;
        }
        typedArrayA.recycle();
        return i;
    }

    private static int a(TypedArray typedArray, int i, int i2) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        boolean z = typedValuePeekValue != null;
        int i3 = z ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i2);
        boolean z2 = typedValuePeekValue2 != null;
        return ((z && a(i3)) || (z2 && a(z2 ? typedValuePeekValue2.type : 0))) ? 3 : 0;
    }

    private static PropertyValuesHolder a(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, String str, int i) throws XmlPullParserException, IOException {
        int size;
        PropertyValuesHolder propertyValuesHolderOfKeyframe = null;
        int iA = i;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3 || next == 1) {
                break;
            }
            if (xmlPullParser.getName().equals("keyframe")) {
                if (iA == 4) {
                    iA = a(resources, theme, Xml.asAttributeSet(xmlPullParser), xmlPullParser);
                }
                Keyframe keyframeA = a(context, resources, theme, Xml.asAttributeSet(xmlPullParser), iA, xmlPullParser);
                if (keyframeA != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(keyframeA);
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null && (size = arrayList.size()) > 0) {
            Keyframe keyframe = (Keyframe) arrayList.get(0);
            Keyframe keyframe2 = (Keyframe) arrayList.get(size - 1);
            float fraction = keyframe2.getFraction();
            if (fraction < 1.0f) {
                if (fraction < 0.0f) {
                    keyframe2.setFraction(1.0f);
                } else {
                    arrayList.add(arrayList.size(), a(keyframe2, 1.0f));
                    size++;
                }
            }
            float fraction2 = keyframe.getFraction();
            if (fraction2 != 0.0f) {
                if (fraction2 < 0.0f) {
                    keyframe.setFraction(0.0f);
                } else {
                    arrayList.add(0, a(keyframe, 0.0f));
                    size++;
                }
            }
            Keyframe[] keyframeArr = new Keyframe[size];
            arrayList.toArray(keyframeArr);
            for (int i2 = 0; i2 < size; i2++) {
                Keyframe keyframe3 = keyframeArr[i2];
                if (keyframe3.getFraction() < 0.0f) {
                    if (i2 == 0) {
                        keyframe3.setFraction(0.0f);
                    } else {
                        int i3 = size - 1;
                        if (i2 == i3) {
                            keyframe3.setFraction(1.0f);
                        } else {
                            int i4 = i2;
                            for (int i5 = i2 + 1; i5 < i3 && keyframeArr[i5].getFraction() < 0.0f; i5++) {
                                i4 = i5;
                            }
                            a(keyframeArr, keyframeArr[i4 + 1].getFraction() - keyframeArr[i2 - 1].getFraction(), i2, i4);
                        }
                    }
                }
            }
            propertyValuesHolderOfKeyframe = PropertyValuesHolder.ofKeyframe(str, keyframeArr);
            if (iA == 3) {
                propertyValuesHolderOfKeyframe.setEvaluator(f.a());
            }
        }
        return propertyValuesHolderOfKeyframe;
    }

    private static Keyframe a(Keyframe keyframe, float f2) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f2);
        }
        if (keyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(f2);
        }
        return Keyframe.ofObject(f2);
    }

    private static void a(Keyframe[] keyframeArr, float f2, int i, int i2) {
        float f3 = f2 / ((i2 - i) + 2);
        while (i <= i2) {
            keyframeArr[i].setFraction(keyframeArr[i - 1].getFraction() + f3);
            i++;
        }
    }

    private static Keyframe a(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, int i, XmlPullParser xmlPullParser) {
        Keyframe keyframeOfInt;
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.j);
        float fA = androidx.core.content.c.g.a(typedArrayA, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue typedValueB = androidx.core.content.c.g.b(typedArrayA, xmlPullParser, "value", 0);
        boolean z = typedValueB != null;
        if (i == 4) {
            i = (z && a(typedValueB.type)) ? 3 : 0;
        }
        if (z) {
            if (i != 0) {
                keyframeOfInt = (i == 1 || i == 3) ? Keyframe.ofInt(fA, androidx.core.content.c.g.b(typedArrayA, xmlPullParser, "value", 0, 0)) : null;
            } else {
                keyframeOfInt = Keyframe.ofFloat(fA, androidx.core.content.c.g.a(typedArrayA, xmlPullParser, "value", 0, 0.0f));
            }
        } else if (i == 0) {
            keyframeOfInt = Keyframe.ofFloat(fA);
        } else {
            keyframeOfInt = Keyframe.ofInt(fA);
        }
        int iC = androidx.core.content.c.g.c(typedArrayA, xmlPullParser, "interpolator", 1, 0);
        if (iC > 0) {
            keyframeOfInt.setInterpolator(d.a(context, iC));
        }
        typedArrayA.recycle();
        return keyframeOfInt;
    }

    private static ObjectAnimator a(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, float f2, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        a(context, resources, theme, attributeSet, objectAnimator, f2, xmlPullParser);
        return objectAnimator;
    }

    private static ValueAnimator a(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f2, XmlPullParser xmlPullParser) {
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.g);
        TypedArray typedArrayA2 = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.k);
        if (valueAnimator == null) {
            valueAnimator = new ValueAnimator();
        }
        a(valueAnimator, typedArrayA, typedArrayA2, f2, xmlPullParser);
        int iC = androidx.core.content.c.g.c(typedArrayA, xmlPullParser, "interpolator", 0, 0);
        if (iC > 0) {
            valueAnimator.setInterpolator(d.a(context, iC));
        }
        typedArrayA.recycle();
        if (typedArrayA2 != null) {
            typedArrayA2.recycle();
        }
        return valueAnimator;
    }
}
