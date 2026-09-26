package androidx.appcompat.d;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.f.x;
import androidx.core.f.y;
import androidx.core.f.z;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Interpolator f394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    y f395d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f396e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f393b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final z f397f = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final ArrayList<x> f392a = new ArrayList<>();

    public h a(x xVar) {
        if (!this.f396e) {
            this.f392a.add(xVar);
        }
        return this;
    }

    void b() {
        this.f396e = false;
    }

    public void c() {
        if (this.f396e) {
            return;
        }
        for (x xVar : this.f392a) {
            long j = this.f393b;
            if (j >= 0) {
                xVar.a(j);
            }
            Interpolator interpolator = this.f394c;
            if (interpolator != null) {
                xVar.a(interpolator);
            }
            if (this.f395d != null) {
                xVar.a(this.f397f);
            }
            xVar.c();
        }
        this.f396e = true;
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompatSet.java */
    class a extends z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f398a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f399b = 0;

        a() {
        }

        void a() {
            this.f399b = 0;
            this.f398a = false;
            h.this.b();
        }

        @Override // androidx.core.f.z, androidx.core.f.y
        public void b(View view) {
            if (this.f398a) {
                return;
            }
            this.f398a = true;
            y yVar = h.this.f395d;
            if (yVar != null) {
                yVar.b(null);
            }
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            int i = this.f399b + 1;
            this.f399b = i;
            if (i == h.this.f392a.size()) {
                y yVar = h.this.f395d;
                if (yVar != null) {
                    yVar.a(null);
                }
                a();
            }
        }
    }

    public h a(x xVar, x xVar2) {
        this.f392a.add(xVar);
        xVar2.b(xVar.b());
        this.f392a.add(xVar2);
        return this;
    }

    public void a() {
        if (this.f396e) {
            Iterator<x> it = this.f392a.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.f396e = false;
        }
    }

    public h a(long j) {
        if (!this.f396e) {
            this.f393b = j;
        }
        return this;
    }

    public h a(Interpolator interpolator) {
        if (!this.f396e) {
            this.f394c = interpolator;
        }
        return this;
    }

    public h a(y yVar) {
        if (!this.f396e) {
            this.f395d = yVar;
        }
        return this;
    }
}
