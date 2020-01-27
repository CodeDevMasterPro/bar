package cn.ybzy.bar.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author: CJ
 * @description: 统一响应类
 * @time: 2020/1/14 11:27
 */
public class BaseUtil {
	
	public static String DATA_NULL = "此数据不存在";
	public static String SAVE_SUCCESS = "保存成功";
	public static String ADD_SUCCESS = "创建成功";
	public static String ADD_FAIL = "创建失败";
	public static String DELETE_SUCCESS = "删除成功";
	public static String DELETE_FAIL = "删除失败";
	public static String UPDATE_SUCCESS = "修改成功";
	public static String UPDATE_FAIL = "修改失败";
	public static String QUERY_SUCCESS = "查询成功";
	public static String QUERY_FAIL = "查询失败";
	public static String PLEASE_LOGIN = "请登录";
	public static String TOKEN_INVALID = "登录过期";
	public static String USER_PASSWORD_ERROR = "账号或密码错误";
	public static String USER_PASSWORD_NULL = "账号或密码不能为空";
	public static String USER_DISABLED = "账号已禁用";
	public static String NO_AUTHORITY = "没有权限";
	public static String INVALID_ARGUMENT = "无效参数";
	public static String SYSTEM_ERROR = "服务器错误";
	private static String defaultDatePattern = "yyyy-MM-dd";
	
	
	public static String getException(Exception e) {
		StackTraceElement[] ste = e.getStackTrace();
		StringBuffer str = new StringBuffer();
		str.append("自定义捕获异常：\r\n");
		str.append(e.getClass().getName() + ":" + e.getMessage() + "\r\n");
		for (int i = 0; i < ste.length; i++) {
			str.append("	" + ste[i].toString() + "\r\n");
		}
		return str.toString();
	}
	
	/**
	 * json 响应返回格式-可自行添加数据（需自行弹出提示信息）
	 *
	 * @param code 1 表示成功，其他表示失败
	 * @param msg  提示信息
	 * @return code/msg/data
	 */
	public static HashMap<String, Object> back(int code, String msg) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", false);
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * json 响应返回格式-可自行添加数据（需自行弹出提示信息）
	 *
	 * @param code 1 表示成功，其他表示失败
	 * @param msg  提示信息
	 * @param data 携带数据 json/jsonArray/string 等等
	 * @return code/msg/data
	 */
	public static HashMap<String, Object> back(int code, String msg, Object data) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", false);
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}
	
	/**
	 * json 响应返回格式-可自行添加数据（需自行弹出提示信息）
	 *
	 * @param code 1 表示成功，其他表示失败
	 * @param data 携带数据 json/jsonArray/string 等等
	 * @return code/msg/data
	 */
	public static HashMap<String, Object> back(int code, Object data) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", false);
		map.put("code", code);
		map.put("data", data);
		return map;
	}
	
	/**
	 * json 响应返回格式-可自行添加数据（前端统一弹出提示信息 - 只对code:0,1生效）
	 *
	 * @param code 1 表示成功，其他表示失败
	 * @param msg  提示信息
	 * @return code/msg/data
	 */
	public static HashMap<String, Object> backGlobal(int code, String msg) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", true);
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}
	
	/**
	 * json 响应返回格式-可自行添加数据（前端统一弹出提示信息 - 只对code:0,1生效）
	 *
	 * @param code 1 表示成功，其他表示失败
	 * @param msg  提示信息
	 * @param data 携带数据 json/jsonArray/string 等等
	 * @return code/msg/data
	 */
	public static HashMap<String, Object> backGlobal(int code, String msg, Object data) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", true);
		map.put("code", code);
		map.put("msg", msg);
		map.put("data", data);
		return map;
	}
	
	/**
	 * json 响应返回格式-可自行添加数据（前端统一弹出提示信息 - 只对code:0,1生效）
	 *
	 * @param code 1 表示成功，其他表示失败
	 * @param data 携带数据 json/jsonArray/string 等等
	 * @return code/msg/data
	 */
	public static HashMap<String, Object> backGlobal(int code, Object data) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", true);
		map.put("code", code);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 当前时间-格式yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getDateTimeSign() {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	/**
	 * json 响应返回格式
	 *
	 * @param code  1 表示成功，其他表示失败
	 * @param msg   提示信息
	 * @param total 总条数
	 * @param data  携带数据 json/jsonArray/string 等等
	 * @return
	 */
	public static HashMap<String, Object> backResult(int code, String msg, int total, Object data) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("global", false);
		map.put("code", code);
		map.put("msg", msg);
		map.put("total", total);
		map.put("data", data);
		return map;
	}
	
	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}
	
	public static String getDateTimePattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}
	
	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		
		return (returnValue);
	}
	
	/**
	 * 使用预设格式化Date成yyyy-MM-dd字符串
	 */
	public static String formatDate(Date date) {
		return format(date, getDatePattern());
	}
	
	/**
	 * 使用预设格式化Date成yyyy-MM-dd hh:mm:ss字符串
	 */
	public static String formatTime(Date date) {
		return format(date, getDateTimePattern());
	}
	
	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}
	
	/**
	 * 使用预设格式将字符串转为Date yyyy-MM-dd hh:mm:ss
	 */
	public static Date parseDate(String strDate) throws ParseException {
		return parse(strDate, getDatePattern());
	}
	
	/**
	 * 使用预设格式将字符串转为Date yyyy-MM-dd hh:mm:ss
	 */
	public static Date parseTime(String strDate) throws ParseException {
		return parse(strDate, getDateTimePattern());
	}
	
	// 计算两日期之差
	public static int subtrationDate(Date start, Date end) {
		// 1970 年 1 月 1 日 08:00:00 GMT 以来此 Date 对象表示的毫秒数。 所以加上8*3600*1000
		long startTime = start.getTime() + 8 * 3600 * 1000;
		long startDays = days(startTime);
		// 1970 年 1 月 1 日 08:00:00 GMT 以来此 Date 对象表示的毫秒数。 所以加上8*3600*1000
		long endTime = end.getTime() + 8 * 3600 * 1000;
		long endDays = days(endTime);
		int tmp = (int) (endDays - startDays);
		return tmp;
	}
	
	public static long days(long time) {
		return time / (24 * 60 * 60 * 1000);
	}
}
