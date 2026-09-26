package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MapSerializer extends SerializeFilterable implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();
    private static final int NON_STRINGKEY_AS_STRING = SerializerFeature.of(new SerializerFeature[]{SerializerFeature.BrowserCompatible, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.BrowserSecure});

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Code duplicated, block: B:103:0x015b A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:105:0x015f A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:108:0x0167 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:118:0x0186 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:11:0x0022 A[PHI: r1
      0x0022: PHI (r1v68 java.util.Map<java.lang.String, java.lang.Object>) = 
      (r1v2 java.util.Map<java.lang.String, java.lang.Object>)
      (r1v2 java.util.Map<java.lang.String, java.lang.Object>)
      (r1v2 java.util.Map<java.lang.String, java.lang.Object>)
      (r1v1 java.util.Map<java.lang.String, java.lang.Object>)
     binds: [B:16:0x0030, B:18:0x0034, B:219:0x0022, B:9:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:120:0x018f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0195 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:126:0x019d A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x01b9 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x01c3 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:140:0x01cb A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x01e7 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:151:0x01f0 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:164:0x022e A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:166:0x024a A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:172:0x0261 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:174:0x0266 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:177:0x0271 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:179:0x0279  */
    /* JADX WARN: Code duplicated, block: B:181:0x027c A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:184:0x0287 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:187:0x0293 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:190:0x029d A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:192:0x02a6 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:194:0x02ac A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:195:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:198:0x02c0 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:205:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:207:0x02e5 A[Catch: all -> 0x0056, TRY_LEAVE, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x0094  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f3 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x00f7 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:85:0x0127 A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x012b A[Catch: all -> 0x0056, TryCatch #0 {all -> 0x0056, blocks: (B:28:0x0052, B:31:0x005a, B:33:0x0065, B:44:0x0084, B:46:0x0095, B:47:0x00a5, B:49:0x00ab, B:51:0x00bd, B:54:0x00c5, B:57:0x00ca, B:59:0x00d4, B:61:0x00d8, B:64:0x00e3, B:67:0x00f3, B:69:0x00f7, B:72:0x00ff, B:75:0x0104, B:77:0x010e, B:79:0x0112, B:82:0x011d, B:85:0x0127, B:87:0x012b, B:90:0x0133, B:93:0x0138, B:95:0x0142, B:97:0x0146, B:100:0x0151, B:103:0x015b, B:105:0x015f, B:108:0x0167, B:111:0x016c, B:113:0x0176, B:115:0x017a, B:118:0x0186, B:121:0x0191, B:123:0x0195, B:126:0x019d, B:129:0x01a2, B:131:0x01ac, B:133:0x01b0, B:134:0x01b9, B:135:0x01bf, B:137:0x01c3, B:140:0x01cb, B:143:0x01d0, B:145:0x01da, B:147:0x01de, B:148:0x01e7, B:151:0x01f0, B:154:0x01f5, B:156:0x01f9, B:162:0x0203, B:166:0x024a, B:170:0x025b, B:172:0x0261, B:174:0x0266, B:175:0x0269, B:177:0x0271, B:178:0x0274, B:190:0x029d, B:192:0x02a6, B:194:0x02ac, B:196:0x02b8, B:198:0x02c0, B:200:0x02c4, B:202:0x02c8, B:204:0x02d3, B:206:0x02d9, B:207:0x02e5, B:181:0x027c, B:182:0x027f, B:184:0x0287, B:186:0x028b, B:188:0x0296, B:187:0x0293, B:164:0x022e, B:39:0x0079), top: B:220:0x0052 }] */
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i, boolean z) throws IOException {
        Map<String, Object> treeMap;
        boolean z2;
        List<PropertyPreFilter> list;
        List<PropertyFilter> list2;
        List<PropertyFilter> list3;
        List<NameFilter> list4;
        List<NameFilter> list5;
        String str;
        String str2;
        Object objProcessValue;
        char c;
        Class<?> cls;
        Class<?> cls2;
        ObjectSerializer objectWriter;
        Type type2;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Map<String, Object> innerMap = (Map) obj;
        int i2 = SerializerFeature.MapSortField.mask;
        if ((serializeWriter.features & i2) == 0 && (i2 & i) == 0) {
            treeMap = innerMap;
        } else {
            if (innerMap instanceof JSONObject) {
                innerMap = ((JSONObject) innerMap).getInnerMap();
            }
            if ((innerMap instanceof SortedMap) || (innerMap instanceof LinkedHashMap)) {
                treeMap = innerMap;
            } else {
                try {
                    treeMap = new TreeMap(innerMap);
                } catch (Exception unused) {
                    treeMap = innerMap;
                }
            }
        }
        if (jSONSerializer.containsReference(obj)) {
            jSONSerializer.writeReference(obj);
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        boolean z3 = false;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        if (!z) {
            try {
                serializeWriter.write(123);
            } catch (Throwable th) {
                jSONSerializer.context = serialContext;
                throw th;
            }
        }
        jSONSerializer.incrementIndent();
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
            String str3 = jSONSerializer.config.typeKey;
            Class<?> cls3 = treeMap.getClass();
            if ((cls3 == JSONObject.class || cls3 == HashMap.class || cls3 == LinkedHashMap.class) && treeMap.containsKey(str3)) {
                z2 = true;
            } else {
                serializeWriter.writeFieldName(str3);
                serializeWriter.writeString(obj.getClass().getName());
                z2 = false;
            }
        } else {
            z2 = true;
        }
        Iterator<Map.Entry<String, Object>> it = treeMap.entrySet().iterator();
        boolean z4 = z2;
        Class<?> cls4 = null;
        ObjectSerializer objectSerializer = null;
        while (it.hasNext()) {
            Map.Entry<String, Object> next = it.next();
            Object value = next.getValue();
            String key = next.getKey();
            List<PropertyPreFilter> list6 = jSONSerializer.propertyPreFilters;
            if (list6 != null && list6.size() > 0) {
                if (key == null || (key instanceof String)) {
                    if (applyName(jSONSerializer, obj, key)) {
                        list = this.propertyPreFilters;
                        if (list == null) {
                            list2 = jSONSerializer.propertyFilters;
                            if (list2 == null) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str4 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str4, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            } else {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str5 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str5, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            }
                        } else {
                            list2 = jSONSerializer.propertyFilters;
                            if (list2 == null) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str6 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str6, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            } else {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str7 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str7, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            }
                        }
                    }
                } else if ((!key.getClass().isPrimitive() && !(key instanceof Number)) || applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                    list = this.propertyPreFilters;
                    if (list == null) {
                        list2 = jSONSerializer.propertyFilters;
                        if (list2 == null) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str8 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str8, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        } else {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str9 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str9, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        }
                    } else {
                        list2 = jSONSerializer.propertyFilters;
                        if (list2 == null) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str10 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str10, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        } else {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str11 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str11, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        }
                    }
                }
                cls4 = cls4;
                it = it;
                z3 = z3;
                cls4 = cls4;
                z3 = z3;
            } else {
                list = this.propertyPreFilters;
                if (list == null && list.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        if (applyName(jSONSerializer, obj, key)) {
                            list2 = jSONSerializer.propertyFilters;
                            if (list2 == null) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str12 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str12, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            } else {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str13 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str13, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            }
                        }
                    } else if ((!key.getClass().isPrimitive() && !(key instanceof Number)) || applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                        list2 = jSONSerializer.propertyFilters;
                        if (list2 == null) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str14 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str14, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        } else {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str15 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str15, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        }
                    }
                    cls4 = cls4;
                    it = it;
                    z3 = z3;
                    cls4 = cls4;
                    z3 = z3;
                } else {
                    list2 = jSONSerializer.propertyFilters;
                    if (list2 == null && list2.size() > 0) {
                        if (key == null || (key instanceof String)) {
                            if (apply(jSONSerializer, obj, key, value)) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                                }
                                if (objProcessValue == null) {
                                }
                                if (str2 instanceof String) {
                                    String str16 = str2;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    c = 1;
                                    serializeWriter.writeFieldName(str16, true);
                                } else {
                                    c = 1;
                                    if (!z4) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (objProcessValue == null) {
                                    serializeWriter.writeNull();
                                    cls4 = cls4;
                                } else {
                                    cls = objProcessValue.getClass();
                                    if (cls != cls4) {
                                        cls2 = cls;
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                    } else {
                                        cls2 = cls4;
                                        objectWriter = objectSerializer;
                                    }
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    } else {
                                        objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                    }
                                    objectSerializer = objectWriter;
                                    cls4 = cls2;
                                }
                                z3 = z3;
                                z4 = z3;
                            }
                        } else if ((!key.getClass().isPrimitive() && !(key instanceof Number)) || apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                            }
                            if (objProcessValue == null) {
                            }
                            if (str2 instanceof String) {
                                String str17 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str17, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        }
                        cls4 = cls4;
                        it = it;
                        z3 = z3;
                        cls4 = cls4;
                        z3 = z3;
                    } else {
                        list3 = this.propertyFilters;
                        if (list3 != null && list3.size() > 0) {
                            if (key != null || (key instanceof String)) {
                                if (!apply(jSONSerializer, obj, key, value)) {
                                    cls4 = cls4;
                                    it = it;
                                    z3 = z3;
                                    cls4 = cls4;
                                    z3 = z3;
                                }
                            } else if ((key.getClass().isPrimitive() || (key instanceof Number)) && !apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                                cls4 = cls4;
                                it = it;
                                z3 = z3;
                                cls4 = cls4;
                                z3 = z3;
                            }
                        }
                        list4 = jSONSerializer.nameFilters;
                        if (list4 != null && list4.size() > 0) {
                            if (key != null || (key instanceof String)) {
                                key = processKey(jSONSerializer, obj, key, value);
                            } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                                key = processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                            }
                        }
                        list5 = this.nameFilters;
                        if (list5 != null && list5.size() > 0) {
                            if (key != null || (key instanceof String)) {
                                key = processKey(jSONSerializer, obj, key, value);
                            } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                                key = processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                            }
                        }
                        str = key;
                        if (str != null || (str instanceof String)) {
                            str2 = str;
                            objProcessValue = processValue(jSONSerializer, null, obj, str, value);
                        } else {
                            if (((str instanceof Map) || (str instanceof Collection)) ? true : z3) {
                                str2 = str;
                                objProcessValue = value;
                            } else {
                                objProcessValue = processValue(jSONSerializer, null, obj, JSON.toJSONString(str), value);
                                str2 = str;
                            }
                        }
                        if (objProcessValue == null || serializeWriter.isEnabled(SerializerFeature.WriteMapNullValue)) {
                            if (str2 instanceof String) {
                                String str18 = str2;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                c = 1;
                                serializeWriter.writeFieldName(str18, true);
                            } else {
                                c = 1;
                                if (!z4) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING) && !(str2 instanceof Enum)) {
                                    jSONSerializer.write(JSON.toJSONString(str2));
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (objProcessValue == null) {
                                serializeWriter.writeNull();
                                cls4 = cls4;
                            } else {
                                cls = objProcessValue.getClass();
                                if (cls != cls4) {
                                    cls2 = cls;
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                } else {
                                    cls2 = cls4;
                                    objectWriter = objectSerializer;
                                }
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName) && (objectWriter instanceof JavaBeanSerializer)) {
                                    if (type instanceof ParameterizedType) {
                                        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                                        if (actualTypeArguments.length == 2) {
                                            type2 = actualTypeArguments[c];
                                        } else {
                                            type2 = null;
                                        }
                                    } else {
                                        type2 = null;
                                    }
                                    ((JavaBeanSerializer) objectWriter).writeNoneASM(jSONSerializer, objProcessValue, str2, type2, i);
                                } else {
                                    objectWriter.write(jSONSerializer, objProcessValue, str2, null, i);
                                }
                                objectSerializer = objectWriter;
                                cls4 = cls2;
                            }
                            z3 = z3;
                            z4 = z3;
                        } else {
                            cls4 = cls4;
                            z3 = z3;
                        }
                    }
                }
            }
            it = it;
        }
        jSONSerializer.context = serialContext;
        jSONSerializer.decrementIdent();
        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat) && treeMap.size() > 0) {
            jSONSerializer.println();
        }
        if (z) {
            return;
        }
        serializeWriter.write(125);
    }
}
