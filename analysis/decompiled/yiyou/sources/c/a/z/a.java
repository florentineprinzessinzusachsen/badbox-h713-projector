package c.a.z;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* JADX INFO: compiled from: CompositeException.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<Throwable> f3192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Throwable f3194c;

    /* JADX INFO: renamed from: c.a.z.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CompositeException.java */
    static final class C0079a extends RuntimeException {
        C0079a() {
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "Chain of Causes for CompositeException In Order Received =>";
        }
    }

    /* JADX INFO: compiled from: CompositeException.java */
    static abstract class b {
        b() {
        }

        abstract void a(Object obj);
    }

    /* JADX INFO: compiled from: CompositeException.java */
    static final class c extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final PrintStream f3195a;

        c(PrintStream printStream) {
            this.f3195a = printStream;
        }

        @Override // c.a.z.a.b
        void a(Object obj) {
            this.f3195a.println(obj);
        }
    }

    /* JADX INFO: compiled from: CompositeException.java */
    static final class d extends b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final PrintWriter f3196a;

        d(PrintWriter printWriter) {
            this.f3196a = printWriter;
        }

        @Override // c.a.z.a.b
        void a(Object obj) {
            this.f3196a.println(obj);
        }
    }

    public a(Throwable... thArr) {
        this(thArr == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(thArr));
    }

    private List<Throwable> b(Throwable th) {
        ArrayList arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause != null && cause != th) {
            while (true) {
                arrayList.add(cause);
                Throwable cause2 = cause.getCause();
                if (cause2 == null || cause2 == cause) {
                    break;
                }
                cause = cause2;
            }
        }
        return arrayList;
    }

    public List<Throwable> a() {
        return this.f3192a;
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        if (this.f3194c == null) {
            C0079a c0079a = new C0079a();
            HashSet hashSet = new HashSet();
            Iterator<Throwable> it = this.f3192a.iterator();
            Throwable thA = c0079a;
            while (it.hasNext()) {
                Throwable next = it.next();
                if (!hashSet.contains(next)) {
                    hashSet.add(next);
                    for (Throwable th : b(next)) {
                        if (hashSet.contains(th)) {
                            next = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th);
                        }
                    }
                    try {
                        thA.initCause(next);
                    } catch (Throwable unused) {
                    }
                    thA = a(thA);
                }
            }
            this.f3194c = c0079a;
        }
        return this.f3194c;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f3193b;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    private void a(b bVar) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : getStackTrace()) {
            sb.append("\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        int i = 1;
        for (Throwable th : this.f3192a) {
            sb.append("  ComposedException ");
            sb.append(i);
            sb.append(" :\n");
            a(sb, th, "\t");
            i++;
        }
        bVar.a(sb.toString());
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        a(new c(printStream));
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        a(new d(printWriter));
    }

    public a(Iterable<? extends Throwable> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (iterable != null) {
            for (Throwable th : iterable) {
                if (th instanceof a) {
                    linkedHashSet.addAll(((a) th).a());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (!linkedHashSet.isEmpty()) {
            arrayList.addAll(linkedHashSet);
            this.f3192a = Collections.unmodifiableList(arrayList);
            this.f3193b = this.f3192a.size() + " exceptions occurred. ";
            return;
        }
        throw new IllegalArgumentException("errors is empty");
    }

    private void a(StringBuilder sb, Throwable th, String str) {
        sb.append(str);
        sb.append(th);
        sb.append('\n');
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\t\tat ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        if (th.getCause() != null) {
            sb.append("\tCaused by: ");
            a(sb, th.getCause(), "");
        }
    }

    Throwable a(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null || this.f3194c == cause) {
            return th;
        }
        while (true) {
            Throwable cause2 = cause.getCause();
            if (cause2 == null || cause2 == cause) {
                break;
            }
            cause = cause2;
        }
        return cause;
    }
}
