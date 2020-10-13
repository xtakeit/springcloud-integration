package cn.edu.dgut.integration.common.util;

public enum ResultCode {

	// 秒课错误: 500100 - 500199
	SECKILL_LINE_UP(500100, "排队中"), 
	SECKILL_NO_QUOTE(500101, "库存不足"),
	SECKILL_BOUGHT(500102, "已经购买，请去订单详情页查看"),
	SECKILL_PATH_ERROR(500104, "你的请求地址不正确，请联系小姐姐。"),
	SECKILL_IP_OUTMAX(500105, "你的请求过于频繁，请稍后再试。"),	
	
	SUCCESS(200, "成功"),
	FAIL(500, "失败"),
	USER_LOGIN_ERROR(500201, "登录失败，用户名或密码出错，请重新输入");

	private Integer code;
	
	private String message;
	
	ResultCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
