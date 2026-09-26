package com.blankj.utilcode.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ShellUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }

        public String toString() {
            return "result: " + this.result + "\nsuccessMsg: " + this.successMsg + "\nerrorMsg: " + this.errorMsg;
        }
    }

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static CommandResult execCmd(String str, boolean z) {
        return execCmd(new String[]{str}, z, true);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String str, boolean z, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(new String[]{str}, z, true, callback);
    }

    public static CommandResult execCmd(List<String> list, boolean z) {
        return execCmd(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static Utils.Task<CommandResult> execCmdAsync(List<String> list, boolean z, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(list == null ? null : (String[]) list.toArray(new String[0]), z, true, callback);
    }

    public static CommandResult execCmd(String[] strArr, boolean z) {
        return execCmd(strArr, z, true);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String[] strArr, boolean z, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(strArr, z, true, callback);
    }

    public static CommandResult execCmd(String str, boolean z, boolean z2) {
        return execCmd(new String[]{str}, z, z2);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String str, boolean z, boolean z2, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(new String[]{str}, z, z2, callback);
    }

    public static CommandResult execCmd(List<String> list, boolean z, boolean z2) {
        return execCmd(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    public static Utils.Task<CommandResult> execCmdAsync(List<String> list, boolean z, boolean z2, Utils.Callback<CommandResult> callback) {
        return execCmdAsync(list == null ? null : (String[]) list.toArray(new String[0]), z, z2, callback);
    }

    /* JADX WARN: Code duplicated, block: B:102:0x013a  */
    /* JADX WARN: Code duplicated, block: B:106:0x0143  */
    /* JADX WARN: Code duplicated, block: B:107:0x0145  */
    /* JADX WARN: Code duplicated, block: B:110:0x014c  */
    /* JADX WARN: Code duplicated, block: B:132:0x0177  */
    /* JADX WARN: Code duplicated, block: B:138:0x0130 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:140:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x0126 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:148:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:154:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:157:0x0159 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static CommandResult execCmd(String[] strArr, boolean z, boolean z2) throws Throwable {
        Process processExec;
        DataOutputStream dataOutputStream;
        StringBuilder sb;
        ?? r6;
        StringBuilder sb2;
        ?? bufferedReader;
        ?? bufferedReader2;
        StringBuilder sb3;
        StringBuilder sb4;
        String string;
        ?? r7;
        int iWaitFor = -1;
        if (strArr != null && strArr.length != 0) {
            ?? r3 = 0;
            ?? r4 = 0;
            r3 = 0;
            DataOutputStream dataOutputStream2 = null;
            try {
                processExec = Runtime.getRuntime().exec(z ? com.android.umanalytics.utils.ShellUtils.COMMAND_SU : com.android.umanalytics.utils.ShellUtils.COMMAND_SH);
                try {
                    dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                    try {
                        try {
                            for (String str : strArr) {
                                if (str != null) {
                                    dataOutputStream.write(str.getBytes());
                                    dataOutputStream.writeBytes(LINE_SEP);
                                    dataOutputStream.flush();
                                }
                            }
                            dataOutputStream.writeBytes("exit" + LINE_SEP);
                            dataOutputStream.flush();
                            iWaitFor = processExec.waitFor();
                            if (z2) {
                                sb3 = new StringBuilder();
                                try {
                                    sb4 = new StringBuilder();
                                    try {
                                        bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getInputStream(), "UTF-8"));
                                        try {
                                            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getErrorStream(), "UTF-8"));
                                            try {
                                                String line = bufferedReader2.readLine();
                                                if (line != null) {
                                                    sb3.append(line);
                                                    while (true) {
                                                        String line2 = bufferedReader2.readLine();
                                                        if (line2 == null) {
                                                            break;
                                                        }
                                                        sb3.append(LINE_SEP);
                                                        sb3.append(line2);
                                                    }
                                                }
                                                String line3 = bufferedReader.readLine();
                                                if (line3 != null) {
                                                    sb4.append(line3);
                                                    while (true) {
                                                        String line4 = bufferedReader.readLine();
                                                        if (line4 == null) {
                                                            break;
                                                        }
                                                        sb4.append(LINE_SEP);
                                                        sb4.append(line4);
                                                    }
                                                }
                                                r4 = bufferedReader2;
                                                r7 = bufferedReader;
                                            } catch (Exception e2) {
                                                e = e2;
                                                dataOutputStream2 = dataOutputStream;
                                                sb = sb3;
                                                e = e;
                                                sb2 = sb4;
                                                bufferedReader2 = bufferedReader2;
                                                bufferedReader = bufferedReader;
                                                try {
                                                    e.printStackTrace();
                                                    if (dataOutputStream2 != null) {
                                                        try {
                                                            dataOutputStream2.close();
                                                        } catch (IOException e3) {
                                                            e3.printStackTrace();
                                                        }
                                                    }
                                                    if (bufferedReader2 != 0) {
                                                        try {
                                                            bufferedReader2.close();
                                                        } catch (IOException e4) {
                                                            e4.printStackTrace();
                                                        }
                                                    }
                                                    if (bufferedReader != 0) {
                                                        try {
                                                            bufferedReader.close();
                                                        } catch (IOException e5) {
                                                            e5.printStackTrace();
                                                        }
                                                    }
                                                    if (processExec != null) {
                                                        processExec.destroy();
                                                    }
                                                    sb3 = sb;
                                                    sb4 = sb2;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    dataOutputStream = dataOutputStream2;
                                                    r3 = bufferedReader2;
                                                    r6 = bufferedReader;
                                                    if (dataOutputStream != null) {
                                                        try {
                                                            dataOutputStream.close();
                                                        } catch (IOException e6) {
                                                            e6.printStackTrace();
                                                        }
                                                    }
                                                    if (r3 != 0) {
                                                        try {
                                                            r3.close();
                                                        } catch (IOException e7) {
                                                            e7.printStackTrace();
                                                        }
                                                    }
                                                    if (r6 != 0) {
                                                        try {
                                                            r6.close();
                                                        } catch (IOException e8) {
                                                            e8.printStackTrace();
                                                        }
                                                    }
                                                    if (processExec != null) {
                                                        throw th;
                                                    }
                                                    processExec.destroy();
                                                    throw th;
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                r3 = bufferedReader2;
                                                r6 = bufferedReader;
                                                if (dataOutputStream != null) {
                                                    dataOutputStream.close();
                                                }
                                                if (r3 != 0) {
                                                    r3.close();
                                                }
                                                if (r6 != 0) {
                                                    r6.close();
                                                }
                                                if (processExec != null) {
                                                    throw th;
                                                }
                                                processExec.destroy();
                                                throw th;
                                            }
                                        } catch (Exception e9) {
                                            e = e9;
                                            bufferedReader = 0;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            bufferedReader = 0;
                                        }
                                    } catch (Exception e10) {
                                        e = e10;
                                        bufferedReader2 = 0;
                                        bufferedReader = 0;
                                    }
                                } catch (Exception e11) {
                                    sb2 = null;
                                    bufferedReader2 = 0;
                                    bufferedReader = 0;
                                    dataOutputStream2 = dataOutputStream;
                                    sb = sb3;
                                    e = e11;
                                }
                            } else {
                                sb3 = null;
                                sb4 = null;
                                r7 = 0;
                            }
                            try {
                                dataOutputStream.close();
                            } catch (IOException e12) {
                                e12.printStackTrace();
                            }
                            if (r4 != 0) {
                                try {
                                    r4.close();
                                } catch (IOException e13) {
                                    e13.printStackTrace();
                                }
                            }
                            if (r7 != 0) {
                                try {
                                    r7.close();
                                } catch (IOException e14) {
                                    e14.printStackTrace();
                                }
                            }
                            if (processExec != null) {
                                processExec.destroy();
                            }
                        } catch (Exception e15) {
                            e = e15;
                            sb = null;
                            sb2 = null;
                            bufferedReader2 = 0;
                            bufferedReader = 0;
                            dataOutputStream2 = dataOutputStream;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        r6 = 0;
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (r3 != 0) {
                            r3.close();
                        }
                        if (r6 != 0) {
                            r6.close();
                        }
                        if (processExec != null) {
                            throw th;
                        }
                        processExec.destroy();
                        throw th;
                    }
                } catch (Exception e16) {
                    e = e16;
                    sb = null;
                    sb2 = sb;
                    StringBuilder sb5 = sb2;
                    bufferedReader = sb5;
                    bufferedReader2 = sb5;
                    e.printStackTrace();
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    if (bufferedReader2 != 0) {
                        bufferedReader2.close();
                    }
                    if (bufferedReader != 0) {
                        bufferedReader.close();
                    }
                    if (processExec != null) {
                        processExec.destroy();
                    }
                    sb3 = sb;
                    sb4 = sb2;
                    if (sb3 == null) {
                        string = "";
                    } else {
                        string = sb3.toString();
                    }
                    return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : "");
                } catch (Throwable th5) {
                    th = th5;
                    dataOutputStream = null;
                    r6 = dataOutputStream;
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (r3 != 0) {
                        r3.close();
                    }
                    if (r6 != 0) {
                        r6.close();
                    }
                    if (processExec != null) {
                        throw th;
                    }
                    processExec.destroy();
                    throw th;
                }
            } catch (Exception e17) {
                e = e17;
                processExec = null;
                sb = null;
            } catch (Throwable th6) {
                th = th6;
                processExec = null;
                dataOutputStream = null;
            }
            if (sb3 == null) {
                string = "";
            } else {
                string = sb3.toString();
            }
            return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : "");
        }
        return new CommandResult(-1, "", "");
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String[] strArr, final boolean z, final boolean z2, Utils.Callback<CommandResult> callback) {
        if (callback != null) {
            return Utils.doAsync(new Utils.Task<CommandResult>(callback) { // from class: com.blankj.utilcode.util.ShellUtils.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.blankj.utilcode.util.Utils.Task
                public CommandResult doInBackground() {
                    return ShellUtils.execCmd(strArr, z, z2);
                }
            });
        }
        throw new NullPointerException("Argument 'callback' of type Utils.Callback<CommandResult> (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
}
