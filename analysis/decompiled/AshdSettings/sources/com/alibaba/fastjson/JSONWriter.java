package com.alibaba.fastjson;

import android.support.v4.view.PointerIconCompat;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class JSONWriter implements Closeable, Flushable {
    private JSONStreamContext context;
    private JSONSerializer serializer;
    private SerializeWriter writer;

    public JSONWriter(Writer writer) {
        this.writer = new SerializeWriter(writer);
        this.serializer = new JSONSerializer(this.writer);
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        this.writer.config(serializerFeature, z);
    }

    public void startObject() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, PointerIconCompat.TYPE_CONTEXT_MENU);
        this.writer.write(123);
    }

    public void endObject() {
        this.writer.write(125);
        endStructure();
    }

    public void writeKey(String str) {
        writeObject(str);
    }

    public void writeValue(Object obj) {
        writeObject(obj);
    }

    public void writeObject(String str) {
        beforeWrite();
        this.serializer.write(str);
        afterWriter();
    }

    public void writeObject(Object obj) {
        beforeWrite();
        this.serializer.write(obj);
        afterWriter();
    }

    public void startArray() {
        if (this.context != null) {
            beginStructure();
        }
        this.context = new JSONStreamContext(this.context, PointerIconCompat.TYPE_WAIT);
        this.writer.write(91);
    }

    private void beginStructure() {
        int i = this.context.state;
        switch (this.context.state) {
            case PointerIconCompat.TYPE_CONTEXT_MENU /* 1001 */:
            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                return;
            case 1002:
                this.writer.write(58);
                return;
            case PointerIconCompat.TYPE_HELP /* 1003 */:
            default:
                throw new JSONException("illegal state : " + i);
            case 1005:
                this.writer.write(44);
                return;
        }
    }

    public void endArray() {
        this.writer.write(93);
        endStructure();
    }

    private void endStructure() {
        int i;
        this.context = this.context.parent;
        if (this.context == null) {
            return;
        }
        switch (this.context.state) {
            case PointerIconCompat.TYPE_CONTEXT_MENU /* 1001 */:
                i = 1002;
                break;
            case 1002:
                i = PointerIconCompat.TYPE_HELP;
                break;
            case PointerIconCompat.TYPE_HELP /* 1003 */:
            case 1005:
            default:
                i = -1;
                break;
            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                i = 1005;
                break;
        }
        if (i != -1) {
            this.context.state = i;
        }
    }

    private void beforeWrite() {
        if (this.context == null) {
        }
        switch (this.context.state) {
            case 1002:
                this.writer.write(58);
                break;
            case PointerIconCompat.TYPE_HELP /* 1003 */:
                this.writer.write(44);
                break;
            case 1005:
                this.writer.write(44);
                break;
        }
    }

    private void afterWriter() {
        int i;
        if (this.context == null) {
            return;
        }
        switch (this.context.state) {
            case PointerIconCompat.TYPE_CONTEXT_MENU /* 1001 */:
            case PointerIconCompat.TYPE_HELP /* 1003 */:
                i = 1002;
                break;
            case 1002:
                i = PointerIconCompat.TYPE_HELP;
                break;
            case PointerIconCompat.TYPE_WAIT /* 1004 */:
                i = 1005;
                break;
            case 1005:
            default:
                i = -1;
                break;
        }
        if (i != -1) {
            this.context.state = i;
        }
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.writer.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.writer.close();
    }

    @Deprecated
    public void writeStartObject() {
        startObject();
    }

    @Deprecated
    public void writeEndObject() {
        endObject();
    }

    @Deprecated
    public void writeStartArray() {
        startArray();
    }

    @Deprecated
    public void writeEndArray() {
        endArray();
    }
}
