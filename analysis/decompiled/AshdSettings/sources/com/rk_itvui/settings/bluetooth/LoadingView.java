package com.rk_itvui.settings.bluetooth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class LoadingView extends View {
    public static final int CIRCLE = 1;
    public static final int DEFAULT_NUM = 8;
    public static final int DEFAULT_STRIP_NUM = 120;
    public static final int MAX_CONNER = 100;
    public static final int MAX_NUM = 16;
    public static final int MAX_STRIP_NUM = 200;
    public static final int MIN_CONNER = 0;
    public static final int MIN_NUM = 6;
    public static final int MIN_STRIP_NUM = 50;
    public static final int POINT = 0;
    public static final int STRIP = 2;
    int mAlpha;
    double mAngle;
    int mBackAlpha;
    int mBackColor;
    int mBubleNum;
    int mBubleRadius;
    int mCenterX;
    int mCenterY;
    RectF mCircleRectF;
    int mColor;
    int mConnerX;
    int mConnerY;
    int mInterval;
    int mLength;
    float mOriginWidth;
    Paint mPaint;
    RectF mRectF;
    int mStripNum;
    int mStyle;
    String mText;
    int mTextColor;
    Path mTextPath;
    TimerTask mUpdateTask;
    Timer mUpdateTimer;
    float mWidth;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mStyle = 0;
        this.mConnerX = 100;
        this.mConnerY = 100;
        this.mBubleNum = 8;
        this.mStripNum = 120;
        this.mColor = -7829368;
        this.mBackColor = -65281;
        this.mTextColor = -13421773;
        this.mAlpha = 255;
        this.mBackAlpha = 0;
        this.mWidth = 5.0f;
        this.mAngle = 0.0d;
        this.mInterval = 150;
        this.mText = "";
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mRectF = new RectF();
        this.mCircleRectF = new RectF();
        this.mTextPath = new Path();
        this.mUpdateTimer = new Timer();
        this.mUpdateTask = new TimerTask() { // from class: com.rk_itvui.settings.bluetooth.LoadingView.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                LoadingView.this.mAlpha -= 255 / LoadingView.this.mBubleNum;
                if (LoadingView.this.mAlpha < 0) {
                    LoadingView.this.mAlpha = 255;
                }
                LoadingView.this.postInvalidate();
            }
        };
        this.mUpdateTimer.schedule(this.mUpdateTask, 100L, this.mInterval);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mOriginWidth = this.mPaint.getStrokeWidth();
        this.mPaint.setColor(this.mBackColor);
        this.mPaint.setAlpha(this.mBackAlpha);
        canvas.drawRoundRect(this.mRectF, this.mConnerX, this.mConnerY, this.mPaint);
        this.mPaint.setColor(this.mColor);
        int i = this.mAlpha;
        int i2 = 0;
        if (this.mStyle == 1) {
            this.mPaint.setStrokeWidth(this.mWidth);
            this.mPaint.setStyle(Paint.Style.STROKE);
            int i3 = i;
            float f = 0.0f;
            for (int i4 = 0; i4 < 360; i4++) {
                this.mPaint.setAlpha(i3);
                canvas.drawArc(this.mCircleRectF, f, 1.4117647f, false, this.mPaint);
                f += 1.4117647f;
                i3--;
            }
        } else {
            int i5 = 2;
            if (this.mStyle == 2) {
                float f2 = this.mWidth * 10.0f;
                if (f2 > 100.0f) {
                    f2 = 100.0f;
                }
                this.mPaint.setStrokeWidth(f2);
                float f3 = 330.0f / this.mStripNum;
                this.mPaint.setStyle(Paint.Style.STROKE);
                int i6 = i;
                float f4 = 0.0f;
                for (int i7 = 0; i7 < this.mStripNum; i7++) {
                    this.mPaint.setAlpha(i6);
                    canvas.drawArc(this.mCircleRectF, f4, f3, false, this.mPaint);
                    f4 += 360.0f / this.mStripNum;
                    i6 = (int) (i6 - (255.0f / this.mStripNum));
                }
            } else {
                while (i2 < this.mBubleNum) {
                    this.mPaint.setAlpha(i);
                    canvas.drawCircle((float) (((double) this.mCenterX) + (((double) (this.mCenterX / i5)) * Math.sin((this.mAngle * 3.141592653589793d) / 180.0d))), (float) (((double) this.mCenterY) + (((double) (this.mCenterY / i5)) * Math.cos((this.mAngle * 3.141592653589793d) / 180.0d))), this.mBubleRadius, this.mPaint);
                    this.mAngle += 360.0d / ((double) this.mBubleNum);
                    i -= 255 / this.mBubleNum;
                    i2++;
                    i5 = 2;
                }
            }
        }
        this.mPaint.setStrokeWidth(this.mOriginWidth);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAlpha(255);
        this.mPaint.setColor(this.mTextColor);
        this.mPaint.setTextSize(15.0f);
        canvas.drawTextOnPath(this.mText, this.mTextPath, 0.0f, 0.0f, this.mPaint);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (size > size2) {
            size = size2;
        }
        this.mLength = size;
        this.mRectF.bottom = this.mLength;
        this.mRectF.right = this.mLength;
        this.mCircleRectF.top = this.mLength / 4;
        this.mCircleRectF.left = this.mLength / 4;
        this.mCircleRectF.bottom = (this.mLength / 4) * 3;
        this.mCircleRectF.right = (this.mLength / 4) * 3;
        this.mCenterX = this.mLength / 2;
        this.mCenterY = this.mLength / 2;
        this.mBubleRadius = (this.mLength / this.mBubleNum) / 2;
        this.mTextPath.moveTo(this.mCenterX - (this.mCenterX / 4), this.mCenterY);
        this.mTextPath.lineTo(this.mCenterX + (this.mCenterX / 4), this.mCenterY);
        this.mTextPath.close();
        setMeasuredDimension(this.mLength, this.mLength);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public int getmBubleNum() {
        return this.mBubleNum;
    }

    public void setmBubleNum(int i) {
        if (i < 6) {
            this.mBubleNum = 6;
        } else if (i > 16) {
            this.mBubleNum = 16;
        } else {
            this.mBubleNum = i;
        }
    }

    public int getmBackColor() {
        return this.mBackColor;
    }

    public void setmBackColor(int i) {
        this.mBackColor = i;
    }

    public int getmColor() {
        return this.mColor;
    }

    public void setmColor(int i) {
        this.mColor = i;
    }

    public int getmBackAlpha() {
        return this.mBackAlpha;
    }

    public void setmBackAlpha(int i) {
        this.mBackAlpha = i;
    }

    public int getConner() {
        return this.mConnerX;
    }

    public void setConner(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 100) {
            i = 100;
        }
        this.mConnerX = i;
        this.mCenterY = i;
    }

    public int getmTextColor() {
        return this.mTextColor;
    }

    public void setmTextColor(int i) {
        this.mTextColor = i;
    }

    public String getmText() {
        return this.mText;
    }

    public void setmText(String str) {
        this.mText = str;
    }

    public int getmStyle() {
        return this.mStyle;
    }

    public void setmStyle(int i) {
        this.mStyle = i;
    }

    public void setCircleWidth(float f) {
        this.mWidth = f;
    }

    public float getCircleWidth() {
        return this.mWidth;
    }

    public int getmStripNum() {
        return this.mStripNum;
    }

    public void setmStripNum(int i) {
        if (i < 50) {
            i = 50;
        } else if (i > 200) {
            i = 200;
        }
        this.mStripNum = i;
    }
}
