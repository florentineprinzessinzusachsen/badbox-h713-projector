package ddth2;

/* JADX INFO: loaded from: classes.dex */
public final class MetricsSnapshot {
    public final long aggregateRecvBufferedBytes;
    public final long aggregateRecvBufferedCapBytes;
    public final int aggregateWorkNodesActive;
    public final int aggregateWorkNodesCap;
    public final long executorQueueLength;
    public final int fdApprox;
    public final int httpConnectionsInFlightApprox;
    public final int mainTcpCount;
    public final int maxConcurrentHttpConnections;
    public final int maxMainTcpCount;
    public final long reconnectAttemptsTotal;
    public final long recvBufferPeakBytes;
    public final int runningMainTcpCount;
    public final int threadPoolActiveCount;
    public final int threadPoolMaxSize;
    public final int threadPoolSize;

    public MetricsSnapshot(int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, long j2, long j3, int i8, int i9, long j4, long j5, int i10, int i11) {
        this.threadPoolSize = i;
        this.threadPoolMaxSize = i2;
        this.threadPoolActiveCount = i3;
        this.executorQueueLength = j;
        this.mainTcpCount = i4;
        this.runningMainTcpCount = i5;
        this.maxMainTcpCount = i6;
        this.fdApprox = i7;
        this.recvBufferPeakBytes = j2;
        this.reconnectAttemptsTotal = j3;
        this.httpConnectionsInFlightApprox = i8;
        this.maxConcurrentHttpConnections = i9;
        this.aggregateRecvBufferedBytes = j4;
        this.aggregateRecvBufferedCapBytes = j5;
        this.aggregateWorkNodesActive = i10;
        this.aggregateWorkNodesCap = i11;
    }
}
