package d.h0.j;

import e.l;
import e.r;
import e.s;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: compiled from: FileSystem.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f4582a = new C0101a();

    void a(File file);

    void a(File file, File file2);

    s b(File file);

    r c(File file);

    void d(File file);

    r e(File file);

    boolean f(File file);

    long g(File file);

    /* JADX INFO: renamed from: d.h0.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FileSystem.java */
    class C0101a implements a {
        C0101a() {
        }

        @Override // d.h0.j.a
        public void a(File file) throws IOException {
            if (file.delete() || !file.exists()) {
                return;
            }
            throw new IOException("failed to delete " + file);
        }

        @Override // d.h0.j.a
        public s b(File file) {
            return l.c(file);
        }

        @Override // d.h0.j.a
        public r c(File file) {
            try {
                return l.b(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return l.b(file);
            }
        }

        @Override // d.h0.j.a
        public void d(File file) throws IOException {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null) {
                throw new IOException("not a readable directory: " + file);
            }
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    d(file2);
                }
                if (!file2.delete()) {
                    throw new IOException("failed to delete " + file2);
                }
            }
        }

        @Override // d.h0.j.a
        public r e(File file) {
            try {
                return l.a(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return l.a(file);
            }
        }

        @Override // d.h0.j.a
        public boolean f(File file) {
            return file.exists();
        }

        @Override // d.h0.j.a
        public long g(File file) {
            return file.length();
        }

        @Override // d.h0.j.a
        public void a(File file, File file2) throws IOException {
            a(file2);
            if (file.renameTo(file2)) {
                return;
            }
            throw new IOException("failed to rename " + file + " to " + file2);
        }
    }
}
