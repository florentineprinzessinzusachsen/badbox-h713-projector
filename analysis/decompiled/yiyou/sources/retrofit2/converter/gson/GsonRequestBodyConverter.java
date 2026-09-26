package retrofit2.converter.gson;

import b.b.a.f;
import com.google.gson.stream.JsonWriter;
import d.b0;
import d.v;
import e.c;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes.dex */
final class GsonRequestBodyConverter<T> implements Converter<T, b0> {
    private static final v MEDIA_TYPE = v.b("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final b.b.a.v<T> adapter;
    private final f gson;

    GsonRequestBodyConverter(f fVar, b.b.a.v<T> vVar) {
        this.gson = fVar;
        this.adapter = vVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // retrofit2.Converter
    public b0 convert(T t) throws IOException {
        c cVar = new c();
        JsonWriter jsonWriterA = this.gson.a((Writer) new OutputStreamWriter(cVar.m(), UTF_8));
        this.adapter.a(jsonWriterA, t);
        jsonWriterA.close();
        return b0.create(MEDIA_TYPE, cVar.n());
    }
}
