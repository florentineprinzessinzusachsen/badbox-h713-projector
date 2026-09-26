package com.link.core.b;

import com.link.core.a.y;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class c implements com.link.core.b.a.c {
    public Thread g;
    public long a = new Date().getTime();
    public long b = new Date().getTime();
    public final HashMap<String, InetAddress> c = new HashMap<>();
    public final HashMap<String, com.link.core.b.a.C0001a> d = new HashMap<>();
    public final y<a> e = new y<>();
    public final List<com.link.core.b.a.C0001a> f = new LinkedList();
    public AtomicBoolean h = new AtomicBoolean();

    public static class a {
        public String a;
        public com.link.core.b.a.b b;
        public InetAddress c = null;
    }

    @Override // com.link.core.b.a.c
    public final void a() {
    }

    public final void a(String str, com.link.core.b.a.b bVar, InetAddress inetAddress) {
        com.link.core.b.a.C0001a c0001a = this.d.get(str);
        if (c0001a == null) {
            return;
        }
        Iterator<com.link.core.b.a.d> it = c0001a.c.iterator();
        while (it.hasNext()) {
            it.next().a(bVar, inetAddress);
        }
        this.d.remove(str);
    }

    public final boolean a(Object obj) {
        this.h.set(false);
        Thread thread = new Thread(new b(this));
        this.g = thread;
        thread.start();
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.LinkedList, java.util.List<com.link.core.b.a$a>] */
    public final void b() {
        if (this.g == null) {
            return;
        }
        this.h.set(true);
        this.g.interrupt();
        while (true) {
            try {
                this.g.join();
                break;
            } catch (InterruptedException unused) {
            }
        }
        this.f.clear();
        this.g = null;
        y<a> yVar = this.e;
        synchronized (yVar) {
            LinkedList<a> linkedList = yVar.a.get();
            if (linkedList != null) {
                linkedList.clear();
            }
        }
    }

    public final void c() {
        LinkedList<a> andSet;
        long time = new Date().getTime();
        if (time - this.a >= 300000) {
            this.c.clear();
            this.a = time;
        }
        if (time - this.b >= 1000) {
            ArrayList<String> arrayList = new ArrayList();
            for (Map.Entry<String, com.link.core.b.a.C0001a> entry : this.d.entrySet()) {
                if (entry.getValue().b + 3000 < time) {
                    arrayList.add(entry.getKey());
                }
            }
            for (String str : arrayList) {
                a(str, com.link.core.b.a.b.FAILED, null);
                this.d.remove(str);
            }
            this.b = time;
        }
        y<a> yVar = this.e;
        synchronized (yVar) {
            andSet = yVar.a.getAndSet(new LinkedList<>());
        }
        for (a aVar : andSet) {
            a(aVar.a, aVar.b, aVar.c);
        }
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [java.util.LinkedList, java.util.List<com.link.core.b.a$a>] */
    public final void a(String str, com.link.core.b.a.d dVar) {
        if (com.link.core.b.a.a(str)) {
            try {
                d.c cVar = (d.c) dVar;
                cVar.a(com.link.core.b.a.b.CACHED, InetAddress.getByName(str));
                return;
            } catch (UnknownHostException unused) {
            }
        }
        InetAddress inetAddress = this.c.get(str);
        if (inetAddress != null) {
            ((d.c) dVar).a(com.link.core.b.a.b.CACHED, inetAddress);
            return;
        }
        com.link.core.b.a.C0001a c0001a = this.d.get(str);
        if (c0001a != null) {
            c0001a.c.add(dVar);
            return;
        }
        com.link.core.b.a.C0001a c0001a2 = new com.link.core.b.a.C0001a();
        c0001a2.a = str;
        c0001a2.c.add(dVar);
        this.d.put(str, c0001a2);
        synchronized (this.f) {
            this.f.add(c0001a2);
            this.f.notify();
        }
    }
}
