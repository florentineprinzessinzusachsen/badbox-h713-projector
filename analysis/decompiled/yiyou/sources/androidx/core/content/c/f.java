package androidx.core.content.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static Drawable a(Resources resources, int i, Resources.Theme theme) {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static abstract class a {

        /* JADX INFO: renamed from: androidx.core.content.c.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ResourcesCompat.java */
        class RunnableC0019a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Typeface f1033a;

            RunnableC0019a(Typeface typeface) {
                this.f1033a = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a(this.f1033a);
            }
        }

        /* JADX INFO: compiled from: ResourcesCompat.java */
        class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f1035a;

            b(int i) {
                this.f1035a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a(this.f1035a);
            }
        }

        public abstract void a(int i);

        public abstract void a(Typeface typeface);

        public final void a(Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new RunnableC0019a(typeface));
        }

        public final void a(int i, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new b(i));
        }
    }

    public static Typeface a(Context context, int i, TypedValue typedValue, int i2, a aVar) {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, typedValue, i2, aVar, null, true);
    }

    private static Typeface a(Context context, int i, TypedValue typedValue, int i2, a aVar, Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface typefaceA = a(context, resources, typedValue, i, i2, aVar, handler, z);
        if (typefaceA != null || aVar != null) {
            return typefaceA;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARN: Code duplicated, block: B:35:0x00a3  */
    private static Typeface a(Context context, Resources resources, TypedValue typedValue, int i, int i2, a aVar, Handler handler, boolean z) {
        CharSequence charSequence = typedValue.string;
        if (charSequence != null) {
            String string = charSequence.toString();
            if (!string.startsWith("res/")) {
                if (aVar != null) {
                    aVar.a(-3, handler);
                }
                return null;
            }
            Typeface typefaceB = androidx.core.a.c.b(resources, i, i2);
            if (typefaceB != null) {
                if (aVar != null) {
                    aVar.a(typefaceB, handler);
                }
                return typefaceB;
            }
            try {
                if (string.toLowerCase().endsWith(".xml")) {
                    c.a aVarA = c.a(resources.getXml(i), resources);
                    if (aVarA == null) {
                        Log.e("ResourcesCompat", "Failed to find font-family tag");
                        if (aVar != null) {
                            aVar.a(-3, handler);
                        }
                        return null;
                    }
                    return androidx.core.a.c.a(context, aVarA, resources, i, i2, aVar, handler, z);
                }
                Typeface typefaceA = androidx.core.a.c.a(context, resources, i, string, i2);
                if (aVar != null) {
                    if (typefaceA != null) {
                        aVar.a(typefaceA, handler);
                    } else {
                        aVar.a(-3, handler);
                    }
                }
                return typefaceA;
            } catch (IOException e2) {
                Log.e("ResourcesCompat", "Failed to read xml resource " + string, e2);
                if (aVar != null) {
                    aVar.a(-3, handler);
                }
                return null;
            } catch (XmlPullParserException e3) {
                Log.e("ResourcesCompat", "Failed to parse xml resource " + string, e3);
                if (aVar != null) {
                    aVar.a(-3, handler);
                }
                return null;
            }
        }
        throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(i) + "\" (" + Integer.toHexString(i) + ") is not a Font: " + typedValue);
    }
}
