package com.cloudmedia.tv.server;

import com.hs.p.common.http.HTTPHelper;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public abstract class a {
    public static final int m = 3000;
    public static final String n = "text/plain";
    public static final String o = "text/html";
    private static final String p = "NanoHttpd.QUERY_STRING";
    protected static Map<String, String> r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile ServerSocket f7b;
    private s c;
    private Thread d;
    protected b e;
    private v f;
    private static final String g = "([ |\t]*Content-Disposition[ |\t]*:)(.*)";
    private static final Pattern h = Pattern.compile(g, 2);
    private static final String i = "([ |\t]*content-type[ |\t]*:)(.*)";
    private static final Pattern j = Pattern.compile(i, 2);
    private static final String k = "[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]";
    private static final Pattern l = Pattern.compile(k);
    private static final Logger q = Logger.getLogger(a.class.getName());
    public static int s = 16889;

    public interface b {
        void a(c cVar);

        void b(c cVar);

        void c();
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final InputStream f8a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Socket f9b;

        public c(InputStream inputStream, Socket socket) {
            this.f8a = inputStream;
            this.f9b = socket;
        }

        public void a() {
            a.E(this.f8a);
            a.E(this.f9b);
        }

        @Override // java.lang.Runnable
        public void run() {
            OutputStream outputStream = null;
            try {
                outputStream = this.f9b.getOutputStream();
                l lVar = a.this.new l(a.this.f.a(), this.f8a, outputStream, this.f9b.getInetAddress());
                while (!this.f9b.isClosed()) {
                    lVar.a();
                }
            } catch (Exception e) {
                if ((!(e instanceof SocketException) || !"NanoHttpd Shutdown".equals(e.getMessage())) && !(e instanceof SocketTimeoutException)) {
                    a.q.log(Level.SEVERE, "Communication with the client broken, or an bug in the handler code", (Throwable) e);
                }
            } finally {
                a.E(outputStream);
                a.E(this.f8a);
                a.E(this.f9b);
                a.this.e.b(this);
            }
        }
    }

    protected static class d {
        private static final String e = "US-ASCII";
        private static final String f = "multipart/form-data";
        private static final String g = "[ |\t]*([^/^ ^;^,]+/[^ ^;^,]+)";
        private static final Pattern h = Pattern.compile(g, 2);
        private static final String i = "[ |\t]*(charset)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?";
        private static final Pattern j = Pattern.compile(i, 2);
        private static final String k = "[ |\t]*(boundary)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?";
        private static final Pattern l = Pattern.compile(k, 2);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f10a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f11b;
        private final String c;
        private final String d;

        public d(String str) {
            String strD;
            this.f10a = str;
            if (str != null) {
                this.f11b = d(str, h, "", 1);
                strD = d(str, j, null, 2);
            } else {
                this.f11b = "";
                strD = HTTPHelper.CHARSET_UTF8;
            }
            this.c = strD;
            if (f.equalsIgnoreCase(this.f11b)) {
                this.d = d(str, l, null, 2);
            } else {
                this.d = null;
            }
        }

        private String d(String str, Pattern pattern, String str2, int i2) {
            Matcher matcher = pattern.matcher(str);
            return matcher.find() ? matcher.group(i2) : str2;
        }

        public String a() {
            return this.d;
        }

        public String b() {
            return this.f11b;
        }

        public String c() {
            return this.f10a;
        }

        public String e() {
            String str = this.c;
            return str == null ? e : str;
        }

        public boolean f() {
            return f.equalsIgnoreCase(this.f11b);
        }

        public d g() {
            if (this.c != null) {
                return this;
            }
            return new d(this.f10a + "; charset=UTF-8");
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f12a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final String f13b;
        private final String c;

        public e(String str, String str2) {
            this(str, str2, 30);
        }

        public static String b(int i) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            calendar.add(5, i);
            return simpleDateFormat.format(calendar.getTime());
        }

        public String a() {
            return String.format("%s=%s; expires=%s", this.f12a, this.f13b, this.c);
        }

        public e(String str, String str2, int i) {
            this.f12a = str;
            this.f13b = str2;
            this.c = b(i);
        }

        public e(String str, String str2, String str3) {
            this.f12a = str;
            this.f13b = str2;
            this.c = str3;
        }
    }

    public class f implements Iterable<String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final HashMap<String, String> f14a = new HashMap<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final ArrayList<e> f15b = new ArrayList<>();

        public f(Map<String, String> map) {
            String str = map.get("cookie");
            if (str != null) {
                for (String str2 : str.split(";")) {
                    String[] strArrSplit = str2.trim().split("=");
                    if (strArrSplit.length == 2) {
                        this.f14a.put(strArrSplit[0], strArrSplit[1]);
                    }
                }
            }
        }

        public void a(String str) {
            d(str, "-delete-", -30);
        }

        public String b(String str) {
            return this.f14a.get(str);
        }

        public void c(e eVar) {
            this.f15b.add(eVar);
        }

        public void d(String str, String str2, int i) {
            this.f15b.add(new e(str, str2, e.b(i)));
        }

        public void e(o oVar) {
            Iterator<e> it = this.f15b.iterator();
            while (it.hasNext()) {
                oVar.b("Set-Cookie", it.next().a());
            }
        }

        @Override // java.lang.Iterable
        public Iterator<String> iterator() {
            return this.f14a.keySet().iterator();
        }
    }

    public static class g implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private long f16a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<c> f17b = Collections.synchronizedList(new ArrayList());

        @Override // com.cloudmedia.tv.server.a.b
        public void a(c cVar) {
            this.f16a++;
            Thread thread = new Thread(cVar);
            thread.setDaemon(true);
            thread.setName("NanoHttpd Request Processor (#" + this.f16a + ")");
            this.f17b.add(cVar);
            thread.start();
        }

        @Override // com.cloudmedia.tv.server.a.b
        public void b(c cVar) {
            this.f17b.remove(cVar);
        }

        @Override // com.cloudmedia.tv.server.a.b
        public void c() {
            Iterator it = new ArrayList(this.f17b).iterator();
            while (it.hasNext()) {
                ((c) it.next()).a();
            }
        }

        public List<c> d() {
            return this.f17b;
        }
    }

    public static class h implements s {
        @Override // com.cloudmedia.tv.server.a.s
        public ServerSocket a() throws IOException {
            return new ServerSocket();
        }
    }

    public static class i implements t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final File f18a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final OutputStream f19b;

        public i(File file) throws IOException {
            File fileCreateTempFile = File.createTempFile("NanoHTTPD-", "", file);
            this.f18a = fileCreateTempFile;
            this.f19b = new FileOutputStream(fileCreateTempFile);
        }

        @Override // com.cloudmedia.tv.server.a.t
        public void a() throws Exception {
            a.E(this.f19b);
            if (this.f18a.delete()) {
                return;
            }
            throw new Exception("could not delete temporary file: " + this.f18a.getAbsolutePath());
        }

        @Override // com.cloudmedia.tv.server.a.t
        public OutputStream b() throws Exception {
            return this.f19b;
        }

        @Override // com.cloudmedia.tv.server.a.t
        public String c() {
            return this.f18a.getAbsolutePath();
        }
    }

    public static class j implements u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final File f20a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<t> f21b;

        public j() {
            File file = new File(System.getProperty("java.io.tmpdir"));
            this.f20a = file;
            if (!file.exists()) {
                file.mkdirs();
            }
            this.f21b = new ArrayList();
        }

        @Override // com.cloudmedia.tv.server.a.u
        public t a(String str) throws Exception {
            i iVar = new i(this.f20a);
            this.f21b.add(iVar);
            return iVar;
        }

        @Override // com.cloudmedia.tv.server.a.u
        public void clear() {
            Iterator<t> it = this.f21b.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a();
                } catch (Exception e) {
                    a.q.log(Level.WARNING, "could not delete file ", (Throwable) e);
                }
            }
            this.f21b.clear();
        }
    }

    private class k implements v {
        private k() {
        }

        @Override // com.cloudmedia.tv.server.a.v
        public u a() {
            return new j();
        }
    }

    protected class l implements m {
        private static final int p = 512;
        private static final int q = 1024;
        public static final int r = 8192;
        public static final int s = 1024;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final u f23a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final OutputStream f24b;
        private final BufferedInputStream c;
        private int d;
        private int e;
        private String f;
        private n g;
        private Map<String, List<String>> h;
        private Map<String, String> i;
        private f j;
        private String k;
        private String l;
        private String m;
        private String n;

        public l(u uVar, InputStream inputStream, OutputStream outputStream) {
            this.f23a = uVar;
            this.c = new BufferedInputStream(inputStream, r);
            this.f24b = outputStream;
        }

        private void m(BufferedReader bufferedReader, Map<String, String> map, Map<String, List<String>> map2, Map<String, String> map3) throws p {
            String strN;
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                if (!stringTokenizer.hasMoreTokens()) {
                    throw new p(o.d.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                }
                map.put("method", stringTokenizer.nextToken());
                if (!stringTokenizer.hasMoreTokens()) {
                    throw new p(o.d.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                }
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(63);
                if (iIndexOf >= 0) {
                    o(strNextToken.substring(iIndexOf + 1), map2);
                    strN = a.n(strNextToken.substring(0, iIndexOf));
                } else {
                    strN = a.n(strNextToken);
                }
                if (stringTokenizer.hasMoreTokens()) {
                    this.n = stringTokenizer.nextToken();
                } else {
                    this.n = "HTTP/1.1";
                    a.q.log(Level.FINE, "no protocol version specified, strange. Assuming HTTP/1.1.");
                }
                while (true) {
                    String line2 = bufferedReader.readLine();
                    if (line2 == null || line2.trim().isEmpty()) {
                        break;
                    }
                    int iIndexOf2 = line2.indexOf(58);
                    if (iIndexOf2 >= 0) {
                        map3.put(line2.substring(0, iIndexOf2).trim().toLowerCase(Locale.US), line2.substring(iIndexOf2 + 1).trim());
                    }
                }
                map.put("uri", strN);
            } catch (IOException e) {
                throw new p(o.d.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException: " + e.getMessage(), e);
            }
        }

        private void n(d dVar, ByteBuffer byteBuffer, Map<String, List<String>> map, Map<String, String> map2) throws Throwable {
            String strGroup;
            try {
                int[] iArrR = r(byteBuffer, dVar.a().getBytes());
                int i = 2;
                if (iArrR.length < 2) {
                    throw new p(o.d.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but contains less than two boundary strings.");
                }
                int i2 = 1024;
                byte[] bArr = new byte[1024];
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    int i6 = 1;
                    if (i4 >= iArrR.length - 1) {
                        return;
                    }
                    byteBuffer.position(iArrR[i4]);
                    int iRemaining = byteBuffer.remaining() < i2 ? byteBuffer.remaining() : 1024;
                    byteBuffer.get(bArr, i3, iRemaining);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, i3, iRemaining), Charset.forName(dVar.e())), iRemaining);
                    String line = bufferedReader.readLine();
                    if (line == null || !line.contains(dVar.a())) {
                        throw new p(o.d.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
                    }
                    String line2 = bufferedReader.readLine();
                    String str = null;
                    String str2 = null;
                    String strTrim = null;
                    int i7 = 2;
                    while (line2 != null && line2.trim().length() > 0) {
                        Matcher matcher = a.h.matcher(line2);
                        if (matcher.matches()) {
                            Matcher matcher2 = a.l.matcher(matcher.group(i));
                            while (matcher2.find()) {
                                String strGroup2 = matcher2.group(i6);
                                if ("name".equalsIgnoreCase(strGroup2)) {
                                    strGroup = matcher2.group(2);
                                } else {
                                    if ("filename".equalsIgnoreCase(strGroup2)) {
                                        String strGroup3 = matcher2.group(2);
                                        if (!strGroup3.isEmpty()) {
                                            if (i5 > 0) {
                                                strGroup = str + String.valueOf(i5);
                                                str2 = strGroup3;
                                                i5++;
                                            } else {
                                                i5++;
                                            }
                                        }
                                        str2 = strGroup3;
                                    }
                                    i6 = 1;
                                }
                                str = strGroup;
                                i6 = 1;
                            }
                        }
                        Matcher matcher3 = a.j.matcher(line2);
                        if (matcher3.matches()) {
                            strTrim = matcher3.group(2).trim();
                        }
                        line2 = bufferedReader.readLine();
                        i7++;
                        i = 2;
                        i6 = 1;
                    }
                    int iU = 0;
                    while (true) {
                        int i8 = i7 - 1;
                        if (i7 <= 0) {
                            break;
                        }
                        iU = u(bArr, iU);
                        i7 = i8;
                    }
                    if (iU >= iRemaining - 4) {
                        throw new p(o.d.INTERNAL_ERROR, "Multipart header size exceeds MAX_HEADER_SIZE.");
                    }
                    int i9 = iArrR[i4] + iU;
                    i4++;
                    int i10 = iArrR[i4] - 4;
                    byteBuffer.position(i9);
                    List<String> arrayList = map.get(str);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                        map.put(str, arrayList);
                    }
                    int i11 = i10 - i9;
                    if (strTrim == null) {
                        byte[] bArr2 = new byte[i11];
                        byteBuffer.get(bArr2);
                        arrayList.add(new String(bArr2, dVar.e()));
                    } else {
                        String strT = t(byteBuffer, i9, i11, str2);
                        if (map2.containsKey(str)) {
                            int i12 = 2;
                            while (true) {
                                if (!map2.containsKey(str + i12)) {
                                    break;
                                } else {
                                    i12++;
                                }
                            }
                            map2.put(str + i12, strT);
                        } else {
                            map2.put(str, strT);
                        }
                        arrayList.add(str2);
                    }
                    i2 = 1024;
                    i = 2;
                    i3 = 0;
                }
            } catch (p e) {
                throw e;
            } catch (Exception e2) {
                throw new p(o.d.INTERNAL_ERROR, e2.toString());
            }
        }

        private void o(String str, Map<String, List<String>> map) {
            String strTrim;
            String strN;
            if (str == null) {
                this.k = "";
                return;
            }
            this.k = str;
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(61);
                if (iIndexOf >= 0) {
                    strTrim = a.n(strNextToken.substring(0, iIndexOf)).trim();
                    strN = a.n(strNextToken.substring(iIndexOf + 1));
                } else {
                    strTrim = a.n(strNextToken).trim();
                    strN = "";
                }
                List<String> arrayList = map.get(strTrim);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    map.put(strTrim, arrayList);
                }
                arrayList.add(strN);
            }
        }

        private int p(byte[] bArr, int i) {
            int i2;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i4 >= i) {
                    return 0;
                }
                byte b2 = bArr[i3];
                if (b2 == 13 && bArr[i4] == 10 && (i2 = i3 + 3) < i && bArr[i3 + 2] == 13 && bArr[i2] == 10) {
                    return i3 + 4;
                }
                if (b2 == 10 && bArr[i4] == 10) {
                    return i3 + 2;
                }
                i3 = i4;
            }
        }

        private int[] r(ByteBuffer byteBuffer, byte[] bArr) {
            int[] iArr = new int[0];
            if (byteBuffer.remaining() < bArr.length) {
                return iArr;
            }
            int length = bArr.length + 4096;
            byte[] bArr2 = new byte[length];
            int iRemaining = byteBuffer.remaining() < length ? byteBuffer.remaining() : length;
            byteBuffer.get(bArr2, 0, iRemaining);
            int length2 = iRemaining - bArr.length;
            int i = 0;
            do {
                for (int i2 = 0; i2 < length2; i2++) {
                    for (int i3 = 0; i3 < bArr.length && bArr2[i2 + i3] == bArr[i3]; i3++) {
                        if (i3 == bArr.length - 1) {
                            int[] iArr2 = new int[iArr.length + 1];
                            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                            iArr2[iArr.length] = i + i2;
                            iArr = iArr2;
                        }
                    }
                }
                i += length2;
                System.arraycopy(bArr2, length - bArr.length, bArr2, 0, bArr.length);
                length2 = length - bArr.length;
                if (byteBuffer.remaining() < length2) {
                    length2 = byteBuffer.remaining();
                }
                byteBuffer.get(bArr2, bArr.length, length2);
            } while (length2 > 0);
            return iArr;
        }

        private RandomAccessFile s() {
            try {
                return new RandomAccessFile(this.f23a.a(null).c(), "rw");
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        private String t(ByteBuffer byteBuffer, int i, int i2, String str) throws Throwable {
            if (i2 <= 0) {
                return "";
            }
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    t tVarA = this.f23a.a(str);
                    ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(tVarA.c());
                    try {
                        FileChannel channel = fileOutputStream2.getChannel();
                        byteBufferDuplicate.position(i).limit(i + i2);
                        channel.write(byteBufferDuplicate.slice());
                        String strC = tVarA.c();
                        a.E(fileOutputStream2);
                        return strC;
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        throw new Error(e);
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        a.E(fileOutputStream);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        private int u(byte[] bArr, int i) {
            byte b2;
            do {
                b2 = bArr[i];
                i++;
            } while (b2 != 10);
            return i;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public void a() throws IOException {
            OutputStream outputStream;
            o oVarF = null;
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    byte[] bArr = new byte[r];
                                    boolean z = false;
                                    this.d = 0;
                                    this.e = 0;
                                    this.c.mark(r);
                                    try {
                                        int i = this.c.read(bArr, 0, r);
                                        if (i == -1) {
                                            a.E(this.c);
                                            a.E(this.f24b);
                                            throw new SocketException("NanoHttpd Shutdown");
                                        }
                                        while (i > 0) {
                                            int i2 = this.e + i;
                                            this.e = i2;
                                            int iP = p(bArr, i2);
                                            this.d = iP;
                                            if (iP > 0) {
                                                break;
                                            }
                                            BufferedInputStream bufferedInputStream = this.c;
                                            int i3 = this.e;
                                            i = bufferedInputStream.read(bArr, i3, 8192 - i3);
                                        }
                                        if (this.d < this.e) {
                                            this.c.reset();
                                            this.c.skip(this.d);
                                        }
                                        this.h = new HashMap();
                                        Map<String, String> map = this.i;
                                        if (map == null) {
                                            this.i = new HashMap();
                                        } else {
                                            map.clear();
                                        }
                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, this.e)));
                                        HashMap map2 = new HashMap();
                                        m(bufferedReader, map2, this.h, this.i);
                                        String str = this.l;
                                        if (str != null) {
                                            this.i.put("remote-addr", str);
                                            this.i.put("http-client-ip", this.l);
                                        }
                                        n nVarB = n.b(map2.get("method"));
                                        this.g = nVarB;
                                        if (nVarB == null) {
                                            throw new p(o.d.BAD_REQUEST, "BAD REQUEST: Syntax error. HTTP verb " + map2.get("method") + " unhandled.");
                                        }
                                        this.f = map2.get("uri");
                                        this.j = a.this.new f(this.i);
                                        String str2 = this.i.get("connection");
                                        boolean z2 = "HTTP/1.1".equals(this.n) && (str2 == null || !str2.matches("(?i).*close.*"));
                                        oVarF = a.this.F(this);
                                        if (oVarF == null) {
                                            throw new p(o.d.INTERNAL_ERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                                        }
                                        String str3 = this.i.get("accept-encoding");
                                        this.j.e(oVarF);
                                        oVarF.u(this.g);
                                        if (a.this.O(oVarF) && str3 != null && str3.contains("gzip")) {
                                            z = true;
                                        }
                                        oVarF.r(z);
                                        oVarF.s(z2);
                                        oVarF.k(this.f24b);
                                        if (!z2 || oVarF.i()) {
                                            throw new SocketException("NanoHttpd Shutdown");
                                        }
                                        a.E(oVarF);
                                        this.f23a.clear();
                                    } catch (SSLException e) {
                                        throw e;
                                    } catch (IOException unused) {
                                        a.E(this.c);
                                        a.E(this.f24b);
                                        throw new SocketException("NanoHttpd Shutdown");
                                    }
                                } catch (SocketTimeoutException e2) {
                                    throw e2;
                                }
                            } catch (SSLException e3) {
                                a.C(o.d.INTERNAL_ERROR, a.n, "SSL PROTOCOL FAILURE: " + e3.getMessage()).k(this.f24b);
                                outputStream = this.f24b;
                                a.E(outputStream);
                            }
                        } catch (IOException e4) {
                            a.C(o.d.INTERNAL_ERROR, a.n, "SERVER INTERNAL ERROR: IOException: " + e4.getMessage()).k(this.f24b);
                            outputStream = this.f24b;
                            a.E(outputStream);
                        }
                    } catch (SocketException e5) {
                        throw e5;
                    }
                } catch (p e6) {
                    a.C(e6.a(), a.n, e6.getMessage()).k(this.f24b);
                    outputStream = this.f24b;
                    a.E(outputStream);
                }
            } catch (Throwable th) {
                a.E(null);
                this.f23a.clear();
                throw th;
            }
        }

        @Override // com.cloudmedia.tv.server.a.m
        public final Map<String, List<String>> b() {
            return this.h;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public final n c() {
            return this.g;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public f d() {
            return this.j;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public void e(Map<String, String> map) throws Throwable {
            RandomAccessFile randomAccessFileS;
            ByteArrayOutputStream byteArrayOutputStream;
            DataOutput dataOutputStream;
            ByteBuffer map2;
            RandomAccessFile randomAccessFile = null;
            try {
                long jQ = q();
                if (jQ < 1024) {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    randomAccessFileS = null;
                } else {
                    randomAccessFileS = s();
                    byteArrayOutputStream = null;
                    dataOutputStream = randomAccessFileS;
                }
                try {
                    byte[] bArr = new byte[p];
                    while (this.e >= 0 && jQ > 0) {
                        int i = this.c.read(bArr, 0, (int) Math.min(jQ, 512L));
                        this.e = i;
                        jQ -= (long) i;
                        if (i > 0) {
                            dataOutputStream.write(bArr, 0, i);
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        map2 = ByteBuffer.wrap(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
                    } else {
                        map2 = randomAccessFileS.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFileS.length());
                        randomAccessFileS.seek(0L);
                    }
                    if (n.POST.equals(this.g)) {
                        d dVar = new d(this.i.get("content-type"));
                        if (!dVar.f()) {
                            byte[] bArr2 = new byte[map2.remaining()];
                            map2.get(bArr2);
                            String strTrim = new String(bArr2, dVar.e()).trim();
                            if ("application/x-www-form-urlencoded".equalsIgnoreCase(dVar.b())) {
                                o(strTrim, this.h);
                            } else if (strTrim.length() != 0) {
                                map.put("postData", strTrim);
                            }
                        } else {
                            if (dVar.a() == null) {
                                throw new p(o.d.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                            }
                            n(dVar, map2, this.h, map);
                        }
                    } else if (n.PUT.equals(this.g)) {
                        map.put("content", t(map2, 0, map2.limit(), null));
                    }
                    a.E(randomAccessFileS);
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFileS;
                    a.E(randomAccessFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        @Override // com.cloudmedia.tv.server.a.m
        public final InputStream f() {
            return this.c;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public String g() {
            return this.m;
        }

        @Override // com.cloudmedia.tv.server.a.m
        @Deprecated
        public final Map<String, String> h() {
            HashMap map = new HashMap();
            for (String str : this.h.keySet()) {
                map.put(str, this.h.get(str).get(0));
            }
            return map;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public final String i() {
            return this.f;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public String j() {
            return this.k;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public final Map<String, String> k() {
            return this.i;
        }

        @Override // com.cloudmedia.tv.server.a.m
        public String l() {
            return this.l;
        }

        public long q() {
            if (this.i.containsKey("content-length")) {
                return Long.parseLong(this.i.get("content-length"));
            }
            int i = this.d;
            int i2 = this.e;
            if (i < i2) {
                return i2 - i;
            }
            return 0L;
        }

        public l(u uVar, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
            this.f23a = uVar;
            this.c = new BufferedInputStream(inputStream, r);
            this.f24b = outputStream;
            this.l = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "127.0.0.1" : inetAddress.getHostAddress().toString();
            this.m = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "localhost" : inetAddress.getHostName().toString();
            this.i = new HashMap();
        }
    }

    public interface m {
        void a() throws IOException;

        Map<String, List<String>> b();

        n c();

        f d();

        void e(Map<String, String> map) throws p, IOException;

        InputStream f();

        String g();

        @Deprecated
        Map<String, String> h();

        String i();

        String j();

        Map<String, String> k();

        String l();
    }

    public enum n {
        GET,
        PUT,
        POST,
        DELETE,
        HEAD,
        OPTIONS,
        TRACE,
        CONNECT,
        PATCH,
        PROPFIND,
        PROPPATCH,
        MKCOL,
        MOVE,
        COPY,
        LOCK,
        UNLOCK;

        static n b(String str) {
            if (str == null) {
                return null;
            }
            try {
                return valueOf(str);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    public static class o implements Closeable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private c f27a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f28b;
        private InputStream c;
        private long d;
        private final Map<String, String> e = new C0004a();
        private final Map<String, String> f = new HashMap();
        private n g;
        private boolean h;
        private boolean i;
        private boolean j;

        /* JADX INFO: renamed from: com.cloudmedia.tv.server.a$o$a, reason: collision with other inner class name */
        class C0004a extends HashMap<String, String> {
            C0004a() {
            }

            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public String put(String str, String str2) {
                o.this.f.put(str == null ? str : str.toLowerCase(), str2);
                return (String) super.put(str, str2);
            }
        }

        private static class b extends FilterOutputStream {
            public b(OutputStream outputStream) {
                super(outputStream);
            }

            public void a() throws IOException {
                ((FilterOutputStream) this).out.write("0\r\n\r\n".getBytes());
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) throws IOException {
                write(new byte[]{(byte) i}, 0, 1);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr) throws IOException {
                write(bArr, 0, bArr.length);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                if (i2 == 0) {
                    return;
                }
                ((FilterOutputStream) this).out.write(String.format("%x\r\n", Integer.valueOf(i2)).getBytes());
                ((FilterOutputStream) this).out.write(bArr, i, i2);
                ((FilterOutputStream) this).out.write("\r\n".getBytes());
            }
        }

        public interface c {
            String a();

            int b();
        }

        public enum d implements c {
            SWITCH_PROTOCOL(101, "Switching Protocols"),
            OK(200, "OK"),
            CREATED(201, "Created"),
            ACCEPTED(202, "Accepted"),
            NO_CONTENT(204, "No Content"),
            PARTIAL_CONTENT(206, "Partial Content"),
            MULTI_STATUS(207, "Multi-Status"),
            REDIRECT(301, "Moved Permanently"),
            FOUND(302, "Found"),
            REDIRECT_SEE_OTHER(303, "See Other"),
            NOT_MODIFIED(304, "Not Modified"),
            TEMPORARY_REDIRECT(307, "Temporary Redirect"),
            BAD_REQUEST(400, "Bad Request"),
            UNAUTHORIZED(401, "Unauthorized"),
            FORBIDDEN(403, "Forbidden"),
            NOT_FOUND(404, "Not Found"),
            METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
            NOT_ACCEPTABLE(406, "Not Acceptable"),
            REQUEST_TIMEOUT(408, "Request Timeout"),
            CONFLICT(409, "Conflict"),
            GONE(410, "Gone"),
            LENGTH_REQUIRED(411, "Length Required"),
            PRECONDITION_FAILED(412, "Precondition Failed"),
            PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
            UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
            RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
            EXPECTATION_FAILED(417, "Expectation Failed"),
            TOO_MANY_REQUESTS(429, "Too Many Requests"),
            INTERNAL_ERROR(500, "Internal Server Error"),
            NOT_IMPLEMENTED(501, "Not Implemented"),
            SERVICE_UNAVAILABLE(503, "Service Unavailable"),
            UNSUPPORTED_HTTP_VERSION(505, "HTTP Version Not Supported");


            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final int f30a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private final String f31b;

            d(int i, String str) {
                this.f30a = i;
                this.f31b = str;
            }

            public static d d(int i) {
                for (d dVar : values()) {
                    if (dVar.b() == i) {
                        return dVar;
                    }
                }
                return null;
            }

            @Override // com.cloudmedia.tv.server.a.o.c
            public String a() {
                return "" + this.f30a + " " + this.f31b;
            }

            @Override // com.cloudmedia.tv.server.a.o.c
            public int b() {
                return this.f30a;
            }
        }

        protected o(c cVar, String str, InputStream inputStream, long j) {
            this.f27a = cVar;
            this.f28b = str;
            if (inputStream == null) {
                this.c = new ByteArrayInputStream(new byte[0]);
                this.d = 0L;
            } else {
                this.c = inputStream;
                this.d = j;
            }
            this.h = this.d < 0;
            this.j = true;
        }

        private void l(OutputStream outputStream, long j) throws IOException {
            byte[] bArr = new byte[(int) 16384];
            boolean z = j == -1;
            while (true) {
                if (j <= 0 && !z) {
                    return;
                }
                int i = this.c.read(bArr, 0, (int) (z ? 16384L : Math.min(j, 16384L)));
                if (i <= 0) {
                    return;
                }
                outputStream.write(bArr, 0, i);
                if (!z) {
                    j -= (long) i;
                }
            }
        }

        private void m(OutputStream outputStream, long j) throws IOException {
            if (!this.i) {
                l(outputStream, j);
                return;
            }
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
            l(gZIPOutputStream, -1L);
            gZIPOutputStream.finish();
        }

        private void n(OutputStream outputStream, long j) throws IOException {
            if (this.g == n.HEAD || !this.h) {
                m(outputStream, j);
                return;
            }
            b bVar = new b(outputStream);
            m(bVar, -1L);
            bVar.a();
        }

        public void b(String str, String str2) {
            this.e.put(str, str2);
        }

        public void c(boolean z) {
            if (z) {
                this.e.put("connection", "close");
            } else {
                this.e.remove("connection");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            InputStream inputStream = this.c;
            if (inputStream != null) {
                inputStream.close();
            }
        }

        public InputStream d() {
            return this.c;
        }

        public String e(String str) {
            return this.f.get(str.toLowerCase());
        }

        public String f() {
            return this.f28b;
        }

        public n g() {
            return this.g;
        }

        public c h() {
            return this.f27a;
        }

        public boolean i() {
            return "close".equals(e("connection"));
        }

        protected void j(PrintWriter printWriter, String str, String str2) {
            printWriter.append((CharSequence) str).append(": ").append((CharSequence) str2).append("\r\n");
        }

        protected void k(OutputStream outputStream) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                if (this.f27a == null) {
                    throw new Error("sendResponse(): Status can't be null.");
                }
                PrintWriter printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, new d(this.f28b).e())), false);
                printWriter.append("HTTP/1.1 ").append((CharSequence) this.f27a.a()).append(" \r\n");
                String str = this.f28b;
                if (str != null) {
                    j(printWriter, HTTPHelper.HEADER_CONTENT_TYPE, str);
                }
                if (e("date") == null) {
                    j(printWriter, "Date", simpleDateFormat.format(new Date()));
                }
                for (Map.Entry<String, String> entry : this.e.entrySet()) {
                    j(printWriter, entry.getKey(), entry.getValue());
                }
                if (e("connection") == null) {
                    j(printWriter, "Connection", this.j ? "keep-alive" : "close");
                }
                if (e("content-length") != null) {
                    this.i = false;
                }
                if (this.i) {
                    j(printWriter, "Content-Encoding", "gzip");
                    p(true);
                }
                long jO = this.c != null ? this.d : 0L;
                if (this.g != n.HEAD && this.h) {
                    j(printWriter, "Transfer-Encoding", "chunked");
                } else if (!this.i) {
                    jO = o(printWriter, jO);
                }
                printWriter.append("\r\n");
                printWriter.flush();
                n(outputStream, jO);
                outputStream.flush();
                a.E(this.c);
            } catch (IOException e) {
                a.q.log(Level.SEVERE, "Could not send response to the client", (Throwable) e);
            }
        }

        protected long o(PrintWriter printWriter, long j) {
            String strE = e("content-length");
            if (strE != null) {
                try {
                    j = Long.parseLong(strE);
                } catch (NumberFormatException unused) {
                    a.q.severe("content-length was no number " + strE);
                }
            }
            printWriter.print("Content-Length: " + j + "\r\n");
            return j;
        }

        public void p(boolean z) {
            this.h = z;
        }

        public void q(InputStream inputStream) {
            this.c = inputStream;
        }

        public void r(boolean z) {
            this.i = z;
        }

        public void s(boolean z) {
            this.j = z;
        }

        public void t(String str) {
            this.f28b = str;
        }

        public void u(n nVar) {
            this.g = nVar;
        }

        public void v(c cVar) {
            this.f27a = cVar;
        }
    }

    public static final class p extends Exception {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final long f32b = 6569838532917408380L;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final o.d f33a;

        public p(o.d dVar, String str) {
            super(str);
            this.f33a = dVar;
        }

        public o.d a() {
            return this.f33a;
        }

        public p(o.d dVar, String str, Exception exc) {
            super(str, exc);
            this.f33a = dVar;
        }
    }

    public static class q implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private SSLServerSocketFactory f34a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String[] f35b;

        public q(SSLServerSocketFactory sSLServerSocketFactory, String[] strArr) {
            this.f34a = sSLServerSocketFactory;
            this.f35b = strArr;
        }

        @Override // com.cloudmedia.tv.server.a.s
        public ServerSocket a() throws IOException {
            SSLServerSocket sSLServerSocket = (SSLServerSocket) this.f34a.createServerSocket();
            String[] strArr = this.f35b;
            if (strArr != null) {
                sSLServerSocket.setEnabledProtocols(strArr);
            } else {
                sSLServerSocket.setEnabledProtocols(sSLServerSocket.getSupportedProtocols());
            }
            sSLServerSocket.setUseClientMode(false);
            sSLServerSocket.setWantClientAuth(false);
            sSLServerSocket.setNeedClientAuth(false);
            return sSLServerSocket;
        }
    }

    public class r implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f36a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private IOException f37b;
        private boolean c = false;

        public r(int i) {
            this.f36a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.this.f7b.bind(a.this.f6a != null ? new InetSocketAddress(a.this.f6a, a.s) : new InetSocketAddress(a.s));
                this.c = true;
                do {
                    try {
                        Socket socketAccept = a.this.f7b.accept();
                        int i = this.f36a;
                        if (i > 0) {
                            socketAccept.setSoTimeout(i);
                        }
                        InputStream inputStream = socketAccept.getInputStream();
                        a aVar = a.this;
                        aVar.e.a(aVar.j(socketAccept, inputStream));
                    } catch (IOException e) {
                        a.q.log(Level.FINE, "Communication with the client broken", (Throwable) e);
                    }
                } while (!a.this.f7b.isClosed());
            } catch (IOException e2) {
                this.f37b = e2;
            }
        }
    }

    public interface s {
        ServerSocket a() throws IOException;
    }

    public interface t {
        void a() throws Exception;

        OutputStream b() throws Exception;

        String c();
    }

    public interface u {
        t a(String str) throws Exception;

        void clear();
    }

    public interface v {
        u a();
    }

    public a(int i2) {
        this(null, i2);
    }

    public static o A(o.c cVar, String str, InputStream inputStream) {
        return new o(cVar, str, inputStream, -1L);
    }

    public static o B(o.c cVar, String str, InputStream inputStream, long j2) {
        return new o(cVar, str, inputStream, j2);
    }

    public static o C(o.c cVar, String str, String str2) {
        byte[] bytes;
        d dVar = new d(str);
        if (str2 == null) {
            return B(cVar, str, new ByteArrayInputStream(new byte[0]), 0L);
        }
        try {
            if (!Charset.forName(dVar.e()).newEncoder().canEncode(str2)) {
                dVar = dVar.g();
            }
            bytes = str2.getBytes(dVar.e());
        } catch (UnsupportedEncodingException e2) {
            q.log(Level.SEVERE, "encoding problem, responding nothing", (Throwable) e2);
            bytes = new byte[0];
        }
        return B(cVar, dVar.c(), new ByteArrayInputStream(bytes), bytes.length);
    }

    public static o D(String str) {
        return C(o.d.OK, o, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void E(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else if (obj instanceof Socket) {
                    ((Socket) obj).close();
                } else {
                    if (!(obj instanceof ServerSocket)) {
                        throw new IllegalArgumentException("Unknown object to close");
                    }
                    ((ServerSocket) obj).close();
                }
            } catch (IOException e2) {
                q.log(Level.SEVERE, "Could not close", (Throwable) e2);
            }
        }
    }

    protected static Map<String, List<String>> l(String str) {
        HashMap map = new HashMap();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(61);
                String strTrim = (iIndexOf >= 0 ? n(strNextToken.substring(0, iIndexOf)) : n(strNextToken)).trim();
                if (!map.containsKey(strTrim)) {
                    map.put(strTrim, new ArrayList());
                }
                String strN = iIndexOf >= 0 ? n(strNextToken.substring(iIndexOf + 1)) : null;
                if (strN != null) {
                    ((List) map.get(strTrim)).add(strN);
                }
            }
        }
        return map;
    }

    protected static Map<String, List<String>> m(Map<String, String> map) {
        return l(map.get(p));
    }

    protected static String n(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException e2) {
            q.log(Level.WARNING, "Encoding not supported, ignored", (Throwable) e2);
            return null;
        }
    }

    public static String q(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        String str2 = iLastIndexOf >= 0 ? z().get(str.substring(iLastIndexOf + 1).toLowerCase()) : null;
        return str2 == null ? HTTPHelper.CONTENT_TYPE_BINARY : str2;
    }

    private static void u(Map<String, String> map, String str) {
        try {
            Enumeration<URL> resources = a.class.getClassLoader().getResources(str);
            while (resources.hasMoreElements()) {
                URL urlNextElement = resources.nextElement();
                Properties properties = new Properties();
                InputStream inputStreamOpenStream = null;
                try {
                    try {
                        inputStreamOpenStream = urlNextElement.openStream();
                        properties.load(inputStreamOpenStream);
                    } catch (IOException e2) {
                        q.log(Level.SEVERE, "could not load mimetypes from " + urlNextElement, (Throwable) e2);
                    }
                    E(inputStreamOpenStream);
                    map.putAll(properties);
                } catch (Throwable th) {
                    E(inputStreamOpenStream);
                    throw th;
                }
            }
        } catch (IOException unused) {
            q.log(Level.INFO, "no mime types available at " + str);
        }
    }

    public static SSLServerSocketFactory v(String str, char[] cArr) throws IOException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream resourceAsStream = a.class.getResourceAsStream(str);
            if (resourceAsStream != null) {
                keyStore.load(resourceAsStream, cArr);
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, cArr);
                return w(keyStore, keyManagerFactory);
            }
            throw new IOException("Unable to load keystore from classpath: " + str);
        } catch (Exception e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public static SSLServerSocketFactory w(KeyStore keyStore, KeyManagerFactory keyManagerFactory) throws IOException {
        try {
            return x(keyStore, keyManagerFactory.getKeyManagers());
        } catch (Exception e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public static SSLServerSocketFactory x(KeyStore keyStore, KeyManager[] keyManagerArr) throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(keyManagerArr, trustManagerFactory.getTrustManagers(), null);
            return sSLContext.getServerSocketFactory();
        } catch (Exception e2) {
            throw new IOException(e2.getMessage());
        }
    }

    public static Map<String, String> z() {
        if (r == null) {
            HashMap map = new HashMap();
            r = map;
            u(map, "META-INF/nanohttpd/default-mimetypes.properties");
            u(r, "META-INF/nanohttpd/mimetypes.properties");
            if (r.isEmpty()) {
                q.log(Level.WARNING, "no mime types found in the classpath! please provide mimetypes.properties");
            }
        }
        return r;
    }

    public o F(m mVar) {
        HashMap map = new HashMap();
        n nVarC = mVar.c();
        if (n.PUT.equals(nVarC) || n.POST.equals(nVarC)) {
            try {
                mVar.e(map);
            } catch (p e2) {
                return C(e2.a(), n, e2.getMessage());
            } catch (IOException e3) {
                return C(o.d.INTERNAL_ERROR, n, "SERVER INTERNAL ERROR: IOException: " + e3.getMessage());
            }
        }
        Map<String, String> mapH = mVar.h();
        mapH.put(p, mVar.j());
        return G(mVar.i(), nVarC, mVar.k(), mapH, map);
    }

    @Deprecated
    public o G(String str, n nVar, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        return C(o.d.NOT_FOUND, n, "Not Found");
    }

    public void H(b bVar) {
        this.e = bVar;
    }

    public void I(s sVar) {
        this.c = sVar;
    }

    public void J(v vVar) {
        this.f = vVar;
    }

    public void K() throws IOException {
        L(m);
    }

    public void L(int i2) throws IOException {
        M(i2, true);
    }

    public void M(int i2, boolean z) throws IOException {
        this.f7b = r().a();
        this.f7b.setReuseAddress(true);
        r rVarK = k(i2);
        Thread thread = new Thread(rVarK);
        this.d = thread;
        thread.setDaemon(z);
        this.d.setName("NanoHttpd Listener");
        this.d.start();
        while (!rVarK.c && rVarK.f37b == null) {
            try {
                Thread.sleep(10L);
            } catch (Throwable unused) {
            }
        }
        if (rVarK.f37b != null) {
            throw rVarK.f37b;
        }
    }

    public void N() {
        try {
            E(this.f7b);
            this.e.c();
            Thread thread = this.d;
            if (thread != null) {
                thread.join();
            }
        } catch (Exception e2) {
            q.log(Level.SEVERE, "Could not stop all connections", (Throwable) e2);
        }
    }

    protected boolean O(o oVar) {
        return oVar.f() != null && oVar.f().toLowerCase().contains("text/");
    }

    public final boolean P() {
        return (this.f7b == null || this.d == null) ? false : true;
    }

    public synchronized void i() {
        N();
    }

    protected c j(Socket socket, InputStream inputStream) {
        return new c(inputStream, socket);
    }

    protected r k(int i2) {
        return new r(i2);
    }

    public String o() {
        return this.f6a;
    }

    public final int p() {
        if (this.f7b == null) {
            return -1;
        }
        return this.f7b.getLocalPort();
    }

    public s r() {
        return this.c;
    }

    public v s() {
        return this.f;
    }

    public final boolean t() {
        return P() && !this.f7b.isClosed() && this.d.isAlive();
    }

    public void y(SSLServerSocketFactory sSLServerSocketFactory, String[] strArr) {
        this.c = new q(sSLServerSocketFactory, strArr);
    }

    public a(String str, int i2) {
        this.c = new h();
        this.f6a = str;
        s = i2;
        J(new k());
        H(new g());
    }
}
