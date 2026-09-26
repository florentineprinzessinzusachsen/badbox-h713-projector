package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
    protected transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    static {
        for (Class<?> cls : new Class[]{Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class}) {
            primitiveClasses.add(cls);
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            this.dateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(str, new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        if (this.input instanceof char[]) {
            return new String((char[]) this.input);
        }
        return this.input.toString();
    }

    /* JADX WARN: Code duplicated, block: B:107:0x021a A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:176:0x0344  */
    /* JADX WARN: Code duplicated, block: B:179:0x034a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:217:0x03e6  */
    /* JADX WARN: Code duplicated, block: B:229:0x0435 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:231:0x0439 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:236:0x0449 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:238:0x044f  */
    /* JADX WARN: Code duplicated, block: B:242:0x0459 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:246:0x0461 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:248:0x0470 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:250:0x047b A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:253:0x048a  */
    /* JADX WARN: Code duplicated, block: B:254:0x048c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:255:0x048e  */
    /* JADX WARN: Code duplicated, block: B:264:0x04b7 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:267:0x04c2 A[Catch: all -> 0x0650, TRY_LEAVE, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:270:0x04d2 A[Catch: all -> 0x0650, TRY_ENTER, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:274:0x04f9 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:276:0x0503 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:278:0x050b A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:281:0x0519 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:287:0x052f A[Catch: all -> 0x0650, TRY_ENTER, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:289:0x0537  */
    /* JADX WARN: Code duplicated, block: B:292:0x0544  */
    /* JADX WARN: Code duplicated, block: B:294:0x0548 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:296:0x054d A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:299:0x0557  */
    /* JADX WARN: Code duplicated, block: B:302:0x0561 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:303:0x0572 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:305:0x057f A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:306:0x0584  */
    /* JADX WARN: Code duplicated, block: B:309:0x0589 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:310:0x058b A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:311:0x0590  */
    /* JADX WARN: Code duplicated, block: B:314:0x0599 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:315:0x05a6  */
    /* JADX WARN: Code duplicated, block: B:317:0x05ab A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:319:0x05b1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:322:0x05b7 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:325:0x05c3 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:331:0x05d8 A[Catch: all -> 0x0650, TRY_ENTER, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:333:0x05e0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:334:0x05e2 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:335:0x05e7 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:338:0x0607 A[Catch: all -> 0x0650, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:343:0x0620 A[Catch: all -> 0x0650, TRY_ENTER, TryCatch #0 {all -> 0x0650, blocks: (B:29:0x007e, B:32:0x0091, B:35:0x00a9, B:37:0x00b8, B:38:0x00da, B:107:0x021a, B:108:0x0220, B:110:0x022b, B:112:0x0233, B:116:0x024a, B:118:0x0258, B:121:0x026b, B:122:0x0271, B:124:0x027e, B:125:0x0281, B:127:0x028b, B:128:0x0299, B:130:0x029f, B:132:0x02ad, B:134:0x02b5, B:139:0x02c4, B:140:0x02ca, B:142:0x02d2, B:143:0x02d7, B:148:0x02e1, B:149:0x02e8, B:150:0x02e9, B:153:0x02f3, B:155:0x02f7, B:157:0x02ff, B:158:0x0302, B:160:0x0308, B:163:0x0315, B:169:0x032f, B:173:0x033c, B:170:0x0334, B:172:0x0338, B:119:0x025e, B:180:0x034c, B:182:0x0354, B:184:0x035e, B:186:0x036f, B:188:0x037a, B:190:0x0382, B:192:0x0386, B:194:0x038e, B:197:0x0393, B:199:0x0397, B:218:0x03e7, B:220:0x03ef, B:221:0x0409, B:222:0x040a, B:200:0x039c, B:202:0x03a4, B:204:0x03a8, B:205:0x03ab, B:206:0x03b7, B:209:0x03c0, B:211:0x03c4, B:212:0x03c7, B:214:0x03cb, B:215:0x03cf, B:216:0x03db, B:225:0x0413, B:226:0x0431, B:229:0x0435, B:231:0x0439, B:233:0x043f, B:235:0x0445, B:236:0x0449, B:240:0x0451, B:246:0x0461, B:248:0x0470, B:250:0x047b, B:251:0x0483, B:252:0x0486, B:262:0x04ae, B:264:0x04b7, B:267:0x04c2, B:270:0x04d2, B:271:0x04f4, B:257:0x0492, B:259:0x049c, B:261:0x04ab, B:260:0x04a1, B:274:0x04f9, B:276:0x0503, B:278:0x050b, B:279:0x050e, B:281:0x0519, B:282:0x051d, B:284:0x0528, B:287:0x052f, B:290:0x053c, B:291:0x0543, B:294:0x0548, B:296:0x054d, B:300:0x0559, B:302:0x0561, B:305:0x057f, B:307:0x0585, B:310:0x058b, B:312:0x0591, B:314:0x0599, B:317:0x05ab, B:320:0x05b3, B:322:0x05b7, B:323:0x05be, B:325:0x05c3, B:326:0x05c6, B:328:0x05ce, B:331:0x05d8, B:334:0x05e2, B:335:0x05e7, B:336:0x05ec, B:337:0x0606, B:303:0x0572, B:338:0x0607, B:340:0x0619, B:343:0x0620, B:346:0x062d, B:347:0x064f, B:41:0x00e0, B:43:0x00eb, B:45:0x00ef, B:47:0x00f5, B:49:0x00fb, B:51:0x00ff, B:58:0x010e, B:60:0x0116, B:61:0x011d, B:62:0x011e, B:64:0x012d, B:65:0x0147, B:68:0x014c, B:69:0x0153, B:71:0x0156, B:72:0x015d, B:77:0x0166, B:78:0x016c, B:80:0x0173, B:82:0x017c, B:84:0x0184, B:86:0x0189, B:88:0x018f, B:89:0x01a9, B:81:0x0178, B:90:0x01aa, B:91:0x01c4, B:97:0x01ce, B:99:0x01d6, B:100:0x01dd, B:101:0x01de, B:103:0x01ed, B:104:0x020f, B:105:0x0210), top: B:354:0x007e, inners: #1, #3 }] */
    /* JADX WARN: Code duplicated, block: B:362:0x0432 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:375:0x04c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:376:0x0528 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:377:0x053c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:378:0x05ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:379:0x05ec A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:380:0x0619 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:381:0x062d A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:270:0x04d2, please report this as an issue */
    public final Object parseObject(Map map, Object obj) throws Throwable {
        Throwable th;
        ParseContext parseContext;
        ParseContext context;
        Object objScanSymbolUnQuoted;
        boolean z;
        char current;
        char c;
        boolean z2;
        Map jSONObject;
        ParseContext context2;
        boolean z3;
        Object object;
        String string;
        Type typeResolve;
        JSONArray jSONArray;
        JSONArray array;
        Object obj2;
        char current2;
        String strStringVal;
        Object obj3;
        JSONScanner jSONScanner;
        Object time;
        ParseContext context3;
        char c2;
        Object obj4;
        Object obj5;
        Class<?> clsCheckAutoType;
        Object map2;
        FieldDeserializer fieldDeserializer;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (jSONLexer.token() == 13) {
            jSONLexer.nextToken();
            return map;
        }
        if (jSONLexer.token() == 4 && jSONLexer.stringVal().length() == 0) {
            jSONLexer.nextToken();
            return map;
        }
        if (jSONLexer.token() != 12 && jSONLexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + jSONLexer.tokenName() + ", " + jSONLexer.info());
        }
        ParseContext parseContext2 = this.context;
        try {
            Map innerMap = map instanceof JSONObject ? ((JSONObject) map).getInnerMap() : map;
            parseContext = parseContext2;
            boolean z4 = false;
            while (true) {
                try {
                    jSONLexer.skipWhitespace();
                    char current3 = jSONLexer.getCurrent();
                    if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                        while (current3 == ',') {
                            jSONLexer.next();
                            jSONLexer.skipWhitespace();
                            current3 = jSONLexer.getCurrent();
                        }
                    }
                    if (current3 == '\"') {
                        objScanSymbolUnQuoted = jSONLexer.scanSymbol(this.symbolTable, '\"');
                        jSONLexer.skipWhitespace();
                        if (jSONLexer.getCurrent() != ':') {
                            throw new JSONException("expect ':' at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                        }
                    } else {
                        if (current3 == '}') {
                            jSONLexer.next();
                            jSONLexer.resetStringPosition();
                            jSONLexer.nextToken();
                            if (!z4) {
                                if (this.context != null && obj == this.context.fieldName && map == this.context.object) {
                                    context = this.context;
                                } else {
                                    context = setContext(map, obj);
                                    if (parseContext == null) {
                                    }
                                }
                                parseContext = context;
                            }
                            setContext(parseContext);
                            return map;
                        }
                        if (current3 == '\'') {
                            if (!jSONLexer.isEnabled(Feature.AllowSingleQuotes)) {
                                throw new JSONException("syntax error");
                            }
                            objScanSymbolUnQuoted = jSONLexer.scanSymbol(this.symbolTable, '\'');
                            jSONLexer.skipWhitespace();
                            if (jSONLexer.getCurrent() != ':') {
                                throw new JSONException("expect ':' at " + jSONLexer.pos());
                            }
                        } else {
                            if (current3 == 26) {
                                throw new JSONException("syntax error");
                            }
                            if (current3 == ',') {
                                throw new JSONException("syntax error");
                            }
                            if ((current3 < '0' || current3 > '9') && current3 != '-') {
                                if (current3 == '{' || current3 == '[') {
                                    jSONLexer.nextToken();
                                    objScanSymbolUnQuoted = parse();
                                    z = true;
                                } else {
                                    if (!jSONLexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                                        throw new JSONException("syntax error");
                                    }
                                    objScanSymbolUnQuoted = jSONLexer.scanSymbolUnQuoted(this.symbolTable);
                                    jSONLexer.skipWhitespace();
                                    char current4 = jSONLexer.getCurrent();
                                    if (current4 != ':') {
                                        throw new JSONException("expect ':' at " + jSONLexer.pos() + ", actual " + current4);
                                    }
                                }
                                if (!z) {
                                    jSONLexer.next();
                                    jSONLexer.skipWhitespace();
                                }
                                current = jSONLexer.getCurrent();
                                jSONLexer.resetStringPosition();
                                if (objScanSymbolUnQuoted != JSON.DEFAULT_TYPE_KEY && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                    String strScanSymbol = jSONLexer.scanSymbol(this.symbolTable, '\"');
                                    if (!jSONLexer.isEnabled(Feature.IgnoreAutoType)) {
                                        if (map == null || !map.getClass().getName().equals(strScanSymbol)) {
                                            obj5 = null;
                                            clsCheckAutoType = this.config.checkAutoType(strScanSymbol, null, jSONLexer.getFeatures());
                                        } else {
                                            clsCheckAutoType = map.getClass();
                                            obj5 = null;
                                        }
                                        if (clsCheckAutoType != null) {
                                            jSONLexer.nextToken(16);
                                            if (jSONLexer.token() == 13) {
                                                jSONLexer.nextToken(16);
                                                try {
                                                    ObjectDeserializer deserializer = this.config.getDeserializer(clsCheckAutoType);
                                                    if (deserializer instanceof JavaBeanDeserializer) {
                                                        JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                                                        map2 = javaBeanDeserializer.createInstance(this, clsCheckAutoType);
                                                        for (Map.Entry entry : innerMap.entrySet()) {
                                                            Object key = entry.getKey();
                                                            if ((key instanceof String) && (fieldDeserializer = javaBeanDeserializer.getFieldDeserializer((String) key)) != null) {
                                                                fieldDeserializer.setValue(map2, entry.getValue());
                                                            }
                                                        }
                                                    } else {
                                                        map2 = obj5;
                                                    }
                                                    if (map2 == null) {
                                                        map2 = clsCheckAutoType == Cloneable.class ? new HashMap() : "java.util.Collections$EmptyMap".equals(strScanSymbol) ? Collections.emptyMap() : clsCheckAutoType.newInstance();
                                                    }
                                                    setContext(parseContext);
                                                    return map2;
                                                } catch (Exception e) {
                                                    throw new JSONException("create instance error", e);
                                                }
                                            }
                                            setResolveStatus(2);
                                            if (this.context != null && obj != null && !(obj instanceof Integer) && !(this.context.fieldName instanceof Integer)) {
                                                popContext();
                                            }
                                            if (map.size() > 0) {
                                                Object objCast = TypeUtils.cast((Object) map, (Class<Object>) clsCheckAutoType, this.config);
                                                parseObject(objCast);
                                                setContext(parseContext);
                                                return objCast;
                                            }
                                            ObjectDeserializer deserializer2 = this.config.getDeserializer(clsCheckAutoType);
                                            Class<?> cls = deserializer2.getClass();
                                            if ((JavaBeanDeserializer.class.isAssignableFrom(cls) && cls != JavaBeanDeserializer.class && cls != ThrowableDeserializer.class) || (deserializer2 instanceof MapDeserializer)) {
                                                setResolveStatus(0);
                                            }
                                            Object objDeserialze = deserializer2.deserialze(this, clsCheckAutoType, obj);
                                            setContext(parseContext);
                                            return objDeserialze;
                                        }
                                        innerMap.put(JSON.DEFAULT_TYPE_KEY, strScanSymbol);
                                    }
                                    c2 = 4;
                                } else if (objScanSymbolUnQuoted == "$ref" || parseContext == null || jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                    if (!z4) {
                                        if (this.context == null && obj == this.context.fieldName && map == this.context.object) {
                                            parseContext = this.context;
                                        } else {
                                            context3 = setContext(map, obj);
                                            if (parseContext == null) {
                                                parseContext = context3;
                                            }
                                            z4 = true;
                                        }
                                    }
                                    if (map.getClass() == JSONObject.class && objScanSymbolUnQuoted == null) {
                                        objScanSymbolUnQuoted = "null";
                                    }
                                    if (current == '\"') {
                                        jSONLexer.scanString();
                                        strStringVal = jSONLexer.stringVal();
                                        if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                                            jSONScanner = new JSONScanner(strStringVal);
                                            if (jSONScanner.scanISO8601DateIfMatch()) {
                                                obj3 = strStringVal;
                                                time = strStringVal;
                                                time = jSONScanner.getCalendar().getTime();
                                            }
                                            obj3 = strStringVal;
                                            time = strStringVal;
                                            jSONScanner.close();
                                            obj3 = time;
                                        }
                                        obj3 = strStringVal;
                                        innerMap.put(objScanSymbolUnQuoted, obj3);
                                        obj2 = obj3;
                                    } else if ((current < '0' && current <= '9') || current == '-') {
                                        jSONLexer.scanNumber();
                                        Number numberIntegerValue = jSONLexer.token() == 2 ? jSONLexer.integerValue() : jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
                                        innerMap.put(objScanSymbolUnQuoted, numberIntegerValue);
                                        obj2 = numberIntegerValue;
                                    } else if (current == '[') {
                                        jSONLexer.nextToken();
                                        jSONArray = new JSONArray();
                                        if (obj != null) {
                                            obj.getClass();
                                        }
                                        if (obj == null) {
                                            setContext(parseContext);
                                        }
                                        parseArray(jSONArray, objScanSymbolUnQuoted);
                                        array = jSONArray;
                                        if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                            array = jSONArray.toArray();
                                        }
                                        innerMap.put(objScanSymbolUnQuoted, array);
                                        if (jSONLexer.token() == 13) {
                                            jSONLexer.nextToken();
                                            setContext(parseContext);
                                            return map;
                                        }
                                        if (jSONLexer.token() != 16) {
                                            throw new JSONException("syntax error");
                                        }
                                        c = 16;
                                    } else if (current == '{') {
                                        jSONLexer.nextToken();
                                        if (obj == null && obj.getClass() == Integer.class) {
                                            z2 = true;
                                        } else {
                                            z2 = false;
                                        }
                                        if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                                            jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                                        } else {
                                            jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                                        }
                                        if (z2) {
                                            context2 = null;
                                        } else {
                                            context2 = setContext(parseContext, jSONObject, objScanSymbolUnQuoted);
                                        }
                                        if (this.fieldTypeResolver == null) {
                                            z3 = false;
                                            object = null;
                                        } else {
                                            if (objScanSymbolUnQuoted != null) {
                                                string = objScanSymbolUnQuoted.toString();
                                            } else {
                                                string = null;
                                            }
                                            typeResolve = this.fieldTypeResolver.resolve(map, string);
                                            if (typeResolve != null) {
                                                object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                                                z3 = true;
                                            } else {
                                                z3 = false;
                                                object = null;
                                            }
                                        }
                                        if (!z3) {
                                            object = parseObject(jSONObject, objScanSymbolUnQuoted);
                                        }
                                        if (context2 != null && jSONObject != object) {
                                            context2.object = map;
                                        }
                                        if (objScanSymbolUnQuoted != null) {
                                            checkMapResolve(map, objScanSymbolUnQuoted.toString());
                                        }
                                        innerMap.put(objScanSymbolUnQuoted, object);
                                        if (z2) {
                                            setContext(object, objScanSymbolUnQuoted);
                                        }
                                        if (jSONLexer.token() == 13) {
                                            jSONLexer.nextToken();
                                            setContext(parseContext);
                                            setContext(parseContext);
                                            return map;
                                        }
                                        if (jSONLexer.token() != 16) {
                                            throw new JSONException("syntax error, " + jSONLexer.tokenName());
                                        }
                                        if (z2) {
                                            popContext();
                                        } else {
                                            setContext(parseContext);
                                        }
                                        c = 16;
                                    } else {
                                        jSONLexer.nextToken();
                                        innerMap.put(objScanSymbolUnQuoted, parse());
                                        if (jSONLexer.token() == 13) {
                                            jSONLexer.nextToken();
                                            setContext(parseContext);
                                            return map;
                                        }
                                        c = 16;
                                        if (jSONLexer.token() != 16) {
                                            throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                                        }
                                    }
                                    jSONLexer.skipWhitespace();
                                    current2 = jSONLexer.getCurrent();
                                    if (current2 != ',') {
                                        if (current2 == '}') {
                                            jSONLexer.next();
                                            jSONLexer.resetStringPosition();
                                            jSONLexer.nextToken();
                                            setContext(obj2, objScanSymbolUnQuoted);
                                            setContext(parseContext);
                                            return map;
                                        }
                                        throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                                    }
                                    jSONLexer.next();
                                    c = 16;
                                } else {
                                    c2 = 4;
                                    jSONLexer.nextToken(4);
                                    if (jSONLexer.token() != 4) {
                                        throw new JSONException("illegal ref, " + JSONToken.name(jSONLexer.token()));
                                    }
                                    String strStringVal2 = jSONLexer.stringVal();
                                    jSONLexer.nextToken(13);
                                    if (jSONLexer.token() != 16) {
                                        if ("@".equals(strStringVal2)) {
                                            if (this.context != null) {
                                                ParseContext parseContext3 = this.context;
                                                obj4 = parseContext3.object;
                                                if (!(obj4 instanceof Object[]) && !(obj4 instanceof Collection)) {
                                                    if (parseContext3.parent != null) {
                                                        obj4 = parseContext3.parent.object;
                                                    } else {
                                                        obj4 = null;
                                                    }
                                                }
                                            } else {
                                                obj4 = null;
                                            }
                                        } else if (!"..".equals(strStringVal2)) {
                                            if ("$".equals(strStringVal2)) {
                                                ParseContext parseContext4 = parseContext;
                                                while (parseContext4.parent != null) {
                                                    parseContext4 = parseContext4.parent;
                                                }
                                                if (parseContext4.object != null) {
                                                    obj4 = parseContext4.object;
                                                } else {
                                                    addResolveTask(new ResolveTask(parseContext4, strStringVal2));
                                                    setResolveStatus(1);
                                                }
                                            } else {
                                                addResolveTask(new ResolveTask(parseContext, strStringVal2));
                                                setResolveStatus(1);
                                            }
                                            obj4 = null;
                                        } else if (parseContext.object != null) {
                                            obj4 = parseContext.object;
                                        } else {
                                            addResolveTask(new ResolveTask(parseContext, strStringVal2));
                                            setResolveStatus(1);
                                            obj4 = null;
                                        }
                                        if (jSONLexer.token() == 13) {
                                            jSONLexer.nextToken(16);
                                            setContext(parseContext);
                                            return obj4;
                                        }
                                        throw new JSONException("syntax error, " + jSONLexer.info());
                                    }
                                    innerMap.put(objScanSymbolUnQuoted, strStringVal2);
                                }
                            } else {
                                jSONLexer.resetStringPosition();
                                jSONLexer.scanNumber();
                                try {
                                    Object objIntegerValue = jSONLexer.token() == 2 ? jSONLexer.integerValue() : jSONLexer.decimalValue(true);
                                    Object string2 = objIntegerValue;
                                    if (jSONLexer.isEnabled(Feature.NonStringKeyAsString)) {
                                        string2 = objIntegerValue.toString();
                                    }
                                    objScanSymbolUnQuoted = string2;
                                    if (jSONLexer.getCurrent() != ':') {
                                        throw new JSONException("parse number key error" + jSONLexer.info());
                                    }
                                } catch (NumberFormatException unused) {
                                    throw new JSONException("parse number key error" + jSONLexer.info());
                                }
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                        jSONLexer.next();
                        jSONLexer.skipWhitespace();
                    }
                    current = jSONLexer.getCurrent();
                    jSONLexer.resetStringPosition();
                    if (objScanSymbolUnQuoted != JSON.DEFAULT_TYPE_KEY) {
                        if (objScanSymbolUnQuoted == "$ref") {
                        }
                        if (!z4) {
                            if (this.context == null) {
                                context3 = setContext(map, obj);
                                if (parseContext == null) {
                                    parseContext = context3;
                                }
                                z4 = true;
                            } else {
                                context3 = setContext(map, obj);
                                if (parseContext == null) {
                                    parseContext = context3;
                                }
                                z4 = true;
                            }
                        }
                        if (map.getClass() == JSONObject.class) {
                            objScanSymbolUnQuoted = "null";
                        }
                        if (current == '\"') {
                            jSONLexer.scanString();
                            strStringVal = jSONLexer.stringVal();
                            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                                jSONScanner = new JSONScanner(strStringVal);
                                if (jSONScanner.scanISO8601DateIfMatch()) {
                                    obj3 = strStringVal;
                                    time = strStringVal;
                                    time = jSONScanner.getCalendar().getTime();
                                }
                                obj3 = strStringVal;
                                time = strStringVal;
                                jSONScanner.close();
                                obj3 = time;
                            }
                            obj3 = strStringVal;
                            innerMap.put(objScanSymbolUnQuoted, obj3);
                            obj2 = obj3;
                        } else if (current < '0') {
                            if (current == '[') {
                                jSONLexer.nextToken();
                                jSONArray = new JSONArray();
                                if (obj != null) {
                                    obj.getClass();
                                }
                                if (obj == null) {
                                    setContext(parseContext);
                                }
                                parseArray(jSONArray, objScanSymbolUnQuoted);
                                array = jSONArray;
                                if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                    array = jSONArray.toArray();
                                }
                                innerMap.put(objScanSymbolUnQuoted, array);
                                if (jSONLexer.token() == 13) {
                                    jSONLexer.nextToken();
                                    setContext(parseContext);
                                    return map;
                                }
                                if (jSONLexer.token() != 16) {
                                    throw new JSONException("syntax error");
                                }
                                c = 16;
                            } else if (current == '{') {
                                jSONLexer.nextToken();
                                if (obj == null) {
                                    z2 = false;
                                } else {
                                    z2 = false;
                                }
                                if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                                    jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                                } else {
                                    jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                                }
                                if (z2) {
                                    context2 = setContext(parseContext, jSONObject, objScanSymbolUnQuoted);
                                } else {
                                    context2 = null;
                                }
                                if (this.fieldTypeResolver == null) {
                                    z3 = false;
                                    object = null;
                                } else {
                                    if (objScanSymbolUnQuoted != null) {
                                        string = objScanSymbolUnQuoted.toString();
                                    } else {
                                        string = null;
                                    }
                                    typeResolve = this.fieldTypeResolver.resolve(map, string);
                                    if (typeResolve != null) {
                                        object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                        object = null;
                                    }
                                }
                                if (!z3) {
                                    object = parseObject(jSONObject, objScanSymbolUnQuoted);
                                }
                                if (context2 != null) {
                                    context2.object = map;
                                }
                                if (objScanSymbolUnQuoted != null) {
                                    checkMapResolve(map, objScanSymbolUnQuoted.toString());
                                }
                                innerMap.put(objScanSymbolUnQuoted, object);
                                if (z2) {
                                    setContext(object, objScanSymbolUnQuoted);
                                }
                                if (jSONLexer.token() == 13) {
                                    jSONLexer.nextToken();
                                    setContext(parseContext);
                                    setContext(parseContext);
                                    return map;
                                }
                                if (jSONLexer.token() != 16) {
                                    throw new JSONException("syntax error, " + jSONLexer.tokenName());
                                }
                                if (z2) {
                                    popContext();
                                } else {
                                    setContext(parseContext);
                                }
                                c = 16;
                            } else {
                                jSONLexer.nextToken();
                                innerMap.put(objScanSymbolUnQuoted, parse());
                                if (jSONLexer.token() == 13) {
                                    jSONLexer.nextToken();
                                    setContext(parseContext);
                                    return map;
                                }
                                c = 16;
                                if (jSONLexer.token() != 16) {
                                    throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                                }
                            }
                        } else if (current == '[') {
                            jSONLexer.nextToken();
                            jSONArray = new JSONArray();
                            if (obj != null) {
                                obj.getClass();
                            }
                            if (obj == null) {
                                setContext(parseContext);
                            }
                            parseArray(jSONArray, objScanSymbolUnQuoted);
                            array = jSONArray;
                            if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                array = jSONArray.toArray();
                            }
                            innerMap.put(objScanSymbolUnQuoted, array);
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                setContext(parseContext);
                                return map;
                            }
                            if (jSONLexer.token() != 16) {
                                throw new JSONException("syntax error");
                            }
                            c = 16;
                        } else if (current == '{') {
                            jSONLexer.nextToken();
                            if (obj == null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                                jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                            } else {
                                jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                            }
                            if (z2) {
                                context2 = setContext(parseContext, jSONObject, objScanSymbolUnQuoted);
                            } else {
                                context2 = null;
                            }
                            if (this.fieldTypeResolver == null) {
                                z3 = false;
                                object = null;
                            } else {
                                if (objScanSymbolUnQuoted != null) {
                                    string = objScanSymbolUnQuoted.toString();
                                } else {
                                    string = null;
                                }
                                typeResolve = this.fieldTypeResolver.resolve(map, string);
                                if (typeResolve != null) {
                                    object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                                    z3 = true;
                                } else {
                                    z3 = false;
                                    object = null;
                                }
                            }
                            if (!z3) {
                                object = parseObject(jSONObject, objScanSymbolUnQuoted);
                            }
                            if (context2 != null) {
                                context2.object = map;
                            }
                            if (objScanSymbolUnQuoted != null) {
                                checkMapResolve(map, objScanSymbolUnQuoted.toString());
                            }
                            innerMap.put(objScanSymbolUnQuoted, object);
                            if (z2) {
                                setContext(object, objScanSymbolUnQuoted);
                            }
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                setContext(parseContext);
                                setContext(parseContext);
                                return map;
                            }
                            if (jSONLexer.token() != 16) {
                                throw new JSONException("syntax error, " + jSONLexer.tokenName());
                            }
                            if (z2) {
                                popContext();
                            } else {
                                setContext(parseContext);
                            }
                            c = 16;
                        } else {
                            jSONLexer.nextToken();
                            innerMap.put(objScanSymbolUnQuoted, parse());
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                setContext(parseContext);
                                return map;
                            }
                            c = 16;
                            if (jSONLexer.token() != 16) {
                                throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                            }
                        }
                        jSONLexer.skipWhitespace();
                        current2 = jSONLexer.getCurrent();
                        if (current2 != ',') {
                            if (current2 == '}') {
                                jSONLexer.next();
                                jSONLexer.resetStringPosition();
                                jSONLexer.nextToken();
                                setContext(obj2, objScanSymbolUnQuoted);
                                setContext(parseContext);
                                return map;
                            }
                            throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                        }
                        jSONLexer.next();
                        c = 16;
                    } else {
                        if (objScanSymbolUnQuoted == "$ref") {
                        }
                        if (!z4) {
                            if (this.context == null) {
                                context3 = setContext(map, obj);
                                if (parseContext == null) {
                                    parseContext = context3;
                                }
                                z4 = true;
                            } else {
                                context3 = setContext(map, obj);
                                if (parseContext == null) {
                                    parseContext = context3;
                                }
                                z4 = true;
                            }
                        }
                        if (map.getClass() == JSONObject.class) {
                            objScanSymbolUnQuoted = "null";
                        }
                        if (current == '\"') {
                            jSONLexer.scanString();
                            strStringVal = jSONLexer.stringVal();
                            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                                jSONScanner = new JSONScanner(strStringVal);
                                if (jSONScanner.scanISO8601DateIfMatch()) {
                                    obj3 = strStringVal;
                                    time = strStringVal;
                                    time = jSONScanner.getCalendar().getTime();
                                }
                                obj3 = strStringVal;
                                time = strStringVal;
                                jSONScanner.close();
                                obj3 = time;
                            }
                            obj3 = strStringVal;
                            innerMap.put(objScanSymbolUnQuoted, obj3);
                            obj2 = obj3;
                        } else if (current < '0') {
                            if (current == '[') {
                                jSONLexer.nextToken();
                                jSONArray = new JSONArray();
                                if (obj != null) {
                                    obj.getClass();
                                }
                                if (obj == null) {
                                    setContext(parseContext);
                                }
                                parseArray(jSONArray, objScanSymbolUnQuoted);
                                array = jSONArray;
                                if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                    array = jSONArray.toArray();
                                }
                                innerMap.put(objScanSymbolUnQuoted, array);
                                if (jSONLexer.token() == 13) {
                                    jSONLexer.nextToken();
                                    setContext(parseContext);
                                    return map;
                                }
                                if (jSONLexer.token() != 16) {
                                    throw new JSONException("syntax error");
                                }
                                c = 16;
                            } else if (current == '{') {
                                jSONLexer.nextToken();
                                if (obj == null) {
                                    z2 = false;
                                } else {
                                    z2 = false;
                                }
                                if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                                    jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                                } else {
                                    jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                                }
                                if (z2) {
                                    context2 = setContext(parseContext, jSONObject, objScanSymbolUnQuoted);
                                } else {
                                    context2 = null;
                                }
                                if (this.fieldTypeResolver == null) {
                                    z3 = false;
                                    object = null;
                                } else {
                                    if (objScanSymbolUnQuoted != null) {
                                        string = objScanSymbolUnQuoted.toString();
                                    } else {
                                        string = null;
                                    }
                                    typeResolve = this.fieldTypeResolver.resolve(map, string);
                                    if (typeResolve != null) {
                                        object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                        object = null;
                                    }
                                }
                                if (!z3) {
                                    object = parseObject(jSONObject, objScanSymbolUnQuoted);
                                }
                                if (context2 != null) {
                                    context2.object = map;
                                }
                                if (objScanSymbolUnQuoted != null) {
                                    checkMapResolve(map, objScanSymbolUnQuoted.toString());
                                }
                                innerMap.put(objScanSymbolUnQuoted, object);
                                if (z2) {
                                    setContext(object, objScanSymbolUnQuoted);
                                }
                                if (jSONLexer.token() == 13) {
                                    jSONLexer.nextToken();
                                    setContext(parseContext);
                                    setContext(parseContext);
                                    return map;
                                }
                                if (jSONLexer.token() != 16) {
                                    throw new JSONException("syntax error, " + jSONLexer.tokenName());
                                }
                                if (z2) {
                                    popContext();
                                } else {
                                    setContext(parseContext);
                                }
                                c = 16;
                            } else {
                                jSONLexer.nextToken();
                                innerMap.put(objScanSymbolUnQuoted, parse());
                                if (jSONLexer.token() == 13) {
                                    jSONLexer.nextToken();
                                    setContext(parseContext);
                                    return map;
                                }
                                c = 16;
                                if (jSONLexer.token() != 16) {
                                    throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                                }
                            }
                        } else if (current == '[') {
                            jSONLexer.nextToken();
                            jSONArray = new JSONArray();
                            if (obj != null) {
                                obj.getClass();
                            }
                            if (obj == null) {
                                setContext(parseContext);
                            }
                            parseArray(jSONArray, objScanSymbolUnQuoted);
                            array = jSONArray;
                            if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                array = jSONArray.toArray();
                            }
                            innerMap.put(objScanSymbolUnQuoted, array);
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                setContext(parseContext);
                                return map;
                            }
                            if (jSONLexer.token() != 16) {
                                throw new JSONException("syntax error");
                            }
                            c = 16;
                        } else if (current == '{') {
                            jSONLexer.nextToken();
                            if (obj == null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                                jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                            } else {
                                jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                            }
                            if (z2) {
                                context2 = setContext(parseContext, jSONObject, objScanSymbolUnQuoted);
                            } else {
                                context2 = null;
                            }
                            if (this.fieldTypeResolver == null) {
                                z3 = false;
                                object = null;
                            } else {
                                if (objScanSymbolUnQuoted != null) {
                                    string = objScanSymbolUnQuoted.toString();
                                } else {
                                    string = null;
                                }
                                typeResolve = this.fieldTypeResolver.resolve(map, string);
                                if (typeResolve != null) {
                                    object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                                    z3 = true;
                                } else {
                                    z3 = false;
                                    object = null;
                                }
                            }
                            if (!z3) {
                                object = parseObject(jSONObject, objScanSymbolUnQuoted);
                            }
                            if (context2 != null) {
                                context2.object = map;
                            }
                            if (objScanSymbolUnQuoted != null) {
                                checkMapResolve(map, objScanSymbolUnQuoted.toString());
                            }
                            innerMap.put(objScanSymbolUnQuoted, object);
                            if (z2) {
                                setContext(object, objScanSymbolUnQuoted);
                            }
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                setContext(parseContext);
                                setContext(parseContext);
                                return map;
                            }
                            if (jSONLexer.token() != 16) {
                                throw new JSONException("syntax error, " + jSONLexer.tokenName());
                            }
                            if (z2) {
                                popContext();
                            } else {
                                setContext(parseContext);
                            }
                            c = 16;
                        } else {
                            jSONLexer.nextToken();
                            innerMap.put(objScanSymbolUnQuoted, parse());
                            if (jSONLexer.token() == 13) {
                                jSONLexer.nextToken();
                                setContext(parseContext);
                                return map;
                            }
                            c = 16;
                            if (jSONLexer.token() != 16) {
                                throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                            }
                        }
                        jSONLexer.skipWhitespace();
                        current2 = jSONLexer.getCurrent();
                        if (current2 != ',') {
                            if (current2 == '}') {
                                jSONLexer.next();
                                jSONLexer.resetStringPosition();
                                jSONLexer.nextToken();
                                setContext(obj2, objScanSymbolUnQuoted);
                                setContext(parseContext);
                                return map;
                            }
                            throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                        }
                        jSONLexer.next();
                        c = 16;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    setContext(parseContext);
                    throw th;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            parseContext = parseContext2;
        }
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type, Object obj) {
        int i = this.lexer.token();
        if (i == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                T t = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t;
            }
            if (type == char[].class) {
                String strStringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) strStringVal.toCharArray();
            }
        }
        ObjectDeserializer deserializer = this.config.getDeserializer(type);
        try {
            if (deserializer.getClass() == JavaBeanDeserializer.class) {
                return (T) ((JavaBeanDeserializer) deserializer).deserialze(this, type, obj, 0);
            }
            return (T) deserializer.deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        int i = this.lexer.token();
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token();
        }
        if (i != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(i) + ", " + this.lexer.info());
        }
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(deserializer.getFastMatchToken());
        }
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i2 = 0;
        while (true) {
            try {
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (this.lexer.token() == 16) {
                        this.lexer.nextToken();
                    }
                }
                if (this.lexer.token() != 15) {
                    Object objDeserialze = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    } else if (String.class == type) {
                        if (this.lexer.token() == 4) {
                            objDeserialze = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object obj2 = parse();
                            if (obj2 != null) {
                                objDeserialze = obj2.toString();
                            }
                        }
                        collection.add(objDeserialze);
                    } else {
                        if (this.lexer.token() == 8) {
                            this.lexer.nextToken();
                        } else {
                            objDeserialze = deserializer.deserialze(this, type, Integer.valueOf(i2));
                        }
                        collection.add(objDeserialze);
                        checkListResolve(collection);
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    i2++;
                } else {
                    setContext(parseContext);
                    this.lexer.nextToken(16);
                    return;
                }
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object objCast;
        Class<?> componentType;
        boolean zIsArray;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.lexer.nextToken(15);
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return new Object[0];
        }
        this.lexer.nextToken(2);
        for (int i = 0; i < typeArr.length; i++) {
            if (this.lexer.token() == 8) {
                this.lexer.nextToken(16);
                objCast = null;
            } else {
                Type type = typeArr[i];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.lexer.token() == 2) {
                        objCast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else if (type == String.class) {
                    if (this.lexer.token() == 4) {
                        objCast = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else {
                    if (i == typeArr.length - 1 && (type instanceof Class)) {
                        Class cls = (Class) type;
                        zIsArray = cls.isArray();
                        componentType = cls.getComponentType();
                    } else {
                        componentType = null;
                        zIsArray = false;
                    }
                    if (zIsArray && this.lexer.token() != 14) {
                        ArrayList arrayList = new ArrayList();
                        ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                        int fastMatchToken = deserializer.getFastMatchToken();
                        if (this.lexer.token() != 15) {
                            while (true) {
                                arrayList.add(deserializer.deserialze(this, type, null));
                                if (this.lexer.token() != 16) {
                                    break;
                                }
                                this.lexer.nextToken(fastMatchToken);
                            }
                            if (this.lexer.token() != 15) {
                                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                            }
                        }
                        objCast = TypeUtils.cast(arrayList, type, this.config);
                    } else {
                        objCast = this.config.getDeserializer(type).deserialze(this, type, Integer.valueOf(i));
                    }
                }
            }
            objArr[i] = objCast;
            if (this.lexer.token() == 15) {
                break;
            }
            if (this.lexer.token() != 16) {
                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
            }
            if (i == typeArr.length - 1) {
                this.lexer.nextToken(15);
            } else {
                this.lexer.nextToken(2);
            }
        }
        if (this.lexer.token() != 15) {
            throw new JSONException("syntax error");
        }
        this.lexer.nextToken(16);
        return objArr;
    }

    public void parseObject(Object obj) {
        Object objDeserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() != 12 && this.lexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
        while (true) {
            String strScanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (strScanSymbol == null) {
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token() != 16 || !this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(strScanSymbol) : null;
            if (fieldDeserializer == null) {
                if (!this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + strScanSymbol);
                }
                this.lexer.nextTokenWithColon();
                parse();
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken();
                    return;
                }
            } else {
                Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                Type type = fieldDeserializer.fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    objDeserialze = StringCodec.deserialze(this);
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = LongCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                    objDeserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, objDeserialze);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (Object.class.equals(type3)) {
                if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
                }
                throw new JSONException("not support type : " + type);
            }
            ArrayList arrayList2 = new ArrayList();
            parseArray((Class<?>) type3, (Collection) arrayList2);
            return arrayList2;
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        }
        if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
                return;
            }
            return;
        }
        throw new JSONException("type not match error");
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus == 1) {
            if (collection instanceof List) {
                int size = collection.size() - 1;
                ResolveTask lastResolveTask = getLastResolveTask();
                lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, size);
                lastResolveTask.ownerContext = this.context;
                setResolveStatus(0);
                return;
            }
            ResolveTask lastResolveTask2 = getLastResolveTask();
            lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
            lastResolveTask2.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    public final void parseArray(Collection collection, Object obj) {
        Number numberDecimalValue;
        Object time;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 21 || jSONLexer.token() == 22) {
            jSONLexer.nextToken();
        }
        if (jSONLexer.token() != 14) {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(jSONLexer.token()) + ", pos " + jSONLexer.pos() + ", fieldName " + obj);
        }
        jSONLexer.nextToken(4);
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i = 0;
        while (true) {
            try {
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (jSONLexer.token() == 16) {
                        jSONLexer.nextToken();
                    }
                }
                Object object = null;
                object = null;
                switch (jSONLexer.token()) {
                    case 2:
                        Number numberIntegerValue = jSONLexer.integerValue();
                        jSONLexer.nextToken(16);
                        object = numberIntegerValue;
                        break;
                    case 3:
                        if (jSONLexer.isEnabled(Feature.UseBigDecimal)) {
                            numberDecimalValue = jSONLexer.decimalValue(true);
                        } else {
                            numberDecimalValue = jSONLexer.decimalValue(false);
                        }
                        object = numberDecimalValue;
                        jSONLexer.nextToken(16);
                        break;
                    case 4:
                        String strStringVal = jSONLexer.stringVal();
                        jSONLexer.nextToken(16);
                        object = strStringVal;
                        if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                            JSONScanner jSONScanner = new JSONScanner(strStringVal);
                            if (jSONScanner.scanISO8601DateIfMatch()) {
                                time = strStringVal;
                                time = jSONScanner.getCalendar().getTime();
                            }
                            time = strStringVal;
                            jSONScanner.close();
                            object = time;
                        }
                        break;
                    case 6:
                        Boolean bool = Boolean.TRUE;
                        jSONLexer.nextToken(16);
                        object = bool;
                        break;
                    case 7:
                        Boolean bool2 = Boolean.FALSE;
                        jSONLexer.nextToken(16);
                        object = bool2;
                        break;
                    case 8:
                        jSONLexer.nextToken(4);
                        break;
                    case 12:
                        object = parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), Integer.valueOf(i));
                        break;
                    case 14:
                        JSONArray jSONArray = new JSONArray();
                        parseArray(jSONArray, Integer.valueOf(i));
                        object = jSONArray;
                        if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                            object = jSONArray.toArray();
                        }
                        break;
                    case 15:
                        jSONLexer.nextToken(16);
                        setContext(parseContext);
                        return;
                    case 20:
                        throw new JSONException("unclosed jsonArray");
                    case 23:
                        jSONLexer.nextToken(4);
                        break;
                    default:
                        object = parse();
                        break;
                }
                collection.add(object);
                checkListResolve(collection);
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
                i++;
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(this.resolveTaskList.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = parseContext;
    }

    public void popContext() {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.parent;
        if (this.contextArrayIndex <= 0) {
            return;
        }
        this.contextArrayIndex--;
        this.contextArray[this.contextArrayIndex] = null;
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        this.context = new ParseContext(parseContext, obj, obj2);
        addContext(this.context);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        if (this.contextArray == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= this.contextArray.length) {
            ParseContext[] parseContextArr = new ParseContext[(this.contextArray.length * 3) / 2];
            System.arraycopy(this.contextArray, 0, parseContextArr, 0, this.contextArray.length);
            this.contextArray = parseContextArr;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() == 18) {
            String strStringVal = this.lexer.stringVal();
            this.lexer.nextToken(16);
            return strStringVal;
        }
        return parse(null);
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        switch (jSONLexer.token()) {
            case 2:
                Number numberIntegerValue = jSONLexer.integerValue();
                jSONLexer.nextToken();
                return numberIntegerValue;
            case 3:
                Number numberDecimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
                jSONLexer.nextToken();
                return numberDecimalValue;
            case 4:
                String strStringVal = jSONLexer.stringVal();
                jSONLexer.nextToken(16);
                if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner jSONScanner = new JSONScanner(strStringVal);
                    try {
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            return jSONScanner.getCalendar().getTime();
                        }
                    } finally {
                        jSONScanner.close();
                    }
                }
                return strStringVal;
            case 5:
            case 10:
            case 11:
            case 13:
            case 15:
            case 16:
            case 17:
            case 19:
            case 24:
            case 25:
            default:
                throw new JSONException("syntax error, " + jSONLexer.info());
            case 6:
                jSONLexer.nextToken();
                return Boolean.TRUE;
            case 7:
                jSONLexer.nextToken();
                return Boolean.FALSE;
            case 8:
                jSONLexer.nextToken();
                return null;
            case 9:
                jSONLexer.nextToken(18);
                if (jSONLexer.token() != 18) {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken(10);
                accept(10);
                long jLongValue = jSONLexer.integerValue().longValue();
                accept(2);
                accept(11);
                return new Date(jLongValue);
            case 12:
                return parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
            case 14:
                JSONArray jSONArray = new JSONArray();
                parseArray(jSONArray, obj);
                return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
            case 18:
                if ("NaN".equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    return null;
                }
                throw new JSONException("syntax error, " + jSONLexer.info());
            case 20:
                if (jSONLexer.isBlankInput()) {
                    return null;
                }
                throw new JSONException("unterminated json string, " + jSONLexer.info());
            case 21:
                jSONLexer.nextToken();
                HashSet hashSet = new HashSet();
                parseArray(hashSet, obj);
                return hashSet;
            case 22:
                jSONLexer.nextToken();
                TreeSet treeSet = new TreeSet();
                parseArray(treeSet, obj);
                return treeSet;
            case 23:
                jSONLexer.nextToken();
                return null;
            case 26:
                byte[] bArrBytesValue = jSONLexer.bytesValue();
                jSONLexer.nextToken();
                return bArrBytesValue;
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
        } else {
            throwException(i);
        }
    }

    public void throwException(int i) {
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource) && jSONLexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
            }
            jSONLexer.close();
        } catch (Throwable th) {
            jSONLexer.close();
            throw th;
        }
    }

    public Object resolveReference(String str) {
        if (this.contextArray == null) {
            return null;
        }
        for (int i = 0; i < this.contextArray.length && i < this.contextArrayIndex; i++) {
            ParseContext parseContext = this.contextArray[i];
            if (parseContext.toString().equals(str)) {
                return parseContext.object;
            }
        }
        return null;
    }

    public void handleResovleTask(Object obj) {
        Object objEval;
        if (this.resolveTaskList == null) {
            return;
        }
        int size = this.resolveTaskList.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i);
            String str = resolveTask.referenceValue;
            Object obj2 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
            if (str.startsWith("$")) {
                objEval = getObject(str);
                if (objEval == null) {
                    try {
                        objEval = JSONPath.eval(obj, str);
                    } catch (JSONPathException unused) {
                    }
                }
            } else {
                objEval = resolveTask.context.object;
            }
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                if (objEval != null && objEval.getClass() == JSONObject.class && fieldDeserializer.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeserializer.fieldInfo.fieldClass)) {
                    objEval = JSONPath.eval(this.contextArray[0].object, str);
                }
                fieldDeserializer.setValue(obj2, objEval);
            }
        }
    }

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public void parseExtra(Object obj, String str) {
        Object object;
        this.lexer.nextTokenWithColon();
        Type extraType = null;
        if (this.extraTypeProviders != null) {
            Iterator<ExtraTypeProvider> it = this.extraTypeProviders.iterator();
            while (it.hasNext()) {
                extraType = it.next().getExtraType(obj, str);
            }
        }
        if (extraType == null) {
            object = parse();
        } else {
            object = parseObject(extraType);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, object);
            return;
        }
        if (this.extraProcessors != null) {
            Iterator<ExtraProcessor> it2 = this.extraProcessors.iterator();
            while (it2.hasNext()) {
                it2.next().processExtra(obj, str, object);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    public Object parse(PropertyProcessable propertyProcessable, Object obj) {
        String strScanSymbolUnQuoted;
        int i = 0;
        if (this.lexer.token() != 12) {
            String str = "syntax error, expect {, actual " + this.lexer.tokenName();
            if (obj instanceof String) {
                str = (str + ", fieldName ") + obj;
            }
            String str2 = (str + ", ") + this.lexer.info();
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            if (jSONArray.size() == 1) {
                Object obj2 = jSONArray.get(0);
                if (obj2 instanceof JSONObject) {
                    return (JSONObject) obj2;
                }
            }
            throw new JSONException(str2);
        }
        ParseContext parseContext = this.context;
        while (true) {
            try {
                this.lexer.skipWhitespace();
                char current = this.lexer.getCurrent();
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (current == ',') {
                        this.lexer.next();
                        this.lexer.skipWhitespace();
                        current = this.lexer.getCurrent();
                    }
                }
                if (current == '\"') {
                    strScanSymbolUnQuoted = this.lexer.scanSymbol(this.symbolTable, '\"');
                    this.lexer.skipWhitespace();
                    if (this.lexer.getCurrent() != ':') {
                        throw new JSONException("expect ':' at " + this.lexer.pos());
                    }
                } else {
                    if (current == '}') {
                        this.lexer.next();
                        this.lexer.resetStringPosition();
                        this.lexer.nextToken(16);
                        setContext(parseContext);
                        return propertyProcessable;
                    }
                    if (current == '\'') {
                        if (!this.lexer.isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("syntax error");
                        }
                        strScanSymbolUnQuoted = this.lexer.scanSymbol(this.symbolTable, '\'');
                        this.lexer.skipWhitespace();
                        if (this.lexer.getCurrent() != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos());
                        }
                    } else {
                        if (!this.lexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                            throw new JSONException("syntax error");
                        }
                        strScanSymbolUnQuoted = this.lexer.scanSymbolUnQuoted(this.symbolTable);
                        this.lexer.skipWhitespace();
                        char current2 = this.lexer.getCurrent();
                        if (current2 != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos() + ", actual " + current2);
                        }
                    }
                }
                this.lexer.next();
                this.lexer.skipWhitespace();
                this.lexer.getCurrent();
                this.lexer.resetStringPosition();
                Object object = null;
                if (strScanSymbolUnQuoted != JSON.DEFAULT_TYPE_KEY || this.lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    this.lexer.nextToken();
                    if (i != 0) {
                        setContext(parseContext);
                    }
                    Type type = propertyProcessable.getType(strScanSymbolUnQuoted);
                    if (this.lexer.token() == 8) {
                        this.lexer.nextToken();
                    } else {
                        object = parseObject(type, strScanSymbolUnQuoted);
                    }
                    propertyProcessable.apply(strScanSymbolUnQuoted, object);
                    setContext(parseContext, object, strScanSymbolUnQuoted);
                    setContext(parseContext);
                    int i2 = this.lexer.token();
                    if (i2 == 20 || i2 == 15) {
                        break;
                        break;
                    }
                    if (i2 == 13) {
                        this.lexer.nextToken();
                        setContext(parseContext);
                        return propertyProcessable;
                    }
                } else {
                    Class<?> clsCheckAutoType = this.config.checkAutoType(this.lexer.scanSymbol(this.symbolTable, '\"'), null, this.lexer.getFeatures());
                    if (!Map.class.isAssignableFrom(clsCheckAutoType)) {
                        ObjectDeserializer deserializer = this.config.getDeserializer(clsCheckAutoType);
                        this.lexer.nextToken(16);
                        setResolveStatus(2);
                        if (parseContext != null && !(obj instanceof Integer)) {
                            popContext();
                        }
                        Map map = (Map) deserializer.deserialze(this, clsCheckAutoType, obj);
                        setContext(parseContext);
                        return map;
                    }
                    this.lexer.nextToken(16);
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        setContext(parseContext);
                        return propertyProcessable;
                    }
                }
                i++;
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
        setContext(parseContext);
        return propertyProcessable;
    }
}
