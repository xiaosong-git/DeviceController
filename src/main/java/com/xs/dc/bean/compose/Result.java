package com.xs.dc.bean.compose;


import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 *
 * @author linyb
 * @param <T>
 * @Date 2016/7/25 16:41
 */
public class Result<T> {

    private String desc = "操作成功";   //返回提示信息
    private String sign = "success"; //成功或者失败  fail
    private T data;  //数据


    public Result(Integer result, String desc) {
        this.desc = desc;

    }

    public Result(String sign, String desc) {
        this.sign = sign;
        this.desc = desc;

    }

    public Result() {
    }


    /**
     * 没有返回数据
     *
     * @Date 2016/7/25 17:18
     * @author linyb
     */
    public static Result unDataResult(String sign, String desc) {
        Result result = new Result();
        Map map = new HashMap();
        map.put("sign", sign);
        map.put("desc", desc);
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

    @Override
    public String toString() {
        return "Result{" +
                "desc='" + desc + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
    
}