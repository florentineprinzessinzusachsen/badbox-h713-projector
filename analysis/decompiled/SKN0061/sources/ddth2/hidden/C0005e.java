package ddth2.hidden;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.LinkedList;

/* JADX INFO: renamed from: ddth2.hidden.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0005e {
    public int a = 0;
    public SelectionKey b = null;
    public LinkedList c;

    public C0005e() {
        this.c = null;
        this.c = new LinkedList();
    }

    public final void a() throws Exception {
        this.c.clear();
        this.c = null;
        SelectionKey selectionKey = this.b;
        if (selectionKey != null) {
            selectionKey.cancel();
            this.b.attach(null);
            try {
                this.b.channel().close();
                this.b = null;
            } catch (Exception e) {
                this.b = null;
                throw e;
            }
        }
    }

    public final void a(ByteBuffer byteBuffer) {
        if (this.c.isEmpty()) {
            SelectionKey selectionKey = this.b;
            selectionKey.interestOps(selectionKey.interestOps() | 4);
        }
        this.c.add(byteBuffer);
    }

    public final void a(boolean z, String str) {
        ByteBuffer byteBufferAllocate;
        int length = str.length();
        if (z) {
            byteBufferAllocate = ByteBuffer.allocate(length + 10);
            byteBufferAllocate.put(new byte[]{0, 19, 0, 0, 0, 0, (byte) ((length >> 24) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 8) & 255), (byte) (length & 255)});
        } else {
            byteBufferAllocate = ByteBuffer.allocate(length + 4);
            byteBufferAllocate.put(new byte[]{(byte) ((length >> 24) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 8) & 255), (byte) (length & 255)});
        }
        byteBufferAllocate.put(str.getBytes());
        a(byteBufferAllocate);
    }

    public final void a(int i, int i2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(7);
        byteBufferAllocate.put(new byte[]{0, 1, (byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) (i2 & 255)});
        a(byteBufferAllocate);
    }
}
