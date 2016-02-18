package utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LogUtils {
	public static final Log L = LogFactory.getLog(LogUtils.class);
	
	public static void log(String msg){
		 L.info(msg);
	}
	
}
