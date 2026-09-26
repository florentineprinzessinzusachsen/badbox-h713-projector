package retrofit2.converter.gson;

import b.b.a.f;
import b.b.a.m;
import b.b.a.v;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import d.d0;
import retrofit2.Converter;

/* JADX INFO: loaded from: classes.dex */
final class GsonResponseBodyConverter<T> implements Converter<d0, T> {
    private final v<T> adapter;
    private final f gson;

    GsonResponseBodyConverter(f fVar, v<T> vVar) {
        this.gson = fVar;
        this.adapter = vVar;
    }

    @Override // retrofit2.Converter
    public T convert(d0 d0Var) {
        JsonReader jsonReaderA = this.gson.a(d0Var.charStream());
        try {
            T tA2 = this.adapter.a2(jsonReaderA);
            if (jsonReaderA.peek() != JsonToken.END_DOCUMENT) {
                throw new m("JSON document was not fully consumed.");
            }
            d0Var.close();
            return tA2;
        } catch (Throwable th) {
            d0Var.close();
            throw th;
        }
    }
}
