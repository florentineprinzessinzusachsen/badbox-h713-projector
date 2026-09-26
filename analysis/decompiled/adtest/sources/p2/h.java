package p2;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Pattern f1761d;

    public h(String str) {
        Pattern patternCompile = Pattern.compile(str);
        j2.i.d(patternCompile, "compile(...)");
        this.f1761d = patternCompile;
    }

    public final a2.f a(int i4, String str) {
        j2.i.e(str, "input");
        Matcher matcherRegion = this.f1761d.matcher(str).useAnchoringBounds(false).useTransparentBounds(true).region(i4, str.length());
        if (matcherRegion.lookingAt()) {
            return new a2.f(matcherRegion, str);
        }
        return null;
    }

    public final String toString() {
        String string = this.f1761d.toString();
        j2.i.d(string, "toString(...)");
        return string;
    }
}
