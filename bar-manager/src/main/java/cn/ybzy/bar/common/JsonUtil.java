package cn.ybzy.bar.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.util.StringUtils;


/**
 * fastjson工具类
 */
public class JsonUtil {
	
	/**
	 * 描述：将对象格式化成json字符串
	 */
	public static String toJson(Object object) throws JSONException {
		try {
			return JSON.toJSONString(object, new SerializerFeature[]{
					SerializerFeature.WriteMapNullValue,
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNonStringKeyAsString});
		} catch (Exception e) {
			throw new JSONException("json转换异常");
		}
	}
	
	/**
	 * 描述：将对象格式化成json字符串（PrettyFormat格式）
	 */
	public static String toJsonFormat(Object object) throws JSONException {
		try {
			return JSON.toJSONString(object, new SerializerFeature[]{
					SerializerFeature.WriteMapNullValue,
					SerializerFeature.PrettyFormat,
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNonStringKeyAsString});
		} catch (Exception e) {
			throw new JSONException("json转换异常");
		}
	}
	
	/**
	 * 描述：转Map
	 */
	public static Object toJsonObject(Object obj) throws JSONException {
		try {
			return JSON.toJSON(obj);
		} catch (Exception e) {
			throw new JSONException("json转换异常");
		}
	}
	
	/**
	 * 描述：将json串转为对象
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) throws JSONException {
		try {
			if (StringUtils.isEmpty(jsonString)) {
				return null;
			}
			return (T) JSON.parseObject(jsonString, clazz);
		} catch (Exception e) {
			throw new JSONException("json转换异常");
		}
	}
	
}
