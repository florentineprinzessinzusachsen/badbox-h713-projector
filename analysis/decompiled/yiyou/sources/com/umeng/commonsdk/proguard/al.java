package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TProtocolUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class al {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f3927a = Integer.MAX_VALUE;

    public static void a(int i) {
        f3927a = i;
    }

    public static void a(ai aiVar, byte b2) {
        a(aiVar, b2, f3927a);
    }

    public static void a(ai aiVar, byte b2, int i) throws p {
        if (i > 0) {
            int i2 = 0;
            switch (b2) {
                case 2:
                    aiVar.t();
                    return;
                case 3:
                    aiVar.u();
                    return;
                case 4:
                    aiVar.y();
                    return;
                case 5:
                case 7:
                case 9:
                default:
                    return;
                case 6:
                    aiVar.v();
                    return;
                case 8:
                    aiVar.w();
                    return;
                case 10:
                    aiVar.x();
                    return;
                case 11:
                    aiVar.A();
                    return;
                case 12:
                    aiVar.j();
                    while (true) {
                        byte b3 = aiVar.l().f3907b;
                        if (b3 == 0) {
                            aiVar.k();
                            return;
                        } else {
                            a(aiVar, b3, i - 1);
                            aiVar.m();
                        }
                    }
                    break;
                case 13:
                    af afVarN = aiVar.n();
                    while (i2 < afVarN.f3913c) {
                        int i3 = i - 1;
                        a(aiVar, afVarN.f3911a, i3);
                        a(aiVar, afVarN.f3912b, i3);
                        i2++;
                    }
                    aiVar.o();
                    return;
                case 14:
                    am amVarR = aiVar.r();
                    while (i2 < amVarR.f3929b) {
                        a(aiVar, amVarR.f3928a, i - 1);
                        i2++;
                    }
                    aiVar.s();
                    return;
                case 15:
                    ae aeVarP = aiVar.p();
                    while (i2 < aeVarP.f3910b) {
                        a(aiVar, aeVarP.f3909a, i - 1);
                        i2++;
                    }
                    aiVar.q();
                    return;
            }
        } else {
            throw new p("Maximum skip depth exceeded");
        }
    }

    public static ak a(byte[] bArr, ak akVar) {
        if (bArr[0] > 16) {
            return new ac.a();
        }
        return (bArr.length <= 1 || (bArr[1] & 128) == 0) ? akVar : new ac.a();
    }
}
