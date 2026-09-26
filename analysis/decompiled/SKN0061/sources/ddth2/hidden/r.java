package ddth2.hidden;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static void a(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i + i3, i2 - i3);
            if (i4 < 0) {
                throw new EOFException();
            }
            i3 += i4;
        }
    }

    public static long b(int i, byte[] bArr) {
        return ((long) (bArr[i + 3] & 255)) | (((long) (bArr[i] & 255)) << 24) | (((long) (bArr[i + 1] & 255)) << 16) | (((long) (bArr[i + 2] & 255)) << 8);
    }

    public static int a(int i, byte[] bArr) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public static void a(ScheduledExecutorService scheduledExecutorService, Socket socket, byte[] bArr, int i) throws IOException {
        if (socket == null) {
            throw new IOException("socket is null");
        }
        if (scheduledExecutorService != null && i > 0) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
            try {
                ScheduledFuture<?> scheduledFutureSchedule = scheduledExecutorService.schedule(new RunnableC0016p(atomicBoolean, atomicBoolean2, socket), i, TimeUnit.MILLISECONDS);
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(bArr);
                    outputStream.flush();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        scheduledFutureSchedule.cancel(false);
                    }
                    if (atomicBoolean2.get()) {
                        throw new IOException("write timed out");
                    }
                    return;
                } catch (Throwable th) {
                    if (atomicBoolean.compareAndSet(false, true)) {
                        scheduledFutureSchedule.cancel(false);
                    }
                    throw th;
                }
            } catch (RejectedExecutionException unused) {
                z.a(socket);
                throw new IOException("write watchdog rejected");
            }
        }
        OutputStream outputStream2 = socket.getOutputStream();
        outputStream2.write(bArr);
        outputStream2.flush();
    }
}
