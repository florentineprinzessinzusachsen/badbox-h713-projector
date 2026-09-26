package com.ad.proxy.f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class D implements Runnable {
    public final /* synthetic */ String a;
    public final /* synthetic */ A b;

    public D(String str, A a) {
        this.a = str;
        this.b = a;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00e0 A[Catch: IOException -> 0x00dc, TRY_LEAVE, TryCatch #17 {IOException -> 0x00dc, blocks: (B:40:0x00d8, B:44:0x00e0), top: B:100:0x00d8 }] */
    /* JADX WARN: Code duplicated, block: B:61:0x010d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x010f A[Catch: IOException -> 0x010b, TRY_LEAVE, TryCatch #8 {IOException -> 0x010b, blocks: (B:58:0x0107, B:62:0x010f), top: B:93:0x0107 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0130 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x0132 A[Catch: IOException -> 0x012e, TRY_LEAVE, TryCatch #7 {IOException -> 0x012e, blocks: (B:70:0x012a, B:74:0x0132), top: B:91:0x012a }] */
    /* JADX WARN: Code duplicated, block: B:87:0x0168 A[Catch: IOException -> 0x0164, TRY_LEAVE, TryCatch #11 {IOException -> 0x0164, blocks: (B:83:0x0160, B:87:0x0168), top: B:96:0x0160 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0107 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        InputStream inputStream;
        Exception e;
        IOException e2;
        InputStream inputStream2;
        Exception e3;
        IOException e4;
        String message;
        String message2;
        boolean z;
        String strA = this.a;
        A a = this.b;
        try {
            strA = com.ad.proxy.g.D.a(strA);
        } catch (InvalidKeyException | NoSuchAlgorithmException e5) {
            com.ad.proxy.g.F.a("Http", e5);
        }
        com.ad.proxy.g.F.a("Http", "START GET " + strA);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strA).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    try {
                        httpURLConnection.setConnectTimeout(50000);
                        httpURLConnection.setReadTimeout(50000);
                        httpURLConnection.setRequestProperty("User-Agent", "proxy");
                        httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
                        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                        httpURLConnection.setRequestProperty("Charset", "UTF-8");
                        httpURLConnection.setRequestProperty("Accept", "application/json");
                        z = true;
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.connect();
                        httpURLConnection.getContentLength();
                        if (httpURLConnection.getResponseCode() == 200) {
                            inputStream = httpURLConnection.getInputStream();
                            try {
                                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
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
                                    String string = byteArrayOutputStream2.toString();
                                    com.ad.proxy.g.F.a("Http", " result:" + string);
                                    message2 = string;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                } catch (IOException e6) {
                                    inputStream2 = inputStream;
                                    e4 = e6;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    message = e4.getMessage();
                                    com.ad.proxy.g.F.b("Http", message);
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                            if (inputStream2 != null) {
                                                inputStream2.close();
                                            }
                                        } catch (IOException e7) {
                                            message = e7.getMessage();
                                            com.ad.proxy.g.F.b("Http", message);
                                            message2 = message;
                                            z = false;
                                            com.ad.proxy.g.F.a("Http", "END GET " + strA + " result : " + message2);
                                            G.a(message2, z, a);
                                        }
                                    } else if (inputStream2 != null) {
                                        inputStream2.close();
                                    }
                                    message2 = message;
                                    z = false;
                                } catch (Exception e8) {
                                    inputStream2 = inputStream;
                                    e3 = e8;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    message = e3.getMessage();
                                    com.ad.proxy.g.F.b("Http", message);
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                            if (inputStream2 != null) {
                                                inputStream2.close();
                                            }
                                        } catch (IOException e9) {
                                            message = e9.getMessage();
                                            com.ad.proxy.g.F.b("Http", message);
                                            message2 = message;
                                            z = false;
                                            com.ad.proxy.g.F.a("Http", "END GET " + strA + " result : " + message2);
                                            G.a(message2, z, a);
                                        }
                                    } else if (inputStream2 != null) {
                                        inputStream2.close();
                                    }
                                    message2 = message;
                                    z = false;
                                } catch (Throwable th) {
                                    th = th;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                            if (inputStream != null) {
                                                inputStream.close();
                                            }
                                        } catch (IOException e10) {
                                            com.ad.proxy.g.F.b("Http", e10.getMessage());
                                            throw th;
                                        }
                                    } else if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (IOException e11) {
                                e2 = e11;
                                IOException iOException = e2;
                                inputStream2 = inputStream;
                                e4 = iOException;
                                message = e4.getMessage();
                                com.ad.proxy.g.F.b("Http", message);
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                    if (inputStream2 != null) {
                                        inputStream2.close();
                                    }
                                } else if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                message2 = message;
                                z = false;
                                com.ad.proxy.g.F.a("Http", "END GET " + strA + " result : " + message2);
                                G.a(message2, z, a);
                            } catch (Exception e12) {
                                e = e12;
                                Exception exc = e;
                                inputStream2 = inputStream;
                                e3 = exc;
                                message = e3.getMessage();
                                com.ad.proxy.g.F.b("Http", message);
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                    if (inputStream2 != null) {
                                        inputStream2.close();
                                    }
                                } else if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                message2 = message;
                                z = false;
                                com.ad.proxy.g.F.a("Http", "END GET " + strA + " result : " + message2);
                                G.a(message2, z, a);
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } else {
                            message2 = "请求失败 code:" + httpURLConnection.getResponseCode();
                            inputStream = null;
                            z = false;
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (IOException e13) {
                                message2 = e13.getMessage();
                                com.ad.proxy.g.F.b("Http", message2);
                            }
                        } else if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e14) {
                        e4 = e14;
                        inputStream2 = null;
                    } catch (Exception e15) {
                        e3 = e15;
                        inputStream2 = null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                }
            } catch (IOException e16) {
                e2 = e16;
                inputStream = null;
            } catch (Exception e17) {
                e = e17;
                inputStream = null;
            }
            com.ad.proxy.g.F.a("Http", "END GET " + strA + " result : " + message2);
            G.a(message2, z, a);
        } catch (Throwable th4) {
            th = th4;
            inputStream = inputStream2;
        }
    }
}
