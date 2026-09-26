package com.blankj.utilcode.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ResourceUtils {
    private static final int BUFFER_SIZE = 8192;

    private ResourceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean copyFileFromAssets(String str, String str2) {
        try {
            String[] list = Utils.getApp().getAssets().list(str);
            if (list.length <= 0) {
                return writeFileFromIS(str2, Utils.getApp().getAssets().open(str), false);
            }
            boolean zCopyFileFromAssets = true;
            for (String str3 : list) {
                zCopyFileFromAssets &= copyFileFromAssets(str + "/" + str3, str2 + "/" + str3);
            }
            return zCopyFileFromAssets;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean copyFileFromRaw(int i, String str) {
        return writeFileFromIS(str, Utils.getApp().getResources().openRawResource(i), false);
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int getAnimIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "anim", Utils.getApp().getPackageName());
    }

    public static int getColorIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "color", Utils.getApp().getPackageName());
    }

    public static int getDimenIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "dimen", Utils.getApp().getPackageName());
    }

    public static int getDrawableIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "drawable", Utils.getApp().getPackageName());
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static int getIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "id", Utils.getApp().getPackageName());
    }

    public static int getLayoutIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "layout", Utils.getApp().getPackageName());
    }

    public static int getMenuIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "menu", Utils.getApp().getPackageName());
    }

    public static int getMipmapIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "mipmap", Utils.getApp().getPackageName());
    }

    public static int getStringIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "string", Utils.getApp().getPackageName());
    }

    public static int getStyleIdByName(String str) {
        return Utils.getApp().getResources().getIdentifier(str, "style", Utils.getApp().getPackageName());
    }

    private static byte[] is2Bytes(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[BUFFER_SIZE];
                    while (true) {
                        int i = inputStream.read(bArr, 0, BUFFER_SIZE);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return byteArray;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException e7) {
                e = e7;
                byteArrayOutputStream = null;
            } catch (Throwable th) {
                th = th;
                try {
                    inputStream.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
                if (0 == 0) {
                    throw th;
                }
                try {
                    byteArrayOutputStream2.close();
                    throw th;
                } catch (IOException e9) {
                    e9.printStackTrace();
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static List<String> is2List(InputStream inputStream, String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            ArrayList arrayList = new ArrayList();
            bufferedReader = isSpace(str) ? new BufferedReader(new InputStreamReader(inputStream)) : new BufferedReader(new InputStreamReader(inputStream, str));
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line != null) {
                            arrayList.add(line);
                        } else {
                            try {
                                break;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                }
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
            bufferedReader.close();
            return arrayList;
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static List<String> readAssets2List(String str) {
        return readAssets2List(str, null);
    }

    public static String readAssets2String(String str) {
        return readAssets2String(str, null);
    }

    public static List<String> readRaw2List(int i) {
        return readRaw2List(i, null);
    }

    public static String readRaw2String(int i) {
        return readRaw2String(i, null);
    }

    private static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z);
    }

    public static List<String> readAssets2List(String str, String str2) {
        try {
            return is2List(Utils.getApp().getResources().getAssets().open(str), str2);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String readAssets2String(String str, String str2) throws Throwable {
        try {
            byte[] bArrIs2Bytes = is2Bytes(Utils.getApp().getAssets().open(str));
            if (bArrIs2Bytes == null) {
                return null;
            }
            if (isSpace(str2)) {
                return new String(bArrIs2Bytes);
            }
            try {
                return new String(bArrIs2Bytes, str2);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return "";
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static List<String> readRaw2List(int i, String str) {
        return is2List(Utils.getApp().getResources().openRawResource(i), str);
    }

    public static String readRaw2String(int i, String str) throws Throwable {
        byte[] bArrIs2Bytes = is2Bytes(Utils.getApp().getResources().openRawResource(i));
        if (bArrIs2Bytes == null) {
            return null;
        }
        if (isSpace(str)) {
            return new String(bArrIs2Bytes);
        }
        try {
            return new String(bArrIs2Bytes, str);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) throws Throwable {
        if (!createOrExistsFile(file) || inputStream == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file, z));
            try {
                byte[] bArr = new byte[BUFFER_SIZE];
                while (true) {
                    int i = inputStream.read(bArr, 0, BUFFER_SIZE);
                    if (i == -1) {
                        break;
                    }
                    bufferedOutputStream2.write(bArr, 0, i);
                }
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return true;
            } catch (IOException e4) {
                e = e4;
                bufferedOutputStream = bufferedOutputStream2;
                e.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                if (bufferedOutputStream == null) {
                    throw th;
                }
                try {
                    bufferedOutputStream.close();
                    throw th;
                } catch (IOException e8) {
                    e8.printStackTrace();
                    throw th;
                }
            }
        } catch (IOException e9) {
            e = e9;
        }
    }
}
