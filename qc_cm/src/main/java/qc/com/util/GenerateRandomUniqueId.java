package qc.com.util;

import java.security.SecureRandom;
import java.text.DecimalFormat; 
import java.text.FieldPosition; 
import java.text.Format; 
import java.text.NumberFormat; 
import java.text.SimpleDateFormat; 
import java.util.Calendar; 
 
import org.apache.log4j.Logger; 
/**
 *  
 * @author flj
 *
 */
public class GenerateRandomUniqueId { 
 
    /** .log */ 
    private static final Logger logger = Logger.getLogger(GenerateRandomUniqueId.class); 
 
    /** The FieldPosition. */ 
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0); 
 
    /** This Format for format the data to special format. */ 
    private final static Format dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS"); 
 
    /** This Format for format the number to special format. */ 
    private final static NumberFormat numberFormat = new DecimalFormat("000"); 
 
    /** This int is the sequence number ,the default value is 0. */ 
    private static int seq = 0; 
 
    private static final int MAX = 999; 

    /** The array of printable characters to be used in our random string. */
    private static final char[] PRINTABLE_CHARACTERS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ2345679"
        .toCharArray();    

	/** The array of printable characters to be used in our random string. */
    private static final char[] PRINTABLE_NUM_CHARACTERS = "012345679".toCharArray();

    /** The default maximum length. */
    private static final int DEFAULT_MAX_RANDOM_LENGTH = 15;

    /** An instance of secure random to ensure randomness is secure. */
    private static SecureRandom randomizer = new SecureRandom();
    /** 
     * 时间格式生成序列 
     * @return String 
     */ 
    public static synchronized String generateSequenceNo(String syscode) { 
 
        Calendar rightNow = Calendar.getInstance(); 
 
        StringBuffer sb = new StringBuffer(syscode); 
 
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION); 
 
        numberFormat.format(seq, sb, HELPER_POSITION); 
 
        if (seq == MAX) { 
            seq = 0; 
        } else { 
            seq++; 
        } 
        logger.info("THE SQUENCE IS :" + sb.toString()); 
        return sb.toString(); 
    } 
    
    public static synchronized String generateKey(String tableCode) { 
   	 
        Calendar rightNow = Calendar.getInstance(); 
        StringBuffer sb = new StringBuffer(tableCode); 
        dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION); 
        numberFormat.format(seq, sb, HELPER_POSITION); 
        if (seq == MAX) { 
            seq = 0; 
        } else { 
            seq++; 
        } 
        return sb.toString(); 
    } 
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
