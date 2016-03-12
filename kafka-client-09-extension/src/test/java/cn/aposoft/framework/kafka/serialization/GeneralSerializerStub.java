/**
 * 
 */
package cn.aposoft.framework.kafka.serialization;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 序列化,反序列化测试用例
 * 
 * @author Jann Liu
 *
 */
public class GeneralSerializerStub {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		new GeneralSerializerStub().testSerializeDeserialize();
	}

	@SuppressWarnings("resource")
	@Test
	public void testSerializeDeserialize() {
		Person person = new Person();
		person.setName("张三");
		person.setAge(35);
		person.setSex("男");

		GeneralSerializer se = new GeneralSerializer();
		byte[] pBytes = se.serialize("person", person);
		System.out.println(pBytes.length);
		GeneralDeserializer de = new GeneralDeserializer();
		Person pResult = (Person) de.deserialize("person", pBytes);
		assertEquals(person.toString(), pResult.toString());
	}

}
