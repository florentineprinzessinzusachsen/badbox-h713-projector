package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: TDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ai f3989a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final av f3990b;

    public m() {
        this(new ac.a());
    }

    private ad j(byte[] bArr, q qVar, q... qVarArr) {
        this.f3990b.a(bArr);
        q[] qVarArr2 = new q[qVarArr.length + 1];
        int i = 0;
        qVarArr2[0] = qVar;
        int i2 = 0;
        while (i2 < qVarArr.length) {
            int i3 = i2 + 1;
            qVarArr2[i3] = qVarArr[i2];
            i2 = i3;
        }
        this.f3989a.j();
        ad adVarL = null;
        while (i < qVarArr2.length) {
            adVarL = this.f3989a.l();
            if (adVarL.f3907b == 0 || adVarL.f3908c > qVarArr2[i].a()) {
                return null;
            }
            if (adVarL.f3908c != qVarArr2[i].a()) {
                al.a(this.f3989a, adVarL.f3907b);
                this.f3989a.m();
            } else {
                i++;
                if (i < qVarArr2.length) {
                    this.f3989a.j();
                }
            }
        }
        return adVarL;
    }

    public void a(j jVar, byte[] bArr) {
        try {
            this.f3990b.a(bArr);
            jVar.read(this.f3989a);
        } finally {
            this.f3990b.e();
            this.f3989a.B();
        }
    }

    public Byte b(byte[] bArr, q qVar, q... qVarArr) {
        return (Byte) a((byte) 3, bArr, qVar, qVarArr);
    }

    public Double c(byte[] bArr, q qVar, q... qVarArr) {
        return (Double) a((byte) 4, bArr, qVar, qVarArr);
    }

    public Short d(byte[] bArr, q qVar, q... qVarArr) {
        return (Short) a((byte) 6, bArr, qVar, qVarArr);
    }

    public Integer e(byte[] bArr, q qVar, q... qVarArr) {
        return (Integer) a((byte) 8, bArr, qVar, qVarArr);
    }

    public Long f(byte[] bArr, q qVar, q... qVarArr) {
        return (Long) a((byte) 10, bArr, qVar, qVarArr);
    }

    public String g(byte[] bArr, q qVar, q... qVarArr) {
        return (String) a((byte) 11, bArr, qVar, qVarArr);
    }

    public ByteBuffer h(byte[] bArr, q qVar, q... qVarArr) {
        return (ByteBuffer) a((byte) 100, bArr, qVar, qVarArr);
    }

    public Short i(byte[] bArr, q qVar, q... qVarArr) {
        Short shValueOf;
        try {
            try {
                if (j(bArr, qVar, qVarArr) != null) {
                    this.f3989a.j();
                    shValueOf = Short.valueOf(this.f3989a.l().f3908c);
                } else {
                    shValueOf = null;
                }
                this.f3990b.e();
                this.f3989a.B();
                return shValueOf;
            } catch (Exception e2) {
                throw new p(e2);
            }
        } catch (Throwable th) {
            this.f3990b.e();
            this.f3989a.B();
            throw th;
        }
    }

    public m(ak akVar) {
        this.f3990b = new av();
        this.f3989a = akVar.a(this.f3990b);
    }

    public void a(j jVar, String str, String str2) {
        try {
            try {
                a(jVar, str.getBytes(str2));
                this.f3989a.B();
            } catch (UnsupportedEncodingException unused) {
                throw new p("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } catch (Throwable th) {
            this.f3989a.B();
            throw th;
        }
    }

    public void a(j jVar, byte[] bArr, q qVar, q... qVarArr) {
        try {
            try {
                if (j(bArr, qVar, qVarArr) != null) {
                    jVar.read(this.f3989a);
                }
                this.f3990b.e();
                this.f3989a.B();
            } catch (Exception e2) {
                throw new p(e2);
            }
        } catch (Throwable th) {
            this.f3990b.e();
            this.f3989a.B();
            throw th;
        }
    }

    public Boolean a(byte[] bArr, q qVar, q... qVarArr) {
        return (Boolean) a((byte) 2, bArr, qVar, qVarArr);
    }

    /* JADX WARN: Code duplicated, block: B:47:0x009e  */
    private Object a(byte b2, byte[] bArr, q qVar, q... qVarArr) {
        Object objValueOf;
        try {
            try {
                ad adVarJ = j(bArr, qVar, qVarArr);
                if (adVarJ == null) {
                    objValueOf = null;
                } else if (b2 != 2) {
                    if (b2 != 3) {
                        if (b2 != 4) {
                            if (b2 != 6) {
                                if (b2 != 8) {
                                    if (b2 != 100) {
                                        if (b2 != 10) {
                                            if (b2 == 11 && adVarJ.f3907b == 11) {
                                                objValueOf = this.f3989a.z();
                                            } else {
                                                objValueOf = null;
                                            }
                                        } else if (adVarJ.f3907b == 10) {
                                            objValueOf = Long.valueOf(this.f3989a.x());
                                        } else {
                                            objValueOf = null;
                                        }
                                    } else if (adVarJ.f3907b == 11) {
                                        objValueOf = this.f3989a.A();
                                    } else {
                                        objValueOf = null;
                                    }
                                } else if (adVarJ.f3907b == 8) {
                                    objValueOf = Integer.valueOf(this.f3989a.w());
                                } else {
                                    objValueOf = null;
                                }
                            } else if (adVarJ.f3907b == 6) {
                                objValueOf = Short.valueOf(this.f3989a.v());
                            } else {
                                objValueOf = null;
                            }
                        } else if (adVarJ.f3907b == 4) {
                            objValueOf = Double.valueOf(this.f3989a.y());
                        } else {
                            objValueOf = null;
                        }
                    } else if (adVarJ.f3907b == 3) {
                        objValueOf = Byte.valueOf(this.f3989a.u());
                    } else {
                        objValueOf = null;
                    }
                } else if (adVarJ.f3907b == 2) {
                    objValueOf = Boolean.valueOf(this.f3989a.t());
                } else {
                    objValueOf = null;
                }
                this.f3990b.e();
                this.f3989a.B();
                return objValueOf;
            } catch (Exception e2) {
                throw new p(e2);
            }
        } catch (Throwable th) {
            this.f3990b.e();
            this.f3989a.B();
            throw th;
        }
    }

    public void a(j jVar, String str) {
        a(jVar, str.getBytes());
    }
}
