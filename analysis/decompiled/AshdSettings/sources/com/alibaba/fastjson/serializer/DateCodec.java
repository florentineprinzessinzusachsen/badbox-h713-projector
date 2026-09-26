package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class DateCodec extends AbstractDateDeserializer implements ObjectSerializer, ObjectDeserializer {
    public static final DateCodec instance = new DateCodec();

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        Date dateCastToDate;
        char[] charArray;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj instanceof Date) {
            dateCastToDate = (Date) obj;
        } else {
            dateCastToDate = TypeUtils.castToDate(obj);
        }
        if (serializeWriter.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormat = jSONSerializer.getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, jSONSerializer.locale);
                dateFormat.setTimeZone(jSONSerializer.timeZone);
            }
            serializeWriter.writeString(dateFormat.format(dateCastToDate));
            return;
        }
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName) && obj.getClass() != type) {
            if (obj.getClass() == Date.class) {
                serializeWriter.write("new Date(");
                serializeWriter.writeLong(((Date) obj).getTime());
                serializeWriter.write(41);
                return;
            } else {
                serializeWriter.write(123);
                serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
                jSONSerializer.write(obj.getClass().getName());
                serializeWriter.writeFieldValue(',', "val", ((Date) obj).getTime());
                serializeWriter.write(125);
                return;
            }
        }
        long time = dateCastToDate.getTime();
        if (serializeWriter.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            int i2 = serializeWriter.isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            serializeWriter.write(i2);
            Calendar calendar = Calendar.getInstance(jSONSerializer.timeZone, jSONSerializer.locale);
            calendar.setTimeInMillis(time);
            int i3 = calendar.get(1);
            int i4 = calendar.get(2) + 1;
            int i5 = calendar.get(5);
            int i6 = calendar.get(11);
            int i7 = calendar.get(12);
            int i8 = calendar.get(13);
            int i9 = calendar.get(14);
            if (i9 != 0) {
                charArray = "0000-00-00T00:00:00.000".toCharArray();
                IOUtils.getChars(i9, 23, charArray);
                IOUtils.getChars(i8, 19, charArray);
                IOUtils.getChars(i7, 16, charArray);
                IOUtils.getChars(i6, 13, charArray);
                IOUtils.getChars(i5, 10, charArray);
                IOUtils.getChars(i4, 7, charArray);
                IOUtils.getChars(i3, 4, charArray);
            } else if (i8 == 0 && i7 == 0 && i6 == 0) {
                charArray = "0000-00-00".toCharArray();
                IOUtils.getChars(i5, 10, charArray);
                IOUtils.getChars(i4, 7, charArray);
                IOUtils.getChars(i3, 4, charArray);
            } else {
                charArray = "0000-00-00T00:00:00".toCharArray();
                IOUtils.getChars(i8, 19, charArray);
                IOUtils.getChars(i7, 16, charArray);
                IOUtils.getChars(i6, 13, charArray);
                IOUtils.getChars(i5, 10, charArray);
                IOUtils.getChars(i4, 7, charArray);
                IOUtils.getChars(i3, 4, charArray);
            }
            serializeWriter.write(charArray);
            int offset = calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 3600000;
            if (offset == 0) {
                serializeWriter.write(90);
            } else {
                if (offset > 9) {
                    serializeWriter.write(43);
                    serializeWriter.writeInt(offset);
                } else if (offset > 0) {
                    serializeWriter.write(43);
                    serializeWriter.write(48);
                    serializeWriter.writeInt(offset);
                } else if (offset < -9) {
                    serializeWriter.write(45);
                    serializeWriter.writeInt(offset);
                } else if (offset < 0) {
                    serializeWriter.write(45);
                    serializeWriter.write(48);
                    serializeWriter.writeInt(-offset);
                }
                serializeWriter.append((CharSequence) ":00");
            }
            serializeWriter.write(i2);
            return;
        }
        serializeWriter.writeLong(time);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r2v12, types: [T, java.util.Calendar] */
    @Override // com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer
    public <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == 0) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof BigDecimal) {
            return (T) new Date(TypeUtils.longValue((BigDecimal) obj2));
        }
        if (obj2 instanceof Number) {
            return (T) new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String strSubstring = (String) obj2;
            if (strSubstring.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(strSubstring);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    ?? r2 = (T) jSONScanner.getCalendar();
                    if (type != Calendar.class) {
                        T t = (T) r2.getTime();
                        jSONScanner.close();
                        return t;
                    }
                    jSONScanner.close();
                    return r2;
                }
                jSONScanner.close();
                if (strSubstring.length() == defaultJSONParser.getDateFomartPattern().length() || (strSubstring.length() == 22 && defaultJSONParser.getDateFomartPattern().equals("yyyyMMddHHmmssSSSZ"))) {
                    try {
                        return (T) defaultJSONParser.getDateFormat().parse(strSubstring);
                    } catch (ParseException unused) {
                    }
                }
                if (strSubstring.startsWith("/Date(") && strSubstring.endsWith(")/")) {
                    strSubstring = strSubstring.substring(6, strSubstring.length() - 2);
                }
                if ("0000-00-00".equals(strSubstring) || "0000-00-00T00:00:00".equalsIgnoreCase(strSubstring) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(strSubstring)) {
                    return null;
                }
                int iLastIndexOf = strSubstring.lastIndexOf(124);
                if (iLastIndexOf > 20) {
                    TimeZone timeZone = TimeZone.getTimeZone(strSubstring.substring(iLastIndexOf + 1));
                    if (!"GMT".equals(timeZone.getID())) {
                        JSONScanner jSONScanner2 = new JSONScanner(strSubstring.substring(0, iLastIndexOf));
                        try {
                            if (jSONScanner2.scanISO8601DateIfMatch(false)) {
                                ?? r3 = (T) jSONScanner2.getCalendar();
                                r3.setTimeZone(timeZone);
                                return type == Calendar.class ? r3 : (T) r3.getTime();
                            }
                        } finally {
                            jSONScanner2.close();
                        }
                    }
                }
                return (T) new Date(Long.parseLong(strSubstring));
            } catch (Throwable th) {
                jSONScanner.close();
                throw th;
            }
        }
        throw new JSONException("parse error");
    }
}
