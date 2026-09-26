package l3;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f1375a = new e();

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int i4;
        j2.i.e(logRecord, "record");
        CopyOnWriteArraySet copyOnWriteArraySet = d.f1373a;
        String loggerName = logRecord.getLoggerName();
        j2.i.d(loggerName, "getLoggerName(...)");
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        if (iIntValue > level.intValue()) {
            i4 = 5;
        } else {
            i4 = logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
        }
        String message = logRecord.getMessage();
        j2.i.d(message, "getMessage(...)");
        d.a(loggerName, i4, message, logRecord.getThrown());
    }

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }
}
