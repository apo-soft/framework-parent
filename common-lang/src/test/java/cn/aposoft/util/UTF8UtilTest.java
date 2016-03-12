/**
 * 
 */
package cn.aposoft.util;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import cn.aposoft.util.UTF8Util;

/**
 * @author Jann Liu
 *
 */
public class UTF8UtilTest {
	//
	private static final String DEFAULT_TEXT = "abcdefg12098@#$%%张三和李四。。lsks；‘’“”，。，@#%￥！@3。２２５２１３９１８２３王五ａｎｄ木六，";
	//
	private static final byte[] DEFAULT_ARR = createSrcArray();

	/**
	 * 测试字符串转UTF-8数组
	 */
	@Test
	public void toUtf8ByteArray() {
		byte[] testResultArray = UTF8Util.utf8(DEFAULT_TEXT);

		Assert.assertArrayEquals(DEFAULT_ARR, testResultArray);

	}

	/**
	 * 测试UTF-8数组转字符串
	 */
	@Test
	public void convertUtf8ArrayToString() {
		String resString = UTF8Util.utf8(DEFAULT_ARR);
		assertEquals(DEFAULT_TEXT, resString);
	}

	@Test
	public void utf8Length() {
		int length = UTF8Util.utf8Length(DEFAULT_TEXT);
		assertEquals(length, DEFAULT_ARR.length);
	}

	// 生成default_text对应的utf-8字符数组
	private final static byte[] createSrcArray() {
		byte[] arr1 = new byte[] { 97, 98, 99, 100, 101, 102, 103, 49, 50, 48, 57, 56, 64, 35, 36, 37, 37, -27, -68,
				-96, -28, -72, -119, -27, -110, -116, -26, -99, -114, -27, -101, -101, -29, -128, -126, -29, -128, -126,
				108, 115, 107, 115, -17, -68, -101, -30, -128, -104, -30, -128, -103, -30, -128, -100, -30, -128, -99,
				-17, -68, -116, -29, -128, -126, -17, -68, -116, 64, 35, 37, -17, -65, -91, -17, -68, -127, 64, 51, -29,
				-128, -126, -17, -68, -110, -17, -68, -110, -17, -68, -107, -17, -68, -110, -17, -68, -111, -17, -68,
				-109, -17, -68, -103, -17, -68, -111, -17, -68, -104, -17, -68, -110, -17, -68, -109, -25, -114, -117,
				-28, -70, -108, -17, -67, -127, -17, -67, -114, -17, -67, -124, -26, -100, -88, -27, -123, -83, -17,
				-68, -116 };
		return arr1;
	}

	// 用于格式化输出字符数组
	// private void printArray(byte[] arr) {
	// int i = 0;
	// System.out.print("{");
	// for (byte v : arr) {
	// // System.out.printf("srcArray[%d] = %d;\r\n", i, v);
	// System.out.print(v);
	// if (i < arr.length - 1) {
	// System.out.print(",");
	// }
	// i++;
	// }
	// System.out.println("}");
	// }

}
