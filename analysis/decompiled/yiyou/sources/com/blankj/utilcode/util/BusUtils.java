package com.blankj.utilcode.util;

import android.util.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class BusUtils {
    private static final Object NULL = "nULl";
    private static final String TAG = "BusUtils";
    private final Map<String, Set<Object>> mClassName_BusesMap;
    private final Map<String, Map<String, Object>> mClassName_Tag_Arg4StickyMap;
    private final Map<String, List<String>> mClassName_TagsMap;
    private final Map<String, List<BusInfo>> mTag_BusInfoListMap;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface Bus {
        int priority() default 0;

        boolean sticky() default false;

        String tag();

        ThreadMode threadMode() default ThreadMode.POSTING;
    }

    private static final class BusInfo {
        String className;
        List<String> classNames = new CopyOnWriteArrayList();
        String funName;
        Method method;
        String paramName;
        String paramType;
        int priority;
        boolean sticky;
        String threadMode;

        BusInfo(String str, String str2, String str3, String str4, boolean z, String str5, int i) {
            this.className = str;
            this.funName = str2;
            this.paramType = str3;
            this.paramName = str4;
            this.sticky = z;
            this.threadMode = str5;
            this.priority = i;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("BusInfo { desc: ");
            sb.append(this.className);
            sb.append("#");
            sb.append(this.funName);
            if ("".equals(this.paramType)) {
                str = "()";
            } else {
                str = "(" + this.paramType + " " + this.paramName + ")";
            }
            sb.append(str);
            sb.append(", sticky: ");
            sb.append(this.sticky);
            sb.append(", threadMode: ");
            sb.append(this.threadMode);
            sb.append(", method: ");
            sb.append(this.method);
            sb.append(", priority: ");
            sb.append(this.priority);
            sb.append(" }");
            return sb.toString();
        }
    }

    private static class LazyHolder {
        private static final BusUtils INSTANCE = new BusUtils();

        private LazyHolder() {
        }
    }

    public enum ThreadMode {
        MAIN,
        IO,
        CPU,
        CACHED,
        SINGLE,
        POSTING
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:29:0x0058  */
    private Class getClassName(String str) {
        switch (str) {
            case "boolean":
                return Boolean.TYPE;
            case "int":
                return Integer.TYPE;
            case "long":
                return Long.TYPE;
            case "short":
                return Short.TYPE;
            case "byte":
                return Byte.TYPE;
            case "double":
                return Double.TYPE;
            case "float":
                return Float.TYPE;
            case "char":
                return Character.TYPE;
            default:
                return Class.forName(str);
        }
    }

    private static BusUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Method getMethodByBusInfo(BusInfo busInfo) {
        try {
            return "".equals(busInfo.paramType) ? Class.forName(busInfo.className).getDeclaredMethod(busInfo.funName, new Class[0]) : Class.forName(busInfo.className).getDeclaredMethod(busInfo.funName, getClassName(busInfo.paramType));
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private void init() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x004b  */
    private void invokeMethod(final String str, final Object obj, final BusInfo busInfo, final boolean z) {
        byte b2;
        Runnable runnable = new Runnable() { // from class: com.blankj.utilcode.util.BusUtils.1
            @Override // java.lang.Runnable
            public void run() {
                BusUtils.this.realInvokeMethod(str, obj, busInfo, z);
            }
        };
        switch (busInfo.threadMode) {
            case "SINGLE":
                b2 = 4;
                break;
            case "IO":
                b2 = 1;
                break;
            case "CPU":
                b2 = 2;
                break;
            case "MAIN":
                b2 = 0;
                break;
            case "CACHED":
                b2 = 3;
                break;
            default:
                b2 = -1;
                break;
        }
        if (b2 == 0) {
            Utils.runOnUiThread(runnable);
            return;
        }
        if (b2 == 1) {
            ThreadUtils.getIoPool().execute(runnable);
            return;
        }
        if (b2 == 2) {
            ThreadUtils.getCpuPool().execute(runnable);
            return;
        }
        if (b2 == 3) {
            ThreadUtils.getCachedPool().execute(runnable);
        } else if (b2 != 4) {
            runnable.run();
        } else {
            ThreadUtils.getSinglePool().execute(runnable);
        }
    }

    public static void post(String str) {
        post(str, NULL);
    }

    private void postInner(String str, Object obj) {
        postInner(str, obj, false);
    }

    public static void postSticky(String str) {
        postSticky(str, NULL);
    }

    private void postStickyInner(String str, Object obj) {
        List<BusInfo> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (BusInfo busInfo : list) {
            if (!busInfo.sticky) {
                postInner(str, obj);
                return;
            }
            synchronized (this.mClassName_Tag_Arg4StickyMap) {
                Map<String, Object> map = this.mClassName_Tag_Arg4StickyMap.get(busInfo.className);
                if (map == null) {
                    map = new HashMap<>();
                    this.mClassName_Tag_Arg4StickyMap.put(busInfo.className, map);
                }
                map.put(str, obj);
            }
            postInner(str, obj, true);
        }
    }

    private void processSticky(Object obj) {
        Map<String, Object> map = this.mClassName_Tag_Arg4StickyMap.get(obj.getClass().getName());
        if (map == null) {
            return;
        }
        synchronized (this.mClassName_Tag_Arg4StickyMap) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                postInner(entry.getKey(), entry.getValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realInvokeMethod(String str, Object obj, BusInfo busInfo, boolean z) {
        HashSet hashSet = new HashSet();
        Iterator<String> it = busInfo.classNames.iterator();
        while (it.hasNext()) {
            Set<Object> set = this.mClassName_BusesMap.get(it.next());
            if (set != null && !set.isEmpty()) {
                hashSet.addAll(set);
            }
        }
        if (hashSet.size() == 0) {
            if (z) {
                return;
            }
            Log.e(TAG, "The bus of tag <" + str + "> was not registered before.");
            return;
        }
        try {
            if (obj == NULL) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    busInfo.method.invoke(it2.next(), new Object[0]);
                }
            } else {
                Iterator it3 = hashSet.iterator();
                while (it3.hasNext()) {
                    busInfo.method.invoke(it3.next(), obj);
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public static void register(Object obj) {
        getInstance().registerInner(obj);
    }

    private void registerBus(String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
        registerBus(str, str2, str3, str4, str5, z, str6, 0);
    }

    private void registerInner(Object obj) {
        if (obj == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        synchronized (this.mClassName_BusesMap) {
            Set<Object> copyOnWriteArraySet = this.mClassName_BusesMap.get(name);
            if (copyOnWriteArraySet == null) {
                copyOnWriteArraySet = new CopyOnWriteArraySet<>();
                this.mClassName_BusesMap.put(name, copyOnWriteArraySet);
            }
            copyOnWriteArraySet.add(obj);
        }
        if (this.mClassName_TagsMap.get(name) == null) {
            synchronized (this.mClassName_TagsMap) {
                if (this.mClassName_TagsMap.get(name) == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Map.Entry<String, List<BusInfo>> entry : this.mTag_BusInfoListMap.entrySet()) {
                        for (BusInfo busInfo : entry.getValue()) {
                            try {
                                if (Class.forName(busInfo.className).isAssignableFrom(cls)) {
                                    arrayList.add(entry.getKey());
                                    busInfo.classNames.add(name);
                                }
                            } catch (ClassNotFoundException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    this.mClassName_TagsMap.put(name, arrayList);
                }
            }
        }
        processSticky(obj);
    }

    public static void removeSticky(String str) {
        getInstance().removeStickyInner(str);
    }

    private void removeStickyInner(String str) {
        List<BusInfo> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (BusInfo busInfo : list) {
            if (!busInfo.sticky) {
                Log.e(TAG, "The bus of tag <" + str + "> is not sticky.");
                return;
            }
            synchronized (this.mClassName_Tag_Arg4StickyMap) {
                Map<String, Object> map = this.mClassName_Tag_Arg4StickyMap.get(busInfo.className);
                if (map != null && map.containsKey(str)) {
                    map.remove(str);
                }
                Log.e(TAG, "The sticky bus of tag <" + str + "> didn't post.");
                return;
            }
        }
    }

    public static String toString_() {
        return getInstance().toString();
    }

    public static void unregister(Object obj) {
        getInstance().unregisterInner(obj);
    }

    private void unregisterInner(Object obj) {
        if (obj == null) {
            return;
        }
        String name = obj.getClass().getName();
        synchronized (this.mClassName_BusesMap) {
            Set<Object> set = this.mClassName_BusesMap.get(name);
            if (set != null && set.contains(obj)) {
                set.remove(obj);
                return;
            }
            Log.e(TAG, "The bus of <" + obj + "> was not registered before.");
        }
    }

    public String toString() {
        return "BusUtils: " + this.mTag_BusInfoListMap;
    }

    private BusUtils() {
        this.mTag_BusInfoListMap = new HashMap();
        this.mClassName_BusesMap = new ConcurrentHashMap();
        this.mClassName_TagsMap = new HashMap();
        this.mClassName_Tag_Arg4StickyMap = new ConcurrentHashMap();
        init();
    }

    public static void post(String str, Object obj) {
        getInstance().postInner(str, obj);
    }

    private void postInner(String str, Object obj, boolean z) {
        List<BusInfo> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (BusInfo busInfo : list) {
            if (busInfo.method == null) {
                Method methodByBusInfo = getMethodByBusInfo(busInfo);
                if (methodByBusInfo == null) {
                    return;
                } else {
                    busInfo.method = methodByBusInfo;
                }
            }
            invokeMethod(str, obj, busInfo, z);
        }
    }

    public static void postSticky(String str, Object obj) {
        getInstance().postStickyInner(str, obj);
    }

    private void registerBus(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
        List<BusInfo> arrayList = this.mTag_BusInfoListMap.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mTag_BusInfoListMap.put(str, arrayList);
        }
        arrayList.add(new BusInfo(str2, str3, str4, str5, z, str6, i));
    }
}
