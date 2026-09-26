package d0;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f460a;

    public i(int i4) {
        switch (i4) {
            case 1:
                this.f460a = new LinkedHashMap();
                break;
            case 2:
                this.f460a = new LinkedHashMap();
                break;
            default:
                this.f460a = new LinkedHashMap();
                break;
        }
    }

    public void a(t.a aVar) {
        j2.i.e(aVar, "migration");
        int i4 = aVar.f2149a;
        int i5 = aVar.f2150b;
        Integer numValueOf = Integer.valueOf(i4);
        LinkedHashMap linkedHashMap = this.f460a;
        Object treeMap = linkedHashMap.get(numValueOf);
        if (treeMap == null) {
            treeMap = new TreeMap();
            linkedHashMap.put(numValueOf, treeMap);
        }
        TreeMap treeMap2 = (TreeMap) treeMap;
        if (treeMap2.containsKey(Integer.valueOf(i5))) {
            Log.w("ROOM", "Overriding migration " + treeMap2.get(Integer.valueOf(i5)) + " with " + aVar);
        }
        treeMap2.put(Integer.valueOf(i5), aVar);
    }

    public void b(HashMap map) {
        Object[] objArr;
        j2.i.e(map, "values");
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            j2.i.e(str, "key");
            if (value == null) {
                value = null;
            } else {
                j2.e eVarA = j2.o.a(value.getClass());
                if (!eVarA.equals(j2.o.a(Boolean.TYPE)) && !eVarA.equals(j2.o.a(Byte.TYPE)) && !eVarA.equals(j2.o.a(Integer.TYPE)) && !eVarA.equals(j2.o.a(Long.TYPE)) && !eVarA.equals(j2.o.a(Float.TYPE)) && !eVarA.equals(j2.o.a(Double.TYPE)) && !eVarA.equals(j2.o.a(String.class)) && !eVarA.equals(j2.o.a(Boolean[].class)) && !eVarA.equals(j2.o.a(Byte[].class)) && !eVarA.equals(j2.o.a(Integer[].class)) && !eVarA.equals(j2.o.a(Long[].class)) && !eVarA.equals(j2.o.a(Float[].class)) && !eVarA.equals(j2.o.a(Double[].class)) && !eVarA.equals(j2.o.a(String[].class))) {
                    int i4 = 0;
                    if (eVarA.equals(j2.o.a(boolean[].class))) {
                        boolean[] zArr = (boolean[]) value;
                        String str2 = k.f466a;
                        int length = zArr.length;
                        objArr = new Boolean[length];
                        while (i4 < length) {
                            objArr[i4] = Boolean.valueOf(zArr[i4]);
                            i4++;
                        }
                    } else if (eVarA.equals(j2.o.a(byte[].class))) {
                        byte[] bArr = (byte[]) value;
                        String str3 = k.f466a;
                        int length2 = bArr.length;
                        objArr = new Byte[length2];
                        while (i4 < length2) {
                            objArr[i4] = Byte.valueOf(bArr[i4]);
                            i4++;
                        }
                    } else if (eVarA.equals(j2.o.a(int[].class))) {
                        int[] iArr = (int[]) value;
                        String str4 = k.f466a;
                        int length3 = iArr.length;
                        objArr = new Integer[length3];
                        while (i4 < length3) {
                            objArr[i4] = Integer.valueOf(iArr[i4]);
                            i4++;
                        }
                    } else if (eVarA.equals(j2.o.a(long[].class))) {
                        long[] jArr = (long[]) value;
                        String str5 = k.f466a;
                        int length4 = jArr.length;
                        objArr = new Long[length4];
                        while (i4 < length4) {
                            objArr[i4] = Long.valueOf(jArr[i4]);
                            i4++;
                        }
                    } else if (eVarA.equals(j2.o.a(float[].class))) {
                        float[] fArr = (float[]) value;
                        String str6 = k.f466a;
                        int length5 = fArr.length;
                        objArr = new Float[length5];
                        while (i4 < length5) {
                            objArr[i4] = Float.valueOf(fArr[i4]);
                            i4++;
                        }
                    } else {
                        if (!eVarA.equals(j2.o.a(double[].class))) {
                            throw new IllegalArgumentException("Key " + str + " has invalid type " + eVarA);
                        }
                        double[] dArr = (double[]) value;
                        String str7 = k.f466a;
                        int length6 = dArr.length;
                        objArr = new Double[length6];
                        while (i4 < length6) {
                            objArr[i4] = Double.valueOf(dArr[i4]);
                            i4++;
                        }
                    }
                    value = objArr;
                }
            }
            this.f460a.put(str, value);
        }
    }

    public e0.l c(l0.k kVar) {
        j2.i.e(kVar, "id");
        return (e0.l) this.f460a.remove(kVar);
    }

    public List d(String str) {
        j2.i.e(str, "workSpecId");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = this.f460a;
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            if (j2.i.a(((l0.k) entry.getKey()).f1321a, str)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Iterator it = linkedHashMap.keySet().iterator();
        while (it.hasNext()) {
            linkedHashMap2.remove((l0.k) it.next());
        }
        return v1.j.G0(linkedHashMap.values());
    }

    public e0.l e(l0.k kVar) {
        LinkedHashMap linkedHashMap = this.f460a;
        Object lVar = linkedHashMap.get(kVar);
        if (lVar == null) {
            lVar = new e0.l(kVar);
            linkedHashMap.put(kVar, lVar);
        }
        return (e0.l) lVar;
    }
}
