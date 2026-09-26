package s0;

import java.lang.reflect.Field;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final enum a extends h {
    public a() {
        super("IDENTITY", 0);
    }

    @Override // s0.h
    public final String b(Field field) {
        return field.getName();
    }
}
