package com.ad.proxy.b;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {
    public static F a(int i, String str) throws IOException {
        int port;
        URL url = new URL(str);
        String host = url.getHost();
        if (url.getPort() == -1) {
            port = url.getProtocol().equals("https") ? 443 : 80;
        } else {
            port = url.getPort();
        }
        boolean zEqualsIgnoreCase = url.getProtocol().equalsIgnoreCase("https");
        F f = new F();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        InetAddress byName = InetAddress.getByName(host);
        f.b = System.currentTimeMillis() - jCurrentTimeMillis2;
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        Socket socketCreateSocket = zEqualsIgnoreCase ? SSLSocketFactory.getDefault().createSocket() : new Socket();
        socketCreateSocket.connect(new InetSocketAddress(byName, port), 10000);
        f.c = System.currentTimeMillis() - jCurrentTimeMillis3;
        if (zEqualsIgnoreCase) {
            long jCurrentTimeMillis4 = System.currentTimeMillis();
            ((SSLSocket) socketCreateSocket).startHandshake();
            f.d = System.currentTimeMillis() - jCurrentTimeMillis4;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socketCreateSocket.getOutputStream(), "UTF-8"));
        bufferedWriter.write("GET " + (url.getFile().isEmpty() ? "/" : url.getFile()) + " HTTP/1.1\r\n");
        bufferedWriter.write("Host: " + host + "\r\n");
        bufferedWriter.write("Connection: close\r\n");
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socketCreateSocket.getInputStream());
        long jCurrentTimeMillis5 = System.currentTimeMillis();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i2 = bufferedInputStream.read();
            if (i2 == -1) {
                break;
            }
            byteArrayOutputStream.write(i2);
            if (byteArrayOutputStream.size() >= 4 && byteArrayOutputStream.toString("UTF-8").endsWith("\r\n\r\n")) {
                break;
            }
        }
        f.e = System.currentTimeMillis() - jCurrentTimeMillis5;
        Matcher matcher = Pattern.compile("HTTP/\\d\\.\\d\\s+(\\d{3})").matcher(byteArrayOutputStream.toString("UTF-8"));
        if (matcher.find()) {
            f.a = Integer.parseInt(matcher.group(1));
        }
        int i3 = i * 1024;
        byte[] bArr = new byte[1024];
        int i4 = 0;
        while (i4 < i3) {
            int i5 = bufferedInputStream.read(bArr, 0, Math.min(1024, i3 - i4));
            if (i5 == -1) {
                break;
            }
            i4 += i5;
        }
        f.g = i4;
        f.f = System.currentTimeMillis() - jCurrentTimeMillis;
        socketCreateSocket.close();
        return f;
    }
}
