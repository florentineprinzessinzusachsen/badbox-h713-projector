package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
public class CalendarCodec implements ObjectSerializer, ObjectDeserializer {
    public static final CalendarCodec instance = new CalendarCodec();
    private DatatypeFactory dateFactory;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 2;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        Calendar gregorianCalendar;
        char[] charArray;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj instanceof XMLGregorianCalendar) {
            gregorianCalendar = ((XMLGregorianCalendar) obj).toGregorianCalendar();
        } else {
            gregorianCalendar = (Calendar) obj;
        }
        if (serializeWriter.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
            char c = serializeWriter.isEnabled(SerializerFeature.UseSingleQuotes) ? '\'' : '\"';
            serializeWriter.append(c);
            int i2 = gregorianCalendar.get(1);
            int i3 = gregorianCalendar.get(2) + 1;
            int i4 = gregorianCalendar.get(5);
            int i5 = gregorianCalendar.get(11);
            int i6 = gregorianCalendar.get(12);
            int i7 = gregorianCalendar.get(13);
            int i8 = gregorianCalendar.get(14);
            if (i8 != 0) {
                charArray = "0000-00-00T00:00:00.000".toCharArray();
                IOUtils.getChars(i8, 23, charArray);
                IOUtils.getChars(i7, 19, charArray);
                IOUtils.getChars(i6, 16, charArray);
                IOUtils.getChars(i5, 13, charArray);
                IOUtils.getChars(i4, 10, charArray);
                IOUtils.getChars(i3, 7, charArray);
                IOUtils.getChars(i2, 4, charArray);
            } else if (i7 == 0 && i6 == 0 && i5 == 0) {
                charArray = "0000-00-00".toCharArray();
                IOUtils.getChars(i4, 10, charArray);
                IOUtils.getChars(i3, 7, charArray);
                IOUtils.getChars(i2, 4, charArray);
            } else {
                charArray = "0000-00-00T00:00:00".toCharArray();
                IOUtils.getChars(i7, 19, charArray);
                IOUtils.getChars(i6, 16, charArray);
                IOUtils.getChars(i5, 13, charArray);
                IOUtils.getChars(i4, 10, charArray);
                IOUtils.getChars(i3, 7, charArray);
                IOUtils.getChars(i2, 4, charArray);
            }
            serializeWriter.write(charArray);
            int offset = gregorianCalendar.getTimeZone().getOffset(gregorianCalendar.getTimeInMillis()) / 3600000;
            if (offset == 0) {
                serializeWriter.append((CharSequence) "Z");
            } else if (offset > 0) {
                serializeWriter.append((CharSequence) "+").append((CharSequence) String.format("%02d", Integer.valueOf(offset))).append((CharSequence) ":00");
            } else {
                serializeWriter.append((CharSequence) "-").append((CharSequence) String.format("%02d", Integer.valueOf(-offset))).append((CharSequence) ":00");
            }
            serializeWriter.append(c);
            return;
        }
        jSONSerializer.write(gregorianCalendar.getTime());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.util.Calendar] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t = (T) DateCodec.instance.deserialze(defaultJSONParser, type, obj);
        if (t instanceof Calendar) {
            return t;
        }
        Date date = (Date) t;
        if (date == null) {
            return null;
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        ?? r2 = (T) Calendar.getInstance(jSONLexer.getTimeZone(), jSONLexer.getLocale());
        r2.setTime(date);
        return type == XMLGregorianCalendar.class ? (T) createXMLGregorianCalendar((GregorianCalendar) r2) : r2;
    }

    public XMLGregorianCalendar createXMLGregorianCalendar(Calendar calendar) {
        if (this.dateFactory == null) {
            try {
                this.dateFactory = DatatypeFactory.newInstance();
            } catch (DatatypeConfigurationException e) {
                throw new IllegalStateException("Could not obtain an instance of DatatypeFactory.", e);
            }
        }
        return this.dateFactory.newXMLGregorianCalendar((GregorianCalendar) calendar);
    }
}
