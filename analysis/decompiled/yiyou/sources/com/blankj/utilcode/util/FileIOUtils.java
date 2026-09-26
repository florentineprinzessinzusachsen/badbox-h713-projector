package com.blankj.utilcode.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FileIOUtils {
    private static int sBufferSize = 524288;

    public interface OnProgressUpdateListener {
        void onProgressUpdate(double d2);
    }

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static byte[] is2Bytes(InputStream inputStream) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        if (inputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    byte[] bArr = new byte[sBufferSize];
                    while (true) {
                        int i = inputStream.read(bArr, 0, sBufferSize);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                        th = th;
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        if (byteArrayOutputStream == null) {
                            throw th;
                        }
                        try {
                            byteArrayOutputStream.close();
                            throw th;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    return byteArray;
                } catch (IOException e6) {
                    e = e6;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e9) {
            e = e9;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            byteArrayOutputStream = null;
            th = th3;
        }
    }

    private static boolean isFileExists(File file) {
        return file != null && file.exists();
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

    public static byte[] readFile2BytesByChannel(String str) {
        return readFile2BytesByChannel(getFileByPath(str));
    }

    public static byte[] readFile2BytesByMap(String str) {
        return readFile2BytesByMap(getFileByPath(str));
    }

    public static byte[] readFile2BytesByStream(String str) {
        return readFile2BytesByStream(getFileByPath(str), (OnProgressUpdateListener) null);
    }

    public static List<String> readFile2List(String str) {
        return readFile2List(getFileByPath(str), (String) null);
    }

    public static String readFile2String(String str) {
        return readFile2String(getFileByPath(str), (String) null);
    }

    public static void setBufferSize(int i) {
        sBufferSize = i;
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(str, bArr, false, z);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream) {
        return writeFileFromIS(getFileByPath(str), inputStream, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromString(String str, String str2) {
        return writeFileFromString(getFileByPath(str), str2, false);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3 */
    public static byte[] readFile2BytesByChannel(File file) throws Throwable {
        FileChannel channel;
        try {
            if (!isFileExists(file)) {
                return null;
            }
            try {
                channel = new RandomAccessFile(file, "r").getChannel();
                try {
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) channel.size());
                    while (channel.read(byteBufferAllocate) > 0) {
                    }
                    byte[] bArrArray = byteBufferAllocate.array();
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return bArrArray;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException e5) {
                e = e5;
                channel = null;
            } catch (Throwable th) {
                th = th;
                file = 0;
                if (file != 0) {
                    try {
                        file.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static byte[] readFile2BytesByMap(File file) throws Throwable {
        FileChannel channel;
        if (!isFileExists(file)) {
            return null;
        }
        try {
            channel = new RandomAccessFile(file, "r").getChannel();
            try {
                try {
                    int size = (int) channel.size();
                    byte[] bArr = new byte[size];
                    channel.map(FileChannel.MapMode.READ_ONLY, 0L, size).load().get(bArr, 0, size);
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (channel != null) {
                    try {
                        channel.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e6) {
            e = e6;
            channel = null;
        } catch (Throwable th2) {
            th = th2;
            channel = null;
            if (channel != null) {
                channel.close();
            }
            throw th;
        }
    }

    public static byte[] readFile2BytesByStream(File file) {
        return readFile2BytesByStream(file, (OnProgressUpdateListener) null);
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(getFileByPath(str), str2);
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(getFileByPath(str), str2);
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByChannel(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByMap(getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(getFileByPath(str), str2, z);
    }

    public static byte[] readFile2BytesByStream(String str, OnProgressUpdateListener onProgressUpdateListener) {
        return readFile2BytesByStream(getFileByPath(str));
    }

    public static List<String> readFile2List(File file) {
        return readFile2List(file, 0, Integer.MAX_VALUE, (String) null);
    }

    public static String readFile2String(File file) {
        return readFile2String(file, (String) null);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr) {
        return writeFileFromBytesByStream(file, bArr, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream) {
        return writeFileFromIS(file, inputStream, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromString(File file, String str) {
        return writeFileFromString(file, str, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r10v5 */
    public static byte[] readFile2BytesByStream(File file, OnProgressUpdateListener onProgressUpdateListener) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ?? IsFileExists = isFileExists(file);
        if (IsFileExists == 0) {
            return null;
        }
        try {
            try {
                IsFileExists = new BufferedInputStream(new FileInputStream((File) file), sBufferSize);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        byte[] bArr = new byte[sBufferSize];
                        if (onProgressUpdateListener != null) {
                            double dAvailable = IsFileExists.available();
                            onProgressUpdateListener.onProgressUpdate(0.0d);
                            int i = 0;
                            while (true) {
                                int i2 = IsFileExists.read(bArr, 0, sBufferSize);
                                if (i2 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i2);
                                i += i2;
                                double d2 = i;
                                Double.isNaN(d2);
                                Double.isNaN(dAvailable);
                                onProgressUpdateListener.onProgressUpdate(d2 / dAvailable);
                            }
                        } else {
                            while (true) {
                                int i3 = IsFileExists.read(bArr, 0, sBufferSize);
                                if (i3 == -1) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, i3);
                            }
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        try {
                            IsFileExists.close();
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
                            IsFileExists.close();
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
                    file = 0;
                    try {
                        IsFileExists.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                    if (file != 0) {
                        try {
                            file.close();
                            throw th;
                        } catch (IOException e9) {
                            e9.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static String readFile2String(File file, String str) {
        byte[] file2BytesByStream = readFile2BytesByStream(file);
        if (file2BytesByStream == null) {
            return null;
        }
        if (isSpace(str)) {
            return new String(file2BytesByStream);
        }
        try {
            return new String(file2BytesByStream, str);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        FileChannel channel = null;
        try {
            channel = new FileOutputStream(file, z).getChannel();
            channel.position(channel.size());
            channel.write(ByteBuffer.wrap(bArr));
            if (z2) {
                channel.force(true);
            }
            return true;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z, boolean z2) throws Throwable {
        FileChannel channel;
        if (bArr == null || !createOrExistsFile(file)) {
            return false;
        }
        FileChannel fileChannel = null;
        try {
            try {
                channel = new FileOutputStream(file, z).getChannel();
                try {
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, channel.size(), bArr.length);
                    map.put(bArr);
                    if (z2) {
                        map.force();
                    }
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return true;
                } catch (IOException e3) {
                    e = e3;
                    fileChannel = channel;
                    e.printStackTrace();
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                channel = fileChannel;
            }
        } catch (IOException e6) {
            e = e6;
        }
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(file, bArr, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        return writeFileFromIS(file, inputStream, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromString(File file, String str, boolean z) throws Throwable {
        if (file == null || str == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, z));
                try {
                    bufferedWriter2.write(str);
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return true;
                } catch (IOException e3) {
                    e = e3;
                    bufferedWriter = bufferedWriter2;
                    e.printStackTrace();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e6) {
            e = e6;
        }
    }

    public static List<String> readFile2List(String str, int i, int i2) {
        return readFile2List(getFileByPath(str), i, i2, (String) null);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(getFileByPath(str), inputStream, false, onProgressUpdateListener);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(getFileByPath(str), i, i2, str2);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(getFileByPath(str), bArr, z, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(getFileByPath(str), inputStream, z, onProgressUpdateListener);
    }

    public static List<String> readFile2List(File file, int i, int i2) {
        return readFile2List(file, i, i2, (String) null);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(file, bArr, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(file, inputStream, false, onProgressUpdateListener);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static List<String> readFile2List(File file, int i, int i2, String str) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (!isFileExists(file) || i > i2) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            int i3 = 1;
            if (isSpace(str)) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
            }
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null || i3 > i2) {
                            break;
                            break;
                        }
                        if (i <= i3 && i3 <= i2) {
                            arrayList.add(line);
                        }
                        i3++;
                    } catch (IOException e2) {
                        e = e2;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
            try {
                bufferedReader.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            return arrayList;
        } catch (IOException e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        if (bArr == null) {
            return false;
        }
        return writeFileFromIS(file, new ByteArrayInputStream(bArr), z, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z, OnProgressUpdateListener onProgressUpdateListener) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        if (inputStream == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z), sBufferSize);
                try {
                    if (onProgressUpdateListener == null) {
                        byte[] bArr = new byte[sBufferSize];
                        while (true) {
                            int i = inputStream.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, i);
                        }
                    } else {
                        double dAvailable = inputStream.available();
                        onProgressUpdateListener.onProgressUpdate(0.0d);
                        byte[] bArr2 = new byte[sBufferSize];
                        int i2 = 0;
                        while (true) {
                            int i3 = inputStream.read(bArr2);
                            if (i3 == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr2, 0, i3);
                            i2 += i3;
                            double d2 = i2;
                            Double.isNaN(d2);
                            Double.isNaN(dAvailable);
                            onProgressUpdateListener.onProgressUpdate(d2 / dAvailable);
                        }
                    }
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return true;
                } catch (IOException e4) {
                    e = e4;
                    bufferedOutputStream2 = bufferedOutputStream;
                    e.printStackTrace();
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    try {
                        inputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                            throw th;
                        } catch (IOException e8) {
                            e8.printStackTrace();
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (IOException e9) {
                e = e9;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = bufferedOutputStream2;
        }
    }
}
