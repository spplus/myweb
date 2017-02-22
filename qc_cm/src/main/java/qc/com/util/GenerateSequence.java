package qc.com.util;

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
public class GenerateSequence { 
 
    /** .log */ 
    private static final Logger logger = Logger.getLogger(GenerateSequence.class); 
 
    /** The FieldPosition. */ 
    private static final FieldPosition HELPER_POSITION = new FieldPosition(0); 
 
    /** This Format for format the data to special format. */ 
    private final static Format dateFormat = new SimpleDateFormat("YYMMddHHmmssSSS"); 
 
    /** This Format for format the number to special format. */ 
    private final static NumberFormat numberFormat = new DecimalFormat("000"); 
 
    /** This int is the sequence number ,the default value is 0. */ 
    private static int seq = 0; 
 
    private static final int MAX = 999; 
    /** 
     * 时间格式生成序列号 
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
    public static void main(String[] args) {
    	
    	while(true){
    		String no = generateSequenceNo("03");
    		if(no.length()==19){
        		System.out.println(generateSequenceNo("03"));
    		}
    	}
    	
	}
} 
