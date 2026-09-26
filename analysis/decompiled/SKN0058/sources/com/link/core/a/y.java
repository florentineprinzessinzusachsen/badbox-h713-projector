package com.link.core.a;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class y<T> {
    public final AtomicReference<LinkedList<T>> a;

    public y() {
        AtomicReference<LinkedList<T>> atomicReference = new AtomicReference<>();
        this.a = atomicReference;
        atomicReference.set(new LinkedList<>());
    }
}
