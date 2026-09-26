package com.umeng.analytics.filter;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: SmartDict.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3575b = "Ă";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MessageDigest f3577c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3579e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3576a = "MD5";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Set<Object> f3578d = new HashSet();

    public d(boolean z, String str) {
        int i = 0;
        this.f3579e = false;
        this.f3579e = z;
        try {
            this.f3577c = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        if (str != null) {
            if (!z) {
                String[] strArrSplit = str.split(f3575b);
                int length = strArrSplit.length;
                while (i < length) {
                    this.f3578d.add(strArrSplit[i]);
                    i++;
                }
                return;
            }
            try {
                byte[] bArrDecode = Base64.decode(str.getBytes(), 0);
                while (i < bArrDecode.length / 4) {
                    int i2 = i * 4;
                    this.f3578d.add(Integer.valueOf(((bArrDecode[i2 + 0] & 255) << 24) + ((bArrDecode[i2 + 1] & 255) << 16) + ((bArrDecode[i2 + 2] & 255) << 8) + (bArrDecode[i2 + 3] & 255)));
                    i++;
                }
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
        }
    }

    private Integer c(String str) {
        try {
            this.f3577c.update(str.getBytes());
            byte[] bArrDigest = this.f3577c.digest();
            return Integer.valueOf(((bArrDigest[0] & 255) << 24) + ((bArrDigest[1] & 255) << 16) + ((bArrDigest[2] & 255) << 8) + (bArrDigest[3] & 255));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean a(String str) {
        return this.f3579e ? this.f3578d.contains(c(str)) : this.f3578d.contains(str);
    }

    public void b(String str) {
        if (this.f3579e) {
            this.f3578d.add(c(str));
        } else {
            this.f3578d.add(str);
        }
    }

    public String toString() {
        if (!this.f3579e) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : this.f3578d) {
                if (sb.length() > 0) {
                    sb.append(f3575b);
                }
                sb.append(obj.toString());
            }
            return sb.toString();
        }
        byte[] bArr = new byte[this.f3578d.size() * 4];
        Iterator<Object> it = this.f3578d.iterator();
        int i = 0;
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            int i2 = i + 1;
            bArr[i] = (byte) (((-16777216) & iIntValue) >> 24);
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((16711680 & iIntValue) >> 16);
            int i4 = i3 + 1;
            bArr[i3] = (byte) ((65280 & iIntValue) >> 8);
            i = i4 + 1;
            bArr[i4] = (byte) (iIntValue & 255);
        }
        return new String(Base64.encode(bArr, 0));
    }

    public void a() {
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = this.f3578d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (sb.length() > 0) {
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }
}
