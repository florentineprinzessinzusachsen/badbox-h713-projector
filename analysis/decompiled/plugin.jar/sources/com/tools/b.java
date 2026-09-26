package com.tools;

import android.util.Log;
import com.hs.p.common.http.HTTPHelper;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f43a = 7000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f44b = "IOUtils";

    class a implements Callable<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f45a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f46b;

        a(String str, String str2) {
            this.f45a = str;
            this.f46b = str2;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String call() throws Exception {
            return b.n(this.f45a, this.f46b);
        }
    }

    /* JADX INFO: renamed from: com.tools.b$b, reason: collision with other inner class name */
    class CallableC0005b implements Callable<Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f47a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f48b;

        CallableC0005b(String str, String str2) {
            this.f47a = str;
            this.f48b = str2;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            b.c(this.f47a, this.f48b);
            return null;
        }
    }

    private static void a(HttpGet httpGet, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            httpGet.addHeader(list.get(i), list.get(i + 1));
        }
    }

    public static void b(HttpPost httpPost, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            httpPost.addHeader(list.get(i), list.get(i + 1));
        }
    }

    public static void c(String str, String str2) throws Throwable {
        OutputStream outputStream;
        InputStream inputStream = null;
        OutputStream outputStreamJ = null;
        try {
            InputStream inputStreamI = i(str);
            try {
                outputStreamJ = j(str2);
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = inputStreamI.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        outputStreamJ.write(bArr, 0, i);
                    }
                }
                inputStreamI.close();
                if (outputStreamJ != null) {
                    outputStreamJ.close();
                }
            } catch (Throwable th) {
                th = th;
                OutputStream outputStream2 = outputStreamJ;
                inputStream = inputStreamI;
                outputStream = outputStream2;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
        }
    }

    public static Future<Void> d(String str, String str2) {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            return executorServiceNewSingleThreadExecutor.submit(new CallableC0005b(str, str2));
        } finally {
            executorServiceNewSingleThreadExecutor.shutdown();
        }
    }

    public static String e(String str, String... strArr) throws IOException {
        String str2;
        ArrayList arrayList = new ArrayList();
        String str3 = null;
        String str4 = HTTPHelper.CHARSET_UTF8;
        String str5 = null;
        String str6 = null;
        int i = 268435455;
        boolean z = false;
        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
            if ("--data".equals(strArr[i2]) || "-D".equals(strArr[i2])) {
                str6 = strArr[i2 + 1];
            } else if ("--encoding".equals(strArr[i2])) {
                str4 = strArr[i2 + 1];
            } else {
                if ("--user-agent".equals(strArr[i2]) || "-A".equals(strArr[i2])) {
                    arrayList.add(HTTPHelper.HEADER_USER_AGENT);
                    str2 = strArr[i2 + 1];
                } else if ("-H".equals(strArr[i2])) {
                    String[] strArrSplit = strArr[i2 + 1].split(": ");
                    arrayList.add(strArrSplit[0]);
                    str2 = strArrSplit[1];
                } else if ("-L".equals(strArr[i2]) || "--location".equals(strArr[i2])) {
                    i = Integer.parseInt(strArr[i2 + 1]);
                } else if ("-u".equals(strArr[i2]) || "--user".equals(strArr[i2])) {
                    String[] strArrSplit2 = strArr[i2 + 1].split("(?<!\\\\):");
                    String str7 = strArrSplit2[0];
                    str5 = strArrSplit2[1];
                    str3 = str7;
                } else if ("-v".equals(strArr[i2]) || "--verbose".equals(strArr[i2])) {
                    z = true;
                }
                arrayList.add(str2);
            }
        }
        String str8 = str5;
        int i3 = i;
        boolean z2 = z;
        return str6 != null ? g(str, str3, str8, i3, z2, str6, arrayList, str4) : f(str, str3, str8, i3, z2, arrayList, str4);
    }

    public static final String f(String str, String str2, String str3, int i, boolean z, List<String> list, String str4) throws IOException {
        HttpGet httpGet = new HttpGet(str);
        a(httpGet, list);
        return l(httpGet, str2, str3, i, z, str4);
    }

    public static final String g(String str, String str2, String str3, int i, boolean z, String str4, List<String> list, String str5) throws IOException {
        HttpPost httpPost = new HttpPost(str);
        b(httpPost, list);
        httpPost.setEntity(new StringEntity(str4));
        return l(httpPost, str2, str3, i, z, str5);
    }

    /* JADX WARN: Code duplicated, block: B:104:0x00f5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x00b8 A[Catch: IOException -> 0x00da, TRY_ENTER, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x00bd A[Catch: IOException -> 0x00da, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x00c2 A[Catch: IOException -> 0x00da, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x00c7 A[Catch: IOException -> 0x00da, TRY_LEAVE, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x00d6 A[Catch: IOException -> 0x00da, TRY_ENTER, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x00de A[Catch: IOException -> 0x00da, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x00e3 A[Catch: IOException -> 0x00da, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x00e8 A[Catch: IOException -> 0x00da, TRY_LEAVE, TryCatch #1 {IOException -> 0x00da, blocks: (B:48:0x0095, B:50:0x009a, B:52:0x009f, B:54:0x00a4, B:63:0x00b8, B:65:0x00bd, B:67:0x00c2, B:69:0x00c7, B:75:0x00d6, B:79:0x00de, B:81:0x00e3, B:83:0x00e8), top: B:106:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x00fd A[Catch: IOException -> 0x00f9, TryCatch #0 {IOException -> 0x00f9, blocks: (B:91:0x00f5, B:95:0x00fd, B:97:0x0102, B:99:0x0107), top: B:104:0x00f5 }] */
    /* JADX WARN: Code duplicated, block: B:97:0x0102 A[Catch: IOException -> 0x00f9, TryCatch #0 {IOException -> 0x00f9, blocks: (B:91:0x00f5, B:95:0x00fd, B:97:0x0102, B:99:0x0107), top: B:104:0x00f5 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x0107 A[Catch: IOException -> 0x00f9, TRY_LEAVE, TryCatch #0 {IOException -> 0x00f9, blocks: (B:91:0x00f5, B:95:0x00fd, B:97:0x0102, B:99:0x0107), top: B:104:0x00f5 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.OutputStream] */
    public static boolean h(String str, String str2, String str3) throws Throwable {
        ?? fileOutputStream;
        BufferedInputStream bufferedInputStream;
        ?? bufferedOutputStream;
        IOException e;
        MalformedURLException e2;
        ?? r7;
        ?? r3;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file.getAbsolutePath(), str3);
        if (file2.exists()) {
            file2.delete();
        }
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        ?? r2 = 0;
        try {
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(com.cloudmedia.tv.server.a.m);
                    if (httpURLConnection.getResponseCode() == 200) {
                        str = httpURLConnection.getInputStream();
                        try {
                            bufferedInputStream = new BufferedInputStream(str);
                            try {
                                fileOutputStream = new FileOutputStream(file2);
                                try {
                                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                                    try {
                                        byte[] bArr = new byte[com.cloudmedia.tv.server.a.l.s];
                                        while (true) {
                                            int i = bufferedInputStream.read(bArr);
                                            if (i == -1) {
                                                break;
                                            }
                                            bufferedOutputStream.write(bArr, 0, i);
                                        }
                                        bufferedOutputStream.flush();
                                        r2 = bufferedOutputStream;
                                        r3 = fileOutputStream;
                                        r7 = str;
                                    } catch (MalformedURLException e3) {
                                        e2 = e3;
                                        e2.printStackTrace();
                                        if (bufferedOutputStream != 0) {
                                            bufferedOutputStream.close();
                                        }
                                        if (fileOutputStream != 0) {
                                            fileOutputStream.close();
                                        }
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (str != 0) {
                                            str.close();
                                        }
                                        return true;
                                    } catch (IOException e4) {
                                        e = e4;
                                        e.printStackTrace();
                                        if (bufferedOutputStream != 0) {
                                            bufferedOutputStream.close();
                                        }
                                        if (fileOutputStream != 0) {
                                            fileOutputStream.close();
                                        }
                                        if (bufferedInputStream != null) {
                                            bufferedInputStream.close();
                                        }
                                        if (str != 0) {
                                            str.close();
                                        }
                                        return true;
                                    }
                                } catch (MalformedURLException e5) {
                                    e2 = e5;
                                    bufferedOutputStream = 0;
                                } catch (IOException e6) {
                                    e = e6;
                                    bufferedOutputStream = 0;
                                } catch (Throwable th) {
                                    th = th;
                                    if (r1 != 0) {
                                        try {
                                            r1.close();
                                        } catch (IOException e7) {
                                            e7.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (fileOutputStream != 0) {
                                        fileOutputStream.close();
                                    }
                                    if (bufferedInputStream != null) {
                                        bufferedInputStream.close();
                                    }
                                    if (str != 0) {
                                        str.close();
                                    }
                                    return true;
                                }
                            } catch (MalformedURLException e8) {
                                e = e8;
                                fileOutputStream = 0;
                                e2 = e;
                                bufferedOutputStream = fileOutputStream;
                                e2.printStackTrace();
                                if (bufferedOutputStream != 0) {
                                    bufferedOutputStream.close();
                                }
                                if (fileOutputStream != 0) {
                                    fileOutputStream.close();
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (str != 0) {
                                    str.close();
                                }
                                return true;
                            } catch (IOException e9) {
                                e = e9;
                                fileOutputStream = 0;
                                e = e;
                                bufferedOutputStream = fileOutputStream;
                                e.printStackTrace();
                                if (bufferedOutputStream != 0) {
                                    bufferedOutputStream.close();
                                }
                                if (fileOutputStream != 0) {
                                    fileOutputStream.close();
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (str != 0) {
                                    str.close();
                                }
                                return true;
                            } catch (Throwable th2) {
                                th = th2;
                                fileOutputStream = 0;
                            }
                        } catch (MalformedURLException e10) {
                            e = e10;
                            bufferedInputStream = null;
                            fileOutputStream = 0;
                        } catch (IOException e11) {
                            e = e11;
                            bufferedInputStream = null;
                            fileOutputStream = 0;
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedInputStream = null;
                            str = str;
                            fileOutputStream = bufferedInputStream;
                            if (r1 != 0) {
                                r1.close();
                            }
                            if (fileOutputStream != 0) {
                                fileOutputStream.close();
                            }
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            if (str != 0) {
                                str.close();
                            }
                            return true;
                        }
                    } else {
                        r7 = 0;
                        bufferedInputStream = null;
                        r3 = 0;
                    }
                    if (r2 != 0) {
                        r2.close();
                    }
                    if (r3 != 0) {
                        r3.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (r7 != 0) {
                        r7.close();
                    }
                    return true;
                } catch (IOException e12) {
                    e12.printStackTrace();
                    return false;
                }
            } catch (MalformedURLException e13) {
                bufferedOutputStream = 0;
                bufferedInputStream = null;
                fileOutputStream = 0;
                e2 = e13;
                str = 0;
            } catch (IOException e14) {
                bufferedOutputStream = 0;
                bufferedInputStream = null;
                fileOutputStream = 0;
                e = e14;
                str = 0;
            } catch (Throwable th4) {
                th = th4;
                str = 0;
                bufferedInputStream = null;
            }
        } catch (Throwable th5) {
            th = th5;
            r1 = file2;
        }
    }

    public static InputStream i(String str) throws IOException {
        if (str.startsWith("http://")) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setConnectTimeout(f43a - 500);
            httpURLConnection.setReadTimeout(f43a);
            InputStream inputStream = httpURLConnection.getInputStream();
            return "gzip".equals(httpURLConnection.getContentEncoding()) ? new GZIPInputStream(inputStream) : inputStream;
        }
        if (str.charAt(0) == '~') {
            str = System.getProperty("user.home") + str.substring(1);
        }
        return new FileInputStream(str);
    }

    public static OutputStream j(String str) throws FileNotFoundException {
        if (str.charAt(0) == '~') {
            str = System.getProperty("user.home") + str.substring(1);
        }
        return new FileOutputStream(str);
    }

    public static void k(Header[] headerArr) {
        for (Header header : headerArr) {
            System.out.println(header.toString());
        }
    }

    public static final String l(HttpUriRequest httpUriRequest, String str, String str2, int i, boolean z, String str3) throws IOException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        if (str != null) {
            defaultHttpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(str, str2));
        }
        HttpClientParams.setRedirecting(defaultHttpClient.getParams(), false);
        HttpResponse httpResponseExecute = defaultHttpClient.execute(httpUriRequest);
        Header contentEncoding = httpResponseExecute.getEntity().getContentEncoding();
        int statusCode = httpResponseExecute.getStatusLine().getStatusCode();
        if (z) {
            System.out.println("********request header********");
            k(httpUriRequest.getAllHeaders());
            System.out.println();
            System.out.println("********response header********");
            System.out.println("Status: " + statusCode);
            k(httpResponseExecute.getAllHeaders());
            System.out.println();
            System.out.println();
        }
        if (statusCode != 200) {
            if (statusCode < 300 || statusCode >= 400 || i <= 0) {
                throw new IOException(httpUriRequest.getMethod() + " " + httpUriRequest.getURI() + " response status: " + httpResponseExecute.getStatusLine().getStatusCode());
            }
            String value = httpResponseExecute.getHeaders(HTTPHelper.HEADER_LOCATION)[0].getValue();
            if (z) {
                System.out.println("[DEBUG] redirecting..." + value);
            }
            return l(new HttpGet(value), str, str2, i - 1, z, str3);
        }
        InputStream content = httpResponseExecute.getEntity().getContent();
        if (contentEncoding != null) {
            for (HeaderElement headerElement : contentEncoding.getElements()) {
                if ("gzip".equalsIgnoreCase(headerElement.getName())) {
                    content = new GZIPInputStream(content);
                }
            }
        }
        InputStreamReader inputStreamReader = new InputStreamReader(content, str3);
        StringBuilder sb = new StringBuilder(256);
        char[] cArr = new char[4096];
        while (true) {
            int i2 = inputStreamReader.read(cArr);
            if (i2 == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, i2);
        }
    }

    public static String m(String str) throws IOException {
        return n(str, "utf-8");
    }

    public static String n(String str, String str2) throws Throwable {
        InputStreamReader inputStreamReader = null;
        try {
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(i(str), str2);
                try {
                    StringBuilder sb = new StringBuilder(256);
                    char[] cArr = new char[4096];
                    while (true) {
                        int i = inputStreamReader2.read(cArr);
                        if (i == -1) {
                            String string = sb.toString();
                            inputStreamReader2.close();
                            return string;
                        }
                        sb.append(cArr, 0, i);
                    }
                } catch (FileNotFoundException e) {
                    e = e;
                    Log.d(f44b, str + " ---> fetch failed!");
                    throw e;
                } catch (SocketTimeoutException unused) {
                    inputStreamReader = inputStreamReader2;
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    throw new SocketTimeoutException(str + " retry failed!");
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader = inputStreamReader2;
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException e2) {
            e = e2;
        } catch (SocketTimeoutException unused2) {
        }
    }

    public static Future<String> o(String str) {
        return p(str, "utf-8");
    }

    public static Future<String> p(String str, String str2) {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            return executorServiceNewSingleThreadExecutor.submit(new a(str, str2));
        } finally {
            executorServiceNewSingleThreadExecutor.shutdown();
        }
    }

    public static void q(String str, String str2) throws Throwable {
        r(str, str2, "utf-8");
    }

    public static void r(String str, String str2, String str3) throws Throwable {
        OutputStreamWriter outputStreamWriter = null;
        try {
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(j(str), str3);
            try {
                outputStreamWriter2.write(str2);
                outputStreamWriter2.close();
            } catch (Throwable th) {
                th = th;
                outputStreamWriter = outputStreamWriter2;
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean s(String str, String str2) {
        try {
            ZipFile zipFile = new ZipFile(str);
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                File file = new File(str2 + File.separator + zipEntryNextElement.getName());
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = bufferedInputStream.read(bArr, 0, 4096);
                    if (i != -1) {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.close();
            }
            zipFile.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String t(String str) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, HTTPHelper.CHARSET_UTF8);
    }

    public static String u(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, HTTPHelper.CHARSET_UTF8);
    }
}
