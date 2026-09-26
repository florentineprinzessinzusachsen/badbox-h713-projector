package com.ad.proxy.f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class E implements Runnable {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ B c;

    public E(String str, String str2, B b) {
        this.a = str;
        this.b = str2;
        this.c = b;
    }

    /* JADX WARN: Code duplicated, block: B:41:0x00f5 A[Catch: IOException -> 0x00f1, TRY_LEAVE, TryCatch #3 {IOException -> 0x00f1, blocks: (B:37:0x00ed, B:41:0x00f5), top: B:84:0x00ed }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0117 A[Catch: IOException -> 0x0113, TRY_LEAVE, TryCatch #2 {IOException -> 0x0113, blocks: (B:50:0x010f, B:54:0x0117), top: B:82:0x010f }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0136 A[Catch: IOException -> 0x0132, TRY_LEAVE, TryCatch #4 {IOException -> 0x0132, blocks: (B:61:0x012e, B:65:0x0136), top: B:86:0x012e }] */
    /* JADX WARN: Code duplicated, block: B:77:0x016c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:78:0x016e A[Catch: IOException -> 0x016a, TRY_LEAVE, TryCatch #9 {IOException -> 0x016a, blocks: (B:74:0x0166, B:78:0x016e), top: B:88:0x0166 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x0166 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        String message;
        InputStream inputStream2;
        String strA = this.a;
        String str = this.b;
        B b = this.c;
        try {
            strA = com.ad.proxy.g.D.a(strA);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            com.ad.proxy.g.F.a("Http", e);
        }
        com.ad.proxy.g.F.a("Http", "START PUT " + strA + "\nparams : " + str);
        Object obj = null;
        byteArrayOutputStream = null;
        byteArrayOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        ByteArrayOutputStream byteArrayOutputStream4 = null;
        ByteArrayOutputStream byteArrayOutputStream5 = null;
        boolean z = false;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strA).openConnection();
                httpURLConnection.setRequestMethod("PUT");
                httpURLConnection.setConnectTimeout(50000);
                httpURLConnection.setReadTimeout(50000);
                httpURLConnection.setRequestProperty("User-Agent", "proxy");
                httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                if (str != null) {
                    outputStreamWriter.write(str);
                    outputStreamWriter.flush();
                }
                httpURLConnection.connect();
                httpURLConnection.getContentLength();
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        byteArrayOutputStream2 = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int i = inputStream.read(bArr);
                                if (i == -1) {
                                    break;
                                } else {
                                    byteArrayOutputStream2.write(bArr, 0, i);
                                }
                            }
                            message = byteArrayOutputStream2.toString();
                            com.ad.proxy.g.F.a("Http", "backStr:" + message);
                            byteArrayOutputStream3 = byteArrayOutputStream2;
                            z = true;
                            inputStream2 = inputStream;
                        } catch (MalformedURLException e2) {
                            e = e2;
                            byteArrayOutputStream4 = byteArrayOutputStream2;
                            message = e.getMessage();
                            e.printStackTrace();
                            if (byteArrayOutputStream4 != null) {
                                try {
                                    byteArrayOutputStream4.close();
                                    if (inputStream != 0) {
                                        inputStream.close();
                                    }
                                } catch (IOException e3) {
                                    message = e3.getMessage();
                                    e3.printStackTrace();
                                }
                            } else if (inputStream != 0) {
                                inputStream.close();
                            }
                        } catch (IOException e4) {
                            e = e4;
                            byteArrayOutputStream5 = byteArrayOutputStream2;
                            message = e.getMessage();
                            e.printStackTrace();
                            if (byteArrayOutputStream5 != null) {
                                try {
                                    byteArrayOutputStream5.close();
                                    if (inputStream != 0) {
                                        inputStream.close();
                                    }
                                } catch (IOException e5) {
                                    message = e5.getMessage();
                                    e5.printStackTrace();
                                }
                            } else if (inputStream != 0) {
                                inputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            if (byteArrayOutputStream2 != null) {
                                try {
                                    byteArrayOutputStream2.close();
                                    if (inputStream != 0) {
                                        inputStream.close();
                                    }
                                } catch (IOException e6) {
                                    e6.getMessage();
                                    e6.printStackTrace();
                                    throw th;
                                }
                            } else if (inputStream != 0) {
                                inputStream.close();
                            }
                            throw th;
                        }
                    } catch (MalformedURLException e7) {
                        e = e7;
                    } catch (IOException e8) {
                        e = e8;
                    }
                } else {
                    message = "请求失败 code:" + httpURLConnection.getResponseCode();
                    inputStream2 = null;
                }
                if (byteArrayOutputStream3 != null) {
                    try {
                        byteArrayOutputStream3.close();
                        if (inputStream2 != null) {
                            inputStream2.close();
                        }
                    } catch (IOException e9) {
                        message = e9.getMessage();
                        e9.printStackTrace();
                    }
                } else if (inputStream2 != null) {
                    inputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                obj = "UTF-8";
                byteArrayOutputStream = null;
                byteArrayOutputStream2 = byteArrayOutputStream;
                inputStream = obj;
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                    if (inputStream != 0) {
                        inputStream.close();
                    }
                } else if (inputStream != 0) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (MalformedURLException e10) {
            e = e10;
            inputStream = 0;
        } catch (IOException e11) {
            e = e11;
            inputStream = 0;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            byteArrayOutputStream2 = byteArrayOutputStream;
            inputStream = obj;
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
                if (inputStream != 0) {
                    inputStream.close();
                }
            } else if (inputStream != 0) {
                inputStream.close();
            }
            throw th;
        }
        com.ad.proxy.g.F.a("Http", "END PUT " + strA + " result : " + message);
        G.a(message, z, b);
    }
}
