package com.umeng.commonsdk.internal.utils;

import com.android.umanalytics.utils.ShellUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: ExecShell.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: compiled from: ExecShell.java */
    public enum a {
        check_su_binary(new String[]{"/system/xbin/which", ShellUtils.COMMAND_SU});


        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String[] f3845b;

        a(String[] strArr) {
            this.f3845b = strArr;
        }
    }

    public ArrayList a(a aVar) {
        ArrayList arrayList = new ArrayList();
        try {
            Process processExec = Runtime.getRuntime().exec(aVar.f3845b);
            new BufferedWriter(new OutputStreamWriter(processExec.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    arrayList.add(line);
                } catch (Exception unused) {
                }
            }
            return arrayList;
        } catch (Exception unused2) {
            return null;
        }
    }
}
