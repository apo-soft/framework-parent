package cn.aposoft.util;

import static cn.aposoft.util.HashCodeUtil.hash;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * HashCodeHelper 测试用例
 * 
 * @author Jann Liu
 */
public class HashCodeUtilTest {

	/**
	 * 
	 * 测试Helper类的全部公有方法
	 */
	@Test
	public void testBasicPublicMethods() {
		// 验证byte
		assertEquals(1, hash((byte) 1));
		// 验证short
		assertEquals(1, hash((short) 1));
		// 验证char
		assertEquals(1, hash('\u0001'));

		assertEquals(1, hash(1L));

		assertEquals(Float.floatToIntBits(1.0f), hash(1.0f));
		long l = Double.doubleToLongBits(1.0d);
		assertEquals(hash(l), hash(1.0d));
	}

	/**
	 * 测试Helper类的全部公有方法
	 */
	@Test
	public void testListMethods() {
		assertNotEquals((long) 0, (long) hash(getList()));
	}

	private List<Object> getList() {
		List<Object> list = new ArrayList<Object>();
		list.add("Only");
		list.add(1);
		list.add(1.0f);
		return list;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int i = hash(1.0f);
		System.out.println(i);
		long l = Double.doubleToLongBits(1.0d);
		System.out.println(l);
	}
}
