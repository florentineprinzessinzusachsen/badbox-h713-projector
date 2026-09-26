package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ListSerializer implements ObjectSerializer {
    public static final ListSerializer instance = new ListSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        char c;
        int i2;
        int i3;
        Object obj3;
        Object obj4 = obj;
        boolean z = jSONSerializer.out.isEnabled(SerializerFeature.WriteClassName) || SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName);
        SerializeWriter serializeWriter = jSONSerializer.out;
        Type collectionItemType = z ? TypeUtils.getCollectionItemType(type) : null;
        if (obj4 == null) {
            serializeWriter.writeNull(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List) obj4;
        if (list.size() == 0) {
            serializeWriter.append((CharSequence) "[]");
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        Object obj5 = obj2;
        jSONSerializer.setContext(serialContext, obj4, obj5, 0);
        try {
            char c2 = ']';
            char c3 = ',';
            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                serializeWriter.append('[');
                jSONSerializer.incrementIndent();
                int i4 = 0;
                for (Object obj6 : list) {
                    if (i4 != 0) {
                        serializeWriter.append(c3);
                    }
                    jSONSerializer.println();
                    if (obj6 != null) {
                        if (jSONSerializer.containsReference(obj6)) {
                            jSONSerializer.writeReference(obj6);
                        } else {
                            ObjectSerializer objectWriter = jSONSerializer.getObjectWriter(obj6.getClass());
                            jSONSerializer.context = new SerialContext(serialContext, obj4, obj5, 0, 0);
                            objectWriter.write(jSONSerializer, obj6, Integer.valueOf(i4), collectionItemType, i);
                        }
                    } else {
                        jSONSerializer.out.writeNull();
                    }
                    i4++;
                    c2 = c2;
                    c3 = ',';
                    obj5 = obj2;
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.append(c2);
                return;
            }
            char c4 = ']';
            serializeWriter.append('[');
            int size = list.size();
            int i5 = 0;
            while (i5 < size) {
                Object obj7 = list.get(i5);
                if (i5 != 0) {
                    c = ',';
                    serializeWriter.append(',');
                } else {
                    c = ',';
                }
                if (obj7 == null) {
                    serializeWriter.append((CharSequence) "null");
                } else {
                    Class<?> cls = obj7.getClass();
                    if (cls == Integer.class) {
                        serializeWriter.writeInt(((Integer) obj7).intValue());
                    } else {
                        if (cls == Long.class) {
                            long jLongValue = ((Long) obj7).longValue();
                            if (z) {
                                serializeWriter.writeLong(jLongValue);
                                serializeWriter.write(76);
                            } else {
                                serializeWriter.writeLong(jLongValue);
                            }
                        } else if ((SerializerFeature.DisableCircularReferenceDetect.mask & i) != 0) {
                            i2 = i5;
                            i3 = size;
                            jSONSerializer.getObjectWriter(obj7.getClass()).write(jSONSerializer, obj7, Integer.valueOf(i5), collectionItemType, i);
                        } else {
                            i2 = i5;
                            i3 = size;
                            if (serializeWriter.disableCircularReferenceDetect) {
                                obj3 = obj7;
                            } else {
                                jSONSerializer.context = new SerialContext(serialContext, obj4, obj2, 0, 0);
                                obj3 = obj7;
                            }
                            if (jSONSerializer.containsReference(obj3)) {
                                jSONSerializer.writeReference(obj3);
                            } else {
                                ObjectSerializer objectWriter2 = jSONSerializer.getObjectWriter(obj3.getClass());
                                if ((SerializerFeature.WriteClassName.mask & i) != 0 && (objectWriter2 instanceof JavaBeanSerializer)) {
                                    ((JavaBeanSerializer) objectWriter2).writeNoneASM(jSONSerializer, obj3, Integer.valueOf(i2), collectionItemType, i);
                                } else {
                                    objectWriter2.write(jSONSerializer, obj3, Integer.valueOf(i2), collectionItemType, i);
                                }
                            }
                        }
                        i5 = i2 + 1;
                        size = i3;
                        obj4 = obj;
                        c4 = ']';
                    }
                }
                i2 = i5;
                i3 = size;
                i5 = i2 + 1;
                size = i3;
                obj4 = obj;
                c4 = ']';
            }
            serializeWriter.append(c4);
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
