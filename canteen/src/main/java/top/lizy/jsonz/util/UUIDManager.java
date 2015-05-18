package top.lizy.jsonz.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDManager {

    /**
     * Generates a cryptographically strong pseudo random UUID.
     */
    public static UUID gen() {
        return UUID.randomUUID();
    }

    /**
     * Converts an UUID to a bytes array with length 16.
     */
    public static byte[] toBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    /**
     * Constructs an UUID from a bytes array with length 16.
     *
     * @throws IOException
     */
    public static UUID fromBytes(byte[] b) throws IOException {
        ByteBuffer bb = ByteBuffer.wrap(b);
        long hi, lo;
        hi = bb.getLong();
        lo = bb.getLong();
        return new UUID(hi, lo);
    }

}
