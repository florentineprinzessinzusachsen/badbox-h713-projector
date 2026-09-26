package com.tools;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f50a = "Plugin";

    private d() {
        throw new AssertionError();
    }

    public static boolean a(Context context, String str) {
        return b(context, str, false);
    }

    public static boolean b(Context context, String str, boolean z) {
        return context.getSharedPreferences(f50a, 0).getBoolean(str, z);
    }

    public static float c(Context context, String str) {
        return d(context, str, -1.0f);
    }

    public static float d(Context context, String str, float f) {
        return context.getSharedPreferences(f50a, 0).getFloat(str, f);
    }

    public static int e(Context context, String str) {
        return f(context, str, -1);
    }

    public static int f(Context context, String str, int i) {
        return context.getSharedPreferences(f50a, 0).getInt(str, i);
    }

    public static long g(Context context, String str) {
        return h(context, str, -1L);
    }

    public static long h(Context context, String str, long j) {
        return context.getSharedPreferences(f50a, 0).getLong(str, j);
    }

    public static SharedPreferences i(Context context) {
        return context.getSharedPreferences(f50a, 0);
    }

    public static String j(Context context, String str) {
        return k(context, str, null);
    }

    public static String k(Context context, String str, String str2) {
        return context.getSharedPreferences(f50a, 0).getString(str, str2);
    }

    public static boolean l(Context context, String str, boolean z) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f50a, 0).edit();
        editorEdit.putBoolean(str, z);
        return editorEdit.commit();
    }

    public static boolean m(Context context, String str, float f) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f50a, 0).edit();
        editorEdit.putFloat(str, f);
        return editorEdit.commit();
    }

    public static boolean n(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f50a, 0).edit();
        editorEdit.putInt(str, i);
        return editorEdit.commit();
    }

    public static boolean o(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f50a, 0).edit();
        editorEdit.putLong(str, j);
        return editorEdit.commit();
    }

    public static boolean p(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f50a, 0).edit();
        editorEdit.putString(str, str2);
        return editorEdit.commit();
    }

    public static boolean q(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(f50a, 0).edit();
        editorEdit.remove(str);
        return editorEdit.commit();
    }
}
