package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTOUtils {
	static Log LOG = LogFactory.getLog(DTOUtils.class);
	// private static JsonGenerator jsonGenerator = null;
	private static ObjectMapper objectMapper = null;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public static String toJSON(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (IOException e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}

	public static <T> T toObj(String json, Class<T> type) {
		try {
			return objectMapper.readValue(json, type);
		} catch (IOException e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}

	public static String toString(Object o) {
		StringBuffer sb = new StringBuffer();
		sb.append("{").append(o.getClass().getSimpleName()).append(":").append(toJSON(o)).append("}");
		return sb.toString();
	}

	public static Map<Integer, String> GRADE_MAP = new HashMap<Integer, String>() {
		{
			put(-1, "骞煎効鍥�");
			put(-2, "瀛﹀墠鐝�");
			put(-3, "澶х彮");
			put(-4, "涓彮");
			put(-5, "灏忕彮");
			put(-6, "灏忓皬鐝�");
			put(-7, "鎵樺効鐝�");
			put(0, "瀛﹀墠鐝�");
			put(1, "涓�骞寸骇");//灏忎竴
			put(2, "浜屽勾绾�");//灏忎簩
			put(3, "涓夊勾绾�");//灏忎笁
			put(4, "鍥涘勾绾�");//灏忓洓
			put(5, "浜斿勾绾�");//灏忎簲
			put(6, "鍏勾绾�");//灏忓叚
			put(7, "鍒濅竴");
			put(8, "鍒濅簩");
			put(9, "鍒濅笁");
			put(10, "楂樹竴");
			put(11, "楂樹簩");
			put(12, "楂樹笁");
			put(13, "澶т竴");
			put(14, "澶т簩");
			put(15, "澶т笁");
			put(16, "澶у洓");
		}
	};

	// 寮�鏀惧钩鍙板勾绾у垪琛�
	public static Map<String, String> PUBLIC_GRADE_MAP = new HashMap<String, String>() {
		{
			put("g_-1", "骞煎効鍥�");
			put("g_1", "灏忓涓�骞寸骇");//灏忎竴
			put("g_2", "灏忓浜屽勾绾�");//灏忎簩
			put("g_3", "灏忓涓夊勾绾�");//灏忎笁
			put("g_4", "灏忓鍥涘勾绾�");//灏忓洓
			put("g_5", "灏忓浜斿勾绾�");//灏忎簲
			put("g_6", "灏忓鍏勾绾�");//灏忓叚
			put("g_7", "鍒濅腑涓�骞寸骇");
			put("g_8", "鍒濅腑浜屽勾绾�");
			put("g_9", "鍒濅腑涓夊勾绾�");
			put("g_10", "楂樹腑涓�骞寸骇");
			put("g_11", "楂樹腑浜屽勾绾�");
			put("g_12", "楂樹腑涓夊勾绾�");
		}
	};
	

	public static Map<String, Integer> GRADE_NAME_MAP = new HashMap<String, Integer>() {
		{
			put("骞煎効鍥�",-1);
			put("瀛﹀墠鐝�",-2);
            put("骞煎効鍥ぇ鐝�", -3);
			put("澶х彮",-3);
            put("骞煎効鍥腑鐝�", -4);
			put("涓彮",-4);
            put("骞煎効鍥皬鐝�",-5);
			put("灏忕彮",-5);
            put("骞煎効鍥皬灏忕彮", -6);
			put("灏忓皬鐝�",-6);
            put("骞煎効鍥墭鍎跨彮", -7);
			put("鎵樺効鐝�",-7);
            put("骞煎効鍥鍓嶇彮", 0);
			put("瀛﹀墠鐝�",0);
			put("灏忎竴",1);
			put("灏忎簩",2);
			put("灏忎笁",3);
			put("灏忓洓",4);
			put("灏忎簲",5);
			put("灏忓叚",6);
			put("鍒濅竴",7);
			put("鍒濅簩",8);
			put("鍒濅笁",9);
			put( "楂樹竴",10);
			put( "楂樹簩",11);
			put( "楂樹笁",12);
		}
	};

	private static final String CLASS_OBJ_NAME_TEMPLET = "%s(%s)鐝�";

	public static String getClassObjName(Integer grade, Integer index) {
		return String.format(CLASS_OBJ_NAME_TEMPLET, GRADE_MAP.get(grade), index);
	}

	// C_11377_8_1_2013
	public static String getClassObjName(String cId) {
		String els[] = cId.split("_");
		return getClassObjName(Integer.valueOf(els[2]), Integer.valueOf(els[3]));
	}

	public static String getJZCompName(String schName, int grade) {
		StringBuffer sb = new StringBuffer(schName);
		sb.append(GRADE_MAP.get(grade)).append("绾ч暱");
		return sb.toString();
	}

	public static String getXZCompName(String schName) {
		StringBuffer sb = new StringBuffer(schName).append("鏍￠暱");
		return sb.toString();
	}

	public static String subString(String v, int length) {
		if (isNull(v) || v.length() <= length) {
			return v;
		}
		return v.substring(0, length);
	}

	public static boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

    public static interface ROLE{
        /** 瀹堕暱 */
        public static final int ROLE_P = 0;
        /** 浠昏鑰佸笀 */
        public static final int ROLE_T = 1;
        /** 绾ч暱 */
        public static final int ROLE_JZ = 2;
        /** 鏍￠暱 */
        public static final int ROLE_XZ = 3;
        /** 杈呭鍛� */
        public static final int ROLE_FDY = 4;
        /** 鐝富浠� */
        public static final int ROLE_BZR = 5;
    }

   

    public static String getGradeDesc(List<Integer> grades) {
        if (grades != null && !grades.isEmpty()) {
            String s = GRADE_MAP.get(grades.get(0));
            if (s != null) {
                return s;
            }
        }
        return "";
    }
}
