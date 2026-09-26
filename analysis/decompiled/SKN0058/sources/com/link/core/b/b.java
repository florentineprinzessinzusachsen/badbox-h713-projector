package com.link.core.b;

import com.link.core.a.y;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {
    public final /* synthetic */ c a;

    public b(c cVar) {
        this.a = cVar;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.LinkedList, java.util.List<com.link.core.b.a$a>] */
    @Override // java.lang.Runnable
    public final void run() {
        c cVar = this.a;
        while (!cVar.h.get()) {
            ArrayList<a.C0001a> arrayList = new ArrayList();
            synchronized (cVar.f) {
                if (cVar.f.isEmpty()) {
                    try {
                        cVar.f.wait();
                    } catch (InterruptedException unused) {
                    }
                }
                arrayList.addAll(cVar.f);
                cVar.f.clear();
            }
            for (a.C0001a c0001a : arrayList) {
                c.a aVar = new c.a();
                String str = c0001a.a;
                aVar.a = str;
                try {
                    InetAddress byName = InetAddress.getByName(str);
                    aVar.b = a.b.DONE;
                    aVar.c = byName;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    aVar.b = a.b.FAILED;
                }
                y<c.a> yVar = cVar.e;
                synchronized (yVar) {
                    LinkedList<c.a> linkedList = yVar.a.get();
                    if (linkedList == null) {
                        linkedList = new LinkedList<>();
                    }
                    linkedList.add(aVar);
                }
            }
        }
    }
}
