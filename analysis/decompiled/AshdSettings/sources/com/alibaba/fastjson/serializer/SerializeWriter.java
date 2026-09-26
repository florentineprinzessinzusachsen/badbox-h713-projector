package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.RyuDouble;
import com.alibaba.fastjson.util.RyuFloat;
import com.android.settingslib.accessibility.AccessibilityUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SerializeWriter extends Writer {
    private static int BUFFER_THRESHOLD = 131072;
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
    private static final ThreadLocal<byte[]> bytesBufLocal = new ThreadLocal<>();
    static final int nonDirectFeatures;
    protected boolean beanToArray;
    protected boolean browserSecure;
    protected char[] buf;
    protected int count;
    protected boolean disableCircularReferenceDetect;
    protected int features;
    protected char keySeperator;
    protected int maxBufSize;
    protected boolean notWriteDefaultValue;
    protected boolean quoteFieldNames;
    protected long sepcialBits;
    protected boolean sortField;
    protected boolean useSingleQuotes;
    protected boolean writeDirect;
    protected boolean writeEnumUsingName;
    protected boolean writeEnumUsingToString;
    protected boolean writeNonStringValueAsString;
    private final Writer writer;

    static {
        int i;
        try {
            String stringProperty = IOUtils.getStringProperty("fastjson.serializer_buffer_threshold");
            if (stringProperty != null && stringProperty.length() > 0 && (i = Integer.parseInt(stringProperty)) >= 64 && i <= 65536) {
                BUFFER_THRESHOLD = i * 1024;
            }
        } catch (Throwable unused) {
        }
        nonDirectFeatures = SerializerFeature.UseSingleQuotes.mask | 0 | SerializerFeature.BrowserCompatible.mask | SerializerFeature.PrettyFormat.mask | SerializerFeature.WriteEnumUsingToString.mask | SerializerFeature.WriteNonStringValueAsString.mask | SerializerFeature.WriteSlashAsSpecial.mask | SerializerFeature.IgnoreErrorGetter.mask | SerializerFeature.WriteClassName.mask | SerializerFeature.NotWriteDefaultValue.mask;
    }

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this(writer, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this((Writer) null, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, SerializerFeature... serializerFeatureArr) {
        this(writer, 0, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, int i, SerializerFeature... serializerFeatureArr) {
        this.maxBufSize = -1;
        this.writer = writer;
        this.buf = bufLocal.get();
        if (this.buf != null) {
            bufLocal.set(null);
        } else {
            this.buf = new char[2048];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i |= serializerFeature.getMask();
        }
        this.features = i;
        computeFeatures();
    }

    public int getMaxBufSize() {
        return this.maxBufSize;
    }

    public void setMaxBufSize(int i) {
        if (i < this.buf.length) {
            throw new JSONException("must > " + this.buf.length);
        }
        this.maxBufSize = i;
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public SerializeWriter(int i) {
        this((Writer) null, i);
    }

    public SerializeWriter(Writer writer, int i) {
        this.maxBufSize = -1;
        this.writer = writer;
        if (i <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        this.buf = new char[i];
        computeFeatures();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.features |= serializerFeature.getMask();
            if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                this.features &= ~SerializerFeature.WriteEnumUsingName.getMask();
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.features &= ~SerializerFeature.WriteEnumUsingToString.getMask();
            }
        } else {
            this.features = (~serializerFeature.getMask()) & this.features;
        }
        computeFeatures();
    }

    protected void computeFeatures() {
        long j;
        this.quoteFieldNames = (this.features & SerializerFeature.QuoteFieldNames.mask) != 0;
        this.useSingleQuotes = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
        this.sortField = (this.features & SerializerFeature.SortField.mask) != 0;
        this.disableCircularReferenceDetect = (this.features & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
        this.beanToArray = (this.features & SerializerFeature.BeanToArray.mask) != 0;
        this.writeNonStringValueAsString = (this.features & SerializerFeature.WriteNonStringValueAsString.mask) != 0;
        this.notWriteDefaultValue = (this.features & SerializerFeature.NotWriteDefaultValue.mask) != 0;
        this.writeEnumUsingName = (this.features & SerializerFeature.WriteEnumUsingName.mask) != 0;
        this.writeEnumUsingToString = (this.features & SerializerFeature.WriteEnumUsingToString.mask) != 0;
        this.writeDirect = this.quoteFieldNames && (this.features & nonDirectFeatures) == 0 && (this.beanToArray || this.writeEnumUsingName);
        this.keySeperator = this.useSingleQuotes ? '\'' : '\"';
        this.browserSecure = (this.features & SerializerFeature.BrowserSecure.mask) != 0;
        if (this.browserSecure) {
            j = 5764610843043954687L;
        } else {
            j = (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 21474836479L;
        }
        this.sepcialBits = j;
    }

    public boolean isSortField() {
        return this.sortField;
    }

    public boolean isNotWriteDefaultValue() {
        return this.notWriteDefaultValue;
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return (this.features & serializerFeature.mask) != 0;
    }

    public boolean isEnabled(int i) {
        return (this.features & i) != 0;
    }

    @Override // java.io.Writer
    public void write(int i) {
        int i2 = this.count + 1;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                flush();
                i2 = 1;
            }
        }
        this.buf[this.count] = (char) i;
        this.count = i2;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.count + i2;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            } else {
                do {
                    int length = this.buf.length - this.count;
                    System.arraycopy(cArr, i, this.buf, this.count, length);
                    this.count = this.buf.length;
                    flush();
                    i2 -= length;
                    i += length;
                } while (i2 > this.buf.length);
                i4 = i2;
            }
        }
        System.arraycopy(cArr, i, this.buf, this.count, i2);
        this.count = i4;
    }

    public void expandCapacity(int i) {
        char[] cArr;
        if (this.maxBufSize != -1 && i >= this.maxBufSize) {
            throw new JSONException("serialize exceeded MAX_OUTPUT_LENGTH=" + this.maxBufSize + ", minimumCapacity=" + i);
        }
        int length = this.buf.length + (this.buf.length >> 1) + 1;
        if (length >= i) {
            i = length;
        }
        char[] cArr2 = new char[i];
        System.arraycopy(this.buf, 0, cArr2, 0, this.count);
        if (this.buf.length < BUFFER_THRESHOLD && ((cArr = bufLocal.get()) == null || cArr.length < this.buf.length)) {
            bufLocal.set(this.buf);
        }
        this.buf = cArr2;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence) {
        String string = charSequence == null ? "null" : charSequence.toString();
        write(string, 0, string.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String string = charSequence.subSequence(i, i2).toString();
        write(string, 0, string.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(char c) {
        write(c);
        return this;
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        int i3;
        int i4 = this.count + i2;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            } else {
                while (true) {
                    int length = this.buf.length - this.count;
                    i3 = i + length;
                    str.getChars(i, i3, this.buf, this.count);
                    this.count = this.buf.length;
                    flush();
                    i2 -= length;
                    if (i2 <= this.buf.length) {
                        break;
                    } else {
                        i = i3;
                    }
                }
                i4 = i2;
                i = i3;
            }
        }
        str.getChars(i, i2 + i, this.buf, this.count);
        this.count = i4;
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        writer.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        writeToEx(outputStream, charset);
    }

    public int writeToEx(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return encodeToUTF8(outputStream);
        }
        byte[] bytes = new String(this.buf, 0, this.count).getBytes(charset);
        outputStream.write(bytes);
        return bytes.length;
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        char[] cArr = new char[this.count];
        System.arraycopy(this.buf, 0, cArr, 0, this.count);
        return cArr;
    }

    public char[] toCharArrayForSpringWebSocket() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        char[] cArr = new char[this.count - 2];
        System.arraycopy(this.buf, 1, cArr, 0, this.count - 2);
        return cArr;
    }

    public byte[] toBytes(String str) {
        Charset charsetForName;
        if (str == null || "UTF-8".equals(str)) {
            charsetForName = IOUtils.UTF8;
        } else {
            charsetForName = Charset.forName(str);
        }
        return toBytes(charsetForName);
    }

    public byte[] toBytes(Charset charset) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return encodeToUTF8Bytes();
        }
        return new String(this.buf, 0, this.count).getBytes(charset);
    }

    private int encodeToUTF8(OutputStream outputStream) throws IOException {
        int i = (int) (((double) this.count) * 3.0d);
        byte[] bArr = bytesBufLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            bytesBufLocal.set(bArr);
        }
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        int iEncodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        outputStream.write(bArr, 0, iEncodeUTF8);
        return iEncodeUTF8;
    }

    private byte[] encodeToUTF8Bytes() {
        int i = (int) (((double) this.count) * 3.0d);
        byte[] bArr = bytesBufLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            bytesBufLocal.set(bArr);
        }
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        int iEncodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        byte[] bArr2 = new byte[iEncodeUTF8];
        System.arraycopy(bArr, 0, bArr2, 0, iEncodeUTF8);
        return bArr2;
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        if (this.buf.length <= BUFFER_THRESHOLD) {
            bufLocal.set(this.buf);
        }
        this.buf = null;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int iStringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int i2 = this.count + iStringSize;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                char[] cArr = new char[iStringSize];
                IOUtils.getChars(i, iStringSize, cArr);
                write(cArr, 0, cArr.length);
                return;
            }
        }
        IOUtils.getChars(i, i2, this.buf);
        this.count = i2;
    }

    public void writeByteArray(byte[] bArr) {
        if (isEnabled(SerializerFeature.WriteClassName.mask)) {
            writeHex(bArr);
            return;
        }
        int length = bArr.length;
        char c = this.useSingleQuotes ? '\'' : '\"';
        if (length == 0) {
            write(this.useSingleQuotes ? "''" : "\"\"");
            return;
        }
        char[] cArr = IOUtils.CA;
        int i = (length / 3) * 3;
        int i2 = length - 1;
        int i3 = this.count;
        int i4 = this.count + (((i2 / 3) + 1) << 2) + 2;
        if (i4 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                int i5 = 0;
                while (i5 < i) {
                    int i6 = i5 + 1;
                    int i7 = i6 + 1;
                    int i8 = ((bArr[i5] & 255) << 16) | ((bArr[i6] & 255) << 8) | (bArr[i7] & 255);
                    write(cArr[(i8 >>> 18) & 63]);
                    write(cArr[(i8 >>> 12) & 63]);
                    write(cArr[(i8 >>> 6) & 63]);
                    write(cArr[i8 & 63]);
                    i5 = i7 + 1;
                }
                int i9 = length - i;
                if (i9 > 0) {
                    int i10 = ((bArr[i] & 255) << 10) | (i9 == 2 ? (bArr[i2] & 255) << 2 : 0);
                    write(cArr[i10 >> 12]);
                    write(cArr[(i10 >>> 6) & 63]);
                    write(i9 == 2 ? cArr[i10 & 63] : '=');
                    write(61);
                }
                write(c);
                return;
            }
            expandCapacity(i4);
        }
        this.count = i4;
        int i11 = i3 + 1;
        this.buf[i3] = c;
        int i12 = 0;
        while (i12 < i) {
            int i13 = i12 + 1;
            int i14 = i13 + 1;
            int i15 = ((bArr[i12] & 255) << 16) | ((bArr[i13] & 255) << 8);
            int i16 = i14 + 1;
            int i17 = i15 | (bArr[i14] & 255);
            int i18 = i11 + 1;
            this.buf[i11] = cArr[(i17 >>> 18) & 63];
            int i19 = i18 + 1;
            this.buf[i18] = cArr[(i17 >>> 12) & 63];
            int i20 = i19 + 1;
            this.buf[i19] = cArr[(i17 >>> 6) & 63];
            this.buf[i20] = cArr[i17 & 63];
            i12 = i16;
            i11 = i20 + 1;
        }
        int i21 = length - i;
        if (i21 > 0) {
            int i22 = ((bArr[i] & 255) << 10) | (i21 == 2 ? (bArr[i2] & 255) << 2 : 0);
            this.buf[i4 - 5] = cArr[i22 >> 12];
            this.buf[i4 - 4] = cArr[(i22 >>> 6) & 63];
            this.buf[i4 - 3] = i21 == 2 ? cArr[i22 & 63] : '=';
            this.buf[i4 - 2] = '=';
        }
        this.buf[i4 - 1] = c;
    }

    public void writeHex(byte[] bArr) {
        int i = 2;
        int length = this.count + (bArr.length * 2) + 3;
        int i2 = 0;
        if (length > this.buf.length) {
            if (this.writer != null) {
                char[] cArr = new char[bArr.length + 3];
                cArr[0] = 'x';
                cArr[1] = '\'';
                while (i2 < bArr.length) {
                    int i3 = bArr[i2] & 255;
                    int i4 = i3 >> 4;
                    int i5 = i3 & 15;
                    int i6 = i + 1;
                    cArr[i] = (char) (i4 + (i4 < 10 ? 48 : 55));
                    i = i6 + 1;
                    cArr[i6] = (char) (i5 + (i5 < 10 ? 48 : 55));
                    i2++;
                }
                cArr[i] = '\'';
                try {
                    this.writer.write(cArr);
                    return;
                } catch (IOException e) {
                    throw new JSONException("writeBytes error.", e);
                }
            }
            expandCapacity(length);
        }
        char[] cArr2 = this.buf;
        int i7 = this.count;
        this.count = i7 + 1;
        cArr2[i7] = 'x';
        char[] cArr3 = this.buf;
        int i8 = this.count;
        this.count = i8 + 1;
        cArr3[i8] = '\'';
        while (i2 < bArr.length) {
            int i9 = bArr[i2] & 255;
            int i10 = i9 >> 4;
            int i11 = i9 & 15;
            char[] cArr4 = this.buf;
            int i12 = this.count;
            this.count = i12 + 1;
            cArr4[i12] = (char) (i10 + (i10 < 10 ? 48 : 55));
            char[] cArr5 = this.buf;
            int i13 = this.count;
            this.count = i13 + 1;
            cArr5[i13] = (char) (i11 + (i11 < 10 ? 48 : 55));
            i2++;
        }
        char[] cArr6 = this.buf;
        int i14 = this.count;
        this.count = i14 + 1;
        cArr6[i14] = '\'';
    }

    public void writeFloat(float f, boolean z) {
        if (f != f || f == Float.POSITIVE_INFINITY || f == Float.NEGATIVE_INFINITY) {
            writeNull();
            return;
        }
        int i = this.count + 15;
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else {
                String string = RyuFloat.toString(f);
                write(string, 0, string.length());
                if (z && isEnabled(SerializerFeature.WriteClassName)) {
                    write(70);
                    return;
                }
                return;
            }
        }
        this.count += RyuFloat.toString(f, this.buf, this.count);
        if (z && isEnabled(SerializerFeature.WriteClassName)) {
            write(70);
        }
    }

    public void writeDouble(double d, boolean z) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            writeNull();
            return;
        }
        int i = this.count + 24;
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else {
                String string = RyuDouble.toString(d);
                write(string, 0, string.length());
                if (z && isEnabled(SerializerFeature.WriteClassName)) {
                    write(68);
                    return;
                }
                return;
            }
        }
        this.count += RyuDouble.toString(d, this.buf, this.count);
        if (z && isEnabled(SerializerFeature.WriteClassName)) {
            write(68);
        }
    }

    public void writeEnum(Enum<?> r3) {
        if (r3 == null) {
            writeNull();
            return;
        }
        String string = null;
        if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            string = r3.name();
        } else if (this.writeEnumUsingToString) {
            string = r3.toString();
        }
        if (string != null) {
            int i = isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            write(i);
            write(string);
            write(i);
            return;
        }
        writeInt(r3.ordinal());
    }

    public void writeLongAndChar(long j, char c) throws IOException {
        writeLong(j);
        write(c);
    }

    public void writeLong(long j) {
        boolean z = isEnabled(SerializerFeature.BrowserCompatible) && !isEnabled(SerializerFeature.WriteClassName) && (j > 9007199254740991L || j < -9007199254740991L);
        if (j == Long.MIN_VALUE) {
            if (z) {
                write("\"-9223372036854775808\"");
                return;
            } else {
                write("-9223372036854775808");
                return;
            }
        }
        int iStringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int i = this.count + iStringSize;
        if (z) {
            i += 2;
        }
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else {
                char[] cArr = new char[iStringSize];
                IOUtils.getChars(j, iStringSize, cArr);
                if (z) {
                    write(34);
                    write(cArr, 0, cArr.length);
                    write(34);
                    return;
                }
                write(cArr, 0, cArr.length);
                return;
            }
        }
        if (z) {
            this.buf[this.count] = '\"';
            int i2 = i - 1;
            IOUtils.getChars(j, i2, this.buf);
            this.buf[i2] = '\"';
        } else {
            IOUtils.getChars(j, i, this.buf);
        }
        this.count = i;
    }

    public void writeNull() {
        write("null");
    }

    public void writeNull(SerializerFeature serializerFeature) {
        writeNull(0, serializerFeature.mask);
    }

    public void writeNull(int i, int i2) {
        if ((i & i2) == 0 && (this.features & i2) == 0) {
            writeNull();
            return;
        }
        if (i2 == SerializerFeature.WriteNullListAsEmpty.mask) {
            write("[]");
            return;
        }
        if (i2 == SerializerFeature.WriteNullStringAsEmpty.mask) {
            writeString("");
            return;
        }
        if (i2 == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            write("false");
        } else if (i2 == SerializerFeature.WriteNullNumberAsZero.mask) {
            write(48);
        } else {
            writeNull();
        }
    }

    /* JADX WARN: Code duplicated, block: B:61:0x0165  */
    public void writeStringWithDoubleQuote(String str, char c) {
        int i;
        if (str == null) {
            writeNull();
            if (c != 0) {
                write(c);
                return;
            }
            return;
        }
        int length = str.length();
        int i2 = this.count + length + 2;
        if (c != 0) {
            i2++;
        }
        char c2 = '\b';
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(34);
                for (int i3 = 0; i3 < str.length(); i3++) {
                    char cCharAt = str.charAt(i3);
                    if (isEnabled(SerializerFeature.BrowserSecure) && (cCharAt == '(' || cCharAt == ')' || cCharAt == '<' || cCharAt == '>')) {
                        write(92);
                        write(117);
                        write(IOUtils.DIGITS[(cCharAt >>> '\f') & 15]);
                        write(IOUtils.DIGITS[(cCharAt >>> '\b') & 15]);
                        write(IOUtils.DIGITS[(cCharAt >>> 4) & 15]);
                        write(IOUtils.DIGITS[cCharAt & 15]);
                    } else if (isEnabled(SerializerFeature.BrowserCompatible)) {
                        if (cCharAt == '\b' || cCharAt == '\f' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t' || cCharAt == '\"' || cCharAt == '/' || cCharAt == '\\') {
                            write(92);
                            write(IOUtils.replaceChars[cCharAt]);
                        } else if (cCharAt < ' ') {
                            write(92);
                            write(117);
                            write(48);
                            write(48);
                            int i4 = cCharAt * 2;
                            write(IOUtils.ASCII_CHARS[i4]);
                            write(IOUtils.ASCII_CHARS[i4 + 1]);
                        } else if (cCharAt >= 127) {
                            write(92);
                            write(117);
                            write(IOUtils.DIGITS[(cCharAt >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(cCharAt >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(cCharAt >>> 4) & 15]);
                            write(IOUtils.DIGITS[cCharAt & 15]);
                        } else {
                            write(cCharAt);
                        }
                    } else if ((cCharAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[cCharAt] != 0) || (cCharAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        if (IOUtils.specicalFlags_doubleQuotes[cCharAt] == 4) {
                            write(117);
                            write(IOUtils.DIGITS[(cCharAt >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(cCharAt >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(cCharAt >>> 4) & 15]);
                            write(IOUtils.DIGITS[cCharAt & 15]);
                        } else {
                            write(IOUtils.replaceChars[cCharAt]);
                        }
                    } else {
                        write(cCharAt);
                    }
                }
                write(34);
                if (c != 0) {
                    write(c);
                    return;
                }
                return;
            }
            expandCapacity(i2);
        }
        int i5 = this.count + 1;
        int i6 = i5 + length;
        this.buf[this.count] = '\"';
        str.getChars(0, length, this.buf, i5);
        this.count = i2;
        int i7 = -1;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            for (int i8 = i5; i8 < i6; i8++) {
                char c3 = this.buf[i8];
                if (c3 == '\"' || c3 == '/' || c3 == '\\' || c3 == '\b' || c3 == '\f' || c3 == '\n' || c3 == '\r' || c3 == '\t') {
                    i2++;
                } else {
                    if (c3 < ' ' || c3 >= 127) {
                        i2 += 5;
                    }
                }
                i7 = i8;
            }
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            this.count = i2;
            while (i7 >= i5) {
                char c4 = this.buf[i7];
                if (c4 == c2 || c4 == '\f' || c4 == '\n' || c4 == '\r' || c4 == '\t') {
                    int i9 = i7 + 1;
                    System.arraycopy(this.buf, i9, this.buf, i7 + 2, (i6 - i7) - 1);
                    this.buf[i7] = '\\';
                    this.buf[i9] = IOUtils.replaceChars[c4];
                    i6++;
                } else if (c4 == '\"' || c4 == '/' || c4 == '\\') {
                    int i10 = i7 + 1;
                    System.arraycopy(this.buf, i10, this.buf, i7 + 2, (i6 - i7) - 1);
                    this.buf[i7] = '\\';
                    this.buf[i10] = c4;
                    i6++;
                } else if (c4 < ' ') {
                    int i11 = i7 + 1;
                    System.arraycopy(this.buf, i11, this.buf, i7 + 6, (i6 - i7) - 1);
                    this.buf[i7] = '\\';
                    this.buf[i11] = 'u';
                    this.buf[i7 + 2] = '0';
                    this.buf[i7 + 3] = '0';
                    int i12 = c4 * 2;
                    this.buf[i7 + 4] = IOUtils.ASCII_CHARS[i12];
                    this.buf[i7 + 5] = IOUtils.ASCII_CHARS[i12 + 1];
                    i6 += 5;
                } else if (c4 >= 127) {
                    int i13 = i7 + 1;
                    System.arraycopy(this.buf, i13, this.buf, i7 + 6, (i6 - i7) - 1);
                    this.buf[i7] = '\\';
                    this.buf[i13] = 'u';
                    this.buf[i7 + 2] = IOUtils.DIGITS[(c4 >>> '\f') & 15];
                    this.buf[i7 + 3] = IOUtils.DIGITS[(c4 >>> '\b') & 15];
                    this.buf[i7 + 4] = IOUtils.DIGITS[(c4 >>> 4) & 15];
                    this.buf[i7 + 5] = IOUtils.DIGITS[c4 & 15];
                    i6 += 5;
                }
                i7--;
                c2 = '\b';
            }
            if (c != 0) {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = c;
                return;
            } else {
                this.buf[this.count - 1] = '\"';
                return;
            }
        }
        int i14 = i2;
        int i15 = 0;
        char c5 = 0;
        int i16 = -1;
        int i17 = -1;
        for (int i18 = i5; i18 < i6; i18++) {
            char c6 = this.buf[i18];
            if (c6 < ']') {
                if ((c6 < '@' && (this.sepcialBits & (1 << c6)) != 0) || c6 == '\\') {
                    i15++;
                    if (c6 == '(' || c6 == ')' || c6 == '<' || c6 == '>' || (c6 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c6] == 4)) {
                        i14 += 4;
                    }
                    i7 = -1;
                    if (i16 == -1) {
                        i16 = i18;
                        i17 = i16;
                    } else {
                        i17 = i18;
                    }
                    c5 = c6;
                } else {
                    i7 = -1;
                }
            } else if (c6 >= 127 && (c6 == 8232 || c6 == 8233 || c6 < 160)) {
                if (i16 == i7) {
                    i16 = i18;
                }
                i15++;
                i14 += 4;
                i17 = i18;
                c5 = c6;
            }
        }
        if (i15 > 0) {
            int i19 = i14 + i15;
            if (i19 > this.buf.length) {
                expandCapacity(i19);
            }
            this.count = i19;
            if (i15 == 1) {
                if (c5 == 8232) {
                    int i20 = i17 + 1;
                    System.arraycopy(this.buf, i20, this.buf, i17 + 6, (i6 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i20] = 'u';
                    int i21 = i20 + 1;
                    this.buf[i21] = '2';
                    int i22 = i21 + 1;
                    this.buf[i22] = '0';
                    int i23 = i22 + 1;
                    this.buf[i23] = '2';
                    this.buf[i23 + 1] = '8';
                } else if (c5 == 8233) {
                    int i24 = i17 + 1;
                    System.arraycopy(this.buf, i24, this.buf, i17 + 6, (i6 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i24] = 'u';
                    int i25 = i24 + 1;
                    this.buf[i25] = '2';
                    int i26 = i25 + 1;
                    this.buf[i26] = '0';
                    int i27 = i26 + 1;
                    this.buf[i27] = '2';
                    this.buf[i27 + 1] = '9';
                } else if (c5 == '(' || c5 == ')' || c5 == '<' || c5 == '>') {
                    int i28 = i17 + 1;
                    System.arraycopy(this.buf, i28, this.buf, i17 + 6, (i6 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i28] = 'u';
                    int i29 = i28 + 1;
                    this.buf[i29] = IOUtils.DIGITS[(c5 >>> '\f') & 15];
                    int i30 = i29 + 1;
                    this.buf[i30] = IOUtils.DIGITS[(c5 >>> '\b') & 15];
                    int i31 = i30 + 1;
                    this.buf[i31] = IOUtils.DIGITS[(c5 >>> 4) & 15];
                    this.buf[i31 + 1] = IOUtils.DIGITS[c5 & 15];
                } else if (c5 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c5] == 4) {
                    int i32 = i17 + 1;
                    System.arraycopy(this.buf, i32, this.buf, i17 + 6, (i6 - i17) - 1);
                    this.buf[i17] = '\\';
                    int i33 = i32 + 1;
                    this.buf[i32] = 'u';
                    int i34 = i33 + 1;
                    this.buf[i33] = IOUtils.DIGITS[(c5 >>> '\f') & 15];
                    int i35 = i34 + 1;
                    this.buf[i34] = IOUtils.DIGITS[(c5 >>> '\b') & 15];
                    this.buf[i35] = IOUtils.DIGITS[(c5 >>> 4) & 15];
                    this.buf[i35 + 1] = IOUtils.DIGITS[c5 & 15];
                } else {
                    int i36 = i17 + 1;
                    System.arraycopy(this.buf, i36, this.buf, i17 + 2, (i6 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i36] = IOUtils.replaceChars[c5];
                }
            } else if (i15 > 1) {
                for (int i37 = i16 - i5; i37 < str.length(); i37++) {
                    char cCharAt2 = str.charAt(i37);
                    if (this.browserSecure && (cCharAt2 == '(' || cCharAt2 == ')' || cCharAt2 == '<' || cCharAt2 == '>')) {
                        int i38 = i16 + 1;
                        this.buf[i16] = '\\';
                        int i39 = i38 + 1;
                        this.buf[i38] = 'u';
                        int i40 = i39 + 1;
                        this.buf[i39] = IOUtils.DIGITS[(cCharAt2 >>> '\f') & 15];
                        int i41 = i40 + 1;
                        this.buf[i40] = IOUtils.DIGITS[(cCharAt2 >>> '\b') & 15];
                        int i42 = i41 + 1;
                        this.buf[i41] = IOUtils.DIGITS[(cCharAt2 >>> 4) & 15];
                        this.buf[i42] = IOUtils.DIGITS[cCharAt2 & 15];
                        i16 = i42 + 1;
                    } else if ((cCharAt2 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[cCharAt2] == 0) && !(cCharAt2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        if (cCharAt2 == 8232 || cCharAt2 == 8233) {
                            int i43 = i16 + 1;
                            this.buf[i16] = '\\';
                            int i44 = i43 + 1;
                            this.buf[i43] = 'u';
                            int i45 = i44 + 1;
                            this.buf[i44] = IOUtils.DIGITS[(cCharAt2 >>> '\f') & 15];
                            int i46 = i45 + 1;
                            this.buf[i45] = IOUtils.DIGITS[(cCharAt2 >>> '\b') & 15];
                            int i47 = i46 + 1;
                            this.buf[i46] = IOUtils.DIGITS[(cCharAt2 >>> 4) & 15];
                            this.buf[i47] = IOUtils.DIGITS[cCharAt2 & 15];
                            i16 = i47 + 1;
                        } else {
                            this.buf[i16] = cCharAt2;
                            i16++;
                        }
                    } else {
                        int i48 = i16 + 1;
                        this.buf[i16] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[cCharAt2] == 4) {
                            int i49 = i48 + 1;
                            this.buf[i48] = 'u';
                            int i50 = i49 + 1;
                            this.buf[i49] = IOUtils.DIGITS[(cCharAt2 >>> '\f') & 15];
                            int i51 = i50 + 1;
                            this.buf[i50] = IOUtils.DIGITS[(cCharAt2 >>> '\b') & 15];
                            int i52 = i51 + 1;
                            this.buf[i51] = IOUtils.DIGITS[(cCharAt2 >>> 4) & 15];
                            i = i52 + 1;
                            this.buf[i52] = IOUtils.DIGITS[cCharAt2 & 15];
                        } else {
                            i = i48 + 1;
                            this.buf[i48] = IOUtils.replaceChars[cCharAt2];
                        }
                        i16 = i;
                    }
                }
            }
        }
        if (c != 0) {
            this.buf[this.count - 2] = '\"';
            this.buf[this.count - 1] = c;
        } else {
            this.buf[this.count - 1] = '\"';
        }
    }

    /* JADX WARN: Code duplicated, block: B:61:0x015d  */
    public void writeStringWithDoubleQuote(char[] cArr, char c) {
        int i;
        if (cArr == null) {
            writeNull();
            if (c != 0) {
                write(c);
                return;
            }
            return;
        }
        int length = cArr.length;
        int i2 = this.count + length + 2;
        if (c != 0) {
            i2++;
        }
        char c2 = '\b';
        char c3 = '\f';
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(34);
                for (char c4 : cArr) {
                    if (isEnabled(SerializerFeature.BrowserSecure) && (c4 == '(' || c4 == ')' || c4 == '<' || c4 == '>')) {
                        write(92);
                        write(117);
                        write(IOUtils.DIGITS[(c4 >>> '\f') & 15]);
                        write(IOUtils.DIGITS[(c4 >>> '\b') & 15]);
                        write(IOUtils.DIGITS[(c4 >>> 4) & 15]);
                        write(IOUtils.DIGITS[c4 & 15]);
                    } else if (isEnabled(SerializerFeature.BrowserCompatible)) {
                        if (c4 == '\b' || c4 == '\f' || c4 == '\n' || c4 == '\r' || c4 == '\t' || c4 == '\"' || c4 == '/' || c4 == '\\') {
                            write(92);
                            write(IOUtils.replaceChars[c4]);
                        } else if (c4 < ' ') {
                            write(92);
                            write(117);
                            write(48);
                            write(48);
                            int i3 = c4 * 2;
                            write(IOUtils.ASCII_CHARS[i3]);
                            write(IOUtils.ASCII_CHARS[i3 + 1]);
                        } else if (c4 >= 127) {
                            write(92);
                            write(117);
                            write(IOUtils.DIGITS[(c4 >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(c4 >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(c4 >>> 4) & 15]);
                            write(IOUtils.DIGITS[c4 & 15]);
                        } else {
                            write(c4);
                        }
                    } else if ((c4 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c4] != 0) || (c4 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        if (IOUtils.specicalFlags_doubleQuotes[c4] == 4) {
                            write(117);
                            write(IOUtils.DIGITS[(c4 >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(c4 >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(c4 >>> 4) & 15]);
                            write(IOUtils.DIGITS[c4 & 15]);
                        } else {
                            write(IOUtils.replaceChars[c4]);
                        }
                    } else {
                        write(c4);
                    }
                }
                write(34);
                if (c != 0) {
                    write(c);
                    return;
                }
                return;
            }
            expandCapacity(i2);
        }
        int i4 = this.count + 1;
        int i5 = length + i4;
        this.buf[this.count] = '\"';
        System.arraycopy(cArr, 0, this.buf, i4, cArr.length);
        this.count = i2;
        int i6 = -1;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            for (int i7 = i4; i7 < i5; i7++) {
                char c5 = this.buf[i7];
                if (c5 == '\"' || c5 == '/' || c5 == '\\' || c5 == '\b' || c5 == '\f' || c5 == '\n' || c5 == '\r' || c5 == '\t') {
                    i2++;
                } else {
                    if (c5 < ' ' || c5 >= 127) {
                        i2 += 5;
                    }
                }
                i6 = i7;
            }
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            this.count = i2;
            while (i6 >= i4) {
                char c6 = this.buf[i6];
                if (c6 == c2 || c6 == c3 || c6 == '\n' || c6 == '\r' || c6 == '\t') {
                    int i8 = i6 + 1;
                    System.arraycopy(this.buf, i8, this.buf, i6 + 2, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i8] = IOUtils.replaceChars[c6];
                    i5++;
                } else if (c6 == '\"' || c6 == '/' || c6 == '\\') {
                    int i9 = i6 + 1;
                    System.arraycopy(this.buf, i9, this.buf, i6 + 2, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i9] = c6;
                    i5++;
                } else if (c6 < ' ') {
                    int i10 = i6 + 1;
                    System.arraycopy(this.buf, i10, this.buf, i6 + 6, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i10] = 'u';
                    this.buf[i6 + 2] = '0';
                    this.buf[i6 + 3] = '0';
                    int i11 = c6 * 2;
                    this.buf[i6 + 4] = IOUtils.ASCII_CHARS[i11];
                    this.buf[i6 + 5] = IOUtils.ASCII_CHARS[i11 + 1];
                    i5 += 5;
                } else if (c6 >= 127) {
                    int i12 = i6 + 1;
                    System.arraycopy(this.buf, i12, this.buf, i6 + 6, (i5 - i6) - 1);
                    this.buf[i6] = '\\';
                    this.buf[i12] = 'u';
                    this.buf[i6 + 2] = IOUtils.DIGITS[(c6 >>> '\f') & 15];
                    this.buf[i6 + 3] = IOUtils.DIGITS[(c6 >>> '\b') & 15];
                    this.buf[i6 + 4] = IOUtils.DIGITS[(c6 >>> 4) & 15];
                    this.buf[i6 + 5] = IOUtils.DIGITS[c6 & 15];
                    i5 += 5;
                }
                i6--;
                c2 = '\b';
                c3 = '\f';
            }
            if (c != 0) {
                this.buf[this.count - 2] = '\"';
                this.buf[this.count - 1] = c;
                return;
            } else {
                this.buf[this.count - 1] = '\"';
                return;
            }
        }
        int i13 = i2;
        int i14 = i4;
        int i15 = 0;
        char c7 = 0;
        int i16 = -1;
        int i17 = -1;
        while (i14 < i5) {
            char c8 = this.buf[i14];
            if (c8 >= ']') {
                if (c8 >= 127 && (c8 == 8232 || c8 == 8233 || c8 < 160)) {
                    if (i16 == i6) {
                        i16 = i14;
                    }
                    i15++;
                    i13 += 4;
                    i17 = i14;
                    c7 = c8;
                }
                i = i6;
            } else if ((c8 < '@' && (this.sepcialBits & (1 << c8)) != 0) || c8 == '\\') {
                i15++;
                if (c8 == '(' || c8 == ')' || c8 == '<' || c8 == '>' || (c8 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c8] == 4)) {
                    i13 += 4;
                }
                i = -1;
                if (i16 == -1) {
                    i16 = i14;
                    i17 = i16;
                } else {
                    i17 = i14;
                }
                c7 = c8;
            } else {
                i = -1;
            }
            i14++;
            i6 = i;
        }
        if (i15 > 0) {
            int i18 = i13 + i15;
            if (i18 > this.buf.length) {
                expandCapacity(i18);
            }
            this.count = i18;
            if (i15 == 1) {
                if (c7 == 8232) {
                    int i19 = i17 + 1;
                    System.arraycopy(this.buf, i19, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i19] = 'u';
                    int i20 = i19 + 1;
                    this.buf[i20] = '2';
                    int i21 = i20 + 1;
                    this.buf[i21] = '0';
                    int i22 = i21 + 1;
                    this.buf[i22] = '2';
                    this.buf[i22 + 1] = '8';
                } else if (c7 == 8233) {
                    int i23 = i17 + 1;
                    System.arraycopy(this.buf, i23, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i23] = 'u';
                    int i24 = i23 + 1;
                    this.buf[i24] = '2';
                    int i25 = i24 + 1;
                    this.buf[i25] = '0';
                    int i26 = i25 + 1;
                    this.buf[i26] = '2';
                    this.buf[i26 + 1] = '9';
                } else if (c7 == '(' || c7 == ')' || c7 == '<' || c7 == '>') {
                    int i27 = i17 + 1;
                    System.arraycopy(this.buf, i27, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i27] = 'u';
                    int i28 = i27 + 1;
                    this.buf[i28] = IOUtils.DIGITS[(c7 >>> '\f') & 15];
                    int i29 = i28 + 1;
                    this.buf[i29] = IOUtils.DIGITS[(c7 >>> '\b') & 15];
                    int i30 = i29 + 1;
                    this.buf[i30] = IOUtils.DIGITS[(c7 >>> 4) & 15];
                    this.buf[i30 + 1] = IOUtils.DIGITS[c7 & 15];
                } else if (c7 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c7] == 4) {
                    int i31 = i17 + 1;
                    System.arraycopy(this.buf, i31, this.buf, i17 + 6, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    int i32 = i31 + 1;
                    this.buf[i31] = 'u';
                    int i33 = i32 + 1;
                    this.buf[i32] = IOUtils.DIGITS[(c7 >>> '\f') & 15];
                    int i34 = i33 + 1;
                    this.buf[i33] = IOUtils.DIGITS[(c7 >>> '\b') & 15];
                    this.buf[i34] = IOUtils.DIGITS[(c7 >>> 4) & 15];
                    this.buf[i34 + 1] = IOUtils.DIGITS[c7 & 15];
                } else {
                    int i35 = i17 + 1;
                    System.arraycopy(this.buf, i35, this.buf, i17 + 2, (i5 - i17) - 1);
                    this.buf[i17] = '\\';
                    this.buf[i35] = IOUtils.replaceChars[c7];
                }
            } else if (i15 > 1) {
                for (int i36 = i16 - i4; i36 < cArr.length; i36++) {
                    char c9 = cArr[i36];
                    if (this.browserSecure && (c9 == '(' || c9 == ')' || c9 == '<' || c9 == '>')) {
                        int i37 = i16 + 1;
                        this.buf[i16] = '\\';
                        int i38 = i37 + 1;
                        this.buf[i37] = 'u';
                        int i39 = i38 + 1;
                        this.buf[i38] = IOUtils.DIGITS[(c9 >>> '\f') & 15];
                        int i40 = i39 + 1;
                        this.buf[i39] = IOUtils.DIGITS[(c9 >>> '\b') & 15];
                        int i41 = i40 + 1;
                        this.buf[i40] = IOUtils.DIGITS[(c9 >>> 4) & 15];
                        i16 = i41 + 1;
                        this.buf[i41] = IOUtils.DIGITS[c9 & 15];
                    } else if ((c9 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c9] == 0) && !(c9 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        if (c9 == 8232 || c9 == 8233) {
                            int i42 = i16 + 1;
                            this.buf[i16] = '\\';
                            int i43 = i42 + 1;
                            this.buf[i42] = 'u';
                            int i44 = i43 + 1;
                            this.buf[i43] = IOUtils.DIGITS[(c9 >>> '\f') & 15];
                            int i45 = i44 + 1;
                            this.buf[i44] = IOUtils.DIGITS[(c9 >>> '\b') & 15];
                            int i46 = i45 + 1;
                            this.buf[i45] = IOUtils.DIGITS[(c9 >>> 4) & 15];
                            i16 = i46 + 1;
                            this.buf[i46] = IOUtils.DIGITS[c9 & 15];
                        } else {
                            this.buf[i16] = c9;
                            i16++;
                        }
                    } else {
                        int i47 = i16 + 1;
                        this.buf[i16] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[c9] == 4) {
                            int i48 = i47 + 1;
                            this.buf[i47] = 'u';
                            int i49 = i48 + 1;
                            this.buf[i48] = IOUtils.DIGITS[(c9 >>> '\f') & 15];
                            int i50 = i49 + 1;
                            this.buf[i49] = IOUtils.DIGITS[(c9 >>> '\b') & 15];
                            int i51 = i50 + 1;
                            this.buf[i50] = IOUtils.DIGITS[(c9 >>> 4) & 15];
                            i16 = i51 + 1;
                            this.buf[i51] = IOUtils.DIGITS[c9 & 15];
                        } else {
                            i16 = i47 + 1;
                            this.buf[i47] = IOUtils.replaceChars[c9];
                        }
                    }
                }
            }
        }
        if (c != 0) {
            this.buf[this.count - 2] = '\"';
            this.buf[this.count - 1] = c;
        } else {
            this.buf[this.count - 1] = '\"';
        }
    }

    public void writeFieldNameDirect(String str) {
        int length = str.length();
        int i = this.count + length + 3;
        if (i > this.buf.length) {
            expandCapacity(i);
        }
        int i2 = this.count + 1;
        this.buf[this.count] = '\"';
        str.getChars(0, length, this.buf, i2);
        this.count = i;
        this.buf[this.count - 2] = '\"';
        this.buf[this.count - 1] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
    }

    public void write(List<String> list) {
        boolean z;
        int i;
        if (list.isEmpty()) {
            write("[]");
            return;
        }
        int i2 = this.count;
        int size = list.size();
        int i3 = i2;
        int i4 = 0;
        while (i4 < size) {
            String str = list.get(i4);
            if (str == null) {
                z = true;
            } else {
                int length = str.length();
                z = false;
                for (int i5 = 0; i5 < length; i5++) {
                    char cCharAt = str.charAt(i5);
                    z = cCharAt < ' ' || cCharAt > '~' || cCharAt == '\"' || cCharAt == '\\';
                    if (z) {
                        break;
                    }
                }
            }
            if (z) {
                this.count = i2;
                write(91);
                for (int i6 = 0; i6 < list.size(); i6++) {
                    String str2 = list.get(i6);
                    if (i6 != 0) {
                        write(44);
                    }
                    if (str2 == null) {
                        write("null");
                    } else {
                        writeStringWithDoubleQuote(str2, (char) 0);
                    }
                }
                write(93);
                return;
            }
            int length2 = str.length() + i3 + 3;
            if (i4 == list.size() - 1) {
                length2++;
            }
            if (length2 > this.buf.length) {
                this.count = i3;
                expandCapacity(length2);
            }
            if (i4 == 0) {
                i = i3 + 1;
                this.buf[i3] = '[';
            } else {
                i = i3 + 1;
                this.buf[i3] = ',';
            }
            int i7 = i + 1;
            this.buf[i] = '\"';
            str.getChars(0, str.length(), this.buf, i7);
            int length3 = i7 + str.length();
            this.buf[length3] = '\"';
            i4++;
            i3 = length3 + 1;
        }
        this.buf[i3] = ']';
        this.count = i3 + 1;
    }

    public void writeFieldValue(char c, String str, char c2) {
        write(c);
        writeFieldName(str);
        if (c2 == 0) {
            writeString("\u0000");
        } else {
            writeString(Character.toString(c2));
        }
    }

    public void writeFieldValue(char c, String str, boolean z) {
        if (!this.quoteFieldNames) {
            write(c);
            writeFieldName(str);
            write(z);
            return;
        }
        int i = z ? 4 : 5;
        int length = str.length();
        int i2 = this.count + length + 4 + i;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeString(str);
                write(58);
                write(z);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count;
        this.count = i2;
        this.buf[i3] = c;
        int i4 = i3 + length + 1;
        this.buf[i3 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i3 + 2);
        this.buf[i4 + 1] = this.keySeperator;
        if (z) {
            System.arraycopy(":true".toCharArray(), 0, this.buf, i4 + 2, 5);
        } else {
            System.arraycopy(":false".toCharArray(), 0, this.buf, i4 + 2, 6);
        }
    }

    public void write(boolean z) {
        if (z) {
            write("true");
        } else {
            write("false");
        }
    }

    public void writeFieldValue(char c, String str, int i) {
        if (i == Integer.MIN_VALUE || !this.quoteFieldNames) {
            write(c);
            writeFieldName(str);
            writeInt(i);
            return;
        }
        int iStringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int length = str.length();
        int i2 = this.count + length + 4 + iStringSize;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeFieldName(str);
                writeInt(i);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count;
        this.count = i2;
        this.buf[i3] = c;
        int i4 = i3 + length + 1;
        this.buf[i3 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i3 + 2);
        this.buf[i4 + 1] = this.keySeperator;
        this.buf[i4 + 2] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
        IOUtils.getChars(i, this.count, this.buf);
    }

    public void writeFieldValue(char c, String str, long j) {
        if (j == Long.MIN_VALUE || !this.quoteFieldNames) {
            write(c);
            writeFieldName(str);
            writeLong(j);
            return;
        }
        int iStringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int length = str.length();
        int i = this.count + length + 4 + iStringSize;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeFieldName(str);
                writeLong(j);
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count;
        this.count = i;
        this.buf[i2] = c;
        int i3 = i2 + length + 1;
        this.buf[i2 + 1] = this.keySeperator;
        str.getChars(0, length, this.buf, i2 + 2);
        this.buf[i3 + 1] = this.keySeperator;
        this.buf[i3 + 2] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
        IOUtils.getChars(j, this.count, this.buf);
    }

    public void writeFieldValue(char c, String str, float f) {
        write(c);
        writeFieldName(str);
        writeFloat(f, false);
    }

    public void writeFieldValue(char c, String str, double d) {
        write(c);
        writeFieldName(str);
        writeDouble(d, false);
    }

    public void writeFieldValue(char c, String str, String str2) {
        if (this.quoteFieldNames) {
            if (this.useSingleQuotes) {
                write(c);
                writeFieldName(str);
                if (str2 == null) {
                    writeNull();
                    return;
                } else {
                    writeString(str2);
                    return;
                }
            }
            if (isEnabled(SerializerFeature.BrowserCompatible)) {
                write(c);
                writeStringWithDoubleQuote(str, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            writeFieldValueStringWithDoubleQuoteCheck(c, str, str2);
            return;
        }
        write(c);
        writeFieldName(str);
        if (str2 == null) {
            writeNull();
        } else {
            writeString(str2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00b7 A[PHI: r6 r7 r10 r14
      0x00b7: PHI (r6v46 int) = (r6v39 int), (r6v3 int) binds: [B:58:0x00fc, B:30:0x00b3] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r7v29 int) = (r7v25 int), (r7v30 int) binds: [B:58:0x00fc, B:30:0x00b3] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r10v26 int) = (r10v23 int), (r10v27 int) binds: [B:58:0x00fc, B:30:0x00b3] A[DONT_GENERATE, DONT_INLINE]
      0x00b7: PHI (r14v18 int) = (r14v1 int), (r14v19 int) binds: [B:58:0x00fc, B:30:0x00b3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:41:0x00d6 A[PHI: r16
      0x00d6: PHI (r16v4 int) = (r16v2 int), (r16v5 int) binds: [B:40:0x00d4, B:37:0x00cf] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:44:0x00db  */
    /* JADX WARN: Code duplicated, block: B:56:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:59:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:60:0x0101  */
    public void writeFieldValueStringWithDoubleQuoteCheck(char c, String str, String str2) {
        int length;
        int i;
        int i2;
        int i3;
        boolean z;
        int length2 = str.length();
        int i4 = this.count;
        if (str2 == null) {
            i = i4 + length2 + 8;
            length = 4;
        } else {
            length = str2.length();
            i = i4 + length2 + length + 6;
        }
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeStringWithDoubleQuote(str, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            expandCapacity(i);
        }
        this.buf[this.count] = c;
        int i5 = this.count + 2;
        int i6 = i5 + length2;
        this.buf[this.count + 1] = '\"';
        str.getChars(0, length2, this.buf, i5);
        this.count = i;
        this.buf[i6] = '\"';
        int i7 = i6 + 1;
        int i8 = i7 + 1;
        this.buf[i7] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
        if (str2 == null) {
            int i9 = i8 + 1;
            this.buf[i8] = 'n';
            int i10 = i9 + 1;
            this.buf[i9] = 'u';
            this.buf[i10] = 'l';
            this.buf[i10 + 1] = 'l';
            return;
        }
        int i11 = i8 + 1;
        this.buf[i8] = '\"';
        int i12 = i11 + length;
        str2.getChars(0, length, this.buf, i11);
        int i13 = -1;
        int i14 = i;
        int i15 = -1;
        int i16 = -1;
        int i17 = 0;
        char c2 = 0;
        for (int i18 = i11; i18 < i12; i18++) {
            char c3 = this.buf[i18];
            if (c3 < ']') {
                if (c3 < '@') {
                    i3 = i17;
                    if ((this.sepcialBits & (1 << c3)) != 0) {
                        z = true;
                    }
                    if (z) {
                        i17 = i3 + 1;
                        if (c3 != '(' || c3 == ')' || c3 == '<' || c3 == '>' || (c3 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c3] == 4)) {
                            i14 += 4;
                        }
                        i13 = -1;
                        if (i15 == -1) {
                            i15 = i18;
                            i16 = i15;
                        } else {
                            i16 = i18;
                        }
                        c2 = c3;
                    } else {
                        i13 = -1;
                        i17 = i3;
                    }
                } else {
                    i3 = i17;
                }
                if (c3 == '\\') {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i17 = i3 + 1;
                    if (c3 != '(') {
                        i14 += 4;
                    } else {
                        i14 += 4;
                    }
                    i13 = -1;
                    if (i15 == -1) {
                        i15 = i18;
                        i16 = i15;
                    } else {
                        i16 = i18;
                    }
                    c2 = c3;
                } else {
                    i13 = -1;
                    i17 = i3;
                }
            } else if (c3 < 127 || !(c3 == 8232 || c3 == 8233 || c3 < 160)) {
                i3 = i17;
                i17 = i3;
            } else {
                if (i15 == i13) {
                    i15 = i18;
                }
                i17++;
                i14 += 4;
                i16 = i18;
                c2 = c3;
            }
        }
        int i19 = i17;
        if (i19 > 0) {
            int i20 = i14 + i19;
            if (i20 > this.buf.length) {
                expandCapacity(i20);
            }
            this.count = i20;
            if (i19 == 1) {
                if (c2 == 8232) {
                    int i21 = i16 + 1;
                    System.arraycopy(this.buf, i21, this.buf, i16 + 6, (i12 - i16) - 1);
                    this.buf[i16] = '\\';
                    this.buf[i21] = 'u';
                    int i22 = i21 + 1;
                    this.buf[i22] = '2';
                    int i23 = i22 + 1;
                    this.buf[i23] = '0';
                    int i24 = i23 + 1;
                    this.buf[i24] = '2';
                    this.buf[i24 + 1] = '8';
                } else if (c2 == 8233) {
                    int i25 = i16 + 1;
                    System.arraycopy(this.buf, i25, this.buf, i16 + 6, (i12 - i16) - 1);
                    this.buf[i16] = '\\';
                    this.buf[i25] = 'u';
                    int i26 = i25 + 1;
                    this.buf[i26] = '2';
                    int i27 = i26 + 1;
                    this.buf[i27] = '0';
                    int i28 = i27 + 1;
                    this.buf[i28] = '2';
                    this.buf[i28 + 1] = '9';
                } else if (c2 == '(' || c2 == ')' || c2 == '<' || c2 == '>') {
                    int i29 = i16 + 1;
                    System.arraycopy(this.buf, i29, this.buf, i16 + 6, (i12 - i16) - 1);
                    this.buf[i16] = '\\';
                    int i30 = i29 + 1;
                    this.buf[i29] = 'u';
                    int i31 = i30 + 1;
                    this.buf[i30] = IOUtils.DIGITS[(c2 >>> '\f') & 15];
                    int i32 = i31 + 1;
                    this.buf[i31] = IOUtils.DIGITS[(c2 >>> '\b') & 15];
                    this.buf[i32] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                    this.buf[i32 + 1] = IOUtils.DIGITS[c2 & 15];
                } else if (c2 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c2] == 4) {
                    int i33 = i16 + 1;
                    System.arraycopy(this.buf, i33, this.buf, i16 + 6, (i12 - i16) - 1);
                    this.buf[i16] = '\\';
                    int i34 = i33 + 1;
                    this.buf[i33] = 'u';
                    int i35 = i34 + 1;
                    this.buf[i34] = IOUtils.DIGITS[(c2 >>> '\f') & 15];
                    int i36 = i35 + 1;
                    this.buf[i35] = IOUtils.DIGITS[(c2 >>> '\b') & 15];
                    this.buf[i36] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                    this.buf[i36 + 1] = IOUtils.DIGITS[c2 & 15];
                } else {
                    int i37 = i16 + 1;
                    System.arraycopy(this.buf, i37, this.buf, i16 + 2, (i12 - i16) - 1);
                    this.buf[i16] = '\\';
                    this.buf[i37] = IOUtils.replaceChars[c2];
                }
            } else if (i19 > 1) {
                for (int i38 = i15 - i11; i38 < str2.length(); i38++) {
                    char cCharAt = str2.charAt(i38);
                    if (this.browserSecure && (cCharAt == '(' || cCharAt == ')' || cCharAt == '<' || cCharAt == '>')) {
                        int i39 = i15 + 1;
                        this.buf[i15] = '\\';
                        int i40 = i39 + 1;
                        this.buf[i39] = 'u';
                        int i41 = i40 + 1;
                        this.buf[i40] = IOUtils.DIGITS[(cCharAt >>> '\f') & 15];
                        int i42 = i41 + 1;
                        this.buf[i41] = IOUtils.DIGITS[(cCharAt >>> '\b') & 15];
                        int i43 = i42 + 1;
                        this.buf[i42] = IOUtils.DIGITS[(cCharAt >>> 4) & 15];
                        this.buf[i43] = IOUtils.DIGITS[cCharAt & 15];
                        i15 = i43 + 1;
                    } else if ((cCharAt >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[cCharAt] == 0) && !(cCharAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        if (cCharAt == 8232 || cCharAt == 8233) {
                            int i44 = i15 + 1;
                            this.buf[i15] = '\\';
                            int i45 = i44 + 1;
                            this.buf[i44] = 'u';
                            int i46 = i45 + 1;
                            this.buf[i45] = IOUtils.DIGITS[(cCharAt >>> '\f') & 15];
                            int i47 = i46 + 1;
                            this.buf[i46] = IOUtils.DIGITS[(cCharAt >>> '\b') & 15];
                            int i48 = i47 + 1;
                            this.buf[i47] = IOUtils.DIGITS[(cCharAt >>> 4) & 15];
                            this.buf[i48] = IOUtils.DIGITS[cCharAt & 15];
                            i15 = i48 + 1;
                        } else {
                            this.buf[i15] = cCharAt;
                            i15++;
                        }
                    } else {
                        int i49 = i15 + 1;
                        this.buf[i15] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[cCharAt] == 4) {
                            int i50 = i49 + 1;
                            this.buf[i49] = 'u';
                            int i51 = i50 + 1;
                            this.buf[i50] = IOUtils.DIGITS[(cCharAt >>> '\f') & 15];
                            int i52 = i51 + 1;
                            this.buf[i51] = IOUtils.DIGITS[(cCharAt >>> '\b') & 15];
                            int i53 = i52 + 1;
                            this.buf[i52] = IOUtils.DIGITS[(cCharAt >>> 4) & 15];
                            i2 = i53 + 1;
                            this.buf[i53] = IOUtils.DIGITS[cCharAt & 15];
                        } else {
                            i2 = i49 + 1;
                            this.buf[i49] = IOUtils.replaceChars[cCharAt];
                        }
                        i15 = i2;
                    }
                }
            }
        }
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldValueStringWithDoubleQuote(char c, String str, String str2) {
        int length = str.length();
        int i = this.count;
        int length2 = str2.length();
        int i2 = i + length + length2 + 6;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeStringWithDoubleQuote(str, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            expandCapacity(i2);
        }
        this.buf[this.count] = c;
        int i3 = this.count + 2;
        int i4 = i3 + length;
        this.buf[this.count + 1] = '\"';
        str.getChars(0, length, this.buf, i3);
        this.count = i2;
        this.buf[i4] = '\"';
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        this.buf[i5] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
        this.buf[i6] = '\"';
        str2.getChars(0, length2, this.buf, i6 + 1);
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldValue(char c, String str, Enum<?> r4) {
        if (r4 == null) {
            write(c);
            writeFieldName(str);
            writeNull();
        } else if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            writeEnumFieldValue(c, str, r4.name());
        } else if (this.writeEnumUsingToString) {
            writeEnumFieldValue(c, str, r4.toString());
        } else {
            writeFieldValue(c, str, r4.ordinal());
        }
    }

    private void writeEnumFieldValue(char c, String str, String str2) {
        if (this.useSingleQuotes) {
            writeFieldValue(c, str, str2);
        } else {
            writeFieldValueStringWithDoubleQuote(c, str, str2);
        }
    }

    public void writeFieldValue(char c, String str, BigDecimal bigDecimal) {
        String string;
        write(c);
        writeFieldName(str);
        if (bigDecimal == null) {
            writeNull();
            return;
        }
        int iScale = bigDecimal.scale();
        if (isEnabled(SerializerFeature.WriteBigDecimalAsPlain) && iScale >= -100 && iScale < 100) {
            string = bigDecimal.toPlainString();
        } else {
            string = bigDecimal.toString();
        }
        write(string);
    }

    public void writeString(String str, char c) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
            write(c);
        } else {
            writeStringWithDoubleQuote(str, c);
        }
    }

    public void writeString(String str) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, (char) 0);
        }
    }

    public void writeString(char[] cArr) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(cArr);
        } else {
            writeStringWithDoubleQuote(new String(cArr), (char) 0);
        }
    }

    protected void writeStringWithSingleQuote(String str) {
        int i = 0;
        if (str == null) {
            int i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        int length = str.length();
        int i3 = this.count + length + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt <= '\r' || cCharAt == '\\' || cCharAt == '\'' || (cCharAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(IOUtils.replaceChars[cCharAt]);
                    } else {
                        write(cCharAt);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count + 1;
        int i5 = i4 + length;
        this.buf[this.count] = '\'';
        str.getChars(0, length, this.buf, i4);
        this.count = i3;
        int i6 = -1;
        char c = 0;
        for (int i7 = i4; i7 < i5; i7++) {
            char c2 = this.buf[i7];
            if (c2 <= '\r' || c2 == '\\' || c2 == '\'' || (c2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i6 = i7;
                c = c2;
            }
        }
        int i8 = i3 + i;
        if (i8 > this.buf.length) {
            expandCapacity(i8);
        }
        this.count = i8;
        if (i == 1) {
            int i9 = i6 + 1;
            System.arraycopy(this.buf, i9, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i9] = IOUtils.replaceChars[c];
        } else if (i > 1) {
            int i10 = i6 + 1;
            System.arraycopy(this.buf, i10, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i10] = IOUtils.replaceChars[c];
            int i11 = i5 + 1;
            for (int i12 = i10 - 2; i12 >= i4; i12--) {
                char c3 = this.buf[i12];
                if (c3 <= '\r' || c3 == '\\' || c3 == '\'' || (c3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    int i13 = i12 + 1;
                    System.arraycopy(this.buf, i13, this.buf, i12 + 2, (i11 - i12) - 1);
                    this.buf[i12] = '\\';
                    this.buf[i13] = IOUtils.replaceChars[c3];
                    i11++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    protected void writeStringWithSingleQuote(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            int i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        int length = cArr.length;
        int i3 = this.count + length + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < cArr.length) {
                    char c = cArr[i];
                    if (c <= '\r' || c == '\\' || c == '\'' || (c == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(IOUtils.replaceChars[c]);
                    } else {
                        write(c);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count + 1;
        int i5 = length + i4;
        this.buf[this.count] = '\'';
        System.arraycopy(cArr, 0, this.buf, i4, cArr.length);
        this.count = i3;
        int i6 = -1;
        char c2 = 0;
        for (int i7 = i4; i7 < i5; i7++) {
            char c3 = this.buf[i7];
            if (c3 <= '\r' || c3 == '\\' || c3 == '\'' || (c3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i6 = i7;
                c2 = c3;
            }
        }
        int i8 = i3 + i;
        if (i8 > this.buf.length) {
            expandCapacity(i8);
        }
        this.count = i8;
        if (i == 1) {
            int i9 = i6 + 1;
            System.arraycopy(this.buf, i9, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i9] = IOUtils.replaceChars[c2];
        } else if (i > 1) {
            int i10 = i6 + 1;
            System.arraycopy(this.buf, i10, this.buf, i6 + 2, (i5 - i6) - 1);
            this.buf[i6] = '\\';
            this.buf[i10] = IOUtils.replaceChars[c2];
            int i11 = i5 + 1;
            for (int i12 = i10 - 2; i12 >= i4; i12--) {
                char c4 = this.buf[i12];
                if (c4 <= '\r' || c4 == '\\' || c4 == '\'' || (c4 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    int i13 = i12 + 1;
                    System.arraycopy(this.buf, i13, this.buf, i12 + 2, (i11 - i12) - 1);
                    this.buf[i12] = '\\';
                    this.buf[i13] = IOUtils.replaceChars[c4];
                    i11++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String str) {
        writeFieldName(str, false);
    }

    public void writeFieldName(String str, boolean z) {
        if (str == null) {
            write("null:");
            return;
        }
        if (this.useSingleQuotes) {
            if (this.quoteFieldNames) {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            } else {
                writeKeyWithSingleQuoteIfHasSpecial(str);
                return;
            }
        }
        if (this.quoteFieldNames) {
            writeStringWithDoubleQuote(str, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
            return;
        }
        boolean z2 = str.length() == 0;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '@' && (this.sepcialBits & (1 << cCharAt)) != 0) || cCharAt == '\\') {
                z2 = true;
                break;
            }
        }
        if (z2) {
            writeStringWithDoubleQuote(str, AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR);
        } else {
            write(str);
            write(58);
        }
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        int i;
        byte[] bArr = IOUtils.specicalFlags_singleQuotes;
        int length = str.length();
        boolean z = true;
        int i2 = this.count + length + 1;
        int i3 = 0;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                if (length == 0) {
                    write(39);
                    write(39);
                    write(58);
                    return;
                }
                int i4 = 0;
                while (true) {
                    if (i4 < length) {
                        char cCharAt = str.charAt(i4);
                        if (cCharAt < bArr.length && bArr[cCharAt] != 0) {
                            break;
                        } else {
                            i4++;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    write(39);
                }
                while (i3 < length) {
                    char cCharAt2 = str.charAt(i3);
                    if (cCharAt2 < bArr.length && bArr[cCharAt2] != 0) {
                        write(92);
                        write(IOUtils.replaceChars[cCharAt2]);
                    } else {
                        write(cCharAt2);
                    }
                    i3++;
                }
                if (z) {
                    write(39);
                }
                write(58);
                return;
            }
            expandCapacity(i2);
        }
        if (length == 0) {
            if (this.count + 3 > this.buf.length) {
                expandCapacity(this.count + 3);
            }
            char[] cArr = this.buf;
            int i5 = this.count;
            this.count = i5 + 1;
            cArr[i5] = '\'';
            char[] cArr2 = this.buf;
            int i6 = this.count;
            this.count = i6 + 1;
            cArr2[i6] = '\'';
            char[] cArr3 = this.buf;
            int i7 = this.count;
            this.count = i7 + 1;
            cArr3[i7] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
            return;
        }
        int i8 = this.count;
        int i9 = i8 + length;
        str.getChars(0, length, this.buf, i8);
        this.count = i2;
        int i10 = i8;
        boolean z2 = false;
        while (i10 < i9) {
            char c = this.buf[i10];
            if (c >= bArr.length || bArr[c] == 0) {
                i = i10;
            } else if (!z2) {
                i2 += 3;
                if (i2 > this.buf.length) {
                    expandCapacity(i2);
                }
                this.count = i2;
                int i11 = i10 + 1;
                System.arraycopy(this.buf, i11, this.buf, i10 + 3, (i9 - i10) - 1);
                System.arraycopy(this.buf, i3, this.buf, 1, i10);
                this.buf[i8] = '\'';
                this.buf[i11] = '\\';
                int i12 = i11 + 1;
                this.buf[i12] = IOUtils.replaceChars[c];
                i9 += 2;
                this.buf[this.count - 2] = '\'';
                i = i12;
                z2 = true;
            } else {
                i2++;
                if (i2 > this.buf.length) {
                    expandCapacity(i2);
                }
                this.count = i2;
                i = i10 + 1;
                System.arraycopy(this.buf, i, this.buf, i10 + 2, i9 - i10);
                this.buf[i10] = '\\';
                this.buf[i] = IOUtils.replaceChars[c];
                i9++;
            }
            i10 = i + 1;
            i3 = 0;
        }
        this.buf[i2 - 1] = AccessibilityUtils.ENABLED_ACCESSIBILITY_SERVICES_SEPARATOR;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        if (this.writer == null) {
            return;
        }
        try {
            this.writer.write(this.buf, 0, this.count);
            this.writer.flush();
            this.count = 0;
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }
}
