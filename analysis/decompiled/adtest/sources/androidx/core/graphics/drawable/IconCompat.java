package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import j.a;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f291k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f293b;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f301j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f292a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f294c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f295d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f296e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f297f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f298g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f299h = f291k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f300i = null;

    public final String toString() {
        String str;
        int iIntValue;
        if (this.f292a == -1) {
            return String.valueOf(this.f293b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f292a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f292a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f293b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f293b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f301j);
                sb.append(" id=");
                int i4 = this.f292a;
                if (i4 == -1) {
                    int i5 = Build.VERSION.SDK_INT;
                    Object obj = this.f293b;
                    if (i5 >= 28) {
                        iIntValue = a.a(obj);
                    } else {
                        try {
                            iIntValue = ((Integer) obj.getClass().getMethod("getResId", null).invoke(obj, null)).intValue();
                        } catch (IllegalAccessException e4) {
                            Log.e("IconCompat", "Unable to get icon resource", e4);
                            iIntValue = 0;
                        } catch (NoSuchMethodException e5) {
                            Log.e("IconCompat", "Unable to get icon resource", e5);
                            iIntValue = 0;
                        } catch (InvocationTargetException e6) {
                            Log.e("IconCompat", "Unable to get icon resource", e6);
                            iIntValue = 0;
                        }
                    }
                } else {
                    if (i4 != 2) {
                        throw new IllegalStateException("called getResId() on " + this);
                    }
                    iIntValue = this.f296e;
                }
                sb.append(String.format("0x%08x", Integer.valueOf(iIntValue)));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f296e);
                if (this.f297f != 0) {
                    sb.append(" off=");
                    sb.append(this.f297f);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f293b);
                break;
        }
        if (this.f298g != null) {
            sb.append(" tint=");
            sb.append(this.f298g);
        }
        if (this.f299h != f291k) {
            sb.append(" mode=");
            sb.append(this.f299h);
        }
        sb.append(")");
        return sb.toString();
    }
}
