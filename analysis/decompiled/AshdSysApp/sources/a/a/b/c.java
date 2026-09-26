package a.a.b;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import com.android.sysapp.UpdateService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class c extends j {
    public String f;
    public String g;
    public long h;
    public long i;
    public Context j;
    public h k;
    public long l = 0;
    public long m = 0;
    public RandomAccessFile n;

    public c(Context context, Handler handler) {
        this.i = 0L;
        this.j = context;
        this.k = new h(context);
        this.f = this.k.f8a.getString("download_URL", null);
        this.g = this.k.d();
        this.i = this.k.f8a.getLong("download_position", 0L);
        this.h = this.k.c();
        if (this.i != 0 && this.h != 0) {
            File file = new File(this.g);
            if (file.exists() && file.length() == this.h) {
                Log.i("CMUpdate2DownloadTask", "mRunningStatus = RUNNING_STATUS_PAUSE");
                this.d = 2;
                long j = this.h;
                this.b = j != 0 ? (int) ((this.i * 100) / j) : 0;
                return;
            }
        }
        g();
        this.d = 0;
    }

    @Override // a.a.b.j
    public void a() {
        Intent intent;
        this.f = this.k.f8a.getString("download_URL", null);
        this.g = this.k.d();
        if (n.f13a) {
            Log.i("CMUpdate2DownloadTask", "onRunning()");
        }
        try {
            URL url = new URL(this.f);
            this.n = new RandomAccessFile(this.g, "rw");
            if (n.f13a) {
                Log.i("CMUpdate2DownloadTask", "start download, url=" + this.f + " target=" + this.g);
            }
            byte[] bArr = new byte[8192];
            this.h = ((HttpURLConnection) url.openConnection()).getContentLength();
            if (this.h < 0) {
                Log.e("CMUpdate2DownloadTask", "Download file " + this.g + " from " + this.f + " is failure");
                this.c = 1;
                Intent intent2 = new Intent(this.j, (Class<?>) UpdateService.class);
                intent2.putExtra("start_command", 109);
                this.j.startService(intent2);
                g();
                throw new IOException("Something is wrong with network!");
            }
            if (n.f13a) {
                Log.v("CMUpdate2DownloadTask", "FileSize=" + this.h);
            }
            this.n.setLength(this.h);
            this.n.seek(this.i);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Range", "bytes=" + this.i + "-" + this.h);
            this.l = this.i;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            int i = 200;
            while (true) {
                int i2 = bufferedInputStream.read(bArr);
                if (i2 <= 0) {
                    if (d.a(this.k.f8a.getString("package_md5", ""), n.h)) {
                        this.c = 0;
                        if (UpdateService.p) {
                            Log.i("CMUpdate2DownloadTask", "Force update Mode!Donwload ota finish");
                            if (n.f13a) {
                                Log.d("CMUpdate2DownloadTask", "startService for Force copy verify！");
                            }
                            intent = new Intent(this.j, (Class<?>) UpdateService.class);
                            intent.putExtra("start_command", 106);
                        } else {
                            Log.i("CMUpdate2DownloadTask", "Normal update Mode!Donwload ota finish");
                            intent = new Intent(this.j, (Class<?>) UpdateService.class);
                            intent.putExtra("start_command", 105);
                        }
                    } else {
                        Log.d("CMUpdate2DownloadTask", "md5 check failed");
                        this.c = 1;
                        Log.i("CMUpdate2DownloadTask", "Force update Mode!Donwload failed");
                        if (n.f13a) {
                            Log.d("CMUpdate2DownloadTask", "Notify Service Donwload failed！");
                        }
                        intent = new Intent(this.j, (Class<?>) UpdateService.class);
                        intent.putExtra("start_command", 104);
                    }
                    this.j.startService(intent);
                    g();
                    return;
                }
                while (true) {
                    if (this.d != 2 && this.d != 0) {
                        break;
                    }
                    try {
                        if (this.d == 0) {
                            g();
                            return;
                        }
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (this.n) {
                    this.n.write(bArr, 0, i2);
                    this.l += (long) i2;
                    this.b = (int) ((this.l * 100) / this.h);
                    if (this.h == this.l) {
                        this.b = 100;
                    }
                    this.i = this.l;
                    i++;
                    if (i >= 200) {
                        Log.i("CMUpdate2DownloadTask", "Download mPosition=" + this.i);
                        i = 0;
                    }
                    this.k.a(this.h, this.i);
                }
            }
        } catch (FileNotFoundException e2) {
            Log.e("CMUpdate2DownloadTask", "URL is not exist\n" + e2.toString());
            this.c = 1;
            g();
            e2.printStackTrace();
        } catch (MalformedURLException e3) {
            Log.e("CMUpdate2DownloadTask", "Download is  failure\n" + e3.toString());
            this.c = 1;
            g();
            e3.printStackTrace();
        } catch (IOException e4) {
            Log.e("CMUpdate2DownloadTask", "IO is exception\n" + e4.toString());
            this.c = 1;
            this.m = this.m + 1;
            if (this.m >= 3) {
                this.m = 0L;
                Intent intent3 = new Intent(this.j, (Class<?>) UpdateService.class);
                intent3.putExtra("start_command", 109);
                this.j.startService(intent3);
            } else {
                g();
            }
            e4.printStackTrace();
        }
    }

    @Override // a.a.b.j
    public void b() {
    }

    public void g() {
        this.i = 0L;
        this.h = 0L;
        this.k.a(0L, 0L);
    }
}
