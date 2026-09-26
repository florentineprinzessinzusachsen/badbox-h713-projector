package com.softwinner.tv;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.softwinner.tv.client.AwTvServerManagerProxy;
import com.softwinner.tv.module.BitmapUtils;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;

/* JADX INFO: loaded from: classes.dex */
public class AwTvImagePlayer {
    private static final int PQ_DRAW_TIMES = 12;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 180;
    public static final int ROTATION_270 = 270;
    public static final int ROTATION_90 = 90;
    private static final String TAG = "awapi-AwTvImagePlayer";
    private Context mContext;
    private Bitmap mDecodeBitmap;
    private Bitmap mDecodeNinetyAngleBitmap;
    private ITvServer mDisplayHidlManager;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private SurfaceHolder mHolder;
    private byte[] mNV21;
    private BitmapFactory.Options mOptions;
    private int mOriginalBpHeight;
    private int mOriginalBpWidth;
    private String mPath;
    private Surface mSurface;
    private Bitmap mSurfaceBitmap;
    private int mSurfaceHeight;
    private Bitmap mSurfaceNinetyAngleBitmap;
    private int mSurfaceWidth;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private int mRotation = 0;
    private boolean mUsePQ = false;
    private final Runnable mPlayCallback = this::onPlay;
    private int SCREEN_WIDTH = AwTvDisplayManager.getInstance().getPannelWidth();
    private int SCREEN_HEIGHT = AwTvDisplayManager.getInstance().getPannelHeight();

    private native boolean nativeDrawSurface(Surface surface, int i, int i2, int i3, byte[] bArr);

    private native void nativeImageSurfaceChanged();

    private native void nativeSetImageSurface(Surface surface);

    static {
        System.loadLibrary("awimage_jni");
    }

    public AwTvImagePlayer(Context context) {
        this.mDisplayHidlManager = null;
        this.mContext = context;
        Log.i(TAG, "screen width: " + this.SCREEN_WIDTH + " ,height: " + this.SCREEN_HEIGHT);
        ((Activity) context).getWindow().getDecorView().setBackgroundColor(-16777216);
        this.mDisplayHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
        this.mHandlerThread = new HandlerThread(TAG);
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mSurfaceBitmap = BitmapUtils.createBlankBitmap(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
        this.mSurfaceNinetyAngleBitmap = BitmapUtils.createBlankBitmap(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mHolder = surfaceHolder;
        if (this.mHolder == null) {
            throw new RuntimeException("SurfaceHolder is null");
        }
        this.mSurface = this.mHolder.getSurface();
        nativeImageSurfaceChanged();
        this.mHolder.setFixedSize(this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
    }

    public boolean getUsePQ() {
        return this.mUsePQ;
    }

    public void setUsePQ(boolean z) {
        this.mUsePQ = z;
    }

    public float getMaxScaleFactor() {
        return BitmapUtils.getMaxScaleFactor(this.SCREEN_WIDTH, this.SCREEN_HEIGHT, this.mOriginalBpWidth, this.mOriginalBpHeight);
    }

    public float getMinScaleFactor() {
        return BitmapUtils.getMinScaleFactor();
    }

    public void play(String str) {
        play(str, this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
    }

    public void play(String str, int i, int i2) {
        this.mPath = str;
        this.mSurfaceWidth = i;
        this.mSurfaceHeight = i2;
        this.mOptions = new BitmapFactory.Options();
        if (this.mHandler.hasCallbacks(this.mPlayCallback)) {
            this.mHandler.removeCallbacks(this.mPlayCallback);
            Log.d(TAG, "repeat play task, cancel decode");
            if (this.mOptions != null) {
                this.mOptions.requestCancelDecode();
            }
        }
        this.mHandler.post(this.mPlayCallback);
    }

    public void scale(float f, float f2) {
        if (Math.max(f, f2) >= getMaxScaleFactor() || Math.min(f, f2) <= getMinScaleFactor()) {
            Log.w(TAG, "image scale limit, sx: " + f + " ,sy: " + f2);
            return;
        }
        this.mScaleX = f;
        this.mScaleY = f2;
        transform();
    }

    public void rotate(int i) {
        if (i != 0 && i != 90 && i != 180 && i != 270) {
            Log.w(TAG, "unsupport rotation for image rotation: " + i);
            return;
        }
        this.mRotation = i;
        transform();
    }

    public void release() {
        if (this.mUsePQ) {
            try {
                this.mDisplayHidlManager.SubDeviceSeamlessDisable();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void onPlay() {
        if (this.mSurfaceWidth != this.SCREEN_WIDTH && this.mSurfaceHeight != this.SCREEN_HEIGHT) {
            Log.w(TAG, "surface size not equal maximnum width and height");
            return;
        }
        reset();
        int[] bitmapSize = BitmapUtils.getBitmapSize(this.mPath, this.mOptions);
        this.mOriginalBpWidth = bitmapSize[0];
        this.mOriginalBpHeight = bitmapSize[1];
        Log.d(TAG, "play pic, path: " + this.mPath + " ,w: " + this.mOriginalBpWidth + " ,h: " + this.mOriginalBpHeight);
        updateSurface();
    }

    private void updateSurface() {
        this.mDecodeBitmap = BitmapUtils.decodeToBitmap(this.mPath, this.mOptions, this.SCREEN_WIDTH, this.SCREEN_HEIGHT);
        this.mDecodeNinetyAngleBitmap = BitmapUtils.createNinetyAngleBitmap(this.mDecodeBitmap);
        BitmapUtils.cropCenterBitmap(this.mSurfaceBitmap, this.mDecodeBitmap);
        BitmapUtils.cropCenterBitmap(this.mSurfaceNinetyAngleBitmap, this.mDecodeNinetyAngleBitmap);
        render(this.mSurfaceBitmap);
    }

    private void render(Bitmap bitmap) {
        int i;
        if (!BitmapUtils.isValidBitmap(bitmap)) {
            return;
        }
        if (!this.mUsePQ) {
            Canvas canvasLockHardwareCanvas = this.mHolder.lockHardwareCanvas();
            canvasLockHardwareCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvasLockHardwareCanvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            this.mHolder.unlockCanvasAndPost(canvasLockHardwareCanvas);
            return;
        }
        try {
            this.mDisplayHidlManager.SubDeviceSeamlessEnable();
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    return;
                }
                nativeDrawSurface(this.mSurface, this.SCREEN_WIDTH, this.SCREEN_HEIGHT, InputDeviceCompat.SOURCE_DPAD, this.mNV21);
                i = i2;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        this.mNV21 = BitmapUtils.fetchNV21(bitmap);
        nativeSetImageSurface(this.mSurface);
        i = 12;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0013  */
    /* JADX WARN: Code duplicated, block: B:12:0x0021  */
    private void transform() {
        Bitmap bitmapCreateTransformBitmap;
        int i = this.mRotation;
        if (i == 0) {
            bitmapCreateTransformBitmap = BitmapUtils.createTransformBitmap(this.mSurfaceBitmap, this.mScaleX, this.mScaleY, this.mRotation, false);
        } else if (i == 90) {
            bitmapCreateTransformBitmap = BitmapUtils.createTransformBitmap(this.mSurfaceNinetyAngleBitmap, this.mScaleX, this.mScaleY, this.mRotation - 90, false);
        } else if (i == 180) {
            bitmapCreateTransformBitmap = BitmapUtils.createTransformBitmap(this.mSurfaceBitmap, this.mScaleX, this.mScaleY, this.mRotation, false);
        } else if (i != 270) {
            bitmapCreateTransformBitmap = null;
        } else {
            bitmapCreateTransformBitmap = BitmapUtils.createTransformBitmap(this.mSurfaceNinetyAngleBitmap, this.mScaleX, this.mScaleY, this.mRotation - 90, false);
        }
        render(bitmapCreateTransformBitmap);
    }

    private void reset() {
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mRotation = 0;
        BitmapUtils.recycleBitmap(this.mDecodeBitmap);
        BitmapUtils.recycleBitmap(this.mDecodeNinetyAngleBitmap);
    }
}
