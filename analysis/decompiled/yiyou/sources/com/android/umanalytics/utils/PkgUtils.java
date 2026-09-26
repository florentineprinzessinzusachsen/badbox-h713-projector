package com.android.umanalytics.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import c.a.f0.b;
import c.a.l;
import c.a.n;
import c.a.o;
import c.a.s;
import c.a.x.b.a;
import com.android.umanalytics.App;
import com.android.umanalytics.BootReceiver;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.util.LogUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class PkgUtils {
    public static void install(final String str, boolean z) {
        l.create(new o<Integer>() { // from class: com.android.umanalytics.utils.PkgUtils.2
            /* JADX WARN: Code duplicated, block: B:46:0x00a0 A[Catch: IOException -> 0x009c, TRY_LEAVE, TryCatch #10 {IOException -> 0x009c, blocks: (B:42:0x0098, B:46:0x00a0), top: B:81:0x0098 }] */
            /* JADX WARN: Code duplicated, block: B:50:0x00a9 A[PHI: r3
              0x00a9: PHI (r3v8 java.lang.Process) = (r3v7 java.lang.Process), (r3v12 java.lang.Process) binds: [B:49:0x00a7, B:21:0x0077] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:66:0x00e0 A[DONT_INVERT] */
            /* JADX WARN: Code duplicated, block: B:67:0x00e2 A[Catch: IOException -> 0x00de, TRY_LEAVE, TryCatch #9 {IOException -> 0x00de, blocks: (B:63:0x00da, B:67:0x00e2), top: B:79:0x00da }] */
            /* JADX WARN: Code duplicated, block: B:71:0x00eb  */
            /* JADX WARN: Code duplicated, block: B:79:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Code duplicated, block: B:93:? A[SYNTHETIC] */
            @Override // c.a.o
            public void subscribe(n<Integer> nVar) throws Throwable {
                Process processStart;
                BufferedReader bufferedReader;
                BufferedReader bufferedReader2;
                new File(str);
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    nVar.onNext(0);
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
                                                nVar.onNext(2);
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
                    nVar.onNext(2);
                } else {
                    nVar.onNext(1);
                }
            }
        }).subscribeOn(b.c()).observeOn(a.a()).subscribe(new s<Integer>() { // from class: com.android.umanalytics.utils.PkgUtils.1
            @Override // c.a.s
            public void onComplete() {
            }

            @Override // c.a.s
            public void onError(Throwable th) {
                LogUtils.e("安装错误：" + th.toString());
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
            }

            @Override // c.a.s
            public void onNext(Integer num) {
                if (num.intValue() == 2) {
                    LogUtils.i("安装成功: " + str);
                    return;
                }
                LogUtils.e("安装失败：" + str);
            }
        });
    }

    public static void restartApp() {
        Intent intent = new Intent(App.b(), (Class<?>) BootReceiver.class);
        intent.setAction("INSTALL_AND_START");
        PendingIntent broadcast = PendingIntent.getBroadcast(App.b(), 0, intent, 268435456);
        App appB = App.b();
        App.b();
        ((AlarmManager) appB.getSystemService("alarm")).set(0, System.currentTimeMillis() + 60000, broadcast);
        LogUtils.i("发送重启App广播");
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
