package com.umeng.commonsdk.internal.utils;

import android.os.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* JADX INFO: compiled from: ProcessUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3852a = "\n";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f3853b = "\nexit\n".getBytes();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static byte[] f3854c = new byte[32];

    /* JADX WARN: Code duplicated, block: B:22:0x0063 A[PHI: r7 r9
      0x0063: PHI (r7v5 java.lang.Object) = (r7v13 java.lang.Object), (r7v14 java.lang.Object), (r7v15 java.lang.Object) binds: [B:62:0x00cc, B:71:0x00d9, B:21:0x0061] A[DONT_GENERATE, DONT_INLINE]
      0x0063: PHI (r9v19 java.lang.Process) = (r9v16 java.lang.Process), (r9v17 java.lang.Process), (r9v21 java.lang.Process) binds: [B:62:0x00cc, B:71:0x00d9, B:21:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:74:0x00de A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:75:0x00df  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.StringBuilder] */
    public static String a(String... strArr) throws Throwable {
        OutputStream outputStream;
        InputStream inputStream;
        InputStream errorStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Throwable th;
        Process processStart;
        OutputStream outputStream2;
        OutputStream outputStream3;
        OutputStream outputStream4;
        OutputStream outputStream5;
        Object obj;
        OutputStream outputStream6;
        Object obj2;
        OutputStream outputStream7;
        Object obj3;
        Object obj4;
        OutputStream outputStream8;
        Object obj5;
        Object obj6;
        OutputStream outputStream9;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        Object obj14;
        ?? r5;
        ?? r4;
        ?? r3;
        ?? r2;
        ?? r6;
        ?? r7;
        ?? r8;
        ?? r9;
        ?? r10;
        Object obj15;
        StringBuilder sb;
        try {
            processStart = new ProcessBuilder(new String[0]).command(strArr).start();
            try {
                outputStream = processStart.getOutputStream();
                try {
                    inputStream = processStart.getInputStream();
                    try {
                        errorStream = processStart.getErrorStream();
                        try {
                            outputStream.write(f3853b);
                            outputStream.flush();
                            processStart.waitFor();
                            inputStreamReader = new InputStreamReader(inputStream);
                            try {
                                bufferedReader = new BufferedReader(inputStreamReader);
                                try {
                                    try {
                                        String line = bufferedReader.readLine();
                                        if (line != null) {
                                            sb = new StringBuilder();
                                            try {
                                                sb.append(line);
                                                sb.append(f3852a);
                                                while (true) {
                                                    String line2 = bufferedReader.readLine();
                                                    if (line2 == null) {
                                                        break;
                                                    }
                                                    sb.append(line2);
                                                    sb.append(f3852a);
                                                }
                                            } catch (IOException unused) {
                                                r9 = inputStream;
                                                r8 = errorStream;
                                                r7 = inputStreamReader;
                                                r6 = bufferedReader;
                                                obj14 = sb;
                                                a(outputStream, r8, r9, r7, r6);
                                                obj15 = obj14;
                                                r10 = obj14;
                                                if (processStart != null) {
                                                }
                                                if (r10 == 0) {
                                                    return null;
                                                }
                                                return r10.toString();
                                            } catch (Exception unused2) {
                                                r2 = inputStream;
                                                r3 = errorStream;
                                                r4 = inputStreamReader;
                                                r5 = bufferedReader;
                                                obj10 = sb;
                                                a(outputStream, r3, r2, r4, r5);
                                                obj15 = obj10;
                                                r10 = obj10;
                                                if (processStart != null) {
                                                }
                                                if (r10 == 0) {
                                                    return null;
                                                }
                                                return r10.toString();
                                            }
                                        } else {
                                            sb = null;
                                        }
                                        do {
                                        } while (errorStream.read(f3854c) > 0);
                                        a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader);
                                        obj15 = sb;
                                        r10 = sb;
                                        if (processStart != null) {
                                            c(processStart);
                                            r10 = obj15;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        a(outputStream, errorStream, inputStream, inputStreamReader, bufferedReader);
                                        if (processStart != null) {
                                            c(processStart);
                                        }
                                        throw th;
                                    }
                                } catch (IOException unused3) {
                                    obj14 = null;
                                    r9 = inputStream;
                                    r8 = errorStream;
                                    r7 = inputStreamReader;
                                    r6 = bufferedReader;
                                } catch (Exception unused4) {
                                    obj10 = null;
                                    r2 = inputStream;
                                    r3 = errorStream;
                                    r4 = inputStreamReader;
                                    r5 = bufferedReader;
                                }
                            } catch (IOException unused5) {
                                outputStream9 = null;
                                obj13 = inputStream;
                                obj12 = errorStream;
                                obj11 = inputStreamReader;
                                obj14 = outputStream9;
                                r9 = obj13;
                                r8 = obj12;
                                r7 = obj11;
                                r6 = outputStream9;
                                a(outputStream, r8, r9, r7, r6);
                                obj15 = obj14;
                                r10 = obj14;
                                if (processStart != null) {
                                    c(processStart);
                                    r10 = obj15;
                                }
                                if (r10 == 0) {
                                    return null;
                                }
                                return r10.toString();
                            } catch (Exception unused6) {
                                outputStream8 = null;
                                obj9 = inputStream;
                                obj8 = errorStream;
                                obj7 = inputStreamReader;
                                obj10 = outputStream8;
                                r2 = obj9;
                                r3 = obj8;
                                r4 = obj7;
                                r5 = outputStream8;
                                a(outputStream, r3, r2, r4, r5);
                                obj15 = obj10;
                                r10 = obj10;
                                if (processStart != null) {
                                    c(processStart);
                                    r10 = obj15;
                                }
                                if (r10 == 0) {
                                    return null;
                                }
                                return r10.toString();
                            } catch (Throwable th3) {
                                bufferedReader = null;
                                th = th3;
                            }
                        } catch (IOException unused7) {
                            outputStream7 = null;
                            obj6 = inputStream;
                            obj5 = errorStream;
                            outputStream9 = outputStream7;
                            obj13 = obj6;
                            obj12 = obj5;
                            obj11 = outputStream7;
                            obj14 = outputStream9;
                            r9 = obj13;
                            r8 = obj12;
                            r7 = obj11;
                            r6 = outputStream9;
                            a(outputStream, r8, r9, r7, r6);
                            obj15 = obj14;
                            r10 = obj14;
                            if (processStart != null) {
                                c(processStart);
                                r10 = obj15;
                            }
                            if (r10 == 0) {
                                return null;
                            }
                            return r10.toString();
                        } catch (Exception unused8) {
                            outputStream6 = null;
                            obj4 = inputStream;
                            obj3 = errorStream;
                            outputStream8 = outputStream6;
                            obj9 = obj4;
                            obj8 = obj3;
                            obj7 = outputStream6;
                            obj10 = outputStream8;
                            r2 = obj9;
                            r3 = obj8;
                            r4 = obj7;
                            r5 = outputStream8;
                            a(outputStream, r3, r2, r4, r5);
                            obj15 = obj10;
                            r10 = obj10;
                            if (processStart != null) {
                                c(processStart);
                                r10 = obj15;
                            }
                            if (r10 == 0) {
                                return null;
                            }
                            return r10.toString();
                        } catch (Throwable th4) {
                            bufferedReader = null;
                            th = th4;
                            inputStreamReader = null;
                        }
                    } catch (IOException unused9) {
                        outputStream5 = null;
                        obj2 = inputStream;
                        outputStream7 = outputStream5;
                        obj6 = obj2;
                        obj5 = outputStream5;
                        outputStream9 = outputStream7;
                        obj13 = obj6;
                        obj12 = obj5;
                        obj11 = outputStream7;
                        obj14 = outputStream9;
                        r9 = obj13;
                        r8 = obj12;
                        r7 = obj11;
                        r6 = outputStream9;
                        a(outputStream, r8, r9, r7, r6);
                        obj15 = obj14;
                        r10 = obj14;
                        if (processStart != null) {
                            c(processStart);
                            r10 = obj15;
                        }
                        if (r10 == 0) {
                            return null;
                        }
                        return r10.toString();
                    } catch (Exception unused10) {
                        outputStream4 = null;
                        obj = inputStream;
                        outputStream6 = outputStream4;
                        obj4 = obj;
                        obj3 = outputStream4;
                        outputStream8 = outputStream6;
                        obj9 = obj4;
                        obj8 = obj3;
                        obj7 = outputStream6;
                        obj10 = outputStream8;
                        r2 = obj9;
                        r3 = obj8;
                        r4 = obj7;
                        r5 = outputStream8;
                        a(outputStream, r3, r2, r4, r5);
                        obj15 = obj10;
                        r10 = obj10;
                        if (processStart != null) {
                            c(processStart);
                            r10 = obj15;
                        }
                        if (r10 == 0) {
                            return null;
                        }
                        return r10.toString();
                    } catch (Throwable th5) {
                        inputStreamReader = null;
                        bufferedReader = null;
                        th = th5;
                        errorStream = null;
                    }
                } catch (IOException unused11) {
                    outputStream3 = null;
                    outputStream5 = outputStream3;
                    obj2 = outputStream3;
                    outputStream7 = outputStream5;
                    obj6 = obj2;
                    obj5 = outputStream5;
                    outputStream9 = outputStream7;
                    obj13 = obj6;
                    obj12 = obj5;
                    obj11 = outputStream7;
                    obj14 = outputStream9;
                    r9 = obj13;
                    r8 = obj12;
                    r7 = obj11;
                    r6 = outputStream9;
                    a(outputStream, r8, r9, r7, r6);
                    obj15 = obj14;
                    r10 = obj14;
                    if (processStart != null) {
                        c(processStart);
                        r10 = obj15;
                    }
                    if (r10 == 0) {
                        return null;
                    }
                    return r10.toString();
                } catch (Exception unused12) {
                    outputStream2 = null;
                    outputStream4 = outputStream2;
                    obj = outputStream2;
                    outputStream6 = outputStream4;
                    obj4 = obj;
                    obj3 = outputStream4;
                    outputStream8 = outputStream6;
                    obj9 = obj4;
                    obj8 = obj3;
                    obj7 = outputStream6;
                    obj10 = outputStream8;
                    r2 = obj9;
                    r3 = obj8;
                    r4 = obj7;
                    r5 = outputStream8;
                    a(outputStream, r3, r2, r4, r5);
                    obj15 = obj10;
                    r10 = obj10;
                    if (processStart != null) {
                        c(processStart);
                        r10 = obj15;
                    }
                    if (r10 == 0) {
                        return null;
                    }
                    return r10.toString();
                } catch (Throwable th6) {
                    errorStream = null;
                    inputStreamReader = null;
                    bufferedReader = null;
                    th = th6;
                    inputStream = null;
                }
            } catch (IOException unused13) {
                outputStream = null;
                outputStream3 = outputStream;
                outputStream5 = outputStream3;
                obj2 = outputStream3;
                outputStream7 = outputStream5;
                obj6 = obj2;
                obj5 = outputStream5;
                outputStream9 = outputStream7;
                obj13 = obj6;
                obj12 = obj5;
                obj11 = outputStream7;
                obj14 = outputStream9;
                r9 = obj13;
                r8 = obj12;
                r7 = obj11;
                r6 = outputStream9;
                a(outputStream, r8, r9, r7, r6);
                obj15 = obj14;
                r10 = obj14;
                if (processStart != null) {
                    c(processStart);
                    r10 = obj15;
                }
                if (r10 == 0) {
                    return null;
                }
                return r10.toString();
            } catch (Exception unused14) {
                outputStream = null;
                outputStream2 = outputStream;
                outputStream4 = outputStream2;
                obj = outputStream2;
                outputStream6 = outputStream4;
                obj4 = obj;
                obj3 = outputStream4;
                outputStream8 = outputStream6;
                obj9 = obj4;
                obj8 = obj3;
                obj7 = outputStream6;
                obj10 = outputStream8;
                r2 = obj9;
                r3 = obj8;
                r4 = obj7;
                r5 = outputStream8;
                a(outputStream, r3, r2, r4, r5);
                obj15 = obj10;
                r10 = obj10;
                if (processStart != null) {
                    c(processStart);
                    r10 = obj15;
                }
                if (r10 == 0) {
                    return null;
                }
                return r10.toString();
            } catch (Throwable th7) {
                inputStream = null;
                errorStream = null;
                inputStreamReader = null;
                bufferedReader = null;
                th = th7;
                outputStream = null;
            }
        } catch (IOException unused15) {
            processStart = null;
            outputStream = null;
        } catch (Exception unused16) {
            processStart = null;
            outputStream = null;
        } catch (Throwable th8) {
            outputStream = null;
            inputStream = null;
            errorStream = null;
            inputStreamReader = null;
            bufferedReader = null;
            th = th8;
            processStart = null;
        }
        if (r10 == 0) {
            return null;
        }
        return r10.toString();
    }

    private static int b(Process process) {
        String string = process.toString();
        try {
            return Integer.parseInt(string.substring(string.indexOf("=") + 1, string.indexOf("]")));
        } catch (Exception unused) {
            return 0;
        }
    }

    private static void c(Process process) {
        if (process != null) {
            try {
                if (process.exitValue() != 0) {
                    a(process);
                }
            } catch (IllegalThreadStateException unused) {
                a(process);
            }
        }
    }

    private static void a(OutputStream outputStream, InputStream inputStream, InputStream inputStream2, InputStreamReader inputStreamReader, BufferedReader bufferedReader) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused2) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException unused3) {
            }
        }
        if (inputStreamReader != null) {
            try {
                inputStreamReader.close();
            } catch (IOException unused4) {
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused5) {
            }
        }
    }

    private static void a(Process process) {
        int iB = b(process);
        if (iB != 0) {
            try {
                try {
                    Process.killProcess(iB);
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                process.destroy();
            }
        }
    }
}
