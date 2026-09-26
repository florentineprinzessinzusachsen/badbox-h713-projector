package com.android.umanalytics.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String str, boolean z) {
        return execCommand(new String[]{str}, z, true);
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i) {
            this.result = i;
        }

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }
    }

    public static CommandResult execCommand(List<String> list, boolean z) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static CommandResult execCommand(String[] strArr, boolean z) {
        return execCommand(strArr, z, true);
    }

    public static CommandResult execCommand(String str, boolean z, boolean z2) {
        return execCommand(new String[]{str}, z, z2);
    }

    public static CommandResult execCommand(List<String> list, boolean z, boolean z2) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x0138 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:105:0x013a A[Catch: IOException -> 0x0136, TryCatch #2 {IOException -> 0x0136, blocks: (B:101:0x0132, B:105:0x013a, B:107:0x013f), top: B:140:0x0132 }] */
    /* JADX WARN: Code duplicated, block: B:107:0x013f A[Catch: IOException -> 0x0136, TRY_LEAVE, TryCatch #2 {IOException -> 0x0136, blocks: (B:101:0x0132, B:105:0x013a, B:107:0x013f), top: B:140:0x0132 }] */
    /* JADX WARN: Code duplicated, block: B:114:0x014d  */
    /* JADX WARN: Code duplicated, block: B:115:0x014f  */
    /* JADX WARN: Code duplicated, block: B:118:0x0156  */
    /* JADX WARN: Code duplicated, block: B:127:0x0168 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:128:0x016a A[Catch: IOException -> 0x0166, TryCatch #15 {IOException -> 0x0166, blocks: (B:124:0x0162, B:128:0x016a, B:130:0x016f), top: B:146:0x0162 }] */
    /* JADX WARN: Code duplicated, block: B:130:0x016f A[Catch: IOException -> 0x0166, TRY_LEAVE, TryCatch #15 {IOException -> 0x0166, blocks: (B:124:0x0162, B:128:0x016a, B:130:0x016f), top: B:146:0x0162 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x0178  */
    /* JADX WARN: Code duplicated, block: B:140:0x0132 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:144:0x010c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:146:0x0162 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x00cf A[Catch: IOException -> 0x00d8, TryCatch #1 {IOException -> 0x00d8, blocks: (B:52:0x00ca, B:54:0x00cf, B:56:0x00d4), top: B:138:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:56:0x00d4 A[Catch: IOException -> 0x00d8, TRY_LEAVE, TryCatch #1 {IOException -> 0x00d8, blocks: (B:52:0x00ca, B:54:0x00cf, B:56:0x00d4), top: B:138:0x00ca }] */
    /* JADX WARN: Code duplicated, block: B:61:0x00de  */
    /* JADX WARN: Code duplicated, block: B:87:0x0112 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x0114 A[Catch: IOException -> 0x0110, TryCatch #6 {IOException -> 0x0110, blocks: (B:84:0x010c, B:88:0x0114, B:90:0x0119), top: B:144:0x010c }] */
    /* JADX WARN: Code duplicated, block: B:90:0x0119 A[Catch: IOException -> 0x0110, TRY_LEAVE, TryCatch #6 {IOException -> 0x0110, blocks: (B:84:0x010c, B:88:0x0114, B:90:0x0119), top: B:144:0x010c }] */
    /* JADX WARN: Code duplicated, block: B:94:0x0122 A[PHI: r1 r5 r9 r10
      0x0122: PHI (r1v4 int) = (r1v3 int), (r1v5 int) binds: [B:93:0x0120, B:110:0x0146] A[DONT_GENERATE, DONT_INLINE]
      0x0122: PHI (r5v3 java.lang.StringBuilder) = (r5v2 java.lang.StringBuilder), (r5v4 java.lang.StringBuilder) binds: [B:93:0x0120, B:110:0x0146] A[DONT_GENERATE, DONT_INLINE]
      0x0122: PHI (r9v12 ??) = (r9v11 ??), (r9v13 ??) binds: [B:93:0x0120, B:110:0x0146] A[DONT_GENERATE, DONT_INLINE]
      0x0122: PHI (r10v9 java.lang.StringBuilder) = (r10v8 java.lang.StringBuilder), (r10v10 java.lang.StringBuilder) binds: [B:93:0x0120, B:110:0x0146] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.DataOutputStream] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r9v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v33 */
    /* JADX WARN: Type inference failed for: r9v34 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v36 */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v39 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static CommandResult execCommand(String[] strArr, boolean z, boolean z2) throws Throwable {
        ?? r9;
        BufferedReader bufferedReader;
        ?? r2;
        ?? r10;
        BufferedReader bufferedReader2;
        ?? r11;
        StringBuilder sb;
        ?? r12;
        StringBuilder sb2;
        StringBuilder sb3;
        ?? r13;
        ?? r3;
        StringBuilder sb4;
        ?? r14;
        ?? r4;
        ?? r15;
        ?? r5;
        ?? r6;
        ?? r7;
        ?? r8;
        ?? r16;
        ?? r17;
        String string;
        StringBuilder sb5;
        StringBuilder sb6;
        BufferedReader bufferedReader3;
        BufferedReader bufferedReader4;
        BufferedReader bufferedReader5 = null;
        bufferedReader5 = null;
        int iWaitFor = -1;
        if (strArr != null) {
            ?? length = strArr.length;
            try {
                if (length != 0) {
                    try {
                        z = Runtime.getRuntime().exec(z != 0 ? COMMAND_SU : COMMAND_SH);
                        try {
                            length = new DataOutputStream(z.getOutputStream());
                            try {
                                try {
                                    for (String str : strArr) {
                                        if (str != null) {
                                            length.write(str.getBytes());
                                            length.writeBytes(COMMAND_LINE_END);
                                            length.flush();
                                        }
                                    }
                                    length.writeBytes(COMMAND_EXIT);
                                    length.flush();
                                    iWaitFor = z.waitFor();
                                    try {
                                        if (z2) {
                                            sb5 = new StringBuilder();
                                            try {
                                                sb6 = new StringBuilder();
                                                try {
                                                    BufferedReader bufferedReader6 = new BufferedReader(new InputStreamReader(z.getInputStream()));
                                                    try {
                                                        BufferedReader bufferedReader7 = new BufferedReader(new InputStreamReader(z.getErrorStream()));
                                                        while (true) {
                                                            try {
                                                                String line = bufferedReader6.readLine();
                                                                if (line == null) {
                                                                    break;
                                                                }
                                                                sb5.append(line);
                                                            } catch (IOException e2) {
                                                                sb = sb5;
                                                                e = e2;
                                                                sb4 = sb6;
                                                                r17 = length;
                                                                r16 = bufferedReader6;
                                                                r8 = bufferedReader7;
                                                                r15 = z;
                                                                e.printStackTrace();
                                                                if (r17 != 0) {
                                                                    try {
                                                                        r17.close();
                                                                        if (r16 != 0) {
                                                                            r16.close();
                                                                        }
                                                                        if (r8 != 0) {
                                                                            r8.close();
                                                                        }
                                                                    } catch (IOException e3) {
                                                                        e3.printStackTrace();
                                                                        if (r15 != 0) {
                                                                            r15.destroy();
                                                                        }
                                                                        if (sb == null) {
                                                                            string = null;
                                                                        } else {
                                                                            string = sb.toString();
                                                                        }
                                                                        return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                                                                    }
                                                                } else {
                                                                    if (r16 != 0) {
                                                                        r16.close();
                                                                    }
                                                                    if (r8 != 0) {
                                                                        r8.close();
                                                                    }
                                                                }
                                                                if (r15 != 0) {
                                                                    r15.destroy();
                                                                }
                                                            } catch (Exception e4) {
                                                                sb = sb5;
                                                                e = e4;
                                                                sb4 = sb6;
                                                                r7 = length;
                                                                r6 = bufferedReader6;
                                                                r5 = bufferedReader7;
                                                                r15 = z;
                                                                e.printStackTrace();
                                                                if (r7 != 0) {
                                                                    try {
                                                                        r7.close();
                                                                        if (r6 != 0) {
                                                                            r6.close();
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r5.close();
                                                                        }
                                                                    } catch (IOException e5) {
                                                                        e5.printStackTrace();
                                                                        if (r15 != 0) {
                                                                            r15.destroy();
                                                                        }
                                                                        if (sb == null) {
                                                                            string = null;
                                                                        } else {
                                                                            string = sb.toString();
                                                                        }
                                                                        return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                                                                    }
                                                                } else {
                                                                    if (r6 != 0) {
                                                                        r6.close();
                                                                    }
                                                                    if (r5 != 0) {
                                                                        r5.close();
                                                                    }
                                                                }
                                                                if (r15 != 0) {
                                                                    r15.destroy();
                                                                }
                                                            }
                                                        }
                                                        while (true) {
                                                            String line2 = bufferedReader7.readLine();
                                                            bufferedReader3 = bufferedReader6;
                                                            bufferedReader4 = bufferedReader7;
                                                            if (line2 == null) {
                                                                break;
                                                            }
                                                            sb6.append(line2);
                                                        }
                                                        length.close();
                                                        if (bufferedReader3 != null) {
                                                            bufferedReader3.close();
                                                        }
                                                        if (bufferedReader4 != null) {
                                                            bufferedReader4.close();
                                                        }
                                                        if (z != 0) {
                                                            z.destroy();
                                                        }
                                                        sb4 = sb6;
                                                        sb = sb5;
                                                    } catch (IOException e6) {
                                                        sb4 = sb6;
                                                        sb = sb5;
                                                        e = e6;
                                                        r8 = 0;
                                                        r17 = length;
                                                        r16 = bufferedReader6;
                                                        r15 = z;
                                                    } catch (Exception e7) {
                                                        sb4 = sb6;
                                                        sb = sb5;
                                                        e = e7;
                                                        r5 = 0;
                                                        r7 = length;
                                                        r6 = bufferedReader6;
                                                        r15 = z;
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        bufferedReader = null;
                                                        bufferedReader5 = bufferedReader6;
                                                        r2 = length;
                                                        r9 = z;
                                                        if (r2 != 0) {
                                                            try {
                                                                r2.close();
                                                                if (bufferedReader5 != null) {
                                                                    bufferedReader5.close();
                                                                }
                                                                if (bufferedReader != null) {
                                                                    bufferedReader.close();
                                                                }
                                                            } catch (IOException e8) {
                                                                e8.printStackTrace();
                                                                if (r9 != 0) {
                                                                    r9.destroy();
                                                                    throw th;
                                                                }
                                                                throw th;
                                                            }
                                                        } else {
                                                            if (bufferedReader5 != null) {
                                                                bufferedReader5.close();
                                                            }
                                                            if (bufferedReader != null) {
                                                                bufferedReader.close();
                                                            }
                                                        }
                                                        if (r9 != 0) {
                                                            r9.destroy();
                                                            throw th;
                                                        }
                                                        throw th;
                                                    }
                                                } catch (IOException e9) {
                                                    sb4 = sb6;
                                                    r8 = 0;
                                                    sb = sb5;
                                                    e = e9;
                                                    r16 = 0;
                                                    r17 = length;
                                                    r15 = z;
                                                } catch (Exception e10) {
                                                    sb4 = sb6;
                                                    r5 = 0;
                                                    sb = sb5;
                                                    e = e10;
                                                    r6 = 0;
                                                    r7 = length;
                                                    r15 = z;
                                                }
                                            } catch (IOException e11) {
                                                r16 = 0;
                                                r8 = 0;
                                                sb4 = null;
                                                sb = sb5;
                                                e = e11;
                                                r17 = length;
                                                r15 = z;
                                            } catch (Exception e12) {
                                                r6 = 0;
                                                r5 = 0;
                                                sb4 = null;
                                                sb = sb5;
                                                e = e12;
                                                r7 = length;
                                                r15 = z;
                                            }
                                            if (sb == null) {
                                                string = null;
                                            } else {
                                                string = sb.toString();
                                            }
                                            return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                                        }
                                        sb5 = null;
                                        sb6 = null;
                                        bufferedReader3 = null;
                                        bufferedReader4 = null;
                                        length.close();
                                        if (bufferedReader3 != null) {
                                            bufferedReader3.close();
                                        }
                                        if (bufferedReader4 != null) {
                                            bufferedReader4.close();
                                        }
                                    } catch (IOException e13) {
                                        e13.printStackTrace();
                                    }
                                    if (z != 0) {
                                        z.destroy();
                                    }
                                    sb4 = sb6;
                                    sb = sb5;
                                } catch (Throwable th2) {
                                    th = th2;
                                    bufferedReader = null;
                                    r2 = length;
                                    r9 = z;
                                    if (r2 != 0) {
                                        r2.close();
                                        if (bufferedReader5 != null) {
                                            bufferedReader5.close();
                                        }
                                        if (bufferedReader != null) {
                                            bufferedReader.close();
                                        }
                                    } else {
                                        if (bufferedReader5 != null) {
                                            bufferedReader5.close();
                                        }
                                        if (bufferedReader != null) {
                                            bufferedReader.close();
                                        }
                                    }
                                    if (r9 != 0) {
                                        r9.destroy();
                                        throw th;
                                    }
                                    throw th;
                                }
                            } catch (IOException e14) {
                                e = e14;
                                sb = null;
                                sb3 = null;
                                r4 = length;
                                r14 = z;
                                StringBuilder sb7 = sb3;
                                sb4 = sb7;
                                r17 = r4;
                                r16 = sb3;
                                r8 = sb7;
                                r15 = r14;
                                e.printStackTrace();
                                if (r17 != 0) {
                                    r17.close();
                                    if (r16 != 0) {
                                        r16.close();
                                    }
                                    if (r8 != 0) {
                                        r8.close();
                                    }
                                } else {
                                    if (r16 != 0) {
                                        r16.close();
                                    }
                                    if (r8 != 0) {
                                        r8.close();
                                    }
                                }
                                if (r15 != 0) {
                                    r15.destroy();
                                }
                                if (sb == null) {
                                    string = null;
                                } else {
                                    string = sb.toString();
                                }
                                return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                            } catch (Exception e15) {
                                e = e15;
                                sb = null;
                                sb2 = null;
                                r3 = length;
                                r13 = z;
                                StringBuilder sb8 = sb2;
                                sb4 = sb8;
                                r7 = r3;
                                r6 = sb2;
                                r5 = sb8;
                                r15 = r13;
                                e.printStackTrace();
                                if (r7 != 0) {
                                    r7.close();
                                    if (r6 != 0) {
                                        r6.close();
                                    }
                                    if (r5 != 0) {
                                        r5.close();
                                    }
                                } else {
                                    if (r6 != 0) {
                                        r6.close();
                                    }
                                    if (r5 != 0) {
                                        r5.close();
                                    }
                                }
                                if (r15 != 0) {
                                    r15.destroy();
                                }
                                if (sb == null) {
                                    string = null;
                                } else {
                                    string = sb.toString();
                                }
                                return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                            }
                        } catch (IOException e16) {
                            e = e16;
                            sb = null;
                            r12 = z;
                            StringBuilder sb9 = sb;
                            sb3 = sb9;
                            r4 = sb9;
                            r14 = r12;
                            StringBuilder sb10 = sb3;
                            sb4 = sb10;
                            r17 = r4;
                            r16 = sb3;
                            r8 = sb10;
                            r15 = r14;
                            e.printStackTrace();
                            if (r17 != 0) {
                                r17.close();
                                if (r16 != 0) {
                                    r16.close();
                                }
                                if (r8 != 0) {
                                    r8.close();
                                }
                            } else {
                                if (r16 != 0) {
                                    r16.close();
                                }
                                if (r8 != 0) {
                                    r8.close();
                                }
                            }
                            if (r15 != 0) {
                                r15.destroy();
                            }
                            if (sb == null) {
                                string = null;
                            } else {
                                string = sb.toString();
                            }
                            return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                        } catch (Exception e17) {
                            e = e17;
                            sb = null;
                            r11 = z;
                            StringBuilder sb11 = sb;
                            sb2 = sb11;
                            r3 = sb11;
                            r13 = r11;
                            StringBuilder sb12 = sb2;
                            sb4 = sb12;
                            r7 = r3;
                            r6 = sb2;
                            r5 = sb12;
                            r15 = r13;
                            e.printStackTrace();
                            if (r7 != 0) {
                                r7.close();
                                if (r6 != 0) {
                                    r6.close();
                                }
                                if (r5 != 0) {
                                    r5.close();
                                }
                            } else {
                                if (r6 != 0) {
                                    r6.close();
                                }
                                if (r5 != 0) {
                                    r5.close();
                                }
                            }
                            if (r15 != 0) {
                                r15.destroy();
                            }
                            if (sb == null) {
                                string = null;
                            } else {
                                string = sb.toString();
                            }
                            return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader2 = null;
                            r10 = z;
                            bufferedReader = bufferedReader2;
                            r2 = bufferedReader2;
                            r9 = r10;
                            if (r2 != 0) {
                                r2.close();
                                if (bufferedReader5 != null) {
                                    bufferedReader5.close();
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                            } else {
                                if (bufferedReader5 != null) {
                                    bufferedReader5.close();
                                }
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                            }
                            if (r9 != 0) {
                                r9.destroy();
                                throw th;
                            }
                            throw th;
                        }
                    } catch (IOException e18) {
                        e = e18;
                        r12 = 0;
                        sb = null;
                    } catch (Exception e19) {
                        e = e19;
                        r11 = 0;
                        sb = null;
                    } catch (Throwable th4) {
                        th = th4;
                        r10 = 0;
                        bufferedReader2 = null;
                    }
                    if (sb == null) {
                        string = null;
                    } else {
                        string = sb.toString();
                    }
                    return new CommandResult(iWaitFor, string, sb4 != null ? sb4.toString() : null);
                }
            } catch (Throwable th5) {
                th = th5;
            }
        }
        return new CommandResult(-1, null, null);
    }
}
