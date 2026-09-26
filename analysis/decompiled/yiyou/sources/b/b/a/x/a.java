package b.b.a.x;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: Expose.java */
/* JADX INFO: loaded from: classes.dex */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface a {
    boolean deserialize() default true;

    boolean serialize() default true;
}
