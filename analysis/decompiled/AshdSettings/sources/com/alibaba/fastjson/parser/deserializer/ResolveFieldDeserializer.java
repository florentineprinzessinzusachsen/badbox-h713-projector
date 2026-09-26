package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ResolveFieldDeserializer extends FieldDeserializer {
    private final Collection collection;
    private final int index;
    private final Object key;
    private final List list;
    private final Map map;
    private final DefaultJSONParser parser;

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
    }

    public ResolveFieldDeserializer(DefaultJSONParser defaultJSONParser, List list, int i) {
        super(null, null);
        this.parser = defaultJSONParser;
        this.index = i;
        this.list = list;
        this.key = null;
        this.map = null;
        this.collection = null;
    }

    public ResolveFieldDeserializer(Map map, Object obj) {
        super(null, null);
        this.parser = null;
        this.index = -1;
        this.list = null;
        this.key = obj;
        this.map = map;
        this.collection = null;
    }

    public ResolveFieldDeserializer(Collection collection) {
        super(null, null);
        this.parser = null;
        this.index = -1;
        this.list = null;
        this.key = null;
        this.map = null;
        this.collection = collection;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void setValue(Object obj, Object obj2) {
        JSONArray jSONArray;
        Object relatedArray;
        if (this.map != null) {
            this.map.put(this.key, obj2);
            return;
        }
        if (this.collection != null) {
            this.collection.add(obj2);
            return;
        }
        this.list.set(this.index, obj2);
        if (!(this.list instanceof JSONArray) || (relatedArray = (jSONArray = (JSONArray) this.list).getRelatedArray()) == null || Array.getLength(relatedArray) <= this.index) {
            return;
        }
        if (jSONArray.getComponentType() != null) {
            obj2 = TypeUtils.cast(obj2, jSONArray.getComponentType(), this.parser.getConfig());
        }
        Array.set(relatedArray, this.index, obj2);
    }
}
