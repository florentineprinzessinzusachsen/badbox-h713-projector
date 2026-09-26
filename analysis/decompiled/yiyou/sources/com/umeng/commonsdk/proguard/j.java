package com.umeng.commonsdk.proguard;

import com.umeng.commonsdk.proguard.j;
import com.umeng.commonsdk.proguard.q;
import java.io.Serializable;

/* JADX INFO: compiled from: TBase.java */
/* JADX INFO: loaded from: classes.dex */
public interface j<T extends j<?, ?>, F extends q> extends Serializable {
    void clear();

    j<T, F> deepCopy();

    F fieldForId(int i);

    void read(ai aiVar);

    void write(ai aiVar);
}
