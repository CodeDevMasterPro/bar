package cn.ybzy.bar.common.exception;

import lombok.Data;

/**
 * @author: CJ
 * @description: 自定义异常
 * @time: 2020/1/14 11:59
 */
@Data
public class CustomerException extends RuntimeException {
	private String code;
	private String msg;
	
	public CustomerException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
