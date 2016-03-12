/**
 * Copyright: aposoft.cn 2015~~Now
 */
package cn.aposoft.util;

import java.util.List;

/**
 * hash值计算帮助类:根据Effective Java中的实例来进行标准的Object求HashCode
 * <p>
 * 算法为:
 * 
 * <pre>
 * 确定SEED魔数为17, 乘数为31.
 * 对于List内部的全部对象
 * 初始化result=17;
 * 执行以下操作: result = result * 31 + o.hashCode();
 * 针对字长小于int的数字去本值
 * 针对long: (int) (l ^ (l >>> 32));
 * float: Float.floatToIntBits(f);
 * double: l=Double.doubleToLongBits(d);  然后根据long求int值
 * </pre>
 * </p>
 * <date>2015-09-22</date>
 * 
 * @author Jann Liu
 * @version 1.0
 */
public final class HashCodeUtil {
	// 根据Effective Java 的demo计算hash值
	private static final int HASH_SEED = 17;
	private static final int MULTIPLIER = 31;

	/**
	 * 返回初始魔数
	 * 
	 * @return 魔数值
	 */
	public static final int magic() {
		return HASH_SEED;
	}

	/**
	 * 返回乘数
	 * 
	 * @return 乘数值
	 */
	public static final int multiplier() {
		return MULTIPLIER;
	}

	/**
	 * 将对象的主属性放入列表可以用于计算hash值
	 * 
	 * @param list
	 *            需要计算hash值的对象列表
	 * @return 对象列表对应的hash值
	 */
	public static final int hash(List<Object> list) {
		int result = HASH_SEED;
		for (Object o : list) {
			// Int溢出不会抛出异常
			result += result * MULTIPLIER + hash(o);
		}
		return result;
	}

	/**
	 * 计算对象的hash值
	 * 
	 * @param o
	 *            需要计算hash值的对象
	 * @return 如果o==null 返回0 否则返回 o.hashCode()
	 */
	public static final int hash(Object o) {
		if (o == null) {
			return 0;
		} else {
			return o.hashCode();
		}
	}

	public static final int hash(boolean b) {
		return b ? 1 : 0;
	}

	public static final int hash(byte b) {
		return (int) b;
	}

	public static final int hash(char c) {
		return (int) c;
	}

	public static final int hash(short s) {
		return (int) s;
	}

	public static final int hash(int i) {
		return i;
	}

	public static final int hash(long l) {
		return (int) (l ^ (l >>> 32));
	}

	public static final int hash(float f) {
		return Float.floatToIntBits(f);
	}

	public static final int hash(double d) {
		return hash(Double.doubleToLongBits(d));
	}
}
