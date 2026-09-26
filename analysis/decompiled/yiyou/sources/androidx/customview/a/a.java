package androidx.customview.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: AbsSavedState.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Parcelable f1174a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f1173b = new C0026a();
    public static final Parcelable.Creator<a> CREATOR = new b();

    /* JADX INFO: renamed from: androidx.customview.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsSavedState.java */
    static class C0026a extends a {
        C0026a() {
            super((C0026a) null);
        }
    }

    /* JADX INFO: compiled from: AbsSavedState.java */
    static class b implements Parcelable.ClassLoaderCreator<a> {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i) {
            return new a[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return a.f1173b;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return createFromParcel(parcel, (ClassLoader) null);
        }
    }

    /* synthetic */ a(C0026a c0026a) {
        this();
    }

    public final Parcelable a() {
        return this.f1174a;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f1174a, i);
    }

    private a() {
        this.f1174a = null;
    }

    protected a(Parcelable parcelable) {
        if (parcelable != null) {
            this.f1174a = parcelable == f1173b ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    protected a(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.f1174a = parcelable == null ? f1173b : parcelable;
    }
}
