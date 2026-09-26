package vendor.aw.hardware.btvserver.V1_0;

import android.hidl.safe_union.V1_0.Monostate;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class TvLockInfo {
    private byte hidl_d = 0;
    private Object hidl_o;

    public TvLockInfo() {
        this.hidl_o = null;
        this.hidl_o = new Monostate();
    }

    public static final class hidl_discriminator {
        public static final byte atvLockInfo = 2;
        public static final byte dtvLockInfo = 1;
        public static final byte noinit = 0;

        public static final String getName(byte b) {
            switch (b) {
                case 0:
                    return "noinit";
                case 1:
                    return "dtvLockInfo";
                case 2:
                    return "atvLockInfo";
                default:
                    return "Unknown";
            }
        }

        private hidl_discriminator() {
        }
    }

    public void noinit(Monostate monostate) {
        this.hidl_d = (byte) 0;
        this.hidl_o = monostate;
    }

    public Monostate noinit() {
        if (this.hidl_d != 0) {
            throw new IllegalStateException("Read access to inactive union components is disallowed. Discriminator value is " + ((int) this.hidl_d) + " (corresponding to " + hidl_discriminator.getName(this.hidl_d) + "), and hidl_o is of type " + (this.hidl_o != null ? this.hidl_o.getClass().getName() : "null") + ".");
        }
        if (this.hidl_o != null && !Monostate.class.isInstance(this.hidl_o)) {
            throw new Error("Union is in a corrupted state.");
        }
        return (Monostate) this.hidl_o;
    }

    public void dtvLockInfo(DtvLockInfo dtvLockInfo) {
        this.hidl_d = (byte) 1;
        this.hidl_o = dtvLockInfo;
    }

    public DtvLockInfo dtvLockInfo() {
        if (this.hidl_d != 1) {
            throw new IllegalStateException("Read access to inactive union components is disallowed. Discriminator value is " + ((int) this.hidl_d) + " (corresponding to " + hidl_discriminator.getName(this.hidl_d) + "), and hidl_o is of type " + (this.hidl_o != null ? this.hidl_o.getClass().getName() : "null") + ".");
        }
        if (this.hidl_o != null && !DtvLockInfo.class.isInstance(this.hidl_o)) {
            throw new Error("Union is in a corrupted state.");
        }
        return (DtvLockInfo) this.hidl_o;
    }

    public void atvLockInfo(AtvLockInfo atvLockInfo) {
        this.hidl_d = (byte) 2;
        this.hidl_o = atvLockInfo;
    }

    public AtvLockInfo atvLockInfo() {
        if (this.hidl_d != 2) {
            throw new IllegalStateException("Read access to inactive union components is disallowed. Discriminator value is " + ((int) this.hidl_d) + " (corresponding to " + hidl_discriminator.getName(this.hidl_d) + "), and hidl_o is of type " + (this.hidl_o != null ? this.hidl_o.getClass().getName() : "null") + ".");
        }
        if (this.hidl_o != null && !AtvLockInfo.class.isInstance(this.hidl_o)) {
            throw new Error("Union is in a corrupted state.");
        }
        return (AtvLockInfo) this.hidl_o;
    }

    public byte getDiscriminator() {
        return this.hidl_d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != TvLockInfo.class) {
            return false;
        }
        TvLockInfo tvLockInfo = (TvLockInfo) obj;
        return this.hidl_d == tvLockInfo.hidl_d && HidlSupport.deepEquals(this.hidl_o, tvLockInfo.hidl_o);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(HidlSupport.deepHashCode(this.hidl_o)), Integer.valueOf(Objects.hashCode(Byte.valueOf(this.hidl_d))));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        switch (this.hidl_d) {
            case 0:
                sb.append(".noinit = ");
                sb.append(noinit());
                break;
            case 1:
                sb.append(".dtvLockInfo = ");
                sb.append(dtvLockInfo());
                break;
            case 2:
                sb.append(".atvLockInfo = ");
                sb.append(atvLockInfo());
                break;
            default:
                throw new Error("Unknown union discriminator (value: " + ((int) this.hidl_d) + ").");
        }
        sb.append("}");
        return sb.toString();
    }

    public final void readFromParcel(HwParcel hwParcel) {
        readEmbeddedFromParcel(hwParcel, hwParcel.readBuffer(24L), 0L);
    }

    public static final ArrayList<TvLockInfo> readVectorFromParcel(HwParcel hwParcel) {
        ArrayList<TvLockInfo> arrayList = new ArrayList<>();
        HwBlob buffer = hwParcel.readBuffer(16L);
        int int32 = buffer.getInt32(8L);
        HwBlob embeddedBuffer = hwParcel.readEmbeddedBuffer(int32 * 24, buffer.handle(), 0L, true);
        arrayList.clear();
        for (int i = 0; i < int32; i++) {
            TvLockInfo tvLockInfo = new TvLockInfo();
            tvLockInfo.readEmbeddedFromParcel(hwParcel, embeddedBuffer, i * 24);
            arrayList.add(tvLockInfo);
        }
        return arrayList;
    }

    public final void readEmbeddedFromParcel(HwParcel hwParcel, HwBlob hwBlob, long j) {
        this.hidl_d = hwBlob.getInt8(0 + j);
        switch (this.hidl_d) {
            case 0:
                this.hidl_o = new Monostate();
                ((Monostate) this.hidl_o).readEmbeddedFromParcel(hwParcel, hwBlob, j + 4);
                return;
            case 1:
                this.hidl_o = new DtvLockInfo();
                ((DtvLockInfo) this.hidl_o).readEmbeddedFromParcel(hwParcel, hwBlob, j + 4);
                return;
            case 2:
                this.hidl_o = new AtvLockInfo();
                ((AtvLockInfo) this.hidl_o).readEmbeddedFromParcel(hwParcel, hwBlob, j + 4);
                return;
            default:
                throw new IllegalStateException("Unknown union discriminator (value: " + ((int) this.hidl_d) + ").");
        }
    }

    public final void writeToParcel(HwParcel hwParcel) {
        HwBlob hwBlob = new HwBlob(24);
        writeEmbeddedToBlob(hwBlob, 0L);
        hwParcel.writeBuffer(hwBlob);
    }

    public static final void writeVectorToParcel(HwParcel hwParcel, ArrayList<TvLockInfo> arrayList) {
        HwBlob hwBlob = new HwBlob(16);
        int size = arrayList.size();
        hwBlob.putInt32(8L, size);
        hwBlob.putBool(12L, false);
        HwBlob hwBlob2 = new HwBlob(size * 24);
        for (int i = 0; i < size; i++) {
            arrayList.get(i).writeEmbeddedToBlob(hwBlob2, i * 24);
        }
        hwBlob.putBlob(0L, hwBlob2);
        hwParcel.writeBuffer(hwBlob);
    }

    public final void writeEmbeddedToBlob(HwBlob hwBlob, long j) {
        hwBlob.putInt8(0 + j, this.hidl_d);
        switch (this.hidl_d) {
            case 0:
                noinit().writeEmbeddedToBlob(hwBlob, j + 4);
                return;
            case 1:
                dtvLockInfo().writeEmbeddedToBlob(hwBlob, j + 4);
                return;
            case 2:
                atvLockInfo().writeEmbeddedToBlob(hwBlob, j + 4);
                return;
            default:
                throw new Error("Unknown union discriminator (value: " + ((int) this.hidl_d) + ").");
        }
    }
}
