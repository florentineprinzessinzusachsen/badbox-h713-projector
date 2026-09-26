package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatTextClassifierHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextClassifier f784b;

    k(TextView textView) {
        androidx.core.e.e.a(textView);
        this.f783a = textView;
    }

    public void a(TextClassifier textClassifier) {
        this.f784b = textClassifier;
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f784b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f783a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }
}
