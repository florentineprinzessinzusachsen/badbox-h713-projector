package androidx.core.d;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.core.e.c;

/* JADX INFO: compiled from: PrecomputedTextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Spannable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Spannable f1037a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final C0020a f1038b;

    /* JADX INFO: renamed from: androidx.core.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class C0020a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f1039a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f1040b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1041c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f1042d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f1043e = null;

        /* JADX INFO: renamed from: androidx.core.d.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0021a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f1044a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f1045b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f1046c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f1047d;

            public C0021a(TextPaint textPaint) {
                this.f1044a = textPaint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.f1046c = 1;
                    this.f1047d = 1;
                } else {
                    this.f1047d = 0;
                    this.f1046c = 0;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    this.f1045b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f1045b = null;
                }
            }

            public C0021a a(int i) {
                this.f1046c = i;
                return this;
            }

            public C0021a b(int i) {
                this.f1047d = i;
                return this;
            }

            public C0021a a(TextDirectionHeuristic textDirectionHeuristic) {
                this.f1045b = textDirectionHeuristic;
                return this;
            }

            public C0020a a() {
                return new C0020a(this.f1044a, this.f1045b, this.f1046c, this.f1047d);
            }
        }

        C0020a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            this.f1039a = textPaint;
            this.f1040b = textDirectionHeuristic;
            this.f1041c = i;
            this.f1042d = i2;
        }

        public int a() {
            return this.f1041c;
        }

        public int b() {
            return this.f1042d;
        }

        public TextDirectionHeuristic c() {
            return this.f1040b;
        }

        public TextPaint d() {
            return this.f1039a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0020a)) {
                return false;
            }
            C0020a c0020a = (C0020a) obj;
            if (a(c0020a)) {
                return Build.VERSION.SDK_INT < 18 || this.f1040b == c0020a.c();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                return c.a(Float.valueOf(this.f1039a.getTextSize()), Float.valueOf(this.f1039a.getTextScaleX()), Float.valueOf(this.f1039a.getTextSkewX()), Float.valueOf(this.f1039a.getLetterSpacing()), Integer.valueOf(this.f1039a.getFlags()), this.f1039a.getTextLocales(), this.f1039a.getTypeface(), Boolean.valueOf(this.f1039a.isElegantTextHeight()), this.f1040b, Integer.valueOf(this.f1041c), Integer.valueOf(this.f1042d));
            }
            if (i >= 21) {
                return c.a(Float.valueOf(this.f1039a.getTextSize()), Float.valueOf(this.f1039a.getTextScaleX()), Float.valueOf(this.f1039a.getTextSkewX()), Float.valueOf(this.f1039a.getLetterSpacing()), Integer.valueOf(this.f1039a.getFlags()), this.f1039a.getTextLocale(), this.f1039a.getTypeface(), Boolean.valueOf(this.f1039a.isElegantTextHeight()), this.f1040b, Integer.valueOf(this.f1041c), Integer.valueOf(this.f1042d));
            }
            if (i >= 18) {
                return c.a(Float.valueOf(this.f1039a.getTextSize()), Float.valueOf(this.f1039a.getTextScaleX()), Float.valueOf(this.f1039a.getTextSkewX()), Integer.valueOf(this.f1039a.getFlags()), this.f1039a.getTextLocale(), this.f1039a.getTypeface(), this.f1040b, Integer.valueOf(this.f1041c), Integer.valueOf(this.f1042d));
            }
            return i >= 17 ? c.a(Float.valueOf(this.f1039a.getTextSize()), Float.valueOf(this.f1039a.getTextScaleX()), Float.valueOf(this.f1039a.getTextSkewX()), Integer.valueOf(this.f1039a.getFlags()), this.f1039a.getTextLocale(), this.f1039a.getTypeface(), this.f1040b, Integer.valueOf(this.f1041c), Integer.valueOf(this.f1042d)) : c.a(Float.valueOf(this.f1039a.getTextSize()), Float.valueOf(this.f1039a.getTextScaleX()), Float.valueOf(this.f1039a.getTextSkewX()), Integer.valueOf(this.f1039a.getFlags()), this.f1039a.getTypeface(), this.f1040b, Integer.valueOf(this.f1041c), Integer.valueOf(this.f1042d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1039a.getTextSize());
            sb.append(", textScaleX=" + this.f1039a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1039a.getTextSkewX());
            if (Build.VERSION.SDK_INT >= 21) {
                sb.append(", letterSpacing=" + this.f1039a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1039a.isElegantTextHeight());
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                sb.append(", textLocale=" + this.f1039a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f1039a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1039a.getTypeface());
            if (Build.VERSION.SDK_INT >= 26) {
                sb.append(", variationSettings=" + this.f1039a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f1040b);
            sb.append(", breakStrategy=" + this.f1041c);
            sb.append(", hyphenationFrequency=" + this.f1042d);
            sb.append("}");
            return sb.toString();
        }

        public boolean a(C0020a c0020a) {
            PrecomputedText.Params params = this.f1043e;
            if (params != null) {
                return params.equals(c0020a.f1043e);
            }
            if ((Build.VERSION.SDK_INT >= 23 && (this.f1041c != c0020a.a() || this.f1042d != c0020a.b())) || this.f1039a.getTextSize() != c0020a.d().getTextSize() || this.f1039a.getTextScaleX() != c0020a.d().getTextScaleX() || this.f1039a.getTextSkewX() != c0020a.d().getTextSkewX()) {
                return false;
            }
            if ((Build.VERSION.SDK_INT >= 21 && (this.f1039a.getLetterSpacing() != c0020a.d().getLetterSpacing() || !TextUtils.equals(this.f1039a.getFontFeatureSettings(), c0020a.d().getFontFeatureSettings()))) || this.f1039a.getFlags() != c0020a.d().getFlags()) {
                return false;
            }
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                if (!this.f1039a.getTextLocales().equals(c0020a.d().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f1039a.getTextLocale().equals(c0020a.d().getTextLocale())) {
                return false;
            }
            if (this.f1039a.getTypeface() == null) {
                return c0020a.d().getTypeface() == null;
            }
            return this.f1039a.getTypeface().equals(c0020a.d().getTypeface());
        }

        public C0020a(PrecomputedText.Params params) {
            this.f1039a = params.getTextPaint();
            this.f1040b = params.getTextDirection();
            this.f1041c = params.getBreakStrategy();
            this.f1042d = params.getHyphenationFrequency();
        }
    }

    public C0020a a() {
        return this.f1038b;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f1037a.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f1037a.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f1037a.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f1037a.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return (T[]) this.f1037a.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f1037a.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f1037a.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        this.f1037a.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        this.f1037a.setSpan(obj, i, i2, i3);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f1037a.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f1037a.toString();
    }
}
