package com.hs.p.dx;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class NumUtils {
    public static int bytes2int(byte[] bArr) {
        return ((bArr[3] << 24) & (-16777216)) | 0 | (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680);
    }

    public static long bytes2long(byte[] bArr) {
        return (((long) bArr[0]) & 255) | 0 | ((((long) bArr[1]) << 8) & 65280) | ((((long) bArr[2]) << 16) & 16711680) | ((((long) bArr[3]) << 24) & 4278190080L) | ((((long) bArr[4]) << 32) & 1095216660480L) | ((((long) bArr[5]) << 40) & 280375465082880L) | ((((long) bArr[6]) << 48) & 71776119061217280L) | ((((long) bArr[7]) << 56) & (-72057594037927936L));
    }

    public static short bytes2short(byte[] bArr) {
        return (short) (((short) ((bArr[1] << 8) & 65280)) | ((short) (0 | ((short) (bArr[0] & 255)))));
    }

    public static byte[] int2bytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    public static byte[] long2bytes(long j) {
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 56) & 255)};
    }

    public static byte[] short2bytes(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)};
    }
}
