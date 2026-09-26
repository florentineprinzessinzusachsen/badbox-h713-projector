package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.baidu.mobstat.Config;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode j = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object f1114b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1113a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f1115c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f1116d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1117e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1118f = 0;
    public ColorStateList g = null;
    PorterDuff.Mode h = j;
    public String i = null;

    private static String a(int i) {
        if (i == 1) {
            return "BITMAP";
        }
        if (i == 2) {
            return "RESOURCE";
        }
        if (i == 3) {
            return "DATA";
        }
        if (i != 4) {
            return i != 5 ? "UNKNOWN" : "BITMAP_MASKABLE";
        }
        return "URI";
    }

    public int a() {
        if (this.f1113a == -1 && Build.VERSION.SDK_INT >= 23) {
            return a((Icon) this.f1114b);
        }
        if (this.f1113a == 2) {
            return this.f1117e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String b() {
        if (this.f1113a == -1 && Build.VERSION.SDK_INT >= 23) {
            return b((Icon) this.f1114b);
        }
        if (this.f1113a == 2) {
            return ((String) this.f1114b).split(Config.TRACE_TODAY_VISIT_SPLIT, -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public void c() {
        this.h = PorterDuff.Mode.valueOf(this.i);
        int i = this.f1113a;
        if (i == -1) {
            Parcelable parcelable = this.f1116d;
            if (parcelable == null) {
                throw new IllegalArgumentException("Invalid icon");
            }
            this.f1114b = parcelable;
            return;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    this.f1114b = this.f1115c;
                    return;
                } else if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                }
            }
            this.f1114b = new String(this.f1115c, Charset.forName("UTF-16"));
            return;
        }
        Parcelable parcelable2 = this.f1116d;
        if (parcelable2 != null) {
            this.f1114b = parcelable2;
            return;
        }
        byte[] bArr = this.f1115c;
        this.f1114b = bArr;
        this.f1113a = 3;
        this.f1117e = 0;
        this.f1118f = bArr.length;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007a  */
    public String toString() {
        if (this.f1113a == -1) {
            return String.valueOf(this.f1114b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(a(this.f1113a));
        int i = this.f1113a;
        if (i == 1) {
            sb.append(" size=");
            sb.append(((Bitmap) this.f1114b).getWidth());
            sb.append(Config.EVENT_HEAT_X);
            sb.append(((Bitmap) this.f1114b).getHeight());
        } else if (i == 2) {
            sb.append(" pkg=");
            sb.append(b());
            sb.append(" id=");
            sb.append(String.format("0x%08x", Integer.valueOf(a())));
        } else if (i == 3) {
            sb.append(" len=");
            sb.append(this.f1117e);
            if (this.f1118f != 0) {
                sb.append(" off=");
                sb.append(this.f1118f);
            }
        } else if (i == 4) {
            sb.append(" uri=");
            sb.append(this.f1114b);
        } else if (i == 5) {
            sb.append(" size=");
            sb.append(((Bitmap) this.f1114b).getWidth());
            sb.append(Config.EVENT_HEAT_X);
            sb.append(((Bitmap) this.f1114b).getHeight());
        }
        if (this.g != null) {
            sb.append(" tint=");
            sb.append(this.g);
        }
        if (this.h != j) {
            sb.append(" mode=");
            sb.append(this.h);
        }
        sb.append(")");
        return sb.toString();
    }

    private static String b(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon package", e4);
            return null;
        }
    }

    public void a(boolean z) {
        this.i = this.h.name();
        int i = this.f1113a;
        if (i == -1) {
            if (!z) {
                this.f1116d = (Parcelable) this.f1114b;
                return;
            }
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
        if (i != 1) {
            if (i == 2) {
                this.f1115c = ((String) this.f1114b).getBytes(Charset.forName("UTF-16"));
                return;
            }
            if (i == 3) {
                this.f1115c = (byte[]) this.f1114b;
                return;
            } else if (i == 4) {
                this.f1115c = this.f1114b.toString().getBytes(Charset.forName("UTF-16"));
                return;
            } else if (i != 5) {
                return;
            }
        }
        if (z) {
            Bitmap bitmap = (Bitmap) this.f1114b;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
            this.f1115c = byteArrayOutputStream.toByteArray();
            return;
        }
        this.f1116d = (Parcelable) this.f1114b;
    }

    private static int a(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon resource", e4);
            return 0;
        }
    }
}
