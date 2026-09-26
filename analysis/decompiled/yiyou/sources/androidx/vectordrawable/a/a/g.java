package androidx.vectordrawable.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: PathInterpolatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float[] f1440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float[] f1441b;

    public g(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (androidx.core.content.c.g.a(xmlPullParser, "pathData")) {
            String strA = androidx.core.content.c.g.a(typedArray, xmlPullParser, "pathData", 4);
            Path pathB = androidx.core.a.b.b(strA);
            if (pathB != null) {
                a(pathB);
                return;
            }
            throw new InflateException("The path is null, which is created from " + strA);
        }
        if (!androidx.core.content.c.g.a(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        if (!androidx.core.content.c.g.a(xmlPullParser, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        float fA = androidx.core.content.c.g.a(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
        float fA2 = androidx.core.content.c.g.a(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
        boolean zA = androidx.core.content.c.g.a(xmlPullParser, "controlX2");
        if (zA != androidx.core.content.c.g.a(xmlPullParser, "controlY2")) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        }
        if (zA) {
            a(fA, fA2, androidx.core.content.c.g.a(typedArray, xmlPullParser, "controlX2", 2, 0.0f), androidx.core.content.c.g.a(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
        } else {
            a(fA, fA2);
        }
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.f1440a.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f2 < this.f1440a[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.f1440a;
        float f3 = fArr[length] - fArr[i];
        if (f3 == 0.0f) {
            return this.f1441b[i];
        }
        float f4 = (f2 - fArr[i]) / f3;
        float[] fArr2 = this.f1441b;
        float f5 = fArr2[i];
        return f5 + (f4 * (fArr2[length] - f5));
    }

    public g(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, a.l);
        a(typedArrayA, xmlPullParser);
        typedArrayA.recycle();
    }

    private void a(float f2, float f3) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f2, f3, 1.0f, 1.0f);
        a(path);
    }

    private void a(float f2, float f3, float f4, float f5) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f2, f3, f4, f5, 1.0f, 1.0f);
        a(path);
    }

    private void a(Path path) {
        int i = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int iMin = Math.min(3000, ((int) (length / 0.002f)) + 1);
        if (iMin > 0) {
            this.f1440a = new float[iMin];
            this.f1441b = new float[iMin];
            float[] fArr = new float[2];
            for (int i2 = 0; i2 < iMin; i2++) {
                pathMeasure.getPosTan((i2 * length) / (iMin - 1), fArr, null);
                this.f1440a[i2] = fArr[0];
                this.f1441b[i2] = fArr[1];
            }
            if (Math.abs(this.f1440a[0]) <= 1.0E-5d && Math.abs(this.f1441b[0]) <= 1.0E-5d) {
                int i3 = iMin - 1;
                if (Math.abs(this.f1440a[i3] - 1.0f) <= 1.0E-5d && Math.abs(this.f1441b[i3] - 1.0f) <= 1.0E-5d) {
                    int i4 = 0;
                    float f2 = 0.0f;
                    while (i < iMin) {
                        float[] fArr2 = this.f1440a;
                        int i5 = i4 + 1;
                        float f3 = fArr2[i4];
                        if (f3 >= f2) {
                            fArr2[i] = f3;
                            i++;
                            f2 = f3;
                            i4 = i5;
                        } else {
                            throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f3);
                        }
                    }
                    if (pathMeasure.nextContour()) {
                        throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                    }
                    return;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("The Path must start at (0,0) and end at (1,1) start: ");
            sb.append(this.f1440a[0]);
            sb.append(",");
            sb.append(this.f1441b[0]);
            sb.append(" end:");
            int i6 = iMin - 1;
            sb.append(this.f1440a[i6]);
            sb.append(",");
            sb.append(this.f1441b[i6]);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("The Path has a invalid length " + length);
    }
}
