package qc.com.util;

import java.security.SecureRandom;

/**
 * 
 */
public class RandomUniqueIdGenerator {
	/** The array of printable characters to be used in our random string. */
    private static final char[] PRINTABLE_CHARACTERS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ2345679"
        .toCharArray();    

	/** The array of printable characters to be used in our random string. */
    private static final char[] PRINTABLE_NUM_CHARACTERS = "012345679".toCharArray();

    /** The default maximum length. */
    private static final int DEFAULT_MAX_RANDOM_LENGTH = 15;

    /** An instance of secure random to ensure randomness is secure. */
    private static SecureRandom randomizer = new SecureRandom();

    public static String getNewString() {
        final byte[] random = getNewStringAsBytes();

        return convertBytesToString(random);
    }
    
    public static String getNewString(int length) {
        final byte[] random = getNewStringAsBytes(length);

        return convertBytesToString(random);
    }
    
    /**
     * 取得数字随机码
     * @param length
     * @return
     */
    public static String getNewNumString(int length) {
        final byte[] random = getNewStringAsBytes(length);

        return convertBytesToNumString(random);
    }

    private static byte[] getNewStringAsBytes() {
    	return getNewStringAsBytes(DEFAULT_MAX_RANDOM_LENGTH);
    }

    private static byte[] getNewStringAsBytes(int maximumRandomLength) {
        final byte[] random = new byte[maximumRandomLength];

        randomizer.nextBytes(random);
        
        return random;
    }

    
    /**
     * 生成数字字母随机码
     * @param random
     * @return
     */
    private static String convertBytesToString(final byte[] random) {
        final char[] output = new char[random.length];
        for (int i = 0; i < random.length; i++) {
            final int index = Math.abs(random[i] % PRINTABLE_CHARACTERS.length);
            output[i] = PRINTABLE_CHARACTERS[index];
        }

        return new String(output);
    }
    
    /**
     * 生成数字随机码
     * @param random
     * @return
     */
    private static String convertBytesToNumString(final byte[] random) {
        final char[] output = new char[random.length];
        for (int i = 0; i < random.length; i++) {
            final int index = Math.abs(random[i] % PRINTABLE_NUM_CHARACTERS.length);
            output[i] = PRINTABLE_NUM_CHARACTERS[index];
        }

        return new String(output);
    }
}
