package utils;

import java.io.IOException;
import java.io.Writer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class JsonUtils {
	 //private static JsonGenerator jsonGenerator = null;
	 private static ObjectMapper objectMapper = null;
	 
	 static{
		 objectMapper = new ObjectMapper();
		 objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		  // or (for older versions):
		 //objectMapper.configure(MapperFeature.WRITE_NULL_PROPERTIES, false);
	 }
	 
	 public static String toJSON(Object value){
	        try {
	        	return objectMapper.writeValueAsString(value);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return null;
	 }
	 
	 public static <T> T toObj(String json , Class<T> type){
			try {
				return objectMapper.readValue(json, type);
			} catch (IOException e) {
				return null;
			}
	 }
	 
	 public static String toString(Object o){
		 StringBuffer sb = new StringBuffer();
		 sb.append("{")
		 .append(o.getClass().getSimpleName())
		 .append(":")
		 .append(toJSON(o))
		 .append("}");
		 return sb.toString();
	 }
	 
	 public static void main(String[] args){

		 
	 }
}
