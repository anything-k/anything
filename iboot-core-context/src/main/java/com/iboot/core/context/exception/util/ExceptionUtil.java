package com.iboot.core.context.exception.util;

import com.iboot.core.context.exception.IBusinessException;
import com.iboot.core.context.exception.IRpcException;
import com.iboot.core.context.exception.IWarningException;
import com.iboot.core.context.exception.impl.BusinessException;
import com.iboot.core.context.exception.impl.RpcException;
import com.iboot.core.context.exception.impl.WarningException;
import com.iboot.core.context.util.CommonUtil;
import com.sun.corba.se.impl.io.TypeMismatchException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author FanMingxin
 * @date 2018-05-16 下午 04:19
 */
public final class ExceptionUtil {

	/**
	 * 生成businessException
	 *
	 * @param message 异常信息
	 * @return 业务异常
	 */
	public static BusinessException ex(final String message, final Object... args) {
		return new BusinessException(formatMessage(message, args));
	}

	public static BusinessException ex(final int code,final String message, final Object... args) {
		return new BusinessException(code,formatMessage(message, args));
	}

	/**
	 * 生成businessException
	 *
	 * @param message 异常信息
	 * @param e 包装的其他异常
	 * @return 业务异常
	 */
	public static BusinessException ex(final String message, final Throwable e, final Object... args) {
		return new BusinessException(formatMessage(message, args), e);
	}

	public static BusinessException ex(final int code,final String message, final Throwable e, final Object... args) {
		return new BusinessException(code,formatMessage(message, args), e);
	}

	/**
	 * 生成RpcException
	 *
	 * @param message 异常信息
	 * @param e 包装的其他异常
	 * @return 调用异常
	 */
	public static RpcException rpcEx(final String message, final Throwable e, final Object... args) {
		return new RpcException(formatMessage(message, args), e);
	}

	public static RpcException rpcEx(final int code,final String message, final Throwable e, final Object... args) {
		return new RpcException(code,formatMessage(message, args), e);
	}

	/**
	 * 生成WarningException
	 *
	 * @param message 异常信息
	 * @return 警告
	 */
	public static WarningException warn(final String message, final Object... args) {
		return new WarningException(formatMessage(message, args));
	}

	public static WarningException warn(final int code,final String message, final Object... args) {
		return new WarningException(code,formatMessage(message, args));
	}

	/**
	 * 判断主键是否为空
	 *
	 * @param id 主键
	 * @param msg 提示信息
	*/
	public static void checkId(final Integer id, final String msg) {
		checkWarn(id == null || id <= 0,msg);
	}

	/**
	 * 判断整型数值是否为空
	 *
	 * @param id 数值
	 * @param code 自定义code值
	 * @param msg  提示信息
	 */
	public static void checkId(final Integer id, int code, final String msg) {
		checkWarn(id == null || id <= 0,code,msg);
	}

	/**
	 * 判断主键是否为空
	 *
	 * @param id 主键
	 * @param msg 提示信息
	*/
	public static void checkId(final Long id, final String msg) {
		checkWarn(id == null || id <= 0,msg);
	}

	/**
	 * 判断长整型数值是否为空
	 *
	 * @param id 数值
	 * @param code 自定义code值
	 * @param msg  提示信息
	 */
	public static void checkId(final Long id,int code, final String msg) {
		checkWarn(id == null || id <= 0,code,msg);
	}

	/**
	 * 判断主键是否为空
	 *
	 * @param id 主键
	 * @param msg 提示信息
	 */
	public static void checkIdEx(final Long id, final String msg) {
		checkEx(id == null || id <= 0,msg);
	}

	/**
	 * 判断整型数值是否为空
	 *
	 * @param id 数值
	 * @param code 自定义code值
	 * @param msg  提示信息
	 */
	public static void checkIdEx(final Long id, int code, final String msg) {
		checkEx(id == null || id <= 0,code,msg);
	}

	/**
	 * 判断主键是否为空
	 *
	 * @param id 主键
	 * @param msg 提示信息
	 */
	public static void checkIdEx(final Integer id, final String msg) {
		checkEx(id == null || id <= 0,msg);
	}

	/**
	 * 判断整型数值是否为空
	 *
	 * @param id 数值
	 * @param code 自定义code值
	 * @param msg  提示信息
	 */
	public static void checkIdEx(final Integer id, int code, final String msg) {
		checkEx(id == null || id <= 0,code,msg);
	}

	/**
	 * 判断是否为空，如果为空则抛出数据异常
	 *
	 * @param obj 判断对象
	 * @param msg 提示信息
	*/
	public static void checkEmpty(final Object obj, final String msg) {
		checkWarn(CommonUtil.isEmpty(obj),msg);
	}

	/**
	 * 判断是否为空，如果为空则抛出数据异常
	 *
	 * @param obj 判断对象
	 * @param code 自定义code值
	 * @param msg 提示信息
	 */
	public static void checkEmpty(final Object obj, int code, final String msg) {
		checkWarn(CommonUtil.isEmpty(obj),code,msg);
	}

	/**
	 * 判断是否为空，如果为空则抛出数据异常
	 *
	 * @param obj 判断对象
	 * @param msg 提示信息
	 */
	public static void checkEmptyEx(final Object obj, final String msg) {
		checkEx(CommonUtil.isEmpty(obj),msg);
	}

	/**
	 * 判断是否为空，如果为空则抛出数据异常
	 *
	 * @param obj 判断对象
	 * @param code 自定义code值
	 * @param msg 提示信息
	 */
	public static void checkEmptyEx(final Object obj, int code, final String msg) {
		checkEx(CommonUtil.isEmpty(obj),code,msg);
	}

	/**
	 * 检查并抛出异常
	 *
	 * @param bool 检查表达式结果
	 * @param code 状态码
	 * @param msg 异常信息
	 */

	public static void checkEx(boolean bool,final Integer code, final String msg) {
		if (bool) {
			throw ex(code,msg);
		}
	}

	/**
	 * 检查并抛出异常
	 *
	 * @param bool 检查表达式
	 * @param msg 异常信息
	 */
	public static void checkEx(boolean bool,final String msg) {
		if (bool) {
			throw ex(msg);
		}
	}

	/**
	 * 检查并抛出异常
	 *
	 * @param bool 检查表达式结果
	 * @param code 状态码
	 * @param msg 异常信息
	 */
	public static void checkWarn(boolean bool,final Integer code, final String msg) {
		if (bool) {
			throw warn(code,msg);
		}
	}

	/**
	 * 检查并抛出异常
	 *
	 * @param bool 检查表达式
	 * @param msg 异常信息
	 */
	public static void checkWarn(boolean bool,final String msg) {
		if (bool) {
			throw warn(msg);
		}
	}

	/**
	 * 获取异常的描述
	 *
	 * @param th 异常
	 * @return java.lang.String
	 */
	public static String getSimpleMessage(final Throwable th) {

		if (th instanceof IBusinessException || th instanceof IRpcException || th instanceof IWarningException){
			return th.getMessage();
		} else if (th instanceof NullPointerException) {
			return "调用了未经初始化的对象或者是不存在的对象！";
		} else if (th instanceof IOException) {
			return "IO异常！";
		} else if (th instanceof ClassNotFoundException) {
			return "指定的类不存在！";
		} else if (th instanceof ArithmeticException) {
			return "数学运算异常！";
		} else if (th instanceof ArrayIndexOutOfBoundsException) {
			return "数组下标越界!";
		} else if (th instanceof ClassCastException) {
			return "类型强制转换错误！";
		} else if (th instanceof SecurityException) {
			return "违背安全原则异常！";
		} else if (th instanceof SQLException) {
			return "操作数据库异常！";
		} else if (th instanceof NoSuchMethodError) {
			return "方法未找到异常！";
		} else if (th instanceof InternalError) {
			return "Java虚拟机发生了内部错误";
		} else if (th instanceof TypeMismatchException) {
			return "类型不匹配";
		} else {
			return "程序内部错误，操作失败！";
		}
	}

	private static String formatMessage(String message,Object... args){
		String content;
		try {
			content = String.format(message, args);
		} catch (Exception e1) {
			content = message;
		}
		return content;
	}
}
