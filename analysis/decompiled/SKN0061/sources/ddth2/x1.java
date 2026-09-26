package ddth2;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class x1 {
    public Selector a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public AtomicBoolean f99a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final Queue<y1> f98a = new ConcurrentLinkedQueue();
    public final Queue<z1> b = new ConcurrentLinkedQueue();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            x1.this.d();
        }
    }

    public x1() {
        try {
            this.a = Selector.open();
            this.f99a = new AtomicBoolean(false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create selector", e);
        }
    }

    public Selector a() {
        return this.a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final void m75a() {
        try {
            for (SelectionKey selectionKey : this.a.keys()) {
                if (selectionKey.isValid() && (selectionKey.channel() instanceof SocketChannel)) {
                    Object objAttachment = selectionKey.attachment();
                    if (objAttachment instanceof z1) {
                        ((z1) objAttachment).f101a.a();
                    } else if (objAttachment instanceof c2) {
                        ((c2) objAttachment).b();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    public final void a(SelectionKey selectionKey) {
        z1 z1Var = (z1) selectionKey.attachment();
        try {
            if (((SocketChannel) selectionKey.channel()).finishConnect()) {
                selectionKey.interestOps(1);
                selectionKey.attach(z1Var.f101a);
                z1Var.f101a.a(z1Var.a);
            } else {
                z1Var.f101a.b(z1Var.a);
                selectionKey.cancel();
            }
        } catch (IOException unused) {
            z1Var.f101a.b(z1Var.a);
            selectionKey.cancel();
        }
    }

    public void a(SocketChannel socketChannel, c2 c2Var) {
        this.f98a.offer(new y1(socketChannel, c2Var));
        this.a.wakeup();
    }

    public void a(SocketChannel socketChannel, c2 c2Var, int i) {
        this.b.offer(new z1(socketChannel, c2Var, i));
        this.a.wakeup();
    }

    public void a(Executor executor) {
        if (executor == null) {
            throw new IllegalArgumentException("executor required");
        }
        if (this.f99a.compareAndSet(false, true)) {
            executor.execute(new a());
        }
    }

    public final void b() {
        while (true) {
            z1 z1VarPoll = this.b.poll();
            if (z1VarPoll == null) {
                return;
            }
            try {
                z1VarPoll.f102a.configureBlocking(false);
                z1VarPoll.f102a.register(this.a, 8, z1VarPoll);
            } catch (IOException unused) {
                z1VarPoll.f101a.b(z1VarPoll.a);
            }
        }
    }

    public final void b(SelectionKey selectionKey) {
        c2 c2Var = (c2) selectionKey.attachment();
        if (c2Var == null || c2Var.m6a()) {
            return;
        }
        selectionKey.cancel();
    }

    public final void c() {
        while (true) {
            y1 y1VarPoll = this.f98a.poll();
            if (y1VarPoll == null) {
                return;
            }
            try {
                y1VarPoll.f100a.configureBlocking(false);
                y1VarPoll.f100a.register(this.a, 1, y1VarPoll.a);
            } catch (IOException unused) {
                y1VarPoll.a.f();
            }
        }
    }

    public final void d() {
        while (this.f99a.get() && this.a.isOpen()) {
            try {
                c();
                b();
                m75a();
                if (this.a.select(1000L) != 0) {
                    Iterator<SelectionKey> it = this.a.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey next = it.next();
                        it.remove();
                        if (next.isValid()) {
                            if (next.isReadable()) {
                                b(next);
                            } else if (next.isConnectable()) {
                                a(next);
                            }
                        }
                    }
                }
            } catch (IOException | Exception unused) {
            }
        }
    }

    public void e() {
        if (this.f99a.compareAndSet(true, false)) {
            Selector selector = this.a;
            if (selector != null) {
                selector.wakeup();
            }
            Selector selector2 = this.a;
            if (selector2 != null) {
                try {
                    selector2.close();
                } catch (IOException unused) {
                }
            }
        }
    }
}
