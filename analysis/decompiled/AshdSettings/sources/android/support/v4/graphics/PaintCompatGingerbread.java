package android.support.v4.graphics;

import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.util.Pair;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(9)
class PaintCompatGingerbread {
    private static final String TOFU_STRING = "\udfffd";
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    PaintCompatGingerbread() {
    }

    static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        int length = str.length();
        if (length == 1 && Character.isWhitespace(str.charAt(0))) {
            return true;
        }
        float fMeasureText = paint.measureText(TOFU_STRING);
        float fMeasureText2 = paint.measureText(str);
        if (fMeasureText2 == 0.0f) {
            return false;
        }
        if (str.codePointCount(0, str.length()) > 1) {
            if (fMeasureText2 > 2.0f * fMeasureText) {
                return false;
            }
            float fMeasureText3 = 0.0f;
            int i = 0;
            while (i < length) {
                int iCharCount = Character.charCount(str.codePointAt(i)) + i;
                fMeasureText3 += paint.measureText(str, i, iCharCount);
                i = iCharCount;
            }
            if (fMeasureText2 >= fMeasureText3) {
                return false;
            }
        }
        if (fMeasureText2 != fMeasureText) {
            return true;
        }
        Pair<Rect, Rect> pairObtainEmptyRects = obtainEmptyRects();
        paint.getTextBounds(TOFU_STRING, 0, TOFU_STRING.length(), pairObtainEmptyRects.first);
        paint.getTextBounds(str, 0, length, pairObtainEmptyRects.second);
        return !pairObtainEmptyRects.first.equals(pairObtainEmptyRects.second);
    }

    private static Pair<Rect, Rect> obtainEmptyRects() {
        Pair<Rect, Rect> pair = sRectThreadLocal.get();
        if (pair == null) {
            Pair<Rect, Rect> pair2 = new Pair<>(new Rect(), new Rect());
            sRectThreadLocal.set(pair2);
            return pair2;
        }
        pair.first.setEmpty();
        pair.second.setEmpty();
        return pair;
    }
}
