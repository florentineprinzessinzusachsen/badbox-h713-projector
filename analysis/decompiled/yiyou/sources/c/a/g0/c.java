package c.a.g0;

import c.a.l;
import c.a.s;

/* JADX INFO: compiled from: Subject.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> extends l<T> implements s<T> {
    public final c<T> a() {
        return this instanceof b ? this : new b(this);
    }
}
