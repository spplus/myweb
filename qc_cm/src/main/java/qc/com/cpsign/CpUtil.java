package qc.com.cpsign;

/**
 * cp公共方法.
 * 
 * @author yuanls
 *
 */
public  class CpUtil {

	/**
	 * 转为分.
	 * @param m
	 * @return
	 */
	public static String toCpMoney(String m){
		if(m.length()<=0){
			return "";
		}
		
		String ret;
		String arr[] = m.split("\\.");
		if(arr.length==2){
			String left;
			if(arr[1].length()>=2){
				left = arr[1].substring(0,2);
			}else if(arr[1].length() == 1){
				left = arr[1]+"0";
			}else{
				left = "00";
			}
			ret = arr[0]+left;
		}else if(arr.length == 1){
			ret = arr[0] + "00";
		}else{
			return "";
		}
	
		return ret;
	}
}
