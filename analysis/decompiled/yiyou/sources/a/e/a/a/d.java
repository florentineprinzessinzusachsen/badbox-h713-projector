package a.e.a.a;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: LookupTableInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f186b;

    protected d(float[] fArr) {
        this.f185a = fArr;
        this.f186b = 1.0f / (this.f185a.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f185a;
        int iMin = Math.min((int) ((fArr.length - 1) * f2), fArr.length - 2);
        float f3 = this.f186b;
        float f4 = (f2 - (iMin * f3)) / f3;
        float[] fArr2 = this.f185a;
        return fArr2[iMin] + (f4 * (fArr2[iMin + 1] - fArr2[iMin]));
    }
}
