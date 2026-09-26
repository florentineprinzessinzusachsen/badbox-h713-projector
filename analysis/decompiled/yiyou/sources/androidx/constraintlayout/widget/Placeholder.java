package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class Placeholder extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private View f872b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f873c;

    public Placeholder(Context context) {
        super(context);
        this.f871a = -1;
        this.f872b = null;
        this.f873c = 4;
        a((AttributeSet) null);
    }

    private void a(AttributeSet attributeSet) {
        super.setVisibility(this.f873c);
        this.f871a = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_placeholder);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_placeholder_content) {
                    this.f871a = typedArrayObtainStyledAttributes.getResourceId(index, this.f871a);
                } else if (index == R$styleable.ConstraintLayout_placeholder_emptyVisibility) {
                    this.f873c = typedArrayObtainStyledAttributes.getInt(index, this.f873c);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f871a == -1 && !isInEditMode()) {
            setVisibility(this.f873c);
        }
        this.f872b = constraintLayout.findViewById(this.f871a);
        View view = this.f872b;
        if (view != null) {
            ((ConstraintLayout.a) view.getLayoutParams()).Z = true;
            this.f872b.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f872b;
    }

    public int getEmptyVisibility() {
        return this.f873c;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawRGB(223, 223, 223);
            Paint paint = new Paint();
            paint.setARGB(255, 210, 210, 210);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            paint.setTextSize(rect.height());
            int iHeight = rect.height();
            int iWidth = rect.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds("?", 0, 1, rect);
            canvas.drawText("?", ((iWidth / 2.0f) - (rect.width() / 2.0f)) - rect.left, ((iHeight / 2.0f) + (rect.height() / 2.0f)) - rect.bottom, paint);
        }
    }

    public void setContentId(int i) {
        View viewFindViewById;
        if (this.f871a == i) {
            return;
        }
        View view = this.f872b;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.a) this.f872b.getLayoutParams()).Z = false;
            this.f872b = null;
        }
        this.f871a = i;
        if (i == -1 || (viewFindViewById = ((View) getParent()).findViewById(i)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i) {
        this.f873c = i;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f871a = -1;
        this.f872b = null;
        this.f873c = 4;
        a(attributeSet);
    }

    public void a(ConstraintLayout constraintLayout) {
        if (this.f872b == null) {
            return;
        }
        ConstraintLayout.a aVar = (ConstraintLayout.a) getLayoutParams();
        ConstraintLayout.a aVar2 = (ConstraintLayout.a) this.f872b.getLayoutParams();
        aVar2.k0.n(0);
        aVar.k0.o(aVar2.k0.s());
        aVar.k0.g(aVar2.k0.i());
        aVar2.k0.n(8);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f871a = -1;
        this.f872b = null;
        this.f873c = 4;
        a(attributeSet);
    }
}
