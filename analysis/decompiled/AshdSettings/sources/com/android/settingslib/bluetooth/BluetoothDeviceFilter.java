package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothDeviceFilter {
    public static final Filter ALL_FILTER;
    public static final Filter BONDED_DEVICE_FILTER;
    private static final Filter[] FILTERS;
    private static final String TAG = "BluetoothDeviceFilter";
    public static final Filter UNBONDED_DEVICE_FILTER;

    public interface Filter {
        boolean matches(BluetoothDevice bluetoothDevice);
    }

    static {
        ALL_FILTER = new AllFilter();
        BONDED_DEVICE_FILTER = new BondedDeviceFilter();
        UNBONDED_DEVICE_FILTER = new UnbondedDeviceFilter();
        FILTERS = new Filter[]{ALL_FILTER, new AudioFilter(), new TransferFilter(), new PanuFilter(), new NapFilter()};
    }

    private BluetoothDeviceFilter() {
    }

    public static Filter getFilter(int i) {
        if (i >= 0 && i < FILTERS.length) {
            return FILTERS[i];
        }
        Log.w(TAG, "Invalid filter type " + i + " for device picker");
        return ALL_FILTER;
    }

    private static final class AllFilter implements Filter {
        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice bluetoothDevice) {
            return true;
        }

        private AllFilter() {
        }
    }

    private static final class BondedDeviceFilter implements Filter {
        private BondedDeviceFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice bluetoothDevice) {
            return bluetoothDevice.getBondState() == 12;
        }
    }

    private static final class UnbondedDeviceFilter implements Filter {
        private UnbondedDeviceFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice bluetoothDevice) {
            return bluetoothDevice.getBondState() != 12;
        }
    }

    private static abstract class ClassUuidFilter implements Filter {
        abstract boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass);

        private ClassUuidFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice bluetoothDevice) {
            return matches(bluetoothDevice.getUuids(), bluetoothDevice.getBluetoothClass());
        }
    }

    private static final class AudioFilter extends ClassUuidFilter {
        private AudioFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass) {
            if (parcelUuidArr != null) {
                if (BluetoothUuid.containsAnyUuid(parcelUuidArr, A2dpProfile.SINK_UUIDS) || BluetoothUuid.containsAnyUuid(parcelUuidArr, HeadsetProfile.UUIDS)) {
                    return true;
                }
            } else if (bluetoothClass != null && (bluetoothClass.doesClassMatch(1) || bluetoothClass.doesClassMatch(0))) {
                return true;
            }
            return false;
        }
    }

    private static final class TransferFilter extends ClassUuidFilter {
        private TransferFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass) {
            if (parcelUuidArr == null || !BluetoothUuid.isUuidPresent(parcelUuidArr, BluetoothUuid.ObexObjectPush)) {
                return bluetoothClass != null && bluetoothClass.doesClassMatch(2);
            }
            return true;
        }
    }

    private static final class PanuFilter extends ClassUuidFilter {
        private PanuFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass) {
            if (parcelUuidArr == null || !BluetoothUuid.isUuidPresent(parcelUuidArr, BluetoothUuid.PANU)) {
                return bluetoothClass != null && bluetoothClass.doesClassMatch(4);
            }
            return true;
        }
    }

    private static final class NapFilter extends ClassUuidFilter {
        private NapFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass) {
            if (parcelUuidArr == null || !BluetoothUuid.isUuidPresent(parcelUuidArr, BluetoothUuid.NAP)) {
                return bluetoothClass != null && bluetoothClass.doesClassMatch(5);
            }
            return true;
        }
    }
}
