package d;

import java.io.File;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: RequestBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b0 {

    /* JADX INFO: compiled from: RequestBody.java */
    class a extends b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ v f4248a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ e.f f4249b;

        a(v vVar, e.f fVar) {
            this.f4248a = vVar;
            this.f4249b = fVar;
        }

        @Override // d.b0
        public long contentLength() {
            return this.f4249b.f();
        }

        @Override // d.b0
        public v contentType() {
            return this.f4248a;
        }

        @Override // d.b0
        public void writeTo(e.d dVar) {
            dVar.a(this.f4249b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    class b extends b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ v f4250a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4251b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ byte[] f4252c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f4253d;

        b(v vVar, int i, byte[] bArr, int i2) {
            this.f4250a = vVar;
            this.f4251b = i;
            this.f4252c = bArr;
            this.f4253d = i2;
        }

        @Override // d.b0
        public long contentLength() {
            return this.f4251b;
        }

        @Override // d.b0
        public v contentType() {
            return this.f4250a;
        }

        @Override // d.b0
        public void writeTo(e.d dVar) {
            dVar.write(this.f4252c, this.f4253d, this.f4251b);
        }
    }

    /* JADX INFO: compiled from: RequestBody.java */
    class c extends b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ v f4254a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ File f4255b;

        c(v vVar, File file) {
            this.f4254a = vVar;
            this.f4255b = file;
        }

        @Override // d.b0
        public long contentLength() {
            return this.f4255b.length();
        }

        @Override // d.b0
        public v contentType() {
            return this.f4254a;
        }

        @Override // d.b0
        public void writeTo(e.d dVar) {
            e.s sVarC = null;
            try {
                sVarC = e.l.c(this.f4255b);
                dVar.a(sVarC);
            } finally {
                d.h0.c.a(sVarC);
            }
        }
    }

    public static b0 create(v vVar, String str) {
        Charset charsetA = d.h0.c.i;
        if (vVar != null && (charsetA = vVar.a()) == null) {
            charsetA = d.h0.c.i;
            vVar = v.b(vVar + "; charset=utf-8");
        }
        return create(vVar, str.getBytes(charsetA));
    }

    public long contentLength() {
        return -1L;
    }

    public abstract v contentType();

    public abstract void writeTo(e.d dVar);

    public static b0 create(v vVar, e.f fVar) {
        return new a(vVar, fVar);
    }

    public static b0 create(v vVar, byte[] bArr) {
        return create(vVar, bArr, 0, bArr.length);
    }

    public static b0 create(v vVar, byte[] bArr, int i, int i2) {
        if (bArr != null) {
            d.h0.c.a(bArr.length, i, i2);
            return new b(vVar, i2, bArr, i);
        }
        throw new NullPointerException("content == null");
    }

    public static b0 create(v vVar, File file) {
        if (file != null) {
            return new c(vVar, file);
        }
        throw new NullPointerException("content == null");
    }
}
