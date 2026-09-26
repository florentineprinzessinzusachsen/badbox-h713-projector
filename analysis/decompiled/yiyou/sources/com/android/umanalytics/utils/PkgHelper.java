package com.android.umanalytics.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.baidu.mobstat.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class PkgHelper {
    private static final String TAG = "PkgHelper";

    public static void install(final String str) {
        new Thread() { // from class: com.android.umanalytics.utils.PkgHelper.1
            /* JADX WARN: Code duplicated, block: B:46:0x00a4 A[Catch: IOException -> 0x00a0, TRY_LEAVE, TryCatch #7 {IOException -> 0x00a0, blocks: (B:42:0x009c, B:46:0x00a4), top: B:79:0x009c }] */
            /* JADX WARN: Code duplicated, block: B:50:0x00ad A[PHI: r2
              0x00ad: PHI (r2v10 java.lang.Process) = (r2v9 java.lang.Process), (r2v18 java.lang.Process) binds: [B:49:0x00ab, B:21:0x0079] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:66:0x0102 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:67:0x0104 A[Catch: IOException -> 0x0100, TRY_LEAVE, TryCatch #8 {IOException -> 0x0100, blocks: (B:63:0x00fc, B:67:0x0104), top: B:81:0x00fc }] */
            /* JADX WARN: Code duplicated, block: B:71:0x010d  */
            /* JADX WARN: Code duplicated, block: B:81:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:93:? A[SYNTHETIC] */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                Process processStart;
                BufferedReader bufferedReader;
                BufferedReader bufferedReader2;
                new File(str);
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    Log.e(PkgHelper.TAG, "文件不存在");
                    return;
                }
                ProcessBuilder processBuilder = new ProcessBuilder("pm", Config.INPUT_INSTALLED_PKG, "-r", str);
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                BufferedReader bufferedReader3 = null;
                try {
                    processStart = processBuilder.start();
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));
                        try {
                            bufferedReader2 = new BufferedReader(new InputStreamReader(processStart.getErrorStream()));
                            while (true) {
                                try {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    } else {
                                        sb.append(line);
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    bufferedReader3 = bufferedReader;
                                    try {
                                        e.printStackTrace();
                                        if (bufferedReader3 != null) {
                                            try {
                                                bufferedReader3.close();
                                                if (bufferedReader2 != null) {
                                                    bufferedReader2.close();
                                                }
                                            } catch (IOException e3) {
                                                e3.printStackTrace();
                                                if (processStart != null) {
                                                    processStart.destroy();
                                                }
                                                if (sb.toString().contains("Success")) {
                                                }
                                                Log.i(PkgHelper.TAG, "安装成功: " + str);
                                                return;
                                            }
                                        } else if (bufferedReader2 != null) {
                                            bufferedReader2.close();
                                        }
                                        if (processStart != null) {
                                            processStart.destroy();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        bufferedReader = bufferedReader3;
                                        bufferedReader3 = bufferedReader2;
                                        if (bufferedReader != null) {
                                            try {
                                                bufferedReader.close();
                                                if (bufferedReader3 != null) {
                                                    bufferedReader3.close();
                                                }
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                                if (processStart != null) {
                                                    throw th;
                                                }
                                                processStart.destroy();
                                                throw th;
                                            }
                                        } else if (bufferedReader3 != null) {
                                            bufferedReader3.close();
                                        }
                                        if (processStart != null) {
                                            throw th;
                                        }
                                        processStart.destroy();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    bufferedReader3 = bufferedReader2;
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                        if (bufferedReader3 != null) {
                                            bufferedReader3.close();
                                        }
                                    } else if (bufferedReader3 != null) {
                                        bufferedReader3.close();
                                    }
                                    if (processStart != null) {
                                        throw th;
                                    }
                                    processStart.destroy();
                                    throw th;
                                }
                            }
                            while (true) {
                                String line2 = bufferedReader2.readLine();
                                if (line2 != null) {
                                    sb2.append(line2);
                                } else {
                                    try {
                                        break;
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                            }
                            bufferedReader.close();
                            bufferedReader2.close();
                            if (processStart != null) {
                                processStart.destroy();
                            }
                        } catch (IOException e6) {
                            e = e6;
                            bufferedReader2 = null;
                        } catch (Throwable th3) {
                            th = th3;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                if (bufferedReader3 != null) {
                                    bufferedReader3.close();
                                }
                            } else if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            if (processStart != null) {
                                throw th;
                            }
                            processStart.destroy();
                            throw th;
                        }
                    } catch (IOException e7) {
                        e = e7;
                        bufferedReader2 = null;
                    } catch (Throwable th4) {
                        th = th4;
                        bufferedReader = null;
                    }
                } catch (IOException e8) {
                    e = e8;
                    processStart = null;
                    bufferedReader2 = null;
                } catch (Throwable th5) {
                    th = th5;
                    processStart = null;
                    bufferedReader = null;
                }
                if (!sb.toString().contains("Success") || sb.toString().contains("success")) {
                    Log.i(PkgHelper.TAG, "安装成功: " + str);
                    return;
                }
                Log.e(PkgHelper.TAG, "安装失败：" + str);
            }
        }.start();
    }

    public static void uninstall(Context context, String str) {
        Method method;
        try {
            PackageManager packageManager = context.getPackageManager();
            Method[] declaredMethods = packageManager != null ? packageManager.getClass().getDeclaredMethods() : null;
            if (declaredMethods != null && declaredMethods.length > 0) {
                int length = declaredMethods.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        method = null;
                        break;
                    }
                    method = declaredMethods[i];
                    if (method.getName().toString().equals("deletePackage")) {
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                method = null;
                break;
            }
            if (method != null) {
                method.setAccessible(true);
                method.invoke(packageManager, str, null, 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
