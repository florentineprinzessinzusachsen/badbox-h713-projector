package com.baidu.mobstat;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'a' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k f3508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k f3509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k f3510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k f3511d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k f3512e;
    private static final /* synthetic */ k[] g;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3513f;

    static {
        int i = 0;
        f3508a = new k("AP_LIST", i, i) { // from class: com.baidu.mobstat.k.1
            @Override // com.baidu.mobstat.k
            public j a() {
                return new n();
            }
        };
        int i2 = 1;
        f3509b = new k("APP_LIST", i2, i2) { // from class: com.baidu.mobstat.k.2
            @Override // com.baidu.mobstat.k
            public j a() {
                return new q();
            }
        };
        int i3 = 2;
        f3510c = new k("APP_TRACE", i3, i3) { // from class: com.baidu.mobstat.k.3
            @Override // com.baidu.mobstat.k
            public j a() {
                return new r();
            }
        };
        int i4 = 3;
        f3511d = new k("APP_CHANGE", i4, i4) { // from class: com.baidu.mobstat.k.4
            @Override // com.baidu.mobstat.k
            public j a() {
                return new p();
            }
        };
        int i5 = 4;
        f3512e = new k("APP_APK", i5, i5) { // from class: com.baidu.mobstat.k.5
            @Override // com.baidu.mobstat.k
            public j a() {
                return new o();
            }
        };
        g = new k[]{f3508a, f3509b, f3510c, f3511d, f3512e};
    }

    private int c() {
        j jVarA = null;
        try {
            jVarA = a();
            if (jVarA.a()) {
                return jVarA.b();
            }
            if (jVarA == null) {
                return 0;
            }
        } catch (Exception e2) {
            al.c().b(e2);
            if (jVarA == null) {
                return 0;
            }
        } finally {
            if (jVarA != null) {
                jVarA.close();
            }
        }
        return 0;
    }

    public static k valueOf(String str) {
        return (k) Enum.valueOf(k.class, str);
    }

    public static k[] values() {
        return (k[]) g.clone();
    }

    public abstract j a();

    public synchronized ArrayList<i> a(int i, int i2) {
        ArrayList<i> arrayList = new ArrayList<>();
        j jVarA = null;
        try {
            try {
                jVarA = a();
                if (!jVarA.a()) {
                    if (jVarA != null) {
                        jVarA.close();
                    }
                    return arrayList;
                }
                arrayList = jVarA.a(i, i2);
                if (jVarA != null) {
                    jVarA.close();
                }
                return arrayList;
            } catch (Exception e2) {
                al.c().b(e2);
                if (jVarA != null) {
                }
                return arrayList;
            }
        } catch (Throwable th) {
            if (jVarA != null) {
                jVarA.close();
            }
            throw th;
        }
    }

    public synchronized boolean b() {
        return c() == 0;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.f3513f);
    }

    private k(String str, int i, int i2) {
        super(str, i);
        this.f3513f = i2;
    }

    public synchronized boolean b(int i) {
        return c() >= i;
    }

    public synchronized long a(long j, String str) {
        long jA = -1;
        j jVarA = null;
        try {
            try {
                jVarA = a();
                if (!jVarA.a()) {
                    if (jVarA != null) {
                        jVarA.close();
                    }
                    return -1L;
                }
                jA = jVarA.a(String.valueOf(j), str);
                if (jVarA != null) {
                    jVarA.close();
                }
                return jA;
            } catch (Exception e2) {
                al.c().b(e2);
                if (jVarA != null) {
                }
                return jA;
            }
        } catch (Throwable th) {
            if (jVarA != null) {
                jVarA.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0044 A[Catch: all -> 0x005e, PHI: r1 r3
      0x0044: PHI (r1v4 com.baidu.mobstat.j) = (r1v5 com.baidu.mobstat.j), (r1v6 com.baidu.mobstat.j) binds: [B:35:0x0053, B:27:0x0042] A[DONT_GENERATE, DONT_INLINE]
      0x0044: PHI (r3v1 int) = (r3v3 int), (r3v5 int) binds: [B:35:0x0053, B:27:0x0042] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #3 {, blocks: (B:5:0x0004, B:12:0x0018, B:21:0x0036, B:28:0x0044, B:40:0x005a, B:41:0x005d, B:9:0x000c, B:15:0x001d, B:18:0x0024, B:34:0x004c), top: B:52:0x0004, inners: #2 }] */
    public synchronized int a(ArrayList<Long> arrayList) {
        int i;
        if (arrayList != null) {
            if (arrayList.size() != 0) {
                j jVarA = null;
                try {
                    try {
                        jVarA = a();
                        if (!jVarA.a()) {
                            if (jVarA != null) {
                                jVarA.close();
                            }
                            return 0;
                        }
                        int size = arrayList.size();
                        i = 0;
                        for (int i2 = 0; i2 < size; i2++) {
                            try {
                                if (!jVarA.b(arrayList.get(i2).longValue())) {
                                    if (jVarA != null) {
                                        jVarA.close();
                                    }
                                    return i;
                                }
                                i++;
                            } catch (Exception e2) {
                                e = e2;
                            }
                        }
                        if (jVarA != null) {
                            jVarA.close();
                        }
                        return i;
                    } catch (Exception e3) {
                        e = e3;
                        i = 0;
                    }
                    if (jVarA != null) {
                        jVarA.close();
                    }
                    return i;
                } catch (Throwable th) {
                    if (jVarA != null) {
                        jVarA.close();
                    }
                    throw th;
                }
                al.c().b(e);
            }
        }
        return 0;
    }

    public synchronized List<String> a(int i) {
        List<String> arrayList;
        arrayList = new ArrayList<>();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        ArrayList<i> arrayList3 = new ArrayList<>();
        a(arrayList, arrayList2, arrayList3, i, 500);
        if (arrayList3.size() != 0 && arrayList.size() == 0 && arrayList2.size() == 0) {
            i iVar = arrayList3.get(0);
            long jA = iVar.a();
            String strB = iVar.b();
            arrayList2.add(Long.valueOf(jA));
            arrayList.add(strB);
        }
        int iA = a(arrayList2);
        if (iA != arrayList.size()) {
            arrayList = arrayList.subList(0, iA);
        }
        return arrayList;
    }

    private int a(List<String> list, ArrayList<Long> arrayList, ArrayList<i> arrayList2, int i, int i2) {
        int iC = c();
        int i3 = i2;
        int i4 = 0;
        int i5 = 0;
        while (iC > 0) {
            if (iC < i3) {
                i3 = iC;
            }
            ArrayList<i> arrayListA = a(i3, i5);
            if (i5 == 0 && arrayListA.size() != 0) {
                arrayList2.add(arrayListA.get(0));
            }
            for (i iVar : arrayListA) {
                long jA = iVar.a();
                String strB = iVar.b();
                int length = strB.length() + i4;
                if (length > i) {
                    break;
                }
                arrayList.add(Long.valueOf(jA));
                list.add(strB);
                i4 = length;
            }
            iC -= i3;
            i5 += i3;
        }
        return i4;
    }
}
