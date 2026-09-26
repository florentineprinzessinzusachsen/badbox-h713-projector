package c.a.b0.j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ArrayListSupplier.java */
/* JADX INFO: loaded from: classes.dex */
public enum b implements Callable<List<Object>>, c.a.a0.n<Object, List<Object>> {
    INSTANCE;

    public static <T> Callable<List<T>> a() {
        return INSTANCE;
    }

    public static <T, O> c.a.a0.n<O, List<T>> b() {
        return INSTANCE;
    }

    @Override // c.a.a0.n
    public List<Object> apply(Object obj) {
        return new ArrayList();
    }

    @Override // java.util.concurrent.Callable
    public List<Object> call() {
        return new ArrayList();
    }
}
