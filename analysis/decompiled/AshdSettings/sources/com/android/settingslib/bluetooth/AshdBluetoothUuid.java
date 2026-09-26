package com.android.settingslib.bluetooth;

import android.os.ParcelUuid;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.MotionEventCompat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class AshdBluetoothUuid {
    public static final int UUID_BYTES_128_BIT = 16;
    public static final int UUID_BYTES_16_BIT = 2;
    public static final int UUID_BYTES_32_BIT = 4;

    @NonNull
    public static final ParcelUuid A2DP_SINK = ParcelUuid.fromString("0000110B-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid A2DP_SOURCE = ParcelUuid.fromString("0000110A-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid ADV_AUDIO_DIST = ParcelUuid.fromString("0000110D-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid HSP = ParcelUuid.fromString("00001108-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid HSP_AG = ParcelUuid.fromString("00001112-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid HFP = ParcelUuid.fromString("0000111E-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid HFP_AG = ParcelUuid.fromString("0000111F-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid AVRCP = ParcelUuid.fromString("0000110E-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid AVRCP_CONTROLLER = ParcelUuid.fromString("0000110F-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid AVRCP_TARGET = ParcelUuid.fromString("0000110C-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid OBEX_OBJECT_PUSH = ParcelUuid.fromString("00001105-0000-1000-8000-00805f9b34fb");

    @NonNull
    public static final ParcelUuid HID = ParcelUuid.fromString("00001124-0000-1000-8000-00805f9b34fb");

    @NonNull
    public static final ParcelUuid HOGP = ParcelUuid.fromString("00001812-0000-1000-8000-00805f9b34fb");

    @NonNull
    public static final ParcelUuid PANU = ParcelUuid.fromString("00001115-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid NAP = ParcelUuid.fromString("00001116-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid BNEP = ParcelUuid.fromString("0000000f-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid PBAP_PCE = ParcelUuid.fromString("0000112e-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid PBAP_PSE = ParcelUuid.fromString("0000112f-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid MAP = ParcelUuid.fromString("00001134-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid MNS = ParcelUuid.fromString("00001133-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid MAS = ParcelUuid.fromString("00001132-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid SAP = ParcelUuid.fromString("0000112D-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid HEARING_AID = ParcelUuid.fromString("0000FDF0-0000-1000-8000-00805f9b34fb");

    @NonNull
    public static final ParcelUuid HAS = ParcelUuid.fromString("00001854-0000-1000-8000-00805F9B34FB");

    @NonNull
    public static final ParcelUuid LE_AUDIO = ParcelUuid.fromString("0000184E-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid DIP = ParcelUuid.fromString("00001200-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid VOLUME_CONTROL = ParcelUuid.fromString("00001844-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid GENERIC_MEDIA_CONTROL = ParcelUuid.fromString("00001849-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid MEDIA_CONTROL = ParcelUuid.fromString("00001848-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid COORDINATED_SET = ParcelUuid.fromString("00001846-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid CAP = ParcelUuid.fromString("00001853-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BATTERY = ParcelUuid.fromString("0000180F-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BASS = ParcelUuid.fromString("0000184F-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid TMAP = ParcelUuid.fromString("00001855-0000-1000-8000-00805F9B34FB");
    public static final ParcelUuid BASE_UUID = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    public static boolean containsAnyUuid(@Nullable ParcelUuid[] parcelUuidArr, @Nullable ParcelUuid[] parcelUuidArr2) {
        if (parcelUuidArr == null && parcelUuidArr2 == null) {
            return true;
        }
        if (parcelUuidArr == null) {
            return parcelUuidArr2.length == 0;
        }
        if (parcelUuidArr2 == null) {
            return parcelUuidArr.length == 0;
        }
        HashSet hashSet = new HashSet(Arrays.asList(parcelUuidArr));
        for (ParcelUuid parcelUuid : parcelUuidArr2) {
            if (hashSet.contains(parcelUuid)) {
                return true;
            }
        }
        return false;
    }

    private static int getServiceIdentifierFromParcelUuid(ParcelUuid parcelUuid) {
        return (int) ((parcelUuid.getUuid().getMostSignificantBits() & (-4294967296L)) >>> 32);
    }

    public static ParcelUuid parseUuidFrom(@Nullable byte[] bArr) {
        long j;
        if (bArr == null) {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
        int length = bArr.length;
        if (length != 2 && length != 4 && length != 16) {
            throw new IllegalArgumentException("uuidBytes length invalid - " + length);
        }
        if (length == 16) {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new ParcelUuid(new UUID(byteBufferOrder.getLong(8), byteBufferOrder.getLong(0)));
        }
        if (length == 2) {
            j = ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8));
        } else {
            j = ((long) ((bArr[3] & 255) << 24)) + ((long) (bArr[0] & 255)) + ((long) ((bArr[1] & 255) << 8)) + ((long) ((bArr[2] & 255) << 16));
        }
        return new ParcelUuid(new UUID(BASE_UUID.getUuid().getMostSignificantBits() + (j << 32), BASE_UUID.getUuid().getLeastSignificantBits()));
    }

    public static byte[] uuidToBytes(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            throw new IllegalArgumentException("uuid cannot be null");
        }
        if (is16BitUuid(parcelUuid)) {
            int serviceIdentifierFromParcelUuid = getServiceIdentifierFromParcelUuid(parcelUuid);
            return new byte[]{(byte) (serviceIdentifierFromParcelUuid & 255), (byte) ((serviceIdentifierFromParcelUuid & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
        }
        if (is32BitUuid(parcelUuid)) {
            int serviceIdentifierFromParcelUuid2 = getServiceIdentifierFromParcelUuid(parcelUuid);
            return new byte[]{(byte) (serviceIdentifierFromParcelUuid2 & 255), (byte) ((65280 & serviceIdentifierFromParcelUuid2) >> 8), (byte) ((16711680 & serviceIdentifierFromParcelUuid2) >> 16), (byte) ((serviceIdentifierFromParcelUuid2 & (-16777216)) >> 24)};
        }
        long mostSignificantBits = parcelUuid.getUuid().getMostSignificantBits();
        long leastSignificantBits = parcelUuid.getUuid().getLeastSignificantBits();
        byte[] bArr = new byte[16];
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putLong(8, mostSignificantBits);
        byteBufferOrder.putLong(0, leastSignificantBits);
        return bArr;
    }

    public static boolean is16BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && (uuid.getMostSignificantBits() & (-281470681743361L)) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    public static boolean is32BitUuid(ParcelUuid parcelUuid) {
        UUID uuid = parcelUuid.getUuid();
        return uuid.getLeastSignificantBits() == BASE_UUID.getUuid().getLeastSignificantBits() && !is16BitUuid(parcelUuid) && (uuid.getMostSignificantBits() & 4294967295L) == PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    private AshdBluetoothUuid() {
    }
}
