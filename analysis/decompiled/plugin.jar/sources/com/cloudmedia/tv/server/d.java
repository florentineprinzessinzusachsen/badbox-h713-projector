package com.cloudmedia.tv.server;

import android.text.TextUtils;
import android.util.Log;
import com.anlytics.plug.ParserUtils;
import com.hs.p.common.http.HTTPHelper;
import com.tools.f;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class d extends a {
    private static final Logger t = Logger.getLogger(d.class.getName());
    private static String u = "SimpleWebServer_setDataSource";

    public d() {
        super(a.s);
    }

    public static void R() {
        b.b(d.class);
    }

    @Override // com.cloudmedia.tv.server.a
    public a.o F(a.m mVar) {
        String strI = mVar.i();
        String strJ = mVar.j();
        String str = mVar.k().get("host");
        if ("/live/aiqiyi/vod.m3u8".equals(strI)) {
            f.g().A(strJ);
            return S(str, strI, f.g().f53a);
        }
        if ("/live/aiqiyi127/vod.m3u8".equals(strI)) {
            f.g().A(strJ);
            return S(str, strI, f.g().f53a);
        }
        if ("/live/custom/vod.m3u8".equals(strI)) {
            f.g().A(strJ);
            return S(str, strI, f.g().f53a);
        }
        if (!"/live/aiqiyi127.ts".equals(strI)) {
            return a.C(a.o.d.NOT_FOUND, a.o, Q());
        }
        f.g().A(strJ);
        return U(strJ, f.g().f53a);
    }

    public String Q() {
        return "<html><head><title>404 Not Found</title></head><body bgcolor=\"white\"><center><h1>404 Not Found</h1></center><hr><center>lgsg</center></body></html>";
    }

    public a.o S(String str, String str2, Map<String, String> map) {
        String strC = f.g().c(map.get("url2"));
        if (strC.contains("http://127.0.0.1:") && strC.contains(".gitv.tv") && strC.contains("&tvid=&")) {
            return a.C(a.o.d.NOT_FOUND, a.o, Q());
        }
        if (strC.contains("pl-ali.youku.com/playlist/m3u8") && strC.contains("&device_type=")) {
            return a.C(a.o.d.OK, "application/vnd.apple.mpegurl", W(str, strC));
        }
        if ("/live/aiqiyi/vod.m3u8".equals(str2)) {
            return a.C(a.o.d.OK, "application/vnd.apple.mpegurl", V(str, strC));
        }
        if (strC.contains(".ott.cibntv.net/") && strC.contains("/m3u8")) {
            return a.C(a.o.d.NOT_FOUND, a.o, Q());
        }
        return (strC.contains(".ott.cibntv.net/") && strC.contains(".m3u8")) ? a.C(a.o.d.OK, "application/vnd.apple.mpegurl", X(str, strC)) : a.C(a.o.d.OK, "application/vnd.apple.mpegurl", Y(str, strC));
    }

    public String T(String str, Map<String, String> map) {
        String strC = f.g().c(map.get("url2"));
        Log.i(u, "get_Aqiyi127List " + strC);
        try {
            new URL(strC);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strC).openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HTTPHelper.HEADER_USER_AGENT, f.g().B(22));
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            int i = 0;
            if (strC.contains("?")) {
                strC.substring(0, strC.indexOf("?"));
            }
            strC.substring(0, strC.lastIndexOf("/"));
            int responseCode = httpURLConnection.getResponseCode();
            if (200 != responseCode && 206 != responseCode) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), HTTPHelper.CHARSET_UTF8));
            StringBuilder sb = new StringBuilder();
            f.g();
            String strM = f.m(ParserUtils.getContext());
            int i2 = 0;
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return sb.toString();
                }
                if (!TextUtils.isEmpty(line) && !TextUtils.isEmpty(line)) {
                    if (!line.contains("#EXTINF:")) {
                        if (!line.startsWith("http://")) {
                            sb.append(line);
                        } else if (i2 < 5) {
                            i2++;
                            if (line.startsWith("http://127.0.0.1:")) {
                                line = "http://" + strM + ":" + a.s + "/live/aiqiyi127.ts?url2=" + f.g().d(line);
                            }
                            sb.append(line);
                        }
                        sb.append("\n");
                    } else if (i < 5) {
                        i++;
                        sb.append(line);
                        sb.append("\n");
                    }
                }
            }
        } catch (MalformedURLException | Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public a.o U(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return a.C(a.o.d.NOT_FOUND, "text/html; charset=utf-8", Q());
        }
        String str2 = map.get("url2");
        a.o oVar = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(f.g().c(str2)).openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HTTPHelper.HEADER_USER_AGENT, "AppleCoreMedia/1.0.0.9A405 (iPad; U; CPU OS 5_0_1 like Mac OS X; zh_cn)");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpURLConnection.setRequestProperty("Connection", "close");
            if (200 == httpURLConnection.getResponseCode() || 206 == httpURLConnection.getResponseCode()) {
                String headerField = httpURLConnection.getHeaderField(HTTPHelper.HEADER_CONTENT_RANGE);
                int contentLength = httpURLConnection.getContentLength();
                if (contentLength < 1) {
                    contentLength = 0;
                }
                a.o oVarB = a.B(a.o.d.OK, "video/MP2T", httpURLConnection.getInputStream(), contentLength);
                try {
                    if (!TextUtils.isEmpty(headerField)) {
                        oVarB.b(HTTPHelper.HEADER_CONTENT_RANGE, headerField);
                        oVarB.b("Accept-Ranges", "bytes");
                    }
                    return oVarB;
                } catch (MalformedURLException | IOException e) {
                    oVar = oVarB;
                    e = e;
                    e.printStackTrace();
                    return oVar;
                }
            }
        } catch (MalformedURLException e2) {
            e = e2;
        } catch (IOException e3) {
            e = e3;
        }
        return oVar;
    }

    public String V(String str, String str2) {
        try {
            new URL(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HTTPHelper.HEADER_USER_AGENT, f.g().B(22));
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            char c = 0;
            if (str2.contains("?")) {
                str2.substring(0, str2.indexOf("?"));
            }
            str2.substring(0, str2.lastIndexOf("/"));
            int responseCode = httpURLConnection.getResponseCode();
            if (200 != responseCode && 206 != responseCode) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), HTTPHelper.CHARSET_UTF8));
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (!TextUtils.isEmpty(line) && !TextUtils.isEmpty(line)) {
                    if (line.contains("#EXT-X-DISCONTINUITY")) {
                        if (c == 0) {
                            sb.append(line);
                            sb.append("\n");
                            sb2.append(line);
                            sb2.append("\n");
                            c = 1;
                        } else if (c == 1) {
                            sb2.append(line);
                            sb2.append("\n");
                            c = 2;
                        }
                    } else if (c != 1) {
                        sb.append(line);
                        sb.append("\n");
                        sb2.append(line);
                        sb2.append("\n");
                    }
                }
            }
            return c > 1 ? sb.toString() : sb2.toString();
        } catch (MalformedURLException | Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String W(String str, String str2) {
        try {
            new URL(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HTTPHelper.HEADER_USER_AGENT, f.g().B(22));
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            char c = 0;
            if (str2.contains("?")) {
                str2.substring(0, str2.indexOf("?"));
            }
            str2.substring(0, str2.lastIndexOf("/"));
            int responseCode = httpURLConnection.getResponseCode();
            if (200 == responseCode || 206 == responseCode) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), HTTPHelper.CHARSET_UTF8));
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                String str3 = "";
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!TextUtils.isEmpty(line)) {
                        if (line.contains("#EXT-X-DISCONTINUITY")) {
                            c = 1;
                        } else if (!line.contains("#EXTINF")) {
                            if (!line.startsWith("http")) {
                                sb.append(line);
                            } else if (line.contains(".ott.cibntv.net/") && line.contains("&s=")) {
                                sb.append(str3);
                                sb.append("\n");
                                sb.append(line);
                            }
                            sb.append("\n");
                        }
                        str3 = line;
                    }
                }
                return c >= 1 ? sb.toString() : sb2.toString();
            }
        } catch (MalformedURLException | Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String X(String str, String str2) {
        try {
            new URL(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HTTPHelper.HEADER_USER_AGENT, f.g().B(22));
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            char c = 0;
            if (str2.contains("?")) {
                str2.substring(0, str2.indexOf("?"));
            }
            str2.substring(0, str2.lastIndexOf("/"));
            int responseCode = httpURLConnection.getResponseCode();
            if (200 == responseCode || 206 == responseCode) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), HTTPHelper.CHARSET_UTF8));
                StringBuilder sb = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                String str3 = "";
                String str4 = str3;
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!TextUtils.isEmpty(line)) {
                        sb2.append(line);
                        sb2.append("\n");
                        if (line.contains("#EXT-X-DISCONTINUITY")) {
                            c = 1;
                        } else {
                            if (line.contains("#EXT-X-PRIVINF")) {
                                str4 = line;
                            }
                            if (line.contains("#EXTINF")) {
                                str3 = line;
                            }
                            if (!line.contains("#EXTINF") && !line.contains("#EXT-X-PRIVINF")) {
                                if (!line.startsWith("http")) {
                                    sb.append(line);
                                } else if (!line.contains(".ott.cibntv.net/") || !line.contains("/ad/")) {
                                    sb.append(str3);
                                    sb.append("\n");
                                    if (str4 != null) {
                                        sb.append(str4);
                                        sb.append("\n");
                                    }
                                    sb.append(line);
                                }
                                sb.append("\n");
                            }
                        }
                    }
                }
                return c >= 1 ? sb.toString() : sb2.toString();
            }
        } catch (MalformedURLException | Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String Y(String str, String str2) {
        try {
            new URL(str2);
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            httpURLConnection.setInstanceFollowRedirects(true);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(HTTPHelper.HEADER_USER_AGENT, f.g().B(22));
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            if (str2.contains("?")) {
                str2.substring(0, str2.indexOf("?"));
            }
            str2.substring(0, str2.lastIndexOf("/"));
            int responseCode = httpURLConnection.getResponseCode();
            if (200 != responseCode && 206 != responseCode) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), HTTPHelper.CHARSET_UTF8));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return sb.toString();
                }
                if (!TextUtils.isEmpty(line) && !TextUtils.isEmpty(line)) {
                    sb.append(line);
                    sb.append("\n");
                }
            }
        } catch (MalformedURLException | Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
