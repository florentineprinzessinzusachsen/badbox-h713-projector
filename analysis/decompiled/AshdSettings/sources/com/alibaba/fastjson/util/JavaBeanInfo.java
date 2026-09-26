package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/* JADX INFO: loaded from: classes.dex */
public class JavaBeanInfo {
    public final Method buildMethod;
    public final Class<?> builderClass;
    public final Class<?> clazz;
    public final Constructor<?> creatorConstructor;
    public Type[] creatorConstructorParameterTypes;
    public String[] creatorConstructorParameters;
    public final Constructor<?> defaultConstructor;
    public final int defaultConstructorParameterSize;
    public final Method factoryMethod;
    public final FieldInfo[] fields;
    public final JSONType jsonType;
    public boolean kotlin;
    public Constructor<?> kotlinDefaultConstructor;
    public String[] orders;
    public final int parserFeatures;
    public final FieldInfo[] sortedFields;
    public final String typeKey;
    public final String typeName;

    public JavaBeanInfo(Class<?> cls, Class<?> cls2, Constructor<?> constructor, Constructor<?> constructor2, Method method, Method method2, JSONType jSONType, List<FieldInfo> list) {
        JSONField jSONField;
        this.clazz = cls;
        this.builderClass = cls2;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.parserFeatures = TypeUtils.getParserFeatures(cls);
        this.buildMethod = method2;
        this.jsonType = jSONType;
        if (jSONType != null) {
            String strTypeName = jSONType.typeName();
            String strTypeKey = jSONType.typeKey();
            this.typeKey = strTypeKey.length() <= 0 ? null : strTypeKey;
            if (strTypeName.length() != 0) {
                this.typeName = strTypeName;
            } else {
                this.typeName = cls.getName();
            }
            String[] strArrOrders = jSONType.orders();
            this.orders = strArrOrders.length == 0 ? null : strArrOrders;
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            this.orders = null;
        }
        this.fields = new FieldInfo[list.size()];
        list.toArray(this.fields);
        FieldInfo[] fieldInfoArr = new FieldInfo[this.fields.length];
        boolean z = false;
        if (this.orders != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (FieldInfo fieldInfo : this.fields) {
                linkedHashMap.put(fieldInfo.name, fieldInfo);
            }
            int i = 0;
            for (String str : this.orders) {
                FieldInfo fieldInfo2 = (FieldInfo) linkedHashMap.get(str);
                if (fieldInfo2 != null) {
                    fieldInfoArr[i] = fieldInfo2;
                    linkedHashMap.remove(str);
                    i++;
                }
            }
            Iterator it = linkedHashMap.values().iterator();
            while (it.hasNext()) {
                fieldInfoArr[i] = (FieldInfo) it.next();
                i++;
            }
        } else {
            System.arraycopy(this.fields, 0, fieldInfoArr, 0, this.fields.length);
            Arrays.sort(fieldInfoArr);
        }
        this.sortedFields = Arrays.equals(this.fields, fieldInfoArr) ? this.fields : fieldInfoArr;
        if (constructor != null) {
            this.defaultConstructorParameterSize = constructor.getParameterTypes().length;
        } else if (method != null) {
            this.defaultConstructorParameterSize = method.getParameterTypes().length;
        } else {
            this.defaultConstructorParameterSize = 0;
        }
        if (constructor2 != null) {
            this.creatorConstructorParameterTypes = constructor2.getParameterTypes();
            this.kotlin = TypeUtils.isKotlin(cls);
            if (this.kotlin) {
                this.creatorConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                try {
                    this.kotlinDefaultConstructor = cls.getConstructor(new Class[0]);
                } catch (Throwable unused) {
                }
                Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                for (int i2 = 0; i2 < this.creatorConstructorParameters.length && i2 < parameterAnnotations.length; i2++) {
                    Annotation[] annotationArr = parameterAnnotations[i2];
                    int length = annotationArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            jSONField = null;
                            break;
                        }
                        Annotation annotation = annotationArr[i3];
                        if (annotation instanceof JSONField) {
                            jSONField = (JSONField) annotation;
                            break;
                        }
                        i3++;
                    }
                    if (jSONField != null) {
                        String strName = jSONField.name();
                        if (strName.length() > 0) {
                            this.creatorConstructorParameters[i2] = strName;
                        }
                    }
                }
                return;
            }
            if (this.creatorConstructorParameterTypes.length == this.fields.length) {
                int i4 = 0;
                while (true) {
                    if (i4 >= this.creatorConstructorParameterTypes.length) {
                        z = true;
                        break;
                    } else if (this.creatorConstructorParameterTypes[i4] != this.fields[i4].fieldClass) {
                        break;
                    } else {
                        i4++;
                    }
                }
            }
            if (z) {
                return;
            }
            this.creatorConstructorParameters = ASMUtils.lookupParameterNames(constructor2);
        }
    }

    private static FieldInfo getField(List<FieldInfo> list, String str) {
        for (FieldInfo fieldInfo : list) {
            if (fieldInfo.name.equals(str)) {
                return fieldInfo;
            }
            Field field = fieldInfo.field;
            if (field != null && fieldInfo.getAnnotation() != null && field.getName().equals(str)) {
                return fieldInfo;
            }
        }
        return null;
    }

    static boolean add(List<FieldInfo> list, FieldInfo fieldInfo) {
        for (int size = list.size() - 1; size >= 0; size--) {
            FieldInfo fieldInfo2 = list.get(size);
            if (fieldInfo2.name.equals(fieldInfo.name) && (!fieldInfo2.getOnly || fieldInfo.getOnly)) {
                if (fieldInfo2.fieldClass.isAssignableFrom(fieldInfo.fieldClass)) {
                    list.set(size, fieldInfo);
                    return true;
                }
                if (fieldInfo2.compareTo(fieldInfo) >= 0) {
                    return false;
                }
                list.set(size, fieldInfo);
                return true;
            }
        }
        list.add(fieldInfo);
        return true;
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy) {
        return build(cls, type, propertyNamingStrategy, false, TypeUtils.compatibleWithJavaBean, false);
    }

    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean z, boolean z2) {
        return build(cls, type, propertyNamingStrategy, z, z2, false);
    }

    /* JADX WARN: Code duplicated, block: B:146:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:147:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:164:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:165:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:224:0x042c  */
    /* JADX WARN: Code duplicated, block: B:249:0x050f A[PHI: r11
      0x050f: PHI (r11v23 java.lang.String) = (r11v22 java.lang.String), (r11v22 java.lang.String), (r11v24 java.lang.String) binds: [B:248:0x050d, B:251:0x051a, B:255:0x0533] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:286:0x05c8  */
    /* JADX WARN: Code duplicated, block: B:288:0x05dc  */
    /* JADX WARN: Code duplicated, block: B:289:0x05ee  */
    /* JADX WARN: Code duplicated, block: B:299:0x0615  */
    /* JADX WARN: Code duplicated, block: B:301:0x0619  */
    /* JADX WARN: Code duplicated, block: B:303:0x062b  */
    /* JADX WARN: Code duplicated, block: B:305:0x0637  */
    /* JADX WARN: Code duplicated, block: B:313:0x0672  */
    /* JADX WARN: Code duplicated, block: B:315:0x067f  */
    /* JADX WARN: Code duplicated, block: B:316:0x0689 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:317:0x068b  */
    /* JADX WARN: Code duplicated, block: B:318:0x0691  */
    /* JADX WARN: Code duplicated, block: B:320:0x0695  */
    /* JADX WARN: Code duplicated, block: B:323:0x069c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:324:0x069e  */
    /* JADX WARN: Code duplicated, block: B:327:0x06a5  */
    /* JADX WARN: Code duplicated, block: B:329:0x06c3  */
    /* JADX WARN: Code duplicated, block: B:330:0x06de  */
    /* JADX WARN: Code duplicated, block: B:331:0x06e8  */
    /* JADX WARN: Code duplicated, block: B:333:0x06ec  */
    /* JADX WARN: Code duplicated, block: B:336:0x06f5  */
    /* JADX WARN: Code duplicated, block: B:338:0x0700  */
    /* JADX WARN: Code duplicated, block: B:352:0x0733  */
    /* JADX WARN: Code duplicated, block: B:354:0x0738  */
    /* JADX WARN: Code duplicated, block: B:356:0x0743  */
    /* JADX WARN: Code duplicated, block: B:359:0x0765  */
    /* JADX WARN: Code duplicated, block: B:361:0x076c  */
    /* JADX WARN: Code duplicated, block: B:362:0x0793  */
    /* JADX WARN: Code duplicated, block: B:363:0x0795  */
    /* JADX WARN: Code duplicated, block: B:365:0x0799  */
    /* JADX WARN: Code duplicated, block: B:367:0x07a4  */
    /* JADX WARN: Code duplicated, block: B:369:0x07aa  */
    /* JADX WARN: Code duplicated, block: B:371:0x07b4  */
    /* JADX WARN: Code duplicated, block: B:373:0x07d2  */
    /* JADX WARN: Code duplicated, block: B:375:0x07fe  */
    /* JADX WARN: Code duplicated, block: B:377:0x0807  */
    /* JADX WARN: Code duplicated, block: B:378:0x080e  */
    /* JADX WARN: Code duplicated, block: B:383:0x0850  */
    /* JADX WARN: Code duplicated, block: B:385:0x085c  */
    /* JADX WARN: Code duplicated, block: B:417:0x08eb  */
    /* JADX WARN: Code duplicated, block: B:419:0x090f  */
    /* JADX WARN: Code duplicated, block: B:424:0x092f A[DONT_INVERT, PHI: r0 r4 r5
      0x092f: PHI (r0v16 java.lang.String) = (r0v15 java.lang.String), (r0v15 java.lang.String), (r0v15 java.lang.String), (r0v22 java.lang.String) binds: [B:418:0x090d, B:420:0x0917, B:422:0x091d, B:416:0x08e3] A[DONT_GENERATE, DONT_INLINE]
      0x092f: PHI (r4v3 java.lang.reflect.Field[]) = 
      (r4v1 java.lang.reflect.Field[])
      (r4v1 java.lang.reflect.Field[])
      (r4v1 java.lang.reflect.Field[])
      (r4v5 java.lang.reflect.Field[])
     binds: [B:418:0x090d, B:420:0x0917, B:422:0x091d, B:416:0x08e3] A[DONT_GENERATE, DONT_INLINE]
      0x092f: PHI (r5v6 char) = (r5v4 char), (r5v4 char), (r5v4 char), (r5v8 char) binds: [B:418:0x090d, B:420:0x0917, B:422:0x091d, B:416:0x08e3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:425:0x0931  */
    /* JADX WARN: Code duplicated, block: B:429:0x093d  */
    /* JADX WARN: Code duplicated, block: B:433:0x0991  */
    /* JADX WARN: Code duplicated, block: B:438:0x09a6  */
    /* JADX WARN: Code duplicated, block: B:440:0x09aa  */
    /* JADX WARN: Code duplicated, block: B:442:0x09ad A[LOOP:6: B:441:0x09ab->B:442:0x09ad, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:459:0x0836 A[EDGE_INSN: B:459:0x0836->B:381:0x0836 BREAK  A[LOOP:4: B:284:0x05c5->B:380:0x0828], SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:356:0x0743, please report this as an issue */
    public static JavaBeanInfo build(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, boolean z, boolean z2, boolean z3) {
        Constructor<?> defaultConstructor;
        Constructor<?> constructor;
        Constructor<?> constructor2;
        Method[] methodArr;
        PropertyNamingStrategy propertyNamingStrategy2;
        ArrayList arrayList;
        Constructor<?> constructor3;
        Constructor<?> constructor4;
        Constructor<?> constructor5;
        Field[] fieldArr;
        JSONType jSONType;
        Class<?> cls2;
        int i;
        int length;
        int i2;
        int i3;
        PropertyNamingStrategy propertyNamingStrategy3;
        Field[] fieldArr2;
        Type type2;
        Method[] methods;
        int length2;
        int i4;
        Field[] fieldArr3;
        Type type3;
        XmlAccessorType annotation;
        boolean z4;
        Class<?> superclass;
        Method method;
        String name;
        JSONField jSONField;
        char c;
        String string;
        Field[] fieldArr4;
        Field field;
        JSONField jSONField2;
        Field[] fieldArr5;
        String str;
        Method method2;
        int iOrdinal;
        int iOf;
        String name2;
        Class<?> returnType;
        Class<?>[] parameterTypes;
        JSONField jSONField3;
        int i5;
        int i6;
        Method[] methodArr2;
        int i7;
        JSONField superMethodAnnotation;
        int iOf2;
        char cCharAt;
        String strDecapitalize;
        Field[] fieldArr6;
        Field field2;
        boolean z5;
        int i8;
        int i9;
        JSONField jSONField4;
        PropertyNamingStrategy propertyNamingStrategy4;
        Field[] fieldArr7;
        PropertyNamingStrategy propertyNamingStrategy5;
        JSONField jSONField5;
        Method method3;
        int i10;
        int i11;
        int i12;
        String str2;
        StringBuilder sb;
        String str3;
        Method method4;
        int i13;
        String[] strArr;
        Constructor<?> constructor6;
        int i14;
        int i15;
        String[] strArrLookupParameterNames;
        String[] strArrLookupParameterNames2;
        Class<?>[] parameterTypes2;
        JSONField jSONField6;
        int iOrdinal2;
        int iOf3;
        int i16;
        JSONField jSONField7;
        String strName;
        int i17;
        int i18;
        int iOf4;
        String str4;
        String[] strArr2;
        JSONField jSONField8;
        String strName2;
        Field field3;
        int i19;
        int iOrdinal3;
        int i20;
        PropertyNamingStrategy propertyNamingStrategyNaming;
        boolean z6 = z3;
        JSONType jSONType2 = (JSONType) TypeUtils.getAnnotation(cls, JSONType.class);
        PropertyNamingStrategy propertyNamingStrategy6 = (jSONType2 == null || (propertyNamingStrategyNaming = jSONType2.naming()) == null || propertyNamingStrategyNaming == PropertyNamingStrategy.CamelCase) ? propertyNamingStrategy : propertyNamingStrategyNaming;
        Class<?> builderClass = getBuilderClass(cls, jSONType2);
        Field[] declaredFields = cls.getDeclaredFields();
        Method[] methods2 = cls.getMethods();
        boolean zIsKotlin = TypeUtils.isKotlin(cls);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        if (!zIsKotlin || declaredConstructors.length == 1) {
            if (builderClass == null) {
                defaultConstructor = getDefaultConstructor(cls, declaredConstructors);
            } else {
                defaultConstructor = getDefaultConstructor(builderClass, builderClass.getDeclaredConstructors());
            }
            constructor = defaultConstructor;
        } else {
            constructor = null;
        }
        Method method5 = null;
        Method method6 = null;
        ArrayList arrayList2 = new ArrayList();
        if (z) {
            for (Class<?> superclass2 = cls; superclass2 != null; superclass2 = superclass2.getSuperclass()) {
                computeFields(cls, type, propertyNamingStrategy6, arrayList2, superclass2.getDeclaredFields());
            }
            return new JavaBeanInfo(cls, builderClass, constructor, null, null, null, jSONType2, arrayList2);
        }
        boolean z7 = cls.isInterface() || Modifier.isAbstract(cls.getModifiers());
        if ((constructor == null && builderClass == null) || z7) {
            propertyNamingStrategy2 = propertyNamingStrategy6;
            Constructor<?> creatorConstructor = getCreatorConstructor(declaredConstructors);
            if (creatorConstructor != null && !z7) {
                TypeUtils.setAccessible(creatorConstructor);
                Class<?>[] parameterTypes3 = creatorConstructor.getParameterTypes();
                if (parameterTypes3.length > 0) {
                    Annotation[][] parameterAnnotations = creatorConstructor.getParameterAnnotations();
                    String[] strArrLookupParameterNames3 = null;
                    int i21 = 0;
                    while (i21 < parameterTypes3.length) {
                        Annotation[] annotationArr = parameterAnnotations[i21];
                        int length3 = annotationArr.length;
                        int i22 = 0;
                        while (true) {
                            if (i22 >= length3) {
                                jSONField8 = null;
                                break;
                            }
                            Annotation annotation2 = annotationArr[i22];
                            Annotation[] annotationArr2 = annotationArr;
                            if (annotation2 instanceof JSONField) {
                                jSONField8 = (JSONField) annotation2;
                                break;
                            }
                            i22++;
                            annotationArr = annotationArr2;
                        }
                        Class<?> cls3 = parameterTypes3[i21];
                        Type type4 = creatorConstructor.getGenericParameterTypes()[i21];
                        if (jSONField8 != null) {
                            Field field4 = TypeUtils.getField(cls, jSONField8.name(), declaredFields);
                            iOrdinal3 = jSONField8.ordinal();
                            int iOf5 = SerializerFeature.of(jSONField8.serialzeFeatures());
                            int iOf6 = Feature.of(jSONField8.parseFeatures());
                            strName2 = jSONField8.name();
                            i20 = iOf6;
                            field3 = field4;
                            i19 = iOf5;
                        } else {
                            strName2 = null;
                            field3 = null;
                            i19 = 0;
                            iOrdinal3 = 0;
                            i20 = 0;
                        }
                        if (strName2 == null || strName2.length() == 0) {
                            if (strArrLookupParameterNames3 == null) {
                                strArrLookupParameterNames3 = ASMUtils.lookupParameterNames(creatorConstructor);
                            }
                            strName2 = strArrLookupParameterNames3[i21];
                        }
                        String[] strArr3 = strArrLookupParameterNames3;
                        Constructor<?> constructor7 = creatorConstructor;
                        ArrayList arrayList3 = arrayList2;
                        add(arrayList3, new FieldInfo(strName2, cls, cls3, type4, field3, iOrdinal3, i19, i20));
                        i21++;
                        methods2 = methods2;
                        arrayList2 = arrayList3;
                        strArrLookupParameterNames3 = strArr3;
                        creatorConstructor = constructor7;
                        constructor = constructor;
                    }
                }
                constructor2 = constructor;
                methodArr = methods2;
                constructor3 = creatorConstructor;
                arrayList = arrayList2;
            } else {
                constructor2 = constructor;
                methodArr = methods2;
                constructor3 = creatorConstructor;
                arrayList = arrayList2;
                Method factoryMethod = getFactoryMethod(cls, methodArr, z6);
                if (factoryMethod != null) {
                    TypeUtils.setAccessible(factoryMethod);
                    Class<?>[] parameterTypes4 = factoryMethod.getParameterTypes();
                    if (parameterTypes4.length > 0) {
                        Annotation[][] parameterAnnotations2 = factoryMethod.getParameterAnnotations();
                        String[] strArrLookupParameterNames4 = null;
                        int i23 = 0;
                        while (i23 < parameterTypes4.length) {
                            Annotation[] annotationArr3 = parameterAnnotations2[i23];
                            int length4 = annotationArr3.length;
                            int i24 = 0;
                            while (true) {
                                if (i24 >= length4) {
                                    jSONField7 = null;
                                    break;
                                }
                                Annotation annotation3 = annotationArr3[i24];
                                if (annotation3 instanceof JSONField) {
                                    jSONField7 = (JSONField) annotation3;
                                    break;
                                }
                                i24++;
                            }
                            if (jSONField7 == null && (!z6 || !TypeUtils.isJacksonCreator(factoryMethod))) {
                                throw new JSONException("illegal json creator");
                            }
                            if (jSONField7 != null) {
                                strName = jSONField7.name();
                                int iOrdinal4 = jSONField7.ordinal();
                                int iOf7 = SerializerFeature.of(jSONField7.serialzeFeatures());
                                iOf4 = Feature.of(jSONField7.parseFeatures());
                                i17 = iOrdinal4;
                                i18 = iOf7;
                            } else {
                                strName = null;
                                i17 = 0;
                                i18 = 0;
                                iOf4 = 0;
                            }
                            if (strName == null || strName.length() == 0) {
                                if (strArrLookupParameterNames4 == null) {
                                    strArrLookupParameterNames4 = ASMUtils.lookupParameterNames(factoryMethod);
                                }
                                str4 = strArrLookupParameterNames4[i23];
                                strArr2 = strArrLookupParameterNames4;
                            } else {
                                strArr2 = strArrLookupParameterNames4;
                                str4 = strName;
                            }
                            add(arrayList, new FieldInfo(str4, cls, parameterTypes4[i23], factoryMethod.getGenericParameterTypes()[i23], TypeUtils.getField(cls, str4, declaredFields), i17, i18, iOf4));
                            i23++;
                            parameterTypes4 = parameterTypes4;
                            strArrLookupParameterNames4 = strArr2;
                            factoryMethod = factoryMethod;
                            z6 = z3;
                        }
                        return new JavaBeanInfo(cls, builderClass, null, null, factoryMethod, null, jSONType2, arrayList);
                    }
                    method4 = factoryMethod;
                } else {
                    method4 = factoryMethod;
                    if (!z7) {
                        String name3 = cls.getName();
                        if (zIsKotlin && declaredConstructors.length > 0) {
                            String[] koltinConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                            Constructor<?> koltinConstructor = TypeUtils.getKoltinConstructor(declaredConstructors, koltinConstructorParameters);
                            TypeUtils.setAccessible(koltinConstructor);
                            constructor6 = koltinConstructor;
                            strArr = koltinConstructorParameters;
                        } else {
                            int length5 = declaredConstructors.length;
                            String[] strArr4 = null;
                            int i25 = 0;
                            while (true) {
                                if (i25 < length5) {
                                    Constructor<?> constructor8 = declaredConstructors[i25];
                                    Class<?>[] parameterTypes5 = constructor8.getParameterTypes();
                                    if (name3.equals("org.springframework.security.web.authentication.WebAuthenticationDetails") && parameterTypes5.length == 2 && parameterTypes5[0] == String.class && parameterTypes5[1] == String.class) {
                                        constructor8.setAccessible(true);
                                        strArrLookupParameterNames2 = ASMUtils.lookupParameterNames(constructor8);
                                    } else if (name3.equals("org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken") && parameterTypes5.length == 3 && parameterTypes5[0] == Object.class && parameterTypes5[1] == Object.class && parameterTypes5[2] == Collection.class) {
                                        constructor8.setAccessible(true);
                                        strArrLookupParameterNames2 = new String[]{"principal", "credentials", "authorities"};
                                    } else {
                                        if (name3.equals("org.springframework.security.core.authority.SimpleGrantedAuthority")) {
                                            i14 = 1;
                                            if (parameterTypes5.length == 1) {
                                                i13 = 0;
                                                if (parameterTypes5[0] == String.class) {
                                                    strArr = new String[]{"authority"};
                                                    constructor6 = constructor8;
                                                }
                                            }
                                            if ((constructor8.getModifiers() & i14) != 0) {
                                                i15 = 1;
                                            } else {
                                                i15 = i13;
                                            }
                                            if (i15 != 0 && (strArrLookupParameterNames = ASMUtils.lookupParameterNames(constructor8)) != null && strArrLookupParameterNames.length != 0 && (constructor3 == null || strArr4 == null || strArrLookupParameterNames.length > strArr4.length)) {
                                                constructor3 = constructor8;
                                                strArr4 = strArrLookupParameterNames;
                                            }
                                            i25++;
                                        } else {
                                            i14 = 1;
                                        }
                                        i13 = 0;
                                        if ((constructor8.getModifiers() & i14) != 0) {
                                            i15 = 1;
                                        } else {
                                            i15 = i13;
                                        }
                                        if (i15 != 0) {
                                            constructor3 = constructor8;
                                            strArr4 = strArrLookupParameterNames;
                                        }
                                        i25++;
                                    }
                                    strArr = strArrLookupParameterNames2;
                                    constructor6 = constructor8;
                                } else {
                                    i13 = 0;
                                    strArr = strArr4;
                                    constructor6 = constructor3;
                                }
                                if (strArr != null) {
                                    parameterTypes2 = constructor6.getParameterTypes();
                                } else {
                                    parameterTypes2 = null;
                                }
                                if (strArr == null && parameterTypes2.length == strArr.length) {
                                    Annotation[][] parameterAnnotations3 = constructor6.getParameterAnnotations();
                                    int i26 = i13;
                                    while (i26 < parameterTypes2.length) {
                                        Annotation[] annotationArr4 = parameterAnnotations3[i26];
                                        String str5 = strArr[i26];
                                        int length6 = annotationArr4.length;
                                        int i27 = i13;
                                        while (true) {
                                            if (i27 >= length6) {
                                                jSONField6 = null;
                                                break;
                                            }
                                            Annotation annotation4 = annotationArr4[i27];
                                            Annotation[] annotationArr5 = annotationArr4;
                                            if (annotation4 instanceof JSONField) {
                                                jSONField6 = (JSONField) annotation4;
                                                break;
                                            }
                                            i27++;
                                            annotationArr4 = annotationArr5;
                                        }
                                        Class<?> cls4 = parameterTypes2[i26];
                                        Type type5 = constructor6.getGenericParameterTypes()[i26];
                                        Field field5 = TypeUtils.getField(cls, str5, declaredFields);
                                        if (field5 != null && jSONField6 == null) {
                                            jSONField6 = (JSONField) field5.getAnnotation(JSONField.class);
                                        }
                                        if (jSONField6 == null) {
                                            if ("org.springframework.security.core.userdetails.User".equals(name3) && "password".equals(str5)) {
                                                iOf3 = Feature.InitStringFieldAsEmpty.mask;
                                                i16 = 0;
                                            } else {
                                                i16 = 0;
                                                iOf3 = 0;
                                            }
                                            iOrdinal2 = 0;
                                        } else {
                                            String str6 = str5;
                                            String strName3 = jSONField6.name();
                                            if (strName3.length() != 0) {
                                                str6 = strName3;
                                            }
                                            iOrdinal2 = jSONField6.ordinal();
                                            int iOf8 = SerializerFeature.of(jSONField6.serialzeFeatures());
                                            iOf3 = Feature.of(jSONField6.parseFeatures());
                                            i16 = iOf8;
                                            str5 = str6;
                                        }
                                        add(arrayList, new FieldInfo(str5, cls, cls4, type5, field5, iOrdinal2, i16, iOf3));
                                        i26++;
                                        strArr = strArr;
                                        parameterTypes2 = parameterTypes2;
                                        constructor6 = constructor6;
                                        i13 = 0;
                                    }
                                    constructor3 = constructor6;
                                    if (!zIsKotlin && !cls.getName().equals("javax.servlet.http.Cookie")) {
                                        return new JavaBeanInfo(cls, builderClass, null, constructor3, null, null, jSONType2, arrayList);
                                    }
                                } else {
                                    throw new JSONException("default constructor not found. " + cls);
                                }
                            }
                        }
                        i13 = 0;
                        if (strArr != null) {
                            parameterTypes2 = constructor6.getParameterTypes();
                        } else {
                            parameterTypes2 = null;
                        }
                        if (strArr == null) {
                        }
                        throw new JSONException("default constructor not found. " + cls);
                    }
                }
                method6 = method4;
            }
        } else {
            constructor2 = constructor;
            methodArr = methods2;
            propertyNamingStrategy2 = propertyNamingStrategy6;
            arrayList = arrayList2;
            constructor3 = null;
        }
        if (constructor2 != null) {
            constructor4 = constructor2;
            TypeUtils.setAccessible(constructor4);
        } else {
            constructor4 = constructor2;
        }
        if (builderClass != null) {
            JSONPOJOBuilder jSONPOJOBuilder = (JSONPOJOBuilder) builderClass.getAnnotation(JSONPOJOBuilder.class);
            String strWithPrefix = jSONPOJOBuilder != null ? jSONPOJOBuilder.withPrefix() : null;
            if (strWithPrefix == null || strWithPrefix.length() == 0) {
                strWithPrefix = "with";
            }
            String str7 = strWithPrefix;
            Method[] methods3 = builderClass.getMethods();
            int length7 = methods3.length;
            int i28 = 0;
            while (i28 < length7) {
                Method method7 = methods3[i28];
                if (!Modifier.isStatic(method7.getModifiers()) && method7.getReturnType().equals(builderClass)) {
                    JSONField superMethodAnnotation2 = (JSONField) method7.getAnnotation(JSONField.class);
                    if (superMethodAnnotation2 == null) {
                        superMethodAnnotation2 = TypeUtils.getSuperMethodAnnotation(cls, method7);
                    }
                    JSONField jSONField9 = superMethodAnnotation2;
                    if (jSONField9 == null) {
                        i10 = 0;
                        i11 = 0;
                        i12 = 0;
                    } else if (jSONField9.deserialize()) {
                        int iOrdinal5 = jSONField9.ordinal();
                        int iOf9 = SerializerFeature.of(jSONField9.serialzeFeatures());
                        int iOf10 = Feature.of(jSONField9.parseFeatures());
                        if (jSONField9.name().length() != 0) {
                            constructor4 = constructor4;
                            i28 = i28;
                            length7 = length7;
                            methods3 = methods3;
                            declaredFields = declaredFields;
                            jSONType2 = jSONType2;
                            builderClass = builderClass;
                            add(arrayList, new FieldInfo(jSONField9.name(), method7, null, cls, type, iOrdinal5, iOf9, iOf10, jSONField9, null, null));
                            str3 = str7;
                        } else {
                            i10 = iOrdinal5;
                            i11 = iOf9;
                            i12 = iOf10;
                        }
                    } else {
                        constructor4 = constructor4;
                        i28 = i28;
                        length7 = length7;
                        methods3 = methods3;
                        str3 = str7;
                        declaredFields = declaredFields;
                        jSONType2 = jSONType2;
                        builderClass = builderClass;
                    }
                    String name4 = method7.getName();
                    if (name4.startsWith("set") && name4.length() > 3) {
                        sb = new StringBuilder(name4.substring(3));
                        str2 = str7;
                    } else {
                        str2 = str7;
                        if (name4.startsWith(str2) && name4.length() > str2.length()) {
                            sb = new StringBuilder(name4.substring(str2.length()));
                        } else {
                            str3 = str2;
                        }
                    }
                    char cCharAt2 = sb.charAt(0);
                    if (Character.isUpperCase(cCharAt2)) {
                        sb.setCharAt(0, Character.toLowerCase(cCharAt2));
                        str3 = str2;
                        add(arrayList, new FieldInfo(sb.toString(), method7, null, cls, type, i10, i11, i12, jSONField9, null, null));
                    } else {
                        str3 = str2;
                    }
                } else {
                    constructor4 = constructor4;
                    i28 = i28;
                    length7 = length7;
                    methods3 = methods3;
                    str3 = str7;
                    declaredFields = declaredFields;
                    jSONType2 = jSONType2;
                    builderClass = builderClass;
                }
                i28++;
                builderClass = builderClass;
                str7 = str3;
                constructor4 = constructor4;
                length7 = length7;
                methods3 = methods3;
                declaredFields = declaredFields;
                jSONType2 = jSONType2;
            }
            constructor5 = constructor4;
            fieldArr = declaredFields;
            jSONType = jSONType2;
            cls2 = builderClass;
            if (cls2 != null) {
                JSONPOJOBuilder jSONPOJOBuilder2 = (JSONPOJOBuilder) cls2.getAnnotation(JSONPOJOBuilder.class);
                String strBuildMethod = jSONPOJOBuilder2 != null ? jSONPOJOBuilder2.buildMethod() : null;
                if (strBuildMethod == null || strBuildMethod.length() == 0) {
                    strBuildMethod = "build";
                }
                i = 0;
                try {
                    method3 = cls2.getMethod(strBuildMethod, new Class[0]);
                } catch (NoSuchMethodException | SecurityException unused) {
                    method3 = null;
                }
                if (method3 == null) {
                    try {
                        method3 = cls2.getMethod("create", new Class[0]);
                    } catch (NoSuchMethodException | SecurityException unused2) {
                    }
                }
                if (method3 == null) {
                    throw new JSONException("buildMethod not found.");
                }
                TypeUtils.setAccessible(method3);
                method5 = method3;
            }
            length = methodArr.length;
            i2 = i;
            while (true) {
                i3 = 4;
                if (i2 < length) {
                    break;
                }
                method2 = methodArr[i2];
                iOrdinal = 0;
                iOf = 0;
                name2 = method2.getName();
                if (Modifier.isStatic(method2.getModifiers())) {
                    i5 = i2;
                    i6 = length;
                    i8 = i;
                    methodArr2 = methodArr;
                    propertyNamingStrategy5 = propertyNamingStrategy2;
                    fieldArr7 = fieldArr;
                } else {
                    returnType = method2.getReturnType();
                    if ((!returnType.equals(Void.TYPE) || returnType.equals(method2.getDeclaringClass())) && method2.getDeclaringClass() != Object.class) {
                        parameterTypes = method2.getParameterTypes();
                        if (parameterTypes.length != 0) {
                            if (parameterTypes.length > 2) {
                                i5 = i2;
                                i6 = length;
                                i8 = i;
                                methodArr2 = methodArr;
                            } else {
                                jSONField3 = (JSONField) method2.getAnnotation(JSONField.class);
                                if (jSONField3 == null && parameterTypes.length == 2 && parameterTypes[i] == String.class && parameterTypes[1] == Object.class) {
                                    i5 = i2;
                                    i6 = length;
                                    methodArr2 = methodArr;
                                    i7 = i;
                                    add(arrayList, new FieldInfo("", method2, null, cls, type, 0, 0, 0, jSONField3, null, null));
                                } else {
                                    i5 = i2;
                                    i6 = length;
                                    methodArr2 = methodArr;
                                    i7 = i;
                                    if (parameterTypes.length != 1) {
                                        i8 = i7;
                                        propertyNamingStrategy5 = propertyNamingStrategy2;
                                        fieldArr7 = fieldArr;
                                    } else {
                                        if (jSONField3 == null) {
                                            superMethodAnnotation = TypeUtils.getSuperMethodAnnotation(cls, method2);
                                        } else {
                                            superMethodAnnotation = jSONField3;
                                        }
                                        if (superMethodAnnotation == null || name2.length() >= 4) {
                                            if (superMethodAnnotation == null) {
                                                iOf2 = 0;
                                            } else if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            if (superMethodAnnotation == null || name2.startsWith("set")) {
                                                cCharAt = name2.charAt(3);
                                                if (!Character.isUpperCase(cCharAt) || cCharAt > 512) {
                                                    if (TypeUtils.compatibleWithJavaBean) {
                                                        strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                    } else {
                                                        strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                    }
                                                } else if (cCharAt == '_') {
                                                    strDecapitalize = name2.substring(4);
                                                } else if (cCharAt == 'f') {
                                                    strDecapitalize = name2.substring(3);
                                                } else if (name2.length() < 5 || !Character.isUpperCase(name2.charAt(4))) {
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                    fieldArr7 = fieldArr;
                                                    i8 = 0;
                                                } else {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                }
                                                fieldArr6 = fieldArr;
                                                field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                                if (field2 == null) {
                                                    i8 = 0;
                                                    if (parameterTypes[0] == Boolean.TYPE) {
                                                        StringBuilder sb2 = new StringBuilder();
                                                        sb2.append("is");
                                                        sb2.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                        z5 = true;
                                                        sb2.append(strDecapitalize.substring(1));
                                                        field2 = TypeUtils.getField(cls, sb2.toString(), fieldArr6);
                                                    } else {
                                                        z5 = true;
                                                    }
                                                } else {
                                                    z5 = true;
                                                    i8 = 0;
                                                }
                                                if (field2 != null) {
                                                    jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                    if (jSONField5 != null) {
                                                        if (jSONField5.deserialize()) {
                                                            iOrdinal = jSONField5.ordinal();
                                                            iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                            iOf2 = Feature.of(jSONField5.parseFeatures());
                                                            if (jSONField5.name().length() != 0) {
                                                                add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                            }
                                                        }
                                                        fieldArr7 = fieldArr6;
                                                        propertyNamingStrategy5 = propertyNamingStrategy2;
                                                    }
                                                    i9 = iOf2;
                                                    jSONField4 = jSONField5;
                                                } else {
                                                    i9 = iOf2;
                                                    jSONField4 = null;
                                                }
                                                if (propertyNamingStrategy2 != null) {
                                                    propertyNamingStrategy4 = propertyNamingStrategy2;
                                                    strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                                } else {
                                                    propertyNamingStrategy4 = propertyNamingStrategy2;
                                                }
                                                fieldArr7 = fieldArr6;
                                                propertyNamingStrategy5 = propertyNamingStrategy4;
                                                add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                            } else {
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                        }
                                    }
                                }
                                i8 = i7;
                            }
                            propertyNamingStrategy5 = propertyNamingStrategy2;
                            fieldArr7 = fieldArr;
                        } else {
                            i5 = i2;
                            i6 = length;
                            i8 = i;
                            methodArr2 = methodArr;
                            propertyNamingStrategy5 = propertyNamingStrategy2;
                            fieldArr7 = fieldArr;
                        }
                    } else {
                        i5 = i2;
                        i6 = length;
                        i8 = i;
                        methodArr2 = methodArr;
                        propertyNamingStrategy5 = propertyNamingStrategy2;
                        fieldArr7 = fieldArr;
                    }
                }
                i2 = i5 + 1;
                propertyNamingStrategy2 = propertyNamingStrategy5;
                i = i8;
                length = i6;
                methodArr = methodArr2;
                fieldArr = fieldArr7;
            }
            int i29 = i;
            propertyNamingStrategy3 = propertyNamingStrategy2;
            fieldArr2 = fieldArr;
            type2 = type;
            computeFields(cls, type2, propertyNamingStrategy3, arrayList, cls.getFields());
            methods = cls.getMethods();
            length2 = methods.length;
            i4 = i29;
            while (i4 < length2) {
                method = methods[i4];
                name = method.getName();
                if (name.length() < i3 && !Modifier.isStatic(method.getModifiers()) && cls2 == null && name.startsWith("get") && Character.isUpperCase(name.charAt(3)) && method.getParameterTypes().length == 0 && ((Collection.class.isAssignableFrom(method.getReturnType()) || Map.class.isAssignableFrom(method.getReturnType()) || AtomicBoolean.class == method.getReturnType() || AtomicInteger.class == method.getReturnType() || AtomicLong.class == method.getReturnType()) && ((jSONField = (JSONField) method.getAnnotation(JSONField.class)) == null || !jSONField.deserialize()))) {
                    if (jSONField == null && jSONField.name().length() > 0) {
                        string = jSONField.name();
                        fieldArr4 = fieldArr2;
                        c = 3;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        c = 3;
                        sb3.append(Character.toLowerCase(name.charAt(3)));
                        sb3.append(name.substring(i3));
                        string = sb3.toString();
                        fieldArr4 = fieldArr2;
                        field = TypeUtils.getField(cls, string, fieldArr4);
                        if (field != null || (jSONField2 = (JSONField) field.getAnnotation(JSONField.class)) == null || jSONField2.deserialize()) {
                        }
                        fieldArr5 = fieldArr4;
                    }
                    if (propertyNamingStrategy3 != null) {
                        string = propertyNamingStrategy3.translate(string);
                    }
                    str = string;
                    if (getField(arrayList, str) != null) {
                        fieldArr5 = fieldArr4;
                    } else {
                        fieldArr5 = fieldArr4;
                        i4 = i4;
                        i3 = i3;
                        length2 = length2;
                        methods = methods;
                        cls2 = cls2;
                        add(arrayList, new FieldInfo(str, method, null, cls, type2, 0, 0, 0, jSONField, null, null));
                    }
                    i4++;
                    type2 = type2;
                    i3 = i3;
                    length2 = length2;
                    methods = methods;
                    fieldArr2 = fieldArr5;
                    cls2 = cls2;
                } else {
                    fieldArr5 = fieldArr2;
                }
                i4++;
                type2 = type2;
                i3 = i3;
                length2 = length2;
                methods = methods;
                fieldArr2 = fieldArr5;
                cls2 = cls2;
            }
            Class<?> cls5 = cls2;
            fieldArr3 = fieldArr2;
            type3 = type2;
            if (arrayList.size() == 0) {
                annotation = cls.getAnnotation(XmlAccessorType.class);
                if (annotation == null && annotation.value() == XmlAccessType.FIELD) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (z4) {
                    for (superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                        computeFields(cls, type3, propertyNamingStrategy3, arrayList, fieldArr3);
                    }
                }
            }
            return new JavaBeanInfo(cls, cls5, constructor5, constructor3, method6, method5, jSONType, arrayList);
        }
        constructor5 = constructor4;
        fieldArr = declaredFields;
        jSONType = jSONType2;
        cls2 = builderClass;
        i = 0;
        length = methodArr.length;
        i2 = i;
        while (true) {
            i3 = 4;
            if (i2 < length) {
                break;
                break;
            }
            method2 = methodArr[i2];
            iOrdinal = 0;
            iOf = 0;
            name2 = method2.getName();
            if (Modifier.isStatic(method2.getModifiers())) {
                i5 = i2;
                i6 = length;
                i8 = i;
                methodArr2 = methodArr;
                propertyNamingStrategy5 = propertyNamingStrategy2;
                fieldArr7 = fieldArr;
            } else {
                returnType = method2.getReturnType();
                if (returnType.equals(Void.TYPE)) {
                    parameterTypes = method2.getParameterTypes();
                    if (parameterTypes.length != 0) {
                        if (parameterTypes.length > 2) {
                            i5 = i2;
                            i6 = length;
                            i8 = i;
                            methodArr2 = methodArr;
                        } else {
                            jSONField3 = (JSONField) method2.getAnnotation(JSONField.class);
                            if (jSONField3 == null) {
                                i5 = i2;
                                i6 = length;
                                methodArr2 = methodArr;
                                i7 = i;
                                if (parameterTypes.length != 1) {
                                    i8 = i7;
                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                    fieldArr7 = fieldArr;
                                } else {
                                    if (jSONField3 == null) {
                                        superMethodAnnotation = TypeUtils.getSuperMethodAnnotation(cls, method2);
                                    } else {
                                        superMethodAnnotation = jSONField3;
                                    }
                                    if (superMethodAnnotation == null) {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb4 = new StringBuilder();
                                                    sb4.append("is");
                                                    sb4.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb4.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb4.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb5 = new StringBuilder();
                                                    sb5.append("is");
                                                    sb5.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb5.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb5.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    } else {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb6 = new StringBuilder();
                                                    sb6.append("is");
                                                    sb6.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb6.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb6.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb7 = new StringBuilder();
                                                    sb7.append("is");
                                                    sb7.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb7.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb7.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    }
                                }
                            } else {
                                i5 = i2;
                                i6 = length;
                                methodArr2 = methodArr;
                                i7 = i;
                                if (parameterTypes.length != 1) {
                                    i8 = i7;
                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                    fieldArr7 = fieldArr;
                                } else {
                                    if (jSONField3 == null) {
                                        superMethodAnnotation = TypeUtils.getSuperMethodAnnotation(cls, method2);
                                    } else {
                                        superMethodAnnotation = jSONField3;
                                    }
                                    if (superMethodAnnotation == null) {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb8 = new StringBuilder();
                                                    sb8.append("is");
                                                    sb8.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb8.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb8.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb9 = new StringBuilder();
                                                    sb9.append("is");
                                                    sb9.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb9.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb9.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    } else {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb10 = new StringBuilder();
                                                    sb10.append("is");
                                                    sb10.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb10.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb10.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb11 = new StringBuilder();
                                                    sb11.append("is");
                                                    sb11.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb11.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb11.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    }
                                }
                            }
                        }
                        propertyNamingStrategy5 = propertyNamingStrategy2;
                        fieldArr7 = fieldArr;
                    } else {
                        i5 = i2;
                        i6 = length;
                        i8 = i;
                        methodArr2 = methodArr;
                        propertyNamingStrategy5 = propertyNamingStrategy2;
                        fieldArr7 = fieldArr;
                    }
                } else {
                    parameterTypes = method2.getParameterTypes();
                    if (parameterTypes.length != 0) {
                        if (parameterTypes.length > 2) {
                            i5 = i2;
                            i6 = length;
                            i8 = i;
                            methodArr2 = methodArr;
                        } else {
                            jSONField3 = (JSONField) method2.getAnnotation(JSONField.class);
                            if (jSONField3 == null) {
                                i5 = i2;
                                i6 = length;
                                methodArr2 = methodArr;
                                i7 = i;
                                if (parameterTypes.length != 1) {
                                    i8 = i7;
                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                    fieldArr7 = fieldArr;
                                } else {
                                    if (jSONField3 == null) {
                                        superMethodAnnotation = TypeUtils.getSuperMethodAnnotation(cls, method2);
                                    } else {
                                        superMethodAnnotation = jSONField3;
                                    }
                                    if (superMethodAnnotation == null) {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb12 = new StringBuilder();
                                                    sb12.append("is");
                                                    sb12.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb12.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb12.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb13 = new StringBuilder();
                                                    sb13.append("is");
                                                    sb13.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb13.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb13.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    } else {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb14 = new StringBuilder();
                                                    sb14.append("is");
                                                    sb14.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb14.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb14.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb15 = new StringBuilder();
                                                    sb15.append("is");
                                                    sb15.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb15.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb15.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    }
                                }
                            } else {
                                i5 = i2;
                                i6 = length;
                                methodArr2 = methodArr;
                                i7 = i;
                                if (parameterTypes.length != 1) {
                                    i8 = i7;
                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                    fieldArr7 = fieldArr;
                                } else {
                                    if (jSONField3 == null) {
                                        superMethodAnnotation = TypeUtils.getSuperMethodAnnotation(cls, method2);
                                    } else {
                                        superMethodAnnotation = jSONField3;
                                    }
                                    if (superMethodAnnotation == null) {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb16 = new StringBuilder();
                                                    sb16.append("is");
                                                    sb16.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb16.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb16.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb17 = new StringBuilder();
                                                    sb17.append("is");
                                                    sb17.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb17.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb17.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    } else {
                                        if (superMethodAnnotation == null) {
                                            if (superMethodAnnotation.deserialize()) {
                                                iOrdinal = superMethodAnnotation.ordinal();
                                                iOf = SerializerFeature.of(superMethodAnnotation.serialzeFeatures());
                                                iOf2 = Feature.of(superMethodAnnotation.parseFeatures());
                                                if (superMethodAnnotation.name().length() != 0) {
                                                    add(arrayList, new FieldInfo(superMethodAnnotation.name(), method2, null, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, null, null));
                                                }
                                                propertyNamingStrategy5 = propertyNamingStrategy2;
                                                fieldArr7 = fieldArr;
                                                i8 = 0;
                                            }
                                            i8 = i7;
                                        } else {
                                            iOf2 = 0;
                                        }
                                        if (superMethodAnnotation == null) {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb18 = new StringBuilder();
                                                    sb18.append("is");
                                                    sb18.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb18.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb18.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        } else {
                                            cCharAt = name2.charAt(3);
                                            if (Character.isUpperCase(cCharAt)) {
                                                if (TypeUtils.compatibleWithJavaBean) {
                                                    strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                                } else {
                                                    strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                                }
                                            } else if (TypeUtils.compatibleWithJavaBean) {
                                                strDecapitalize = TypeUtils.decapitalize(name2.substring(3));
                                            } else {
                                                strDecapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                                            }
                                            fieldArr6 = fieldArr;
                                            field2 = TypeUtils.getField(cls, strDecapitalize, fieldArr6);
                                            if (field2 == null) {
                                                i8 = 0;
                                                if (parameterTypes[0] == Boolean.TYPE) {
                                                    StringBuilder sb19 = new StringBuilder();
                                                    sb19.append("is");
                                                    sb19.append(Character.toUpperCase(strDecapitalize.charAt(0)));
                                                    z5 = true;
                                                    sb19.append(strDecapitalize.substring(1));
                                                    field2 = TypeUtils.getField(cls, sb19.toString(), fieldArr6);
                                                } else {
                                                    z5 = true;
                                                }
                                            } else {
                                                z5 = true;
                                                i8 = 0;
                                            }
                                            if (field2 != null) {
                                                jSONField5 = (JSONField) field2.getAnnotation(JSONField.class);
                                                if (jSONField5 != null) {
                                                    if (jSONField5.deserialize()) {
                                                        iOrdinal = jSONField5.ordinal();
                                                        iOf = SerializerFeature.of(jSONField5.serialzeFeatures());
                                                        iOf2 = Feature.of(jSONField5.parseFeatures());
                                                        if (jSONField5.name().length() != 0) {
                                                            add(arrayList, new FieldInfo(jSONField5.name(), method2, field2, cls, type, iOrdinal, iOf, iOf2, superMethodAnnotation, jSONField5, null));
                                                        }
                                                    }
                                                    fieldArr7 = fieldArr6;
                                                    propertyNamingStrategy5 = propertyNamingStrategy2;
                                                }
                                                i9 = iOf2;
                                                jSONField4 = jSONField5;
                                            } else {
                                                i9 = iOf2;
                                                jSONField4 = null;
                                            }
                                            if (propertyNamingStrategy2 != null) {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                                strDecapitalize = propertyNamingStrategy4.translate(strDecapitalize);
                                            } else {
                                                propertyNamingStrategy4 = propertyNamingStrategy2;
                                            }
                                            fieldArr7 = fieldArr6;
                                            propertyNamingStrategy5 = propertyNamingStrategy4;
                                            add(arrayList, new FieldInfo(strDecapitalize, method2, field2, cls, type, iOrdinal, iOf, i9, superMethodAnnotation, jSONField4, null));
                                        }
                                    }
                                }
                            }
                        }
                        propertyNamingStrategy5 = propertyNamingStrategy2;
                        fieldArr7 = fieldArr;
                    } else {
                        i5 = i2;
                        i6 = length;
                        i8 = i;
                        methodArr2 = methodArr;
                        propertyNamingStrategy5 = propertyNamingStrategy2;
                        fieldArr7 = fieldArr;
                    }
                }
            }
            i2 = i5 + 1;
            propertyNamingStrategy2 = propertyNamingStrategy5;
            i = i8;
            length = i6;
            methodArr = methodArr2;
            fieldArr = fieldArr7;
        }
        int i210 = i;
        propertyNamingStrategy3 = propertyNamingStrategy2;
        fieldArr2 = fieldArr;
        type2 = type;
        computeFields(cls, type2, propertyNamingStrategy3, arrayList, cls.getFields());
        methods = cls.getMethods();
        length2 = methods.length;
        i4 = i210;
        while (i4 < length2) {
            method = methods[i4];
            name = method.getName();
            if (name.length() < i3) {
                fieldArr5 = fieldArr2;
            } else {
                if (jSONField == null) {
                    StringBuilder sb20 = new StringBuilder();
                    c = 3;
                    sb20.append(Character.toLowerCase(name.charAt(3)));
                    sb20.append(name.substring(i3));
                    string = sb20.toString();
                    fieldArr4 = fieldArr2;
                    field = TypeUtils.getField(cls, string, fieldArr4);
                    if (field != null) {
                        if (propertyNamingStrategy3 != null) {
                            string = propertyNamingStrategy3.translate(string);
                        }
                        str = string;
                        if (getField(arrayList, str) != null) {
                            fieldArr5 = fieldArr4;
                            i4 = i4;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            cls2 = cls2;
                            add(arrayList, new FieldInfo(str, method, null, cls, type2, 0, 0, 0, jSONField, null, null));
                        }
                    } else {
                        if (propertyNamingStrategy3 != null) {
                            string = propertyNamingStrategy3.translate(string);
                        }
                        str = string;
                        if (getField(arrayList, str) != null) {
                            fieldArr5 = fieldArr4;
                            i4 = i4;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            cls2 = cls2;
                            add(arrayList, new FieldInfo(str, method, null, cls, type2, 0, 0, 0, jSONField, null, null));
                        }
                    }
                } else {
                    StringBuilder sb21 = new StringBuilder();
                    c = 3;
                    sb21.append(Character.toLowerCase(name.charAt(3)));
                    sb21.append(name.substring(i3));
                    string = sb21.toString();
                    fieldArr4 = fieldArr2;
                    field = TypeUtils.getField(cls, string, fieldArr4);
                    if (field != null) {
                        if (propertyNamingStrategy3 != null) {
                            string = propertyNamingStrategy3.translate(string);
                        }
                        str = string;
                        if (getField(arrayList, str) != null) {
                            fieldArr5 = fieldArr4;
                            i4 = i4;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            cls2 = cls2;
                            add(arrayList, new FieldInfo(str, method, null, cls, type2, 0, 0, 0, jSONField, null, null));
                        }
                    } else {
                        if (propertyNamingStrategy3 != null) {
                            string = propertyNamingStrategy3.translate(string);
                        }
                        str = string;
                        if (getField(arrayList, str) != null) {
                            fieldArr5 = fieldArr4;
                            i4 = i4;
                            i3 = i3;
                            length2 = length2;
                            methods = methods;
                            cls2 = cls2;
                            add(arrayList, new FieldInfo(str, method, null, cls, type2, 0, 0, 0, jSONField, null, null));
                        }
                    }
                }
                fieldArr5 = fieldArr4;
            }
            i4++;
            type2 = type2;
            i3 = i3;
            length2 = length2;
            methods = methods;
            fieldArr2 = fieldArr5;
            cls2 = cls2;
        }
        Class<?> cls6 = cls2;
        fieldArr3 = fieldArr2;
        type3 = type2;
        if (arrayList.size() == 0) {
            annotation = cls.getAnnotation(XmlAccessorType.class);
            if (annotation == null) {
                z4 = z;
            } else {
                z4 = z;
            }
            if (z4) {
                while (superclass != null) {
                    computeFields(cls, type3, propertyNamingStrategy3, arrayList, fieldArr3);
                }
            }
        }
        return new JavaBeanInfo(cls, cls6, constructor5, constructor3, method6, method5, jSONType, arrayList);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x004e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x006f  */
    /* JADX WARN: Code duplicated, block: B:35:0x007e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0085  */
    /* JADX WARN: Code duplicated, block: B:40:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:44:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:51:0x006b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:6:0x0012  */
    private static void computeFields(Class<?> cls, Type type, PropertyNamingStrategy propertyNamingStrategy, List<FieldInfo> list, Field[] fieldArr) {
        Iterator<FieldInfo> it;
        String name;
        JSONField jSONField;
        int i;
        int i2;
        int i3;
        for (Field field : fieldArr) {
            int modifiers = field.getModifiers();
            if ((modifiers & 8) == 0) {
                boolean z = true;
                if ((modifiers & 16) != 0) {
                    Class<?> type2 = field.getType();
                    if (Map.class.isAssignableFrom(type2) || Collection.class.isAssignableFrom(type2) || AtomicLong.class.equals(type2) || AtomicInteger.class.equals(type2) || AtomicBoolean.class.equals(type2)) {
                        it = list.iterator();
                        do {
                            if (it.hasNext()) {
                                z = false;
                                break;
                            }
                        } while (!it.next().name.equals(field.getName()));
                        if (z) {
                            name = field.getName();
                            jSONField = (JSONField) field.getAnnotation(JSONField.class);
                            if (jSONField != null) {
                                i = 0;
                                i2 = 0;
                                i3 = 0;
                            } else if (!jSONField.deserialize()) {
                                int iOrdinal = jSONField.ordinal();
                                int iOf = SerializerFeature.of(jSONField.serialzeFeatures());
                                int iOf2 = Feature.of(jSONField.parseFeatures());
                                if (jSONField.name().length() != 0) {
                                    name = jSONField.name();
                                }
                                i = iOrdinal;
                                i2 = iOf;
                                i3 = iOf2;
                            }
                            if (propertyNamingStrategy != null) {
                                name = propertyNamingStrategy.translate(name);
                            }
                            add(list, new FieldInfo(name, null, field, cls, type, i, i2, i3, null, jSONField, null));
                        }
                    }
                } else {
                    it = list.iterator();
                    do {
                        if (it.hasNext()) {
                            z = false;
                            break;
                        }
                    } while (!it.next().name.equals(field.getName()));
                    if (z) {
                        name = field.getName();
                        jSONField = (JSONField) field.getAnnotation(JSONField.class);
                        if (jSONField != null) {
                            i = 0;
                            i2 = 0;
                            i3 = 0;
                        } else if (!jSONField.deserialize()) {
                            int iOrdinal2 = jSONField.ordinal();
                            int iOf3 = SerializerFeature.of(jSONField.serialzeFeatures());
                            int iOf4 = Feature.of(jSONField.parseFeatures());
                            if (jSONField.name().length() != 0) {
                                name = jSONField.name();
                            }
                            i = iOrdinal2;
                            i2 = iOf3;
                            i3 = iOf4;
                        }
                        if (propertyNamingStrategy != null) {
                            name = propertyNamingStrategy.translate(name);
                        }
                        add(list, new FieldInfo(name, null, field, cls, type, i, i2, i3, null, jSONField, null));
                    }
                }
            }
        }
    }

    static Constructor<?> getDefaultConstructor(Class<?> cls, Constructor<?>[] constructorArr) {
        Constructor<?> constructor = null;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        for (Constructor<?> constructor2 : constructorArr) {
            if (constructor2.getParameterTypes().length == 0) {
                constructor = constructor2;
                break;
            }
        }
        if (constructor != null || !cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
            return constructor;
        }
        for (Constructor<?> constructor3 : constructorArr) {
            Class<?>[] parameterTypes = constructor3.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                return constructor3;
            }
        }
        return constructor;
    }

    public static Constructor<?> getCreatorConstructor(Constructor[] constructorArr) {
        boolean z;
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                if (constructor != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                constructor = constructor2;
            }
        }
        if (constructor != null) {
            return constructor;
        }
        for (Constructor constructor3 : constructorArr) {
            Annotation[][] parameterAnnotations = constructor3.getParameterAnnotations();
            if (parameterAnnotations.length != 0) {
                int length = parameterAnnotations.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        break;
                    }
                    Annotation[] annotationArr = parameterAnnotations[i];
                    int length2 = annotationArr.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length2) {
                            z = false;
                            break;
                        }
                        if (annotationArr[i2] instanceof JSONField) {
                            break;
                        }
                        i2++;
                    }
                    if (!z) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (!z) {
                    continue;
                } else {
                    if (constructor != null) {
                        throw new JSONException("multi-JSONCreator");
                    }
                    constructor = constructor3;
                }
            }
        }
        return constructor != null ? constructor : constructor;
    }

    private static Method getFactoryMethod(Class<?> cls, Method[] methodArr, boolean z) {
        Method method = null;
        for (Method method2 : methodArr) {
            if (Modifier.isStatic(method2.getModifiers()) && cls.isAssignableFrom(method2.getReturnType()) && ((JSONCreator) method2.getAnnotation(JSONCreator.class)) != null) {
                if (method != null) {
                    throw new JSONException("multi-JSONCreator");
                }
                method = method2;
            }
        }
        if (method == null && z) {
            for (Method method3 : methodArr) {
                if (TypeUtils.isJacksonCreator(method3)) {
                    return method3;
                }
            }
        }
        return method;
    }

    public static Class<?> getBuilderClass(JSONType jSONType) {
        return getBuilderClass(null, jSONType);
    }

    public static Class<?> getBuilderClass(Class<?> cls, JSONType jSONType) {
        Class<?> clsBuilder;
        if (cls != null && cls.getName().equals("org.springframework.security.web.savedrequest.DefaultSavedRequest")) {
            return TypeUtils.loadClass("org.springframework.security.web.savedrequest.DefaultSavedRequest$Builder");
        }
        if (jSONType == null || (clsBuilder = jSONType.builder()) == Void.class) {
            return null;
        }
        return clsBuilder;
    }
}
