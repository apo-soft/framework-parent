/**
 * 
 */
package cn.aposoft.util;

import java.io.UnsupportedEncodingException;

/**
 * help to change String to UTF-8 byte[] <br/>
 * and convert UTF-8 byte[] to String <br />
 * count UTF-8 byte[].length of a given String
 * 
 * @author Jann Liu
 *
 */
public class UTF8Util {
	/**
	 * Turn the given UTF8 byte array into a string
	 *
	 * @param bytes
	 *            The byte array
	 * @return The string
	 */
	public static String utf8(byte[] bytes) {
		try {
			return new String(bytes, "UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("This shouldn't happen.", e);
		}
	}

	/**
	 * Turn a string into a utf8 byte[]
	 *
	 * @param string
	 *            The string
	 * @return The byte[]
	 */
	public static byte[] utf8(String string) {
		try {
			return string.getBytes("UTF8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("This shouldn't happen.", e);
		}
	}

	/**
	 * Get the length for UTF8-encoding a string without encoding it first
	 *
	 * @param s
	 *            The string to calculate the length for
	 * @return The length when serialized
	 */
	public static int utf8Length(CharSequence s) {
		int count = 0;
		for (int i = 0, len = s.length(); i < len; i++) {
			char ch = s.charAt(i);
			if (ch <= 0x7F) {
				count++;
			} else if (ch <= 0x7FF) {
				count += 2;
			} else if (Character.isHighSurrogate(ch)) {
				count += 4;
				++i;
			} else {
				count += 3;
			}
		}
		return count;
	}
}
