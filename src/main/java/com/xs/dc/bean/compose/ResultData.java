package com.xs.dc.bean.compose;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author linyb
 * @Date 2017/4/10 19:49
 */
public class ResultData extends Result {

	private Object data; // 数据

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 返回数据
	 *
	 * @Date 2016/7/25 17:18
	 * @author linyb
	 */
	public static Result<Object> dataResult(Object data) {
		Result result = new Result();
		result.setData(data);
		return result;
	}

	/**
	 * 没有返回数据
	 *
	 * @Date 2016/7/25 17:18
	 * @author linyb
	 */
	public static Result unDataResult(String sign, String desc) {
		Result result = new Result();
		result.setDesc(desc);
		result.setSign(sign);
		return result;
	}

	/**
	 * 操作成功
	 *
	 * @Date 2016/7/25 17:18
	 * @author linyb
	 */
	public static Result success() {
		return unDataResult("success", "操作成功");
	}

	/**
	 * 操作成功
	 *
	 * @Date 2016/7/25 17:18
	 * @author linyb
	 */
	public static Result fail() {
		return unDataResult("fail", "操作失败");
	}
}
