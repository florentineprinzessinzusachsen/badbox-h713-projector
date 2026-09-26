package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, str);
        }
        return map;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        boolean z;
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        for (int i = 0; i < this.sortedGetters.length; i++) {
            this.sortedGetters[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
        } else {
            this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.getters.length) {
                    z = false;
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i2].name);
                if (fieldSerializer == null) {
                    z = true;
                    break;
                } else {
                    this.getters[i2] = fieldSerializer;
                    i2++;
                }
            }
            if (z) {
                System.arraycopy(this.sortedGetters, 0, this.getters, 0, this.sortedGetters.length);
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class<? extends SerializeFilter> cls : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class<? extends SerializeFilter> cls2 : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter(cls2.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused2) {
                }
            }
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Code duplicated, block: B:112:0x0157  */
    /* JADX WARN: Code duplicated, block: B:113:0x0163  */
    /* JADX WARN: Code duplicated, block: B:119:0x017b  */
    /* JADX WARN: Code duplicated, block: B:122:0x019e A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:124:0x01a6 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:127:0x01b7 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:138:0x01d5 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:139:0x01db  */
    /* JADX WARN: Code duplicated, block: B:141:0x01df A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:152:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:153:0x0201 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:155:0x0209 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:166:0x0227 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:167:0x022c A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:169:0x0234 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:180:0x0252 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:181:0x0257  */
    /* JADX WARN: Code duplicated, block: B:187:0x0269 A[DONT_INVERT, PHI: r1
      0x0269: PHI (r1v46 java.lang.Object) = 
      (r1v45 java.lang.Object)
      (r1v45 java.lang.Object)
      (r1v45 java.lang.Object)
      (r1v45 java.lang.Object)
      (r1v56 java.lang.Object)
      (r1v45 java.lang.Object)
      (r1v57 java.lang.Object)
      (r1v45 java.lang.Object)
      (r1v58 java.lang.Object)
      (r1v45 java.lang.Object)
      (r1v59 java.lang.Object)
      (r1v45 java.lang.Object)
     binds: [B:121:0x019c, B:181:0x0257, B:183:0x025b, B:185:0x0265, B:180:0x0252, B:179:0x0250, B:166:0x0227, B:165:0x0225, B:152:0x01fd, B:151:0x01fb, B:138:0x01d5, B:137:0x01d3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:188:0x026b A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:243:0x0311 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:244:0x0313 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:246:0x0317 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:253:0x0336 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:254:0x033a  */
    /* JADX WARN: Code duplicated, block: B:257:0x0340 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:258:0x0342 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:259:0x0347  */
    /* JADX WARN: Code duplicated, block: B:261:0x034d  */
    /* JADX WARN: Code duplicated, block: B:263:0x0352 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:264:0x0354 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:266:0x035c  */
    /* JADX WARN: Code duplicated, block: B:267:0x035e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:271:0x0366 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:272:0x036f A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:274:0x0374 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:276:0x037c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:291:0x03b4 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:293:0x03b8 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:299:0x03cc A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:302:0x03d3 A[Catch: Exception -> 0x040b, all -> 0x0461, TryCatch #2 {all -> 0x0461, blocks: (B:77:0x00ef, B:80:0x00f7, B:86:0x010c, B:88:0x0112, B:94:0x0120, B:96:0x0126, B:98:0x0130, B:110:0x0151, B:115:0x0167, B:118:0x0173, B:120:0x017c, B:122:0x019e, B:124:0x01a6, B:127:0x01b7, B:129:0x01c2, B:131:0x01c6, B:134:0x01cd, B:136:0x01d0, B:138:0x01d5, B:141:0x01df, B:143:0x01ea, B:145:0x01ee, B:148:0x01f5, B:150:0x01f8, B:153:0x0201, B:155:0x0209, B:157:0x0214, B:159:0x0218, B:162:0x021f, B:164:0x0222, B:166:0x0227, B:167:0x022c, B:169:0x0234, B:171:0x023f, B:173:0x0243, B:176:0x024a, B:178:0x024d, B:180:0x0252, B:182:0x0259, B:184:0x025d, B:188:0x026b, B:190:0x026f, B:192:0x0278, B:194:0x0283, B:196:0x0289, B:198:0x028d, B:201:0x0298, B:203:0x029c, B:205:0x02a0, B:208:0x02ab, B:210:0x02af, B:212:0x02b3, B:215:0x02be, B:217:0x02c2, B:219:0x02c6, B:222:0x02d4, B:224:0x02d8, B:226:0x02dc, B:229:0x02e9, B:231:0x02ed, B:233:0x02f1, B:236:0x02ff, B:238:0x0303, B:240:0x0307, B:244:0x0313, B:246:0x0317, B:248:0x031b, B:251:0x0329, B:253:0x0336, B:258:0x0342, B:260:0x0348, B:300:0x03cf, B:302:0x03d3, B:304:0x03d7, B:307:0x03e1, B:309:0x03e9, B:310:0x03f1, B:312:0x03f7, B:264:0x0354, B:265:0x0357, B:268:0x0360, B:271:0x0366, B:272:0x036f, B:274:0x0374, B:277:0x037e, B:280:0x0388, B:282:0x0391, B:285:0x039b, B:286:0x039f, B:287:0x03a5, B:289:0x03ac, B:290:0x03b0, B:291:0x03b4, B:293:0x03b8, B:295:0x03bc, B:298:0x03c8, B:299:0x03cc, B:104:0x013e, B:107:0x0146, B:325:0x0414, B:339:0x043f, B:341:0x0447, B:343:0x044f, B:345:0x0457), top: B:396:0x00ef }] */
    /* JADX WARN: Code duplicated, block: B:318:0x0403  */
    /* JADX WARN: Code duplicated, block: B:320:0x0406  */
    /* JADX WARN: Code duplicated, block: B:366:0x04a1 A[Catch: all -> 0x04b6, TRY_ENTER, TryCatch #9 {all -> 0x04b6, blocks: (B:366:0x04a1, B:376:0x04f8, B:378:0x04fe, B:379:0x0516, B:381:0x051a, B:385:0x0523, B:386:0x0528, B:370:0x04bb, B:372:0x04bf, B:374:0x04c5, B:375:0x04e0), top: B:408:0x049f }] */
    /* JADX WARN: Code duplicated, block: B:369:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:370:0x04bb A[Catch: all -> 0x04b6, TryCatch #9 {all -> 0x04b6, blocks: (B:366:0x04a1, B:376:0x04f8, B:378:0x04fe, B:379:0x0516, B:381:0x051a, B:385:0x0523, B:386:0x0528, B:370:0x04bb, B:372:0x04bf, B:374:0x04c5, B:375:0x04e0), top: B:408:0x049f }] */
    /* JADX WARN: Code duplicated, block: B:374:0x04c5 A[Catch: all -> 0x04b6, TryCatch #9 {all -> 0x04b6, blocks: (B:366:0x04a1, B:376:0x04f8, B:378:0x04fe, B:379:0x0516, B:381:0x051a, B:385:0x0523, B:386:0x0528, B:370:0x04bb, B:372:0x04bf, B:374:0x04c5, B:375:0x04e0), top: B:408:0x049f }] */
    /* JADX WARN: Code duplicated, block: B:375:0x04e0 A[Catch: all -> 0x04b6, TryCatch #9 {all -> 0x04b6, blocks: (B:366:0x04a1, B:376:0x04f8, B:378:0x04fe, B:379:0x0516, B:381:0x051a, B:385:0x0523, B:386:0x0528, B:370:0x04bb, B:372:0x04bf, B:374:0x04c5, B:375:0x04e0), top: B:408:0x049f }] */
    /* JADX WARN: Code duplicated, block: B:378:0x04fe A[Catch: all -> 0x04b6, TryCatch #9 {all -> 0x04b6, blocks: (B:366:0x04a1, B:376:0x04f8, B:378:0x04fe, B:379:0x0516, B:381:0x051a, B:385:0x0523, B:386:0x0528, B:370:0x04bb, B:372:0x04bf, B:374:0x04c5, B:375:0x04e0), top: B:408:0x049f }] */
    /* JADX WARN: Code duplicated, block: B:381:0x051a A[Catch: all -> 0x04b6, TryCatch #9 {all -> 0x04b6, blocks: (B:366:0x04a1, B:376:0x04f8, B:378:0x04fe, B:379:0x0516, B:381:0x051a, B:385:0x0523, B:386:0x0528, B:370:0x04bb, B:372:0x04bf, B:374:0x04c5, B:375:0x04e0), top: B:408:0x049f }] */
    /* JADX WARN: Code duplicated, block: B:383:0x0520  */
    /* JADX WARN: Code duplicated, block: B:384:0x0521  */
    /* JADX WARN: Code duplicated, block: B:410:0x0479 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:85:0x0100  */
    /* JADX WARN: Instruction removed from duplicated block: B:366:0x04a1, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:374:0x04c5, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:375:0x04e0, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:378:0x04fe, please report this as an issue */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    protected void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i, boolean z) throws Throwable {
        FieldSerializer[] fieldSerializerArr;
        Exception exc;
        FieldSerializer fieldSerializer;
        Throwable th;
        SerialContext serialContext;
        Exception exc2;
        String str;
        String str2;
        FieldInfo fieldInfo;
        Throwable cause;
        Throwable th2;
        boolean z2;
        SerialContext serialContext2;
        boolean z3;
        FieldSerializer fieldSerializer2;
        Object propertyValueDirect;
        Object objTrim;
        String strProcessKey;
        int i2;
        FieldSerializer[] fieldSerializerArr2;
        Object obj3;
        char c;
        Object objProcessValue;
        char c2;
        boolean z4;
        boolean z5;
        boolean z6;
        Class<?> cls;
        int iOf;
        int i3;
        int i4;
        int i5;
        int i6;
        Type type2 = type;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (writeReference(jSONSerializer, obj, i)) {
            return;
        }
        if (serializeWriter.sortField) {
            fieldSerializerArr = this.sortedGetters;
        } else {
            fieldSerializerArr = this.getters;
        }
        FieldSerializer[] fieldSerializerArr3 = fieldSerializerArr;
        SerialContext serialContext3 = jSONSerializer.context;
        if (!this.beanInfo.beanType.isEnum()) {
            jSONSerializer.setContext(serialContext3, obj, obj2, this.beanInfo.features, i);
        }
        boolean zIsWriteAsArray = isWriteAsArray(jSONSerializer, i);
        char c3 = zIsWriteAsArray ? '[' : '{';
        char c4 = zIsWriteAsArray ? ']' : '}';
        if (!z) {
            try {
                try {
                    serializeWriter.append(c3);
                } catch (Throwable th3) {
                    th = th3;
                    serialContext = serialContext3;
                    jSONSerializer.context = serialContext;
                    throw th;
                }
            } catch (Exception e) {
                exc = e;
                fieldSerializer = null;
                exc2 = exc;
                str = "write javaBean error, fastjson version 1.2.53";
                if (obj != null) {
                    try {
                        str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
                    } catch (Throwable th4) {
                        th = th4;
                        serialContext = serialContext3;
                        jSONSerializer.context = serialContext;
                        throw th;
                    }
                }
                str2 = str;
                serialContext = serialContext3;
                try {
                    if (obj2 != null) {
                        str2 = str2 + ", fieldName : " + obj2;
                    } else if (fieldSerializer != null) {
                        fieldInfo = fieldSerializer.fieldInfo;
                        if (fieldInfo.method != null) {
                            str2 = str2 + ", method : " + fieldInfo.method.getName();
                        } else {
                            str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                        }
                    }
                    if (exc2.getMessage() != null) {
                        str2 = str2 + ", " + exc2.getMessage();
                    }
                    cause = exc2 instanceof InvocationTargetException ? exc2.getCause() : null;
                    if (cause == null) {
                        th2 = exc2;
                    } else {
                        th2 = cause;
                    }
                    throw new JSONException(str2, th2);
                } catch (Throwable th5) {
                    th = th5;
                    jSONSerializer.context = serialContext;
                    throw th;
                }
            }
        }
        try {
            if (fieldSerializerArr3.length > 0 && serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
            }
            if ((this.beanInfo.features & SerializerFeature.WriteClassName.mask) == 0 && (SerializerFeature.WriteClassName.mask & i) == 0 && !jSONSerializer.isWriteClassName(type2, obj)) {
                z2 = false;
            } else {
                Class<?> cls2 = obj.getClass();
                if (cls2 != ((cls2 == type2 || !(type2 instanceof WildcardType)) ? type2 : TypeUtils.getClass(type))) {
                    writeClassName(jSONSerializer, this.beanInfo.typeKey, obj);
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            char c5 = ',';
            char c6 = z2 ? ',' : (char) 0;
            boolean zIsEnabled = serializeWriter.isEnabled(SerializerFeature.WriteClassName);
            boolean z7 = serializeWriter.quoteFieldNames && !serializeWriter.useSingleQuotes;
            boolean z8 = writeBefore(jSONSerializer, obj, c6) == ',';
            boolean zIsEnabled2 = serializeWriter.isEnabled(SerializerFeature.SkipTransientField);
            boolean zIsEnabled3 = serializeWriter.isEnabled(SerializerFeature.IgnoreNonFieldGetter);
            boolean z9 = z8;
            fieldSerializer = null;
            int i7 = 0;
            while (i7 < fieldSerializerArr3.length) {
                try {
                    try {
                        fieldSerializer = fieldSerializerArr3[i7];
                        Field field = fieldSerializer.fieldInfo.field;
                        FieldInfo fieldInfo2 = fieldSerializer.fieldInfo;
                        serialContext2 = serialContext3;
                        try {
                            try {
                                String str3 = fieldInfo2.name;
                                Class<?> cls3 = fieldInfo2.fieldClass;
                                if ((zIsEnabled2 && field != null && fieldInfo2.fieldTransient) || (zIsEnabled3 && field == null)) {
                                    i2 = i7;
                                    c = c4;
                                    fieldSerializerArr2 = fieldSerializerArr3;
                                    c2 = ',';
                                } else {
                                    if (applyName(jSONSerializer, obj, str3) && applyLabel(jSONSerializer, fieldInfo2.label)) {
                                        z3 = false;
                                    } else if (zIsWriteAsArray) {
                                        z3 = true;
                                    } else {
                                        i2 = i7;
                                        c = c4;
                                        fieldSerializerArr2 = fieldSerializerArr3;
                                        c2 = ',';
                                    }
                                    if (this.beanInfo.typeKey != null && str3.equals(this.beanInfo.typeKey) && jSONSerializer.isWriteClassName(type2, obj)) {
                                        i2 = i7;
                                        c = c4;
                                        fieldSerializerArr2 = fieldSerializerArr3;
                                        c2 = ',';
                                    } else {
                                        try {
                                            if (z3) {
                                                fieldSerializer2 = fieldSerializer;
                                            } else {
                                                try {
                                                    propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj);
                                                    fieldSerializer2 = fieldSerializer;
                                                } catch (InvocationTargetException e2) {
                                                    try {
                                                        if (!serializeWriter.isEnabled(SerializerFeature.IgnoreErrorGetter)) {
                                                            try {
                                                                throw e2;
                                                            } catch (Exception e3) {
                                                                e = e3;
                                                                exc2 = e;
                                                                serialContext3 = serialContext2;
                                                                str = "write javaBean error, fastjson version 1.2.53";
                                                                if (obj != null) {
                                                                    str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
                                                                }
                                                                str2 = str;
                                                                serialContext = serialContext3;
                                                                if (obj2 != null) {
                                                                    str2 = str2 + ", fieldName : " + obj2;
                                                                } else if (fieldSerializer != null) {
                                                                    fieldInfo = fieldSerializer.fieldInfo;
                                                                    if (fieldInfo.method != null) {
                                                                        str2 = str2 + ", method : " + fieldInfo.method.getName();
                                                                    } else {
                                                                        str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                                                                    }
                                                                }
                                                                if (exc2.getMessage() != null) {
                                                                    str2 = str2 + ", " + exc2.getMessage();
                                                                }
                                                                if (exc2 instanceof InvocationTargetException) {
                                                                }
                                                                if (cause == null) {
                                                                    th2 = exc2;
                                                                } else {
                                                                    th2 = cause;
                                                                }
                                                                throw new JSONException(str2, th2);
                                                            }
                                                        }
                                                        fieldSerializer2 = fieldSerializer;
                                                        propertyValueDirect = null;
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                    }
                                                }
                                                if (apply(jSONSerializer, obj, str3, propertyValueDirect)) {
                                                    if (cls3 == String.class || !"trim".equals(fieldInfo2.format) || propertyValueDirect == null) {
                                                        objTrim = propertyValueDirect;
                                                    } else {
                                                        objTrim = ((String) propertyValueDirect).trim();
                                                    }
                                                    strProcessKey = processKey(jSONSerializer, obj, str3, objTrim);
                                                    i2 = i7;
                                                    fieldSerializerArr2 = fieldSerializerArr3;
                                                    obj3 = objTrim;
                                                    c = c4;
                                                    objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str3, obj3);
                                                    if (objProcessValue == null) {
                                                        iOf = fieldInfo2.serialzeFeatures;
                                                        if (this.beanInfo.jsonType != null) {
                                                            iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                        }
                                                        if (cls3 == Boolean.class) {
                                                            i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                            int i8 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                            if (!zIsWriteAsArray || (iOf & i8) != 0 || (i8 & serializeWriter.features) != 0) {
                                                                if ((iOf & i6) == 0 || (serializeWriter.features & i6) != 0) {
                                                                    objProcessValue = false;
                                                                }
                                                                if (objProcessValue != null || ((!serializeWriter.notWriteDefaultValue && (fieldInfo2.serialzeFeatures & SerializerFeature.NotWriteDefaultValue.mask) == 0 && (this.beanInfo.features & SerializerFeature.NotWriteDefaultValue.mask) == 0) || (((cls = fieldInfo2.fieldClass) != Byte.TYPE || !(objProcessValue instanceof Byte) || ((Byte) objProcessValue).byteValue() != 0) && ((cls != Short.TYPE || !(objProcessValue instanceof Short) || ((Short) objProcessValue).shortValue() != 0) && ((cls != Integer.TYPE || !(objProcessValue instanceof Integer) || ((Integer) objProcessValue).intValue() != 0) && ((cls != Long.TYPE || !(objProcessValue instanceof Long) || ((Long) objProcessValue).longValue() != 0) && ((cls != Float.TYPE || !(objProcessValue instanceof Float) || ((Float) objProcessValue).floatValue() != 0.0f) && ((cls != Double.TYPE || !(objProcessValue instanceof Double) || ((Double) objProcessValue).doubleValue() != 0.0d) && (cls != Boolean.TYPE || !(objProcessValue instanceof Boolean) || ((Boolean) objProcessValue).booleanValue()))))))))) {
                                                                    if (!z9) {
                                                                        c2 = ',';
                                                                    } else if (fieldInfo2.unwrapped || !(objProcessValue instanceof Map) || ((Map) objProcessValue).size() != 0) {
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                        } else {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray && (zIsEnabled || !fieldInfo2.unwrapped)) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class || !(annotation == null || annotation.serializeUsing() == Void.class)) {
                                                                                    if (!fieldInfo2.unwrapped && (objProcessValue instanceof Map) && ((Map) objProcessValue).size() == 0) {
                                                                                        z9 = false;
                                                                                    } else {
                                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                    }
                                                                                } else if (objProcessValue == null) {
                                                                                    if ((serializeWriter.features & SerializerFeature.WriteNullStringAsEmpty.mask) != 0 || (fieldSerializer.features & SerializerFeature.WriteNullStringAsEmpty.mask) != 0) {
                                                                                        serializeWriter.writeString("");
                                                                                    } else {
                                                                                        serializeWriter.writeNull();
                                                                                    }
                                                                                } else {
                                                                                    String str4 = (String) objProcessValue;
                                                                                    if (serializeWriter.useSingleQuotes) {
                                                                                        serializeWriter.writeStringWithSingleQuote(str4);
                                                                                    } else {
                                                                                        serializeWriter.writeStringWithDoubleQuote(str4, (char) 0);
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped || !(objProcessValue instanceof Map)) {
                                                                        z5 = false;
                                                                    } else {
                                                                        Map map = (Map) objProcessValue;
                                                                        if (map.size() != 0) {
                                                                            if (!jSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue)) {
                                                                                Iterator it = map.values().iterator();
                                                                                while (true) {
                                                                                    if (!it.hasNext()) {
                                                                                        z6 = false;
                                                                                        break;
                                                                                    } else if (it.next() != null) {
                                                                                        z6 = z4;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                if (!z6) {
                                                                                }
                                                                            }
                                                                            z5 = false;
                                                                        }
                                                                        z5 = z4;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                }
                                                            }
                                                        } else if (cls3 == String.class) {
                                                            i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                            int i9 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                            if (!zIsWriteAsArray || (iOf & i9) != 0 || (i9 & serializeWriter.features) != 0) {
                                                                if ((iOf & i5) == 0 || (serializeWriter.features & i5) != 0) {
                                                                    objProcessValue = "";
                                                                }
                                                                if (objProcessValue != null) {
                                                                    if (!z9) {
                                                                        if (fieldInfo2.unwrapped) {
                                                                        }
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    } else {
                                                                        c2 = ',';
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        } else {
                                                                            z4 = true;
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation2 = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class) {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                } else {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                } else {
                                                                    if (!z9) {
                                                                        if (fieldInfo2.unwrapped) {
                                                                        }
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    } else {
                                                                        c2 = ',';
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        } else {
                                                                            z4 = true;
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation3 = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class) {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                } else {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                }
                                                            }
                                                        } else if (Number.class.isAssignableFrom(cls3)) {
                                                            i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                            int i10 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                            if (!zIsWriteAsArray || (iOf & i10) != 0 || (i10 & serializeWriter.features) != 0) {
                                                                if ((iOf & i4) == 0 || (serializeWriter.features & i4) != 0) {
                                                                    objProcessValue = 0;
                                                                }
                                                                if (objProcessValue != null) {
                                                                    if (!z9) {
                                                                        if (fieldInfo2.unwrapped) {
                                                                        }
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    } else {
                                                                        c2 = ',';
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        } else {
                                                                            z4 = true;
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation4 = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class) {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                } else {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                } else {
                                                                    if (!z9) {
                                                                        if (fieldInfo2.unwrapped) {
                                                                        }
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    } else {
                                                                        c2 = ',';
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        } else {
                                                                            z4 = true;
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation5 = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class) {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                } else {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                }
                                                            }
                                                        } else if (Collection.class.isAssignableFrom(cls3)) {
                                                            i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                            int i11 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                            if (!zIsWriteAsArray || (iOf & i11) != 0 || (i11 & serializeWriter.features) != 0) {
                                                                if ((iOf & i3) == 0 || (serializeWriter.features & i3) != 0) {
                                                                    objProcessValue = Collections.emptyList();
                                                                }
                                                                if (objProcessValue != null) {
                                                                    if (!z9) {
                                                                        if (fieldInfo2.unwrapped) {
                                                                        }
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    } else {
                                                                        c2 = ',';
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        } else {
                                                                            z4 = true;
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation6 = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class) {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                } else {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                } else {
                                                                    if (!z9) {
                                                                        if (fieldInfo2.unwrapped) {
                                                                        }
                                                                        c2 = ',';
                                                                        serializeWriter.write(44);
                                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                            jSONSerializer.println();
                                                                        }
                                                                    } else {
                                                                        c2 = ',';
                                                                    }
                                                                    if (strProcessKey != str3) {
                                                                        if (zIsWriteAsArray) {
                                                                            z4 = true;
                                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                                        } else {
                                                                            z4 = true;
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        z4 = true;
                                                                        if (obj3 != objProcessValue) {
                                                                            if (!zIsWriteAsArray) {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                            jSONSerializer.write(objProcessValue);
                                                                        } else {
                                                                            if (!zIsWriteAsArray) {
                                                                                if (z7) {
                                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                                } else {
                                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                                }
                                                                            }
                                                                            if (!zIsWriteAsArray) {
                                                                                JSONField annotation7 = fieldInfo2.getAnnotation();
                                                                                if (cls3 == String.class) {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                } else {
                                                                                    if (!fieldInfo2.unwrapped) {
                                                                                    }
                                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                                }
                                                                            } else {
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        }
                                                                    }
                                                                    if (fieldInfo2.unwrapped) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = false;
                                                                    }
                                                                    if (!z5) {
                                                                        z9 = z4;
                                                                    }
                                                                }
                                                            }
                                                        } else if (zIsWriteAsArray || fieldSerializer.writeNull || serializeWriter.isEnabled(SerializerFeature.WriteMapNullValue.mask)) {
                                                            if (objProcessValue != null) {
                                                                if (!z9) {
                                                                    if (fieldInfo2.unwrapped) {
                                                                    }
                                                                    c2 = ',';
                                                                    serializeWriter.write(44);
                                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                        jSONSerializer.println();
                                                                    }
                                                                } else {
                                                                    c2 = ',';
                                                                }
                                                                if (strProcessKey != str3) {
                                                                    if (zIsWriteAsArray) {
                                                                        z4 = true;
                                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                                    } else {
                                                                        z4 = true;
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    z4 = true;
                                                                    if (obj3 != objProcessValue) {
                                                                        if (!zIsWriteAsArray) {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        if (!zIsWriteAsArray) {
                                                                            if (z7) {
                                                                                serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                            } else {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                        }
                                                                        if (!zIsWriteAsArray) {
                                                                            JSONField annotation8 = fieldInfo2.getAnnotation();
                                                                            if (cls3 == String.class) {
                                                                                if (!fieldInfo2.unwrapped) {
                                                                                }
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            } else {
                                                                                if (!fieldInfo2.unwrapped) {
                                                                                }
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        } else {
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    }
                                                                }
                                                                if (fieldInfo2.unwrapped) {
                                                                    z5 = false;
                                                                } else {
                                                                    z5 = false;
                                                                }
                                                                if (!z5) {
                                                                    z9 = z4;
                                                                }
                                                            } else {
                                                                if (!z9) {
                                                                    if (fieldInfo2.unwrapped) {
                                                                    }
                                                                    c2 = ',';
                                                                    serializeWriter.write(44);
                                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                        jSONSerializer.println();
                                                                    }
                                                                } else {
                                                                    c2 = ',';
                                                                }
                                                                if (strProcessKey != str3) {
                                                                    if (zIsWriteAsArray) {
                                                                        z4 = true;
                                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                                    } else {
                                                                        z4 = true;
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    z4 = true;
                                                                    if (obj3 != objProcessValue) {
                                                                        if (!zIsWriteAsArray) {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                        jSONSerializer.write(objProcessValue);
                                                                    } else {
                                                                        if (!zIsWriteAsArray) {
                                                                            if (z7) {
                                                                                serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                            } else {
                                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                                            }
                                                                        }
                                                                        if (!zIsWriteAsArray) {
                                                                            JSONField annotation9 = fieldInfo2.getAnnotation();
                                                                            if (cls3 == String.class) {
                                                                                if (!fieldInfo2.unwrapped) {
                                                                                }
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            } else {
                                                                                if (!fieldInfo2.unwrapped) {
                                                                                }
                                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                            }
                                                                        } else {
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    }
                                                                }
                                                                if (fieldInfo2.unwrapped) {
                                                                    z5 = false;
                                                                } else {
                                                                    z5 = false;
                                                                }
                                                                if (!z5) {
                                                                    z9 = z4;
                                                                }
                                                            }
                                                        }
                                                        c2 = ',';
                                                    } else if (objProcessValue != null) {
                                                        if (!z9) {
                                                            if (fieldInfo2.unwrapped) {
                                                            }
                                                            c2 = ',';
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        } else {
                                                            c2 = ',';
                                                        }
                                                        if (strProcessKey != str3) {
                                                            if (zIsWriteAsArray) {
                                                                z4 = true;
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            } else {
                                                                z4 = true;
                                                            }
                                                            jSONSerializer.write(objProcessValue);
                                                        } else {
                                                            z4 = true;
                                                            if (obj3 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    if (z7) {
                                                                        serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                    } else {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation10 = fieldInfo2.getAnnotation();
                                                                    if (cls3 == String.class) {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                }
                                                            }
                                                        }
                                                        if (fieldInfo2.unwrapped) {
                                                            z5 = false;
                                                        } else {
                                                            z5 = false;
                                                        }
                                                        if (!z5) {
                                                            z9 = z4;
                                                        }
                                                    } else {
                                                        if (!z9) {
                                                            if (fieldInfo2.unwrapped) {
                                                            }
                                                            c2 = ',';
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        } else {
                                                            c2 = ',';
                                                        }
                                                        if (strProcessKey != str3) {
                                                            if (zIsWriteAsArray) {
                                                                z4 = true;
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            } else {
                                                                z4 = true;
                                                            }
                                                            jSONSerializer.write(objProcessValue);
                                                        } else {
                                                            z4 = true;
                                                            if (obj3 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    if (z7) {
                                                                        serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                    } else {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation11 = fieldInfo2.getAnnotation();
                                                                    if (cls3 == String.class) {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                }
                                                            }
                                                        }
                                                        if (fieldInfo2.unwrapped) {
                                                            z5 = false;
                                                        } else {
                                                            z5 = false;
                                                        }
                                                        if (!z5) {
                                                            z9 = z4;
                                                        }
                                                    }
                                                } else {
                                                    i2 = i7;
                                                    c = c4;
                                                    fieldSerializerArr2 = fieldSerializerArr3;
                                                    c2 = ',';
                                                }
                                                fieldSerializer = fieldSerializer2;
                                            }
                                            if (apply(jSONSerializer, obj, str3, propertyValueDirect)) {
                                                i2 = i7;
                                                c = c4;
                                                fieldSerializerArr2 = fieldSerializerArr3;
                                                c2 = ',';
                                            } else {
                                                if (cls3 == String.class) {
                                                    objTrim = propertyValueDirect;
                                                } else {
                                                    objTrim = propertyValueDirect;
                                                }
                                                strProcessKey = processKey(jSONSerializer, obj, str3, objTrim);
                                                i2 = i7;
                                                fieldSerializerArr2 = fieldSerializerArr3;
                                                obj3 = objTrim;
                                                c = c4;
                                                objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str3, obj3);
                                                if (objProcessValue == null) {
                                                    iOf = fieldInfo2.serialzeFeatures;
                                                    if (this.beanInfo.jsonType != null) {
                                                        iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                    }
                                                    if (cls3 == Boolean.class) {
                                                        i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                        int i12 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i6) == 0) {
                                                            objProcessValue = false;
                                                        } else {
                                                            objProcessValue = false;
                                                        }
                                                        if (objProcessValue != null) {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation12 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        } else {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation13 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        }
                                                    } else if (cls3 == String.class) {
                                                        i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                        int i13 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i5) == 0) {
                                                            objProcessValue = "";
                                                        } else {
                                                            objProcessValue = "";
                                                        }
                                                        if (objProcessValue != null) {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation14 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        } else {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation15 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        }
                                                    } else if (Number.class.isAssignableFrom(cls3)) {
                                                        i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                        int i14 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i4) == 0) {
                                                            objProcessValue = 0;
                                                        } else {
                                                            objProcessValue = 0;
                                                        }
                                                        if (objProcessValue != null) {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation16 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        } else {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation17 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        }
                                                    } else if (Collection.class.isAssignableFrom(cls3)) {
                                                        i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                        int i15 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i3) == 0) {
                                                            objProcessValue = Collections.emptyList();
                                                        } else {
                                                            objProcessValue = Collections.emptyList();
                                                        }
                                                        if (objProcessValue != null) {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation18 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        } else {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation19 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        }
                                                    } else if (zIsWriteAsArray) {
                                                        if (objProcessValue != null) {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation110 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        } else {
                                                            if (!z9) {
                                                                if (fieldInfo2.unwrapped) {
                                                                }
                                                                c2 = ',';
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            } else {
                                                                c2 = ',';
                                                            }
                                                            if (strProcessKey != str3) {
                                                                if (zIsWriteAsArray) {
                                                                    z4 = true;
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                } else {
                                                                    z4 = true;
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                z4 = true;
                                                                if (obj3 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                    jSONSerializer.write(objProcessValue);
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        if (z7) {
                                                                            serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                        } else {
                                                                            fieldSerializer.writePrefix(jSONSerializer);
                                                                        }
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation111 = fieldInfo2.getAnnotation();
                                                                        if (cls3 == String.class) {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo2.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                }
                                                            }
                                                            if (fieldInfo2.unwrapped) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (!z5) {
                                                                z9 = z4;
                                                            }
                                                        }
                                                    } else if (objProcessValue != null) {
                                                        if (!z9) {
                                                            if (fieldInfo2.unwrapped) {
                                                            }
                                                            c2 = ',';
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        } else {
                                                            c2 = ',';
                                                        }
                                                        if (strProcessKey != str3) {
                                                            if (zIsWriteAsArray) {
                                                                z4 = true;
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            } else {
                                                                z4 = true;
                                                            }
                                                            jSONSerializer.write(objProcessValue);
                                                        } else {
                                                            z4 = true;
                                                            if (obj3 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    if (z7) {
                                                                        serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                    } else {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation112 = fieldInfo2.getAnnotation();
                                                                    if (cls3 == String.class) {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                }
                                                            }
                                                        }
                                                        if (fieldInfo2.unwrapped) {
                                                            z5 = false;
                                                        } else {
                                                            z5 = false;
                                                        }
                                                        if (!z5) {
                                                            z9 = z4;
                                                        }
                                                    } else {
                                                        if (!z9) {
                                                            if (fieldInfo2.unwrapped) {
                                                            }
                                                            c2 = ',';
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        } else {
                                                            c2 = ',';
                                                        }
                                                        if (strProcessKey != str3) {
                                                            if (zIsWriteAsArray) {
                                                                z4 = true;
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            } else {
                                                                z4 = true;
                                                            }
                                                            jSONSerializer.write(objProcessValue);
                                                        } else {
                                                            z4 = true;
                                                            if (obj3 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                                jSONSerializer.write(objProcessValue);
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    if (z7) {
                                                                        serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                    } else {
                                                                        fieldSerializer.writePrefix(jSONSerializer);
                                                                    }
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation113 = fieldInfo2.getAnnotation();
                                                                    if (cls3 == String.class) {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo2.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                }
                                                            }
                                                        }
                                                        if (fieldInfo2.unwrapped) {
                                                            z5 = false;
                                                        } else {
                                                            z5 = false;
                                                        }
                                                        if (!z5) {
                                                            z9 = z4;
                                                        }
                                                    }
                                                    c2 = ',';
                                                } else if (objProcessValue != null) {
                                                    if (!z9) {
                                                        if (fieldInfo2.unwrapped) {
                                                        }
                                                        c2 = ',';
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    } else {
                                                        c2 = ',';
                                                    }
                                                    if (strProcessKey != str3) {
                                                        if (zIsWriteAsArray) {
                                                            z4 = true;
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        } else {
                                                            z4 = true;
                                                        }
                                                        jSONSerializer.write(objProcessValue);
                                                    } else {
                                                        z4 = true;
                                                        if (obj3 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                            }
                                                            jSONSerializer.write(objProcessValue);
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                if (z7) {
                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                } else {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation114 = fieldInfo2.getAnnotation();
                                                                if (cls3 == String.class) {
                                                                    if (!fieldInfo2.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo2.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                            }
                                                        }
                                                    }
                                                    if (fieldInfo2.unwrapped) {
                                                        z5 = false;
                                                    } else {
                                                        z5 = false;
                                                    }
                                                    if (!z5) {
                                                        z9 = z4;
                                                    }
                                                } else {
                                                    if (!z9) {
                                                        if (fieldInfo2.unwrapped) {
                                                        }
                                                        c2 = ',';
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    } else {
                                                        c2 = ',';
                                                    }
                                                    if (strProcessKey != str3) {
                                                        if (zIsWriteAsArray) {
                                                            z4 = true;
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        } else {
                                                            z4 = true;
                                                        }
                                                        jSONSerializer.write(objProcessValue);
                                                    } else {
                                                        z4 = true;
                                                        if (obj3 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                            }
                                                            jSONSerializer.write(objProcessValue);
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                if (z7) {
                                                                    serializeWriter.write(fieldInfo2.name_chars, 0, fieldInfo2.name_chars.length);
                                                                } else {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation115 = fieldInfo2.getAnnotation();
                                                                if (cls3 == String.class) {
                                                                    if (!fieldInfo2.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo2.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer, objProcessValue);
                                                            }
                                                        }
                                                    }
                                                    if (fieldInfo2.unwrapped) {
                                                        z5 = false;
                                                    } else {
                                                        z5 = false;
                                                    }
                                                    if (!z5) {
                                                        z9 = z4;
                                                    }
                                                }
                                            }
                                            fieldSerializer = fieldSerializer2;
                                        } catch (Exception e5) {
                                            exc2 = e5;
                                            serialContext3 = serialContext2;
                                            fieldSerializer = fieldSerializer2;
                                            str = "write javaBean error, fastjson version 1.2.53";
                                            if (obj != null) {
                                                str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
                                            }
                                            str2 = str;
                                            serialContext = serialContext3;
                                            if (obj2 != null) {
                                                str2 = str2 + ", fieldName : " + obj2;
                                            } else if (fieldSerializer != null) {
                                                fieldInfo = fieldSerializer.fieldInfo;
                                                if (fieldInfo.method != null) {
                                                    str2 = str2 + ", method : " + fieldInfo.method.getName();
                                                } else {
                                                    str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                                                }
                                            }
                                            if (exc2.getMessage() != null) {
                                                str2 = str2 + ", " + exc2.getMessage();
                                            }
                                            if (exc2 instanceof InvocationTargetException) {
                                            }
                                            if (cause == null) {
                                                th2 = exc2;
                                            } else {
                                                th2 = cause;
                                            }
                                            throw new JSONException(str2, th2);
                                        }
                                        propertyValueDirect = null;
                                    }
                                }
                                i7 = i2 + 1;
                                c5 = c2;
                                serialContext3 = serialContext2;
                                fieldSerializerArr3 = fieldSerializerArr2;
                                c4 = c;
                                type2 = type;
                            } catch (Throwable th6) {
                                th = th6;
                                serialContext = serialContext2;
                                jSONSerializer.context = serialContext;
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                            serialContext3 = serialContext2;
                            exc2 = e;
                            str = "write javaBean error, fastjson version 1.2.53";
                            if (obj != null) {
                                str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
                            }
                            str2 = str;
                            serialContext = serialContext3;
                            if (obj2 != null) {
                                str2 = str2 + ", fieldName : " + obj2;
                            } else if (fieldSerializer != null) {
                                fieldInfo = fieldSerializer.fieldInfo;
                                if (fieldInfo.method != null) {
                                    str2 = str2 + ", method : " + fieldInfo.method.getName();
                                } else {
                                    str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                                }
                            }
                            if (exc2.getMessage() != null) {
                                str2 = str2 + ", " + exc2.getMessage();
                            }
                            if (exc2 instanceof InvocationTargetException) {
                            }
                            if (cause == null) {
                                th2 = exc2;
                            } else {
                                th2 = cause;
                            }
                            throw new JSONException(str2, th2);
                        }
                    } catch (Exception e7) {
                        e = e7;
                        serialContext3 = serialContext3;
                        exc2 = e;
                        str = "write javaBean error, fastjson version 1.2.53";
                        if (obj != null) {
                            str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
                        }
                        str2 = str;
                        serialContext = serialContext3;
                        if (obj2 != null) {
                            str2 = str2 + ", fieldName : " + obj2;
                        } else if (fieldSerializer != null && fieldSerializer.fieldInfo != null) {
                            fieldInfo = fieldSerializer.fieldInfo;
                            if (fieldInfo.method != null) {
                                str2 = str2 + ", method : " + fieldInfo.method.getName();
                            } else {
                                str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                            }
                        }
                        if (exc2.getMessage() != null) {
                            str2 = str2 + ", " + exc2.getMessage();
                        }
                        if (exc2 instanceof InvocationTargetException) {
                        }
                        if (cause == null) {
                            th2 = exc2;
                        } else {
                            th2 = cause;
                        }
                        throw new JSONException(str2, th2);
                    }
                } catch (Exception e8) {
                    e = e8;
                    serialContext3 = serialContext3;
                }
            }
            char c7 = c4;
            serialContext2 = serialContext3;
            FieldSerializer[] fieldSerializerArr4 = fieldSerializerArr3;
            try {
                writeAfter(jSONSerializer, obj, z9 ? c5 : (char) 0);
                if (fieldSerializerArr4.length > 0 && serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                    jSONSerializer.decrementIdent();
                    jSONSerializer.println();
                }
                if (!z) {
                    serializeWriter.append(c7);
                }
                jSONSerializer.context = serialContext2;
            } catch (Exception e9) {
                e = e9;
                serialContext3 = serialContext2;
                exc2 = e;
                str = "write javaBean error, fastjson version 1.2.53";
                if (obj != null) {
                    str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
                }
                str2 = str;
                serialContext = serialContext3;
                if (obj2 != null) {
                    str2 = str2 + ", fieldName : " + obj2;
                } else if (fieldSerializer != null) {
                    fieldInfo = fieldSerializer.fieldInfo;
                    if (fieldInfo.method != null) {
                        str2 = str2 + ", method : " + fieldInfo.method.getName();
                    } else {
                        str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                    }
                }
                if (exc2.getMessage() != null) {
                    str2 = str2 + ", " + exc2.getMessage();
                }
                if (exc2 instanceof InvocationTargetException) {
                }
                if (cause == null) {
                    th2 = exc2;
                } else {
                    th2 = cause;
                }
                throw new JSONException(str2, th2);
            }
        } catch (Exception e10) {
            exc = e10;
            fieldSerializer = null;
            exc2 = exc;
            str = "write javaBean error, fastjson version 1.2.53";
            if (obj != null) {
                str = "write javaBean error, fastjson version 1.2.53, class " + obj.getClass().getName();
            }
            str2 = str;
            serialContext = serialContext3;
            if (obj2 != null) {
                str2 = str2 + ", fieldName : " + obj2;
            } else if (fieldSerializer != null) {
                fieldInfo = fieldSerializer.fieldInfo;
                if (fieldInfo.method != null) {
                    str2 = str2 + ", method : " + fieldInfo.method.getName();
                } else {
                    str2 = str2 + ", fieldName : " + fieldSerializer.fieldInfo.name;
                }
            }
            if (exc2.getMessage() != null) {
                str2 = str2 + ", " + exc2.getMessage();
            }
            if (exc2 instanceof InvocationTargetException) {
            }
            if (cause == null) {
                th2 = exc2;
            } else {
                th2 = cause;
            }
            throw new JSONException(str2, th2);
        }
    }

    protected void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String name = this.beanInfo.typeName;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (TypeUtils.isProxy(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        jSONSerializer.write(name);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.sortedGetters[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArrValues.length];
            int i = 0;
            int i2 = 0;
            while (i < this.sortedGetters.length) {
                String str = this.sortedGetters[i].fieldInfo.name;
                int i3 = i2 + 1;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i3] = TypeUtils.fnv1a_64(strTranslate);
                        i3++;
                    }
                }
                i++;
                i2 = i3;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            for (int i4 = 0; i4 < this.sortedGetters.length; i4++) {
                String str2 = this.sortedGetters[i4].fieldInfo.name;
                int iBinarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i4;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i4;
                    }
                }
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Set<String> getFieldNames(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    protected BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    protected Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    protected char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.beforeFilters != null) {
            Iterator<BeforeFilter> it = jSONSerializer.beforeFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        if (this.beforeFilters != null) {
            Iterator<BeforeFilter> it2 = this.beforeFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.afterFilters != null) {
            Iterator<AfterFilter> it = jSONSerializer.afterFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        if (this.afterFilters != null) {
            Iterator<AfterFilter> it2 = this.afterFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            Iterator<LabelFilter> it = jSONSerializer.labelFilters.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = this.labelFilters.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }
}
