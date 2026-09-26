package android.support.v7.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompatBase;
import android.support.v7.appcompat.R;
import android.widget.RemoteViews;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(9)
@TargetApi(9)
class NotificationCompatImplBase {
    private static final int MAX_ACTION_BUTTONS = 3;
    static final int MAX_MEDIA_BUTTONS = 5;
    static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;

    public static float constrain(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return f > f3 ? f3 : f;
    }

    NotificationCompatImplBase() {
    }

    @RequiresApi(11)
    @TargetApi(11)
    public static <T extends NotificationCompatBase.Action> RemoteViews overrideContentViewMedia(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, List<T> list, int[] iArr, boolean z2, PendingIntent pendingIntent, boolean z3) {
        RemoteViews remoteViewsGenerateContentViewMedia = generateContentViewMedia(context, charSequence, charSequence2, charSequence3, i, bitmap, charSequence4, z, j, i2, list, iArr, z2, pendingIntent, z3);
        notificationBuilderWithBuilderAccessor.getBuilder().setContent(remoteViewsGenerateContentViewMedia);
        if (z2) {
            notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
        }
        return remoteViewsGenerateContentViewMedia;
    }

    @RequiresApi(11)
    @TargetApi(11)
    private static <T extends NotificationCompatBase.Action> RemoteViews generateContentViewMedia(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, List<T> list, int[] iArr, boolean z2, PendingIntent pendingIntent, boolean z3) {
        RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, i, 0, bitmap, charSequence4, z, j, i2, 0, z3 ? R.layout.notification_template_media_custom : R.layout.notification_template_media, true);
        int size = list.size();
        int iMin = iArr == null ? 0 : Math.min(iArr.length, 3);
        remoteViewsApplyStandardTemplate.removeAllViews(R.id.media_actions);
        if (iMin > 0) {
            for (int i3 = 0; i3 < iMin; i3++) {
                if (i3 >= size) {
                    throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i3), Integer.valueOf(size - 1)));
                }
                remoteViewsApplyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(context, list.get(iArr[i3])));
            }
        }
        if (z2) {
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.end_padder, 8);
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
            remoteViewsApplyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, pendingIntent);
            remoteViewsApplyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", context.getResources().getInteger(R.integer.cancel_button_image_alpha));
        } else {
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
        }
        return remoteViewsApplyStandardTemplate;
    }

    @RequiresApi(16)
    @TargetApi(16)
    public static <T extends NotificationCompatBase.Action> void overrideMediaBigContentView(Notification notification, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, int i3, List<T> list, boolean z2, PendingIntent pendingIntent, boolean z3) {
        notification.bigContentView = generateMediaBigView(context, charSequence, charSequence2, charSequence3, i, bitmap, charSequence4, z, j, i2, i3, list, z2, pendingIntent, z3);
        if (z2) {
            notification.flags |= 2;
        }
    }

    @RequiresApi(11)
    @TargetApi(11)
    public static <T extends NotificationCompatBase.Action> RemoteViews generateMediaBigView(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i2, int i3, List<T> list, boolean z2, PendingIntent pendingIntent, boolean z3) {
        int iMin = Math.min(list.size(), 5);
        RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, i, 0, bitmap, charSequence4, z, j, i2, i3, getBigMediaLayoutResource(z3, iMin), false);
        remoteViewsApplyStandardTemplate.removeAllViews(R.id.media_actions);
        if (iMin > 0) {
            for (int i4 = 0; i4 < iMin; i4++) {
                remoteViewsApplyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(context, list.get(i4)));
            }
        }
        if (z2) {
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
            remoteViewsApplyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", context.getResources().getInteger(R.integer.cancel_button_image_alpha));
            remoteViewsApplyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, pendingIntent);
        } else {
            remoteViewsApplyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
        }
        return remoteViewsApplyStandardTemplate;
    }

    @RequiresApi(11)
    @TargetApi(11)
    private static RemoteViews generateMediaActionButton(Context context, NotificationCompatBase.Action action) {
        boolean z = action.getActionIntent() == null;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_media_action);
        remoteViews.setImageViewResource(R.id.action0, action.getIcon());
        if (!z) {
            remoteViews.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
        }
        if (Build.VERSION.SDK_INT >= 15) {
            remoteViews.setContentDescription(R.id.action0, action.getTitle());
        }
        return remoteViews;
    }

    @RequiresApi(11)
    @TargetApi(11)
    private static int getBigMediaLayoutResource(boolean z, int i) {
        if (i <= 3) {
            return z ? R.layout.notification_template_big_media_narrow_custom : R.layout.notification_template_big_media_narrow;
        }
        return z ? R.layout.notification_template_big_media_custom : R.layout.notification_template_big_media;
    }

    public static RemoteViews applyStandardTemplateWithActions(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i3, int i4, int i5, boolean z2, ArrayList<android.support.v4.app.NotificationCompat.Action> arrayList) {
        boolean z3;
        int size;
        RemoteViews remoteViewsApplyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, i, i2, bitmap, charSequence4, z, j, i3, i4, i5, z2);
        remoteViewsApplyStandardTemplate.removeAllViews(R.id.actions);
        if (arrayList == null || (size = arrayList.size()) <= 0) {
            z3 = false;
        } else {
            if (size > 3) {
                size = 3;
            }
            for (int i6 = 0; i6 < size; i6++) {
                remoteViewsApplyStandardTemplate.addView(R.id.actions, generateActionButton(context, arrayList.get(i6)));
            }
            z3 = true;
        }
        int i7 = z3 ? 0 : 8;
        remoteViewsApplyStandardTemplate.setViewVisibility(R.id.actions, i7);
        remoteViewsApplyStandardTemplate.setViewVisibility(R.id.action_divider, i7);
        return remoteViewsApplyStandardTemplate;
    }

    private static RemoteViews generateActionButton(Context context, android.support.v4.app.NotificationCompat.Action action) {
        int actionLayoutResource;
        boolean z = action.actionIntent == null;
        String packageName = context.getPackageName();
        if (z) {
            actionLayoutResource = getActionTombstoneLayoutResource();
        } else {
            actionLayoutResource = getActionLayoutResource();
        }
        RemoteViews remoteViews = new RemoteViews(packageName, actionLayoutResource);
        remoteViews.setImageViewBitmap(R.id.action_image, createColoredBitmap(context, action.getIcon(), context.getResources().getColor(R.color.notification_action_color_filter)));
        remoteViews.setTextViewText(R.id.action_text, action.title);
        if (!z) {
            remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            remoteViews.setContentDescription(R.id.action_container, action.title);
        }
        return remoteViews;
    }

    private static Bitmap createColoredBitmap(Context context, int i, int i2) {
        return createColoredBitmap(context, i, i2, 0);
    }

    private static Bitmap createColoredBitmap(Context context, int i, int i2, int i3) {
        Drawable drawable = context.getResources().getDrawable(i);
        int intrinsicWidth = i3 == 0 ? drawable.getIntrinsicWidth() : i3;
        if (i3 == 0) {
            i3 = drawable.getIntrinsicHeight();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, i3, Bitmap.Config.ARGB_8888);
        drawable.setBounds(0, 0, intrinsicWidth, i3);
        if (i2 != 0) {
            drawable.mutate().setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
        }
        drawable.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    private static int getActionLayoutResource() {
        return R.layout.notification_action;
    }

    private static int getActionTombstoneLayoutResource() {
        return R.layout.notification_action_tombstone;
    }

    /* JADX WARN: Code duplicated, block: B:58:0x0167  */
    /* JADX WARN: Code duplicated, block: B:61:0x016c  */
    /* JADX WARN: Code duplicated, block: B:64:0x0174  */
    /* JADX WARN: Code duplicated, block: B:68:0x0193  */
    /* JADX WARN: Code duplicated, block: B:69:0x0195  */
    /* JADX WARN: Code duplicated, block: B:73:0x01ca A[PHI: r14
      0x01ca: PHI (r14v8 boolean) = (r14v7 boolean), (r14v11 boolean) binds: [B:67:0x0191, B:71:0x019b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:76:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:77:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:81:0x01da  */
    public static RemoteViews applyStandardTemplate(Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, int i2, Bitmap bitmap, CharSequence charSequence4, boolean z, long j, int i3, int i4, int i5, boolean z2) {
        boolean z3;
        boolean z4;
        boolean z5;
        int i6;
        boolean z6;
        int i7;
        int i8;
        Resources resources = context.getResources();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), i5);
        boolean z7 = i3 < -1;
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
            if (z7) {
                remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg_low);
                remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_low_bg);
            } else {
                remoteViews.setInt(R.id.notification_background, "setBackgroundResource", R.drawable.notification_bg);
                remoteViews.setInt(R.id.icon, "setBackgroundResource", R.drawable.notification_template_icon_bg);
            }
        }
        if (bitmap != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                remoteViews.setViewVisibility(R.id.icon, 0);
                remoteViews.setImageViewBitmap(R.id.icon, bitmap);
            } else {
                remoteViews.setViewVisibility(R.id.icon, 8);
            }
            if (i2 != 0) {
                int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_right_icon_size);
                int dimensionPixelSize2 = dimensionPixelSize - (resources.getDimensionPixelSize(R.dimen.notification_small_icon_background_padding) * 2);
                if (Build.VERSION.SDK_INT >= 21) {
                    remoteViews.setImageViewBitmap(R.id.right_icon, createIconWithBackground(context, i2, dimensionPixelSize, dimensionPixelSize2, i4));
                } else {
                    remoteViews.setImageViewBitmap(R.id.right_icon, createColoredBitmap(context, i2, -1));
                }
                remoteViews.setViewVisibility(R.id.right_icon, 0);
            }
        } else if (i2 != 0) {
            remoteViews.setViewVisibility(R.id.icon, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                remoteViews.setImageViewBitmap(R.id.icon, createIconWithBackground(context, i2, resources.getDimensionPixelSize(R.dimen.notification_large_icon_width) - resources.getDimensionPixelSize(R.dimen.notification_big_circle_margin), resources.getDimensionPixelSize(R.dimen.notification_small_icon_size_as_large), i4));
            } else {
                remoteViews.setImageViewBitmap(R.id.icon, createColoredBitmap(context, i2, -1));
            }
        }
        if (charSequence != null) {
            remoteViews.setTextViewText(R.id.title, charSequence);
        }
        if (charSequence2 != null) {
            remoteViews.setTextViewText(R.id.text, charSequence2);
            z3 = true;
        } else {
            z3 = false;
        }
        boolean z8 = Build.VERSION.SDK_INT < 21 && bitmap != null;
        if (charSequence3 != null) {
            remoteViews.setTextViewText(R.id.info, charSequence3);
            remoteViews.setViewVisibility(R.id.info, 0);
        } else {
            if (i > 0) {
                if (i > resources.getInteger(R.integer.status_bar_notification_info_maxnum)) {
                    remoteViews.setTextViewText(R.id.info, resources.getString(R.string.status_bar_notification_info_overflow));
                } else {
                    remoteViews.setTextViewText(R.id.info, NumberFormat.getIntegerInstance().format(i));
                }
                remoteViews.setViewVisibility(R.id.info, 0);
            } else {
                remoteViews.setViewVisibility(R.id.info, 8);
                z4 = z3;
                z5 = z8;
            }
            if (charSequence4 == null && Build.VERSION.SDK_INT >= 16) {
                remoteViews.setTextViewText(R.id.text, charSequence4);
                if (charSequence2 != null) {
                    remoteViews.setTextViewText(R.id.text2, charSequence2);
                    remoteViews.setViewVisibility(R.id.text2, 0);
                    z6 = true;
                    i6 = 8;
                } else {
                    i6 = 8;
                    remoteViews.setViewVisibility(R.id.text2, 8);
                }
                if (z6 && Build.VERSION.SDK_INT >= 16) {
                    if (z2) {
                        remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                    }
                    remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
                }
                if (j == 0) {
                    i7 = 0;
                } else if (!z && Build.VERSION.SDK_INT >= 16) {
                    remoteViews.setViewVisibility(R.id.chronometer, 0);
                    remoteViews.setLong(R.id.chronometer, "setBase", j + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    z5 = true;
                    remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
                    i7 = 0;
                } else {
                    z5 = true;
                    i7 = 0;
                    remoteViews.setViewVisibility(R.id.time, 0);
                    remoteViews.setLong(R.id.time, "setTime", j);
                }
                int i9 = R.id.right_side;
                if (z5) {
                    i8 = i7;
                } else {
                    i8 = i6;
                }
                remoteViews.setViewVisibility(i9, i8);
                int i10 = R.id.line3;
                if (!z4) {
                    i7 = i6;
                }
                remoteViews.setViewVisibility(i10, i7);
                return remoteViews;
            }
            i6 = 8;
            z6 = false;
            if (z6) {
                if (z2) {
                    remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
                }
                remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
            }
            if (j == 0) {
                i7 = 0;
            } else {
                if (!z) {
                }
                z5 = true;
                i7 = 0;
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", j);
            }
            int i11 = R.id.right_side;
            if (z5) {
                i8 = i7;
            } else {
                i8 = i6;
            }
            remoteViews.setViewVisibility(i11, i8);
            int i12 = R.id.line3;
            if (!z4) {
                i7 = i6;
            }
            remoteViews.setViewVisibility(i12, i7);
            return remoteViews;
        }
        z4 = true;
        z5 = true;
        if (charSequence4 == null) {
            i6 = 8;
            z6 = false;
        } else {
            i6 = 8;
            z6 = false;
        }
        if (z6) {
            if (z2) {
                remoteViews.setTextViewTextSize(R.id.text, 0, resources.getDimensionPixelSize(R.dimen.notification_subtext_size));
            }
            remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
        }
        if (j == 0) {
            i7 = 0;
        } else {
            if (!z) {
            }
            z5 = true;
            i7 = 0;
            remoteViews.setViewVisibility(R.id.time, 0);
            remoteViews.setLong(R.id.time, "setTime", j);
        }
        int i13 = R.id.right_side;
        if (z5) {
            i8 = i7;
        } else {
            i8 = i6;
        }
        remoteViews.setViewVisibility(i13, i8);
        int i14 = R.id.line3;
        if (!z4) {
            i7 = i6;
        }
        remoteViews.setViewVisibility(i14, i7);
        return remoteViews;
    }

    public static Bitmap createIconWithBackground(Context context, int i, int i2, int i3, int i4) {
        int i5 = R.drawable.notification_icon_background;
        if (i4 == 0) {
            i4 = 0;
        }
        Bitmap bitmapCreateColoredBitmap = createColoredBitmap(context, i5, i4, i2);
        Canvas canvas = new Canvas(bitmapCreateColoredBitmap);
        Drawable drawableMutate = context.getResources().getDrawable(i).mutate();
        drawableMutate.setFilterBitmap(true);
        int i6 = (i2 - i3) / 2;
        int i7 = i3 + i6;
        drawableMutate.setBounds(i6, i6, i7, i7);
        drawableMutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
        drawableMutate.draw(canvas);
        return bitmapCreateColoredBitmap;
    }

    public static void buildIntoRemoteViews(Context context, RemoteViews remoteViews, RemoteViews remoteViews2) {
        hideNormalContent(remoteViews);
        remoteViews.removeAllViews(R.id.notification_main_column);
        remoteViews.addView(R.id.notification_main_column, remoteViews2.clone());
        remoteViews.setViewVisibility(R.id.notification_main_column, 0);
        if (Build.VERSION.SDK_INT >= 21) {
            remoteViews.setViewPadding(R.id.notification_main_column_container, 0, calculateTopPadding(context), 0, 0);
        }
    }

    private static void hideNormalContent(RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.title, 8);
        remoteViews.setViewVisibility(R.id.text2, 8);
        remoteViews.setViewVisibility(R.id.text, 8);
    }

    public static int calculateTopPadding(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.notification_top_pad);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
        float fConstrain = (constrain(context.getResources().getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
        return Math.round(((1.0f - fConstrain) * dimensionPixelSize) + (fConstrain * dimensionPixelSize2));
    }
}
