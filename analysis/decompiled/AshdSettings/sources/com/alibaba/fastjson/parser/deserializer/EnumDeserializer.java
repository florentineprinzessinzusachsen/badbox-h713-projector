package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class EnumDeserializer implements ObjectDeserializer {
    protected final Class<?> enumClass;
    protected long[] enumNameHashCodes;
    protected final Enum[] enums;
    protected final Enum[] ordinalEnums;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    public EnumDeserializer(Class<?> cls) {
        JSONField jSONField;
        this.enumClass = cls;
        this.ordinalEnums = (Enum[]) cls.getEnumConstants();
        HashMap map = new HashMap();
        int i = 0;
        while (i < this.ordinalEnums.length) {
            Enum r5 = this.ordinalEnums[i];
            String strName = r5.name();
            try {
                jSONField = (JSONField) cls.getField(strName).getAnnotation(JSONField.class);
                if (jSONField != null) {
                    try {
                        String strName2 = jSONField.name();
                        if (strName2 != null && strName2.length() > 0) {
                            strName = strName2;
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception unused2) {
                jSONField = null;
            }
            int i2 = 0;
            long j = -3750763034362895579L;
            long j2 = -3750763034362895579L;
            while (i2 < strName.length()) {
                int iCharAt = strName.charAt(i2);
                long j3 = ((long) iCharAt) ^ j;
                if (iCharAt >= 65 && iCharAt <= 90) {
                    iCharAt += 32;
                }
                j2 = (((long) iCharAt) ^ j2) * 1099511628211L;
                i2++;
                j = j3 * 1099511628211L;
            }
            map.put(Long.valueOf(j), r5);
            if (j != j2) {
                map.put(Long.valueOf(j2), r5);
            }
            if (jSONField != null) {
                String[] strArrAlternateNames = jSONField.alternateNames();
                int length = strArrAlternateNames.length;
                int i3 = 0;
                while (i3 < length) {
                    String str = strArrAlternateNames[i3];
                    int i4 = i;
                    long jCharAt = -3750763034362895579L;
                    int i5 = 0;
                    while (i5 < str.length()) {
                        jCharAt = (jCharAt ^ ((long) str.charAt(i5))) * 1099511628211L;
                        i5++;
                        strArrAlternateNames = strArrAlternateNames;
                        length = length;
                    }
                    String[] strArr = strArrAlternateNames;
                    int i6 = length;
                    if (jCharAt != j && jCharAt != j2) {
                        map.put(Long.valueOf(jCharAt), r5);
                    }
                    i3++;
                    i = i4;
                    strArrAlternateNames = strArr;
                    length = i6;
                }
            }
            i++;
        }
        this.enumNameHashCodes = new long[map.size()];
        Iterator it = map.keySet().iterator();
        int i7 = 0;
        while (it.hasNext()) {
            this.enumNameHashCodes[i7] = ((Long) it.next()).longValue();
            i7++;
        }
        Arrays.sort(this.enumNameHashCodes);
        this.enums = new Enum[this.enumNameHashCodes.length];
        for (int i8 = 0; i8 < this.enumNameHashCodes.length; i8++) {
            this.enums[i8] = (Enum) map.get(Long.valueOf(this.enumNameHashCodes[i8]));
        }
    }

    public Enum getEnumByHashCode(long j) {
        int iBinarySearch;
        if (this.enums != null && (iBinarySearch = Arrays.binarySearch(this.enumNameHashCodes, j)) >= 0) {
            return this.enums[iBinarySearch];
        }
        return null;
    }

    public Enum<?> valueOf(int i) {
        return this.ordinalEnums[i];
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        try {
            JSONLexer jSONLexer = defaultJSONParser.lexer;
            int i = jSONLexer.token();
            if (i == 2) {
                int iIntValue = jSONLexer.intValue();
                jSONLexer.nextToken(16);
                if (iIntValue >= 0 && iIntValue <= this.ordinalEnums.length) {
                    return (T) this.ordinalEnums[iIntValue];
                }
                throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + iIntValue);
            }
            if (i == 4) {
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (strStringVal.length() == 0) {
                    return null;
                }
                long jCharAt = -3750763034362895579L;
                for (int i2 = 0; i2 < strStringVal.length(); i2++) {
                    jCharAt = (jCharAt ^ ((long) strStringVal.charAt(i2))) * 1099511628211L;
                }
                return (T) getEnumByHashCode(jCharAt);
            }
            if (i == 8) {
                jSONLexer.nextToken(16);
                return null;
            }
            throw new JSONException("parse enum " + this.enumClass.getName() + " error, value : " + defaultJSONParser.parse());
        } catch (JSONException e) {
            throw e;
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }
}
