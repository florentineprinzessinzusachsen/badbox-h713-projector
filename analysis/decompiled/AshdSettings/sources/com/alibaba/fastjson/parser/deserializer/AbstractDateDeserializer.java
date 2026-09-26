package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDateDeserializer extends ContextObjectDeserializer implements ObjectDeserializer {
    protected abstract <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    @Override // com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null, 0);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i) throws Throwable {
        SimpleDateFormat simpleDateFormat;
        Object obj2;
        Object obj3;
        SimpleDateFormat simpleDateFormat2;
        Object obj4;
        Object time;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        Object objValueOf = null;
        objValueOf = null;
        objValueOf = null;
        objValueOf = null;
        objValueOf = null;
        if (jSONLexer.token() == 2) {
            objValueOf = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
        } else if (jSONLexer.token() == 4) {
            String strStringVal = jSONLexer.stringVal();
            if (str != null) {
                try {
                    simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                } catch (IllegalArgumentException e) {
                    if (str.contains("T")) {
                        try {
                            simpleDateFormat = new SimpleDateFormat(str.replaceAll("T", "'T'"), JSON.defaultLocale);
                        } catch (IllegalArgumentException unused) {
                            throw e;
                        }
                    } else {
                        simpleDateFormat = null;
                    }
                }
                if (JSON.defaultTimeZone != null) {
                    simpleDateFormat.setTimeZone(JSON.defaultTimeZone);
                }
                try {
                    obj2 = simpleDateFormat.parse(strStringVal);
                } catch (ParseException unused2) {
                    obj2 = null;
                }
                if (obj2 == null && JSON.defaultLocale == Locale.CHINA) {
                    try {
                        simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
                    } catch (IllegalArgumentException e2) {
                        simpleDateFormat2 = simpleDateFormat;
                        if (str.contains("T")) {
                            try {
                                simpleDateFormat2 = new SimpleDateFormat(str.replaceAll("T", "'T'"), JSON.defaultLocale);
                            } catch (IllegalArgumentException unused3) {
                                throw e2;
                            }
                        }
                    }
                    try {
                        obj3 = simpleDateFormat2.parse(strStringVal);
                    } catch (ParseException unused4) {
                        obj3 = null;
                    }
                } else {
                    obj3 = obj2;
                }
                if (obj3 != null) {
                    objValueOf = obj3;
                } else if (str.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && strStringVal.length() == 19) {
                    try {
                        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", JSON.defaultLocale);
                        simpleDateFormat3.setTimeZone(JSON.defaultTimeZone);
                        objValueOf = simpleDateFormat3.parse(strStringVal);
                    } catch (ParseException unused5) {
                    }
                }
            }
            if (objValueOf == null) {
                jSONLexer.nextToken(16);
                if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                    JSONScanner jSONScanner = new JSONScanner(strStringVal);
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        obj4 = strStringVal;
                        time = strStringVal;
                        time = jSONScanner.getCalendar().getTime();
                    }
                    obj4 = strStringVal;
                    time = strStringVal;
                    jSONScanner.close();
                    obj4 = time;
                }
                obj4 = strStringVal;
                objValueOf = obj4;
            }
        } else if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
        } else if (jSONLexer.token() == 12) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 4) {
                if (JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Type typeCheckAutoType = defaultJSONParser.getConfig().checkAutoType(jSONLexer.stringVal(), null, jSONLexer.getFeatures());
                    if (typeCheckAutoType != null) {
                        type = typeCheckAutoType;
                    }
                    defaultJSONParser.accept(4);
                    defaultJSONParser.accept(16);
                }
                jSONLexer.nextTokenWithColon(2);
                if (jSONLexer.token() == 2) {
                    long jLongValue = jSONLexer.longValue();
                    jSONLexer.nextToken();
                    objValueOf = Long.valueOf(jLongValue);
                    defaultJSONParser.accept(13);
                } else {
                    throw new JSONException("syntax error : " + jSONLexer.tokenName());
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else if (defaultJSONParser.getResolveStatus() == 2) {
            defaultJSONParser.setResolveStatus(0);
            defaultJSONParser.accept(16);
            if (jSONLexer.token() == 4) {
                if (!"val".equals(jSONLexer.stringVal())) {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                objValueOf = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            objValueOf = defaultJSONParser.parse();
        }
        return (T) cast(defaultJSONParser, type, obj, objValueOf);
    }
}
