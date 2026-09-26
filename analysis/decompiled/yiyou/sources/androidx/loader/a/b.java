package androidx.loader.a;

import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.h;
import androidx.lifecycle.m;
import androidx.lifecycle.n;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: compiled from: LoaderManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
class b extends androidx.loader.a.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static boolean f1408c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f1409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f1410b;

    /* JADX INFO: renamed from: androidx.loader.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class C0031b<D> implements n<D> {
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    static class c extends q {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final r.a f1411c = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private a.b.h<a> f1412b = new a.b.h<>();

        /* JADX INFO: compiled from: LoaderManagerImpl.java */
        static class a implements r.a {
            a() {
            }

            @Override // androidx.lifecycle.r.a
            public <T extends q> T a(Class<T> cls) {
                return new c();
            }
        }

        c() {
        }

        static c a(s sVar) {
            return (c) new r(sVar, f1411c).a(c.class);
        }

        @Override // androidx.lifecycle.q
        protected void b() {
            super.b();
            if (this.f1412b.b() <= 0) {
                this.f1412b.a();
            } else {
                this.f1412b.d(0).a(true);
                throw null;
            }
        }

        void c() {
            int iB = this.f1412b.b();
            for (int i = 0; i < iB; i++) {
                this.f1412b.d(i).c();
            }
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f1412b.b() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                if (this.f1412b.b() <= 0) {
                    return;
                }
                a aVarD = this.f1412b.d(0);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.f1412b.b(0));
                printWriter.print(": ");
                printWriter.println(aVarD.toString());
                aVarD.a(str2, fileDescriptor, printWriter, strArr);
                throw null;
            }
        }
    }

    b(h hVar, s sVar) {
        this.f1409a = hVar;
        this.f1410b = c.a(sVar);
    }

    @Override // androidx.loader.a.a
    public void a() {
        this.f1410b.c();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        androidx.core.e.a.a(this.f1409a, sb);
        sb.append("}}");
        return sb.toString();
    }

    /* JADX INFO: compiled from: LoaderManagerImpl.java */
    public static class a<D> extends m<D> implements androidx.loader.b.a.InterfaceC0032a<D> {
        private final int j;
        private final Bundle k;
        private final androidx.loader.b.a<D> l;
        private h m;
        private C0031b<D> n;
        private androidx.loader.b.a<D> o;

        @Override // androidx.lifecycle.LiveData
        protected void a() {
            if (b.f1408c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.l.c();
            throw null;
        }

        @Override // androidx.lifecycle.LiveData
        protected void b() {
            if (b.f1408c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.l.d();
            throw null;
        }

        void c() {
            h hVar = this.m;
            C0031b<D> c0031b = this.n;
            if (hVar == null || c0031b == null) {
                return;
            }
            super.a((n) c0031b);
            a(hVar, c0031b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.j);
            sb.append(" : ");
            androidx.core.e.a.a(this.l, sb);
            sb.append("}}");
            return sb.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void a(n<? super D> nVar) {
            super.a((n) nVar);
            this.m = null;
            this.n = null;
        }

        androidx.loader.b.a<D> a(boolean z) {
            if (b.f1408c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.l.a();
            throw null;
        }

        @Override // androidx.lifecycle.m, androidx.lifecycle.LiveData
        public void a(D d2) {
            super.a(d2);
            androidx.loader.b.a<D> aVar = this.o;
            if (aVar == null) {
                return;
            }
            aVar.b();
            throw null;
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.j);
            printWriter.print(" mArgs=");
            printWriter.println(this.k);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.l);
            this.l.a(str + "  ", fileDescriptor, printWriter, strArr);
            throw null;
        }
    }

    @Override // androidx.loader.a.a
    @Deprecated
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f1410b.a(str, fileDescriptor, printWriter, strArr);
    }
}
