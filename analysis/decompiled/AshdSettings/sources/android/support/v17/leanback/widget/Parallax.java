package android.support.v17.leanback.widget;

import android.support.annotation.CallSuper;
import android.util.Property;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Parallax<PropertyT extends Property> {
    final List<PropertyT> mProperties = new ArrayList();
    final List<PropertyT> mPropertiesReadOnly = Collections.unmodifiableList(this.mProperties);
    private int[] mValues = new int[4];
    private float[] mFloatValues = new float[4];
    private final List<ParallaxEffect> mEffects = new ArrayList(4);

    public abstract PropertyT createProperty(String str, int i);

    public abstract float getMaxValue();

    public static class PropertyMarkerValue<PropertyT> {
        private final PropertyT mProperty;

        public PropertyMarkerValue(PropertyT propertyt) {
            this.mProperty = propertyt;
        }

        public PropertyT getProperty() {
            return this.mProperty;
        }
    }

    public static class IntProperty extends Property<Parallax, Integer> {
        public static final int UNKNOWN_AFTER = Integer.MAX_VALUE;
        public static final int UNKNOWN_BEFORE = Integer.MIN_VALUE;
        private final int mIndex;

        public IntProperty(String str, int i) {
            super(Integer.class, str);
            this.mIndex = i;
        }

        @Override // android.util.Property
        public final Integer get(Parallax parallax) {
            return Integer.valueOf(parallax.getIntPropertyValue(this.mIndex));
        }

        @Override // android.util.Property
        public final void set(Parallax parallax, Integer num) {
            parallax.setIntPropertyValue(this.mIndex, num.intValue());
        }

        public final int getIndex() {
            return this.mIndex;
        }

        public final int getValue(Parallax parallax) {
            return parallax.getIntPropertyValue(this.mIndex);
        }

        public final void setValue(Parallax parallax, int i) {
            parallax.setIntPropertyValue(this.mIndex, i);
        }

        public final PropertyMarkerValue atAbsolute(int i) {
            return new IntPropertyMarkerValue(this, i, 0.0f);
        }

        public final PropertyMarkerValue atMax() {
            return new IntPropertyMarkerValue(this, 0, 1.0f);
        }

        public final PropertyMarkerValue atMin() {
            return new IntPropertyMarkerValue(this, 0);
        }

        public final PropertyMarkerValue atFraction(float f) {
            return new IntPropertyMarkerValue(this, 0, f);
        }

        public final PropertyMarkerValue at(int i, float f) {
            return new IntPropertyMarkerValue(this, i, f);
        }
    }

    static class IntPropertyMarkerValue extends PropertyMarkerValue<IntProperty> {
        private final float mFactionOfMax;
        private final int mValue;

        IntPropertyMarkerValue(IntProperty intProperty, int i) {
            this(intProperty, i, 0.0f);
        }

        IntPropertyMarkerValue(IntProperty intProperty, int i, float f) {
            super(intProperty);
            this.mValue = i;
            this.mFactionOfMax = f;
        }

        final int getMarkerValue(Parallax parallax) {
            if (this.mFactionOfMax == 0.0f) {
                return this.mValue;
            }
            return Math.round(parallax.getMaxValue() * this.mFactionOfMax) + this.mValue;
        }
    }

    public static class FloatProperty extends Property<Parallax, Float> {
        public static final float UNKNOWN_AFTER = Float.MAX_VALUE;
        public static final float UNKNOWN_BEFORE = -3.4028235E38f;
        private final int mIndex;

        public FloatProperty(String str, int i) {
            super(Float.class, str);
            this.mIndex = i;
        }

        @Override // android.util.Property
        public final Float get(Parallax parallax) {
            return Float.valueOf(parallax.getFloatPropertyValue(this.mIndex));
        }

        @Override // android.util.Property
        public final void set(Parallax parallax, Float f) {
            parallax.setFloatPropertyValue(this.mIndex, f.floatValue());
        }

        public final int getIndex() {
            return this.mIndex;
        }

        public final float getValue(Parallax parallax) {
            return parallax.getFloatPropertyValue(this.mIndex);
        }

        public final void setValue(Parallax parallax, float f) {
            parallax.setFloatPropertyValue(this.mIndex, f);
        }

        public final PropertyMarkerValue atAbsolute(float f) {
            return new FloatPropertyMarkerValue(this, f, 0.0f);
        }

        public final PropertyMarkerValue atMax() {
            return new FloatPropertyMarkerValue(this, 0.0f, 1.0f);
        }

        public final PropertyMarkerValue atMin() {
            return new FloatPropertyMarkerValue(this, 0.0f);
        }

        public final PropertyMarkerValue atFraction(float f) {
            return new FloatPropertyMarkerValue(this, 0.0f, f);
        }

        public final PropertyMarkerValue at(float f, float f2) {
            return new FloatPropertyMarkerValue(this, f, f2);
        }
    }

    static class FloatPropertyMarkerValue extends PropertyMarkerValue<FloatProperty> {
        private final float mFactionOfMax;
        private final float mValue;

        FloatPropertyMarkerValue(FloatProperty floatProperty, float f) {
            this(floatProperty, f, 0.0f);
        }

        FloatPropertyMarkerValue(FloatProperty floatProperty, float f, float f2) {
            super(floatProperty);
            this.mValue = f;
            this.mFactionOfMax = f2;
        }

        final float getMarkerValue(Parallax parallax) {
            return this.mFactionOfMax == 0.0f ? this.mValue : this.mValue + (parallax.getMaxValue() * this.mFactionOfMax);
        }
    }

    final int getIntPropertyValue(int i) {
        return this.mValues[i];
    }

    final void setIntPropertyValue(int i, int i2) {
        if (i >= this.mProperties.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mValues[i] = i2;
    }

    public final PropertyT addProperty(String str) {
        int size = this.mProperties.size();
        PropertyT propertyt = (PropertyT) createProperty(str, size);
        int i = 0;
        if (propertyt instanceof IntProperty) {
            int length = this.mValues.length;
            if (length == size) {
                int[] iArr = new int[length * 2];
                while (i < length) {
                    iArr[i] = this.mValues[i];
                    i++;
                }
                this.mValues = iArr;
            }
            this.mValues[size] = Integer.MAX_VALUE;
        } else if (propertyt instanceof FloatProperty) {
            int length2 = this.mFloatValues.length;
            if (length2 == size) {
                float[] fArr = new float[length2 * 2];
                while (i < length2) {
                    fArr[i] = this.mFloatValues[i];
                    i++;
                }
                this.mFloatValues = fArr;
            }
            this.mFloatValues[size] = Float.MAX_VALUE;
        } else {
            throw new IllegalArgumentException("Invalid Property type");
        }
        this.mProperties.add(propertyt);
        return propertyt;
    }

    void verifyIntProperties() throws IllegalStateException {
        if (this.mProperties.size() < 2) {
            return;
        }
        int intPropertyValue = getIntPropertyValue(0);
        int i = 1;
        while (i < this.mProperties.size()) {
            int intPropertyValue2 = getIntPropertyValue(i);
            if (intPropertyValue2 < intPropertyValue) {
                int i2 = i - 1;
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is smaller than Property[%d]\"%s\"", Integer.valueOf(i), this.mProperties.get(i).getName(), Integer.valueOf(i2), this.mProperties.get(i2).getName()));
            }
            if (intPropertyValue == Integer.MIN_VALUE && intPropertyValue2 == Integer.MAX_VALUE) {
                int i3 = i - 1;
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is UNKNOWN_BEFORE and Property[%d]\"%s\" is UNKNOWN_AFTER", Integer.valueOf(i3), this.mProperties.get(i3).getName(), Integer.valueOf(i), this.mProperties.get(i).getName()));
            }
            i++;
            intPropertyValue = intPropertyValue2;
        }
    }

    final void verifyFloatProperties() throws IllegalStateException {
        if (this.mProperties.size() < 2) {
            return;
        }
        float floatPropertyValue = getFloatPropertyValue(0);
        int i = 1;
        while (i < this.mProperties.size()) {
            float floatPropertyValue2 = getFloatPropertyValue(i);
            if (floatPropertyValue2 < floatPropertyValue) {
                int i2 = i - 1;
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is smaller than Property[%d]\"%s\"", Integer.valueOf(i), this.mProperties.get(i).getName(), Integer.valueOf(i2), this.mProperties.get(i2).getName()));
            }
            if (floatPropertyValue == -3.4028235E38f && floatPropertyValue2 == Float.MAX_VALUE) {
                int i3 = i - 1;
                throw new IllegalStateException(String.format("Parallax Property[%d]\"%s\" is UNKNOWN_BEFORE and Property[%d]\"%s\" is UNKNOWN_AFTER", Integer.valueOf(i3), this.mProperties.get(i3).getName(), Integer.valueOf(i), this.mProperties.get(i).getName()));
            }
            i++;
            floatPropertyValue = floatPropertyValue2;
        }
    }

    final float getFloatPropertyValue(int i) {
        return this.mFloatValues[i];
    }

    final void setFloatPropertyValue(int i, float f) {
        if (i >= this.mProperties.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mFloatValues[i] = f;
    }

    public final List<PropertyT> getProperties() {
        return this.mPropertiesReadOnly;
    }

    @CallSuper
    public void updateValues() {
        for (int i = 0; i < this.mEffects.size(); i++) {
            this.mEffects.get(i).performMapping(this);
        }
    }

    public List<ParallaxEffect> getEffects() {
        return this.mEffects;
    }

    public void removeEffect(ParallaxEffect parallaxEffect) {
        this.mEffects.remove(parallaxEffect);
    }

    public void removeAllEffects() {
        this.mEffects.clear();
    }

    public ParallaxEffect addEffect(PropertyMarkerValue... propertyMarkerValueArr) {
        ParallaxEffect floatEffect;
        if (propertyMarkerValueArr[0].getProperty() instanceof IntProperty) {
            floatEffect = new ParallaxEffect.IntEffect();
        } else {
            floatEffect = new ParallaxEffect.FloatEffect();
        }
        floatEffect.setPropertyRanges(propertyMarkerValueArr);
        this.mEffects.add(floatEffect);
        return floatEffect;
    }
}
