package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final PorterDuff.Mode f734b = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static e f735c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private u f736a;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    static class a implements u.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f737a = {R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f738b = {R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f739c = {R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl_dark, R$drawable.abc_text_select_handle_middle_mtrl_dark, R$drawable.abc_text_select_handle_right_mtrl_dark, R$drawable.abc_text_select_handle_left_mtrl_light, R$drawable.abc_text_select_handle_middle_mtrl_light, R$drawable.abc_text_select_handle_right_mtrl_light};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int[] f740d = {R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int[] f741e = {R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final int[] f742f = {R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};

        a() {
        }

        private ColorStateList a(Context context) {
            return b(context, 0);
        }

        private ColorStateList b(Context context) {
            return b(context, y.b(context, R$attr.colorAccent));
        }

        private ColorStateList c(Context context) {
            return b(context, y.b(context, R$attr.colorButtonNormal));
        }

        private ColorStateList d(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            ColorStateList colorStateListC = y.c(context, R$attr.colorSwitchThumbNormal);
            if (colorStateListC == null || !colorStateListC.isStateful()) {
                iArr[0] = y.f846b;
                iArr2[0] = y.a(context, R$attr.colorSwitchThumbNormal);
                iArr[1] = y.f849e;
                iArr2[1] = y.b(context, R$attr.colorControlActivated);
                iArr[2] = y.f850f;
                iArr2[2] = y.b(context, R$attr.colorSwitchThumbNormal);
            } else {
                iArr[0] = y.f846b;
                iArr2[0] = colorStateListC.getColorForState(iArr[0], 0);
                iArr[1] = y.f849e;
                iArr2[1] = y.b(context, R$attr.colorControlActivated);
                iArr[2] = y.f850f;
                iArr2[2] = colorStateListC.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        @Override // androidx.appcompat.widget.u.e
        public Drawable a(u uVar, Context context, int i) {
            if (i == R$drawable.abc_cab_background_top_material) {
                return new LayerDrawable(new Drawable[]{uVar.a(context, R$drawable.abc_cab_background_internal_bg), uVar.a(context, R$drawable.abc_cab_background_top_mtrl_alpha)});
            }
            return null;
        }

        private ColorStateList b(Context context, int i) {
            int iB = y.b(context, R$attr.colorControlHighlight);
            return new ColorStateList(new int[][]{y.f846b, y.f848d, y.f847c, y.f850f}, new int[]{y.a(context, R$attr.colorButtonNormal), androidx.core.a.a.b(iB, i), androidx.core.a.a.b(iB, i), i});
        }

        private void a(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (p.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = e.f734b;
            }
            drawable.setColorFilter(e.a(i, mode));
        }

        private boolean a(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.appcompat.widget.u.e
        public ColorStateList a(Context context, int i) {
            if (i == R$drawable.abc_edit_text_material) {
                return androidx.appcompat.a.a.a.b(context, R$color.abc_tint_edittext);
            }
            if (i == R$drawable.abc_switch_track_mtrl_alpha) {
                return androidx.appcompat.a.a.a.b(context, R$color.abc_tint_switch_track);
            }
            if (i == R$drawable.abc_switch_thumb_material) {
                return d(context);
            }
            if (i == R$drawable.abc_btn_default_mtrl_shape) {
                return c(context);
            }
            if (i == R$drawable.abc_btn_borderless_material) {
                return a(context);
            }
            if (i == R$drawable.abc_btn_colored_material) {
                return b(context);
            }
            if (i != R$drawable.abc_spinner_mtrl_am_alpha && i != R$drawable.abc_spinner_textfield_background_material) {
                if (a(this.f738b, i)) {
                    return y.c(context, R$attr.colorControlNormal);
                }
                if (a(this.f741e, i)) {
                    return androidx.appcompat.a.a.a.b(context, R$color.abc_tint_default);
                }
                if (a(this.f742f, i)) {
                    return androidx.appcompat.a.a.a.b(context, R$color.abc_tint_btn_checkable);
                }
                if (i == R$drawable.abc_seekbar_thumb_material) {
                    return androidx.appcompat.a.a.a.b(context, R$color.abc_tint_seek_thumb);
                }
                return null;
            }
            return androidx.appcompat.a.a.a.b(context, R$color.abc_tint_spinner);
        }

        @Override // androidx.appcompat.widget.u.e
        public boolean b(Context context, int i, Drawable drawable) {
            if (i == R$drawable.abc_seekbar_track_material) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                a(layerDrawable.findDrawableByLayerId(R.id.background), y.b(context, R$attr.colorControlNormal), e.f734b);
                a(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), y.b(context, R$attr.colorControlNormal), e.f734b);
                a(layerDrawable.findDrawableByLayerId(R.id.progress), y.b(context, R$attr.colorControlActivated), e.f734b);
                return true;
            }
            if (i != R$drawable.abc_ratingbar_material && i != R$drawable.abc_ratingbar_indicator_material && i != R$drawable.abc_ratingbar_small_material) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            a(layerDrawable2.findDrawableByLayerId(R.id.background), y.a(context, R$attr.colorControlNormal), e.f734b);
            a(layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress), y.b(context, R$attr.colorControlActivated), e.f734b);
            a(layerDrawable2.findDrawableByLayerId(R.id.progress), y.b(context, R$attr.colorControlActivated), e.f734b);
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:20:0x004b  */
        /* JADX WARN: Code duplicated, block: B:22:0x0051  */
        /* JADX WARN: Code duplicated, block: B:25:0x0062  */
        /* JADX WARN: Code duplicated, block: B:27:0x0066 A[RETURN] */
        @Override // androidx.appcompat.widget.u.e
        public boolean a(Context context, int i, Drawable drawable) {
            PorterDuff.Mode mode;
            boolean z;
            int iRound;
            PorterDuff.Mode mode2 = e.f734b;
            boolean zA = a(this.f737a, i);
            int i2 = R.attr.colorBackground;
            if (zA) {
                i2 = R$attr.colorControlNormal;
            } else if (a(this.f739c, i)) {
                i2 = R$attr.colorControlActivated;
            } else if (a(this.f740d, i)) {
                mode2 = PorterDuff.Mode.MULTIPLY;
            } else {
                if (i == R$drawable.abc_list_divider_mtrl_alpha) {
                    i2 = R.attr.colorForeground;
                    mode = mode2;
                    iRound = Math.round(40.8f);
                    z = true;
                } else if (i != R$drawable.abc_dialog_material_background) {
                    mode = mode2;
                    z = false;
                    iRound = -1;
                    i2 = 0;
                }
                if (z) {
                    return false;
                }
                if (p.a(drawable)) {
                    drawable = drawable.mutate();
                }
                drawable.setColorFilter(e.a(y.b(context, i2), mode));
                if (iRound != -1) {
                    drawable.setAlpha(iRound);
                }
                return true;
            }
            mode = mode2;
            z = true;
            iRound = -1;
            if (z) {
                return false;
            }
            if (p.a(drawable)) {
                drawable = drawable.mutate();
            }
            drawable.setColorFilter(e.a(y.b(context, i2), mode));
            if (iRound != -1) {
                drawable.setAlpha(iRound);
            }
            return true;
        }

        @Override // androidx.appcompat.widget.u.e
        public PorterDuff.Mode a(int i) {
            if (i == R$drawable.abc_switch_thumb_material) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized e b() {
        if (f735c == null) {
            c();
        }
        return f735c;
    }

    public static synchronized void c() {
        if (f735c == null) {
            f735c = new e();
            f735c.f736a = u.a();
            f735c.f736a.a(new a());
        }
    }

    public synchronized Drawable a(Context context, int i) {
        return this.f736a.a(context, i);
    }

    synchronized Drawable a(Context context, int i, boolean z) {
        return this.f736a.a(context, i, z);
    }

    public synchronized void a(Context context) {
        this.f736a.a(context);
    }

    synchronized ColorStateList b(Context context, int i) {
        return this.f736a.b(context, i);
    }

    static void a(Drawable drawable, b0 b0Var, int[] iArr) {
        u.a(drawable, b0Var, iArr);
    }

    public static synchronized PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
        return u.a(i, mode);
    }
}
