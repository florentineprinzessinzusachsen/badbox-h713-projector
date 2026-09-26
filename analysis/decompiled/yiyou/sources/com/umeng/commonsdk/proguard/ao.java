package com.umeng.commonsdk.proguard;

import java.util.BitSet;

/* JADX INFO: compiled from: TTupleProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public final class ao extends ac {

    /* JADX INFO: compiled from: TTupleProtocol.java */
    public static class a implements ak {
        @Override // com.umeng.commonsdk.proguard.ak
        public ai a(aw awVar) {
            return new ao(awVar);
        }
    }

    public ao(aw awVar) {
        super(awVar);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public Class<? extends aq> D() {
        return at.class;
    }

    public void a(BitSet bitSet, int i) {
        for (byte b2 : b(bitSet, i)) {
            a(b2);
        }
    }

    public BitSet b(int i) {
        double d2 = i;
        Double.isNaN(d2);
        int iCeil = (int) Math.ceil(d2 / 8.0d);
        byte[] bArr = new byte[iCeil];
        for (int i2 = 0; i2 < iCeil; i2++) {
            bArr[i2] = u();
        }
        return a(bArr);
    }

    public static BitSet a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    public static byte[] b(BitSet bitSet, int i) {
        double d2 = i;
        Double.isNaN(d2);
        byte[] bArr = new byte[(int) Math.ceil(d2 / 8.0d)];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) ((1 << (i2 % 8)) | bArr[length]);
            }
        }
        return bArr;
    }
}
