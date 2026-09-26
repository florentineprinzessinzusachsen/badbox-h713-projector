package android.support.v17.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
public class SectionRow extends Row {
    @Override // android.support.v17.leanback.widget.Row
    public final boolean isRenderedAsRowView() {
        return false;
    }

    public SectionRow(HeaderItem headerItem) {
        super(headerItem);
    }

    public SectionRow(long j, String str) {
        super(new HeaderItem(j, str));
    }

    public SectionRow(String str) {
        super(new HeaderItem(str));
    }
}
