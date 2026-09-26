package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ArrayListTypeFieldDeserializer extends FieldDeserializer {
    private ObjectDeserializer deserializer;
    private int itemFastMatchToken;
    private final Type itemType;

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public int getFastMatchToken() {
        return 14;
    }

    public ArrayListTypeFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo);
        if (fieldInfo.fieldType instanceof ParameterizedType) {
            Type type = ((ParameterizedType) fieldInfo.fieldType).getActualTypeArguments()[0];
            if (type instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                if (upperBounds.length == 1) {
                    type = upperBounds[0];
                }
            }
            this.itemType = type;
            return;
        }
        this.itemType = Object.class;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        if (i == 8 || (i == 4 && jSONLexer.stringVal().length() == 0)) {
            setValue(obj, (String) null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ParseContext context = defaultJSONParser.getContext();
        defaultJSONParser.setContext(context, obj, this.fieldInfo.name);
        parseArray(defaultJSONParser, type, arrayList);
        defaultJSONParser.setContext(context);
        if (obj == null) {
            map.put(this.fieldInfo.name, arrayList);
        } else {
            setValue(obj, arrayList);
        }
    }

    public final void parseArray(DefaultJSONParser defaultJSONParser, Type type, Collection collection) {
        Class cls;
        int i;
        int i2;
        Type parameterizedTypeImpl = this.itemType;
        ObjectDeserializer deserializer = this.deserializer;
        int i3 = 0;
        if (type instanceof ParameterizedType) {
            if (parameterizedTypeImpl instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) parameterizedTypeImpl;
                ParameterizedType parameterizedType = (ParameterizedType) type;
                cls = parameterizedType.getRawType() instanceof Class ? (Class) parameterizedType.getRawType() : null;
                if (cls == null) {
                    i2 = -1;
                    break;
                }
                int length = cls.getTypeParameters().length;
                i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        i2 = -1;
                        break;
                    } else if (cls.getTypeParameters()[i2].getName().equals(typeVariable.getName())) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i2 != -1) {
                    parameterizedTypeImpl = parameterizedType.getActualTypeArguments()[i2];
                    if (!parameterizedTypeImpl.equals(this.itemType)) {
                        deserializer = defaultJSONParser.getConfig().getDeserializer(parameterizedTypeImpl);
                    }
                }
            } else if (parameterizedTypeImpl instanceof ParameterizedType) {
                ParameterizedType parameterizedType2 = (ParameterizedType) parameterizedTypeImpl;
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                if (actualTypeArguments.length == 1 && (actualTypeArguments[0] instanceof TypeVariable)) {
                    TypeVariable typeVariable2 = (TypeVariable) actualTypeArguments[0];
                    ParameterizedType parameterizedType3 = (ParameterizedType) type;
                    cls = parameterizedType3.getRawType() instanceof Class ? (Class) parameterizedType3.getRawType() : null;
                    if (cls == null) {
                        i = -1;
                        break;
                    }
                    int length2 = cls.getTypeParameters().length;
                    i = 0;
                    while (true) {
                        if (i >= length2) {
                            i = -1;
                            break;
                        } else if (cls.getTypeParameters()[i].getName().equals(typeVariable2.getName())) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (i != -1) {
                        actualTypeArguments[0] = parameterizedType3.getActualTypeArguments()[i];
                        parameterizedTypeImpl = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType2.getOwnerType(), parameterizedType2.getRawType());
                    }
                }
            }
        } else if ((parameterizedTypeImpl instanceof TypeVariable) && (type instanceof Class)) {
            Class cls2 = (Class) type;
            TypeVariable typeVariable3 = (TypeVariable) parameterizedTypeImpl;
            cls2.getTypeParameters();
            int length3 = cls2.getTypeParameters().length;
            for (int i4 = 0; i4 < length3; i4++) {
                TypeVariable typeVariable4 = cls2.getTypeParameters()[i4];
                if (typeVariable4.getName().equals(typeVariable3.getName())) {
                    Type[] bounds = typeVariable4.getBounds();
                    if (bounds.length != 1) {
                        break;
                    }
                    parameterizedTypeImpl = bounds[0];
                    break;
                }
            }
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 14) {
            if (deserializer == null) {
                deserializer = defaultJSONParser.getConfig().getDeserializer(parameterizedTypeImpl);
                this.deserializer = deserializer;
                this.itemFastMatchToken = this.deserializer.getFastMatchToken();
            }
            ObjectDeserializer objectDeserializer = deserializer;
            jSONLexer.nextToken(this.itemFastMatchToken);
            while (true) {
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (jSONLexer.token() == 16) {
                        jSONLexer.nextToken();
                    }
                }
                if (jSONLexer.token() != 15) {
                    collection.add(objectDeserializer.deserialze(defaultJSONParser, parameterizedTypeImpl, Integer.valueOf(i3)));
                    defaultJSONParser.checkListResolve(collection);
                    if (jSONLexer.token() == 16) {
                        jSONLexer.nextToken(this.itemFastMatchToken);
                    }
                    i3++;
                } else {
                    jSONLexer.nextToken(16);
                    return;
                }
            }
        } else {
            if (deserializer == null) {
                deserializer = defaultJSONParser.getConfig().getDeserializer(parameterizedTypeImpl);
                this.deserializer = deserializer;
            }
            collection.add(deserializer.deserialze(defaultJSONParser, parameterizedTypeImpl, 0));
            defaultJSONParser.checkListResolve(collection);
        }
    }
}
