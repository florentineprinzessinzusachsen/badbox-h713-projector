package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import sun.reflect.annotation.AnnotationType;

/* JADX INFO: loaded from: classes.dex */
public class AnnotationSerializer implements ObjectSerializer {
    public static AnnotationSerializer instance = new AnnotationSerializer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IllegalAccessException, IOException, InvocationTargetException {
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        if (interfaces.length == 1 && interfaces[0].isAnnotation()) {
            Map mapMembers = AnnotationType.getInstance(interfaces[0]).members();
            JSONObject jSONObject = new JSONObject(mapMembers.size());
            Object objInvoke = null;
            for (Map.Entry entry : mapMembers.entrySet()) {
                try {
                    objInvoke = ((Method) entry.getValue()).invoke(obj, new Object[0]);
                } catch (IllegalAccessException | InvocationTargetException unused) {
                }
                jSONObject.put((String) entry.getKey(), JSON.toJSON(objInvoke));
            }
            jSONSerializer.write(jSONObject);
        }
    }
}
