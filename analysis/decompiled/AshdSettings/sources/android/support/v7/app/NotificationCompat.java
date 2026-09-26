package android.support.v7.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.text.BidiFormatter;
import android.support.v7.appcompat.R;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.RemoteViews;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NotificationCompat extends android.support.v4.app.NotificationCompat {

    public static class DecoratedCustomViewStyle extends android.support.v4.app.NotificationCompat.Style {
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
    }

    public static MediaSessionCompat.Token getMediaSession(Notification notification) {
        Bundle extras = getExtras(notification);
        if (extras == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Parcelable parcelable = extras.getParcelable(android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
            if (parcelable != null) {
                return MediaSessionCompat.Token.fromToken(parcelable);
            }
            return null;
        }
        IBinder binder = BundleCompat.getBinder(extras, android.support.v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
        if (binder == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeStrongBinder(binder);
        parcelObtain.setDataPosition(0);
        MediaSessionCompat.Token tokenCreateFromParcel = MediaSessionCompat.Token.CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        return tokenCreateFromParcel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(24)
    @TargetApi(24)
    public static void addStyleToBuilderApi24(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof DecoratedCustomViewStyle) {
            NotificationCompatImpl24.addDecoratedCustomViewStyle(notificationBuilderWithBuilderAccessor);
        } else if (builder.mStyle instanceof DecoratedMediaCustomViewStyle) {
            NotificationCompatImpl24.addDecoratedMediaCustomViewStyle(notificationBuilderWithBuilderAccessor);
        } else {
            if (builder.mStyle instanceof android.support.v4.app.NotificationCompat.MessagingStyle) {
                return;
            }
            addStyleGetContentViewLollipop(notificationBuilderWithBuilderAccessor, builder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    @TargetApi(21)
    public static RemoteViews addStyleGetContentViewLollipop(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            MediaStyle mediaStyle = (MediaStyle) builder.mStyle;
            NotificationCompatImpl21.addMediaStyle(notificationBuilderWithBuilderAccessor, mediaStyle.mActionsToShowInCompact, mediaStyle.mToken != null ? mediaStyle.mToken.getToken() : null);
            boolean z = false;
            boolean z2 = builder.getContentView() != null;
            boolean z3 = Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 23;
            if (z2 || (z3 && builder.getBigContentView() != null)) {
                z = true;
            }
            if (!(builder.mStyle instanceof DecoratedMediaCustomViewStyle) || !z) {
                return null;
            }
            RemoteViews remoteViewsOverrideContentViewMedia = NotificationCompatImplBase.overrideContentViewMedia(notificationBuilderWithBuilderAccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.mActions, mediaStyle.mActionsToShowInCompact, false, null, z2);
            if (z2) {
                NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteViewsOverrideContentViewMedia, builder.getContentView());
            }
            setBackgroundColor(builder.mContext, remoteViewsOverrideContentViewMedia, builder.getColor());
            return remoteViewsOverrideContentViewMedia;
        }
        if (builder.mStyle instanceof DecoratedCustomViewStyle) {
            return getDecoratedContentView(builder);
        }
        return addStyleGetContentViewJellybean(notificationBuilderWithBuilderAccessor, builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(16)
    @TargetApi(16)
    public static RemoteViews addStyleGetContentViewJellybean(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof android.support.v4.app.NotificationCompat.MessagingStyle) {
            addMessagingFallBackStyle((android.support.v4.app.NotificationCompat.MessagingStyle) builder.mStyle, notificationBuilderWithBuilderAccessor, builder);
        }
        return addStyleGetContentViewIcs(notificationBuilderWithBuilderAccessor, builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static android.support.v4.app.NotificationCompat.MessagingStyle.Message findLatestIncomingMessage(android.support.v4.app.NotificationCompat.MessagingStyle messagingStyle) {
        List<android.support.v4.app.NotificationCompat.MessagingStyle.Message> messages = messagingStyle.getMessages();
        for (int size = messages.size() - 1; size >= 0; size--) {
            android.support.v4.app.NotificationCompat.MessagingStyle.Message message = messages.get(size);
            if (!TextUtils.isEmpty(message.getSender())) {
                return message;
            }
        }
        if (messages.isEmpty()) {
            return null;
        }
        return messages.get(messages.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CharSequence makeMessageLine(android.support.v4.app.NotificationCompat.Builder builder, android.support.v4.app.NotificationCompat.MessagingStyle messagingStyle, android.support.v4.app.NotificationCompat.MessagingStyle.Message message) {
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        boolean z = Build.VERSION.SDK_INT >= 21;
        int color = (z || Build.VERSION.SDK_INT <= 10) ? -16777216 : -1;
        CharSequence sender = message.getSender();
        if (TextUtils.isEmpty(message.getSender())) {
            sender = messagingStyle.getUserDisplayName() == null ? "" : messagingStyle.getUserDisplayName();
            if (z && builder.getColor() != 0) {
                color = builder.getColor();
            }
        }
        CharSequence charSequenceUnicodeWrap = bidiFormatter.unicodeWrap(sender);
        spannableStringBuilder.append(charSequenceUnicodeWrap);
        spannableStringBuilder.setSpan(makeFontColorSpan(color), spannableStringBuilder.length() - charSequenceUnicodeWrap.length(), spannableStringBuilder.length(), 33);
        spannableStringBuilder.append((CharSequence) "  ").append(bidiFormatter.unicodeWrap(message.getText() == null ? "" : message.getText()));
        return spannableStringBuilder;
    }

    private static TextAppearanceSpan makeFontColorSpan(int i) {
        return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i), null);
    }

    private static void addMessagingFallBackStyle(android.support.v4.app.NotificationCompat.MessagingStyle messagingStyle, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, android.support.v4.app.NotificationCompat.Builder builder) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        List<android.support.v4.app.NotificationCompat.MessagingStyle.Message> messages = messagingStyle.getMessages();
        boolean z = messagingStyle.getConversationTitle() != null || hasMessagesWithoutSender(messagingStyle.getMessages());
        for (int size = messages.size() - 1; size >= 0; size--) {
            android.support.v4.app.NotificationCompat.MessagingStyle.Message message = messages.get(size);
            CharSequence charSequenceMakeMessageLine = z ? makeMessageLine(builder, messagingStyle, message) : message.getText();
            if (size != messages.size() - 1) {
                spannableStringBuilder.insert(0, (CharSequence) "\n");
            }
            spannableStringBuilder.insert(0, charSequenceMakeMessageLine);
        }
        NotificationCompatImplJellybean.addBigTextStyle(notificationBuilderWithBuilderAccessor, spannableStringBuilder);
    }

    private static boolean hasMessagesWithoutSender(List<android.support.v4.app.NotificationCompat.MessagingStyle.Message> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).getSender() == null) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(14)
    @TargetApi(14)
    public static RemoteViews addStyleGetContentViewIcs(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            MediaStyle mediaStyle = (MediaStyle) builder.mStyle;
            boolean z = (builder.mStyle instanceof DecoratedMediaCustomViewStyle) && builder.getContentView() != null;
            RemoteViews remoteViewsOverrideContentViewMedia = NotificationCompatImplBase.overrideContentViewMedia(notificationBuilderWithBuilderAccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.mActions, mediaStyle.mActionsToShowInCompact, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent, z);
            if (!z) {
                return null;
            }
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteViewsOverrideContentViewMedia, builder.getContentView());
            return remoteViewsOverrideContentViewMedia;
        }
        if (builder.mStyle instanceof DecoratedCustomViewStyle) {
            return getDecoratedContentView(builder);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(16)
    @TargetApi(16)
    public static void addBigStyleToBuilderJellybean(Notification notification, android.support.v4.app.NotificationCompat.Builder builder) {
        RemoteViews contentView;
        if (builder.mStyle instanceof MediaStyle) {
            MediaStyle mediaStyle = (MediaStyle) builder.mStyle;
            if (builder.getBigContentView() != null) {
                contentView = builder.getBigContentView();
            } else {
                contentView = builder.getContentView();
            }
            boolean z = (builder.mStyle instanceof DecoratedMediaCustomViewStyle) && contentView != null;
            RemoteViews remoteViews = contentView;
            NotificationCompatImplBase.overrideMediaBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), 0, builder.mActions, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent, z);
            if (z) {
                NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notification.bigContentView, remoteViews);
                return;
            }
            return;
        }
        if (builder.mStyle instanceof DecoratedCustomViewStyle) {
            addDecoratedBigStyleToBuilderJellybean(notification, builder);
        }
    }

    private static RemoteViews getDecoratedContentView(android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.getContentView() == null) {
            return null;
        }
        RemoteViews remoteViewsApplyStandardTemplateWithActions = NotificationCompatImplBase.applyStandardTemplateWithActions(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mNotification.icon, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.getColor(), R.layout.notification_template_custom_big, false, null);
        NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteViewsApplyStandardTemplateWithActions, builder.getContentView());
        return remoteViewsApplyStandardTemplateWithActions;
    }

    @RequiresApi(16)
    @TargetApi(16)
    private static void addDecoratedBigStyleToBuilderJellybean(Notification notification, android.support.v4.app.NotificationCompat.Builder builder) {
        RemoteViews bigContentView = builder.getBigContentView();
        if (bigContentView == null) {
            bigContentView = builder.getContentView();
        }
        if (bigContentView == null) {
            return;
        }
        RemoteViews remoteViewsApplyStandardTemplateWithActions = NotificationCompatImplBase.applyStandardTemplateWithActions(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, notification.icon, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.getColor(), R.layout.notification_template_custom_big, false, builder.mActions);
        NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteViewsApplyStandardTemplateWithActions, bigContentView);
        notification.bigContentView = remoteViewsApplyStandardTemplateWithActions;
    }

    @RequiresApi(21)
    @TargetApi(21)
    private static void addDecoratedHeadsUpToBuilderLollipop(Notification notification, android.support.v4.app.NotificationCompat.Builder builder) {
        RemoteViews headsUpContentView = builder.getHeadsUpContentView();
        RemoteViews contentView = headsUpContentView != null ? headsUpContentView : builder.getContentView();
        if (headsUpContentView == null) {
            return;
        }
        RemoteViews remoteViewsApplyStandardTemplateWithActions = NotificationCompatImplBase.applyStandardTemplateWithActions(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, notification.icon, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), builder.getColor(), R.layout.notification_template_custom_big, false, builder.mActions);
        NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, remoteViewsApplyStandardTemplateWithActions, contentView);
        notification.headsUpContentView = remoteViewsApplyStandardTemplateWithActions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    @TargetApi(21)
    public static void addBigStyleToBuilderLollipop(Notification notification, android.support.v4.app.NotificationCompat.Builder builder) {
        RemoteViews contentView;
        if (builder.getBigContentView() != null) {
            contentView = builder.getBigContentView();
        } else {
            contentView = builder.getContentView();
        }
        RemoteViews remoteViews = contentView;
        if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && remoteViews != null) {
            NotificationCompatImplBase.overrideMediaBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), 0, builder.mActions, false, null, true);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notification.bigContentView, remoteViews);
            setBackgroundColor(builder.mContext, notification.bigContentView, builder.getColor());
        } else if (builder.mStyle instanceof DecoratedCustomViewStyle) {
            addDecoratedBigStyleToBuilderJellybean(notification, builder);
        }
    }

    private static void setBackgroundColor(Context context, RemoteViews remoteViews, int i) {
        if (i == 0) {
            i = context.getResources().getColor(R.color.notification_material_background_media_default_color);
        }
        remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(21)
    @TargetApi(21)
    public static void addHeadsUpToBuilderLollipop(Notification notification, android.support.v4.app.NotificationCompat.Builder builder) {
        RemoteViews contentView;
        if (builder.getHeadsUpContentView() != null) {
            contentView = builder.getHeadsUpContentView();
        } else {
            contentView = builder.getContentView();
        }
        if ((builder.mStyle instanceof DecoratedMediaCustomViewStyle) && contentView != null) {
            notification.headsUpContentView = NotificationCompatImplBase.generateMediaBigView(builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.getWhenIfShowing(), builder.getPriority(), 0, builder.mActions, false, null, true);
            NotificationCompatImplBase.buildIntoRemoteViews(builder.mContext, notification.headsUpContentView, contentView);
            setBackgroundColor(builder.mContext, notification.headsUpContentView, builder.getColor());
        } else if (builder.mStyle instanceof DecoratedCustomViewStyle) {
            addDecoratedHeadsUpToBuilderLollipop(notification, builder);
        }
    }

    public static class Builder extends android.support.v4.app.NotificationCompat.Builder {
        public Builder(Context context) {
            super(context);
        }

        @Override // android.support.v4.app.NotificationCompat.Builder
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected CharSequence resolveText() {
            if (this.mStyle instanceof android.support.v4.app.NotificationCompat.MessagingStyle) {
                android.support.v4.app.NotificationCompat.MessagingStyle messagingStyle = (android.support.v4.app.NotificationCompat.MessagingStyle) this.mStyle;
                android.support.v4.app.NotificationCompat.MessagingStyle.Message messageFindLatestIncomingMessage = NotificationCompat.findLatestIncomingMessage(messagingStyle);
                CharSequence conversationTitle = messagingStyle.getConversationTitle();
                if (messageFindLatestIncomingMessage != null) {
                    if (conversationTitle != null) {
                        return NotificationCompat.makeMessageLine(this, messagingStyle, messageFindLatestIncomingMessage);
                    }
                    return messageFindLatestIncomingMessage.getText();
                }
            }
            return super.resolveText();
        }

        @Override // android.support.v4.app.NotificationCompat.Builder
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected CharSequence resolveTitle() {
            if (this.mStyle instanceof android.support.v4.app.NotificationCompat.MessagingStyle) {
                android.support.v4.app.NotificationCompat.MessagingStyle messagingStyle = (android.support.v4.app.NotificationCompat.MessagingStyle) this.mStyle;
                android.support.v4.app.NotificationCompat.MessagingStyle.Message messageFindLatestIncomingMessage = NotificationCompat.findLatestIncomingMessage(messagingStyle);
                CharSequence conversationTitle = messagingStyle.getConversationTitle();
                if (conversationTitle != null || messageFindLatestIncomingMessage != null) {
                    return conversationTitle != null ? conversationTitle : messageFindLatestIncomingMessage.getSender();
                }
            }
            return super.resolveTitle();
        }

        @Override // android.support.v4.app.NotificationCompat.Builder
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected android.support.v4.app.NotificationCompat.BuilderExtender getExtender() {
            if (Build.VERSION.SDK_INT >= 24) {
                return new Api24Extender();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                return new LollipopExtender();
            }
            if (Build.VERSION.SDK_INT >= 16) {
                return new JellybeanExtender();
            }
            if (Build.VERSION.SDK_INT >= 14) {
                return new IceCreamSandwichExtender();
            }
            return super.getExtender();
        }
    }

    private static class IceCreamSandwichExtender extends android.support.v4.app.NotificationCompat.BuilderExtender {
        IceCreamSandwichExtender() {
        }

        @Override // android.support.v4.app.NotificationCompat.BuilderExtender
        public Notification build(android.support.v4.app.NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViewsAddStyleGetContentViewIcs = NotificationCompat.addStyleGetContentViewIcs(notificationBuilderWithBuilderAccessor, builder);
            Notification notificationBuild = notificationBuilderWithBuilderAccessor.build();
            if (remoteViewsAddStyleGetContentViewIcs != null) {
                notificationBuild.contentView = remoteViewsAddStyleGetContentViewIcs;
            } else if (builder.getContentView() != null) {
                notificationBuild.contentView = builder.getContentView();
            }
            return notificationBuild;
        }
    }

    private static class JellybeanExtender extends android.support.v4.app.NotificationCompat.BuilderExtender {
        JellybeanExtender() {
        }

        @Override // android.support.v4.app.NotificationCompat.BuilderExtender
        public Notification build(android.support.v4.app.NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViewsAddStyleGetContentViewJellybean = NotificationCompat.addStyleGetContentViewJellybean(notificationBuilderWithBuilderAccessor, builder);
            Notification notificationBuild = notificationBuilderWithBuilderAccessor.build();
            if (remoteViewsAddStyleGetContentViewJellybean != null) {
                notificationBuild.contentView = remoteViewsAddStyleGetContentViewJellybean;
            }
            NotificationCompat.addBigStyleToBuilderJellybean(notificationBuild, builder);
            return notificationBuild;
        }
    }

    private static class LollipopExtender extends android.support.v4.app.NotificationCompat.BuilderExtender {
        LollipopExtender() {
        }

        @Override // android.support.v4.app.NotificationCompat.BuilderExtender
        public Notification build(android.support.v4.app.NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViewsAddStyleGetContentViewLollipop = NotificationCompat.addStyleGetContentViewLollipop(notificationBuilderWithBuilderAccessor, builder);
            Notification notificationBuild = notificationBuilderWithBuilderAccessor.build();
            if (remoteViewsAddStyleGetContentViewLollipop != null) {
                notificationBuild.contentView = remoteViewsAddStyleGetContentViewLollipop;
            }
            NotificationCompat.addBigStyleToBuilderLollipop(notificationBuild, builder);
            NotificationCompat.addHeadsUpToBuilderLollipop(notificationBuild, builder);
            return notificationBuild;
        }
    }

    private static class Api24Extender extends android.support.v4.app.NotificationCompat.BuilderExtender {
        private Api24Extender() {
        }

        @Override // android.support.v4.app.NotificationCompat.BuilderExtender
        public Notification build(android.support.v4.app.NotificationCompat.Builder builder, NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addStyleToBuilderApi24(notificationBuilderWithBuilderAccessor, builder);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }

    public static class MediaStyle extends android.support.v4.app.NotificationCompat.Style {
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;

        public MediaStyle() {
        }

        public MediaStyle(android.support.v4.app.NotificationCompat.Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.mActionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            this.mShowCancelButton = z;
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }
    }
}
