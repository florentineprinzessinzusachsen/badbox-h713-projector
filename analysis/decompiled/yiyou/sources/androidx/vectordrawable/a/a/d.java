package androidx.vectordrawable.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: AnimationUtilsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static Interpolator a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, i);
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                if (i == 17563663) {
                    return new a.e.a.a.a();
                }
                if (i == 17563661) {
                    return new a.e.a.a.b();
                }
                if (i == 17563662) {
                    return new a.e.a.a.c();
                }
                XmlResourceParser animation = context.getResources().getAnimation(i);
                Interpolator interpolatorA = a(context, context.getResources(), context.getTheme(), animation);
                if (animation != null) {
                    animation.close();
                }
                return interpolatorA;
            } catch (IOException e2) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e2);
                throw notFoundException;
            } catch (XmlPullParserException e3) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e3);
                throw notFoundException2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    private static Interpolator a(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Interpolator accelerateInterpolator;
        int depth = xmlPullParser.getDepth();
        Interpolator linearInterpolator = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                break;
            }
            if (next == 2) {
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlPullParser);
                String name = xmlPullParser.getName();
                if (name.equals("linearInterpolator")) {
                    linearInterpolator = new LinearInterpolator();
                } else {
                    if (name.equals("accelerateInterpolator")) {
                        accelerateInterpolator = new AccelerateInterpolator(context, attributeSetAsAttributeSet);
                    } else if (name.equals("decelerateInterpolator")) {
                        accelerateInterpolator = new DecelerateInterpolator(context, attributeSetAsAttributeSet);
                    } else if (name.equals("accelerateDecelerateInterpolator")) {
                        linearInterpolator = new AccelerateDecelerateInterpolator();
                    } else if (name.equals("cycleInterpolator")) {
                        accelerateInterpolator = new CycleInterpolator(context, attributeSetAsAttributeSet);
                    } else if (name.equals("anticipateInterpolator")) {
                        accelerateInterpolator = new AnticipateInterpolator(context, attributeSetAsAttributeSet);
                    } else if (name.equals("overshootInterpolator")) {
                        accelerateInterpolator = new OvershootInterpolator(context, attributeSetAsAttributeSet);
                    } else if (name.equals("anticipateOvershootInterpolator")) {
                        accelerateInterpolator = new AnticipateOvershootInterpolator(context, attributeSetAsAttributeSet);
                    } else if (name.equals("bounceInterpolator")) {
                        linearInterpolator = new BounceInterpolator();
                    } else if (name.equals("pathInterpolator")) {
                        accelerateInterpolator = new g(context, attributeSetAsAttributeSet, xmlPullParser);
                    } else {
                        throw new RuntimeException("Unknown interpolator name: " + xmlPullParser.getName());
                    }
                    linearInterpolator = accelerateInterpolator;
                }
            }
        }
        return linearInterpolator;
    }
}
