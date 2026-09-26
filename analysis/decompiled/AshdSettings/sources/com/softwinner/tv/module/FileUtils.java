package com.softwinner.tv.module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    private FileUtils() {
    }

    public static boolean writeStringToFile(String str, String str2) throws Throwable {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        boolean z = false;
        if (!file.isFile()) {
            File file2 = new File(str.substring(0, str.lastIndexOf("/")));
            if (!file2.exists() && !file2.mkdirs()) {
                return false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    try {
                        file.createNewFile();
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            fileOutputStream2.write(str2.getBytes());
                            z = true;
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.flush();
                                fileOutputStream2.close();
                            }
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            }
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        return z;
    }

    public static String readFileToString(String str) throws Throwable {
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        if (file.exists() && file.isFile()) {
            BufferedReader bufferedReader = null;
            try {
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                        while (true) {
                            try {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line);
                            } catch (IOException e) {
                                e = e;
                                bufferedReader = bufferedReader2;
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                                return sb.toString();
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        }
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        return sb.toString();
    }
}
