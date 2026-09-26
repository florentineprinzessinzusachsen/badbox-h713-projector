package android.support.v17.leanback.widget;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GuidedAction extends Action {
    public static final long ACTION_ID_CANCEL = -5;
    public static final long ACTION_ID_CONTINUE = -7;
    public static final long ACTION_ID_CURRENT = -3;
    public static final long ACTION_ID_FINISH = -6;
    public static final long ACTION_ID_NEXT = -2;
    public static final long ACTION_ID_NO = -9;
    public static final long ACTION_ID_OK = -4;
    public static final long ACTION_ID_YES = -8;
    public static final int CHECKBOX_CHECK_SET_ID = -1;
    public static final int DEFAULT_CHECK_SET_ID = 1;
    static final int EDITING_ACTIVATOR_VIEW = 3;
    static final int EDITING_DESCRIPTION = 2;
    static final int EDITING_NONE = 0;
    static final int EDITING_TITLE = 1;
    public static final int NO_CHECK_SET = 0;
    static final int PF_AUTORESTORE = 64;
    static final int PF_CHECKED = 1;
    static final int PF_ENABLED = 16;
    static final int PF_FOCUSABLE = 32;
    static final int PF_HAS_NEXT = 4;
    static final int PF_INFO_ONLY = 8;
    static final int PF_MULTI_lINE_DESCRIPTION = 2;
    private static final String TAG = "GuidedAction";
    int mActionFlags;
    int mCheckSetId;
    int mDescriptionEditInputType;
    int mDescriptionInputType;
    private CharSequence mEditDescription;
    int mEditInputType;
    private CharSequence mEditTitle;
    int mEditable;
    int mInputType;
    Intent mIntent;
    List<GuidedAction> mSubActions;

    static final boolean isPasswordVariant(int i) {
        int i2 = i & 4080;
        return i2 == 128 || i2 == 144 || i2 == 224;
    }

    public static abstract class BuilderBase<B extends BuilderBase> {
        private Context mContext;
        private CharSequence mDescription;
        private CharSequence mEditDescription;
        private CharSequence mEditTitle;
        private Drawable mIcon;
        private long mId;
        private Intent mIntent;
        private List<GuidedAction> mSubActions;
        private CharSequence mTitle;
        private int mEditable = 0;
        private int mInputType = 1;
        private int mDescriptionInputType = 1;
        private int mEditInputType = 1;
        private int mDescriptionEditInputType = 1;
        private int mCheckSetId = 0;
        private int mActionFlags = 112;

        public BuilderBase(Context context) {
            this.mContext = context;
        }

        public Context getContext() {
            return this.mContext;
        }

        private void setFlags(int i, int i2) {
            this.mActionFlags = (i & i2) | (this.mActionFlags & (~i2));
        }

        protected final void applyValues(GuidedAction guidedAction) {
            guidedAction.setId(this.mId);
            guidedAction.setLabel1(this.mTitle);
            guidedAction.setEditTitle(this.mEditTitle);
            guidedAction.setLabel2(this.mDescription);
            guidedAction.setEditDescription(this.mEditDescription);
            guidedAction.setIcon(this.mIcon);
            guidedAction.mIntent = this.mIntent;
            guidedAction.mEditable = this.mEditable;
            guidedAction.mInputType = this.mInputType;
            guidedAction.mDescriptionInputType = this.mDescriptionInputType;
            guidedAction.mEditInputType = this.mEditInputType;
            guidedAction.mDescriptionEditInputType = this.mDescriptionEditInputType;
            guidedAction.mActionFlags = this.mActionFlags;
            guidedAction.mCheckSetId = this.mCheckSetId;
            guidedAction.mSubActions = this.mSubActions;
        }

        public B clickAction(long j) {
            if (j == -4) {
                this.mId = -4L;
                this.mTitle = this.mContext.getString(R.string.ok);
            } else if (j == -5) {
                this.mId = -5L;
                this.mTitle = this.mContext.getString(R.string.cancel);
            } else if (j == -6) {
                this.mId = -6L;
                this.mTitle = this.mContext.getString(android.support.v17.leanback.R.string.lb_guidedaction_finish_title);
            } else if (j == -7) {
                this.mId = -7L;
                this.mTitle = this.mContext.getString(android.support.v17.leanback.R.string.lb_guidedaction_continue_title);
            } else if (j == -8) {
                this.mId = -8L;
                this.mTitle = this.mContext.getString(R.string.yes);
            } else if (j == -9) {
                this.mId = -9L;
                this.mTitle = this.mContext.getString(R.string.no);
            }
            return this;
        }

        public B id(long j) {
            this.mId = j;
            return this;
        }

        public B title(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public B title(@StringRes int i) {
            this.mTitle = getContext().getString(i);
            return this;
        }

        public B editTitle(CharSequence charSequence) {
            this.mEditTitle = charSequence;
            return this;
        }

        public B editTitle(@StringRes int i) {
            this.mEditTitle = getContext().getString(i);
            return this;
        }

        public B description(CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public B description(@StringRes int i) {
            this.mDescription = getContext().getString(i);
            return this;
        }

        public B editDescription(CharSequence charSequence) {
            this.mEditDescription = charSequence;
            return this;
        }

        public B editDescription(@StringRes int i) {
            this.mEditDescription = getContext().getString(i);
            return this;
        }

        public B intent(Intent intent) {
            this.mIntent = intent;
            return this;
        }

        public B icon(Drawable drawable) {
            this.mIcon = drawable;
            return this;
        }

        @Deprecated
        public B iconResourceId(@DrawableRes int i, Context context) {
            return (B) icon(ContextCompat.getDrawable(context, i));
        }

        public B icon(@DrawableRes int i) {
            return (B) icon(ContextCompat.getDrawable(getContext(), i));
        }

        public B editable(boolean z) {
            if (!z) {
                if (this.mEditable == 1) {
                    this.mEditable = 0;
                }
                return this;
            }
            this.mEditable = 1;
            if (isChecked() || this.mCheckSetId != 0) {
                throw new IllegalArgumentException("Editable actions cannot also be checked");
            }
            return this;
        }

        public B descriptionEditable(boolean z) {
            if (!z) {
                if (this.mEditable == 2) {
                    this.mEditable = 0;
                }
                return this;
            }
            this.mEditable = 2;
            if (isChecked() || this.mCheckSetId != 0) {
                throw new IllegalArgumentException("Editable actions cannot also be checked");
            }
            return this;
        }

        public B hasEditableActivatorView(boolean z) {
            if (!z) {
                if (this.mEditable == 3) {
                    this.mEditable = 0;
                }
                return this;
            }
            this.mEditable = 3;
            if (isChecked() || this.mCheckSetId != 0) {
                throw new IllegalArgumentException("Editable actions cannot also be checked");
            }
            return this;
        }

        public B inputType(int i) {
            this.mInputType = i;
            return this;
        }

        public B descriptionInputType(int i) {
            this.mDescriptionInputType = i;
            return this;
        }

        public B editInputType(int i) {
            this.mEditInputType = i;
            return this;
        }

        public B descriptionEditInputType(int i) {
            this.mDescriptionEditInputType = i;
            return this;
        }

        private boolean isChecked() {
            return (this.mActionFlags & 1) == 1;
        }

        public B checked(boolean z) {
            setFlags(z ? 1 : 0, 1);
            if (this.mEditable != 0) {
                throw new IllegalArgumentException("Editable actions cannot also be checked");
            }
            return this;
        }

        public B checkSetId(int i) {
            this.mCheckSetId = i;
            if (this.mEditable != 0) {
                throw new IllegalArgumentException("Editable actions cannot also be in check sets");
            }
            return this;
        }

        public B multilineDescription(boolean z) {
            setFlags(z ? 2 : 0, 2);
            return this;
        }

        public B hasNext(boolean z) {
            setFlags(z ? 4 : 0, 4);
            return this;
        }

        public B infoOnly(boolean z) {
            setFlags(z ? 8 : 0, 8);
            return this;
        }

        public B enabled(boolean z) {
            setFlags(z ? 16 : 0, 16);
            return this;
        }

        public B focusable(boolean z) {
            setFlags(z ? 32 : 0, 32);
            return this;
        }

        public B subActions(List<GuidedAction> list) {
            this.mSubActions = list;
            return this;
        }

        public B autoSaveRestoreEnabled(boolean z) {
            setFlags(z ? 64 : 0, 64);
            return this;
        }
    }

    public static class Builder extends BuilderBase<Builder> {
        @Deprecated
        public Builder() {
            super(null);
        }

        public Builder(Context context) {
            super(context);
        }

        public GuidedAction build() {
            GuidedAction guidedAction = new GuidedAction();
            applyValues(guidedAction);
            return guidedAction;
        }
    }

    protected GuidedAction() {
        super(0L);
    }

    private void setFlags(int i, int i2) {
        this.mActionFlags = (i & i2) | (this.mActionFlags & (~i2));
    }

    public CharSequence getTitle() {
        return getLabel1();
    }

    public void setTitle(CharSequence charSequence) {
        setLabel1(charSequence);
    }

    public CharSequence getEditTitle() {
        return this.mEditTitle;
    }

    public void setEditTitle(CharSequence charSequence) {
        this.mEditTitle = charSequence;
    }

    public CharSequence getEditDescription() {
        return this.mEditDescription;
    }

    public void setEditDescription(CharSequence charSequence) {
        this.mEditDescription = charSequence;
    }

    public boolean isEditTitleUsed() {
        return this.mEditTitle != null;
    }

    public CharSequence getDescription() {
        return getLabel2();
    }

    public void setDescription(CharSequence charSequence) {
        setLabel2(charSequence);
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public boolean isEditable() {
        return this.mEditable == 1;
    }

    public boolean isDescriptionEditable() {
        return this.mEditable == 2;
    }

    public boolean hasTextEditable() {
        return this.mEditable == 1 || this.mEditable == 2;
    }

    public boolean hasEditableActivatorView() {
        return this.mEditable == 3;
    }

    public int getEditInputType() {
        return this.mEditInputType;
    }

    public int getDescriptionEditInputType() {
        return this.mDescriptionEditInputType;
    }

    public int getInputType() {
        return this.mInputType;
    }

    public int getDescriptionInputType() {
        return this.mDescriptionInputType;
    }

    public boolean isChecked() {
        return (this.mActionFlags & 1) == 1;
    }

    public void setChecked(boolean z) {
        setFlags(z ? 1 : 0, 1);
    }

    public int getCheckSetId() {
        return this.mCheckSetId;
    }

    public boolean hasMultilineDescription() {
        return (this.mActionFlags & 2) == 2;
    }

    public boolean isEnabled() {
        return (this.mActionFlags & 16) == 16;
    }

    public void setEnabled(boolean z) {
        setFlags(z ? 16 : 0, 16);
    }

    public boolean isFocusable() {
        return (this.mActionFlags & 32) == 32;
    }

    public void setFocusable(boolean z) {
        setFlags(z ? 32 : 0, 32);
    }

    public boolean hasNext() {
        return (this.mActionFlags & 4) == 4;
    }

    public boolean infoOnly() {
        return (this.mActionFlags & 8) == 8;
    }

    public void setSubActions(List<GuidedAction> list) {
        this.mSubActions = list;
    }

    public List<GuidedAction> getSubActions() {
        return this.mSubActions;
    }

    public boolean hasSubActions() {
        return this.mSubActions != null;
    }

    public final boolean isAutoSaveRestoreEnabled() {
        return (this.mActionFlags & 64) == 64;
    }

    public void onSaveInstanceState(Bundle bundle, String str) {
        if (needAutoSaveTitle() && getTitle() != null) {
            bundle.putString(str, getTitle().toString());
            return;
        }
        if (needAutoSaveDescription() && getDescription() != null) {
            bundle.putString(str, getDescription().toString());
        } else if (getCheckSetId() != 0) {
            bundle.putBoolean(str, isChecked());
        }
    }

    public void onRestoreInstanceState(Bundle bundle, String str) {
        if (needAutoSaveTitle()) {
            String string = bundle.getString(str);
            if (string != null) {
                setTitle(string);
                return;
            }
            return;
        }
        if (needAutoSaveDescription()) {
            String string2 = bundle.getString(str);
            if (string2 != null) {
                setDescription(string2);
                return;
            }
            return;
        }
        if (getCheckSetId() != 0) {
            setChecked(bundle.getBoolean(str, isChecked()));
        }
    }

    final boolean needAutoSaveTitle() {
        return isEditable() && !isPasswordVariant(getEditInputType());
    }

    final boolean needAutoSaveDescription() {
        return isDescriptionEditable() && !isPasswordVariant(getDescriptionEditInputType());
    }
}
