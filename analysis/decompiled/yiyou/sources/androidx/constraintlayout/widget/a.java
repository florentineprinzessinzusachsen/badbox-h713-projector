package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ConstraintSet.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f874b = {0, 4, 8};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SparseIntArray f875c = new SparseIntArray();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<Integer, b> f876a = new HashMap<>();

    /* JADX INFO: compiled from: ConstraintSet.java */
    private static class b {
        public int A;
        public int B;
        public int C;
        public int D;
        public int E;
        public int F;
        public int G;
        public int H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public int N;
        public int O;
        public int P;
        public float Q;
        public float R;
        public int S;
        public int T;
        public float U;
        public boolean V;
        public float W;
        public float X;
        public float Y;
        public float Z;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f877a;
        public float a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f878b;
        public float b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f879c;
        public float c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f880d;
        public float d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f881e;
        public float e0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f882f;
        public float f0;
        public float g;
        public float g0;
        public int h;
        public boolean h0;
        public int i;
        public boolean i0;
        public int j;
        public int j0;
        public int k;
        public int k0;
        public int l;
        public int l0;
        public int m;
        public int m0;
        public int n;
        public int n0;
        public int o;
        public int o0;
        public int p;
        public float p0;
        public int q;
        public float q0;
        public int r;
        public boolean r0;
        public int s;
        public int s0;
        public int t;
        public int t0;
        public float u;
        public int[] u0;
        public float v;
        public String v0;
        public String w;
        public int x;
        public int y;
        public float z;

        private b() {
            this.f877a = false;
            this.f881e = -1;
            this.f882f = -1;
            this.g = -1.0f;
            this.h = -1;
            this.i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = -1;
            this.o = -1;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = 0.5f;
            this.v = 0.5f;
            this.w = null;
            this.x = -1;
            this.y = 0;
            this.z = 0.0f;
            this.A = -1;
            this.B = -1;
            this.C = -1;
            this.D = -1;
            this.E = -1;
            this.F = -1;
            this.G = -1;
            this.H = -1;
            this.I = -1;
            this.J = 0;
            this.K = -1;
            this.L = -1;
            this.M = -1;
            this.N = -1;
            this.O = -1;
            this.P = -1;
            this.Q = 0.0f;
            this.R = 0.0f;
            this.S = 0;
            this.T = 0;
            this.U = 1.0f;
            this.V = false;
            this.W = 0.0f;
            this.X = 0.0f;
            this.Y = 0.0f;
            this.Z = 0.0f;
            this.a0 = 1.0f;
            this.b0 = 1.0f;
            this.c0 = Float.NaN;
            this.d0 = Float.NaN;
            this.e0 = 0.0f;
            this.f0 = 0.0f;
            this.g0 = 0.0f;
            this.h0 = false;
            this.i0 = false;
            this.j0 = 0;
            this.k0 = 0;
            this.l0 = -1;
            this.m0 = -1;
            this.n0 = -1;
            this.o0 = -1;
            this.p0 = 1.0f;
            this.q0 = 1.0f;
            this.r0 = false;
            this.s0 = -1;
            this.t0 = -1;
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public b m2clone() {
            b bVar = new b();
            bVar.f877a = this.f877a;
            bVar.f878b = this.f878b;
            bVar.f879c = this.f879c;
            bVar.f881e = this.f881e;
            bVar.f882f = this.f882f;
            bVar.g = this.g;
            bVar.h = this.h;
            bVar.i = this.i;
            bVar.j = this.j;
            bVar.k = this.k;
            bVar.l = this.l;
            bVar.m = this.m;
            bVar.n = this.n;
            bVar.o = this.o;
            bVar.p = this.p;
            bVar.q = this.q;
            bVar.r = this.r;
            bVar.s = this.s;
            bVar.t = this.t;
            bVar.u = this.u;
            bVar.v = this.v;
            bVar.w = this.w;
            bVar.A = this.A;
            bVar.B = this.B;
            bVar.u = this.u;
            bVar.u = this.u;
            bVar.u = this.u;
            bVar.u = this.u;
            bVar.u = this.u;
            bVar.C = this.C;
            bVar.D = this.D;
            bVar.E = this.E;
            bVar.F = this.F;
            bVar.G = this.G;
            bVar.H = this.H;
            bVar.I = this.I;
            bVar.J = this.J;
            bVar.K = this.K;
            bVar.L = this.L;
            bVar.M = this.M;
            bVar.N = this.N;
            bVar.O = this.O;
            bVar.P = this.P;
            bVar.Q = this.Q;
            bVar.R = this.R;
            bVar.S = this.S;
            bVar.T = this.T;
            bVar.U = this.U;
            bVar.V = this.V;
            bVar.W = this.W;
            bVar.X = this.X;
            bVar.Y = this.Y;
            bVar.Z = this.Z;
            bVar.a0 = this.a0;
            bVar.b0 = this.b0;
            bVar.c0 = this.c0;
            bVar.d0 = this.d0;
            bVar.e0 = this.e0;
            bVar.f0 = this.f0;
            bVar.g0 = this.g0;
            bVar.h0 = this.h0;
            bVar.i0 = this.i0;
            bVar.j0 = this.j0;
            bVar.k0 = this.k0;
            bVar.l0 = this.l0;
            bVar.m0 = this.m0;
            bVar.n0 = this.n0;
            bVar.o0 = this.o0;
            bVar.p0 = this.p0;
            bVar.q0 = this.q0;
            bVar.s0 = this.s0;
            bVar.t0 = this.t0;
            int[] iArr = this.u0;
            if (iArr != null) {
                bVar.u0 = Arrays.copyOf(iArr, iArr.length);
            }
            bVar.x = this.x;
            bVar.y = this.y;
            bVar.z = this.z;
            bVar.r0 = this.r0;
            return bVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(ConstraintHelper constraintHelper, int i, Constraints.a aVar) {
            a(i, aVar);
            if (constraintHelper instanceof Barrier) {
                this.t0 = 1;
                Barrier barrier = (Barrier) constraintHelper;
                this.s0 = barrier.getType();
                this.u0 = barrier.getReferencedIds();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, Constraints.a aVar) {
            a(i, (ConstraintLayout.a) aVar);
            this.U = aVar.m0;
            this.X = aVar.p0;
            this.Y = aVar.q0;
            this.Z = aVar.r0;
            this.a0 = aVar.s0;
            this.b0 = aVar.t0;
            this.c0 = aVar.u0;
            this.d0 = aVar.v0;
            this.e0 = aVar.w0;
            this.f0 = aVar.x0;
            this.g0 = aVar.y0;
            this.W = aVar.o0;
            this.V = aVar.n0;
        }

        private void a(int i, ConstraintLayout.a aVar) {
            this.f880d = i;
            this.h = aVar.f866d;
            this.i = aVar.f867e;
            this.j = aVar.f868f;
            this.k = aVar.g;
            this.l = aVar.h;
            this.m = aVar.i;
            this.n = aVar.j;
            this.o = aVar.k;
            this.p = aVar.l;
            this.q = aVar.p;
            this.r = aVar.q;
            this.s = aVar.r;
            this.t = aVar.s;
            this.u = aVar.z;
            this.v = aVar.A;
            this.w = aVar.B;
            this.x = aVar.m;
            this.y = aVar.n;
            this.z = aVar.o;
            this.A = aVar.P;
            this.B = aVar.Q;
            this.C = aVar.R;
            this.g = aVar.f865c;
            this.f881e = aVar.f863a;
            this.f882f = aVar.f864b;
            this.f878b = ((ViewGroup.MarginLayoutParams) aVar).width;
            this.f879c = ((ViewGroup.MarginLayoutParams) aVar).height;
            this.D = ((ViewGroup.MarginLayoutParams) aVar).leftMargin;
            this.E = ((ViewGroup.MarginLayoutParams) aVar).rightMargin;
            this.F = ((ViewGroup.MarginLayoutParams) aVar).topMargin;
            this.G = ((ViewGroup.MarginLayoutParams) aVar).bottomMargin;
            this.Q = aVar.E;
            this.R = aVar.D;
            this.T = aVar.G;
            this.S = aVar.F;
            boolean z = aVar.S;
            this.h0 = z;
            this.i0 = aVar.T;
            this.j0 = aVar.H;
            this.k0 = aVar.I;
            this.h0 = z;
            this.l0 = aVar.L;
            this.m0 = aVar.M;
            this.n0 = aVar.J;
            this.o0 = aVar.K;
            this.p0 = aVar.N;
            this.q0 = aVar.O;
            if (Build.VERSION.SDK_INT >= 17) {
                this.H = aVar.getMarginEnd();
                this.I = aVar.getMarginStart();
            }
        }

        public void a(ConstraintLayout.a aVar) {
            aVar.f866d = this.h;
            aVar.f867e = this.i;
            aVar.f868f = this.j;
            aVar.g = this.k;
            aVar.h = this.l;
            aVar.i = this.m;
            aVar.j = this.n;
            aVar.k = this.o;
            aVar.l = this.p;
            aVar.p = this.q;
            aVar.q = this.r;
            aVar.r = this.s;
            aVar.s = this.t;
            ((ViewGroup.MarginLayoutParams) aVar).leftMargin = this.D;
            ((ViewGroup.MarginLayoutParams) aVar).rightMargin = this.E;
            ((ViewGroup.MarginLayoutParams) aVar).topMargin = this.F;
            ((ViewGroup.MarginLayoutParams) aVar).bottomMargin = this.G;
            aVar.x = this.P;
            aVar.y = this.O;
            aVar.z = this.u;
            aVar.A = this.v;
            aVar.m = this.x;
            aVar.n = this.y;
            aVar.o = this.z;
            aVar.B = this.w;
            aVar.P = this.A;
            aVar.Q = this.B;
            aVar.E = this.Q;
            aVar.D = this.R;
            aVar.G = this.T;
            aVar.F = this.S;
            aVar.S = this.h0;
            aVar.T = this.i0;
            aVar.H = this.j0;
            aVar.I = this.k0;
            aVar.L = this.l0;
            aVar.M = this.m0;
            aVar.J = this.n0;
            aVar.K = this.o0;
            aVar.N = this.p0;
            aVar.O = this.q0;
            aVar.R = this.C;
            aVar.f865c = this.g;
            aVar.f863a = this.f881e;
            aVar.f864b = this.f882f;
            ((ViewGroup.MarginLayoutParams) aVar).width = this.f878b;
            ((ViewGroup.MarginLayoutParams) aVar).height = this.f879c;
            if (Build.VERSION.SDK_INT >= 17) {
                aVar.setMarginStart(this.I);
                aVar.setMarginEnd(this.H);
            }
            aVar.a();
        }
    }

    static {
        f875c.append(R$styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        f875c.append(R$styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        f875c.append(R$styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        f875c.append(R$styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
        f875c.append(R$styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
        f875c.append(R$styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        f875c.append(R$styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        f875c.append(R$styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        f875c.append(R$styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        f875c.append(R$styleable.ConstraintSet_layout_editor_absoluteX, 6);
        f875c.append(R$styleable.ConstraintSet_layout_editor_absoluteY, 7);
        f875c.append(R$styleable.ConstraintSet_layout_constraintGuide_begin, 17);
        f875c.append(R$styleable.ConstraintSet_layout_constraintGuide_end, 18);
        f875c.append(R$styleable.ConstraintSet_layout_constraintGuide_percent, 19);
        f875c.append(R$styleable.ConstraintSet_android_orientation, 27);
        f875c.append(R$styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
        f875c.append(R$styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
        f875c.append(R$styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        f875c.append(R$styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        f875c.append(R$styleable.ConstraintSet_layout_goneMarginLeft, 13);
        f875c.append(R$styleable.ConstraintSet_layout_goneMarginTop, 16);
        f875c.append(R$styleable.ConstraintSet_layout_goneMarginRight, 14);
        f875c.append(R$styleable.ConstraintSet_layout_goneMarginBottom, 11);
        f875c.append(R$styleable.ConstraintSet_layout_goneMarginStart, 15);
        f875c.append(R$styleable.ConstraintSet_layout_goneMarginEnd, 12);
        f875c.append(R$styleable.ConstraintSet_layout_constraintVertical_weight, 40);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        f875c.append(R$styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
        f875c.append(R$styleable.ConstraintSet_layout_constraintVertical_bias, 37);
        f875c.append(R$styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
        f875c.append(R$styleable.ConstraintSet_layout_constraintLeft_creator, 75);
        f875c.append(R$styleable.ConstraintSet_layout_constraintTop_creator, 75);
        f875c.append(R$styleable.ConstraintSet_layout_constraintRight_creator, 75);
        f875c.append(R$styleable.ConstraintSet_layout_constraintBottom_creator, 75);
        f875c.append(R$styleable.ConstraintSet_layout_constraintBaseline_creator, 75);
        f875c.append(R$styleable.ConstraintSet_android_layout_marginLeft, 24);
        f875c.append(R$styleable.ConstraintSet_android_layout_marginRight, 28);
        f875c.append(R$styleable.ConstraintSet_android_layout_marginStart, 31);
        f875c.append(R$styleable.ConstraintSet_android_layout_marginEnd, 8);
        f875c.append(R$styleable.ConstraintSet_android_layout_marginTop, 34);
        f875c.append(R$styleable.ConstraintSet_android_layout_marginBottom, 2);
        f875c.append(R$styleable.ConstraintSet_android_layout_width, 23);
        f875c.append(R$styleable.ConstraintSet_android_layout_height, 21);
        f875c.append(R$styleable.ConstraintSet_android_visibility, 22);
        f875c.append(R$styleable.ConstraintSet_android_alpha, 43);
        f875c.append(R$styleable.ConstraintSet_android_elevation, 44);
        f875c.append(R$styleable.ConstraintSet_android_rotationX, 45);
        f875c.append(R$styleable.ConstraintSet_android_rotationY, 46);
        f875c.append(R$styleable.ConstraintSet_android_rotation, 60);
        f875c.append(R$styleable.ConstraintSet_android_scaleX, 47);
        f875c.append(R$styleable.ConstraintSet_android_scaleY, 48);
        f875c.append(R$styleable.ConstraintSet_android_transformPivotX, 49);
        f875c.append(R$styleable.ConstraintSet_android_transformPivotY, 50);
        f875c.append(R$styleable.ConstraintSet_android_translationX, 51);
        f875c.append(R$styleable.ConstraintSet_android_translationY, 52);
        f875c.append(R$styleable.ConstraintSet_android_translationZ, 53);
        f875c.append(R$styleable.ConstraintSet_layout_constraintWidth_default, 54);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHeight_default, 55);
        f875c.append(R$styleable.ConstraintSet_layout_constraintWidth_max, 56);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHeight_max, 57);
        f875c.append(R$styleable.ConstraintSet_layout_constraintWidth_min, 58);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHeight_min, 59);
        f875c.append(R$styleable.ConstraintSet_layout_constraintCircle, 61);
        f875c.append(R$styleable.ConstraintSet_layout_constraintCircleRadius, 62);
        f875c.append(R$styleable.ConstraintSet_layout_constraintCircleAngle, 63);
        f875c.append(R$styleable.ConstraintSet_android_id, 38);
        f875c.append(R$styleable.ConstraintSet_layout_constraintWidth_percent, 69);
        f875c.append(R$styleable.ConstraintSet_layout_constraintHeight_percent, 70);
        f875c.append(R$styleable.ConstraintSet_chainUseRtl, 71);
        f875c.append(R$styleable.ConstraintSet_barrierDirection, 72);
        f875c.append(R$styleable.ConstraintSet_constraint_referenced_ids, 73);
        f875c.append(R$styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
    }

    public void a(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f876a.clear();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraints.getChildAt(i);
            Constraints.a aVar = (Constraints.a) childAt.getLayoutParams();
            int id = childAt.getId();
            if (id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f876a.containsKey(Integer.valueOf(id))) {
                this.f876a.put(Integer.valueOf(id), new b());
            }
            b bVar = this.f876a.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                bVar.a((ConstraintHelper) childAt, id, aVar);
            }
            bVar.a(id, aVar);
        }
    }

    void a(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashSet<Integer> hashSet = new HashSet(this.f876a.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (id != -1) {
                if (this.f876a.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    b bVar = this.f876a.get(Integer.valueOf(id));
                    if (childAt instanceof Barrier) {
                        bVar.t0 = 1;
                    }
                    int i2 = bVar.t0;
                    if (i2 != -1 && i2 == 1) {
                        Barrier barrier = (Barrier) childAt;
                        barrier.setId(id);
                        barrier.setType(bVar.s0);
                        barrier.setAllowsGoneWidget(bVar.r0);
                        int[] iArr = bVar.u0;
                        if (iArr != null) {
                            barrier.setReferencedIds(iArr);
                        } else {
                            String str = bVar.v0;
                            if (str != null) {
                                bVar.u0 = a(barrier, str);
                                barrier.setReferencedIds(bVar.u0);
                            }
                        }
                    }
                    ConstraintLayout.a aVar = (ConstraintLayout.a) childAt.getLayoutParams();
                    bVar.a(aVar);
                    childAt.setLayoutParams(aVar);
                    childAt.setVisibility(bVar.J);
                    if (Build.VERSION.SDK_INT >= 17) {
                        childAt.setAlpha(bVar.U);
                        childAt.setRotation(bVar.X);
                        childAt.setRotationX(bVar.Y);
                        childAt.setRotationY(bVar.Z);
                        childAt.setScaleX(bVar.a0);
                        childAt.setScaleY(bVar.b0);
                        if (!Float.isNaN(bVar.c0)) {
                            childAt.setPivotX(bVar.c0);
                        }
                        if (!Float.isNaN(bVar.d0)) {
                            childAt.setPivotY(bVar.d0);
                        }
                        childAt.setTranslationX(bVar.e0);
                        childAt.setTranslationY(bVar.f0);
                        if (Build.VERSION.SDK_INT >= 21) {
                            childAt.setTranslationZ(bVar.g0);
                            if (bVar.V) {
                                childAt.setElevation(bVar.W);
                            }
                        }
                    }
                }
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
        for (Integer num : hashSet) {
            b bVar2 = this.f876a.get(num);
            int i3 = bVar2.t0;
            if (i3 != -1 && i3 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                int[] iArr2 = bVar2.u0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = bVar2.v0;
                    if (str2 != null) {
                        bVar2.u0 = a(barrier2, str2);
                        barrier2.setReferencedIds(bVar2.u0);
                    }
                }
                barrier2.setType(bVar2.s0);
                ConstraintLayout.a aVarGenerateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.a();
                bVar2.a(aVarGenerateDefaultLayoutParams);
                constraintLayout.addView(barrier2, aVarGenerateDefaultLayoutParams);
            }
            if (bVar2.f877a) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.a aVarGenerateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                bVar2.a(aVarGenerateDefaultLayoutParams2);
                constraintLayout.addView(guideline, aVarGenerateDefaultLayoutParams2);
            }
        }
    }

    public void a(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    b bVarA = a(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        bVarA.f877a = true;
                    }
                    this.f876a.put(Integer.valueOf(bVarA.f880d), bVarA);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    private static int a(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    private b a(Context context, AttributeSet attributeSet) {
        b bVar = new b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintSet);
        a(bVar, typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
        return bVar;
    }

    private void a(b bVar, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            int i2 = f875c.get(index);
            switch (i2) {
                case 1:
                    bVar.p = a(typedArray, index, bVar.p);
                    break;
                case 2:
                    bVar.G = typedArray.getDimensionPixelSize(index, bVar.G);
                    break;
                case 3:
                    bVar.o = a(typedArray, index, bVar.o);
                    break;
                case 4:
                    bVar.n = a(typedArray, index, bVar.n);
                    break;
                case 5:
                    bVar.w = typedArray.getString(index);
                    break;
                case 6:
                    bVar.A = typedArray.getDimensionPixelOffset(index, bVar.A);
                    break;
                case 7:
                    bVar.B = typedArray.getDimensionPixelOffset(index, bVar.B);
                    break;
                case 8:
                    bVar.H = typedArray.getDimensionPixelSize(index, bVar.H);
                    break;
                case 9:
                    bVar.t = a(typedArray, index, bVar.t);
                    break;
                case 10:
                    bVar.s = a(typedArray, index, bVar.s);
                    break;
                case 11:
                    bVar.N = typedArray.getDimensionPixelSize(index, bVar.N);
                    break;
                case 12:
                    bVar.O = typedArray.getDimensionPixelSize(index, bVar.O);
                    break;
                case 13:
                    bVar.K = typedArray.getDimensionPixelSize(index, bVar.K);
                    break;
                case 14:
                    bVar.M = typedArray.getDimensionPixelSize(index, bVar.M);
                    break;
                case 15:
                    bVar.P = typedArray.getDimensionPixelSize(index, bVar.P);
                    break;
                case 16:
                    bVar.L = typedArray.getDimensionPixelSize(index, bVar.L);
                    break;
                case 17:
                    bVar.f881e = typedArray.getDimensionPixelOffset(index, bVar.f881e);
                    break;
                case 18:
                    bVar.f882f = typedArray.getDimensionPixelOffset(index, bVar.f882f);
                    break;
                case 19:
                    bVar.g = typedArray.getFloat(index, bVar.g);
                    break;
                case 20:
                    bVar.u = typedArray.getFloat(index, bVar.u);
                    break;
                case 21:
                    bVar.f879c = typedArray.getLayoutDimension(index, bVar.f879c);
                    break;
                case 22:
                    bVar.J = typedArray.getInt(index, bVar.J);
                    bVar.J = f874b[bVar.J];
                    break;
                case 23:
                    bVar.f878b = typedArray.getLayoutDimension(index, bVar.f878b);
                    break;
                case 24:
                    bVar.D = typedArray.getDimensionPixelSize(index, bVar.D);
                    break;
                case 25:
                    bVar.h = a(typedArray, index, bVar.h);
                    break;
                case 26:
                    bVar.i = a(typedArray, index, bVar.i);
                    break;
                case 27:
                    bVar.C = typedArray.getInt(index, bVar.C);
                    break;
                case 28:
                    bVar.E = typedArray.getDimensionPixelSize(index, bVar.E);
                    break;
                case 29:
                    bVar.j = a(typedArray, index, bVar.j);
                    break;
                case 30:
                    bVar.k = a(typedArray, index, bVar.k);
                    break;
                case 31:
                    bVar.I = typedArray.getDimensionPixelSize(index, bVar.I);
                    break;
                case 32:
                    bVar.q = a(typedArray, index, bVar.q);
                    break;
                case 33:
                    bVar.r = a(typedArray, index, bVar.r);
                    break;
                case 34:
                    bVar.F = typedArray.getDimensionPixelSize(index, bVar.F);
                    break;
                case 35:
                    bVar.m = a(typedArray, index, bVar.m);
                    break;
                case 36:
                    bVar.l = a(typedArray, index, bVar.l);
                    break;
                case 37:
                    bVar.v = typedArray.getFloat(index, bVar.v);
                    break;
                case 38:
                    bVar.f880d = typedArray.getResourceId(index, bVar.f880d);
                    break;
                case 39:
                    bVar.R = typedArray.getFloat(index, bVar.R);
                    break;
                case 40:
                    bVar.Q = typedArray.getFloat(index, bVar.Q);
                    break;
                case 41:
                    bVar.S = typedArray.getInt(index, bVar.S);
                    break;
                case 42:
                    bVar.T = typedArray.getInt(index, bVar.T);
                    break;
                case 43:
                    bVar.U = typedArray.getFloat(index, bVar.U);
                    break;
                case 44:
                    bVar.V = true;
                    bVar.W = typedArray.getDimension(index, bVar.W);
                    break;
                case 45:
                    bVar.Y = typedArray.getFloat(index, bVar.Y);
                    break;
                case 46:
                    bVar.Z = typedArray.getFloat(index, bVar.Z);
                    break;
                case 47:
                    bVar.a0 = typedArray.getFloat(index, bVar.a0);
                    break;
                case 48:
                    bVar.b0 = typedArray.getFloat(index, bVar.b0);
                    break;
                case 49:
                    bVar.c0 = typedArray.getFloat(index, bVar.c0);
                    break;
                case 50:
                    bVar.d0 = typedArray.getFloat(index, bVar.d0);
                    break;
                case 51:
                    bVar.e0 = typedArray.getDimension(index, bVar.e0);
                    break;
                case 52:
                    bVar.f0 = typedArray.getDimension(index, bVar.f0);
                    break;
                case 53:
                    bVar.g0 = typedArray.getDimension(index, bVar.g0);
                    break;
                default:
                    switch (i2) {
                        case 60:
                            bVar.X = typedArray.getFloat(index, bVar.X);
                            break;
                        case 61:
                            bVar.x = a(typedArray, index, bVar.x);
                            break;
                        case 62:
                            bVar.y = typedArray.getDimensionPixelSize(index, bVar.y);
                            break;
                        case 63:
                            bVar.z = typedArray.getFloat(index, bVar.z);
                            break;
                        default:
                            switch (i2) {
                                case 69:
                                    bVar.p0 = typedArray.getFloat(index, 1.0f);
                                    break;
                                case 70:
                                    bVar.q0 = typedArray.getFloat(index, 1.0f);
                                    break;
                                case 71:
                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                    break;
                                case 72:
                                    bVar.s0 = typedArray.getInt(index, bVar.s0);
                                    break;
                                case 73:
                                    bVar.v0 = typedArray.getString(index);
                                    break;
                                case 74:
                                    bVar.r0 = typedArray.getBoolean(index, bVar.r0);
                                    break;
                                case 75:
                                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f875c.get(index));
                                    break;
                                default:
                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f875c.get(index));
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }

    private int[] a(View view, String str) {
        int iIntValue;
        Object objA;
        String[] strArrSplit = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            try {
                iIntValue = R$id.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (objA = ((ConstraintLayout) view.getParent()).a(0, strTrim)) != null && (objA instanceof Integer)) {
                iIntValue = ((Integer) objA).intValue();
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }
}
