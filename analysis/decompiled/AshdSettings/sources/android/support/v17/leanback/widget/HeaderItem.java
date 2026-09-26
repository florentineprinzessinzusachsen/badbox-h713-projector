package android.support.v17.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
public class HeaderItem {
    private CharSequence mContentDescription;
    private CharSequence mDescription;
    private final long mId;
    private final String mName;

    public HeaderItem(long j, String str) {
        this.mId = j;
        this.mName = str;
    }

    public HeaderItem(String str) {
        this(-1L, str);
    }

    public final long getId() {
        return this.mId;
    }

    public final String getName() {
        return this.mName;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
    }

    public void setDescription(CharSequence charSequence) {
        this.mDescription = charSequence;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }
}
