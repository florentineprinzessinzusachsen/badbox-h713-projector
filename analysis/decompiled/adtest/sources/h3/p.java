package h3;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f1129d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ q f1130e;

    public p(q qVar, u uVar) {
        this.f1130e = qVar;
        this.f1129d = uVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [h3.q] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [h3.b] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // i2.a
    public final Object a() throws Throwable {
        Throwable th;
        b bVar;
        ?? r4 = this.f1130e;
        u uVar = this.f1129d;
        b bVar2 = b.INTERNAL_ERROR;
        ?? r5 = 1;
        IOException e4 = null;
        try {
            try {
                try {
                    if (!uVar.b(true, this)) {
                        throw new IOException("Required SETTINGS preface not received");
                    }
                    do {
                        try {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } while (uVar.b(false, this));
                    bVar = b.NO_ERROR;
                    try {
                        bVar2 = b.CANCEL;
                        r4.b(bVar, bVar2, null);
                        r5 = bVar;
                    } catch (IOException e5) {
                        e4 = e5;
                        bVar2 = b.PROTOCOL_ERROR;
                        r4.b(bVar2, bVar2, e4);
                        r5 = bVar;
                    }
                    b3.d.b(uVar);
                    return u1.k.f2301a;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e6) {
                e4 = e6;
                bVar = bVar2;
            }
        } catch (Throwable th4) {
            th = th4;
        }
        r5 = bVar2;
        r4.b(r5, bVar2, e4);
        b3.d.b(uVar);
        throw th;
    }
}
